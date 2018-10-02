<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>


	<%@ page isErrorPage = "true"
		import = "java.io.*"
		contentType = "text/plain" %>
		
		
	Message: <%= exception.getMessage() %>

	Stack Trace: 
	<% 
		StringWriter stringWriter = new StringWriter(); 
		PrintWriter printWriter = new PrintWriter(stringWriter); 
		exception.printStackTrace(printWriter); 
		out.println(stringWriter);
		printWriter.close();
		stringWriter.close();
	%>
</body>
</html>