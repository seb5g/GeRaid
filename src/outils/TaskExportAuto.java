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
public class TaskExportAuto extends SwingWorker<Void, Void>
{    
  private volatile Thread currentThread = null;
  private IhmGeRaidMain ihm = null;
  
  public TaskExportAuto(IhmGeRaidMain ihm)
  {
    this.ihm = ihm;
  }
  
  @Override
  protected Void doInBackground() throws Exception
  {        
    currentThread = Thread.currentThread();
    //Thread.sleep(3000);
    while(! currentThread.isInterrupted())
    {
      if(ihm.existeDossierSauvegarde())
      {
        ihm.exportHtml();
        ihm.setExportOk();
      }
      else
      {
        ihm.setExportNok();
      }
      Thread.sleep(1000);
      ihm.setExportNormal();
      Thread.sleep(ihm.getTempoExportAuto()*60000);
    }
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
