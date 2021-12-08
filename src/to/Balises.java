/**
 * 
 */
package to;

import java.util.Vector;

/**
 * <P>
 * Titre : Balises.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Balises implements Cloneable
{
  private Vector<Balise> balises = new Vector<Balise>();
  
  public Balises()
  {
    
  }

  public Object clone() 
  {
    Balises p = null;
    try 
    {
        // On récupère l'instance à renvoyer par l'appel de la 
        // méthode super.clone()
        p = (Balises) super.clone();
    } 
    catch(CloneNotSupportedException cnse) 
    {
        // Ne devrait jamais arriver car nous implémentons 
        // l'interface Cloneable
        cnse.printStackTrace(System.err);
    }
    p.balises = new Vector<Balise>();

    for(int i=0; i<balises.size(); i++)
    {
      p.getBalises().add((Balise) balises.get(i).clone());
    }
    // on renvoie le clone
    return p;
  }

  /**
   * @return the balises
   */
  public Vector<Balise> getBalises()
  {
    return balises;
  }

  /**
   * @param balises the balises to set
   */
  public void setBalises(Vector<Balise> balises)
  {
    this.balises = balises;
  }
  
  public boolean addBalise(Balise e)
  {
    boolean retour = false;

      balises.add(e);
      retour = true;

    return retour;
  }
  
  public void removeBalise(Balise e)
  {
    balises.remove(e);
  }
  
  public int getSize()
  {
    return balises.size();
  }
  
  public void upBalise(Balise b)
  {
    int index = balises.indexOf(b);
    if(index>0)
    {
      balises.insertElementAt(b, index-1);
      balises.removeElementAt(index+1);
    }
  }
  
  public void downBalise(Balise b)
  {
    int index = balises.indexOf(b);
    if(index<balises.size()-1)
    {
      balises.insertElementAt(b, index+2);
      balises.removeElementAt(index);
    }
  }
  
  public String toStringResultatBalise(Balise b)
  {
    return b.toStringResultat();
  }
  
  public int getNumeroDerniereBalise()
  {
    if(getSize()>0)
    {
      return balises.get(getSize()-1).getNumero();
    }
    return 30;
  }
  
  public int getNumeroPremiereBalise()
  {
    if(getSize()>0)
    {
      return balises.get(0).getNumero();
    }
    return 30;
  }
  
  public int getDernierNumero()
  {
    int retour = 30;
    for(int i=0; i<balises.size(); i++)
    {
      if(balises.get(i).getNumero()>retour)
      {
        retour = balises.get(i).getNumero();
      }
    }
    return retour;
  }
  
  public String toStringBalises()
  {
    StringBuffer retour = new StringBuffer("<ul>");
    for(int i=0; i<balises.size(); i++)
    {
      retour.append(balises.get(i).toStringBalise());
    }
    retour.append("</ul>");
    return retour.toString();
  }
}
