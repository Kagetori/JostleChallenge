package com.example.jostlechallenge.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.VideoElement;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class JostleChallenge implements EntryPoint {
	private TabBar tabs = new TabBar();
	private final DeckPanel deck = new DeckPanel();
	private LayoutPanel mainPanel = new LayoutPanel();
	private VerticalPanel tabOne = new VerticalPanel();
	private Label tabOneTitle = new Label();
	private Label tabOneBody = new Label();
	private VerticalPanel tabTwo = new VerticalPanel();
	private PictureServiceAsync pictureServ = GWT.create(PictureService.class);
	private static final String TITLE_URL = "http://jsonplaceholder.typicode.com/posts/1";
	Label alert = new Label("Alert");

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		// Make tabs (makes 3)
		for (int i = 0; i < 3; i++) {
			tabs.addTab("Tab " + Integer.toString(i + 1));
		}

		// Adds things to deck
		//TODO construct tabs 2&3
		buildTabOne();
		deck.add(tabOne);
		buildTabTwo();
		
		deck.add(tabTwo);
		deck.add(new Label("This thing 3"));

		// SelectionHandler for tabs
		tabs.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				deck.showWidget(event.getSelectedItem());
			}
		});

		//TODO Fade in-out animation
		mainPanel.add(deck);
		mainPanel.add(tabs);

		mainPanel.setWidgetBottomHeight(tabs, 0, Unit.PX, 50, Unit.PX);

		tabs.selectTab(0);

		// Associate main panel with HTML
		RootLayoutPanel.get().add(mainPanel);

	}


	private void buildTabOne() {
		getTitleAndBody();
		tabOne.add(tabOneTitle);
		tabOne.add(tabOneBody);
		
		tabOneTitle.setStyleName("title");
		
		Video video = Video.createIfSupported();
		if (video == null) {
			//Error message here
			Window.alert("HTML5 Video is not supported by your browser");
			return;
		}
		video.addSource("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4", VideoElement.TYPE_MP4);
		video.setControls(true);
		video.setStyleName("tabOneVideo");
		tabOne.add(video);
	}

	private void getTitleAndBody() {
		String url = TITLE_URL;
		url = URL.encode(url);

		JsonpRequestBuilder builder = new JsonpRequestBuilder();
		builder.requestObject(url, new AsyncCallback<TitleData>() {
			public void onFailure(Throwable caught) {
				Window.alert("Couldn't retrieve JSON");
			}

			public void onSuccess(TitleData result) {
				if (result == null) {
					Window.alert("Couldn't retrieve JSON");
					return;
				}

				String title = result.getTitle();
				String body = result.getBody();

				tabOneTitle.setText(title);
				tabOneBody.setText(body);
			}
		});
	}
	
	private void buildTabTwo() {	
		alert.setVisible(false);
	    // Initialize the service proxy.
	    if (pictureServ == null) {
	    	pictureServ = GWT.create(PictureService.class);
	    }
		
	    //alert.setText("Got to just before callback");
	     // Set up the callback object.
	    AsyncCallback<Picture> callback = new AsyncCallback<Picture>() {
	      public void onFailure(Throwable caught) {
	        // TODO: Do something with errors.
	    	  alert.setText("Something went wrong!");
	    	  alert.setVisible(true);
	      }

	      public void onSuccess(Picture result) {
	        //alert.setText("Picture title: " + result.getTitle());
	        alert.setText("Picture url: " + result.getUrl());
	        alert.setVisible(true);
	      }
	    };
	    
	    // Make the call to the stock price service.
	    pictureServ.getPictures(callback);
	    
	    tabTwo.add(alert);
		
	}
}