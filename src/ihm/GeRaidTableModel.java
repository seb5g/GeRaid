package ihm;

import javax.swing.table.DefaultTableModel;

public class GeRaidTableModel extends DefaultTableModel
{
  /**
   * 
   */
  private static final long serialVersionUID = 6004261080488297472L;
  protected String[] columnNames;
  protected Object[][] data;

  public GeRaidTableModel()
  {
    super();
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
  
  public String getEntetesHtml()
  {
    StringBuffer retour = new StringBuffer("<table><tr>");
    retour.append("<td>" +columnNames[0] + "</td>");
    for(int i =1; i<columnNames.length; i++)
    {
      retour.append("<td>" + columnNames[i] + "</td>");
    }
    retour.append("</tr>");
    return retour.toString();
  }
  
  public String getDataHtml()
  {
    StringBuffer retour = new StringBuffer();
    for(int r=0; r<getRowCount(); r++)
    {
      retour.append("<tr>");
      for(int c =0; c<getColumnCount(); c++)
      {
        retour.append("<td>" + getValueAt(r, c) + "</td>" );
      }
      retour.append("</tr>");
    }
    retour.append("</table>");
    
    return retour.toString();
  }
}
