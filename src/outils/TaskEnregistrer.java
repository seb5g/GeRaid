/**
 * 
 */
package outils;

import ihm.IhmGeRaidMain;

import javax.swing.SwingWorker;


/**
 * <P>
 * Titre : Task.java
 * </P>
 * <P>
 * Description : 
 * </P>
 * @author thierry
 *
 */
public class TaskEnregistrer extends SwingWorker<Void, Void>
{    
  private volatile Thread currentThread = null;
  private IhmGeRaidMain ihm = null;
  
  public TaskEnregistrer(IhmGeRaidMain ihm)
  {
    this.ihm = ihm;
  }
  
  @Override
  protected Void doInBackground() throws Exception
  {        
    currentThread = Thread.currentThread();
    ihm.setEnregistrementOk();
    Thread.sleep(1000);
    ihm.setEnregistrementNormal();
    stop();
    return null;
  }

  /*
   * Executed in event dispatching thread
   */
  @Override
  public void done() 
  {
    setProgress(0);
  }

  public void stop()
  {
    if(currentThread != null)
    {
      currentThread.interrupt();
    }
  }
}
