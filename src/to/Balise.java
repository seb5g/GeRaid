/**
 * 
 */
package to;


import outils.TimeManager;

/**
 * <P>
 * Titre : Balise.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class Balise implements Cloneable
{
  private int numero = 31;
  private boolean demarerChrono = false;
  private boolean arreterChrono = false;
  private int points = 0;
  private int temps = 0;
  private int pointsPm = 0;
  private int tempsPm = 0;
  
  public Balise(int numero)
  {
    this.numero = numero;
  }

  public Balise()
  {
    
  }

  public Object clone() 
  {
    Balise p = null;
    try 
    {
        // On récupère l'instance à renvoyer par l'appel de la 
        // méthode super.clone()
        p = (Balise) super.clone();
    } 
    catch(CloneNotSupportedException cnse) 
    {
        // Ne devrait jamais arriver car nous implémentons 
        // l'interface Cloneable
        cnse.printStackTrace(System.err);
    }
    // on renvoie le clone
    return p;
  }

  /**
   * @return the numero
   */
  public int getNumero()
  {
    return numero;
  }

  /**
   * @param numero the numero to set
   */
  public void setNumero(int numero)
  {
    this.numero = numero;
  }
  
  public String toString()
  {
    StringBuffer retour = new StringBuffer("Balise " + numero + " : ");

        if(points != 0)
        {
          retour.append(points + " pts");
          if(temps > 0)
          {
            retour.append(" / " + TimeManager.fullTime(temps*1000));
          }
          if(temps < 0)
          {
            retour.append(" / " + TimeManager.fullTime(temps*1000));
          }
        }
        else if(temps != 0)
        {
          if(temps > 0)
          {
            retour.append("  " + TimeManager.fullTime(temps*1000));
          }
          if(temps < 0)
          {
            retour.append("  " + TimeManager.fullTime(temps*1000));
          }
        }
        if(pointsPm != 0)
        {
          retour.append(" (Si PM : " + pointsPm + " pts");
          if(tempsPm > 0)
          {
            retour.append(" / " + TimeManager.fullTime(tempsPm*1000));
          }
          if(tempsPm < 0)
          {
            retour.append(" / " + TimeManager.fullTime(tempsPm*1000));
          }
          retour.append(")");
        }
        else if(tempsPm != 0)
        {
          retour.append(" (Si PM : ");
          if(tempsPm > 0)
          {
            retour.append("" + TimeManager.fullTime(tempsPm*1000));
          }
          if(tempsPm < 0)
          {
            retour.append(TimeManager.fullTime(tempsPm*1000));
          }
          retour.append(")");
        }
        
        if(demarerChrono)
        {
          retour.append(" avec arrêt du gel du chrono");
        }
        if(arreterChrono)
        {
          retour.append(" avec démarrage du gel du chrono");
        }
        
    
    return retour.toString();
  }
  
  public String toStringNom()
  {
    StringBuffer retour = new StringBuffer();

    retour.append("Balise " + numero);
      
    return retour.toString();
  }
  
  public String toStringResultat()
  {
    StringBuffer retour = new StringBuffer();
    if(points != 0)
    {
      retour.append(points + " pts");
      if(temps != 0)
      {
        retour.append(" / " + temps + " mn");
      }
    }
    else if(temps != 0)
    {
      retour.append(temps + " mn");
    }
    return retour.toString();
  }

  /**
   * @return the demarerChrono
   */
  public boolean isDemarerChrono()
  {
    return demarerChrono;
  }

  /**
   * @param demarerChrono the demarerChrono to set
   */
  public void setDemarerChrono(boolean demarerChrono)
  {
    this.demarerChrono = demarerChrono;
  }

  /**
   * @return the arreterChrono
   */
  public boolean isArreterChrono()
  {
    return arreterChrono;
  }

  /**
   * @param arreterChrono the arreterChrono to set
   */
  public void setArreterChrono(boolean arreterChrono)
  {
    this.arreterChrono = arreterChrono;
  }

  /**
   * @return the points
   */
  public int getPoints()
  {
    return points;
  }

  /**
   * @param points the points to set
   */
  public void setPoints(int points)
  {
    this.points = points;
  }

  /**
   * @return the temps
   */
  public int getTemps()
  {
    return temps;
  }

  /**
   * @param temps the temps to set
   */
  public void setTemps(int temps)
  {
    this.temps = temps;
  }

  public void setPointsPm(int pointsPm)
  {
    this.pointsPm = pointsPm;
  }

  public int getPointsPm()
  {
    return pointsPm;
  }

  public void setTempsPm(int tempsPm)
  {
    this.tempsPm = tempsPm;
  }

  public int getTempsPm()
  {
    return tempsPm;
  }

  public String toStringBalise()
  {
    StringBuffer retour = new StringBuffer();
    retour.append("<li>"+ toString() + "</li>");
    return retour.toString();
  }

}
