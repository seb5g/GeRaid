/**
 * 
 */
package ihm;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;

import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.JList;

import outils.TimeManager;

import to.Equipe;
import to.Etape;
import to.Parcours;
import to.Partiel;
import to.ResultatPuce;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

/**
 * <P>
 * Titre : IhmGestionResultatPuce.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class IhmGestionResultatPuce extends JDialog
{
  private IhmGeRaidMain owner = null;
  
  public ResultatPuce resultatCourant = null;

  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JPanel jPanelNord = null;

  private JPanel jPanelParcours = null;

  private JLabel jLabel = null;

  private JComboBox<Parcours> jComboBoxParcours = null;

  private JPanel jPanelEtape = null;

  private JLabel jLabel1 = null;

  private JComboBox<Etape> jComboBoxEtape = null;

  private JPanel jPanelEquipe = null;

  private JLabel jLabel2 = null;

  private JComboBox<Equipe> jComboBoxEquipe = null;

  private JPanel jPanelCenter = null;

  private JPanel jPaneltemps = null;

  private JPanel jPanelStart = null;

  private JLabel jLabel3 = null;

  private JSpinner jSpinnerHeureD = null;

  private JLabel jLabel4 = null;

  private JSpinner jSpinnerMinuteD = null;

  private JLabel jLabel5 = null;

  private JSpinner jSpinnerSecondeD = null;

  private JPanel jPanelFinish = null;

  private JLabel jLabel31 = null;

  private JSpinner jSpinnerHeureA = null;

  private JLabel jLabel41 = null;

  private JSpinner jSpinnerMinuteA = null;

  private JLabel jLabel51 = null;

  private JSpinner jSpinnerSecondeA = null;

  private JPanel jPanelPostes = null;

  private JPanel jPanel = null;

  private JButton jButtonAjouter = null;

  private JButton jButtonEdit = null;

  private JButton jButtonSupprimer = null;

  private JPanel jPanel1 = null;

  private JButton jButtonUp = null;

  private JButton jButtonDown = null;

  private JPanel jPanelSud = null;

  private JButton jButtonSupprimerResultat = null;

  private JLabel jLabel6 = null;

  private JScrollPane jScrollPane = null;

  public JList<Partiel> jListPostes = null;

  private JLabel jLabelMessage = null;

  private JLabel jLabel7 = null;

  private JButton jButtonVisu = null;

  /**
   * @param owner
   */
  public IhmGestionResultatPuce(IhmGeRaidMain owner)
  {
    super(owner);
    initialize();
    this.owner = owner;
    jPanelCenter.setVisible(false);
    jLabelMessage.setText("<html>Attention, les modifications effectuées dans cette fenêtre sont " +
    		"immédiatement prises en compte.</html>");
    initParcours();
  }
  
  public IhmGestionResultatPuce(IhmGeRaidMain owner, Parcours parcours, Etape etape, Equipe equipe)
  {
    super(owner);
    initialize();
    this.owner = owner;
    jPanelCenter.setVisible(false);
    jLabelMessage.setText("<html>Attention, les modifications effectuées dans cette fenêtre sont " +
        "immédiatement prises en compte.</html>");
    initParcours();
    jComboBoxParcours.setSelectedItem(parcours);
    jComboBoxEtape.setSelectedItem(etape);
    jComboBoxEquipe.setSelectedItem(equipe);
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(268, 720);
    this.setModal(true);
    this.setResizable(false);
    this.setTitle("Gestion des résultats");
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
      jContentPane = new JPanel();
      jContentPane.setLayout(new BorderLayout());
      jContentPane.add(getJPanelNord(), BorderLayout.NORTH);
      jContentPane.add(getJPanelCenter(), BorderLayout.CENTER);
      jContentPane.add(getJPanelSud(), BorderLayout.SOUTH);
    }
    return jContentPane;
  }

  /**
   * This method initializes jPanelNord	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelNord()
  {
    if (jPanelNord == null)
    {
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setAlignment(FlowLayout.LEFT);
      jPanelNord = new JPanel();
      jPanelNord.setPreferredSize(new Dimension(806, 155));
      jPanelNord.setBorder(BorderFactory.createTitledBorder(null, "Choix du résultat", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
      jPanelNord.setLayout(flowLayout);
      jPanelNord.add(getJPanelParcours(), null);
      jPanelNord.add(getJPanelEtape(), null);
      jPanelNord.add(getJPanelEquipe(), null);
    }
    return jPanelNord;
  }

  /**
   * This method initializes jPanelParcours	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelParcours()
  {
    if (jPanelParcours == null)
    {
      jLabel = new JLabel();
      jLabel.setText("Parcours :");
      jLabel.setPreferredSize(new Dimension(70, 16));
      FlowLayout flowLayout1 = new FlowLayout();
      flowLayout1.setAlignment(FlowLayout.LEFT);
      jPanelParcours = new JPanel();
      jPanelParcours.setLayout(flowLayout1);
      jPanelParcours.add(jLabel, null);
      jPanelParcours.add(getJComboBoxParcours(), null);
    }
    return jPanelParcours;
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
      jComboBoxParcours.setPreferredSize(new Dimension(150, 25));
      jComboBoxParcours.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxParcours.getSelectedIndex() != -1)
          {
            jComboBoxEtape.setModel(new DefaultComboBoxModel<Etape>(owner.geRaid.getResultatsPuce().getEtapes((Parcours) jComboBoxParcours.getSelectedItem())));
            if(jComboBoxEtape.getItemCount()>0)
            {
              jComboBoxEtape.setSelectedIndex(0);
            }
            else
            {
              jComboBoxEtape.setSelectedIndex(-1);
            }
          }
        }
      });
    }
    return jComboBoxParcours;
  }

  /**
   * This method initializes jPanelEtape	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelEtape()
  {
    if (jPanelEtape == null)
    {
      jLabel1 = new JLabel();
      jLabel1.setText("Etape :");
      jLabel1.setPreferredSize(new Dimension(70, 16));
      FlowLayout flowLayout2 = new FlowLayout();
      flowLayout2.setAlignment(FlowLayout.LEFT);
      jPanelEtape = new JPanel();
      jPanelEtape.setLayout(flowLayout2);
      jPanelEtape.add(jLabel1, null);
      jPanelEtape.add(getJComboBoxEtape(), null);
    }
    return jPanelEtape;
  }

  /**
   * This method initializes jComboBoxEtape	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<Etape> getJComboBoxEtape()
  {
    if (jComboBoxEtape == null)
    {
      jComboBoxEtape = new JComboBox<Etape>();
      jComboBoxEtape.setPreferredSize(new Dimension(150, 25));
      jComboBoxEtape.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxEtape.getSelectedIndex() != -1)
          {
            jComboBoxEquipe.setModel(new DefaultComboBoxModel<Equipe>(owner.geRaid.getResultatsPuce().getEquipes( (Etape) jComboBoxEtape.getSelectedItem())));
            if(jComboBoxEquipe.getItemCount()>0)
            {
              jComboBoxEquipe.setSelectedIndex(0);
            }
            else
            {
              jComboBoxEquipe.setSelectedIndex(-1);
            }
          }
        }
      });
    }
    return jComboBoxEtape;
  }

  /**
   * This method initializes jPanelEquipe	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelEquipe()
  {
    if (jPanelEquipe == null)
    {
      jLabel2 = new JLabel();
      jLabel2.setText("Equipe :");
      jLabel2.setPreferredSize(new Dimension(70, 16));
      FlowLayout flowLayout3 = new FlowLayout();
      flowLayout3.setAlignment(FlowLayout.LEFT);
      jPanelEquipe = new JPanel();
      jPanelEquipe.setLayout(flowLayout3);
      jPanelEquipe.add(jLabel2, null);
      jPanelEquipe.add(getJComboBoxEquipe(), null);
    }
    return jPanelEquipe;
  }

  /**
   * This method initializes jComboBoxEquipe	
   * 	
   * @return javax.swing.JComboBox	
   */
  private JComboBox<Equipe> getJComboBoxEquipe()
  {
    if (jComboBoxEquipe == null)
    {
      jComboBoxEquipe = new JComboBox<Equipe>();
      jComboBoxEquipe.setPreferredSize(new Dimension(150, 25));
      jComboBoxEquipe.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jComboBoxEquipe.getSelectedIndex() != -1)
          {
            jPanelCenter.setVisible(true);
            resultatCourant = owner.geRaid.getResultatsPuce().getResultatPuce((Etape)jComboBoxEtape.getSelectedItem(),
                (Equipe)jComboBoxEquipe.getSelectedItem());
            jSpinnerHeureD.setValue((Integer)TimeManager.getHeures(resultatCourant.getPuce().getStarttime()));
            jSpinnerMinuteD.setValue((Integer)TimeManager.getMinutes(resultatCourant.getPuce().getStarttime()));
            jSpinnerSecondeD.setValue((Integer)TimeManager.getSecondes(resultatCourant.getPuce().getStarttime()));
            jSpinnerHeureA.setValue((Integer)TimeManager.getHeures(resultatCourant.getPuce().getFinishtime()));
            jSpinnerMinuteA.setValue((Integer)TimeManager.getMinutes(resultatCourant.getPuce().getFinishtime()));
            jSpinnerSecondeA.setValue((Integer)TimeManager.getSecondes(resultatCourant.getPuce().getFinishtime()));
            jListPostes.setListData(resultatCourant.getPuce().getPartiels());
            jListPostes.setSelectedIndex(0);
          }
          else
          {
            jPanelCenter.setVisible(false);
          }
        }
      });
    }
    return jComboBoxEquipe;
  }

  /**
   * This method initializes jPanelCenter	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelCenter()
  {
    if (jPanelCenter == null)
    {
      jPanelCenter = new JPanel();
      jPanelCenter.setBorder(BorderFactory.createTitledBorder(null, "Résultat", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
      jPanelCenter.setLayout(new BorderLayout());
      jPanelCenter.add(getJPaneltemps(), BorderLayout.NORTH);
      jPanelCenter.add(getJPanelPostes(), BorderLayout.CENTER);
    }
    return jPanelCenter;
  }

  /**
   * This method initializes jPaneltemps	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPaneltemps()
  {
    if (jPaneltemps == null)
    {
      jLabel7 = new JLabel();
      jLabel7.setText("Visualiser ce résultat :");
      jLabel6 = new JLabel();
      jLabel6.setText("Supprimer ce résultat :");
      FlowLayout flowLayout4 = new FlowLayout();
      flowLayout4.setAlignment(FlowLayout.LEFT);
      jPaneltemps = new JPanel();
      jPaneltemps.setPreferredSize(new Dimension(463, 145));
      jPaneltemps.setLayout(flowLayout4);
      jPaneltemps.add(jLabel6, null);
      jPaneltemps.add(getJButtonSupprimerResultat(), null);
      jPaneltemps.add(jLabel7, null);
      jPaneltemps.add(getJButtonVisu(), null);
      jPaneltemps.add(getJPanelStart(), null);
      jPaneltemps.add(getJPanelFinish(), null);
    }
    return jPaneltemps;
  }

  /**
   * This method initializes jPanelStart	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelStart()
  {
    if (jPanelStart == null)
    {
      jLabel5 = new JLabel();
      jLabel5.setText("mn");
      jLabel4 = new JLabel();
      jLabel4.setText("h");
      jLabel3 = new JLabel();
      jLabel3.setText("Départ :");
      jLabel3.setPreferredSize(new Dimension(50, 16));
      FlowLayout flowLayout5 = new FlowLayout();
      flowLayout5.setAlignment(FlowLayout.LEFT);
      jPanelStart = new JPanel();
      jPanelStart.setLayout(flowLayout5);
      jPanelStart.add(jLabel3, null);
      jPanelStart.add(getJSpinnerHeureD(), null);
      jPanelStart.add(jLabel4, null);
      jPanelStart.add(getJSpinnerMinuteD(), null);
      jPanelStart.add(jLabel5, null);
      jPanelStart.add(getJSpinnerSecondeD(), null);
    }
    return jPanelStart;
  }

  /**
   * This method initializes jSpinnerHeureD	
   * 	
   * @return javax.swing.JTextField	
   */
  private JSpinner getJSpinnerHeureD()
  {
    if (jSpinnerHeureD == null)
    {
      jSpinnerHeureD = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
      jSpinnerHeureD.setPreferredSize(new Dimension(40, 20));
      jSpinnerHeureD.addChangeListener(new javax.swing.event.ChangeListener()
      {
        public void stateChanged(javax.swing.event.ChangeEvent e)
        {
          Date st = resultatCourant.getPuce().getStarttime();
          st = TimeManager.setHeure(st, (Integer) jSpinnerHeureD.getValue());
          resultatCourant.getPuce().setStarttime(st);
        }
      });
    }
    return jSpinnerHeureD;
  }

  /**
   * This method initializes jSpinnerMinuteD	
   * 	
   * @return javax.swing.JTextField	
   */
  private JSpinner getJSpinnerMinuteD()
  {
    if (jSpinnerMinuteD == null)
    {
      jSpinnerMinuteD = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
      jSpinnerMinuteD.setPreferredSize(new Dimension(40, 20));
      jSpinnerMinuteD.addChangeListener(new javax.swing.event.ChangeListener()
      {
        public void stateChanged(javax.swing.event.ChangeEvent e)
        {
          Date st = resultatCourant.getPuce().getStarttime();
          st = TimeManager.setMinute(st, (Integer) jSpinnerMinuteD.getValue());
          resultatCourant.getPuce().setStarttime(st);
        }
      });
    }
    return jSpinnerMinuteD;
  }

  /**
   * This method initializes jSpinnerSecondeD	
   * 	
   * @return javax.swing.JTextField	
   */
  private JSpinner getJSpinnerSecondeD()
  {
    if (jSpinnerSecondeD == null)
    {
      jSpinnerSecondeD = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
      jSpinnerSecondeD.setPreferredSize(new Dimension(40, 20));
      jSpinnerSecondeD.addChangeListener(new javax.swing.event.ChangeListener()
      {
        public void stateChanged(javax.swing.event.ChangeEvent e)
        {
          Date st = resultatCourant.getPuce().getStarttime();
          st = TimeManager.setSeconde(st, (Integer) jSpinnerSecondeD.getValue());
          resultatCourant.getPuce().setStarttime(st);
        }
      });
    }
    return jSpinnerSecondeD;
  }

  /**
   * This method initializes jPanelFinish	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelFinish()
  {
    if (jPanelFinish == null)
    {
      jLabel51 = new JLabel();
      jLabel51.setText("mn");
      jLabel41 = new JLabel();
      jLabel41.setText("h");
      jLabel31 = new JLabel();
      jLabel31.setText("Arrivée :");
      jLabel31.setPreferredSize(new Dimension(50, 16));
      FlowLayout flowLayout51 = new FlowLayout();
      flowLayout51.setAlignment(FlowLayout.LEFT);
      jPanelFinish = new JPanel();
      jPanelFinish.setLayout(flowLayout51);
      jPanelFinish.add(jLabel31, null);
      jPanelFinish.add(getJSpinnerHeureA(), null);
      jPanelFinish.add(jLabel41, null);
      jPanelFinish.add(getJSpinnerMinuteA(), null);
      jPanelFinish.add(jLabel51, null);
      jPanelFinish.add(getJSpinnerSecondeA(), null);
    }
    return jPanelFinish;
  }

  /**
   * This method initializes jSpinnerHeureA	
   * 	
   * @return javax.swing.JTextField	
   */
  private JSpinner getJSpinnerHeureA()
  {
    if (jSpinnerHeureA == null)
    {
      jSpinnerHeureA = new JSpinner(new SpinnerNumberModel(0, 0, 47, 1));
      jSpinnerHeureA.setPreferredSize(new Dimension(40, 20));
      jSpinnerHeureA.addChangeListener(new javax.swing.event.ChangeListener()
      {
        public void stateChanged(javax.swing.event.ChangeEvent e)
        {
          Date st = resultatCourant.getPuce().getFinishtime();
          st = TimeManager.setHeure(st, (Integer) jSpinnerHeureA.getValue());
          resultatCourant.getPuce().setFinishtime(st);
        }
      });
    }
    return jSpinnerHeureA;
  }

  /**
   * This method initializes jSpinnerMinuteA	
   * 	
   * @return javax.swing.JTextField	
   */
  private JSpinner getJSpinnerMinuteA()
  {
    if (jSpinnerMinuteA == null)
    {
      jSpinnerMinuteA = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
      jSpinnerMinuteA.setPreferredSize(new Dimension(40, 20));
      jSpinnerMinuteA.addChangeListener(new javax.swing.event.ChangeListener()
      {
        public void stateChanged(javax.swing.event.ChangeEvent e)
        {
          Date st = resultatCourant.getPuce().getFinishtime();
          st = TimeManager.setMinute(st, (Integer) jSpinnerMinuteA.getValue());
          resultatCourant.getPuce().setFinishtime(st);
        }
      });
    }
    return jSpinnerMinuteA;
  }

  /**
   * This method initializes jSpinnerSecondeA	
   * 	
   * @return javax.swing.JTextField	
   */
  private JSpinner getJSpinnerSecondeA()
  {
    if (jSpinnerSecondeA == null)
    {
      jSpinnerSecondeA = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
      jSpinnerSecondeA.setPreferredSize(new Dimension(40, 20));
      jSpinnerSecondeA.addChangeListener(new javax.swing.event.ChangeListener()
      {
        public void stateChanged(javax.swing.event.ChangeEvent e)
        {
          Date st = resultatCourant.getPuce().getFinishtime();
          st = TimeManager.setSeconde(st, (Integer) jSpinnerSecondeA.getValue());
          resultatCourant.getPuce().setFinishtime(st);
        }
      });
    }
    return jSpinnerSecondeA;
  }

  /**
   * This method initializes jPanelPostes	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelPostes()
  {
    if (jPanelPostes == null)
    {
      jPanelPostes = new JPanel();
      jPanelPostes.setLayout(new BorderLayout());
      jPanelPostes.setBorder(BorderFactory.createTitledBorder(null, "Postes", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
      jPanelPostes.setPreferredSize(new Dimension(309, 140));
      jPanelPostes.add(getJPanel(), BorderLayout.NORTH);
      jPanelPostes.add(getJPanel1(), BorderLayout.WEST);
      jPanelPostes.add(getJScrollPane(), BorderLayout.CENTER);
    }
    return jPanelPostes;
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
      FlowLayout flowLayout6 = new FlowLayout();
      flowLayout6.setAlignment(FlowLayout.LEFT);
      jPanel = new JPanel();
      jPanel.setLayout(flowLayout6);
      jPanel.add(getJButtonAjouter(), null);
      jPanel.add(getJButtonEdit(), null);
      jPanel.add(getJButtonSupprimer(), null);
    }
    return jPanel;
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
      jButtonAjouter.setToolTipText("Ajouter un poste");
      jButtonAjouter.setIcon(new ImageIcon(getClass().getResource("/icones/plus.png")));
      jButtonAjouter.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmPartiel ihm = new IhmPartiel(IhmGestionResultatPuce.this, new Partiel(), true);
          ihm.setLocationRelativeTo(IhmGestionResultatPuce.this);
          ihm.setVisible(true);
          jListPostes.setListData(resultatCourant.getPuce().getPartiels());
          jListPostes.setSelectedIndex(0);
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
      jButtonEdit.setToolTipText("Modifier le poste");
      jButtonEdit.setIcon(new ImageIcon(getClass().getResource("/icones/search.png")));
      jButtonEdit.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jListPostes.getModel().getSize()>0)
          {
            IhmPartiel ihm = new IhmPartiel(IhmGestionResultatPuce.this, (Partiel) jListPostes.getSelectedValue(), false);
            ihm.setLocationRelativeTo(IhmGestionResultatPuce.this);
            ihm.setVisible(true);
            jListPostes.setListData(resultatCourant.getPuce().getPartiels());
            jListPostes.setSelectedIndex(0);
          }
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
      jButtonSupprimer.setToolTipText("Supprimer le poste");
      jButtonSupprimer.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
      jButtonSupprimer.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jListPostes.getModel().getSize()>0)
          {
            StringBuffer message = new StringBuffer("<html>Souhaitez-vous supprimer définitivement ce partiel?</html>");
            IhmPartielDelete ihm = new IhmPartielDelete(IhmGestionResultatPuce.this, message.toString());
            ihm.setLocationRelativeTo(IhmGestionResultatPuce.this);
            ihm.setVisible(true);
            jListPostes.setListData(resultatCourant.getPuce().getPartiels());
            jListPostes.setSelectedIndex(0);
          }
        }
      });
    }
    return jButtonSupprimer;
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
      FlowLayout flowLayout7 = new FlowLayout();
      flowLayout7.setAlignment(FlowLayout.LEFT);
      jPanel1 = new JPanel();
      jPanel1.setPreferredSize(new Dimension(40, 40));
      jPanel1.setLayout(flowLayout7);
      jPanel1.add(getJButtonUp(), null);
      jPanel1.add(getJButtonDown(), null);
    }
    return jPanel1;
  }

  /**
   * This method initializes jButtonUp	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonUp()
  {
    if (jButtonUp == null)
    {
      jButtonUp = new JButton();
      jButtonUp.setPreferredSize(new Dimension(30, 30));
      jButtonUp.setToolTipText("Monter le poste");
      jButtonUp.setIcon(new ImageIcon(getClass().getResource("/icones/up.png")));
      jButtonUp.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jListPostes.getSelectedIndex()>=0)
          {
            resultatCourant.getPuce().upBalise((Partiel)jListPostes.getSelectedValue());
            jListPostes.repaint();
            jListPostes.setSelectedIndex(jListPostes.getSelectedIndex()-1);
          }
        }
      });
    }
    return jButtonUp;
  }

  /**
   * This method initializes jButtonDown	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonDown()
  {
    if (jButtonDown == null)
    {
      jButtonDown = new JButton();
      jButtonDown.setPreferredSize(new Dimension(30, 30));
      jButtonDown.setMnemonic(KeyEvent.VK_UNDEFINED);
      jButtonDown.setToolTipText("Descendre le poste");
      jButtonDown.setIcon(new ImageIcon(getClass().getResource("/icones/down.png")));
      jButtonDown.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          if(jListPostes.getSelectedIndex()>=0)
          {
            resultatCourant.getPuce().downBalise((Partiel)jListPostes.getSelectedValue());
            jListPostes.repaint();
            jListPostes.setSelectedIndex(jListPostes.getSelectedIndex()+1);
          }
        }
      });
    }
    return jButtonDown;
  }

  /**
   * This method initializes jPanelSud	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelSud()
  {
    if (jPanelSud == null)
    {
      jLabelMessage = new JLabel();
      jLabelMessage.setText("");
      jLabelMessage.setForeground(Color.red);
      jLabelMessage.setPreferredSize(new Dimension(200, 70));
      FlowLayout flowLayout8 = new FlowLayout();
      flowLayout8.setHgap(50);
      jPanelSud = new JPanel();
      jPanelSud.setLayout(flowLayout8);
      jPanelSud.add(jLabelMessage, null);
    }
    return jPanelSud;
  }

  /**
   * This method initializes jButtonSupprimerResultat	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonSupprimerResultat()
  {
    if (jButtonSupprimerResultat == null)
    {
      jButtonSupprimerResultat = new JButton();
      jButtonSupprimerResultat.setPreferredSize(new Dimension(30, 30));
      jButtonSupprimerResultat.setToolTipText("Supprimer ce résultat");
      jButtonSupprimerResultat.setIcon(new ImageIcon(getClass().getResource("/icones/delete.png")));
      jButtonSupprimerResultat.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          StringBuffer message = new StringBuffer("<html>Souhaitez-vous supprimer définitivement ce résultat?</html>");
          IhmResultatPuceDelete ihm = new IhmResultatPuceDelete(IhmGestionResultatPuce.this, message.toString(), owner.geRaid);
          ihm.setLocationRelativeTo(IhmGestionResultatPuce.this);
          ihm.setVisible(true);
        }
      });
    }
    return jButtonSupprimerResultat;
  }

  public void initParcours()
  {
    jComboBoxParcours.setModel(new DefaultComboBoxModel<Parcours>(owner.geRaid.getResultatsPuce().getParcours()));
    if(jComboBoxParcours.getItemCount()>0)
    {
      jComboBoxParcours.setSelectedIndex(0);
      jComboBoxEtape.setVisible(true);
      jComboBoxEquipe.setVisible(true);
      jPanelCenter.setVisible(true);
    }
    else
    {
      jComboBoxParcours.setSelectedIndex(-1);
      jComboBoxEtape.setVisible(false);
      jComboBoxEquipe.setVisible(false);
      jPanelCenter.setVisible(false);
      
    }
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
      jScrollPane.setViewportView(getJListPostes());
      jScrollPane.setViewportView(getJListPostes());
    }
    return jScrollPane;
  }

  /**
   * This method initializes jListPostes	
   * 	
   * @return javax.swing.JList	
   */
  private JList<Partiel> getJListPostes()
  {
    if (jListPostes == null)
    {
      jListPostes = new JList<Partiel>();
      jListPostes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    return jListPostes;
  }

  /**
   * This method initializes jButtonVisu	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getJButtonVisu()
  {
    if (jButtonVisu == null)
    {
      jButtonVisu = new JButton();
      jButtonVisu.setPreferredSize(new Dimension(30, 30));
      jButtonVisu.setIcon(new ImageIcon(getClass().getResource("/icones/podium.png")));
      jButtonVisu.setMnemonic(KeyEvent.VK_UNDEFINED);
      jButtonVisu.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          IhmResultatPuce ihm = new IhmResultatPuce(owner, resultatCourant, true, true, owner.geRaid.resultatReduit);
          ihm.setLocationRelativeTo(IhmGestionResultatPuce.this);
          ihm.setVisible(true);
        }
      });
    }
    return jButtonVisu;
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
