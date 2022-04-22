
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
    
    public StageGphy(String nomEntreprise, String sujetStage)
    {
        this.nomEntreprise = nomEntreprise;
        this.sujetStage = sujetStage;
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
