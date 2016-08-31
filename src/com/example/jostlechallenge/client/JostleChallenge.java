package com.example.jostlechallenge.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
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
	private VerticalPanel tab1 = new VerticalPanel();
	private Label tabOneTitle = new Label();
	private Label tabOneBody = new Label();
	private static final String TITLE_URL = "http://jsonplaceholder.typicode.com/posts/1";

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		// Make tabs (makes 3)
		for (int i = 0; i < 3; i++) {
			tabs.addTab("Tab " + Integer.toString(i + 1));
		}

		// Adds things to deck
		tabOneTitle.setText("TITLE");
		// videoText.setText("Text description");
		buildTab1();
		tab1.add(tabOneTitle);
		tab1.add(tabOneBody);

		deck.add(tab1);
		deck.add(new Label("This thing 2"));
		deck.add(new Label("This thing 3"));

		// SelectionHandler for tabs
		tabs.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				deck.showWidget(event.getSelectedItem());
			}
		});

		mainPanel.add(deck);
		mainPanel.add(tabs);

		mainPanel.setWidgetBottomHeight(tabs, 0, Unit.PX, 50, Unit.PX);

		tabs.selectTab(0);

		// Associate main panel with HTML
		RootLayoutPanel.get().add(mainPanel);

	}

	private void buildTab1() {
		String url = TITLE_URL;
		url = URL.encode(url);

		JsonpRequestBuilder builder = new JsonpRequestBuilder();
		builder.requestObject(url, new AsyncCallback<TitleData>() {
			public void onFailure(Throwable caught) {
				// TODO handle failure
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
}