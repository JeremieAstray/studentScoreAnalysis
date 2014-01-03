package com.jeremie.scoreAnalysis.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author jeremie
 */
public class MainUI extends Application {

	private static final String AppCss = MainUI.class.getResource("MainUI.css")
			.toExternalForm();

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		scene.getStylesheets().add(AppCss);
		stage.setTitle("成绩分析程序");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
