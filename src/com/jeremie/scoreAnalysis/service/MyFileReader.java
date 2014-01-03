package com.jeremie.scoreAnalysis.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.jeremie.scoreAnalysis.model.Student;
import com.jeremie.scoreAnalysis.view.MessageView;

/**
 * 
 * @author jeremie
 */
public class MyFileReader {

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	@SuppressWarnings("unchecked")  
	public ArrayList<Student> analyzeFile() throws NumberFormatException {
		ArrayList<Student> students = new ArrayList<Student>();
		if (file.getName().toLowerCase().endsWith(".txt")) {
			try {
				BufferedReader br = null;
				br = new BufferedReader(new InputStreamReader(
						new FileInputStream(file), "UTF-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					String[] obj = line.split(",");
					if (obj.length == 3) {
						Student student = new Student(obj[0], obj[1],
								Integer.parseInt(obj[2]));
						students.add(student);
					}
				}
				br.close();
				if(students.size() == 0)
					MessageView.createView("读取失败或者文件不符合格式!");
				else
					MessageView.createView("读取成功!");
			} catch (FileNotFoundException e) {
				MessageView.createView("找不到文件");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				MessageView.createView("不支持GBK编码错误");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e) {
				MessageView.createView("txt文件内容格式错误");
				e.printStackTrace();
			}
			return students;
		} else if (file.getName().toLowerCase().endsWith(".dat")) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				students = (ArrayList<Student>) ois.readObject();
				ois.close();
				MessageView.createView("读取成功!");
			} catch (FileNotFoundException e) {
				MessageView.createView("找不到文件");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return students;
		} else {
			MessageView.createView("文件后缀名错误！");
		}
		return null;
	}

}
