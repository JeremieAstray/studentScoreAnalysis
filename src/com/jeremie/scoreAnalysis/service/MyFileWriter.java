package com.jeremie.scoreAnalysis.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.jeremie.scoreAnalysis.model.Student;
import com.jeremie.scoreAnalysis.view.MessageView;

/**
 * 
 * @author jeremie
 */
public class MyFileWriter {

	public void saveFile(File file, ArrayList<Student> students) {
		try {
			if (file.getName().toLowerCase().endsWith(".txt")) {
				BufferedWriter bw = null;
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
				for (Student student : students) {
					bw.write(student.getStudentNum() + ","
							+ student.getName() + "," + student.getScore());
					bw.newLine();
				}
				bw.flush();
				bw.close();
			} else if (file.getName().toLowerCase().endsWith(".dat")) {
				FileOutputStream fos = null;
				fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(students);
				oos.flush();
				oos.close();
			} else {
				MessageView.createView("文件后缀名错误");
			}
		} catch (FileNotFoundException e) {
			MessageView.createView("找不到文件");
			e.printStackTrace();
		} catch (IOException e) {
			MessageView.createView("保存文件错误");
			e.printStackTrace();
		}
		MessageView.createView("保存成功!");
	}
}
