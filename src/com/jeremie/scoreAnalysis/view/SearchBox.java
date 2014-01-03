package com.jeremie.scoreAnalysis.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

/**
 * 
 * @author jeremie
 */
public class SearchBox extends Region {

	private TextField textBox;
	private Button clearButton;
	private EventHandler<ActionEvent> EventHandler;

	public SearchBox() {
		setId("SearchBox");
		getStyleClass().add("search-box");
		setMinHeight(24);
		setPrefSize(150, 24);
		setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
		textBox = new TextField();
		textBox.setPromptText("Search");
		clearButton = new Button();
		clearButton.setVisible(false);
		getChildren().addAll(textBox, clearButton);
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				textBox.setText("");
				textBox.requestFocus();
				EventHandler.handle(actionEvent);
			}
		});
		textBox.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				clearButton.setVisible(textBox.getText().length() != 0);
			}
		});
	}

	public TextField getTextBox() {
		return textBox;
	}

	@Override
	protected void layoutChildren() {
		textBox.resize(getWidth(), getHeight());
		clearButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
	}

	public void setEventHandler(EventHandler<ActionEvent> eventHandler) {
		EventHandler = eventHandler;
	}

}
