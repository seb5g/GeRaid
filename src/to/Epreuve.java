/**
 * 
 */
package to;

import java.util.Date;
import java.util.Vector;

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
public class Epreuve implements Cloneable
{
  private String nom = "";
  private Balises balises = new Balises();
  private TypeLimite typeLimite = TypeLimite.SANSLIMITE;
  private Date heureLimite = new Date(0);
  private Date tempsLimite = new Date(0);
  private int multiplicateurTemps = 1;
  private int intervalPenalite = 5;
  private int penalite = 0;
  private int penaliteTemps = 0;
  private boolean chrono = false; // true s'il faut chronom�trer cette �preuve
  private boolean ligne = false; // true si c'est une �preuve type course en ligne
  private boolean debutEpreuve = false; // l'�preuve commence avec la derni�re balise de l'�preuve pr�c�dente == true
  private boolean finEpreuve = false; // l'�preuve se termine avec la premi�re balise de l'�preuve suivante == true
  private boolean horsChrono = false; // Si l'�preuve est effectu�e apr�s l'arriv�e. Permet d'utiliser le boitier arriv�e pour une autre �preuve que la derni�re
  private boolean avantEpreuveSuivante = false; // Si true = chaque poste de cette �preuve doit �tre fait avant ceux de l'�preuve suivante sinon PM
  private boolean apresEpreuvePrecedente = false; // Si true = chaque poste de cette �preuve doit �tre fait apr�s ceux de l'�preuve pr�c�dente sinon PM
  private static Vector<String> entetes = new Vector<String>();


  public Object clone() 
  {
    Epreuve p = null;
    try 
    {
        // On r�cup�re l'instance � renvoyer par l'appel de la 
        // m�thode super.clone()
        p = (Epreuve) super.clone();
    } 
    catch(CloneNotSupportedException cnse) 
    {
        // Ne devrait jamais arriver car nous impl�mentons 
        // l'interface Cloneable
        cnse.printStackTrace(System.err);
    }
    p.balises = (Balises) balises.clone();
    // on renvoie le clone
    return p;
  }

  /**
   * @return the multiplicateurTemps
   */
  public int getMultiplicateurTemps()
  {
    return multiplicateurTemps;
  }

  /**
   * @param multiplicateurTemps the multiplicateurTemps to set
   */
  public void setMultiplicateurTemps(int multiplicateurTemps)
  {
    this.multiplicateurTemps = multiplicateurTemps;
  }

  public int[] getCodes()
  {
    int[] codes = new int[getNbBalises()];
    
    for(int i=0; i<balises.getSize(); i++)
    {
        codes[i] = balises.getBalises().get(i).getNumero();
    }
    
    return codes;
  }
  
  /**
   * @return the chrono
   */
  public boolean isChrono()
  {
    return chrono;
  }

  /**
   * @param chrono the chrono to set
   */
  public void setChrono(boolean chrono)
  {
    this.chrono = chrono;
  }

  /**
   * @return   = 1 ou  = 0
   */
  public String getChrono()
  {
    return (isChrono())? "1" : "0";
  }

  /**
   * @return  C = 1 ou  = 0
   */
  public String getChronoString()
  {
    return (isChrono())? "NC" : "";
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

  public Epreuve()
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
  
  public String toString()
  {
    StringBuffer retour = new StringBuffer(nom);
    return retour.toString();
  }

  /**
   * @return the balises
   */
  public Balises getBalises()
  {
    return balises;
  }

  /**
   * @param balises the balises to set
   */
  public void setBalises(Balises balises)
  {
    this.balises = balises;
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
  
  public int getNbBalises()
  {
    return balises.getSize();
  }

  /**
   * @return the ligne
   */
  public boolean isLigne()
  {
    return ligne;
  }

  /**
   * @param ligne the ligne to set
   */
  public void setLigne(boolean ligne)
  {
    this.ligne = ligne;
  }

  /**
   * @return the debutEpreuveBaliseUne
   */
  public boolean isDebutEpreuve()
  {
    return debutEpreuve;
  }

  /**
   * @param debutEpreuveBaliseUne the debutEpreuveBaliseUne to set
   */
  public void setDebutEpreuve(boolean debutEpreuve)
  {
    this.debutEpreuve = debutEpreuve;
  }

  /**
   * @return the finEpreuvederniereBalise
   */
  public boolean isFinEpreuve()
  {
    return finEpreuve;
  }

  /**
   * @param finEpreuvederniereBalise the finEpreuvederniereBalise to set
   */
  public void setFinEpreuve(boolean finEpreuve)
  {
    this.finEpreuve = finEpreuve;
  }

  /**
   * @return   = 1 ou  = 0
   */
  public String getDebutEpreuve()
  {
    return (isDebutEpreuve())? "1" : "0";
  }

  /**
   * @return   = 1 ou  = 0
   */
  public String getDebutEpreuveString()
  {
    return (isDebutEpreuve())? "NC" : "";
  }

  /**
   * @return   = 1 ou  = 0
   */
  public String getFinEpreuve()
  {
    return (isFinEpreuve())? "1" : "0";
  }

  /**
   * @return   = 1 ou  = 0
   */
  public String getFinEpreuveString()
  {
    return (isFinEpreuve())? "NC" : "";
  }

  /**
   * @return   = 1 ou  = 0
   */
  public String getLigne()
  {
    return (isLigne())? "1" : "0";
  }

  /**
   * @return  C = 1 ou  = 0
   */
  public String getLigneString()
  {
    return (isLigne())? "NC" : "";
  }
  
  public int getDernierNumero()
  {    
    return getBalises().getDernierNumero();
  }
  
  public int getDernierNumeroBalises()
  {    
    return getBalises().getNumeroDerniereBalise();
  }
  
  public int getPremierNumeroBalises()
  {    
    return getBalises().getNumeroPremiereBalise();
  }

  /**
   * @return the horsChrono
   */
  public boolean isHorsChrono()
  {
    return horsChrono;
  }

  /**
   * @param horsChrono the horsChrono to set
   */
  public void setHorsChrono(boolean horsChrono)
  {
    this.horsChrono = horsChrono;
  }

  /**
   * @return   = 1 ou  = 0
   */
  public String getHorsChrono()
  {
    return (isHorsChrono())? "1" : "0";
  }
  
  public static String[] getEntetes(Epreuve e, TypeVisualisationEpreuve tv)
  {
    editEntetes(e, tv);
    return (String[]) entetes.toArray(new String[0]);
  }
  
  private static void editEntetes(Epreuve e, TypeVisualisationEpreuve tv)
  {
  // Visualisation simple
    entetes.clear();
  // Classement
    entetes.add("Clt");
    entetes.add("Dossard");
    entetes.add("Puce");
    entetes.add("Nom �quipe");
    entetes.add("Cat�gorie");
    entetes.add("Tps final");
    entetes.add("Pts finaux");
  // Temps
    entetes.add("Tps course");
    entetes.add("Bonification");
    entetes.add("P�nalit�s");
    entetes.add("D�passement");
    entetes.add("Gel");
  // Points
    entetes.add("Bonification");
    entetes.add("P�nalit�s");
    entetes.add("D�passement");
  // Gestion des autres visualisations
    if(!tv.equals(TypeVisualisationEpreuve.SIMPLE))
    {
      if(e!=null)
      {
        for(int i=0; i<e.getBalises().getSize(); i++)
        {
              entetes.add("Code");
              entetes.add("Ok/Pm"); // heure de passage ou PM
              entetes.add("Points");
              entetes.add("Temps");
        }
      }
    }
  }

  public String toStringEpreuve(Epreuves es)
  {
    StringBuffer retour = new StringBuffer();
    retour.append("<li><code>Epreuve : "+ nom + "<br>");
    switch (typeLimite)
    {
      case SANSLIMITE:
        retour.append("Sans limite");
        break;

      case AVECLIMITETEMPS:
        retour.append("Avec limite de temps � " + TimeManager.fullTime(tempsLimite) 
            + " et p�nalit� de " + penalite + " pts et " + TimeManager.fullTime(penaliteTemps*60000)
            + " par " + intervalPenalite + " mn de d�passement");
        break;

      case AVECLIMITEHORAIRE:
        retour.append("Avec limite horaire � " + TimeManager.fullTime(heureLimite) 
            + " et p�nalit� de " + penalite + " pts et " + TimeManager.fullTime(penaliteTemps*60000)
            + " par " + intervalPenalite + " mn de d�passement");
        break;

      default:
        break;
    }
    retour.append("<br>");
    if(chrono)
    {
      retour.append("Epreuve chronom�tr�e entre ");
      if(es.isFirstEpreuve(this))
      {
        retour.append("le d�part et ");
      }
      else if(debutEpreuve)
      {
        retour.append("la balise " + es.getLastCodeEpreuvePrecedente(this) + " et ");
      }
      else
      {
        retour.append("la balise " + balises.getNumeroPremiereBalise() + " et ");
      }
      if(es.isLastEpreuveAvantArrivee(this))
      {
        retour.append("l'arriv�e.");
      }
      else if(finEpreuve)
      {
        retour.append("la balise " + es.getFirstCodeEpreuveSuivante(this));
      }
      else
      {
        retour.append("la balise " + balises.getNumeroDerniereBalise() + ".");
      }
      retour.append("<br>");
    }
    if(apresEpreuvePrecedente)
    {
      retour.append("Postes � valider apr�s l'�preuve p�c�dente.<br>");
    }
    if(avantEpreuveSuivante)
    {
      retour.append("Postes � valider avant l'�preuve suivante.<br>");
    }
    if(multiplicateurTemps > 1)
    {
      retour.append("Temps de l'�preuve X " + multiplicateurTemps + ".<br>");
    }
    if(ligne)
    {
      retour.append("Postes � valider dans l'ordre.<br>");
    }
    if(horsChrono)
    {
      retour.append("Epreuve effectu�e apr�s l'arriv�e.<br>");
    }
    retour.append("</code>");
    retour.append(balises.toStringBalises());
    retour.append("</li>");
    return retour.toString();
  }

  public boolean isAvantEpreuveSuivante()
  {
    return avantEpreuveSuivante;
  }

  /**
   * @return   = 1 ou  = 0
   */
  public String getAvantEpreuveSuivante()
  {
    return (isAvantEpreuveSuivante())? "1" : "0";
  }

  public void setAvantEpreuveSuivante(boolean avantEpreuveSuivante)
  {
    this.avantEpreuveSuivante = avantEpreuveSuivante;
  }

  public boolean isApresEpreuvePrecedente()
  {
    return apresEpreuvePrecedente;
  }

  /**
   * @return   = 1 ou  = 0
   */
  public String getApresEpreuvePrecedente()
  {
    return (isApresEpreuvePrecedente())? "1" : "0";
  }

  public void setApresEpreuvePrecedente(boolean apresEpreuvePrecedente)
  {
    this.apresEpreuvePrecedente = apresEpreuvePrecedente;
  }
  
  public String toCSV()
  {
    StringBuffer tampon = new StringBuffer ( nom) ;
    for(int i=0; i<balises.getSize(); i++)
    {
      tampon . append( ";" ) ;
      tampon . append( balises.getBalises().get(i).getNumero() + "") ;
    }
    
    return tampon . toString ( ) ;
  }
}
