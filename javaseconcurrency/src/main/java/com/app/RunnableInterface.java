package com.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RunnableInterface {

	public static void main(String[] args) {
//Basic api-->Thread class,Runnable interface
//High level api-->Executor framework in 1.5 version		
		Runnable r=
				()->{
					try(BufferedReader reader=new BufferedReader(new FileReader(new File(""
							+ "D:\\Spring Course\\H2Databases.txt")))){
						String line=null;
						while((line=reader.readLine())!=null) {
							System.out.println(Thread.currentThread().getName()+"reasing:-"+line);
						}
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				};
				
		Thread t=new Thread(r);
		t.start();
	}

}
