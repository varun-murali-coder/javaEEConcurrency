package com.app.rest;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ContextService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.app.runnables.ContextServiceProcessor;

@Path("contextService")
public class ContextServiceResource {
	
	@Resource(lookup="java:comp/DefaultContextService")
	private ContextService service;
	
	@GET
	public String getSecurityInfo() {
		ContextServiceProcessor context=new ContextServiceProcessor();
		Runnable proxy=service.createContextualProxy(context, Runnable.class);
		Thread t=new Thread(proxy);
		t.setName("Contextual Proxy Thread");
		t.start();
		return "Security info retrieved";
	}

}
