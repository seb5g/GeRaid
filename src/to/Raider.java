/**
 * 
 */
package to;

/**
 * <P>
 * Titre : Raider.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Raider
{
  private String nom = "";
  private String prenom = "";
  
  public Raider()
  {
    
  }
  
  public Raider(String nom, String prenom)
  {
    setNom(nom);
    setPrenom(prenom);
  }

  /**
   * @return the nom
   */
  public String getNom()
  {
    return nom;
  }

  /**
   * @param nom the nom to set
   */
  public void setNom(String nom)
  {
    this.nom = nom.trim();
  }

  /**
   * @return the prenom
   */
  public String getPrenom()
  {
    return prenom;
  }

  /**
   * @param prenom the prenom to set
   */
  public void setPrenom(String prenom)
  {
    this.prenom = prenom.trim();
  }
  
  public String toString()
  {
    return nom + " " + prenom;
  }
  
}
