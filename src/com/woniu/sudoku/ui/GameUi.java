package com.woniu.sudoku.ui;

import javax.swing.JFrame;

public class GameUi {
	static JFrame j = new JFrame();
	static Menu m = new Menu();
	public static void main(String[] args) {
		init();
	}

	/**
	 * 载入游戏界面
	 */
	public static void go() {
		Grid grid = new Grid();
		GameHead gameHead = new GameHead(grid);
		m.setVisible(false);
		j.remove(m);
		j.add(gameHead);
		j.add(grid);
		j.add(new Rim());
	}

	/**
	 * 初始窗口 加载菜单界面
	 */
	public static void init() {
		//if(grid!=null) {
		//grid.setVisible(false);
		//gameHead.setVisible(false);
		//m = new Menu();
		//}
		j.setSize(700, 800);
		j.setLocationRelativeTo(null);
		j.setTitle("Sudoku" + Menu.lev);
		j.setLayout(null);
		j.setResizable(false);// 不可改变窗口大小
		j.add(m);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}
}