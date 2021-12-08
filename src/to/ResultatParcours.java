/**
 * 
 */
package to;

import geRaid.GeRaid;

import java.util.Vector;

import outils.TimeManager;

/**
 * <P>
 * Titre : ResultatParcours.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class ResultatParcours
{
  public GeRaid geRaid = null;
  public Parcours parcours = null;
  public Categorie categorie = null;
  private TypeVisualisationParcours tv = TypeVisualisationParcours.SIMPLE;
  private Vector<String> entetes = new Vector<String>();
  //private Vector<ResultatParcoursEquipe> data = new Vector<ResultatParcoursEquipe>();
  private ResultatsParcoursEquipe rpes = new ResultatsParcoursEquipe();
  private int nbRaiders = 0;
  private boolean visuRaiders = false;

  public ResultatParcours(GeRaid g, Parcours p, Categorie c, TypeVisualisationParcours tv, boolean puceParEquipe)
  {
    this.geRaid = g;
    this.parcours = p;
    this.categorie = c;
    this.tv = tv;
    //this.visuRaiders = raiders;
    editEntetes();
    editData(puceParEquipe);
  }
  
  private void editEntetes()
  {
    entetes.clear();
    entetes.add("Clt");
    entetes.add("Dossard");
    entetes.add("Puce");
    entetes.add("Nom équipe");
    entetes.add("Catégorie");
    entetes.add("Pts total");
    entetes.add("Tps final");
    if(!tv.equals(TypeVisualisation.SIMPLE))
    {
      if(parcours!=null)
      {
        if(tv.equals(TypeVisualisationParcours.AVECETAPE))
        {
          for(int i=0; i<parcours.getEtapes().getSize(); i++)
          {
            entetes.add(parcours.getEtapes().getEtapes().get(i).getNom());
            entetes.add("Points");
            entetes.add("Temps");
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
      //if(rp.getParcours().equals(parcours))
      if(rp.getParcours().estPresqueEgal(parcours))
      {
        int points = geRaid.penalites.getTotalPointPenalite(rp.getParcours(), rp.getEtape(), rp.getEquipe().getIdPuce());
        int temps = geRaid.penalites.getTotalTempsPenalite(rp.getParcours(),rp.getEtape(), rp.getEquipe().getIdPuce());

        if(geRaid.getCategorie().getCategories().contains(categorie))
        {
          if(rp.getEquipe().getCategorie().equals(categorie))
          {
            rpes.ajouterResultat(rp, points, temps);
            if(visuRaiders)
            {
              nbRaiders = nbRaiders + rp.getEquipe().getRaiders().getSize();
            }
          }
        }
        else
        {
          rpes.ajouterResultat(rp, points, temps);
          if(visuRaiders)
          {
            nbRaiders = nbRaiders + rp.getEquipe().getRaiders().getSize();
          }
        }
      }
    }
    rpes.trier(puceParEquipe);
    trierNC();
    faireClassement();
  }
  
  public Object[][] getData()
  {
    Object[][] retour = new Object[rpes.getSize()+nbRaiders][entetes.size()];
    int indexRaider = 0;
    for(int i=0; i<rpes.getSize(); i++)
    {
      if(rpes.getResultat().get(i).classement == -1)
      {
        retour[i + indexRaider][0] = "NC";
      }
      else
      {
        retour[i + indexRaider][0] = rpes.getResultat().get(i).classement;
      }
      retour[i + indexRaider][1] = rpes.getResultat().get(i).getEquipe().getDossard();
      retour[i + indexRaider][2] = rpes.getResultat().get(i).getEquipe().getIdPuce();
      retour[i + indexRaider][3] = rpes.getResultat().get(i).getEquipe().getNomEtRaiders();
      retour[i + indexRaider][4] = rpes.getResultat().get(i).getEquipe().getCategorie().getNomLong();
      retour[i + indexRaider][5] = rpes.getResultat().get(i).totalPoints;
      retour[i + indexRaider][6] = TimeManager.fullTime(rpes.getResultat().get(i).tempsCompense);
      int index = 0;
      for(int j=7; j<entetes.size(); j=j+3)
      {
        retour[i + indexRaider][j] = rpes.getResultat().get(i).etape[index];
        retour[i + indexRaider][j+1] = rpes.getResultat().get(i).points[index];
        retour[i + indexRaider][j+2] = rpes.getResultat().get(i).temps[index];
        index++;
      }
      Raiders raiders = rpes.getResultat().get(i).equipe.getRaiders();
      for(int k=0; k<raiders.getSize(); k++)
      {
        if(visuRaiders)
        {
          indexRaider++;
          retour[i + indexRaider][2] = raiders.getRaiders().get(k).toString();
        }
      }
    }
    return  retour;
  }
  
  private void faireClassement()
  {
    for(int i=0; i<rpes.getSize(); i++)
    {
      if(rpes.getResultat().get(i).classement != -1)
      {
        rpes.getResultat().get(i).classement = i+1;
      }
    }
  }
  
  private void trierNC()
  {
    int index = 0;
    for(int i=0; i<rpes.getSize(); i++)
    {
      if(rpes.getResultat().get(index).classement == -1)
      {
        rpes.getResultat().add(rpes.getResultat().get(index));
        rpes.getResultat().remove(index);
      }
      else
      {
        index++;
      }
    }
  }

}
