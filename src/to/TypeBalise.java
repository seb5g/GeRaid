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
public enum TypeBalise
{
  BONIFICATION("Bonification") , CONTROLE("Contrôle");
  
  private final String value;
  
  TypeBalise(String value) 
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
