/**
 * 
 */
package ihm;

import geRaid.GeRaid;

import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * <P>
 * Titre : IhmConfirm.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class IhmResultatPuceDelete extends JDialog
{
  private IhmGestionResultatPuce owner = null;
  private String message = "";
  private GeRaid geraid = null;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;
  private JButton jButtonOk = null;
  private JButton jButtonAnnuler = null;
  private JLabel jLabelMessage = null;
  /**
   * @param owner
   */
  public IhmResultatPuceDelete(IhmGestionResultatPuce owner, String message, GeRaid g)
  {
    super(owner);
    this.owner = owner;
    this.message = message;
    this.geraid = g;
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
    this.setSize(252, 168);
    this.setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setTitle("Confirmation");
    this.setModal(true);
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
      jLabelMessage = new JLabel();
      jLabelMessage.setText(message);
      jLabelMessage.setHorizontalTextPosition(SwingConstants.CENTER);
      jLabelMessage.setHorizontalAlignment(SwingConstants.CENTER);
      jLabelMessage.setPreferredSize(new Dimension(200, 70));
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setVgap(5);
      flowLayout.setHgap(30);
      jContentPane = new JPanel();
      jContentPane.setLayout(flowLayout);
      jContentPane.add(jLabelMessage, null);
      jContentPane.add(getJButtonOk(), null);
      jContentPane.add(getJButtonAnnuler(), null);
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
      jButtonOk.setPreferredSize(new Dimension(50, 50));
      jButtonOk.setToolTipText("Supprimer l'�quipe");
      jButtonOk.setIcon(new ImageIcon(getClass().getResource("/icones/ok64.png")));
      jButtonOk.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          geraid.getResultatsPuce().removeResultatPuce(owner.resultatCourant);
          owner.initParcours();
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
      jButtonAnnuler.setToolTipText("Retour � l'application");
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
