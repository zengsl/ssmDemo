package com.ssm.pt.common.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * 异常容器，用于存储所有异常
 * @author Zengsl
 *
 */
public class ExceptionPack extends Exception{
	
	//容纳所有异常
	List<Throwable> causeList = new ArrayList<Throwable>();
	
	public ExceptionPack() {
	}
	
	/**
	 * 构造函数，传递一个异常列表
	 * @param _causeList
	 * 			所有异常
	 */
	public ExceptionPack(List<? extends Throwable> _causeList) {
		causeList.addAll(_causeList);
	}
	
	/**
	 * 读取所有异常
	 * @return  
	 * 		所有异常
	 * @author 曾声亮  	
	 * @date 2016年8月25日
	 * @version 1.0
	 */
	public List<Throwable> getExceptions() {
		return causeList;
	}
	
}
