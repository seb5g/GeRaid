/**
 * 
 */
package to;


/**
 * <P>
 * Titre : ResultatBalise.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author Thierry
 *
 */
public class ResultatBalise
{
  private int points =0;
  private long temps = 0;
  private boolean pm = false;
  private String time = "";
  private int code =0;
  
  public ResultatBalise()
  {
    
  }

  /**
   * @return the points
   */
  public String getTime()
  {
    return time;
  }

  /**
   * @param points the points to set
   */
  public void setTime(String time)
  {
    this.time = time;
  }

  /**
   * @return the points
   */
  public int getCode()
  {
    return code;
  }

  /**
   * @param points the points to set
   */
  public void setCode(int code)
  {
    this.code = code;
  }


  /**
   * @return the points
   */
  public int getPoints()
  {
    return points;
  }

  /**
   * @param points the points to set
   */
  public void setPoints(int points)
  {
    this.points = points;
  }

  /**
   * @return the temps
   */
  public long getTemps()
  {
    return temps;
  }

  /**
   * @param temps the temps to set
   */
  public void setTemps(long temps)
  {
    this.temps = temps;
  }

  /**
   * @return the pm
   */
  public boolean isPm()
  {
    return pm;
  }

  /**
   * @param pm the pm to set
   */
  public void setPm(boolean pm)
  {
    this.pm = pm;
  }

}
