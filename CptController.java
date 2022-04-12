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
 * @author Vianney SOUDAY
 * @version 04/04/2022
 */
public class CptController
{
    private int count = 0;
    
    @FXML
    private Label myLabel = new Label("0");
    
    @FXML
    private void buttonClick(ActionEvent event)
    {
        count = count + 1;
        myLabel.setText(Integer.toString(count));
    }
}