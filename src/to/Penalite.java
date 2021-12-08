/**
 * 
 */
package to;

import java.util.Vector;

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
public class Penalite
{
  private String nom ="Nouveau nom";
  private Parcours parcours = null;
  private Etape etape = null;
  private Vector<PenaliteIndividuelle> penalites = new Vector<PenaliteIndividuelle>();
  
  public Penalite()
  {
    
  }

  /**
   * @return the nom
   */
  public String getNom()
  {
    return nom;
  }

  /**
   * @param nom the nom to set
   */
  public void setNom(String nom)
  {
    this.nom = nom;
  }

  /**
   * @return the parcours
   */
  public Parcours getParcours()
  {
    return parcours;
  }

  /**
   * @param parcours the parcours to set
   */
  public void setParcours(Parcours parcours)
  {
    this.parcours = parcours;
  }

  /**
   * @return the etape
   */
  public Etape getEtape()
  {
    return etape;
  }

  /**
   * @param etape the etape to set
   */
  public void setEtape(Etape etape)
  {
    this.etape = etape;
  }

  /**
   * @return the penalites
   */
  public Vector<PenaliteIndividuelle> getPenalites()
  {
    return penalites;
  }

  /**
   * @param penalites the penalites to set
   */
  public void setPenalites(Vector<PenaliteIndividuelle> penalites)
  {
    this.penalites = penalites;
  }
  
  public void addPenaliteIndividuelle(PenaliteIndividuelle p)
  {
    if(p.getPuce().compareTo("")!=0)
    {
      supprimePenalitePuce(p.getPuce());
      penalites.add(p);
    }
  }
  
  private void supprimePenalitePuce(String puce)
  {
    for(int i=0; i<penalites.size(); i++)
    {
      if(penalites.get(i).getPuce().compareTo(puce)==0)
      {
        penalites.remove(i);
        return;
      }
    }
  }
  
  public boolean aPuce(String puce)
  {
    boolean retour = false;
    for(int i=0; i<penalites.size(); i++)
    {
      if(penalites.get(i).getPuce().compareTo(puce)==0)
      {
        return true;
      }
    }
    
    return retour;
  }
  
  public int getPoints(String puce)
  {
    int retour = 0;
    for(int i=0; i<penalites.size(); i++)
    {
      if(penalites.get(i).getPuce().compareTo(puce)==0)
      {
        return penalites.get(i).getPoint();
      }
    }
    return retour;
  }
  
  public int getTemps(String puce)
  {
    int retour = 0;
    for(int i=0; i<penalites.size(); i++)
    {
      if(penalites.get(i).getPuce().compareTo(puce)==0)
      {
        return penalites.get(i).getTemps();
      }
    }
    return retour;
  }
  
  public PenaliteIndividuelle getPenaliteIndividuelle(String puce)
  {
    PenaliteIndividuelle retour = null;
    
    for(int i=0; i<penalites.size(); i++)
    {
      if(puce.compareTo(penalites.get(i).getPuce())==0)
      {
        return penalites.get(i);
      }
    }
    
    return retour;
  }
}
