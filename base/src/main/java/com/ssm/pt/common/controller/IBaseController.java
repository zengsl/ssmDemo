package com.ssm.pt.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author asus
 *
 */
public interface IBaseController {
	
	/**
	 * 初始化上下文参数
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月6日
	 * @version 1.0
	 */
	public void initContext(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 线程安全初始化reque，respose对象
	 * @param request
	 * @param response  
	 * @return  
	 * @author 曾声亮  	
	 * @date 2016年9月6日
	 * @version 1.0
	 */
	public void initReqAndRep(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 线程安全
	 * 
	 * @return
	 * @date 2015年11月24日
	 * @author 漂泊者及其影子
	 */
	public HttpServletRequest request();

	/**
	 * 线程安全
	 * 
	 * @return
	 * @date 2015年11月24日
	 * @author 漂泊者及其影子
	 */
	public HttpServletResponse response();	
	
}
