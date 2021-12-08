/**
 * 
 */
package to;

/**
 * <P>
 * Titre : TypeVisualisation.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public enum TypeVisualisation
{
  SIMPLE("Simple"), AVECEPREUVE("Avec épreuves"), COMPLET("Totale");
  
  private final String value;
  
  TypeVisualisation(String value) 
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
