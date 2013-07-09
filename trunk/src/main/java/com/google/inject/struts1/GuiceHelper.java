package com.google.inject.struts1;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.struts1.example.aop.ComputeExecutionTime;
import com.google.inject.struts1.example.aop.ComputeExecutionTimeMethodInterceptor;
import com.google.inject.util.Modules;

/**
 * @author sripad
 * @version $Revision$
 */
public class GuiceHelper {

	private static Injector injector;

	/**
	 * @return the injector
	 */
	public static Injector getInjector() {
		if (injector == null) {
			createInjector();
		}
		return injector;
	}

	/**
	 * Retrieve an injected instance constructed by the Dependency Injection
	 * Container (Guice).
	 */
	public static <T> T getInjectedInstance(Class<T> classToInstantiate) {
		return getInjector().getInstance(classToInstantiate);
	}

	/**
	 * Inject dependencies into an already created instance. This is useful you
	 * don't have control over instantiation but would still like to inject
	 * dependencies via setter injection. Your setters must be annotated with
	 * 
	 * @Inject.
	 */
	public static void inject(Object instanceToPopulate) {
		getInjector().injectMembers(instanceToPopulate);
	}

	private static void createInjector() {
		AopModule aopModule = new AopModule();
		injector = Guice.createInjector(Modules.EMPTY_MODULE, aopModule);
		aopModule.injectInterceptors(injector);
	}
}

class AopModule extends AbstractModule {
	ComputeExecutionTimeMethodInterceptor computeExecutionTimeMethodInterceptor;

	@Override
	protected void configure() {
		computeExecutionTimeMethodInterceptor = new ComputeExecutionTimeMethodInterceptor();
		requestInjection(computeExecutionTimeMethodInterceptor);
		bindInterceptor(Matchers.any(),
				Matchers.annotatedWith(ComputeExecutionTime.class),
				computeExecutionTimeMethodInterceptor);
	}

	public void injectInterceptors(Injector injector) {
		injector.injectMembers(computeExecutionTimeMethodInterceptor);
	}
}
