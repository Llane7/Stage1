package com.woniu.sudoku.ui;

import com.woniu.sudoku.datacentre.Service;
import com.woniu.sudoku.datacentre.Sudo;
import com.woniu.sudoku.util.ReadImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class Grid extends JPanel {
	/**
	 * 游戏主体
	 */
	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	public static int t = 30;// 题目数字数量
	public static int input;// 欲填写数字
	public Sudo su;
	public static HashMap<Integer, Image> map;
	// 数字与绘制图片对应
	static {
		map = new HashMap<Integer, Image>();
		map.put(1, ReadImageUtil.one);
		map.put(2, ReadImageUtil.two);
		map.put(3, ReadImageUtil.three);
		map.put(4, ReadImageUtil.four);
		map.put(5, ReadImageUtil.five);
		map.put(6, ReadImageUtil.six);
		map.put(7, ReadImageUtil.seven);
		map.put(8, ReadImageUtil.eight);
		map.put(9, ReadImageUtil.nine);
	}

	public Grid() {
		// 生成数独
		Service s = new Service();
		if(s.getSu() == null) {
			su = Sudo.getInstance();
			su.genSudo(t);
		}else {
			su = Service.su;
			Sudo.setSu(Service.su);
		}
		su.printSudo();
		su.printCor();
		this.setSize(700, 700);
		this.setLocation(0, 75);
		this.setVisible(true);
		/**
		 * 监听鼠标点击的格子
		 */
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					int xs = grids(e.getX());
					int ys = grids(e.getY());
					// 高亮框位置
					Rim.x = xs * (675 / 9) + 5;
					Rim.y = ys * (675 / 9) + 5;
					// 选定格子对应的数组下标
					Sudo.x = ys;
					Sudo.y = xs;
					repaint();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	protected void paintComponent(Graphics g) {
		int x1;
		int y1 = 25;
		int x2;
		int y2 = 60;
		super.paintComponent(g);// 绘制父类背景颜色(不懂)
		// 绘制格子背景
		g.drawImage(ReadImageUtil.grid, 0, 0, 685, 685, 1, 0, 871, 871, this);// 自动缩放
		// 绘制选择框
		g.drawImage(ReadImageUtil.rim, Rim.x, Rim.y, Rim.x + 73, Rim.y + 74, 0, 0, 91, 91, this);
		// 绘制题目
		int[][] data = su.getData();
		for (int i = 0; i < 9; i++) {
			x1 = 25;
			x2 = 60;
			for (int j = 0; j < 9; j++) {
				g.drawImage(map.get(data[i][j]), x1, y1, x2, y2, 1, 0, 74, 74, this);
				x1 += 75;
				x2 += 75;
			}
			y1 += 75;
			y2 += 75;
		}
	}

	/**
	 * 根据坐标计算第几个格子
	 * 
	 * @param x:鼠标点击坐标
	 * @return 第几个格子
	 */
	private static int grids(int x) {
		int i = (630 / 9) + 5;// 871九宫格长度（像素）
		int j = x / i;// 第几个格子
		return j;
	}
	
}
