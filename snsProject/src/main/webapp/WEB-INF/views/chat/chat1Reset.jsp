<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
     <c:if test="${member_name eq '' || member_name eq null }">
   <jsp:forward page="/member/loginForm.do"></jsp:forward>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
<%request.setCharacterEncoding("UTF-8"); %>
<form method=post action="${contextPath}/chat/chat1.do" name="frm">
<input type="hidden" name="my_name" value="${member_name}">
<input type="hidden" name="my_friend" value="${my_friend}">
</form>
<script language="javascript">
document.frm.submit();
</script>
</body>
</html>