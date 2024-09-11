<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
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
	<link rel="icon" href="${contextPath }/favicon.ico" type="image/x-icon">
	<script type="text/javascript" src="${contextPath }/js/jquery/jquery-3.7.1.min.js"></script>
	<script defer src="${contextPath }/js/class/RoundedButton.js"></script>
	<script defer src="${contextPath }/js/script/common.js"></script>
	<script defer src="${contextPath }/js/script/header.js"></script>
	<script defer src="${contextPath }/js/script/index.js"></script>
	<script defer src="${contextPath }/js/script/login.js"></script>
	<script defer src="${contextPath }/js/script/article.js"></script>
  <script>
  	$(function() {
  		initHeaderEvent();
  		initIndexEvent();
  		initCommonEvent();
  		initLoginEvent();
  		console.log(`${userList}`)
  		console.log(`${userVO.getUserID()}`)
  		console.log(`${userVO.getUserPW()}`)
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
							<div class="grouper ">
								<div class="top">
									<h1>Bookey</h1>
									<h2>A book must be the axe for the frozen sea inside us.</h2>
								</div>
								<div class="bottom">
									<div class="utils">
										<div class="top">
											<div class="left">
												<div class="inputArea">
													<input type="text" class="searchInput" id="searchInput02" placeholder="Input your keywords"/>
													<button class="searchIcon" id="searchIcon" onclick="event_direct('#searchInput02')"></button>
												</div>
											</div>
											<div class="right">
												<div class="trending">
													<div class="trendingSlider">
														<ul class="trendingList">
															<li class="trendingItem"><span>test01</span></li>
														</ul>
													</div>
													<div class="trendingController">
														<p class="operator goUp"></p>
														<p class="operator goDown"></p>
													</div>
												</div>
											</div>
										</div>
										<div class="bottom">
											<div class="dayOff">
												<div class="left">
													<div class="title"><span>Day off</span></div>
													<div class="yyyymm">
														<p class="yyyy"></p>
														<p class="controller">
															<span class="prev"><img src="${contextPath }/image/icon/chevron-left-white.png" alt="left button" /></span>
															<span class="mm"></span>
															<span class="next"><img src="${contextPath }/image/icon/chevron-right-white.png" alt="right button" /></span>
														</p>
													</div>
												</div>
												<div class="right">
													<div class="dayOffsAmount"><span></span> Days</div>
													<div class="dayOffsList circleTextBox">
		<!-- 												<span class="circleText">11</span> -->
													</div>
													<div class="desc">Every 2nd and 4th Monday is a day off</div>
												</div>
											</div>
											<div class="businessHour">
												<section>
													<article class="left">
														<p class="place">(Mon-Sun) Reading room</p>
														<p class="place">(Weekdays) Intergrate reference room</p>
														<p class="place">(Weekdays) Reference room</p>
														<p class="place">(Weekdays) Digital reference room</p>
													</article>
													<article class="right">
														<p class="time">07:00~22:00</p>
														<p class="time">09:00~22:00</p>
														<p class="time">09:00~18:00</p>
														<p class="time">09:00~22:00</p>
													</article>
												</section>
											</div>
										</div>
									</div>
									<c:choose>
										<c:when test="${userVO != null }">
											<div class="login">
												<div class="top">
													<p class="title">User Information</p>
												</div>
												<div class="bottom">
													<h1 class="userName">hey, ${userVO.getName() }!</h1>
													<ul class="buttonList">
														<li class="buttonItem"><bky-rounded-button icon="${contextPath }/image/icon/book.png" label="Check out" class="checkout" height="80px" ></bky-rounded-button></li>
														<li class="buttonItem"><bky-rounded-button icon="${contextPath }/image/icon/flag.png" label="Reservation" class="reservations" height="80px"></bky-rounded-button></li>
														<li class="buttonItem"><bky-rounded-button icon="${contextPath }/image/icon/file-text.png" label="My Articles" class="articles" height="80px"></bky-rounded-button></li>
													</ul>
												</div>
											</div>
										</c:when>
										<c:otherwise>
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
											<!-- end login -->
										</c:otherwise>
									</c:choose>

								</div>
							</div>
						</div>
					</div>
				</div>
<!-- 				■ frequent -->
				<div class="frequent">
					<div class="cover">
						<div class="center">
							<ul class="shortcutList">
								<li class="shortcutItem">
									<img src="${contextPath }/image/icon/icon_frequent_apply.png" alt="Apply" class="icon" />
									<p class="text">Apply</p>
								</li>
								<li class="shortcutItem">
									<img src="${contextPath }/image/icon/icon_frequent_schedules.png" alt="Schedules" class="icon" />
									<p class="text">Schedules</p>
								</li>
								<li class="shortcutItem">
									<img src="${contextPath }/image/icon/icon_frequent_notices.png" alt="Notices" class="icon" />
									<p class="text">Notices</p>
								</li>
								<li class="shortcutItem">
									<img src="${contextPath }/image/icon/icon_frequent_questions.png" alt="Questions" class="icon" />
									<p class="text">Questions</p>
								</li>
								<li class="shortcutItem">
									<img src="${contextPath }/image/icon/icon_frequent_directions.png" alt="Directions" class="icon" />
									<p class="text">Directions</p>
								</li>
							</ul>						
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