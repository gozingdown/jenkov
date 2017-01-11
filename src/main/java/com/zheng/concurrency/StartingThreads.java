package com.zheng.concurrency;

import static java.util.logging.Level.INFO;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartingThreads {
	
	private static final Logger logger = Logger.getLogger(StartingThreads.class.getName());
	
	static {
		logger.setLevel(INFO);
		logger.addHandler(new ConsoleHandler());
		logger.setUseParentHandlers(false);
	}
	
	public static class MyRunnable implements Runnable {
		@Override
		public void run() {
			Thread thread = Thread.currentThread();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			logger.log(INFO, thread.getName());
			
		}
	}
	public static class MyThread extends Thread {
		public MyThread(String string) {
			super(string);
		}

		@Override
		public void run() {
			Thread thread = Thread.currentThread();
			logger.log(INFO, thread.getName());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new MyRunnable(), "zheng");
		thread.start();
		logger.log(INFO, Thread.currentThread().getName());
		Thread thread2 = new MyThread("hello");
		thread2.start();
	}
}
