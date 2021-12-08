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
import javax.swing.WindowConstants;

import to.Epreuve;
import to.Etape;

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
public class IhmEpreuveDelete extends JDialog
{
  private IhmGeRaidMain owner = null;
  private String message = "";
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;
  private JButton jButtonOk = null;
  private JButton jButtonAnnuler = null;
  private JLabel jLabelMessage = null;
  /**
   * @param owner
   */
  public IhmEpreuveDelete(IhmGeRaidMain owner, String message)
  {
    super(owner);
    this.owner = owner;
    this.message = message;
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
    this.setSize(252, 183);
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
      jButtonOk.setToolTipText("Supprimer l'�preuve");
      jButtonOk.setIcon(new ImageIcon(getClass().getResource("/icones/ok64.png")));
      jButtonOk.addActionListener(new java.awt.event.ActionListener()
      {
        @SuppressWarnings("unchecked")
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          Epreuve et = (Epreuve)owner.jListEpreuves.getSelectedValue();
          ((Etape)owner.jComboBoxEtapes.getSelectedItem()).getEpreuves().removeEpreuve(et);
         // owner.geRaid.getResultatsPuce().effacerEtape(et);
          owner.jListEpreuves.repaint();
          owner.jListEpreuves.setListData(((Etape)owner.jComboBoxEtapes.getSelectedItem()).getEpreuves().getEpreuves());
          if(((Etape)owner.jComboBoxEtapes.getSelectedItem()).getEpreuves().getSize()==0)
          {
            owner.jListEpreuves.setSelectedIndex(-1);
          }
          else
          {
            owner.jListEpreuves.setSelectedIndex(0);
          }
          owner.jListEpreuves.repaint();
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
