package com.example.jostlechallenge.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("pictures")
public interface PictureService extends RemoteService {
	Picture getPictures() throws IllegalArgumentException;;
}
