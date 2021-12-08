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
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import outils.TimeManager;

import to.Partiel;

import java.util.Date;

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
public class IhmPartiel extends JDialog
{
  private Partiel partiel = null;
  private boolean creation = true;
  private IhmGestionResultatPuce owner = null;
  
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;

  private JPanel jPanel = null;

  private JPanel jPanel1 = null;
  private JPanel jPanelHeureDepart = null;
  private JLabel jLabel2 = null;
  private JSpinner jSpinnerHeure = null;
  private JLabel jLabel3 = null;
  private JSpinner jSpinnerMinute = null;
  private JLabel jLabel4 = null;
  private JSpinner jSpinnerSeconde = null;
  private JLabel jLabel41 = null;
  private JSpinner jSpinnerCode = null;

  /**
   * @param owner
   */
  public IhmPartiel(IhmGestionResultatPuce owner, Partiel p, boolean creation)
  {
    super(owner);
    this.owner = owner;
    this.partiel = p;
    this.creation = creation;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    if(this.creation)
    {
      setTitle("Création d'un poste");
    }
    else
    {
      setTitle("Modification d'un poste");
    }
    jSpinnerCode.setValue(partiel.getCode());
    jSpinnerHeure.setValue(TimeManager.getHeures(partiel.getTime()));
    jSpinnerMinute.setValue(TimeManager.getMinutes(partiel.getTime()));
    jSpinnerSeconde.setValue(TimeManager.getSecondes(partiel.getTime()));
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(287, 160);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Partiel");
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
      jLabel.setText("Code :");
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(getJPanel(), BorderLayout.NORTH);
      jContentPane.add(getJPanel1(), BorderLayout.CENTER);
    }
    return jContentPane;
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
          if(creation)
          {
            partiel.setCode((Integer)jSpinnerCode.getValue());
            partiel.setTime(new Date((Integer)jSpinnerHeure.getValue()*3600000 +
                  (Integer)jSpinnerMinute.getValue()*60000 +
                  (Integer)jSpinnerSeconde.getValue()*1000));
            Partiel[] p = new Partiel[owner.resultatCourant.getPuce().getPartiels().length+1];
            System.arraycopy(owner.resultatCourant.getPuce().getPartiels(), 0, 
                p, 0, owner.resultatCourant.getPuce().getPartiels().length);
            p[owner.resultatCourant.getPuce().getPartiels().length] = partiel;
            owner.resultatCourant.getPuce().setPartiels(p);
          }
          else  
          {
            partiel.setCode((Integer)jSpinnerCode.getValue());
            partiel.setTime(new Date((Integer)jSpinnerHeure.getValue()*3600000 +
                  (Integer)jSpinnerMinute.getValue()*60000 +
                  (Integer)jSpinnerSeconde.getValue()*1000));
          }
          dispose();
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
      FlowLayout flowLayout1 = new FlowLayout();
      flowLayout1.setAlignment(FlowLayout.LEFT);
      flowLayout1.setHgap(10);
      jPanel = new JPanel();
      jPanel.setPreferredSize(new Dimension(1091, 70));
      jPanel.setLayout(flowLayout1);
      jPanel.add(jLabel, null);
      jPanel.add(getJSpinnerCode(), null);
      jPanel.add(getJPanelHeureDepart(), null);
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
   * This method initializes jPanelHeureDepart	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelHeureDepart()
  {
    if (jPanelHeureDepart == null)
    {
      jLabel41 = new JLabel();
      jLabel41.setText("S");
      jLabel4 = new JLabel();
      jLabel4.setText("M");
      jLabel3 = new JLabel();
      jLabel3.setText("H");
      jLabel2 = new JLabel();
      jLabel2.setText("Heure  : ");
      FlowLayout flowLayout3 = new FlowLayout();
      flowLayout3.setAlignment(FlowLayout.LEFT);
      jPanelHeureDepart = new JPanel();
      jPanelHeureDepart.setPreferredSize(new Dimension(250, 35));
      jPanelHeureDepart.setLayout(flowLayout3);
      jPanelHeureDepart.add(jLabel2, null);
      jPanelHeureDepart.add(getJSpinnerHeure(), null);
      jPanelHeureDepart.add(jLabel3, null);
      jPanelHeureDepart.add(getJSpinnerMinute(), null);
      jPanelHeureDepart.add(jLabel4, null);
      jPanelHeureDepart.add(getJSpinnerSeconde(), null);
      jPanelHeureDepart.add(jLabel41, null);
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
      jSpinnerHeure = new JSpinner(new SpinnerNumberModel(0, 0, 47, 1));
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
   * This method initializes jSpinnerSeconde	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerSeconde()
  {
    if (jSpinnerSeconde == null)
    {
      jSpinnerSeconde = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
    }
    return jSpinnerSeconde;
  }

  /**
   * This method initializes jSpinnerCode	
   * 	
   * @return javax.swing.JSpinner	
   */
  private JSpinner getJSpinnerCode()
  {
    if (jSpinnerCode == null)
    {
      jSpinnerCode = new JSpinner(new SpinnerNumberModel(31, 31, 500, 1));
    }
    return jSpinnerCode;
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
