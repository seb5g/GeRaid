/**
 * 
 */
package ihm;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.WindowConstants;

import to.PenaliteIndividuelle;

import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
public class IhmValeursPenalite extends JDialog
{
  private PenaliteIndividuelle penalite[];
  private IhmPenalite owner = null;
  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JButton jButtonOk = null;

  private JButton jButtonAnnuler = null;
  private JPanel panel;
  private JRadioButton rdbtnBonification;
  private JRadioButton rdbtnPnalit;
  private JPanel panel_1;
  private JSpinner spinner_pts;
  private JLabel lblNewLabel;
  private JPanel panel_2;
  private JSpinner spinner_h;
  private JLabel lblHeures;
  private JSpinner spinner_mn;
  private JLabel lblMn;
  private JSpinner spinner_s;
  private JLabel lblS;

  /**
   * @param owner
   */
  public IhmValeursPenalite(IhmPenalite owner, PenaliteIndividuelle[] p, boolean creation)
  {
    super(owner);
    this.owner = owner;
    this.penalite = p;
    initialize();
    this.getRootPane().setDefaultButton(jButtonOk);
    setTitle("Gestion d'une pénalité");
    initPoints();
    initTemps();
  }
  
  private void initPoints()
  {
    if(penalite[0].getPoint()==0 && penalite[0].getTemps()<0)
    {
      rdbtnBonification.setSelected(true);
    }
    if(penalite[0].getPoint()>0 && penalite[0].getTemps()<=0)
    {
      rdbtnBonification.setSelected(true);
    }
    spinner_pts.setValue(Math.abs(penalite[0].getPoint()));
  }
  
  private void initTemps()
  {
    int heures = (int) Math.floor(Math.abs(penalite[0].getTemps())/3600);
    int minutes = (int) Math.floor((Math.abs(penalite[0].getTemps())- heures*3600)/60);
    int secondes = Math.abs(penalite[0].getTemps())- heures*3600 - minutes*60;
    spinner_h.setValue(heures);
    spinner_mn.setValue(minutes);
    spinner_s.setValue(secondes);
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(227, 276);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setModal(true);
    this.setTitle("Pénalité");
    this.setResizable(false);
    this.setContentPane(getJContentPane());
    ButtonGroup bg = new ButtonGroup();
    bg.add(rdbtnBonification);
    bg.add(rdbtnPnalit);
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
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setHgap(40);
      jContentPane = new JPanel();
      jContentPane.setLayout(flowLayout);
      jContentPane.add(getPanel());
      jContentPane.add(getPanel_1());
      jContentPane.add(getPanel_2());
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
      jButtonOk.setToolTipText("Validation des données");
      jButtonOk.setIcon(new ImageIcon(getClass().getResource("/icones/ok64.png")));
      jButtonOk.addActionListener(new java.awt.event.ActionListener()
      {
        public void actionPerformed(java.awt.event.ActionEvent e)
        {
          for(int i=0; i<penalite.length; i++)
          {
            if(rdbtnBonification.isSelected())
            {
              penalite[i].setPoint((int) spinner_pts.getValue());
              if(((int)spinner_h.getValue() +  (int)spinner_mn.getValue() + (int)spinner_s.getValue()) != 0)
                {
                  penalite[i].setTemps(((int)(spinner_h.getValue())*3600 + (int)(spinner_mn.getValue())*60 + (int)(spinner_s.getValue()))*(-1));
                }
              else
              {
                penalite[i].setTemps(0);
              }
            }
            else
            {
              if((int) spinner_pts.getValue() != 0)
              {
                penalite[i].setPoint((int) spinner_pts.getValue()*(-1));
              }
              else
              {
                penalite[i].setPoint(0);
              }
              penalite[i].setTemps(((int)(spinner_h.getValue())*3600 + (int)(spinner_mn.getValue())*60 + (int)(spinner_s.getValue())));
            }
              owner.addPenaliteIndividuelle(penalite[i]);
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

  private JPanel getPanel() {
    if (panel == null) {
    	panel = new JPanel();
    	panel.setBorder(new TitledBorder(null, "Type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	panel.add(getRdbtnBonification());
    	panel.add(getRdbtnPnalit());
    }
    return panel;
  }
  private JRadioButton getRdbtnBonification() {
    if (rdbtnBonification == null) {
    	rdbtnBonification = new JRadioButton("Bonification");
    }
    return rdbtnBonification;
  }
  private JRadioButton getRdbtnPnalit() {
    if (rdbtnPnalit == null) {
    	rdbtnPnalit = new JRadioButton("P\u00E9nalit\u00E9");
    	rdbtnPnalit.setSelected(true);
    }
    return rdbtnPnalit;
  }
  private JPanel getPanel_1() {
    if (panel_1 == null) {
    	panel_1 = new JPanel();
    	panel_1.setBorder(new TitledBorder(null, "Points", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	panel_1.add(getSpinner_pts());
    	panel_1.add(getLblNewLabel());
    }
    return panel_1;
  }
  private JSpinner getSpinner_pts() {
    if (spinner_pts == null) {
    	spinner_pts = new JSpinner();
    	spinner_pts.setModel(new SpinnerNumberModel(0, 0, 100, 10));
    }
    return spinner_pts;
  }
  private JLabel getLblNewLabel() {
    if (lblNewLabel == null) {
    	lblNewLabel = new JLabel("Points");
    }
    return lblNewLabel;
  }
  private JPanel getPanel_2() {
    if (panel_2 == null) {
    	panel_2 = new JPanel();
    	panel_2.setBorder(new TitledBorder(null, "Temps", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	panel_2.add(getSpinner_h());
    	panel_2.add(getLblHeures());
    	panel_2.add(getSpinner_mn());
    	panel_2.add(getLblMn());
    	panel_2.add(getSpinner_s());
    	panel_2.add(getLblS());
    }
    return panel_2;
  }
  private JSpinner getSpinner_h() {
    if (spinner_h == null) {
    	spinner_h = new JSpinner();
    	spinner_h.setModel(new SpinnerNumberModel(0, 0, 23, 1));
    }
    return spinner_h;
  }
  private JLabel getLblHeures() {
    if (lblHeures == null) {
    	lblHeures = new JLabel("h");
    }
    return lblHeures;
  }
  private JSpinner getSpinner_mn() {
    if (spinner_mn == null) {
    	spinner_mn = new JSpinner();
    	spinner_mn.setModel(new SpinnerNumberModel(0, 0, 59, 1));
    }
    return spinner_mn;
  }
  private JLabel getLblMn() {
    if (lblMn == null) {
    	lblMn = new JLabel("mn");
    }
    return lblMn;
  }
  private JSpinner getSpinner_s() {
    if (spinner_s == null) {
    	spinner_s = new JSpinner();
    	spinner_s.setModel(new SpinnerNumberModel(0, 0, 59, 1));
    }
    return spinner_s;
  }
  private JLabel getLblS() {
    if (lblS == null) {
    	lblS = new JLabel("s");
    }
    return lblS;
  }
}  //  @jve:decl-index=0:visual-constraint="10,10"
