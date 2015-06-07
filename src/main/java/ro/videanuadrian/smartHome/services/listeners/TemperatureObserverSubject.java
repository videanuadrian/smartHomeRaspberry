package ro.videanuadrian.smartHome.services.listeners;

import ro.videanuadrian.smartHome.entities.Sensor;

public interface TemperatureObserverSubject {

	public abstract void addObserver(TemperatureObserver te);	
	public abstract void deleteObserver(TemperatureObserver te);
	void notifyObservers(Sensor s, float temp);
}
