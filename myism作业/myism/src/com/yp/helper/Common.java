package com.yp.helper;

public final class Common {
		public static final String FILEPATH="测试数据.txt";//文件路径
		public static final int SIZE=33;//大小
		
		/*单位矩阵*/
		public static final int[][] getIn() {
			int In[][]=new int[SIZE][SIZE];
			for (int i = 1; i < In.length; i++) {
				for (int j = 1; j < In[0].length; j++) {
					In[i][j]=i==j?1:0;
				}
			}
			return In;
		}
}
