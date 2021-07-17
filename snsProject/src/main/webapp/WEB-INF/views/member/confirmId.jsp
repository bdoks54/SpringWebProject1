<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function sendTrue(){
		opener.document.getElementById("confirmCheck").value="1";
		self.close();
	}
	function sendFalse(){
		self.close();
	}
</script>
<body>
	<c:choose>
		<c:when test="${confirmId ne ''}">
		<br>
		<h4>이미 사용중인 ID입니다.</h4>
		<input type="button" value="닫기" onclick="sendFalse()">
		</c:when>
		<c:otherwise>
		<br>
		<h4>사용할 수 있는 ID입니다.</h4>
		<input type="button" value="닫기" onclick="sendTrue()">
		</c:otherwise>
	</c:choose>
</body>
</html>