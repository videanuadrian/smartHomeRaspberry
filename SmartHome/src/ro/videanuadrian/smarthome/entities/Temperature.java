package ro.videanuadrian.smarthome.entities;

import java.io.Serializable;

public class Temperature implements Serializable{

	private static final long serialVersionUID = 6389940162411850540L;

	private Integer id;
	private Sensor sensor;
	private Long timestamp;
	private Float temp; 
		
	public Temperature(){
		super();
	}

	public Temperature(Integer id,Sensor s,Long ts,Float t){
		super();		
		this.id = id;
		this.sensor = s;
		this.timestamp = ts;
		this.temp = t;		
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Float getTemp() {
		return temp;
	}

	public void setTemp(Float temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return "Temperature [id=" + id + ", sensor=" + sensor + ", timestamp="+timestamp + ", temp=" + temp + "]";
	}
	
	
}
