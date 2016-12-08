package com.ssm.pt.test;

/**
 * 饿汉式 static final field
 * 使用静态内部类 static nested class实现
 * @author asus
 *
 */
public class Singleton2 {
	private static class SingletonHolder{
		private static final Singleton2 INSTANCE = new Singleton2();
	}
	private Singleton2(){}
	public static final Singleton2 getInstance(){
		return SingletonHolder.INSTANCE;
	}
}
