/**
 * 
 */
package to;

import java.awt.Point;

import outils.TimeManager;


/**
 * <P>
 * Titre : Parcours.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Parcours implements Cloneable
{
  private String nom = "";
  private Equipes equipes = new  Equipes();
  private Etapes etapes = new Etapes();
  
  public Parcours(String nom)
  {
   this.nom = nom; 
  }
  
  public int getNbMaxEquipiers()
  {
    return equipes.getNbMaxEquipiers();
  }

  public Object clone() 
  {
    Parcours p = null;
    try 
    {
        // On récupère l'instance à renvoyer par l'appel de la 
        // méthode super.clone()
        p = (Parcours) super.clone();
    } 
    catch(CloneNotSupportedException cnse) 
    {
        // Ne devrait jamais arriver car nous implémentons 
        // l'interface Cloneable
        cnse.printStackTrace(System.err);
    }
    p.etapes = (Etapes) etapes.clone();
    p.nom = nom + " -" + TimeManager.getDateHeureMinuteSeconte();
    p.equipes = new Equipes();
    // on renvoie le clone
    return p;
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
   * @return the equipes
   */
  public Equipes getEquipes()
  {
    return equipes;
  }

  /**
   * @param equipes the equipes to set
   */
  public void setEquipes(Equipes equipes)
  {
    this.equipes = equipes;
  }

  /**
   * @return the etapes
   */
  public Etapes getEtapes()
  {
    return etapes;
  }

  /**
   * @param etapes the etapes to set
   */
  public void setEtapes(Etapes etapes)
  {
    this.etapes = etapes;
  }
  
  public String toString()
  {
    return nom;
  }
  
  public Equipe getEquipe(String idEquipe)
  {
    Equipe retour = null;
    for(int i=0; i<equipes.getSize(); i++)
    {
      if(equipes.getEquipes().get(i).getIdPuce().compareTo(idEquipe)==0)
      {
        return equipes.getEquipes().get(i);
      }
    }
    return retour;
  }
  
  public Etape getEtape(String nom)
  {
    Etape retour = null;
    for(int i=0; i<etapes.getSize(); i++)
    {
      if(etapes.getEtapes().get(i).getNom().compareTo(nom)==0)
      {
        return etapes.getEtapes().get(i);
      }
    }
    return retour;
  }
  
  public Etape getEtapeEnCours()
  {
    Etape retour = null;
    for(int i=0; i<etapes.getSize(); i++)
    {
      if(!etapes.getEtapes().get(i).isFini())
      {
        return etapes.getEtapes().get(i);
      }
    }
    return retour;
  }
  
  public boolean existeIdPuce(String idPuce, Equipe e)
  {
    return equipes.existeIdPuce(idPuce, e);
  }
  
  public boolean existeIdPuce(String idPuce)
  {
    return equipes.existeIdPuce(idPuce);
  }
  
  public boolean existeEtape(String nom, Etape e)
  {
    return etapes.existeEtape(nom, e);
  }
  
  public Equipe getEquipeIdPuce(String idPuce)
  {
    return equipes.getEquipeIdPuce(idPuce);
  }
  
  public int getIndexEtape(Etape e)
  {
    for(int i=0; i<etapes.getSize(); i++)
    {
      if(etapes.getEtapes().get(i).equals(e))
      {
        return i;
      }
    }
    return -1;
  }
  
  public int getNbEquipes()
  {
    return equipes.getSize();
  }
  
  public int getNbEquipesPresentes()
  {
    int retour = 0;
    for(int i=0; i<equipes.getSize(); i++)
    {
      if(!equipes.getEquipes().get(i).isABS())
      {
        retour++;
      }
    }
    return retour;
  }

  public String toStringParcours()
  {
    StringBuffer retour = new StringBuffer();
    retour.append("<b>Parcours : "+ nom + "</b>");
    retour.append(etapes.toStringEtapes());
    return retour.toString();
  }

  public boolean existeEpreuve(Epreuve epreuve)
  {
    for(int i=0; i<etapes.getSize(); i++)
    {
      if(etapes.getEtapes().get(i).existeEpreuve(epreuve))
      {
        return true;
      }
    }
    return false;
  }

  public boolean existeEpreuve(Etape etape)
  {
    for(int i=0; i<etapes.getSize(); i++)
    {
      if(etapes.getEtapes().get(i).equals(etape))
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean estPresqueEgal(Parcours parcours)
  {
    
    Point p = compare(nom, parcours.getNom());
    double d = (double) p.y/p.x;
    if(d > 0.75)
    {
      return true;
    }
    else
    {
      return false;
    }
    
  }
  
  private Point compare(String nom1, String nom2)
  {
    Point retour = new Point(0, 0);
    if(nom1.length() > nom2.length())
    {
      retour.x = nom2.length();
      retour.y = nom2.length();
    }
    else
    {
      retour.x = nom1.length();
      retour.y = nom1.length();
    }
    for(int i=0; i<retour.x; i++)
    {
      if(nom1.charAt(i) != nom2.charAt(i))
      {
        retour.y = i;
        break;
      }
    }
    return retour;
  }

  public boolean existeEquipe(Equipe equipe)
  {
    for(int i=0; i<equipes.getSize(); i++)
    {
      if(equipes.getEquipes().contains(equipe))
      {
        return true;
      }
    }
    return false;
  }
}
