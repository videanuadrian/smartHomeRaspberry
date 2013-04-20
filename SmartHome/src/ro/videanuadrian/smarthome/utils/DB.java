package ro.videanuadrian.smarthome.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB{

    private static Connection conn;
    private static DB instance;

    private DB(){
    	conn = dbConnect();
    }
    
    public static DB getInstance() { 
    	if(instance == null) 
    		instance = new DB();
    	return instance;
    }
    
    public static Connection getConnection() {
    	
    	if(conn == null) {
    		instance = new DB();
    	}
    	return conn;
    }

    
    public static Connection dbConnect(){

        try{
        	Class.forName("com.mysql.jdbc.Driver");
        	
        	Properties prop = new Properties();
        	prop.load(DB.class.getClassLoader().getResourceAsStream("ro/videanuadrian/smarthome/conf/dbConfig.properties"));
        	String conStr = "jdbc:mysql://"+prop.getProperty("dbHost")+":"+prop.getProperty("dbPort")+"/"+prop.getProperty("dbName");        	
        	conn = DriverManager.getConnection(conStr,prop.getProperty("dbUser"),prop.getProperty("dbPassword"));
        	return conn;
            
        } catch(final Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
