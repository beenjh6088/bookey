<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/jsp/config/setting.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="userList" value="${applicationScope.userList}"></c:set>
<c:set var="userVO" value="${sessionScope.userVO}"></c:set> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${contextPath }/css/common.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/header.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/footer.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/index.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/aside.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/article.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/detail.css" type="text/css">
	<link rel="icon" href="${contextPath }/favicon.ico" type="image/x-icon">
	<script type="text/javascript" src="${contextPath }/js/jquery/jquery-3.7.1.min.js"></script>
	<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.2/kakao.min.js" integrity="sha384-TiCUE00h649CAMonG018J2ujOgDKW/kVWlChEuu4jK2vxfAAD0eZxzCKakxg55G4" crossorigin="anonymous"></script>
	<script defer src="${contextPath }/js/class/RoundedButton.js"></script>
	<script defer src="${contextPath }/js/class/BookItem.js"></script>
	<script defer src="${contextPath }/js/class/BookDetail.js"></script>
	<script defer src="${contextPath }/js/script/common.js"></script>
	<script defer src="${contextPath }/js/script/header.js"></script>
	<script defer src="${contextPath }/js/script/index.js"></script>
	<script defer src="${contextPath }/js/script/login.js"></script>
	<script defer src="${contextPath }/js/script/aside.js"></script>
	<script defer src="${contextPath }/js/script/article.js"></script>
	<script defer src="${contextPath }/js/script/detail.js"></script>
  <script>
  	$(function() {
  		initHeaderEvent();
  		initCommonEvent();
  		initAsideEvent();
  		initDetailEvent();
  	})
  </script>
</head>
<body>
	<div class="whole">
		<header id="header">
				<jsp:include page="/jsp/common/header.jsp" ></jsp:include>
		</header>
		<main id="centre">
			<section id="background">
<!-- 				■ main -->
				<div class="main">
					<div class="cover">
						<div class="center">
							<jsp:include page="/jsp/search/books/aside.jsp"></jsp:include>
							<jsp:include page="/jsp/search/books/detail/detailArticle.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</section>
		</main>
		
		<footer id="footer">
			<jsp:include page="/jsp/common/footer.jsp"></jsp:include>
		</footer>
	</div>
</body>
</html>