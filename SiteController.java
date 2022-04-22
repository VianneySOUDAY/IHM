import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javafx.scene.control.TextArea;

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
 * @version 22/04/2022
 */
public class SiteController implements Initializable
{  
    @FXML private Label monLabel = new Label("un label vide");
    
    @FXML private ChoiceBox<String> debutBox;
    @FXML private ChoiceBox<String> dureeBox;
    @FXML private ChoiceBox<String> cibleBox;
    @FXML private ChoiceBox<String> trieBox;
    @FXML private Spinner<Integer> nombreSpinner;
    @FXML private TextArea nomText;
    @FXML private TextArea sujetText;
    
    @FXML private TableView<Stage> stagesTable;
    @FXML private TableColumn<Stage,String> sujetStage;
    @FXML private TableColumn<Stage,String> nomEntreprise;
    
    StageGphy unStage; 
    
    @FXML
    /**
     * Methode modifierClick
     * permet de valider la modification faites sur les champs d'un stage
     */
    private void modifierClick(ActionEvent event)
    {
        Connexion connexion = new Connexion("ScriptSQL_IHM.db");
        connexion.connect();
        ResultSet resultSet = connexion.query("SELECT * FROM STAGE");
        try {
            while (resultSet.next()) {
                monLabel.setText(resultSet.getString("entreprise"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         connexion.close();
    }
    
    @FXML
    /**
     * Methode supprimerClick
     * permet de supprimer le stage sélectionné
     */
    private void supprimerClick(ActionEvent event)
    {
        monLabel.setText("clic sur supprimer");
    }
    
    @FXML
    /**
     * Methode abandonnerClick
     * permet d'abandonné les mofications faites les données d'un stage
     */
    private void abandonnerClick(ActionEvent event)
    {
        monLabel.setText("clic sur abandonner");
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
         connexion.close();
    }
    
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
        nomEntreprise.setCellValueFactory(new PropertyValueFactory<Stage,String>("nomEntreprise"));
        sujetStage.setCellValueFactory(new PropertyValueFactory<Stage,String>("sujetStage"));
        Connexion connexion = new Connexion("ScriptSQL_IHM.db");
        connexion.connect();
        ResultSet resultSet = connexion.query("SELECT * FROM STAGE");
        
        try {
            while (resultSet.next()) {
                unStage = new StageGphy(resultSet.getString("entreprise"), resultSet.getString("sujet"));
                sujetStage.setCellValueFactory(new PropertyValueFactory<Stage,String>("sujetStage"));
                stagesTable.getItems();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         connexion.close();
        
    }
}
    