package com.exception;

import java.io.Serializable;


/**
 * CXF自定义异常类，针对预期的异常，需要在程序中抛出此类的异常
 * @author Ati
 *
 */
public class CustomCXFFault implements Serializable{
	private static final long serialVersionUID = 1L;  

    private String message;  
    
    public CustomCXFFault(){}  
      
    public CustomCXFFault(String message) {  
        super(); 
        this.message = message;  
    }
      
  
    public String getMessage() {  
        return message;  
    }  
  
    public void setMessage(String message) {  
        this.message = message;  
    }  
}
