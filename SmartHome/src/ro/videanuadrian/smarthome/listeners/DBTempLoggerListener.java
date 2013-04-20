package ro.videanuadrian.smarthome.listeners;

import java.util.Date;

import ro.videanuadrian.smarthome.entities.Sensor;
import ro.videanuadrian.smarthome.entities.Temperature;
import ro.videanuadrian.smarthome.listeners.abs.TemperatureObserver;
import ro.videanuadrian.smarthome.services.TemperatureServiceImpl;
import ro.videanuadrian.smarthome.services.abs.TemperatureService;

public class DBTempLoggerListener implements TemperatureObserver {

	private TemperatureService service;
	
	@Override
	public void update(Sensor s, float value) {
		
		service = new TemperatureServiceImpl();
		Temperature t = new Temperature();
		t.setSensor(s);
		t.setTemp(value);
		t.setTimestamp(new Date().getTime());		
		service.save(t);		
	}

}
