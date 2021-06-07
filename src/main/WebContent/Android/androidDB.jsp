<%@ page import="member.ConnectDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   

<%
	ConnectDB connectDB = ConnectDB.getInstance();

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	String returns = connectDB.connectionDB(id,pw);
	
	System.out.println(returns);
	
	out.println(returns);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>