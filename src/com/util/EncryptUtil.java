package com.util;

import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密
 * @author Ati
 *
 */
public class EncryptUtil {
	
	private static final String KEY_SHA = "SHA";  
	private static final String KEY_MD5 = "MD5";
	private static final byte[] secret = "xxx".getBytes();
	private static StringBuilder displayText = new StringBuilder();
	
	
	public static byte[] createCiphertext(byte[] data){
		byte[] newCiphertext = new byte[data.length + secret.length];  
	    System.arraycopy(data, 0, newCiphertext, 0, data.length);  
	    System.arraycopy(secret, 0, newCiphertext, data.length, secret.length); 
	    return newCiphertext;
	}
    /**
     * BASE64解密
     * @param key
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unused")
	private static byte[] decryptBASE64(String key) throws Exception {  
        return (new BASE64Decoder()).decodeBuffer(key);  
    }  
    
    /**
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
	private static String encryptBASE64(byte[] key) throws Exception {
		if(displayText.length() > 1)
			displayText.delete(0,displayText.length());
		displayText.append((new BASE64Encoder()).encodeBuffer(key));
		displayText.deleteCharAt(2).deleteCharAt(3);
        return displayText.toString();
    }
    
    /**
     * MD5加密
     * @param data
     * @return
     * @throws Exception
     */
	public static String encryptMD5(byte[] data) throws Exception {
	    MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);  
	    md5.update(createCiphertext(data));  
	    //生成MD5密文字节后由base64加密成字符串
	    return encryptBASE64(md5.digest()).replaceAll("\r", "").replaceAll("\n", "");  
	}
	
	/**
	 * SHA加密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptSHA(byte[] data) throws Exception {  
		
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);  
        sha.update(createCiphertext(data));  
        //生成SHA密文字节后由base64加密成字符串
        return encryptBASE64(sha.digest()).replaceAll("\r", "").replaceAll("\n", "");
    }
	
	
}
