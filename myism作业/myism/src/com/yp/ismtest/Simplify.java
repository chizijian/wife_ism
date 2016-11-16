package com.yp.ismtest;

import java.io.IOException;

import com.yp.helper.Common;
//约简回路类
public class Simplify {
	MyFileReader mReader;
	private boolean del[];
	
	public Simplify() {
		mReader=new MyFileReader();
		//构造布尔数组，存储各个节点是否与其他节点存在回路，若存在则为true
		setDel(new boolean[33]);
		//初始化此数组
		for (int i = 0; i < getDel().length; i++) {
			getDel()[i]=false;
		}
	}
	
	public Simplify delete() throws IOException {
		mReader=new MyFileReader(Common.FILEPATH);
		mReader.readFromTxt();
		int array[][]=mReader.getArray();
		
		//判断两节点之间是否存在回路，即array[i]=array[j]=1，可找出所有存在回路的节点并删除一个
		for (int i = 1; i < array.length; i++) {
			if(del[i])
				continue;
			for (int j = 1; j < array[0].length; j++) {
				if(array[i][j]==1&&array[j][i]==1&&i!=j){
					del[j]=true;					
				}
			}
		}
		
		mReader.setArray(array);
		return this;
	}
	
	public MyFileReader getMReader() {
		return mReader;
	}

	public void setMReader(MyFileReader mReader) {
		this.mReader = mReader;
	}
	public boolean[] getDel() {
		return del;
	}
	public void setDel(boolean del[]) {
		this.del = del;
	}
}


class Simtest{
	public static void main(String[] args) {
		try {
			System.out.println("简约矩阵：");
			new Simplify().delete().getMReader().display();;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
