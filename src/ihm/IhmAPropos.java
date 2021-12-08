/**
 * 
 */
package ihm;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.SystemColor;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Point;

/**
 * <P>
 * Titre : IhmAPropos.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class IhmAPropos extends JDialog
{

  private static final long serialVersionUID = 1L;

  private JPanel jContentPane = null;

  private JPanel jPanelSigle = null;

  private JPanel jPanelTexte = null;

  private JPanel jPanelTitre = null;

  private JLabel jLabel = null;

  private JLabel jTextAreaTitre = null;

  private JLabel jTextAreaTexte = null;

  private JPanel jPanelFond = null;

  private JLabel jLabelImage = null;

  /**
   * @param owner
   */
  public IhmAPropos(IhmGeRaidMain owner)
  {
    super(owner);
    initialize();
    StringBuffer titre = new StringBuffer("<html><center><h2>GeRaid est un logiciel de gestion des raids multisports utilisant le système SportIdent.</h2></center></html>");
    jTextAreaTitre.setText(titre.toString());
    StringBuffer texte = new StringBuffer("<html><h4><center>Développeur : Thierry PORRET");
    texte.append("<br>Testeurs : David BERRY, Philippe DURAND");
    texte.append("<br>Merci à Simon DENIER pour GecoSi");
    texte.append("<br><br>Version : " + owner.geRaid.version);
    texte.append("</center></h4></html>");
    jTextAreaTexte.setText(texte.toString());
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize()
  {
    this.setSize(431, 275);
    this.setModal(true);
    this.setTitle("A propos de GeRaid");
    this.setResizable(false);
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
      jLabelImage = new JLabel();
      jLabelImage.setText("");
      jLabelImage.setIcon(new ImageIcon(getClass().getResource("/icones/fond.png")));
      jLabelImage.setVerticalAlignment(SwingConstants.TOP);
      jLabelImage.setHorizontalAlignment(SwingConstants.LEFT);
      jLabelImage.setBounds(new Rectangle(0, 0, 425, 268));
      jContentPane = new JPanel();
      jContentPane.setLayout(null);
      jContentPane.add(getJPanelFond(), null);
      jContentPane.add(jLabelImage, null);
    }
    return jContentPane;
  }

  /**
   * This method initializes jPanelSigle	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelSigle()
  {
    if (jPanelSigle == null)
    {
      FlowLayout flowLayout = new FlowLayout();
      flowLayout.setAlignment(FlowLayout.LEFT);
      jLabel = new JLabel();
      jLabel.setText("");
      jLabel.setIcon(new ImageIcon(getClass().getResource("/icones/g128.png")));
      jPanelSigle = new JPanel();
      jPanelSigle.setLayout(flowLayout);
      jPanelSigle.setOpaque(false);
      jPanelSigle.add(jLabel, null);
    }
    return jPanelSigle;
  }

  /**
   * This method initializes jPanelTexte	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelTexte()
  {
    if (jPanelTexte == null)
    {
      FlowLayout flowLayout1 = new FlowLayout();
      flowLayout1.setHgap(10);
      jPanelTexte = new JPanel();
      jPanelTexte.setOpaque(false);
      jPanelTexte.setLayout(flowLayout1);
      jPanelTexte.add(getJTextAreaTexte(), null);
    }
    return jPanelTexte;
  }

  /**
   * This method initializes jPanelTitre	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelTitre()
  {
    if (jPanelTitre == null)
    {
      GridBagConstraints gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.fill = GridBagConstraints.BOTH;
      gridBagConstraints.weighty = 1.0;
      gridBagConstraints.weightx = 1.0;
      jPanelTitre = new JPanel();
      jPanelTitre.setOpaque(false);
      jPanelTitre.setLayout(new GridBagLayout());
      jPanelTitre.setPreferredSize(new Dimension(430, 70));
      jPanelTitre.add(getJTextAreaTitre(), gridBagConstraints);
    }
    return jPanelTitre;
  }

  /**
   * This method initializes jTextAreaTitre	
   * 	
   * @return javax.swing.JTextArea	
   */
  private JLabel getJTextAreaTitre()
  {
    if (jTextAreaTitre == null)
    {
      jTextAreaTitre = new JLabel();
      jTextAreaTitre.setEnabled(false);
    }
    return jTextAreaTitre;
  }

  /**
   * This method initializes jTextAreaTexte	
   * 	
   * @return javax.swing.JTextArea	
   */
  private JLabel getJTextAreaTexte()
  {
    if (jTextAreaTexte == null)
    {
      jTextAreaTexte = new JLabel();
      jTextAreaTexte.setBackground(SystemColor.control);
      jTextAreaTexte.setEnabled(false);
    }
    return jTextAreaTexte;
  }

  /**
   * This method initializes jPanelFond	
   * 	
   * @return javax.swing.JPanel	
   */
  private JPanel getJPanelFond()
  {
    if (jPanelFond == null)
    {
      jPanelFond = new JPanel();
      jPanelFond.setLayout(new BorderLayout());
      jPanelFond.setOpaque(false);
      jPanelFond.setLocation(new Point(0, 16));
      jPanelFond.setSize(new Dimension(430, 208));
      jPanelFond.add(getJPanelTexte(), BorderLayout.CENTER);
      jPanelFond.add(getJPanelSigle(), BorderLayout.WEST);
      jPanelFond.add(getJPanelTitre(), BorderLayout.NORTH);
    }
    return jPanelFond;
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
