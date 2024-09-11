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
<c:set var="userList" value="${applicationScope.userList}"></c:set>
<c:set var="userVO" value="${sessionScope.userVO}"></c:set> 
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
				<li class="sharingItem"><img src="${contextPath }/image/icon/link-2.png" alt="url" class="url" /></li>
				<li class="sharingItem"><img src="${contextPath }/image/icon/qr-code.png" alt="qrCode" class="qrCode" /></li>
				<li class="sharingItem"><img src="${contextPath }/image/icon/icon_line 1.png" alt="line" class="line" /></li>
				<li class="sharingItem"><img src="${contextPath }/image/icon/icon_kakao 1.png" alt="kakao" class="kakao" /></li>
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
							<label for="S_RENTAL_DUE_DATE">Return Date</label>
							<input type="date" name="S_RENTAL_DUE_DATE" id="S_RENTAL_DUE_DATE" max="9999-12-31" />						
							<input type="date" name="E_RENTAL_DUE_DATE" id="E_RENTAL_DUE_DATE" max="9999-12-31" />
						</li>
						<li class="filterItem">
							<label for="BOOK_APPERANCE_CODE">Apperance</label>
							<select name="BOOK_APPERANCE_CODE" id="BOOK_APPERANCE_CODE"><option></option></select>
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
					<div class="controller">
						<div class="info">
							<p class="count"><label>the number of book : </label><span></span></p>
						</div>
						<div class="operator">
							<ul class="operatorList">
								<li class="operatorItem"><img src="${contextPath }/image/icon/excel.png" alt="excel" class="excel" /></li>
								<li class="operatorItem"><img src="${contextPath }/image/icon/list.png" alt="list" class="list" /></li>
								<li class="operatorItem"><img src="${contextPath }/image/icon/grid.png" alt="grid" class="grid" /></li>
							</ul>
						</div>
					</div>
					<ul class="bookList">	
<%-- 						<li><bky-book-item src="${contextPath }/image/book/JAVA.jpg" ></bky-book-item></li> --%>
					</ul>
					<input type='hidden' name='userID' value='${userVO.getUserID() }' id="userID"/>
				</div>
				<div class="paging">
					<ul class="pageList">
<!-- 						<li class="pageItem"><a href="javascript:;">&lt;</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">1</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">2</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">3</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">4</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">5</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">6</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">7</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">8</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">9</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">10</a></li> -->
<!-- 						<li class="pageItem"><a href="javascript:;">&gt;</a></li> -->
					</ul>
				</div>
			</div>
		</div>
	</div>
</article>