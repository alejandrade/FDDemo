package com.gigamog.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class IceCreamController {

	@GET
	@Path("/listing")
	public Response listFavorites() {

		// list all icecream and names
		return Response.ok().entity("test").build();
	}
//
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response insertFavorite() {
//		// insert icecream and name
//		return Response.ok().entity("test").build();
//	}
//
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response updateFavorites() {
//
//		// update icecream and name
//		return Response.ok().entity("").build();
//	}
//
//	@GET
//	@Path("{name}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getByName(@PathParam("name") String name) {
//
//		// insert icecream and name
//		return Response.ok().entity(name).build();
//	}

}
