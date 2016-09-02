package com.example.jostlechallenge.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface JsonServiceAsync {

	void getPictures(AsyncCallback<String> callback);

}
