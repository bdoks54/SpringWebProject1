<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <title>알림창</title>
</head>
<body>
<script>
<%request.setCharacterEncoding("utf-8"); String msg = request.getParameter("msg"); %>
<%if(msg.equals("0")){%>
    alert('로그인실패');
    history.back();
    <%}%>
<%if(msg.equals("1")){%>
alert('로그인성공');
location.href='<c:out value="${pageContext.request.contextPath}/board/listBoard.do"/>${url}';
<%}%>
</script>
</body>
</html>