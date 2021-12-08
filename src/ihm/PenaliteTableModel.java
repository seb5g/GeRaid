/**
 * 
 */
package ihm;

import geRaid.GeRaid;

import javax.swing.table.AbstractTableModel;

import to.Etape;
import to.Parcours;

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
public class PenaliteTableModel extends AbstractTableModel
{
  /**
   * 
   */
  private static final long serialVersionUID = -5974347188812805359L;
  private String[] columnNames;
  private Object[][] data;

  /**
   * 
   */
  public PenaliteTableModel(GeRaid gr, Parcours p, Etape e)
  {
    columnNames = gr.getEntetes(e);
    data = gr.getData(p, e);
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
    return data[row][col];
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
        ligne.append(";" + getValueAt(r, c));
      }
      retour[r] = ligne.toString();
    }
    
    return retour;
  }
  
  public String getEntetesEquipesCSV()
  {
    StringBuffer retour = new StringBuffer();
    retour.append(columnNames[0]);
    for(int i =1; i<3; i++)
    {
      retour.append(";" + columnNames[i]);
    }
    retour.append(";Points");
    retour.append(";Temps (hh:mm:ss)");
    
    return retour.toString();
  }
  
  public String[] getDataEquipesCSV()
  {
    String[] retour = new String[getRowCount()];
    for(int r=0; r<getRowCount(); r++)
    {
      StringBuffer ligne = new StringBuffer(getValueAt(r, 0).toString());
      for(int c =1; c<3; c++)
      {
        ligne.append(";" + getValueAt(r, c));
      }
      retour[r] = ligne.toString();
    }
    
    return retour;
  }
}
