package ThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TDS {

	/** 可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程 */
	public static void CachedThreadPool() {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			cachedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						// 用休眠模拟执行事务
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(index + " : " + Thread.currentThread().toString());
				}
			});
		}
	}

	/**
	 * 定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	 * 
	 * @throws InterruptedException
	 */
	public static void FixtedThreadPool() throws InterruptedException {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 20; i++) {
			final int index = i;
			/** 延时使得线程依次创建、顺序结束 */
			Thread.sleep(5);
			fixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(800);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

	/** 定长线程池，支持定时及周期性任务执行 */
	public static void ScheduledThreadPool() {

		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		/** scheduleAtFixedRate */
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("delay 3 seconds");
			}
		}, 1, 200, TimeUnit.MILLISECONDS);
		/** 5s后关闭线程，方便测试 */
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scheduledThreadPool.shutdown();
	}

	/** 单线程化的线程池 */
	public static void SingleThreadExecutor() {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
//		singleThreadExecutor.
		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThreadExecutor.execute
			(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(index + ": " + Thread.currentThread().toString());
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

}
