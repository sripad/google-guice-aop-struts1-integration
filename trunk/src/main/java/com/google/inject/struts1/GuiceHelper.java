package com.google.inject.struts1;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

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
		if (injector == null) {
			createInjector();
		}
		return injector.getInstance(classToInstantiate);
	}

	private static void createInjector() {
		injector = Guice.createInjector(new AbstractModule() {
			protected void configure() {
				//
			}
		});
	}

	/**
	 * Inject dependencies into an already created instance. This is useful you
	 * don't have control over instantiation but would still like to inject
	 * dependencies via setter injection. Your setters must be annotated with
	 * 
	 * @Inject.
	 */
	public static void inject(Object instanceToPopulate) {
		if (injector == null) {
			createInjector();
		}
		injector.injectMembers(instanceToPopulate);
	}
}
