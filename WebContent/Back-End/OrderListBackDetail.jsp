<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.* , com.Product.model.ProductVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.StoreOrder.model.*"%>
<%@ page import="com.Product.model.*"%>
<%@ page import="com.StoreReceiptDetail.model.*"%>
<%@ page import="com.PersonalStore.model.*"%>

<%
	request.getAttribute("storeReceiptDetailList");

	request.getAttribute("storeOrderList");

	request.getAttribute("productMap");
%>

<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Gogo shop</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Base2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/Sean.css">
<link rel="icon" href="${pageContext.request.contextPath }/img/gogo.png">
<style type="text/css">
</style>


<!--   自己寫的css   -->
<!-- <link rel="stylesheet" href="css/OOXX.css"> -->
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<style>
table#table-1 {
	background-color: #3ab2d9;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h3 {
	color: black;
	display: block;
	margin: 5px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 1px;
	text-align: center;
}
</style>


</head>
<body bgcolor='white'>
	<c:forEach var="storeOrder" items="${storeOrderList}">
		<c:forEach var="storeReceiptDetail" items="${storeReceiptDetailList}">
			<c:forEach var="product" items="${productMap}">
				<c:if
					test="${storeReceiptDetail.rec_id==storeOrder.rec_id&&storeReceiptDetail.it_id==product.value.it_id}">

					<table class="myTable">
						<tr>
							<th class="center  mytable"></th>
							<th class="center  mytable">訂單編號</th>
							<th class="center  mytable">商品編號</th>
							<th class="center  mytable">商品名稱</th>
							<th class="center  mytable">商品單價</th>
							<th class="center  mytable">商品數量</th>
							<th class="center  mytable">小計</th>
						</tr>

						<tr>
							<td class="center  mytable"><img
								src="${pageContext.request.contextPath}/ProductImageReader?it_id=${storeReceiptDetail.it_id}"
								alt="" class="img100"></td>
							<td class="center  mytable">${storeReceiptDetail.rec_id}</td>
							<td class="center  mytable">${storeReceiptDetail.it_id}</td>

							<td class="center  mytable">${product.value.it_name}</td>

							<td class="center  mytable">$<fmt:formatNumber
									value="${product.value.it_prc}" pattern="#" type="number" /></td>
							<td class="center  mytable">${storeReceiptDetail.it_num}</td>
							<td class="center  mytable"><font color="red">$<fmt:formatNumber
										value="${product.value.it_prc*storeReceiptDetail.it_num}"
										pattern="#" type="number" /></font></td>
						</tr>

					</table>
					<div class="modal-footer">


					

						<c:if
							test="${storeOrder.rec_dlv_sts != '已完成訂單'&&storeOrder.rec_dlv_sts!='訂單取消'&&storeOrder.rec_dlv_sts!='已出貨'}">
							<form
								action="${pageContext.request.contextPath}/StoreReceiptDetailController"
								method="post" onsubmit="return CancelOrder(this)">
								<div class="btn-group btn-group-lg">
									<input type="submit" value="取消訂單" class="btn btn-danger">
									<input type="hidden" name="action"
										value="CancelOrderFromOrderListBack"> <input type="hidden"
										name="rec_id" value="${storeReceiptDetail.rec_id}">
								</div>
							</form>
						</c:if>

					</div>
				</c:if>
				
			</c:forEach>
		</c:forEach>
	</c:forEach>
	
	<div class="modal-footer">
		<div class="btn-group btn-group-lg">
			<input type="button" data-dismiss="modal" value="離開"
				class="btn btn-info">
		</div>
	</div>


	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>


</html>