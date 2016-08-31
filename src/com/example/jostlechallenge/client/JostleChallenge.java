package com.example.jostlechallenge.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
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

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		TabBar tabs = new TabBar();
		final DeckPanel deck = new DeckPanel(); 

		//Make tabs + deck labels
		for(int i = 0; i < 3; i++) {
		    tabs.addTab("Tab " + Integer.toString(i + 1));
		    deck.add(new Label("Label " + Integer.toString(i + 1)));
		}

		tabs.addSelectionHandler(new SelectionHandler<Integer>() {
		    @Override
		    public void onSelection(SelectionEvent<Integer> event) {
		        deck.showWidget(event.getSelectedItem());
		    }
		});
		
		LayoutPanel p = new LayoutPanel();
		p.add(deck);
		p.add(tabs);
		
		p.setWidgetBottomHeight(tabs, 0, Unit.PX, 50, Unit.PX);	
		tabs.selectTab(0);
		
		//Associate main panel with HTML
		RootLayoutPanel.get().add(p);
		
	}
}