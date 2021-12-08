/**
 * 
 */
package ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import outils.classementComparator;

import to.Epreuve;
import to.Equipe;
import to.Parcours;
import to.ResultatEtape;
import to.TypeVisualisationEpreuve;

/**
 * <P>
 * Titre : EtapeTableModel.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class EpreuveTableModel extends AbstractTableModel implements Printable
{
  /**
   * 
   */
  private static final long serialVersionUID = -5974347188812805359L;
  private String[] columnNames;
  private Object[][] data;
  private Epreuve epreuve;
  private ResultatEtape resultatEtape;

  /**
   * 
   */
  public EpreuveTableModel(ResultatEtape re, Epreuve e, TypeVisualisationEpreuve tv)
  {
    this.epreuve = e;
    this.resultatEtape = re;
    columnNames = Epreuve.getEntetes(e, tv);
    data = re.getData(e, tv);
    int i = getIndexNC();
    Arrays.sort(data, 0, i, new classementComparator()
    {
    });
    faireClassement();
  }
  
  private int getIndexNC()
  {
    int retour = data.length;

    for(int i=0; i<data.length; i++)
    {
      if(data[i][0]=="NC")
      {
        retour = i;
        return i;
      }
    }
    return retour;
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getColumnCount()
   */
  @Override
  public int getColumnCount()
  {
    return columnNames.length;
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getRowCount()
   */
  @Override
  public int getRowCount()
  {
    return data.length;
  }
  
  public String getColumnName(int col) 
  {
    return columnNames[col];
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getValueAt(int, int)
   */
  @Override
  public Object getValueAt(int row, int col)
  {
    if(getRowCount()>0)
    {
      if((data[row][col] + "").compareTo("0") == 0 || (data[row][col] + "").compareTo("0:00:00") == 0)
      {
        return "";
      }
      else
      {
        return data[row][col];
      }
    }
    return "";
  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Class getColumnClass(int c) 
  {
    return getValueAt(0, c).getClass();
  }
  
  public void setValueAt(Object value, int row, int col) 
  {
    data[row][col] = value;
    fireTableCellUpdated(row, col);
  }
  
  public String getEntetesCSV()
  {
    StringBuffer retour = new StringBuffer();
    retour.append(columnNames[0]);
    for(int i =1; i<columnNames.length; i++)
    {
      retour.append(";" + columnNames[i]);
    }
    
    return retour.toString();
  }
  
  public String[] getDataCSV()
  {
    String[] retour = new String[getRowCount()];
    for(int r=0; r<getRowCount(); r++)
    {
      StringBuffer ligne = new StringBuffer(getValueAt(r, 0).toString());
      for(int c =1; c<getColumnCount(); c++)
      {
        ligne.append(";" + "\"" + getValueAt(r, c) + "\"");
      }
      retour[r] = ligne.toString();
    }
    
    return retour;
  }
  
  private void faireClassement()
  {
    for(int i=0; i<data.length; i++)
    {
      if(((String)data[i][0]).compareTo("NC")!=0)
      {
        data[i][0] = i+1+"";
      }
    }
  }
  
  public String getEntetesHtml()
  {
    StringBuffer retour = new StringBuffer("<table><tr style='font-weight: bold;'>");
    for(int i =0; i<7; i++)
    {
      if(i!=2)
      {
        retour.append("<td>" + columnNames[i] + "</td>");
      }
    }
    retour.append("</tr>");
    return retour.toString();
  }
  
  public String getDataHtml(JTable table)
  {
    StringBuffer retour = new StringBuffer();
    for(int r=0; r<getRowCount(); r++)
    {
      retour.append("<tr style='");
      if(r%2==0)
      {
        retour.append("background-color: #FFFFE0;'");
      }
      else
      {
        retour.append("background-color: #E0FFFF;'");
      }
      retour.append("align=center>");
      for(int c =0; c<7; c++)
      {
        if(c!=2)
        {
          retour.append("<td>" + getValueAt(table.convertRowIndexToModel(r), c) + "</td>" );
        }
      }
      retour.append("</tr>");
    }
    retour.append("</table>");
    if(getRowCount()<25)
    {
      for(int e=getRowCount(); e<25; e++)
      {
        retour.append("<br>");
      }
    }
    
    return retour.toString();
  }
  
  public String getDataHtml(JTable table, int index)
  {
    StringBuffer retour = new StringBuffer();
    if(getRowCount()<index)
    {
      index = getRowCount();
    }
    for(int r=0; r<index; r++)
    {
      retour.append("<tr style='");
      if(r%2==0)
      {
        retour.append("background-color: #FFFFE0;'");
      }
      else
      {
        retour.append("background-color: #E0FFFF;'");
      }
      retour.append("align=center>");
      for(int c =0; c<7; c++)
      {
        if(c!=2)
        {
          if(c==3)
          {
            Parcours p = resultatEtape.getGeRaid().getParcoursIdPuce((String) getValueAt(table.convertRowIndexToModel(r), 2));
            Equipe equipe = p.getEquipes().getEquipeIdPuce((String) getValueAt(table.convertRowIndexToModel(r), 2));
            retour.append("<td><b>" + getValueAt(table.convertRowIndexToModel(r), c) + "</b>");
            for(int n=0; n<equipe.getRaiders().getRaiders().size(); n++)
            {
              retour.append("<br>" + equipe.getRaiders().getRaiders().get(n).toString() );
            }
            retour.append("</td>");
          }
          else
          {
            retour.append("<td>" + getValueAt(table.convertRowIndexToModel(r), c) + "</td>" );
          }
        }
      }
      retour.append("</tr>");
    }
    retour.append("</table>");
    
    return retour.toString();
  }

  @Override
  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
      throws PrinterException
  {
    int retValue = Printable.NO_SUCH_PAGE;
    int size = 12;
    int marge = 10;
    int index = 0;
    switch(pageIndex)
    {
      case 0 : 
      {
        graphics.setColor(Color.BLACK);
        index = index + size;
        graphics.setFont(new Font("Serif", Font.PLAIN, index));
        index = index + size;
        graphics.drawString(resultatEtape.getGeRaid().nomRaid, marge, index);
        index = index + size;
        graphics.drawString("Parcours : " + resultatEtape.getGeRaid().getParcours(epreuve).getNom(), marge, index);
        index = index + size;
        graphics.drawString("Etape : " + resultatEtape.getEtape().getNom(), marge, index);
        index = index + size;
        graphics.drawString("Epreuve : " + epreuve.getNom(), marge, index);
        index = index + size;
        graphics.drawString("Catégorie : " + resultatEtape.getCategorie().getNomLong(), marge, index);
        index = index + size;
        index = index + size;

        for(int i=0; i<data.length; i++)
        {
          graphics.drawString(data[i][0] + "  " + data[i][3], marge, index);
          index = index + size;
        }
        // La page est valide
        retValue = Printable.PAGE_EXISTS;
        break;
      }
    }
    return retValue;
  }
}
