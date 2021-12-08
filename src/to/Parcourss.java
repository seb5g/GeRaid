/**
 * 
 */
package to;

import java.util.Vector;

/**
 * <P>
 * Titre : Parcourss.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Parcourss
{
  private Vector<Parcours> parcourss = new Vector<Parcours>();
  
  public Parcourss()
  {
    
  }

  /**
   * @return the parcourss
   */
  public Vector<Parcours> getParcourss()
  {
    return parcourss;
  }

  /**
   * @param parcourss the parcourss to set
   */
  public void setParcourss(Vector<Parcours> parcourss)
  {
    this.parcourss = parcourss;
  }
  
  public boolean addParcours(Parcours p)
  {
    boolean retour = false;
    if(!existeNom(p.getNom()))
    {
      parcourss.add(p);
      retour = true;
    }
    return retour;
  }
  
  private boolean existeNom(String nom)
  {
    boolean retour = false;
    for(int i=0; i<parcourss.size(); i++)
    {
      if(parcourss.get(i).getNom().toUpperCase().compareTo(nom.trim().toUpperCase())==0)
      {
        return true;
      }
    }
    return retour;
  }
  
  public boolean existeParcours(String nom, Parcours p)
  {
    boolean retour = false;
    for(int i=0; i<parcourss.size(); i++)
    {
      if(parcourss.get(i).getNom().toUpperCase().compareTo(nom.trim().toUpperCase())==0)
      {
        if(!parcourss.get(i).equals(p))
        {
          return true;
        }
      }
    }
    return retour;
  }
  
  public void removeParcours(String nom)
  {
    Parcours p = getParcours(nom);
    if(p != null)
    {
      parcourss.remove(p);
    }
      
    parcourss.remove(p);
  }
  
  public void removeParcours(Parcours p)
  {
    parcourss.remove(p);
  }
  
  public Parcours getParcours(String nom)
  {
    Parcours retour = null;
    for(int i=0; i<parcourss.size(); i++)
    {
      if(parcourss.get(i).getNom().compareTo(nom)==0)
      {
        retour = parcourss.get(i);
        break;
      }
    }
    return retour;
  }
  
  public int getSize()
  {
    return parcourss.size();
  }
  
  public boolean editParcours(Parcours p, String nom)
  {
    boolean retour = false;
    if(!existeNom(nom))
    {
      p.setNom(nom);
      retour = true;
    }
    return retour;
  }
  
  public String getNom(int i)
  {
    return parcourss.get(i).getNom();
  }
  
  public void clearParcours()
  {
    parcourss.clear();
  }
  
  public boolean addEquipe(Parcours p, Equipe e)
  {
    return p.getEquipes().addEquipe(e);
  }
  
  public boolean existeIdPuce(String idPuce, Equipe e)
  {
    boolean retour = false;
    for(int i=0; i<parcourss.size(); i++)
    {
      if(parcourss.get(i).existeIdPuce(idPuce, e))
      {
        return true;
      }
    }
    return retour;
  }
  
  public boolean existePuce(String idPuce)
  {
    boolean retour = false;
    for(int i=0; i<parcourss.size(); i++)
    {
      if(parcourss.get(i).existeIdPuce(idPuce))
      {
        return true;
      }
    }
    return retour;
  }
  
  public Parcours getParcoursIdPuce(String idPuce)
  {
    for(int i=0; i<parcourss.size(); i++)
    {
      if(parcourss.get(i).existeIdPuce(idPuce))
      {
        return parcourss.get(i);
      }
    }
    return null;
  }
  
  public String toStringParcours()
  {
    StringBuffer retour = new StringBuffer();
    for(int i=0; i<parcourss.size(); i++)
    {
      retour.append(parcourss.get(i).toStringParcours());
    }
    return retour.toString();
  }

  public Parcours getParcours(Epreuve epreuve)
  {
    for(int i=0; i<parcourss.size(); i++)
    {
      if(parcourss.get(i).existeEpreuve(epreuve))
      {
        return parcourss.get(i);
      }
    }
    return null;
  }

  public Parcours getParcours(Equipe equipe)
  {
    for(int i=0; i<parcourss.size(); i++)
    {
      if(parcourss.get(i).existeEquipe(equipe))
      {
        return parcourss.get(i);
      }
    }
    return null;
  }

  public Parcours getParcours(Etape etape)
  {
    for(int i=0; i<parcourss.size(); i++)
    {
      if(parcourss.get(i).existeEpreuve(etape))
      {
        return parcourss.get(i);
      }
    }
    return null;
  }
}
