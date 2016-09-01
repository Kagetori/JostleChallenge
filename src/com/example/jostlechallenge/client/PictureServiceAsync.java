package com.example.jostlechallenge.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PictureServiceAsync {

	void getPictures(AsyncCallback<Picture> callback) throws IllegalArgumentException;

}
