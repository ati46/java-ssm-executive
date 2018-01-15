package com.po.custom;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 错误消息类
 * @author Ati
 *
 */
@XmlRootElement
public class SysErrorMessageCustom {

	private boolean state;
	
	private String message;

	public SysErrorMessageCustom(){
		this.setState(false);
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
