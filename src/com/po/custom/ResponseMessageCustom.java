package com.po.custom;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 返回信息
 * @author Ati
 *
 */
@XmlRootElement
public class ResponseMessageCustom {
	private boolean state;
	private String message;
	private String text;
	public boolean isState() {
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
