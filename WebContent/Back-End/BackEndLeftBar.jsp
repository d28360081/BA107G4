<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<div class="col-xs-12 col-sm-2">
		<div class="sidenav">
			<a href="#about">權限管理</a> <a data-toggle="collapse" href="#services"
				aria-expanded="false" aria-controls="#services"> 檢舉管理
				<div class="collapse" id="services">
					<a href="CommisionAppeal.html">代購檢舉</a> <a
						href="CommisionCommentAppeal.html">代購回文檢舉</a> <a
						href="ProductAppeal.jsp">商品檢舉</a> <a href="ArticleAppeal.html">文章檢舉</a>
					<a href="ArticleReplyAppeal.html">回復文章檢舉</a>

				</div>
			</a> <a href="#clients">最新消息管理</a> <a href="#about">權限管理</a> 
			<a
				data-toggle="collapse" href="#ProductOfficial" aria-expanded="false"
				aria-controls="#ProductOfficial"> 官方商城管理
				<div class="collapse" id="ProductOfficial">
					<a href="${pageContext.request.contextPath}/Back-End/AddProductBack.jsp">新增商品</a> <a
						href="${pageContext.request.contextPath}/Back-End/ProductALLEditBackOfficial.jsp">所有商品</a>
					<a href="${pageContext.request.contextPath}/Back-End/ProductSts_On_EditBackOfficial.jsp">所有上架商品</a> <a href="${pageContext.request.contextPath}/Back-End/ProductSts_Off_EditBackOfficial.jsp">所有下架商品</a>
					<a href="${pageContext.request.contextPath}/Back-End/OrderListBackForOfficial.jsp">官方商城訂單管理</a>			
						<a href="${pageContext.request.contextPath}/Back-End/OrderListBack.jsp">會員商城訂單管理</a>	
				</div>
			</a>	
			 <a href="#about">任務管理</a> <a href="#about">促銷活動管理</a> <a
				href="#about">其他管理</a>
		</div>
	</div>

</body>
</html>