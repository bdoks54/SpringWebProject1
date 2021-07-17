<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>글쓰기</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function fn_img(input) {
		var reader = new FileReader();
		var file = document.querySelector('input[type=file]').files[0];
		if (file) {
			document.getElementById("img").value = "1";
		}

	}
</script>
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
<link rel="stylesheet" href="${contextPath}/resources/css/post_writing.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
	<%
		String name = (String) session.getAttribute("member_name");
		String id = (String) session.getAttribute("member_id");
		if (name == null) {
			name = "";
		}
		if (id == null) {
			id = "";
		}
	%>
	<c:set var="sessionName"><%=name%></c:set>
	<c:set var="sessionId"><%=id%></c:set>
	
	<header>
		<div class="home">
			<div class="titleimg">
				<a class="ezenstagram" href="${contextPath}/board/listBoard.do"><img class="ezenstagram"
					src="${contextPath}/resources/images/title.png">EzenStagram</a>
			</div>
			<div class="menu">
				<ul id="menu_ul">
					<li class="menu_li"><i class="fas fa-home"
						style="color: dodgerblue;"><a href="${contextPath}/board/listBoard.do"> <b>홈</b>
						</a></i></li>
					<li>&nbsp;&nbsp;&nbsp;</li>
					<li class="menu_li"><i class="fab fa-youtube"
						style="color: dodgerblue;"><a href="#"> <b>영상</b>
						</a></i></li>
					<li>&nbsp;&nbsp;&nbsp;</li>
					<li class="menu_li"><i class="fas fa-users"
						style="color: dodgerblue;"><a href="#"> <b>그룹</b>
						</a></i></li>
				</ul>
			</div>
		</div>
		<c:choose>
			<c:when test="${sessionId eq '' }">
				<span class="top_button"><a id="join"
					href="${contextPath}/member/memberForm.do">회원가입</a> <a id="login"
					href="${contextPath}/member/loginForm.do">로그인</a> </span>
			</c:when>
			<c:otherwise>
				<span class="top_button">${sessionName}님 환영합니다. <br> <a
					href="${contextPath}/member/logoutPro.do">로그아웃</a></span>
			</c:otherwise>
		</c:choose>
	</header>
	<main>
		<div style="display: flex;">
			<div>
				<div role="navigation" class="navi">
					<h2 class="navi_menu">Facebook 메뉴</h2>
					<div class="navi_content1">
						<div class="navi_content2">
							<div>
								<ul>
									<li><a>
											<div class="navi_content3"
												style="padding-left: 8px; padding-right: 8px">
												<div class="navi_content_img">
													<img
														src="https://static.xx.fbcdn.net/rsrc.php/v3/yR/r/tInzwsw2pVX.png">
												</div>
												<div class="navi_content_text">홍길동</div>
											</div>
									</a></li>
									<li><a>
											<div class="navi_content3"
												style="padding-left: 8px; padding-right: 8px">
												<div class="navi_content_img">
													<img
														src="https://static.xx.fbcdn.net/rsrc.php/v3/yx/r/-XF4FQcre_i.png">
												</div>
												<div class="navi_content_text">친구</div>
											</div>
									</a></li>
									<li><a>
											<div class="navi_content3"
												style="padding-left: 8px; padding-right: 8px">
												<div class="navi_content_img">
													<img
														src="https://static.xx.fbcdn.net/rsrc.php/v3/yD/r/mk4dH3FK0jT.png">
												</div>
												<div class="navi_content_text">그룹</div>
											</div>
									</a></li>
									<li><a>
											<div class="navi_content3"
												style="padding-left: 8px; padding-right: 8px">
												<div class="navi_content_img">
													<img
														src="https://static.xx.fbcdn.net/rsrc.php/v3/yv/r/QAyfjudAqqG.png">
												</div>
												<div class="navi_content_text">이벤트</div>
											</div>
									</a></li>
									<li><a>
											<div class="navi_content3"
												style="padding-left: 8px; padding-right: 8px">
												<div class="navi_content_img">
													<img
														src="https://static.xx.fbcdn.net/rsrc.php/v3/yZ/r/i7hepQ2OeZg.png">
												</div>
												<div class="navi_content_text">페이지</div>
											</div>
									</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="container">
				<div class="writeForm">
					<div class="postMenu">
						<section id="section">
							<ul id="menu_ul">
								<li class="menu_li"><a href="${contextPath}/board/boardForm.do"> 게시물 작성
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
								<li class="menu_li"><a href="#"> 저장됨
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
								<li class="menu_li"><a href="#"> 사진/동영상 </a></li>
							</ul>
						</section>
					</div>
					<div class="write_form">
						<article class="write">
							<h2 class="tit_write">게시물 만들기</h2>
							<hr class="hr_write_title">
							<form name="writing_form" id="w_form" action="${contextPath}/board/addBoard.do" method="post" enctype="multipart/form-data">
								<div class="img_preview">
									<span id="preview"></span> <input type="file" class="hiddenBtn"
										name="board_imgname" onchange="fn_img(this);" accept="image/*" id="bizFile">
									<!-- <span id="fileName">선택된 파일없음</span> -->
								</div>
								<div class="basicInfo">
									<ul>
										<li><label for="postName">글제목 </label><input type="text"
											id="postName" name="board_title" size="5"></li>
										<li><label for="post">내용</label>
										<textarea style="border-color: rgb(172, 243, 239)"
												name="board_content" cols="50" rows="5"
												placeholder="내용을 작성하세요."></textarea></li>
										<input type="hidden" id="img" name="board_img" value="0">
										<input type="hidden" name="board_name" value="${sessionName}">
										<input type="hidden" name="board_id" value="${sessionId}">
									</ul>
								</div>
								<button type="submit" class="btn submitBtn" name="등록">
									등록</button>
							</form>
						</article>
					</div>
				</div>
			</div>

			<div class="complementary">
				<div class="comp_content1">
					<div class="comp_title">연락처</div>
					<div class="comp_content2">
						<ul>
							<li><a>
									<div class="comp_content3"
										style="padding-left: 8px; padding-right: 8px">
										<div class="comp_content_img">
											<img
												src="https://static.xx.fbcdn.net/rsrc.php/v3/yR/r/tInzwsw2pVX.png">
										</div>
										<div class="comp_content_text">홍길동</div>
									</div>
							</a></li>
							<li><a>
									<div class="comp_content3"
										style="padding-left: 8px; padding-right: 8px">
										<div class="comp_content_img">
											<img
												src="https://static.xx.fbcdn.net/rsrc.php/v3/yR/r/tInzwsw2pVX.png">
										</div>
										<div class="comp_content_text">홍길동</div>
									</div>
							</a></li>
							<li><a>
									<div class="comp_content3"
										style="padding-left: 8px; padding-right: 8px">
										<div class="comp_content_img">
											<img
												src="https://static.xx.fbcdn.net/rsrc.php/v3/yR/r/tInzwsw2pVX.png">
										</div>
										<div class="comp_content_text">홍길동</div>
									</div>
							</a></li>
							<li><a>
									<div class="comp_content3"
										style="padding-left: 8px; padding-right: 8px">
										<div class="comp_content_img">
											<img
												src="https://static.xx.fbcdn.net/rsrc.php/v3/yR/r/tInzwsw2pVX.png">
										</div>
										<div class="comp_content_text">홍길동</div>
									</div>
							</a></li>
							<li><a>
									<div class="comp_content3"
										style="padding-left: 8px; padding-right: 8px">
										<div class="comp_content_img">
											<img
												src="https://static.xx.fbcdn.net/rsrc.php/v3/yR/r/tInzwsw2pVX.png">
										</div>
										<div class="comp_content_text">홍길동</div>
									</div>
							</a></li>
							<li><a>
									<div class="comp_content3"
										style="padding-left: 8px; padding-right: 8px">
										<div class="comp_content_img">
											<img
												src="https://static.xx.fbcdn.net/rsrc.php/v3/yR/r/tInzwsw2pVX.png">
										</div>
										<div class="comp_content_text">홍길동</div>
									</div>
							</a></li>

						</ul>
					</div>
				</div>
			</div>

		</div>
	</main>
</body>


</html>