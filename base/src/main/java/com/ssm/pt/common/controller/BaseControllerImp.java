package com.ssm.pt.common.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * 控制器基础类
 * @author 
 */
public class BaseControllerImp implements IBaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * ThreadLocal确保高并发下每个请求的request，response都是独立的
	 */
	private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
	private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();
	/*private static ThreadLocal<CommonParams> currentParams = new ThreadLocal<CommonParams>();*/
	
	/**
	 * 线程安全初始化reque，respose对象
	 * 
	 * @param request
	 * @param response
	 * @date 2016年09月06日
	 * @author 
	 */
	@ModelAttribute
	public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) {
		currentRequest.set(request);
		currentResponse.set(response);
	}
	
	/**
	 * 线程安全
	 * 
	 * @return
	 * @date 2015年11月24日
	 * @author 漂泊者及其影子
	 */
	public HttpServletRequest request() {
		return (HttpServletRequest) currentRequest.get();
	}

	/**
	 * 线程安全
	 * 
	 * @return
	 * @date 2015年11月24日
	 * @author 漂泊者及其影子
	 */
	public HttpServletResponse response() {
		return (HttpServletResponse) currentResponse.get();
	}

	public void initContext(HttpServletRequest request, HttpServletResponse response) {
		initReqAndRep(request, response);
		String LUI_ContextPath = request.getContextPath();
		request.setAttribute("LUI_ContextPath", LUI_ContextPath);
	}	
	
}
