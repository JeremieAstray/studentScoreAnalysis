package com.jeremie.scoreAnalysis.model;

import java.io.Serializable;

/**
 * 
 * @author jeremie
 */
@SuppressWarnings("serial")
public class Student implements Serializable, Cloneable {

	private String studentNum;
	private String name;
	private int score;

	public String getStudentNum() {
		return studentNum;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Student(String studentNum, String name, int score) {
		super();
		this.studentNum = studentNum;
		this.name = name;
		this.score = score;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Student student = new Student(this.studentNum, this.name, this.score);
		return student;
	}

	@Override
	public String toString() {
		return studentNum + name + score;
	}

}
