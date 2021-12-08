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
public enum TypeLimite
{
  SANSLIMITE("Sans limite") , AVECLIMITETEMPS("Avec limite de temps") , AVECLIMITEHORAIRE("Avec limite horaire");
  
  private final String value;
  
  TypeLimite(String value) 
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
