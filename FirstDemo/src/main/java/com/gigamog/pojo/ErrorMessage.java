package com.gigamog.pojo;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class ErrorMessage {
	
	int code;
	String message;
	
	public ErrorMessage(int code, String message){
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String toJson(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return  mapper.writeValueAsString(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "";
	
		}
	}

}
