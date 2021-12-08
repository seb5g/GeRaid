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

import to.Penalite;

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
public class IhmNomPenalite extends JDialog
{
  private Penalite penalite = null;
  private boolean creation = true;
  private IhmPenalite owner = null;
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
  public IhmNomPenalite(IhmPenalite owner, Penalite p, boolean creation)
  {
    super(owner);
    this.owner = owner;
    this.penalite = p;
    this.creation = creation;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    if(this.creation)
    {
      setTitle("Création d'une pénalité");
    }
    else
    {
      setTitle("Modification d'une pénalité");
    }
    jTextFieldNom.setText(penalite.getNom());
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
    this.setTitle("Pénalité");
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
      jLabel.setText("Nom de la pénalité :");
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
            StringBuffer msg = new StringBuffer("<html>Vous devez donner un nom à cette pénalité.<br>");
            jLabelmessage.setText(msg.toString());
            return;
          }
          else if(creation)
          {
              penalite.setNom(jTextFieldNom.getText());
              owner.geraid.penalites.getPenalites().add(penalite);
              dispose();
          }
          else if(!creation)
          {
              penalite.setNom(jTextFieldNom.getText());
              dispose();
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
