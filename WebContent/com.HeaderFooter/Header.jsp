<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<nav class="navbar navbar-default navbar-default-custom"
		role="navigation">
		<div class="container container-top">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath }/res/logo.svg" alt=""></a>
			</div>
			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- 左選單 -->
				<ul class="nav navbar-nav nav-custom">
					<li><a href="${pageContext.request.contextPath }/ShoppingMallForOfficial.jsp" class="hvr-underline-from-left">官方商店</a></li>
					<li><a href="#" class="hvr-underline-from-left">代購專區</a></li>
					<li><a href="#" class="hvr-underline-from-left"> 揪團專區</a></li>
					<li><a href="#" class="hvr-underline-from-left">文章專區</a></li>
					<li><a href="#" class="hvr-underline-from-left">聯絡客服</a></li>
				</ul>
				<!-- 搜尋表單 -->
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="輸入關鍵字">
					</div>
					<button type="submit" class="btn btn-default btn-go ">搜尋</button>
				</form>
				<!-- 會員下拉選單 -->
				<ul class="nav navbar-nav navbar-right">
			<c:if test="${cart.map.size()>0}">
				
				<div id="fastbtn">
					<!-- 浮動區快鍵開始 -->

					<div id="openShoppingcartBtn">
						<a href="${pageContext.request.contextPath}/ShoppingCart.jsp">
							<img src="${pageContext.request.contextPath}/img/Cart.png"
							class="fastbtn_icon"> <span class="badge"
							style="background-color: #2489ce;">${cart.map.size()}</span>

						</a>
					</div>
				</div>
				<!-- 浮動區快鍵結束 -->				
				</c:if>
                
                
					<li><a href="#">登出</a></li>
				</ul>
			</div>
			<!-- 手機隱藏選單區結束 -->
		</div>
	</nav>	
	
</body>
</html>