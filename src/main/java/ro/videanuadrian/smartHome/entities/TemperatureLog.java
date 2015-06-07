package ro.videanuadrian.smartHome.entities;


import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;

@DbTable("sh_temperature_log")
public class TemperatureLog extends Entity {

	private static final long serialVersionUID = 6389940162411850540L;

	@DbColumn("sensor_id")
	private Long sensorId;
	
	@DbColumn("timestamp")
	private Long timestamp;
	
	@DbColumn("temperature")
	private Float temperature; 

	public Long getSensorId() {
		return sensorId;
	}

	public void setSensorId(Long sensorId) {
		this.sensorId = sensorId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Float getTemperature() {
		return temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TemperatureLog [sensorId=").append(sensorId)
				.append(", timestamp=").append(timestamp)
				.append(", temperature=").append(temperature).append("]");
		return builder.toString();
	}
	
	
}
