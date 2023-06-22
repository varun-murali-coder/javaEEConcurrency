package com.app.rest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.app.runnables.LoggingProcessor;

@Path("logging")
public class LoggingResource {
	
	@Resource(lookup="java:comp/DefaultManagedThreadFactory")
	private ManagedThreadFactory threadFactory;
	@GET
	public String logData() {
		Thread t=threadFactory.newThread(new LoggingProcessor());
		t.setName("Logging thread");
		t.start();
		ExecutorService service=getAppPool();
		for(int i=0;i<7;i++) {
			service.submit(new LoggingProcessor());
		}
		return "Job has initiated its work";
	}
	
	public ExecutorService getAppPool() {
		ExecutorService service=new ThreadPoolExecutor(3, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),threadFactory); 
				return service;
	}

}
