/**
 * 
 */
package ihm;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import to.Etape;
import to.Parcours;
import to.TypeDepart;
import to.TypeLimite;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.util.Date;

import javax.swing.JComboBox;

import outils.TimeManager;

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
public class IhmEtapes extends JDialog
{
  private Etape etape = null;
  private boolean creation = true;
  private IhmGeRaidMain owner = null;
  //private GregorianCalendar gc = new GregorianCalendar();
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JTextField jTextFieldNom = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;

  private JPanel jPanel = null;

  private JPanel jPanel1 = null;
  private JCheckBox jCheckBoxFini = null;
  private JLabel jLabelmessage = null;
  private JPanel jPanelType = null;
  private JLabel jLabel1 = null;
  private JComboBox<TypeDepart> jComboBoxType = null;
  private JPanel jPanelHeureDepart = null;
  private JLabel jLabel2 = null;
  private JSpinner jSpinnerHeure = null;
  private JLabel jLabel3 = null;
  private JSpinner jSpinnerMinute = null;
  private JLabel jLabel4 = null;
  private JPanel jPanelTypeLimite = null;
  private JLabel jLabel11 = null;
  private JComboBox<TypeLimite> jComboBoxTypeLimite = null;
  private JPanel jPanelHeureLimiteTemps = null;
  private JLabel jLabel21 = null;
  private JSpinner jSpinnerHeureLimiteTemps = null;
  private JLabel jLabel31 = null;
  private JSpinner jSpinnerMinuteLimiteTemps = null;
  private JLabel jLabel41 = null;
  private JPanel jPanelHeureLimiteHoraire = null;
  private JLabel jLabel211 = null;
  private JSpinner jSpinnerHeureLimiteHoraire = null;
  private JLabel jLabel311 = null;
  private JSpinner jSpinnerMinuteLimiteHoraire = null;
  private JLabel jLabel411 = null;
  private JPanel jPanelPenalite = null;
  private JLabel jLabel212 = null;
  private JSpinner jSpinnerPenalitePoints = null;
  private JLabel jLabel312 = null;
  private JSpinner jSpinnerTemps = null;
  private JLabel jLabel412 = null;
  private JSpinner jSpinnerPenaliteTemps;
  private JLabel lblMnPar;
  private JPanel jPanelGel;
  private JCheckBox chckbxGel;

  /**
   * @param owner
   */
  public IhmEtapes(IhmGeRaidMain owner, Etape e, boolean creation)
  {
    super(owner);
    this.owner = owner;
    this.etape = e;
    this.creation = creation;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    if(this.creation)
    {
      setTitle("Création d'une étape");
    }
    else
    {
      setTitle("Modification d'une étape");
    }
    jComboBoxType.setModel(new DefaultComboBoxModel<TypeDepart>(TypeDepart.values()));
    jComboBoxType.setSelectedItem(e.getType());
    switch (e.getType())
    {
      case GROUPE:
        jSpinnerHeure.setValue(TimeManager.getHeures(e.getHeureDepart()));
        jSpinnerMinute.setValue(TimeManager.getMinutes(e.getHeureDepart()));
        break;
      case BOITIER:
        jPanelHeureDepart.setVisible(false);

      default:
        break;
    }
    jTextFieldNom.setText(etape.getNom());
    jCheckBoxFini.setSelected(etape.isFini());
    jComboBoxTypeLimite.setModel(new DefaultComboBoxModel<TypeLimite>(TypeLimite.values()));
    jComboBoxTypeLimite.setSelectedItem(e.getTypeLimite());
    jSpinnerPenalitePoints.setValue(etape.getPenalite());
    jSpinnerPenaliteTemps.setValue(etape.getPenaliteTemps());
    jSpinnerTemps.setValue(etape.getIntervalPenalite());
    switch (e.getTypeLimite())
    {
      case AVECLIMITEHORAIRE:
        jPanelHeureLimiteHoraire.setVisible(true);
        jPanelHeureLimiteTemps.setVisible(false);
        jPanelGel.setVisible(false);
        jSpinnerHeureLimiteHoraire.setValue(TimeManager.getHeures(e.getHeureLimite()));
        jSpinnerMinuteLimiteHoraire.setValue(TimeManager.getMinutes(e.getHeureLimite()));
        break;
      case AVECLIMITETEMPS:
        jPanelHeureLimiteHoraire.setVisible(false);
        jPanelHeureLimiteTemps.setVisible(true);
        jPanelGel.setVisible(true);
        jSpinnerHeureLimiteTemps.setValue(TimeManager.getHeures(e.getTempsLimite()));
        jSpinnerMinuteLimiteTemps.setValue(TimeManager.getMinutes(e.getTempsLimite()));
        chckbxGel.setSelected(e.isGelDansLimiteTemps());
        break;
      case SANSLIMITE:
        jPanelHeureLimiteHoraire.setVisible(false);
        jPanelHeureLimiteTemps.setVisible(false);
        jPanelGel.setVisible(false);

      default:
        break;
    }
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(363, 408);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Etapes");
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
      jLabel.setText("Nom :  ");
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
  private JTextField getJTextFieldNom()
  {
    if (jTextFieldNom == null)
    {
      jTextFieldNom = new JTextField();
      jTextFieldNom.setPreferredSize(new Dimension(150, 20));
    }
    return jTextFieldNom;
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
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jTextFieldNom.getText().trim().compareTo("")==0)
          {
            owner.bip.play();
            StringBuffer msg = new StringBuffer("<html>Vous devez donner un nom à cette étape.<br>");
            jLabelmessage.setText(msg.toString());
            return;
          }
          else if(creation)
          {
            etape.setNom(jTextFieldNom.getText());
            etape.setFini(jCheckBoxFini.isSelected());
            etape.setType((TypeDepart)jComboBoxType.getSelectedItem());
            if(etape.getType().equals(TypeDepart.GROUPE))
            {
              etape.setHeureDepart(new Date((Integer)jSpinnerHeure.getValue()*3600000 +
                  (Integer)jSpinnerMinute.getValue()*60000));
            }
            etape.setTypeLimite((TypeLimite)jComboBoxTypeLimite.getSelectedItem());
            if(etape.getTypeLimite().equals(TypeLimite.AVECLIMITEHORAIRE))
            {
              etape.setHeureLimite(new Date((Integer)jSpinnerHeureLimiteHoraire.getValue()*3600000 +
                  (Integer)jSpinnerMinuteLimiteHoraire.getValue()*60000));
            }
            if(etape.getTypeLimite().equals(TypeLimite.AVECLIMITETEMPS))
            {
              etape.setTempsLimite(new Date((Integer)jSpinnerHeureLimiteTemps.getValue()*3600000 +
                  (Integer)jSpinnerMinuteLimiteTemps.getValue()*60000));
              etape.setGelDansLimiteTemps(chckbxGel.isSelected());
            }
            if(!etape.getTypeLimite().equals(TypeLimite.SANSLIMITE))
            {
              etape.setPenalite((Integer)jSpinnerPenalitePoints.getValue());
              etape.setPenaliteTemps((Integer)jSpinnerPenaliteTemps.getValue());
              etape.setIntervalPenalite((Integer)jSpinnerTemps.getValue());
            }
            if(!((Parcours)owner.jComboBoxParcours.getSelectedItem()).existeEtape(jTextFieldNom.getText(), etape))
            {
              ((Parcours)owner.jComboBoxParcours.getSelectedItem()).getEtapes().addEtape(etape);
              owner.jComboBoxEtapes.setModel(new DefaultComboBoxModel<Etape>(((Parcours)owner.jComboBoxParcours.getSelectedItem()).getEtapes().getEtapes()));
              owner.jComboBoxEtapes.setSelectedIndex(((Parcours)owner.jComboBoxParcours.getSelectedItem()).getEtapes().getSize()-1);
              dispose();
            }
            else
            {
              owner.bip.play();
              StringBuffer msg = new StringBuffer("<html>Ce nom existe déjà pour une étape de ce parcours.<br>");
              msg.append("Changer le nom pour créer cette étape.</html>");
              jLabelmessage.setText(msg.toString());
            }
          }
          else if(!creation) 
          {
            if(!((Parcours)owner.jComboBoxParcours.getSelectedItem()).existeEtape(jTextFieldNom.getText(), etape))
              {
                etape.setNom(jTextFieldNom.getText());
                etape.setFini(jCheckBoxFini.isSelected());
                etape.setType((TypeDepart)jComboBoxType.getSelectedItem());
                if(etape.getType().equals(TypeDepart.GROUPE))
                {
                  etape.setHeureDepart(new Date((Integer)jSpinnerHeure.getValue()*3600000 +
                      (Integer)jSpinnerMinute.getValue()*60000));
                }
                etape.setTypeLimite((TypeLimite)jComboBoxTypeLimite.getSelectedItem());
                if(etape.getTypeLimite().equals(TypeLimite.AVECLIMITEHORAIRE))
                {
                  etape.setHeureLimite(new Date((Integer)jSpinnerHeureLimiteHoraire.getValue()*3600000 +
                      (Integer)jSpinnerMinuteLimiteHoraire.getValue()*60000));
                }
                if(etape.getTypeLimite().equals(TypeLimite.AVECLIMITETEMPS))
                {
                  etape.setTempsLimite(new Date((Integer)jSpinnerHeureLimiteTemps.getValue()*3600000 +
                      (Integer)jSpinnerMinuteLimiteTemps.getValue()*60000));
                  etape.setGelDansLimiteTemps(chckbxGel.isSelected());
                }
                if(!etape.getTypeLimite().equals(TypeLimite.SANSLIMITE))
                {
                  etape.setPenalite((Integer)jSpinnerPenalitePoints.getValue());
                  etape.setPenaliteTemps((Integer)jSpinnerPenaliteTemps.getValue());
                  etape.setIntervalPenalite((Integer)jSpinnerTemps.getValue());
                }
                owner.jComboBoxEtapes.repaint();
                dispose();
              }
            else
            {
              owner.bip.play();
              StringBuffer msg = new StringBuffer("<html>Ce nom existe déjà pour une étape de ce parcours.<br>");
              msg.append("Changer le nom pour modifier cette étape.</html>");
              jLabelmessage.setText(msg.toString());
            }
          }
        }
      });
    }
    return jButtonOk;
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
      jLabelmessage = new JLabel();
      jLabelmessage.setText("");
      jLabelmessage.setForeground(Color.red);
      jLabelmessage.setPreferredSize(new Dimension(340, 50));
      FlowLayout flowLayout1 = new FlowLayout();
      flowLayout1.setAlignment(FlowLayout.LEFT);
      flowLayout1.setHgap(10);
      jPanel = new JPanel();
      jPanel.setPreferredSize(new Dimension(1091, 320));
      jPanel.setLayout(flowLayout1);
      jPanel.add(jLabel, null);
      jPanel.add(getJTextFieldNom(), null);
      jPanel.add(getJCheckBoxFini(), null);
      jPanel.add(getJPanelType(), null);
      jPanel.add(getJPanelHeureDepart(), null);
      jPanel.add(getJPanelTypeLimite(), null);
      jPanel.add(getJPanelHeureLimiteTemps(), null);
      jPanel.add(getJPanelHeureLimiteHoraire(), null);
      jPanel.add(getJPanelPenalite(), null);
      jPanel.add(getJPanelGel());
      jPanel.add(jLabelmessage, null);
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
   * This method initializes jCheckBoxFini	
   * 	
   * @return javax.swing.JCheckBox	
   */
  private JCheckBox getJCheckBoxFini()
  {
    if (jCheckBoxFini == null)
    {
      jCheckBoxFini = new JCheckBox();
      jCheckBoxFini.setText("Etape terminée");
    }
    return jCheckBoxFini;
  }

  /**
   * This method initializes jPanelType	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelType()
  {
    if (jPanelType == null)
    {
      jLabel1 = new JLabel();
      jLabel1.setText("Type de départ : ");
      FlowLayout flowLayout2 = new FlowLayout();
      flowLayout2.setAlignment(FlowLayout.LEFT);
      jPanelType = new JPanel();
      jPanelType.setLayout(flowLayout2);
      jPanelType.setPreferredSize(new Dimension(230, 35));
      jPanelType.add(jLabel1, null);
      jPanelType.add(getJComboBoxType(), null);
    }
    return jPanelType;
  }

  /**
   * This method initializes jComboBoxType	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<TypeDepart> getJComboBoxType()
  {
    if (jComboBoxType == null)
    {
      jComboBoxType = new JComboBox<TypeDepart>();
      jComboBoxType.setPreferredSize(new Dimension(120, 25));
      jComboBoxType.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          switch ((TypeDepart)jComboBoxType.getSelectedItem())
          {
            case GROUPE:
              jPanelHeureDepart.setVisible(true);
              break;
            case BOITIER:
              jPanelHeureDepart.setVisible(false);
              break;

            default:
              break;
          }
        }
      });
    }
    return jComboBoxType;
  }

  /**
   * This method initializes jPanelHeureDepart	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelHeureDepart()
  {
    if (jPanelHeureDepart == null)
    {
      jLabel4 = new JLabel();
      jLabel4.setText("M");
      jLabel3 = new JLabel();
      jLabel3.setText("H");
      jLabel2 = new JLabel();
      jLabel2.setText("Heure de départ : ");
      FlowLayout flowLayout3 = new FlowLayout();
      flowLayout3.setAlignment(FlowLayout.LEFT);
      jPanelHeureDepart = new JPanel();
      jPanelHeureDepart.setPreferredSize(new Dimension(230, 35));
      jPanelHeureDepart.setLayout(flowLayout3);
      jPanelHeureDepart.add(jLabel2, null);
      jPanelHeureDepart.add(getJSpinnerHeure(), null);
      jPanelHeureDepart.add(jLabel3, null);
      jPanelHeureDepart.add(getJSpinnerMinute(), null);
      jPanelHeureDepart.add(jLabel4, null);
    }
    return jPanelHeureDepart;
  }

  /**
   * This method initializes jSpinnerHeure	
   * 	
   * @return javax.swing.JTextField	
   */
  private JSpinner getJSpinnerHeure()
  {
    if (jSpinnerHeure == null)
    {
      jSpinnerHeure = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
    }
    return jSpinnerHeure;
  }

  /**
   * This method initializes jSpinnerMinute	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerMinute()
  {
    if (jSpinnerMinute == null)
    {
      jSpinnerMinute = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
    }
    return jSpinnerMinute;
  }

  /**
   * This method initializes jPanelTypeLimite	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelTypeLimite()
  {
    if (jPanelTypeLimite == null)
    {
      jLabel11 = new JLabel();
      jLabel11.setText("Limite : ");
      FlowLayout flowLayout21 = new FlowLayout();
      flowLayout21.setAlignment(FlowLayout.LEFT);
      jPanelTypeLimite = new JPanel();
      jPanelTypeLimite.setPreferredSize(new Dimension(230, 35));
      jPanelTypeLimite.setLayout(flowLayout21);
      jPanelTypeLimite.add(jLabel11, null);
      jPanelTypeLimite.add(getJComboBoxTypeLimite(), null);
    }
    return jPanelTypeLimite;
  }

  /**
   * This method initializes jComboBoxTypeLimite	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<TypeLimite> getJComboBoxTypeLimite()
  {
    if (jComboBoxTypeLimite == null)
    {
      jComboBoxTypeLimite = new JComboBox<TypeLimite>();
      jComboBoxTypeLimite.setPreferredSize(new Dimension(170, 25));
      jComboBoxTypeLimite.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          switch ((TypeLimite)jComboBoxTypeLimite.getSelectedItem())
          {
            case SANSLIMITE:
              jPanelHeureLimiteHoraire.setVisible(false);
              jPanelHeureLimiteTemps.setVisible(false);
              jPanelPenalite.setVisible(false);
              jPanelGel.setVisible(false);
              break;
            case AVECLIMITEHORAIRE:
              jPanelHeureLimiteHoraire.setVisible(true);
              jPanelHeureLimiteTemps.setVisible(false);
              jPanelPenalite.setVisible(true);
              jPanelGel.setVisible(false);
              break;
            case AVECLIMITETEMPS:
              jPanelHeureLimiteHoraire.setVisible(false);
              jPanelHeureLimiteTemps.setVisible(true);
              jPanelPenalite.setVisible(true);
              jPanelGel.setVisible(true);
              break;

            default:
              break;
          }
        }
      });
    }
    return jComboBoxTypeLimite;
  }

  /**
   * This method initializes jPanelHeureLimiteTemps	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelHeureLimiteTemps()
  {
    if (jPanelHeureLimiteTemps == null)
    {
      jLabel41 = new JLabel();
      jLabel41.setText("M");
      jLabel31 = new JLabel();
      jLabel31.setText("H");
      jLabel21 = new JLabel();
      jLabel21.setText("Temps limite :");
      FlowLayout flowLayout31 = new FlowLayout();
      flowLayout31.setAlignment(FlowLayout.LEFT);
      jPanelHeureLimiteTemps = new JPanel();
      jPanelHeureLimiteTemps.setPreferredSize(new Dimension(230, 35));
      jPanelHeureLimiteTemps.setLayout(flowLayout31);
      jPanelHeureLimiteTemps.add(jLabel21, null);
      jPanelHeureLimiteTemps.add(getJSpinnerHeureLimiteTemps(), null);
      jPanelHeureLimiteTemps.add(jLabel31, null);
      jPanelHeureLimiteTemps.add(getJSpinnerMinuteLimiteTemps(), null);
      jPanelHeureLimiteTemps.add(jLabel41, null);
    }
    return jPanelHeureLimiteTemps;
  }

  /**
   * This method initializes jSpinnerHeureLimiteTemps	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerHeureLimiteTemps()
  {
    if (jSpinnerHeureLimiteTemps == null)
    {
      jSpinnerHeureLimiteTemps = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
    }
    return jSpinnerHeureLimiteTemps;
  }

  /**
   * This method initializes jSpinnerMinuteLimiteTemps	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerMinuteLimiteTemps()
  {
    if (jSpinnerMinuteLimiteTemps == null)
    {
      jSpinnerMinuteLimiteTemps = new JSpinner(
          new SpinnerNumberModel(0, 0, 59, 1));
    }
    return jSpinnerMinuteLimiteTemps;
  }

  /**
   * This method initializes jPanelHeureLimiteHoraire	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelHeureLimiteHoraire()
  {
    if (jPanelHeureLimiteHoraire == null)
    {
      jLabel411 = new JLabel();
      jLabel411.setText("M");
      jLabel311 = new JLabel();
      jLabel311.setText("H");
      jLabel211 = new JLabel();
      jLabel211.setText("Heure limite : ");
      FlowLayout flowLayout311 = new FlowLayout();
      flowLayout311.setAlignment(FlowLayout.LEFT);
      jPanelHeureLimiteHoraire = new JPanel();
      jPanelHeureLimiteHoraire.setPreferredSize(new Dimension(230, 35));
      jPanelHeureLimiteHoraire.setVisible(false);
      jPanelHeureLimiteHoraire.setLayout(flowLayout311);
      jPanelHeureLimiteHoraire.add(jLabel211, null);
      jPanelHeureLimiteHoraire.add(getJSpinnerHeureLimiteHoraire(), null);
      jPanelHeureLimiteHoraire.add(jLabel311, null);
      jPanelHeureLimiteHoraire.add(getJSpinnerMinuteLimiteHoraire(), null);
      jPanelHeureLimiteHoraire.add(jLabel411, null);
    }
    return jPanelHeureLimiteHoraire;
  }

  /**
   * This method initializes jSpinnerHeureLimiteHoraire	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerHeureLimiteHoraire()
  {
    if (jSpinnerHeureLimiteHoraire == null)
    {
      jSpinnerHeureLimiteHoraire = new JSpinner(new SpinnerNumberModel(0, 0, 48, 1));
    }
    return jSpinnerHeureLimiteHoraire;
  }

  /**
   * This method initializes jSpinnerMinuteLimiteHoraire	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerMinuteLimiteHoraire()
  {
    if (jSpinnerMinuteLimiteHoraire == null)
    {
      jSpinnerMinuteLimiteHoraire = new JSpinner(new SpinnerNumberModel(0, 0, 59,
          1));
    }
    return jSpinnerMinuteLimiteHoraire;
  }

  /**
   * This method initializes jPanelPenalite	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelPenalite()
  {
    if (jPanelPenalite == null)
    {
      jLabel412 = new JLabel();
      jLabel412.setText("MN");
      jLabel312 = new JLabel();
      jLabel312.setText("points et plus ");
      jLabel212 = new JLabel();
      jLabel212.setText("Moins");
      FlowLayout flowLayout312 = new FlowLayout();
      flowLayout312.setAlignment(FlowLayout.LEFT);
      jPanelPenalite = new JPanel();
      jPanelPenalite.setPreferredSize(new Dimension(340, 35));
      jPanelPenalite.setLayout(flowLayout312);
      jPanelPenalite.add(jLabel212, null);
      jPanelPenalite.add(getJSpinnerPoints(), null);
      jPanelPenalite.add(jLabel312, null);
      jPanelPenalite.add(getJSpinnerPenaliteTemps());
      jPanelPenalite.add(getLblMnPar());
      jPanelPenalite.add(getJSpinnerTemps(), null);
      jPanelPenalite.add(jLabel412, null);
    }
    return jPanelPenalite;
  }

  /**
   * This method initializes jSpinnerPoints	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerPoints()
  {
    if (jSpinnerPenalitePoints == null)
    {
      jSpinnerPenalitePoints = new JSpinner(new SpinnerNumberModel(0, 0, 500, 10));
    }
    return jSpinnerPenalitePoints;
  }

  /**
   * This method initializes jSpinnerTemps	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerTemps()
  {
    if (jSpinnerTemps == null)
    {
      jSpinnerTemps = new JSpinner(new SpinnerNumberModel(1, 1, 60, 1));
    }
    return jSpinnerTemps;
  }

  private JSpinner getJSpinnerPenaliteTemps() {
    if (jSpinnerPenaliteTemps == null) {
    	jSpinnerPenaliteTemps = new JSpinner();
    	jSpinnerPenaliteTemps.setPreferredSize(new Dimension(47, 20));
    	jSpinnerPenaliteTemps.setMinimumSize(new Dimension(47, 20));
    	jSpinnerPenaliteTemps.setModel(new SpinnerNumberModel(0, 0, 60, 1));
    }
    return jSpinnerPenaliteTemps;
  }
  private JLabel getLblMnPar() {
    if (lblMnPar == null) {
    	lblMnPar = new JLabel("mn par ");
    }
    return lblMnPar;
  }
  private JPanel getJPanelGel() {
    if (jPanelGel == null) {
    	jPanelGel = new JPanel();
    	jPanelGel.add(getChckbxGel());
    }
    return jPanelGel;
  }
  private JCheckBox getChckbxGel() {
    if (chckbxGel == null) {
    	chckbxGel = new JCheckBox("Prendre en compte le gel du chrono dans le temps limite.");
    }
    return chckbxGel;
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
