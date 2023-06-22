package com.app.runnables;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlHealthProcessor implements Runnable {

	private static final String urlName="http://www.google.com";//checking google up and running
	@Override
	public void run() {
String statusOfApp="";
System.out.println("Thread name:"+Thread.currentThread().getName()+" checking health of app");
try {
	URL url=new URL(urlName);
	HttpURLConnection conn=(HttpURLConnection)url.openConnection();
	conn.setRequestMethod("GET");
	conn.connect();
	int responseCode=conn.getResponseCode();
	if(responseCode==200) {
		statusOfApp="yes it is working";
	}else {
		statusOfApp="something went wrong";
	}
	System.out.println("The status of app is :"+statusOfApp);
} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
	}

}
