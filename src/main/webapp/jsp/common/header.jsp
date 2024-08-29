<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/config/setting.jsp" %>
<div class="center">
	<div class="top">
		<div class="left">
			<h1 class="logo">
				<a href="${contextPath }/index.jsp"><img src="${contextPath}/image/logo/logo_bookey.png" alt="logo"></a>
			</h1>
		</div>
		<div class="right">
			<div class="utils">
				<bky-rounded-button icon="${contextPath }/image/icon/log-in.png" label="Login" onclick=""></bky-rounded-button>
				<bky-rounded-button icon="${contextPath }/image/icon/plus-square.png" label="Join" onclick=""></bky-rounded-button>
			</div>
		</div>
	</div>
	<div class="bottom">
		<ul class="mainMenu">
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
		<div class='search'>
			<button class='icon'></button>
		</div>
		<div class="navibg"></div>
		
		<div class="searchArea hdd">
			<div class="container">
				<div class="grouper">
					<div class="inputArea">
						<input type="text" name="keyword" class="searchInput">
						<button class="searchIcon"></button>
					</div>
					<span class="indicator">Trends</span>
					<ul class="trendingList">
						<li class="trendingItem">test01</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>