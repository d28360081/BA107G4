<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.ProductVO"%>
<%@page import="com.news.model.*"%>
<%@page import="java.util.*, java.util.Map.Entry"%>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Gogo shop</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/base.css">
<link rel="icon" href="img/gogo.png">

<link rel="stylesheet" href="css/style.css">
<!--  animate -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.4.0/animate.min.css">
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>


 <style>
    .a-ellipsis {
      width: 100%;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
  
  </style>
<!--  hover -->
<!--   <link href="css/hover.css" rel="stylesheet" media="all"> -->
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<c:if test="${not empty errMsg}">
	<script>
    $(document).ready(function (){
    <c:forEach var="msg" items="${errMsg}">
    window.alert('<c:out value="${msg}"/>');
    </c:forEach>
    });
    </script>
</c:if>
</head>
<% 
Map<String, ProductVO> ProductMapAll = null;
ProductService service = new ProductService();
ProductMapAll = service.getAllProductMapBy_Sts_On_ST001();
pageContext.setAttribute("ProductMapAll", ProductMapAll);	
%>
<body>
	<%@ include file="/com.HeaderFooter/FrontHeader.jsp"%>
	<div class="gradient"></div>
	<div id="carousel-id" class="carousel slide" data-ride="carousel">
		<!-- 幻燈片小圓點區 -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-id" data-slide-to="0" class=""></li>
			<li data-target="#carousel-id" data-slide-to="1" class=""></li>
			<li data-target="#carousel-id" data-slide-to="2" class="active"></li>
		</ol>
		<!-- 幻燈片主圖區 -->
		<div class="carousel-inner">
            
            <div class="item">                 
                 <a href ="<%=request.getContextPath()%>/pmt/pmt.do?action=checkPmtDetail&pmt_id=PMT004">
                 	<img src="<%=request.getContextPath()%>/com.pmt.view/img/PROMOTION/PMT004.jpg" alt="" width= 100% >
                 </a>
                <div class="container">
                    <div class="carousel-caption">
                        <p><a class="btn btn-lg btn-primary btn-gogo hvr-sweep-to-right" 
                        	href="<%=request.getContextPath()%>/com.pmt.view/pmtFont_Index.jsp" role="button" role="button">查看更多</a></p>
                    </div>
                </div>
            </div>
            <div class="item ">
            	<a href ="<%=request.getContextPath()%>/pmt/pmt.do?action=checkPmtDetail&pmt_id=PMT005">
                	<img src="<%=request.getContextPath()%>/com.pmt.view/img/PROMOTION/PMT005.jpg" alt="" width= 100%>
                </a>
                <div class="container">
                    <div class="carousel-caption">
                        <p><a class="btn btn-lg btn-primary btn-gogo hvr-sweep-to-right" 
                        	href="<%=request.getContextPath()%>/com.pmt.view/pmtFont_Index.jsp" role="button">查看更多</a></p>
                    </div>
                </div>
            </div>
            <div class="item active">
            	<a href ="<%=request.getContextPath()%>/pmt/pmt.do?action=checkPmtDetail&pmt_id=PMT006">
                 	<img src="<%=request.getContextPath()%>/com.pmt.view/img/PROMOTION/PMT006.jpg" alt="" width= 100%>
                </a>
                <div class="container">
                    <div class="carousel-caption">
                        <p><a class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right" 
                        		href="<%=request.getContextPath()%>/com.pmt.view/pmtFont_Index.jsp" 
                        		role="button" role="button">看更多</a></p>
                    </div>
                </div>
            </div>
        </div>
		<!-- 上下頁控制區 -->
		<!--  <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
        <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a> -->
	</div>
	<!-- 熱 -->
	<section>				
		<div class="container">		
				<!-- 標題 -->
				<div class="row row-topic">
					<div class="title">
						<h1 class="topic">官方商店</h1>
					</div>
					<div class="more">
						<a
						target="_blank"	href="${pageContext.request.contextPath}/ShoppingMall/ShoppingMallForOfficial.jsp">更多商品</a>
					</div>
				</div>
				<!-- 標題 end-->
				<!--  商品x12 -->		
						
						
						
				<div class="row">
				<c:forEach var="ProductMapAll2" items="${ProductMapAll}" begin="1"
				end="6">
					<div class="col-xs-12 col-sm-2">
						<div class="row">
							<div class="pro-container mask">
								<div class="content">
									<a
										href="${pageContext.request.contextPath}/ShoppingMall/ProductDetailOfficial.jsp?it_id=${ProductMapAll2.value.it_id}">
										<img
										src="${pageContext.request.contextPath}/ProductImageReader?it_id=${ProductMapAll2.value.it_id}"
										alt="" class="img100">
									</a>
								</div>
							</div>
							<div class="pro-detail">
								<p class="pro-name a-ellipsis" >${ProductMapAll2.value.it_name}</p>
								<a
									href="${pageContext.request.contextPath}/BuyServlet?it_id=${ProductMapAll2.value.it_id}"
									class="btn btn-info">加入購物車</a>
							</div>
						</div>
					</div>
					</c:forEach>
					
					<c:forEach var="ProductMapAll2" items="${ProductMapAll}" begin="13"
				end="18">
					<div class="col-xs-12 col-sm-2">
						<div class="row">
							<div class="pro-container mask">
								<div class="content">
									<a
										href="${pageContext.request.contextPath}/ShoppingMall/ProductDetailOfficial.jsp?it_id=${ProductMapAll2.value.it_id}">
										<img
										src="${pageContext.request.contextPath}/ProductImageReader?it_id=${ProductMapAll2.value.it_id}"
										alt="" class="img100">
									</a>
								</div>
							</div>
							<div class="pro-detail">
								<p class="pro-name a-ellipsis">${ProductMapAll2.value.it_name}</p>
								<a
									href="${pageContext.request.contextPath}/BuyServlet?it_id=${ProductMapAll2.value.it_id}"
									class="btn btn-info">加入購物車</a>
							</div>
						</div>
					</div>
					</c:forEach>
						
					<!--  商品x12 end-->
				</div>			
			</div>
	
	</section>
	<section class="grey">
		<!-- 代購專區 -->
		<div class="container rel">
			<a href="#" class="btn btn-default create-case">建立我的代購</a>
			<div class="row row-topic">
				<div class="title">
					<h1 class="topic topic-purchase">代購專區</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-4">
					<!--    案件1 -->
					<a href="#">
						<div class="card">
							<div class="purchase-head ">
								<div class="user-avatar">
									<img src="img/avatar.jpg" alt="">
								</div>
								<div class="subject">
									<h5 class="green">幫買一雙鞋</h5>
								</div>
							</div>
							<hr>
							<div class="purchase-body">
								<ul class="purchase-list">
									<li>商品所在地：荷蘭</li>
									<li>期望金額：2000台幣</li>
									<li>商品名稱：XXX</li>
									<li>商品數量：1</li>
								</ul>
							</div>
							<div class="purchase-foot ">
								<a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">暸解更多</a><a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">私訊案主</a>
							</div>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-4">
					<!--案件2 -->
					<a href="#">
						<div class="card">
							<div class="purchase-head ">
								<div class="user-avatar">
									<img src="img/avatar.jpg" alt="">
								</div>
								<div class="subject">
									<h5 class="green">幫買一雙鞋</h5>
								</div>
							</div>
							<hr>
							<div class="purchase-body">
								<ul class="purchase-list">
									<li>商品所在地：荷蘭</li>
									<li>期望金額：2000台幣</li>
									<li>商品名稱：XXX</li>
									<li>商品數量：1</li>
								</ul>
							</div>
							<div class="purchase-foot ">
								<a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">暸解更多</a><a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">私訊案主</a>
							</div>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-4">
					<!--    案件3 -->
					<a href="#">
						<div class="card">
							<div class="purchase-head ">
								<div class="user-avatar">
									<img src="img/avatar.jpg" alt="">
								</div>
								<div class="subject">
									<h5 class="green">幫買一雙鞋</h5>
								</div>
							</div>
							<hr>
							<div class="purchase-body">
								<ul class="purchase-list">
									<li>商品所在地：荷蘭</li>
									<li>期望金額：2000台幣</li>
									<li>商品名稱：XXX</li>
									<li>商品數量：1</li>
								</ul>
							</div>
							<div class="purchase-foot ">
								<a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">暸解更多</a><a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">私訊案主</a>
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
		<div class="mt30">
			<a class="lookmore" href="#">更多案件</a>
		</div>
	</section>
	<section class="grey groupbuy">
		<!-- 揪團專區 -->
		<div class="container">
			<div class="row row-topic">
				<div class="title">
					<h1 class="topic topic-groupbuy">揪團專區</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-4">
					<!--    案件1 -->
					<a href="#">
						<div class="card blue">
							<div class="purchase-head ">
								<div class="user-avatar">
									<img src="img/avatar.jpg" alt="">
								</div>
								<div class="subject">
									<h5 class="blue">10人揪團買便當</h5>
									<div class="progress progress-custom">
										<div class="progress-bar bg-warning" role="progressbar"
											style="width: 75%" aria-valuenow="75" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<p class="countdown">倒數19天</p>
								</div>
							</div>
							<hr>
							<div class="purchase-body">
								<ul class="purchase-list">
									<li>商品所在地：荷蘭</li>
									<li>期望金額：2000台幣</li>
									<li>商品名稱：XXX</li>
									<li>商品數量：1</li>
								</ul>
							</div>
							<div class="purchase-foot ">
								<a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">暸解更多</a><a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">私訊案主</a>
							</div>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-4">
					<!--    案件1 -->
					<a href="#">
						<div class="card blue">
							<div class="purchase-head ">
								<div class="user-avatar">
									<img src="img/avatar.jpg" alt="">
								</div>
								<div class="subject">
									<h5 class="blue">10人揪團買便當</h5>
									<div class="progress progress-custom">
										<div class="progress-bar bg-warning" role="progressbar"
											style="width: 75%" aria-valuenow="75" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<p class="countdown">倒數19天</p>
								</div>
							</div>
							<hr>
							<div class="purchase-body">
								<ul class="purchase-list">
									<li>商品所在地：荷蘭</li>
									<li>期望金額：2000台幣</li>
									<li>商品名稱：XXX</li>
									<li>商品數量：1</li>
								</ul>
							</div>
							<div class="purchase-foot ">
								<a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">暸解更多</a><a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">私訊案主</a>
							</div>
						</div>
					</a>
				</div>
				<div class="col-xs-12 col-sm-4">
					<!--    案件3 -->
					<!--    案件1 -->
					<a href="#">
						<div class="card blue">
							<div class="purchase-head ">
								<div class="user-avatar">
									<img src="img/avatar.jpg" alt="">
								</div>
								<div class="subject">
									<h5 class="blue">10人揪團買便當</h5>
									<div class="progress progress-custom">
										<div class="progress-bar bg-warning" role="progressbar"
											style="width: 75%" aria-valuenow="75" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<p class="countdown">倒數19天</p>
								</div>
							</div>
							<hr>
							<div class="purchase-body">
								<ul class="purchase-list">
									<li>商品所在地：荷蘭</li>
									<li>期望金額：2000台幣</li>
									<li>商品名稱：XXX</li>
									<li>商品數量：1</li>
								</ul>
							</div>
							<div class="purchase-foot ">
								<a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">暸解更多</a><a
									class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green"
									href="#" role="button">私訊案主</a>
							</div>
						</div>
					</a>
				</div>
			</div>
			<div class="mt30">
				<a class="lookmore" href="#">更多揪團</a>
			</div>
	</section>
	<!-- 熱銷商品 -->
<%
NewsService nsSvc = new NewsService(); 
List<NewsVO> list = nsSvc.getAll();
pageContext.setAttribute("list",list);
%>
	
	<section>
		<div class="container">
			<div class="row row-topic">
				<div class="title">
					<h1 class="topic">最新消息</h1>
				</div>
				<div class="more">
					<a href="<%=request.getContextPath()%>/frontform/News.jsp">更多消息</a>
				</div>
			</div>
			<div class="row">
				<c:forEach var="nsVO" items="${list}">
                      <div class="container listnews2" align="left">
                        <div class="row">
                          <div class="col-xs-12 col-sm-2">
                          		 ${nsVO.ns_date}
                          </div>
                          <div class="col-xs-12 col-sm-10" style='margin-bottom:10px '>
                           	<div class="lgreen text-left" style='font-weight:bold; font-size:18px'> 
                           	<a href="<%=request.getContextPath()%>/news.do?ns_id=${nsVO.ns_id}&action=shownews">${nsVO.ns_tit}</a></div>
                          </div>
                          </div>
                        </div>
                   </c:forEach>
			</div>
		</div>
	</section>
	<footer class="footer">
		<div class="row">
			<div class="container add-mobile-gutter">
				<div class="row">
					<div class="col-xs-12 col-sm-12 fix-col-xs">
						<div class="row">
							<ul class="link-list">
								<li><a href="<%=request.getContextPath()%>/frontform/News.jsp">最新消息</a></li>
								<li><a href="FAQ_bonus.asp">紅利點數</a></li>
								<li><a href="FAQ_order.asp">購物須知</a></li>
								<li><a href="FAQ_returns-exchanges.asp">售後服務</a></li>
								<li><a href="FAQ_order-overseas.asp">INTERNATIONAL
										ORDERS</a></li>
								<li><a href="FAQ_anti-fraud.asp">防詐騙</a></li>
							</ul>
							<ul class="link-list">
								<li><a href="about.asp">關於我們</a></li>
								<li><a href="news.asp?poid=452">媒體露出</a></li>
								<li><a href="news.asp?poid=450">工作職缺</a></li>
								<li><a href="FAQ_contact.asp">合作聯絡</a></li>
								<li><a href="journal.asp?conditiontype=1&amp;poids=573">關懷社會</a></li>
								<li><a href="journal.asp?conditiontype=1&amp;poids=569">活動分享</a></li>
							</ul>
							<ul class="link-list">
								<li><a href="TWCA.asp" target="_blank"><span
										class="lock"></span>安心購物</a></li>
								<li><a href="LEGAL_privacy-policy.asp">隱私權</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>	
	</footer>
	<!-- banner之下開始 -->
	<script></script>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>