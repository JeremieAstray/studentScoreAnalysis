package com.jeremie.scoreAnalysis.controller;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

import com.jeremie.scoreAnalysis.model.AnalysisModel;
import com.jeremie.scoreAnalysis.model.Student;
import com.jeremie.scoreAnalysis.service.AnalysisService;
import com.jeremie.scoreAnalysis.service.MyFileReader;
import com.jeremie.scoreAnalysis.service.MyFileWriter;
import com.jeremie.scoreAnalysis.service.SearchService;
import com.jeremie.scoreAnalysis.view.MessageView;
import com.jeremie.scoreAnalysis.view.MyBarChart;
import com.jeremie.scoreAnalysis.view.MyFileChooser;
import com.jeremie.scoreAnalysis.view.MyPieChart;
import com.jeremie.scoreAnalysis.view.ScoreEditingCell;
import com.jeremie.scoreAnalysis.view.SearchBox;

/**
 * 
 * @author jeremie
 */

public class MainController implements Initializable {

	String fileName;

	@FXML
	private Text status;

	private ArrayList<Student> students = null;
	private AnalysisModel analysisModel = null;

	@FXML
	private TextField max;
	@FXML
	private TextField min;
	@FXML
	private TextField avg;
	@FXML
	private TextField excellent;
	@FXML
	private TextField well;
	@FXML
	private TextField mid;
	@FXML
	private TextField pass;
	@FXML
	private TextField fail;
	@FXML
	private TextField excellentPercentage;
	@FXML
	private TextField wellPercentage;
	@FXML
	private TextField midPercentage;
	@FXML
	private TextField passPercentage;
	@FXML
	private TextField failPercentage;

	@FXML
	private SearchBox searchBox;

	@FXML
	private void LoadFile(ActionEvent event) {

		MyFileReader fileLloader = new MyFileReader();
		fileLloader.setFile(MyFileChooser.chooseFile());
		if (fileLloader.getFile() != null) {
			try {
				students = fileLloader.analyzeFile();
			} catch (NumberFormatException e) {
				MessageView.createView("文件格式错误，请重新编排文件格式！");
				e.printStackTrace();
			}
			if (students.size() != 0) {
				ObservableList<Student> data = FXCollections
						.observableArrayList(students);
				AnalysisStudents(students);
				status.setText(fileLloader.getFile().getPath() + "_共"
						+ analysisModel.getTotalNum() + "人");
				studentView.setItems(data);
				String temp = fileLloader.getFile().getName();
				fileName = temp.substring(0, temp.lastIndexOf("."));
			}
		} else {
			MessageView.createView("文件为空");
		}
	}

	private void AnalysisStudents(ArrayList<Student> students) {
		AnalysisService analysis = new AnalysisService();
		analysisModel = analysis.analyseFile(students);

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);

		max.setText(((Integer) analysisModel.getMax()).toString());
		min.setText(((Integer) analysisModel.getMin()).toString());
		avg.setText(df.format(analysisModel.getAvg()));

		excellent.setText(((Integer) analysisModel.getExcellent()).toString());
		well.setText(((Integer) analysisModel.getWell()).toString());
		mid.setText(((Integer) analysisModel.getMid()).toString());
		pass.setText(((Integer) analysisModel.getPass()).toString());
		fail.setText(((Integer) analysisModel.getFail()).toString());

		double excellentPercentageTempDouble = ((double) analysisModel
				.getExcellent()) / analysisModel.getTotalNum() * 100;
		double wellPercentageTempDouble = ((double) analysisModel.getWell())
				/ analysisModel.getTotalNum() * 100;
		double midPercentageTempDouble = ((double) analysisModel.getMid())
				/ analysisModel.getTotalNum() * 100;
		double passPercentageTempDouble = ((double) analysisModel.getPass())
				/ analysisModel.getTotalNum() * 100;
		double failPercentageTempDouble = ((double) analysisModel.getFail())
				/ analysisModel.getTotalNum() * 100;

		excellentPercentage.setText(df.format(excellentPercentageTempDouble));
		wellPercentage.setText(df.format(wellPercentageTempDouble));
		midPercentage.setText(df.format(midPercentageTempDouble));
		passPercentage.setText(df.format(passPercentageTempDouble));
		failPercentage.setText(df.format(failPercentageTempDouble));
	}

	@FXML
	private void saveFile(ActionEvent event) {
		MyFileWriter myFileWriter = new MyFileWriter();
		if (students == null || students.isEmpty()) {
			MessageView.createView("学生信息未载入");
			return;
		}
		File file = null;
		file = MyFileChooser.chooseSaveFile(fileName);
		if (file != null) {
			myFileWriter.saveFile(file, students);
		}
	}

	@FXML
	private void CloseAll(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	public TableView<Student> studentView;

	public TableColumn<Student, String> studentNum = new TableColumn<Student, String>(
			"学号");
	public TableColumn<Student, String> name = new TableColumn<Student, String>(
			"名称");
	public TableColumn<Student, Integer> score = new TableColumn<Student, Integer>(
			"分数");

	@FXML
	private ObservableList<Student> columns = FXCollections
			.observableArrayList();

	@FXML
	private void pieChartButtonAction(ActionEvent event) {
		if (analysisModel != null) {
			MyPieChart.printMyPieChart(analysisModel);
		} else {
			MessageView.createView("未载入数据");
		}
	}

	@FXML
	private void barChartButtonAction(ActionEvent event) {
		if (analysisModel != null) {
			MyBarChart.printMyBarChart(analysisModel);
		} else {
			MessageView.createView("未载入数据");
		}
	}

	@FXML
	private void search(KeyEvent event) {
		if (students == null) {
			return;
		}
		String message = searchBox.getTextBox().getText();
		SearchService searchService = new SearchService();
		ArrayList<Student> studentsTemp = null;
		if (message.equals("")) {
			studentsTemp = students;
		} else {
			studentsTemp = searchService.search(students, message);
		}
		ObservableList<Student> data = FXCollections
				.observableArrayList(studentsTemp);
		studentView.setItems(data);

	}

	@FXML
	private void clearText() {
		if (students != null) {
			ObservableList<Student> data = FXCollections
					.observableArrayList(students);
			studentView.setItems(data);
		}
	}

	@FXML
	private void clearTable(ActionEvent event) {
		max.setText("");
		min.setText("");
		avg.setText("");
		excellent.setText("");
		well.setText("");
		mid.setText("");
		pass.setText("");
		fail.setText("");
		excellentPercentage.setText("");
		wellPercentage.setText("");
		midPercentage.setText("");
		passPercentage.setText("");
		failPercentage.setText("");
		status.setText("");
		if (students != null) {
			students.clear();
			ObservableList<Student> data = FXCollections
					.observableArrayList(students);
			studentView.setItems(data);
		}
		analysisModel = null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableViewinitialize();
		searchBox.setEventHandler(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				clearText();
			}
		});

	}

	@SuppressWarnings("unchecked")
	private void tableViewinitialize() {
		studentNum
				.setCellValueFactory(new PropertyValueFactory<Student, String>(
						"studentNum"));
		studentNum.setPrefWidth(150);
		name.setCellValueFactory(new PropertyValueFactory<Student, String>(
				"name"));
		name.setPrefWidth(150);
		score.setCellValueFactory(new PropertyValueFactory<Student, Integer>(
				"score"));
		score.setPrefWidth(100);
		studentView.getColumns().clear();
		studentView.setEditable(true);

		score.setEditable(true);

		Callback<TableColumn<Student, Integer>, TableCell<Student, Integer>> cellFactoryNum = new Callback<TableColumn<Student, Integer>, TableCell<Student, Integer>>() {
			public TableCell<Student, Integer> call(
					TableColumn<Student, Integer> p) {
				return new ScoreEditingCell();
			}
		};
		score.setCellFactory(cellFactoryNum);
		score.setOnEditCommit(new EventHandler<CellEditEvent<Student, Integer>>() {
			@Override
			public void handle(CellEditEvent<Student, Integer> score) {
				((Student) score.getTableView().getItems()
						.get(score.getTablePosition().getRow())).setScore(score
						.getNewValue());
				AnalysisStudents(students);
			}
		});

		studentView.getColumns().addAll(studentNum, name, score);

	}

}
