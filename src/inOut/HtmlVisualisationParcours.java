/**
 * 
 */
package inOut;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;


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
public class HtmlVisualisationParcours
{
  public static void save(String codeHtml, String fichier)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      monFichier . write ( codeHtml , 0 , codeHtml . length ());
      monFichier . newLine ( ) ;
      
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier en html : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
    // Lancement du fichier
    Runtime r = Runtime.getRuntime();
    
    try
    {
      // Lancement du fichier d'aide de l'application
      String adresse = new File(".").getCanonicalPath().toString();
      adresse = "cmd /c start \"\" \"" + adresse + "/" + fichier +"\"";
      r.exec(adresse);
    }
    catch (IOException e1)
    {
      JOptionPane.showMessageDialog(null,"Erreur de lancement du fichier : "+e1.getClass().getName());
    }
  }
}
