/**
 * 
 */
package to;

import java.util.Vector;


/**
 * <P>
 * Titre : Etapes.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Epreuves implements Cloneable
{
  private Vector<Epreuve> epreuves = new Vector<Epreuve>();


  public Object clone() 
  {
    Epreuves p = null;
    try 
    {
        // On récupère l'instance à renvoyer par l'appel de la 
        // méthode super.clone()
        p = (Epreuves) super.clone();
    } 
    catch(CloneNotSupportedException cnse) 
    {
        // Ne devrait jamais arriver car nous implémentons 
        // l'interface Cloneable
        cnse.printStackTrace(System.err);
    }
    p.epreuves = new Vector<Epreuve>();
    for(int i=0; i<epreuves.size(); i++)
    {
      p.getEpreuves().add((Epreuve) epreuves.get(i).clone());
    }
    // on renvoie le clone
    return p;
  }
  
  public Epreuves()
  {
    
  }

  /**
   * @return the etapes
   */
  public Vector<Epreuve> getEpreuves()
  {
    return epreuves;
  }

  /**
   * @param etapes the etapes to set
   */
  public void setEpreuves(Vector<Epreuve> epreuves)
  {
    this.epreuves = epreuves;
  }
  
  public void upEpreuve(Epreuve b)
  {
    int index = epreuves.indexOf(b);
    if(index>0)
    {
      epreuves.insertElementAt(b, index-1);
      epreuves.removeElementAt(index+1);
    }
  }
  
  public void downEpreuve(Epreuve b)
  {
    int index = epreuves.indexOf(b);
    if(index<epreuves.size()-1)
    {
      epreuves.insertElementAt(b, index+2);
      epreuves.removeElementAt(index);
    }
  }
  
  public boolean addEpreuve(Epreuve e)
  {
    boolean retour = false;
    if(existeNom(e) == null)
    {
      epreuves.add(e);
      retour = true;
    }
    return retour;
  }
  
  private Epreuve existeNom(Epreuve e)
  {
    Epreuve retour = null;
    for(int i=0; i<epreuves.size(); i++)
    {
      if(epreuves.get(i)!= e && epreuves.get(i).getNom().compareTo(e.getNom().trim())==0)
      {
        return epreuves.get(i);
      }
    }
    return retour;
  }
  
  public boolean existeEpreuve(String nom, Epreuve e)
  {
    boolean retour = false;
    for(int i=0; i<epreuves.size(); i++)
    {
      if(epreuves.get(i).getNom().toUpperCase().compareTo(nom.trim().toUpperCase())==0)
      {
        if(!epreuves.get(i).equals(e))
        {
          return true;
        }
      }
    }
    
    return retour;
  }
  
  public boolean existeNomEpreuve(String nom)
  {
    boolean retour = false;
    for(int i=0; i<epreuves.size(); i++)
    {
      if(epreuves.get(i).getNom().toUpperCase().compareTo(nom.trim().toUpperCase())==0)
      {
         return true;
      }
    }
    
    return retour;
  }
  
  public Epreuve editEpreuve(Epreuve e)
  {
    Epreuve retour = null;
    if(existeNom(e)==null)
    {
      retour = e;
    }
    return retour;
  }
  
  public void removeEpreuve(Epreuve e)
  {
    epreuves.remove(e);
  }
  
  public int getSize()
  {
    return epreuves.size();
  }
  
  public int getDernierNumero()
  {    
    int retour = 30;
    for(int i=0; i<epreuves.size(); i++)
    {
      if(epreuves.get(i).getDernierNumero()>retour)
      {
        retour = epreuves.get(i).getDernierNumero();
      }
    }
    return retour;
  }
  
  public String toStringEpreuves()
  {
    StringBuffer retour = new StringBuffer("<ul>");
    for(int i=0; i<epreuves.size(); i++)
    {
      retour.append(epreuves.get(i).toStringEpreuve(this));
    }
    retour.append("</ul>");
    return retour.toString();
  }
  
  public Vector<Integer> getCodesEnPlus(ResultatPuce rp)
  {
    Vector<Integer> retour = new Vector<Integer>();
    
    for(int i=0; i<rp.getPuce().getPartiels().length; i++)
    {
      if(!existeCode(rp.getPuce().getPartiels()[i].getCode()))
      {
        retour.add(rp.getPuce().getPartiels()[i].getCode());
      }
    }
    
    return retour;
  }
  
  public boolean existeCode(int code)
  {
    boolean retour = false;
    
    for(int i=0; i<epreuves.size(); i++)
    {
      for(int j=0; j<epreuves.get(i).getBalises().getSize(); j++)
      {
        if(epreuves.get(i).getBalises().getBalises().get(j).getNumero()==code)
        {
          retour = true;
        }
      }
    }
    
    return retour;
  }
  
  public boolean isFirstEpreuve(Epreuve e)
  {
    if(epreuves.size()>0)
    {
      if(e.equals(epreuves.get(0)))
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    return false;
  }
  
  public int getLastCodeEpreuvePrecedente(Epreuve e)
  {
    for(int i=0; i<epreuves.size(); i++)
    {
      if(e.equals(epreuves.get(i)))
      {
        return epreuves.get(i-1).getDernierNumeroBalises();
      }
    }
    return 30;
  }

  public boolean isLastEpreuveAvantArrivee(Epreuve epreuve)
  {
    if(epreuve.equals(epreuves.get(getSize()-1)) && !epreuve.isHorsChrono())
    {
      return true;
    }
    if(getRangEpreuve(epreuve)<epreuves.size()-1)
    {
      if(!epreuve.isHorsChrono() && epreuves.get(getRangEpreuve(epreuve)+1).isHorsChrono())
      {
        return true;
      }
    }
    return false;
  }
  
  public int getRangEpreuve(Epreuve e)
  {
    for(int i=0; i<epreuves.size(); i++)
    {
      if(e.equals(epreuves.get(i)))
      {
        return i;
      }
    }
    return -1;
  }

  public int getFirstCodeEpreuveSuivante(Epreuve epreuve)
  {
    for(int i=0; i<epreuves.size(); i++)
    {
      if(epreuve.equals(epreuves.get(i)))
      {
        return epreuves.get(i+1).getPremierNumeroBalises();
      }
    }
    return 30;
  }
}
