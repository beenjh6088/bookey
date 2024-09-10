<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/jsp/config/setting.jsp" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="userVO" value="${sessionScope.userVO}"></c:set>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${contextPath }/css/common.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/header.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/footer.css" type="text/css">
	<link rel="stylesheet" href="${contextPath }/css/reservation.css" type="text/css">
	<link rel="icon" href="${contextPath }/favicon.ico" type="image/x-icon">
	<script type="text/javascript" src="${contextPath }/js/jquery/jquery-3.7.1.min.js"></script>
	<script defer src="${contextPath }/js/class/RoundedButton.js"></script>
	<script defer src="${contextPath }/js/class/SquaredButton.js"></script>
	<script defer src="${contextPath }/js/class/ItemI1F4B1.js"></script>
	<script defer src="${contextPath }/js/script/common.js"></script>
	<script defer src="${contextPath }/js/script/header.js"></script>
	<script defer src="${contextPath }/js/script/reservation.js"></script>
  <script>
  	$(function() {
  		initHeaderEvent();
  		initCommonEvent();
  		initReservationEvent();
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
								<h1 class="title">Reservation List</h1>
								<form class="frmUnderline frmReservation" method="post" name="frmReservation" action="${contextPath }/user/.do">
									<input type='hidden' name='userID' value='${userVO.getUserID() }' id="userID"/>
									<ul class="dataList">
<%-- 										<li class="dataItem"><bky-item-i1f4b1 class="bookItem" src="${contextPath }/image/book/JAVA.jpg" bookNM="JAVA" rentalDate="2019-01-01" dueDate="2019-02-10" rentalValue="ongoing" buttonValue="Return" bookID="A0000001" userID="qwer"></bky-item-i1f4b1></li> --%>
									</ul>
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