package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.app.runnables.LoggingProcessor;

public class ExecutorServiceInvokeAnyAndAll {

	public static void main(String[] args) {

		List<Callable<Boolean>> callables=new ArrayList<Callable<Boolean>>();
		ExecutorService service=Executors.newFixedThreadPool(2);
		callables.add(new LoggingProcessor());
		callables.add(new LoggingProcessor());
		callables.add(new LoggingProcessor());
		callables.add(new LoggingProcessor());
		callables.add(new LoggingProcessor());
		callables.add(new LoggingProcessor());
		callables.add(new LoggingProcessor());
//InvokeAny-->returns only one successful call/exception ie.one future object only
		//InvokeAll-->returns all future objects for all callables.
		//Imp future.get()-->Blocking operation
		//3 types of shutdown
		try {
			List<Future<Boolean>> futures=service.invokeAll(callables);
			for(Future<Boolean> future:futures) {
				System.out.println("The operations are :-"+future.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(service.invokeAny(callables));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.shutdown();//This doesnt guarantee completion of all initiated tasks(no new tasks
		//accepted,all previously submitted tasks are executed)
		//shutdownNow-->shuts down immediately
		//3)awaitTermination:-blocks untill all tasks completed,after the shutdown request/
		//time elapses/the current thread is executed.
		try {
			System.out.println("is service shutdown?"+service.awaitTermination(30, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
service.shutdownNow();
			e.printStackTrace();
		}
		
		
	}

}
