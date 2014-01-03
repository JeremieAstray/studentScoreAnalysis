package com.jeremie.scoreAnalysis.view;

import javax.swing.JOptionPane;

/**
 * 
 * @author jeremie
 */
public class MessageView {

	public static void createView(String message) {
		/*
		 * Stage primaryStage = new Stage(); Group root = new Group();
		 * primaryStage.setScene(new Scene(root)); primaryStage.setHeight(200);
		 * primaryStage.setWidth(350); primaryStage.setResizable(false); Text
		 * sample = new Text(0,40,message);
		 * sample.setFont(Font.font(Font.getDefault().getFamily(),
		 * FontWeight.BOLD,36)); final DropShadow dropShadow = new DropShadow();
		 * sample.setEffect(dropShadow);
		 * 
		 * root.getChildren().add(sample); primaryStage.setTitle("错误信息");
		 * primaryStage.show();
		 */
		JOptionPane.showMessageDialog(null, message);
	}
}
