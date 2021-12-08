/**
 * 
 */
package ihm;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.WindowConstants;

import to.Equipe;
import to.Parcours;
import java.awt.Color;
import javax.swing.JComboBox;

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
public class IhmDeplacementEquipe extends JDialog
{
  private Parcours parcours = null;
  private Equipe equipe = null;
  private IhmGeRaidMain owner = null;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;
  private JLabel jLabelmessage = null;
  @SuppressWarnings("rawtypes")
  private JComboBox comboBox;

  /**
   * @param owner
   */
  @SuppressWarnings("unchecked")
  public IhmDeplacementEquipe(IhmGeRaidMain owner, Parcours p, Equipe e)
  {
    super(owner);
    this.owner = owner;
    this.parcours = p;
    this.equipe = e;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    comboBox.setModel(new DefaultComboBoxModel<Parcours>(this.owner.geRaid.getParcourss().getParcourss())); 
    if(owner.geRaid.existeResultatPuce(equipe))
    {
      jLabelmessage.setText("<html><center>Cette équipe a déjà au moins un résultat.<br>En déplacant cette équipe, vous supprimerez ce ou ces résultats.</center></html>");
    }
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
    this.setTitle("D\u00E9placement d'une \u00E9quipe");
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
      jLabelmessage.setHorizontalTextPosition(SwingConstants.CENTER);
      jLabelmessage.setHorizontalAlignment(SwingConstants.CENTER);
      jLabelmessage.setText("");
      jLabelmessage.setForeground(Color.red);
      jLabelmessage.setPreferredSize(new Dimension(215, 55));
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setHgap(40);
      jLabel = new JLabel();
      jLabel.setText("D\u00E9placement de l'\u00E9quipe vers le parcours :");
      jContentPane = new JPanel();
      jContentPane.setLayout(flowLayout);
      jContentPane.add(jLabel, null);
      jContentPane.add(getComboBox());
      jContentPane.add(jLabelmessage, null);
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
      jButtonOk.setHorizontalTextPosition(SwingConstants.CENTER);
      jButtonOk.setToolTipText("D\u00E9placer l'\u00E9quipe");
      jButtonOk.setIcon(new ImageIcon(getClass().getResource("/icones/ok64.png")));
      jButtonOk.addActionListener(new java.awt.event.ActionListener()
      {
        @SuppressWarnings("unchecked")
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          Parcours p = ((Parcours)comboBox.getSelectedItem());
          if(!p.equals(parcours))
          {
            p.getEquipes().addEquipe(equipe);
            owner.geRaid.removeResultatPuce(equipe);
            parcours.getEquipes().removeEquipe(equipe);
            owner.jListEquipes.setListData(((Parcours)owner.jComboBoxParcours.getSelectedItem()).getEquipes().getEquipes());
            if(parcours.getEquipes().getSize()>0)
            {
              owner.jListEquipes.setSelectedIndex(0);            
            }
            else
            {
              owner.jListEquipes.setSelectedIndex(-1); 
            }
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
      jButtonAnnuler.setIcon(new ImageIcon(getClass().getResource("/icones/back.png")));
      jButtonAnnuler.setPreferredSize(new Dimension(50, 50));
      jButtonAnnuler.setToolTipText("Annuler le d\u00E9placement");
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

  @SuppressWarnings("rawtypes")
  private JComboBox getComboBox() {
    if (comboBox == null) {
    	comboBox = new JComboBox();
    	comboBox.setPreferredSize(new Dimension(200, 20));
    }
    return comboBox;
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
