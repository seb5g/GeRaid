package outils;

import java.util.Comparator;

public abstract class classementComparator implements Comparator<Object[]>
{

  @Override
  public int compare(Object[] arg0, Object[] arg1)
  {
    int temps = ((String) arg0[5]).compareTo((String)arg1[5]);
    Integer a = Integer.parseInt((String) arg0[6]);
    Integer b = Integer.parseInt((String) arg1[6]);
    int points = ( a).compareTo(b);
    
    if(points<0)
    {
      return 1;
    }
    else if(points>0)
    {
      return -1;
    }
    else
    {
      if(temps<0)
      {
        return -1;
      }
      else if(temps>0)
      {
        return 1;
      }
      else
      {
        return 0;
      }
    }
    
  }

}
