package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.TokenThreadUtil;
import com.util.wxpay.PayConfigUtil;
/**
 * 自启动servlet 用于获取到微信公众号access_token 不需要可以删掉
 * @author Ati
 *
 */
@WebServlet(name = "AccessTokenServlet")
public class AccessTokenServlet extends HttpServlet {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
    	TokenThreadUtil.appId = PayConfigUtil.appid;  //获取servlet初始参数appid和appsecret getInitParameter("appid");
    	TokenThreadUtil.appSecret = PayConfigUtil.APP_SECRET; //getInitParameter("appsecret");
    	String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        new Thread(new TokenThreadUtil(path)).start(); //启动进程
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    }
}