package com.example.jostlechallenge.client;

import com.google.gwt.core.client.JavaScriptObject;

public class TitleData extends JavaScriptObject{
	//Constructor required for overlay types
	protected TitleData() {}
	
    // JSNI methods to get data
	public final native String getTitle() /*-{ return this.title; }-*/;
	public final native String getBody() /*-{ return this.body; }-*/;

}
