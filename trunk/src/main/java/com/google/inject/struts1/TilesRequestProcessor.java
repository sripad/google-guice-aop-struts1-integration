package com.google.inject.struts1;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;

/**
 * @author sripad
 * @version $Revision$
 */
public class TilesRequestProcessor extends
		org.apache.struts.tiles.TilesRequestProcessor {

	@Override
	protected boolean processPreprocess(HttpServletRequest request,
			HttpServletResponse response) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Action processActionCreate(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException {
		
		String className = mapping.getType();
		Action action = null;
		
		synchronized (actions) {
			boolean isNewInstance = (actions.get(className) == null);
			if (isNewInstance) {
				// Step 1: create an instance of the action by the normal way
				Action strutsCreatedAction = super.processActionCreate(request,
						response, mapping);

				// Step 2: create an instance of the action from Guice
				Action guiceCreatedAction = GuiceHelper.getInjector()
						.getInstance(strutsCreatedAction.getClass());

				// Step 3: copy the servlet object from strutsCreatedAction to
				// guiceCreatedAction
				guiceCreatedAction.setServlet(strutsCreatedAction.getServlet());

				// Step 4: replace strutsCreatedAction in the actions map with
				// guiceCreatedAction
				actions.put(className, guiceCreatedAction);
			}
			// instance = <from map>
			action = (Action) actions.get(className);
		}
		return action;
	}
}
