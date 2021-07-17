<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link rel="stylesheet" href="${contextPath}/resources/css/header.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<style type="text/css">
button, .btn {
	background-color: transparent;
	border: 1px solid;
}

button:hover, .btn:hover {
	box-shadow: rgba(30, 22, 54, 0.7) 0 0px 0px 40px inset;
}

.comment{
	
}
</style>
</head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<c:set var="sessionName"><%=name%></c:set>
<c:set var="sessionId"><%=id%></c:set>
<c:if test="${sessionId eq '' }">
	<jsp:forward page="/member/loginForm.do"></jsp:forward>
</c:if>
<script type="text/javascript">

	history.scrollRestoration = "manual";
	
	var viewType = '<%=request.getParameter("viewType")%>';
	
	
	var page=2;
	var appendComment ="";
	var str = "";

	

	$(window)
			.scroll(
					function() {
						if ($(window).scrollTop() >= $(document).height()
								- $(window).height()) {
							
							if(viewType != "1"){
							$.ajax({
								url: "getBoardList",
					            type: "POST",
					            data: {
					            	page: page
					            },
					            dataType: 'json',
					            success: function(data){
					            	page++;	
					            	var contact = JSON.parse(JSON.stringify(data.boardList));
					            	var contact2 = JSON.parse(JSON.stringify(data.commentList));
					            	console.log(contact);
					            	console.log(contact2);
					            	for(var i=0;i<contact.length;i++){
					            		
					            		if(contact[i].board_img == "1"){
					            			var append1 = '<div class="feed"><h3 class="name">'+contact[i].board_name+'</h3><div class="date">'+contact[i].board_date+'</div><a class="title">'+contact[i].board_title;
					            			var append2 = '</a><p class="content">'+contact[i].board_content+'<br><img alt="이미지" src="${contextPath}/download.do?board_num='+contact[i].board_number+'&imageFileName='+contact[i].board_imgname+'">'+'</p>'+'<div class="accessory"><img src="${contextPath}/resources/images/ic_like.png" width="16px" onclick="location.href=\'countBoard.do?board_num='+contact[i].board_number;
					            			var append3 = '\'">Like : '+contact[i].board_like+'<img src="${contextPath }/resources/images/ic_comment.png" width="16px"> Comments ';
					            			var append4 = '';
					            			if(contact[i].board_id == '<%=id%>'){
					            				append4 = '<button style="float:right;" onclick="delPro('+contact[i].board_number+')">삭제</button> <button style="float:right;" onclick="location.href=\'updateForm.do?board_num='+contact[i].board_number+'\'">수정</button> ';
					            			}
					            			var append5 = '</div>'+'</div>';
					            								            			
					            		$('.post').append(append1 + append2 + append3 + append4 + append5);	
					            		
					            		for(var j=0;j<contact2.length;j++){
					            			if(contact2[j].comment_boardNum == contact[i].board_number){
					            				str1 = '<div style="border-bottom:1px solid #eee;"><div class="font_small">'+contact2[j].comment_name+' '+contact2[j].comment_date+'</div>'+'&nbsp;'+contact2[j].comment_content;
					            				str2 = '</div>';
					            				if(contact2[j].comment_id == '<%=id%>'){
					            					str2 = '<button onclick="delProComment('+contact2[j].comment_num+')">삭제</button></div>';
					            				}
					            				
					            				appendComment = appendComment + str1+str2;
					            			}else{
					            				
					            			}
					            		}
					            		
					            		$('.post').append('<div class="comment"><div style="border-bottom:1px solid #eee;">댓글</div>'+appendComment+' <c:if test="${sessionId ne '' }"><div><form action="${contextPath}/comment/addComment.do" method="post"> <input type="hidden" name="comment_id" value="<%=id%>"><input type="hidden" name="comment_name" value="<%=name%>"><input type="hidden" name="comment_boardNum" value="'+contact[i].board_number+'"><input type="text" name="comment_content" class="commentText"> <input class="btn" type="submit" value="작성"></form></div></c:if></div>');
					            		appendComment = '';
					            		}else{
					            			var append1 = '<div class="feed"><h3 class="name">'+contact[i].board_name+'</h3><div class="date">'+contact[i].board_date+'</div><a class="title">'+contact[i].board_title+'</a><p class="content">'+contact[i].board_content+'<br></p>'+'<div class="accessory"><img src="${contextPath}/resources/images/ic_like.png" width="16px" onclick="location.href=\'countBoard.do?board_num='+contact[i].board_number+'\'">Like : '+contact[i].board_like+'<img src="${contextPath }/resources/images/ic_comment.png" width="16px"> Comments ';
					            			var append2 = '';
					            			if(contact[i].board_id == '<%=id%>'){
					            				append2 = '<button style="float:right;" onclick="delPro('+contact[i].board_number+')">삭제</button> <button style="float:right;" onclick="location.href=\'updateForm.do?board_num='+contact[i].board_number+'\'">수정</button> ';
					            			}
					            			var append3 = '</div>'+'</div>';
					            			$('.post').append(append1+append2+append3);
					            			for(var j=0;j<contact2.length;j++){
						            			if(contact2[j].comment_boardNum == contact[i].board_number){
						            				var str1 = '<div style="border-bottom:1px solid #eee;"><div class="font_small">'+contact2[j].comment_name+' '+contact2[j].comment_date+'</div>'+'&nbsp;'+contact2[j].comment_content;
						            				var str2 = '</div>';
						            				if(contact2[j].comment_id == '<%=id%>'){
						            					str2 = '<button onclick="location.href=\'delProComment('+contact2[j].comment_num+')">삭제</button></div>';
						            				}
						            				
						            				appendComment = appendComment + str1+str2;
						            			}else{
						            				
						            			}
						            		}
					            			
						            		$('.post').append('<div class="comment"><div style="border-bottom:1px solid #eee;">댓글</div>'+appendComment+' <c:if test="${sessionId ne '' }"><div><form action="${contextPath}/comment/addComment.do" method="post"> <input type="hidden" name="comment_id" value="<%=id%>"><input type="hidden" name="comment_name" value="<%=name%>"><input type="hidden" name="comment_boardNum" value="'+contact[i].board_number+'"><input type="text" name="comment_content" class="commentText"> <input class="btn" type="submit" value="작성"></form></div></c:if></div>');
						            		appendComment = '';
					            		}
					            			
					            	}
					            	
					        
					            },
					            error: function(){
					                console.log("serialize err");
					            }
							});
						}else if(viewType==="1"){
							$.ajax({
								url: "getImgBoardList",
					            type: "POST",
					            data: {
					            	page: page
					            },
					            dataType: 'json',
					            success: function(data){
					            	page++;
					            	var contact = JSON.parse(JSON.stringify(data.boardList));
					            	var contact2 = JSON.parse(JSON.stringify(data.commentList));
					            	console.log(contact);
					            	console.log(contact2);
					            	for(var i=0;i<contact.length;i++){
					            		var append1 = '<div class="feed"><h3 class="name">'+contact[i].board_name+'</h3><div class="date">'+contact[i].board_date+'</div><a class="title">'+contact[i].board_title+'</a><p class="content">'+contact[i].board_content+'<br><img alt="이미지" src="${contextPath}/download.do?board_num='+contact[i].board_number+'&imageFileName='+contact[i].board_imgname+'">'+'</p>'+'<div class="accessory"><img src="${contextPath}/resources/images/ic_like.png" width="16px" onclick="location.href=\'countBoard.do?board_num='+contact[i].board_number+'\'">Like : '+contact[i].board_like+'<img src="${contextPath }/resources/images/ic_comment.png" width="16px"> Comments ';
					            		var append2 = '</div>'+'</div>';
					            		if(contact[i].board_id == '<%=id%>'){
					            			append2 = '<button style="float:right;" onclick="delPro('+contact[i].board_number+')">삭제</button> <button style="float:right;" onclick="location.href=\'updateForm.do?board_num='+contact[i].board_number+'\'">수정</button> '+'</div>'+'</div>';
					            		}
					            		
					            		$('.post').append(append1+append2);					           					            
					            		for(var j=0; j<contact2.length ;j++){
					            			if(contact2[j].comment_boardNum == contact[i].board_number){
					            				var str1 = '<div style="border-bottom:1px solid #eee;"><div class="font_small">'+contact2[j].comment_name+' '+contact2[j].comment_date+'</div>'+'&nbsp;'+contact2[j].comment_content+'</div>';
					            				var str2 = '';
					            				if(contact2[j].comment_id == '<%=id%>'){
					            					str2 = '<button onclick="location.href=\'delProComment('+contact2[j].comment_num+')">삭제</button>';
					            				}
					            				appendComment = appendComment + str;
					            			}else{
					            				
					            			}
					            		}
					            		
					            		$('.post').append('<div class="comment"><div style="border-bottom:1px solid #eee;">댓글</div>'+appendComment+' <c:if test="${sessionId ne '' }"><div><form action="${contextPath}/comment/addComment.do" method="post"> <input type="hidden" name="comment_id" value="<%=id%>"><input type="hidden" name="comment_name" value="<%=name%>"><input type="hidden" name="comment_boardNum" value="'+contact[i].board_number+'"><input type="text" name="comment_content" class="commentText"> <input class="btn" type="submit" value="작성"></form></div></c:if></div>');
					            		appendComment = '';
					            	}
					            	
					            },
					            error: function(){
					                console.log("serialize err");
					            }
							});
						}
						}
					});
	
	function delPro(value){
		if(confirm("정말 삭제하시겠습니까??") == true){
			location.href='deleteBoard.do?board_num='+value;
		}else{
			return false;
		}
	}
	
	function delProComment(value){
		if(confirm("정말 삭제하시겠습니까??") == true){
			location.href='${contextPath}/comment/deleteComment.do?comment_num='+value;
		}else{
			return false;
		}
		
	}
</script>


<body>
	<header>
		<div class="home">
			<div class="titleimg">
				<a class="ezenstagram" href="${contextPath}/board/listBoard.do"><img
					class="ezenstagram"
					src="${contextPath }/resources/images/title.png">EzenStagram</a>
			</div>
			<div class="menu">
				<ul id="menu_ul">
					<li class="menu_li"><i class="fas fa-home"
						style="color: dodgerblue;"><a
							href="${contextPath}/board/listBoard.do"> <b>홈</b>
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
				<c:if test="${sessionId ne ''}">
					<div class="writeForm">
						<div class="postMenu">
							<section id="section">
								<ul id="menu_ul">
									<li class="menu_li"><a
										href="${contextPath }/board/boardForm.do"> 게시물 작성
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
									<li class="menu_li"><a href="#"> 저장됨
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
									<li class="menu_li"><a
										href="${contextPath }/board/listBoard.do?viewType=1">
											사진/동영상 </a></li>
								</ul>
							</section>
						</div>
					</div>
				</c:if>
				<div class="post" id="post">
					<c:forEach items="${boardList}" var="board">
						<!-- 글 내용 -->
						<div class="feed">
							<h3 class="name">${board.board_name }</h3>
							<div class="date">${board.board_date }</div>
							<a class="title">${board.board_title }</a>
							<p class="content">${board.board_content }<br>
								<c:if test="${board.board_img eq 1}">
									<img alt="이미지"
										src="${contextPath}/download.do?board_num=${board.board_number}&imageFileName=${board.board_imgname}">
								</c:if>
							</p>
							<div class="accessory">
								<img src="${contextPath }/resources/images/ic_like.png"
									width="16px"
									onclick="location.href='countBoard.do?board_num=${board.board_number}'">Like
								: ${board.board_like } <img
									src="${contextPath }/resources/images/ic_comment.png"
									width="16px"> Comments

								<c:if test="${board.board_id eq sessionId}">
									<button style="float: right;"
										onclick="delPro(${board.board_number})">삭제</button>
									<button style="float: right;"
										onclick="location.href='updateForm.do?board_num=${board.board_number}'">수정</button>

								</c:if>
							</div>
						</div>
						<!-- 댓글창 -->
						<div class="comment">
							<div style="border-bottom:1px solid #eee;">
							댓글
							</div>
							<c:forEach items="${commentList}" var="comment">
								<c:if test="${comment.comment_boardNum eq board.board_number}">
									<div style="border-bottom:1px solid #eee;"><div class="font_small">${comment.comment_name } ${comment.comment_date }</div> &nbsp;${comment.comment_content }
										<c:if test="${comment.comment_id eq sessionId }">
										<button onclick="delProComment(${comment.comment_num})">삭제</button>
										</c:if>
										
									</div>
								</c:if>
							</c:forEach>
							<c:if test="${sessionId ne '' }">
								<div>
									<form action="${contextPath}/comment/addComment.do"
										method="post">
										<input type="hidden" name="comment_id" value="${sessionId }">
										<input type="hidden" name="comment_name" value="${sessionName }">
										<input type="hidden" name="comment_boardNum"
											value="${board.board_number }"> <input type="text"
											name="comment_content" class="commentText"> <input class="btn"
											type="submit" value="작성">
									</form>
								</div>
							</c:if>
						</div>
					</c:forEach>
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