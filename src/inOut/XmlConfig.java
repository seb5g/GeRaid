/**
 * 
 */
package inOut;

import geRaid.GeRaid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import to.Categorie;

/**
 * <P>
 * Titre : XmlRaid.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class XmlConfig
{
  // Déclaration des variables Document et de la racine
  private static Document document;
  private static Element racine;
  //private static String GERAID = "geraid";
  private static String CONFIG = "config";
  private static String NOM = "nom";
  private static String CATEGORIES = "categories";
  private static String CATEGORIE = "categorie";
  private static String NOMLONG = "nomLong";
  private static String NOMCOURT = "nomCourt";
  private static String PORT = "port";
  private static String DOSSIER = "dossier";
  private static String DOSSIERS = "dossiers";
  private static String TICKET = "ticket";
  private static String IMPRESSION = "print";
  private static String POINTS = "points";
  private static String TEMPS = "temps";
  private static String POINTS_PM = "pointsPm";
  private static String TEMPS_PM = "tempsPm";
  private static String VITESSE = "vitesse";
  private static String DEFILEMENT = "defilement";


  /**
   * Méthode lisant le fichier XML passé en paramètre et construisant 
   * Le vecteur contenant tous les parcours
   * @param fichier
   */
  // Méthode de lecture du fichier XML
  public  static void lectureConfig(GeRaid geRaid, String fichier)
  {
    File fileConfig = new File(fichier);
    if(fileConfig.exists())
    {
      SAXBuilder sxb = new SAXBuilder();
      try
      {
        document = sxb.build(new File(fichier));
      }
      catch (JDOMException e)
      {
        JOptionPane.showMessageDialog(null,"Erreur de lecture du fichier de configuration : "+e.getClass().getName()+", "+e.getMessage());
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(null,"Erreur de lecture du fichier de configuration : "+e.getClass().getName()+", "+e.getMessage());
      }
      racine = document.getRootElement();
      Element eNom = racine.getChild(NOM);
      geRaid.setNomAssociation(eNom.getText().trim());
      Element ePort = racine.getChild(PORT);
      geRaid.setPort(ePort.getText().trim());
      Element eDossier = racine.getChild(DOSSIER);
      File file = new File(eDossier.getText().trim());
      if(file.exists())
      {
        geRaid.setDossierDefault(eDossier.getText().trim());
      }
      else
      {
        geRaid.setDossierDefault("");
      }
      Element eDossiers = racine.getChild(DOSSIERS);
      File files = new File(eDossiers.getText().trim());
      if(files.exists())
      {
        geRaid.setDossierSauvegarde(eDossiers.getText().trim());
      }
      else
      {
        geRaid.setDossierSauvegarde("");
      }
      if(racine.getChild(TICKET) != null)
      {
        int t = Integer.parseInt(racine.getChildText(TICKET));
        geRaid.setResultatReduit((t == 1)?true:false);
      }
      if(racine.getChild(IMPRESSION) != null)
      {
        int t = Integer.parseInt(racine.getChildText(IMPRESSION));
        geRaid.setImpressionReduite((t == 1)?true:false);
      }
      if(racine.getChild(DEFILEMENT) != null)
      {
        int t = Integer.parseInt(racine.getChildText(DEFILEMENT));
        geRaid.setDefilement((t == 1)?true:false);
      } 
      if(racine.getChild(VITESSE) != null)
      {
        geRaid.setVitesse(Integer.parseInt(racine.getChildText(VITESSE)));
      } 
      
      recupereAllCategorie(geRaid);
      
      if(racine.getChild(POINTS) != null)
      {
        geRaid.pointsDefaut = Integer.parseInt(racine.getChildText(POINTS));
        geRaid.tempsDefaut = Integer.parseInt(racine.getChildText(TEMPS));
        geRaid.pointsPmDefaut = Integer.parseInt(racine.getChildText(POINTS_PM));
        geRaid.tempsPmDefaut = Integer.parseInt(racine.getChildText(TEMPS_PM));
      }
    }
    else
    {
      geRaid.categoriesNouveauRaid.addCategorie(new Categorie("Masculin", "MAS"));
      geRaid.categoriesNouveauRaid.addCategorie(new Categorie("Fémimine", "FEM"));
      geRaid.categoriesNouveauRaid.addCategorie(new Categorie("Mixte", "MIX"));
      enregistre(geRaid, fichier);
    }
  }
  
  // Méthode récupérant toutes les categories
  public static void recupereAllCategorie(GeRaid geRaid)
  {
    Element eCategories = racine.getChild(CATEGORIES);
    List<?> categories = eCategories.getChildren(CATEGORIE);
    Iterator<?> i = categories.iterator();
    
    while (i.hasNext())
    {
      // Création et instanciation de la nouvelle catégorie

        Element eCat = (Element) i.next();
        
        Categorie cat = new Categorie(eCat.getChild(NOMLONG).getText().trim(),eCat.getChild(NOMCOURT).getText().trim());
      
      // Ajout du parcours dans le vecteur
        geRaid.categoriesNouveauRaid.addCategorie(cat);
    }
  }
  
  /**
   * Méthode créant le fichier XML passé en paramètre 
   * et enregistrant les verbes
   * @param fichier
   */
  //Méthode qui enregistre au format XML les verbes
  public static void enregistre(GeRaid geRaid, String fichier)
  {
    //Déclaration des variables Document et de la racine
    Element racine = new Element(CONFIG);
    Document document = new Document(racine);
    Element eNom = new Element(NOM);
    eNom.setText(geRaid.getNomAssociation());
    racine.addContent(eNom);
    Element eport = new Element(PORT);
    eport.setText(geRaid.getPort());
    racine.addContent(eport);
    Element eDossier = new Element(DOSSIER);
    eDossier.setText(geRaid.getDossierDefault());
    racine.addContent(eDossier);
    Element eDossiers = new Element(DOSSIERS);
    eDossiers.setText(geRaid.getDossierSauvegarde());
    racine.addContent(eDossiers);
    Element t = new Element(TICKET);
    t.setText(geRaid.getResultatReduit());
    racine.addContent(t);
    Element imp = new Element(IMPRESSION);
    imp.setText(geRaid.getImpressionReduite());
    racine.addContent(imp);
    Element def = new Element(DEFILEMENT);
    def.setText(geRaid.getDefilement());
    racine.addContent(def);
    Element vit = new Element(VITESSE);
    vit.setText(geRaid.getVitesse()+"");
    racine.addContent(vit);
    //Lecture du vecteur de verbes et construction de la structure XML
    Element eCats = new Element(CATEGORIES);
    for(int i =0;i<geRaid.categoriesNouveauRaid.getSize();i++)
    {
      Element eCat = new Element(CATEGORIE);
      
      Element eNomL = new Element(NOMLONG);
      eNomL.setText(geRaid.categoriesNouveauRaid.getCategories().get(i).getNomLong());
      eCat.addContent(eNomL);
      Element eNomC = new Element(NOMCOURT);
      eNomC.setText(geRaid.categoriesNouveauRaid.getCategories().get(i).getNomCourt());
      eCat.addContent(eNomC);

      eCats.addContent(eCat);
    }

    Element bpoints = new Element(POINTS);
    bpoints.setText(geRaid.pointsDefaut + "");
    racine.addContent(bpoints);
    Element bTemps = new Element(TEMPS);
    bTemps.setText(geRaid.tempsDefaut + "");
    racine.addContent(bTemps);
    
    Element bpointsPm = new Element(POINTS_PM);
    bpointsPm.setText(geRaid.pointsPmDefaut + "");
    racine.addContent(bpointsPm);
    Element bTempsPm = new Element(TEMPS_PM);
    bTempsPm.setText(geRaid.tempsPmDefaut + "");
    racine.addContent(bTempsPm);
    
    racine.addContent(eCats);
      
    // Ecriture du fichier XML
    XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
    FileOutputStream fo;
    try
     {
      fo = new FileOutputStream(fichier);
      sortie.output(document, fo);
      fo.close();
     }
    catch (FileNotFoundException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture: "+e.getClass().getName()+", "+e.getMessage());
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur d'écriture: "+e.getClass().getName()+", "+e.getMessage());
    }
  }
}
