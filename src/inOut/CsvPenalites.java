/**
 * 
 */
package inOut;

import geRaid.GeRaid;
import ihm.PenaliteTableModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import outils.Outils;

import to.Etape;
import to.Parcours;
import to.Penalite;
import to.PenaliteIndividuelle;


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
public class CsvPenalites
{
  public static void exporterListeEquipes(PenaliteTableModel etm, String fichier)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      String tampon = etm.getEntetesEquipesCSV();
      monFichier . write ( tampon , 0 , tampon . length ());
      monFichier . newLine ( ) ;
      
      String[] lignes = etm.getDataEquipesCSV();
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
  
  public static void exporterPenalites(PenaliteTableModel etm, String fichier)
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
  
  public static void importer(GeRaid g, Parcours p, Etape e, String fichier)
  {
    
    File chemin = new File ( fichier ) ;
    String chaine ;
    String [ ] tampon ;

    try
    {
      if (!chemin.exists())
      {
        chemin.createNewFile();
      }
      BufferedReader monFichier = new BufferedReader ( new FileReader ( chemin )) ;
      monFichier . readLine ( );
      Penalite pen = new Penalite();
      pen.setNom(Outils.getNom(fichier));
      pen.setParcours(p);
      pen.setEtape(e);
      while (( chaine = monFichier . readLine ( )) != null )
      {
          tampon = chaine . trim ( ) . split ( ";" ) ;
          if(tampon.length>3)
          {
            PenaliteIndividuelle pi = new PenaliteIndividuelle();
            pi.setPuce(tampon[2]);
            if(Outils.estUnEntier(tampon[3]))
            {
              pi.setPoint(Integer.parseInt(tampon[3]));
            }
            if(tampon.length>4)
            {
              pi.setTemps(Outils.parseInt(tampon[4]));
            }
            pen.getPenalites().add(pi);
          }
          /*
          eq.setDossard(tampon [ 0 ]);
          eq.setNom(tampon [ 1 ]);
          eq.setIdPuce(tampon [ 2 ]);
          eq.setCategorie(g.getCategorie().getCategorie(tampon [ 3 ]));
          for(int i=0; i<(tampon.length - 4)/2; i++)
          {
            eq.getRaiders().addRaider(new Raider(tampon [ 4+i*2 ], tampon [ 5+i*2 ]));
          }
          */
      }
        g.penalites.getPenalites().add(pen);
        monFichier . close ( ) ;
    }
    catch (IOException io)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier d'import : "+io.getClass().getName()+", "+io.getMessage());
      return;
    }
  }
}
