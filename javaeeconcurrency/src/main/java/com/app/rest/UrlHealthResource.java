package com.app.rest;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.app.runnables.UrlHealthProcessor;

@Path("urlCheck")
public class UrlHealthResource {
	
	@Resource(lookup="java:comp/DefaultManagedScheduledExecutorService")
	private ManagedScheduledExecutorService managedScheduledExecutorService;
	
	@GET
	public String checkHealthOfApp(){
		String message="";
		managedScheduledExecutorService.schedule(new UrlHealthProcessor(), 3,TimeUnit.SECONDS);
		message="Health check initiated";
		return message;
		
	}

}
