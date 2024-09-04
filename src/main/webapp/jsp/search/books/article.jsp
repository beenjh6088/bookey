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
%>
<article id="article">
	<header class="header">
		<nav class="navigator">
			<ul class="menu">
				<li><img src="${contextPath }/image/icon/home.png" alt="start" /></li>
				<li><span><%=dept01 %></span></li>
				<li><span><%=dept02 %></span></li>
			</ul>
		</nav>
		<div class="sharing">
			<ul class="sharingList">
				<li class="sharingItem"><img src="${contextPath }/image/icon/link-2.png" alt="url" /></li>
				<li class="sharingItem"><img src="${contextPath }/image/icon/qr-code.png" alt="qrCode" /></li>
				<li class="sharingItem"><img src="${contextPath }/image/icon/icon_line 1.png" alt="line" /></li>
				<li class="sharingItem"><img src="${contextPath }/image/icon/icon_kakao 1.png" alt="kakao" /></li>
			</ul>
		</div>
	</header>
	<div class="content">
		
	</div>
</article>