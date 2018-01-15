package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties properties= new Properties();
    
    /*properties文件名*/
    private static final String PROPERTIES_FILE_NAME="access_token.properties";
    /*键*/
    private static final String KEY_LASTID="access_token";
    
    /**
     * 初始化properties，即载入数据
     */
    private static void initProperties(){
        try {
            InputStream ips = PropertiesUtil.class.getResourceAsStream(PROPERTIES_FILE_NAME);
            properties.load(ips);
            ips.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getPrimaryKey(){
        if(properties.isEmpty())//如果properties为空，则初始化一次。
            initProperties();
        return properties.getProperty(KEY_LASTID);
    }
    
    public static void saveAccess_Token(String access_token){
        if(properties.isEmpty())
            initProperties();
        //修改值
        properties.setProperty(KEY_LASTID, access_token);
        //保存文件
        try {
            URL fileUrl = PropertiesUtil.class.getResource(PROPERTIES_FILE_NAME);//得到文件路径
            FileOutputStream fos = new FileOutputStream(new File(fileUrl.toURI()));
            properties.store(fos, "the primary key of article table");
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
