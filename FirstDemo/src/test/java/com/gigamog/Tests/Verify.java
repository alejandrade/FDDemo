package com.gigamog.Tests;

import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

import com.gigamog.HttpUtils;
import com.gigamog.Resp;
import com.gigamog.TestConstants;
import com.gigamog.pojo.Jwt;import com.gigamog.util.KunderaConnect;

public class Verify {
	
	@Test
	public void verifyJWTValid(){
		Resp resp = HttpUtils.httpCall(TestConstants.server + "account/validate?t=s4g05&p=1231", "GET");
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(resp.getResponseBody());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Jwt jwt = new Jwt(json.get("jwt").toString());
		Assert.assertTrue(jwt.validateJWT(KunderaConnect.getInstance().getEm()));
		KunderaConnect.getInstance().close();
	}
	
	
	@Test
	public void verifyOptions(){
		Resp resp = HttpUtils.httpCall(TestConstants.server + "account", "OPTIONS");
		Assert.assertTrue(resp.getResponseBody().equals("thanks for options call"));
	}
	@Test
	public void verifyCors(){
		Resp resp = HttpUtils.httpCall(TestConstants.server + "account", "OPTIONS");
		
		Assert.assertEquals(resp.getHeaders().get("Access-Control-Allow-Origin").get(0), "*");
		Assert.assertEquals(resp.getHeaders().get("Access-Control-Allow-Headers").get(0), "origin, content-type, accept, authorization");
		Assert.assertEquals(resp.getHeaders().get("Access-Control-Allow-Credentials").get(0), "true");
		Assert.assertEquals(resp.getHeaders().get("Access-Control-Allow-Methods").get(0), "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		Assert.assertEquals(resp.getHeaders().get("Access-Control-Max-Age").get(0), "3000");

	}
	

}
