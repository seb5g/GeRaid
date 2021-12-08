/**
 * 
 */
package ihm;

import javax.swing.table.AbstractTableModel;

import to.Categories;

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
public class CategorieTableModel extends AbstractTableModel
{
  /**
   * 
   */
  private static final long serialVersionUID = -5974347188812805359L;
  private String[] columnNames = new String[2];
  private Object[][] data;

  /**
   * 
   */
  public CategorieTableModel(Categories c)
  {
    columnNames[0] = "Nom long";
    columnNames[1] = "Nom court";
    data = c.getData();
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


}
