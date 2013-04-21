package ro.videanuadrian.smarthome.services;

import java.util.Collection;
import java.util.List;

import com.pi4j.component.light.impl.GpioDimmableLightComponent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigital;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import ro.videanuadrian.smarthome.services.abs.GpioService;

public class GpioServiceImpl implements GpioService {
	
	
	@Override
	public void turnOn(Pin raspPin){
		
		GpioController gpio = GpioFactory.getInstance();	    
	    
	    Collection<GpioPin> pins = gpio.getProvisionedPins();
	    //boolean found = false;
	    for(GpioPin gpPin:pins){
	    	if (gpPin.getPin().equals(raspPin)){
	    	//	found = true;
	    		GpioPinDigitalOutput gp = (GpioPinDigitalOutput) gpPin;
	    		gp.high();
	    		break;
	    	}	    	
	    }
	    
	  //  if (found == false){
	   // 	gpio.provisionDigitalOutputPin(raspPin, "MyPin", PinState.HIGH);
	    //}
	    	 
	    //System.out.println(pins);
	    
	}
		
	@Override
	public void turnOff(Pin raspPin){		
		
		GpioController gpio = GpioFactory.getInstance();
		Collection<GpioPin> pins = gpio.getProvisionedPins();
	  //  boolean found = false;
	    
	    
	    for(GpioPin gpPin:pins){
	    //	System.out.println("gpPin:"+gpPin+" -> rapPin:"+raspPin);
	    	if (gpPin.getPin().equals(raspPin)){
	    		//found = true;
	    		GpioPinDigitalOutput gp = (GpioPinDigitalOutput) gpPin;
	    		gp.low();
	    		break;
	    	}	    	
	    }
	    
	    //System.out.println("found="+found);
	    
	    //if (found == false){
//	    	gpio.provisionDigitalOutputPin(raspPin, "MyPin", PinState.LOW);
	//    }
	}
}
