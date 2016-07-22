package tps03_J_CSTEH;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class CustomThreadPoolExecutor extends ThreadPoolExecutor { 
	
	// ... Constructor ...
	public CustomThreadPoolExecutor( int corePoolSize, int maximumPoolSize, long keepAliveTime,
	TimeUnit unit, BlockingQueue<Runnable> workQueue) { 
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	@Override
	public void afterExecute(Runnable r, Throwable t) {
		
		super.afterExecute(r, t); if (t != null) {
			// Exception occurred, forward to handler 
		}
		// ... Perform task-specific clean-up actions 
	}
	
	@Override
	public void terminated() {
		   super.terminated();
		// ... Perform final clean-up actions 
	}
}