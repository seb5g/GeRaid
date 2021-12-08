/**
 * 
 */
package inOut;

import geRaid.GeRaid;
import ihm.EpreuveTableModel;
import ihm.EtapeTableModel;
import ihm.ParcoursTableModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import outils.OutilsJavaScript;


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
public class HtmlResultat
{
  public static void exporterParcours(ParcoursTableModel etm, String fichier, String entete, JTable table)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      StringBuffer tampon = new StringBuffer();
      if(GeRaid.defilementJavaScript)
      {
        tampon.append("<html><br>" + OutilsJavaScript.getJavaScriptDefilementParPage() + "<body>");
      }
      else
      {
        tampon.append("<html><br><body>");
      }
      //tampon.append("<style type='text/css'>tr.entete{font-weight: bold;}tr.jaune{background-color: #ffff96;}tr.bleu{background-color: #affdff;}</style>");
      tampon.append(entete);
      tampon.append(etm.getEntetesHtml());
      tampon.append(etm.getDataHtml(table));
      if(GeRaid.defilementJavaScript)
      {
        tampon.append(OutilsJavaScript.getJavaScriptLancementDefilementParPage(GeRaid.getTempsPause()) + "<br><br><br></body></html>");
      }
      else
      {
        tampon.append("<br><br><br></body></html>");
      }
      
      monFichier . write ( tampon.toString() , 0 , tampon.toString() . length ());
      monFichier . newLine ( ) ;
      
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier d'export : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
  public static void exporterEtape(EtapeTableModel etm, String fichier, String entete, JTable table)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      StringBuffer tampon = new StringBuffer();
      if(GeRaid.defilementJavaScript)
      {
        tampon.append("<html><br>" + OutilsJavaScript.getJavaScriptDefilementParPage() + "<body>");
      }
      else
      {
        tampon.append("<html><br><body>");
      }
      //tampon.append("<style type='text/css'>tr.entete{font-weight: bold;}tr.jaune{background-color: #ffff96;}tr.bleu{background-color: #affdff;}</style>");
      tampon.append(entete);
      tampon.append(etm.getEntetesHtml());
      tampon.append(etm.getDataHtml(table));
      if(GeRaid.defilementJavaScript)
      {
        tampon.append(OutilsJavaScript.getJavaScriptLancementDefilementParPage(GeRaid.getTempsPause()) + "<br><br><br></body></html>");
      }
      else
      {
        tampon.append("<br><br><br></body></html>");
      }
      
      monFichier . write ( tampon.toString() , 0 , tampon.toString() . length ());
      monFichier . newLine ( ) ;
      
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier d'export : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
  public static void exporterEpreuve(EpreuveTableModel etm, String fichier, String entete, JTable table)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      StringBuffer tampon = new StringBuffer();
      if(GeRaid.defilementJavaScript)
      {
        tampon.append("<html><br>" + OutilsJavaScript.getJavaScriptDefilementParPage() + "<body>");
      }
      else
      {
        tampon.append("<html><br><body>");
      }
      //tampon.append("<style type='text/css'>tr.entete{font-weight: bold;}tr.jaune{background-color: #ffff96;}tr.bleu{background-color: #affdff;}</style>");
      tampon.append(entete);
      tampon.append(etm.getEntetesHtml());
      tampon.append(etm.getDataHtml(table));
      if(GeRaid.defilementJavaScript)
      {
        tampon.append(OutilsJavaScript.getJavaScriptLancementDefilementParPage(GeRaid.getTempsPause()) + "<br><br><br></body></html>");
      }
      else
      {
        tampon.append("<br><br><br></body></html>");
      }
      
      monFichier . write ( tampon.toString() , 0 , tampon.toString() . length ());
      monFichier . newLine ( ) ;
      
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier d'export : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
  public static void exporterParcoursPodium(ParcoursTableModel etm, String fichier, String entete, JTable table, int index)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      StringBuffer tampon = new StringBuffer();
      if(GeRaid.defilementJavaScript)
      {
        tampon.append("<html><br>" + OutilsJavaScript.getJavaScriptDefilementParPage() + "<body>");
      }
      else
      {
        tampon.append("<html><br><body>");
      }
      //tampon.append("<style type='text/css'>tr.entete{font-weight: bold;}tr.jaune{background-color: #ffff96;}tr.bleu{background-color: #affdff;}</style>");
      tampon.append(entete);
      tampon.append(etm.getEntetesHtml());
      tampon.append(etm.getDataHtml(table, index));
      if(GeRaid.defilementJavaScript)
      {
        tampon.append(OutilsJavaScript.getJavaScriptLancementDefilementParPage(GeRaid.getTempsPause()) + "<br><br><br></body></html>");
      }
      else
      {
        tampon.append("<br><br><br></body></html>");
      }
      
      monFichier . write ( tampon.toString() , 0 , tampon.toString() . length ());
      monFichier . newLine ( ) ;
      
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier d'export : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
  public static void exporterEtapePodium(EtapeTableModel etm, String fichier, String entete, JTable table, int index)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      StringBuffer tampon = new StringBuffer();
      if(GeRaid.defilementJavaScript)
      {
        tampon.append("<html><br>" + OutilsJavaScript.getJavaScriptDefilementParPage() + "<body>");
      }
      else
      {
        tampon.append("<html><br><body>");
      }
      //tampon.append("<style type='text/css'>tr.entete{font-weight: bold;}tr.jaune{background-color: #ffff96;}tr.bleu{background-color: #affdff;}</style>");
      tampon.append(entete);
      tampon.append(etm.getEntetesHtml());
      tampon.append(etm.getDataHtml(table, index));
      if(GeRaid.defilementJavaScript)
      {
        tampon.append(OutilsJavaScript.getJavaScriptLancementDefilementParPage(GeRaid.getTempsPause()) + "<br><br><br></body></html>");
      }
      else
      {
        tampon.append("<br><br><br></body></html>");
      }
      
      monFichier . write ( tampon.toString() , 0 , tampon.toString() . length ());
      monFichier . newLine ( ) ;
      
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier d'export : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
  public static void exporterEpreuvePodium(EpreuveTableModel etm, String fichier, String entete, JTable table, int index)
  {
    File chemin = new File ( fichier ) ;
    BufferedWriter monFichier;
    try
    {
      monFichier = new BufferedWriter ( new FileWriter ( chemin )) ;
      
      StringBuffer tampon = new StringBuffer();
      if(GeRaid.defilementJavaScript)
      {
        tampon.append("<html><br>" + OutilsJavaScript.getJavaScriptDefilementParPage() + "<body>");
      }
      else
      {
        tampon.append("<html><br><body>");
      }
      //tampon.append("<style type='text/css'>tr.entete{font-weight: bold;}tr.jaune{background-color: #ffff96;}tr.bleu{background-color: #affdff;}</style>");
      tampon.append(entete);
      tampon.append(etm.getEntetesHtml());
      tampon.append(etm.getDataHtml(table, index));
      if(GeRaid.defilementJavaScript)
      {
        tampon.append(OutilsJavaScript.getJavaScriptLancementDefilementParPage(GeRaid.getTempsPause()) + "<br><br><br></body></html>");
      }
      else
      {
        tampon.append("<br><br><br></body></html>");
      }
      
      monFichier . write ( tampon.toString() , 0 , tampon.toString() . length ());
      monFichier . newLine ( ) ;
      
      monFichier . close ( ) ;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture du fichier d'export : "+e.getClass().getName()+", "+e.getMessage());
      return;
    }
  }
}
