package ro.videanuadrian.smarthome.services;

import ro.videanuadrian.smarthome.dao.TemperatureDaoImpl;
import ro.videanuadrian.smarthome.dao.abs.TemperatureDao;
import ro.videanuadrian.smarthome.entities.Temperature;
import ro.videanuadrian.smarthome.services.abs.GenericService;
import ro.videanuadrian.smarthome.services.abs.TemperatureService;

public class TemperatureServiceImpl extends GenericService implements TemperatureService {

	TemperatureDao dao;
		
	@Override
	public boolean save(Temperature t){
	
		dao = new TemperatureDaoImpl();
		if (dao.save(t))
			return true;
		return false;
		
	}
	
}
