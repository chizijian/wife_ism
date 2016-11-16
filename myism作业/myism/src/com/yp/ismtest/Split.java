package com.yp.ismtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Split {
	boolean root[];
	//使用MAP容器（以键值对形式的存放数据）储存根结点和对应的可达集合，相当于映射
	Map<Integer, ArrayList<Integer>> rootmap;
	Map<ArrayList<Integer>,Map<Integer, ArrayList<Integer>>>[] map;
	boolean del[];
	
	public Split() throws IOException {
		rootmap=new HashMap<>();
		//定义root数组的长度
		root=new boolean[new Simplify().delete().getMReader().getArray().length];
		for (int i = 0; i < root.length; i++) {
			root[i]=false;
		}
		del=new Simplify().delete().getDel();
	}
	
	//如果此节点i的先行集合为空且此节点未被删除，则此节点为根结点，root[i]=true，否则root[i]=false
	public void findRoot() throws IOException {
		MyFileReader mReader=new Simplify().delete().getMReader();
		int array[][]=mReader.getArray();
		for (int i = 1; i < array[0].length; i++) {
			boolean flag=false;
			for (int j = 1; j <array.length; j++) {
				if((array[j][i]==1&&i!=j)||del[i]){
					flag=true;
					break;
				}
			}
			if(!flag){
				root[i]=true;
			}
		}
		for (int i = 1; i < root.length; i++) {
			if(root[i])
				System.out.println("根结点："+i);
		}
	}
	
	//使用MAP容器存储根节点的可达集合
	public Map<Integer, ArrayList<Integer>> findReachSet() throws IOException {
		int array[][]=new Simplify().delete().getMReader().getArray();
		for (int i = 1; i < root.length; i++) {
			if(root[i]){
				ArrayList<Integer> aIntegers=new ArrayList<>();
					for (int j = 1; j <array.length; j++) {
						if(array[i][j]==1&&!del[j]){
							aIntegers.add(j);
						}
				}
				rootmap.put(i, aIntegers);
			}		
		}

		Set<?> set = rootmap.entrySet();         
		Iterator<?> i = set.iterator();         
		while(i.hasNext()){      
		     @SuppressWarnings("unchecked")
			Map.Entry<Integer, ArrayList<Integer>> entry1=(Map.Entry<Integer, ArrayList<Integer>>)i.next();
		     System.out.print(entry1.getKey()+"==");   
		     ArrayList<Integer>vList =entry1.getValue();
		     for (Iterator<Integer> iterator = vList.iterator(); iterator.hasNext();) {
				Integer integer = (Integer) iterator.next();
				System.out.print(integer+" ");
			}
		    System.out.println();
		}   
		return rootmap;
	}
	
	//判断可达数组是否存在交集，无交集时可划分子图
	public void intersect() {
		int r[]=new int[root.length];
		int k=0;
		for (int i = 1; i < root.length; i++){
			if(root[i]){
				r[k++]=i;
			}
		}

		boolean isequal[][]=new boolean[r.length][r.length];
		
		for (int i = 0; i < r.length; i++) {
			if(r[i]==0)
				break;
			ArrayList<Integer> arrayList=rootmap.get(r[i]);
			for (int j = 0; j < r.length; j++) {
				if(j==i||r[j]==0)
					continue;
				ArrayList<Integer> temp=rootmap.get(r[j]);
				for (Iterator<Integer> iterator = arrayList.iterator(); iterator.hasNext();) {
					Integer integer = (Integer) iterator.next();
					for (Iterator<Integer> iterator2 = temp.iterator(); iterator2.hasNext();) {
						Integer integer2 = (Integer) iterator2.next();
						if(integer==integer2){
							isequal[r[i]][r[j]]=true;
							break;
						}
					}
				}
			}
		}
		
		//输出有交集的根结点
		boolean flag[]=new boolean[r.length];
		for (int i = 0; i < isequal.length; i++) {
			for (int j = 0; j < isequal[0].length; j++) {
				if(isequal[i][j])
					if(!flag[i]){
						System.out.println("i="+i+" j="+j);
						flag[j]=true;
					}			
			}	
		}
	}
	
	public void divi() {
		
	}
	
	//主函数内测试，找出根结点、求出可达集合、求交集
	public static void main(String[] args) {
		try {
			Split split=new Split();
			split.findRoot();
			split.findReachSet();
			split.intersect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
