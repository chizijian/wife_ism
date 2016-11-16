package com.yp.ismtest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.yp.helper.Common;
import com.yp.helper.calcultor;
//文件读取类
public class MyFileReader {
	//用来构建节点的邻接矩阵33X33，其中包含首行和首列
	private int array[][];
	//用来储存矩阵首行和首列的节点1~32
	private int first[];
	//文件路径
	private String Filepath;
	private final int size=Common.SIZE;
	
	//定义构造函数
	public MyFileReader() {
		first=new int[size];
		array=new int[size][size];
		//初始化矩阵
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				array[i][j]=0;
			}
		}
	}
	
	public MyFileReader(String Filepath){
		first=new int[size];
		array=new int[size][size];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				array[i][j]=0;
			}
		}
		this.Filepath=Filepath;
	}
	
	
	//读取文件的内容，转化为邻接矩阵，邻接显示1，不邻接显示0，节点自身与自身不邻接,然后计算得出可达矩阵
	public void readFromTxt() throws IOException {
		BufferedReader bReader=new BufferedReader(new InputStreamReader(new FileInputStream(Filepath)));
		String temp=null;
		int cnt = 0;
		while ((temp = bReader.readLine()) != null) {
			//因为我的文件内容格式为“1 3,7,8\n 2 13,17,19……”，先用空格分割得到节点号，再用逗号分割得到此节点对应的邻接集合，分别用data1[]、data2[]储存
			String[] data1 = temp.split(" ");
			first[cnt++] = Integer.parseInt(data1[0]);
			String[] data2 = null;
			if (data1.length > 1) {
				data2 = data1[1].split(",");
				for (int i = 0; i < data2.length; i++) {
					array[Integer.parseInt(data1[0])][Integer.parseInt(data2[i])] = 1;
				}
			}
		}
		bReader.close();
	
		boolean del[]=new boolean[size];
		int In[][]=Common.getIn();
		
		/*将邻接矩阵转换为可达矩阵*/
		array=calcultor.add(array,In,del);
		array=calcultor.power(array, array.length,del);
	}

	//构造输出矩阵的方法
	public void display() {
		System.out.print("   ");
		for (int i = 0; i < first.length-1; i++) {
			if(first[i]<10)
				System.out.print(first[i]+"  ");
			else {
				System.out.print(first[i]+" ");
			}
		}
		System.out.println();
		for (int i = 0; i < array.length-1; i++) {
			if(first[i]<10)
				System.out.print(first[i]+"  ");
			else {
				System.out.print(first[i]+" ");
			}
			for (int j = 0; j < array[0].length-1; j++) {
				if(array[first[i]][j+1]==-1){
					System.out.print(array[first[i]][j+1]+" ");
				}
				else {
					System.out.print("+"+array[first[i]][j+1]+" ");	
				}
				
			}
			System.out.println();
		}
	}
	
	public int[][] getArray() {
		return array;
	}

	public void setArray(int array[][]) {
		this.array = array;
	}

	public String getFilepath() {
		return Filepath;
	}

	public void setFilepath(String filepath) {
		Filepath = filepath;
	}

	public int[] getFirst() {
		return first;
	}

	public void setFirst(int[] first) {
		this.first = first;
	}
}

//主函数中测试我的文件是否读取成功，能否转化为可达矩阵
class testmain{
	public static void main(String[] args) {
		MyFileReader mReader=new MyFileReader();
		//测试“测试数据.txt”文件，此文件已放入myism工程中
		mReader.setFilepath(Common.FILEPATH);
		try {
			mReader.readFromTxt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
