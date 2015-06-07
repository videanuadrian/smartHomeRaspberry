package ro.videanuadrian.smartHome.services.impl;
import java.util.List;

import jodd.jtx.JtxIsolationLevel;
import jodd.jtx.JtxPropagationBehavior;
import jodd.jtx.meta.Transaction;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import ro.videanuadrian.smartHome.dao.TemperatureDAO;
import ro.videanuadrian.smartHome.entities.TemperatureLog;
import ro.videanuadrian.smartHome.services.GenericService;
import ro.videanuadrian.smartHome.services.TemperatureService;

@PetiteBean(value="temperatureService")
public class TemperatureServiceImpl extends GenericService implements TemperatureService {

	@PetiteInject
	private TemperatureDAO temperatureDAO;
		
	public TemperatureDAO getTemperatureDAO() {
		return temperatureDAO;
	}

	public void setTemperatureDAO(TemperatureDAO temperatureDAO) {
		this.temperatureDAO = temperatureDAO;
	}

	@Override
    @Transaction(propagation=JtxPropagationBehavior.PROPAGATION_REQUIRED, readOnly=false, timeout=100)
	public boolean save(TemperatureLog t){
	
		try {
			temperatureDAO.saveOrUpdate(t);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
    @Transaction(propagation = JtxPropagationBehavior.PROPAGATION_REQUIRED, readOnly = true,isolation=JtxIsolationLevel.ISOLATION_READ_COMMITTED)
	public List<TemperatureLog> getLastHoutTemperatures(){

		return temperatureDAO.getLastHourTemperatures();
		
	}
	
}
