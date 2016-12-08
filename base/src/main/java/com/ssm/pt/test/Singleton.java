package com.ssm.pt.test;

/**
 * 单例模式
 * 双重检验锁模式
 * @author asus
 *
 */
public class Singleton {
	private volatile static Singleton instance;

	private Singleton() {}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
