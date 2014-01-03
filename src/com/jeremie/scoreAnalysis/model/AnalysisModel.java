package com.jeremie.scoreAnalysis.model;

/**
 * 
 * @author jeremie
 */

public class AnalysisModel {

	private int max;
	private int min;
	private double avg;
	private int excellent;
	private int well;
	private int mid;
	private int pass;
	private int fail;
	private int totalNum;

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	public double getAvg() {
		return avg;
	}

	public int getExcellent() {
		return excellent;
	}

	public int getWell() {
		return well;
	}

	public int getMid() {
		return mid;
	}

	public int getPass() {
		return pass;
	}

	public int getFail() {
		return fail;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public void setExcellent(int excellent) {
		this.excellent = excellent;
	}

	public void setWell(int well) {
		this.well = well;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public void setFail(int fail) {
		this.fail = fail;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

}
