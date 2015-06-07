package ro.videanuadrian.smartHome.entities;

import java.io.Serializable;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;

@DbTable("sensors")
public class Sensor extends Entity implements Serializable {
	
	private static final long serialVersionUID = 4537862169353794598L;

	@DbColumn("name")
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sensor [name=").append(name).append("]");
		return builder.toString();
	}
	
}
