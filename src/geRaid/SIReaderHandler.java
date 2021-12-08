/**
 * Copyright (c) 2009 Simon Denier
 * Released under the MIT License (see LICENSE file)
 */
package geRaid;

import java.io.IOException;
import java.util.Date;
import java.util.TooManyListenersException;

import net.gecosi.CommStatus;
import net.gecosi.SiHandler;
import net.gecosi.SiListener;
import net.gecosi.dataframe.SiDataFrame;


import ihm.IhmResultatPuce;


import to.Partiel;
import to.ResultatPuce;


/**
 * @author Simon Denier
 * @since Oct 8, 2009
 *
 */
public class SIReaderHandler implements SiListener
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
	public SIReaderHandler(GeRaid geraid) 
	{
	  this.geraid = geraid;
	}

  private void configure() {
    portHandler = null;
    portHandler = new SiHandler(this);
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
      e.printStackTrace();
    }
  }

  public void start() {
    configure();
    //setNbTry(0);
    //setStarting(true);
    //if (!portHandler.isAlive())
      //portHandler.start();
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
  
  private void remplirResultatPuce(SiDataFrame card, ResultatPuce rp)
  {
    rp.getPuce().setIdPuce(card.getSiNumber());
    rp.getPuce().setErasetime(new Date(card.getCheckTime()));
    rp.getPuce().setControltime(new Date(card.getCheckTime()));
    rp.getPuce().setStarttime(new Date(card.getStartTime()));
    rp.getPuce().setFinishtime(new Date(card.getFinishTime()));
    rp.getPuce().setReadtime(new Date());
    Partiel[] partiels = new Partiel[card.getPunches().length];
    for(int i = 0; i < card.getPunches().length; i++)
    {
      Partiel partiel = new Partiel();
      /*
      if(geraid.getResultatsPuce().existeCodePuce(card.getPunches()[i].code(), card.getSiNumber()))
      {
        partiel.setCode(card.getPunches()[i].code()+1000);
      }
      else
      {*/
        partiel.setCode(card.getPunches()[i].code());
      //}
      partiel.setTime(new Date(card.getPunches()[i].timestamp()));
      partiels[i] = partiel;
    }
    rp.getPuce().setPartiels(partiels);
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
    ResultatPuce rp = geraid.getEquipe(card.getSiNumber());
    remplirResultatPuce(card, rp);
    if(rp.getEquipe()!= null)
    {
      IhmResultatPuce ihm =  new IhmResultatPuce(geraid.getIhm(), rp, true, false, geraid.resultatReduit);
      ihm.setLocationRelativeTo(geraid.getIhm());
      ihm.setVisible(true);
    }
    else
    {
      IhmResultatPuce ihm =  new IhmResultatPuce(geraid.getIhm(), rp, false, false, geraid.resultatReduit);
      ihm.setLocationRelativeTo(geraid.getIhm());
      ihm.setVisible(true);
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
        break;
      }
  }

  @Override
  public void notify(CommStatus errorStatus, String errorMessage)
  {
    // TODO Auto-generated method stub
    
  }
	
}
