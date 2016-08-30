package com.example.jostlechallenge.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class JostleChallenge implements EntryPoint {

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		// TODO Assemble tab panel
		TabLayoutPanel mainPanel = new TabLayoutPanel(50, Unit.PX); // height of
																	// tab bar
		mainPanel.add(new HTML("this"), "[this]");
		mainPanel.add(new HTML("that"), "[that]");
		mainPanel.add(new HTML("the other"), "[the other]");
		
		//TODO Associate main panel with HTML
		RootPanel.get("jostleChallenge").add(mainPanel);
		

		// TODO Create 1st tab
		// TODO Create 2nd tab
		// TODO Create 3rd tab
	}
}