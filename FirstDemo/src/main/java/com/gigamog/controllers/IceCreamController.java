package com.gigamog.controllers;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gigamog.exceptions.ServerErrorException;
import com.gigamog.pojo.IceCream;
import com.gigamog.pojo.IceCreams;
import com.gigamog.util.JwtHelper;
import com.gigamog.util.KunderaConnect;

@Path("api")
public class IceCreamController {

	@GET
	@Path("listing")
	@Produces("application/json")
	public Response listIcecream() {
		EntityManager em = KunderaConnect.getInstance().getEm();
		IceCreams ice = new IceCreams();
		try {
			ice.fillIceCreamList(em);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("databases issues");
		} finally {
			em.close();
		}
		return Response.ok().entity(ice).build();
	}

	@POST
	@Path("upsert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertIceCream(IceCream icecream) {

		EntityManager em = KunderaConnect.getInstance().getEm();
		try {
			
			if(icecream.getInToken().equals("")){
				 icecream.setInToken(JwtHelper.randomString(4));
			}
			
			em.persist(icecream);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException();
		} finally {
			em.close();
		}

		return Response.ok().entity(icecream).build();
	}

	@GET
	@Path("get/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByName(@PathParam("token") String token) {
		IceCream ic = new IceCream(token);

		EntityManager em = KunderaConnect.getInstance().getEm();
		try {
			ic.populateByToken(em);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException(e.getMessage());
		} finally {
			em.close();
		}

		return Response.ok().entity(ic).build();
	}

}
