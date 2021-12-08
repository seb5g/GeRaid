/**
 * 
 */
package ihm;

import inOut.XmlConfig;

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
import javax.swing.JFileChooser;

import outils.CommunicationRail;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import to.Balise;
import to.Categorie;
import to.Categories;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
public class IhmParametres extends JDialog
{
  private IhmGeRaidMain owner = null;
  private CommunicationRail cr = new CommunicationRail();
  public Categories cat = new Categories();  //  @jve:decl-index=0:
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JTextField jTextFieldNom = null;

  private JLabel jLabel1 = null;

  private JLabel jLabel2 = null;

  private JTextField jTextFieldDossier = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;

  private JPanel jPanel = null;

  private JPanel jPanel1 = null;
  private JButton jButtonDossier = null;
  private JComboBox<String> jComboBoxPorts = null;
  private JPanel jPanelCategories = null;
  private JScrollPane jScrollPane = null;
  public JTable jTableCategories = null;
  private JButton jButtonAjouter = null;
  private JButton jButtonEdit = null;
  private JButton jButtonSupprimer = null;
  private JLabel lblDossierSauvegarde;
  private JButton button;
  private JTextField jTextFieldSauvegarde;
  private JCheckBox chckbxImpressionRduite;
  private JLabel lblParamtresParDfaut;
  private JButton button_1;
  private JCheckBox chckbxImpressionRduiteDes;
  private JPanel panel;
  private JPanel panel_1;
  private JCheckBox chckbxDefilement;
  private JSpinner spinnerVitesseDefilement;
  private JLabel lblVitesseDeDfilement;
  private JLabel lblSecondes;
  /**
   * @param owner
   */
  @SuppressWarnings("static-access")
  public IhmParametres(IhmGeRaidMain owner)
  {
    super(owner);
    this.owner = owner;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    jTextFieldDossier.setText(owner.geRaid.getDossierDefault());
    jTextFieldSauvegarde.setText(owner.geRaid.getDossierSauvegarde());
    jTextFieldNom.setText(owner.geRaid.getNomAssociation());
    chckbxImpressionRduite.setSelected(owner.geRaid.resultatReduit);
    chckbxImpressionRduiteDes.setSelected(owner.geRaid.impressionReduite);
    chckbxDefilement.setSelected(owner.geRaid.defilementJavaScript);
    spinnerVitesseDefilement.setValue(owner.geRaid.vitesse);
    for(int i=0; i<owner.geRaid.categoriesNouveauRaid.getSize(); i++)
    {
      cat.addCategorie(owner.geRaid.categoriesNouveauRaid.getCategories().get(i));
    }
    jTableCategories.setModel(new CategorieTableModel(cat));
    this.jComboBoxPorts.setModel(new DefaultComboBoxModel<>(cr.getListePortRail()));
    this.jComboBoxPorts.setSelectedItem(owner.geRaid.getPort());
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(338, 636);
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
      jLabel2 = new JLabel();
      jLabel2.setText("Dossier Raids :  ");
      jLabel1 = new JLabel();
      jLabel1.setText("Port station maitre :  ");
      jLabel = new JLabel();
      jLabel.setText("Nom du club :  ");
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(getJPanel(), BorderLayout.CENTER);
      jContentPane.add(getJPanel1(), BorderLayout.SOUTH);
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
      jTextFieldNom = new JTextField(0);
      jTextFieldNom.setPreferredSize(new Dimension(150, 20));
    }
    return jTextFieldNom;
  }

  /**
   * This method initializes jTextFieldDossier	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getJTextFieldDossier()
  {
    if (jTextFieldDossier == null)
    {
      jTextFieldDossier = new JTextField();
      jTextFieldDossier.setPreferredSize(new Dimension(150, 20));
      jTextFieldDossier.setEditable(false);
    }
    return jTextFieldDossier;
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
        @SuppressWarnings("static-access")
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          owner.geRaid.setNomAssociation(jTextFieldNom.getText().trim());
          if(jComboBoxPorts.getSelectedIndex()!=-1)
          {
            owner.geRaid.setPort(jComboBoxPorts.getSelectedItem().toString());
          }
          owner.geRaid.setDossierDefault(jTextFieldDossier.getText().trim());
          owner.geRaid.setDossierSauvegarde(jTextFieldSauvegarde.getText().trim());
          owner.geRaid.resultatReduit = chckbxImpressionRduite.isSelected();
          owner.geRaid.impressionReduite = chckbxImpressionRduiteDes.isSelected();
          owner.geRaid.defilementJavaScript = chckbxDefilement.isSelected();
          owner.geRaid.vitesse = (int) spinnerVitesseDefilement.getValue();
          owner.geRaid.categoriesNouveauRaid = cat;
          XmlConfig.enregistre(owner.geRaid, owner.geRaid.fichierConfig);
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
      flowLayout1.setAlignment(FlowLayout.LEFT);
      flowLayout1.setHgap(10);
      jPanel = new JPanel();
      jPanel.setPreferredSize(new Dimension(1120, 303));
      jPanel.setLayout(flowLayout1);
      jPanel.add(jLabel, null);
      jPanel.add(getJTextFieldNom(), null);
      jPanel.add(jLabel1, null);
      jPanel.add(getJComboBoxPorts(), null);
      jPanel.add(jLabel2, null);
      jPanel.add(getJButtonDossier(), null);
      jPanel.add(getJTextFieldDossier(), null);
      jPanel.add(getLblDossierSauvegarde());
      jPanel.add(getButton());
      jPanel.add(getJTextFieldSauvegarde());
      jPanel.add(getLblParamtresParDfaut());
      jPanel.add(getButton_1());
      jPanel.add(getPanel_1());
      jPanel.add(getPanel());
      jPanel.add(getJPanelCategories(), null);
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
   * This method initializes jButtonDossier	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonDossier()
  {
    if (jButtonDossier == null)
    {
      jButtonDossier = new JButton();
      jButtonDossier.setPreferredSize(new Dimension(30, 30));
      jButtonDossier.setIcon(new ImageIcon(getClass().getResource("/icones/dossier.png")));
      jButtonDossier.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          // On ouvre le navigateur
          JFileChooser chooser = new JFileChooser(owner.geRaid.getDossierDefault());
          chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          
          int returnVal = chooser.showOpenDialog(IhmParametres.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            jTextFieldDossier.setText(chooser.getSelectedFile().toString());
          }
        }
      });
    }
    return jButtonDossier;
  }

  /**
   * This method initializes jComboBoxPorts	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<String> getJComboBoxPorts()
  {
    if (jComboBoxPorts == null)
    {
      jComboBoxPorts = new JComboBox<String>();
      jComboBoxPorts.setPreferredSize(new Dimension(150, 25));
    }
    return jComboBoxPorts;
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
      FlowLayout flowLayout2 = new FlowLayout();
      flowLayout2.setAlignment(FlowLayout.LEFT);
      jPanelCategories = new JPanel();
      jPanelCategories.setBorder(new TitledBorder(null, "Cat\u00E9gories d'un nouveau raid", TitledBorder.LEADING, TitledBorder.TOP, null, null));
      jPanelCategories.setPreferredSize(new Dimension(310, 200));
      jPanelCategories.setLayout(flowLayout2);
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
      //jScrollPane = new JScrollPane();
      jScrollPane = new JScrollPane(null);
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
      jTableCategories = new JTable(null);
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
          IhmCategorie ihm = new IhmCategorie(IhmParametres.this, 
              new Categorie("", ""), 
                  true);
          ihm.setLocationRelativeTo(IhmParametres.this);
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
          IhmCategorie ihm = new IhmCategorie(IhmParametres.this, 
              cat.getCategorie((String) jTableCategories.getModel().getValueAt(jTableCategories.getSelectedRow(), 1)), 
                  false);
          ihm.setLocationRelativeTo(IhmParametres.this);
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

  private JLabel getLblDossierSauvegarde() {
    if (lblDossierSauvegarde == null) {
    	lblDossierSauvegarde = new JLabel();
    	lblDossierSauvegarde.setText("Dossier sauvegarde :  ");
    }
    return lblDossierSauvegarde;
  }
  private JButton getButton() {
    if (button == null) {
    	button = new JButton();
    	button.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
          // On ouvre le navigateur
          JFileChooser chooser = new JFileChooser(owner.geRaid.getDossierSauvegarde());
          chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          
          int returnVal = chooser.showOpenDialog(IhmParametres.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            jTextFieldSauvegarde.setText(chooser.getSelectedFile().toString());
          }
    	  }
    	});
    	button.setIcon(new ImageIcon(IhmParametres.class.getResource("/icones/dossier.png")));
    	button.setPreferredSize(new Dimension(30, 30));
    }
    return button;
  }
  private JTextField getJTextFieldSauvegarde() {
    if (jTextFieldSauvegarde == null) {
    	jTextFieldSauvegarde = new JTextField();
    	jTextFieldSauvegarde.setText((String) null);
    	jTextFieldSauvegarde.setPreferredSize(new Dimension(150, 20));
    	jTextFieldSauvegarde.setEditable(false);
    }
    return jTextFieldSauvegarde;
  }
  private JCheckBox getChckbxImpressionRduite() {
    if (chckbxImpressionRduite == null) {
    	//chckbxImpressionRduite = new JCheckBox("Impression r\u00E9duite des r\u00E9sultats individuels");
      chckbxImpressionRduite = new JCheckBox("Impression r\u00E9duite des r\u00E9sultats individuels");
    	chckbxImpressionRduite.setSelected(true);
    }
    return chckbxImpressionRduite;
  }
  private JLabel getLblParamtresParDfaut() {
    if (lblParamtresParDfaut == null) {
    	lblParamtresParDfaut = new JLabel("Param\u00E8tres par d\u00E9faut d'une balise : ");
    }
    return lblParamtresParDfaut;
  }
  private JButton getButton_1() {
    if (button_1 == null) {
    	button_1 = new JButton("");
    	button_1.addActionListener(new ActionListener() 
    	{
    	  public void actionPerformed(ActionEvent arg0) 
    	  {
          Balise b = new Balise();
          IhmBalise ihm = new IhmBalise(owner, b, true, true);
          ihm.setLocationRelativeTo(IhmParametres.this);
          ihm.setVisible(true);
    	  }
    	});
    	button_1.setPreferredSize(new Dimension(32, 32));
    	button_1.setIcon(new ImageIcon(IhmParametres.class.getResource("/icones/balise.jpg")));
    }
    return button_1;
  }
  private JCheckBox getChckbxImpressionRduiteDes() {
    if (chckbxImpressionRduiteDes == null) {
    	chckbxImpressionRduiteDes = new JCheckBox("Impression r\u00E9duite des r\u00E9sultats globaux");
    }
    return chckbxImpressionRduiteDes;
  }
  private JPanel getPanel() {
    if (panel == null) {
    	panel = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	panel.setPreferredSize(new Dimension(300, 80));
    	panel.setBorder(new TitledBorder(null, "Impression", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel.add(getChckbxImpressionRduite());
    	panel.add(getChckbxImpressionRduiteDes());
    }
    return panel;
  }
  private JPanel getPanel_1() {
    if (panel_1 == null) {
    	panel_1 = new JPanel();
    	panel_1.setPreferredSize(new Dimension(300, 80));
    	panel_1.setBorder(new TitledBorder(null, "D\u00E9filement r\u00E9sultats HTML", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	panel_1.add(getChckbxDefilement());
    	panel_1.add(getLblVitesseDeDfilement());
    	panel_1.add(getSpinnerVitesseDefilement());
    	panel_1.add(getLblSecondes());
    }
    return panel_1;
  }
  private JCheckBox getChckbxDefilement() {
    if (chckbxDefilement == null) {
    	chckbxDefilement = new JCheckBox("D\u00E9filement automatique des fichiers HTML");
    }
    return chckbxDefilement;
  }
  private JSpinner getSpinnerVitesseDefilement() {
    if (spinnerVitesseDefilement == null) {
    	spinnerVitesseDefilement = new JSpinner();
    	spinnerVitesseDefilement.setModel(new SpinnerNumberModel(20, 10, 60, 10));
    	spinnerVitesseDefilement.setPreferredSize(new Dimension(50, 20));
    }
    return spinnerVitesseDefilement;
  }
  private JLabel getLblVitesseDeDfilement() {
    if (lblVitesseDeDfilement == null) {
    	lblVitesseDeDfilement = new JLabel("Temps d'affichage par page : ");
    }
    return lblVitesseDeDfilement;
  }
  private JLabel getLblSecondes() {
    if (lblSecondes == null) {
    	lblSecondes = new JLabel("secondes");
    }
    return lblSecondes;
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
