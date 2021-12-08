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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import to.Categorie;
import to.Categories;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
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
public class IhmParametresRaid extends JDialog
{
  private IhmGeRaidMain owner = null;
  public Categories cat = new Categories();  //  @jve:decl-index=0:
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JTextField jTextFieldNom = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;

  private JPanel jPanel = null;

  private JPanel jPanel1 = null;
  private JPanel jPanelCategories = null;
  private JLabel jLabel3 = null;
  private JScrollPane jScrollPane = null;
  public JTable jTableCategories = null;
  private JButton jButtonAjouter = null;
  private JButton jButtonEdit = null;
  private JButton jButtonSupprimer = null;
  private JPanel jPanel2 = null;
  private JLabel jLabel1 = null;
  private JTextField jTextFieldEnTete = null;
  private JLabel jLabel2 = null;
  private JTextField jTextFieldPiedPage = null;
  /**
   * @param owner
   */
  public IhmParametresRaid(IhmGeRaidMain owner)
  {
    super(owner);
    this.owner = owner;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    jTextFieldNom.setText(owner.geRaid.nomRaid);
    for(int i=0; i<owner.geRaid.getCategorie().getSize(); i++)
    {
      cat.addCategorie(owner.geRaid.getCategorie().getCategories().get(i));
    }
    jTableCategories.setModel(new CategorieTableModel(cat));
    jTextFieldEnTete.setText(owner.geRaid.entete);
    jTextFieldPiedPage.setText(owner.geRaid.piedPage);
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(324, 394);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Paramètres");
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
      jLabel = new JLabel();
      jLabel.setText("Nom du raid : ");
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
      jButtonOk.setIcon(new ImageIcon(getClass().getResource("/icones/ok64.png")));
      jButtonOk.setToolTipText("Validation des données");
      jButtonOk.setPreferredSize(new Dimension(50, 50));
      jButtonOk.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          owner.geRaid.nomRaid = jTextFieldNom.getText().trim();
          owner.geRaid.entete = jTextFieldEnTete.getText().trim();
          owner.geRaid.piedPage = jTextFieldPiedPage.getText().trim();
          owner.geRaid.setCategorie(cat);
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
      FlowLayout flowLayout1 = new FlowLayout();
      flowLayout1.setAlignment(FlowLayout.RIGHT);
      flowLayout1.setHgap(10);
      jPanel = new JPanel();
      jPanel.setPreferredSize(new Dimension(1091, 300));
      jPanel.setLayout(flowLayout1);
      jPanel.add(jLabel, null);
      jPanel.add(getJTextFieldNom(), null);
      jPanel.add(getJPanelCategories(), null);
      jPanel.add(getJPanel2(), null);
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

  /**
   * This method initializes jPanelCategories	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelCategories()
  {
    if (jPanelCategories == null)
    {
      jLabel3 = new JLabel();
      jLabel3.setText("Catégories du raid : ");
      FlowLayout flowLayout2 = new FlowLayout();
      flowLayout2.setAlignment(FlowLayout.LEFT);
      jPanelCategories = new JPanel();
      jPanelCategories.setPreferredSize(new Dimension(300, 170));
      jPanelCategories.setLayout(flowLayout2);
      jPanelCategories.add(jLabel3, null);
      jPanelCategories.add(getJButtonAjouter(), null);
      jPanelCategories.add(getJButtonEdit(), null);
      jPanelCategories.add(getJButtonSupprimer(), null);
      jPanelCategories.add(getJScrollPane(), null);
    }
    return jPanelCategories;
  }

  /**
   * This method initializes jScrollPane	
   * 	
   * @return javax.swing.JScrollPane	
   */
  private JScrollPane getJScrollPane()
  {
    if (jScrollPane == null)
    {
      jScrollPane = new JScrollPane();
      jScrollPane.setPreferredSize(new Dimension(295, 125));
      jScrollPane.setViewportView(getJTableCategories());
    }
    return jScrollPane;
  }

  /**
   * This method initializes jTableCategories	
   * 	
   * @return javax.swing.JTable	
   */
  private JTable getJTableCategories()
  {
    if (jTableCategories == null)
    {
      jTableCategories = new JTable();
      jTableCategories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      jTableCategories.setSize(new Dimension(292, 120));
      jTableCategories.addMouseListener(new java.awt.event.MouseAdapter()
      {
        public void mouseClicked(java.awt.event.MouseEvent e)
        {
          SelectJTableCategories();
        }
      });
      jTableCategories.getTableHeader().setReorderingAllowed(false);
    }
    return jTableCategories;
  }

  /**
   * This method initializes jButtonAjouter	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonAjouter()
  {
    if (jButtonAjouter == null)
    {
      jButtonAjouter = new JButton();
      jButtonAjouter.setPreferredSize(new Dimension(30, 30));
      jButtonAjouter.setIcon(new ImageIcon(getClass().getResource("/icones/plus.png")));
      jButtonAjouter.setToolTipText("Ajouter une catégorie");
      jButtonAjouter.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmCategorie ihm = new IhmCategorie(IhmParametresRaid.this, 
              new Categorie("", ""), 
                  true);
          ihm.setLocationRelativeTo(IhmParametresRaid.this);
          ihm.setVisible(true);
        }
      });
    }
    return jButtonAjouter;
  }

  /**
   * This method initializes jButtonEdit	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonEdit()
  {
    if (jButtonEdit == null)
    {
      jButtonEdit = new JButton();
      jButtonEdit.setPreferredSize(new Dimension(30, 30));
      jButtonEdit.setIcon(new ImageIcon(getClass().getResource("/icones/search.png")));
      jButtonEdit.setEnabled(false);
      jButtonEdit.setToolTipText("Modifier une catégorie");
      jButtonEdit.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmCategorie ihm = new IhmCategorie(IhmParametresRaid.this, 
              cat.getCategorie((String) jTableCategories.getModel().getValueAt(jTableCategories.getSelectedRow(), 1)), 
                  false);
          ihm.setLocationRelativeTo(IhmParametresRaid.this);
          ihm.setVisible(true);
        }
      });
    }
    return jButtonEdit;
  }

  /**
   * This method initializes jButtonSupprimer	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonSupprimer()
  {
    if (jButtonSupprimer == null)
    {
      jButtonSupprimer = new JButton();
      jButtonSupprimer.setPreferredSize(new Dimension(30, 30));
      jButtonSupprimer.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
      jButtonSupprimer.setEnabled(false);
      jButtonSupprimer.setToolTipText("Supprimer une catégorie");
      jButtonSupprimer.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          cat.removeCategorie((String) jTableCategories.getModel().getValueAt(jTableCategories.getSelectedRow(), 0));
          jTableCategories.setModel(new CategorieTableModel(cat));
          SelectJTableCategories();
        }
      });
    }
    return jButtonSupprimer;
  }

  /**
   * This method initializes jPanel2	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanel2()
  {
    if (jPanel2 == null)
    {
      jLabel2 = new JLabel();
      jLabel2.setText("Pied de page :");
      jLabel1 = new JLabel();
      jLabel1.setText("Entête :");
      FlowLayout flowLayout3 = new FlowLayout();
      flowLayout3.setAlignment(FlowLayout.RIGHT);
      jPanel2 = new JPanel();
      jPanel2.setPreferredSize(new Dimension(300, 90));
      jPanel2.setBorder(BorderFactory.createTitledBorder(null, "Impression des résultats", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
      jPanel2.setLayout(flowLayout3);
      jPanel2.add(jLabel1, null);
      jPanel2.add(getJTextFieldEnTete(), null);
      jPanel2.add(jLabel2, null);
      jPanel2.add(getJTextFieldPiedPage(), null);
    }
    return jPanel2;
  }

  /**
   * This method initializes jTextFieldEnTete	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getJTextFieldEnTete()
  {
    if (jTextFieldEnTete == null)
    {
      jTextFieldEnTete = new JTextField();
      jTextFieldEnTete.setPreferredSize(new Dimension(200, 20));
    }
    return jTextFieldEnTete;
  }

  /**
   * This method initializes jTextFieldPiedPage	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getJTextFieldPiedPage()
  {
    if (jTextFieldPiedPage == null)
    {
      jTextFieldPiedPage = new JTextField();
      jTextFieldPiedPage.setPreferredSize(new Dimension(200, 20));
    }
    return jTextFieldPiedPage;
  }
  
  public void SelectJTableCategories()
  {
    if(jTableCategories.getSelectedRowCount()>0)
    {
      jButtonEdit.setEnabled(true);
      jButtonSupprimer.setEnabled(true);
    }
    else
    {
      jButtonEdit.setEnabled(false);
      jButtonSupprimer.setEnabled(false);
    }
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
