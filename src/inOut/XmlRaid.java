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

import outils.TimeManager;

import to.Balise;
import to.Balises;
import to.Categorie;
import to.Categories;
import to.Epreuve;
import to.Epreuves;
import to.Equipe;
import to.Equipes;
import to.Etape;
import to.Etapes;
import to.Parcours;
import to.Partiel;
import to.Penalite;
import to.PenaliteIndividuelle;
import to.Puce;
import to.Raider;
import to.Raiders;
import to.ResultatPuce;
import to.TypeDepart;
import to.TypeLimite;

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
public class XmlRaid
{
  // Déclaration des variables Document et de la racine
  private static Document document;
  private static Element racine;
  private static String GERAID = "geraid";
  private static String ENTETE = "entete";
  private static String VERSION = "version";
  private static String PIEDPAGE = "piedPage";
  private static String HZH = "hzh";
  private static String HZM = "hzm";
  private static String PARCOURSS = "parcourss";
  private static String PARCOURS = "parcours";
  private static String P_NOM = "nom";
  private static String EQUIPES = "equipes";
  private static String EQUIPE = "equipe";
  private static String E_NOM = "nom";
  private static String RAIDERS = "raiders";
  private static String RAIDER = "raider";
  private static String E_PRENOM = "prenom";
  private static String DOSSARD = "dossard";
  private static String IDPUCE = "idPuce";
  private static String NC = "nc";
  private static String ABS = "abs";
  private static String CATEGORIES = "categories";
  private static String CATEGORIE = "categorie";
  private static String NOMLONG = "nomLong";
  private static String NOMCOURT = "nomCourt";
  private static String ETAPES = "etapes";
  private static String ETAPE = "etape";
  private static String EPREUVES = "epreuves";
  private static String EPREUVE = "epreuve";
  private static String APRESEPREUVEPRECEDENTE = "aep";
  private static String AVANTEPREUVESUIVANTE = "aes";
  private static String MULTI = "multi";
  private static String ET_NOM = "nom";
  private static String FIN = "fin";
  private static String HEURE = "heure";
  private static String TYPEL = "typeL";
  private static String LIMITE = "limite";
  private static String BALISES = "balises";
  private static String BALISE = "balise";
  private static String LIGNE = "ligne";
  private static String DEBUTB1 = "debutB1";
  private static String FINB = "finB";
  private static String NUMERO = "numero";
  private static String TYPE = "type";
  private static String POINTS = "points";
  private static String MN = "mn";
  private static String TEMPS = "temps";
  private static String POINTS_PM = "pointsPm";
  private static String TEMPS_PM = "tempsPm";
  private static String CHRONO = "chrono";
  private static String RESULTATS = "resultats";
  private static String RESULTAT = "resultat";
  private static String PUCE = "puce";
  private static String ERASE = "erase";
  private static String CONTROL = "control";
  private static String START = "start";
  private static String FINISH = "finish";
  private static String READ = "read";
  private static String PARTIELS = "partiels";
  private static String PARTIEL = "partiel";
  private static String CODE = "code";
  private static String TIME = "time";
  private static String HORSCHRONO = "hc";
  private static String PENALITES = "penalites";
  private static String PENALITE = "penalite";
  private static String NOM = "nom";
  private static String PENINDVS = "penIndvs";
  private static String PENINDV = "penIndv";
  private static String GEL = "gel";


  /**
   * Méthode lisant le fichier XML passé en paramètre et construisant 
   * Le vecteur contenant tous les parcours
   * @param fichier
   */
  // Méthode de lecture du fichier XML
  public  static void lecture(GeRaid geRaid, String fichier)
  {
    SAXBuilder sxb = new SAXBuilder();
    try
    {
      document = sxb.build(new File(fichier));
    }
    catch (JDOMException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur de lecture du fichier du Raid : "+e.getClass().getName()+", "+e.getMessage());
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Erreur de lecture du fichier du Raid : "+e.getClass().getName()+", "+e.getMessage());
    }
    racine = document.getRootElement();
    geRaid.nomRaid = racine.getChildText(P_NOM);
    geRaid.entete = racine.getChildText(ENTETE);
    geRaid.piedPage = racine.getChildText(PIEDPAGE);
    if(null != racine.getChild(HZH))
    {
      geRaid.heureZero.x = Integer.parseInt(racine.getChildText(HZH));
      geRaid.heureZero.y = Integer.parseInt(racine.getChildText(HZM));
    }
    recupereAllCategories(geRaid);
    recupereAllParcours(geRaid);
    recupereAllResultats(geRaid);
    recupereAllPenalites(geRaid);
  }

  
  // Méthode récupérant toutes les resultats
  private static void recupereAllPenalites(GeRaid geRaid)
  {
    if(racine.getChild(PENALITES) == null)
    {
      return;
    }
    Element penalite = racine.getChild(PENALITES);
    List<?> penalites = penalite.getChildren(PENALITE);
    Iterator<?> i = penalites.iterator();
    
    while (i.hasNext())
    {
      Element v = (Element) i.next();
      
      Penalite p = new Penalite();
      p.setParcours(geRaid.getParcourss().getParcours(v.getChildText(PARCOURS)));
      p.setEtape(p.getParcours().getEtape(v.getChildText(ETAPE)));
      p.setNom(v.getChildText(NOM));

      Element penIndv = v.getChild(PENINDVS);
      List<?> penIndvs = penIndv.getChildren(PENINDV);
      Iterator<?> j = penIndvs.iterator();

      while (j.hasNext())
      {
        Element pi = (Element) j.next();
        PenaliteIndividuelle pein = new PenaliteIndividuelle();
        pein.setPuce(pi.getChildText(PUCE));
        pein.setPoint(Integer.parseInt(pi.getChildText(POINTS)));
        pein.setTemps(Integer.parseInt(pi.getChildText(TEMPS)));
        p.getPenalites().add(pein);
      }
      // Ajout du parcours dans le vecteur
      geRaid.penalites.getPenalites().add(p);
    }
  }
  
  // Méthode récupérant toutes les resultats
  private static void recupereAllResultats(GeRaid geRaid)
  {
    Element result = racine.getChild(RESULTATS);
    List<?> results = result.getChildren(RESULTAT);
    Iterator<?> i = results.iterator();
    
    while (i.hasNext())
    {
        Element v = (Element) i.next();
        
        ResultatPuce r = new ResultatPuce();
        r.setParcours(geRaid.getParcourss().getParcours(v.getChildText(PARCOURS)));
        r.setEtape(r.getParcours().getEtape(v.getChildText(ETAPE)));
        r.setEquipe(r.getParcours().getEquipe(v.getChildText(EQUIPE)));
        
        
        Element p = v.getChild(PUCE);
        Puce puce = r.getPuce();
        puce.setErasetime(TimeManager.newDate(p.getChildText(ERASE)));
        puce.setControltime(TimeManager.newDate(p.getChildText(CONTROL)));
        puce.setStarttime(TimeManager.newDate(p.getChildText(START)));
        puce.setFinishtime(TimeManager.newDate(p.getChildText(FINISH)));
        puce.setReadtime(TimeManager.newDate(p.getChildText(READ)));

        Element parts = p.getChild(PARTIELS);
        List<?> partiels = parts.getChildren(PARTIEL);
        Iterator<?> j = partiels.iterator();
        int k = 0;
        Partiel[] ps = new Partiel[partiels.size()];
        while (j.hasNext())
        {
          Element part = (Element) j.next();
          Partiel partiel = new Partiel();
          partiel.setCode(Integer.parseInt(part.getChildText(CODE)));
          partiel.setTime(TimeManager.newDate(part.getChildText(TIME)));
          ps[k] = partiel;
          k++;
        }
        puce.setPartiels(ps);
      
      // Ajout du parcours dans le vecteur
        if(r.getEquipe()!=null)
        {
          geRaid.addResultatPuce(r);
        }
        else
        {
          JOptionPane.showMessageDialog(null,"Cette puce n'est pas attribuée mais possède un résultat : "+ v.getChildText(EQUIPE));
        }
    }
  }
  
  // Méthode récupérant toutes les parcours
  private static void recupereAllParcours(GeRaid geRaid)
  {
    Element pars = racine.getChild(PARCOURSS);
    List<?> parcours = pars.getChildren(PARCOURS);
    Iterator<?> i = parcours.iterator();
    
    while (i.hasNext())
    {
        Element v = (Element) i.next();
        
        Parcours p = new Parcours(v.getChild(P_NOM).getText().trim());
        p.setEquipes(recupereEquipes(geRaid, v));
        p.setEtapes(recupereEtapes(geRaid, v));
      
      // Ajout du parcours dans le vecteur
        geRaid.addParcours(p);
    }
  }
  
  private static Equipes recupereEquipes(GeRaid geRaid, Element p)
  {
    Equipes retour = new Equipes();
    Element eqs = p.getChild(EQUIPES);
    List<?> equipes = eqs.getChildren(EQUIPE);
    Iterator<?> i = equipes.iterator();
    
    while (i.hasNext())
    {
      Element eq = (Element) i.next();
      
      Equipe e = new Equipe(geRaid);
      e.setNom(eq.getChildText(E_NOM));
      e.setDossard(eq.getChildText(DOSSARD));
      e.setIdPuce(eq.getChildText(IDPUCE));
      int nc = Integer.parseInt(eq.getChildText(NC));
      e.setNC((nc == 1)?true:false);
      if(eq.getChild(ABS) != null)
      {
        int abs = Integer.parseInt(eq.getChildText(ABS));
        e.setABS((abs == 1)?true:false);
      }
      e.setCategorie(geRaid.getCategorie().getCategorie(eq.getChildText(CATEGORIE)));
      e.setRaiders(recupereAllRaiders(eq));
      
      retour.addEquipe(e);
    }
    return retour;
  }
  
  private static Raiders recupereAllRaiders(Element e)
  {
    Raiders raiders = new Raiders();
    Element rds = e.getChild(RAIDERS);
    List<?> c = rds.getChildren(RAIDER);
    Iterator<?> i = c.iterator();
    
    while (i.hasNext())
    {
        Element v = (Element) i.next();
        
        Raider r = new Raider();
        r.setNom(v.getChildText(E_NOM));
        r.setPrenom(v.getChildText(E_PRENOM));
        raiders.addRaider(r);
    }
    return raiders;
  }

  private static Epreuves recupereEpreuves(GeRaid geRaid, Element p)
  {
    Epreuves retour = new Epreuves();
    Element ets = p.getChild(EPREUVES);
    List<?> epreuves = ets.getChildren(EPREUVE);
    Iterator<?> i = epreuves.iterator();
    
    while (i.hasNext())
    {
      Element et = (Element) i.next();
      
      Epreuve e = new Epreuve();
      e.setNom(et.getChildText(ET_NOM));
      e.setTypeLimite(TypeLimite.valueOf(et.getChildText(TYPEL)));
      if(e.getTypeLimite().equals(TypeLimite.AVECLIMITEHORAIRE))
      {
        e.setHeureLimite(TimeManager.newDate(et.getChildText(LIMITE)));
      }
      if(e.getTypeLimite().equals(TypeLimite.AVECLIMITETEMPS))
      {
        e.setTempsLimite(TimeManager.newDate(et.getChildText(LIMITE)));
      }
      if(!e.getTypeLimite().equals(TypeLimite.SANSLIMITE))
      {
        e.setPenalite(Integer.parseInt(et.getChildText(POINTS)));
        e.setPenaliteTemps(Integer.parseInt(et.getChildText(MN)));
        e.setIntervalPenalite(Integer.parseInt(et.getChildText(TEMPS)));
      }
      if(et.getChild(MULTI) != null)
      {
        e.setMultiplicateurTemps(Integer.parseInt(et.getChildText(MULTI)));
      }
      if(et.getChild(LIGNE) != null)
      {
        int ligne = Integer.parseInt(et.getChildText(LIGNE));
        e.setLigne((ligne)==1?true:false);
        int db1 = Integer.parseInt(et.getChildText(DEBUTB1));
        e.setDebutEpreuve((db1)==1?true:false);
        int fdb = Integer.parseInt(et.getChildText(FINB));
        e.setFinEpreuve((fdb)==1?true:false);if(et.getChild(LIGNE) != null)
        if(et.getChild(CHRONO) != null)
        {
          int c = Integer.parseInt(et.getChildText(CHRONO));
          e.setChrono((c)==1?true:false);
        }
        else
        {
          e.setChrono(false);
        }
        if(et.getChild(HORSCHRONO) != null)
        {
          int c = Integer.parseInt(et.getChildText(HORSCHRONO));
          e.setHorsChrono((c)==1?true:false);
        }
        else
        {
          e.setHorsChrono(false);
        }
      }
      else
      {
        e.setLigne(false);
        e.setDebutEpreuve(false);
        e.setFinEpreuve(false);
        e.setChrono(false);
      }
      if(et.getChild(AVANTEPREUVESUIVANTE) != null)
      {
        int as = Integer.parseInt(et.getChildText(AVANTEPREUVESUIVANTE));
        e.setAvantEpreuveSuivante((as)==1?true:false);
      }
      if(et.getChild(APRESEPREUVEPRECEDENTE) != null)
      {
        int as = Integer.parseInt(et.getChildText(APRESEPREUVEPRECEDENTE));
        e.setApresEpreuvePrecedente((as)==1?true:false);
      }
      
      e.setBalises(recupereBalises(geRaid, et));
      
      retour.getEpreuves().add(e);
    }
    
    return retour;
  }
    
  private static Etapes recupereEtapes(GeRaid geRaid, Element p)
  {
    Etapes retour = new Etapes();
    Element ets = p.getChild(ETAPES);
    List<?> etapes = ets.getChildren(ETAPE);
    Iterator<?> i = etapes.iterator();
    
    while (i.hasNext())
    {
      Element et = (Element) i.next();
      
      Etape e = new Etape();
      e.setNom(et.getChildText(ET_NOM));
      int fini = Integer.parseInt(et.getChildText(FIN));
      e.setFini((fini == 1)?true:false);
      if(et.getChild(GEL) != null)
      {
        int gel = Integer.parseInt(et.getChildText(GEL));
        e.setGelDansLimiteTemps((gel == 1)?true:false);
      }
      e.setType(TypeDepart.valueOf(et.getChildText(TYPE)));
      if(e.getType().equals(TypeDepart.GROUPE))
      {
        e.setHeureDepart(TimeManager.newDate(et.getChildText(HEURE)));
      }
      e.setTypeLimite(TypeLimite.valueOf(et.getChildText(TYPEL)));
      if(e.getTypeLimite().equals(TypeLimite.AVECLIMITEHORAIRE))
      {
        e.setHeureLimite(TimeManager.newDate(et.getChildText(LIMITE)));
      }
      if(e.getTypeLimite().equals(TypeLimite.AVECLIMITETEMPS))
      {
        e.setTempsLimite(TimeManager.newDate(et.getChildText(LIMITE)));
      }
      if(!e.getTypeLimite().equals(TypeLimite.SANSLIMITE))
      {
        e.setPenalite(Integer.parseInt(et.getChildText(POINTS)));
        e.setPenaliteTemps(Integer.parseInt(et.getChildText(MN)));
        e.setIntervalPenalite(Integer.parseInt(et.getChildText(TEMPS)));
      }
      
      e.setEpreuves(recupereEpreuves(geRaid, et));
      
      retour.getEtapes().add(e);
    }
    return retour;
  }
  
  private static Balises recupereBalises(GeRaid geRaid, Element p)
  {
    Balises retour = new Balises();
    Element bs = p.getChild(BALISES);
    List<?> balises = bs.getChildren(BALISE);
    Iterator<?> i = balises.iterator();
    
    while (i.hasNext())
    {
      Element b = (Element) i.next();
      
      Balise e = new Balise();
      e.setNumero(Integer.parseInt(b.getChildText(NUMERO)));
      e.setPoints(Integer.parseInt(b.getChildText(POINTS)));
      e.setTemps(Integer.parseInt(b.getChildText(TEMPS)));
      e.setPointsPm(Integer.parseInt(b.getChildText(POINTS_PM)));
      e.setTempsPm(Integer.parseInt(b.getChildText(TEMPS_PM)));
      int chrono = Integer.parseInt(b.getChildText(CHRONO));
      switch (chrono)
      {
        case 0:
          e.setArreterChrono(false);
          e.setDemarerChrono(false);
          break;
        case 1:
          e.setArreterChrono(true);
          e.setDemarerChrono(false);
          break;
        case 2:
          e.setArreterChrono(false);
          e.setDemarerChrono(true);
          break;
          
        default:
          break;
      }
      
      retour.getBalises().add(e);
    }
    return retour;
  }
  
  private static Categories recupereAllCategories(GeRaid geRaid)
  {
    Categories categories = new Categories();
    Element cats = racine.getChild(CATEGORIES);
    List<?> c = cats.getChildren(CATEGORIE);
    Iterator<?> i = c.iterator();
    
    while (i.hasNext())
    {
        Element v = (Element) i.next();
        
        Categorie cat = new Categorie(v.getChildText(NOMLONG),v.getChildText(NOMCOURT));
        geRaid.addCategorie(cat);
    }
    return categories;
  }
  
  /**
   * Méthode créant le fichier XML passé en paramètre 
   * et enregistrant le Raid
   * @param fichier
   */
  //Méthode qui enregistre au format XML le Raid
  public static void enregistre(GeRaid geRaid, String fichier)
  {
    //Déclaration des variables Document et de la racine
    Element racine = new Element(GERAID);
    Document document = new Document(racine);
    Element version = new Element(VERSION);
    version.setText(geRaid.version);
    racine.addContent(version);
    Element nomRaid = new Element(P_NOM);
    nomRaid.setText(geRaid.nomRaid);
    racine.addContent(nomRaid);
    Element enteteRaid = new Element(ENTETE);
    enteteRaid.setText(geRaid.entete);
    racine.addContent(enteteRaid);
    Element piegPageRaid = new Element(PIEDPAGE);
    piegPageRaid.setText(geRaid.piedPage);
    racine.addContent(piegPageRaid);
    Element heureZeroH = new Element(HZH);
    heureZeroH.setText(geRaid.heureZero.x +"");
    racine.addContent(heureZeroH);
    Element heureZeroM = new Element(HZM);
    heureZeroM.setText(geRaid.heureZero.y +"");
    racine.addContent(heureZeroM);
    //Lecture du vecteur de Categorie et construction de la structure XML
    Element cats = new Element(CATEGORIES);
    for(int i =0;i<geRaid.getCategorie().getSize();i++)
    {
      Element e = new Element(CATEGORIE);
      
      Element nomL = new Element(NOMLONG);
      nomL.setText(geRaid.getCategorie().getCategories().get(i).getNomLong());
      e.addContent(nomL);
      Element nomC = new Element(NOMCOURT);
      nomC.setText(geRaid.getCategorie().getCategories().get(i).getNomCourt());
      e.addContent(nomC);

      cats.addContent(e);
    }
    racine.addContent(cats);
    
    //Lecture du vecteur de Parcours et construction de la structure XML
    Element pars = new Element(PARCOURSS);
    for(int i =0;i<geRaid.getParcourss().getSize();i++)
    {
      Element p = new Element(PARCOURS);
      
      Element nom = new Element(P_NOM);
      nom.setText(geRaid.getParcourss().getNom(i));
      p.addContent(nom);
      
      Element eqs = new Element(EQUIPES);
      Equipes equipes = geRaid.getParcourss().getParcourss().get(i).getEquipes();
      for(int j = 0; j<equipes.getSize(); j++)
      {
        Element e = new Element(EQUIPE);
        Equipe eq = equipes.getEquipes().get(j);
        
        Element enom = new Element(E_NOM);
        enom.setText(eq.getNom());
        e.addContent(enom);
        Element eDos = new Element(DOSSARD);
        eDos.setText(eq.getDossard());
        e.addContent(eDos);
        Element eId = new Element(IDPUCE);
        eId.setText(eq.getIdPuce());
        e.addContent(eId);
        Element nc = new Element(NC);
        nc.setText(eq.getNC());
        e.addContent(nc);
        Element abs = new Element(ABS);
        abs.setText(eq.getABS());
        e.addContent(abs);
        Element eCat = new Element(CATEGORIE);
        eCat.setText(eq.getCategorie().getNomCourt());
        e.addContent(eCat);
        
        // Raiders
        Raiders rds = eq.getRaiders();
        Element rs = new Element(RAIDERS);
        
        for(int k=0; k<rds.getSize(); k++)
        {
          Raider rd = rds.getRaiders().get(k);
          Element r = new Element(RAIDER);

          Element rnom = new Element(E_NOM);
          rnom.setText(rd.getNom());
          r.addContent(rnom);
          Element rpnom = new Element(E_PRENOM);
          rpnom.setText(rd.getPrenom());
          r.addContent(rpnom);

          rs.addContent(r);
        }
        e.addContent(rs);
        
        eqs.addContent(e);
      }
      p.addContent(eqs);

      Element ets = new Element(ETAPES);
      Etapes etapes = geRaid.getParcourss().getParcourss().get(i).getEtapes();
      for(int l = 0; l<etapes.getSize(); l++)
      {
        Element e = new Element(ETAPE);
        
        Element enom = new Element(ET_NOM);
        enom.setText(etapes.getEtapes().get(l).getNom());
        e.addContent(enom);
        Element efini = new Element(FIN);
        efini.setText(etapes.getEtapes().get(l).getFini());
        e.addContent(efini);
        Element eGel = new Element(GEL);
        eGel.setText(etapes.getEtapes().get(l).getGelDansLimiteTemps());
        e.addContent(eGel);
        Element etype = new Element(TYPE);
        etype.setText(etapes.getEtapes().get(l).getType().name());
        e.addContent(etype);
        Element eheure = new Element(HEURE);
        if(etapes.getEtapes().get(l).getType().equals(TypeDepart.GROUPE))
        {
          eheure.setText(etapes.getEtapes().get(l).getHeureDepart().getTime()+"");
        }
        e.addContent(eheure);
        Element etypeL = new Element(TYPEL);
        etypeL.setText(etapes.getEtapes().get(l).getTypeLimite().name());
        e.addContent(etypeL);
        Element eLimite = new Element(LIMITE);
        if(etapes.getEtapes().get(l).getTypeLimite().equals(TypeLimite.AVECLIMITEHORAIRE))
        {
          eLimite.setText(etapes.getEtapes().get(l).getHeureLimite().getTime() + "");
        }
        else if(etapes.getEtapes().get(l).getTypeLimite().equals(TypeLimite.AVECLIMITETEMPS))
        {
          eLimite.setText(etapes.getEtapes().get(l).getTempsLimite().getTime() + "");
        }
        else if(etapes.getEtapes().get(l).getTypeLimite().equals(TypeLimite.SANSLIMITE))
        {
          eLimite.setText(0 + "");
        }
        e.addContent(eLimite);
        Element ePoints = new Element(POINTS);
        ePoints.setText(etapes.getEtapes().get(l).getPenalite() + "");
        e.addContent(ePoints);
        Element eMn = new Element(MN);
        eMn.setText(etapes.getEtapes().get(l).getPenaliteTemps() + "");
        e.addContent(eMn);
        Element eTemps = new Element(TEMPS);
        eTemps.setText(etapes.getEtapes().get(l).getIntervalPenalite() + "");
        e.addContent(eTemps);
        
        Element eps = new Element(EPREUVES);
        Epreuves epreuves = etapes.getEtapes().get(l).getEpreuves();
        for(int z=0; z<epreuves.getSize(); z++)
        {
          Element ep = new Element(EPREUVE);

          Element epnom = new Element(ET_NOM);
          epnom.setText(epreuves.getEpreuves().get(z).getNom());
          ep.addContent(epnom);
          Element multi = new Element(MULTI);
          multi.setText(epreuves.getEpreuves().get(z).getMultiplicateurTemps() + "");
          ep.addContent(multi);
          Element eptypeL = new Element(TYPEL);
          eptypeL.setText(epreuves.getEpreuves().get(z).getTypeLimite().name());
          ep.addContent(eptypeL);
          Element epLimite = new Element(LIMITE);
          if(epreuves.getEpreuves().get(z).getTypeLimite().equals(TypeLimite.AVECLIMITEHORAIRE))
          {
            epLimite.setText(epreuves.getEpreuves().get(z).getHeureLimite().getTime() + "");
          }
          else if(epreuves.getEpreuves().get(z).getTypeLimite().equals(TypeLimite.AVECLIMITETEMPS))
          {
            epLimite.setText(epreuves.getEpreuves().get(z).getTempsLimite().getTime() + "");
          }
          else if(epreuves.getEpreuves().get(z).getTypeLimite().equals(TypeLimite.SANSLIMITE))
          {
            epLimite.setText(0 + "");
          }
          ep.addContent(epLimite);
          Element li = new Element(LIGNE);
          li.setText(epreuves.getEpreuves().get(z).getLigne());
          ep.addContent(li);
          Element db1 = new Element(DEBUTB1);
          db1.setText(epreuves.getEpreuves().get(z).getDebutEpreuve());
          ep.addContent(db1);
          Element fdb = new Element(FINB);
          fdb.setText(epreuves.getEpreuves().get(z).getFinEpreuve());
          ep.addContent(fdb);
          Element ch = new Element(CHRONO);
          ch.setText(epreuves.getEpreuves().get(z).getChrono());
          ep.addContent(ch);
          Element HC = new Element(HORSCHRONO);
          HC.setText(epreuves.getEpreuves().get(z).getHorsChrono());
          ep.addContent(HC);
          Element epPoints = new Element(POINTS);
          epPoints.setText(epreuves.getEpreuves().get(z).getPenalite() + "");
          ep.addContent(epPoints);
          Element epMn = new Element(MN);
          epMn.setText(epreuves.getEpreuves().get(z).getPenaliteTemps() + "");
          ep.addContent(epMn);
          Element epTemps = new Element(TEMPS);
          epTemps.setText(epreuves.getEpreuves().get(z).getIntervalPenalite() + "");
          ep.addContent(epTemps);
          Element aep = new Element(APRESEPREUVEPRECEDENTE);
          aep.setText(epreuves.getEpreuves().get(z).getApresEpreuvePrecedente());
          ep.addContent(aep);
          Element aes = new Element(AVANTEPREUVESUIVANTE);
          aes.setText(epreuves.getEpreuves().get(z).getAvantEpreuveSuivante());
          ep.addContent(aes);
          
          Element bs = new Element(BALISES);
          Balises balises = epreuves.getEpreuves().get(z).getBalises();
          for(int m=0; m<balises.getSize(); m++)
          {
            Element b = new Element(BALISE);
            
            Element enumero = new Element(NUMERO);
            enumero.setText(balises.getBalises().get(m).getNumero() + "");
            b.addContent(enumero);
            
            Element bpoints = new Element(POINTS);
            bpoints.setText(balises.getBalises().get(m).getPoints() + "");
            b.addContent(bpoints);
            Element bTemps = new Element(TEMPS);
            bTemps.setText(balises.getBalises().get(m).getTemps() + "");
            b.addContent(bTemps);
            
            Element bpointsPm = new Element(POINTS_PM);
            bpointsPm.setText(balises.getBalises().get(m).getPointsPm() + "");
            b.addContent(bpointsPm);
            Element bTempsPm = new Element(TEMPS_PM);
            bTempsPm.setText(balises.getBalises().get(m).getTempsPm() + "");
            b.addContent(bTempsPm);
            
            Element bchrono = new Element(CHRONO);
            if(balises.getBalises().get(m).isArreterChrono())
            {
              bchrono.setText("1");
            }
            else if(balises.getBalises().get(m).isDemarerChrono())
            {
              bchrono.setText("2");
            }
            else
            {
              bchrono.setText("0");
            }
            b.addContent(bchrono);

            
            bs.addContent(b);
          }
          
          ep.addContent(bs);
          
          eps.addContent(ep);
        }
        e.addContent(eps);
        
        ets.addContent(e);
      }
      p.addContent(ets);
        
      pars.addContent(p);
    }
    racine.addContent(pars);
    
    //Lecture du vecteur de résultats et construction de la structure XML
    Element rs = new Element(RESULTATS);
    for(int i =0;i<geRaid.getResultatsPuce().getSize();i++)
    {
      Element r = new Element(RESULTAT);
      
      Element pc = new Element(PARCOURS);
      pc.setText(geRaid.getResultatsPuce().getResultatsPuce().get(i).getParcours().getNom());
      r.addContent(pc);
      Element et = new Element(ETAPE);
      et.setText(geRaid.getResultatsPuce().getResultatsPuce().get(i).getEtape().getNom());
      r.addContent(et);
      Element eq = new Element(EQUIPE);
      eq.setText(geRaid.getResultatsPuce().getResultatsPuce().get(i).getEquipe().getIdPuce());
      r.addContent(eq);
      
      Element pu = new Element(PUCE);
      
      Element era = new Element(ERASE);
      era.setText(geRaid.getResultatsPuce().getResultatsPuce().get(i).getPuce().getErasetime().getTime()+"");
      pu.addContent(era);
      Element cnt = new Element(CONTROL);
      cnt.setText(geRaid.getResultatsPuce().getResultatsPuce().get(i).getPuce().getControltime().getTime()+"");
      pu.addContent(cnt);
      Element str = new Element(START);
      str.setText(geRaid.getResultatsPuce().getResultatsPuce().get(i).getPuce().getStarttime().getTime()+"");
      pu.addContent(str);
      Element fns = new Element(FINISH);
      fns.setText(geRaid.getResultatsPuce().getResultatsPuce().get(i).getPuce().getFinishtime().getTime()+"");
      pu.addContent(fns);
      Element red = new Element(READ);
      red.setText(geRaid.getResultatsPuce().getResultatsPuce().get(i).getPuce().getReadtime().getTime()+"");
      pu.addContent(red);
      
      Element parts = new Element(PARTIELS);
      
      for(int j=0; j<geRaid.getResultatsPuce().getResultatsPuce().get(i).getPuce().getPartiels().length; j++)
      {
        Element part = new Element(PARTIEL);
        
        Element cd = new Element(CODE);
        cd.setText(geRaid.getResultatsPuce().getResultatsPuce().get(i).getPuce().getPartiels()[j].getCode()+"");
        part.addContent(cd);
        Element tm = new Element(TIME);
        tm.setText(geRaid.getResultatsPuce().getResultatsPuce().get(i).getPuce().getPartiels()[j].getTime().getTime()+"");
        part.addContent(tm);
        
        parts.addContent(part);
      }
      
      pu.addContent(parts);
      
      r.addContent(pu);

      rs.addContent(r);
    }
    racine.addContent(rs);

    //Lecture du vecteur de pénalités et construction de la structure XML
    Element pns = new Element(PENALITES);
    for(int i =0;i<geRaid.penalites.getPenalites().size();i++)
    {
      Element pn = new Element(PENALITE);

      Element nm = new Element(NOM);
      nm.setText(geRaid.penalites.getPenalites().get(i).getNom());
      pn.addContent(nm);
      Element pc = new Element(PARCOURS);
      pc.setText(geRaid.penalites.getPenalites().get(i).getParcours().getNom());
      pn.addContent(pc);
      Element et = new Element(ETAPE);
      et.setText(geRaid.penalites.getPenalites().get(i).getEtape().getNom());
      pn.addContent(et);

      Element pis = new Element(PENINDVS);
      for(int j=0; j<geRaid.penalites.getPenalites().get(i).getPenalites().size(); j++)
      {
        Element pi = new Element(PENINDV);

        Element pu = new Element(PUCE);
        pu.setText(geRaid.penalites.getPenalites().get(i).getPenalites().get(j).getPuce());
        pi.addContent(pu);
        Element pt = new Element(POINTS);
        pt.setText(geRaid.penalites.getPenalites().get(i).getPenalites().get(j).getPoint()+"");
        pi.addContent(pt);
        Element tp = new Element(TEMPS);
        tp.setText(geRaid.penalites.getPenalites().get(i).getPenalites().get(j).getTemps()+"");
        pi.addContent(tp);
        
        pis.addContent(pi);
      }
      pn.addContent(pis);
      
      pns.addContent(pn);
    }
    racine.addContent(pns);
      
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
