<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% Object n = request.getAttribute("negative");
	String neg = (String)n;
	
	Object ne = request.getAttribute("neutral");
	String neu = (String)ne;
	
	Object p = request.getAttribute("positive");
	String po = (String)p;

%>
<body style="text-align: center;">
	<h2>結果</h2>
	<h1>negative：<%=n %></h1>
	<h1>neutral：<%=ne %></h1>
	<h1>positive：<%=po %></h1>
</body>
</html>