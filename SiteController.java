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

/**
 * Classe SiteController
 * Permet d'interragir avec la fenêtre : donne des actions aux boutons.
 * 
 * @author SOUDAY Vianney / MESLIN Arthur / FAILLOT Mathew
 * @version 12/04/2022
 */
public class SiteController
{  
    @FXML
    private Label monLabel = new Label("un label vide");
    
    @FXML
    /**
     * Methode modifierClick
     * permet de valider la modification faites sur les champs d'un stage
     */
    private void modifierClick(ActionEvent event){
        monLabel.setText("clic sur modifier");
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
    
}