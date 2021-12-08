/**
 * 
 */
package to;

/**
 * <P>
 * Titre : Penalite.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author Thierry
 *
 */
public class PenaliteIndividuelle
{
  private String puce = "";
  private int point = 0;
  private int temps = 0;
  
  public PenaliteIndividuelle()
  {
    
  }

  /**
   * @return the puce
   */
  public String getPuce()
  {
    return puce;
  }

  /**
   * @param puce the puce to set
   */
  public void setPuce(String puce)
  {
    this.puce = puce;
  }

  /**
   * @return the point
   */
  public int getPoint()
  {
    return point;
  }

  /**
   * @param point the point to set
   */
  public void setPoint(int point)
  {
    if(getTemps()<0)
    {
      this.point =  Math.abs(point);
    }
    else if(getTemps()==0)
    {
      this.point = point;
    }
    else
    {
      this.point = - Math.abs(point);
    }
  }

  /**
   * @return the temps
   */
  public int getTemps()
  {
    return temps;
  }

  /**
   * @param temps the temps to set
   */
  public void setTemps(int temps)
  {
    if(getPoint()<0)
    {
      this.temps = Math.abs(temps);
    }
    else if(getPoint()==0)
    {
      this.temps = temps;
    }
    else
    {
      this.temps = - Math.abs(temps);
    }
  }
  
}
