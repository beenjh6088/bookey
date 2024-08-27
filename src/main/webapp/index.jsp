<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/config/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${contextPath }/css/common.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/header.css" type="text/css">
   <link rel="icon" href="favicon.ico" type="image/x-icon">
	<script type="text/javascript" src="js/jquery/jquery-1.12.3.js"></script>
  <script defer src="js/class/RoundedButton.js"></script>
  <script defer src="js/script/header.js"></script>
  <script>
  	$(function() {
  		initHeaderEvent();
  	})
  	
  </script>
</head>
<body>
	<div class="whole">
		<header id="header">
			<jsp:include page="/jsp/common/header.jsp"></jsp:include>
		</header>
		<main id="centre">
<%-- 			<jsp:include page="/jsp/common/aside.jsp"></jsp:include> --%>
<%-- 			<jsp:include page="/jsp/common/main.jsp"></jsp:include> --%>
			<section id="background">
				<div class="container">
					<div class="top">
						<h1>Bookey</h1>
						<h2>A book must be the axe for the frozen sea inside us.</h2>
					</div>
					<div class="bottom"></div>
				</div>
			</section>
		</main>
		
		
		<footer id="footer">
			<jsp:include page="/jsp/common/footer.jsp"></jsp:include>
		</footer>
	</div>
</body>
</html>