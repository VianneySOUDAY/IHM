import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

/**
 * Classe GestionFXML
 * Permet de creer notre fenêtre à partir du fichier .FXML
 * 
 * @author SOUDAY Vianney / MESLIN Arthur / FAILLOT Mathew
 * @version 05/04/2022
 */
public class GestionFXML extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("interface.fxml"));
        
        Scene scene = new Scene (root);
        stage.setTitle("Gestion des stages");
               
        stage.setScene(scene); 
        scene.getStylesheets().add("style.css");
        stage.show();
        
    }
}