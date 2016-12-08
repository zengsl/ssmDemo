package com.ssm.pt.util;

import org.springframework.util.StringUtils;

/**
 * 字符串工具类
 * @author asus
 *
 */
public final class StringUtil extends StringUtils{
	
	/**
	 * 将指定位置的字符转换为大写
	 * @param beginIndex
	 * @param endIndex
	 * @param target
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	public static String toUpperCase(int beginIndex, int endIndex, String target) {
		if(isEmpty(target)){
			return "";
		}
		
		if (beginIndex < 0) {
            throw new StringIndexOutOfBoundsException(beginIndex);
        }
        if (endIndex > target.length()) {
            throw new StringIndexOutOfBoundsException(endIndex);
        }
        int subLen = endIndex - beginIndex;
        if (subLen < 0) {
            throw new StringIndexOutOfBoundsException(subLen);
        }
        
		StringBuilder builder = new StringBuilder();
		builder.append(target.substring(beginIndex, endIndex).toUpperCase());
		builder.append(target.substring(endIndex));
		
		return builder.toString();
	}
	
	/**
	 * 将指定位置的字符转换为小写
	 * @param beginIndex
	 * @param endIndex
	 * @param target
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月8日
	 * @version 1.0
	 */
	public static String toLowerCase(int beginIndex, int endIndex, String target) {
		if(isEmpty(target)){
			return "";
		}
		
		if (beginIndex < 0) {
			throw new StringIndexOutOfBoundsException(beginIndex);
		}
		if (endIndex > target.length()) {
			throw new StringIndexOutOfBoundsException(endIndex);
		}
		int subLen = endIndex - beginIndex;
		if (subLen < 0) {
			throw new StringIndexOutOfBoundsException(subLen);
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(target.substring(beginIndex, endIndex).toLowerCase());
		builder.append(target.substring(endIndex));
		
		return builder.toString();
	}
}
