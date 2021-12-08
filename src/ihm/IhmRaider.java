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

import to.Raider;
import to.Raiders;

import java.awt.Color;

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
public class IhmRaider extends JDialog
{
  private Raider rd = null;
  private Raiders rds = null;
  private boolean creation = true;
  private IhmEquipes owner = null;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JTextField jTextFieldNom = null;

  private JLabel jLabel1 = null;

  private JTextField jTextFieldPrenom = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;

  private JPanel jPanel = null;

  private JPanel jPanel1 = null;
  private JLabel jLabelmessage = null;
  
  /**
   * @param owner
   */
  public IhmRaider(IhmEquipes owner, Raider r, boolean creation)
  {
    super(owner);
    this.owner = owner;
    this.rd = r;
    this.rds = owner.equipe.getRaiders();
    this.creation = creation;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    if(this.creation)
    {
      setTitle("Création d'un raider");
    }
    else
    {
      setTitle("Modification d'un raider");
    }
    jTextFieldNom.setText(rd.getNom());
    jTextFieldPrenom.setText(rd.getPrenom());
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(250, 211);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Raider");
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
      jLabel1 = new JLabel();
      jLabel1.setText("Prénom :");
      jLabel = new JLabel();
      jLabel.setText("Nom :");
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
   * This method initializes jTextFieldPrenom	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getJTextFieldPrenom()
  {
    if (jTextFieldPrenom == null)
    {
      jTextFieldPrenom = new JTextField();
      jTextFieldPrenom.setPreferredSize(new Dimension(150, 20));
    }
    return jTextFieldPrenom;
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
          if(jTextFieldNom.getText().trim().compareTo("")==0  || jTextFieldPrenom.getText().trim().compareTo("")==0)
          {
            jLabelmessage.setText("<html>Vous devez renseigner les deux champs.</html>");
            return;
          }
          else if(creation)
          {
            rd.setNom(jTextFieldNom.getText());
            rd.setPrenom(jTextFieldPrenom.getText());
            rds.addRaider(rd);
            owner.jTableRaiders.setModel(new RaiderTableModel(rds)); 
            dispose();
          }
          else if(!creation) 
          {
            rd.setNom(jTextFieldNom.getText());
            rd.setPrenom(jTextFieldPrenom.getText());
            owner.jTableRaiders.setModel(new RaiderTableModel(rds)); 
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
      jLabelmessage.setPreferredSize(new Dimension(225, 55));
      FlowLayout flowLayout1 = new FlowLayout();
      flowLayout1.setAlignment(FlowLayout.RIGHT);
      flowLayout1.setHgap(10);
      jPanel = new JPanel();
      jPanel.setPreferredSize(new Dimension(1091, 120));
      jPanel.setLayout(flowLayout1);
      jPanel.add(jLabel, null);
      jPanel.add(getJTextFieldNom(), null);
      jPanel.add(jLabel1, null);
      jPanel.add(getJTextFieldPrenom(), null);
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

}  //  @jve:decl-index=0:visual-constraint="10,10"
