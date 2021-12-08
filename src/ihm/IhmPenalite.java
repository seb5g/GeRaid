/**
 * 
 */
package ihm;

import geRaid.GeRaid;

import inOut.CsvPenalites;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import outils.FiltreFichier;
import outils.Outils;

import to.Etape;
import to.Parcours;
import to.Penalite;
import to.PenaliteIndividuelle;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * <P>
 * Titre : IhmPenalite.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author Thierry
 *
 */
public class IhmPenalite extends JDialog
{
  /**
   * 
   */
  private static final long serialVersionUID = 6593087731537480601L;
  public GeRaid geraid;
  private JTable table;
  private JComboBox<Parcours> comboBoxParcours;
  private JComboBox<Etape> comboBoxEtapes;
  private JScrollPane scrollPane;
  
  public IhmPenalite(GeRaid geraid) 
  {
    setModal(true);
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    setIconImage(Toolkit.getDefaultToolkit().getImage(IhmPenalite.class.getResource("/icones/g16.png")));
    setMinimumSize(new Dimension(600, 400));
    setTitle("Gestion des p\u00E9nalit\u00E9s et bonifications hors \u00E9preuves");
    
    JPanel panel = new JPanel();
    getContentPane().add(panel, BorderLayout.NORTH);
    panel.setLayout(new BorderLayout(0, 0));
    
    JPanel panel_2 = new JPanel();
    panel_2.setBorder(new TitledBorder(null, "Gestion globale", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    panel.add(panel_2, BorderLayout.NORTH);
    
    JLabel lblParcours = new JLabel("Parcours : ");
    panel_2.add(lblParcours);
    
    if(comboBoxParcours == null)
    {
      comboBoxParcours = new JComboBox<Parcours>();
    }
    comboBoxParcours.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent arg0) 
      {
        if(comboBoxParcours.getSelectedIndex()!=-1)
        {
          comboBoxEtapes.setModel(new DefaultComboBoxModel<Etape>(((Parcours)comboBoxParcours.getSelectedItem()).getEtapes().getEtapes()));
          comboBoxEtapes.repaint();
          if(comboBoxEtapes.getItemCount()>0)
          {
            comboBoxEtapes.setSelectedIndex(0);
          }
          else
          {
            comboBoxEtapes.setSelectedIndex(-1);
          }
        }
        else
        {
          comboBoxEtapes.removeAllItems();
          comboBoxEtapes.repaint();
          comboBoxEtapes.setSelectedIndex(-1);
        }
        refreshTable();
      }
    });
    panel_2.add(comboBoxParcours);
    
    JLabel lblEtapes = new JLabel("Etapes : ");
    panel_2.add(lblEtapes);
    
    comboBoxEtapes = new JComboBox<Etape>();
    comboBoxEtapes.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent arg0) 
      {
        refreshTable();
      }
    });
    panel_2.add(comboBoxEtapes);
    
    JButton btnImport = new JButton("");
    btnImport.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) 
      {
        if(comboBoxEtapes.getSelectedIndex()>-1)
        {
          // On ouvre le navigateur
          JFileChooser chooser = new JFileChooser();
          FiltreFichier filter = new FiltreFichier();
          filter.addExtension("csv");
          filter.setDescription("Fichiers csv");
          chooser.setFileFilter(filter);
          chooser.setCurrentDirectory(new File(IhmPenalite.this.geraid.getDossierDefault()));
          
          int returnVal = chooser.showOpenDialog(IhmPenalite.this);
          // Si un fichier a été choisi
          if(returnVal == JFileChooser.APPROVE_OPTION) 
          {
            String fichier = chooser.getSelectedFile().getAbsolutePath();
            CsvPenalites.importer(IhmPenalite.this.geraid,(Parcours)comboBoxParcours.getSelectedItem(), (Etape) comboBoxEtapes.getSelectedItem(), fichier);
            refreshTable();
          }
        }
      }
    });
    btnImport.setToolTipText("Importer une liste de p\u00E9nalit\u00E9s...");
    btnImport.setPreferredSize(new Dimension(32, 32));
    btnImport.setIcon(new ImageIcon(IhmPenalite.class.getResource("/icones/import.png")));
    panel_2.add(btnImport);
    
    JButton btnExport = new JButton("");
    btnExport.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) 
      {
        // On ouvre le navigateur sur le répertoire par défaut
        JFileChooser chooser = new JFileChooser();
        FiltreFichier filter = new FiltreFichier();
        filter.addExtension("csv");
        filter.setDescription("Fichiers csv");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File(IhmPenalite.this.geraid.getDossierDefault()));
        
        int returnVal = chooser.showSaveDialog(IhmPenalite.this);
        // Si un fichier a été choisi
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
          String fichier = Outils.verifExtension(chooser.getSelectedFile().getAbsolutePath(), ".csv");
          CsvPenalites.exporterPenalites( (PenaliteTableModel) table.getModel(), fichier);
        }
      }
    });
    btnExport.setToolTipText("Exporter les p\u00E9nalit\u00E9s...");
    btnExport.setIcon(new ImageIcon(IhmPenalite.class.getResource("/icones/export.png")));
    btnExport.setPreferredSize(new Dimension(32, 32));
    panel_2.add(btnExport);
    
    JButton btnPlus = new JButton("");
    btnPlus.setToolTipText("Ajouter une p\u00E9nalit\u00E9...");
    btnPlus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) 
      {
        if(comboBoxEtapes.getSelectedIndex()>-1)
        {
          Penalite p = new Penalite();
          p.setParcours((Parcours)comboBoxParcours.getSelectedItem());
          p.setEtape((Etape)comboBoxEtapes.getSelectedItem());
          IhmNomPenalite ihm = new IhmNomPenalite(IhmPenalite.this, p, true);
          ihm.setLocationRelativeTo(IhmPenalite.this);
          ihm.setVisible(true);
          refreshTable();
        }
      }
    });
    
    JButton btnExportList = new JButton("");
    btnExportList.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) 
      {
        // On ouvre le navigateur sur le répertoire par défaut
        JFileChooser chooser = new JFileChooser();
        FiltreFichier filter = new FiltreFichier();
        filter.addExtension("csv");
        filter.setDescription("Fichiers csv");
        chooser.setFileFilter(filter);
        chooser.setCurrentDirectory(new File(IhmPenalite.this.geraid.getDossierDefault()));
        
        int returnVal = chooser.showSaveDialog(IhmPenalite.this);
        // Si un fichier a été choisi
        if(returnVal == JFileChooser.APPROVE_OPTION) 
        {
          String fichier = Outils.verifExtension(chooser.getSelectedFile().getAbsolutePath(), ".csv");
          CsvPenalites.exporterListeEquipes( (PenaliteTableModel) table.getModel(), fichier);
        }
      }
    });
    btnExportList.setPreferredSize(new Dimension(32, 32));
    btnExportList.setToolTipText("Exporter la liste des \u00E9quipes...");
    btnExportList.setIcon(new ImageIcon(IhmPenalite.class.getResource("/icones/exportList.png")));
    panel_2.add(btnExportList);
    btnPlus.setPreferredSize(new Dimension(32, 32));
    btnPlus.setIcon(new ImageIcon(IhmPenalite.class.getResource("/icones/plus.png")));
    panel_2.add(btnPlus);
    
    JButton btnModif = new JButton("");
    btnModif.setToolTipText("Modifier la p\u00E9nalit\u00E9...");
    btnModif.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) 
      {
        if(table.getSelectedColumn()>2)
        {
          int index = getIndexColonne();
          Penalite p = IhmPenalite.this.geraid.getPenalite((Etape)comboBoxEtapes.getSelectedItem(), index);
          IhmNomPenalite ihm = new IhmNomPenalite(IhmPenalite.this, p, false);
          ihm.setLocationRelativeTo(IhmPenalite.this);
          ihm.setVisible(true);
          refreshTable();
        }
      }
    });
    btnModif.setIcon(new ImageIcon(IhmPenalite.class.getResource("/icones/search.png")));
    btnModif.setPreferredSize(new Dimension(32, 32));
    panel_2.add(btnModif);
    
    JButton btnMoins = new JButton("");
    btnMoins.setToolTipText("Supprimer la p\u00E9nalit\u00E9...");
    btnMoins.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) 
      {
        if(table.getSelectedColumn()>2)
        {
          int index = getIndexColonne();
          Penalite p = IhmPenalite.this.geraid.getPenalite((Etape)comboBoxEtapes.getSelectedItem(), index);
          StringBuffer message = new StringBuffer("<HTML>Souhaitez-vous supprimer la pénalité : <b><center>");
          message.append(p.getNom()+"<center></b></HTML>");
          IhmPenaliteDelete ihm = new IhmPenaliteDelete(IhmPenalite.this, message.toString(), p);
          ihm.setLocationRelativeTo(IhmPenalite.this);
          ihm.setVisible(true);
          refreshTable();
        }
      }
    });
    btnMoins.setIcon(new ImageIcon(IhmPenalite.class.getResource("/icones/delete.png")));
    btnMoins.setPreferredSize(new Dimension(32, 32));
    panel_2.add(btnMoins);
    
    JPanel panel_3 = new JPanel();
    FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
    flowLayout_1.setAlignment(FlowLayout.LEFT);
    panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gestion individuelle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    panel.add(panel_3, BorderLayout.CENTER);
    
    JButton btnModifEquipe = new JButton("");
    btnModifEquipe.setToolTipText("Modifier les valeurs de la p\u00E9nalit\u00E9...");
    btnModifEquipe.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) 
      {
        if(table.getSelectedColumn()>2 && ((String)table.getValueAt(table.getSelectedRow(), 2)).compareTo("") != 0)
        {
          if(((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn())).compareTo("")==0)
          {
            IhmValeursPenalite ihm = new IhmValeursPenalite(IhmPenalite.this, getPenalitesIndividuellesSelected(), true);
            ihm.setLocationRelativeTo(IhmPenalite.this);
            ihm.setVisible(true);
            refreshTable();
          }
          else
          {
            IhmValeursPenalite ihm = new IhmValeursPenalite(IhmPenalite.this, getPenalitesIndividuellesSelected(), false);
            ihm.setLocationRelativeTo(IhmPenalite.this);
            ihm.setVisible(true);
            refreshTable();
          }
        }
      }
    });
    btnModifEquipe.setIcon(new ImageIcon(IhmPenalite.class.getResource("/icones/search.png")));
    btnModifEquipe.setPreferredSize(new Dimension(32, 32));
    panel_3.add(btnModifEquipe);
    
    JPanel panel_1 = new JPanel();
    getContentPane().add(panel_1, BorderLayout.CENTER);
    panel_1.setLayout(new BorderLayout(0, 0));
    
    if(scrollPane == null)
    {
      scrollPane = new JScrollPane();
    }
    panel_1.add(scrollPane);
    

    if(table == null)
    {
      table = new JTable();
    }
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.setRowSelectionAllowed(false);
    table.setCellSelectionEnabled(true);
    
    scrollPane.setViewportView(table);
    
    this.geraid = geraid;
    init();
    pack();
  }
  
  private void init()
  {
    if(geraid.getParcourss().getSize()>0)
    {
      comboBoxParcours.setModel(new DefaultComboBoxModel<Parcours>(geraid.getParcourss().getParcourss()));

      comboBoxEtapes.setModel(new DefaultComboBoxModel<Etape>(((Parcours)comboBoxParcours.getSelectedItem()).getEtapes().getEtapes()));
      comboBoxEtapes.repaint();
      if(comboBoxEtapes.getItemCount()>0)
      {
        comboBoxEtapes.setSelectedIndex(0);
      }
      else
      {
        comboBoxEtapes.setSelectedIndex(-1);
      }
      refreshTable();
    }
  }
  
  public void refreshTable()
  {
    table.setModel(new PenaliteTableModel(IhmPenalite.this.geraid,(Parcours)comboBoxParcours.getSelectedItem(), (Etape)comboBoxEtapes.getSelectedItem()));
    TableColumnModel tcm = table.getColumnModel();
    for(int i=0; i<tcm.getColumnCount(); i++)
    {
      tcm.getColumn(i).setPreferredWidth(60);
    }
    tcm.getColumn(0).setPreferredWidth(50);
    tcm.getColumn(1).setPreferredWidth(150);
    tcm.getColumn(2).setPreferredWidth(60);
  }
  
  public void addPenaliteIndividuelle(PenaliteIndividuelle p)
  {
    IhmPenalite.this.geraid.getPenalite((Etape)comboBoxEtapes.getSelectedItem(), getIndexColonne()).addPenaliteIndividuelle(p);
  }
  
  private int getIndexColonne()
  {
    int index = table.getSelectedColumn() - 3;
    if(index%2 == 1)
    {
      index = (index -1)/2;
    }
    else
    {
      index = index/2;
    }
    return index;
  }
  
  private PenaliteIndividuelle[] getPenalitesIndividuellesSelected()
  {
    PenaliteIndividuelle retour[] = new PenaliteIndividuelle[table.getSelectedRowCount()];
    int valeurs[] = table.getSelectedRows();
    for(int i=0; i<valeurs.length; i++)
    {
      PenaliteIndividuelle pi = new PenaliteIndividuelle();
      pi.setPuce((String)table.getValueAt(valeurs[i], 2));
      PenaliteIndividuelle p = geraid.getPenaliteIndividuelle((Etape)comboBoxEtapes.getSelectedItem(), getIndexColonne(), (String)table.getValueAt(valeurs[i], 2));
      if(p != null)
      {
        pi.setPoint(p.getPoint());
        pi.setTemps(p.getTemps());
      }
      retour[i] = pi;
    }
    
    return retour;
  }

}
