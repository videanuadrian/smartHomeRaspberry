package ro.videanuadrian.smartHome.services;

import ro.videanuadrian.smartHome.entities.User;

public interface UserService {

	User findUserForLogin(String username, String pass);

}
