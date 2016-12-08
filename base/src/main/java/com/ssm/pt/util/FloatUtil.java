package com.ssm.pt.util;
/**
 * Float比较工具类
 * @author asus
 *
 */
public final class FloatUtil {

	private FloatUtil(){
		
	}
	
	/**
	 * 判断Float不为0，同时判断null
	 * @param target
	 * 			需进行判断的目标对象
	 * @return
	 * 			非空返回true，空返回false
	 */
	public static boolean isNotZero(Float target) {
		
		boolean cmpResult = false;
		if(target == null)
			return cmpResult;
		// 当值大于0小于等于Float表示的最大值或者小于0大于等于Float表示的最小值的时候则为非0
		if((target > 0 && target <= Float.MAX_VALUE) || (target < 0 && target >= Float.MIN_VALUE)) {
			cmpResult = true;
		}else {
			cmpResult = false;
		}
		return cmpResult;
	}
	
	/*public static boolean isZero(Float target) {
		
		boolean cmpResult = true;
		if(target == null)
			return cmpResult;
		
		if((target > 0 && target <= Float.MAX_VALUE)) {
			cmpResult = true;
		}else {
			cmpResult = false;
		}
		
		return cmpResult;

	}*/
}
