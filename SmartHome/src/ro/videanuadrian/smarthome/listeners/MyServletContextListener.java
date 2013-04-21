package ro.videanuadrian.smarthome.listeners;



import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import ro.videanuadrian.smarthome.utils.TemperatureGrabber;

public class MyServletContextListener implements ServletContextListener {

	private Thread tempGrabThread = null;
	private TemperatureGrabber tempGrabber = null;
	
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {	
			tempGrabber = new TemperatureGrabber();
			DBTempLoggerListener dbTempLoggerListener = new DBTempLoggerListener();
			tempGrabber.addObserver(dbTempLoggerListener);
			tempGrabThread = new Thread(tempGrabber);
			System.out.println("**************************");
			System.out.println("*Starting TempGrabber    *");
			tempGrabThread.start();
			System.out.println("*Successfully started    *");
			System.out.println("**************************");
				    
		    
			

	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		System.out.println("**************************");
		System.out.println("*Stopping TempGrabber   *");
		if (this.tempGrabThread!=null){
			
			tempGrabber.terminate();			
			try {
				tempGrabThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("*Successfully stoped    *");
			System.out.println("**************************");
		}

	}
}


