package com.gigamog.settings;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;


public class FilterHelper {

	public static void addCORS(HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		resp.setHeader("Access-Control-Max-Age", "3000");

	}


	public static void changeContent(HttpServletResponse resp, String content) throws IOException {
		resp.setContentLength(content.length());
		OutputStream os = resp.getOutputStream();
		os.write(content.getBytes());
		os.flush();
		os.close();
	}
	


}
