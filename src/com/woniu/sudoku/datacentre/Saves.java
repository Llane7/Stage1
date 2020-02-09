package com.woniu.sudoku.datacentre;

import java.io.Serializable;

public class Saves implements Serializable{
	/**
	 * 存档文件
	 */
	private static final long serialVersionUID = 1L;
	Sudo s;
	int min;
	int sec;
public Saves(int min,int sec) {
	this.s = Sudo.getInstance();
	this.min = min;
	this.sec = sec;
}
public Sudo getS() {
	return s;
}
public void setS(Sudo s) {
	this.s = s;
}
public int getMin() {
	return min;
}
public void setMin(int min) {
	this.min = min;
}
public int getSec() {
	return sec;
}
public void setSec(int sec) {
	this.sec = sec;
}

}
