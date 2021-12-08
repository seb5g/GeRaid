/**
 * 
 */
package to;

import java.awt.Point;
import java.util.Date;

import outils.TimeManager;


/**
 * <P>
 * Titre : Etape.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Etape implements Cloneable
{
  private String nom = "";
  private boolean fini = false;
  private Epreuves epreuves = new Epreuves();
  private TypeDepart type = TypeDepart.GROUPE;
  private Date heureDepart = new Date(32400000);//9:00
  private TypeLimite typeLimite = TypeLimite.SANSLIMITE;
  private Date heureLimite = new Date(0);
  private Date tempsLimite = new Date(0);
  private int intervalPenalite = 5;
  private int penalite = 0;
  private int penaliteTemps = 0;
  private boolean gelDansLimiteTemps = false;


  public Object clone() 
  {
    Etape p = null;
    try 
    {
        // On récupère l'instance à renvoyer par l'appel de la 
        // méthode super.clone()
        p = (Etape) super.clone();
    } 
    catch(CloneNotSupportedException cnse) 
    {
        // Ne devrait jamais arriver car nous implémentons 
        // l'interface Cloneable
        cnse.printStackTrace(System.err);
    }
    p.epreuves = (Epreuves) epreuves.clone();
    // on renvoie le clone
    return p;
  }
  
  public boolean isFirstEpreuve(Epreuve e)
  {
    if(epreuves.getEpreuves().get(0).equals(e))
    {
      return true;
    }
    return false;
  }
  
  public boolean isLastEpreuve(Epreuve e)
  {
    boolean retour = false;
    for(int i=0; i<epreuves.getSize(); i++)
    {
      if(epreuves.getEpreuves().get(i).equals(e))
      {
        if(i+1<epreuves.getSize())
        {
          if(epreuves.getEpreuves().get(i+1).isHorsChrono())
          {
            //System.out.println((epreuves.getEpreuves().get(i).getNom() + " = " + e.getNom()));
            return true;
          }
        }
        else if(i+1==epreuves.getSize())
        {
          //System.out.println((epreuves.getEpreuves().get(i).getNom() + " = " + e.getNom()));
          return true;
        }
      }
    }
    return retour;
  }
  
  public int getfirstCodeNextEpreuve(Epreuve e)
  {
    for(int i=0; i<epreuves.getSize(); i++)
    {
      if(epreuves.getEpreuves().get(i).equals(e))
      {
        return epreuves.getEpreuves().get(i+1).getBalises().getNumeroPremiereBalise();
      }
    }
    return 0;
  }
  
  public int getLastCodePreviousEpreuve(Epreuve e)
  {
    for(int i=0; i<epreuves.getSize(); i++)
    {
      if(epreuves.getEpreuves().get(i).equals(e))
      {
        return epreuves.getEpreuves().get(i-1).getBalises().getNumeroDerniereBalise();
      }
    }
    return 0;
  }
  
  public Balises getBalisesEpreuveSuivante(Epreuve e)
  {
    for(int i=0; i<epreuves.getSize(); i++)
    {
      if(epreuves.getEpreuves().get(i).equals(e))
      {
        if(i+1<epreuves.getSize())
        {
          return epreuves.getEpreuves().get(i+1).getBalises();
        }
      }
    }
    return null;
  }
  
  public Balises getBalisesEpreuvePrcedente(Epreuve e)
  {
    for(int i=0; i<epreuves.getSize(); i++)
    {
      if(epreuves.getEpreuves().get(i).equals(e))
      {
        if(i-1>=0)
        {
          return epreuves.getEpreuves().get(i-1).getBalises();
        }
      }
    }
    return null;
  }
  
  /**
   * @return the penaliteTemps
   */
  public int getPenaliteTemps()
  {
    return penaliteTemps;
  }

  /**
   * @param penaliteTemps the penaliteTemps to set
   */
  public void setPenaliteTemps(int penaliteTemps)
  {
    this.penaliteTemps = penaliteTemps;
  }

  public Etape()
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
   * @return the type
   */
  public TypeDepart getType()
  {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(TypeDepart type)
  {
    this.type = type;
  }

  /**
   * @return the heureDepard
   */
  public Date getHeureDepart()
  {
    return heureDepart;
  }

  /**
   * @param heureDepard the heureDepard to set
   */
  public void setHeureDepart(Date heureDepart)
  {
    this.heureDepart = heureDepart;
  }


  /**
   * @param nom the nom to set
   */
  public void setNom(String nom)
  {
    this.nom = nom;
  }
  
  public String toString()
  {
    StringBuffer retour = new StringBuffer(nom);
    if(fini)
    {
      retour.append(" (terminé)");
    }
    else
    {
      retour.append(" (en cours)");
    }
    return retour.toString();
  }

  /**
   * @return the balises
   */
  public Epreuves getEpreuves()
  {
    return epreuves;
  }

  /**
   * @param balises the balises to set
   */
  public void setEpreuves(Epreuves epreuves)
  {
    this.epreuves = epreuves;
  }

  /**
   * @return the fini
   */
  public boolean isFini()
  {
    return fini;
  }
  
  public String getFini()
  {
    if(isFini())
    {
      return "1";
    }
    return "0";
  }

  /**
   * @param fini the fini to set
   */
  public void setFini(boolean fini)
  {
    this.fini = fini;
  }

  /**
   * @return the typeLimite
   */
  public TypeLimite getTypeLimite()
  {
    return typeLimite;
  }

  /**
   * @param typeLimite the typeLimite to set
   */
  public void setTypeLimite(TypeLimite typeLimite)
  {
    this.typeLimite = typeLimite;
  }

  /**
   * @return the heureLimite
   */
  public Date getHeureLimite()
  {
    return heureLimite;
  }

  /**
   * @param heureLimite the heureLimite to set
   */
  public void setHeureLimite(Date heureLimite)
  {
    this.heureLimite = heureLimite;
  }

  /**
   * @return the tempsLimite
   */
  public Date getTempsLimite()
  {
    return tempsLimite;
  }

  /**
   * @param tempsLimite the tempsLimite to set
   */
  public void setTempsLimite(Date tempsLimite)
  {
    this.tempsLimite = tempsLimite;
  }

  /**
   * @return the intervalPenalite
   */
  public int getIntervalPenalite()
  {
    return intervalPenalite;
  }

  /**
   * @param intervalPenalite the intervalPenalite to set
   */
  public void setIntervalPenalite(int intervalPenalite)
  {
    this.intervalPenalite = intervalPenalite;
  }

  /**
   * @return the penalite
   */
  public int getPenalite()
  {
    return penalite;
  }

  /**
   * @param penalite the penalite to set
   */
  public void setPenalite(int penalite)
  {
    this.penalite = penalite;
  }
  
  public int getNbEpreuves()
  {
    return epreuves.getSize();
  }
  
  public boolean existeEpreuve(String nom, Epreuve e)
  {
    return epreuves.existeEpreuve(nom, e);
  }
  
  public boolean existeNomEpreuve(String nom)
  {
    return epreuves.existeNomEpreuve(nom);
  }
  
  public int getNbBalises()
  {
    int nb=0;
    
    for(int i=0; i<getNbEpreuves(); i++)
    {
      nb = nb + epreuves.getEpreuves().get(i).getBalises().getSize();
    }
    
    return nb;
  }

  public String toStringEtape()
  {
    StringBuffer retour = new StringBuffer();
    retour.append("<li><i>Etape : "+ nom + "<br>");
    retour.append("Départ : " + type.getValue());
    if(type.equals(TypeDepart.GROUPE))
    {
      retour.append(" à " + TimeManager.fullTime(heureDepart) );
    }
    retour.append("<br>");
    switch (typeLimite)
    {
      case SANSLIMITE:
        retour.append("Sans limite");
        break;

      case AVECLIMITETEMPS:
        retour.append("Avec limite de temps à " + TimeManager.fullTime(tempsLimite) 
            + " et pénalité de " + penalite + " pts et " + TimeManager.fullTime(penaliteTemps*60000)
            + " par " + intervalPenalite + " mn de dépassement");
        break;

      case AVECLIMITEHORAIRE:
        retour.append("Avec limite horaire à " + TimeManager.fullTime(heureLimite) 
            + " et pénalité de " + penalite + " pts et " + TimeManager.fullTime(penaliteTemps*60000)
            + " par " + intervalPenalite + " mn de dépassement");
        break;

      default:
        break;
    }
    retour.append("</i><br>");
    retour.append(epreuves.toStringEpreuves());
    retour.append("</li>");
    return retour.toString();
  }

  public boolean existeEpreuve(Epreuve epreuve)
  {
    for(int i=0; i<epreuves.getSize(); i++)
    {
      if(epreuves.getEpreuves().get(i).equals(epreuve))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * @return the gelDansLimiteTemps
   */
  public boolean isGelDansLimiteTemps()
  {
    return gelDansLimiteTemps;
  }
  
  public String getGelDansLimiteTemps()
  {
    if(isGelDansLimiteTemps())
    {
      return "1";
    }
    return "0";
  }

  /**
   * @param gelDansLimiteTemps the gelDansLimiteTemps to set
   */
  public void setGelDansLimiteTemps(boolean gelDansLimiteTemps)
  {
    this.gelDansLimiteTemps = gelDansLimiteTemps;
  }
  
  public boolean estPresqueEgal(Etape etape)
  {
    
    Point p = compare(nom, etape.getNom());
    double d = (double) p.y/p.x;
    if(d == 1)
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
      retour.x = nom1.length();
      retour.y = nom1.length();
    }
    else
    {
      retour.x = nom2.length();
      retour.y = nom2.length();
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
}
