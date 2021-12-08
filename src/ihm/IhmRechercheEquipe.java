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

import to.Equipe;
import to.Parcours;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
public class IhmRechercheEquipe extends JDialog
{
  private IhmGeRaidMain owner = null;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JTextField jTextFieldNumero = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;
  private JPanel jPanel = null;
  private JPanel panel;
  private JPanel panel_1;
  private JScrollPane scrollPane;
  private JList<Equipe> list;

  /**
   * @param owner
   */
  public IhmRechercheEquipe(IhmGeRaidMain owner)
  {
    super(owner);
    this.owner = owner;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    list.setListData(owner.geRaid.getToutesLesEquipes().getEquipes());
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(377, 379);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setTitle("Recherche Equipe");
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
      jLabel = new JLabel();
      jLabel.setText("Nom d'\u00E9quipe :");
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout(0, 0));
      jContentPane.add(getJPanel(), BorderLayout.NORTH);
      jContentPane.add(getPanel());
      jContentPane.add(getPanel_1(), BorderLayout.SOUTH);
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
      jTextFieldNumero.addKeyListener(new KeyAdapter() 
      {
        @Override
        public void keyReleased(KeyEvent e) 
        {
          list.setListData(owner.geRaid.getToutesLesEquipes(jTextFieldNumero.getText().toUpperCase()).getEquipes());
          list.repaint();
        }
      });
      jTextFieldNumero.setPreferredSize(new Dimension(240, 20));
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
          if(list.getSelectedIndex()>-1)
          {
            Parcours p = owner.geRaid.getParcourss().getParcours(list.getSelectedValue());
            owner.jComboBoxParcours.setSelectedItem(p);
            owner.jListEquipes.setSelectedValue(list.getSelectedValue(), true);
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

  private JPanel getPanel() {
    if (panel == null) {
    	panel = new JPanel();
    	panel.add(getScrollPane());
    }
    return panel;
  }
  private JPanel getPanel_1() {
    if (panel_1 == null) {
    	panel_1 = new JPanel();
    	panel_1.add(getJButtonOk());
    	panel_1.add(getJButtonAnnuler());
    }
    return panel_1;
  }
  private JScrollPane getScrollPane() {
    if (scrollPane == null) {
    	scrollPane = new JScrollPane();
    	scrollPane.setPreferredSize(new Dimension(340, 250));
    	scrollPane.setViewportView(getList());
    }
    return scrollPane;
  }
  private JList<Equipe> getList() {
    if (list == null) {
    	list = new JList<Equipe>();
    	list.addMouseListener(new MouseAdapter() 
    	{
    	  @Override
    	  public void mouseClicked(MouseEvent e) 
    	  {
          if (e.getClickCount() == 2) 
          {
            jButtonOk.doClick();
          } 
    	  }
    	});
    }
    return list;
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
