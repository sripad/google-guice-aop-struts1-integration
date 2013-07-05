<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
 
<html>
<head>
</head>
<body>
	<h1>
	   Google Guice + Struts 1.x example
	</h1>
	<h4><bean:write name="helloWorldForm" property="message" /></h4>
</body>
</html>