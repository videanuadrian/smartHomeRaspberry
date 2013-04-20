package ro.videanuadrian.smarthome.dao.abs;

import java.sql.Connection;

import ro.videanuadrian.smarthome.utils.DB;

public abstract class GenericAbstractDao {

	protected Connection conn = null;

	public GenericAbstractDao() {
		super();	
		conn = DB.getConnection();		
	}
	
}
