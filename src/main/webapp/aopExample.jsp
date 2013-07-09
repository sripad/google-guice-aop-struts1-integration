<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
 
<html>
<head>
</head>
<body>
	<h1>
	   Google Guice's AOP + Struts 1.x example
	</h1>
	Hello World! This is Google Guice' AOP on struts1 Action example. <br/>
	<bean:write name="someForm" property="message" />
</body>
</html>