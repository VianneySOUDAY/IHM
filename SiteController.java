import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Classe SiteController
 * Permet d'interragir avec la fenêtre : donne des actions aux boutons.
 * 
 * @author SOUDAY Vianney / MESLIN Arthur / FAILLOT Mathew
 * @version 12/04/2022
 */
public class SiteController implements Initializable
{  
    @FXML private Label monLabel = new Label("un label vide");
    
    // éléments pour les choiceBox
    @FXML private ChoiceBox<String> debutBox;
    @FXML private ChoiceBox<String> dureeBox;
    @FXML private ChoiceBox<String> cibleBox;
    @FXML private ChoiceBox<String> trieBox;
    
    @FXML
    /**
     * Methode modifierClick
     * permet de valider la modification faites sur les champs d'un stage
     */
    private void modifierClick(ActionEvent event){
        monLabel.setText("clic sur modifier\n"+debutBox.getValue());
    }
    
    @FXML
    /**
     * Methode supprimerClick
     * permet de supprimer le stage sélectionné
     */
    private void supprimerClick(ActionEvent event){
        monLabel.setText("clic sur supprimer");
    }
    
    @FXML
    /**
     * Methode abandonnerClick
     * permet d'abandonné les mofications faites les données d'un stage
     */
    private void abandonnerClick(ActionEvent event){
        monLabel.setText("clic sur abandonner");
    }
    
    @FXML
    /**
     * Methode ajouterClick
     * permet d'ajouter un stage -> remplir le formulaire
     */
    private void ajouterClick(ActionEvent event){
        monLabel.setText("clic sur ajouter un stage");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        debutBox.getItems().addAll("Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre");
        debutBox.setValue("Janvier");
        
        dureeBox.getItems().addAll("Jours","Semaines","Mois");
        dureeBox.setValue("Jours");
        
        cibleBox.getItems().addAll("L3","M1","M2");
        cibleBox.setValue("L3");
        
        trieBox.getItems().addAll("Tous les stages", "Uniquement les stages de L3", "Uniquement les stages de M1", "Uniquement les stages de M2");
        trieBox.setValue("Tous les stages");
    }
}
    