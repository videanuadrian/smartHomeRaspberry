package ro.videanuadrian.smarthome.listeners.abs;

import ro.videanuadrian.smarthome.entities.Sensor;

public interface TemperatureObserverSubject {

	public abstract void addObserver(TemperatureObserver te);	
	public abstract void deleteObserver(TemperatureObserver te);
	void notifyObservers(Sensor s, float temp);
}
