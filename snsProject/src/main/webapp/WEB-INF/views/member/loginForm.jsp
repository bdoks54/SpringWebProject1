<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${contextPath}/resources/css/loginForm.css">
</head>
<body>
	<div class="login_main">
        <div class="proname">
            <h2>SNS 프로젝트</h2>
        </div>
        <div class="loginimg">
            <img src="${contextPath }/resources/images/han.jpg" alt="han">
        </div>
    </div>
    
    <section id="section">
        <form action="${contextPath}/member/loginPro.do" method="post">
        <div class="id all">
            <p style="font-weight: bold;">아이디</p>
            <input type="text" class="input-all" name="member_id" id="" placeholder="아이디">
        </div>
        <div class="password all">
            <p style="font-weight: bold;">비밀번호</p>
            <input type="password" class="input-all" name="member_pw" id="" placeholder="비밀번호">
        </div>
        <div class="btn Login all">
            <a href="#">
                <button>로그인</button>
            </a>
        </div>
        </form>
        <div class="goSignup all">
            <a href="${pageContext.request.contextPath}/member/memberForm.do">
                <button>회원가입</button>
            </a>
        </div>
    </section>
</body>
</html>