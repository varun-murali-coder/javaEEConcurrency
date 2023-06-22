package com.app.rest;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.app.runnables.TransactionProcessor;

@Path("transaction")
public class TransactionResource {
	
	@Resource(lookup="java:comp/DefaultManagedExecutorService")
	private ManagedExecutorService service;
	
	@POST
	public String startTransaction() {
		
		service.submit(new TransactionProcessor());
		return "Check the console to see transaction successfull or not?";
	}

}
