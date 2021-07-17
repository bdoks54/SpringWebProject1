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
<%request.setCharacterEncoding("UTF-8"); String my_friend = request.getParameter("my_friend"); %>
	<form action="${contextPath}/chat/chat1Reset.do" method="POST">
	<input type="hidden" value="${member_name}" name="my_name">
	<input type="hidden" value="<%=my_friend%>" name="my_friend">
	<input type="submit" value="메시지 새로고침" name="reset">
	</form>
	<c:forEach items="${chat1List}" var="chat1">
				<table>
				<tr>
					<td>${chat1.my_name} : ${chat1.chat}</td>
				</tr>
				</table>					
			</c:forEach>
	<c:forEach items="${chat2List}" var="chat2">
		<table>
				<tr>
					<td>${chat2.my_name} : ${chat2.chat}</td>
				</tr>
				</table>
	</c:forEach>
	<form action="${contextPath}/chat/insertChat1.do" method="POST">
	<input type="hidden" value="${member_name}" name="my_name">
	<input type="hidden" value="<%=my_friend%>" name="my_friend">
	<input type="text" name="chat">
	<input type="submit" value="메시지 보내기">
	</form>
	<form action="${contextPath}/chat/deleteChat1.do" method="POST">
	<input type="hidden" value="${member_name}" name="my_name">
	<input type="hidden" value="<%=my_friend%>" name="my_friend">
	<input type="submit" value="내가 보낸 메시지  삭제">
	</form>
</body>
</html>