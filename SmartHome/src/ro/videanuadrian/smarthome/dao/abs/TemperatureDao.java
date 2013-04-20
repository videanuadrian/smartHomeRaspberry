package ro.videanuadrian.smarthome.dao.abs;

import java.util.List;

import ro.videanuadrian.smarthome.entities.Temperature;

public interface TemperatureDao {

	List<Temperature> getLastHourTemperatures();

	boolean save(Temperature t);

}
