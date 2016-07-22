package tps03_J_CSTEH;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

final class PoolService {
	// The values have been hard coded for brevity 
	ExecutorService pool = new CustomThreadPoolExecutor(
	10, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
	// ...
	public void doSomething() { 
		pool.execute(new Task());
	}
	
	public static class ExceptionThreadFactory implements ThreadFactory { 
		private static final ThreadFactory defaultFactory = Executors.defaultThreadFactory();
		private final Thread.UncaughtExceptionHandler handler;

		public ExceptionThreadFactory( Thread.UncaughtExceptionHandler handler) {
			this.handler = handler;
		}

		@Override 
		public Thread newThread(Runnable run) { 
			Thread thread = defaultFactory.newThread(run); 
			thread.setUncaughtExceptionHandler(handler); 
			return thread;
		} 
	}
	
	public static class MyExceptionHandler extends Exception implements Thread.UncaughtExceptionHandler {
		// ...
		@Override public void uncaughtException(Thread thread, Throwable t) { 
			// Recovery or logging code
		} 
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		PoolService service = new PoolService();
		service.doSomething();
	}
}
	