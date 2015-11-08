package ro.videanuadrian.smartHome.dao;

import ro.videanuadrian.smartHome.entities.User;

public interface UserDAO {

	User findUserForAuth(String username, String pass);

}
