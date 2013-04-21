package ro.videanuadrian.smarthome.services.abs;

import com.pi4j.io.gpio.Pin;

public interface GpioService {

	void turnOn(Pin raspPin);

	void turnOff(Pin raspPin);

	
	
	
}
