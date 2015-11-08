package ro.videanuadrian.smartHome.services.impl;


import jodd.jtx.JtxPropagationBehavior;
import jodd.jtx.meta.Transaction;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import ro.videanuadrian.smartHome.dao.UserDAO;
import ro.videanuadrian.smartHome.entities.User;
import ro.videanuadrian.smartHome.services.GenericService;
import ro.videanuadrian.smartHome.services.UserService;


@PetiteBean(value="userService")
public class UserServiceImpl extends GenericService implements UserService {

	
	@PetiteInject
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
		
	
	@Override
	@Transaction(propagation=JtxPropagationBehavior.PROPAGATION_REQUIRED, readOnly=true, timeout=100)
	public User findUserForLogin(String username,String pass){
		
		return userDAO.findUserForAuth(username, pass);
	}
	
	
	
}
