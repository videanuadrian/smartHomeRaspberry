package ro.videanuadrian.smartHome.web.interceptors.stack;

import jodd.madvoc.interceptor.ActionInterceptorStack;
import jodd.madvoc.interceptor.PreparableInterceptor;
import jodd.madvoc.interceptor.ServletConfigInterceptor;

// public interceptor stack (example)
public class PublicInterceptorStack extends ActionInterceptorStack {

    public PublicInterceptorStack() {
        super(
        		
            PreparableInterceptor.class,
            ServletConfigInterceptor.class);
    }
}