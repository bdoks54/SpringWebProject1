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

	<%
		String name = (String) session.getAttribute("member_name");
		if(name != null){
			%>
			<%=name%>님 안녕하세요.
			<%
		}
		
		pageContext.setAttribute("name", name);
	%>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>이메일</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${membersList}" var="member">
			<c:if test= "${name ne member.member_name}">
			<section id="section">
			<form method="post" id="frm">
         <input type="hidden" value="<%=name%>" name="id_1">      
         <input type="hidden" value="${member.member_name}" name="id_2">
         <input type="hidden" value="${member_name}" name ="my_name">
         <input type="hidden" value="${friends.id_2}" name="my_friend">
				<table>
				<tr>
					<td>${member.member_num}</td>
					<td>${member.member_name}</td>
					<td>${member.member_id}</td>
					<td>${member.member_email}</td>
					<td><input type="submit" value="친구추가" formaction="${contextPath}/friends/insertFriend.do"></td>
					<td><input type="submit" value="메시지 보내기" formaction="${contextPath}/chat/chat1.do"></td>
				</tr>
				</table>					
				</form>
				</section>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<a  href="${contextPath}/member/memberForm.do"><h1 style="text-align:center">회원가입</h1></a>
	<a href="${contextPath}/member/logoutPro.do"><h1>로그아웃</h1></a>
        <form action="${contextPath}/friends/listFriends.do" method="post">
            <input type="hidden" name="id_1" value="<%=name%>">
        	<input type="submit" value="친구목록">
        </form>
</body>
</html>