/**
 * 
 */
package ihm;

import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.WindowConstants;

import to.Parcours;
import java.awt.Color;

/**
 * <P>
 * Titre : IhmParcours.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class IhmRecherchePuce extends JDialog
{
  private IhmGeRaidMain owner = null;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JTextField jTextFieldNumero = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;
  private JLabel jLabelmessage = null;
  private JPanel jPanel = null;

  /**
   * @param owner
   */
  public IhmRecherchePuce(IhmGeRaidMain owner)
  {
    super(owner);
    this.owner = owner;
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
    this.setSize(227, 161);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setTitle("Recherche puce");
    this.setResizable(false);
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
      jLabelmessage = new JLabel();
      jLabelmessage.setText("");
      jLabelmessage.setForeground(Color.red);
      jLabelmessage.setPreferredSize(new Dimension(215, 30));
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setHgap(40);
      jLabel = new JLabel();
      jLabel.setText("Numéro :");
      jContentPane = new JPanel();
      jContentPane.setLayout(flowLayout);
      jContentPane.add(getJPanel(), null);
      jContentPane.add(jLabelmessage, null);
      jContentPane.add(getJButtonOk(), null);
      jContentPane.add(getJButtonAnnuler(), null);
    }
    return jContentPane;
  }

  /**
   * This method initializes jTextFieldNumero	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getJTextFieldNumero()
  {
    if (jTextFieldNumero == null)
    {
      jTextFieldNumero = new JTextField();
      jTextFieldNumero.setPreferredSize(new Dimension(140, 20));
    }
    return jTextFieldNumero;
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
      jButtonOk.setPreferredSize(new Dimension(50, 50));
      jButtonOk.setHorizontalTextPosition(SwingConstants.CENTER);
      jButtonOk.setToolTipText("Validation des données");
      jButtonOk.setIcon(new ImageIcon(getClass().getResource("/icones/ok64.png")));
      jButtonOk.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jTextFieldNumero.getText().trim().compareTo("")==0)
          {
            owner.bip.play();
            StringBuffer msg = new StringBuffer("<html>Vous devez fournir un numéro de puce.<br>");
            jLabelmessage.setText(msg.toString());
            return;
          }
          else if(owner.geRaid.existePuce(jTextFieldNumero.getText().trim()))
          {
            Parcours p = owner.geRaid.getParcoursIdPuce(jTextFieldNumero.getText().trim());
            owner.jComboBoxParcours.setSelectedItem(p);
            owner.jListEquipes.setSelectedValue(p.getEquipes().getEquipeIdPuce(jTextFieldNumero.getText().trim()), true);
            dispose();
          }
          else
          {
            owner.bip.play();
            StringBuffer msg = new StringBuffer("<html>Ce numéro de puce n'existe pas pour ce raid.<br>");
            jLabelmessage.setText(msg.toString());
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
      jButtonAnnuler.setIcon(new ImageIcon(getClass().getResource("/icones/back.png")));
      jButtonAnnuler.setPreferredSize(new Dimension(50, 50));
      jButtonAnnuler.setToolTipText("Retour à l'application");
      jButtonAnnuler.setHorizontalTextPosition(SwingConstants.CENTER);
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
      jPanel = new JPanel();
      jPanel.setLayout(flowLayout1);
      jPanel.add(jLabel, null);
      jPanel.add(getJTextFieldNumero(), null);
    }
    return jPanel;
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
