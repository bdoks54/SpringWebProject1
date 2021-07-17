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
<%request.setCharacterEncoding("utf-8"); %>
<body>
	<table>
		<thead>
			<tr>
				<th>이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${friendsList}" var="friends">
			<form method="post">
			<input type="hidden" value="${member_name}" name ="my_name">
			<input type="hidden" value="${friends.id_2}" name="my_friend">
				<table>
				<tr>
					<td><a>${friends.id_2}</a></td>
					<td><input type="submit" value="메시지 주고받기" formaction="${contextPath}/chat/chat1.do"></td>
					<td><input type="submit" value="친구 삭제" formaction="${contextPath}/friends/deleteFriend.do"></td>
				</tr>
				</table>
				</form>						
			</c:forEach>
		</tbody>
	</table>
	<a  href="${contextPath}/member/memberForm.do"><h1 style="text-align:center">회원가입</h1></a>
	<a href="${contextPath}/member/logoutPro.do"><h1>로그아웃</h1></a>
</body>
</html>