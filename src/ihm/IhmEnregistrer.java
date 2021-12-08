/**
 * 
 */
package ihm;

import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * <P>
 * Titre : IhmEnregistrer.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class IhmEnregistrer extends JDialog
{
  private IhmGeRaidMain owner = null;
  public int retour = 0;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabelMessage = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;
  private JButton jButtonRetour = null;

  /**
   * @param owner
   */
  public IhmEnregistrer(IhmGeRaidMain owner)
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
    this.setSize(300, 156);
    this.setTitle("Enregistrer");
    this.setResizable(false);
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
      jLabelMessage.setText("Souhaitez-vous enregistrer le Raid?");
      jLabelMessage.setHorizontalAlignment(SwingConstants.CENTER);
      jLabelMessage.setHorizontalTextPosition(SwingConstants.CENTER);
      jLabelMessage.setPreferredSize(new Dimension(250, 60));
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setHgap(30);
      jContentPane = new JPanel();
      jContentPane.setLayout(flowLayout);
      jContentPane.add(jLabelMessage, null);
      jContentPane.add(getJButtonOk(), null);
      jContentPane.add(getJButtonAnnuler(), null);
      jContentPane.add(getJButtonRetour(), null);
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
      jButtonOk.setToolTipText("Enregistrer le raid");
      jButtonOk.setIcon(new ImageIcon(getClass().getResource("/icones/ok64.png")));
      jButtonOk.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          setVisible(false);
          owner.jMenuItemEnregistrer.doClick();
          retour = 0;
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
      jButtonAnnuler.setIcon(new ImageIcon(getClass().getResource("/icones/annule64.png")));
      jButtonAnnuler.setToolTipText("Ne pas enregistrer le raid");
      jButtonAnnuler.setPreferredSize(new Dimension(50, 50));
      jButtonAnnuler.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          retour = 1;
          setVisible(false);
        }
      });
    }
    return jButtonAnnuler;
  }

  /**
   * This method initializes jButtonRetour	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonRetour()
  {
    if (jButtonRetour == null)
    {
      jButtonRetour = new JButton();
      jButtonRetour.setPreferredSize(new Dimension(50, 50));
      jButtonRetour.setToolTipText("Retour à l'application");
      jButtonRetour.setIcon(new ImageIcon(getClass().getResource("/icones/back.png")));
      jButtonRetour.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          retour = 2;
          setVisible(false);
        }
      });
    }
    return jButtonRetour;
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
