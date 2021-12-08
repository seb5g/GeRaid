/**
 * 
 */
package to;

import java.util.Vector;

import outils.TimeManager;


/**
 * <P>
 * Titre : ResultatPuce.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class ResultatPuce implements Cloneable
{
  private Parcours parcours = null;
  private Etape etape = null;
  private Equipe equipe = null;
  
  private Puce puce = new Puce();
  
  public ResultatPuce()
  {
    
  }
  
  public int getPositionPoste(int code)
  {
    return puce.getPositionPoste(code);
  }
  
  public Vector<String> getTemps()
  {
    return puce.getTemps();
  }
  
  public Vector<Integer> getCodes()
  {
    return puce.getCodes();
  }
  

  public boolean existeCode(int code)
  {
    for(int i=0; i<getCodes().size(); i++)
    {
      if(getCodes().get(i)==code)
      {
        return true;
      }
    }
    return false;
  }
  /**
   * @return the parcours
   */
  public Parcours getParcours()
  {
    return parcours;
  }

  /**
   * @param parcours the parcours to set
   */
  public void setParcours(Parcours parcours)
  {
    this.parcours = parcours;
  }

  /**
   * @return the etape
   */
  public Etape getEtape()
  {
    return etape;
  }

  /**
   * @param etape the etape to set
   */
  public void setEtape(Etape etape)
  {
    this.etape = etape;
  }

  /**
   * @return the equipe
   */
  public Equipe getEquipe()
  {
    return equipe;
  }

  /**
   * @param equipe the equipe to set
   */
  public void setEquipe(Equipe equipe)
  {
    this.equipe = equipe;
  }

  /**
   * @return the puce
   */
  public Puce getPuce()
  {
    return puce;
  }

  /**
   * @param puce the puce to set
   */
  public void setPuce(Puce puce)
  {
    this.puce = puce;
  }
  
  public String toStringResultatBalise(Balise b)
  {
    StringBuffer retour = new StringBuffer();

        if(b.getPoints() != 0)
        {
          retour.append(b.getPoints() + " pts");
          if(b.getTemps() != 0)
          {
            if(b.getTemps() < 0)
            {
              retour.append(" / -" +TimeManager.fullTime(b.getTemps()*1000));
            }
            else
            {
              retour.append(" / " +TimeManager.fullTime(b.getTemps()*1000));
            }
          }
        }
        else if(b.getTemps() != 0)
        {
          if(b.getTemps() < 0)
          {
            retour.append("-" +TimeManager.fullTime(b.getTemps()*1000));
          }
          else
          {
            retour.append(TimeManager.fullTime(b.getTemps()*1000));
          }
        }

    return retour.toString();
  }
  
  public String toCSV()
  {
    StringBuffer tampon = new StringBuffer ( parcours.getNom()) ;
    tampon . append( ";" ) ;
    tampon . append( etape.getNom()) ;
    tampon . append( ";" ) ;
    tampon . append( equipe.getDossard()) ;
    tampon . append( ";" ) ;
    tampon . append( equipe.getNom()) ;
    tampon . append( ";" ) ;
    tampon . append( equipe.getCategorie().getNomCourt()) ;
    tampon . append( ";" ) ;
    tampon . append( equipe.getIdPuce()) ;
    tampon . append( ";" ) ;
    tampon . append( TimeManager.fullTime(puce.getStarttime().getTime())) ;
    tampon . append( ";" ) ;
    tampon . append( TimeManager.fullTime(puce.getFinishtime().getTime())) ;
    
    for(int i=0; i<puce.getPartiels().length; i++)
    {
      tampon . append( ";" ) ;
      tampon . append( puce.getPartiels()[i].getCode()) ;
      tampon . append( ";" ) ;
      tampon . append( TimeManager.fullTime(puce.getPartiels()[i].getTime())) ;
    }
    
    return tampon . toString ( ) ;
  }
}
