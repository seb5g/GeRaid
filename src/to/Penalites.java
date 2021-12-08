/**
 * 
 */
package to;

import java.util.Vector;

/**
 * <P>
 * Titre : Penalites.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author Thierry
 *
 */
public class Penalites
{
  private Vector<Penalite> penalites = new Vector<Penalite>();
  
  public Penalites()
  {
    
  }
  
  public void effacerEtape(Etape e)
  {
    int i = 0;
    while(i<penalites.size())
    {
      if(penalites.get(i).getEtape().equals(e))
      {
        penalites.remove(i);
      }
      else
      {
        i++;
      }
    }
  }

  /**
   * @return the penalites
   */
  public Vector<Penalite> getPenalites()
  {
    return penalites;
  }

  /**
   * @param penalites the penalites to set
   */
  public void setPenalites(Vector<Penalite> penalites)
  {
    this.penalites = penalites;
  }
  
  public Vector<String> getNbPenalites(Etape e)
  {
    Vector<String> retour = new Vector<String>();
    
    for(int i=0; i<penalites.size(); i++)
    {
      if(penalites.get(i).getEtape().equals(e))
      {
        retour.add(penalites.get(i).getNom());
      }
    }
    
    return retour;
  }
  
  public int getTotalPointPenalite(Parcours p, Etape e, String puce)
  {
    int retour = 0;
    for(int i=0; i<penalites.size(); i++)
    {
      if(penalites.get(i).getParcours().estPresqueEgal(p) && penalites.get(i).getEtape().estPresqueEgal(e))
      {
        for(int j=0; j<penalites.get(i).getPenalites().size(); j++)
        {
          if(penalites.get(i).getPenalites().get(j).getPuce().compareTo(puce)==0)
          {
            retour = retour + penalites.get(i).getPenalites().get(j).getPoint();
          }
        }
      }
    }
    return retour;
  }
  
  public int getTotalTempsPenalite(Parcours p, Etape e, String puce)
  {
    int retour = 0;
    for(int i=0; i<penalites.size(); i++)
    {
      if(penalites.get(i).getParcours().estPresqueEgal(p) && penalites.get(i).getEtape().estPresqueEgal(e))
      {
        for(int j=0; j<penalites.get(i).getPenalites().size(); j++)
        {
          if(penalites.get(i).getPenalites().get(j).getPuce().compareTo(puce)==0)
          {
            retour = retour + penalites.get(i).getPenalites().get(j).getTemps();
          }
        }
      }
    }
    return retour;
  }
}
