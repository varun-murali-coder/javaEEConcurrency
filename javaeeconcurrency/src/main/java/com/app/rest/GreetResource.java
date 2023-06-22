package com.app.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//Creating a rest bean
@Path("greetUser")
public class GreetResource {
	
	@GET
	public String hello() {
		return "Java EE concurrency starts!";
	}

}
