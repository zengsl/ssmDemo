package com.ssm.pt.test.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author asus
 *
 */
public class LiftOff implements Runnable{
	
	protected int countDown = 0;
	private static int taskCount = 0;
	private final int id = taskCount++;
	
	public LiftOff() {
	}
	
	public LiftOff(int _countDown){
		countDown = _countDown;
	}
	
	public String status(){
		return "#" + id + "(" + 
				(countDown > 0 ? countDown : "LiftOff")+").";
	}
	@Override
	public void run() {
		while(countDown-->0){
			System.out.println(status());
			Thread.yield();
		}
	}

	public static void main(String[] args) {
		/*LiftOff liftOff = new LiftOff(5);
		liftOff.run();*/
		
		/*Thread thread = new Thread(new LiftOff(10));
		thread.start();*/
		
		/*for(int i=0;i<5;i++){
			new Thread(new LiftOff(5)).start();
		}*/
		
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			es.execute(new LiftOff(1));
			es.shutdown();
		}
		
	}
}
