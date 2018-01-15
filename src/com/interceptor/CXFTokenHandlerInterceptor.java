package com.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.XMLMessage;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.po.custom.SysMacCustom;
import com.po.custom.SysMaintenanceCustom;
import com.service.TallyService;
import com.util.JWTUtil;
import com.util.wxpay.XMLUtil;

import io.jsonwebtoken.Claims;
import net.sf.json.JSONObject;

/**
 * webservice接口拦截器-cxf
 * 
 * @author Ati 这里使用的是RESTful方式发布的cxf
 *         webservice所以不用SOAP消息的拦截器：AbstractPhaseInterceptor<SoapMessage>
 */
public class CXFTokenHandlerInterceptor extends AbstractPhaseInterceptor<Message> {

	@Autowired
	private TallyService tallyService;

	public CXFTokenHandlerInterceptor() {
		super(Phase.PRE_PROTOCOL); // PRE_PROTOCOL准备协议时拦截。 PRE_INVOKE
									// 在调用方法之前调用自定义拦截器
	}

	@SuppressWarnings({ "unchecked", "static-access", "unused" })
	@Override
	public void handleMessage(Message message) throws Fault {
		String methodName = getMethod(message);
		// 排除拦截的方法
		List<String> list = new ArrayList<String>();
		list.add("ws");
		if (!list.contains(methodName)) {
			String token = "";
			JSONObject jasonObject = null;
			StringBuffer buffer = new StringBuffer();
			InputStream is = message.getContent(InputStream.class);
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String line = "";
			if (!methodName.equals("getCL")) {
				Message messageRequest = message.getExchange().getInMessage();
				Map<String, List<String>> headers = CastUtils
						.cast((Map<?, ?>) messageRequest.get(Message.PROTOCOL_HEADERS)); // 获取header参数

				try {
					token = headers.get("Authorization").get(0);
				} catch (Exception e) {
					throw new Fault(new ServiceException("无权限！"));
				}
			}
			Map<String, String> reqParamsMap = new HashMap<String, String>();
			// 根据请求方式区分取得GET和POST的参数
			if (message.get(message.HTTP_REQUEST_METHOD).equals("GET")) {
				// get参数，=&格式
				String reqParams = (String) message.get(message.QUERY_STRING);
				// 注:toMap为自定义方法,实现将String转成map
				// reqParamsMap = StringUtils.toMap(reqParams, "&");
			} else if (message.get(message.HTTP_REQUEST_METHOD).equals("POST")) {
				try {
					while ((line = in.readLine()) != null) {
						// post参数，json格式
						buffer.append(line);
					}
					jasonObject = JSONObject.fromObject(buffer.toString());
					reqParamsMap = (Map<String, String>) jasonObject;
				} catch (Exception e) {
					throw new Fault(new ServiceException("请提供有效的参数！如果多次提示该错误，请联系客服！"));
				}
			}
			if (reqParamsMap != null) {
				if (!methodName.equals("getCL")) {
					if (token.trim().length() == 5 || token.equals("Basic")) {

						throw new Fault(new ServiceException("732.1"));

					} else {
						try {
							if (!token.substring(0, 6).equals("Basic "))
								throw new Fault(new ServiceException("请求头错误！"));
							token = token.substring(6);
						} catch (Exception e) {
							throw new Fault(new ServiceException("请求头错误！"));
						}
						if (!methodName.equals("getToken")) {
							Claims claims = JWTUtil.parseJWT(token);
							if (claims == null)
								throw new Fault(new ServiceException("732.1"));

							jasonObject.put("open_id", claims.getId());
							buffer.setLength(0);
							buffer.append(jasonObject.toString());
						}
					}
				} else {
					if(!methodName.equals("isOpen")){
						String mac_code = String.valueOf(reqParamsMap.get("mac"));
						String maintenance_name = String.valueOf(reqParamsMap.get("maintenance_name"));
						SysMacCustom sysMacCustom;
						SysMaintenanceCustom sysMaintenanceCustom;
						try {
							sysMacCustom = tallyService.findSysMacByMac(mac_code);

							sysMaintenanceCustom = tallyService.findMaintenanceByName(maintenance_name);
						} catch (Exception e) {
							throw new Fault(new ServiceException("请求数据错误！"));
						}

						if (sysMacCustom == null)
							throw new Fault(new ServiceException("此设备无权限！"));

						if (sysMaintenanceCustom == null)
							throw new Fault(new ServiceException("请输入正确的名字！"));
					}
				}
			}
			if (is != null) {
				// 这里一定要加,post参数流读取结束如果不加这个操作,会报io异常/把修改后的stream重新写回content
				message.setContent(InputStream.class, new ByteArrayInputStream(buffer.toString().getBytes()));
			}
		}
	}

	/**
	 * @Description:handleMessage异常后执行
	 * @param message
	 */
	@Override
	public void handleFault(Message message) {
		super.handleFault(message);
		// System.out.println("=================================："+"handleFault");
	}

	/**
	 * @Description:取得请求服务的具体方法
	 * @param message
	 * @return
	 */
	private String getMethod(Message message) {
		// 通过分析webservice的uri取得实际执行的方法,该webservice使用cxf的RESTFul形式发布
		String requestUri = (String) message.get(XMLMessage.REQUEST_URI);
		String[] methods = StringUtils.split(requestUri, "/");
		// System.out.println("********method name:" + requestUri);
		return methods != null && methods.length > 0 ? methods[methods.length - 1] : "";
	}
}
