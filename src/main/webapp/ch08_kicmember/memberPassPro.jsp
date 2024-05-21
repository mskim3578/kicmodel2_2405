<%@page import="ch08.KicMember"%>
<%@page import="ch08.KicMemberDAO"%>
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
String id = (String) session.getAttribute("id");
String pass = request.getParameter("pass");
String chgpass = request.getParameter("chgpass");
KicMemberDAO  dao = new KicMemberDAO();
KicMember memdb = dao.getMember(id);

String msg = "";
String url = "memberPassForm.jsp";
if (memdb!=null){
	if (memdb.getPass().equals(pass)) {
		msg="비밀번호를 수정 하였습니다";
		session.invalidate();
	 	dao.chgPassMember(id, chgpass);   
		url="login.jsp";
	} else {		msg="비밀번호가 틀렸습니다";	}
} else {
	msg="비밀번호를 수정 할 수 없습니다";}
%>
<script>
alert("<%=msg%>");
location.href="<%=url%>"
</script>
</body>
</html>