
/**
 * Classe Stage
 * Permet de définir ce qui doit être dans la TableView
 * 
 * @author SOUDAY Vianney / MESLIN Arthur / FAILLOT Mathew
 * @version 24/04/2022
 */
public class StageGphy
{
    String nomEntreprise;
    String sujetStage;
    String debutStage;
    int duree;
    String dureeUnite;
    String promotion;
    int id;
    
    /**
     * Constructeur StageGphy
     * définie un stage correspondant aux critères de la base de données
     */
    public StageGphy(String nomEntreprise, String sujetStage, String debutStage, int duree, String dureeUnite, String promotion, int id)
    {
        this.nomEntreprise = nomEntreprise;
        this.sujetStage = sujetStage;
        this.debutStage = debutStage;
        this.duree = duree;
        this.dureeUnite = dureeUnite;
        this.promotion = promotion;
        this.id = id;
        
    } 
    
    /**
     * Méthode getId
     * @return id
     */
    public int getId(){
        return id;
    }
    
    /**
     * Méthode getDebutStage
     * @return debutStage
     */
    public String getDebutStage(){
        return debutStage;
    }
    
    /**
     * Méthode getDuree
     * @return duree
     */
    public int getDuree(){
        return duree;
    }
    
    /**
     * Méthode getDureeUnite
     * @return DureeUnite
     */
    public String getDureeUnite(){
        return dureeUnite;
    }
    
    /**
     * Méthode getPromotion
     * @return promotion
     */
    public String getPromotion(){
        return promotion;
    }
    
    /**
     * Méthode getNomEntreprise
     * @return nomEntreprise
     */
    public String getNomEntreprise()
    {
        return nomEntreprise;
    }
    
    /**
     * Méthode getSujetStage
     * @return sujetStage
     */
    public String getSujetStage()
    {
        return sujetStage;
    }
    
    /**
     * Méthode setNomEntreprise
     * @param nomEntreprise
     */
    public void setNomEntreprise(String nomEntreprise)
    {
        this.nomEntreprise = nomEntreprise;
    }
    
    /**
     * Méthode setSujetStage
     * @param sujetStage
     */
    public void setSujetStage(String sujetStage)
    {
        this.sujetStage = sujetStage;
    }
}
