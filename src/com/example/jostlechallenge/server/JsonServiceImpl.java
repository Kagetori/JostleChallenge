package com.example.jostlechallenge.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.example.jostlechallenge.client.JsonService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class JsonServiceImpl extends RemoteServiceServlet implements JsonService {
	private static String PICTURE_URL = "http://jsonplaceholder.typicode.com/photos";

	// Get data from server	
	public String getPictures(){	
		String jsonString = queryPicturesFromRemote();
		
		return jsonString;
	}

	private String queryPicturesFromRemote() {
		String returnString = "";
		try {
			returnString = readUrl();
		} catch (Exception e) {
			System.out.println("Invalid httpRequest");
		}
		return returnString;
	}
	
	private static String readUrl() throws Exception {
		String urlString = PICTURE_URL;
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
}
