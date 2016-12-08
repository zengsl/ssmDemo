package com.ssm.pt.test.Thread.pc;

import org.springframework.expression.spel.ast.Projection;

/**
 * @author asus
 *
 */
public class MianEntry 
{
	//利用wait()/notify()实现生产者/消费者模型
	/*public static void main(String[] args) 
	{
		Object lock = new Object();
		final Producer producer = new Producer(lock);
		final Customer customer = new Customer(lock);
		Runnable producerRunnable = new Runnable() 
		{
			public void run() {
				while(true)
				{
					producer.setValue();
				}
			}
		};
		Runnable customerRunnable = new Runnable() 
		{
			public void run() {
				while(true)
				{
					customer.getValue();
				}
			}
		};
		Thread producerThread = new Thread(producerRunnable);
		Thread CustomerThread = new Thread(customerRunnable);
		producerThread.start();
		CustomerThread.start();
	}*/
	
	//利用await()/signal()实现生产者和消费者模型
	public static void main(String[] args) 
	{
		final ThreadDomain41 td = new ThreadDomain41();
		Runnable producerRunnable = new Runnable() 
		{
			public void run() 
			{
				for(int i = 0; i < Integer.MAX_VALUE; i++)
				{
					td.set();
				}
			}
		};
		Runnable customerRunnable = new Runnable() 
		{
			public void run() 
			{
				for(int i = 0; i < Integer.MAX_VALUE; i++)
				{
					td.get();
				}
			}
		};
		Thread producerThread = new Thread(producerRunnable);
		producerThread.setName("Producer");
		Thread customerThread = new Thread(customerRunnable);
		customerThread.setName("Customer");
		producerThread.start();
		customerThread.start();
	}
}
