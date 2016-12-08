package com.ssm.pt.builder.interfaces;

import java.util.Map;

/**
 * @Description 动态语句构造器
 * @author Zengsl
 * @date 2016年12月5日
 * @version V1.0
 */
public interface IDynamicStatement {
	
	/**
	 * @Description 动态语句构造方法
	 * @param stmtName 语句name
	 * @param params 查询参数
	 * @throws Exception  
	 * @return  查询语句
	 * @author Zengsl 	
	 * @date 2016年12月7日
	 * @version 1.0
	 */
	public abstract String builder(String stmtName, Map<String, Object> params) throws Exception;
}
