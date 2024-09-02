<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/jsp/config/setting.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${contextPath }/css/common.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/header.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/footer.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/login.css" type="text/css">
	<link rel="icon" href="${contextPath }/favicon.ico" type="image/x-icon">
	<script type="text/javascript" src="${contextPath }/js/jquery/jquery-3.7.1.min.js"></script>
	<script defer src="${contextPath }/js/class/RoundedButton.js"></script>
	<script defer src="${contextPath }/js/script/common.js"></script>
	<script defer src="${contextPath }/js/script/header.js"></script>
	<script defer src="${contextPath }/js/script/login.js"></script>
  <script>
  	$(function() {
  		initHeaderEvent();
  		initCommonEvent();
  		initLoginEvent();
  	})
  </script>
</head>
<body>
	<div class="whole">
		<header id="header">
			<jsp:include page="/jsp/common/header.jsp"></jsp:include>
		</header>
		<main id="centre">
			<section id="background">
<!-- 				■ main -->
				<div class="main">
					<div class="cover">
						<div class="center">
							<div class="login">
								<div class="top">
									<p class="title">Login</p>
								</div>
								<div class="bottom">
									<form class="frmLogin" name="frmLogin">
										<div class="input">
											<div class="id">
												<div class="icon">
													<img src="${contextPath }/image/icon/user.png" alt="userIcon"  />
												</div>
												<input type="text" class="userID" name="userID" placeholder="ID" />
											</div>
											<div class="pw">
												<div class="icon">
													<img src="${contextPath }/image/icon/lock.png" alt="lockIcon"  />
												</div>
												<input type="password" class="userPW" name="userPW" placeholder="Password" />
											</div>
										</div>
										<div class="button"><button class="loginButton">Login</button></div>
									</form>
									<ul class="help">
										<li class="access"><a href="javascript:;">Look for ID</a></li>
										<li class="access"><a href="javascript:;">Look for Password</a></li>
										<li class="access"><a href="${contextPath }/jsp/user/join.jsp">Join</a></li>
									</ul>
								</div>
							</div>
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