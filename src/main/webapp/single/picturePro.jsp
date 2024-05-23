<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
let img = opener.document.getElementById("pic")
img.src
="${pageContext.request.contextPath}/img/member/picture/${filename}";
alert(img.src)
</script>
<body></body></html>