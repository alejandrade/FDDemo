package com.gigamog.settings;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.gigamog.controllers.AccountController;


@ApplicationPath("/")
public class MyApplication extends ResourceConfig {

    
    public MyApplication() {
    	register(FDFilter.class);
    	//register(KunderaConnect.class);
       packages(AccountController.class.getPackage().getName());
    }
}
