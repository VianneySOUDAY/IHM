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
// biblio pour utiliser la base de donnée
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.time.LocalDateTime;
import java.sql.ResultSet;

/**
 * Classe GestionFXML
 * Permet de creer notre fenêtre à partir du fichier .FXML
 * 
 * @author SOUDAY Vianney / MESLIN Arthur / FAILLOT Mathew
 * @version 22/04/2022
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