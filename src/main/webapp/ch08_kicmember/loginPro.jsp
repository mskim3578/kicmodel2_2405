<%@page import="ch08.KicMember"%>
<%@page import="ch08.KicMemberDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

%>
<script>
alert("<%=msg%>");
location.href="<%=url%>"
</script>
<%=id %>
<%=pass %>
</body>
</html>