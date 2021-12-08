/**
 * 
 */
package geRaid;

import java.awt.Point;
import java.util.Vector;

import outils.TimeManager;

import ihm.IhmGeRaidMain;
import to.Categorie;
import to.Categories;
import to.Epreuve;
import to.Equipe;
import to.Equipes;
import to.Etape;
import to.Parcours;
import to.Parcourss;
import to.Penalite;
import to.PenaliteIndividuelle;
import to.Penalites;
import to.PenalitesIndividuelles;
import to.ResultatPuce;
import to.ResultatsPuce;

/**
 * <P>
 * Titre : GeRaid.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class GeRaid
{
  public String nomRaid = "Nouveau raid";
  public String entete = "";
  public String version = "2.1.8";
  public String piedPage = "Ces résultats sont provisoires en attente des décisions du jury.";
  public Point heureZero = new Point(0, 0);
  public boolean resultatReduit = true;
  public boolean impressionReduite = false;
  public static boolean defilementJavaScript = false;
  public static int vitesse = 20;
  public boolean afficherNomsEquipiers = false;
  private Parcourss parcourss = new Parcourss();
  private ResultatsPuce resultatsPuce = new ResultatsPuce();
  private Equipes toutesLesEquipes = new Equipes();
  private IhmGeRaidMain ihm = null;
  private String fichier = "";
  public final String fichierConfig = "config.xml";
  private String nomAssociation = "Mon association";
  private String port = "";
  private String DossierDefault = "";
  private String DossierSauvegarde = "";
  private Categories categories = new Categories();
  public Categories categoriesNouveauRaid = new Categories();
  public Penalites penalites = new Penalites();
  private SIReaderHandler siHandler;
  private SIReaderHandlerPuce siHandlerPuce;
  private Vector<String> entetes = new Vector<String>();
  private Vector<PenalitesIndividuelles> data = new Vector<PenalitesIndividuelles>();
  public int pointsDefaut = 0;
  public int tempsDefaut = 0;
  public int pointsPmDefaut = 0;
  public int tempsPmDefaut = 0;

  public static int getTempsPause()
  {
    return vitesse*1000;
  }
  
  /**
   * @return the vitesse
   */
  public int getVitesse()
  {
    return vitesse;
  }

  /**
   * @param vitesse the vitesse to set
   */
  public void setVitesse(int vitesse)
  {
    GeRaid.vitesse = vitesse;
  }

  /**
   * @return  the RR = 1 ou C = 0
   */
  public String getDefilement()
  {
    if(defilementJavaScript)
    {
      return "1";
    }
    else
    {
      return "0";
    }
  }
  
  @SuppressWarnings("static-access")
  public void setDefilement(boolean rr)
  {
    this.defilementJavaScript = rr;
  }
  
  /**
   * @return  the RR = 1 ou C = 0
   */
  public String getResultatReduit()
  {
    if(resultatReduit)
    {
      return "1";
    }
    else
    {
      return "0";
    }
  }
  
  /**
   * @return  the RR = 1 ou C = 0
   */
  public String getImpressionReduite()
  {
    if(impressionReduite)
    {
      return "1";
    }
    else
    {
      return "0";
    }
  }
  
  public void setResultatReduit(boolean rr)
  {
    this.resultatReduit = rr;
  }

  
  public void setImpressionReduite(boolean rr)
  {
    this.impressionReduite = rr;
  }
  
  /**
   * @return the siHandlerPuce
   */
  public SIReaderHandlerPuce getSiHandlerPuce()
  {
    return siHandlerPuce;
  }

  /**
   * @param siHandlerPuce the siHandlerPuce to set
   */
  public void setSiHandlerPuce(SIReaderHandlerPuce siHandlerPuce)
  {
    this.siHandlerPuce = siHandlerPuce;
  }

  public GeRaid(IhmGeRaidMain ihm)
  {
    this.ihm = ihm;
    siHandler = new SIReaderHandler(this);
    siHandlerPuce = new SIReaderHandlerPuce(this);
  }

  /**
   * @return the parcourss
   */
  public Parcourss getParcourss()
  {
    return parcourss;
  }

  /**
   * @param parcourss the parcourss to set
   */
  public void setParcourss(Parcourss parcourss)
  {
    this.parcourss = parcourss;
  }
  
  public boolean addParcours(Parcours p)
  {
    boolean retour = parcourss.addParcours(p);
    
    return retour;
  }
  
  public boolean existeParcours(String nom, Parcours p)
  {
    return parcourss.existeParcours(nom, p);
  }
  
  public boolean addResultatPuce(ResultatPuce p)
  {
    boolean retour = resultatsPuce.addResultatPuce(p);
    
    return retour;
  }
  
  public void removeResultatPuce(ResultatPuce p)
  {
    resultatsPuce.removeResultatPuce(p);
    
  }
  
  public void removeParcours(Parcours p)
  {
    parcourss.removeParcours(p);
    resultatsPuce.effacerParcours(p);
  }
  public boolean editParcours(Parcours p, String nom)
  {
    return parcourss.editParcours(p, nom);
  }

  /**
   * @return the fichier
   */
  public String getFichier()
  {
    return fichier;
  }
  
  public String getFichierCourt()
  {
    int debut = fichier.lastIndexOf("\\");
    int fin = fichier.lastIndexOf(".");
    if(fin == -1)
    {
      fin = fichier.length();
    }
    return fichier.substring(debut+1, fin);
  }

  /**
   * @param fichier the fichier to set
   */
  public void setFichier(String fichier)
  {
      this.fichier = fichier;
  }

  /**
   * @return the ihm
   */
  public IhmGeRaidMain getIhm()
  {
    return ihm;
  }

  /**
   * @param ihm the ihm to set
   */
  public void setIhm(IhmGeRaidMain ihm)
  {
    this.ihm = ihm;
  }
  
  public void Clear()
  {
    parcourss.clearParcours();
    resultatsPuce.clearResultats();
    penalites.getPenalites().clear();
    data.clear();
    categories.getCategories().clear();
    fichier = "";
  }
  
  public boolean addEquipe(Parcours p, Equipe e)
  {
    return parcourss.addEquipe(p, e);
  }
  
  public Equipe editEquipe(Equipes es,Equipe e)
  {
    return es.editEquipe(e);
  }
  
  public boolean existeIdPuce(String idPuce, Equipe e)
  {
    return parcourss.existeIdPuce(idPuce, e);
  }
  
  public void removeEquipe(Equipes es,Equipe e)
  {
    es.removeEquipe(e);
    resultatsPuce.effacerEquipe(e);
  }

  /**
   * @return the nomAssociation
   */
  public String getNomAssociation()
  {
    return nomAssociation;
  }

  /**
   * @param nomAssociation the nomAssociation to set
   */
  public void setNomAssociation(String nomAssociation)
  {
    this.nomAssociation = nomAssociation;
  }

  /**
   * @return the categorie
   */
  public Categories getCategorie()
  {
    return categories;
  }

  /**
   * @param categorie the categorie to set
   */
  public void setCategorie(Categories categorie)
  {
    this.categories = categorie;
  }
  
  public void addCategorie(Categorie cat)
  {
    categories.addCategorie(cat);
  }
  
  public boolean existeCategorie(String nomCourt)
  {
    return categories.existeCategorie(nomCourt);
  }

  /**
   * @return the siHandler
   */
  public SIReaderHandler getSiHandler()
  {
    return siHandler;
  }

  /**
   * @param siHandler the siHandler to set
   */
  public void setSiHandler(SIReaderHandler siHandler)
  {
    this.siHandler = siHandler;
  }

  /**
   * @return the port
   */
  public String getPort()
  {
    return port;
  }

  /**
   * @param port the port to set
   */
  public void setPort(String port)
  {
    this.port = port;
  }

  /**
   * @return the dossierDefault
   */
  public String getDossierDefault()
  {
    return DossierDefault;
  }

  /**
   * @param dossierDefault the dossierDefault to set
   */
  public void setDossierDefault(String dossierDefault)
  {
    DossierDefault = dossierDefault;
  }

  /**
   * @return the dossierDefault
   */
  public String getDossierSauvegarde()
  {
    return DossierSauvegarde;
  }

  /**
   * @param dossierDefault the dossierDefault to set
   */
  public void setDossierSauvegarde(String dossier)
  {
    DossierSauvegarde = dossier;
  }
  
  public ResultatPuce getEquipe(String idEquipe)
  {
    ResultatPuce retour = new ResultatPuce();
    
    for(int i=0; i<parcourss.getSize(); i++)
    {
      Equipe equipe = parcourss.getParcourss().get(i).getEquipe(idEquipe);
      if(equipe != null)
      {
        Etape etape = parcourss.getParcourss().get(i).getEtapeEnCours();
        if(etape != null)
        {
          retour.setParcours(parcourss.getParcourss().get(i));
          retour.setEtape(etape);
          retour.setEquipe(equipe);
          return retour;
        }
      }
    }
    
    return retour;
  }
  
  public Equipe getEquipePuce(String idEquipe)
  {
    Equipe retour = null;
    
    for(int i=0; i<parcourss.getSize(); i++)
    {
      Equipe equipe = parcourss.getParcourss().get(i).getEquipe(idEquipe);
      if(equipe != null)
      {
          return equipe;
      }
    }
    
    return retour;
  }

  /**
   * @return the resultatsPuce
   */
  public ResultatsPuce getResultatsPuce()
  {
    return resultatsPuce;
  }

  /**
   * @param resultatsPuce the resultatsPuce to set
   */
  public void setResultatsPuce(ResultatsPuce resultatsPuce)
  {
    this.resultatsPuce = resultatsPuce;
  }
  
  public ResultatPuce existeResultatPuce(ResultatPuce rp)
  {
    return resultatsPuce.existeResultatPuce(rp);
  }
  
  public boolean existeResultatPuce(Equipe e)
  {
    return resultatsPuce.existeResultatPuce(e);
  }
  
  public boolean existePuce(String idPuce)
  {
    return parcourss.existePuce(idPuce);
  }
  
  public Parcours getParcoursIdPuce(String idPuce)
  {
    return parcourss.getParcoursIdPuce(idPuce);
  }
  
  public Parcours getParcours(Epreuve epreuve)
  {
    return parcourss.getParcours(epreuve);
  }
  
  public Equipe getEquipeSansPuce(Parcours p, int index)
  {
    Equipe retour = null;
    Equipes eqs = p.getEquipes();
    for(int i=index; i<eqs.getSize(); i++)
    {
      if(eqs.getEquipes().get(i).getIdPuce().compareTo("")==0)
      {
        return eqs.getEquipes().get(i);
      }
    }
    
    return retour;
  }
  
  private void editEntetes(Etape e)
  {
  // Visualisation simple
    entetes.clear();
  // Classement
    entetes.add("Dossard");
    entetes.add("Nom équipe");
    entetes.add("Puce");
    Vector<String> nbPenalites = penalites.getNbPenalites(e);
    for(int i=0; i<nbPenalites.size(); i++)
    {
      entetes.add(nbPenalites.get(i)+ " / points");
      entetes.add(nbPenalites.get(i)+ " / temps");
    }
  }
  
  public String[] getEntetes(Etape e)
  {
    editEntetes(e);
    return (String[]) entetes.toArray(new String[0]);
  }
  
  private void editData(Parcours p, Etape e)
  {
    data.clear();
    for(int i=0; i<p.getNbEquipes(); i++)
    {
      PenalitesIndividuelles pi = new PenalitesIndividuelles();
      pi.setDossard(p.getEquipes().getEquipes().get(i).getDossard());
      pi.setPuce(p.getEquipes().getEquipes().get(i).getIdPuce());
      pi.setEquipe(p.getEquipes().getEquipes().get(i).getNom());
      data.add(pi);
    }
    for(int i=0; i<penalites.getPenalites().size(); i++)
    {
      if(penalites.getPenalites().get(i).getEtape().equals(e))
      {
        for(int j=0; j<data.size(); j++)
        {
          if(penalites.getPenalites().get(i).aPuce(data.get(j).getPuce()))
          {
            data.get(j).getPoints().add(penalites.getPenalites().get(i).getPoints(data.get(j).getPuce())+"");
            data.get(j).getTemps().add(TimeManager.fullTime(penalites.getPenalites().get(i).getTemps(data.get(j).getPuce())*1000));
          }
          else
          {
            data.get(j).getPoints().add("");
            data.get(j).getTemps().add("");
          }
        }
      }
    }
    
  }
  
  public Object[][] getData(Parcours p, Etape e)
  {
    editData(p, e);
    Object[][] retour = new Object[data.size()][entetes.size()];
    for(int i=0; i<data.size(); i++)
    {
      retour[i][0] = data.get(i).getDossard();
      retour[i][1] = data.get(i).getEquipe();
      retour[i][2] = data.get(i).getPuce();
      for(int j=3; j<entetes.size(); j=j+2)
      {
        retour[i][j] = data.get(i).getPoints().get((j-3)/2);
        retour[i][j+1] = data.get(i).getTemps().get((j-3)/2);
      }
    }
    return  retour;
  }
  
  public Penalite getPenalite(Etape e, int index)
  {
    Penalite retour = null;
    int tmp = 0;
    for(int i=0; i<penalites.getPenalites().size(); i++)
    {
      if(penalites.getPenalites().get(i).getEtape().equals(e))
      {
        if(tmp == index)
        {
          return penalites.getPenalites().get(i);
        }
        else
        {
          tmp++;
        }
      }
    }
    
    return retour;
  }
  
  public PenaliteIndividuelle getPenaliteIndividuelle(Etape e, int indexPen, String puce)
  {
    PenaliteIndividuelle retour = null;
    Penalite p = getPenalite(e, indexPen);
    
    retour = p.getPenaliteIndividuelle(puce);
    
    return retour;
  }
  
  public String toStringParcours()
  {
    StringBuffer retour = new StringBuffer("<html><body>");
    
    retour.append(parcourss.toStringParcours());
    
    retour.append("</body></html>");
    return retour.toString();
  }
  
  public boolean isArrived(Equipe equipe)
  {
    boolean retour = false;
    retour = resultatsPuce.isArrived(equipe, (Parcours)ihm.jComboBoxParcours.getSelectedItem(), (Etape)ihm.jComboBoxEtapes.getSelectedItem());
    return retour;
  }

  public void removeResultatPuce(Equipe equipe)
  {
    resultatsPuce.effacerEquipe(equipe);
  }

  public Parcours getParcours(Etape etape)
  {
    return parcourss.getParcours(etape);
  }
  
  public Equipes getToutesLesEquipes(String debutNomEquipe)
  {
    if(debutNomEquipe.trim().compareTo("")==0)
    {
      return getToutesLesEquipes();
    }
    toutesLesEquipes = new Equipes();
    for(int i=0; i<parcourss.getSize(); i++)
    {
      toutesLesEquipes.getEquipes().addAll(parcourss.getParcourss().get(i).getEquipes().getEquipes(debutNomEquipe));
    }
    return toutesLesEquipes;
  }

  /**
   * @return the toutesLesEquipes
   */
  public Equipes getToutesLesEquipes()
  {
    toutesLesEquipes = new Equipes();
    for(int i=0; i<parcourss.getSize(); i++)
    {
      toutesLesEquipes.getEquipes().addAll(parcourss.getParcourss().get(i).getEquipes().getEquipes());
    }
    return toutesLesEquipes;
  }

  /**
   * @param toutesLesEquipes the toutesLesEquipes to set
   */
  public void setToutesLesEquipes(Equipes toutesLesEquipes)
  {
    this.toutesLesEquipes = toutesLesEquipes;
  }
  
  
  
}
