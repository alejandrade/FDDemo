package com.gigamog.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.gigamog.pojo.User;
import com.gigamog.util.KunderaConnect;

@Path("account")
public class AccountController {


	@GET
	@Path("validate")
	public Response validate() {

		User user = new User(System.nanoTime()+"emaixl@email.com", "ss123");

		 KunderaConnect conn = KunderaConnect.getInstance();
		 conn.getConnection();
		
		 //user = conn.selectById(user.getClass(), user.getEmail());
		//
		// user.setTimesChecked(user.getTimesChecked()+1);
		 conn.save(user);
		 conn.close();
		return Response.ok().entity("success").build();

	}

}
