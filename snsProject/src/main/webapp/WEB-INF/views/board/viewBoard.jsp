<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
     <c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>제목</td>
			<td>${board.board_title}</td>
		</tr>
		<tr>
			<td>글쓴이</td>
			<td>${board.board_id}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${board.board_date}</td>
		</tr>
		<tr>
			<td>좋아요</td>
			<td>${board.board_like}</td>
		</tr>
		<tr>
			<td colspan="2">${board.board_content}</td>
		</tr>
	</table>
	<button onclick="location.href='updateBoard.do?board_num=${board.}'">수정</button>
	<button onclick="location.href='deleteBoard.do?board_num=${board.board_number}'">삭제</button>
</body>
</html>