package com.yp.helper;
//定义矩阵之间的运算方法，在进行运算时可直接调用
public class calcultor {

	public calcultor() {
		// TODO Auto-generated constructor stub
	}

	//矩阵相加，采用“逻辑或”运算
	public static int[][] add(int[][] a, int[][] b,boolean del[]) {
		int c[][] = a;
		for (int i = 1; i < b.length; i++) {
			for (int j = 1; j < b[0].length; j++) {
				if (!del[a[i][j]])
					c[i][j] = a[i][j] | b[i][j];
				else {
					c[i][j] = a[i][j];
				}
			}
		}
		return c;
	}

	//	矩阵相乘，采用“逻辑与”运算
	public  static int[][] muti(int[][] a, int[][] b,boolean del[]) {
		int c[][] = new int[a.length][b.length];
		for (int i = 1; i < b.length; i++) {
			for (int j = 1; j < b[0].length; j++) {
				if (!del[a[i][j]])
					for (int j2 = 1; j2 < a.length; j2++) {
						c[i][j] = c[i][j]|(a[i][j2]&b[j2][j]);
					}
				else {
					c[i][j] = a[i][j];
				}
			}
		}
		return c;
	}

	//矩阵的幂运算
	public  static int[][] power(int[][]a,int n,boolean del[]){  
        int[][] res=new int[a.length][a[0].length];  
        for(int i=0;i<res.length;i++){  
            for(int j=0;j<res[0].length;j++){  
                if(i==j)  
                    res[i][j]=1;  
                else  
                    res[i][j]=0;  
            }  
              
        }  
        while(n!=0){  
            if((n&1)==1)  
                res=muti(res,a,del);  
            n>>=1;  
            a=muti(a,a,del);  
        }  
        return res;  
    }  

	//矩阵相减，采用“逻辑异或”运算
	public  static int[][] sub(int[][] a, int[][] b,boolean del[]) {
		int c[][] = a;
		for (int i = 1; i < b.length; i++) {
			for (int j = 1; j < b[0].length; j++) {
				if (!del[a[i][j]])
					c[i][j] = a[i][j] ^ b[i][j];
				else {
					c[i][j] = a[i][j];
				}
			}
		}
		return c;
	}
}
