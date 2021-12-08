/**
 * 
 */
package to;

import java.util.Vector;


/**
 * <P>
 * Titre : Etapes.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Etapes implements Cloneable
{
  private Vector<Etape> etapes = new Vector<Etape>();
  
  public Etapes()
  {
    
  }

  public Object clone() 
  {
    Etapes p = null;
    try 
    {
        // On récupère l'instance à renvoyer par l'appel de la 
        // méthode super.clone()
        p = (Etapes) super.clone();
    } 
    catch(CloneNotSupportedException cnse) 
    {
        // Ne devrait jamais arriver car nous implémentons 
        // l'interface Cloneable
        cnse.printStackTrace(System.err);
    }
    p.etapes = new Vector<Etape>();
    for(int i=0; i<etapes.size(); i++)
    {
      p.getEtapes().add((Etape) etapes.get(i).clone());
    }
    // on renvoie le clone
    return p;
  }

  /**
   * @return the etapes
   */
  public Vector<Etape> getEtapes()
  {
    return etapes;
  }

  /**
   * @param etapes the etapes to set
   */
  public void setEtapes(Vector<Etape> etapes)
  {
    this.etapes = etapes;
  }
  
  public boolean addEtape(Etape e)
  {
    boolean retour = false;
    if(existeNom(e) == null)
    {
      etapes.add(e);
      retour = true;
    }
    return retour;
  }
  
  private Etape existeNom(Etape e)
  {
    Etape retour = null;
    for(int i=0; i<etapes.size(); i++)
    {
      if(etapes.get(i)!= e && etapes.get(i).getNom().compareTo(e.getNom().trim())==0)
      {
        return etapes.get(i);
      }
    }
    return retour;
  }
  
  public boolean existeEtape(String nom, Etape e)
  {
    boolean retour = false;
    for(int i=0; i<etapes.size(); i++)
    {
      if(etapes.get(i).getNom().toUpperCase().compareTo(nom.trim().toUpperCase())==0)
      {
        if(!etapes.get(i).equals(e))
        {
          return true;
        }
      }
    }
    
    return retour;
  }
  
  public Etape editEtape(Etape e)
  {
    Etape retour = null;
    if(existeNom(e)==null)
    {
      retour = e;
    }
    return retour;
  }
  
  public void removeEtape(Etape e)
  {
    etapes.remove(e);
  }
  
  public int getSize()
  {
    return etapes.size();
  }
  
  public String toStringEtapes()
  {
    StringBuffer retour = new StringBuffer("<ul>");
    for(int i=0; i<etapes.size(); i++)
    {
      retour.append(etapes.get(i).toStringEtape());
    }
    retour.append("</ul>");
    return retour.toString();
  }
}
