package com.servlet;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.util.DateUtil;
import com.util.SignUtil;
import com.util.wxpay.XMLUtil;

@WebServlet("/ws/system/callBackPay")
public class CallBackPay extends HttpServlet {
	@Autowired
	private SystemService systemService;

	@Autowired
	private ShopService shopService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8501684751323930133L;
	
	@Override
    public void init() throws ServletException {
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        super.init();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String resXml = "";
			boolean isNewTrade = false;
			 InputStream inputStream ; 
	         StringBuffer sb = new StringBuffer(); 
	         inputStream = request.getInputStream(); 
	         String s ; 
	         BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); 
	         while ((s = in.readLine()) != null){  
	             sb.append(s);
	         }
	         in.close();
	         inputStream.close();
			Map<String, String> m = new HashMap<String, String>();
			m = XMLUtil.doXMLParse(sb.toString());
			// 回调函数，先查看其中的内容，看看能不能根据订单号来区分是充值还是付款
			//String appid = m.get("appid").toString(); // 公众账号ID
			//String bank_type = m.get("bank_type").toString(); // 付款银行
			//String cash_fee = m.get("cash_fee").toString(); // 现金支付金额
			//String fee_type = m.get("cash_fee_type").toString(); // 现金支付货币类型
			//String is_subscribe = m.get("is_subscribe").toString(); // 是否关注公众账号
			//String mch_id = m.get("mch_id").toString(); // 商户号
			//String nonce_str = m.get("nonce_str").toString(); // 随机字符串
			String open_id = m.get("openid").toString(); // 用户标识
			String out_trade_no = m.get("out_trade_no").toString(); // 商户订单号
			String result_code = m.get("result_code").toString(); // 业务结果
			//String return_code = m.get("return_code").toString(); // 返回状态码
			String sign = m.get("sign").toString(); // 签名
			//String time_end = m.get("time_end").toString(); // 支付完成时间
			String total_fee = m.get("total_fee").toString(); // 订单金额
			//String trade_type = m.get("trade_type").toString(); // 交易类型
			String transaction_id = m.get("transaction_id").toString(); // 微信支付订单号
			String shop_id = m.get("device_info").toString(); // 设备号
			
			if (sign.equals(SignUtil.getSign(m))) {
				if ("SUCCESS".equals(result_code)) {
					//自己的业务逻辑
					resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
							+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
				}else{
					resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
							+ "<return_msg><![CDATA[支付失败]]></return_msg>" + "</xml> ";
				}
			} else {
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[签名错误]]></return_msg>" + "</xml> ";
			}
			BufferedOutputStream out = new BufferedOutputStream(  
	                response.getOutputStream()); 
	        out.write(resXml.getBytes()); 
	        out.flush(); 
	        out.close();
		}catch(Exception e){
			try {
				throw new ServiceException("支付出现错误！请联系管理员！");
			} catch (ServiceException e1) {
			}
		}
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
