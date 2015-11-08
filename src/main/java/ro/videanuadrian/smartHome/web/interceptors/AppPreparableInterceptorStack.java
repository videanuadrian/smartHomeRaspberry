package ro.videanuadrian.smartHome.web.interceptors;

import jodd.joy.madvoc.interceptor.DefaultPreparableInterceptorStack;
import jodd.madvoc.interceptor.ActionInterceptorStack;
import jodd.madvoc.interceptor.EchoInterceptor;

/**
 * Preparable app interceptor stack.
 */
public class AppPreparableInterceptorStack extends ActionInterceptorStack {

	public AppPreparableInterceptorStack() {
		super(
				EchoInterceptor.class,
				AppAuthenticationInterceptor.class,
				DefaultPreparableInterceptorStack.class
		);
	}
}