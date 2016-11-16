package com.yp.ismtest;

import java.io.IOException;

import com.yp.helper.Common;
import com.yp.helper.calcultor;


public class Test {
	boolean[] del;


	public Test() throws IOException {
		del=new Simplify().delete().getDel();
	}

	public void cal() throws IOException {
		int array[][] = new Simplify().delete().getMReader().getArray();

		int n = array.length;
		int result[][] = array;		   //result[]可达矩阵
		int In[][] = Common.getIn();		//In[]单位矩阵
		int Sn[][] = new int[n][n];		//Sn[]骨架矩阵
		
		int [][] temp=calcultor.sub(result, In,del);
		
		int [][] temp2=calcultor.power(temp, 2,del);
		
		//骨架矩阵,Sn=sub(sub(result, In),power(sub(result, In),2));
		Sn = calcultor.sub(temp, temp2,del);
		
		System.out.println("骨架");
		for (int i = 1; i < Sn.length; i++) {
			int[] js = Sn[i];
			for (int j = 1; j < js.length; j++) {
				int num = js[j];
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		try {
			new Test().cal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
