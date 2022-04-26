import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.scene.control.SelectionModel;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

import java.io.File;
import java.net.URISyntaxException;
import javafx.collections.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.TextArea;
import java.sql.*;
import java.time.LocalDateTime;
// pour remplir les box
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

// pour communiquer avec la bdd 
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javafx.scene.control.cell.PropertyValueFactory; // pour remplir un TableView


/**
 * Classe SiteController
 * Permet d'interragir avec la fenêtre : donne des actions aux boutons.
 * 
 * @author SOUDAY Vianney / MESLIN Arthur / FAILLOT Mathew
 * @version 24/04/2022
 */
public class SiteController implements Initializable
{   
   @FXML private ChoiceBox<String> debutBox;
   @FXML private ChoiceBox<String> dureeBox;
   @FXML private ChoiceBox<String> cibleBox;
   @FXML private ChoiceBox<String> trieBox;
   
   private ObservableList<StageGphy> data;
   
   @FXML private Spinner<Integer> nombreSpinner;
   @FXML private TextArea nomText;
   @FXML private TextArea sujetText;
   @FXML private TableView<StageGphy> stagesTable;
   @FXML private TableColumn<StageGphy,String> sujetStage;
   @FXML private TableColumn<StageGphy,String> nomEntreprise;
   
   StageGphy unStage; 
   StageGphy precStage;
   TableView.TableViewSelectionModel precSelec;
  
   @FXML
   /**
    * Methode modifierClick
    * permet de valider la modification faites sur les champs d'un stage
    */
   private void modifierClick(ActionEvent event)
   {
       Connexion connexion = new Connexion("ScriptSQL_IHM.db");
       connexion.connect();
       StageGphy stag = stagesTable.getSelectionModel().getSelectedItem();
       if (stag != null){
           precStage = stag;
           precSelec = stagesTable.getSelectionModel();
       }else{
           stagesTable.setSelectionModel(precSelec);
           stagesTable.getSelectionModel().select(precStage);
           stag = stagesTable.getSelectionModel().getSelectedItem();
       }
       
       try {
          PreparedStatement preparedStatement = Connexion.getConnection()
              .prepareStatement
              ("UPDATE STAGE SET entreprise = ?, sujet= ?, debutStage = ?, dureeNb = ?, dureeUnite = ?, promotion = ? WHERE idS = ?");
                         
          preparedStatement.setString(1, nomText.getText()); // entrerpise
          preparedStatement.setString(2, sujetText.getText()); // sujet
          preparedStatement.setString(3, debutBox.getValue());   // debut
          preparedStatement.setInt(4, nombreSpinner.getValue()); // duree nombre
          preparedStatement.setString(5, dureeBox.getValue());   // duree Unité
          preparedStatement.setString(6, cibleBox.getValue());   // promotion cible
               
          preparedStatement.setInt(7, stag.getId());
               
          int retour = JOptionPane.showConfirmDialog(null,
          "Êtes-vous sûr ?", 
          "Confirmation",
          JOptionPane.OK_CANCEL_OPTION);
          System.out.println( retour);
           
          if (retour == 0){
              preparedStatement.executeUpdate();
          }
          else{
              connexion.close();
          }
          } catch (SQLException e) {
              e.printStackTrace();
          }
       // on met à jour l'affichage de la TableView
       String choice = trieBox.getValue();
       GetStage(choice, connexion);
       connexion.close();
       JOptionPane.showMessageDialog(null,"Modification enregistrée !");
       // on réinitialise le formulaire
       nomText.setText("");
       sujetText.setText("");
       debutBox.setValue("Mai");
       SpinnerValueFactory<Integer> nombreValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,360,3); // min=1; max=360; val. par défaut=3
       nombreSpinner.setValueFactory(nombreValueFactory);
       dureeBox.setValue("Mois");
       cibleBox.setValue("L3");
   }
    
   @FXML
   /**
    * Methode supprimerClick
    * permet de supprimer le stage sélectionné
    */
   private void supprimerClick(ActionEvent event)
   {
       Connexion connexion = new Connexion("ScriptSQL_IHM.db");
       connexion.connect();
       StageGphy stag = stagesTable.getSelectionModel().getSelectedItem();
       if (stag != null){
           precStage = stag;
           precSelec = stagesTable.getSelectionModel();
       }else{
           stagesTable.setSelectionModel(precSelec);
           stagesTable.getSelectionModel().select(precStage);
           stag = stagesTable.getSelectionModel().getSelectedItem();
       }
       int retour = JOptionPane.showConfirmDialog(null,
       "Êtes-vous sûr ?", 
       "Confirmation",
       JOptionPane.OK_CANCEL_OPTION);
       System.out.println( retour);
    
       if (retour == 0) {
          ResultSet resultSet = connexion.query("DELETE FROM STAGE WHERE (idS = " + stag.getId() + ")");
          // on met à jour l'affichage de la TableView
          String choice = trieBox.getValue();
          GetStage(choice, connexion);
          connexion.close();
          JOptionPane.showMessageDialog(null,"Stage supprimé !");
       }else {
           connexion.close();
       }
   }
   
   @FXML
   /**
    * Methode abandonnerClick
    * permet d'abandonné les mofications faites les données d'un stage
    */
   private void abandonnerClick(ActionEvent event)
   {
       Connexion connexion = new Connexion("ScriptSQL_IHM.db");
       connexion.connect();
           
       StageGphy stag = stagesTable.getSelectionModel().getSelectedItem();
           
       if (stag!=null){
           precStage = stag;
           precSelec = stagesTable.getSelectionModel();
       }else{
           stagesTable.setSelectionModel(precSelec);
           stagesTable.getSelectionModel().select(precStage);
           stag = stagesTable.getSelectionModel().getSelectedItem();
       }
           
       String nom = stag.getNomEntreprise();
       if (nomText != null){nomText.setText(nom);}
       sujetText.setText(stag.getSujetStage());
       debutBox.setValue(stag.getDebutStage());
       SpinnerValueFactory<Integer> dureeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,360,stag.getDuree()); // min=1; max=360; val. par défaut=3
       nombreSpinner.setValueFactory(dureeValueFactory);
       dureeBox.setValue(stag.getDureeUnite());
       cibleBox.setValue(stag.getPromotion());
       String choice = trieBox.getValue();
       GetStage(choice, connexion);
       
       connexion.close();
       JOptionPane.showMessageDialog(null,"Modification annulée !");
   }

   
   @FXML
   /**
    * Methode ajouterClick
    * permet d'ajouter un stage -> remplir le formulaire
    */
   private void ajouterClick(ActionEvent event)
   {
       Connexion connexion = new Connexion("ScriptSQL_IHM.db");
       connexion.connect();
       // on place le stage dans la base de donnée
       try {
           PreparedStatement preparedStatement = Connexion.getConnection()
               .prepareStatement("INSERT INTO STAGE(entreprise,sujet,debutStage,dureeNb,dureeUnite,promotion) VALUES(?,?,?,?,?,?)");
           preparedStatement.setString(1, nomText.getText()); // entrerpise
           preparedStatement.setString(2, sujetText.getText()); // sujet
           preparedStatement.setString(3, debutBox.getValue());   // debut
           preparedStatement.setInt(4, nombreSpinner.getValue()); // duree nombre
           preparedStatement.setString(5, dureeBox.getValue());   // duree Unité
           preparedStatement.setString(6, cibleBox.getValue());   // promotion cible
           preparedStatement.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       JOptionPane.showMessageDialog(null,"Stage ajouté !");
       // on met à jour l'affichage de la TableView
       String choice = trieBox.getValue();
       GetStage(choice, connexion);
       connexion.close();
       // on réinitialise le formulaire
       nomText.setText("");
       sujetText.setText("");
       debutBox.setValue("Mai");
       SpinnerValueFactory<Integer> nombreValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,360,3); // min=1; max=360; val. par défaut=3
       nombreSpinner.setValueFactory(nombreValueFactory);
       dureeBox.setValue("Mois");
       cibleBox.setValue("L3");
   }
   
   /**
    * Méthode popupHelp
    * Permet d'afficher un popup expliquant comment marche l'interface.
    */
   @FXML
   public void popupHelp(ActionEvent event)
   {
       String message1 = "Pour AJOUTER un stage : \n Remplissez le formulaire (partie droite de l'écran), puis cliquez sur le bouton [Ajouter un Stage] situé en bas, à gauche \n\n"; 
       String message2 = "Pour CONSULTER un stage : \n Cliquer sur le stage d'intérêt se trouver dans la liste (à gauche de l'écran)\n\n";
       String message3 = "Pour MODIFIER un stage : \n Ouvrez le stage à modifier en consultation, modifiez les zones devant être modifiés et cliquez sur le bouton [Modifier]\n\n ";
       String message4 = "Pour SUPPRIMER un stage : \n Ouvrez le stage à modifier en consultation. Puis, cliquer sur le bouton [Supprimer]";
       
       String message = message1 + message2 + message3 + message4;
       
       JOptionPane.showMessageDialog(null,message);
   }
   
   /**
    * Méthode getStage
    * permet d'afficher les stages dans la TableView en fonction du choiceBox trieBox
    * @param choice, connexion
    */
   public void GetStage(String choice, Connexion connexion){
       connexion.connect();
       ResultSet resultSet = connexion.query("SELECT * FROM STAGE;");
       if (choice == "Uniquement les stages de L3"){
       resultSet = connexion.query("SELECT * FROM STAGE where (promotion ='L3')");
       }
       else if (choice == "Uniquement les stages de M1"){
           resultSet = connexion.query("SELECT * FROM STAGE where (promotion ='M1')");
       }
       else if (choice == "Uniquement les stages de M2"){
           resultSet = connexion.query("SELECT * FROM STAGE where (promotion ='M2')");
       }
       data = FXCollections.observableArrayList();
            
       try {
           while (resultSet.next()) {
               unStage = new StageGphy(resultSet.getString("entreprise"), resultSet.getString("sujet"), resultSet.getString("debutStage"),resultSet.getInt("dureeNb"),
               resultSet.getString("dureeUnite"), resultSet.getString("promotion"), resultSet.getInt("idS"));
               sujetStage.setCellValueFactory(new PropertyValueFactory<StageGphy,String>("sujetStage"));
               
               stagesTable.getItems();
               data.add(unStage);
               
                
           }
           stagesTable.setItems(data);
            
       } catch (SQLException e) {
           e.printStackTrace();
       }
       connexion.close();
        
   }
   
   /**
    * Méthode initialize
    * permet d'initialiser tous les éléments de l'interface
    * @param url, rb
    */
   @Override
   public void initialize(URL url, ResourceBundle rb)
   {
       debutBox.getItems().addAll("Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre");
       debutBox.setValue("Mai");
       
       dureeBox.getItems().addAll("Jours","Semaines","Mois");
       dureeBox.setValue("Mois");
       
       cibleBox.getItems().addAll("L3","M1","M2");
       cibleBox.setValue("L3");
       
       trieBox.getItems().addAll("Tous les stages", "Uniquement les stages de L3", "Uniquement les stages de M1", "Uniquement les stages de M2");
       trieBox.setValue("Tous les stages");
       
       SpinnerValueFactory<Integer> nombreValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,360,3); // min=1; max=360; val. par défaut=3
       nombreSpinner.setValueFactory(nombreValueFactory);
       
       // remplissage de TableView
       nomEntreprise.setCellValueFactory(new PropertyValueFactory<StageGphy,String>("nomEntreprise"));
       sujetStage.setCellValueFactory(new PropertyValueFactory<StageGphy,String>("sujetStage"));
       Connexion connexion = new Connexion("ScriptSQL_IHM.db");
       connexion.connect();
       String choice = trieBox.getValue();
       GetStage(choice, connexion);
       
       // Trie des stages à afficher dans la TableView
       trieBox.setOnAction((event)->{
           String choice2 = trieBox.getValue();
           GetStage(choice2, connexion);
       });
       
       // Remplissage du form quand on clique sur un stage dans la TableView
       stagesTable.setOnMouseClicked((event)->{
           StageGphy stag = stagesTable.getSelectionModel().getSelectedItem();
           if (stag!=null){
               String nom = ".";
               nom = stag.getNomEntreprise();
               if (nomText != null){nomText.setText(nom);}
               
               sujetText.setText(stag.getSujetStage());
               debutBox.setValue(stag.getDebutStage());
               SpinnerValueFactory<Integer> dureeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,360,stag.getDuree()); // min=1; max=360; val. par défaut=3
               nombreSpinner.setValueFactory(dureeValueFactory);
               dureeBox.setValue(stag.getDureeUnite());
               cibleBox.setValue(stag.getPromotion());
           }
       });
       connexion.close();
       
   }
}
    