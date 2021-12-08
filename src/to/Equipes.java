/**
 * 
 */
package to;

import java.util.Collections;
import java.util.Vector;

/**
 * <P>
 * Titre : Equipes.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Equipes
{
  private Vector<Equipe> equipes = new Vector<Equipe>();
  
  public Equipes()
  {
    
  }
  
  public int getNbMaxEquipiers()
  {
    int retour = 0;
    for(int i=0; i<equipes.size(); i++)
    {
      if(equipes.get(i).getRaiders().getSize()>retour)
      {
        retour = equipes.get(i).getRaiders().getSize();
      }
    }
    return retour;
  }

  /**
   * @return the equipes
   */
  public Vector<Equipe> getEquipes()
  {
    return equipes;
  }

  /**
   * @param equipes the equipes to set
   */
  public void setEquipes(Vector<Equipe> equipes)
  {
    this.equipes = equipes;
  }
  
  public boolean addEquipe(Equipe equipe)
  {
    boolean retour = false;
    if(existeIdPuce(equipe) == null)
    {
      equipes.add(equipe);
      Collections.sort(equipes, new EquipeComparator(TypeTri.DOSSARD));
      retour = true;
    }
    return retour;
  }
  
  private Equipe existeIdPuce(Equipe e)
  {
    Equipe retour = null;
    for(int i=0; i<equipes.size(); i++)
    {
      if(equipes.get(i).getIdPuce().compareTo("")!=0)
      {
        if(equipes.get(i)!= e && equipes.get(i).getIdPuce().compareTo(e.getIdPuce().trim())==0)
        {
          return equipes.get(i);
        }
      }
    }
    return retour;
  }
  
  public boolean existeIdPuce(String idPuce, Equipe e)
  {
    boolean retour = false;
    for(int i=0; i<equipes.size(); i++)
    {
      if(equipes.get(i).getIdPuce().compareTo("")!=0)
      {
        if(equipes.get(i).getIdPuce().compareTo(idPuce.trim())==0)
        {
          if(!equipes.get(i).equals(e))
          {
            return true;
          }
        }
      }
    }
    
    return retour;
  }
  
  public boolean existeIdPuce(String idPuce)
  {
    boolean retour = false;
    for(int i=0; i<equipes.size(); i++)
    {
      if(equipes.get(i).getIdPuce().compareTo("")!=0)
      {
        if(equipes.get(i).getIdPuce().compareTo(idPuce.trim())==0)
        {
            return true;
        }
      }
    }
    
    return retour;
  }
  
  public Equipe editEquipe(Equipe e)
  {
    Equipe retour = null;
    if(existeIdPuce(e)==null)
    {
      retour = e;
    }
    return retour;
  }
  
  public void removeEquipe(Equipe e)
  {
    equipes.remove(e);
  }
  
  public int getSize()
  {
    return equipes.size();
  }

  public Equipe getEquipeIdPuce(String idPuce)
  {
    for(int i=0; i<equipes.size(); i++)
    {
      if(equipes.get(i).getIdPuce().compareTo(idPuce.trim())==0)
      {
          return equipes.get(i);
      }
    }
    
    return null;
  }
  
  public void trierEquipes(TypeTri mode)
  {
    Collections.sort(equipes, new EquipeComparator(mode));
  }
  
  public void numeroterEquipes(String prefixe, int premierNumero, String suffixe)
  {
    for(int i=0; i<getSize(); i++)
    {
      equipes.get(i).setDossard(prefixe + (premierNumero + i) + suffixe);
    }
  }

  public Vector<Equipe> getEquipes(String debutNomEquipe)
  {
    Vector<Equipe> retour = new Vector<>() ;
    for(int i=0; i<equipes.size(); i++)
    {
      if(equipes.get(i).getNom().toUpperCase().startsWith(debutNomEquipe))
      {
        retour.add(equipes.get(i));
      }
    }
    return retour;
  }
}
