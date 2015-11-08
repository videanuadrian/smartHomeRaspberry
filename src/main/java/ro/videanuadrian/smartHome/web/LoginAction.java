package ro.videanuadrian.smartHome.web;


import jodd.joy.auth.AuthAction;
import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.MadvocAction;


@MadvocAction("login")
public class LoginAction extends AuthAction {
	
	@Action(alias = "login")
	public void view() {
	}
	
}
