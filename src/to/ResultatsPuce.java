/**
 * 
 */
package to;

import java.util.Vector;

/**
 * <P>
 * Titre : ResultatsPuce.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class ResultatsPuce implements Cloneable
{
  private Vector<ResultatPuce> resultatsPuce = new Vector<ResultatPuce>();
  
  public ResultatsPuce()
  {
    
  }

  /**
   * @return the resultatsPuce
   */
  public Vector<ResultatPuce> getResultatsPuce()
  {
    return resultatsPuce;
  }

  /**
   * @param resultatsPuce the resultatsPuce to set
   */
  public void setResultatsPuce(Vector<ResultatPuce> resultatsPuce)
  {
    this.resultatsPuce = resultatsPuce;
  }
  
  public boolean addResultatPuce(ResultatPuce p)
  {
    boolean retour = false;
      resultatsPuce.add(p);
      retour = true;
    return retour;
  }
  
  public int getSize()
  {
    return resultatsPuce.size();
  }
  
  public void clearResultats()
  {
    resultatsPuce.clear();
  }
  
  public void removeResultatPuce(ResultatPuce rp)
  {
    resultatsPuce.remove(rp);
  }
  
  public ResultatPuce existeResultatPuce(ResultatPuce rp)
  {
    for(int i=0;i<resultatsPuce.size();i++)
    {
      if(compare(resultatsPuce.get(i), rp))
      {
        return resultatsPuce.get(i);
      }
    }
    return null;
  }
  
  private boolean compare(ResultatPuce rp1, ResultatPuce rp2)
  {
    if(rp1.getEtape().equals(rp2.getEtape()) && rp1.getEquipe().equals(rp2.getEquipe()))
    {
      return true;
    }
    return false;
  }
  
  public void effacerParcours(Parcours p)
  {
    int i = 0;
    while(i<resultatsPuce.size())
    {
      if(resultatsPuce.get(i).getParcours().equals(p))
      {
        resultatsPuce.remove(i);
      }
      else
      {
        i++;
      }
    }
  }
  
  public void effacerEquipe(Equipe e)
  {
    int i = 0;
    while(i<resultatsPuce.size())
    {
      if(resultatsPuce.get(i).getEquipe().equals(e))
      {
        resultatsPuce.remove(i);
      }
      else
      {
        i++;
      }
    }
  }
  
  public void effacerEtape(Etape e)
  {
    int i = 0;
    while(i<resultatsPuce.size())
    {
      if(resultatsPuce.get(i).getEtape().equals(e))
      {
        resultatsPuce.remove(i);
      }
      else
      {
        i++;
      }
    }
  }
  
  public Vector<Parcours> getParcours()
  {
    Vector<Parcours> retour = new Vector<Parcours>();
    for(int i=0; i<resultatsPuce.size(); i++)
    {
      if(!retour.contains(resultatsPuce.get(i).getParcours()))
      {
        retour.add(resultatsPuce.get(i).getParcours());
      }
    }
    return retour;
  }
  
  public Vector<Etape> getEtapes(Parcours p)
  {
    Vector<Etape> retour = new Vector<Etape>();
    for(int i=0; i<resultatsPuce.size(); i++)
    {
      if(resultatsPuce.get(i).getParcours().equals(p) && !retour.contains(resultatsPuce.get(i).getEtape()))
      {
        retour.add(resultatsPuce.get(i).getEtape());
      }
    }
    return retour;
  }
  
  public Vector<Equipe> getEquipes(Etape e)
  {
    Vector<Equipe> retour = new Vector<Equipe>();
    for(int i=0; i<resultatsPuce.size(); i++)
    {
      if(resultatsPuce.get(i).getEtape().equals(e) && !retour.contains(resultatsPuce.get(i).getEquipe()))
      {
        retour.add(resultatsPuce.get(i).getEquipe());
      }
    }
    return retour;
  }
  
  public ResultatPuce getResultatPuce(Etape et, Equipe eq)
  {
    for(int i=0;i<resultatsPuce.size();i++)
    {
      if(resultatsPuce.get(i).getEtape().equals(et) && resultatsPuce.get(i).getEquipe().equals(eq))
      {
        return resultatsPuce.get(i);
      }
    }
    return null;
  }
  
  public boolean isArrived(Equipe equipe, Parcours parcours, Etape etape)
  {
    boolean retour = false;

    for(int i=0;i<resultatsPuce.size();i++)
    {
      if(resultatsPuce.get(i).getEtape().equals(etape) && resultatsPuce.get(i).getEquipe().equals(equipe) && resultatsPuce.get(i).getParcours().equals(parcours))
      {
        return true;
      }
    }
    return retour;
  }
  
  public ResultatPuce getResultatPuce(Parcours p, Etape e, String puce)
  {
    for(int i=0;i<resultatsPuce.size();i++)
    {
      if(p.equals(resultatsPuce.get(i).getParcours()) && 
          e.equals(resultatsPuce.get(i).getEtape()) && 
              resultatsPuce.get(i).getEquipe().getIdPuce().compareTo(puce)==0)
      {
        return resultatsPuce.get(i);
      }
    }
    return null;
  }

  public boolean existeResultatPuce(Equipe e)
  {
    for(int i=0;i<resultatsPuce.size();i++)
    {
      if(resultatsPuce.get(i).getEquipe().equals(e))
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean existeCodePuce(int code, String puce)
  {
    for(int i=0;i<resultatsPuce.size();i++)
    {
      if(puce.compareTo(resultatsPuce.get(i).getPuce().getIdPuce())==0)
      {
        if(resultatsPuce.get(i).existeCode(code))
        {
          return true;
        }
      }
    }
    return false;
  }
}
