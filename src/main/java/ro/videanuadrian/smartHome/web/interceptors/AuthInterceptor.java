package ro.videanuadrian.smartHome.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jodd.joy.auth.AuthUtil;
import jodd.madvoc.ActionRequest;
import jodd.madvoc.interceptor.BaseActionInterceptor;
import jodd.servlet.DispatcherUtil;

public class AuthInterceptor extends BaseActionInterceptor {
	
    @Override
    public Object intercept(ActionRequest request) throws Exception {
        HttpServletRequest servletRequest = request.getHttpServletRequest();
        HttpSession session = servletRequest.getSession();
        if (AuthUtil.getUserSession(session)!= null) {
            return request.invoke();
        }
        
        servletRequest.setAttribute("path", DispatcherUtil.getPathInfo(servletRequest));
        return "chain:/%login%";
    }
}