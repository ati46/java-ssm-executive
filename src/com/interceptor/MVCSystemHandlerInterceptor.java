package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统拦截器-spring
 * @author Ati
 *
 */
public class MVCSystemHandlerInterceptor implements HandlerInterceptor {

	//进入handler方法之前执行
	//一般用于 身份验证，身份授权
	//比如说登陆
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  
        throws Exception {  
        //...
		//获取url
		String url = request.getRequestURI();
		//正常开发要把公开地址配在配置文件中，
		if(url.indexOf("index.action") >= 0)
			return true;
		
		//判断session
		HttpSession httpSession = request.getSession();
		String username = (String) httpSession.getAttribute("username");
		if(username != null)
			return true;
		
		//如果到这里表示身份需要验证，跳转到登陆界面
		//request.getRequestDispatcher("WEB-INF/webpage/test/login.jsp").forward(request, response);
		
		return true;  
    }  
	
	//进入handler方法之后，返回ModelAndView之前
	//比如将面包屑导航放在这里。
    public void postHandle(  
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  
            throws Exception {  
    }  
  
    //handler执行完成后
    //统一的日志处理，统一的异常处理。
    public void afterCompletion(  
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
    }  
}
