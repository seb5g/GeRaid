/**
 * 
 */
package to;

import geRaid.GeRaid;
import to.Epreuve;

import java.util.Vector;

/**
 * <P>
 * Titre : ResultatEtape.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class ResultatEtape
{

  private Categorie categorie = null;
  private TypeVisualisation tv = TypeVisualisation.SIMPLE;
  private Vector<String> entetes = new Vector<String>();
  private Vector<ResultatEquipe> data = new Vector<ResultatEquipe>();
  
  private GeRaid geRaid = null;
  /**
   * @return the geRaid
   */
  public GeRaid getGeRaid()
  {
    return geRaid;
  }

  private Etape etape = null;
  /**
   * @return the etape
   */
  public Etape getEtape()
  {
    return etape;
  }

  /**
   * @return the categorie
   */
  public Categorie getCategorie()
  {
    return categorie;
  }

  public ResultatEtape(GeRaid g, Etape e, Categorie c, TypeVisualisation tv, boolean puceParEquipe)
  {
    this.geRaid = g;
    this.etape = e;
    this.categorie = c;
    this.tv = tv;
    editEntetes();
    editData(puceParEquipe);
  }
  
  private void editEntetes()
  {
	// Visualisation simple
    entetes.clear();
	// Classement
    entetes.add("Clt");
    entetes.add("Dossard");
    entetes.add("Puce");
    entetes.add("Nom équipe");
    entetes.add("Catégorie");
    entetes.add("Tps final");
    entetes.add("Pts finaux");
	// Temps
    entetes.add("Départ");
    entetes.add("Arrivée");
    entetes.add("Tps course");
    entetes.add("Bonification");
    entetes.add("Pénalités");
    entetes.add("Dépassement");
    entetes.add("Gel");
    entetes.add("Pénalités hors étape"); // 14
	// Points
    entetes.add("Bonification");
    entetes.add("Pénalités");
    entetes.add("Dépassement");
    entetes.add("Pénalités hors étape"); // 18
	// Gestion des autres visualisations
    if(!tv.equals(TypeVisualisation.SIMPLE))
    {
      if(etape!=null)
      {
        for(int i=0; i<etape.getEpreuves().getSize(); i++)
        {
    			// Visualisation avec épreuves
    			Epreuve e = etape.getEpreuves().getEpreuves().get(i);
    			entetes.add(e.getNom());
    			// Classement
    		    entetes.add("Tps final");
    		    entetes.add("Pts finaux");
    			// Temps
    		    entetes.add("Tps course");
    		    entetes.add("Bonification");
    		    entetes.add("Pénalités");
    		    entetes.add("Dépassement");
    		    entetes.add("Gel");
    			// Points
    		    entetes.add("Bonification");
    		    entetes.add("Pénalités");
    		    entetes.add("Dépassement");
    			
    			// Visualisation complète
            if(tv.equals(TypeVisualisation.COMPLET))
            {
            for(int j=0; j<e.getBalises().getSize(); j++)
            {
    					entetes.add("Code");
    					entetes.add("Ok/Pm"); // heure de passage ou PM
    					entetes.add("Points");
    					entetes.add("Temps");
    				}
          }
        }
      }
    }
  }
  
  public String[] getEntetes()
  {
    return (String[]) entetes.toArray(new String[0]);
  }
  
  private void editData(boolean puceParEquipe)
  {
    for(int i=0; i<geRaid.getResultatsPuce().getSize(); i++)
    {
      ResultatPuce rp = geRaid.getResultatsPuce().getResultatsPuce().get(i);
      //if(rp.getEtape().equals(etape))
      if(rp.getParcours().estPresqueEgal(geRaid.getParcours(etape)) && rp.getEtape().estPresqueEgal(etape))
      {
        if(geRaid.getCategorie().getCategories().contains(categorie))
        {
          if(rp.getEquipe().getCategorie().equals(categorie))
          {
            ajouterResultat(rp);
          }
        }
        else
        {
          ajouterResultat(rp);
        }
      }
    }
    if(puceParEquipe)
    {
      simplifier();
    }
    trierNC();
    faireClassement();
  }
  
  private void ajouterResultat(ResultatPuce rp)
  {
    int points = geRaid.penalites.getTotalPointPenalite(geRaid.getParcours(etape),etape, rp.getEquipe().getIdPuce());
    int temps = geRaid.penalites.getTotalTempsPenalite(geRaid.getParcours(etape), etape, rp.getEquipe().getIdPuce());
    ResultatEquipe re = new ResultatEquipe(rp, points, temps);
    int index = indexAdd(re.totalPoints, re.totalTemps);
    if(index == -1)
    {
      data.add(re);
    }
    else
    {
      data.add(index, re);
    }
  }
  
  public Object[][] getData()
  {
    Object[][] retour = new Object[data.size()][entetes.size()];
    for(int i=0; i<data.size(); i++)
    {
      for(int j=0; j<entetes.size(); j++)
      {
        retour[i][j] = data.get(i).toVector(tv).get(j);
      }
    }
    return  retour;
  }
  
  public Object[][] getData(Epreuve e, TypeVisualisationEpreuve tv)
  {
    Object[][] retour = new Object[data.size()][Epreuve.getEntetes(e, tv).length];
    for(int i=0; i<data.size(); i++)
    {
      for(int j=0; j<Epreuve.getEntetes(e, tv).length; j++)
      {
        retour[i][j] = data.get(i).toVector(e, tv).get(j);
      }
    }
    return  retour;
  }
  
  private int indexAdd(int pts, long tps)
  {
    return indexFirstTemps(indexFirstPoints(pts), pts, tps) ;
  }
  
  private int indexFirstPoints(int pts)
  {
    for(int i=0; i<data.size(); i++)
    {
      if(data.get(i).totalPoints<=pts)
      {
        return i;
      }
    }
    return 0;
  }
  
  private int indexFirstTemps(int index, int pts, long tps)
  {
    for(int i=index; i<data.size(); i++)
    {
      if(data.get(i).totalPoints==pts && data.get(i).totalTemps>=tps)
      {
        return i;
      }
      if(data.get(i).totalPoints<pts)
      {
        return i;
      }
    }
    
    return -1;
  }
  
  private void faireClassement()
  {
    for(int i=0; i<data.size(); i++)
    {
      if(data.get(i).classement != -1)
      {
        data.get(i).classement = i+1;
      }
    }
  }
  
  private void trierNC()
  {
    int index = 0;
    for(int i=0; i<data.size(); i++)
    {
      if(data.get(index).classement == -1)
      {
        data.add(data.get(index));
        data.remove(index);
      }
      else
      {
        index++;
      }
    }
  }
  
  public void simplifier()
  {
    for(int i=0; i<data.size(); i++)
    {
      String dossard = data.get(i).dossardEquipe;
      if(dossard.compareTo("")!=0)
      {
        if(existeDossard(i+1, dossard))
        {
          data.remove(i);
          i--;
        }
      }
    }
  }
  
  private boolean existeDossard (int index, String dossard)
  {
    for(int i = index; i < data.size(); i++)
    {
      if(data.get(i).dossardEquipe.compareTo(dossard)==0)
      {
        return true;
      }
    }
    return false;
  }
}
