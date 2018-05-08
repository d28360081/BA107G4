<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.ProductVO"%>
<%@page import="com.FavoriteProduct.model.*"%>
<%@page import="com.FavoriteProduct.model.FavoriteProductService"%>
<%@page import="com.Product.model.*"%>
<%@page import="java.util.*, java.util.Map.Entry"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="com.member.model.*"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="">

<%
	Cart cart = (Cart) request.getSession().getAttribute("cart");
%>







<%
	Map<String, ProductVO> ProductMapAll = null;

	if (request.getAttribute("ProductMap") == null) {
		ProductService service = new ProductService();
		ProductMapAll = service.getAllProductMapBy_Sts_On_ST001();
		pageContext.setAttribute("ProductMapAll", ProductMapAll);	
	
	}
%>


<%
	ProductService service = new ProductService();
	String it_cate = request.getParameter("it_cate");
	String type = request.getParameter("type");
	String from = request.getParameter("from");
	Map<String, ProductVO> ProductMap = service.findBy_Cate_Sts_OnProduct_ST001(it_cate);
	request.setAttribute("ProductMap", ProductMap);

	request.setAttribute("type", type);
	request.setAttribute("it_cate", it_cate);
	request.setAttribute("from", from);
	request.setAttribute("it_cateActive", it_cate);

	String it_cateActive = (String) request.getAttribute("it_cateActive");
%>







<%
	String it_id = (String) session.getAttribute("it_id");
%>

<%
	FavoriteProductService favoriteProductService = new FavoriteProductService();
	List<FavoriteProductVO> favoriteProductList = favoriteProductService.getAll();
	HashMap<String, Integer> favCount = new HashMap<String, Integer>();
	for (FavoriteProductVO fav : favoriteProductList) {
		String It_id = fav.getIt_id();
		if (favCount.containsKey(It_id)) {
			int count = favCount.get(It_id) + 1;
			favCount.put(It_id, count);
		} else {
			favCount.put(It_id, 1);
		}	
	}			
	
	request.setAttribute("favCount", favCount);
	
	request.setAttribute("favoriteProductList", favoriteProductList);

	HashMap<String, ProductVO> ProductSearchMap = (HashMap) request.getAttribute("ProductSearchMap");
	request.setAttribute("ProductSearchMap", ProductSearchMap);

	MemVO memVO = (MemVO) session.getAttribute("MemVO");
	request.setAttribute("memVO", memVO);
	

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

<!--   自己寫的css   -->
<!-- <link rel="stylesheet" href="css/OOXX.css"> -->
<!--[if lt IE 9]>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script>
function add(href){
	location.reload("<%=request.getContextPath()%>/ShoppingMall/ShoppingMall.jsp");
}

function LoginCheck(form){
	<c:if test="${memVO eq null}">			
	    $('#modal-id').modal('show');
	    return false
	</c:if>	
	    
	<c:if test="${memVO ne null}">	
	    return true
	</c:if>	
	    
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

<style>
    .a-ellipsis {
display : inline-block;
  overflow : hidden;
  text-overflow : ellipsis;
  white-space : nowrap;
  width : 100%;
    }
    
    
  
  </style>

</head>


<body>
	<jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true" />

	<section>
		<!-- 從這裡開始寫吧 -->


		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-3">
					<div class="list-group MB20">
						<a class="list-group-item list-group-item-info">官方商店</a> <a
							href="<%=request.getContextPath()%>/ShoppingMall/ShoppingMallForOfficial.jsp"
							class="list-group-item ${(ProductMapAll!=null)?'active':''} ">所有商品</a>
						<a
							href="<%=request.getContextPath()%>/ProductOfficalCategoryController?type=snack&it_cate=snack&from=from"
							class="list-group-item ${(it_cateActive=='snack')?'active':''}">零食點心區</a>
						<a
							href="<%=request.getContextPath()%>/ProductOfficalCategoryController?type=sport&it_cate=sport&from=from"
							class="list-group-item ${(it_cateActive=='sport')?'active':''}">休閒運動區</a>
						<a
							href="<%=request.getContextPath()%>/ProductOfficalCategoryController?type=tripleC&it_cate=tripleC&from=from"
							class="list-group-item ${(it_cateActive=='tripleC')?'active':''}">3C周邊區</a>
					</div>
					<div class="list-group MB20">
						<a
							href="<%=request.getContextPath()%>/ProductOfficalCategoryController?type=lifeLiving&it_cate=lifeLiving&from=from"
							class="list-group-item ${(it_cateActive=='lifeLiving')?'active':''}">居家生活區</a>


						<a
							href="<%=request.getContextPath()%>/ProductOfficalCategoryController?type=workOut&it_cate=workOut&from=from"
							class="list-group-item ${(it_cateActive=='workOut')?'active':''}">運動健身區</a>
						<a
							href="<%=request.getContextPath()%>/ProductOfficalCategoryController?type=Game&it_cate=Game&from=from"
							class="list-group-item ${(it_cateActive=='Game')?'active':''}">電玩遊戲區</a>
					</div>
					<div class="list-group MB20">
						<a
							href="<%=request.getContextPath()%>/ProductOfficalCategoryController?type=furniture&it_cate=furniture&from=from"
							class="list-group-item ${(it_cateActive=='furniture')?'active':''}">傢俱寢飾區</a>
						<a
							href="<%=request.getContextPath()%>/ProductOfficalCategoryController?type=Women_Clothing&it_cate=Women_Clothing&from=from"
							class="list-group-item ${(it_cateActive=='Women_Clothing')?'active':''}">女性服飾區</a>
						<a
							href="<%=request.getContextPath()%>/ProductOfficalCategoryController?type=toyForJoy&it_cate=toyForJoy&from=from"
							class="list-group-item ${(it_cateActive=='toyForJoy')?'active':''}">玩具童裝區</a>
					</div>
				</div>



				<%
					if (pageContext.getAttribute("ProductMapAll") != null
							&& ((HashMap) pageContext.getAttribute("ProductMapAll")).size() > 0
							&& request.getAttribute("ProductSearchMap") == null) {
				%>
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
				<div class="col-xs-12 col-sm-9">
					<%@ include file="ShoppingMalALLpage1.file"%>
					<div class="col-xs-12 col-sm-12">
						<a
							href="${pageContext.request.contextPath}/ShoppingMall/ShoppingMall.jsp">會員商店</a>
						<form action="${pageContext.request.contextPath}/SerachProduct"
							method="post" class="navbar-form navbar-center" role="search">
							<div class="form-group">
								<input type="text" name="It_name" class="form-control"
									placeholder="搜尋商品"> <input type="hidden"
									name="whichPage" value="<%=whichPage%>"> <input
									type="hidden" name="action" value="SerachFromAllOfficial">
								<button type="submit" class="btn btn-default btn-go ">搜尋</button>

							</div>
						</form>
					</div>
					<c:forEach var="ProductMapAll2" items="${ProductMapAll}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<div class="container-fluid">
							<div class="row">
								<div class="col-xs-12 col-sm-4">
									<b class="a-ellipsis">${ProductMapAll2.value.it_name}</b>
									<div class="pic pic-relative">
										<a
											href="${pageContext.request.contextPath}/ShoppingMall/ProductDetailOfficial.jsp?it_id=${ProductMapAll2.value.it_id}">
											<img
											src="${pageContext.request.contextPath}/ProductImageReader?it_id=${ProductMapAll2.value.it_id}"
											alt="" class="img100">
										</a>


									</div>

									<div>
										<b class="amountRed">$<fmt:formatNumber
												value="${ProductMapAll2.value.it_prc}" pattern="#"
												type="number" /></b>
									</div>
									剩餘商品數量:${ProductMapAll2.value.it_num}<br>

									<c:if test="${ProductMapAll2.value.it_num>0}">
										<font color=#3ab2d9>現正熱賣中</font>
									</c:if>
									<c:if test="${ProductMapAll2.value.it_num==0}">
										<font color=red>缺貨中</font>
									</c:if>

									<div class="container-fluid btn-buy">
										<div class="row">
											<div class="col-xs-12 col-sm-6">
												<div class="row">
													<c:if test="${ProductMapAll2.value.it_num>0}">
														<a
															href="${pageContext.request.contextPath}/BuyServlet?it_id=${ProductMapAll2.value.it_id}"
															target="_blank" onclick="add(this)"> <input
															id="addCart" type=image
															src="${pageContext.request.contextPath }/img/Cart.png"
															class="minjpg2">
														</a>
													</c:if>
													<c:if test="${ProductMapAll2.value.it_num==0}">
														<input type=image
															src="${pageContext.request.contextPath }/img/Cart2.png"
															class="minjpg2">
													</c:if>
												</div>
											</div>

											<div class="col-xs-12 col-sm-6">
												<div class="row">
													<form
														action="${pageContext.request.contextPath }/FavoriteProductController"
														method="post" onsubmit="return LoginCheck(this)">
														<input type=image
															src="${pageContext.request.contextPath }/img/heart2.png"
															class="minjpg2"> <input type="hidden"
															name="action" value="addFavoriteProductOfficial">
														<input type="hidden" name="it_id"
															value="${ProductMapAll2.value.it_id}"> <input
															type="hidden" name="whichPage" value="<%=whichPage%>">
														<input type="hidden" name="mem_id" value="${memVO.mem_id}">
													</form>

													<c:if
														test="${favCount.containsKey(ProductMapAll2.value.it_id)}">
														收藏(${favCount.get(ProductMapAll2.value.it_id)})
													</c:if>		
													<c:if
														test="${!favCount.containsKey(ProductMapAll2.value.it_id)}">
														收藏(0)
													</c:if>		


												</div>
											</div>



										</div>
									</div>
								</div>
					</c:forEach>
				</div>
			</div>
		</div>

		<%@ include file="ShoppingMallALLPage2.file"%>

		<%
			}

			else if ((pageContext.getAttribute("ProductMapAll") != null
					&& ((HashMap) pageContext.getAttribute("ProductMapAll")).size() > 0
					&& request.getAttribute("ProductSearchMap") != null)) {
		%>
		<%
			int rowsPerPage = 9; //每頁的筆數    
				int rowNumber = 0; //總筆數
				int pageNumber = 0; //總頁數      
				int whichPage = 1; //第幾頁
				int pageIndexArray[] = null;
				int pageIndex = 0;
		%>

		<%
			rowNumber = ProductSearchMap.size();
				if (rowNumber % rowsPerPage != 0)
					pageNumber = rowNumber / rowsPerPage + 1;
				else
					pageNumber = rowNumber / rowsPerPage;

				pageIndexArray = new int[pageNumber];
				for (int i = 1; i <= pageIndexArray.length; i++)
					pageIndexArray[i - 1] = i * rowsPerPage - rowsPerPage;
		%>

		<%
			try {
					whichPage = Integer.parseInt(request.getParameter("whichPage"));
					pageIndex = pageIndexArray[whichPage - 1];
				} catch (NumberFormatException e) { //第一次執行的時候
					whichPage = 1;
					pageIndex = 0;
				} catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數
					if (pageNumber > 0) {
						whichPage = pageNumber;
						pageIndex = pageIndexArray[pageNumber - 1];
					}
				}
		%>
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
		<div class="col-xs-12 col-sm-9">
			<div class="col-xs-12 col-sm-12">
				<a
					href="${pageContext.request.contextPath}/ShoppingMall/ShoppingMall.jsp">會員商店</a>
				<form action="${pageContext.request.contextPath}/SerachProduct"
					method="post" class="navbar-form navbar-center" role="search">
					<div class="form-group">
						<input type="text" name="It_name" class="form-control"
							placeholder="搜尋商品"> <input type="hidden" name="whichPage"
							value="<%=whichPage%>"> <input type="hidden"
							name="action" value="SerachFromAllOfficial">
						<button type="submit" class="btn btn-default btn-go ">搜尋</button>
					</div>
				</form>
			</div>
			<c:forEach var="ProductSearch" items="${ProductSearchMap}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<b class="a-ellipsis">${ProductSearch.value.it_name}</b>
							<div class="pic pic-relative">
								<a
									href="${pageContext.request.contextPath}/ShoppingMall/ProductDetailOfficial.jsp?it_id=${ProductSearch.value.it_id}&whichPage=<%=whichPage%>">
									<img
									src="${pageContext.request.contextPath}/ProductImageReader?it_id=${ProductSearch.value.it_id}"
									alt="" class="img100">
								</a>


							</div>

							<div>
								<b class="amountRed">$<fmt:formatNumber
										value="${ProductSearch.value.it_prc}" pattern="#"
										type="number" /></b>
							</div>
							剩餘商品數量:${ProductSearch.value.it_num}<br>

							<c:if test="${ProductSearch.value.it_num>0}">
								<font color=#3ab2d9>現正熱賣中</font>
							</c:if>
							<c:if test="${ProductSearch.value.it_num==0}">
								<font color=red>缺貨中</font>
							</c:if>

							<div class="container-fluid btn-buy">
								<div class="row">
									<div class="col-xs-12 col-sm-6">
										<div class="row">
											<c:if test="${ProductSearch.value.it_num>0}">
												<a
													href="${pageContext.request.contextPath}/BuyServlet?it_id=${ProductSearch.value.it_id}"
													target="_blank" onclick="return add(this)"> <input
													id="addCart" type=image
													src="${pageContext.request.contextPath }/img/Cart.png"
													class="minjpg2">
												</a>
											</c:if>
											<c:if test="${ProductSearch.value.it_num==0}">
												<input type=image
													src="${pageContext.request.contextPath }/img/Cart2.png"
													class="minjpg2">
											</c:if>
										</div>
									</div>

									<div class="col-xs-12 col-sm-6">
										<div class="row">
											<form
												action="${pageContext.request.contextPath }/FavoriteProductController"
												method="post" onsubmit="return LoginCheck(this)">
												<input type=image
													src="${pageContext.request.contextPath }/img/heart2.png"
													class="minjpg2"> <input type="hidden" name="action"
													value="addFavoriteProduct"> <input type="hidden"
													name="it_id" value="${ProductSearch.value.it_id}">
												<input type="hidden" name="whichPage" value="<%=whichPage%>">
												<input type="hidden" name="mem_id" value="${memVO.mem_id}">
											</form>

											<c:if
														test="${favCount.containsKey(ProductSearch.value.it_id)}">
														收藏(${favCount.get(ProductSearch.value.it_id)})
													</c:if>		
													<c:if
														test="${!favCount.containsKey(ProductSearch.value.it_id)}">
														收藏(0)
													</c:if>		

										</div>
									</div>


								</div>
							</div>
						</div>
			</c:forEach>
		</div>
		<%@ include file="ShoppingMallALLPage2.file"%>

		<%
			} else {
		%>
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
		<div class="col-xs-12 col-sm-9">
			<a
				href="${pageContext.request.contextPath}/ShoppingMall/ShoppingMall.jsp">會員商店</a>
			<%@ include file="ShoppingMallPage1.file"%>
			<c:forEach var="ProductMap2" items="${ProductMap}"
				begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<div class="container-fluid">
					<div class="row">
						<div class="col-xs-12 col-sm-4">
							<b class="a-ellipsis">${ProductMap2.value.it_name}</b>
							<div class="pic pic-relative">
								<a
									href="${pageContext.request.contextPath}/ShoppingMall/ProductDetailOfficial.jsp?it_id=${ProductMap2.value.it_id}">
									<img
									src="${pageContext.request.contextPath}/ProductImageReader?it_id=${ProductMap2.value.it_id}"
									alt="" class="img100">
								</a>



							</div>

							<div>
								<b class="amountRed">$<fmt:formatNumber
										value="${ProductMap2.value.it_prc}" pattern="#" type="number" /></b>
							</div>
							剩餘商品數量:${ProductMap2.value.it_num}<br>
							<c:if test="${ProductMap2.value.it_num>0}">
								<font color=#3ab2d9>現正熱賣中</font>
							</c:if>
							<c:if test="${ProductMap2.value.it_num==0}">
								<font color=red>缺貨中</font>
							</c:if>
							<div class="container-fluid btn-buy">
								<div class="row">
									<div class="col-xs-12 col-sm-6">
										<div class="row">
											<c:if test="${ProductMap2.value.it_num>0}">
												<a
													href="${pageContext.request.contextPath}/BuyServlet?it_id=${ProductMap2.value.it_id}"
													target="_blank" onclick="add(this)"> <input
													id="addCart" type=image
													src="${pageContext.request.contextPath }/img/Cart.png"
													class="minjpg2">
												</a>
											</c:if>
											<c:if test="${ProductMap2.value.it_num==0}">
												<input type=image
													src="${pageContext.request.contextPath }/img/Cart2.png"
													class="minjpg2">
											</c:if>
										</div>
									</div>

									<div class="col-xs-12 col-sm-6">
										<div class="row">
											<form
												action="${pageContext.request.contextPath }/FavoriteProductController"
												method="post" onsubmit="return LoginCheck(this)">
												<input type=image
													src="${pageContext.request.contextPath }/img/heart2.png"
													class="minjpg2"> <input type="hidden" name="action"
													value="addFavoriteProductFromCategoryOfficial"> <input
													type="hidden" name="it_id"
													value="${ProductMap2.value.it_id}"> <input
													type="hidden" name="whichPage" value="<%=whichPage%>">
												<input type="hidden" name="type" value="<%=type%>">
												<input type="hidden" name="it_cate" value="<%=it_cate%>">
												<input type="hidden" name="from" value="<%=from%>">
												<input type="hidden" name="mem_id" value="${memVO.mem_id}">
											</form>
											<c:if
														test="${favCount.containsKey(ProductMap2.value.it_id)}">
														收藏(${favCount.get(ProductMap2.value.it_id)})
													</c:if>		
													<c:if
														test="${!favCount.containsKey(ProductMap2.value.it_id)}">
														收藏(0)
													</c:if>		
										</div>
									</div>


								</div>
							</div>
						</div>
			</c:forEach>
		</div>
		<%@ include file="ShoppingMallPage2.file"%>

		<%
			}
		%>




	</section>



	<%@ include file="/Footer.jsp"%>








	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>