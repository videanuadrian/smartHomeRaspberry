package ro.videanuadrian.smarthome.entities;

import java.io.Serializable;

public class Sensor implements Serializable {
	
	private static final long serialVersionUID = 4537862169353794598L;

	private Integer id;
	private String name;

	public Sensor() {
		super();
	}
	
	public Sensor(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", name=" + name + "]";
	} 
	
}
