package com.gigamog.exceptions;

public class ServerErrorException extends RuntimeException {
	
	public ServerErrorException(){
		super();
	}

	public ServerErrorException(String message){
		super(message);
	}
}
