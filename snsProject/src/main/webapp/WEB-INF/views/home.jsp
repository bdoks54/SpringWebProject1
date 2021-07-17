<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
     <c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<html>
<head>
	<meta charset="utf-8">
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
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
			<c:forEach items="${memberList}" var="member">
				<tr>
					<td>${member.member_num}</td>
					<td>${member.member_name}</td>
					<td>${member.member_id}</td>
					<td>${member.member_email}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
