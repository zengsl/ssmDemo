package com.ssm.pt.util;

/**
 * @author asus
 *
 */
public final class ArrayUtil {
	
	/**
	 * 插入排序
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年8月7日
	 * @version 1.0
	 * @param <T>
	 */
	public static void insertionSort(int[] targetArr) {
		
		for(int out = 1,size = targetArr.length; out < size; out++){
			int tmp = targetArr[out];
			int in = out;
			while (in > 0 && targetArr[in - 1] > tmp) {
				targetArr[in] = targetArr[in - 1];
				in --;
			}
			targetArr[in] = tmp;
		}
		
	}
	
	private ArrayUtil(){
		
	}
	/*public static void main(String[] args) {
		int[] arry = {2,4,1,3,8,5,0,1};
		System.out.println("before sort:" );
		for(int i = 0, size = arry.length; i < size;i++){
			System.out.print(arry[i]);
		}
		ArrayUtil.insertionSort(arry);
		System.out.println("" );
		System.out.println("after sort:" );
		for(int i = 0, size = arry.length; i < size;i++){
			System.out.print(arry[i]);
		}
	}*/
}
