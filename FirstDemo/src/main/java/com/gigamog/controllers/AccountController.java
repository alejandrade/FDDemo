package com.gigamog.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.gigamog.pojo.Access;
import com.gigamog.util.JwtHelper;
import com.gigamog.util.KunderaConnect;


@Path("account")
public class AccountController {

	

	@GET
	@Path("validate")
	@Produces("application/json")
	public Response validate(@QueryParam("t") String token, 
			@QueryParam("p") String passCode) 
	{
		
		Access acc = new Access(token, passCode);
		acc.validate(KunderaConnect.getInstance().getEm(),token, passCode);
		JwtHelper jwt = new JwtHelper(token);
		jwt.saveJwt(KunderaConnect.getInstance().getEm());
		KunderaConnect.getInstance().close();
		return Response.status(200).entity(jwt).build();

	}

}
