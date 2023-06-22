package com.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;


import com.app.dao.UserDao;
import com.app.runnables.UserProcessor;

public class ExecutorServiceThreadPools {

	public static void main(String[] args) {

		ExecutorService service= Executors.newFixedThreadPool(3);
		List<String> users=getUsersFromFile("D:\\JavaEEConcurrency\\javaseconcurrency\\src\\main\\java\\com\\app\\new_users.txt");
		UserDao dao=new UserDao();
		for(String user:users) {
			Future<Integer> recordUpdate=service.submit(new UserProcessor(user, dao));
			try {
				System.out.println("Record updated:-"+recordUpdate.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		service.shutdown();
		System.out.println("Exiting the main method");
	}
	
	public static List<String> getUsersFromFile(String fileName) {
		List<String> users=new ArrayList<String>();
		try(BufferedReader reader=new BufferedReader(new FileReader(new File(fileName)))){
			String line=null;
			while((line=reader.readLine())!=null) {
        users.add(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return users;
	}

}
