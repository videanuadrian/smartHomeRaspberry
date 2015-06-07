package ro.videanuadrian.smartHome.services;

import java.util.List;

import ro.videanuadrian.smartHome.entities.TemperatureLog;

public interface TemperatureService {

	public abstract boolean save(TemperatureLog t);

	public abstract List<TemperatureLog> getLastHoutTemperatures();

}
