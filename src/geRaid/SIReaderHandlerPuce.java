/**
 * Copyright (c) 2009 Simon Denier
 * Released under the MIT License (see LICENSE file)
 */
package geRaid;

import java.io.IOException;
import java.util.TooManyListenersException;

import net.gecosi.CommStatus;
import net.gecosi.SiHandler;
import net.gecosi.SiListener;
import net.gecosi.dataframe.SiDataFrame;
import ihm.IhmMessage;

import to.Equipe;
import to.Parcours;


/**
 * @author Simon Denier
 * @since Oct 8, 2009
 *
 */
public class SIReaderHandlerPuce implements SiListener
{
	
	private SiHandler portHandler;

	private String portName = "";
	
	private long zeroTime = 32400000; // 9:00
  //private long zeroTime = 0; // 9:00
	
	private int nbTry;
	
	private boolean starting;
	
	private GeRaid geraid = null;
	
	/**
	 * @param factory
	 * @param stage
	 * @param announcer
	 */
	public SIReaderHandlerPuce(GeRaid geraid) 
	{
	  this.geraid = geraid;
	}

  private void configure() {
    if(portHandler == null)
    {
      portHandler = new SiHandler(this);
    }
    portHandler.setZeroHour(getZeroTime());
    try
    {
      portHandler.connect(getPortName());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (TooManyListenersException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void start() {
    configure();
    /*setNbTry(0);
    setStarting(true);
    if (!portHandler.isAlive())
      portHandler.start();*/
  }
  
  public void stop() {
    if( portHandler==null )
      return;
    portHandler.stop();
  }
  
  public String getPortName() {
    return portName;
  }

  public void setPortName(String portName) {
    this.portName = portName;
  }

  public long getZeroTime() {
    return zeroTime;
  }

  public void setZeroTime(long zeroTime) {
    this.zeroTime = zeroTime;
  }

  public void setNbTry(int nbTry)
  {
    this.nbTry = nbTry;
  }

  public int getNbTry()
  {
    return nbTry;
  }

  public void setStarting(boolean starting)
  {
    this.starting = starting;
  }

  public boolean isStarting()
  {
    return starting;
  }

  /**
   * @return the geraid
   */
  public GeRaid getGeraid()
  {
    return geraid;
  }

  /**
   * @param geraid the geraid to set
   */
  public void setGeraid(GeRaid geraid)
  {
    this.geraid = geraid;
  }

  @Override
  public void handleEcard(SiDataFrame card)
  {
    if(geraid.existePuce(card.getSiNumber()))
    {
      IhmMessage ihm = new IhmMessage(geraid.getIhm(), "Cette puce existe déjà pour ce raid.");
      ihm.setVisible(true);
    }
    else
    {
      Equipe equipe = geraid.getEquipeSansPuce((Parcours)geraid.getIhm().jComboBoxParcours.getSelectedItem(), geraid.getIhm().jListEquipes.getSelectedIndex());
      if(equipe != null)
      {
        equipe.setIdPuce(card.getSiNumber());
        geraid.getIhm().jListEquipes.repaint();
      }
      else
      {
        IhmMessage ihm = new IhmMessage(geraid.getIhm(), "Il n'y a plus d'équipe sans puce pour ce parcours à partir de la sélection.");
        ihm.setVisible(true);
      }
    }
  }

  @Override
  public void notify(CommStatus status)
  {
    switch (status) {
      case ON:
        geraid.getIhm().stationStatus("Ready");
        starting = false;
        break;
      default:
        geraid.getIhm().stationStatus("Failed");
        //portHandler.stop();
        break;
      }
  }

  @Override
  public void notify(CommStatus arg0, String arg1)
  {
    //geraid.getIhm().stationStatus("Failed");
    System.out.println(arg1);
  }
	
}
