package com.ssm.pt.test;

import com.ssm.pt.util.FloatUtil;

/**
 * @author asus
 *
 */
public class HttpTest {

	public static void main(String[] args) {
		Float f1 = 0.0000000f;
		Float f2 = 1.0f;
		System.out.println("f2: " + FloatUtil.isNotZero(f2)+" f1: "+FloatUtil.isNotZero(f1));
		System.out.println("f2: " + (Float.compare(f2,0.000f)!=0)+" f1: "+(Float.compare(f1,0.0000f)!=0));
		/*float f3 = 0.4F;
		int i1 = (int) f3;
		System.out.println("i1 :" + i1);*/
		
	}
}
