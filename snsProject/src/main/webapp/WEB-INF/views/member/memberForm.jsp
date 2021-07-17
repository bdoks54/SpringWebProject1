<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입창</title>
 <link rel="stylesheet" href="${contextPath}/resources/css/memberForm.css">
</head>
<script type="text/javascript">

	function checkForm(){
		var regExpName = /^[가-힣]*$/;		
	    var regExpId = /^[0-9a-z]+$/;	
		var regExpPw = /^[a-zA-Z0-9]{4,16}$/;	
		var regExpEmail = /^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/;
		
		var strId = document.userInfo.member_id.value;
		var strPw = document.userInfo.member_pw.value;
		var strPwR = document.userInfo.member_pw2.value;
		var strName = document.userInfo.member_name.value;
		var strEmail = document.userInfo.email1.value + "@" + document.userInfo.email2.value;
		var strAddress = document.userInfo.member_home;
		
		if(document.userInfo.email1.value==""){
			strEmail = "none@none.no";
		}
		if (!regExpId.test(strId)) {
			alert("아이디는 영문, 숫자만 입력 가능합니다.");
			return false;
		}
		if(document.getElementById("confirmCheck").value == "0"){
			alert('아이디 중복검사를 먼저 완료해주세요.');
			return false;
		}
		
		if(!regExpName.test(strName)){
			alert("이름은 한글만 입력해야합니다.");
			return false;
		}
		
		if(!regExpPw.test(strPw)){
			alert("비밀번호는 4~16자로 최소 1개의 숫자, 영어를 포함해야 합니다.");
			return false;
		}
		if (!(strPw==strPwR)) {
			alert("비밀번호확인란을 확인해주세요.");
			return false;
		}
		if(!regExpEmail.test(strEmail)){
			alert("이메일을 확인해주세요.");
			return false;
		}
		
		fn_submit();
	}

	function fn_submit() {
		document.getElementById("fullEmail").value = document.getElementById("email1").value + "@" +document.getElementById("email2").value;
		document.getElementById("frm").submit();
	}
	
	function confirmId(){
		if(document.userInfo.member_id.value==""){
			alert("ID를 입력하세요.");
			return;
		}
		url = "${contextPath}" + "/member/confirmId.do?id=" + document.userInfo.member_id.value;
		open(url, "confirm",
				"toolbar=no, location=no, status=no, menubar=no, scrollbars=no, resizable=no, width=300, height=200");
	}
	
</script>
<body>
	<section id="section">
		<!--<div id="close-div" onclick="disappear()">
            <img class="close" src="../image/ui-02-512.png" alt="">
        </div>-->
		<div class="signForm">
			<form action="${contextPath}/member/addMember.do" method="post" id="frm" name="userInfo" onsubmit="return false;">
				<h1 class="all title">회원가입</h1>
				<div class="id all">
					<p class="p-all">아이디</p>
					<input type="text" class="input-all" name="member_id" id="id">&nbsp;<button onclick="confirmId()">중복검사</button>
				</div>
				<div class="password all">
					<p class="p-all">비밀번호</p>
					<input type="password" class="input-all" name="member_pw" id="pw1">
				</div>
				<div class="password all">
					<p class="p-all">비밀번호 확인</p>
					<input type="password" class="input-all" name="member_pw2" id="pw2">
				</div>
				<div class="name all">
					<p class="p-all">이름</p>
					<input type="text" class="input-all" name="member_name" id="">
				</div>
				<div class="email all">
					<p class="p-all">이메일</p>
					<input type="text" id="email1">@<input type="text" id="email2">
					<input type="hidden" name="member_email" id="fullEmail">
				</div>
				<div class="address all">
					<p class="p-all">주소</p>
					<input type="text" name="member_home">
				</div>

				<br> <br>
				<div class="btn all">
					
						<button id="submitBtn" onclick="checkForm();">가입하기</button>
					
				</div>
				<input type="hidden" id="confirmCheck" value="0">
			</form>
			<div class="login">
				계정이 있으신가요? <a href="loginForm.do">로그인</a>
			</div>
		</div>

	</section>
</body>