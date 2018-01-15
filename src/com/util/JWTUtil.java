package com.util;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date; 
public class JWTUtil {
	
	public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "xxx";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
	
	/**
     * 由字符串生成加密key
     * @return
     */
    public static SecretKey generalKey(){
        String stringKey = JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
	
	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		 
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		 
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		//byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey.getSecret());
		//Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		Key signingKey = generalKey();
		JwtBuilder builder = Jwts.builder().setId(id)
		                                .setIssuedAt(now)
		                                .setSubject(subject)
		                                .setIssuer(issuer)
		                                .signWith(signatureAlgorithm, signingKey);
		
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		
		return builder.compact();
	}
	
	public static Claims parseJWT(String jwt) {
		
		Claims claims = null;
		try{
			claims = Jwts.parser()        
					   //.setSigningKey(DatatypeConverter.parseBase64Binary(apiKey.getSecret()))
					   .setSigningKey(generalKey())
					   .parseClaimsJws(jwt).getBody();
		}catch(Exception e){
			return null;
		}
		return claims;
	}
}
