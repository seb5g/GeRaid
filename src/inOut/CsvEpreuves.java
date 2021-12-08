/**
 * 
 */
package inOut;

import geRaid.GeRaid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;


import to.Balise;
import to.Epreuve;
import to.Epreuves;
import to.Etape;


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
public class CsvEpreuves
{
  public static void exporter(Epreuves es, String fichier)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      String tampon = "";
      monFichier . write ( tampon , 0 , tampon . length ());
      //monFichier . newLine ( ) ;
      
      for ( int i = 0 ; i < es.getSize() ; i++ )
      {
        tampon = es.getEpreuves().get(i).toCSV() ;
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
  
  public static void importer(GeRaid geraid, Etape p, String fichier)
  {
    
    File chemin = new File ( fichier ) ;
    String chaine ;
    String [ ] tampon ;
    Vector<Integer> lignes = new Vector<Integer>();
    int ligne = 1;
    Vector<String> codes = new Vector<String>();

    try
    {
    if (!chemin.exists())
    {
      chemin.createNewFile();
    }
    BufferedReader monFichier = new BufferedReader ( new FileReader ( chemin )) ;
    //monFichier . readLine ( );
    while (( chaine = monFichier . readLine ( )) != null )
    {
        ligne++;
        tampon = chaine . trim ( ) . split ( ";" ) ;
        if(tampon.length > 0 && tampon[0].length() > 0 && !p.existeNomEpreuve(tampon[0]))
        {
          Epreuve eq = new Epreuve();
          eq.setNom(tampon [ 0 ]);
          for(int i=1; i<tampon.length; i++)
          {
            try
            {
              Balise balise = new Balise(Integer.parseInt(tampon [i]));
              balise.setPoints(geraid.pointsDefaut);
              balise.setTemps(geraid.tempsDefaut);
              balise.setPointsPm(geraid.pointsPmDefaut);
              balise.setTempsPm(geraid.tempsPmDefaut);
              eq.getBalises().addBalise(balise);
            }
            catch (NumberFormatException e)
            {
              codes.add(tampon[i]);
            }
          }
          p.getEpreuves().addEpreuve(eq);
        }
        else
        {
          lignes.add(ligne);
        }
    }
      monFichier . close ( ) ;
      if(lignes.size()>0)
      {
        StringBuffer message = new StringBuffer("Certaines épreuves n'ont pu être importées :\nLignes ");
        for(int i=0; i<lignes.size(); i++)
        {
          message.append(lignes.get(i)+",");
        }
        message.append("\nVérifiez que ces épreuves ont un nom ou qu'elles n'existent pas déjà.\n");
        if(codes.size()>0)
        {
          message.append("\nLes codes suivants ne sont pas conformes (31 à 255) :\n");
        }
        for(int i=0; i<codes.size(); i++)
        {
          message.append(codes.get(i)+",");
        }
        JOptionPane.showMessageDialog(geraid.getIhm(), message.toString(), "Import des épreuves", JOptionPane.OK_OPTION);
      }
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur lors de l'import : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
}
