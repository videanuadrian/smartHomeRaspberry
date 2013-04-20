package ro.videanuadrian.smarthome.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ro.videanuadrian.smarthome.dao.abs.GenericAbstractDao;
import ro.videanuadrian.smarthome.dao.abs.TemperatureDao;
import ro.videanuadrian.smarthome.entities.Sensor;
import ro.videanuadrian.smarthome.entities.Temperature;

public class TemperatureDaoImpl extends GenericAbstractDao implements TemperatureDao {
	
	@Override
	public List<Temperature> getLastHourTemperatures(){
				
		List<Temperature> temps  = new ArrayList<Temperature>();
		long now = new Date().getTime();
		long aHourAgo = ((now/1000)-86400)*1000;
		
		String sql = "SELECT t1.id,t1.timestamp,t1.temp,t2.id AS sensor_id,t2.name AS sensor_name FROM temperatures AS t1 LEFT JOIN sensors AS t2 ON t1.sensor_id=t2.id WHERE timestamp>"+aHourAgo+" ORDER BY t1.id ASC";
		//String sql = "SELECT t1.id,t1.timestamp,t1.temp,t2.id AS sensor_id,t2.name AS sensor_name FROM temperatures AS t1 LEFT JOIN sensors AS t2 ON t1.sensor_id=t2.id ORDER BY t1.id";
				
		Statement st = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()){				
				Sensor sensor = new Sensor(rs.getInt("sensor_id"),rs.getString("sensor_name"));
				Temperature t = new Temperature(rs.getInt("id"),sensor,rs.getLong("timestamp"),rs.getFloat("temp"));
				temps.add(t);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
		return temps;
	}

	@Override
	public boolean save(Temperature t){
		
		Integer sensorId = t.getSensor().getId();		
		String query = "INSERT INTO temperatures SET sensor_id="+sensorId+",timestamp="+t.getTimestamp()+",`temp`="+t.getTemp();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute(query);		
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
