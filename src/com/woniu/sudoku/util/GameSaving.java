package com.woniu.sudoku.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.woniu.sudoku.datacentre.Records;
import com.woniu.sudoku.datacentre.Saves;

public class GameSaving {

	/**
	 * 游戏中存档
	 * 
	 * @param s
	 */
	public static boolean saveFile(Saves s) {
		ObjectOutputStream o = null;
		try {
			o = new ObjectOutputStream(new FileOutputStream(new File("save\\save.txt")));
			o.writeObject(s);
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 载入存档
	 * @return
	 */
	public static Saves loadFile() {
		ObjectInputStream r = null;
		Saves s = null;
		try {
			r = new ObjectInputStream(new FileInputStream(new File("save\\save.txt")));
			Object o =  r.readObject();
			if(o instanceof  Saves) {
				s = (Saves) o;
			}
			r.close();
			return s;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * 游戏得分
	 */
	public static void end(Records r) {
		ObjectOutputStream o = null;
		try {
			o = new ObjectOutputStream(new FileOutputStream(new File("save\\records.txt"),true));
			o.writeObject(r);
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * TODO 菜单中计分板
	 */
	public static Records getRecords() {
		ObjectInputStream in = null;
		Records r = null;
			try {
				in = new ObjectInputStream(new FileInputStream(new File("save\\records.txt")));
				Object o =  in.readObject();
				if(o instanceof  Records) {
					r = (Records) o;
				}
				in.close();
				return r;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return r;
	}
}
