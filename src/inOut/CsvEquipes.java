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


import to.Equipe;
import to.Equipes;
import to.Parcours;
import to.Raider;


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
public class CsvEquipes
{
  public static void exporter(GeRaid grd, String fichier)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      String tampon = "Dossard;Nom;Puce;Categorie";
      monFichier . write ( tampon , 0 , tampon . length ());
      monFichier . newLine ( ) ;
      for(int j=0; j<grd.getParcourss().getSize(); j++)
      {
        Parcours p = grd.getParcourss().getParcourss().get(j);
        Equipes es = p.getEquipes();
        for ( int i = 0 ; i < es.getSize() ; i++ )
        {
          tampon = es.getEquipes().get(i).toCSV() ;
            monFichier . write ( tampon , 0 , tampon . length ());
            monFichier . newLine ( ) ;
        }
      }
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier d'export : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
  
  public static void exporter(Equipes es, String fichier)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      String tampon = "Dossard;Nom;Puce;Categorie";
      monFichier . write ( tampon , 0 , tampon . length ());
      monFichier . newLine ( ) ;
      
      for ( int i = 0 ; i < es.getSize() ; i++ )
      {
        tampon = es.getEquipes().get(i).toCSV() ;
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
  
  public static void importer(GeRaid g, Parcours p, String fichier)
  {
    
    File chemin = new File ( fichier ) ;
    String chaine ;
    String [ ] tampon ;
    Vector<Integer> lignes = new Vector<Integer>();
    int ligne = 1;

    try
    {
    if (!chemin.exists())
    {
      chemin.createNewFile();
    }
    BufferedReader monFichier = new BufferedReader ( new FileReader ( chemin )) ;
    p.getEquipes().getEquipes().clear();
    monFichier . readLine ( );
    while (( chaine = monFichier . readLine ( )) != null )
    {
        ligne++;
        tampon = chaine . trim ( ) . split ( ";" ) ;
        if(tampon.length > 1 && tampon[1].length() > 0 && g.existeCategorie(tampon[3]))
        {
          Equipe eq = new Equipe(g);
          eq.setDossard(tampon [ 0 ]);
          eq.setNom(tampon [ 1 ]);
          eq.setIdPuce(tampon [ 2 ]);
          eq.setCategorie(g.getCategorie().getCategorie(tampon [ 3 ]));
          for(int i=0; i<(tampon.length - 4)/2; i++)
          {
            eq.getRaiders().addRaider(new Raider(tampon [ 4+i*2 ], tampon [ 5+i*2 ]));
          }
          p.getEquipes().addEquipe(eq);
        }
        else
        {
          lignes.add(ligne);
        }
    }
      monFichier . close ( ) ;
      if(lignes.size()>0)
      {
        StringBuffer message = new StringBuffer("Certaines équipes n'ont pu être importées :\nLignes ");
        for(int i=0; i<lignes.size(); i++)
        {
          message.append(lignes.get(i)+",");
        }
        message.append("\nVérifiez que ces équipes ont un nom et une catégorie conforme (nom court).");
        JOptionPane.showMessageDialog(g.getIhm(), message.toString(), "Import des équipes", JOptionPane.OK_OPTION);
      }
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'import : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
}
