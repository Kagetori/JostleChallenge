package com.example.jostlechallenge.client;

import java.io.Serializable;

public class Picture implements Serializable{
	
	private int position;
	private String title;
	private String url;
	
	public Picture(){
	}
	
	public Picture (int position, String title, String url){
		this.position = position;
		this.title = title;
		this.url = url;
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getUrl(){
		return this.url;
	}


}
