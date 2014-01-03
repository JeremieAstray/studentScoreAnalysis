package com.jeremie.scoreAnalysis.service;

import java.util.ArrayList;

import com.jeremie.scoreAnalysis.model.Student;

/**
 * 
 * @author jeremie
 */
public class SearchService {

	public ArrayList<Student> search(ArrayList<Student> students, String message) {
		ArrayList<Student> studentsTemp = new ArrayList<Student>();
		for (Student student : students) {
			/*
			 * if(student.getName().contains(message)
			 * ||student.getStudentNum().contains(message)
			 * ||judgeNumber(student.getScore() , message)) {
			 * studentsTemp.add(student); }
			 */
			if (student.toString().contains(message))
				studentsTemp.add(student);
		}
		return studentsTemp;
	}

	/*
	 * private boolean judgeNumber(int score, String message) {
	 * if(message.length() <= 3 && message.matches("^[1-9]\\d*$") ) if(score ==
	 * Integer.parseInt(message)) return true; return false; }
	 */
}
