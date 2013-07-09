package com.google.inject.struts1.example.simpleinjection;

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
public class HelloWorldAction extends Action {

	@Inject
	private HelloService helloService;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HelloWorldForm helloWorldForm = (HelloWorldForm) form;
		helloWorldForm.setMessage(helloService.foo());

		return mapping.findForward("success");
	}
}
