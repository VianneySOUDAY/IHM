import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Classe Connexion
 * Permet la connexion avec la base de données en SQLite
 * 
 * @author SOUDAY Vianney / MESLIN Arthur / FAILLOT Mathew
 * @version 22/04/2022
 */
public class Connexion 
{
   private String DBPath = "ScriptSQL_IHM.db";
   private Connection connection = null;
   private Statement statement = null;
 
   public Connexion(String dBPath) 
   {
       DBPath = dBPath;
   }
    
   /*
    * Méthode connect
    * permet de se connecter à la base de données
    */
   public void connect() 
   {
       try {
           Class.forName("org.sqlite.JDBC");
           connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
           statement = connection.createStatement();
          System.out.println("Connexion a " + DBPath + " avec succès");
       } catch (ClassNotFoundException notFoundException) {
           notFoundException.printStackTrace();
           System.out.println("Erreur de connecxion");
       } catch (SQLException sqlException) {
           sqlException.printStackTrace();
           System.out.println("Erreur de connecxion");
       }
   }
    
   /*
    * Méthode close
    * Permet de rompre la connexion avec la base de données
    */
   public void close() 
   {
       try {
           connection.close();
           statement.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

   /*
    * Méthode query
    * @parm requête
    * @return objet de type ReslutSet
    */
    public ResultSet query(String requet) {
       ResultSet resultat = null;
       try {
           resultat = statement.executeQuery(requet);
       } catch (SQLException e) {
           e.printStackTrace();
           System.out.println("Erreur dans la requet : " + requet);
       }
       return resultat;
 
   }
}