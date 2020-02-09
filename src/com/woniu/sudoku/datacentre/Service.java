package com.woniu.sudoku.datacentre;

import java.io.File;

import com.woniu.sudoku.ui.GameHead;
import com.woniu.sudoku.ui.GameUi;
import com.woniu.sudoku.util.GameSaving;

public class Service {
	 public static Sudo su = null;
	/**
	 * 游戏中存储
	 * 
	 * @param 数独对象
	 * @param 已用时间
	 */
	public void save(int min,int sec) {
		boolean flag = true;
		Saves saves = new Saves(min,sec);
		System.out.println("保存的数组：");
		saves.getS().printSudo();
		flag = GameSaving.saveFile(saves);
		//TODO保存成功提示做成弹出窗口
		File f = new File("save\\save.txt"); 
		if(f.length() > 0 && flag) {
			System.out.println("保存成功");
		}else {
			System.out.println("保存失败");
		}
	}


		/**
		 * TODO 菜单载入上一次游戏 
		 */

	public void load() {
		Saves load = GameSaving.loadFile();
		su=load.getS();
		GameHead.min = load.getMin();
		GameHead.sec = load.getSec();
		GameUi.go();
	}

	public Sudo getSu() {
		return su;
	}

	public void setSu(Sudo su) {
		Service.su = su;
	}
	
}
