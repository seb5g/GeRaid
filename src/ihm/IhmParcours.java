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
public class IhmParcours extends JDialog
{
  private Parcours parcours = null;
  private boolean creation = true;
  private IhmGeRaidMain owner = null;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JTextField jTextFieldNom = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;
  private JLabel jLabelmessage = null;

  /**
   * @param owner
   */
  public IhmParcours(IhmGeRaidMain owner, Parcours p, boolean creation)
  {
    super(owner);
    this.owner = owner;
    this.parcours = p;
    this.creation = creation;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    if(this.creation)
    {
      setTitle("Création d'un parcours");
    }
    else
    {
      setTitle("Modification d'un parcours");
    }
    jTextFieldNom.setText(parcours.getNom());
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(227, 199);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setTitle("Parcours");
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
      jLabelmessage.setPreferredSize(new Dimension(215, 55));
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setHgap(40);
      jLabel = new JLabel();
      jLabel.setText("Nom du parcours :");
      jContentPane = new JPanel();
      jContentPane.setLayout(flowLayout);
      jContentPane.add(jLabel, null);
      jContentPane.add(getJTextFieldNom(), null);
      jContentPane.add(jLabelmessage, null);
      jContentPane.add(getJButtonOk(), null);
      jContentPane.add(getJButtonAnnuler(), null);
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
      jTextFieldNom.setPreferredSize(new Dimension(200, 20));
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
      jButtonOk.setPreferredSize(new Dimension(50, 50));
      jButtonOk.setHorizontalTextPosition(SwingConstants.CENTER);
      jButtonOk.setToolTipText("Validation des données");
      jButtonOk.setIcon(new ImageIcon(getClass().getResource("/icones/ok64.png")));
      jButtonOk.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jTextFieldNom.getText().trim().compareTo("")==0)
          {
            owner.bip.play();
            StringBuffer msg = new StringBuffer("<html>Vous devez donner un nom à ce parcours.<br>");
            jLabelmessage.setText(msg.toString());
            return;
          }
          else if(creation)
          {
            if(!owner.geRaid.existeParcours(jTextFieldNom.getText(), parcours))
            {
              parcours.setNom(jTextFieldNom.getText());
              owner.geRaid.addParcours(parcours);
              owner.jComboBoxParcours.repaint();
              owner.jComboBoxParcours.setSelectedIndex(owner.jComboBoxParcours.getItemCount()-1);
              dispose();
            }
            else
            {
              owner.bip.play();
              StringBuffer msg = new StringBuffer("<html>Ce nom existe déjà pour un parcours.<br>");
              msg.append("Changer le nom pour créer ce parcours.</html>");
              jLabelmessage.setText(msg.toString());
            }
          }
          else if(!creation)
          {
            if(!owner.geRaid.existeParcours(jTextFieldNom.getText(), parcours))
            {
              owner.geRaid.editParcours(parcours, jTextFieldNom.getText());
              owner.jComboBoxParcours.repaint();
              dispose();
            }
            else
            {
              owner.bip.play();
              StringBuffer msg = new StringBuffer("<html>Ce nom existe déjà pour un parcours.<br>");
              msg.append("Changer le nom pour créer ce parcours.</html>");
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
