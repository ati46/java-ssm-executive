package com.pay;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.jdom.JDOMException;

import com.util.wxpay.HttpUtil;
import com.util.wxpay.PayCommonUtil;
import com.util.wxpay.PayConfigUtil;
import com.util.wxpay.XMLUtil;

public class WXPay {
	
	@Resource
	private static WebServiceContext wscontext;
	
	public static String createWXPayTrade(String trade_code,String shop_id,String open_id,String nameList,BigDecimal price,String product_id) throws JDOMException, IOException{
		
        String key = PayConfigUtil.API_KEY; // key 

        String currTime = PayCommonUtil.getCurrTime(); //获取当前时间
        String strTime = currTime.substring(8, currTime.length()); 
        String strRandom = PayCommonUtil.buildRandom(4) + ""; //取出一个指定长度大小的随机正整数
        String nonce_str = strTime + strRandom; 

        SortedMap<Object,Object> packageParams = createSign(trade_code,shop_id,open_id,nameList,price,product_id,nonce_str);

        String sign = PayCommonUtil.createSign("UTF-8", packageParams,key); 
        packageParams.put("sign", sign);  //签名
        
        String requestXML = PayCommonUtil.getRequestXml(packageParams); 

        String resXml = HttpUtil.postData(PayConfigUtil.UFDODER_URL, requestXML); 

        Map map = XMLUtil.doXMLParse(resXml); 
        //String return_code = (String) map.get("return_code");  
        //String prepay_id = (String) map.get("prepay_id");  //预支付交易会话标识
        String urlCode = (String) map.get("code_url");  //二维码链接

        return urlCode; 
	}
	
	public static SortedMap<Object,Object> createSign(String trade_code,String shop_id,String open_id, String nameList,BigDecimal price,String product_id,String nonce_str){
		String appid = PayConfigUtil.appid; // appid 
        String mch_id = PayConfigUtil.MCH_ID; // 商业号 

      //获取发起电脑 ip  
//        String spbill_create_ip = getIP(); //PayConfigUtil.CREATE_IP; 
//        if("0:0:0:0:0:0:0:1".equals(spbill_create_ip)){
//        	
//        }
        String spbill_create_ip = PayConfigUtil.CREATE_IP; 
        
        //回调接口   
        String notify_url = PayConfigUtil.NOTIFY_URL; 
        String trade_type = "NATIVE"; 
		SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>(); 
        packageParams.put("appid", appid); //公众账号ID
        packageParams.put("mch_id", mch_id); //商户号
        packageParams.put("device_info", shop_id); //设备号
        packageParams.put("nonce_str", nonce_str); //随机字符串
        packageParams.put("out_trade_no", trade_code); //商户订单号
        packageParams.put("total_fee", String.valueOf(price.multiply(new BigDecimal(1000)).intValue())); //标价金额 注意：价格的单位是分 
        packageParams.put("trade_type", trade_type); //交易类型
        packageParams.put("openid", open_id); //用户标识
        packageParams.put("body", nameList);  //商品描述
        packageParams.put("spbill_create_ip", spbill_create_ip); //终端IP
        packageParams.put("notify_url", notify_url); //通知地址
        packageParams.put("product_id", product_id); //商品ID
        
        return packageParams;
	}
	
	public static String getIP(){  
		Message message = PhaseInterceptorChain.getCurrentMessage();  
		HttpServletRequest httprequest = (HttpServletRequest)message.get(AbstractHTTPDestination.HTTP_REQUEST);  
		return httprequest.getRemoteAddr();
	}
}
