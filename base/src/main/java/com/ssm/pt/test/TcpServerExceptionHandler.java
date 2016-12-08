package com.ssm.pt.test;

/**
 * 异常处理器
 * @author asus
 *
 */
public class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		//记录线程异常信息
		System.out.println("线程 " + t.getName() + " 出现异常，自动重启，请分析原因。");
		e.printStackTrace();
		//重启线程，保证业务不中断
		new TcpServer();
	}

}
