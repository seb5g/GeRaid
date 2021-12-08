/**
 * 
 */
package to;

/**
 * <P>
 * Titre : TypeBalise.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public enum TypeTri
{
  DOSSARD("Dossard") , NOM("Nom d'�quipe") , PUCE("Puce") , CATEGORIE("Cat�gorie");
  
  private final String value;
  
  TypeTri(String value) 
  {
    this.value = value;
  }
  
  public String getValue() 
  {
    return this.value;
  }
  
  public String toString()
  {
    return value;
  }
  
}
