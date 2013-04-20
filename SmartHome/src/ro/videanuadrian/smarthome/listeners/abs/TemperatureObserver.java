package ro.videanuadrian.smarthome.listeners.abs;

import ro.videanuadrian.smarthome.entities.Sensor;

public interface TemperatureObserver {
	
	public abstract void update(Sensor s, float value);
	
}
