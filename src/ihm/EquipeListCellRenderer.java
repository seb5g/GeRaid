package ihm;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import to.Equipe;

public class EquipeListCellRenderer extends JLabel implements ListCellRenderer<Equipe>
{

  /**
   * 
   */
  private static final long serialVersionUID = -6294326879574699200L;

  @Override
  public Component getListCellRendererComponent(JList<? extends Equipe> list,
      Equipe value, int index, boolean isSelected, boolean cellHasFocus)
  {
    setText(value.toString());
    
    if (isSelected) 
    {
      setBackground(list.getSelectionBackground());
      setForeground(Color.RED);
    }
    else 
    {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }
    
    setEnabled(list.isEnabled());
    setFont(list.getFont());
    setOpaque(true);
    
    if(value.isArrived())
    {
      setBackground(new Color(0, 255, 0, 200));
    }
    
    if(value.isABS())
    {
      setBackground(new Color(255, 255, 0, 200));
    }
    return this;
  }

}
