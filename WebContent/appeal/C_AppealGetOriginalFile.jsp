<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.commision_appeal.model.*"%>
<%@page import="com.com.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
request.getAttribute("cVO");
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
		<td>代購標題</td><td>代購會員</td><td>發起時間</td>
		</tr>
	<tr align="center">
		<td>${cVO.com_tit}</td><td>${cVO.mem_id}</td><td>${cVO.pur_s_date}</td>
	</tr>
	<tr align="center">
		<td colspan='3'>代購內容介紹</td>
	</tr>
	<tr align="center">
		<td colspan='3'>${cVO.com_cnt}</td>
		</tr>
	
	
</table>





</body>
</html>