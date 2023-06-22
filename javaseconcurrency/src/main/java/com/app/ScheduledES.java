package com.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.app.runnables.CleaningProcessor;

public class ScheduledES {

	public static void main(String[] args) {

		ScheduledExecutorService service=Executors.newSingleThreadScheduledExecutor();
		//service.schedule(new CleaningProcessor(), 5, TimeUnit.SECONDS);
		//Imp:-scheduleWithFixedDelay takes into account the time required for task to complete
		//eg:-5s+2s+delay(4s),after this only service schduled again
		//1)scheduleAtfixedRate-->1st task starts after 5 seconds,2nd task-->after 4 seconds
		//(Time taken by run not taken into consideration),sleep time of 2s not taken into consideration
		//service.scheduleAtFixedRate(new CleaningProcessor(),5,4, TimeUnit.SECONDS);
		//2)scheduleWithFixedDelay:-The sleep time also taken into consideration
		//Task 1-->starts after 5 seconds,task 2 started after-->2s+4s,task 3-->2s(sleep time)+4s
		service.scheduleWithFixedDelay(new CleaningProcessor(), 5, 4, TimeUnit.SECONDS);
	}

}
