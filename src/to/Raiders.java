/**
 * 
 */
package to;

import java.util.Vector;

/**
 * <P>
 * Titre : Raiders.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Raiders
{
  private Vector<Raider> raiders = new Vector<Raider>();
  
  public Raiders()
  {
    
  }

  /**
   * @return the raiders
   */
  public Vector<Raider> getRaiders()
  {
    return raiders;
  }

  /**
   * @param raiders the raiders to set
   */
  public void setRaiders(Vector<Raider> raiders)
  {
    this.raiders = raiders;
  }
  
  public int getSize()
  {
    return raiders.size();
  }
  
  public Object[][] getData()
  {
    Object[][] retour = new Object[raiders.size()][2];
    for(int i=0; i<raiders.size(); i++)
    {
        retour[i][0] = raiders.get(i).getNom();
        retour[i][1] = raiders.get(i).getPrenom();
    }
    
    return  retour;
  }
  
  public void addRaider(Raider r)
  {
    raiders.add(r);
  }
  
  public Raider getRaider(String nom, String prenom)
  {
    Raider retour = null;
    for(int i=0; i<raiders.size(); i++)
    {
      if(raiders.get(i).getNom().trim().compareTo(nom.trim()) == 0 &&
          raiders.get(i).getPrenom().trim().compareTo(prenom.trim()) == 0)
      {
        retour = raiders.get(i);
        break;
      }
    }
    return retour;
  }
  
  public void removeRaider(String nom, String prenom)
  {
    for(int i=0; i<raiders.size(); i++)
    {
      if(raiders.get(i).getNom().trim().compareTo(nom.trim()) == 0 &&
          raiders.get(i).getPrenom().trim().compareTo(prenom.trim()) == 0)
      {
        raiders.remove(i);
        break;
      }
    }
  }
}
