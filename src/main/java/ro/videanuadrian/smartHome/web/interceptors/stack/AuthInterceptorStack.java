package ro.videanuadrian.smartHome.web.interceptors.stack;

import ro.videanuadrian.smartHome.web.interceptors.AuthInterceptor;
import jodd.madvoc.interceptor.ActionInterceptorStack;

// auth interceptor stack
public class AuthInterceptorStack extends ActionInterceptorStack {

    public AuthInterceptorStack() {
        super(AuthInterceptor.class, PublicInterceptorStack.class);
    }
}