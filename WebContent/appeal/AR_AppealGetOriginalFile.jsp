<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.article_appeal.model.*"%>
<%@page import="com.article.model.*"%>
<%@page import="com.articlereply.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
request.getAttribute("aVO");
request.getAttribute("artreVO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>morinfo</title>
</head>
<body>

<table border='1'>
	<tr align="center">
		<td>回文會員</td><td>回文編號</td><td>回文時間</td>
	</tr>
	<tr align="center">	
		<td>${artreVO.mem_id}</td><td>${artreVO.re_id}</td><td>${artreVO.re_date}</td>
	</tr>
	<tr align="center">
		<td colspan='3'>回文內容</td>
	</tr>
	<tr align="center">
		<td colspan='3' style='height:50pt'>${artreVO.re_cnt}</td>
	</tr>
	<tr align="center">
		<td>原始文章標題</td><td>發文會員</td><td>發文時間</td>
		</tr>
	<tr align="center">
		<td>${aVO.art_tlt}</td><td>${aVO.mem_id}</td><td>${aVO.art_date}</td>
	</tr>
	<tr align="center">
		<td colspan='3'>文章內容</td>
	</tr>
	<tr align="center">
		<td colspan='3'>${aVO.art_cnt}</td>
		</tr>
	
</table>





</body>
</html>