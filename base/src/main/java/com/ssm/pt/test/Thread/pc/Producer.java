package com.ssm.pt.test.Thread.pc;

/**
 * 生产者
 * @author asus
 *
 */
public class Producer 
{

	private Object lock;
	
	public Producer()
	{
		
	}
	
	public Producer(Object lock)
	{
		this.lock = lock;
	}
	
	public void setValue()
	{
		try 
		{
			synchronized (lock) 
			{
				if(!ValueObject.value.equals(""))
					lock.wait();
				String value = System.currentTimeMillis() + "_" + System.nanoTime();
				System.out.println("Set的值是：" + value);
				ValueObject.value = value;
				lock.notify();
			}
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
