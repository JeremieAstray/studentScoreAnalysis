package com.jeremie.scoreAnalysis.view;

import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import com.jeremie.scoreAnalysis.model.AnalysisModel;

/**
 * 
 * @author jeremie
 */
public class MyPieChart {

	public static void printMyPieChart(AnalysisModel analysisModel) {
		Stage primaryStage = new Stage();
		Group root = new Group();
		primaryStage.setScene(new Scene(root));
		double excellent = ((double) analysisModel.getExcellent())
				/ analysisModel.getTotalNum() * 100;
		double well = ((double) analysisModel.getWell())
				/ analysisModel.getTotalNum() * 100;
		double mid = ((double) analysisModel.getMid())
				/ analysisModel.getTotalNum() * 100;
		double pass = ((double) analysisModel.getPass())
				/ analysisModel.getTotalNum() * 100;
		double fail = ((double) analysisModel.getFail())
				/ analysisModel.getTotalNum() * 100;
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(
						new PieChart.Data("优秀 " + df.format(excellent) + "%",
								excellent),
						new PieChart.Data("良好 " + df.format(well) + "%", well),
						new PieChart.Data("中等 " + df.format(mid) + "%", mid),
						new PieChart.Data("及格 " + df.format(pass) + "%", pass),
						new PieChart.Data("不及格 " + df.format(fail) + "%", fail));
		PieChart chart = new PieChart(pieChartData);
		chart.setClockwise(false);
		primaryStage.setResizable(false);
		root.getChildren().add(chart);
		primaryStage.setTitle("饼图");
		primaryStage.show();
	}

}
