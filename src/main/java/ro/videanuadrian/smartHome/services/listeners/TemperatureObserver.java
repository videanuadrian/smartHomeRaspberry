package ro.videanuadrian.smartHome.services.listeners;

import ro.videanuadrian.smartHome.entities.Sensor;

public interface TemperatureObserver {
	
	public abstract void update(Sensor s, float value);
	
}
