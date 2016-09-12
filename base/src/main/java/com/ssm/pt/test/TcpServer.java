package com.ssm.pt.test;

/**
 * @author asus
 *
 */
public class TcpServer implements Runnable{
	

	public TcpServer() {
		Thread t = new Thread(this);
		t.setUncaughtExceptionHandler(new TcpServerExceptionHandler());
		t.start();
	}

	public void run() {
		//正常业务运行， 运行3秒
		for(int i = 0; i < 3; i++){
			try {
				Thread.sleep(1000);
				System.out.println(" 系统正常运行：" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//抛出异常
		throw new RuntimeException();
	}

}
