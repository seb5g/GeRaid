/**
 * 
 */
package inOut;

import ihm.ParcoursTableModel;

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
public class CsvResultatParcours
{
  public static void exporter(ParcoursTableModel etm, String fichier)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      String tampon = etm.getEntetesCSV();
      monFichier . write ( tampon , 0 , tampon . length ());
      monFichier . newLine ( ) ;
      
      String[] lignes = etm.getDataCSV();
      for ( int i = 0 ; i < lignes.length ; i++ )
      {
        tampon = lignes[i] ;
          monFichier . write ( tampon , 0 , tampon . length ());
          monFichier . newLine ( ) ;
      }
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier d'export : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
}
