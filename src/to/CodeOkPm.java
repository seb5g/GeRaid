/**
 * 
 */
package to;

/**
 * <P>
 * Titre : CodeOkPm.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author Thierry
 *
 */
public class CodeOkPm
{
  private int code = 0;
  private boolean oK = false;
  private String temps = "PM";
  
  public CodeOkPm()
  {
    
  }
  
  public CodeOkPm(int code, boolean ok, String temps)
  {
    this.code = code;
    this.oK = ok;
    if(temps != null)
    {
      this.temps = temps;
    }
  }

  /**
   * @return the code
   */
  public int getCode()
  {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(int code)
  {
    this.code = code;
  }

  /**
   * @return the oK
   */
  public boolean isoK()
  {
    return oK;
  }

  /**
   * @param oK the oK to set
   */
  public void setoK(boolean oK)
  {
    this.oK = oK;
  }

  /**
   * @return the temps
   */
  public String getTemps()
  {
    return temps;
  }

  /**
   * @param temps the temps to set
   */
  public void setTemps(String temps)
  {
    this.temps = temps;
  }

}
