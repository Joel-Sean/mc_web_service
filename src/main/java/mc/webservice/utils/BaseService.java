package mc.webservice.utils;
import org.apache.log4j.Logger;

public abstract class BaseService implements Runnable {

	private State state = State.RUNNING;
	private long sleepTime = 1000 * 10; // default 10 seconds
	private static final Logger logger = Logger.getLogger(BaseService.class);

	@Override
	public void run() {
		while (state != State.EXITING) {

			if (state == State.PENDING) {
				continue;
			}
			long start = System.currentTimeMillis();
			try {
				execute();
			} catch (Throwable e) {
				logger.error(e.getMessage(), e);
			} finally { 
				try {
					long remainTime = sleepTime + start - System.currentTimeMillis();
					if (remainTime > 0) {
						Thread.sleep(remainTime);
					}
				} catch (InterruptedException e) {
					return;
				}
				
			}

		}
	}

	public void start() {
		if (state == State.PENDING) {
			state = State.RUNNING;
		}
	}

	public void stop() {
		if (state == State.RUNNING) {
			state = State.PENDING;
		}
	}

	public void exit() {
		state = State.EXITING;
	}
	
	protected abstract void execute();
	
	protected void setConfig(Object config) {}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	private enum State {
		EXITING, PENDING, RUNNING
		
	}

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);

}
