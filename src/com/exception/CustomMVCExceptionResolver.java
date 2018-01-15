package com.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理类
 * @author Ati
 *
 */
public class CustomMVCExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//异常会从dao => service => controller => 前端控制器(springmvc DispatcherServlet)，
		//一层一层往上抛出，到达前端控制器的时候会调用全局异常处理器(ExceptionResolver)
		
		// handler就是处理器适配器要执行的handler对象(它只有一个method方法)
		//解析出异常类型，如果为系统自定义异常。直接取出异常信息，在页面上展示
//		String message = null;
//		if(ex instanceof CustomException){
//			message = ((CustomException)ex).getMessage();
//		}else{
//			//如果不是系统自定义异常就抛出一个自定义异常
//			message = "未知错误";
//		}
		CustomMVCException customException = null;
		if(ex instanceof CustomMVCException){
			customException = (CustomMVCException)ex;
		}else{
			//如果不是系统自定义异常就抛出一个自定义异常
			customException = new CustomMVCException("未知错误");
		}
		String message = customException.getMessage();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", message);
		modelAndView.setViewName("test/error");
		
		return modelAndView;
	}

}
