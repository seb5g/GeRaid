/**
 * 
 */
package inOut;
import geRaid.GeRaid;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import to.Balise;
import to.Epreuve;

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
public class XmlOcadCircuit
{
  // Déclaration des variables Document et de la racine
  private static Document document;
  private static Element racine;
  private static String IOFVERSIONV2 = "IOFVersion";
  private static String IOFVERSIONV3 = "iofVersion";
  private static String VERSION = "version";
  private static String COURSE = "Course";
  private static String RACECOURSEDATA = "RaceCourseData";
  private static String COURSENAME = "CourseName";
  private static String NAME = "Name";
  private static String COURSEVARIATION = "CourseVariation";
  private static String COURSECONTROL = "CourseControl";
  private static String CONTROLCODE = "ControlCode";
  private static String CONTROL = "Control";
  private static String ID = "Id";
  private static String COURSEVARIATIONID = "CourseVariationId";


  /**
   * Méthode lisant le fichier XML passé en paramètre et construisant 
   * Le vecteur contenant tous les parcours
   * @param fichier
   */
  // Méthode de lecture du fichier XML
  public  static void importer(GeRaid geraid, Epreuve epreuve, String fichier)
  {
    SAXBuilder sxb = new SAXBuilder(false);
    sxb.setValidation(false);
    sxb.setFeature("http://xml.org/sax/features/validation", false);
    sxb.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
    sxb.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    try
    {
      document = sxb.build(new File(fichier));
    }
    catch (JDOMException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur de lecture du fichier : "+e.getClass().getName()+", "+e.getMessage());
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur de lecture du fichier : "+e.getClass().getName()+", "+e.getMessage());
    }
    racine = document.getRootElement();
    
    if(racine.getAttribute(IOFVERSIONV3)!= null)
    {
      if(racine.getAttributeValue(IOFVERSIONV3).substring(0, 1).compareTo("3")==0)
      {
        recupereCourseV3(geraid, epreuve);
      }
    }
    else if(racine.getChild(IOFVERSIONV2)!=null)
    {
      if(racine.getChild(IOFVERSIONV2).getAttributeValue(VERSION).substring(0, 1).compareTo("2")==0)
      {
        recupereCourseV2(geraid, epreuve);
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Cette version d'export XML d'OCAD n'est pas valide");
    }
  }
  
  // Méthode récupérant toutes les categories
  public static void recupereCourseV2(GeRaid geraid, Epreuve epreuve)
  {
    List<?> courses = racine.getChildren(COURSE);
    ArrayList<String> liste = new ArrayList<String>();
    liste.add("Tous postes");
    if(courses.size()>0)
    {
      for (int i=0; i<courses.size(); i++)
      {
          Element course = (Element) courses.get(i);
          List<?> variations = course.getChildren(COURSEVARIATION);
          for(int j=0; j<variations.size(); j++)
          {
            Element variation = (Element) variations.get(j);
            if(variation.getChildText(NAME) != null)
            {
              liste.add(course.getChildText(COURSENAME) + " - " + variation.getChildText(NAME));
            }
            else
            {
              liste.add(course.getChildText(COURSENAME) + " - " + variation.getChildText(COURSEVARIATIONID));
            }
          }
          
      }
      String courseChoisie = (String) JOptionPane.showInputDialog(null, "Choisissez un circuit : ", "Choix d'un circuit", JOptionPane.INFORMATION_MESSAGE, 
          null, liste.toArray(), liste.get(0));
      if(courseChoisie != null)  
      {
        if(courseChoisie.compareTo(liste.get(0))==0)
        {
          recupereTousPostesV2(geraid, epreuve);
          return;
        }
        Iterator<?> i = courses.iterator();
        while (i.hasNext())
        {
          Element course = (Element) i.next();
          List<?> variations = course.getChildren(COURSEVARIATION);
          Iterator<?> j = variations.iterator();
          while (j.hasNext())
          {
            Element variation = (Element) j.next();
            if((course.getChildText(COURSENAME) + " - " + variation.getChildText(NAME)).compareTo(courseChoisie)==0  ||
                (course.getChildText(COURSENAME) + " - " + variation.getChildText(COURSEVARIATIONID)).compareTo(courseChoisie)==0)
            {
              List<?> controls = variation.getChildren(COURSECONTROL);
              Iterator<?> k = controls.iterator();
              while (k.hasNext())
              {
                Element code = (Element) k.next();
                Balise balise = new Balise(Integer.parseInt(code.getChildTextTrim(CONTROLCODE)));
                balise.setPoints(geraid.pointsDefaut);
                balise.setTemps(geraid.tempsDefaut);
                balise.setPointsPm(geraid.pointsPmDefaut);
                balise.setTempsPm(geraid.tempsPmDefaut);
                epreuve.getBalises().addBalise(balise);
              }
            }
          }
        }
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Il n'existe pas de circuit dans ce fichier.");
    }
  }
  
  // Méthode récupérant toutes les categories
  public static void recupereTousPostesV2(GeRaid geraid, Epreuve epreuve)
  {
    List<?> postes = racine.getChildren(CONTROL);
    if(postes.size()>0)
    {
      Iterator<?> i = postes.iterator();
      while (i.hasNext())
      {
        Element poste = (Element) i.next();
        Balise balise = new Balise(Integer.parseInt(poste.getChildTextTrim(CONTROLCODE)));
        balise.setPoints(geraid.pointsDefaut);
        balise.setTemps(geraid.tempsDefaut);
        balise.setPointsPm(geraid.pointsPmDefaut);
        balise.setTempsPm(geraid.tempsPmDefaut);
        epreuve.getBalises().addBalise(balise);
      }
    }
  }
  
  // Méthode récupérant toutes les categories
  public static void recupereCourseV3(GeRaid geraid, Epreuve epreuve)
  {
    Element race = racine.getChild(RACECOURSEDATA, racine.getNamespace());
    List<?> courses = race.getChildren(COURSE, racine.getNamespace());
    String[] liste = new String[courses.size()+1];
    liste[0] = "Tous postes";
    if(courses.size()>0)
    {
      for (int i=0; i<courses.size(); i++)
      {
          Element course = (Element) courses.get(i);
          liste[i+1] = course.getChildText(NAME, racine.getNamespace());
          
      }
      String courseChoisie = (String) JOptionPane.showInputDialog(null, "Choisissez un circuit : ", "Choix d'un circuit", JOptionPane.INFORMATION_MESSAGE, 
          null, liste, liste[0]);
      if(courseChoisie != null)  
      {
        if(courseChoisie.compareTo(liste[0])==0)
        {
          recupereTousPostesV3(geraid, epreuve);
          return;
        }
        Iterator<?> i = courses.iterator();
        while (i.hasNext())
        {
          Element course = (Element) i.next();
          if(course.getChildText(NAME, racine.getNamespace()).compareTo(courseChoisie)==0)
          {
            //Element cv = course.getChild(COURSEVARIATION);
            List<?> controls = course.getChildren(COURSECONTROL, racine.getNamespace());
            Iterator<?> j = controls.iterator();
            while (j.hasNext())
            {
              Element code = (Element) j.next();
              if(code.getAttributeValue("type").compareTo(CONTROL)==0)
              {
                Balise balise = new Balise(Integer.parseInt(code.getChildTextTrim(CONTROL, racine.getNamespace())));
                balise.setPoints(geraid.pointsDefaut);
                balise.setTemps(geraid.tempsDefaut);
                balise.setPointsPm(geraid.pointsPmDefaut);
                balise.setTempsPm(geraid.tempsPmDefaut);
                epreuve.getBalises().addBalise(balise);
              }
            }
          }
        }
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"Il n'existe pas de circuit dans ce fichier.");
    }
  }
  
  // Méthode récupérant toutes les categories
  public static void recupereTousPostesV3(GeRaid geraid, Epreuve epreuve)
  {
    List<?> postes = racine.getChild(RACECOURSEDATA, racine.getNamespace()).getChildren(CONTROL, racine.getNamespace());
    if(postes.size()>0)
    {
      Iterator<?> i = postes.iterator();
      while (i.hasNext())
      {
        Element poste = (Element) i.next();
        if (isInt(poste.getChildTextTrim(ID, racine.getNamespace()))) 
        {
          Balise balise = new Balise(Integer.parseInt(poste.getChildTextTrim(ID, racine.getNamespace())));
          balise.setPoints(geraid.pointsDefaut);
          balise.setTemps(geraid.tempsDefaut);
          balise.setPointsPm(geraid.pointsPmDefaut);
          balise.setTempsPm(geraid.tempsPmDefaut);
          epreuve.getBalises().addBalise(balise);
        }
      }
    }
  }

  private static boolean isInt(String str)
  {
      return (str.lastIndexOf("-") == 0 && !str.equals("-0")) ? str.replace("-", "").matches(
              "[0-9]+") : str.matches("[0-9]+");
  }
}
