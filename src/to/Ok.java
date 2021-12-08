/**
 * 
 */
package to;

import java.util.Vector;

/**
 * <P>
 * Titre : Ok.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author Thierry
 *
 */
public class Ok
{
  private Vector<Boolean> ok = new Vector<Boolean>();
  
  /**
   * @return the ok
   */
  public Vector<Boolean> getOk()
  {
    return ok;
  }

  /**
   * @param ok the ok to set
   */
  public void setOk(Vector<Boolean> ok)
  {
    this.ok = ok;
  }

  public Ok(int nb)
  {
    for(int i=0; i<nb; i++)
    {
      ok.add(false);
    }
  }

}
