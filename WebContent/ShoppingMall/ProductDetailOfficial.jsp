<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.* , com.Product.model.ProductVO"%>
<%@page import="com.Product.model.ProductService"%>
<%@page import="com.FavoriteProduct.model.*"%>
<%@page import="com.FavoriteProduct.model.FavoriteProductService"%>
<%@page import="com.productFAQ.model.*"%>
<%@page import="com.member.model.*"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<%
	String it_id = request.getParameter("it_id");
	request.setAttribute("it_id", it_id);
	ProductService service = new ProductService();
	ProductVO product = service.findProduct(it_id);
	request.setAttribute("product", product);

	FavoriteProductService favoriteProductService = new FavoriteProductService();
	List<FavoriteProductVO> favoriteProductList = favoriteProductService.getAll();
	request.setAttribute("favoriteProductList", favoriteProductList);
	
	MemVO memVO = (MemVO) session.getAttribute("MemVO");
	request.setAttribute("memVO", memVO);
	
	Map<String, ProductVO> ProductMapAll = null;	
	ProductMapAll = service.getAllProductMapBy_Sts_On_ST001();
	pageContext.setAttribute("ProductMapAll", ProductMapAll);	

%>


<%
	String it_name = product.getIt_name();
%>
<%
	String it_intro = product.getIt_intro();
%>
<%
	Double it_prc = product.getIt_prc();
%>
<%
	Integer it_num = product.getIt_num();
%>




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
	href="${pageContext.request.contextPath }/css/base.css">
<link rel="icon" href="${pageContext.request.contextPath }/img/gogo.png">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/Sean.css">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css"
	rel="stylesheet">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css"
	rel="stylesheet">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>
<!--   自己寫的css   -->
<!-- <link rel="stylesheet" href="css/OOXX.css"> -->
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<script>
function add(href){
	location.reload("<%=request.getContextPath()%>/ShoppingMall/ProductDetail.jsp?it_id=<%=it_id%>");
	}
</script>
<style type="text/css">
.btn-primary {
	font-size: medium;
}

.list-group>a {
	margin-left: 3px;
	font-size: medium;
}

.btn-sm {
	font-size: medium;
}

.thumbnail>img {
	width: 275px;
	height: 275px;
}

.price {
	color: red;
}

.pageform {
	font-size: medium;
}

#prdname {
	color: #337ab7;
}

#prdname:hover {
	color: #23527c;
}

h1, .h1, h2, .h2, h3, .h3, h4, .h4 {
	margin: 0;
}

/* CSS浮動按鈕 */
#fastbtn {
	width: 70px;
	right: 2px;
	position: fixed; /*固定位置定位*/
	top: 40%; /*距離上方 0 像素*/
	z-index: 7; /*重疊時會在其他元素之上*/
}

#fastbtn_div_top {
	padding-left: 15px;
	padding-right: 25px;
	display: none;
}

.fastbtn_icon {
	display: inline;
	margin-left: auto;
	margin-right: auto;
	margin-top: 2px;
	width: 72%;
}

#fastbtn img:hover, #fastbtn img:active {
	width: 82%;
	display: inline;
}

#fastbtn img:active {
	transform: translateY(4px);
}

.badge {
	/* 	  position: relative; */
	/* 	  padding: 3px 6px; */
	/* 	  top: -25px; */
	/* 	  right: 20px; */
	/* 	  border-radius: 15px; */
	margin-left: -16px;
	margin-top: -30px;
	z-index: 40;
	display: inline-block;
	min-width: 10px;
	padding: 3px 7px;
	font-size: 12px;
	font-weight: 700;
	line-height: 1;
	color: #fff;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	background-color: #777;
	border-radius: 10px;
}
</style>

</head>

<body>
	<jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true" />

	<section>
		<c:if test="${cart.map.size()>0}">

			<div id="fastbtn">
				<!-- 浮動區快鍵開始 -->

				<div id="openShoppingcartBtn">
					<a
						href="${pageContext.request.contextPath}/ShoppingMall/ShoppingCart.jsp">
						<img src="${pageContext.request.contextPath}/img/Cart.png"
						class="fastbtn_icon"> <span class="badge"
						style="background-color: #2489ce;">${cart.map.size()}</span>

					</a>
				</div>
			</div>
			<!-- 浮動區快鍵結束 -->
		</c:if>


		<div class="container">
			<!-- 標題 -->
			<div class="row row-topic">
				<div class="title">
					<h1 class="topic">
						官方商店 ><%=it_name%></h1>
				</div>
			</div>
			<!-- 標題 end-->
			<!--  商品x12 -->
			<div class="row">
				<!-- product1 -->
				<div class="col-xs-12 col-sm-3">
					<div class="row">
						<div class="pro-container mask">
							<div class="content">
								<div class="pic pic-relative">
									<a href="#"> <img
										src="${pageContext.request.contextPath }/ProductImageReader?it_id=<%=it_id%>">
									</a>									
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xs-12 col-sm-9">
					<div class="row text-left ML">
						<!--標題開始-->
						<h1 class="MT0">
							<p style="padding-bottom: 25px;" id="ProductIntro">商品簡介</p>
						</h1>


						<h1 class="MT0">
							<p><%=it_name%></p>
						</h1>

						<h1 class="MT0">
							<b class="amountRed">$<fmt:formatNumber value="<%=it_prc%>"
									pattern="#" type="number" /></b>
						</h1>

						<!--標題結束-->
						<!--描述開始-->
						<div class="text-left PT">
							<p class="MB"><%=it_intro%></p>
						</div>
						<!--描述結束-->
						<!--按鈕開始     之後action寫Controller判斷-->





					</div>
				</div>
				<!--按鈕結束-->

			</div>

		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-3"></div>

				<div class="col-xs-12 col-sm-1">
					<div class="btn-group btn-group-lg">
						<c:if test="${product.it_num>0}">
							<a
								href="${pageContext.request.contextPath }/BuyServlet?it_id=<%=it_id%>"
								target="_blank" onclick="add(this)"> <input type=image
								src="${pageContext.request.contextPath }/img/Cart.png"
								class="minjpg2">
							</a>
						</c:if>
						<c:if test="${product.it_num==0}">
							<input type=image
								src="${pageContext.request.contextPath }/img/Cart2.png"
								class="minjpg2">
						</c:if>
					</div>
				</div>

			</div>
		</div>

		<!--商品詳情水平線開始-->
		<div class="container text-left">
			<div class="row">
				<div class="text-left">
					<div class="col-xs-12 col-sm-12 lineHight">
						<div class="topic topic-purchase MT50">
							<div class="btn-group btn-group-xs ML10">
								<a href="#ProductIntro">商品詳情</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<%
double number=Math.random()*130;
%>
		<!--商品詳情水平線結束-->

		<!--商品圖片開始-->
		<div class="container text-left PL0">
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<c:forEach var="ProductMapAll2" items="${ProductMapAll}" begin="3"
						end="3">
					<div class="item">
						<img
							src="${pageContext.request.contextPath }/ProductImageReader?it_id=${ProductMapAll2.value.it_id}">
						<p class="MT50" style="padding-right: 25px;">${ProductMapAll2.value.it_intro}</p>
					</div>
					</c:forEach>
				</div>
			</div>


	
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<c:forEach var="ProductMapAll2" items="${ProductMapAll}" begin="4"
						end="4">
					<div class="item">
						<img
							src="${pageContext.request.contextPath }/ProductImageReader?it_id=${ProductMapAll2.value.it_id}">
						<p class="MT50" style="padding-right: 25px;">${ProductMapAll2.value.it_intro}</p>
					</div>
					</c:forEach>
				</div>
			</div>

			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<c:forEach var="ProductMapAll2" items="${ProductMapAll}" begin="2"
						end="2">
					<div class="item">
						<img
							src="${pageContext.request.contextPath }/ProductImageReader?it_id=${ProductMapAll2.value.it_id}">
						<p class="MT50" style="padding-right: 25px;">${ProductMapAll2.value.it_intro}</p>
					</div>
					</c:forEach>
				</div>
			</div>

		</div>
	
		<!--商品圖片結束-->

		<!--商品描述開始-->


		<!--商品描述結束-->


	</section>
	<%@ include file="/Footer.jsp"%>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>