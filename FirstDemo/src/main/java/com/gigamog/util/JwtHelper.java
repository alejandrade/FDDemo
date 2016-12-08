package com.gigamog.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.persistence.EntityManager;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import com.gigamog.exceptions.ServerErrorException;
import com.gigamog.pojo.Jwt;

public class JwtHelper {
	
	
	String jwt;
	String token;
	final String superSecretPassword = "secret";
	
	public JwtHelper(String Token){
		this.token = Token;
		this.jwt = createJWT(Token);
	}
	
	public JwtHelper(){
		
	}
	public void saveJwt(EntityManager em){
		//new Thread(()->{
			Jwt jwt = new Jwt(this.jwt,token);
			jwt.logAccess(em);
		//});
	}

	public boolean validateJwt(String jwt){
	    JsonWebSignature jws = new JsonWebSignature();
	    boolean result = false;
	    try {
	    	jws.setCompactSerialization(jwt);
			jws.setKey(generateKey(superSecretPassword));
			result = jws.verifySignature();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException | JoseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServerErrorException("missing authentication key");

		}
	    
	    return  result;
	}
	
	public String getToken(String jwt){
	    //JsonWebSignature jws = new JsonWebSignature();
	    String result = "";
	    try {
	    //	jws.setCompactSerialization(jwt);
			//jws.setKey(generateKey(superSecretPassword));
			
		    JwtConsumer jwtConsumer = new JwtConsumerBuilder()
		            .setRequireExpirationTime() // the JWT must have an expiration time
		            .setMaxFutureValidityInMinutes(300) // but the  expiration time can't be too crazy
		            .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
		            .setVerificationKey(generateKey(superSecretPassword)) // verify the signature with the public key
		            .build(); // create the JwtConsumer instance
		    
		    JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
		    
		    result = jwtClaims.getClaimValue("token").toString();
		    
			
		} catch (NoSuchAlgorithmException | InvalidJwtException |UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServerErrorException("missing authentication key");
		}
	    
	    return  result;
	}
	
	public  String createJWT(String Token){
		 JwtClaims claims = new JwtClaims();
		    claims.setExpirationTimeMinutesInTheFuture(1440); // time when the token will expire (10 minutes from now)
		    claims.setGeneratedJwtId(); // a unique identifier for the token
		    claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
		    claims.setClaim("token",Token); // additional claims/attributes about the subject can be added

		    JsonWebSignature jws = new JsonWebSignature();

		    // The payload of the JWS is JSON content of the JWT Claims
		    jws.setPayload(claims.toJson());
		    jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
		    try {
				jws.setKey(generateKey(superSecretPassword));
			} catch (UnsupportedEncodingException | NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new ServerErrorException("missing authentication key");
			}
			String jwt = "";
		    try {
				jwt =  jws.getCompactSerialization().toString();
			} catch (JoseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return jwt;

	}


	public HmacKey generateKey(String secret) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    	md.update(secret.getBytes("UTF-8"));
    	byte[] key = md.digest();
    	return new HmacKey(key);
	}


	public String getJwt() {
		return jwt;
	}




	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	public static String randomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}

}
