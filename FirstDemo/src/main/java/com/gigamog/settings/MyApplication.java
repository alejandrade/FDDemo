package com.gigamog.settings;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.gigamog.controllers.AccountController;
import com.gigamog.controllers.IceCreamController;
import com.gigamog.pojo.Access;


@ApplicationPath("/")
public class MyApplication extends ResourceConfig {
	
	

    
    public MyApplication() {
    	register(BaseFilter.class);
    	register(JwtFilter.class);
    	register(Access.class);
    	register(IceCreamController.class);
    	register(AccountController.class);
    	register(MyExcpetionMapper.class);

 
    }
    
    
}
