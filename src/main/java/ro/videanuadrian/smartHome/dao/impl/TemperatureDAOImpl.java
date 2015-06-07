package ro.videanuadrian.smartHome.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jodd.db.oom.DbOomQuery;
import jodd.db.oom.sqlgen.DbSqlBuilder;
import jodd.petite.meta.PetiteBean;
import jodd.petite.scope.SingletonScope;
import ro.videanuadrian.smartHome.dao.GenericAbstractDAO;
import ro.videanuadrian.smartHome.dao.TemperatureDAO;
import ro.videanuadrian.smartHome.entities.TemperatureLog;

@PetiteBean(scope=SingletonScope.class,value="temperatureDAO")
public class TemperatureDAOImpl extends GenericAbstractDAO<TemperatureLog> implements TemperatureDAO {
	
		@Override
		public List<TemperatureLog> getLastHourTemperatures(){
					
			List<TemperatureLog> temps  = new ArrayList<TemperatureLog>();
			long now = new Date().getTime();
			long aHourAgo = ((now/1000)-86400)*1000;
			
			//String sql = "SELECT $C{tt.*},$C{st.*} FROM $T{Temperature tt}  JOIN $T{Sensor st} ON st.+=tt.sensorId WHERE timestamp>"+aHourAgo+" ORDER BY tt.id ASC";
			//String sql = "SELECT t1.id,t1.timestamp,t1.temp,t2.id AS sensor_id,t2.name AS sensor_name FROM temperatures AS t1 LEFT JOIN sensors AS t2 ON t1.sensor_id=t2.id ORDER BY t1.id";
			
			String sql = "SELECT $C{tt.*} FROM $T{TemperatureLog tt}  WHERE tt.timestamp>"+aHourAgo+" ORDER BY $C{tt.+} ASC";			
			DbSqlBuilder sqlBuilder = new DbSqlBuilder(sql);
			
			DbOomQuery query = DbOomQuery.query(sqlBuilder.generateQuery());			
			temps = query.list(TemperatureLog.class);
			
			return temps;
		}
}

