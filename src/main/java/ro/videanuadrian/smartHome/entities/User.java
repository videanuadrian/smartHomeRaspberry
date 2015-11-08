package ro.videanuadrian.smartHome.entities;

import java.io.Serializable;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;

@DbTable("users")
public class User extends Entity implements Serializable {
	
	private static final long serialVersionUID = 4537862169353794598L;

	@DbColumn("username")
	private String username;

	@DbColumn("password")
	private String password;
	
	@DbColumn("full_name")
	private String fullName;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [username=").append(username)
				.append(", password=").append(password).append(", fullName=")
				.append(fullName).append(", id=").append(id).append("]");
		return builder.toString();
	}
		
	
}
