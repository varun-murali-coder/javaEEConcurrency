package com.app.runnables;

import java.io.File;

public class CleaningProcessor implements Runnable {

	@Override
	public void run() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File folder=new File("D:\\deletefiles");
		File[] files=folder.listFiles();
		for(File file:files) {
			if(System.currentTimeMillis()-file.lastModified()>3*60*1000) {
				//3 minutes
				System.out.println("File is deleted successfully" +file.getName());
				//file.delete();
			}
		}
	}

}
