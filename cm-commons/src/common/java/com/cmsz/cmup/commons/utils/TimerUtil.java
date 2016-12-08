package com.cmsz.cmup.commons.utils;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

/**
 * 
 * 定时执行某一逻辑，用来取代while(true) sleep(xxx)
 * 
 * <p>用法:</p>
 * 自己的定时逻辑代码，需要实现Runnable接口例如:
 * 	<p>TimerUtil.executeTimer(printHello, 2);类printHello需要实现Runnable接口</p>
 * 也可以
 * 	<p>Runnable runnable = new Runnable() {</p>
 *            public void run() {... } };
 *  <p>TimerUtil.executeTimer(runnable, 2);</p>
 *  
 *	executeTimer方法，支持秒(int)、毫秒(long)、cron表达式(String)，具体请参考方法注释
 *
 *
 * @author JinChao
 * 
 * @date 2015年11月24日 上午10:59:31
 *
 */
public class TimerUtil{
	
	/**
	 * 定时执行某一业务逻辑
	 * @param runnable 定时执行逻辑方法类，需要实现 Runnable接口
	 * @param cronTriggerString cron表达式
	 * 
	 */
	public static void executeTimer(Runnable runnable,String cronTriggerString){
		CronTrigger cronTrigger = new CronTrigger(cronTriggerString);
		ScheduledExecutorService localExecutor =  Executors.newSingleThreadScheduledExecutor();;
		TaskScheduler taskScheduler =  new ConcurrentTaskScheduler(localExecutor);
		taskScheduler.schedule(runnable,cronTrigger);
	}
	
	
	/**
	 * 定时执行某一业务逻辑
	 * @param runnable 定时执行逻辑方法类，需要实现 Runnable接口
	 * @param second 秒数，多少秒执行一次runnable的逻辑
	 * 
	 */
	public static void executeTimer(Runnable runnable,int second){
		CronTrigger cronTrigger = new CronTrigger(new StringBuilder("*/").append(second).append(" * * * * *").toString());
		ScheduledExecutorService localExecutor =  Executors.newSingleThreadScheduledExecutor();;
		TaskScheduler taskScheduler =  new ConcurrentTaskScheduler(localExecutor);
		taskScheduler.schedule(runnable,cronTrigger);
	}
	
	/**
	 * 定时执行某一业务逻辑
	 * @param runnable 定时执行逻辑方法类，需要实现 Runnable接口
	 * @param millisecond 毫秒数，隔多少毫秒数执行一次runnable的逻辑
	 * 
	 */
	public static void executeTimer(Runnable runnable,long millisecond){
		long second = millisecond/1000;
		CronTrigger cronTrigger = new CronTrigger(new StringBuilder("*/").append(second).append(" * * * * *").toString());
		ScheduledExecutorService localExecutor =  Executors.newSingleThreadScheduledExecutor();;
		TaskScheduler taskScheduler =  new ConcurrentTaskScheduler(localExecutor);
		taskScheduler.schedule(runnable,cronTrigger);
	}
	
	
}
