/**
 * 
 */
package to;

import java.util.Vector;

/**
 * <P>
 * Titre : PenalitesIndividuelles.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author Thierry
 *
 */
public class PenalitesIndividuelles
{
  private String dossard = "";
  private String puce = "";
  private String equipe = "";
  private Vector<String> points = new Vector<String>();
  private Vector<String> temps = new Vector<String>();
  
  public PenalitesIndividuelles()
  {
    
  }

  /**
   * @return the dossard
   */
  public String getDossard()
  {
    return dossard;
  }

  /**
   * @param dossard the dossard to set
   */
  public void setDossard(String dossard)
  {
    this.dossard = dossard;
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
   * @return the equipe
   */
  public String getEquipe()
  {
    return equipe;
  }

  /**
   * @param equipe the equipe to set
   */
  public void setEquipe(String equipe)
  {
    this.equipe = equipe;
  }

  /**
   * @return the points
   */
  public Vector<String> getPoints()
  {
    return points;
  }

  /**
   * @param points the points to set
   */
  public void setPoints(Vector<String> points)
  {
    this.points = points;
  }

  /**
   * @return the temps
   */
  public Vector<String> getTemps()
  {
    return temps;
  }

  /**
   * @param temps the temps to set
   */
  public void setTemps(Vector<String> temps)
  {
    this.temps = temps;
  }
}
