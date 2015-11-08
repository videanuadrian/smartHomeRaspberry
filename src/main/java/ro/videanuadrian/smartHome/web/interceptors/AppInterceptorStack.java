package ro.videanuadrian.smartHome.web.interceptors;

import jodd.joy.madvoc.interceptor.DefaultInterceptorStack;
import jodd.madvoc.interceptor.ActionInterceptorStack;
import jodd.madvoc.interceptor.EchoInterceptor;

/**
 * Application interceptor stack
 */
public class AppInterceptorStack extends ActionInterceptorStack {

	public AppInterceptorStack() {
		super(
				EchoInterceptor.class,
				AppAuthenticationInterceptor.class,				
				DefaultInterceptorStack.class
		);

	}
}
