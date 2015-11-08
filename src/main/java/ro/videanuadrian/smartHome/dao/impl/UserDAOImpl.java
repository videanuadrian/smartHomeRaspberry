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
import ro.videanuadrian.smartHome.dao.UserDAO;
import ro.videanuadrian.smartHome.entities.TemperatureLog;
import ro.videanuadrian.smartHome.entities.User;

@PetiteBean(scope=SingletonScope.class,value="userDAO")
public class UserDAOImpl extends GenericAbstractDAO<User> implements UserDAO {
	
			
		@Override
		public User findUserForAuth(String username,String pass){
			
			/* 
			String sql = "SELECT $C{tt.*} FROM $T{TemperatureLog tt}  WHERE tt.timestamp>"+aHourAgo+" ORDER BY $C{tt.+} ASC";			
			DbSqlBuilder sqlBuilder = new DbSqlBuilder(sql);
			
			DbOomQuery query = DbOomQuery.query(sqlBuilder.generateQuery());			
			temps = query.list(TemperatureLog.class);
			
			return temps;
			*/
			return new User();
		}
	
	
}

