package com.ssm.pt.test;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author zengsl
 *
 */
public class HashCodeTest {
	private static String userName ;
	public static void main(String[] args) {
		System.out.println(new HashCodeBuilder().append(userName).toHashCode());
	}
}
