/**
 * 
 */
package to;

/**
 * <P>
 * Titre : TypeDepard.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public enum TypeDepart
{
  GROUPE("Group�"), BOITIER("Au boitier");
  
  private final String value;
  
  TypeDepart(String value) 
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
