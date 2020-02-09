package com.woniu.sudoku.ui;

import javax.swing.JPanel;

public class Rim extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 高亮边框
	 */
	public static int x;
	public static int y;
	//起始位置
	static {
		x=5;
		y=5;
	}
public Rim() {
	this.setSize(91,91);
	this.setLocation(x, y);
	this.setVisible(true);
}
}
