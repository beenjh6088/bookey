<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="${contextPath }/css/join.css" type="text/css">
	<link rel="icon" href="${contextPath }/favicon.ico" type="image/x-icon">
	<script type="text/javascript" src="${contextPath }/js/jquery/jquery-3.7.1.min.js"></script>
	<script defer src="${contextPath }/js/class/RoundedButton.js"></script>
	<script defer src="${contextPath }/js/class/SquaredButton.js"></script>
	<script defer src="${contextPath }/js/script/common.js"></script>
	<script defer src="${contextPath }/js/script/header.js"></script>
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
								<h1 class="title">Application form to Join</h1>
								<form class="frmJoin">
									<ul class="joinList">
										<li class="joinItem">
											<label class="question"><span>ID</span></label>
											<div class="answer">
												<input type="text" name="_id" placeholder="Input your ID">
											</div>
										</li>
										<li class="joinItem">
											<label class="question"><span>Password</span></label>
											<div class="answer">
												<input type="password" name="_pw" placeholder="Input your Password">
											</div>
										</li>
										<li class="joinItem">
											<label class="question"><span>Input your <b>Password agian</b></span></label>
											<div class="answer">
												<input type="password" name="_pw02" placeholder="Input your Password agian">
											</div>
										</li>
										<li class="joinItem resultForID hdd">
											<p><span></span></p>
										</li>
										<li class="joinItem">
											<label class="question"><span>Email</span></label>
											<div class="answer">
												<input type="email" name="_email" placeholder="Input your Email">
											</div>
										</li>
										<li class="joinItem">
											<label class="question"><span>Address</span></label>
											<div class="answer">
												<input type="text" name="_address" placeholder="Input your Address">
											</div>
										</li>
										<li class="joinItem">
											<label class="question"><span>BirthDay</span></label>
											<div class="answer">
												<input type="date" name="_birthday" >
											</div>
										</li>
										<li class="joinItem">
											<label class="question"><span>Gender</span></label>
											<div class="answer">
												<span><input type="radio" name="_gender" value="1" checked>Man</span>
												<span><input type="radio" name="_gender" value="2">Woman</span>
											</div>
										</li>
										<li class="joinItem">
											<label class="question"><span>Are you agree with receiving mails for Marketing</span></label>
											<div class="answer">
												<span><input type="radio" name="_marketing" value="Y" checked>Yes</span>
												<span><input type="radio" name="_marketing" value="N">No</span>
											</div>
										</li>
									</ul>
									<div class="buttons">
										<bky-squared-button label="reset" border="1px solid var(--main-black)" background-color="var(--main-white)" color="var(--main-black)" width="160px" height="24px" font-size="20px"></bky-squared-button>
										<bky-squared-button label="join" border="0px" background-color="var(--main-red)" color="var(--main-white)" width="160px" height="24px" font-size="20px"></bky-squared-button>
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