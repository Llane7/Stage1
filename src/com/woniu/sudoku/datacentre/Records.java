package com.woniu.sudoku.datacentre;

import java.io.Serializable;
import java.util.Date;

import com.woniu.sudoku.ui.GameHead;

public class Records  implements Serializable{

	/**
	 * 计分板
	 */
	private static final long serialVersionUID = 1L;

	 public int min;
	 public int sec;
	 public String name;
	 public  Date date;
	
	 public Records(String str) {
		this.min = GameHead.min;
		this.sec = GameHead.sec;
		this.date = new Date();
		this.name = str;
	 }
}
