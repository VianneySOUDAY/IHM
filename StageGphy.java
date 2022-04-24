
/**
 * Classe Stage
 * Permet de définir ce qui doit être dans la TableView
 * 
 * @author SOUDAY Vianney / MESLIN Arthur / FAILLOT Mathew
 * @version 22/04/2022
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
    public int getId(){
        return id;
    }
    public String getDebutStage(){
        return debutStage;
    }
    public int getDuree(){
        return duree;
    }
    public String getDureeUnite(){
        return dureeUnite;
    }
    public String getPromotion(){
        return promotion;
    }
    public String getNomEntreprise()
    {
        return nomEntreprise;
    }
    
    public String getSujetStage()
    {
        return sujetStage;
    }
    
    public void setNomEntreprise(String nomEntreprise)
    {
        this.nomEntreprise = nomEntreprise;
    }
    
    public void setSujetStage(String sujetStage)
    {
        this.sujetStage = sujetStage;
    }
}
