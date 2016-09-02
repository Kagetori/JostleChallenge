package com.example.jostlechallenge.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("json")
public interface JsonService extends RemoteService {
	String getPictures();
}
