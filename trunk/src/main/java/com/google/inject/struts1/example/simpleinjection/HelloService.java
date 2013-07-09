/**
 * 
 */
package com.google.inject.struts1.example.simpleinjection;

/**
 * @author Sripad
 * @version $Revision$
 */
public class HelloService {
	public String foo() {
		return "The HelloService is injected by Guice :) ";
	}
}
