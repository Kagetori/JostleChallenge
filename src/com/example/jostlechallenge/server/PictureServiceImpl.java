package com.example.jostlechallenge.server;

import com.example.jostlechallenge.client.Picture;
import com.example.jostlechallenge.client.PictureService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class PictureServiceImpl extends RemoteServiceServlet implements PictureService {

	public Picture getPictures() throws IllegalArgumentException{
		// TODO get data from server
		//Picture[] pictures = new Picture[1];
		Picture picture = new Picture("title", "myurl");
		
		return picture;
	}

}
