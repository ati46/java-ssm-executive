package com.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Properties;

import com.po.custom.SysAccessTokenCustom;

import net.sf.json.JSONObject;

public class TokenThreadUtil implements Runnable {
    public static String appId = "";
 
    public static String appSecret= "";
    
    public String path;
    
    //注意是静态的
    public static SysAccessTokenCustom sysAccessTokenCustom = null;
 
    public TokenThreadUtil(String path){
    	this.path = path;
    }
    
    public void run(){
        while (true){
            try{
            	sysAccessTokenCustom = this.getAccessToken();
                if(null!=sysAccessTokenCustom){
                    Thread.sleep(7000 * 1000); //获取到access_token 休眠7000秒
 
                }else{
                    Thread.sleep(1000*3); //获取的access_token为空 休眠3秒
                }
            }catch(Exception e){
                System.out.println("发生异常："+e.getMessage());
                e.printStackTrace();
                try{
                    Thread.sleep(1000*10); //发生异常休眠1秒
                }catch (Exception e1){
 
                }
            }
        }
    }
 
 
    /**
     * 获取access_token
     * @return
     * @throws IOException 
     */
    private SysAccessTokenCustom getAccessToken() throws Exception{
        String Url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",appId,appSecret);
        JSONObject json = HttpUtil.httpsRequest(Url, "GET", null); 
        SysAccessTokenCustom newToken = new SysAccessTokenCustom();
        newToken.setAccess_token(json.getString("access_token"));
        newToken.setExpires_in(json.getInt("expires_in"));
        newToken.setAccess_token_time(new Date());
        Properties prop = new Properties();// 属性集合对象 
        if(System.getProperties().getProperty("os.name").toLowerCase().startsWith("win"))
        	path = path.substring(1);
		FileInputStream fis = new FileInputStream(path+"access_token.properties");
		prop.load(fis);
		fis.close();

		prop.setProperty("access_token", json.getString("access_token"));
		prop.setProperty("expires_in", json.getString("expires_in"));
		prop.setProperty("last_time", new Date().toString());
		FileOutputStream fos = new FileOutputStream(path+"access_token.properties"); 
		prop.store(fos, "最后一次更新时间"); 
		fos.close();
        return newToken;
    }
}
