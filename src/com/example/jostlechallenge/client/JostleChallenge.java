package com.example.jostlechallenge.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.dom.client.VideoElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.media.client.Video;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class JostleChallenge implements EntryPoint {
	private TabBar tabs = new TabBar();
	private final DeckPanel deck = new DeckPanel();
	private LayoutPanel mainPanel = new LayoutPanel();
	private VerticalPanel tabOne = new VerticalPanel();
	private ScrollPanel tabOneScroll = new ScrollPanel();
	private Label tabOneTitle = new Label();
	private Label tabOneBody = new Label();
	private VerticalPanel tabTwo = new VerticalPanel();
	private ScrollPanel tabTwoScroll = new ScrollPanel();
	private Label alert = new Label();
	private VerticalPanel tabThree = new VerticalPanel();
	private ScrollPanel tabThreeScroll = new ScrollPanel();
	private Label tabThreeTitle = new Label();
	private HTML instructions = new HTML();
	private HorizontalPanel textboxPanel = new HorizontalPanel();
	private TextBox inputTextBox = new TextBox();
	private Button submitButton = new Button("Submit");
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private List<String> buttonColors = new ArrayList();
	private Label feedback = new Label();
	private Label display = new Label();
	private String emptyMessage = "Please write something in the text box";
	private JsonServiceAsync pictureServ = GWT.create(JsonService.class);
	private static final String TITLE_URL = "http://jsonplaceholder.typicode.com/posts/1";

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {
		// Make tabs (makes 3)
		for (int i = 0; i < 3; i++) {
			tabs.addTab("Tab " + Integer.toString(i + 1));
		}

		// Add things to deck
		buildTabOne();
		tabOneScroll.add(tabOne);
		deck.add(tabOneScroll);

		buildTabTwo();
		tabTwoScroll.add(tabTwo);
		deck.add(tabTwoScroll);

		buildTabThree();
		tabThreeScroll.add(tabThree);
		deck.add(tabThreeScroll);

		// SelectionHandler for tabs
		tabs.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				deck.showWidget(event.getSelectedItem());
			}
		});

		// TODO Fade in-out animation
		//deck.addStyleName("fade");
		deck.setAnimationEnabled(true);
		
		mainPanel.add(deck);
		mainPanel.add(tabs);

		mainPanel.setWidgetTopBottom(deck, 0, Unit.PX, 50, Unit.PX);
		mainPanel.setWidgetBottomHeight(tabs, 0, Unit.PX, 50, Unit.PX);

		tabs.selectTab(0);

		// Associate main panel with HTML
		RootLayoutPanel.get().add(mainPanel);
		
//		if(deck.isAnimationEnabled()){
//			Window.alert("Animation is enabled for deck!");
//		} else {
//			Window.alert("Animation is NOT enabled for deck!");
//		}

	}

	private void buildTabOne() {
		getTitleAndBody();
		tabOne.add(tabOneTitle);
		tabOne.add(tabOneBody);

		tabOneTitle.setStyleName("title");

		Video video = Video.createIfSupported();
		if (video == null) {
			// Error message here
			Window.alert("HTML5 Video is not supported by your browser");
			return;
		}
		video.addSource("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4",
				VideoElement.TYPE_MP4);
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
		tabTwo.setWidth("100%");
		tabTwo.setHeight("100%");
		tabTwo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tabTwo.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		alert.setVisible(false);

		// making a RPC call to server and then getting server to get JSON from
		// remote server
		// Note: this is probably really inefficient but I wanted to see if I
		// could do it
		// Initialize the service proxy.
		if (pictureServ == null) {
			pictureServ = GWT.create(JsonService.class);
		}

		// Set up the callback object.
		AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				// Got an error
				alert.setText("The RPC call didn't work!");
				alert.setVisible(true);
			}

			public void onSuccess(String result) {
				// alert.setText("Picture title: " + result.getTitle());
				// alert.setText("This is the result: " + result);
				// alert.setVisible(true);
				displayPictures(JsonUtils.<JsArray<Picture>>safeEval(result));
				// updateTable(JsonUtils.<JsArray<StockData>>safeEval(response.getText()));
			}
		};

		// Make the call to the stock price service.
		pictureServ.getPictures(callback);

		tabTwo.add(alert);

	}

	private void displayPictures(JsArray<Picture> pictures) {
		// Add picture and description to tab
		for (int i = 0; i < 10; i++) {
			final Image image = new Image();
			Picture picture = pictures.get(i);
			final String id = picture.getId();

			// Hook up error handler (in case image doesn't load)
			image.addErrorHandler(new ErrorHandler() {
				public void onError(ErrorEvent event) {
					alert.setText("An error occurred while loading.");
				}
			});

			// Point the image at an URL
			image.setUrl(picture.getUrl());

			// Add a ClickHandler so the image is clickable
			image.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Window.alert("Picture position is: " + id);
				}
			});

			image.addStyleName("customImage");

			// Description for the picture
			Label picDescription = new Label(pictures.get(i).getTitle());

			tabTwo.add(image);
			tabTwo.add(picDescription);
		}
		Label out = new Label("Hello");
	}

	private void buildTabThree() {
		tabThree.setWidth("100%");
		tabThree.setHeight("100%");
		tabThree.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tabThreeTitle.setText("Hello! Welcome!");
		tabThreeTitle.setStyleName("title");
		instructions.setHTML("Please type your word below. A word must: " + "<br/>- have 5 - 12 characters"
				+ "<br/>- contain at least one number" + "<br/>- not have any special characters, including spaces");

		feedback.setText(emptyMessage);

		// Add keypress handler for textbox. Triggers for characters.
		inputTextBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				updateLabel();
			}
		});

		// Add keydown handler for textbox. Triggers for other keys.
		inputTextBox.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					// Submit the word
					updateLabel();
					submitWord();
				} else {
					updateLabel();
				}
			}
		});

		// Submit the word when the button is pressed
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				updateLabel();
				submitWord();
			}
		});

		tabThree.add(tabThreeTitle);
		tabThree.add(instructions);
		textboxPanel.add(inputTextBox);
		textboxPanel.add(submitButton);
		tabThree.add(textboxPanel);
		tabThree.add(feedback);
		tabThree.add(display);
		tabThree.add(buttonPanel);
	}

	// Checks if word follows the rules and if it does, go to next step
	private void submitWord() {
		// Check for rules
		String input = inputTextBox.getText();

		if (!input.matches("^[0-9a-zA-Z\\.]{5,12}$")) {
			Window.alert("Sorry, invalid word!");
			// inputTextBox.setText("");
		} else {
			if (input.matches(".*\\d+.*")) {
				// Go to next step
				colorChanger(input);
				inputTextBox.setText("");
			} else {
				Window.alert("Sorry, invalid word!");
			}
		}
	}

	private void colorChanger(String input) {
		makeButtonPanel();

		display.setText(input);
		display.setStyleName("displayText");
	}

	// Makes buttons that change text color. Color strings must be valid.
	private void makeButtonPanel() {
		// Makes sure you don't make multiple buttonPanels
		if (buttonColors.size() == 0) {
			Label colorChanger = new Label("Choose a color: ");
			buttonPanel.add(colorChanger);

			buttonColors.add("Black");
			buttonColors.add("Red");
			buttonColors.add("Blue");
			buttonColors.add("Green");
			buttonColors.add("Purple");

			for (int i = 0; i < buttonColors.size(); i++) {
				final String color = buttonColors.get(i);
				Button colorButton = new Button(color);
				colorButton.addStyleDependentName("color");

				colorButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						display.getElement().getStyle().setColor(color);
					}
				});

				buttonPanel.add(colorButton);
			}
		}

		// for (int i = 0; i < buttonColors.size(); i++) {
		// final String color = buttonColors.get(i);
		// Button colorButton = new Button(color);
		// final String currentColor = "Black";
		//
		// colorButton.addMouseOverHandler(new MouseOverHandler() {
		// public void onMouseOver(MouseOverEvent event) {
		// // TODO Auto-generated method stub
		// currentColor = display.getElement().getStyle().getColor();
		// display.getElement().getStyle().setColor(color);
		// }
		// });
		//
		// colorButton.addMouseOutHandler(new MouseOutHandler() {
		// public void onMouseOut(MouseOutEvent event) {
		// // TODO Auto-generated method stub
		// display.getElement().getStyle().setColor(currentColor);
		// }
		// });
		//
		// buttonPanel.add(colorButton);
		// }
	}

	private void updateLabel() {
		// Make a timer so it will get updated text from textbox
		Timer timer = new Timer() {
			public void run() {
				String input = inputTextBox.getText();
				checkString(input);
			}
		};
		// Execute the timer
		timer.schedule(1);
	}

	// Checks text for rules (5-12 chars, no special chars, at least one number)
	private void checkString(String input) {
		if (!input.matches("^[0-9A-Za-z]{0,100}$")) {
			feedback.setText("Please do not use special characters!");
		} else {
			if (input != "") {
				if (input.length() >= 5) {
					if (input.length() <= 12) {
						if (input.matches(".*\\d+.*")) {
							feedback.setText(input);
						} else {
							// No numbers
							feedback.setText("Must contain at least one number");
						}
					} else {
						// Text too long
						feedback.setText("Please use less than 12 characters");
					}
				} else {
					// Text too short
					feedback.setText("Please use at least 5 characters");
				}
			} else {
				// Textbox is empty
				feedback.setText(emptyMessage);
			}
		}
	}
}