package com.ssm.pt.test;

/**
 * @author asus
 *
 */
public class Person implements Cloneable{

	public static void main(String[] args) {
		try {
			int i = 1 / 0 ;
			System.out.println(" try ");	
		} catch (Exception e) {
			System.out.println("除以0，错误");
		}finally {
			System.out.println("finally");
		}
		System.out.println(" end ");
	}
}
