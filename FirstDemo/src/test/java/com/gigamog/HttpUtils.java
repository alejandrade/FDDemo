package com.gigamog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {
	
	
	
	public static Resp jwtHttpCall(String urlString, String method, String jwt){
		 HashMap<String,String> headers = new HashMap<>();
		 headers.put("Authorization", "JWT " + jwt);
		 return HttpUtils.httpCall(urlString, method, headers, null);
	}
	
	public static Resp httpCall(String urlString, String method){
		 return HttpUtils.httpCall(urlString, method, new HashMap<String,String>(), null);
	}

	
	public static Resp jwtHttpPost(String urlString, String jwt, String body){
		 HashMap<String,String> headers = new HashMap<>();
		 headers.put("Authorization", "JWT " + jwt);
		 headers.put("Content-Type", "application/json");
		 headers.put("charset", "utf-8");
		 headers.put("Content-Length", body.length()+"");
		 return httpCall(urlString, "POST",
				 headers, body);
	}
	
	public static Resp httpCall(String urlString, String method,
			HashMap<String,String> headers, String body) {

		Resp resp = new Resp();
		BufferedReader in = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			for (Map.Entry<String, String> entry : headers.entrySet()) {
			    String key = entry.getKey();
			    String value = entry.getValue();
			    con.setRequestProperty(key, value);
			}

			
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod(method);
			
			if(method.equals("POST"))
			 con.getOutputStream().write(body.getBytes());
			
			
			resp.headers = new HashMap<String, List<String>>(con.getHeaderFields());

			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer response = new StringBuffer();

			while ((output = in.readLine()) != null) {
				response.append(output);
			}
			resp.responseCode = con.getResponseCode();
			resp.responseBody = response.toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resp;
	}

}
