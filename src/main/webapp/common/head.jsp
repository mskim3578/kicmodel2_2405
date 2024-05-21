<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-light">
  <ul class="navbar-nav">
  <c:if test="${sessionScope.id==null }">
   
    <li class="nav-item">
      <a class="nav-link" href="${pageContext.request.contextPath}/member/join">회원가입</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${pageContext.request.contextPath}/member/login">로그인</a>
    </li>
    </c:if>
    
    
    <c:if test="${sessionScope.id!=null }">
     <li class="nav-item">
      <a class="nav-link" href="${pageContext.request.contextPath}/member/joinInfo">
      회원정보[<%=session.getAttribute("id") %>]</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
    </li>
   </c:if>
    
    
    <li class="nav-item">
      <a class="nav-link" href="#">공지사항</a>
    </li>
  </ul>
</nav>
<br>
</body>
</html>