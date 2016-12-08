package com.gigamog.settings;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.gigamog.exceptions.UnauthorizedException;
import com.gigamog.pojo.ErrorMessage;

public class MyExcpetionMapper  implements ExceptionMapper<Exception> {
	

@Override
@Produces("application/json")
public Response toResponse(Exception e) {
	// TODO Auto-generated method stub
	Response resp = null;
	if(e instanceof  UnauthorizedException){
		resp = Response.status(401).entity(new ErrorMessage(401,e.getMessage()).toJson()).build();
	}else{
		resp = Response.status(404).entity(new ErrorMessage(404,e.getMessage()).toJson()).build();
	}
	
	return resp;
}

}
