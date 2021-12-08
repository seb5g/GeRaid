/**
 * 
 */
package to;

import java.util.Vector;

/**
 * <P>
 * Titre : Categories.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Categories
{
  private Vector<Categorie> categories = new Vector<Categorie>();
  
  public Categories()
  {
    
  }

  /**
   * @return the categories
   */
  public Vector<Categorie> getCategories()
  {
    return categories;
  }

  /**
   * @param categories the categories to set
   */
  public void setCategories(Vector<Categorie> categories)
  {
    this.categories = categories;
  }
  
  public void addCategorie(Categorie cat)
  {
    if(!existeCategorie(cat.getNomLong(), cat.getNomCourt()))
    {
      categories.add(cat);
    }
  }
  
  public int getSize()
  {
    return categories.size();
  }
  
  public Categorie getCategorie(String nomCourt)
  {
    for(int i=0; i<categories.size(); i++)
    {
      if(categories.get(i).getNomCourt().compareTo(nomCourt)==0)
      {
        return categories.get(i);
      }
    }
    return null;
  }
  
  public Object[][] getData()
  {
    Object[][] retour = new Object[categories.size()][2];
    for(int i=0; i<categories.size(); i++)
    {
        retour[i][0] = categories.get(i).getNomLong();
        retour[i][1] = categories.get(i).getNomCourt();
    }
    
    return  retour;
  }
  
  public void removeCategorie(String nomLong)
  {
    for(int i=0; i<categories.size(); i++)
    {
      if(nomLong.compareTo(categories.get(i).getNomLong())==0)
      {
        categories.remove(categories.get(i));
        return;
      }
    }
  }
  
  public boolean existeCategorie(String nomLong, String nomCourt)
  {
    for(int i=0; i<categories.size(); i++)
    {
      if(nomLong.compareTo(categories.get(i).getNomLong())==0 && nomCourt.compareTo(categories.get(i).getNomCourt())==0)
      {
        return true;
      }
    }
    return false;
  }
  
  public boolean existeCategorie(String nomCourt)
  {
    for(int i=0; i<categories.size(); i++)
    {
      if(nomCourt.compareTo(categories.get(i).getNomCourt())==0)
      {
        return true;
      }
    }
    return false;
  }
}
