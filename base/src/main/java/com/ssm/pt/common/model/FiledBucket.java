package com.ssm.pt.common.model;

/**
 * 属性存储对象
 * @author 曾声亮
 *
 */
public class FiledBucket {
	
	public FiledBucket() {}
	
	public FiledBucket(String filedName, String filedType) {
		this.filedName = filedName;
		this.filedType = filedType;
	}

	/**
	 *  属性名
	 */
	private String filedName; 
	/**
	 * @return  属性名
	 */
	public String getFiledName() {
		return filedName;
	}
	/**
	 * @param filedName  
	 * 			属性名
	 */
	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}
	/**
	 * 属性类型
	 */
	private String filedType; 
	/**
	 * @return  
	 * 		属性类型
	 */
	public String getFiledType() {
		return filedType;
	}
	/**
	 * @param filedType  
	 * 			属性类型
	 */
	public void setFiledType(String filedType) {
		this.filedType = filedType;
	}
	
}
