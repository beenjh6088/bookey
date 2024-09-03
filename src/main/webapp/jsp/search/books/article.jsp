<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String filePath = request.getServletPath();
    String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
%>
<article id="article">
	<%= fileName %>

</article>