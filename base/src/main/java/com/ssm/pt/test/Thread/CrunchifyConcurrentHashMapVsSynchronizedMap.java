package com.ssm.pt.test.Thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.web.servlet.tags.EscapeBodyTag;

/**
 * Map并发性能测试
 * 
 * @author asus
 *
 */
public class CrunchifyConcurrentHashMapVsSynchronizedMap {

	public final static int THREAD_POOL_SIZE = 5;

	public static Map<String, Integer> crunchifyHashTableObject = null;
	public static Map<String, Integer> crunchifySynchronizedMapObject = null;
	public static Map<String, Integer> crunchifyConcurrentHashMapObject = null;

	public static void crunchifyPerformTest(final Map<String, Integer> crunchifyThreads) throws InterruptedException {

		System.out.println("Test started for: " + crunchifyThreads.getClass());
		long averageTime = 0;
		for (int i = 0; i < 5; i++) {

			long startTime = System.nanoTime();
			ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
			for (int j = 0; j < THREAD_POOL_SIZE; j++) {
				es.execute(new Runnable() {

					public void run() {
                        for (int i = 0; i < 500000; i++) {
							Integer randomNumber = (int) Math.ceil(Math.random() * 550000);
							crunchifyThreads.put(String.valueOf(randomNumber), randomNumber);
                        }
					}
				});
			}
			// Make sure executor stops
			es.shutdown();

			// Blocks until all tasks have completed execution after a shutdown
			// request
			es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

			long endTime = System.nanoTime();
			long totalTime = (endTime - startTime) / 1000000L;
			averageTime += totalTime;
			System.out.println("2500K entried added/retrieved in " + totalTime + "ms");
		}
		System.out.println("For " + crunchifyThreads.getClass() + " the average time is " + averageTime / 5 + " ms\n");
	}
	
	 public static void main(String[] args) throws InterruptedException {
	    	// Test with Hashtable Object
	        crunchifyHashTableObject = new Hashtable<>();
	        crunchifyPerformTest(crunchifyHashTableObject);

	        // Test with synchronizedMap Object
	        crunchifySynchronizedMapObject = Collections.synchronizedMap(new HashMap<String, Integer>());
	        crunchifyPerformTest(crunchifySynchronizedMapObject);

	        // Test with ConcurrentHashMap Object
	        crunchifyConcurrentHashMapObject = new ConcurrentHashMap<>();
	        crunchifyPerformTest(crunchifyConcurrentHashMapObject);
		} 
}
