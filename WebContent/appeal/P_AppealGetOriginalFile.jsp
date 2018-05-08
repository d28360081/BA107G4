<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.product_appeal.model.*"%>
<%@page import="com.Product.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
request.getAttribute("pVO");
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
		<td>商品標題</td><td>商品價格</td><td>商品數量</td>
		</tr>
	<tr align="center">
		<td>${pVO.it_name}</td><td>${pVO.it_prc}</td><td>${pVO.it_num}</td>
	</tr>
	<tr align="center">
		<td colspan='3'>商品內容介紹</td>
	</tr>
	<tr align="center">
		<td colspan='3'>${pVO.it_intro}</td>
		</tr>
	
	
</table>





</body>
</html>