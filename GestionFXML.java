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
// java JDBC API
import java.sql.JDBCType;



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
        
        // connexion avec la base de données sur sqlite
        Class.forName("org.sqlite.JDBC"); //force l'utilisation de la classe
        DriverManager.registerDriver(new org.sqlite.JDBC()); //enregistre la classe avec le drive DriverManager
    }
    
    /*
     * Méthode main
     * pour le jar
     */
    public static void main(String[] args) {
        checkDrivers();    //vérifie si il y a des erreurs de driver
    }

    /*
     * Méthode checkDrivers
     * utlisé dans le main pour vérifier qu'il n'y ait pas d'erreurs avec le driver 
     */
    private static boolean checkDrivers() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            return true;
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not start SQLite Drivers");
            return false;
        }
    }
}