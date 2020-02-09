package com.woniu.sudoku.datacentre;

import java.io.Serializable;

/**
 * @description 数独生成和求解
 * @limit 支持从1-80的数字提示数量
 * @method 深度优先搜索/回溯法
 */
public  class  Sudo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[][] data = new int[9][9]; // muti_array
	private int[][] cor = new  int[9][9];//check用唯一解
	public int lef; // the number of zero in array
	private int tip; // the number of nozero_digit in array
	public static  int x;//高亮框对应数组位置
	public static int y;//高亮框对应数组位置
	private static Sudo su;
	//单例模式
	public static synchronized Sudo getInstance() {
		if(su == null){
			su = new Sudo();		
		}
		return su;		
	}

	/**
	 * 构造函数 初始化变量
	 */
	private Sudo() {
		lef = 0;
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				data[i][j] = 0;
			}
		}
	}

	/**
	 * 生成数独 方法：挖洞法
	 */
	public void genSudo(int t) {
		tip = t;
		/* 将1-9 9个数字放在二维数组中随机位置 */
		lef = 81 - 9;
		// data[0]为{1-9}
		for (int i = 0; i < 9; ++i) {
			data[0][i] = i + 1;
		}
		// data[0]乱序
		for (int i = 0; i < 9; ++i) {
			int ta = (int) (Math.random() * 10) % 9;// *为啥不用nextInt
			int tb = (int) (Math.random() * 10) % 9;
			int tem = data[0][ta];
			data[0][ta] = data[0][tb];
			data[0][tb] = tem;
		}
		for (int i = 0; i < 9; ++i) {
			int ta = (int) (Math.random() * 10) % 9;
			int tb = (int) (Math.random() * 10) % 9;
			int tem = data[0][i];
			data[0][i] = data[ta][tb];
			data[ta][tb] = tem;
		}
		/* 通过9个数字求出一个可行解 */
		solveSudo();
		//备份唯一解
		for (int i = 0;i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cor[i][j] = data[i][j];
			}
		}
		//挖洞
		lef = 81 - tip;
		for (int i = 0; i < lef; ++i) {
			int ta = (int) (Math.random() * 10) % 9;
			int tb = (int) (Math.random() * 10) % 9;
			if (data[ta][tb] != 0)
				data[ta][tb] = 0;
			else
				i--;
		}	
	}

	/**
	 * 求解数独
	 * 
	 * @return 是否有解的boolean标识
	 */
	public boolean solveSudo() {
		if (dfs()) {
			System.out.println("Solve completed.");
			return true;
		} else {
			System.out.println("Error:There are no solution.");
			return false;
		}
	}

	/**
	 * 输出数独数组
	 */
	public void printSudo() {
		System.out.println("-----------------");
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (data[i][j] > 0)
					System.out.print(data[i][j] + " ");
				else
					System.out.print("* ");
			}
			System.out.print('\n');
		}
		System.out.println("-----------------");
	}
	
	public void printCor() {
		System.out.println("-----------------");
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (cor[i][j] > 0)
					System.out.print(cor[i][j] + " ");
				else
					System.out.print("* ");
			}
			System.out.print('\n');
		}
		System.out.println("-----------------");
	}
	/**
	 * 计算某格子的可填数字个数，即不确定度
	 * 
	 * @param r
	 * @param c
	 * @param mark
	 * @return 不确定度
	 */
	private static int calcount(int r, int c, int[] mark) {
		for (int ti = 0; ti < 10; ++ti)
			mark[ti] = 0;
		for (int i = 0; i < 9; ++i) {
			// 计算列不确定数
			mark[su.data[i][c]] = 1;
			// 计算行不确定数
			mark[su.data[r][i]] = 1;
		}
		// re.cs为宫起点（宫的0,0）
		int rs = (r / 3) * 3;
		int cs = (c / 3) * 3;
		// 记录宫内不确定数
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				mark[su.data[rs + i][cs + j]] = 1;
			}
		}
		int count = 0;
		for (int i = 1; i <= 9; ++i) {
			// 同一行,一列,一个宫内 不确定数字的个数
			if (mark[i] == 0)
				count++;
		}
		return count;
	}

	/**
	 * 供solve调用的深度优先搜索
	 * 
	 * @return 是否有解的boolean标识
	 */
	private static boolean dfs() {
		if (su.lef == 0)
			return true;
		int mincount = 10;
		int mini = 0, minj = 0;
		int[] mark = new int[10];
		/* 找到不确定度最小的格子 */
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				if (su.data[i][j] != 0)
					continue;

				int count = calcount(i, j, mark);
				if (count == 0)
					return false;// count不会为0，不懂这一步
				// 找出不确定度最小的坐标
				if (count < mincount) {
					mincount = count;
					mini = i;
					minj = j;
				}
			}
		}
		/* 优先处理不确定度最小的格子 */
		calcount(mini, minj, mark);
		for (int i = 1; i <= 9; ++i) {
			if (mark[i] == 0) {
				su.data[mini][minj] = i;
				su.lef--;
				// printSudo();
				// 找到下一个不确定度最小的格子
				dfs();
				if (su.lef== 0)
					return true;
				su.data[mini][minj] = 0;// 回溯法
				su.lef++;
			}
		}
		return true;
	}
	/**
	 * 判断当前输入是否合法
	 * @param i
	 * @return 
	 */
	public static boolean check(int i) {
		int[][] c = su.getCor();
		if(i == c[Sudo.x][Sudo.y]) {
			return true;
		}else {
		return false;
		}
	}

	public  int[][] getData() {
		return data;
	}

	public void setData(int[][] data) {
		su.data = data;
	}

	public int[][] getCor() {
		return cor;
	}

	public void setCor(int[][] cor) {
		su.cor = cor;
	}

	public int getTip() {
		return tip;
	}

	public void setTip(int tip) {
		this.tip = tip;
	}

	public static int getX() {
		return x;
	}

	public static void setX(int x) {
		Sudo.x = x;
	}

	public static int getY() {
		return y;
	}

	public static void setY(int y) {
		Sudo.y = y;
	}

	public static Sudo getSu() {
		return su;
	}

	public static void setSu(Sudo su) {
		Sudo.su = su;
	}

	public int getLef() {
		return lef;
	}

	public void setLef(int lef) {
		this.lef = lef;
	}

}