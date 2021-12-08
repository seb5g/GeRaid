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
import java.text.ParseException;

import javax.swing.JOptionPane;

import outils.TimeManager;

import to.Partiel;
import to.ResultatPuce;
import to.ResultatsPuce;


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
public class CsvResultatPuce
{
  public static void exporter(ResultatsPuce rps, String fichier)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      StringBuffer buf = new StringBuffer("Parcours;Etape;Dossard;Equipe;Categorie;Puce;Départ;Arrivée");
      String tampon = buf.toString();
      monFichier . write ( tampon , 0 , tampon . length ());
      monFichier . newLine ( ) ;
      
      for ( int i = 0 ; i < rps.getSize() ; i++ )
      {
        tampon = rps.getResultatsPuce().get(i).toCSV() ;
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
  
  public static void importer(GeRaid g, String fichier)
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
    g.getResultatsPuce().clearResultats();
    monFichier . readLine ( );
    while (( chaine = monFichier . readLine ( )) != null )
    {
        tampon = chaine . trim ( ) . split ( ";" ) ;
        ResultatPuce rp = new ResultatPuce();
        rp.setParcours(g.getParcourss().getParcours(tampon [ 0 ]));
        rp.setEtape(g.getParcourss().getParcours(tampon [ 0 ]).getEtape(tampon [ 1 ]));
        rp.setEquipe(g.getParcourss().getParcours(tampon [ 0 ]).getEquipeIdPuce(tampon [ 5 ]));
        rp.getPuce().setIdPuce(tampon [ 5 ]);
        try
        {
          rp.getPuce().setStarttime(TimeManager.parse(tampon [ 6 ], TimeManager.FORMATTER));
        }
        catch (ParseException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        try
        {
          rp.getPuce().setFinishtime(TimeManager.parse(tampon [ 7 ], TimeManager.FORMATTER));
        }
        catch (ParseException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        Partiel[] partiels = new Partiel[(tampon.length - 8)/2];
        for(int i=0; i<(tampon.length - 8)/2; i++)
        {
          Partiel partiel = new Partiel();
          partiel.setCode(Integer.parseInt(tampon [ 8+i*2 ]));
          try
          {
            partiel.setTime(TimeManager.parse(tampon [ 9+i*2 ], TimeManager.FORMATTER));
          }
          catch (ParseException e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          partiels[i] = partiel;
        }
        rp.getPuce().setPartiels(partiels);
        g.addResultatPuce(rp);
    }
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier d'import : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
}
