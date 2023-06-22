package com.app.rest;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.app.beans.BankAccount;
import com.app.dao.BankAccountDao;
import com.app.runnables.ReportsProcessor;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Path("reports")//Rest bean for reports
public class ReportsResource {
	//Method 2-->convienent way(this could be ejb,servlet etc)
	@Resource(lookup="java:comp/DefaultManagedExecutorService")//Dependency injection
	private ManagedExecutorService managedExecutorService;
	
	private BankAccountDao dao;

public ReportsResource() {
		//JNDI lookup-->Method 1
//		try {
//			InitialContext context=new InitialContext();
//			ManagedExecutorService service=(ManagedExecutorService)
//					context.lookup("java:comp/DefaultManagedExecutorService");
//			
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	//Lets setup connection pooling for database connection
	ComboPooledDataSource dataSource=new ComboPooledDataSource();
	dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/javase?useSSL=false");
	try {
		dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
	} catch (PropertyVetoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	dataSource.setUser("javase");
	dataSource.setPassword("javase");

	// set connection pool props
	dataSource.setInitialPoolSize(5);
	dataSource.setMinPoolSize(5);
	dataSource.setMaxPoolSize(20);		
	dataSource.setMaxIdleTime(3000);
	dao=new BankAccountDao(dataSource);
	
	}
@GET
public String generateReports() {

	List<BankAccount> accounts=dao.getAllBankAccounts();
	for(BankAccount account:accounts) {
		Future<Boolean> future=managedExecutorService.submit(new ReportsProcessor(account, dao));
		try {
			System.out.println("Report generated?"+future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	return "Report generation tasks submitted";

}

}
