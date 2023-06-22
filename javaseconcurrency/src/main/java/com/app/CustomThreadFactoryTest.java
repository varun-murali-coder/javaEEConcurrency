package com.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.app.custom.CustomThreadFactory;
import com.app.runnables.LoggingProcessor;

public class CustomThreadFactoryTest {

	public static void main(String[] args) {
ExecutorService service=Executors.newFixedThreadPool(3,new CustomThreadFactory());
for(int i=0;i<6;i++) {
	service.submit(new LoggingProcessor());
	
}
		
	}

}
