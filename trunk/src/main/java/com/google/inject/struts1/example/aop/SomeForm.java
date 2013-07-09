/**
 * 
 */
package com.google.inject.struts1.example.aop;

import org.apache.struts.action.ActionForm;

/**
 * @author Sripad
 * @version $Revision$
 */
public class SomeForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
