package com.app.runnables;

import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

public class LoggingProcessor implements Runnable {

	@Override
	public void run() {

		System.out.println("The thread name in logging resource is"+Thread.currentThread().getName());
		Logger.getLogger(LoggingProcessor.class).log(Level.INFO, "logging data for logging resource");
	}

}
