/**
 * 
 */
package to;

import java.util.Date;
import java.util.Vector;

import outils.AuScore;
import outils.EnLigne;
import outils.TimeManager;
import to.ResultatBalise;


/**
 * <P>
 * Titre : ResultatEpreuve.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author Thierry
 *
 */
public class ResultatEpreuve
{
  public Epreuve epreuve = null;
  public int totalPoints = 0; // ce qui est retenue pour le classement après retrait ou ajout divers
  public int totalPointsFinal = 0; 
  public int totalPointsPenalite = 0;
  public int totalPointsDepassement = 0;
  //public long totalPointsMultiplicateur = 0;
  public long totalTemps = 0; // ce qui est retenue pour le classement après retrait ou ajout divers
  public long totalTempsFinal = 0;
  public long totalGelChrono = 0;
  public long totaltempsBonif = 0;
  public long totaltempsPenalite = 0;
  public long totaltempsdepassement = 0;
  public long totalTempsMultiplicateur = 0;
  private Vector<ResultatBalise> resultatBalises = new Vector<ResultatBalise>();
  private Date depart = null;
  private Date arrivee = null;
  public int [] codesATrouver;
  public boolean[] okPm;
  public String[] temps;
  
  public ResultatEpreuve(Epreuve e, ResultatPuce rp)
  {
    epreuve = e;
    // récupération des codes à trouver
    codesATrouver = e.getCodes();
    // récupération des codes de la puce
    Vector<Integer> codesPuce = rp.getCodes();
    Vector<String> tempsPuce = rp.getTemps();
    // calcul des OK et PM
    okPm = new boolean[codesATrouver.length];
    temps = new String[codesATrouver.length];
    if(e.isLigne())
    {
      EnLigne el = new EnLigne(codesATrouver, codesPuce, tempsPuce);
      okPm = el.getOkPm();
      temps = el.getTemps();
    }
    else
    {
      AuScore as = new AuScore(codesATrouver, codesPuce, tempsPuce);
      okPm =as.getOkPm();
      temps = as.getTemps();
    }
    // Calcul de la position par rapport aux épreuves précédentes et suivantes
    if(e.isAvantEpreuveSuivante())
    {
      avantEpreuveSuivante(e, rp);
    }
    if(e.isApresEpreuvePrecedente())
    {
      apresEpreuvePrecedente(e, rp);
    }
    // calcul du temps de course de l'épreuve
    if(e.isChrono())
    {
      // départ de l'épreuve
      if(rp.getEtape().isFirstEpreuve(e))
      {
        switch (rp.getEtape().getType())
        {
          case GROUPE:
            depart = rp.getEtape().getHeureDepart();
            break;
          case BOITIER:
            depart = rp.getPuce().getStarttime();
            break;

          default:
            break;
        }
      }
      else
      {
        if(e.isDebutEpreuve())
        {
          depart = rp.getPuce().getTimePartielLastCode(rp.getEtape().getLastCodePreviousEpreuve(e));
        }
        else
        {
          if(codesATrouver.length>0)
          {
            depart = rp.getPuce().getTimePartielFirstCode(codesATrouver[0]);
          }
          else
          {
            depart = TimeManager.NO_TIME;
          }
        }
      }

      // Arrivée de l'épreuve
      if(rp.getEtape().isLastEpreuve(e))
      {
        arrivee = rp.getPuce().getFinishtime();
      }
      else
      {
        if(e.isFinEpreuve())
        {
          arrivee = rp.getPuce().getTimePartielFirstCode(rp.getEtape().getfirstCodeNextEpreuve(e));
        }
        else
        {
          if(codesATrouver.length>0)
          {
            if(isDoubleCode(codesATrouver[0]))
            {
              arrivee = rp.getPuce().getTimePartielLastCode(codesATrouver[codesATrouver.length - 1]);
            }
            else
            {
              arrivee = rp.getPuce().getTimePartielFirstCode(codesATrouver[codesATrouver.length - 1]);
            }
          }
          else
          {
            arrivee = TimeManager.NO_TIME;
          }
        }
      }
      if(e.isHorsChrono())
      {
        if(e.isFinEpreuve())
        {
          arrivee = rp.getPuce().getTimePartielFirstCode(rp.getEtape().getfirstCodeNextEpreuve(e));
        }
        else
        {
          if(isDoubleCode(codesATrouver[0]))
          {
            arrivee = rp.getPuce().getTimePartielLastCode(codesATrouver[codesATrouver.length - 1]);
          }
          else
          {
            arrivee = rp.getPuce().getTimePartielFirstCode(codesATrouver[codesATrouver.length - 1]);
          }
        }
      }
      
      // durée de l'épreuve
      if(depart != null && arrivee != null)
      {
        totalTempsFinal = arrivee.getTime()-depart.getTime();
      }
    }
	
	// Calcul des résultats en fonction des balises trouvées
	calculResultatBalises(e, rp);
	calculPointsTemps();
	calculDepassement(e, rp);
	// derniers calculs
	totalPoints = totalPointsFinal + totalPointsPenalite - totalPointsDepassement;
	totalTemps = totalTempsFinal + totaltempsBonif*1000 + totaltempsPenalite*1000 + totaltempsdepassement*60000 - totalGelChrono;
	totalTempsMultiplicateur = totalTemps * (epreuve.getMultiplicateurTemps() - 1);
	totalTemps = totalTemps * epreuve.getMultiplicateurTemps();
	//totalPointsMultiplicateur = totalPoints * (epreuve.getMultiplicateurTemps() - 1);
	//totalPoints = totalPoints * epreuve.getMultiplicateurTemps();
  }
  
  private void calculDepassement(Epreuve e, ResultatPuce rp)
  {
    switch (e.getTypeLimite())
    {
      case AVECLIMITEHORAIRE:
        penaliteDepassementHoraire(e);
        break;
      case AVECLIMITETEMPS:
        penaliteDepassementTemps(e);
        break;

      default:
        break;
    }
  }
  
  private void penaliteDepassementHoraire(Epreuve e)
  {
    if(arrivee!=null)
    {
    long diff = arrivee.getTime() - e.getHeureLimite().getTime();
    if(diff > 0)
    {
      float minutes = (float) diff/60000/e.getIntervalPenalite();
      int mnEntamee = (int) Math.ceil(minutes);
      totaltempsdepassement = mnEntamee*e.getPenaliteTemps();
      totalPointsDepassement = mnEntamee*e.getPenalite();
    }
    }
  }
  
  
  private void penaliteDepassementTemps(Epreuve e)
  {
    long diff = totalTempsFinal - e.getTempsLimite().getTime();
    if(diff > 0)
    {
      float minutes = (float) diff/60000/e.getIntervalPenalite();
      int mnEntamee = (int) Math.ceil(minutes);
      totaltempsdepassement = mnEntamee*e.getPenaliteTemps();
      totalPointsDepassement = mnEntamee*e.getPenalite();
    }
  }
  
  private void calculPointsTemps()
  {
  	for(int i=0; i<resultatBalises.size(); i++)
  	{
  	  if(resultatBalises.get(i).getPoints()>0)
  	  {
  	    totalPointsFinal = totalPointsFinal + resultatBalises.get(i).getPoints();
  	  }
  	  else
  	  {
  	    totalPointsPenalite = totalPointsPenalite + resultatBalises.get(i).getPoints();
  	  }
      if(resultatBalises.get(i).getTemps()>0)
      {
        totaltempsPenalite = totaltempsPenalite + resultatBalises.get(i).getTemps();
      }
      else
      {
        totaltempsBonif = totaltempsBonif + resultatBalises.get(i).getTemps();
      }
  	}
  }
  
  private void calculResultatBalises(Epreuve e, ResultatPuce rp)
  {
	// Calcul par balise
    // Init Gestion du gel
    boolean chronoEnCours = true;
    long arretChrono = 0;
	for(int i=0; i<codesATrouver.length; i++)
	{
		ResultatBalise rb = new ResultatBalise();
		Balise balise = e.getBalises().getBalises().get(i);
		rb.setCode(balise.getNumero());
		if(okPm[i])
		{
			rb.setPoints(balise.getPoints());
			rb.setTemps(balise.getTemps());
			rb.setTime(temps[i]);
		}
		else
		{
			rb.setPm(true);
			rb.setPoints(balise.getPointsPm());
			rb.setTemps(balise.getTempsPm());
		}
		resultatBalises.add(rb);
		// gestion du gel du chrono
		if(balise.isArreterChrono())
		{
			chronoEnCours =false;
			//arretChrono = rp.getPuce().getTimePartielFirstCode(balise.getNumero()).getTime();
      arretChrono = TimeManager.safeParse(temps[i]).getTime();
		}
		if(balise.isDemarerChrono() && !chronoEnCours)
		{
			chronoEnCours = true;
			//totalGelChrono = totalGelChrono + rp.getPuce().getTimePartielFirstCode(balise.getNumero()).getTime() - arretChrono;
      totalGelChrono = totalGelChrono + TimeManager.safeParse(temps[i]).getTime() - arretChrono;
		}
	}
  }
  
  private boolean isDoubleCode(int code)
  {
	if(codesATrouver[0] == codesATrouver[codesATrouver.length - 1])
	{
		return true;
	}
	return false;
  }
  
  /**
   * @return the totalPoints
   */
  public int getTotalPoints()
  {
    return totalPoints;
  }
  
  /**
   * @return the totaltempsdepassement
   */
  public long getTotaltempsdepassement()
  {
    return totaltempsdepassement;
  }
  /**
   * @param totaltempsdepassement the totaltempsdepassement to set
   */
  public void setTotaltempsdepassement(long totaltempsdepassement)
  {
    this.totaltempsdepassement = totaltempsdepassement;
  }
  /**
   * @param totalPoints the totalPoints to set
   */
  public void setTotalPoints(int totalPoints)
  {
    this.totalPoints = totalPoints;
  }
  /**
   * @return the totalPointsFinal
   */
  public int getTotalPointsFinal()
  {
    return totalPointsFinal;
  }
  /**
   * @param totalPointsFinal the totalPointsFinal to set
   */
  public void setTotalPointsFinal(int totalPointsFinal)
  {
    this.totalPointsFinal = totalPointsFinal;
  }
  /**
   * @return the totalPointsPenalite
   */
  public int getTotalPointsPenalite()
  {
    return totalPointsPenalite;
  }
  /**
   * @param totalPointsPenalite the totalPointsPenalite to set
   */
  public void setTotalPointsPenalite(int totalPointsPenalite)
  {
    this.totalPointsPenalite = totalPointsPenalite;
  }
  /**
   * @return the totalPointsDepassement
   */
  public int getTotalPointsDepassement()
  {
    return totalPointsDepassement;
  }
  /**
   * @param totalPointsDepassement the totalPointsDepassement to set
   */
  public void setTotalPointsDepassement(int totalPointsDepassement)
  {
    this.totalPointsDepassement = totalPointsDepassement;
  }
  /**
   * @return the totalTemps
   */
  public long getTotalTemps()
  {
    return totalTemps;
  }
  /**
   * @param totalTemps the totalTemps to set
   */
  public void setTotalTemps(long totalTemps)
  {
    this.totalTemps = totalTemps;
  }
  /**
   * @return the totalTempsFinal
   */
  public long getTotalTempsFinal()
  {
    return totalTempsFinal;
  }
  /**
   * @param totalTempsFinal the totalTempsFinal to set
   */
  public void setTotalTempsFinal(long totalTempsFinal)
  {
    this.totalTempsFinal = totalTempsFinal;
  }
  /**
   * @return the totalGelChrono
   */
  public long getTotalGelChrono()
  {
    return totalGelChrono;
  }
  /**
   * @param totalGelChrono the totalGelChrono to set
   */
  public void setTotalGelChrono(long totalGelChrono)
  {
    this.totalGelChrono = totalGelChrono;
  }
  /**
   * @return the totaltempsPenalite
   */
  public long getTotaltempsPenalite()
  {
    return totaltempsPenalite;
  }
  /**
   * @param totaltempsPenalite the totaltempsPenalite to set
   */
  public void setTotaltempsPenalite(long totaltempsPenalite)
  {
    this.totaltempsPenalite = totaltempsPenalite;
  }

  /**
   * @return the totaltempsBonif
   */
  public long getTotaltempsBonif()
  {
    return totaltempsBonif;
  }

  /**
   * @param totaltempsBonif the totaltempsBonif to set
   */
  public void setTotaltempsBonif(long totaltempsBonif)
  {
    this.totaltempsBonif = totaltempsBonif;
  }

  /**
   * @return the totaltempsBonif
   */
  public Vector<ResultatBalise> getResultatBalises()
  {
    return resultatBalises;
  }

  /**
   * @param totaltempsBonif the totaltempsBonif to set
   */
  public void setResultatBalises(Vector<ResultatBalise> resultatBalises)
  {
    this.resultatBalises = resultatBalises;
  }

  private void avantEpreuveSuivante(Epreuve e, ResultatPuce rp)
  {
    Balises balises = rp.getEtape().getBalisesEpreuveSuivante(e);
    if(balises != null)
    {
      for(int i=0; i<okPm.length; i++)
      {
        if(okPm[i])
        {
          int positionPosteATrouver = rp.getPositionPoste(codesATrouver[i]);
          for(int j=0; j<balises.getBalises().size(); j++)
          {
            int positionPosteSuivant = rp.getPositionPoste(balises.getBalises().get(j).getNumero());
            if(positionPosteSuivant>-1 && positionPosteATrouver>positionPosteSuivant)
            {
              okPm[i]=false;
            }
          }
        }
      }
    }
  }

  private void apresEpreuvePrecedente(Epreuve e, ResultatPuce rp)
  {
    Balises balises = rp.getEtape().getBalisesEpreuvePrcedente(e);
    if(balises != null)
    {
      for(int i=0; i<okPm.length; i++)
      {
        if(okPm[i])
        {
          int positionPosteATrouver = rp.getPositionPoste(codesATrouver[i]);
          for(int j=0; j<balises.getBalises().size(); j++)
          {
            int positionPostePrecedent = rp.getPositionPoste(balises.getBalises().get(j).getNumero());
            if(positionPostePrecedent>-1 && positionPosteATrouver<positionPostePrecedent)
            {
              okPm[i]=false;
            }
          }
        }
      }
    }
  }
}
