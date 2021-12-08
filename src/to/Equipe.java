package to;

import geRaid.GeRaid;

public class Equipe 
{
  private String nom = "";
  private Categorie categorie = null;
  private String dossard = "";
  private String IdPuce = "";
  private boolean NC = false;
  private boolean ABS = false;
  private Raiders raiders = new Raiders();
  private GeRaid geRaid;
  
  public Equipe(GeRaid geraid)
  {
    this.geRaid = geraid;
  }
  /**
   * @return the nC
   */
  public boolean isNC()
  {
    return NC;
  }
  /**
   * @param nC the nC to set
   */
  public void setNC(boolean nC)
  {
    NC = nC;
  }

  /**
   * @return  the NC = 1 ou C = 0
   */
  public String getNC()
  {
    return (isNC())? "1" : "0";
  }

  /**
   * @return  the NC = 1 ou C = 0
   */
  public String getABS()
  {
    return (isABS())? "1" : "0";
  }

  /**
   * @return  the NC = 1 ou C = 0
   */
  public String getNCString()
  {
    return (isNC())? "NC" : "";
  }
  
  public String getNomEtRaiders()
  {
    StringBuffer retour = new StringBuffer(getNom());
    if(geRaid.afficherNomsEquipiers)
    {
      for(int i=0; i<raiders.getSize(); i++)
      {
        retour . append( "\n" ) ;
        retour . append( raiders.getRaiders().get(i).getNom()) ;
        retour . append( " " ) ;
        retour . append( raiders.getRaiders().get(i).getPrenom()) ;
      }
    }
    
    return retour.toString();
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
   * @return the categorie
   */
  public Categorie getCategorie()
  {
    return categorie;
  }
  /**
   * @param categorie the categorie to set
   */
  public void setCategorie(Categorie categorie)
  {
    this.categorie = categorie;
  }
  /**
   * @return the dossard
   */
  public String getDossard()
  {
    return dossard;
  }
  /**
   * @param dossard the dossard to set
   */
  public void setDossard(String dossard)
  {
    this.dossard = dossard;
  }
  /**
   * @return the idPuce
   */
  public String getIdPuce()
  {
    return IdPuce;
  }
  /**
   * @param idPuce the idPuce to set
   */
  public void setIdPuce(String idPuce)
  {
    IdPuce = idPuce;
  }
  
  public String toString()
  {
    StringBuffer retour = new StringBuffer(dossard+ " ");
    retour.append(nom);
    retour.append(" " + IdPuce);
    if(categorie!=null)
    {
      retour.append(" " + categorie.getNomCourt());
    }
    retour.append(" " + getNCString());
    if(isABS())
    {
      retour.append(" ABS");
    }
    return retour.toString();
  }
  
  public String getDossardNom()
  {
    return dossard+" - "+nom + " - "+categorie.getNomCourt();
  }
  
  public String toCSV()
  {
    StringBuffer tampon = new StringBuffer ( dossard) ;
    tampon . append( ";" ) ;
    tampon . append( nom) ;
    tampon . append( ";" ) ;
    tampon . append( IdPuce) ;
    tampon . append( ";" ) ;
    tampon . append( categorie.getNomCourt()) ;
    for(int i=0; i<raiders.getSize(); i++)
    {
      tampon . append( ";" ) ;
      tampon . append( raiders.getRaiders().get(i).getNom()) ;
      tampon . append( ";" ) ;
      tampon . append( raiders.getRaiders().get(i).getPrenom()) ;
    }
    
    return tampon . toString ( ) ;
  }


  /**
   * @return the raiders
   */
  public Raiders getRaiders()
  {
    return raiders;
  }
  /**
   * @param raiders the raiders to set
   */
  public void setRaiders(Raiders raiders)
  {
    this.raiders = raiders;
  }
  /**
   * @return the aBS
   */
  public boolean isABS()
  {
    return ABS;
  }
  /**
   * @param aBS the aBS to set
   */
  public void setABS(boolean aBS)
  {
    ABS = aBS;
  }
  
  public boolean isArrived()
  {
    return geRaid.isArrived(this);
  }
}
