package com.util;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.util.wxpay.PayCommonUtil;
import com.util.wxpay.PayConfigUtil;

public class SignUtil {
	public static String getSign(Map<String, String> map){
		SortedMap<Object,Object> signParams = new TreeMap<Object,Object>();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            signParams.put(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        signParams.remove("sign");
        String sign = PayCommonUtil.createSign("UTF-8", signParams,PayConfigUtil.API_KEY);
        return sign;
	}
}
