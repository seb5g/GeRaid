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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import to.Categorie;
import to.Equipe;
import to.Parcours;
import to.Raider;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

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
public class IhmEquipes extends JDialog
{
  public Equipe equipe = null;
  private boolean creation = true;
  private IhmGeRaidMain owner = null;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JTextField jTextFieldNom = null;

  private JLabel jLabel1 = null;

  private JTextField jTextFieldDossard = null;

  private JLabel jLabel2 = null;

  private JTextField jTextFieldPuce = null;

  private JLabel jLabel3 = null;

  private JComboBox<Categorie> jComboBoxCategorie = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;

  private JPanel jPanel = null;

  private JPanel jPanel1 = null;
  private JLabel jLabelmessage = null;
  private JPanel jPanel2 = null;
  private JLabel jLabel4 = null;
  private JCheckBox jCheckBoxNC = null;
  private JPanel jPanelRaiders = null;
  private JPanel jPanelBoutons = null;
  private JButton jButtonAjouter = null;
  private JButton jButtonModifier = null;
  private JButton jButtonSupprimer = null;
  private JScrollPane jScrollPane = null;
  public JTable jTableRaiders = null;
  private JCheckBox chckbxAbsentOuAbandon;

  /**
   * @param owner
   */
  public IhmEquipes(IhmGeRaidMain owner, Equipe e, boolean creation)
  {
    super(owner);
    this.owner = owner;
    this.equipe = e;
    this.creation = creation;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    if(this.creation)
    {
      setTitle("Création d'une équipe");
    }
    else
    {
      setTitle("Modification d'une équipe");
    }
    jTextFieldNom.setText(equipe.getNom());
    jTextFieldDossard.setText(equipe.getDossard());
    jTextFieldPuce.setText(equipe.getIdPuce());
    jCheckBoxNC.setSelected(equipe.isNC());
    chckbxAbsentOuAbandon.setSelected(equipe.isABS());
    jComboBoxCategorie.setModel(new DefaultComboBoxModel<Categorie>(owner.geRaid.getCategorie().getCategories()));
    if(equipe.getCategorie()!=null)
    {
      jComboBoxCategorie.setSelectedItem(equipe.getCategorie());
    }
    else
    {
      jComboBoxCategorie.setSelectedIndex(0);
    }
    jTableRaiders.setModel(new RaiderTableModel(equipe.getRaiders()));
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(250, 482);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Equipes");
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
      jLabel3 = new JLabel();
      jLabel3.setText("Catégorie :  ");
      jLabel2 = new JLabel();
      jLabel2.setText("N° puce :  ");
      jLabel1 = new JLabel();
      jLabel1.setText("Dossard :  ");
      jLabel = new JLabel();
      jLabel.setText("Nom :  ");
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
   * This method initializes jTextFieldDossard	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getJTextFieldDossard()
  {
    if (jTextFieldDossard == null)
    {
      jTextFieldDossard = new JTextField();
      jTextFieldDossard.setPreferredSize(new Dimension(150, 20));
    }
    return jTextFieldDossard;
  }

  /**
   * This method initializes jTextFieldPuce	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getJTextFieldPuce()
  {
    if (jTextFieldPuce == null)
    {
      jTextFieldPuce = new JTextField();
      jTextFieldPuce.setPreferredSize(new Dimension(150, 20));
    }
    return jTextFieldPuce;
  }

  /**
   * This method initializes jComboBoxCategorie	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<Categorie> getJComboBoxCategorie()
  {
    if (jComboBoxCategorie == null)
    {
      jComboBoxCategorie = new JComboBox<Categorie>();
      jComboBoxCategorie.setPreferredSize(new Dimension(150, 25));
    }
    return jComboBoxCategorie;
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
        @SuppressWarnings("unchecked")
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jTextFieldNom.getText().trim().compareTo("")==0)
          {
            owner.bip.play();
            StringBuffer msg = new StringBuffer("<html>Vous devez donner un nom à cette équipe.<br>");
            jLabelmessage.setText(msg.toString());
            return;
          }
          else if(creation)
          {
            equipe.setNom(jTextFieldNom.getText());
            equipe.setDossard(jTextFieldDossard.getText());
            equipe.setIdPuce(jTextFieldPuce.getText());
            equipe.setNC(jCheckBoxNC.isSelected());
            equipe.setABS(chckbxAbsentOuAbandon.isSelected());
            equipe.setCategorie((Categorie)jComboBoxCategorie.getSelectedItem());
            if(!owner.geRaid.existeIdPuce(jTextFieldPuce.getText(), equipe))
            {
              owner.geRaid.addEquipe((Parcours)owner.jComboBoxParcours.getSelectedItem(), equipe);
              owner.jListEquipes.setListData(((Parcours)owner.jComboBoxParcours.getSelectedItem()).getEquipes().getEquipes());
              owner.jListEquipes.setSelectedValue(equipe, true);
              dispose();
            }
            else
            {
              owner.bip.play();
              StringBuffer msg = new StringBuffer("<html>Ce numéro de puce existe déjà pour une équipe.<br>");
              msg.append("Changer le numéro de puce pour créer cette équipe.</html>");
              jLabelmessage.setText(msg.toString());
            }
          }
          else if(!creation) 
          {
            if(!owner.geRaid.existeIdPuce(jTextFieldPuce.getText(), equipe))
              {
                equipe.setNom(jTextFieldNom.getText());
                equipe.setDossard(jTextFieldDossard.getText());
                equipe.setIdPuce(jTextFieldPuce.getText());
                equipe.setNC(jCheckBoxNC.isSelected());
                equipe.setABS(chckbxAbsentOuAbandon.isSelected());
                equipe.setCategorie((Categorie)jComboBoxCategorie.getSelectedItem());
                ((Parcours)owner.jComboBoxParcours.getSelectedItem()).getEquipes().trierEquipes(owner.typeTriCourant);
                owner.jListEquipes.repaint();
                owner.jListEquipes.setSelectedValue(equipe, true);
                dispose();
              }
            else
            {
              owner.bip.play();
              StringBuffer msg = new StringBuffer("<html>Ce numéro de puce existe déjà pour une équipe.<br>");
              msg.append("Changer le numéro de puce pour modifier cette équipe.</html>");
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
      jPanel.setPreferredSize(new Dimension(1091, 380));
      jPanel.setLayout(flowLayout1);
      jPanel.add(jLabel, null);
      jPanel.add(getJTextFieldNom(), null);
      jPanel.add(jLabel1, null);
      jPanel.add(getJTextFieldDossard(), null);
      jPanel.add(jLabel2, null);
      jPanel.add(getJTextFieldPuce(), null);
      jPanel.add(getJPanel2(), null);
      jPanel.add(jLabel3, null);
      jPanel.add(getJComboBoxCategorie(), null);
      jPanel.add(getJPanelRaiders(), null);
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

  /**
   * This method initializes jPanel2	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanel2()
  {
    if (jPanel2 == null)
    {
      jLabel4 = new JLabel();
      jLabel4.setText("Non classé");
      FlowLayout flowLayout2 = new FlowLayout();
      flowLayout2.setAlignment(FlowLayout.LEFT);
      jPanel2 = new JPanel();
      jPanel2.setPreferredSize(new Dimension(220, 25));
      jPanel2.setLayout(flowLayout2);
      jPanel2.add(getJCheckBoxNC(), null);
      jPanel2.add(jLabel4, null);
      jPanel2.add(getChckbxAbsentOuAbandon());
    }
    return jPanel2;
  }

  /**
   * This method initializes jCheckBoxNC	
   * 	
   * @return javax.swing.JCheckBox	
   */
  private JCheckBox getJCheckBoxNC()
  {
    if (jCheckBoxNC == null)
    {
      jCheckBoxNC = new JCheckBox();
    }
    return jCheckBoxNC;
  }

  /**
   * This method initializes jPanelRaiders	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelRaiders()
  {
    if (jPanelRaiders == null)
    {
      FlowLayout flowLayout3 = new FlowLayout();
      flowLayout3.setAlignment(FlowLayout.LEFT);
      jPanelRaiders = new JPanel();
      jPanelRaiders.setBorder(BorderFactory.createTitledBorder(null, "Raiders", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
      jPanelRaiders.setPreferredSize(new Dimension(225, 170));
      jPanelRaiders.setLayout(flowLayout3);
      jPanelRaiders.add(getJPanelBoutons(), null);
      jPanelRaiders.add(getJScrollPane(), null);
    }
    return jPanelRaiders;
  }

  /**
   * This method initializes jPanelBoutons	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelBoutons()
  {
    if (jPanelBoutons == null)
    {
      FlowLayout flowLayout4 = new FlowLayout();
      flowLayout4.setAlignment(FlowLayout.LEFT);
      jPanelBoutons = new JPanel();
      jPanelBoutons.setLayout(flowLayout4);
      jPanelBoutons.add(getJButtonAjouter(), null);
      jPanelBoutons.add(getJButtonModifier(), null);
      jPanelBoutons.add(getJButtonSupprimer(), null);
    }
    return jPanelBoutons;
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
      jButtonAjouter.setPreferredSize(new Dimension(32, 32));
      jButtonAjouter.setIcon(new ImageIcon(getClass().getResource("/icones/plus.png")));
      jButtonAjouter.setToolTipText("Ajouter un raider");
      jButtonAjouter.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmRaider ihm = new IhmRaider(IhmEquipes.this, 
              new Raider(), 
                  true);
          ihm.setLocationRelativeTo(IhmEquipes.this);
          ihm.setVisible(true);
        }
      });
    }
    return jButtonAjouter;
  }

  /**
   * This method initializes jButtonModifier	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonModifier()
  {
    if (jButtonModifier == null)
    {
      jButtonModifier = new JButton();
      jButtonModifier.setPreferredSize(new Dimension(32, 32));
      jButtonModifier.setToolTipText("Modifier un raider");
      jButtonModifier.setEnabled(false);
      jButtonModifier.setIcon(new ImageIcon(getClass().getResource("/icones/search.png")));
      jButtonModifier.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmRaider ihm = new IhmRaider(IhmEquipes.this, 
              equipe.getRaiders().getRaider((String)jTableRaiders.getValueAt(jTableRaiders.getSelectedRow(), 0),
                  (String)jTableRaiders.getValueAt(jTableRaiders.getSelectedRow(), 1)), 
                  false);
          ihm.setLocationRelativeTo(IhmEquipes.this);
          ihm.setVisible(true);
        }
      });
    }
    return jButtonModifier;
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
      jButtonSupprimer.setPreferredSize(new Dimension(32, 32));
      jButtonSupprimer.setToolTipText("Supprimer un raider");
      jButtonSupprimer.setEnabled(false);
      jButtonSupprimer.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
      jButtonSupprimer.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          equipe.getRaiders().removeRaider((String)jTableRaiders.getValueAt(jTableRaiders.getSelectedRow(), 0),
              (String)jTableRaiders.getValueAt(jTableRaiders.getSelectedRow(), 1));
          jTableRaiders.setModel(new RaiderTableModel(equipe.getRaiders()));
          SelectJTableRaiders();
        }
      });
    }
    return jButtonSupprimer;
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
      jScrollPane.setPreferredSize(new Dimension(200, 80));
      jScrollPane.setViewportView(getJTableRaiders());
    }
    return jScrollPane;
  }

  /**
   * This method initializes jTableRaiders	
   * 	
   * @return javax.swing.JTable	
   */
  private JTable getJTableRaiders()
  {
    if (jTableRaiders == null)
    {
      jTableRaiders = new JTable();
      jTableRaiders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      jTableRaiders.addMouseListener(new java.awt.event.MouseAdapter()
      {
        public void mouseClicked(java.awt.event.MouseEvent e)
        {
          SelectJTableRaiders();
        }
      });
      jTableRaiders.getTableHeader().setReorderingAllowed(false);
    }
    return jTableRaiders;
  }
  
  public void SelectJTableRaiders()
  {
    if(jTableRaiders.getSelectedRowCount()>0)
    {
      jButtonModifier.setEnabled(true);
      jButtonSupprimer.setEnabled(true);
    }
    else
    {
      jButtonModifier.setEnabled(false);
      jButtonSupprimer.setEnabled(false);
    }
  }

  private JCheckBox getChckbxAbsentOuAbandon() {
    if (chckbxAbsentOuAbandon == null) {
    	chckbxAbsentOuAbandon = new JCheckBox("Absent ou abandon");
    }
    return chckbxAbsentOuAbandon;
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
