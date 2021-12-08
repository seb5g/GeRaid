/**
 * 
 */
package to;

import outils.TimeManager;

/**
 * <P>
 * Titre : ResultatParcoursEquipe.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class ResultatParcoursEquipe implements Comparable<ResultatParcoursEquipe>
{
  public int classement = 0;
  private Parcours parcours = null;
  public Equipe equipe = null;
  public int totalPoints = 0;
  public long tempsCompense = 0;
  public String[] etape;
  public String[] points;
  public String[] temps;
  public int nbEtapes = 0;
  
  public ResultatParcoursEquipe(Parcours p, Equipe e)
  {
    this.parcours = p;
    this.equipe = e;
    if(equipe.isNC())
    {
      classement = -1;
    }
    int nbEtape = p.getEtapes().getSize();
    etape = new String[nbEtape];
    points = new String[nbEtape];
    temps = new String[nbEtape];
    initTableaux();
  }
  
  private void initTableaux()
  {
    for(int i = 0; i < etape.length; i++)
    {
      etape[i] = "";
      points[i] = "";
      temps[i] = "";
    }
  }
  
  /**
   * @return the equipe
   */
  public Equipe getEquipe()
  {
    return equipe;
  }

  /**
   * @param equipe the equipe to set
   */
  public void setEquipe(Equipe equipe)
  {
    this.equipe = equipe;
  }
  
  public void ajouterResultat(ResultatPuce rp, int pts, int tps)
  {
    ResultatEquipe re = new ResultatEquipe(rp, pts, tps);
    totalPoints = totalPoints + re.totalPoints;
    tempsCompense = tempsCompense + re.totalTemps;
    int index = parcours.getIndexEtape(rp.getEtape());
    etape[index] = "";
    points[index] = re.totalPoints + "";
    temps[index] = TimeManager.fullTime(re.totalTemps);
    setNbEtapes();
  }
  
  private void setNbEtapes()
  {
    for(int i=0; i<temps.length; i++)
    {
      if(temps[i].compareTo("")!=0)
      {
        nbEtapes++;
      }
    }
  }

  @Override
  public int compareTo(ResultatParcoursEquipe o)
  {
    if(totalPoints < o.totalPoints)
    {
      return 1;
    }
    if(totalPoints == o.totalPoints)
    {
      if(nbEtapes < o.nbEtapes)
      {
        return 1;
      }
      else if(o.nbEtapes < nbEtapes)
      {
        return -1;
      }
      else if(tempsCompense > o.tempsCompense)
      {
        return 1;
      }
      else
      {
        return -1;
      }
    }
    return -1;
  }
}
