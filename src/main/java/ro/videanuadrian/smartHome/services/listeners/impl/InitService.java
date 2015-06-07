package ro.videanuadrian.smartHome.services.listeners.impl;


import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInitMethod;
import jodd.petite.meta.PetiteInject;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import ro.videanuadrian.smartHome.services.impl.TemperatureGrabber;

@PetiteBean
public class InitService  {

	private static final Logger logger = LoggerFactory.getLogger(InitService.class.getName());
 	
	private Thread tempGrabThread = null;
	private TemperatureGrabber tempGrabber = null;
	
	@PetiteInject
	private DBTempLoggerObserver dbTempLoggerListener;
	
	@PetiteInitMethod
	public void start() {
			
		
			tempGrabber = new TemperatureGrabber();			
			tempGrabber.addObserver(dbTempLoggerListener);
			tempGrabThread = new Thread(tempGrabber);
			
			logger.info("**************************");
			logger.info("*Starting TempGrabber    *");
			
			//tempGrabThread.start();
			
			logger.info("*Successfully started    *");
			logger.info("**************************");
			
			/*
			GpioController gpio = GpioFactory.getInstance();
			gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "GPIO_01", PinState.LOW);
			*/
	}
	
	
	public void stop() {

		logger.info("**************************");
		logger.info("*Stopping TempGrabber   *");
		if (this.tempGrabThread!=null){
			
			tempGrabber.terminate();			
			try {
				tempGrabThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			logger.info("*Successfully stoped    *");
			logger.info("**************************");
		}
		
		/*
		GpioController gpio = GpioFactory.getInstance();
		gpio.shutdown();
		 */
	}
}


