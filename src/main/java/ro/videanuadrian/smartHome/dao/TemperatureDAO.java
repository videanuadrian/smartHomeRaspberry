package ro.videanuadrian.smartHome.dao;

import java.util.List;

import ro.videanuadrian.smartHome.entities.TemperatureLog;

public interface TemperatureDAO extends GenericDAO<TemperatureLog> {

	public abstract List<TemperatureLog> getLastHourTemperatures();

}
