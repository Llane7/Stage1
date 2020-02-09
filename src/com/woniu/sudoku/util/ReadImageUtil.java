package com.woniu.sudoku.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 读取图片
 * 
 * @author Lane
 *
 */
public class ReadImageUtil {
	public static Image menu;
	public static Image head;
	public static Image grid;
	public static Image rim;
	public static Image start;
	public static Image load;
	public static Image save;
	public static Image exit;
	public static Image easy;
	public static Image medium;
	public static Image hard;
	public static Image one;
	public static Image two;
	public static Image three;
	public static Image four;
	public static Image five;
	public static Image six;
	public static Image seven;
	public static Image eight;
	public static Image nine;
	public static Image zero;
	public static Image onebutton;
	static {
		try {
			grid = ImageIO.read(new File("image\\Grid.png"));
			head = ImageIO.read(new File("image\\Head.png"));
			rim = ImageIO.read(new File("image\\Rim.png"));
			one = ImageIO.read(new File("image\\one.png"));
			two = ImageIO.read(new File("image\\two.png"));
			three = ImageIO.read(new File("image\\three.png"));
			four = ImageIO.read(new File("image\\four.png"));
			five = ImageIO.read(new File("image\\five.png"));
			six = ImageIO.read(new File("image\\six.png"));
			seven = ImageIO.read(new File("image\\seven.png"));
			eight = ImageIO.read(new File("image\\eight.png"));
			nine = ImageIO.read(new File("image\\nine.png"));
			zero = ImageIO.read(new File("image\\zero.png"));
			onebutton = ImageIO.read(new File("image\\OneButton.png"));
			menu = ImageIO.read(new File("image\\menu.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
