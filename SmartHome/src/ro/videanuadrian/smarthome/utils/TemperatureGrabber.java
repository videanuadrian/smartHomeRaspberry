package ro.videanuadrian.smarthome.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ro.videanuadrian.smarthome.entities.Sensor;
import ro.videanuadrian.smarthome.listeners.abs.TemperatureObserver;
import ro.videanuadrian.smarthome.listeners.abs.TemperatureObserverSubject;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class TemperatureGrabber implements Runnable,TemperatureObserverSubject {

	private volatile boolean running = true;
	
	List<TemperatureObserver> observers = new ArrayList<TemperatureObserver>();
	
	public void terminate(){
		this.running = false;
	}
	
	@Override
	public void addObserver(TemperatureObserver te) {
		if (!observers.contains(te)){
			observers.add(te);
		}		
	}

	@Override
	public void deleteObserver(TemperatureObserver te) {
		if (observers.contains(te)){
			observers.remove(te);
		}		
	}

	@Override
	public void notifyObservers(Sensor s,float temp){
		
		for(TemperatureObserver observer:observers){
			observer.update(s,temp);
		}		
	}
	
	@Override
	public void run() {
		
		try {			
			I2CBus i2cBus = I2CFactory.getInstance(I2CBus.BUS_1);
			I2CDevice insideSensor = i2cBus.getDevice(0x48);
			I2CDevice outsideSensor = i2cBus.getDevice(0x49);
			
			// pus 56 ca prima data cand porneste sa se execute
			//s-a ales 55 pentru ca vreau ca update in db sa se faca la 5 minute
			//astfel ca 12iteratii x5 secunde=60 secunde 5 calupuri de 12 iteratii = 5 minute
			int counter =56;
			int sleepTime = 5000; // how much to sleep expressed in miliseconds
			
			while(running == true) {
				
				if (counter >55){
					
					counter = 0;
					byte[] bufferInside = new byte[2];
					byte[] bufferOutside = new byte[2];
				
					insideSensor.read(bufferInside, 0, 2);
					outsideSensor.read(bufferOutside, 0, 2);
					
					byte b1In = bufferInside[0];
					byte b2In = bufferInside[1];
	
					int msbIn = b1In << 4;
					int lsbIn = (b2In & 0xFF)>>4;
					int tIn = msbIn | lsbIn;
					
					byte b1Out = bufferOutside[0];
					byte b2Out = bufferOutside[1];
	
					int msbOut =  b1Out << 4;
					int lsbOut = (b2Out & 0xFF) >> 4;
					int tOut = msbOut | lsbOut;
					
					float tInCelsius = (float) (tIn*0.0625);
					float tOutCelsius = (float) (tOut*0.0625);
					
					Sensor is = new Sensor();
					is.setId(1);
					Sensor os = new Sensor();
					os.setId(2);
					
					notifyObservers(is, tInCelsius);
					notifyObservers(os, tOutCelsius);
					is = null;
					os = null;
					
					tIn = 0;
					tOut = 0;
					tInCelsius = 0.0f;
					tOutCelsius = 0.0f;
				}else{
					counter++;
				}
				
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {				
					e.printStackTrace();				
				}				
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}	
		
	}



	
	
	
	
	
	
}
