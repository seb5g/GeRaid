/**
 * 
 */
package to;

import java.util.Collections;
import java.util.Vector;

/**
 * <P>
 * Titre : ResultatsParcoursEquipe.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class ResultatsParcoursEquipe
{
  private Vector<ResultatParcoursEquipe> resultat = new Vector<ResultatParcoursEquipe>();
  //private GeRaid geRaid = null;
  
  /**
   * @return the resultat
   */
  public Vector<ResultatParcoursEquipe> getResultat()
  {
    return resultat;
  }

  public ResultatsParcoursEquipe()
  {
    //this.geRaid = g;
  }
  
  public void ajouterResultat(ResultatPuce rp, int points, int temps)
  {
    getResultatParcoursEquipe(rp).ajouterResultat(rp, points, temps);
  }
  
  private ResultatParcoursEquipe getResultatParcoursEquipe(ResultatPuce rp)
  {
    for(int i=0; i<resultat.size(); i++)
    {
      if(resultat.get(i).getEquipe().equals(rp.getEquipe()))
      {
        return resultat.get(i);
      }
    }
    ResultatParcoursEquipe retour = new ResultatParcoursEquipe(rp.getParcours(), rp.getEquipe());
    resultat.add(retour);
    return retour;
  }
  
  public int getSize()
  {
    return resultat.size();
  }
  
  public void trier(boolean puceParEquipe)
  {
    Collections.sort(resultat);
    if(puceParEquipe)
    {
      simplifier();
    }
  }
  
  public void simplifier()
  {
    for(int i=0; i<resultat.size(); i++)
    {
      String dossard = resultat.get(i).getEquipe().getDossard();
      if(dossard.compareTo("")!=0)
      {
        if(existeDossard(i+1, dossard))
        {
          resultat.remove(i);
          i--;
        }
      }
    }
  }
  
  private boolean existeDossard (int index, String dossard)
  {
    for(int i = index; i < resultat.size(); i++)
    {
      if(resultat.get(i).getEquipe().getDossard().compareTo(dossard)==0)
      {
        return true;
      }
    }
    return false;
  }
}
