package com.util;

import java.util.Date;

import com.controller.converter.JsonDateValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 格式工具类
 * @author Ati
 *
 */
public class FormatUtil {
	
	private static JsonConfig jsonConfig = new JsonConfig();  
	
	//对象转json
	public static String ObjectToJSON(Object object){

		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
		
		JSONObject jsonObject = JSONObject.fromObject(object,jsonConfig);
		return jsonObject.toString();
	}
}
