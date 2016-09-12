package com.ssm.pt.util;

import org.springframework.context.ApplicationContext;

/**
 * @author ������
 *
 */
public class SpringUtil {
	
	private static ApplicationContext applicationContext;
	
	public static Object getBean(String beanName){
		return beanName;
	}

}
