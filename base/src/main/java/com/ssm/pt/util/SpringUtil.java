package com.ssm.pt.util;

import org.springframework.context.ApplicationContext;

/**
 * @author ������
 *
 */
public class SpringUtil {
	private static ApplicationContext applicationContext = null;

	/**
	 * 判断是否SpringBeanUtil是否已经初时化
	 * 
	 * @return
	 */
	public static boolean isInit() {
		return applicationContext != null;
	}

	/**
	 * 获取spring的applicationContext
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 设置spring的applicationContext
	 * 
	 * @param applicationContext
	 */
	public static void setApplicationContext(
			ApplicationContext applicationContext) {
		SpringUtil.applicationContext = applicationContext;
	}

	/**
	 * 根据名称获取spring的bean
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		if (applicationContext == null)
			return null;
		return applicationContext.getBean(beanName);
	}
}

