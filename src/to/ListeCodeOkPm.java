/**
 * 
 */
package to;

import java.util.Vector;

/**
 * <P>
 * Titre : ListeCodeOkPm.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author Thierry
 *
 */
public class ListeCodeOkPm
{
  private Vector<CodeOkPm> liste = new Vector<CodeOkPm>();
  private Vector<Integer> codesEnPlus = new Vector<Integer>();

  /**
   * @return the liste
   */
  public Vector<CodeOkPm> getListe()
  {
    return liste;
  }

  /**
   * @param liste the liste to set
   */
  public void setListe(Vector<CodeOkPm> liste)
  {
    this.liste = liste;
  }
  
  public void add(CodeOkPm c)
  {
    liste.add(c);
  }
  
  public void add(int[] codes, boolean[] ok, String[] temps)
  {
    for(int i=0; i<codes.length; i++)
    {
      liste.add(new CodeOkPm(codes[i], ok[i], temps[i]));
    }
  }
  
  public String toHtml()
  {
    StringBuffer retour = new StringBuffer("<html><body>");
    int nbPm = getNbPm();
    if(nbPm>0)
    {
      retour.append("<font color=red>  " + nbPm + " PM : </font>");
    }
    if(!liste.get(liste.size()-1).isoK())
    {
      retour.append("<font color=red> " + " AAA" + " </font>");
    }
    String code = "";
    for(int i=0; i<liste.size(); i++)
    {
      if(liste.get(i).getCode()==16)
      {
        code = "D";
      }
      else if(liste.get(i).getCode()==15)
      {
        code = "A";
      }
      else
      {
        code = liste.get(i).getCode() + "";
      }
      if(liste.get(i).isoK())
      {
        retour.append("<font color=green> " + code + " </font>");
      }
      else
      {
        retour.append("<font color=red> " + code + " </font>");
      }
    }
    
    retour.append("<br>");
    if(codesEnPlus.size()>0)
    {
      retour.append("<font color=blue> Codes en plus : ");
      for(int i =0; i<codesEnPlus.size(); i++)
      {
        retour.append(codesEnPlus.get(i)+ " ");
      }
      retour.append("</font>");
    }

    retour.append("</body></html>");
    return retour.toString();
  }
  
  private int getNbPm()
  {
    int retour = 0;
    for(int i=0; i<liste.size(); i++)
    {
      if(!liste.get(i).isoK())
      {
        retour++;
      }
    }
    return retour;
  }

  public Vector<Integer> getCodesEnPlus()
  {
    return codesEnPlus;
  }

  public void setCodesEnPlus(Vector<Integer> codesEnPlus)
  {
    this.codesEnPlus = codesEnPlus;
  }
  
}
