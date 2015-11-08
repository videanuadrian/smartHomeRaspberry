package ro.videanuadrian.smartHome.web.interceptors;

import ro.videanuadrian.smartHome.beans.UserSession;
import ro.videanuadrian.smartHome.config.SmartHomeServiceCore;
import ro.videanuadrian.smartHome.entities.User;
import ro.videanuadrian.smartHome.services.UserService;
import ro.videanuadrian.smartHome.services.impl.UserServiceImpl;
import jodd.joy.auth.AuthenticationInterceptor;
import static jodd.joy.madvoc.action.AppAction.REDIRECT;


/**
 * Application authentication interceptor.
 */
public class AppAuthenticationInterceptor extends AuthenticationInterceptor<Object> {

	@Override
	protected Object resultRegistrationSuccess() {
		return REDIRECT + "<registrationSuccess>";
	}

	/**
	 * Logins user.
	 */
	@Override
	protected Object loginUsernamePassword(String username, String password) {
		
		
		System.out.println("usernam="+username+" pass="+password);
		
		UserService userService = SmartHomeServiceCore.ref.getPetite().getBean(UserService.class);


		
		// check username/password
		User user = userService.findUserForLogin(username, password);
		if (user == null) {
			return null;
		}
		// login
		//userAuthManager.login(user);
		return new UserSession(user);
		
	}

	@Override
	protected Object loginViaCookie(String[] cookieData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] createCookieData(Object userSession) {
		// TODO Auto-generated method stub
		return null;
	}


}
