<%@ page language="java" contentType="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8"%>
<%@ include file="/jsp/config/setting.jsp" %>
<c:set var="excelList" value="${excelList}"></c:set>
<%
	request.setCharacterEncoding("UTF-8");
	String title = request.getParameter("title");
	String fileName = request.getParameter("fileName");
	
	String encodedFileName = java.net.URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	
	response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName + ";");
	response.setHeader("Content-Description", "JSP Generated Data");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
  <style>
		table {
			width: 100%;
			border-collapse: collapse;
		}
		
		th, td {
			padding: 10px;
			text-align: left;
			border: 1px solid #ccc;
		}
		
		thead th {
			position: sticky;
			top: 0;
			background-color: #f2f2f2;
			z-index: 1;
		}
		
		tbody {
			height: 300px; 
			overflow-y: auto;
		}
  </style>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>RECNUM</th>
				<th>BOOKNM</th>
				<th>PUBLISHER</th>
				<th>AUTHOR</th>
				<th>PUBLISHED_DATE</th>
				<th>CATEGORY</th>
				<th>RENTAL_STATUS_VALUE</th>
				<th>RENTAL_DATE</th>
				<th>RETURN_DATE</th>
				<th>BOOK_APPERANCE_STATUS</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="excelItem" items="${excelList }">
				<tr>
					<td>${excelItem.RECNUM}</td>
					<td>${excelItem.BOOKNM}</td>
					<td>${excelItem.PUBLISHER}</td>
					<td>${excelItem.AUTHOR}</td>
					<td>${excelItem.PUBLISHED_DATE}</td>
					<td>${excelItem.CATG03}</td>
					<td>${excelItem.RENTAL_STATUS_VALUE}</td>
					<td>${excelItem.RENTAL_DATE}</td>
					<td>${excelItem.RENTAL_DUE_DATE}</td>
					<td>${excelItem.BOOK_APPERANCE_STATUS}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>