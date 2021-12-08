/**
 * 
 */
package to;

import java.util.Vector;

/**
 * <P>
 * Titre : ResultatEpreuves.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author Thierry
 *
 */
public class ResultatEpreuves
{
  
  private Vector<ResultatEpreuve> resultatEpreuves = null;
  
  public ResultatEpreuves()
  {
    resultatEpreuves = new Vector<ResultatEpreuve>();
  }

  /**
   * @return the resultatEpreuves
   */
  public Vector<ResultatEpreuve> getResultatEpreuves()
  {
    return resultatEpreuves;
  }
  
  public void addResultatEpreuve(Epreuve e, ResultatPuce rp)
  {
    resultatEpreuves.add(new ResultatEpreuve(e, rp));
  }

  /**
   * @param resultatEpreuves the resultatEpreuves to set
   */
  public void setResultatEpreuves(Vector<ResultatEpreuve> resultatEpreuves)
  {
    this.resultatEpreuves = resultatEpreuves;
  }
  

}
