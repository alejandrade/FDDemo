package com.gigamog.settings;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gigamog.exceptions.UnauthorizedException;
import com.gigamog.pojo.Jwt;
import com.gigamog.util.KunderaConnect;

public class FilterHelper {

	public static void addCORS(HttpServletResponse resp) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		resp.setHeader("Access-Control-Max-Age", "3000");

	}

	public static void openJwt(HttpServletRequest req) {

		String authHeader = req.getHeader("Authorization");
		String jwt = "";
		try {
			jwt = authHeader.replaceAll("JWT ", "");

		} catch (Exception e) {
			throw new UnauthorizedException("missing authorization header");
		}
		Jwt jwtobj = new Jwt(jwt);

		try {
			if (!jwtobj.validateJWT(KunderaConnect.getInstance().getEm()))
				throw new UnauthorizedException("denied jwt");
		} catch (UnauthorizedException e) {
			throw new UnauthorizedException(e.getMessage());
		} finally {
			KunderaConnect.getInstance().close();
		}

	}

	public static void changeContent(HttpServletResponse resp, String content) throws IOException {
		resp.setContentLength(content.length());
		OutputStream os = resp.getOutputStream();
		os.write(content.getBytes());
		os.flush();
		os.close();
	}

}
