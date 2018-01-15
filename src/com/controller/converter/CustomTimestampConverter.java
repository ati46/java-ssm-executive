package com.controller.converter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;

/**
 * Timestamp转换器
 * @author Ati
 *
 */
public class CustomTimestampConverter implements Converter<String, Timestamp>{

	//private Timestamp ts = new Timestamp(System.currentTimeMillis());
	
	@Override
	public Timestamp convert(String source) {
		//将日期字符串转为日期类型
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//成功直接返回
			source = simpleDateFormat.parse(source).toString();
			return Timestamp.valueOf(source);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//失败返回为null
		return null;
	}
}
