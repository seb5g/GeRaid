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
public enum TypeVisualisationParcours
{
  SIMPLE("Simple"), AVECETAPE("Avec étapes");
  
  private final String value;
  
  TypeVisualisationParcours(String value) 
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
