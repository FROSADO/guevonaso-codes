<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	
<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Index</title>
	<s:head />
</head>
<body>
   	<h1>Simple Struts 2 "Hello world" Sample.</h1>
	<s:form action="HelloWorld">
		<s:textfield label="What is your name?" name="name" />
		<s:textfield label="What is the date?" name="now" />
		<s:submit />
	</s:form>
	
	<p>Page under construction</p>
</body>
</html>
	