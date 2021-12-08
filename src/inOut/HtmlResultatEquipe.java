/**
 * 
 */
package inOut;

import geRaid.GeRaid;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import to.ResultatEquipe;


/**
 * <P>
 * Titre : CsvEquipes.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class HtmlResultatEquipe
{
  public static void save(ResultatEquipe re, String fichier, GeRaid geraid, boolean resultatReduit)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      StringBuffer tampon = new StringBuffer("<html><body>");
      if(resultatReduit)
      {
        tampon.append("<font size=2>");
        tampon.append(geraid.entete + "<br>");
        tampon.append("<b>" + geraid.nomRaid + "</b></font><br><br>");
        tampon.append(re.toHtmlReduit());
        tampon.append("<br><font size=2>" + geraid.piedPage + "</font><br>");
        tampon.append("<b>Chronométré avec GeRaid</b>");
      }
      else
      {
        tampon.append("<font size=1>");
        tampon.append(geraid.entete + "<br>");
        tampon.append(re.toHtml());
        tampon.append(geraid.piedPage + "<br>");
        tampon.append("</font>");
      }
      tampon.append("</body></html>");
      monFichier . write ( tampon.toString() , 0 , tampon . length ());
      monFichier . newLine ( ) ;
      
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier en html : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
}
