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
public enum TypeVisualisationEpreuve
{
  SIMPLE("Simple"), AVECBALISES("Avec balises");
  
  private final String value;
  
  TypeVisualisationEpreuve(String value) 
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
