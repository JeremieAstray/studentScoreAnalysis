package com.jeremie.scoreAnalysis.view;

import java.util.Arrays;

import com.jeremie.scoreAnalysis.model.AnalysisModel;

import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * 
 * @author jeremie
 */
public class MyBarChart {

	private static final String[] Level = { "优秀", "良好", "中等", "及格", "不及格" };

	public static void printMyBarChart(AnalysisModel analysisModel) {
		int excellent = analysisModel.getExcellent();
		int well = analysisModel.getWell();
		int mid = analysisModel.getMid();
		int pass = analysisModel.getPass();
		int fail = analysisModel.getFail();
		Stage primaryStage = new Stage();
		Group root = new Group();
		primaryStage.setScene(new Scene(root));
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();
		BarChart<String, Number> bChart = new BarChart<String, Number>(xAxis,
				yAxis);
		bChart.setBarGap(-30);
		bChart.setCategoryGap(40);
		xAxis.setLabel("等级");
		yAxis.setTickLabelRotation(50);
		yAxis.setLabel("人数");
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> series4 = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> series5 = new XYChart.Series<String, Number>();
		series1.setName(Level[0] + excellent + "人");
		series2.setName(Level[1] + well + "人");
		series3.setName(Level[2] + mid + "人");
		series4.setName(Level[3] + pass + "人");
		series5.setName(Level[4] + fail + "人");

		xAxis.setCategories(FXCollections.<String> observableArrayList(Arrays
				.asList(Level)));
		series1.getData().add(
				new XYChart.Data<String, Number>(Level[0], excellent));
		series2.getData().add(new XYChart.Data<String, Number>(Level[1], well));
		series3.getData().add(new XYChart.Data<String, Number>(Level[2], mid));
		series4.getData().add(new XYChart.Data<String, Number>(Level[3], pass));
		series5.getData().add(new XYChart.Data<String, Number>(Level[4], fail));

		bChart.getData().add(series1);
		bChart.getData().add(series2);
		bChart.getData().add(series3);
		bChart.getData().add(series4);
		bChart.getData().add(series5);

		primaryStage.setResizable(false);
		root.getChildren().add(bChart);
		primaryStage.setTitle("柱形图");
		primaryStage.show();
	}
}
