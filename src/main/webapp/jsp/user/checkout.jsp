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
	<link rel="stylesheet" href="${contextPath }/css/checkout.css" type="text/css">
	<link rel="icon" href="${contextPath }/favicon.ico" type="image/x-icon">
	<script type="text/javascript" src="${contextPath }/js/jquery/jquery-3.7.1.min.js"></script>
	<script defer src="${contextPath }/js/class/RoundedButton.js"></script>
	<script defer src="${contextPath }/js/class/SquaredButton.js"></script>
	<script defer src="${contextPath }/js/script/common.js"></script>
	<script defer src="${contextPath }/js/script/header.js"></script>
	<script defer src="${contextPath }/js/script/checkout.js"></script>
  <script>
  	$(function() {
  		initHeaderEvent();
  		initCommonEvent();
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
							<div class="container">
								<h1 class="title">Check Out List</h1>
								<form class="frmJoin" method="post" name="frmJoin" action="${contextPath }/user/joinNewUser.do">
									<ul class="joinList">
										<li class="joinItem">
											<label class="question" for="userID"><span>ID</span></label>
											<ul class="answer">
												<li><input type="text" name="userID" id="userID" placeholder="Input your ID" required="required"></li>
												<li class="resultForID hdd"><span></span></li>
											</ul>
										</li>
										<li class="joinItem">
											<label class="question" for="userPW"><span>Password</span></label>
											<div class="answer">
												<input type="password" name="userPW" id="userPW" placeholder="Input your Password" required="required">
											</div>
										</li>
										<li class="joinItem">
											<label class="question" for="userPW02"><span>Input your <b>Password agian</b></span></label>
											<ul class="answer">
												<li><input type="password" name="userPW02" id="userPW02" placeholder="Input your Password agian" required="required"></li>
												<li class="resultForPW hdd"><span></span></li>
											</ul>
										</li>
										<li class="joinItem">
											<label class="question" for="userEmail"><span>Email</span></label>
											<ul class="answer">
												<li>
													<input type="email" name="userEmail" id="userEmail" placeholder="Input your Email" required="required">
													<input type="button" name="sendAuthentication" id="sendAuthentication" value="Send an Authentication number">												
												</li>
												<li>
													<span>a Authentication Number</span>
													<input type="text" name="userAuthentication" id="userAuthentication" placeholder="Input 7 digits for an Authentication">												
												</li>
												<li class="resultForEmail hdd"><span></span></li>
											</ul>
										</li>
										<li class="joinItem">
											<label class="question" for="userName"><span>Name</span></label>
											<div class="answer">
												<input type="text" name="userName" id="userName" placeholder="Input your Name" required="required">
											</div>
										</li>
										<li class="joinItem">
											<label class="question" for="userAddress"><span>Address</span></label>
											<div class="answer">
												<input type="text" name="userAddress" id="userAddress" placeholder="Input your Address" required="required">
											</div>
										</li>
										<li class="joinItem">
											<label class="question" for="userBirthday"><span>BirthDay</span></label>
											<div class="answer">
												<input type="date" name="userBirthday" id="userBirthday" required="required">
											</div>
										</li>
										<li class="joinItem">
											<label class="question"><span>Gender</span></label>
											<div class="answer">
												<span><input type="radio" name="userGender" value="1" checked>Man</span>
												<span><input type="radio" name="userGender" value="2">Woman</span>
											</div>
										</li>
										<li class="joinItem">
											<label class="question"><span>Are you agree with receiving mails for Marketing </span></label>
											<div class="answer">
												<span><input type="radio" name="userMarketing" value="Y" checked>Yes</span>
												<span><input type="radio" name="userMarketing" value="N">No</span>
											</div>
										</li>
									</ul>
									<div class="buttons">
<!-- 										<bky-squared-button label="reset" border="1px solid var(--main-black)" background-color="var(--main-white)" color="var(--main-black)" width="160px" height="24px" font-size="20px"></bky-squared-button> -->
<!-- 										<bky-squared-button label="join" border="0px" background-color="var(--main-red)" color="var(--main-white)" width="160px" height="24px" font-size="20px"></bky-squared-button> -->
										<input type="reset" value="Reset" class="reset"/>
										<input type="button" value="Join" class="submit disabled" />
									</div>
								</form>
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