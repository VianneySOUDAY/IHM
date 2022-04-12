import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

/**
 * @author Vianney SOUDAY, Arthur MESLIN, Mathew FAILLOT
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
        stage.show();
    }
}