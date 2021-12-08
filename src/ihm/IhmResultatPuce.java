/**
 * 
 */
package ihm;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;

import to.Equipe;
import to.Etape;
import to.Parcours;
import to.ResultatEquipe;
import to.ResultatPuce;
import java.awt.Point;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.swing.text.html.HTMLDocument;
import javax.swing.ScrollPaneConstants;

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
public class IhmResultatPuce extends JDialog
{
  private IhmGeRaidMain owner = null;
  private ResultatPuce resultatPuce = null;
  private boolean equipeValide = true;
  private boolean visu = false;
  private boolean resultatReduit = false;
  private static final long serialVersionUID = 1L;
  private Double zoom = 1.5;

  private JPanel jContentPane = null;

  private JLabel jLabel = null;

  private JLabel jLabel1 = null;

  private JLabel jLabel2 = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;

  private JPanel jPanel = null;

  private JPanel jPanel1 = null;
  private JLabel jLabel3 = null;
  private JComboBox<Parcours> jComboBoxParcours = null;
  private JComboBox<Etape> jComboBoxEtapes = null;
  private JComboBox<Equipe> jComboBoxEquipes = null;
  private JScrollPane jScrollPane = null;
  private JLabel jLabel4 = null;
  private JButton jButtonOkPrint = null;
  private JButton jButtonCreer = null;
  private JLabel jLabelMessage = null;
  private JEditorPane jEditorPaneResultat = null;
  private JButton jButtonPrint = null;
  private JButton jButtonRefresh = null;
  private JScrollPane scrollPane;
  private JLabel lblPostes;
  
  /**
   * @param owner
   */
  public IhmResultatPuce(IhmGeRaidMain owner, ResultatPuce rp, boolean equipeValide , boolean visu, boolean resultatReduit)
  {
    super(owner);
    this.owner = owner;
    this.resultatPuce = rp;
    this.equipeValide = equipeValide;
    this.visu = visu;
    this.resultatReduit = resultatReduit;
    initialize();
    jButtonCreer.setEnabled(!equipeValide);
    if(visu)
    {
      setTitle("Visualisation d'un résultat");
      jLabel3.setVisible(false);
      jLabel.setVisible(false);
      jLabel1.setVisible(false);
      jLabel2.setVisible(false);
      jLabel4.setLocation(5, 5);
      jComboBoxParcours.setVisible(false);
      jComboBoxEtapes.setVisible(false);
      jComboBoxEquipes.setVisible(false);
      jLabelMessage.setVisible(false);
      jButtonRefresh.setLocation(jButtonRefresh.getLocation().x, 5);
      jButtonOk.setVisible(false);
      jButtonOkPrint.setVisible(false);
      jButtonPrint.setVisible(true);
      jButtonCreer.setVisible(false);
      jButtonAnnuler.setVisible(true);
      jScrollPane.setBounds(5, 5 + jButtonRefresh.getHeight(), 480, 380 - jButtonRefresh.getHeight());
    }
    jComboBoxParcours.setModel(new DefaultComboBoxModel<Parcours>(owner.geRaid.getParcourss().getParcourss()));
    jLabel4.setText("Résultat de la puce : " + rp.getPuce().getIdPuce());
    if(equipeValide)
    {
      jComboBoxParcours.setSelectedItem(resultatPuce.getParcours());
      ResultatEquipe re = new ResultatEquipe(rp, 0, 0);
      lblPostes.setText(re.listeCodeOkPm.toHtml());
      re.saveHtml(owner.geRaid, resultatReduit);
      try
      {
        String adresse = new File(".").getCanonicalPath().toString();
        jEditorPaneResultat.setPage("file:///" + adresse + "/temp.html");
      }
      catch (IOException e)
      {
        System.err.format("Cannot print %s%n", e.getMessage());
      }
      if(owner.geRaid.existeResultatPuce(rp)!= null)
      {
        StringBuffer message = new StringBuffer("<html>Un résultat existe déjà pour cette équipe sur cette étape.<br>");
        message.append("En validant ce résultat, vous remplacerez l'ancien.</html>");
        jLabelMessage.setText(message.toString());
        miseAJourBoutons(false);
      }
    }
    else
    {
      jComboBoxParcours.setSelectedIndex(0);
      StringBuffer message = new StringBuffer("<html>Le numéro de la puce insérée ne correspond à aucune équipe du raid.<br>");
      message.append("Vous pouvez attribuer ce résultat à une équipe existante ou créer une nouvelle équipe.</html>");
      jLabelMessage.setText(message.toString());
      miseAJourBoutons(false);
      JOptionPane.showMessageDialog(this, message.toString(), "Puce inconnue", JOptionPane.WARNING_MESSAGE);
    }
  }
  
  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(497, 628);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Résultat Puce");
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
      jLabel2.setText("Equipe :  ");
      jLabel2.setSize(new Dimension(65, 18));
      jLabel2.setPreferredSize(new Dimension(50, 18));
      jLabel2.setLocation(new Point(5, 116));
      jLabel1 = new JLabel();
      jLabel1.setText("Etape :  ");
      jLabel1.setSize(new Dimension(67, 18));
      jLabel1.setPreferredSize(new Dimension(44, 18));
      jLabel1.setLocation(new Point(5, 82));
      jLabel = new JLabel();
      jLabel.setText("Parcours :  ");
      jLabel.setSize(new Dimension(67, 20));
      jLabel.setLocation(new Point(5, 50));
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(getJPanel(), BorderLayout.NORTH);
      jContentPane.add(getJPanel1(), BorderLayout.CENTER);
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
      jButtonOk.setIcon(new ImageIcon(getClass().getResource("/icones/ok64.png")));
      jButtonOk.setToolTipText("Valider le résultat");
      jButtonOk.setPreferredSize(new Dimension(50, 50));
      jButtonOk.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          resultatPuce.setParcours((Parcours)jComboBoxParcours.getSelectedItem());
          resultatPuce.setEtape((Etape)jComboBoxEtapes.getSelectedItem());
          resultatPuce.setEquipe((Equipe)jComboBoxEquipes.getSelectedItem());
          resultatPuce.getEquipe().setIdPuce(resultatPuce.getPuce().getIdPuce());
          
          ResultatPuce rp = owner.geRaid.existeResultatPuce(resultatPuce);
          if(rp==null)
          {
            owner.geRaid.getResultatsPuce().addResultatPuce(resultatPuce);
            owner.jMenuItemEnregistrer.doClick();
          }
          else
          {
            owner.geRaid.removeResultatPuce(rp);
            owner.geRaid.addResultatPuce(resultatPuce);
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
      jLabelMessage = new JLabel();
      jLabelMessage.setBounds(new Rectangle(7, 479, 478, 46));
      jLabelMessage.setForeground(Color.red);
      jLabelMessage.setText("");
      jLabel4 = new JLabel();
      jLabel4.setPreferredSize(new Dimension(200, 16));
      jLabel4.setLocation(new Point(6, 153));
      jLabel4.setSize(new Dimension(300, 23));
      jLabel4.setForeground(Color.blue);
      jLabel4.setText("Résultat :");
      jLabel3 = new JLabel();
      jLabel3.setText("<html>Souhaitez-vous attribuer ce résultat à cette équipe <br>pour cette étape de ce parcours?</html>");
      jLabel3.setSize(new Dimension(479, 40));
      jLabel3.setLocation(new Point(5, 5));
      jPanel = new JPanel();
      jPanel.setPreferredSize(new Dimension(1091, 530));
      jPanel.setLayout(null);
      jPanel.add(jLabel, null);
      jPanel.add(getJComboBoxParcours(), null);
      jPanel.add(jLabel1, null);
      jPanel.add(getJComboBoxEtapes(), null);
      jPanel.add(jLabel2, null);
      jPanel.add(getJComboBoxEquipes(), null);
      jPanel.add(getJScrollPane(), null);
      jPanel.add(jLabel3, null);
      jPanel.add(jLabel4, null);
      jPanel.add(jLabelMessage, null);
      jPanel.add(getJButtonRefresh(), null);
      jPanel.add(getScrollPane());
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
      flowLayout.setHgap(30);
      jPanel1 = new JPanel();
      jPanel1.setLayout(flowLayout);
      jPanel1.add(getJButtonOk(), null);
      jPanel1.add(getJButtonOkPrint(), null);
      jPanel1.add(getJButtonPrint(), null);
      jPanel1.add(getJButtonCreer(), null);
      jPanel1.add(getJButtonAnnuler(), null);
    }
    return jPanel1;
  }

  /**
   * This method initializes jComboBoxParcours	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<Parcours> getJComboBoxParcours()
  {
    if (jComboBoxParcours == null)
    {
      jComboBoxParcours = new JComboBox<Parcours>();
      jComboBoxParcours.setPreferredSize(new Dimension(200, 25));
      jComboBoxParcours.setSize(new Dimension(200, 25));
      jComboBoxParcours.setLocation(new Point(75, 50));
      jComboBoxParcours.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxParcours.getSelectedIndex()!=-1)
          {
            jComboBoxEtapes.setModel(new DefaultComboBoxModel<Etape>(((Parcours)(jComboBoxParcours.getSelectedItem())).getEtapes().getEtapes()));
            jComboBoxEquipes.setModel(new DefaultComboBoxModel<Equipe>(((Parcours)(jComboBoxParcours.getSelectedItem())).getEquipes().getEquipes()));
            if(equipeValide)
            {
              jComboBoxEtapes.setSelectedItem(resultatPuce.getEtape());
            }
            else
            {
              jComboBoxEtapes.setSelectedIndex(0);
            }
            miseAJourResultat();
          }
        }
      });
    }
    return jComboBoxParcours;
  }

  /**
   * This method initializes jComboBoxEtapes	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<Etape> getJComboBoxEtapes()
  {
    if (jComboBoxEtapes == null)
    {
      jComboBoxEtapes = new JComboBox<Etape>();
      jComboBoxEtapes.setPreferredSize(new Dimension(200, 25));
      jComboBoxEtapes.setLocation(new Point(75, 82));
      jComboBoxEtapes.setSize(new Dimension(200, 25));
      jComboBoxEtapes.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(equipeValide)
          {
            jComboBoxEquipes.setSelectedItem(resultatPuce.getEquipe());
          }
          else
          {
            jComboBoxEquipes.setSelectedIndex(0);
          }
          miseAJourResultat();
        }
      });
    }
    return jComboBoxEtapes;
  }

  /**
   * This method initializes jComboBoxEquipes	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<Equipe> getJComboBoxEquipes()
  {
    if (jComboBoxEquipes == null)
    {
      jComboBoxEquipes = new JComboBox<Equipe>();
      jComboBoxEquipes.setPreferredSize(new Dimension(200, 25));
      jComboBoxEquipes.setLocation(new Point(75, 116));
      jComboBoxEquipes.setSize(new Dimension(200, 25));
      jComboBoxEquipes.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          miseAJourResultat();
        }
      });
    }
    return jComboBoxEquipes;
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
      jScrollPane.setPreferredSize(new Dimension(300, 50));
      jScrollPane.setLocation(new Point(5, 178));
      jScrollPane.setSize(new Dimension(480, 164));
      jScrollPane.setViewportView(getJEditorPaneResultat());
    }
    return jScrollPane;
  }

  /**
   * This method initializes jButtonOkPrint	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonOkPrint()
  {
    if (jButtonOkPrint == null)
    {
      jButtonOkPrint = new JButton();
      jButtonOkPrint.setPreferredSize(new Dimension(50, 50));
      jButtonOkPrint.setToolTipText("Valider et imprimer le résultat");
      jButtonOkPrint.setIcon(new ImageIcon(getClass().getResource("/icones/okPrint.png")));
      jButtonOkPrint.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            printResultat();
          resultatPuce.setParcours((Parcours)jComboBoxParcours.getSelectedItem());
          resultatPuce.setEtape((Etape)jComboBoxEtapes.getSelectedItem());
          resultatPuce.setEquipe((Equipe)jComboBoxEquipes.getSelectedItem());
          resultatPuce.getEquipe().setIdPuce(resultatPuce.getPuce().getIdPuce());
          
          ResultatPuce rp = owner.geRaid.existeResultatPuce(resultatPuce);
          if(rp==null)
          {
            owner.geRaid.getResultatsPuce().addResultatPuce(resultatPuce);
            owner.jMenuItemEnregistrer.doClick();
          }
          else
          {
            owner.geRaid.removeResultatPuce(rp);
            owner.geRaid.addResultatPuce(resultatPuce);
          }
          dispose();
        }
      });
    }
    return jButtonOkPrint;
  }

  /**
   * This method initializes jButtonCreer	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonCreer()
  {
    if (jButtonCreer == null)
    {
      jButtonCreer = new JButton();
      jButtonCreer.setPreferredSize(new Dimension(50, 50));
      jButtonCreer.setEnabled(false);
      jButtonCreer.setToolTipText("Créer une nouvelle équipe");
      jButtonCreer.setIcon(new ImageIcon(getClass().getResource("/icones/creer.png")));
      jButtonCreer.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          Equipe equipe = new Equipe(owner.geRaid);
          equipe.setIdPuce(resultatPuce.getPuce().getIdPuce());
          equipe.setNom(resultatPuce.getPuce().getIdPuce());
          equipe.setDossard(resultatPuce.getPuce().getIdPuce());
          equipe.setCategorie(owner.geRaid.getCategorie().getCategories().get(0));

          resultatPuce.setParcours((Parcours)jComboBoxParcours.getSelectedItem());
          resultatPuce.setEtape((Etape)jComboBoxEtapes.getSelectedItem());
          resultatPuce.setEquipe(equipe);

          if(((Parcours)jComboBoxParcours.getSelectedItem()).getEquipes().addEquipe(equipe))
          {
            owner.geRaid.getResultatsPuce().addResultatPuce(resultatPuce);
            owner.majListeEquipes();
            owner.jMenuItemEnregistrer.doClick();
            dispose();
          }
          else
          {
            owner.bip.play();
            StringBuffer msg = new StringBuffer("<html>Cette puce est déjà attribuée à une équipe.<br>");
            msg.append("Vous ne pouvez pas créer une nouvelle équipe avec cette puce.</html>");
            jLabelMessage.setText(msg.toString());
          }
        }
      });
    }
    return jButtonCreer;
  }
  
  private void miseAJourResultat()
  {
    resultatPuce.setParcours((Parcours)jComboBoxParcours.getSelectedItem());
    resultatPuce.setEtape((Etape)jComboBoxEtapes.getSelectedItem());
    if(equipeValide)
    {
      resultatPuce.setEquipe((Equipe)jComboBoxEquipes.getSelectedItem());
    }
    else
    {
      resultatPuce.setEquipe((Equipe)jComboBoxEquipes.getItemAt(0));
    }
    ResultatEquipe re = new ResultatEquipe(resultatPuce, 0, 0);
    lblPostes.setText(re.listeCodeOkPm.toHtml());
    re.saveHtml(owner.geRaid, resultatReduit);
    try
    {
      jEditorPaneResultat.setDocument(new HTMLDocument());
      String adresse = new File(".").getCanonicalPath().toString();
      jEditorPaneResultat.setPage("file:///" + adresse + "/temp.html");
    }
    catch (IOException e)
    {
      System.err.format("Cannot print %s%n", e.getMessage());
    }

    if(equipeValide)
    {
      if(resultatPuce.getPuce().getIdPuce().compareTo(((Equipe)jComboBoxEquipes.getSelectedItem()).getIdPuce())==0)
      {
        if(owner.geRaid.existeResultatPuce(resultatPuce)!= null)
        {
          StringBuffer message = new StringBuffer("<html>Un résultat existe déjà pour cette équipe sur cette étape.<br>");
          message.append("En validant ce résultat, vous remplacerez l'ancien.</html>");
          jLabelMessage.setText(message.toString());
          miseAJourBoutons(false);
        }
        else
        {
          jLabelMessage.setText("");
          miseAJourBoutons(false);
        }
      }
      else
      {
        StringBuffer message = new StringBuffer("<html>Cette puce ne correspond pas à l'équipe sélectionnée.<br>");
        message.append("Vous ne pouvez pas valider ce résultat.</html>");
        jLabelMessage.setText(message.toString());
        miseAJourBoutons(true);
      }
    }
    else
    {
      StringBuffer message = new StringBuffer("<html>Le numéro de la puce insérée ne correspond à aucune équipe du raid.<br>");
      message.append("Vous pouvez attribuer ce résultat à une équipe existante ou créer une nouvelle équipe.</html>");
      jLabelMessage.setText(message.toString());
      miseAJourBoutons(false);
    }
  }
  
  private void miseAJourBoutons(boolean oKImpossible)
  {
    if(equipeValide)
    {
      if(oKImpossible)
      {
        jButtonOk.setEnabled(false);
        jButtonOkPrint.setEnabled(false);
        jButtonPrint.setEnabled(false);
      }
      else
      {
        jButtonOk.setEnabled(true);
        jButtonOkPrint.setEnabled(true);
        jButtonPrint.setEnabled(true);
      }
      jButtonCreer.setEnabled(false);
    }
    else
    {
      jButtonOk.setEnabled(true);
      jButtonOkPrint.setEnabled(true);
      jButtonPrint.setEnabled(true);
      jButtonCreer.setEnabled(true);
    }
    if(visu)
    {
      jButtonPrint.setEnabled(true);
    }
  }

  /**
   * This method initializes jEditorPaneResultat	
   * 	
   * @return javax.swing.JEditorPane	
   */
  private JEditorPane getJEditorPaneResultat()
  {
    if (jEditorPaneResultat == null)
    {
      jEditorPaneResultat = new JEditorPane();
      jEditorPaneResultat.setEditable(false);
      jEditorPaneResultat.setDocument(new HTMLDocument());
    //Set the custom HTMLEditorKit
      jEditorPaneResultat.setEditorKit(new LargeHTMLEditorKit(zoom));
      //Set the zoom to 150%
      //jEditorPaneResultat.getDocument().putProperty("ZOOM_FACTOR", new Double(1.5));
    }
    return jEditorPaneResultat;
  }

  /**
   * This method initializes jButtonPrint	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonPrint()
  {
    if (jButtonPrint == null)
    {
      jButtonPrint = new JButton();
      jButtonPrint.setToolTipText("Imprimer le r\u00E9sultat");
      jButtonPrint.setPreferredSize(new Dimension(50, 50));
      jButtonPrint.setIcon(new ImageIcon(getClass().getResource("/icones/print.png")));
      jButtonPrint.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
            printResultat();
            ((LargeHTMLEditorKit)jEditorPaneResultat.getEditorKit()).setZoomFactor(zoom);
            jButtonRefresh.doClick();
        }
      });
    }
    return jButtonPrint;
  }
  
  private void printResultat()
  {
    ((LargeHTMLEditorKit)jEditorPaneResultat.getEditorKit()).setZoomFactor(1.0);
    jButtonRefresh.doClick();
    //MessageFormat header = new MessageFormat(owner.geRaid.nomRaid);
    //MessageFormat footer = new MessageFormat("Cette épreuve est gérée par le logiciel GeRaid.");
    PrinterJob job = PrinterJob.getPrinterJob();
    //System.out.println(job.);
    PageFormat format = job.validatePage(new PageFormat());
    int largeur = (int) (format.getWidth() - 10);
    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

    pras.add(new MediaPrintableArea(5, 5, largeur, jEditorPaneResultat.getHeight(), MediaPrintableArea.MM));
    try
    {
      jEditorPaneResultat.print(null, null, false, null, pras, false);
    }
    catch (PrinterException e1)
    {
      System.out.println(e1.getMessage());
    }
  }

  /**
   * This method initializes jButtonRefresh	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonRefresh()
  {
    if (jButtonRefresh == null)
    {
      jButtonRefresh = new JButton();
      jButtonRefresh.setPreferredSize(new Dimension(30, 30));
      jButtonRefresh.setLocation(new Point(441, 145));
      jButtonRefresh.setSize(new Dimension(30, 30));
      jButtonRefresh.setToolTipText("Rafraichir l'affichage");
      jButtonRefresh.setIcon(new ImageIcon(getClass().getResource("/icones/reload.png")));
      jButtonRefresh.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          try
          {
            //jEditorPaneResultat.setDocument(new HTMLDocument());
            String adresse = new File(".").getCanonicalPath().toString();
            jEditorPaneResultat.setPage("file:///" + adresse + "/temp.html");
          }
          catch (IOException et)
          {
            System.err.format("Cannot print %s%n", et.getMessage());
          }
        }
      });
    }
    return jButtonRefresh;
  }
  private JScrollPane getScrollPane() {
    if (scrollPane == null) {
    	scrollPane = new JScrollPane();
    	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	scrollPane.setBounds(5, 389, 480, 79);
    	scrollPane.setViewportView(getLblPostes());
    	scrollPane.setPreferredSize(new Dimension(480, 40));
    }
    return scrollPane;
  }
  private JLabel getLblPostes() {
    if (lblPostes == null) {
    	lblPostes = new JLabel("");
    	lblPostes.setPreferredSize(new Dimension(480, 0));
    }
    return lblPostes;
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
