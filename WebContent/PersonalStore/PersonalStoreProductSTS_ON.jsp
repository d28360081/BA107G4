<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.ProductVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.PersonalStore.model.*"%>
<html lang="">



<%
MemVO memVO2 = (MemVO) session.getAttribute("MemVO");
request.setAttribute("memVO2", memVO2);
String mem_id2=memVO2.getMem_id();

PersonalStoreVO MempersonalStore=new PersonalStoreVO();
PersonalStoreService  personalStoreService=new PersonalStoreService();
MempersonalStore=personalStoreService.findByMemId(mem_id2);
request.setAttribute("MempersonalStore", MempersonalStore);


	String st_id=MempersonalStore.getSt_id();
	ProductService service = new ProductService();
	Map<String, ProductVO> ProductMap = service.getAllProductMapBy_Sts_On_AndStore(st_id);
	pageContext.setAttribute("ProductMap", ProductMap);
	
	String activeOnProduct = "activeOnProduct";
	request.setAttribute("activeOnProduct", activeOnProduct);
	
	
%>






<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Gogo shop</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/Sean.css">
<link rel="icon" href="${pageContext.request.contextPath }/img/gogo.png">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css"
	rel="stylesheet">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css"
	rel="stylesheet">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>
<script>
	function deleteProduct(form) {
		
		var b = window.confirm("您確定刪除嗎？");
		if (b) {			
			return true;		
		} else {
			alertify.error("取消刪除");
			return false;			
		}

	}
	function ProductOntoOff(form) {
		var b = window.confirm("您確定移除嗎？");
		if (b) {			
			return true;
		} else {
			alertify.error("取消移除");
			return false;
		}

	}
	
</script>
<!--   自己寫的css   -->
<!-- <link rel="stylesheet" href="css/OOXX.css"> -->
<!--[if lt IE 9]>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	 <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
	<section>
		<!-- 從這裡開始寫吧 -->

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-5 ">
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath }/index.html">首頁</a></li>
						<li>商品管理</li>						
					</ol>
				</div>
				<div class="col-xs-12 col-sm-5"></div>
			</div>

		</div>

		<div class="container">
			<%@include file="PersonalStoreLeftBar.jsp"%>



			<div class="col-xs-12 col-sm-9">

				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#tab1"
						aria-controls="tab1" role="tab" data-toggle="tab">商品管理</a></li>
				</ul>

				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab1">

						<div role="tabpanel" class="tab-pane" id="tab1">
							<%@ include file="/ShoppingMall/ShoppingMallPage1.file"%>
							<c:forEach var="ProductMap" items="${ProductMap}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<div class="container-fluid">
									<div class="row">
										<div class="col-xs-12 col-sm-4">
											<b>${ProductMap.value.it_name}</b>

											<div class="pic pic-relative">
												<a
													href="${pageContext.request.contextPath}/PersonalStore/EditProduct.jsp?it_id=${ProductMap.value.it_id}&whichPage=<%=whichPage%>">
													<img
													src="${pageContext.request.contextPath }/ProductImageReader?it_id=${ProductMap.value.it_id}"
													alt="" class="img100">
												</a> <a
													href="${pageContext.request.contextPath}/PersonalStore/EditProduct.jsp?it_id=${ProductMap.value.it_id}&whichPage=<%=whichPage%>"
													class="right-top"> <input type=image
													src="${pageContext.request.contextPath}/img/edit.png"
													class="minjpg2">
												</a>
											</div>

											<div>
												<b class="amountRed">$<fmt:formatNumber
														value="${ProductMap.value.it_prc}" pattern="#"
														type="number" /></b>
											</div>
											剩餘商品數量:${ProductMap.value.it_num}<br>
											商品狀態:${ProductMap.value.it_sts}<br>
											商品分類:${ProductMap.value.it_cate}

											<div class="container-fluid btn-buy">
												<div class="row">
													
													<div class="col-xs-12 col-sm-12">
														<form
															action="${pageContext.request.contextPath }/EditProductServlet"
															method="post" onsubmit="return ProductOntoOff(this)">
															<input type="submit" class="btn btn-info" value="移除商品">
															<input type="hidden" name="action"
																value="EditProductOntoNothing"> <input type="hidden"
																name="it_id" value="${ProductMap.value.it_id}">
															<input type="hidden" name="whichPage"
																value="<%=whichPage%>">
														</form>
													</div>
												</div>
											</div>
										</div>
							</c:forEach>
						</div>

						<%@ include file="/ShoppingMall/ShoppingMallPage2.file"%>

					</div>

				</div>
			</div>

		</div>
		</div>

	</section>
	<%@ include file="/Footer.jsp"%>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>