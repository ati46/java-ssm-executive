package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户拦截器
 * @author Ati
 *
 */
public class MVCUserHandlerInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  
        throws Exception {  
        return true;  
    }  
  
    /** 
     * This implementation is empty. 
     */  
    public void postHandle(  
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  
            throws Exception {  
    }  
  
    /** 
     * This implementation is empty. 
     */  
    public void afterCompletion(  
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
    }  
}
