package com.app;

import com.app.runnables.ReadFileThread;

public class ThreadClass {

	public static void main(String[] args) {

		ReadFileThread thread1=new ReadFileThread();
		ReadFileThread thread2=new ReadFileThread();
		ReadFileThread thread3=new ReadFileThread();
		thread1.start();
		thread2.start();
		thread3.start();


	}

}
