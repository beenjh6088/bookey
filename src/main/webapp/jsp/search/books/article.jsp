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
		<div class="contentArea">
			<div class="contentTitle">
				<span>Filter</span>
			</div>
			<div class="contentPlot  indented">
				<form action="" class="frmSearchBook" name="frmSearchBook">
					<ul class="filterList">
						<li class="filterItem">
							<label for="BOOKNM">Book Name</label>
							<input type="text" name="BOOKNM" id="BOOKNM"/>						
						</li>
						<li class="filterItem">
							<label for="PUBLISHER">Publisher</label>
							<input type="text" name="PUBLISHER" id="PUBLISHER"/>						
						</li>
						<li class="filterItem">
							<label for="AUTHOR">Author</label>
							<input type="text" name="AUTHOR" id="AUTHOR"/>						
						</li>
						<li class="filterItem">
							<label for="CATGID">Category</label>
							<select name="CATGID" id="CATGID"><option></option></select>						
						</li>
						<li class="filterItem">
							<label for="RENTAL_STATUS_CODE">Rental Status</label>
							<select name="RENTAL_STATUS_CODE" id="RENTAL_STATUS_CODE"><option></option></select>
						</li>
						<li class="filterItem">
							<label for="S_RENTAL_DATE">Rental Date</label>
							<input type="date" name="S_RENTAL_DATE" id="S_RENTAL_DATE" max="9999-12-31" />						
							<input type="date" name="E_RENTAL_DATE" id="E_RENTAL_DATE" max="9999-12-31" />						
						</li>
						<li class="filterItem">
							<label for="S_RENTAL_DUE_DATE">Due Date</label>
							<input type="date" name="S_RENTAL_DUE_DATE" id="S_RENTAL_DUE_DATE" max="9999-12-31" />						
							<input type="date" name="E_RENTAL_DUE_DATE" id="E_RENTAL_DUE_DATE" max="9999-12-31" />
						</li>
						<li class="filterItem">
							<label for="BOOK_STATUS_CODE">Book Status</label>
							<select name="BOOK_STATUS_CODE" id="BOOK_STATUS_CODE"><option></option></select>
						</li>
					</ul>
					<div class="buttons">
						<input type="reset" value="Reset" class="reset" />
						<input type="button" value="Submit" class="submit" />
					</div>
				</form>
			</div>
		</div>
		<div class="contentArea">
			<div class="contentTitle">
				<span>List</span>
			</div>
			<div class="contentPlot indented">
				<div class="books">
					<ul class="bookList">	
<%-- 						<li><bky-book-item src="${contextPath }/image/book/JAVA.jpg" ></bky-book-item></li> --%>
					</ul>
				</div>
			</div>
		</div>
	</div>
</article>