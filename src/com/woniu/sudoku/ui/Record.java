package com.woniu.sudoku.ui;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.woniu.sudoku.datacentre.Records;
import com.woniu.sudoku.util.GameSaving;
/**
 * 游戏结束窗口
 * @author Lane
 *
 */
public class Record {
	public static void init() {
		JFrame j = new JFrame();
		j.setSize(100, 300);
		j.setLocationRelativeTo(null);
		j.setTitle("恭喜你");
		j.setLayout(null);
		j.setResizable(false);
		j.setVisible(true);
		TextField field = new TextField();//输入框
		JLabel label = new JLabel("恭喜! ID:");
		JButton button = new JButton("确定");
		j.add(field);
		j.add(label);
		j.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				Records r = new Records(field.getText());
				GameSaving.end(r);
				//TODO返回菜单
			}
		});
	}
}
