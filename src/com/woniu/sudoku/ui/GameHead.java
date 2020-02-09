package com.woniu.sudoku.ui;

import com.woniu.sudoku.datacentre.Service;
import com.woniu.sudoku.datacentre.Sudo;
import com.woniu.sudoku.util.ReadImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Lane 游戏中顶部面板 包含：1-9填写按钮 计时器
 */
public class GameHead extends JPanel {
	/**
	 * 游戏中头部面板
	 */
	private static final long serialVersionUID = 1L;
	int x;
	int y;
	public static int min;
	public static int sec;
	Dimension preferredSize = new Dimension(50, 50);

	public GameHead(Grid grid) {
		this.setSize(700, 75);
		this.setLocation(0, 0);
		this.setVisible(true);

		// 添加数字按钮
		for (int i = 1; i <= 9; i++) {
			button(i, grid);
		}

		/**
		 * 计时功能
		 */
		Timer t = new Timer();// 计时器
		JLabel label = new JLabel("0:0");// 计时器面板
		this.add(label);
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				sec++;
				label.setText(min + ":" + sec);
				if (sec == 60) {
					min++;
					sec = 0;
					label.setText(min + ":" + sec);
				}
			}
		}, 1000, 1000);

		/**
		 * 保存功能
		 */
		JButton b = new JButton("save");
		this.add(b);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Service().save(min, sec);
			}
		});

		/**
		 * 返回菜单
		 */
		JButton back = new JButton("菜单");
		this.add(back);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameUi.init();		
			}
		});
	}

	// 数字输入按钮添加方法
	public void button(int i, Grid grid) {
		String str = i + "";
		JButton b = new JButton(str);
		b.setFont(new Font("corbel", 1, 28));
		b.setPreferredSize(preferredSize);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//输入操作
				if (Sudo.check(i)) {
					Sudo su = Sudo.getInstance();
					int[][] data = su.getData();
					data[Sudo.x][Sudo.y] = i;
					su.setData(data);
					int lef = su.getLef();
					su.setLef(--lef);
					grid.repaint();
					//游戏完成判断
					if (su.getLef() == 0) {
						Record.init();
					}
				}
			}
		});
		this.add(b);
	}
		
		

		
		
	
		

	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ReadImageUtil.head, 0, 0, 685, 76, 1, 0, 600, 55, this);
	}
}
