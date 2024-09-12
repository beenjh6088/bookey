<%@page import="com.bookey.user.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/jsp/config/setting.jsp" %>
<c:set var="userVO" value="${sessionScope.userVO}"></c:set>
<div class="center">
	<div class="top">
		<div class="left">
			<h1 class="logo">
				<a href="${contextPath }/"><img src="${contextPath}/image/logo/logo_bookey.png" alt="logo"></a>
			</h1>
		</div>
		<div class="right">
			<div class="utils">
			<%
				UserVO userVO = (UserVO)session.getAttribute("userVO");
				if(userVO == null) {
			%>
					<bky-rounded-button icon="${contextPath }/image/icon/log-in.png" label="Login" class="login"></bky-rounded-button>
					<bky-rounded-button icon="${contextPath }/image/icon/plus-square.png" label="Join" class="join"></bky-rounded-button>					
			<%		
				} else {
			%>
					<bky-rounded-button icon="${contextPath }/image/icon/book.png" label="Check out" class="checkout"></bky-rounded-button>
					<bky-rounded-button icon="${contextPath }/image/icon/flag.png" label="Reservation" class="reservation"></bky-rounded-button>
					<bky-rounded-button icon="${contextPath }/image/icon/log-out.png" label="Logout" class="logout"></bky-rounded-button>
					<bky-rounded-button icon="${contextPath }/image/icon/edit.png" label="Update" class="update"></bky-rounded-button>			
			<%
				}
			%>
			</div>
		</div>
	</div>
	<div class="bottom">
		<ul class="mainMenu wide">
			<li>
				<a href="javascript:;">Search</a>
				<ul class="subMenu">
					<li><a href="#">Books</a></li>
					<li><a href="#">Recommended</a></li>
					<li><a href="#">Trends</a></li>
				</ul>
			</li>
			<li>
				<a href="javascript:;">Apply</a>
				<ul class="subMenu">
					<li><a href="#">Instruction</a></li>
					<li><a href="#">New books</a></li>
				</ul>
			</li>
			<li>
				<a href="javascript:;">Event</a>
				<ul class="subMenu">
					<li><a href="#">Schedules</a></li>
					<li><a href="#">Hold</a></li>
				</ul>
			</li>
			<li>
				<a href="javascript:;">Introduction</a>
				<ul class="subMenu">
					<li><a href="#">Greeting</a></li>
					<li><a href="#">Organization</a></li>
					<li><a href="#">Directions</a></li>
				</ul>
			</li>
			<li>
				<a href="javascript:;">Guide</a>
				<ul class="subMenu">
					<li><a href="#">Materials</a></li>
					<li><a href="#">Facilities</a></li>
					<li><a href="#">Others</a></li>
					<li><a href="#">Disabled</a></li>
				</ul>
			</li>
			<li>
				<a href="javascript:;">Information</a>
				<ul class="subMenu">
					<li><a href="#">Questions</a></li>
					<li><a href="#">Notices</a></li>
				</ul>
			</li>
		</ul>
		
		<div class='search' id="menu">
			<button class='menu'></button>
			<ul class="mainMenu accordion">
				<li>
					<a href="javascript:;" class="accordion-btn">Search</a>
					<ul class="subMenu">
						<li><a href="#">Books</a></li>
						<li><a href="#">Recommended</a></li>
						<li><a href="#">Trends</a></li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" class="accordion-btn">Apply</a>
					<ul class="subMenu">
						<li><a href="#">Instruction</a></li>
						<li><a href="#">New books</a></li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" class="accordion-btn">Event</a>
					<ul class="subMenu">
						<li><a href="#">Schedules</a></li>
						<li><a href="#">Hold</a></li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" class="accordion-btn">Introduction</a>
					<ul class="subMenu">
						<li><a href="#">Greeting</a></li>
						<li><a href="#">Organization</a></li>
						<li><a href="#">Directions</a></li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" class="accordion-btn">Guide</a>
					<ul class="subMenu">
						<li><a href="#">Materials</a></li>
						<li><a href="#">Facilities</a></li>
						<li><a href="#">Others</a></li>
						<li><a href="#">Disabled</a></li>
					</ul>
				</li>
				<li>
					<a href="javascript:;" class="accordion-btn">Information</a>
					<ul class="subMenu">
						<li><a href="#">Questions</a></li>
						<li><a href="#">Notices</a></li>
					</ul>
				</li>
			</ul>
		</div>

		<div class='search'>
			<button class='icon'></button>
		</div>
		<div class="navibg wide"></div>
		
		<div class="searchArea hdd">
			<div class="container">
				<div class="grouper">
					<div class="inputArea">
						<input type="text" name="keyword" class="searchInput" id="searchInput01">
						<button class="searchIcon" onClick="event_direct('#searchInput01')"></button>
					</div>
					<div class="trendings">
						<span class="indicator">Trends</span>
						<ul class="trendingList">
							<li class="trendingItem">test01</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>