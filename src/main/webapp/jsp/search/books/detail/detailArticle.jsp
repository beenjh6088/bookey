<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/config/setting.jsp" %>
<%
    String serverName = request.getServerName();
    int serverPort = request.getServerPort();
    String contextPath = request.getContextPath();
    String filePath = request.getServletPath();
    String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
    String fullURL = "http://"+serverName+":"+serverPort+contextPath+filePath;
    
    String[] arrFilePath = filePath.split("/");
    String dept01 = arrFilePath[2].substring(0,1).toUpperCase() + arrFilePath[2].substring(1);
    String dept02 = arrFilePath[3].substring(0,1).toUpperCase() + arrFilePath[3].substring(1);
    
//     String isAvailable = request.getParameter("isAvailable");
    String bookID = request.getParameter("bookID");
    String nextUserID = request.getParameter("nextUserID");
%>
<c:set var="userList" value="${applicationScope.userList}"></c:set>
<c:set var="userVO" value="${sessionScope.userVO}"></c:set> 
<article id="article">
	<header class="header">
		<nav class="navigator">
			<ul class="menu">
				<li><img src="${contextPath }/image/icon/home.png" alt="start" /></li>
				<li><span><%=dept01 %></span></li>
				<li><span><%=dept02 %></span></li>
				<li><span>Detail</span></li>
			</ul>
		</nav>
		<div class="sharing">
			<ul class="sharingList">
				<li class="sharingItem"><img src="${contextPath }/image/icon/link-2.png" alt="url" class="url" /></li>
				<li class="sharingItem"><img src="${contextPath }/image/icon/qr-code.png" alt="qrcode" class="qrcode" /></li>
				<li class="sharingItem"><img src="${contextPath }/image/icon/icon_line 1.png" alt="line" class="line" /></li>
				<li class="sharingItem"><img src="${contextPath }/image/icon/icon_kakao 1.png" alt="kakao" class="kakao" /></li>
			</ul>
		</div>
	</header>
	<div class="content">
		<div class="contentArea">
			<div class="contentTitle">
				<span>Detail</span>
			</div>
			<div class="contentPlot indented">
				<div id="detail">
					<input type='hidden' name='userID' value='${userVO.getUserID() }' id="userID"/>
<%-- 					<input type='hidden' name='isAvailable' value='${isAvailable }' id="isAvailable"/> --%>
					<input type='hidden' name='bookID' value='<%=bookID %>' id="bookID"/>
					<input type='hidden' name='nextUserID' value='<%=nextUserID %>' id="nextUserID"/>
<!-- 					<bky-book-detail src="JAVA.jpg" title="JAVA" publisher="Sunrise" author="James Gosling"></bky-book-detail> -->
				</div>
			</div>
		</div>
		<div class="contentArea">
			<div class="contentTitle">
				<span>Plot</span>
			</div>
			<div class="contentPlot indented">
				<div class="plotArea">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit. Esse rem in deleniti consequuntur molestias repudiandae veritatis. Numquam iusto eos ratione ut consequatur soluta nam quaerat nisi quia explicabo eligendi officiis amet culpa modi illum reiciendis accusamus aliquid quisquam saepe voluptatibus reprehenderit adipisci officia autem praesentium debitis voluptatem velit pariatur magnam ex eaque! Ducimus illo modi repellendus quia maxime libero assumenda fugiat temporibus quae ipsum vero laborum omnis iure. Ut iusto accusamus est maxime architecto ratione blanditiis dolorem provident consequuntur doloremque tenetur veniam aut necessitatibus sunt in tempore animi enim obcaecati. Enim assumenda sapiente nam alias nemo modi dolores reprehenderit sunt.
				</div>
			</div>
		</div>
		<div class="contentArea">
			<div class="contentTitle">
				<span>Reputation</span>
			</div>
			<div class="contentPlot indented">
				<div class="reputationArea">
					Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequuntur quaerat quisquam eius velit tenetur magni sed libero quidem ipsum dolor quam error odit debitis ratione culpa facere tempore laborum commodi blanditiis molestiae corporis ullam minus. Consequatur quos impedit amet dignissimos optio voluptas reiciendis quo blanditiis quia ipsa temporibus tenetur quae provident eaque rerum sunt odio atque accusamus fugiat quaerat deserunt incidunt enim esse similique voluptatem nisi voluptatum officia! Nostrum perferendis quod quo atque fugit nobis animi quibusdam accusamus sequi quia quae perspiciatis dolore maxime. Quod aliquam tempora officiis minima quasi delectus debitis odit laudantium harum molestias repudiandae laboriosam modi similique facere vero unde aut culpa nulla dolores dicta ex obcaecati alias tempore accusantium rerum numquam magni deleniti aspernatur nihil officia suscipit sit quaerat libero! Quo quos ipsam quae eum tempore possimus quia eos provident atque voluptatum dicta eaque ullam laboriosam similique necessitatibus expedita commodi vero doloremque dolorem repudiandae nihil obcaecati iure corporis dolore asperiores sit nam id animi in beatae labore illum pariatur ipsa neque porro sint accusantium excepturi illo rerum dolores modi veniam ex veritatis eveniet dolor nisi error accusamus officiis blanditiis ea. Aut ad explicabo nisi consequatur iure labore tempore sint quisquam cupiditate eius consequuntur quo debitis suscipit.
				</div>
			</div>
		</div>
	</div>
</article>