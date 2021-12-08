/**
 * 
 */
package ihm;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import to.Balise;
import to.Epreuve;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

/**
 * <P>
 * Titre : IhmEquipes.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class IhmBalise extends JDialog
{
  private Balise balise = null;
  private boolean creation = true;
  private boolean defaut = false;
  private IhmGeRaidMain owner = null;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JSpinner jSpinnerCode = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;

  private JPanel jPanel = null;

  private JPanel jPanel1 = null;
  private JPanel jPanelBonif = null;
  private JLabel jLabel2 = null;
  private JLabel jLabel4 = null;
  private JSpinner jSpinnerPoints = null;
  private JSpinner jSpinnerMinutes = null;
  private JPanel jPanelChrono = null;
  private JCheckBox jCheckBoxArret = null;
  private JCheckBox jCheckBoxDemarrage = null;
  private JPanel jPanel2 = null;
  private JSpinner jSpinnerSecondes = null;
  private JRadioButton jRadioButtonBonif = null;
  private JPanel jPanelBonifPm = null;
  private JPanel jPanel21 = null;
  private JLabel jLabel21 = null;
  private JSpinner jSpinnerPointsPm = null;
  private JSpinner jSpinnerHeuresPm = null;
  private JSpinner jSpinnerMinutesPm = null;
  private JSpinner jSpinnerSecondesPm = null;
  private JRadioButton jRadioButtonBonifPm = null;
  private JRadioButton jRadioButtonPenalitePm = null;
  private JPanel jPanelTemps = null;
  private JLabel jLabel3 = null;
  private JSpinner jSpinnerHeures = null;
  private JRadioButton jRadioButtonPenalite = null;
  private JPanel jPanelTempsPm = null;
  private JLabel jLabel6 = null;
  private JLabel jLabel41 = null;
  private JLabel jLabel42 = null;
  private JLabel jLabel43 = null;
  private JLabel jLabel44 = null;
  private JLabel jLabel45 = null;

  /**
   * @param owner
   */
  public IhmBalise(IhmGeRaidMain owner, Balise b, boolean creation, boolean defaut)
  {
    super(owner);
    this.owner = owner;
    this.balise = b;
    this.creation = creation;
    this.defaut = defaut;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    if(this.creation)
    {
      setTitle("Création d'une balise");
      balise.setPoints(owner.geRaid.pointsDefaut);
      balise.setTemps(owner.geRaid.tempsDefaut);
      balise.setPointsPm(owner.geRaid.pointsPmDefaut);
      balise.setTempsPm(owner.geRaid.tempsPmDefaut);
    }
    else
    {
      setTitle("Modification d'une balise");
    }
    jSpinnerCode.setValue(balise.getNumero());
    
    jCheckBoxArret.setSelected(balise.isArreterChrono());
    jCheckBoxDemarrage.setSelected(balise.isDemarerChrono());
    jSpinnerPoints.setValue(Math.abs(balise.getPoints()));
    jSpinnerPointsPm.setValue(Math.abs(balise.getPointsPm()));
    initBonifPenalite();
    initTemps();
    if(defaut)
    {
      jLabel.setVisible(false);
      jSpinnerCode.setVisible(false);
      jPanelChrono.setVisible(false);
      setTitle("Paramètres par défaut d'une balise");
    }
  }
  
  private void initBonifPenalite()
  {
    boolean bonif = true;
    if(balise.getPoints()<0 || balise.getTemps()>0)
    {
      bonif = false;
    }
    jRadioButtonBonif.setSelected(bonif);
    jRadioButtonPenalite.setSelected(!bonif);

    bonif = false;
    if(balise.getPointsPm()>0 || balise.getTempsPm()<0)
    {
      bonif = true;
    }
    jRadioButtonBonifPm.setSelected(bonif);
    jRadioButtonPenalitePm.setSelected(!bonif);
  }
  
  private void initTemps()
  {
    int heures = (int) Math.floor(Math.abs(balise.getTemps())/3600);
    int minutes = (int) Math.floor((Math.abs(balise.getTemps())- heures*3600)/60);
    int secondes = Math.abs(balise.getTemps())- heures*3600 - minutes*60;
    jSpinnerHeures.setValue(heures);
    jSpinnerMinutes.setValue(minutes);
    jSpinnerSecondes.setValue(secondes);

    int heuresPm = (int) Math.floor(Math.abs(balise.getTempsPm())/3600);
    int minutesPm = (int) Math.floor((Math.abs(balise.getTempsPm())- heuresPm*3600)/60);
    int secondesPm = Math.abs(balise.getTempsPm())- heuresPm*3600 - minutesPm*60;
    jSpinnerHeuresPm.setValue(heuresPm);
    jSpinnerMinutesPm.setValue(minutesPm);
    jSpinnerSecondesPm.setValue(secondesPm);
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(304, 501);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Balises");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setContentPane(getJContentPane());
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
      jLabel = new JLabel();
      jLabel.setText("Code :  ");
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(getJPanel(), BorderLayout.NORTH);
      jContentPane.add(getJPanel1(), BorderLayout.CENTER);
    }
    return jContentPane;
  }

  /**
   * This method initializes jTextFieldNom	
   * 	
   * @return javax.swing.JTextField	
   */
  private JSpinner getJTextFieldNumero()
  {
    if (jSpinnerCode == null)
    {
      jSpinnerCode = new JSpinner(new SpinnerNumberModel(31, 31, 500, 1));
    }
    return jSpinnerCode;
  }

  /**
   * This method initializes jButtonOk	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonOk()
  {
    if (jButtonOk == null)
    {
      jButtonOk = new JButton();
      jButtonOk.setIcon(new ImageIcon(getClass().getResource("/icones/ok64.png")));
      jButtonOk.setToolTipText("Validation des données");
      jButtonOk.setPreferredSize(new Dimension(50, 50));
      jButtonOk.addActionListener(new java.awt.event.ActionListener()
      {
        @SuppressWarnings("unchecked")
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(defaut)
          {
            if(jRadioButtonBonif.isSelected())
            {
              owner.geRaid.pointsDefaut = (Integer)jSpinnerPoints.getValue();
            }
            else
            {
              owner.geRaid.pointsDefaut = -(Integer)jSpinnerPoints.getValue();
            }
            owner.geRaid.tempsDefaut = getTemps();
            if(jRadioButtonBonifPm.isSelected())
            {
              owner.geRaid.pointsPmDefaut = (Integer)jSpinnerPointsPm.getValue();
            }
            else
            {
              owner.geRaid.pointsPmDefaut = -(Integer)jSpinnerPointsPm.getValue();
            }
            owner.geRaid.tempsPmDefaut = getTempsPm();
            dispose();
          }
          else
          {
            int num = (Integer)jSpinnerCode.getValue();
            
            if(creation)
            {
              balise.setNumero(num);
              balise.setArreterChrono(jCheckBoxArret.isSelected());
              balise.setDemarerChrono(jCheckBoxDemarrage.isSelected());
              if(jRadioButtonBonif.isSelected())
              {
                balise.setPoints((Integer)jSpinnerPoints.getValue());
              }
              else
              {
                balise.setPoints(-(Integer)jSpinnerPoints.getValue());
              }
              balise.setTemps(getTemps());
              if(jRadioButtonBonifPm.isSelected())
              {
                balise.setPointsPm((Integer)jSpinnerPointsPm.getValue());
              }
              else
              {
                balise.setPointsPm(-(Integer)jSpinnerPointsPm.getValue());
              }
              balise.setTempsPm(getTempsPm());
              if(((Epreuve)owner.jListEpreuves.getSelectedValue()).getBalises().addBalise(balise))
              {
                owner.jListBalises.setListData(((Epreuve)owner.jListEpreuves.getSelectedValue()).getBalises().getBalises());
                owner.jListBalises.setSelectedIndex(((Epreuve)owner.jListEpreuves.getSelectedValue()).getBalises().getSize()-1);
                dispose();
              }
            }
            else 
            {
              balise.setNumero(num);
              balise.setArreterChrono(jCheckBoxArret.isSelected());
              balise.setDemarerChrono(jCheckBoxDemarrage.isSelected());
              if(jRadioButtonBonif.isSelected())
              {
                balise.setPoints((Integer)jSpinnerPoints.getValue());
              }
              else
              {
                balise.setPoints(-(Integer)jSpinnerPoints.getValue());
              }
              balise.setTemps(getTemps());
              if(jRadioButtonBonifPm.isSelected())
              {
                balise.setPointsPm((Integer)jSpinnerPointsPm.getValue());
              }
              else
              {
                balise.setPointsPm(-(Integer)jSpinnerPointsPm.getValue());
              }
              balise.setTempsPm(getTempsPm());
              owner.jListBalises.repaint();
              dispose();
            }
          }
        }
      });
    }
    return jButtonOk;
  }
  
  private int getTemps()
  {
    int retour = (Integer)jSpinnerHeures.getValue()*3600+
    (Integer)jSpinnerMinutes.getValue()*60 +
    (Integer)jSpinnerSecondes.getValue();
    if(jRadioButtonBonif.isSelected())
    {
      retour = retour*(-1);
    }
    return retour;
  }
  
  private int getTempsPm()
  {
    int retour = (Integer)jSpinnerHeuresPm.getValue()*3600+
    (Integer)jSpinnerMinutesPm.getValue()*60 +
    (Integer)jSpinnerSecondesPm.getValue();
    if(jRadioButtonBonifPm.isSelected())
    {
      retour = retour*(-1);
    }
    return retour;
  }

  /**
   * This method initializes jButtonAnnuler	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonAnnuler()
  {
    if (jButtonAnnuler == null)
    {
      jButtonAnnuler = new JButton();
      jButtonAnnuler.setPreferredSize(new Dimension(50, 50));
      jButtonAnnuler.setToolTipText("Retour à l'application");
      jButtonAnnuler.setIcon(new ImageIcon(getClass().getResource("/icones/back.png")));
      jButtonAnnuler.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          dispose();
        }
      });
    }
    return jButtonAnnuler;
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
      FlowLayout flowLayout1 = new FlowLayout();
      flowLayout1.setAlignment(FlowLayout.LEFT);
      flowLayout1.setHgap(10);
      jPanel = new JPanel();
      jPanel.setPreferredSize(new Dimension(1091, 410));
      jPanel.setLayout(flowLayout1);
      jPanel.add(jLabel, null);
      jPanel.add(getJTextFieldNumero(), null);
      jPanel.add(getJPanelBonif(), null);
      jPanel.add(getJPanelBonifPm(), null);
      jPanel.add(getJPanelChrono(), null);
    }
    return jPanel;
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
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setHgap(50);
      jPanel1 = new JPanel();
      jPanel1.setLayout(flowLayout);
      jPanel1.add(getJButtonOk(), null);
      jPanel1.add(getJButtonAnnuler(), null);
    }
    return jPanel1;
  }

  /**
   * This method initializes jPanelBonif	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelBonif()
  {
    if (jPanelBonif == null)
    {
      FlowLayout flowLayout3 = new FlowLayout();
      flowLayout3.setAlignment(FlowLayout.LEFT);
      jLabel4 = new JLabel();
      jLabel4.setText(" mn");
      jLabel2 = new JLabel();
      jLabel2.setText("Points : ");
      jLabel2.setPreferredSize(new Dimension(60, 16));
      jPanelBonif = new JPanel();
      jPanelBonif.setLayout(flowLayout3);
      jPanelBonif.setBorder(BorderFactory.createTitledBorder(null, "Si découvert", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
      jPanelBonif.setPreferredSize(new Dimension(275, 140));
      jPanelBonif.add(getJRadioButtonBonif(), null);
      jPanelBonif.add(getJRadioButtonPenalite(), null);
      jPanelBonif.add(getJPanel2(), null);
      jPanelBonif.add(getJPanelTemps(), null);
      ButtonGroup bg = new ButtonGroup();
      bg.add(jRadioButtonBonif);
      bg.add(jRadioButtonPenalite);
    }
    return jPanelBonif;
  }

  /**
   * This method initializes jSpinnerPoints	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JSpinner getJSpinnerPoints()
  {
    if (jSpinnerPoints == null)
    {
      jSpinnerPoints = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 10));
    }
    return jSpinnerPoints;
  }

  /**
   * This method initializes jSpinnerMinutes	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JSpinner getJSpinnerMinutes()
  {
    if (jSpinnerMinutes == null)
    {
      jSpinnerMinutes = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
      jSpinnerMinutes.setPreferredSize(new Dimension(40, 20));
    }
    return jSpinnerMinutes;
  }

  /**
   * This method initializes jPanelChrono	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelChrono()
  {
    if (jPanelChrono == null)
    {
      FlowLayout flowLayout4 = new FlowLayout();
      flowLayout4.setAlignment(FlowLayout.LEFT);
      jPanelChrono = new JPanel();
      jPanelChrono.setBorder(BorderFactory.createTitledBorder(null, "Gel du chronomètre", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
      jPanelChrono.setEnabled(true);
      jPanelChrono.setLayout(flowLayout4);
      jPanelChrono.setPreferredSize(new Dimension(220, 65));
      jPanelChrono.add(getJCheckBoxArret(), null);
      jPanelChrono.add(getJCheckBoxDemarrage(), null);
    }
    return jPanelChrono;
  }

  /**
   * This method initializes jCheckBoxArret	
   * 	
   * @return javax.swing.JCheckBox	
   */
  private JCheckBox getJCheckBoxArret()
  {
    if (jCheckBoxArret == null)
    {
      jCheckBoxArret = new JCheckBox();
      jCheckBoxArret.setText("D\u00E9but du gel");
      jCheckBoxArret.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jCheckBoxArret.isSelected())
          {
            jCheckBoxDemarrage.setSelected(false);
          }
        }
      });
    }
    return jCheckBoxArret;
  }

  /**
   * This method initializes jCheckBoxDemarrage	
   * 	
   * @return javax.swing.JCheckBox	
   */
  private JCheckBox getJCheckBoxDemarrage()
  {
    if (jCheckBoxDemarrage == null)
    {
      jCheckBoxDemarrage = new JCheckBox();
      jCheckBoxDemarrage.setText("Arr\u00EAt du gel");
      jCheckBoxDemarrage.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jCheckBoxDemarrage.isSelected())
          {
            jCheckBoxArret.setSelected(false);
          }
        }
      });
    }
    return jCheckBoxDemarrage;
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
      jPanel2.setLayout(new FlowLayout());
      jPanel2.add(jLabel2, null);
      jPanel2.add(getJSpinnerPoints(), null);
    }
    return jPanel2;
  }

  /**
   * This method initializes jSpinnerSecondes	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerSecondes()
  {
    if (jSpinnerSecondes == null)
    {
      jSpinnerSecondes = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
      jSpinnerSecondes.setPreferredSize(new Dimension(40, 20));
    }
    return jSpinnerSecondes;
  }

  /**
   * This method initializes jRadioButtonBonif	
   * 	
   * @return javax.swing.JRadioButton	
   */
  private JRadioButton getJRadioButtonBonif()
  {
    if (jRadioButtonBonif == null)
    {
      jRadioButtonBonif = new JRadioButton();
      jRadioButtonBonif.setText("Bonification");
    }
    return jRadioButtonBonif;
  }

  /**
   * This method initializes jPanelBonifPm	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelBonifPm()
  {
    if (jPanelBonifPm == null)
    {
      FlowLayout flowLayout31 = new FlowLayout();
      flowLayout31.setAlignment(FlowLayout.LEFT);
      jPanelBonifPm = new JPanel();
      jPanelBonifPm.setPreferredSize(new Dimension(275, 140));
      jPanelBonifPm.setBorder(BorderFactory.createTitledBorder(null, "Si poste manquant", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
      jPanelBonifPm.setLayout(flowLayout31);
      jPanelBonifPm.add(getJRadioButtonBonifPm(), null);
      jPanelBonifPm.add(getJRadioButtonPenalitePm(), null);
      jPanelBonifPm.add(getJPanel21(), null);
      jPanelBonifPm.add(getJPanelTempsPm(), null);
      ButtonGroup bg = new ButtonGroup();
      bg.add(jRadioButtonBonifPm);
      bg.add(jRadioButtonPenalitePm);
    }
    return jPanelBonifPm;
  }

  /**
   * This method initializes jPanel21	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanel21()
  {
    if (jPanel21 == null)
    {
      jLabel21 = new JLabel();
      jLabel21.setPreferredSize(new Dimension(60, 16));
      jLabel21.setText("Points : ");
      jPanel21 = new JPanel();
      jPanel21.setLayout(new FlowLayout());
      jPanel21.add(jLabel21, null);
      jPanel21.add(getJSpinnerPointsPm(), null);
    }
    return jPanel21;
  }

  /**
   * This method initializes jSpinnerPointsPm	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerPointsPm()
  {
    if (jSpinnerPointsPm == null)
    {
      jSpinnerPointsPm = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 10));
    }
    return jSpinnerPointsPm;
  }

  /**
   * This method initializes jSpinnerHeuresPm	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerHeuresPm()
  {
    if (jSpinnerHeuresPm == null)
    {
      jSpinnerHeuresPm = new JSpinner(new SpinnerNumberModel(0, 0, 12, 1));
      jSpinnerHeuresPm.setPreferredSize(new Dimension(40, 20));
    }
    return jSpinnerHeuresPm;
  }

  /**
   * This method initializes jSpinnerMinutesPm	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerMinutesPm()
  {
    if (jSpinnerMinutesPm == null)
    {
      jSpinnerMinutesPm = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
      jSpinnerMinutesPm.setPreferredSize(new Dimension(40, 20));
    }
    return jSpinnerMinutesPm;
  }

  /**
   * This method initializes jSpinnerSecondesPm	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerSecondesPm()
  {
    if (jSpinnerSecondesPm == null)
    {
      jSpinnerSecondesPm = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
      jSpinnerSecondesPm.setPreferredSize(new Dimension(40, 20));
    }
    return jSpinnerSecondesPm;
  }

  /**
   * This method initializes jRadioButtonBonifPm	
   * 	
   * @return javax.swing.JRadioButton	
   */
  private JRadioButton getJRadioButtonBonifPm()
  {
    if (jRadioButtonBonifPm == null)
    {
      jRadioButtonBonifPm = new JRadioButton();
      jRadioButtonBonifPm.setText("Bonification");
    }
    return jRadioButtonBonifPm;
  }

  /**
   * This method initializes jRadioButtonPenalitePm	
   * 	
   * @return javax.swing.JRadioButton	
   */
  private JRadioButton getJRadioButtonPenalitePm()
  {
    if (jRadioButtonPenalitePm == null)
    {
      jRadioButtonPenalitePm = new JRadioButton();
      jRadioButtonPenalitePm.setText("Pénalité");
    }
    return jRadioButtonPenalitePm;
  }

  /**
   * This method initializes jPanelTemps	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelTemps()
  {
    if (jPanelTemps == null)
    {
      jLabel43 = new JLabel();
      jLabel43.setText("s");
      jLabel42 = new JLabel();
      jLabel42.setText("h");
      jLabel3 = new JLabel();
      jLabel3.setText("Temps :");
      FlowLayout flowLayout6 = new FlowLayout();
      flowLayout6.setAlignment(FlowLayout.LEFT);
      jPanelTemps = new JPanel();
      jPanelTemps.setLayout(flowLayout6);
      jPanelTemps.setPreferredSize(new Dimension(250, 30));
      jPanelTemps.add(jLabel3, null);
      jPanelTemps.add(getJSpinnerHeures(), null);
      jPanelTemps.add(jLabel42, null);
      jPanelTemps.add(getJSpinnerMinutes(), null);
      jPanelTemps.add(jLabel4, null);
      jPanelTemps.add(getJSpinnerSecondes(), null);
      jPanelTemps.add(jLabel43, null);
    }
    return jPanelTemps;
  }

  /**
   * This method initializes jSpinnerHeures	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerHeures()
  {
    if (jSpinnerHeures == null)
    {
      jSpinnerHeures = new JSpinner(new SpinnerNumberModel(0, 0, 12, 1));
      jSpinnerHeures.setPreferredSize(new Dimension(40, 20));
    }
    return jSpinnerHeures;
  }

  /**
   * This method initializes jRadioButtonPenalite	
   * 	
   * @return javax.swing.JRadioButton	
   */
  private JRadioButton getJRadioButtonPenalite()
  {
    if (jRadioButtonPenalite == null)
    {
      jRadioButtonPenalite = new JRadioButton();
      jRadioButtonPenalite.setText("Pénalité");
    }
    return jRadioButtonPenalite;
  }

  /**
   * This method initializes jPanelTempsPm	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelTempsPm()
  {
    if (jPanelTempsPm == null)
    {
      jLabel45 = new JLabel();
      jLabel45.setText("s");
      jLabel44 = new JLabel();
      jLabel44.setText("h");
      jLabel41 = new JLabel();
      jLabel41.setText(" mn");
      jLabel6 = new JLabel();
      jLabel6.setText("Temps :");
      FlowLayout flowLayout7 = new FlowLayout();
      flowLayout7.setAlignment(FlowLayout.LEFT);
      jPanelTempsPm = new JPanel();
      jPanelTempsPm.setLayout(flowLayout7);
      jPanelTempsPm.add(jLabel6, null);
      jPanelTempsPm.add(getJSpinnerHeuresPm(), null);
      jPanelTempsPm.add(jLabel44, null);
      jPanelTempsPm.add(getJSpinnerMinutesPm(), null);
      jPanelTempsPm.add(jLabel41, null);
      jPanelTempsPm.add(getJSpinnerSecondesPm(), null);
      jPanelTempsPm.add(jLabel45, null);
    }
    return jPanelTempsPm;
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
