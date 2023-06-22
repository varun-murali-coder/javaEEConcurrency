package com.app.runnables;

import java.security.AccessController;

import javax.security.auth.Subject;

public class ContextServiceProcessor implements Runnable {

	@Override
	public void run() {
System.out.println("Thread executing security info"+Thread.currentThread().getName());
		Subject subject=Subject.getSubject(AccessController.getContext());
		System.out.println("Security info is:-"+subject);
	}

}
