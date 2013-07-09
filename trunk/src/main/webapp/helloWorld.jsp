<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
 
<html>
<head>
</head>
<body>
	<h1>
	   Google Guice + Struts 1.x example
	</h1>
	Hello World! This is simple Google Guice injection on struts1 Action example. <br/> 
	<bean:write name="helloWorldForm" property="message" />
</body>
</html>