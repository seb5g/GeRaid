/**
 * 
 */
package to;

import geRaid.GeRaid;
import inOut.HtmlResultatEquipe;

import java.util.Date;
import java.util.Vector;

import outils.TimeManager;

/**
 * <P>
 * Titre : ResultatEquipe.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class ResultatEquipe
{
  private ResultatPuce rp = null;
  public int totalPoints = 0; // ce qui est retenue pour le classement après retrait ou ajout divers
  public int totalPointsFinal = 0; 
  public int totalPointsPenalite = 0;
  public int totalPointsDepassement = 0;
  private int totalPointsPenalitesHorsEpreuves = 0;
  private Date depart = null;
  private Date arrivee = null;
  public long totalTemps = 0; // ce qui est retenue pour le classement après retrait ou ajout divers
  public long totalTempsFinal = 0;
  public long totalGelChrono = 0;
  public long totaltempsPenalite = 0;
  public long totaltempsBonif = 0;
  public long totaltempsMultiplicateur = 0;
  public int classement = 0;
  public int penaliteDepassementEtapePoints = 0;
  public long penaliteDepassementEtapeTemps = 0;
  private int totalTempsPenalitesHorsEpreuves = 0;
  private String puce = "";
  public String dossardEquipe = "";
  private String nomEquipe = "";
  private String categorieEquipe = "";
  private ResultatEpreuves resultatEpreuves = null;
  public ListeCodeOkPm listeCodeOkPm = new ListeCodeOkPm();

  public ResultatEquipe(ResultatPuce rp, int points, int temps)
  {
    this.rp = rp;
    totalPointsPenalitesHorsEpreuves = points;
    totalTempsPenalitesHorsEpreuves = temps;
    // Classement NC
    if(rp.getEquipe().isNC())
    {
      classement = -1;
    }
	// Init identification de l'équipe
    dossardEquipe = rp.getEquipe().getDossard();
    puce = rp.getEquipe().getIdPuce();
    //nomEquipe = rp.getEquipe().getNom();
    nomEquipe = rp.getEquipe().getNomEtRaiders();
    categorieEquipe = rp.getEquipe().getCategorie().getNomLong();
	// Calcul du temps de course
    switch (rp.getEtape().getType())
    {
      case GROUPE:
        depart = rp.getEtape().getHeureDepart();
        listeCodeOkPm.getListe().add(new CodeOkPm(16, true, ""));
        break;
      case BOITIER:
        depart = rp.getPuce().getStarttime();
        // A tester si le départ n'est pas poinçonné
        if(depart.getTime()<0)
        {
          listeCodeOkPm.getListe().add(new CodeOkPm(16, false, ""));
        }
        else
        {
          listeCodeOkPm.getListe().add(new CodeOkPm(16, true, ""));
        }
        break;

      default:
        break;
    }
    arrivee = rp.getPuce().getFinishtime();
    totalTempsFinal = arrivee.getTime()-depart.getTime();
    
	// Calcul des  résultats des épreuves
    resultatEpreuves = new ResultatEpreuves();
    for(int i=0; i<rp.getEtape().getEpreuves().getSize(); i++)
    {
        Epreuve e = rp.getEtape().getEpreuves().getEpreuves().get(i);
  	  // Création du résultat de l'épreuve
        resultatEpreuves.addResultatEpreuve(e, rp);
        // Edition de la liste des codes Ok
        listeCodeOkPm.add(resultatEpreuves.getResultatEpreuves().get(i).codesATrouver, resultatEpreuves.getResultatEpreuves().get(i).okPm, resultatEpreuves.getResultatEpreuves().get(i).temps );
  	  // Cumul des points
  	  totalPoints = totalPoints + resultatEpreuves.getResultatEpreuves().get(i).totalPoints;
  	  totalPointsFinal = totalPointsFinal + resultatEpreuves.getResultatEpreuves().get(i).totalPointsFinal;
  	  totalPointsPenalite = totalPointsPenalite + resultatEpreuves.getResultatEpreuves().get(i).totalPointsPenalite;
  	  totalPointsDepassement = totalPointsDepassement + resultatEpreuves.getResultatEpreuves().get(i).totalPointsDepassement;
  	  // Cumul des temps
  	  totalGelChrono = totalGelChrono + resultatEpreuves.getResultatEpreuves().get(i).totalGelChrono;
  	  totaltempsBonif = totaltempsBonif + resultatEpreuves.getResultatEpreuves().get(i).totaltempsBonif;
  	  totaltempsPenalite = totaltempsPenalite + resultatEpreuves.getResultatEpreuves().get(i).totaltempsPenalite + resultatEpreuves.getResultatEpreuves().get(i).totaltempsdepassement*60;
  	  totaltempsMultiplicateur = totaltempsMultiplicateur + resultatEpreuves.getResultatEpreuves().get(i).totalTempsMultiplicateur;
    }
    // si l'arrivée n'est pas poinçonnée
    if(arrivee.getTime()>172800000 || arrivee.getTime()<0)
    {
      listeCodeOkPm.getListe().add(new CodeOkPm(15, false, ""));
    }
    else
    {
      listeCodeOkPm.getListe().add(new CodeOkPm(15, true, ""));
    }
	// Calcul des pénalités de dépassement
    switch (rp.getEtape().getTypeLimite())
    {
      case AVECLIMITEHORAIRE:
        penaliteDepassementHoraire();
        break;
      case AVECLIMITETEMPS:
        penaliteDepassementTemps();
        break;

      default:
        break;
    }
	// calculs finaux
    //totaltempsPenalite = totaltempsPenalite + totalTempsPenalitesHorsEpreuves;
    totalTemps = totalTempsFinal + totaltempsPenalite*1000 + totalTempsPenalitesHorsEpreuves*1000 + totaltempsBonif*1000 - totalGelChrono + penaliteDepassementEtapeTemps*60000 + totaltempsMultiplicateur;
    totalPointsPenalite = totalPointsPenalite + totalPointsDepassement ;
    totalPoints = totalPoints - penaliteDepassementEtapePoints + totalPointsPenalitesHorsEpreuves;
    
    // edition des postes en plus
    listeCodeOkPm.setCodesEnPlus(rp.getEtape().getEpreuves().getCodesEnPlus(rp));
  }
  
  
  private void penaliteDepassementHoraire()
  {
    long diff = arrivee.getTime() - rp.getEtape().getHeureLimite().getTime();
    if(diff > 0)
    {
      float minutes = (float) diff/60000/rp.getEtape().getIntervalPenalite();
      int mnEntamee = (int) Math.ceil(minutes);
      penaliteDepassementEtapePoints = mnEntamee*rp.getEtape().getPenalite();
      penaliteDepassementEtapeTemps = mnEntamee*rp.getEtape().getPenaliteTemps();
    }
  }
  
  
  private void penaliteDepassementTemps()
  {
    long diff = totalTempsFinal - rp.getEtape().getTempsLimite().getTime();
    if(rp.getEtape().isGelDansLimiteTemps())
    {
      diff = diff - totalGelChrono;
    }
    if(diff > 0)
    {
      float minutes = (float) diff/60000/rp.getEtape().getIntervalPenalite();
      int mnEntamee = (int) Math.ceil(minutes);
      penaliteDepassementEtapePoints = mnEntamee*rp.getEtape().getPenalite();
      penaliteDepassementEtapeTemps = mnEntamee*rp.getEtape().getPenaliteTemps();
    }
  }
  
  
  
  /**
   * @return the classement
   */
  public int getClassement()
  {
    return classement;
  }

  /**
   * @param classement the classement to set
   */
  public void setClassement(int classement)
  {
    this.classement = classement;
  }

  /**
   * @return the rp
   */
  public ResultatPuce getRp()
  {
    return rp;
  }

  /**
   * @param rp the rp to set
   */
  public void setRp(ResultatPuce rp)
  {
    this.rp = rp;
  }

  public Vector<String> toVector(TypeVisualisation tv)
  {
    Vector<String> retour = new Vector<String>();
    // Visualisation simple
    if(classement == -1)
    {
      retour.add("NC");
    }
    else
    {
      retour.add(classement+"");
    }
    retour.add(dossardEquipe);
    retour.add(puce);
    retour.add(nomEquipe);
    retour.add(categorieEquipe);
    retour.add(TimeManager.fullTime(totalTemps));
    retour.add(totalPoints+"");
    // Temps
    retour.add(TimeManager.fullTime(depart));
    retour.add(TimeManager.fullTime(arrivee));
    retour.add(TimeManager.fullTime(totalTempsFinal));
    retour.add(TimeManager.fullTime(totaltempsBonif*1000));
    retour.add(TimeManager.fullTime(totaltempsPenalite*1000));
    retour.add(TimeManager.fullTime(penaliteDepassementEtapeTemps*60000));
    retour.add(TimeManager.fullTime(totalGelChrono));
    retour.add(TimeManager.fullTime(totalTempsPenalitesHorsEpreuves*1000));
    // Points
    retour.add(totalPointsFinal+"");
    retour.add(totalPointsPenalite+"");
    retour.add(penaliteDepassementEtapePoints+"");
    retour.add(totalPointsPenalitesHorsEpreuves+"");
    // Autres visualisations
    if(!tv.equals(TypeVisualisation.SIMPLE))
    {
    // Visualisation avec épreuves
      for(int i=0; i<resultatEpreuves.getResultatEpreuves().size(); i++)
      {
		// Visualisation avec épreuves
		ResultatEpreuve re = resultatEpreuves.getResultatEpreuves().get(i);
		retour.add(""); // case vide du nom de l'épreuve
		// Classement
		retour.add(TimeManager.fullTime(re.totalTemps));
		retour.add(re.totalPoints+"");
		// Temps
	    retour.add(TimeManager.fullTime(re.totalTempsFinal));
	    retour.add(TimeManager.fullTime(re.totaltempsBonif*1000));
	    retour.add(TimeManager.fullTime(re.totaltempsPenalite*1000));
	    retour.add(TimeManager.fullTime(re.totaltempsdepassement*60000));
	    retour.add(TimeManager.fullTime(re.totalGelChrono));
		// Points
	    retour.add(re.totalPointsFinal+"");
	    retour.add(re.totalPointsPenalite+"");
	    retour.add(re.totalPointsDepassement+"");
		// Visualisation complète
		if(tv.equals(TypeVisualisation.COMPLET))
		{
			for(int j=0; j<re.getResultatBalises().size(); j++)
			{
				ResultatBalise rb = re.getResultatBalises().get(j);
				retour.add(rb.getCode()+"");
				if(!rb.isPm())
				{
					//retour.add("Ok"); 
          retour.add(rb.getTime()); 
				}
				else
				{
					retour.add("Pm"); 
				}
				retour.add(rb.getPoints()+"");
				if(rb.getTemps()<0)
				{
				  retour.add(TimeManager.fullTime(rb.getTemps()*1000));
				}
				else
        {
          retour.add(TimeManager.fullTime(rb.getTemps()*1000));
        }
			}
        }
      }
    }
    return retour;
  }

  
  public void saveHtml(GeRaid geraid, boolean resultatReduit)
  {
    HtmlResultatEquipe.save(this, "temp.html", geraid, resultatReduit);
  }
  
  public String toHtml()
  {
    StringBuffer retour = new StringBuffer();

    // Identification
    retour.append("<table><tr><td><table style=\"font-size:5px;\">");
    retour.append("<tr><td>Parcours : " + rp.getParcours().getNom() + "</td></tr>");
    retour.append("<tr><td>Etape : " + rp.getEtape().getNom() + "</td></tr></table>");    
    // Equipe
    retour.append("<td><table style=\"font-size:5px;\">Equipe : " + rp.getEquipe().getNom() + "</td></tr>");
    retour.append("<tr><td>Dossard : " + rp.getEquipe().getDossard() + "</td></tr>");
    for(int i=0; i<rp.getEquipe().getRaiders().getSize(); i++)
    {
      retour.append("<tr><td>Raider" + (i+1) + " : " + rp.getEquipe().getRaiders().getRaiders().get(i).toString() + "</td></tr>");
    }
    retour.append("</table></td></tr></table>");
    //  Résultats globaux de l'étape
  	//retour.append("<br>");
  	retour.append("<font size=1><b>Résultats de l'étape :</b><br>");
    retour.append("Temps final : ");
    retour.append(TimeManager.fullTime(totalTemps) + "<br>");
    retour.append("Total des points : ");
    retour.append(totalPoints + "<br>");
    // détails de l'étape
  	// Temps et points
  	retour.append("<b>Détails de l'étape :</b><br></font><table style=\"font-size:5px;\">");
    retour.append("<tr><td>Heure de départ : " + TimeManager.fullTime(depart) + "</td>");
    retour.append("<td>Bonification de temps : ");
    retour.append(TimeManager.fullTime(totaltempsBonif*1000) + "</td>");
    retour.append("<td>Bonification de points : ");
    retour.append(totalPointsFinal + "</td></tr>");
    retour.append("<tr><td>Heure d'arrivée : " + TimeManager.fullTime(arrivee) + "</td>");
    retour.append("<td>Pénalité de temps : ");
    retour.append(TimeManager.fullTime(totaltempsPenalite*1000) + "</td>");
    retour.append("<td>Pénalité de points : ");
    retour.append(totalPointsPenalite + "</td></tr>");
    retour.append("<tr><td>Temps de course : ");
    retour.append(TimeManager.fullTime(totalTempsFinal) + "</td>");
    retour.append("<td>Gel du chrono : ");
    retour.append(TimeManager.fullTime(totalGelChrono) + "</td>");
    retour.append("<td>Pénalité de dépassement de l'étape : ");
    retour.append(totalPointsDepassement + "</td></tr>");
    retour.append("<tr><td></td>");
    retour.append("<td>Pénalité de dépassement de l'étape : ");
    retour.append(TimeManager.fullTime(penaliteDepassementEtapeTemps*1000) + "</td></tr></table></font>");
	
    // par épreuve
    for(int i=0; i<resultatEpreuves.getResultatEpreuves().size(); i++)
    {
      ResultatEpreuve re = resultatEpreuves.getResultatEpreuves().get(i);
  		retour.append("<font size=1><b>Epreuve : " + rp.getEtape().getEpreuves().getEpreuves().get(i).getNom() + "</b><br><table style=\"font-size:5px;\">");
      retour.append("<tr><td>Temps final : ");
      retour.append(TimeManager.fullTime(re.totalTemps) + "</td>");
      retour.append("<td>Bonification de temps : ");
      retour.append(TimeManager.fullTime(re.totaltempsBonif*1000) + "</td>");
      retour.append("<td>Bonification de points : ");
      retour.append(re.totalPointsFinal + "</td></tr>");
      retour.append("<tr><td>Total des points : ");
      retour.append(re.totalPoints + "</td>");
      retour.append("<td>Pénalité de temps : ");
      retour.append(TimeManager.fullTime(re.totaltempsPenalite*1000) + "</td>");
      retour.append("<td>Pénalité de points : ");
      retour.append(re.totalPointsPenalite + "</td></tr>");
  		// récapitulatif temps et points
      retour.append("<tr><td>Temps de course : ");
      retour.append(TimeManager.fullTime(re.totalTempsFinal) + "</td>");
      retour.append("<td>Gel du chrono : ");
      retour.append(TimeManager.fullTime(re.totalGelChrono) + "</td>");
      retour.append("<td>Pénalité de dépassement de l'épreuve : ");
      retour.append(re.totalPointsDepassement + "</td></tr>");
      retour.append("<tr><td></td>");
      retour.append("<td>Pénalité de dépassement de l'épreuve : ");
      retour.append(TimeManager.fullTime(re.totaltempsdepassement*1000) + "</td></tr></table>");

  		// Détails par balise
      retour.append("<table style=\"font-size:5px;\"><tr><td>Code</td><td>Ok-Pm</td><td>Points</td><td>Temps</td><td>Code</td><td>Ok-Pm</td><td>Points</td><td>Temps</td>" +
      		"<td>Code</td><td>Ok-Pm</td><td>Points</td><td>Temps</td><td>Code</td><td>Ok-Pm</td><td>Points</td><td>Temps</td></tr>");
  		for(int j=0; j<resultatEpreuves.getResultatEpreuves().get(i).getResultatBalises().size(); j=j+4)
  		{
        retour.append("<tr>");
  		  for(int k=0; k<4; k++)
  		  {
  		    if(resultatEpreuves.getResultatEpreuves().get(i).getResultatBalises().size()>j+k)
  		    {
      		  ResultatBalise rb = resultatEpreuves.getResultatEpreuves().get(i).getResultatBalises().get(j+k);
            retour.append("<td>" + rb.getCode() + "</td><td>");
            if(rb.isPm())
            {
              retour.append("PM</td>");
            }
            else
            {
              retour.append("OK</td>");
            }
            retour.append("<td>" + rb.getPoints() + "</td><td>" + TimeManager.fullTime(rb.getTemps()*1000) + "</td>");
    		  }
  		  }
        retour.append("</tr>");
  		}
      retour.append("</table></font>");
	
    }
    return retour.toString();
  }
  
  public String toHtmlReduit()
  {
    StringBuffer retour = new StringBuffer();
    // Identification
    retour.append("<b>Parcours :</b> " + rp.getParcours().getNom() + "<br>");
    retour.append("<b>Etape :</b> " + rp.getEtape().getNom() + "<br>");    
    // Equipe
    retour.append("<b>Catégorie :</b> " + rp.getEquipe().getCategorie().getNomLong() + "<br>");    
    retour.append("<b>Equipe :</b> " + rp.getEquipe().getNom() + "<br>");   
    retour.append("<b>Dossard :</b> " + rp.getEquipe().getDossard() + "<br>");
    retour.append("<b>Puce :</b> " + rp.getEquipe().getIdPuce() + "<br>");
    for(int i=0; i<rp.getEquipe().getRaiders().getSize(); i++)
    {
      retour.append("<b>Raider" + (i+1) + " :</b> " + rp.getEquipe().getRaiders().getRaiders().get(i).toString() + "<br>");
    }
    //  Résultats globaux de l'étape
    //retour.append("<br>");
    retour.append("<br>");
    retour.append("<b>Départ :</b> ");
    retour.append(TimeManager.fullTime(depart) + "<br>");
    retour.append("<b>Arrivée :</b> ");
    retour.append(TimeManager.fullTime(arrivee) + "<br>");
    retour.append("<b>Temps de course :</b> ");
    retour.append(TimeManager.fullTime(arrivee.getTime() - depart.getTime()) + "<br>");
    retour.append("<b>Temps final :</b> ");
    retour.append(TimeManager.fullTime(totalTemps) + "<br>");
    if(totalPointsFinal != 0)
    {
      retour.append("<b>Points avant Bonif/Penal:</b> ");
      retour.append(totalPointsFinal + "<br>");
    }
    if(totalPointsPenalite != 0)
    {
      retour.append("<b>Pénalité de points :</b> ");
      retour.append(totalPointsPenalite + "<br>");
    }
    if(totalPointsDepassement != 0)
    {
      retour.append("<b>Pénalité dépassement étape :</b> ");
      retour.append(totalPointsDepassement + "<br>");
    }
    if(totalPoints != 0)
    {
      retour.append("<b>Total des points :</b> ");
      retour.append(totalPoints + "<br>");
    }
    if(totaltempsBonif != 0)
    {
      retour.append("<b>Bonification de temps :</b> ");
      retour.append(TimeManager.fullTime(totaltempsBonif*1000) + "<br>");
    }
    if(totaltempsPenalite != 0)
    {
      retour.append("<b>Pénalité de temps :</b> ");
      retour.append(TimeManager.fullTime(totaltempsPenalite*1000) + "<br>");
    }
    if(totalGelChrono != 0)
    {
      retour.append("<b>Gel du chrono :</b> ");
      retour.append(TimeManager.fullTime(totalGelChrono) + "<br>");
    }
    if(penaliteDepassementEtapeTemps != 0)
    {
      retour.append("<b>Pénalité dépassement étape :</b> ");
      retour.append(TimeManager.fullTime(penaliteDepassementEtapeTemps*1000) + "<br>");
    }
    retour.append("<br>");
    
    // par épreuve

    int index = 0;
    for(int i=0; i<resultatEpreuves.getResultatEpreuves().size(); i++)
    {
      retour.append(rp.getEtape().getEpreuves().getEpreuves().get(i).getNom() + "<br>");

      // Détails par balise
      retour.append("<table cellspacing=0 style='font-size:7pt;'><tr align=center><th>code</th><th>temps</th><th>code</th><th>temps</th><th>code</th><th>temps</th></tr>");
      for(int j=0; j<resultatEpreuves.getResultatEpreuves().get(i).getResultatBalises().size(); j=j+3)
      {
        retour.append("<tr align=center><td height=0><b>" + listeCodeOkPm.getListe().get(index + j + 1).getCode() + "</b></td>");
        retour.append("<td height=0>" + listeCodeOkPm.getListe().get(index + j + 1).getTemps() + "</td>");
        if(j+1<resultatEpreuves.getResultatEpreuves().get(i).getResultatBalises().size())
        {
          retour.append("<td height=0><b>" + listeCodeOkPm.getListe().get(index + j+2).getCode() + "</b></td>");
          retour.append("<td height=0>" + listeCodeOkPm.getListe().get(index + j+2).getTemps() + "</td>");
        }
        if(j+2<resultatEpreuves.getResultatEpreuves().get(i).getResultatBalises().size())
        {
          retour.append("<td height=0><b>" + listeCodeOkPm.getListe().get(index + j+3).getCode() + "</b></td>");
          retour.append("<td height=0>" + listeCodeOkPm.getListe().get(index + j+3).getTemps() + "</td>");
        }
        retour.append("</tr>");
      }
      index = index + resultatEpreuves.getResultatEpreuves().get(i).getResultatBalises().size();
      retour.append("</table>");
  
    }
    
    /*
    // balises complètes
    retour.append("<table cellspacing=0 style='font-size:7pt;'><tr align=center><th>code</th><th>temps</th><th>code</th><th>temps</th><th>code</th><th>temps</th></tr>");
    for(int i=1; i<listeCodeOkPm.getListe().size()-1; i=i+3)
    {
      retour.append("<tr align=center><td height=0><b>" + listeCodeOkPm.getListe().get(i).getCode() + "</b></td>");
      retour.append("<td height=0>" + listeCodeOkPm.getListe().get(i).getTemps() + "</td>");
      if(i+1<listeCodeOkPm.getListe().size()-1)
      {
        retour.append("<td height=0><b>" + listeCodeOkPm.getListe().get(i+1).getCode() + "</b></td>");
        retour.append("<td height=0>" + listeCodeOkPm.getListe().get(i+1).getTemps() + "</td>");
      }
      if(i+2<listeCodeOkPm.getListe().size()-1)
      {
        retour.append("<td height=0><b>" + listeCodeOkPm.getListe().get(i+2).getCode() + "</b></td>");
        retour.append("<td height=0>" + listeCodeOkPm.getListe().get(i+2).getTemps() + "</td>");
      }
      retour.append("</tr>");
    }
    retour.append("</table>");
    */
    
    return retour.toString();
  }

  public Vector<String> toVector(Epreuve e, TypeVisualisationEpreuve tv)
  {
    Vector<String> retour = new Vector<String>();
    // Visualisation simple
    if(classement == -1)
    {
      retour.add("NC");
    }
    else
    {
      retour.add(classement+"");
    }
    retour.add(dossardEquipe);
    retour.add(puce);
    retour.add(nomEquipe);
    retour.add(categorieEquipe);
    // Visualisation avec épreuves
      for(int i=0; i<resultatEpreuves.getResultatEpreuves().size(); i++)
      {
        // Visualisation avec épreuves
        ResultatEpreuve re = resultatEpreuves.getResultatEpreuves().get(i);
        //if(re.epreuve.equals(e))
        if(re.epreuve.getNom().compareTo(e.getNom())==0)
        {
          // Classement
          retour.add(TimeManager.fullTime(re.totalTemps));
          retour.add(re.totalPoints+"");
          // Temps
            retour.add(TimeManager.fullTime(re.totalTempsFinal));
            retour.add(TimeManager.fullTime(re.totaltempsBonif*1000));
            retour.add(TimeManager.fullTime(re.totaltempsPenalite*1000));
            retour.add(TimeManager.fullTime(re.totaltempsdepassement*60000));
            retour.add(TimeManager.fullTime(re.totalGelChrono));
          // Points
            retour.add(re.totalPointsFinal+"");
            retour.add(re.totalPointsPenalite+"");
            retour.add(re.totalPointsDepassement+"");
          // Visualisation complète
          if(tv.equals(TypeVisualisationEpreuve.AVECBALISES))
          {
            for(int j=0; j<re.getResultatBalises().size(); j++)
            {
              ResultatBalise rb = re.getResultatBalises().get(j);
              retour.add(rb.getCode()+"");
              if(!rb.isPm())
              {
                //retour.add("Ok"); 
                retour.add(rb.getTime()); 
              }
              else
              {
                retour.add("Pm"); 
              }
              retour.add(rb.getPoints()+"");
              if(rb.getTemps()<0)
              {
                retour.add(TimeManager.fullTime(rb.getTemps()*1000));
              }
              else
              {
                retour.add(TimeManager.fullTime(rb.getTemps()*1000));
              }
            }
          }
        }
      }
    
    return retour;
  }
}