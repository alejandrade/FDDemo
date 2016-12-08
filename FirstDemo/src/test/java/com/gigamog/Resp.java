package com.gigamog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resp {
	int responseCode;
	String responseBody;
	HashMap<String, List<String>> headers;
	
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	public HashMap<String, List<String>> getHeaders() {
		return headers;
	}
	public void setHeaders(HashMap<String, List<String>> headers) {
		this.headers = headers;
	}
}
