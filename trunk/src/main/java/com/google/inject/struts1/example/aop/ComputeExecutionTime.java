/**
 * 
 */
package com.google.inject.struts1.example.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

/**
 * @author Sripad
 * @version $Revision$ 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@BindingAnnotation
public @interface ComputeExecutionTime {
	//
}
