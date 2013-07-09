/**
 * 
 */
package com.google.inject.struts1.example.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.inject.Inject;

/**
 * @author Sripad
 * @version $Revision$
 */
public class SomeAction extends Action {

	@Inject
	private SomeService someService;

	@ComputeExecutionTime
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SomeForm someForm = (SomeForm) form;
		someForm.setMessage(someService.foo());

		return mapping.findForward("success");
	}
}
