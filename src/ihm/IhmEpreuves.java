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

import to.Epreuve;
import to.Etape;
import to.TypeLimite;

import java.awt.Color;
import java.util.Date;

import javax.swing.JComboBox;

import outils.TimeManager;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
public class IhmEpreuves extends JDialog
{
  private Epreuve epreuve = null;
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
  private JLabel jLabelmessage = null;
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
  private JSpinner jSpinnerPoints = null;
  private JLabel jLabel312 = null;
  private JSpinner jSpinnerTemps = null;
  private JLabel jLabel412 = null;
  private JSpinner jSpinnerPenaliteTemps;
  private JLabel lblMnPar;
  private JPanel jPanelChrono;
  private JCheckBox chckbxChronomtragePartir;
  private JCheckBox chckbxFinDuChronomtrage;
  private JCheckBox chckbxCourseEnLigne;
  private JCheckBox chckbxChronomtrageDeLpreuve;
  private JCheckBox chckbxEpreuveEffectueAprs;
  private JPanel panelMultiplicateur;
  private JLabel lblNewLabel;
  private JSpinner spinnerMulti;
  private JSpinner jSpinnerSecondeLimiteTemps;
  private JLabel lblS;
  private JCheckBox chckbxPostesApresEpreuvePrecedente;
  private JCheckBox chckbxPostesAvantEpreuveSuivante;

  /**
   * @param owner
   */
  public IhmEpreuves(IhmGeRaidMain owner, Epreuve e, boolean creation)
  {
    super(owner);
    this.owner = owner;
    this.epreuve = e;
    this.creation = creation;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    if(this.creation)
    {
      setTitle("Création d'une épreuve");
    }
    else
    {
      setTitle("Modification d'une épreuve");
    }

    jTextFieldNom.setText(epreuve.getNom());
    spinnerMulti.setValue(epreuve.getMultiplicateurTemps());
    jComboBoxTypeLimite.setModel(new DefaultComboBoxModel<TypeLimite>(TypeLimite.values()));
    jComboBoxTypeLimite.setSelectedItem(e.getTypeLimite());
    jSpinnerPoints.setValue(epreuve.getPenalite());
    jSpinnerPenaliteTemps.setValue(epreuve.getPenaliteTemps());
    jSpinnerTemps.setValue(epreuve.getIntervalPenalite());
    switch (e.getTypeLimite())
    {
      case AVECLIMITEHORAIRE:
        jPanelHeureLimiteHoraire.setVisible(true);
        jPanelHeureLimiteTemps.setVisible(false);
        jSpinnerHeureLimiteHoraire.setValue(TimeManager.getHeures(e.getHeureLimite()));
        jSpinnerMinuteLimiteHoraire.setValue(TimeManager.getMinutes(e.getHeureLimite()));
        break;
      case AVECLIMITETEMPS:
        jPanelHeureLimiteHoraire.setVisible(false);
        jPanelHeureLimiteTemps.setVisible(true);
        jSpinnerHeureLimiteTemps.setValue(TimeManager.getHeures(e.getTempsLimite()));
        jSpinnerMinuteLimiteTemps.setValue(TimeManager.getMinutes(e.getTempsLimite()));
        jSpinnerSecondeLimiteTemps.setValue(TimeManager.getSecondes(e.getTempsLimite()));
        break;
      case SANSLIMITE:
        jPanelHeureLimiteHoraire.setVisible(false);
        jPanelHeureLimiteTemps.setVisible(false);

      default:
        break;
    }
    chckbxChronomtrageDeLpreuve.setSelected(epreuve.isChrono());
    chckbxCourseEnLigne.setSelected(epreuve.isLigne());
    chckbxEpreuveEffectueAprs.setSelected(epreuve.isHorsChrono());
    chckbxChronomtragePartir.setSelected(epreuve.isDebutEpreuve());
    chckbxFinDuChronomtrage.setSelected(epreuve.isFinEpreuve());
    chckbxPostesApresEpreuvePrecedente.setSelected(epreuve.isApresEpreuvePrecedente());
    chckbxPostesAvantEpreuveSuivante.setSelected(epreuve.isAvantEpreuveSuivante());
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(398, 596);
    this.setModal(true);
    this.setTitle("Epreuves");
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
        @SuppressWarnings("unchecked")
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
            epreuve.setNom(jTextFieldNom.getText());
            epreuve.setMultiplicateurTemps((int) spinnerMulti.getValue());
            epreuve.setTypeLimite((TypeLimite)jComboBoxTypeLimite.getSelectedItem());
            epreuve.setChrono(chckbxChronomtrageDeLpreuve.isSelected());
            epreuve.setLigne(chckbxCourseEnLigne.isSelected());
            epreuve.setHorsChrono(chckbxEpreuveEffectueAprs.isSelected());
            epreuve.setDebutEpreuve(chckbxChronomtragePartir.isSelected());
            epreuve.setFinEpreuve(chckbxFinDuChronomtrage.isSelected());
            epreuve.setApresEpreuvePrecedente(chckbxPostesApresEpreuvePrecedente.isSelected());
            epreuve.setAvantEpreuveSuivante(chckbxPostesAvantEpreuveSuivante.isSelected());
            if(epreuve.getTypeLimite().equals(TypeLimite.AVECLIMITEHORAIRE))
            {
              epreuve.setHeureLimite(new Date((Integer)jSpinnerHeureLimiteHoraire.getValue()*3600000 +
                  (Integer)jSpinnerMinuteLimiteHoraire.getValue()*60000));
            }
            if(epreuve.getTypeLimite().equals(TypeLimite.AVECLIMITETEMPS))
            {
              epreuve.setTempsLimite(new Date((Integer)jSpinnerHeureLimiteTemps.getValue()*3600000 +
                  (Integer)jSpinnerMinuteLimiteTemps.getValue()*60000 + (Integer)jSpinnerSecondeLimiteTemps.getValue()*1000));
            }
            if(!epreuve.getTypeLimite().equals(TypeLimite.SANSLIMITE))
            {
              epreuve.setPenalite((Integer)jSpinnerPoints.getValue());
              epreuve.setPenaliteTemps((Integer)jSpinnerPenaliteTemps.getValue());
              epreuve.setIntervalPenalite((Integer)jSpinnerTemps.getValue());
            }
            if(!((Etape)owner.jComboBoxEtapes.getSelectedItem()).existeEpreuve(jTextFieldNom.getText(), epreuve))
            {
              ((Etape)owner.jComboBoxEtapes.getSelectedItem()).getEpreuves().addEpreuve(epreuve);
              owner.jListEpreuves.setListData(((Etape)owner.jComboBoxEtapes.getSelectedItem()).getEpreuves().getEpreuves());
              owner.jListEpreuves.setSelectedIndex(((Etape)owner.jComboBoxEtapes.getSelectedItem()).getEpreuves().getSize()-1);
              dispose();
            }
            else
            {
              owner.bip.play();
              StringBuffer msg = new StringBuffer("<html>Ce nom existe déjà pour une épreuve de cette étape.<br>");
              msg.append("Changer le nom pour créer cette épreuve.</html>");
              jLabelmessage.setText(msg.toString());
            }
          }
          else if(!creation) 
          {
            if(!((Etape)owner.jComboBoxEtapes.getSelectedItem()).existeEpreuve(jTextFieldNom.getText(), epreuve))
              {
                epreuve.setNom(jTextFieldNom.getText());
                epreuve.setMultiplicateurTemps((int) spinnerMulti.getValue());
                epreuve.setTypeLimite((TypeLimite)jComboBoxTypeLimite.getSelectedItem());
                epreuve.setChrono(chckbxChronomtrageDeLpreuve.isSelected());
                epreuve.setLigne(chckbxCourseEnLigne.isSelected());
                epreuve.setHorsChrono(chckbxEpreuveEffectueAprs.isSelected());
                epreuve.setDebutEpreuve(chckbxChronomtragePartir.isSelected());
                epreuve.setFinEpreuve(chckbxFinDuChronomtrage.isSelected());
                epreuve.setApresEpreuvePrecedente(chckbxPostesApresEpreuvePrecedente.isSelected());
                epreuve.setAvantEpreuveSuivante(chckbxPostesAvantEpreuveSuivante.isSelected());
                if(epreuve.getTypeLimite().equals(TypeLimite.AVECLIMITEHORAIRE))
                {
                  epreuve.setHeureLimite(new Date((Integer)jSpinnerHeureLimiteHoraire.getValue()*3600000 +
                      (Integer)jSpinnerMinuteLimiteHoraire.getValue()*60000));
                }
                if(epreuve.getTypeLimite().equals(TypeLimite.AVECLIMITETEMPS))
                {
                  epreuve.setTempsLimite(new Date((Integer)jSpinnerHeureLimiteTemps.getValue()*3600000 +
                      (Integer)jSpinnerMinuteLimiteTemps.getValue()*60000 + (Integer)jSpinnerSecondeLimiteTemps.getValue()*1000));
                }
                if(!epreuve.getTypeLimite().equals(TypeLimite.SANSLIMITE))
                {
                  epreuve.setPenalite((Integer)jSpinnerPoints.getValue());
                  epreuve.setPenaliteTemps((Integer)jSpinnerPenaliteTemps.getValue());
                  epreuve.setIntervalPenalite((Integer)jSpinnerTemps.getValue());
                }
                owner.jComboBoxEtapes.repaint();
                owner.jListEpreuves.setListData(((Etape)owner.jComboBoxEtapes.getSelectedItem()).getEpreuves().getEpreuves());
                owner.jListEpreuves.setSelectedValue(epreuve, true);
                dispose();
              }
            else
            {
              owner.bip.play();
              StringBuffer msg = new StringBuffer("<html>Ce nom existe déjà pour une épreuve de cette étape.<br>");
              msg.append("Changer le nom pour modifier cette épreuve.</html>");
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
      jLabelmessage.setPreferredSize(new Dimension(340, 55));
      FlowLayout flowLayout1 = new FlowLayout();
      flowLayout1.setAlignment(FlowLayout.LEFT);
      flowLayout1.setHgap(10);
      jPanel = new JPanel();
      jPanel.setPreferredSize(new Dimension(1091, 500));
      jPanel.setLayout(flowLayout1);
      jPanel.add(jLabel, null);
      jPanel.add(getJTextFieldNom(), null);
      jPanel.add(getJPanelTypeLimite(), null);
      jPanel.add(getJPanelHeureLimiteTemps(), null);
      jPanel.add(getJPanelHeureLimiteHoraire(), null);
      jPanel.add(getJPanelPenalite(), null);
      jPanel.add(getJPanelChrono());
      jPanel.add(getPanelMultiplicateur());
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
      jComboBoxTypeLimite.setPreferredSize(new Dimension(120, 25));
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
              break;
            case AVECLIMITEHORAIRE:
              jPanelHeureLimiteHoraire.setVisible(true);
              jPanelHeureLimiteTemps.setVisible(false);
              jPanelPenalite.setVisible(true);
              chckbxChronomtrageDeLpreuve.setSelected(true);
              break;
            case AVECLIMITETEMPS:
              jPanelHeureLimiteHoraire.setVisible(false);
              jPanelHeureLimiteTemps.setVisible(true);
              jPanelPenalite.setVisible(true);
              chckbxChronomtrageDeLpreuve.setSelected(true);
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
      jPanelHeureLimiteTemps.setPreferredSize(new Dimension(270, 35));
      jPanelHeureLimiteTemps.setLayout(flowLayout31);
      jPanelHeureLimiteTemps.add(jLabel21, null);
      jPanelHeureLimiteTemps.add(getJSpinnerHeureLimiteTemps(), null);
      jPanelHeureLimiteTemps.add(jLabel31, null);
      jPanelHeureLimiteTemps.add(getJSpinnerMinuteLimiteTemps(), null);
      jPanelHeureLimiteTemps.add(jLabel41, null);
      jPanelHeureLimiteTemps.add(getJSpinnerSecondeLimiteTemps());
      jPanelHeureLimiteTemps.add(getLblS());
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
      jPanelPenalite.setPreferredSize(new Dimension(400, 35));
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
    if (jSpinnerPoints == null)
    {
      jSpinnerPoints = new JSpinner(new SpinnerNumberModel(0, 0, 500, 10));
    }
    return jSpinnerPoints;
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
      jSpinnerTemps = new JSpinner(new SpinnerNumberModel(0, 0, 1440, 1));
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
  private JPanel getJPanelChrono() {
    if (jPanelChrono == null) {
    	jPanelChrono = new JPanel();
    	jPanelChrono.setPreferredSize(new Dimension(320, 210));
    	FlowLayout fl_jPanelChrono = new FlowLayout();
    	fl_jPanelChrono.setAlignment(FlowLayout.LEFT);
    	jPanelChrono.setLayout(fl_jPanelChrono);
    	jPanelChrono.add(getChckbxChronomtrageDeLpreuve());
    	jPanelChrono.add(getChckbxChronomtragePartir());
    	jPanelChrono.add(getChckbxFinDuChronomtrage());
    	jPanelChrono.add(getChckbxCourseEnLigne());
    	jPanelChrono.add(getChckbxEpreuveEffectueAprs());
    	jPanelChrono.add(getChckbxPostesApresEpreuvePrecedente());
    	jPanelChrono.add(getChckbxPostesAvantEpreuveSuivante());
    }
    return jPanelChrono;
  }
  private JCheckBox getChckbxChronomtragePartir() {
    if (chckbxChronomtragePartir == null) {
    	chckbxChronomtragePartir = new JCheckBox("Chronom\u00E9trage \u00E0 partir de la fin de l'\u00E9preuve pr\u00E9c\u00E9dente\r\n");
    	chckbxChronomtragePartir.setEnabled(false);
    }
    return chckbxChronomtragePartir;
  }
  private JCheckBox getChckbxFinDuChronomtrage() {
    if (chckbxFinDuChronomtrage == null) {
    	chckbxFinDuChronomtrage = new JCheckBox("Fin du chronom\u00E9trage au d\u00E9but de l'\u00E9preuve suivante");
    	chckbxFinDuChronomtrage.setEnabled(false);
    	chckbxFinDuChronomtrage.setSelected(true);
    }
    return chckbxFinDuChronomtrage;
  }
  private JCheckBox getChckbxCourseEnLigne() {
    if (chckbxCourseEnLigne == null) {
    	chckbxCourseEnLigne = new JCheckBox("Course \u00E0 effectuer en ligne");
    }
    return chckbxCourseEnLigne;
  }
  private JCheckBox getChckbxChronomtrageDeLpreuve() {
    if (chckbxChronomtrageDeLpreuve == null) {
    	chckbxChronomtrageDeLpreuve = new JCheckBox("Chronom\u00E9trage de l'\u00E9preuve");
    	chckbxChronomtrageDeLpreuve.addChangeListener(new ChangeListener() {
    	  public void stateChanged(ChangeEvent arg0) 
    	  {
          if((Integer)spinnerMulti.getValue()>1 || !((TypeLimite)jComboBoxTypeLimite.getSelectedItem()).equals(TypeLimite.SANSLIMITE))
          {
            chckbxChronomtrageDeLpreuve.setSelected(true);
          }
    	    if(chckbxChronomtrageDeLpreuve.isSelected())
    	    {
    	      if(((Etape)owner.jComboBoxEtapes.getSelectedItem()).isFirstEpreuve(epreuve))
    	      {
              chckbxChronomtragePartir.setEnabled(false);
              chckbxChronomtragePartir.setSelected(false);
    	      }
    	      else
    	      {
              chckbxChronomtragePartir.setEnabled(true);
    	      }
            if(((Etape)owner.jComboBoxEtapes.getSelectedItem()).isLastEpreuve(epreuve))
            {
              chckbxFinDuChronomtrage.setEnabled(false);
              chckbxFinDuChronomtrage.setSelected(false);
            }
            else
            {
              chckbxFinDuChronomtrage.setEnabled(true);
            }
    	    }
    	    else
    	    {
            chckbxFinDuChronomtrage.setSelected(false);
            chckbxChronomtragePartir.setSelected(false);
            chckbxFinDuChronomtrage.setEnabled(false);
            chckbxChronomtragePartir.setEnabled(false);
    	    }
    	  }
    	});
    }
    return chckbxChronomtrageDeLpreuve;
  }
  private JCheckBox getChckbxEpreuveEffectueAprs() {
    if (chckbxEpreuveEffectueAprs == null) {
    	chckbxEpreuveEffectueAprs = new JCheckBox("Epreuve effectu\u00E9e apr\u00E8s l'arriv\u00E9e");
    }
    return chckbxEpreuveEffectueAprs;
  }
  private JPanel getPanelMultiplicateur() {
    if (panelMultiplicateur == null) {
    	panelMultiplicateur = new JPanel();
    	panelMultiplicateur.add(getLblNewLabel());
    	panelMultiplicateur.add(getSpinnerMulti());
    }
    return panelMultiplicateur;
  }
  private JLabel getLblNewLabel() {
    if (lblNewLabel == null) {
    	lblNewLabel = new JLabel("Temps final multipli\u00E9 par : ");
    }
    return lblNewLabel;
  }
  private JSpinner getSpinnerMulti() {
    if (spinnerMulti == null) {
    	spinnerMulti = new JSpinner();
    	spinnerMulti.setPreferredSize(new Dimension(50, 20));
    	spinnerMulti.addChangeListener(new ChangeListener() 
    	{
    	  public void stateChanged(ChangeEvent arg0) 
    	  {
    	    if((Integer)spinnerMulti.getValue()>1)
    	    {
    	      chckbxChronomtrageDeLpreuve.setSelected(true);
    	    }
    	  }
    	});
    	spinnerMulti.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
    }
    return spinnerMulti;
  }
  private JSpinner getJSpinnerSecondeLimiteTemps() {
    if (jSpinnerSecondeLimiteTemps == null) {
    	jSpinnerSecondeLimiteTemps = new JSpinner(
          new SpinnerNumberModel(0, 0, 59, 1));
    }
    return jSpinnerSecondeLimiteTemps;
  }
  private JLabel getLblS() {
    if (lblS == null) {
    	lblS = new JLabel();
    	lblS.setText("S");
    }
    return lblS;
  }
  private JCheckBox getChckbxPostesApresEpreuvePrecedente() {
    if (chckbxPostesApresEpreuvePrecedente == null) {
    	chckbxPostesApresEpreuvePrecedente = new JCheckBox("Postes \u00E0 faire apr\u00E8s l'\u00E9preuve pr\u00E9c\u00E9dente");
    	chckbxPostesApresEpreuvePrecedente.addItemListener(new ItemListener() 
    	{
    	  public void itemStateChanged(ItemEvent arg0) 
    	  {
    	    if(chckbxPostesApresEpreuvePrecedente.isSelected())
    	    {
    	      chckbxPostesAvantEpreuveSuivante.setSelected(false);
    	    }
    	  }
    	});
    }
    return chckbxPostesApresEpreuvePrecedente;
  }
  private JCheckBox getChckbxPostesAvantEpreuveSuivante() {
    if (chckbxPostesAvantEpreuveSuivante == null) {
    	chckbxPostesAvantEpreuveSuivante = new JCheckBox("Postes \u00E0 faire avant l'\u00E9preuve suivante");
    	chckbxPostesAvantEpreuveSuivante.addItemListener(new ItemListener() 
    	{
    	  public void itemStateChanged(ItemEvent e) 
    	  {
          if(chckbxPostesAvantEpreuveSuivante.isSelected())
          {
            chckbxPostesApresEpreuvePrecedente.setSelected(false);
          }
    	  }
    	});
    }
    return chckbxPostesAvantEpreuveSuivante;
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
