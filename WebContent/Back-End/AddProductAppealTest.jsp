<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.article_appeal.model.ArticleAppealService"%>
<%@page import="com.article_appeal.model.ArticleAppealVO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.ProductVO"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("product");

ProductService service = new ProductService();
ProductVO product = service.findProduct(productVO.getIt_id());




%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
	<!-- ****************************************按鈕*********************************************** -->		
		<a href="#modal-appeal" data-toggle="modal" class="btn btn-go">檢舉</a>
	<!-- **************************************檢舉跳窗********************************************** -->			
		<div class="modal fade" id="modal-appeal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">檢舉此代購</h4>
					</div>
					<div class="modal-body">
						<form action="<%=request.getContextPath()%>/appeal/producteappeal.do" method="post">
							<div class="form-group">
							  <label for="comment">請簡述檢舉原因</label>
							  <textarea class="form-control" rows="5" id="comment" name="it_apl_cnt"></textarea>
							</div>
						
                  			<br>
                  			<input type="hidden" name="action" value="appeal_This_Product">
                  			<input type="hidden" name="mem_id" value="M0000007">
                  			<input type="hidden" name="it_id" value="<%=product.getIt_id()%>">
                  			<input type="hidden" name="requestURL" value="<%=request.getContextPath()%>/放在要商品檢舉按鈕的當前頁面">
                  			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                  			<input type="submit" class="btn btn-info" onclick="getaplValue()" value="確認送出">
                  		</form>	
					</div>
				
				</div>
			</div>
		</div>
	<!-- **************************************檢舉跳窗********************************************** -->	
	
	
	
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>