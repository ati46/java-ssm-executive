package com.exception;

import java.util.Locale;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.xml.rpc.ServiceException;


/**
 * CXF自定义异常类，针对预期的异常，需要在程序中抛出此类的异常
 * @author Ati
 *
 */
@SuppressWarnings("rawtypes")
public class CustomCXFFaultMapper implements ExceptionMapper{

	@Override
	public Response toResponse(Throwable ex) {
		StackTraceElement[] trace = new StackTraceElement[1];  
        trace[0] = ex.getStackTrace()[0];  
        ex.setStackTrace(trace);  
        ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);  
        rb.type("application/json;charset=UTF-8");  
        if (ex instanceof ServiceException) {//自定义的异常类  
        	ServiceException e = (ServiceException) ex;
            CustomCXFFault entity=new CustomCXFFault(e.getMessage());
            rb.entity(entity);
        }else{  
            rb.entity(ex);  
        }  
        rb.language(Locale.SIMPLIFIED_CHINESE);
        Response r = rb.build();  
        return r; 
	}
	
}
