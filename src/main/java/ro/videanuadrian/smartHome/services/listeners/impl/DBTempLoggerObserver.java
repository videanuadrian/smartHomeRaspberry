package ro.videanuadrian.smartHome.services.listeners.impl;

import java.util.Date;

import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import ro.videanuadrian.smartHome.entities.Sensor;
import ro.videanuadrian.smartHome.entities.TemperatureLog;
import ro.videanuadrian.smartHome.services.TemperatureService;
import ro.videanuadrian.smartHome.services.listeners.TemperatureObserver;

@PetiteBean
public class DBTempLoggerObserver implements TemperatureObserver {

	@PetiteInject
	private TemperatureService temperatureService;
	
	@Override
	public void update(Sensor s, float value) {
		
		TemperatureLog t = new TemperatureLog();
		t.setSensorId(s.getId());
		t.setTemperature(value);
		t.setTimestamp(new Date().getTime());		
		temperatureService.save(t);		
	}

}
