# google-guice-aop-struts1-integration
Automatically exported from code.google.com/p/google-guice-aop-struts1-integration

##Motivation:
1. To use Google Guice with the Struts1 Action classes.

2. To use Google Guice's AOP on the Struts1 Action classes.

##Getting Started:
* To integrate Google Guice with Struts1, the Action classes must be created through Guice.
* To achieve this, create a custom implementation of the class TilesRequestProcessor and add it in the controller tag of struts-config.xml. Note: struts-config.xml must also include TilesPlugin.
```
public class TilesRequestProcessor extends
                org.apache.struts.tiles.TilesRequestProcessor {

        @Override
        protected boolean processPreprocess(HttpServletRequest request,
                        HttpServletResponse response) {
                return true;
        }

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

        private static void createInjector() {
                injector = Guice.createInjector(Modules.EMPTY_MODULE);
        }
}
```
And add the controller tag as below in struts-config.xml. (It should also have TilesPlugin)

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts-config PUBLIC 
"-//Apache Software Foundation//DTD Struts Configuration 1.3//EN" 
"http://jakarta.apache.org/struts/dtds/struts-config_1_3.dtd">

<struts-config>
        ......
        <controller nocache="true"
                processorClass="com.google.inject.struts1.TilesRequestProcessor">
        </controller>

        <plug-in className="org.apache.struts.tiles.TilesPlugin">
                <set-property property="definitions-config" value="/WEB-INF/tiles-defs.xml" />
                <set-property property="moduleAware" value="false" />
                <set-property property="definitions-parser-validate"
                        value="false" />
        </plug-in>
</struts-config>
```

With this, one can also use Google Guice's AOP on struts1 action classes. Please find the examples [here](https://github.com/sripad/google-guice-aop-struts1-integration/tree/master/src/main/java/com/google/inject/struts1/example).
