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
public class IhmMessage extends JDialog
{
  private IhmGeRaidMain owner = null;
  public int retour = 0;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabelMessage = null;

  private JButton jButtonOk = null;

  /**
   * @param owner
   */
  public IhmMessage(IhmGeRaidMain owner, String message)
  {
    super(owner);
    this.owner = owner;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    setLocationRelativeTo(this.owner);
    jLabelMessage.setText(message);
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(300, 156);
    this.setTitle("Message Geraid");
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
      jLabelMessage.setText("");
      jLabelMessage.setHorizontalAlignment(SwingConstants.CENTER);
      jLabelMessage.setHorizontalTextPosition(SwingConstants.CENTER);
      jLabelMessage.setPreferredSize(new Dimension(250, 60));
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setHgap(30);
      jContentPane = new JPanel();
      jContentPane.setLayout(flowLayout);
      jContentPane.add(jLabelMessage, null);
      jContentPane.add(getJButtonOk(), null);
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
          dispose();
        }
      });
    }
    return jButtonOk;
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
