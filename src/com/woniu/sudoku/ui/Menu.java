package com.woniu.sudoku.ui;

import com.woniu.sudoku.datacentre.Service;
import com.woniu.sudoku.util.ReadImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Menu extends JPanel {
	/**
	 * 菜单
	 */
	private static final long serialVersionUID = 1L;
	public static String lev = " ";// 窗口显示的游戏难度

	public Menu() {
		Random r = new Random();
		Dimension preferredSize = new Dimension(200, 100);// 难度按钮尺寸
		this.setSize(700, 800);
		this.setLocation(0, 0);
		this.setVisible(true);
		/**
		 * 难度选择
		 */
		JButton easy = new JButton("easy");
		JButton medium = new JButton("Medium");
		JButton expert = new JButton("Expert");
		easy.setFont(new Font("宋体", 1, 28));
		easy.setPreferredSize(preferredSize);
		medium.setPreferredSize(preferredSize);
		expert.setPreferredSize(preferredSize);
		easy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lev = ":easy";
				Grid.t = r.nextInt(17) + 31;// 48-32
				GameUi.go();
			}
		});
		medium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lev = ":Medium";
				Grid.t = r.nextInt(8) + 27;// 31-27
				GameUi.go();
			}
		});
		expert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lev = ":Expert";
				Grid.t = r.nextInt(5) + 21;// 26-21
				GameUi.go();
			}
		});
		this.add(easy);
		this.add(medium);
		this.add(expert);
		/**
		 * 载入功能
		 */
		JButton load = new JButton("load");
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				new Service().load();
			}
		});
		this.add(load);
		
		/**
		 * TODO 计分板
		 */
		
		//JTextArea tx = new JTextArea();
		//tx.setBounds(5, 300, 300, 200);
		//Records rs = GameSaving.getRecords();
		//if(rs!=null) {
		//	tx.setText(rs.toString());			
		//}
		//this.add(tx);
	}
		
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ReadImageUtil.menu, 0, 0, 800, 700, 0, 0, 460, 460, this);
	}
}
