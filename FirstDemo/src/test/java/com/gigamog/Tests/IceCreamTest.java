package com.gigamog.Tests;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.gigamog.HttpUtils;
import com.gigamog.Resp;
import com.gigamog.TestConstants;
import com.gigamog.pojo.IceCream;
import com.vividsolutions.jts.util.Assert;

public class IceCreamTest {
	
	String currentJwt = "";
	
	
	@Test
	public void verifyJwt(){
		Resp resp = HttpUtils.jwtHttpCall(TestConstants.server + "api/listing", "GET",
				"eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE0ODEyNDE5MDgsImp0aSI6IjVQbHlnYjVEMFltdkwxaUdXbDZJbGciLCJuYmYiOjE0ODExNTUzODgsInRva2VuIjoiczRnMDUifQ.xIA_C5ZGPkLOJVwlwapBpNF8LmYGlhmofhQl031AHGE");
		Assert.equals(200, resp.getResponseCode());
		
	}
//	
	
	@Test
	public void getIceCreamByToken(){
		Resp resp = HttpUtils.jwtHttpCall(TestConstants.server + "api/get/yrew", "GET",
		"eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE0ODEyNDE5MDgsImp0aSI6IjVQbHlnYjVEMFltdkwxaUdXbDZJbGciLCJuYmYiOjE0ODExNTUzODgsInRva2VuIjoiczRnMDUifQ.xIA_C5ZGPkLOJVwlwapBpNF8LmYGlhmofhQl031AHGE");
		Assert.equals(200, resp.getResponseCode());
	}
//	
	@Test
	public void insertIceCream(){
		ObjectMapper mapper = new ObjectMapper();
		IceCream ic = new IceCream();
		ic.setFlavor("Rocky Road");
		ic.setInToken("yrew");
		ic.setName("james");
		
		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(ic);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println();
		
		Resp resp = HttpUtils.jwtHttpPost(TestConstants.server + "api/upsert",
				"eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE0ODEyNDE5MDgsImp0aSI6IjVQbHlnYjVEMFltdkwxaUdXbDZJbGciLCJuYmYiOjE0ODExNTUzODgsInRva2VuIjoiczRnMDUifQ.xIA_C5ZGPkLOJVwlwapBpNF8LmYGlhmofhQl031AHGE",
				jsonInString);
		
		Assert.equals("success", resp.getResponseBody());
		
	}
}
