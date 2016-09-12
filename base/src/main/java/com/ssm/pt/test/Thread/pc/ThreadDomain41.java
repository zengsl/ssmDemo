package com.ssm.pt.test.Thread.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.hibernate.mapping.Value;

/**
 * 利用await()/signal()实现生产者和消费者模型
 * @author asus
 *
 */
public class ThreadDomain41 extends ReentrantLock
{
    private Condition producerCond = newCondition();
    private Condition customerCond = newCondition();
    
    public void set()
    {
    	try
    	{
    		lock();
    		while (!"".equals(ValueObject.value)) 
    		{	//当缓冲区有数据的时候，该生产者线程进入等待状态
    			producerCond.await();
			}
    		ValueObject.value = "123";
            System.out.println(Thread.currentThread().getName() + "生产了value, value的当前值是" + ValueObject.value);
            customerCond.signal();
    	}
    	catch(InterruptedException e)
    	{
    		e.printStackTrace();
    	}
    	finally 
    	{
			unlock();
		}
    	
    }
    
    public void get()
    {
    	try 
    	{
        	lock();
        	while("".equals(ValueObject.value))
        	{	// 当缓冲区没有数据的时候消费者线程进入等待状态
        		customerCond.await();
        	}
        	ValueObject.value = "";
            System.out.println(Thread.currentThread().getName() + "消费了value, value的当前值是" + ValueObject.value);
            producerCond.signal();
		} 
    	catch (InterruptedException  e) 
    	{
    		e.printStackTrace();
		}
    	finally
    	{
			unlock();
		}
    }
}
