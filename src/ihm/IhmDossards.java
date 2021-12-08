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

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import to.Equipes;

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
public class IhmDossards extends JDialog
{
  private Equipes equipes = null;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JTextField jTextFieldPrefixe = null;

  private JLabel jLabel1 = null;

  private JSpinner jTextFieldDossard = null;

  private JLabel jLabel2 = null;

  private JTextField jTextFieldSuffixe = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;

  private JPanel jPanel = null;

  private JPanel jPanel1 = null;
  /**
   * @param owner
   */
  public IhmDossards(IhmGeRaidMain owner, Equipes e)
  {
    super(owner);
    this.equipes = e;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(190, 173);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Dossards");
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
      jLabel2 = new JLabel();
      jLabel2.setText("Suffixe :");
      jLabel1 = new JLabel();
      jLabel1.setText("Premier numéro :  ");
      jLabel = new JLabel();
      jLabel.setText("Préfixe :");
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
    if (jTextFieldPrefixe == null)
    {
      jTextFieldPrefixe = new JTextField();
      jTextFieldPrefixe.setPreferredSize(new Dimension(50, 20));
    }
    return jTextFieldPrefixe;
  }

  /**
   * This method initializes jTextFieldDossard	
   * 	
   * @return javax.swing.JTextField	
   */
  private JSpinner getJTextFieldDossard()
  {
    if (jTextFieldDossard == null)
    {
      jTextFieldDossard = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
      jTextFieldDossard.setPreferredSize(new Dimension(50, 20));
    }
    return jTextFieldDossard;
  }

  /**
   * This method initializes jTextFieldPuce	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getJTextFieldPuce()
  {
    if (jTextFieldSuffixe == null)
    {
      jTextFieldSuffixe = new JTextField();
      jTextFieldSuffixe.setPreferredSize(new Dimension(50, 20));
    }
    return jTextFieldSuffixe;
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
          equipes.numeroterEquipes(jTextFieldPrefixe.getText().trim(), 
              (Integer)jTextFieldDossard.getValue(), jTextFieldSuffixe.getText().trim());
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
      flowLayout1.setAlignment(FlowLayout.RIGHT);
      flowLayout1.setHgap(10);
      jPanel = new JPanel();
      jPanel.setPreferredSize(new Dimension(1091, 80));
      jPanel.setLayout(flowLayout1);
      jPanel.add(jLabel, null);
      jPanel.add(getJTextFieldNom(), null);
      jPanel.add(jLabel1, null);
      jPanel.add(getJTextFieldDossard(), null);
      jPanel.add(jLabel2, null);
      jPanel.add(getJTextFieldPuce(), null);
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
      flowLayout.setHgap(10);
      jPanel1 = new JPanel();
      jPanel1.setLayout(flowLayout);
      jPanel1.add(getJButtonOk(), null);
      jPanel1.add(getJButtonAnnuler(), null);
    }
    return jPanel1;
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
