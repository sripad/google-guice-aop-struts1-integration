/**
 * 
 */
package com.google.inject.struts1.example.aop;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Sripad
 * @version $Revision$
 */
public class ComputeExecutionTimeMethodInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object returnValue = null;
		StopWatch timer = startStopWatch();
		try {
			returnValue = invocation.proceed();
		} catch (Throwable e) {
			throw e;
		} finally {
			stopMonitoringEvent(timer);
			printExecutionTime(invocation, timer);
		}
		return returnValue;
	}

	public StopWatch startStopWatch() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		return stopWatch;
	}

	public void stopMonitoringEvent(StopWatch timer) {
		timer.stop();
	}

	public void printExecutionTime(MethodInvocation invocation, StopWatch timer) {
		Method method = invocation.getMethod();
		System.out.println("Execution Time of method "
				+ method.getDeclaringClass().getCanonicalName() + "."
				+ method.getName() + "() is "
				+ timer.getElapsedTimeMillis() + " milli secs");
	}
}

class StopWatch {

	private long startTime = 0;
	private long stopTime = 0;
	private boolean running = false;

	public void start() {
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}

	public void stop() {
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}

	public long getElapsedTimeMillis() {
		long elapsed;
		if (running) {
			elapsed = (System.currentTimeMillis() - startTime);
		} else {
			elapsed = (stopTime - startTime);
		}
		return elapsed;
	}

	public long getElapsedTimeSecs() {
		return getElapsedTimeMillis() / 1000;
	}
}
