package com.example.jostlechallenge.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Picture extends JavaScriptObject{
	//Constructor required for overlay types
	protected Picture() {}
	
    // JSNI methods to get data?
	public final native String getAlbumId() /*-{ return this.albumId; }-*/;
	public final native String getId() /*-{ return this.id; }-*/;
	public final native String getTitle() /*-{ return this.title; }-*/;
	public final native String getUrl() /*-{ return this.url; }-*/;
	public final native String getThumbnailUrl() /*-{ return this.thumbnailUrl; }-*/;

}
