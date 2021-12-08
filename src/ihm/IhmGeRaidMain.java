/**
 * 
 */
package ihm;

import geRaid.GeRaid;
import outils.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import inOut.CsvEpreuves;
import inOut.CsvEquipes;
import inOut.CsvResultatEpreuve;
import inOut.CsvResultatEtape;
import inOut.CsvResultatParcours;
import inOut.CsvResultatPuce;
import inOut.HtmlResultat;
import inOut.HtmlVisualisationParcours;
import inOut.XmlConfig;
import inOut.XmlOcadCircuit;
import inOut.XmlRaid;

import javax.swing.SwingUtilities;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.ComponentOrientation;

import to.Balise;
import to.Categorie;
import to.Epreuve;
import to.Equipe;
import to.Equipes;
import to.Etape;
import to.Parcours;
import to.ResultatEtape;
import to.ResultatParcours;
import to.ResultatPuce;
import to.TypeTri;
import to.TypeVisualisation;
import to.TypeVisualisationEpreuve;
import to.TypeVisualisationParcours;

import javax.swing.JList;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.Vector;

import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.WindowConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JPopupMenu;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * <P>
 * Titre : IhmGeRaidMain.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class IhmGeRaidMain extends JFrame
{
  public AudioClip bip = Applet.newAudioClip(getClass().getResource("/sons/bip.wav"));
  private String titre = "GeRaid ";  //  @jve:decl-index=0:
  public GeRaid geRaid = new GeRaid(this);
  public static Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize(); // récupération de la dimension de l'écran  //  @jve:decl-index=0:
  private Dimension DimMinimiser = new Dimension(400, 115);  //  @jve:decl-index=0:
  private Dimension minDim = new Dimension(400, 130);
  private Dimension defautDim = new Dimension(400, 100);
  private boolean siReaderEnCours = false;
  private boolean siReaderPuceEnCours = false;
  private PrintStream printStreamLog = null;
  private IhmPenalite ihmPenalite = null;
  private TaskSauvegardeAuto taskSauvegardeAuto = null;
  private TaskExportAuto taskExportAuto = null;
  private Dimension dim = new Dimension(0, 0);
  public TypeTri typeTriCourant = TypeTri.DOSSARD;
  
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JMenuBar jJMenuBar = null;

  private JMenu jMenuFichier = null;

  private JMenuItem jMenuItemNouveau = null;

  private JMenuItem jMenuItemOuvrir = null;

  public JMenuItem jMenuItemEnregistrer = null;

  private JMenuItem jMenuItemEnregistrerSous = null;

  private JMenuItem jMenuItemQuitter = null;

  private JMenu jMenuEdition = null;

  private JMenu jMenuOutils = null;

  private JMenuItem jMenuItemParametres = null;

  private JMenu jMenuAide = null;

  private JMenuItem jMenuItemAide = null;

  private JMenuItem jMenuItemAPropos = null;

  private JScrollPane jScrollPane = null;

  private JPanel jPanel = null;

  private JPanel jPanelNord = null;

  public JComboBox<Parcours> jComboBoxParcours = null;

  private JButton jButtonParcoursPlus = null;

  private JButton jButtonParcoursEdit = null;

  private JButton jButtonParcoursMoins = null;

  public JPanel jPanelEquipes = null;

  public JSplitPane jTabbedPaneParcours = null;

  private JPanel jPanelEtapes = null;

  private JPanel jPanel1 = null;

  private JButton jButtonEquipePlus = null;

  private JButton jButtonEquipeEdit = null;

  private JButton jButtonEquipeMoins = null;
  
  private JPanel jPanel2 = null;
  
  private JScrollPane jScrollPane1 = null;
  @SuppressWarnings("rawtypes")
  public JList jListEquipes = null;
  private JPanel jPanel4 = null;
  public JComboBox<Etape> jComboBoxEtapes = null;
  private JButton jButtonEtapePlus = null;
  private JButton jButtonEtapeEdit = null;
  private JButton jButtonEtapeMoins = null;
  private JPanel jPanel6 = null;
  private JPanel jPanel7 = null;
  private JLabel jLabel1 = null;
  private JButton jButtonBalisePlus = null;
  private JButton jButtonBaliseEdit = null;
  private JButton jButtonBaliseMoins = null;
  @SuppressWarnings("rawtypes")
  public JList jListBalises = null;
  private JButton jButtonSiReader = null;
  private JLabel jLabel2 = null;
  private JPanel jPanel8 = null;
  private JButton jButtonUp = null;
  private JButton jButtonDown = null;
  private JButton jButtonSave = null;
  private JLabel jLabel32 = null;
  private JScrollPane jScrollPane3 = null;
  private JMenuItem jMenuItemConsultation = null;
  private JMenuItem jMenuItemRechercherPuce = null;
  private JMenuItem jMenuItemRaidParametres = null;
  private JButton jButtonImporter = null;
  private JLabel jLabel33 = null;
  private JButton jButtonExporter = null;
  private JLabel jLabel = null;
  private JMenu jMenuResultatSI = null;
  private JMenuItem jMenuItemExportation = null;
  private JPanel jPanelMinimum = null;
  private JCheckBoxMenuItem jCheckBoxMenuItemMinimize = null;
  private JPanel jPanelParcours = null;
  private JLabel jLabelNbEquipes = null;
  private JLabel jLabelNbBalises = null;
  private JButton jButtonSiReaderPuce = null;
  private JLabel jLabel331 = null;
  private JLabel jLabel21 = null;
  private JButton jButtonDossard = null;
  private JSplitPane splitPane;
  private JPanel jPanelResultat;
  private JScrollPane scrollPane;
  private JPanel panel;
  private JPanel panel_1;
  private JPanel panel_2;
  private JPanel panel_3;
  private JButton button;
  private JButton button_1;
  private JButton button_2;
  private JLabel label_2;
  private JComboBox<Categorie> comboBox_2;
  private JLabel label_3;
  @SuppressWarnings("rawtypes")
  private JComboBox comboBox_3;
  private JPanel panel_5;
  private JScrollPane scrollPane_1;
  private JTable table;
  private JPanel jPanel10;
  private JButton button_3;
  private JButton button_4;
  private JButton button_5;
  private JLabel label_1;
  @SuppressWarnings("rawtypes")
  public JList jListEpreuves;
  private JPanel panel_4;
  private JScrollPane scrollPane_2;
  private JRadioButton rdbtnEtape;
  private JRadioButton rdbtnParcours;
  private JMenuItem jMenuItemSauvegarder;
  private JRadioButton rdbtnEpreuve;
  private JButton button_6;
  private JMenuItem jMenuItemGestionPenalites;
  private JLabel lblEquipesEnCourse;
  private JMenuItem jMenuItemVisuParcours;
  private JMenuItem mntmImporterDuCsvGeRaid;
  private JButton btnExporterHtml;
  private JButton buttonOcad;
  private JButton buttonImporterEpreuves;
  private JButton buttonExporterEpreuves;
  private JPanel panel_6;
  private JPopupMenu popupMenu;
  private JPopupMenu popupEquipe;
  private JMenuItem MenuItemEquipe;
  private JMenuItem MenuItemReduit;
  private JMenuItem MenuItemComplet;
  private JMenuItem MenuItemResultatSi;
  private JButton jButtonEquipeMove;
  private JSpinner spinnerTempoSauvegarde;
  private JLabel lblNewLabel;
  private JButton btnNewButton;
  private JPanel panel_7;
  private JSpinner spinnerExportAuto;
  private JLabel label;
  private JButton button_7;
  private JSpinner spinnerHeureZero;
  private JLabel lblH;
  private JSpinner spinnerMinuteZero;
  private JLabel lblMn;
  private JSpinner spinnerPodium;
  private JButton btnPodium;
  private JLabel lblPlaces;
  private JCheckBox chckbxPuceParEquipe;
  private JButton buttonDupliquerParcours;
  private JPanel panel_8;
  private JButton button_8;
  private JButton button_9;
  private JPanel panel_9;
  private JPanel panel_10;
  private JButton button_10;
  private JPanel panel_11;
  private JPanel panel_12;
  private JPanel panel_13;
  private JPanel panel_14;
  private JPanel panel_15;
  private JCheckBox chckbxAfficherLesNoms;
  private JButton btnSort;
  private JMenu mnNewMenu;
  private JMenuItem mntmRechercherEquipe;
  /**
   * This method initializes jJMenuBar	
   * 	
   * @return javax.swing.JMenuBar	
   */
  private JMenuBar getJJMenuBar()
  {
    if (jJMenuBar == null)
    {
      jJMenuBar = new JMenuBar();
      jJMenuBar.add(getJMenuFichier());
      jJMenuBar.add(getJMenuEdition());
      jJMenuBar.add(getJMenuOutils());
      jJMenuBar.add(getJMenuAide());
    }
    return jJMenuBar;
  }

  /**
   * This method initializes jMenuFichier	
   * 	
   * @return javax.swing.JMenu	
   */
  private JMenu getJMenuFichier()
  {
    if (jMenuFichier == null)
    {
      jMenuFichier = new JMenu();
      jMenuFichier.setText("Fichier");
      jMenuFichier.add(getJMenuItemNouveau());
      jMenuFichier.add(getJMenuItemOuvrir());
      jMenuFichier.add(getJMenuItemEnregistrer());
      jMenuFichier.add(getJMenuItemEnregistrerSous());
      jMenuFichier.add(getJMenuItemSauvegarder());
      jMenuFichier.add(getJMenuItemQuitter());
    }
    return jMenuFichier;
  }

  /**
   * This method initializes jMenuItemNouveau	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemNouveau()
  {
    if (jMenuItemNouveau == null)
    {
      jMenuItemNouveau = new JMenuItem();
      jMenuItemNouveau.setText("Nouveau");
      jMenuItemNouveau.setIcon(new ImageIcon(getClass().getResource("/icones/new.png")));
      jMenuItemNouveau.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmEnregistrer ihm = new IhmEnregistrer(IhmGeRaidMain.this);
          ihm.setLocationRelativeTo(IhmGeRaidMain.this);
          ihm.setVisible(true);
          if(ihm.retour<2)
          {
            geRaid.Clear();
            geRaid.setCategorie(geRaid.categoriesNouveauRaid);
            geRaid.nomRaid = "Nouveau raid";
            setTitre();
            jComboBoxParcours.repaint();
            jComboBoxParcours.setSelectedIndex(-1);
            table.setModel(new EtapeTableModel(new ResultatEtape(geRaid, 
                (Etape)jComboBoxEtapes.getSelectedItem(), 
                (Categorie)comboBox_2.getSelectedItem(),
                 (TypeVisualisation)comboBox_3.getSelectedItem(),
                 chckbxPuceParEquipe.isSelected())));
          }
          ihm.dispose();
        }
      });
    }
    return jMenuItemNouveau;
  }

  /**
   * This method initializes jMenuItemOuvrir	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemOuvrir()
  {
    if (jMenuItemOuvrir == null)
    {
      jMenuItemOuvrir = new JMenuItem();
      jMenuItemOuvrir.setText("Ouvrir...");
      jMenuItemOuvrir.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK) );
      jMenuItemOuvrir.setIcon(new ImageIcon(getClass().getResource("/icones/open.png")));
      jMenuItemOuvrir.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmEnregistrer ihm = new IhmEnregistrer(IhmGeRaidMain.this);
          ihm.setLocationRelativeTo(IhmGeRaidMain.this);
          ihm.setVisible(true);
          if(ihm.retour<2)
          {
            try
            {
              ouvrir();
            }
            catch (Exception et)
            {
              JOptionPane.showMessageDialog(IhmGeRaidMain.this, "Le fichier que vous essayer d'ouvrir n'est pas un raid valide.");
            }
          }
          ihm.dispose();
        }
      });
    }
    return jMenuItemOuvrir;
  }
  
  private void ouvrir()
  {
    // On ouvre le navigateur
    JFileChooser chooser = new JFileChooser();
    FiltreFichier filter = new FiltreFichier();
    filter.addExtension("grd");
    filter.setDescription("Fichiers grd");
    chooser.setFileFilter(filter);
    chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
    
    int returnVal = chooser.showOpenDialog(IhmGeRaidMain.this);
    // Si un fichier a été choisi
    if(returnVal == JFileChooser.APPROVE_OPTION) 
    {
      geRaid.Clear();
      geRaid.setFichier(chooser.getSelectedFile().getAbsolutePath());
      XmlRaid.lecture(geRaid, geRaid.getFichier());
      setTitre();
      remplirCategories();
      if(geRaid.getParcourss().getSize()>0)
      {
        jComboBoxParcours.setSelectedIndex(0);
      }
      else
      {
        jComboBoxParcours.setSelectedIndex(-1);
      }
      refresh();
    }
    else
    {
      geRaid.Clear();
      geRaid.setCategorie(geRaid.categoriesNouveauRaid);
      geRaid.nomRaid = "Nouveau Raid";
      setTitre();
      jComboBoxParcours.repaint();
      jComboBoxParcours.setSelectedIndex(-1);
    }
  }

  /**
   * This method initializes jMenuItemEnregistrer	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemEnregistrer()
  {
    if (jMenuItemEnregistrer == null)
    {
      jMenuItemEnregistrer = new JMenuItem();
      jMenuItemEnregistrer.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK) );
      jMenuItemEnregistrer.setText("Enregistrer");
      jMenuItemEnregistrer.setIcon(new ImageIcon(getClass().getResource("/icones/save.png")));
      jMenuItemEnregistrer.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(geRaid.getFichier().compareTo("")==0)
          {
            jMenuItemEnregistrerSous.doClick();
          }
          else
          {
            File file = new File(geRaid.getFichier());
            if(file.exists())
            {
              TaskEnregistrer task = new TaskEnregistrer(IhmGeRaidMain.this);
              task.execute();
              XmlRaid.enregistre(geRaid, geRaid.getFichier());
            }
            else
            {
              JOptionPane.showMessageDialog(IhmGeRaidMain.this, "Le répertoire de destination n'est plus accessible.\nLe raid n'a pas été enregistré.");
            }
          }
        }
      });
    }
    return jMenuItemEnregistrer;
  }

  /**
   * This method initializes jMenuItemEnregistrerSous	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemEnregistrerSous()
  {
    if (jMenuItemEnregistrerSous == null)
    {
      jMenuItemEnregistrerSous = new JMenuItem();
      jMenuItemEnregistrerSous.setText("Enregistrer sous...");
      jMenuItemEnregistrerSous.setIcon(new ImageIcon(getClass().getResource("/icones/exportMenu.png")));
      jMenuItemEnregistrerSous.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          // On ouvre le navigateur sur le répertoire par défaut
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("grd");
          filter.setDescription("Fichiers grd");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
          
          int returnVal = chooser.showSaveDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            geRaid.setFichier(Outils.verifExtension(chooser.getSelectedFile().getAbsolutePath(), ".grd"));
            XmlRaid.enregistre(geRaid, geRaid.getFichier());
            setTitre();
          }
        }
      });
    }
    return jMenuItemEnregistrerSous;
  }

  /**
   * This method initializes jMenuItemQuitter	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemQuitter()
  {
    if (jMenuItemQuitter == null)
    {
      jMenuItemQuitter = new JMenuItem();
      jMenuItemQuitter.setText("Quitter");
      jMenuItemQuitter.setIcon(new ImageIcon(getClass().getResource("/icones/exit.png")));
      jMenuItemQuitter.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmEnregistrer ihm = new IhmEnregistrer(IhmGeRaidMain.this);
          ihm.setLocationRelativeTo(IhmGeRaidMain.this);
          ihm.setVisible(true);
          if(ihm.retour<2)
          {
            printStreamLog.close();
            System.exit(0);
          }
          ihm.dispose();
        }
      });
    }
    return jMenuItemQuitter;
  }

  /**
   * This method initializes jMenuEdition	
   * 	
   * @return javax.swing.JMenu	
   */
  private JMenu getJMenuEdition()
  {
    if (jMenuEdition == null)
    {
      jMenuEdition = new JMenu();
      jMenuEdition.setText("Raid");
      jMenuEdition.add(getJMenuItemRaidParametres());
      jMenuEdition.add(getJMenuItemVisuParcours());
      jMenuEdition.add(getMnNewMenu());
      jMenuEdition.add(getJMenuItemGestionPenalites());
      jMenuEdition.add(getJMenuResultatSI());
    }
    return jMenuEdition;
  }

  /**
   * This method initializes jMenuOutils	
   * 	
   * @return javax.swing.JMenu	
   */
  private JMenu getJMenuOutils()
  {
    if (jMenuOutils == null)
    {
      jMenuOutils = new JMenu();
      jMenuOutils.setText("GeRaid");
      jMenuOutils.add(getJMenuItemParametres());
      jMenuOutils.add(getJCheckBoxMenuItemMinimize());
    }
    return jMenuOutils;
  }

  /**
   * This method initializes jMenuItemParametres	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemParametres()
  {
    if (jMenuItemParametres == null)
    {
      jMenuItemParametres = new JMenuItem();
      jMenuItemParametres.setText("Paramètres...");
      jMenuItemParametres.setIcon(new ImageIcon(getClass().getResource("/icones/param.png")));
      jMenuItemParametres.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmParametres ihm = new IhmParametres(IhmGeRaidMain.this);
          ihm.setLocationRelativeTo(IhmGeRaidMain.this);
          ihm.setVisible(true);
          geRaid.getSiHandler().setPortName(geRaid.getPort());
          geRaid.getSiHandlerPuce().setPortName(geRaid.getPort());
          setTitre();
        }
      });
    }
    return jMenuItemParametres;
  }

  /**
   * This method initializes jMenuAide	
   * 	
   * @return javax.swing.JMenu	
   */
  private JMenu getJMenuAide()
  {
    if (jMenuAide == null)
    {
      jMenuAide = new JMenu();
      jMenuAide.setText("?");
      jMenuAide.add(getJMenuItemAide());
      jMenuAide.add(getJMenuItemAPropos());
    }
    return jMenuAide;
  }

  /**
   * This method initializes jMenuItemAide	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemAide()
  {
    if (jMenuItemAide == null)
    {
      jMenuItemAide = new JMenuItem();
      jMenuItemAide.setText("Aide...");
      jMenuItemAide.setIcon(new ImageIcon(getClass().getResource("/icones/help.png")));
      jMenuItemAide.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          Runtime r = Runtime.getRuntime();
          
          try
          {
            // Lancement du fichier d'aide de l'application
            String adresse = new File(".").getCanonicalPath().toString();
            adresse = "cmd /c start \"\" \"" + adresse + "/aide.pdf\"";
            r.exec(adresse);
          }
          catch (IOException e1)
          {
            JOptionPane.showMessageDialog(IhmGeRaidMain.this,"Erreur de lancement de l'aide : "+e1.getClass().getName());
          }
        }
      });
    }
    return jMenuItemAide;
  }

  /**
   * This method initializes jMenuItemAPropos	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemAPropos()
  {
    if (jMenuItemAPropos == null)
    {
      jMenuItemAPropos = new JMenuItem();
      jMenuItemAPropos.setText("A propos de GeRaid");
      jMenuItemAPropos.setIcon(new ImageIcon(getClass().getResource("/icones/info.png")));
      jMenuItemAPropos.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmAPropos ihm = new IhmAPropos(IhmGeRaidMain.this);
          ihm.setLocationRelativeTo(IhmGeRaidMain.this);
          ihm.setVisible(true);
        }
      });
    }
    return jMenuItemAPropos;
  }

  /**
   * This method initializes jScrollPane	
   * 	
   * @return javax.swing.JScrollPane	
   */
  private JScrollPane getJScrollPane()
  {
    if (jScrollPane == null)
    {
      jScrollPane = new JScrollPane();
      jScrollPane.setPreferredSize(new Dimension(0, 0));
      jScrollPane.setViewportView(getJPanel());
    }
    return jScrollPane;
  }

  /**
   * This method initializes jPanel	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanel()
  {
    if (jPanel == null)
    {
      jPanel = new JPanel();
      jPanel.setLayout(new BorderLayout());
      jPanel.setVisible(true);
      jPanel.setSize(new Dimension(0, 0));
      jPanel.setPreferredSize(new Dimension(0, 0));
      jPanel.add(getJPanelNord(), BorderLayout.NORTH);
      jPanel.add(getSplitPane(), BorderLayout.CENTER);
    }
    return jPanel;
  }

  /**
   * This method initializes jPanelNord	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelNord()
  {
    if (jPanelNord == null)
    {
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setAlignment(FlowLayout.LEFT);
      jPanelNord = new JPanel();
      jPanelNord.setLayout(flowLayout);
      jPanelNord.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      jLabel2 = new JLabel();
      jLabel2.setText("Lecture des puces :");
      jPanelNord.add(getJPanelParcours(), null);
    }
    return jPanelNord;
  }

  /**
   * This method initializes jComboBoxParcours	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<Parcours> getJComboBoxParcours()
  {
    if (jComboBoxParcours == null)
    {
      jComboBoxParcours = new JComboBox<Parcours>();
      jComboBoxParcours.setPreferredSize(new Dimension(250, 20));
      jComboBoxParcours.addItemListener(new java.awt.event.ItemListener()
      {
        @SuppressWarnings("unchecked")
        public void itemStateChanged(java.awt.event.ItemEvent e)
        {
          if(jComboBoxParcours.getSelectedIndex()!=-1)
          {
            jListEquipes.setListData(((Parcours)jComboBoxParcours.getSelectedItem()).getEquipes().getEquipes());
            jListEquipes.repaint();
            if(jListEquipes.getModel().getSize()>0)
            {
              jListEquipes.setSelectedIndex(0);
            }
            else
            {
              jListEquipes.setSelectedIndex(-1);
            }
            jComboBoxEtapes.setModel(new DefaultComboBoxModel<Etape>(((Parcours)jComboBoxParcours.getSelectedItem()).getEtapes().getEtapes()));
            jComboBoxEtapes.repaint();
            if(jComboBoxEtapes.getItemCount()>0)
            {
              jComboBoxEtapes.setSelectedIndex(0);
            }
            else
            {
              jComboBoxEtapes.setSelectedIndex(-1);
            }
            jLabelNbEquipes.setText(((Parcours)jComboBoxParcours.getSelectedItem()).getNbEquipes()+"");
          }
          else
          {
            jListEquipes.setListData(new Vector<Equipes>());
            jListEquipes.repaint();
            jLabelNbEquipes.setText("0");
            jListEquipes.setSelectedIndex(-1);
            jComboBoxEtapes.removeAllItems();
            jComboBoxEtapes.repaint();
            jComboBoxEtapes.setSelectedIndex(-1);
            jLabelNbBalises.setText("0");
          }
          refresh();
        }
      });
    }
    return jComboBoxParcours;
  }

  /**
   * This method initializes jButtonParcoursPlus	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonParcoursPlus()
  {
    if (jButtonParcoursPlus == null)
    {
      jButtonParcoursPlus = new JButton();
      jButtonParcoursPlus.setText("");
      jButtonParcoursPlus.setIcon(new ImageIcon(getClass().getResource("/icones/plus.png")));
      jButtonParcoursPlus.setMnemonic(KeyEvent.VK_UNDEFINED);
      jButtonParcoursPlus.setToolTipText("Ajouter un parcours");
      jButtonParcoursPlus.setPreferredSize(new Dimension(30, 30));
      jButtonParcoursPlus.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmParcours ihmParcours = new IhmParcours(IhmGeRaidMain.this, new Parcours(""), true);
          ihmParcours.setLocationRelativeTo(IhmGeRaidMain.this);
          ihmParcours.setVisible(true);
        }
      });
    }
    return jButtonParcoursPlus;
  }

  /**
   * This method initializes jButtonParcoursEdit	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonParcoursEdit()
  {
    if (jButtonParcoursEdit == null)
    {
      jButtonParcoursEdit = new JButton();
      jButtonParcoursEdit.setText("");
      jButtonParcoursEdit.setPreferredSize(new Dimension(30, 30));
      jButtonParcoursEdit.setToolTipText("Modifier un parcours");
      jButtonParcoursEdit.setIcon(new ImageIcon(getClass().getResource("/icones/search.png")));
      jButtonParcoursEdit.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxParcours.getSelectedIndex()!=-1)
          {
            IhmParcours ihmParcours = new IhmParcours(IhmGeRaidMain.this, (Parcours)jComboBoxParcours.getSelectedItem(), false);
            ihmParcours.setLocationRelativeTo(IhmGeRaidMain.this);
            ihmParcours.setVisible(true);
          }
        }
      });
    }
    return jButtonParcoursEdit;
  }

  /**
   * This method initializes jButtonParcoursMoins	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonParcoursMoins()
  {
    if (jButtonParcoursMoins == null)
    {
      jButtonParcoursMoins = new JButton();
      jButtonParcoursMoins.setText("");
      jButtonParcoursMoins.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
      jButtonParcoursMoins.setToolTipText("Effacer un parcours");
      jButtonParcoursMoins.setPreferredSize(new Dimension(30, 30));
      jButtonParcoursMoins.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(geRaid.getParcourss().getSize()!=0 && jComboBoxParcours.getSelectedIndex()!=-1)
          {
            StringBuffer message = new StringBuffer("<HTML>Souhaitez-vous supprimer le parcours : <b><center>");
            message.append(jComboBoxParcours.getSelectedItem().toString()+"<center></b></HTML>");
            IhmParcoursDelete ihm = new IhmParcoursDelete(IhmGeRaidMain.this, message.toString());
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
        }
      });
    }
    return jButtonParcoursMoins;
  }

  /**
   * This method initializes jPanelEquipes	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelEquipes()
  {
    if (jPanelEquipes == null)
    {
      jPanelEquipes = new JPanel();
      jPanelEquipes.setBorder(new TitledBorder(null, "Equipes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      jPanelEquipes.setLayout(new BorderLayout());
      jPanelEquipes.setName("");
      jPanelEquipes.setPreferredSize(new Dimension(360, 0));
      jPanelEquipes.setMinimumSize(new Dimension(450, 100));
      jPanelEquipes.add(getJPanel1(), BorderLayout.NORTH);
      jPanelEquipes.add(getJPanel2(), BorderLayout.CENTER);
    }
    return jPanelEquipes;
  }

  /**
   * This method initializes jTabbedPaneParcours	
   * 	
   * @return javax.swing.JTabbedPane	
   */
  private JSplitPane getJTabbedPaneParcours()
  {
    if (jTabbedPaneParcours == null)
    {
      jTabbedPaneParcours = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
          getJPanelEquipes(),getJPanelEtapes());
      jTabbedPaneParcours.setPreferredSize(new Dimension(800, 300));
      jTabbedPaneParcours.setOneTouchExpandable(true);
      jTabbedPaneParcours.setContinuousLayout(true);
      jTabbedPaneParcours.setDividerSize(10);
      jTabbedPaneParcours.setDividerLocation(500);
      jTabbedPaneParcours.setLeftComponent(getJPanelEquipes());
      jTabbedPaneParcours.setRightComponent(getJPanelEtapes());
    }
    return jTabbedPaneParcours;
  }

  /**
   * This method initializes jPanelEtapes	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelEtapes()
  {
    if (jPanelEtapes == null)
    {
      jPanelEtapes = new JPanel();
      jPanelEtapes.setPreferredSize(new Dimension(0, 0));
      jPanelEtapes.setMinimumSize(new Dimension(300, 50));
      jPanelEtapes.setLayout(new BorderLayout(0, 0));
      jPanelEtapes.add(getPanel_4(), BorderLayout.NORTH);
      jPanelEtapes.add(getJPanel6(),BorderLayout.CENTER);
    }
    return jPanelEtapes;
  }

  /**
   * This method initializes jPanel1	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanel1()
  {
    if (jPanel1 == null)
    {
      jLabel21 = new JLabel();
      jLabel21.setText("Auto Puce :");
      jLabel331 = new JLabel();
      jLabel331.setBackground(Color.cyan);
      jLabel331.setPreferredSize(new Dimension(5, 30));
      jLabel331.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
      jLabel331.setText(" ");
      jLabel331.setForeground(Color.cyan);
      jLabelNbEquipes = new JLabel();
      jLabelNbEquipes.setText("0");
      jLabel = new JLabel();
      jLabel.setText("Equipes :");
      jLabel33 = new JLabel();
      jLabel33.setBackground(Color.cyan);
      jLabel33.setPreferredSize(new Dimension(5, 30));
      jLabel33.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
      jLabel33.setText(" ");
      jLabel33.setForeground(Color.cyan);
      FlowLayout flowLayout1 = new FlowLayout();
      flowLayout1.setAlignment(FlowLayout.LEFT);
      jPanel1 = new JPanel();
      jPanel1.setPreferredSize(new Dimension(450, 40));
      jPanel1.setLayout(flowLayout1);
      jPanel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      jPanel1.add(jLabelNbEquipes, null);
      jPanel1.add(jLabel, null);
      jPanel1.add(getJButtonEquipePlus(), null);
      jPanel1.add(getJButtonEquipeEdit(), null);
      jPanel1.add(getJButtonEquipeMoins(), null);
      jPanel1.add(getJButtonEquipeMove());
      jPanel1.add(jLabel33, null);
      jPanel1.add(getJButtonDossard(), null);
      jPanel1.add(getBtnSort());
      jPanel1.add(getJButtonImporter(), null);
      jPanel1.add(getJButtonExporter(), null);
      jPanel1.add(jLabel331, null);
      jPanel1.add(jLabel21, null);
      jPanel1.add(getJButtonSiReaderPuce(), null);
    }
    return jPanel1;
  }

  /**
   * This method initializes jButtonEquipePlus	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonEquipePlus()
  {
    if (jButtonEquipePlus == null)
    {
      jButtonEquipePlus = new JButton();
      jButtonEquipePlus.setPreferredSize(new Dimension(30, 30));
      jButtonEquipePlus.setToolTipText("Ajouter une équipe");
      jButtonEquipePlus.setIcon(new ImageIcon(getClass().getResource("/icones/plus.png")));
      jButtonEquipePlus.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxParcours.getSelectedIndex()!=-1)
          {
            IhmEquipes ihm = new IhmEquipes(IhmGeRaidMain.this, new Equipe(geRaid), true);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
            jLabelNbEquipes.setText(((Parcours)jComboBoxParcours.getSelectedItem()).getNbEquipes()+"");
          }
        }
      });
    }
    return jButtonEquipePlus;
  }

  /**
   * This method initializes jButtonEquipeEdit	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonEquipeEdit()
  {
    if (jButtonEquipeEdit == null)
    {
      jButtonEquipeEdit = new JButton();
      jButtonEquipeEdit.setPreferredSize(new Dimension(30, 30));
      jButtonEquipeEdit.setToolTipText("Modifier une équipe");
      jButtonEquipeEdit.setIcon(new ImageIcon(getClass().getResource("/icones/search.png")));
      jButtonEquipeEdit.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jListEquipes.getSelectedIndex()!=-1)
          {
            IhmEquipes ihm = new IhmEquipes(IhmGeRaidMain.this, ((Equipe)jListEquipes.getSelectedValue()), false);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
        }
      });
    }
    return jButtonEquipeEdit;
  }

  /**
   * This method initializes jButtonEquipeMoins	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonEquipeMoins()
  {
    if (jButtonEquipeMoins == null)
    {
      jButtonEquipeMoins = new JButton();
      jButtonEquipeMoins.setPreferredSize(new Dimension(30, 30));
      jButtonEquipeMoins.setToolTipText("Supprimer une équipe");
      jButtonEquipeMoins.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
      jButtonEquipeMoins.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxParcours.getSelectedIndex()!=-1 && 
              ((Parcours)jComboBoxParcours.getSelectedItem()).getEquipes().getSize()!=0 &&
              jListEquipes.getSelectedIndex()!=-1)
          {
              StringBuffer message = new StringBuffer("<HTML>Souhaitez-vous supprimer l'equipe : <b><center>");
              message.append(jListEquipes.getSelectedValue().toString()+"<center></b></HTML>");
              IhmEquipeDelete ihm = new IhmEquipeDelete(IhmGeRaidMain.this, message.toString());
              ihm.setLocationRelativeTo(IhmGeRaidMain.this);
              ihm.setVisible(true);
              jLabelNbEquipes.setText(((Parcours)jComboBoxParcours.getSelectedItem()).getNbEquipes()+"");
          }
        }
      });
    }
    return jButtonEquipeMoins;
  }

  /**
   * This method initializes jPanel2	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanel2()
  {
    if (jPanel2 == null)
    {
      jPanel2 = new JPanel();
      jPanel2.setLayout(new BorderLayout());
      jPanel2.add(getJScrollPane1(), BorderLayout.CENTER);
    }
    return jPanel2;
  }

  /**
   * This method initializes jScrollPane1	
   * 	
   * @return javax.swing.JScrollPane	
   */
  private JScrollPane getJScrollPane1()
  {
    if (jScrollPane1 == null)
    {
      jScrollPane1 = new JScrollPane();
      jScrollPane1.setPreferredSize(new Dimension(0, 0));
      jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      jScrollPane1.setViewportView(getJListEquipes());
    }
    return jScrollPane1;
  }

  /**
   * This method initializes jListEquipes	
   * 	
   * @return javax.swing.JList	
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private JList getJListEquipes()
  {
    if (jListEquipes == null)
    {
      jListEquipes = new JList();
      jListEquipes.addMouseListener(new MouseAdapter() 
      {
        @Override
        public void mouseClicked(MouseEvent arg0) 
        {
          if (arg0.getClickCount() == 2) 
          {
            jButtonEquipeEdit.doClick();
          } 
          else
          {
            popupEquipe.setVisible(false);
            //table.changeSelection(table.rowAtPoint(me.getPoint()), 0, false, false);
            jListEquipes.setSelectedIndex(jListEquipes.locationToIndex(arg0.getPoint()));
            if(arg0.getButton()==MouseEvent.BUTTON3)
            {
              int x = (int) (IhmGeRaidMain.this.getX() + getMousePosition().getX());
              int y = (int) (IhmGeRaidMain.this.getY() + getMousePosition().getY());
              popupEquipe.setLocation(x, y);
              popupEquipe.setVisible(true);
            }
          }
        }
      });
      jListEquipes.setMinimumSize(new Dimension(450, 0));
      jListEquipes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      jListEquipes.setCellRenderer(new EquipeListCellRenderer());
    }
    return jListEquipes;
  }

  /**
   * This method initializes jPanel4	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanel4()
  {
    if (jPanel4 == null)
    {
      jLabelNbBalises = new JLabel();
      jLabelNbBalises.setText("0");
      jLabel32 = new JLabel();
      jLabel32.setBackground(Color.cyan);
      jLabel32.setPreferredSize(new Dimension(5, 30));
      jLabel32.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
      jLabel32.setText(" ");
      jLabel32.setForeground(Color.cyan);
      FlowLayout flowLayout2 = new FlowLayout();
      flowLayout2.setAlignment(FlowLayout.LEFT);
      jPanel4 = new JPanel();
      jPanel4.setBorder(new TitledBorder(null, "Etapes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      jPanel4.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      jPanel4.setLayout(flowLayout2);
      jPanel4.add(getJComboBoxEtapes(), null);
      jPanel4.add(getJButtonEtapePlus(), null);
      jPanel4.add(getJButtonEtapeEdit(), null);
      jPanel4.add(getJButtonEtapeMoins(), null);
      jPanel4.add(jLabel32, null);
    }
    return jPanel4;
  }

  /**
   * This method initializes jComboBoxEtapes	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<Etape> getJComboBoxEtapes()
  {
    if (jComboBoxEtapes == null)
    {
      jComboBoxEtapes = new JComboBox<Etape>();
      jComboBoxEtapes.setPreferredSize(new Dimension(250, 20));
      jComboBoxEtapes.addActionListener(new java.awt.event.ActionListener()
      {
        @SuppressWarnings("unchecked")
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxEtapes.getSelectedIndex()!=-1)
          {
            jListEpreuves.setListData(((Etape)jComboBoxEtapes.getSelectedItem()).getEpreuves().getEpreuves());
            jListEpreuves.repaint();
            if(jListEpreuves.getModel().getSize()>0)
            {
              jListEpreuves.setSelectedIndex(0);
              jLabelNbBalises.setText(((Epreuve)jListEpreuves.getSelectedValue()).getNbBalises()+"");
            }
            else
            {
              jListEpreuves.setSelectedIndex(-1);
              jLabelNbBalises.setText("0");
            }
          }
          else
          {
            jListEpreuves.setListData(new Vector<Epreuve>());
            jListEpreuves.repaint();
            jListEpreuves.setSelectedIndex(-1);
            jLabelNbBalises.setText("0");
          }
          refresh();
        }
      });
    }
    return jComboBoxEtapes;
  }

  /**
   * This method initializes jButtonEtapePlus	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonEtapePlus()
  {
    if (jButtonEtapePlus == null)
    {
      jButtonEtapePlus = new JButton();
      jButtonEtapePlus.setIcon(new ImageIcon(getClass().getResource("/icones/plus.png")));
      jButtonEtapePlus.setToolTipText("Ajouter une étape");
      jButtonEtapePlus.setPreferredSize(new Dimension(30, 30));
      jButtonEtapePlus.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxParcours.getSelectedIndex()!=-1)
          {
            IhmEtapes ihm = new IhmEtapes(IhmGeRaidMain.this, new Etape(), true);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
        }
      });
    }
    return jButtonEtapePlus;
  }

  /**
   * This method initializes jButtonEtapeEdit	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonEtapeEdit()
  {
    if (jButtonEtapeEdit == null)
    {
      jButtonEtapeEdit = new JButton();
      jButtonEtapeEdit.setPreferredSize(new Dimension(30, 30));
      jButtonEtapeEdit.setToolTipText("Modifier une étape");
      jButtonEtapeEdit.setIcon(new ImageIcon(getClass().getResource("/icones/search.png")));
      jButtonEtapeEdit.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxEtapes.getSelectedIndex()!=-1)
          {
            IhmEtapes ihm = new IhmEtapes(IhmGeRaidMain.this, ((Etape)jComboBoxEtapes.getSelectedItem()), false);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
        }
      });
    }
    return jButtonEtapeEdit;
  }

  /**
   * This method initializes jButtonEtapeMoins	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonEtapeMoins()
  {
    if (jButtonEtapeMoins == null)
    {
      jButtonEtapeMoins = new JButton();
      jButtonEtapeMoins.setPreferredSize(new Dimension(30, 30));
      jButtonEtapeMoins.setToolTipText("Supprimer une étape");
      jButtonEtapeMoins.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
      jButtonEtapeMoins.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxParcours.getSelectedIndex()!=-1 && 
              ((Parcours)jComboBoxParcours.getSelectedItem()).getEtapes().getSize()!=0 &&
              jComboBoxEtapes.getSelectedIndex()!=-1)
          {
              StringBuffer message = new StringBuffer("<HTML>Souhaitez-vous supprimer l'étape : <b><center>");
              message.append(jComboBoxEtapes.getSelectedItem().toString()+"<center></b></HTML>");
              IhmEtapeDelete ihm = new IhmEtapeDelete(IhmGeRaidMain.this, message.toString());
              ihm.setLocationRelativeTo(IhmGeRaidMain.this);
              ihm.setVisible(true);
          }
        }
      });
    }
    return jButtonEtapeMoins;
  }

  /**
   * This method initializes jPanel6	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanel6()
  {
    if (jPanel6 == null)
    {
      jPanel6 = new JPanel();
      jPanel6.setBorder(new TitledBorder(null, "Balises", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      jPanel6.setLayout(new BorderLayout());
      jPanel6.add(getJPanel7(), BorderLayout.NORTH);
      jPanel6.add(getJPanel8(), BorderLayout.WEST);
      jPanel6.add(getJScrollPane3(), BorderLayout.CENTER);
    }
    return jPanel6;
  }

  /**
   * This method initializes jPanel7	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanel7()
  {
    if (jPanel7 == null)
    {
      FlowLayout flowLayout4 = new FlowLayout();
      flowLayout4.setAlignment(FlowLayout.LEFT);
      jLabel1 = new JLabel();
      jLabel1.setText("Balises : ");
      jPanel7 = new JPanel();
      jPanel7.setLayout(flowLayout4);
      jPanel7.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
      jPanel7.add(jLabelNbBalises, null);
      jPanel7.add(jLabel1, null);
      jPanel7.add(getJButtonBalisePlus(), null);
      jPanel7.add(getJButtonBaliseEdit(), null);
      jPanel7.add(getJButtonBaliseMoins(), null);
      jPanel7.add(getButtonOcad());
    }
    return jPanel7;
  }

  /**
   * This method initializes jButtonBalisePlus	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonBalisePlus()
  {
    if (jButtonBalisePlus == null)
    {
      jButtonBalisePlus = new JButton();
      jButtonBalisePlus.setPreferredSize(new Dimension(30, 30));
      jButtonBalisePlus.setToolTipText("Ajouter une balise");
      jButtonBalisePlus.setIcon(new ImageIcon(getClass().getResource("/icones/plus.png")));
      jButtonBalisePlus.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jListEpreuves.getSelectedIndex()!=-1)
          {
            int numero = ((Etape)jComboBoxEtapes.getSelectedItem()).getEpreuves().getDernierNumero();
            Balise b = new Balise(numero+1);
            IhmBalise ihm = new IhmBalise(IhmGeRaidMain.this, b, true, false);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
            jLabelNbBalises.setText(((Epreuve)jListEpreuves.getSelectedValue()).getNbBalises()+"");
          }
        }
      });
    }
    return jButtonBalisePlus;
  }

  /**
   * This method initializes jButtonBaliseEdit	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonBaliseEdit()
  {
    if (jButtonBaliseEdit == null)
    {
      jButtonBaliseEdit = new JButton();
      jButtonBaliseEdit.setPreferredSize(new Dimension(30, 30));
      jButtonBaliseEdit.setToolTipText("Modifier une balise");
      jButtonBaliseEdit.setIcon(new ImageIcon(getClass().getResource("/icones/search.png")));
      jButtonBaliseEdit.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jListBalises.getSelectedIndex()!=-1)
          {
            IhmBalise ihm = new IhmBalise(IhmGeRaidMain.this, ((Balise)jListBalises.getSelectedValue()), false, false);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
        }
      });
    }
    return jButtonBaliseEdit;
  }

  /**
   * This method initializes jButtonBaliseMoins	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonBaliseMoins()
  {
    if (jButtonBaliseMoins == null)
    {
      jButtonBaliseMoins = new JButton();
      jButtonBaliseMoins.setPreferredSize(new Dimension(30, 30));
      jButtonBaliseMoins.setToolTipText("Supprimer une balise");
      jButtonBaliseMoins.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
      jButtonBaliseMoins.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jListEpreuves.getSelectedIndex()!=-1 && 
              ((Epreuve)jListEpreuves.getSelectedValue()).getBalises().getSize()!=0 &&
              jListBalises.getSelectedIndex()!=-1)
          {
              StringBuffer message = new StringBuffer("<HTML>Souhaitez-vous supprimer la balise : <b><center>");
              message.append(jListBalises.getSelectedValue().toString()+"<center></b></HTML>");
              IhmBaliseDelete ihm = new IhmBaliseDelete(IhmGeRaidMain.this, message.toString());
              ihm.setLocationRelativeTo(IhmGeRaidMain.this);
              ihm.setVisible(true);
              jLabelNbBalises.setText(((Epreuve)jListEpreuves.getSelectedValue()).getNbBalises()+"");
          }
        }
      });
    }
    return jButtonBaliseMoins;
  }

  /**
   * This method initializes jListBalises	
   * 	
   * @return javax.swing.JList	
   */
  @SuppressWarnings("rawtypes")
  private JList getJListBalises()
  {
    if (jListBalises == null)
    {
      jListBalises = new JList();
      jListBalises.addMouseListener(new MouseAdapter() 
      {
        @Override
        public void mouseClicked(MouseEvent arg0) 
        {
          if (arg0.getClickCount() == 2) 
          {
            jButtonBaliseEdit.doClick();
          } 
        }
      });
    }
    return jListBalises;
  }

  /**
   * This method initializes jButtonSiReader	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonSiReader()
  {
    if (jButtonSiReader == null)
    {
      jButtonSiReader = new JButton();
      jButtonSiReader.setPreferredSize(new Dimension(30, 30));
      jButtonSiReader.setToolTipText("Connecter la station maitre");
      jButtonSiReader.setIcon(new ImageIcon(getClass().getResource("/icones/play.png")));
      jButtonSiReader.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(!siReaderEnCours)
          {
            jButtonSiReaderPuce.setEnabled(false);
            geRaid.getSiHandler().setZeroTime(((Integer)spinnerHeureZero.getValue()*3600 + (Integer)spinnerMinuteZero.getValue()*60)*1000);
            geRaid.getSiHandler().start();
            jButtonSiReader.setIcon(new ImageIcon(getClass().getResource("/icones/attente.png")));
            siReaderEnCours = true;
            spinnerHeureZero.setEnabled(false);
            spinnerMinuteZero.setEnabled(false);
          }
          else
          {
            geRaid.getSiHandler().stop();
            jButtonSiReader.setIcon(new ImageIcon(getClass().getResource("/icones/play.png")));
            siReaderEnCours = false;
            jButtonSiReaderPuce.setEnabled(true);
            spinnerHeureZero.setEnabled(true);
            spinnerMinuteZero.setEnabled(true);
          }
        }
      });
    }
    return jButtonSiReader;
  }

  /**
   * This method initializes jPanel8	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanel8()
  {
    if (jPanel8 == null)
    {
      FlowLayout flowLayout5 = new FlowLayout();
      flowLayout5.setAlignment(FlowLayout.LEFT);
      jPanel8 = new JPanel();
      jPanel8.setPreferredSize(new Dimension(40, 10));
      jPanel8.setLayout(flowLayout5);
      jPanel8.add(getJButtonUp(), null);
      jPanel8.add(getJButtonDown(), null);
    }
    return jPanel8;
  }

  /**
   * This method initializes jButtonUp	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonUp()
  {
    if (jButtonUp == null)
    {
      jButtonUp = new JButton();
      jButtonUp.setPreferredSize(new Dimension(30, 30));
      jButtonUp.setToolTipText("Monter une balise");
      jButtonUp.setIcon(new ImageIcon(getClass().getResource("/icones/up.png")));
      jButtonUp.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jListEpreuves.getSelectedIndex()>=0 && jListBalises.getModel().getSize()>0)
          {
            ((Epreuve)jListEpreuves.getSelectedValue()).getBalises().upBalise((Balise)jListBalises.getSelectedValue());
            jListBalises.repaint();
            jListBalises.setSelectedIndex(jListBalises.getSelectedIndex()-1);
          }
        }
      });
    }
    return jButtonUp;
  }

  /**
   * This method initializes jButtonDown	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonDown()
  {
    if (jButtonDown == null)
    {
      jButtonDown = new JButton();
      jButtonDown.setPreferredSize(new Dimension(30, 30));
      jButtonDown.setToolTipText("Descendre une balise");
      jButtonDown.setIcon(new ImageIcon(getClass().getResource("/icones/down.png")));
      jButtonDown.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jListEpreuves.getSelectedIndex()>=0 && jListBalises.getModel().getSize()>0)
          {
            ((Epreuve)jListEpreuves.getSelectedValue()).getBalises().downBalise((Balise)jListBalises.getSelectedValue());
            jListBalises.repaint();
            jListBalises.setSelectedIndex(jListBalises.getSelectedIndex()+1);
          }
        }
      });
    }
    return jButtonDown;
  }

  /**
   * This method initializes jButtonSave	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonSave()
  {
    if (jButtonSave == null)
    {
      jButtonSave = new JButton();
      jButtonSave.setPreferredSize(new Dimension(30, 30));
      jButtonSave.setToolTipText("Enregistrer le raid");
      jButtonSave.setIcon(new ImageIcon(getClass().getResource("/icones/save32.png")));
      jButtonSave.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          jMenuItemEnregistrer.doClick();
        }
      });
    }
    return jButtonSave;
  }

  /**
   * This method initializes jScrollPane3	
   * 	
   * @return javax.swing.JScrollPane	
   */
  private JScrollPane getJScrollPane3()
  {
    if (jScrollPane3 == null)
    {
      jScrollPane3 = new JScrollPane();
      jScrollPane3.setPreferredSize(new Dimension(200, 90));
      jScrollPane3.setViewportView(getJListBalises());
    }
    return jScrollPane3;
  }

  /**
   * This method initializes jMenuItemConsultation	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemConsultation()
  {
    if (jMenuItemConsultation == null)
    {
      jMenuItemConsultation = new JMenuItem();
      jMenuItemConsultation.setMnemonic(KeyEvent.VK_UNDEFINED);
      jMenuItemConsultation.setIcon(new ImageIcon(getClass().getResource("/icones/g16.png")));
      jMenuItemConsultation.setText("Gérer ...");
      jMenuItemConsultation.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(geRaid.getResultatsPuce().getSize()>0)
          {
            IhmGestionResultatPuce ihm = new IhmGestionResultatPuce(IhmGeRaidMain.this);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
        }
      });
    }
    return jMenuItemConsultation;
  }

  /**
   * This method initializes jMenuItemRechercherPuce	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemRechercherPuce()
  {
    if (jMenuItemRechercherPuce == null)
    {
      jMenuItemRechercherPuce = new JMenuItem();
      jMenuItemRechercherPuce.setText("Rechercher puce ...");
      jMenuItemRechercherPuce.setIcon(new ImageIcon(getClass().getResource("/icones/find.png")));
      jMenuItemRechercherPuce.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmRecherchePuce ihm = new IhmRecherchePuce(IhmGeRaidMain.this);
          ihm.setLocationRelativeTo(IhmGeRaidMain.this);
          ihm.setVisible(true);
        }
      });
    }
    return jMenuItemRechercherPuce;
  }

  /**
   * This method initializes jMenuItemRaidParametres	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemRaidParametres()
  {
    if (jMenuItemRaidParametres == null)
    {
      jMenuItemRaidParametres = new JMenuItem();
      jMenuItemRaidParametres.setText("Paramètres ...");
      jMenuItemRaidParametres.setIcon(new ImageIcon(getClass().getResource("/icones/param.png")));
      jMenuItemRaidParametres.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmParametresRaid ihm = new IhmParametresRaid(IhmGeRaidMain.this);
          ihm.setLocationRelativeTo(IhmGeRaidMain.this);
          ihm.setVisible(true);
          jListEquipes.repaint();
          setTitre();
        }
      });
    }
    return jMenuItemRaidParametres;
  }

  /**
   * This method initializes jButtonImporter	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonImporter()
  {
    if (jButtonImporter == null)
    {
      jButtonImporter = new JButton();
      jButtonImporter.setPreferredSize(new Dimension(30, 30));
      jButtonImporter.setIcon(new ImageIcon(getClass().getResource("/icones/import.png")));
      jButtonImporter.setToolTipText("Importer les \u00E9quipes en CSV");
      jButtonImporter.addActionListener(new java.awt.event.ActionListener()
      {
        @SuppressWarnings("unchecked")
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          // On ouvre le navigateur
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("csv");
          filter.setDescription("Fichiers csv");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
          
          int returnVal = chooser.showOpenDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            String fichier = chooser.getSelectedFile().getAbsolutePath();
            CsvEquipes.importer(geRaid,(Parcours)jComboBoxParcours.getSelectedItem(), fichier);
            jListEquipes.setListData(((Parcours)jComboBoxParcours.getSelectedItem()).getEquipes().getEquipes());
            jListEquipes.repaint();
            if(jListEquipes.getModel().getSize()>0)
            {
              jListEquipes.setSelectedIndex(0);
            }
          }
        }
      });
    }
    return jButtonImporter;
  }

  /**
   * This method initializes jButtonExporter	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonExporter()
  {
    if (jButtonExporter == null)
    {
      jButtonExporter = new JButton();
      jButtonExporter.setPreferredSize(new Dimension(30, 30));
      jButtonExporter.setIcon(new ImageIcon(getClass().getResource("/icones/export.png")));
      jButtonExporter.setToolTipText("Exporter les \u00E9quipes du parcours en CSV");
      jButtonExporter.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          // On ouvre le navigateur sur le répertoire par défaut
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("csv");
          filter.setDescription("Fichiers csv");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
          
          int returnVal = chooser.showSaveDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            String fichier = Outils.verifExtension(chooser.getSelectedFile().getAbsolutePath(), ".csv");
            CsvEquipes.exporter(((Parcours)jComboBoxParcours.getSelectedItem()).getEquipes(), fichier);
          }
        }
      });
    }
    return jButtonExporter;
  }

  /**
   * This method initializes jMenuResultatSI	
   * 	
   * @return javax.swing.JMenu	
   */
  private JMenu getJMenuResultatSI()
  {
    if (jMenuResultatSI == null)
    {
      jMenuResultatSI = new JMenu();
      jMenuResultatSI.setText("Résultats SI");
      jMenuResultatSI.add(getJMenuItemConsultation());
      jMenuResultatSI.add(getJMenuItemExportation());
      jMenuResultatSI.add(getMntmImporterDuCsvGeRaid());
    }
    return jMenuResultatSI;
  }

  /**
   * This method initializes jMenuItemExportation	
   * 	
   * @return javax.swing.JMenuItem	
   */
  private JMenuItem getJMenuItemExportation()
  {
    if (jMenuItemExportation == null)
    {
      jMenuItemExportation = new JMenuItem();
      jMenuItemExportation.setText("Exporter en CSV ...");
      jMenuItemExportation.setIcon(new ImageIcon(getClass().getResource("/icones/exportMenu.png")));
      jMenuItemExportation.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          // On ouvre le navigateur sur le répertoire par défaut
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("csv");
          filter.setDescription("Fichiers csv");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
          
          int returnVal = chooser.showSaveDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            String fichier = Outils.verifExtension(chooser.getSelectedFile().getAbsolutePath(), ".csv");
            CsvResultatPuce.exporter(geRaid.getResultatsPuce(), fichier);
          }
        }
      });
    }
    return jMenuItemExportation;
  }

  /**
   * This method initializes jPanelMinimum	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelMinimum()
  {
    if (jPanelMinimum == null)
    {
      FlowLayout flowLayout3 = new FlowLayout();
      flowLayout3.setAlignment(FlowLayout.LEFT);
      jPanelMinimum = new JPanel();
      jPanelMinimum.setBorder(new TitledBorder(null, "Heure z\u00E9ro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      jPanelMinimum.setLayout(flowLayout3);
      jPanelMinimum.add(getSpinnerHeureZero());
      jPanelMinimum.add(getLblH());
      jPanelMinimum.add(getSpinnerMinuteZero());
      jPanelMinimum.add(getLblMn());
      jPanelMinimum.add(jLabel2, null);
      jPanelMinimum.add(getJButtonSiReader(), null);
    }
    return jPanelMinimum;
  }

  /**
   * This method initializes jCheckBoxMenuItemMinimize	
   * 	
   * @return javax.swing.JCheckBoxMenuItem	
   */
  private JCheckBoxMenuItem getJCheckBoxMenuItemMinimize()
  {
    if (jCheckBoxMenuItemMinimize == null)
    {
      jCheckBoxMenuItemMinimize = new JCheckBoxMenuItem();
      jCheckBoxMenuItemMinimize.setVisible(false);
      jCheckBoxMenuItemMinimize.setText("Affichage réduit");
      jCheckBoxMenuItemMinimize.setIcon(new ImageIcon(getClass().getResource("/icones/reduire.png")));
      jCheckBoxMenuItemMinimize.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jCheckBoxMenuItemMinimize.isSelected())
          {
            defautDim = geRaid.getIhm().getSize();
            minDim = geRaid.getIhm().getMinimumSize();
            geRaid.getIhm().setMinimumSize(DimMinimiser);
            geRaid.getIhm().setSize(DimMinimiser);
            geRaid.getIhm().setResizable(false);
            jScrollPane.getHorizontalScrollBar().setValue(0);
            jScrollPane.getVerticalScrollBar().setValue(0);
            jScrollPane.getHorizontalScrollBar().setEnabled(false);
            jScrollPane.getVerticalScrollBar().setEnabled(false);
            jScrollPane.setWheelScrollingEnabled(false);
            jPanelParcours.setVisible(false);
          }
          else
          {
            geRaid.getIhm().setResizable(true);
            geRaid.getIhm().setMinimumSize(minDim);
            geRaid.getIhm().setSize(defautDim);
            jScrollPane.getHorizontalScrollBar().setEnabled(true);
            jScrollPane.getVerticalScrollBar().setEnabled(true);
            jScrollPane.setWheelScrollingEnabled(true);
            jPanelParcours.setVisible(true);
          }
        }
      });
    }
    return jCheckBoxMenuItemMinimize;
  }

  /**
   * This method initializes jPanelParcours	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelParcours()
  {
    if (jPanelParcours == null)
    {
      FlowLayout flowLayout6 = new FlowLayout();
      flowLayout6.setAlignment(FlowLayout.LEFT);
      jPanelParcours = new JPanel();
      jPanelParcours.setLayout(flowLayout6);
      jPanelParcours.add(getPanel_10());
      jPanelParcours.add(getJPanelMinimum());
    }
    return jPanelParcours;
  }

  /**
   * This method initializes jButtonSiReaderPuce	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonSiReaderPuce()
  {
    if (jButtonSiReaderPuce == null)
    {
      jButtonSiReaderPuce = new JButton();
      jButtonSiReaderPuce.setPreferredSize(new Dimension(30, 30));
      jButtonSiReaderPuce.setIcon(new ImageIcon(getClass().getResource("/icones/play.png")));
      jButtonSiReaderPuce.setToolTipText("Connecter la station maitre");
      jButtonSiReaderPuce.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(!siReaderPuceEnCours)
          {
            jButtonSiReader.setEnabled(false);
            geRaid.getSiHandlerPuce().start();
            jButtonSiReaderPuce.setIcon(new ImageIcon(getClass().getResource("/icones/attente.png")));
            siReaderPuceEnCours = true;
          }
          else
          {
            geRaid.getSiHandlerPuce().stop();
            jButtonSiReaderPuce.setIcon(new ImageIcon(getClass().getResource("/icones/play.png")));
            siReaderPuceEnCours = false;
            jButtonSiReader.setEnabled(true);
          }
        }
      });
    }
    return jButtonSiReaderPuce;
  }

  /**
   * This method initializes jButtonDossard	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonDossard()
  {
    if (jButtonDossard == null)
    {
      jButtonDossard = new JButton();
      jButtonDossard.setPreferredSize(new Dimension(30, 30));
      jButtonDossard.setToolTipText("Numéroter les dossards");
      jButtonDossard.setIcon(new ImageIcon(getClass().getResource("/icones/dossard.png")));
      jButtonDossard.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmDossards ihm = new IhmDossards(IhmGeRaidMain.this, ((Parcours)jComboBoxParcours.getSelectedItem()).getEquipes());
          ihm.setLocationRelativeTo(IhmGeRaidMain.this);
          ihm.setVisible(true);
          jListEquipes.repaint();
        }
      });
    }
    return jButtonDossard;
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    // On récupère le Look courant et on l'applique à notre application
    try{ UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() ); }
    catch( Exception e ) {}
    
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        // init fichier rxtxSerial.dll
        String version = System.getProperty("sun.arch.data.model");
        if(version.contains("32"))
        {
           Path source = Paths.get("rxtxSerial32.dll");
           Path target = Paths.get("rxtxSerial.dll");
           try
          {
             Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING );
          }
          catch (IOException e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        else
        {
          Path source = Paths.get("rxtxSerial64.dll");
          Path target = Paths.get("rxtxSerial.dll");
          try
         {
           Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING );
         }
         catch (IOException e)
         {
           // TODO Auto-generated catch block
           e.printStackTrace();
         }
        }
        // Lancement de l'application
        IhmGeRaidMain thisClass = new IhmGeRaidMain();
        thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //thisClass.setLocation((dimEcran.width-thisClass.getWidth())/2, (dimEcran.height-thisClass.getHeight())/2);
        thisClass.setExtendedState(JFrame.MAXIMIZED_BOTH);
        thisClass.setVisible(true);
        thisClass.initLog();
        thisClass.initConfig();
        
        try
        {
          thisClass.ouvrir();
          
        }
        catch (Exception e)
        {
          JOptionPane.showMessageDialog(thisClass, "Le fichier que vous essayer d'ouvrir n'est pas un raid valide.");
        }
      }
    });
  }

  /**
   * This is the default constructor
   */
  public IhmGeRaidMain()
  {
    super();
    addComponentListener(new ComponentAdapter() 
    {
      @Override
      public void componentResized(ComponentEvent e) 
      {
        jPanel.setPreferredSize(dim);
      }
    });
    initialize();
    titre = titre + geRaid.version;
    jPanelEtapes.setMinimumSize(new Dimension(360,50));
    jPanelEquipes.setMinimumSize(new Dimension(250,50));
    jComboBoxParcours.setModel(new DefaultComboBoxModel<Parcours>(geRaid.getParcourss().getParcourss()));  
    ButtonGroup bg = new ButtonGroup();
    bg.add(rdbtnEpreuve);
    bg.add(rdbtnEtape);
    bg.add(rdbtnParcours);
    popupMenu = new JPopupMenu();
    popupMenu.add(getMenuItemReduit());
    popupMenu.add(getMenuItemComplet());
    popupMenu.add(getMenuItemResultatSi());
    popupEquipe = new JPopupMenu();
    popupEquipe.add(getMenuItemEquipe());
    dim = getSize();
    
  }
  
  private void initLog()
  {

    //ouvrir le fichier
    File fileLog = new File("GeRaidLog.txt");
    try 
    {
        printStreamLog = new PrintStream(fileLog);
        System.setOut(printStreamLog);
    } 
    catch (FileNotFoundException e) 
    {
        e.printStackTrace();
    }
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(1069, 727);
    this.setMinimumSize(new Dimension(820, 400));
    this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icones/g24.png")));
    this.setJMenuBar(getJJMenuBar());
    this.setContentPane(getJContentPane());
    this.setTitle("GeRaid");
    this.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(java.awt.event.WindowEvent e)
      {
        jMenuItemQuitter.doClick();
      }
    });
  }

  /**
   * This method initializes jContentPane
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane()
  {
    if (jContentPane == null)
    {
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
    }
    return jContentPane;
  }
  
  private void initConfig()
  {
    XmlConfig.lectureConfig(geRaid, geRaid.fichierConfig);
    geRaid.getSiHandler().setPortName(geRaid.getPort());
    geRaid.getSiHandlerPuce().setPortName(geRaid.getPort());
    setTitre();
  }
  
  private void setTitre()
  {
    setTitle(titre +" - " + geRaid.getNomAssociation()+" - " + geRaid.nomRaid);
  }

  public void stationStatus(String status) 
  {
    if(siReaderEnCours)
    {
      if( status.equals("Ready") ) 
      { //$NON-NLS-1$
        jButtonSiReader.setIcon(new ImageIcon(getClass().getResource("/icones/stop.png")));
        return;
      }
    }
    else if(siReaderPuceEnCours)
    {
      if( status.equals("Ready") ) 
      { //$NON-NLS-1$
        jButtonSiReaderPuce.setIcon(new ImageIcon(getClass().getResource("/icones/stop.png")));
        return;
      }
    }
  }

  private JSplitPane getSplitPane() {
    if (splitPane == null) {
    	splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getJTabbedPaneParcours(), getJPanelResultat());
    	splitPane.setOneTouchExpandable(true);
    	splitPane.setContinuousLayout(true);
    	splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    	splitPane.setDividerSize(10);
    	splitPane.setDividerLocation(400);
    }
    return splitPane;
  }
  private JPanel getJPanelResultat() {
    if (jPanelResultat == null) {
    	jPanelResultat = new JPanel();
    	jPanelResultat.setLayout(new BoxLayout(jPanelResultat, BoxLayout.X_AXIS));
    	jPanelResultat.add(getScrollPane());
    }
    return jPanelResultat;
  }
  private JScrollPane getScrollPane() {
    if (scrollPane == null) {
    	scrollPane = new JScrollPane();
      scrollPane.setViewportView(getPanel());
    }
    return scrollPane;
  }
  private JPanel getPanel() {
    if (panel == null) {
    	panel = new JPanel();
    	panel.setLayout(new BorderLayout());
    	panel.add(getPanel_1(), BorderLayout.NORTH);
    	panel.add(getPanel_5(), BorderLayout.CENTER);
    }
    return panel;
  }
  private JPanel getPanel_1() {
    if (panel_1 == null) {
    	panel_1 = new JPanel();
    	panel_1.setPreferredSize(new Dimension(640, 140));
    	FlowLayout fl_panel_1 = new FlowLayout();
    	fl_panel_1.setVgap(0);
    	fl_panel_1.setHgap(0);
    	fl_panel_1.setAlignment(FlowLayout.LEFT);
    	panel_1.setLayout(fl_panel_1);
    	panel_1.add(getPanel_2());
    }
    return panel_1;
  }
  private JPanel getPanel_2() {
    if (panel_2 == null) {
    	panel_2 = new JPanel();
    	panel_2.setPreferredSize(new Dimension(1000, 140));
    	FlowLayout fl_panel_2 = new FlowLayout();
    	fl_panel_2.setAlignment(FlowLayout.LEFT);
    	panel_2.setLayout(fl_panel_2);
    	panel_2.add(getPanel_7());
    	panel_2.add(getPanel_3());
    }
    return panel_2;
  }
  private JPanel getPanel_3() {
    if (panel_3 == null) {
    	panel_3 = new JPanel();
    	FlowLayout fl_panel_3 = new FlowLayout();
    	fl_panel_3.setAlignment(FlowLayout.LEFT);
    	panel_3.setLayout(fl_panel_3);
    	panel_3.add(getPanel_11());
    	panel_3.add(getPanel_13());
    	panel_3.add(getPanel_12());
    	panel_3.add(getPanel_14());
    	panel_3.add(getPanel_15());
    }
    return panel_3;
  }
  private JButton getButton() {
    if (button == null) {
    	button = new JButton();
    	button.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    	    refresh();
    	  }
    	});
    	button.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/reload.png")));
    	button.setToolTipText("Rafraichir les r\u00E9sultats");
    	button.setPreferredSize(new Dimension(30, 30));
    }
    return button;
  }
  private JButton getButton_1() {
    if (button_1 == null) {
    	button_1 = new JButton();
    	button_1.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/print32.png")));
    	button_1.setToolTipText("Imprimer les r\u00E9sultats");
    	button_1.setPreferredSize(new Dimension(30, 30));
    	button_1.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(geRaid.impressionReduite)
          {
            // Récupère un PrinterJob
            PrinterJob job = PrinterJob.getPrinterJob();
            // Définit son contenu à imprimer
            if(rdbtnEpreuve.isSelected())
            {
              job.setPrintable((EpreuveTableModel)table.getModel());
            }
            else if(rdbtnEtape.isSelected())
            {
              job.setPrintable((EtapeTableModel)table.getModel());
            }
            else
            {
              job.setPrintable((ParcoursTableModel)table.getModel());
            }
            // Affiche une boîte de choix d'imprimante
            if (job.printDialog())
            {
               try 
               {
                  // Effectue l'impression
                  job.print();
               } 
               catch (PrinterException ex) 
               {
                  ex.printStackTrace();
               }
            }
          }
          else
          {
            StringBuffer head = new StringBuffer();
            head.append(geRaid.nomRaid);
            head.append(" - " + ((Parcours)jComboBoxParcours.getSelectedItem()).getNom());
            if(rdbtnEtape.isSelected() || rdbtnEpreuve.isSelected())
            {
              head.append(" - "+((Etape)jComboBoxEtapes.getSelectedItem()).getNom());
            }
            if(rdbtnEpreuve.isSelected())
            {
              head.append(" - "+((Epreuve)jListEpreuves.getSelectedValue()).getNom());
            }
            head.append(" - : "+((Categorie)comboBox_2.getSelectedItem()).getNomCourt());
            MessageFormat header = new MessageFormat(head.toString());
            try 
            {
              table.print(JTable.PrintMode.FIT_WIDTH, header, null);
            } 
            catch (java.awt.print.PrinterException ex) 
            {
              System.err.format("Cannot print %s%n", ex.getMessage());
            }
          }
        }
      });
    }
    return button_1;
  }
  private JButton getButton_2() {
    if (button_2 == null) {
    	button_2 = new JButton();
    	button_2.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/export.png")));
    	button_2.setToolTipText("Exporter ces r\u00E9sultats en CSV");
    	button_2.setPreferredSize(new Dimension(30, 30));
    	button_2.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          // On ouvre le navigateur sur le répertoire par défaut
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("csv");
          filter.setDescription("Fichiers csv");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
          
          int returnVal = chooser.showSaveDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            String fichier = Outils.verifExtension(chooser.getSelectedFile().getAbsolutePath(), ".csv");
            if(rdbtnEtape.isSelected())
            {
              CsvResultatEtape.exporter((EtapeTableModel) table.getModel(), fichier);
            }
            else if(rdbtnParcours.isSelected())
            {
              CsvResultatParcours.exporter((ParcoursTableModel) table.getModel(), fichier);
            }
            else
            {
              CsvResultatEpreuve.exporter((EpreuveTableModel) table.getModel(), fichier);
            }
          }
        }
      });
    }
    return button_2;
  }
  private JLabel getLabel_2() {
    if (label_2 == null) {
    	label_2 = new JLabel();
    	label_2.setText("Cat\u00E9gorie : ");
    }
    return label_2;
  }
  private JComboBox<Categorie> getComboBox_2() {
    if (comboBox_2 == null) {
    	comboBox_2 = new JComboBox<Categorie>();
    	comboBox_2.setSelectedIndex(-1);
    	comboBox_2.setPreferredSize(new Dimension(100, 25));
    }
    return comboBox_2;
  }
  private JLabel getLabel_3() {
    if (label_3 == null) {
    	label_3 = new JLabel();
    	label_3.setText("Visualisation :");
    }
    return label_3;
  }
  @SuppressWarnings("rawtypes")
  private JComboBox getComboBox_3() {
    if (comboBox_3 == null) {
    	comboBox_3 = new JComboBox();
    	comboBox_3.setPreferredSize(new Dimension(100, 25));
    }
    return comboBox_3;
  }
  private JPanel getPanel_5() {
    if (panel_5 == null) {
    	panel_5 = new JPanel();
    	panel_5.setPreferredSize(new Dimension(0, 0));
    	panel_5.setLayout(new BorderLayout());
    	panel_5.add(getScrollPane_1(), BorderLayout.CENTER);
    }
    return panel_5;
  }
  private JScrollPane getScrollPane_1() {
    if (scrollPane_1 == null) {
    	scrollPane_1 = new JScrollPane();
    	scrollPane_1.setName("jScrollPane1");
    	scrollPane_1.setViewportView(getTable());
    	scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    }
    return scrollPane_1;
  }
  private JTable getTable() {
    if (table == null) {
    	table = new JTable(); 

    	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      table.setDefaultRenderer(String.class, new MultiLineCellRenderer());
    	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	table.addMouseListener(new MouseAdapter() 
    	{
    	  @Override
    	  public void mouseClicked(MouseEvent me) 
    	  {
    	    popupMenu.setVisible(false);
    	    table.changeSelection(table.rowAtPoint(me.getPoint()), 0, false, false);
    	    if(me.getButton()==MouseEvent.BUTTON3)
    	    {
    	      int x = (int) (IhmGeRaidMain.this.getX() + getMousePosition().getX());
            int y = (int) (IhmGeRaidMain.this.getY() + getMousePosition().getY());
    	      popupMenu.setLocation(x, y);
    	      popupMenu.setVisible(true);
    	    }
    	  }
    	});
    	table.setAutoCreateRowSorter(true);
    	table.getTableHeader().setReorderingAllowed(false);
    }
    return table;
  }

  private void refresh()
  {
    spinnerHeureZero.setValue(geRaid.heureZero.x);
    spinnerMinuteZero.setValue(geRaid.heureZero.y);
    if(jComboBoxParcours.getSelectedIndex()>=0)
    {
      if(jListEpreuves.getSelectedIndex()>=0)
      {
        if(comboBox_3.getSelectedIndex()<0)
        {
          remplirCategories();
        }
        if(rdbtnEtape.isSelected())
        {
          table.setModel(new EtapeTableModel(new ResultatEtape(geRaid, 
              (Etape)jComboBoxEtapes.getSelectedItem(), 
              (Categorie)comboBox_2.getSelectedItem(),
               (TypeVisualisation)comboBox_3.getSelectedItem(),
               chckbxPuceParEquipe.isSelected())));
          TableColumnModel tcm = table.getColumnModel();
          for(int i=0; i<tcm.getColumnCount()-1; i++)
          {
            tcm.getColumn(i).setPreferredWidth(55);
          }
          tcm.getColumn(0).setPreferredWidth(30);
          tcm.getColumn(3).setPreferredWidth(300);
          tcm.getColumn(4).setPreferredWidth(100);
          if(geRaid.afficherNomsEquipiers)
          {
            table.setRowHeight(20 * (((Parcours)jComboBoxParcours.getSelectedItem()).getNbMaxEquipiers()+1));
          }
          else
          {
            table.setRowHeight(20);
          }
        }
        else if(rdbtnParcours.isSelected())
        {
          table.setModel(new ParcoursTableModel(new ResultatParcours(geRaid, 
              (Parcours)jComboBoxParcours.getSelectedItem(), 
              (Categorie)comboBox_2.getSelectedItem(),
               (TypeVisualisationParcours)comboBox_3.getSelectedItem(),
               chckbxPuceParEquipe.isSelected())));
          TableColumnModel tcm = table.getColumnModel();
          for(int i=0; i<tcm.getColumnCount()-1; i++)
          {
            tcm.getColumn(i).setPreferredWidth(55);
          }
          tcm.getColumn(0).setPreferredWidth(30);
          tcm.getColumn(3).setPreferredWidth(300);
          tcm.getColumn(4).setPreferredWidth(100);
          if(geRaid.afficherNomsEquipiers)
          {
            table.setRowHeight(20 * (((Parcours)jComboBoxParcours.getSelectedItem()).getNbMaxEquipiers()+1));
          }
          else
          {
            table.setRowHeight(20);
          }
        }
        else
        {
          table.setModel(new EpreuveTableModel(new ResultatEtape(geRaid, 
              (Etape)jComboBoxEtapes.getSelectedItem(), 
              (Categorie)comboBox_2.getSelectedItem(),
               TypeVisualisation.SIMPLE, chckbxPuceParEquipe.isSelected()), (Epreuve)jListEpreuves.getSelectedValue(), (TypeVisualisationEpreuve)comboBox_3.getSelectedItem()));
          TableColumnModel tcm = table.getColumnModel();
          for(int i=0; i<tcm.getColumnCount()-1; i++)
          {
            tcm.getColumn(i).setPreferredWidth(55);
          }
          tcm.getColumn(0).setPreferredWidth(30);
          tcm.getColumn(3).setPreferredWidth(300);
          tcm.getColumn(4).setPreferredWidth(100);
          if(geRaid.afficherNomsEquipiers)
          {
            table.setRowHeight(20 * (((Parcours)jComboBoxParcours.getSelectedItem()).getNbMaxEquipiers()+1));
          }
          else
          {
            table.setRowHeight(20);
          }
        }
      }
      if(rdbtnEtape.isSelected())
      {
        int nbEquipesEnCourse = ((Parcours)jComboBoxParcours.getSelectedItem()).getNbEquipesPresentes() - table.getRowCount();
        if(nbEquipesEnCourse <=0)
        {
          lblEquipesEnCourse.setText("Toutes les équipes sont arrivées.");
        }
        else if(nbEquipesEnCourse == 1)
        {
          lblEquipesEnCourse.setText("Il reste 1 équipe en course.");
        }
        else
        {
          lblEquipesEnCourse.setText("Il reste " + nbEquipesEnCourse + " équipes en course.");
        }
      }
      else
      {
        lblEquipesEnCourse.setText("");
      }
    }
    else
    {
      table.removeAll();
    }
  }

  @SuppressWarnings("unchecked")
  private void remplirCategories()
  {
    if(comboBox_2.getActionListeners().length>0)
    {
    comboBox_2.removeActionListener(comboBox_2.getActionListeners()[0]);
    comboBox_3.removeActionListener(comboBox_3.getActionListeners()[0]);
    }
    comboBox_2.removeAllItems();
    comboBox_3.removeAllItems();
    comboBox_2.addItem(new Categorie("Toutes", "toutes catégories"));
    for(int i=0; i<geRaid.getCategorie().getSize(); i++)
    {
      comboBox_2.addItem(geRaid.getCategorie().getCategories().get(i));
    }
    comboBox_2.setSelectedIndex(0);
    if(rdbtnEtape.isSelected())
    {
      comboBox_3.setModel(new DefaultComboBoxModel<TypeVisualisation>(TypeVisualisation.values()));
    }
    else if(rdbtnParcours.isSelected())
    {
      comboBox_3.setModel(new DefaultComboBoxModel<TypeVisualisationParcours>(TypeVisualisationParcours.values()));
    }
    else
    {
      comboBox_3.setModel(new DefaultComboBoxModel<TypeVisualisationEpreuve>(TypeVisualisationEpreuve.values()));
    }
    comboBox_3.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent e)
      {
        refresh();
      }
    });
    comboBox_2.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent e)
      {
        refresh();
      }
    });
  }
  private JPanel getJPanel10() {
    if (jPanel10 == null) {
    	jPanel10 = new JPanel();
    	jPanel10.setBorder(new TitledBorder(null, "Epreuves", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	jPanel10.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	FlowLayout fl_jPanel10 = new FlowLayout();
    	fl_jPanel10.setAlignment(FlowLayout.LEFT);
    	jPanel10.setLayout(fl_jPanel10);
    	jPanel10.add(getPanel_8());
    	jPanel10.add(getScrollPane_2());
    	jPanel10.add(getPanel_6());
    	jPanel10.add(getLabel_1());
    }
    return jPanel10;
  }
  private JButton getButton_3() {
    if (button_3 == null) {
    	button_3 = new JButton();
    	button_3.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
          if(jComboBoxEtapes.getSelectedIndex()!=-1)
          {
            IhmEpreuves ihm = new IhmEpreuves(IhmGeRaidMain.this, new Epreuve(), true);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
    	  }
    	});
    	button_3.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/plus.png")));
    	button_3.setToolTipText("Ajouter une \u00E9preuve");
    	button_3.setPreferredSize(new Dimension(30, 30));
    }
    return button_3;
  }
  private JButton getButton_4() {
    if (button_4 == null) {
    	button_4 = new JButton();
    	button_4.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
          if(jListEpreuves.getSelectedIndex()!=-1)
          {
            IhmEpreuves ihm = new IhmEpreuves(IhmGeRaidMain.this, ((Epreuve)jListEpreuves.getSelectedValue()), false);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
    	  }
    	});
    	button_4.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/search.png")));
    	button_4.setToolTipText("Modifier une \u00E9preuve");
    	button_4.setPreferredSize(new Dimension(30, 30));
    }
    return button_4;
  }
  private JButton getButton_5() {
    if (button_5 == null) {
    	button_5 = new JButton();
    	button_5.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
          if(jComboBoxEtapes.getSelectedIndex()!=-1 && 
              ((Etape)jComboBoxEtapes.getSelectedItem()).getEpreuves().getSize()!=0 &&
              jListEpreuves.getSelectedIndex()!=-1)
          {
              StringBuffer message = new StringBuffer("<HTML>Souhaitez-vous supprimer l'épreuve : <b><center>");
              message.append(jListEpreuves.getSelectedValue().toString()+"<center></b></HTML>");
              IhmEpreuveDelete ihm = new IhmEpreuveDelete(IhmGeRaidMain.this, message.toString());
              ihm.setLocationRelativeTo(IhmGeRaidMain.this);
              ihm.setVisible(true);
          }
    	  }
    	});
    	button_5.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/delete.png")));
    	button_5.setToolTipText("Supprimer une \u00E9preuve");
    	button_5.setPreferredSize(new Dimension(30, 30));
    }
    return button_5;
  }
  private JLabel getLabel_1() {
    if (label_1 == null) {
    	label_1 = new JLabel();
    	label_1.setText(" ");
    	label_1.setPreferredSize(new Dimension(5, 30));
    	label_1.setForeground(Color.CYAN);
    	label_1.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
    	label_1.setBackground(Color.CYAN);
    }
    return label_1;
  }
  @SuppressWarnings({ "rawtypes" })
  private JList getJListEpreuves() {
    if (jListEpreuves == null) {
    	jListEpreuves = new JList();
    	jListEpreuves.addMouseListener(new MouseAdapter() 
      {
        @Override
        public void mouseClicked(MouseEvent arg0) 
        {
          if (arg0.getClickCount() == 2) 
          {
            button_4.doClick();
          } 
        }
      });
    	jListEpreuves.addListSelectionListener(new ListSelectionListener() {
    	  @SuppressWarnings({ "unchecked" })
        public void valueChanged(ListSelectionEvent arg0) {

          if(jListEpreuves.getSelectedIndex()!=-1)
          {
            jListBalises.setListData(((Epreuve)jListEpreuves.getSelectedValue()).getBalises().getBalises());
            jListBalises.repaint();
            if(jListBalises.getModel().getSize()>0)
            {
              jListBalises.setSelectedIndex(0);
            }
            else
            {
              jListBalises.setSelectedIndex(-1);
            }
            jLabelNbBalises.setText(((Epreuve)jListEpreuves.getSelectedValue()).getNbBalises()+"");
          }
          else
          {
            jListBalises.setListData(new Vector<Balise>());
            jListBalises.repaint();
            jListBalises.setSelectedIndex(-1);
            jLabelNbBalises.setText("0");
          }
          refresh();
    	  }
    	});
    	jListEpreuves.setVisibleRowCount(4);
    	jListEpreuves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    return jListEpreuves;
  }
  private JPanel getPanel_4() {
    if (panel_4 == null) {
    	panel_4 = new JPanel();
    	panel_4.setLayout(new BorderLayout(0, 0));
    	panel_4.add(getJPanel10(), BorderLayout.SOUTH);
    	panel_4.add(getPanel_9(), BorderLayout.NORTH);
    	panel_4.add(getJPanel4(), BorderLayout.CENTER);
    }
    return panel_4;
  }
  private JScrollPane getScrollPane_2() {
    if (scrollPane_2 == null) {
    	scrollPane_2 = new JScrollPane();
    	scrollPane_2.setPreferredSize(new Dimension(250, 80));
    	scrollPane_2.setViewportView(getJListEpreuves());
    }
    return scrollPane_2;
  }
  private JRadioButton getRdbtnEtape() {
    if (rdbtnEtape == null) {
    	rdbtnEtape = new JRadioButton("Etape");
    	rdbtnEtape.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    	    remplirCategories();
    	    refresh();
    	  }
    	});
    	rdbtnEtape.setSelected(true);
    }
    return rdbtnEtape;
  }
  private JRadioButton getRdbtnParcours() {
    if (rdbtnParcours == null) {
    	rdbtnParcours = new JRadioButton("Parcours");
    	rdbtnParcours.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
          remplirCategories();
          refresh();
    	  }
    	});
    }
    return rdbtnParcours;
  }
  private JMenuItem getJMenuItemSauvegarder() {
    if (jMenuItemSauvegarder == null) {
    	jMenuItemSauvegarder = new JMenuItem("Sauvegarder");
    	jMenuItemSauvegarder.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
          // On ouvre le navigateur
          JFileChooser chooser = new JFileChooser(geRaid.getDossierSauvegarde());
          chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          
          int returnVal = chooser.showOpenDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            geRaid.setDossierSauvegarde(chooser.getSelectedFile().toString());
            sauvegarder();
          }
    	  }
    	});
    	jMenuItemSauvegarder.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/saveusb.png")));
    }
    return jMenuItemSauvegarder;
  }
  private JRadioButton getRdbtnEpreuve() {
    if (rdbtnEpreuve == null) {
    	rdbtnEpreuve = new JRadioButton("Epreuve");
    	rdbtnEpreuve.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
          remplirCategories();
          refresh();
    	  }
    	});
    }
    return rdbtnEpreuve;
  }
  private JButton getButton_6() {
    if (button_6 == null) {
    	button_6 = new JButton("");
    	button_6.setToolTipText("Sauvegarder le raid");
    	button_6.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
    	    jMenuItemSauvegarder.doClick();
    	  }
    	});
    	button_6.setPreferredSize(new Dimension(30, 30));
    	button_6.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/dossier.png")));
    }
    return button_6;
  }
  private JMenuItem getJMenuItemGestionPenalites() {
    if (jMenuItemGestionPenalites == null) {
    	jMenuItemGestionPenalites = new JMenuItem("Gestion des p\u00E9nalit\u00E9s");
    	jMenuItemGestionPenalites.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) {
    	   // if(ihmPenalite == null)
    	   // {
    	      ihmPenalite = new IhmPenalite(geRaid);
    	      ihmPenalite.setLocationRelativeTo(IhmGeRaidMain.this);
    	      ihmPenalite.setVisible(true);
    	   /* }
    	    else
    	    {
    	      ihmPenalite.setVisible(true);
    	    }*/
    	  }
    	});
    }
    return jMenuItemGestionPenalites;
  }
  private JLabel getLblEquipesEnCourse() {
    if (lblEquipesEnCourse == null) {
    	lblEquipesEnCourse = new JLabel("");
    }
    return lblEquipesEnCourse;
  }
  private JMenuItem getJMenuItemVisuParcours() {
    if (jMenuItemVisuParcours == null) {
    	jMenuItemVisuParcours = new JMenuItem("Visualisation des parcours");
    	jMenuItemVisuParcours.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
    	    if(geRaid.getParcourss().getSize()>0)
    	    {
    	      HtmlVisualisationParcours.save(geRaid.toStringParcours(), "Visualisation des parcours.html");
    	    }
    	  }
    	});
    	jMenuItemVisuParcours.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/new.png")));
    }
    return jMenuItemVisuParcours;
  }
  private JMenuItem getMntmImporterDuCsvGeRaid() {
    if (mntmImporterDuCsvGeRaid == null) {
    	mntmImporterDuCsvGeRaid = new JMenuItem("Importer du CSV GeRaid ...");
    	mntmImporterDuCsvGeRaid.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
          // On ouvre le navigateur
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("csv");
          filter.setDescription("Fichiers csv");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
          
          int returnVal = chooser.showOpenDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            String fichier = chooser.getSelectedFile().getAbsolutePath();
            CsvResultatPuce.importer(geRaid, fichier);
            refresh();
          }
    	  }
    	});
    	mntmImporterDuCsvGeRaid.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/open.png")));
    }
    return mntmImporterDuCsvGeRaid;
  }
  private JButton getBtnExporterHtml() {
    if (btnExporterHtml == null) {
    	btnExporterHtml = new JButton("");
    	btnExporterHtml.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
    	    if(jListEpreuves.getSelectedIndex()>-1)
    	    {
            File file = new File(geRaid.getDossierSauvegarde());
            if(file.exists())
            {
              StringBuffer chemin = exportHtml();
              JOptionPane.showMessageDialog(IhmGeRaidMain.this, "Le fichier " + chemin.toString() + " a été enregistré.", 
                  "Exporter le résultat en HTML", JOptionPane.OK_OPTION, new ImageIcon(IhmGeRaidMain.class.getResource("/icones/g32.png")));
            }
            else
            {
              JOptionPane.showMessageDialog(IhmGeRaidMain.this, "Le répertoire de sauvegarde n'existe pas.\nChoisissez un dossier de sauvegarde dans les paramètres de GeRaid.");
            }
    	    }
    	  }
    	});
    	btnExporterHtml.setToolTipText("Exporter ces r\u00E9sultats en HTML");
    	btnExporterHtml.setPreferredSize(new Dimension(30, 30));
    	btnExporterHtml.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/html32.png")));
    }
    return btnExporterHtml;
  }
  private JButton getButtonOcad() {
    if (buttonOcad == null) {
    	buttonOcad = new JButton("");
    	buttonOcad.addActionListener(new ActionListener() 
    	{
    	  @SuppressWarnings("unchecked")
        public void actionPerformed(ActionEvent arg0) 
    	  {
          // On ouvre le navigateur
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("xml");
          filter.setDescription("Fichiers xml");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
          
          int returnVal = chooser.showOpenDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            String fichier = chooser.getSelectedFile().getAbsolutePath();
            XmlOcadCircuit.importer(geRaid,(Epreuve)jListEpreuves.getSelectedValue(), fichier);
            jListBalises.setListData(((Epreuve)jListEpreuves.getSelectedValue()).getBalises().getBalises());
            jListBalises.repaint();
            if(jListBalises.getModel().getSize()>0)
            {
              jListBalises.setSelectedIndex(0);
            }
            else
            {
              jListBalises.setSelectedIndex(-1);
            }
            jLabelNbBalises.setText(((Epreuve)jListEpreuves.getSelectedValue()).getNbBalises()+"");
          }
    	  }
    	});
    	buttonOcad.setToolTipText("Importer \u00E0 partir d'Ocad");
    	buttonOcad.setPreferredSize(new Dimension(30, 30));
    	buttonOcad.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/ocad.png")));
    }
    return buttonOcad;
  }
  
  @SuppressWarnings("unchecked")
  public void majListeEquipes()
  {
    jListEquipes.setListData(((Parcours)jComboBoxParcours.getSelectedItem()).getEquipes().getEquipes());
    if(jListEquipes.getModel().getSize()>-1)
    {
      jListEquipes.setSelectedIndex(0);
    }
  }
  private JButton getButtonImporterEpreuves() {
    if (buttonImporterEpreuves == null) {
    	buttonImporterEpreuves = new JButton();
    	buttonImporterEpreuves.addActionListener(new ActionListener() 
    	{
    	  @SuppressWarnings("unchecked")
        public void actionPerformed(ActionEvent e) 
    	  {
          // On ouvre le navigateur
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("csv");
          filter.setDescription("Fichiers csv");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
          
          int returnVal = chooser.showOpenDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            String fichier = chooser.getSelectedFile().getAbsolutePath();
            CsvEpreuves.importer(geRaid,(Etape)jComboBoxEtapes.getSelectedItem(), fichier);
            jListEpreuves.setListData(((Etape)jComboBoxEtapes.getSelectedItem()).getEpreuves().getEpreuves());
            jListEpreuves.repaint();
            if(jListEpreuves.getModel().getSize()>0)
            {
              jListEpreuves.setSelectedIndex(0);
            }
          }
    	  }
    	});
    	buttonImporterEpreuves.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/import.png")));
    	buttonImporterEpreuves.setToolTipText("Importer les \u00E9preuves en CSV");
    	buttonImporterEpreuves.setPreferredSize(new Dimension(30, 30));
    }
    return buttonImporterEpreuves;
  }
  private JButton getButtonExporterEpreuves() {
    if (buttonExporterEpreuves == null) {
    	buttonExporterEpreuves = new JButton();
    	buttonExporterEpreuves.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
          // On ouvre le navigateur sur le répertoire par défaut
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("csv");
          filter.setDescription("Fichiers csv");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
          
          int returnVal = chooser.showSaveDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            String fichier = Outils.verifExtension(chooser.getSelectedFile().getAbsolutePath(), ".csv");
            CsvEpreuves.exporter(((Etape)jComboBoxEtapes.getSelectedItem()).getEpreuves(), fichier);
          }
    	  }
    	});
    	buttonExporterEpreuves.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/export.png")));
    	buttonExporterEpreuves.setToolTipText("Exporter les \u00E9preuves en CSV");
    	buttonExporterEpreuves.setPreferredSize(new Dimension(30, 30));
    }
    return buttonExporterEpreuves;
  }
  private JPanel getPanel_6() {
    if (panel_6 == null) {
    	panel_6 = new JPanel();
    	panel_6.setPreferredSize(new Dimension(110, 80));
    	panel_6.add(getButton_3());
    	panel_6.add(getButton_4());
    	panel_6.add(getButton_5());
    	panel_6.add(getButtonImporterEpreuves());
    	panel_6.add(getButtonExporterEpreuves());
    }
    return panel_6;
  }

  private JMenuItem getMenuItemReduit() {
    if (MenuItemReduit == null) {
    	MenuItemReduit = new JMenuItem("Afficher les r\u00E9sultats r\u00E9duits de cette \u00E9quipe.");
    	MenuItemReduit.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          popupMenu.setVisible(false);
          int row = table.getSelectedRow();
          ResultatPuce rp = geRaid.getResultatsPuce().getResultatPuce((Parcours)jComboBoxParcours.getSelectedItem(), (Etape)jComboBoxEtapes.getSelectedItem(), (String)table.getValueAt(row, 2));
          if(rp!=null)
          {
            IhmResultatPuce ihm = new IhmResultatPuce(IhmGeRaidMain.this, rp, true, true, true);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
        }
      });
    }
    return MenuItemReduit;
  }
  private JMenuItem getMenuItemComplet() {
    if (MenuItemComplet == null) {
    	MenuItemComplet = new JMenuItem("Afficher les r\u00E9sultats complets de cette \u00E9quipe.");
    	MenuItemComplet.setEnabled(true);
    	MenuItemComplet.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          popupMenu.setVisible(false);
          int row = table.getSelectedRow();
          ResultatPuce rp = geRaid.getResultatsPuce().getResultatPuce((Parcours)jComboBoxParcours.getSelectedItem(), (Etape)jComboBoxEtapes.getSelectedItem(), (String)table.getValueAt(row, 2));
          if(rp!=null)
          {
            IhmResultatPuce ihm = new IhmResultatPuce(IhmGeRaidMain.this, rp, true, true, false);
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
        }
      });
    }
    return MenuItemComplet;
  }

  private JMenuItem getMenuItemResultatSi() {
    if (MenuItemResultatSi == null) {
      MenuItemResultatSi = new JMenuItem("Afficher les r\u00E9sultats SI de cette \u00E9quipe.");
      MenuItemResultatSi.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          popupMenu.setVisible(false);
          int row = table.getSelectedRow();

          if(geRaid.getResultatsPuce().getSize()>0)
          {
            IhmGestionResultatPuce ihm = new IhmGestionResultatPuce(IhmGeRaidMain.this, (Parcours)jComboBoxParcours.getSelectedItem(), (Etape)jComboBoxEtapes.getSelectedItem(), geRaid.getEquipePuce(table.getValueAt(row, 2).toString()));
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
        }
      });
    }
    return MenuItemResultatSi;
  }

  private JMenuItem getMenuItemEquipe() {
    if (MenuItemEquipe == null) {
      MenuItemEquipe = new JMenuItem("Afficher les r\u00E9sultats SI de cette \u00E9quipe.");
      MenuItemEquipe.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          popupEquipe.setVisible(false);
          if(geRaid.getResultatsPuce().getSize()>0)
          {
            IhmGestionResultatPuce ihm = new IhmGestionResultatPuce(IhmGeRaidMain.this, (Parcours)jComboBoxParcours.getSelectedItem(), (Etape)jComboBoxEtapes.getSelectedItem(), (Equipe)jListEquipes.getSelectedValue());
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
          }
        }
      });
    }
    return MenuItemEquipe;
  }
  private JButton getJButtonEquipeMove() {
    if (jButtonEquipeMove == null) {
    	jButtonEquipeMove = new JButton("");
    	jButtonEquipeMove.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
    	    if(jListEquipes.getSelectedIndex()>-1)
    	    {
            IhmDeplacementEquipe ihm = new IhmDeplacementEquipe(IhmGeRaidMain.this, (Parcours)jComboBoxParcours.getSelectedItem(), (Equipe)jListEquipes.getSelectedValue());
            ihm.setLocationRelativeTo(IhmGeRaidMain.this);
            ihm.setVisible(true);
            jLabelNbEquipes.setText(((Parcours)jComboBoxParcours.getSelectedItem()).getNbEquipes()+"");
    	    }
    	  }
    	});
    	jButtonEquipeMove.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/move.png")));
    	jButtonEquipeMove.setToolTipText("D\u00E9placer l'\u00E9quipe vers un autre parcours.");
    	jButtonEquipeMove.setPreferredSize(new Dimension(30, 30));
    }
    return jButtonEquipeMove;
  }
  private JSpinner getSpinnerTempoSauvegarde() {
    if (spinnerTempoSauvegarde == null) {
    	spinnerTempoSauvegarde = new JSpinner();
    	spinnerTempoSauvegarde.setModel(new SpinnerNumberModel(1, 1, 59, 1));
    }
    return spinnerTempoSauvegarde;
  }
  private JLabel getLblNewLabel() {
    if (lblNewLabel == null) {
    	lblNewLabel = new JLabel("mn");
    }
    return lblNewLabel;
  }
  private JButton getBtnNewButton() {
    if (btnNewButton == null) {
    	btnNewButton = new JButton("");
    	btnNewButton.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
    	    if(taskSauvegardeAuto != null)
    	    {
    	      taskSauvegardeAuto.stop();
    	      taskSauvegardeAuto = null;
    	      btnNewButton.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/run.png")));
    	      btnNewButton.setToolTipText("Lancer la sauvegarde automatique.");
    	    }
    	    else
    	    {
    	      taskSauvegardeAuto = new TaskSauvegardeAuto(IhmGeRaidMain.this);
    	      taskSauvegardeAuto.execute();
    	      btnNewButton.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/pause.png")));
    	      btnNewButton.setToolTipText("Arrêter la sauvegarde automatique.");
    	    }
    	  }
    	});
    	btnNewButton.setToolTipText("Lancer la sauvegarde automatique.");
    	btnNewButton.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/run.png")));
    	btnNewButton.setPreferredSize(new Dimension(30, 30));
    	btnNewButton.setSize(new Dimension(30, 30));
    }
    return btnNewButton;
  }
  
  public void sauvegarder()
  {
    StringBuffer chemin = new StringBuffer(geRaid.getDossierSauvegarde());
    chemin.append("/" + geRaid.nomRaid + " ");
    chemin.append(TimeManager.getDateHeureMinuteSeconte());
    chemin.append(".grd");
    XmlRaid.enregistre(geRaid, chemin.toString());
  }
  
  public boolean existeDossierSauvegarde()
  {
    File dossier = new File(geRaid.getDossierSauvegarde());
    return dossier.exists();
  }
  
  public void setSauvegardeOk()
  {
    button_6.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/good.png")));
  }
  
  public void setSauvegardeNok()
  {
    button_6.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/bad.png")));
  }
  
  public void setSauvegardeNormal()
  {
    button_6.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/dossier.png")));
  }
  
  public int getTempoSauvegardeAuto()
  {
    return (int) spinnerTempoSauvegarde.getValue();
  }
  
  public int getTempoExportAuto()
  {
    return (int) spinnerExportAuto.getValue();
  }
  
  public void setExportOk()
  {
    btnExporterHtml.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/good.png")));
  }
  
  public void setExportNok()
  {
    btnExporterHtml.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/bad.png")));
  }
  
  public void setExportNormal()
  {
    btnExporterHtml.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/html32.png")));
  }
  
  public StringBuffer exportHtml()
  {
    StringBuffer chemin = new StringBuffer(geRaid.getDossierSauvegarde());
    StringBuffer entete = new StringBuffer();
    entete.append("<h1>Résultat provisoire " + geRaid.nomRaid + "</h1>");
    chemin.append("/" + geRaid.nomRaid + "_");
      chemin.append(((Parcours)jComboBoxParcours.getSelectedItem()).getNom());
      entete.append("<h2>Parcours : " + ((Parcours)jComboBoxParcours.getSelectedItem()).getNom() + "</h2>");
    if(rdbtnParcours.isSelected())
    {
      chemin.append("_" + ((Categorie)comboBox_2.getSelectedItem()).getNomCourt());
      chemin.append(".html");
      HtmlResultat.exporterParcours((ParcoursTableModel)table.getModel(), chemin.toString(), entete.toString(), table);
    }
    if(rdbtnEtape.isSelected())
    {
      chemin.append("_" + ((Etape)jComboBoxEtapes.getSelectedItem()).getNom());
      entete.append("<h2>Etape : " + ((Etape)jComboBoxEtapes.getSelectedItem()).getNom() + "</h2>");
      chemin.append("_" + ((Categorie)comboBox_2.getSelectedItem()).getNomCourt());
      chemin.append(".html");
      HtmlResultat.exporterEtape((EtapeTableModel)table.getModel(), chemin.toString(), entete.toString(), table);
    }
    if(rdbtnEpreuve.isSelected())
    {
      chemin.append("_" + ((Etape)jComboBoxEtapes.getSelectedItem()).getNom());
      entete.append("<h2>Etape : " + ((Etape)jComboBoxEtapes.getSelectedItem()).getNom() + "</h2>");
      chemin.append("_" + ((Epreuve)jListEpreuves.getSelectedValue()).getNom());
      entete.append("<h2>Epreuve : " + ((Epreuve)jListEpreuves.getSelectedValue()).getNom() + "</h2>");
      chemin.append("_" + ((Categorie)comboBox_2.getSelectedItem()).getNomCourt());
      chemin.append(".html");
      HtmlResultat.exporterEpreuve((EpreuveTableModel)table.getModel(), chemin.toString(), entete.toString(), table);
    }
    return chemin;
  }
  
  public StringBuffer exportPodiumHtml()
  {
    StringBuffer chemin = new StringBuffer(geRaid.getDossierSauvegarde());
    StringBuffer entete = new StringBuffer();
    entete.append("<h1>Podium provisoire " + geRaid.nomRaid + "</h1>");
    chemin.append("/" + geRaid.nomRaid + "_Podium_");
      chemin.append(((Parcours)jComboBoxParcours.getSelectedItem()).getNom());
      entete.append("<h2>Parcours : " + ((Parcours)jComboBoxParcours.getSelectedItem()).getNom() + "</h2>");
    if(rdbtnParcours.isSelected())
    {
      chemin.append("_" + ((Categorie)comboBox_2.getSelectedItem()).getNomCourt());
      chemin.append(".html");
      HtmlResultat.exporterParcoursPodium((ParcoursTableModel)table.getModel(), chemin.toString(), entete.toString(), table, (int) spinnerPodium.getValue());
    }
    if(rdbtnEtape.isSelected())
    {
      chemin.append("_" + ((Etape)jComboBoxEtapes.getSelectedItem()).getNom());
      entete.append("<h2>Etape : " + ((Etape)jComboBoxEtapes.getSelectedItem()).getNom() + "</h2>");
      chemin.append("_" + ((Categorie)comboBox_2.getSelectedItem()).getNomCourt());
      chemin.append(".html");
      HtmlResultat.exporterEtapePodium((EtapeTableModel)table.getModel(), chemin.toString(), entete.toString(), table, (int) spinnerPodium.getValue());
    }
    if(rdbtnEpreuve.isSelected())
    {
      chemin.append("_" + ((Etape)jComboBoxEtapes.getSelectedItem()).getNom());
      entete.append("<h2>Etape : " + ((Etape)jComboBoxEtapes.getSelectedItem()).getNom() + "</h2>");
      chemin.append("_" + ((Epreuve)jListEpreuves.getSelectedValue()).getNom());
      entete.append("<h2>Epreuve : " + ((Epreuve)jListEpreuves.getSelectedValue()).getNom() + "</h2>");
      chemin.append("_" + ((Categorie)comboBox_2.getSelectedItem()).getNomCourt());
      chemin.append(".html");
      HtmlResultat.exporterEpreuvePodium((EpreuveTableModel)table.getModel(), chemin.toString(), entete.toString(), table, (int) spinnerPodium.getValue());
    }
    return chemin;
  }
  
  private JPanel getPanel_7() {
    if (panel_7 == null) {
    	panel_7 = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	panel_7.setPreferredSize(new Dimension(1200, 20));
      panel_7.add(getLblEquipesEnCourse());
    }
    return panel_7;
  }
  private JSpinner getSpinnerExportAuto() {
    if (spinnerExportAuto == null) {
    	spinnerExportAuto = new JSpinner();
    	spinnerExportAuto.setModel(new SpinnerNumberModel(1, 1, 59, 1));
    }
    return spinnerExportAuto;
  }
  private JLabel getLabel() {
    if (label == null) {
    	label = new JLabel("mn");
    }
    return label;
  }
  private JButton getButton_7() {
    if (button_7 == null) {
    	button_7 = new JButton("");
    	button_7.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
          if(taskExportAuto != null)
          {
            taskExportAuto.stop();
            taskExportAuto = null;
            button_7.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/run.png")));
            button_7.setToolTipText("Lancer l'export automatique en HTML.");
          }
          else
          {
            taskExportAuto = new TaskExportAuto(IhmGeRaidMain.this);
            taskExportAuto.execute();
            button_7.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/pause.png")));
            button_7.setToolTipText("Arrêter l'export automatique.");
          }
    	  }
    	});
    	button_7.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/run.png")));
    	button_7.setToolTipText("Lancer l'export automatique en HTML.");
    	button_7.setSize(new Dimension(30, 30));
    	button_7.setPreferredSize(new Dimension(30, 30));
    }
    return button_7;
  }
  
  public void setEnregistrementOk()
  {
    jButtonSave.setIcon(new ImageIcon(getClass().getResource("/icones/good.png")));
  }
  
  public void setEnregistrementNormal()
  {
    jButtonSave.setIcon(new ImageIcon(getClass().getResource("/icones/save32.png")));
  }
  private JSpinner getSpinnerHeureZero() {
    if (spinnerHeureZero == null) {
    	spinnerHeureZero = new JSpinner();
    	spinnerHeureZero.addChangeListener(new ChangeListener() 
    	{
    	  public void stateChanged(ChangeEvent arg0) 
    	  {
    	    geRaid.heureZero.x = (int) spinnerHeureZero.getValue();
    	  }
    	});
    	spinnerHeureZero.setModel(new SpinnerNumberModel(0, 0, 23, 1));
    }
    return spinnerHeureZero;
  }
  private JLabel getLblH() {
    if (lblH == null) {
    	lblH = new JLabel("H");
    }
    return lblH;
  }
  private JSpinner getSpinnerMinuteZero() {
    if (spinnerMinuteZero == null) {
    	spinnerMinuteZero = new JSpinner();
    	spinnerMinuteZero.addChangeListener(new ChangeListener() 
      {
        public void stateChanged(ChangeEvent arg0) 
        {
          geRaid.heureZero.y = (int) spinnerMinuteZero.getValue();
        }
      });
    	spinnerMinuteZero.setModel(new SpinnerNumberModel(0, 0, 59, 1));
    }
    return spinnerMinuteZero;
  }
  private JLabel getLblMn() {
    if (lblMn == null) {
    	lblMn = new JLabel("MN   ");
    }
    return lblMn;
  }
  private JSpinner getSpinnerPodium() {
    if (spinnerPodium == null) {
    	spinnerPodium = new JSpinner();
    	spinnerPodium.setPreferredSize(new Dimension(50, 20));
    	spinnerPodium.setModel(new SpinnerNumberModel(new Integer(3), new Integer(1), null, new Integer(1)));
    }
    return spinnerPodium;
  }
  private JButton getBtnPodium() {
    if (btnPodium == null) {
    	btnPodium = new JButton("");
    	btnPodium.setToolTipText("Exporter le podium en HTML");
    	btnPodium.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
          File file = new File(geRaid.getDossierSauvegarde());
          if(file.exists())
          {
            StringBuffer chemin = exportPodiumHtml();
            JOptionPane.showMessageDialog(IhmGeRaidMain.this, "Le fichier " + chemin.toString() + " a été enregistré.", 
                "Exporter le résultat en HTML", JOptionPane.OK_OPTION, new ImageIcon(IhmGeRaidMain.class.getResource("/icones/g32.png")));
          }
          else
          {
            JOptionPane.showMessageDialog(IhmGeRaidMain.this, "Le répertoire de sauvegarde n'existe pas.\nChoisissez un dossier de sauvegarde dans les paramètres de GeRaid.");
          }
    	  }
    	});
    	btnPodium.setPreferredSize(new Dimension(32, 32));
    	btnPodium.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/medal.png")));
    }
    return btnPodium;
  }
  private JLabel getLblPlaces() {
    if (lblPlaces == null) {
    	lblPlaces = new JLabel("places");
    }
    return lblPlaces;
  }
  private JCheckBox getChckbxPuceParEquipe() {
    if (chckbxPuceParEquipe == null) {
    	chckbxPuceParEquipe = new JCheckBox("Moins bonne puce par \u00E9quipe");
    	chckbxPuceParEquipe.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent e) 
    	  {
    	    refresh();
    	  }
    	});
    }
    return chckbxPuceParEquipe;
  }
  private JButton getButtonDupliquerParcours() {
    if (buttonDupliquerParcours == null) {
    	buttonDupliquerParcours = new JButton();
    	buttonDupliquerParcours.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent e) 
    	  {
    	    if(jComboBoxParcours.getSelectedIndex()>-1)
    	    {
            Parcours p = (Parcours) ((Parcours) jComboBoxParcours.getSelectedItem()).clone();
            geRaid.getParcourss().addParcours(p);
            jComboBoxParcours.setModel(new DefaultComboBoxModel<Parcours>(geRaid.getParcourss().getParcourss()));
            jComboBoxParcours.setSelectedIndex(jComboBoxParcours.getItemCount()-1);
    	    }
    	  }
    	});
    	buttonDupliquerParcours.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/dupliquer.png")));
    	buttonDupliquerParcours.setToolTipText("Dupliquer le parcours");
    	buttonDupliquerParcours.setText("");
    	buttonDupliquerParcours.setPreferredSize(new Dimension(30, 30));
    }
    return buttonDupliquerParcours;
  }
  private JPanel getPanel_8() {
    if (panel_8 == null) {
    	panel_8 = new JPanel();
    	panel_8.setPreferredSize(new Dimension(40, 75));
    	FlowLayout fl_panel_8 = new FlowLayout();
    	fl_panel_8.setAlignment(FlowLayout.LEFT);
    	panel_8.setLayout(fl_panel_8);
    	panel_8.add(getButton_8());
    	panel_8.add(getButton_9());
    }
    return panel_8;
  }
  private JButton getButton_8() {
    if (button_8 == null) {
    	button_8 = new JButton();
    	button_8.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent e) 
    	  {
          if(jComboBoxEtapes.getSelectedIndex()>=0 && jListEpreuves.getModel().getSize()>0)
          {
            ((Etape)jComboBoxEtapes.getSelectedItem()).getEpreuves().upEpreuve((Epreuve)jListEpreuves.getSelectedValue());
            jListEpreuves.repaint();
            jListEpreuves.setSelectedIndex(jListEpreuves.getSelectedIndex()-1);
          }
    	  }
    	});
    	button_8.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/up.png")));
    	button_8.setToolTipText("Monter une \u00E9preuve");
    	button_8.setPreferredSize(new Dimension(30, 30));
    }
    return button_8;
  }
  private JButton getButton_9() {
    if (button_9 == null) {
    	button_9 = new JButton();
    	button_9.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent e) 
    	  {
          if(jComboBoxEtapes.getSelectedIndex()>=0 && jListEpreuves.getModel().getSize()>0)
          {
            ((Etape)jComboBoxEtapes.getSelectedItem()).getEpreuves().downEpreuve((Epreuve)jListEpreuves.getSelectedValue());
            jListEpreuves.repaint();
            jListEpreuves.setSelectedIndex(jListEpreuves.getSelectedIndex()+1);
          }
    	  }
    	});
    	button_9.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/down.png")));
    	button_9.setToolTipText("Descendre une \u00E9preuve");
    	button_9.setPreferredSize(new Dimension(30, 30));
    }
    return button_9;
  }
  private JPanel getPanel_9() {
    if (panel_9 == null) {
    	panel_9 = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) panel_9.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	panel_9.setBorder(new TitledBorder(null, "Parcours", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_9.add(getJComboBoxParcours());
    	panel_9.add(getJButtonParcoursPlus());
    	panel_9.add(getJButtonParcoursEdit());
    	panel_9.add(getJButtonParcoursMoins());
    	panel_9.add(getButtonDupliquerParcours());
    }
    return panel_9;
  }
  private JPanel getPanel_10() {
    if (panel_10 == null) {
    	panel_10 = new JPanel();
    	panel_10.setBorder(new TitledBorder(null, "GeRaid", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_10.add(getJButtonSave());
    	panel_10.add(getButton_6());
    	panel_10.add(getButton_10());
    	panel_10.add(getSpinnerTempoSauvegarde());
    	panel_10.add(getLblNewLabel());
    	panel_10.add(getBtnNewButton());
    }
    return panel_10;
  }
  private JButton getButton_10() {
    if (button_10 == null) {
    	button_10 = new JButton();
    	button_10.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent e) 
    	  {
          // On ouvre le navigateur sur le répertoire par défaut
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("csv");
          filter.setDescription("Fichiers csv");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(geRaid.getDossierDefault()));
          
          int returnVal = chooser.showSaveDialog(IhmGeRaidMain.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            String fichier = Outils.verifExtension(chooser.getSelectedFile().getAbsolutePath(), ".csv");
            CsvEquipes.exporter(geRaid, fichier);
          }
    	  }
    	});
    	button_10.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/export.png")));
    	button_10.setToolTipText("Exporter les \u00E9quipes du raid en CSV");
    	button_10.setPreferredSize(new Dimension(30, 30));
    }
    return button_10;
  }

  
  class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {

    /**
     * 
     */
    private static final long serialVersionUID = -5699535840057997829L;

    public MultiLineCellRenderer() {
      setLineWrap(true);
      setWrapStyleWord(true);
      setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
      if (isSelected) {
        setForeground(table.getSelectionForeground());
        setBackground(table.getSelectionBackground());
      } else {
        setForeground(table.getForeground());
        setBackground(table.getBackground());
      }
      setFont(table.getFont());
      if (hasFocus) {
        setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        if (table.isCellEditable(row, column)) {
          setForeground(UIManager.getColor("Table.focusCellForeground"));
          setBackground(UIManager.getColor("Table.focusCellBackground"));
        }
      } else {
        setBorder(new EmptyBorder(1, 2, 1, 2));
      }
      setText((value == null) ? "" : value.toString());
      return this;
    }
  }
  private JPanel getPanel_11() {
    if (panel_11 == null) {
    	panel_11 = new JPanel();
    	panel_11.setBorder(new TitledBorder(null, "Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_11.setPreferredSize(new Dimension(100, 105));
    	FlowLayout flowLayout = (FlowLayout) panel_11.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	panel_11.add(getRdbtnEpreuve());
    	panel_11.add(getRdbtnEtape());
    	panel_11.add(getRdbtnParcours());
    }
    return panel_11;
  }
  private JPanel getPanel_12() {
    if (panel_12 == null) {
    	panel_12 = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) panel_12.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	panel_12.setPreferredSize(new Dimension(200, 80));
    	panel_12.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Visualisation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    	panel_12.add(getChckbxPuceParEquipe());
    	panel_12.add(getChckbxAfficherLesNoms());
    }
    return panel_12;
  }
  private JPanel getPanel_13() {
    if (panel_13 == null) {
    	panel_13 = new JPanel();
    	panel_13.setBorder(new TitledBorder(null, "Filtre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_13.setPreferredSize(new Dimension(190, 90));
    	FlowLayout flowLayout = (FlowLayout) panel_13.getLayout();
    	flowLayout.setAlignment(FlowLayout.RIGHT);
    	panel_13.add(getLabel_2());
    	panel_13.add(getComboBox_2());
    	panel_13.add(getLabel_3());
    	panel_13.add(getComboBox_3());
    }
    return panel_13;
  }
  private JPanel getPanel_14() {
    if (panel_14 == null) {
    	panel_14 = new JPanel();
    	panel_14.setBorder(new TitledBorder(null, "Divers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_14.add(getButton());
    	panel_14.add(getButton_1());
    	panel_14.add(getButton_2());
    }
    return panel_14;
  }
  private JPanel getPanel_15() {
    if (panel_15 == null) {
    	panel_15 = new JPanel();
    	panel_15.setBorder(new TitledBorder(null, "Export HTML", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_15.setPreferredSize(new Dimension(150, 90));
    	panel_15.add(getBtnExporterHtml());
    	panel_15.add(getSpinnerExportAuto());
    	panel_15.add(getLabel());
    	panel_15.add(getButton_7());
    	panel_15.add(getSpinnerPodium());
    	panel_15.add(getLblPlaces());
    	panel_15.add(getBtnPodium());
    }
    return panel_15;
  }
  private JCheckBox getChckbxAfficherLesNoms() {
    if (chckbxAfficherLesNoms == null) {
    	chckbxAfficherLesNoms = new JCheckBox("Afficher les noms des \u00E9quipiers");
    	chckbxAfficherLesNoms.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent e) 
    	  {
    	    if(chckbxAfficherLesNoms.isSelected())
    	    {
    	      geRaid.afficherNomsEquipiers = true;
    	    }
    	    else
    	    {
            geRaid.afficherNomsEquipiers = false;
    	    }
    	    refresh();
    	  }
    	});
    }
    return chckbxAfficherLesNoms;
  }
  private JButton getBtnSort() {
    if (btnSort == null) {
    	btnSort = new JButton("");
    	btnSort.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent e) 
    	  {
          try
          {
            typeTriCourant = (TypeTri) JOptionPane.showInputDialog(IhmGeRaidMain.this, "Trier par :" ,
                "Tri des équipes", JOptionPane.OK_CANCEL_OPTION, null, TypeTri.values(), TypeTri.values()[0]);
          }
          catch (NullPointerException es)
          {
            // TODO: handle exception
          }
          if(typeTriCourant != null)
          {
            ((Parcours)jComboBoxParcours.getSelectedItem()).getEquipes().trierEquipes(typeTriCourant);
            jListEquipes.repaint();
          }
    	  }
    	});
    	btnSort.setToolTipText("Trier les \u00E9quipes.");
    	btnSort.setPreferredSize(new Dimension(32, 32));
    	btnSort.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/filtre.png")));
    }
    return btnSort;
  }
  private JMenu getMnNewMenu() {
    if (mnNewMenu == null) {
    	mnNewMenu = new JMenu("Rechercher");
    	mnNewMenu.add(getJMenuItemRechercherPuce());
    	mnNewMenu.add(getMntmRechercherEquipe());
    }
    return mnNewMenu;
  }
  private JMenuItem getMntmRechercherEquipe() {
    if (mntmRechercherEquipe == null) {
    	mntmRechercherEquipe = new JMenuItem();
    	mntmRechercherEquipe.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent e) 
    	  {
          IhmRechercheEquipe ihm = new IhmRechercheEquipe(IhmGeRaidMain.this);
          ihm.setLocationRelativeTo(IhmGeRaidMain.this);
          ihm.setVisible(true);
    	  }
    	});
    	mntmRechercherEquipe.setIcon(new ImageIcon(IhmGeRaidMain.class.getResource("/icones/find.png")));
    	mntmRechercherEquipe.setText("Rechercher Equipe ...");
    }
    return mntmRechercherEquipe;
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
