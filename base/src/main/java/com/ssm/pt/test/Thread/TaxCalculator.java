package com.ssm.pt.test.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 税款计算器
 * @author asus
 *
 */
public class TaxCalculator implements Callable<Integer>{

	//本金
	private int seedMoney;
	//接收主线程提供的参数
	public TaxCalculator(int _seedMoney) {
		seedMoney = _seedMoney;
	}
	@Override
	public Integer call() throws Exception {
		//复杂计算，运行一次需要10秒
		TimeUnit.MILLISECONDS.sleep(10000);
		return seedMoney /10;
	}

	public static void main(String[] args) throws Exception {
		//生成一个单线程的异步执行器
		ExecutorService es = Executors.newSingleThreadExecutor();
		//线程执行后的期望值
		Future<Integer> future = es.submit(new TaxCalculator(100));
		while (!future.isDone()) {
			//还没有运算完成，等待200毫秒
			TimeUnit.MILLISECONDS.sleep(200);
			//输出进度符号
			System.out.println("#");
		}
		System.out.println("\n计算完成， 税金是： " + future.get() + "元");
		es.shutdown();
	}
}
