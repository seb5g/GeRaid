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

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import to.Equipe;
import to.Parcours;
import to.ResultatParcours;

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
public class ParcoursTableModel extends AbstractTableModel implements Printable
{
  /**
   * 
   */
  private static final long serialVersionUID = -5974347188812805359L;
  private String[] columnNames;
  private Object[][] data;
  private ResultatParcours resultatParcours;

  /**
   * 
   */
  public ParcoursTableModel(ResultatParcours rp)
  {
    this.resultatParcours = rp;
    columnNames = rp.getEntetes();
    data = rp.getData();
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
  
  public String getEntetesHtml()
  {
    int index = 7;
    if(columnNames.length>7)
    {
      index = columnNames.length;
    }
    StringBuffer retour = new StringBuffer("<table><tr style='font-weight: bold;'>");
    retour.append("<td>" +columnNames[0] + "</td>");
    for(int i =1; i<index; i++)
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
    int index = 7;
    if(columnNames.length>7)
    {
      index = columnNames.length;
    }
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
      for(int c =0; c<index; c++)
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
            Parcours p = resultatParcours.geRaid.getParcoursIdPuce((String) getValueAt(table.convertRowIndexToModel(r), 2));
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
        graphics.drawString(resultatParcours.geRaid.nomRaid, marge, index);
        index = index + size;
        graphics.drawString("Parcours : " + resultatParcours.parcours.getNom(), marge, index);
        index = index + size;
        graphics.drawString("Catégorie : " + resultatParcours.categorie.getNomLong(), marge, index);
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
