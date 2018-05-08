<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.ProductVO"%>
<%@page import="java.util.*, java.util.Map.Entry"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="">

<%
	Map<String, ProductVO> ProductMapAll = new LinkedHashMap<String, ProductVO>();

	ProductService service = new ProductService();

	ProductMapAll = service.getAllProductMap_ST001();
	pageContext.setAttribute("ProductMapAll", ProductMapAll);

	ProductVO productAllOntoOff = (ProductVO) request.getAttribute("productAllOntoOff");
	ProductVO productAllOfftoOn = (ProductVO) request.getAttribute("productAllOfftoOn");

	ProductVO productEditAll = (ProductVO) request.getAttribute("productEditAll");

	ProductVO EditAllProductToNothingFromBack = (ProductVO) request
			.getAttribute("EditAllProductToNothingFromBack");
%>



<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>後端 檢舉商品</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Base2.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Sean.css">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css"
	rel="stylesheet">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css"
	rel="stylesheet">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>
<style>
.table-title {
	font-size: 16px;
	font-weight: bold;
}

.table-content {
	font-size: 14px;
}

#clients {
	background-color: #bfbfbf;
}

.one_line {
	width: 300px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}
</style>

<script type="text/javascript">
	
</script>
<script src="https://code.jquery.com/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<c:if test="${productEditAll!=null}">
		<script>
			alertify.success('修改商品成功');
		</script>
	</c:if>

	<c:if test="${productAllOntoOff!=null}">
		<script>
			alertify.error('下架商品成功');
		</script>
	</c:if>

	<c:if test="${productAllOfftoOn!=null}">
		<script>
			alertify.success('上架商品成功');
		</script>
	</c:if>

	<c:if test="${EditAllProductToNothingFromBack!=null}">
		<script>
			alertify.success('移除商品成功');
		</script>
	</c:if>

	<div class="container">
		<div class="col-xs-12 col-sm-12">
			<div class="row">
				<nav class="navbar navbar-inverse navbar-fixed-top navtop">
					<div class="container-fluid">
						<a class="navbar-brand" href="#"><span
							class="container navlogo"><img
								src="${pageContext.request.contextPath}/img/gogo.png" alt="">GogoShop
						</span></a>
					</div>
				</nav>
			</div>
		</div>
	</div>

		<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>
	<div class="col-xs-12 col-sm-10">
		<div class="main">
			<ol class="breadcrumb">
				<li><a href="#">首頁</a></li>
				<li><a href="#">檢舉管理</a>
				<li><a
					href="${pageContext.request.contextPath}/Back-End/ProductAppeal.jsp">商品檢舉</a>
			</ol>
			<table
				class="table table-hover table-striped table-bordered table-condensed ">
				<thead class="table-title">
					<tr>
						<th class="center  mytable">編輯</th>
						<th class="center  mytable">商品圖片</th>
						<th class="center  mytable">商品名稱</th>
						<th class="center  mytable">商品描述</th>
						<th class="center  mytable">商品價格</th>
						<th class="center  mytable">商品數量</th>
						<th class="center  mytable">商品狀態</th>
						<th class="center  mytable">商品分類</th>
						<th class="center  mytable">取消檢舉</th>
						<th class="center  mytable">確認檢舉</th>
					</tr>
				</thead>
				<tbody class="table-content">
					<%
						int rowsPerPage = 9; //每頁的筆數    
						int rowNumber = 0; //總筆數
						int pageNumber = 0; //總頁數      
						int whichPage = 1; //第幾頁
						int pageIndexArray[] = null;
						int pageIndex = 0;
					%>

					<%
						rowNumber = ProductMapAll.size();
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
					<c:forEach var="ProductMapAll2" items="${ProductMapAll}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr align="center">
							<form method="post"
								action="<%=request.getContextPath()%>/EditProductServlet">
								<td><div>
										<button type="submit" class="btn btn-primary btn-lg"
											data-toggle="modal" data-target="#myModal">
											查看檢舉內容 <input type="hidden" name="action"
												value="LookUpProductFromAppeal"> <input
												type="hidden" name="it_id"
												value="${ProductMapAll2.value.it_id}"> <input
												type="hidden" name="whichPage" value="<%=whichPage%>">
										</button>
									</div></td>
							</form>
							<td><div>
									<img
										src="${pageContext.request.contextPath}/ProductImageReader?it_id=${ProductMapAll2.value.it_id}"
										alt="" class="img100">
								</div></td>
							<td><div>${ProductMapAll2.value.it_name}</div></td>
							<td><div class="one_line">
									${ProductMapAll2.value.it_intro}</div></td>
							<td><div>
									$
									<fmt:formatNumber value="${ProductMapAll2.value.it_prc}"
										pattern="#" type="number" />
								</div></td>
							<td><div>${ProductMapAll2.value.it_num}</div></td>
							<td><div>
									<c:if test="${ProductMapAll2.value.it_sts eq '上架'}">
										<font color=#3ab2d9>${ProductMapAll2.value.it_sts}</font>
									</c:if>
									<c:if test="${ProductMapAll2.value.it_sts eq '下架'}">
										<font color="red">${ProductMapAll2.value.it_sts}</font>
									</c:if>
								</div></td>
							<td>
								<div>${ProductMapAll2.value.it_cate}</div>
							</td>

							<form method="post" action="<%=request.getContextPath()%>/">
								<td><div>
										<button type="submit" class="btn btn-primary btn-lg"
											data-toggle="modal" data-target="#myModal">
											取消檢舉狀態 <input type="hidden" name="action" value=""> <input
												type="hidden" name="it_id"
												value="${ProductMapAll2.value.it_id}"> <input
												type="hidden" name="whichPage" value="<%=whichPage%>">
										</button>
									</div></td>
							</form>


							<form method="post" action="<%=request.getContextPath()%>/">
								<td><div>
										<button type="submit" class="btn btn-primary btn-lg"
											data-toggle="modal" data-target="#myModal">
											確認檢舉 <input type="hidden" name="action" value=""> <input
												type="hidden" name="it_id"
												value="${ProductMapAll2.value.it_id}"> <input
												type="hidden" name="whichPage" value="<%=whichPage%>">
										</button>
									</div></td>
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<%@ include file="/ShoppingMall/ShoppingMallPage2.file"%>
			<div align="right">
				<a
					href="${pageContext.request.contextPath}/Back-End/AddProductBack.jsp">
					<button type="button" class="btn btn-danger btn-lg">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增商品
					</button>
				</a>
			</div>

		</div>
	</div>

	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-body">
						<%
							ProductVO productVO = (ProductVO) request.getAttribute("product");

								ProductVO product = service.findProduct(productVO.getIt_id());
						%>
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
						

						<div class="modal-footer">
							<div class="btn-group btn-group-lg">
								<input type="button" data-dismiss="modal" value="離開"
									class="btn btn-info">
							</div>
						</div>
						</form>
					</div>



				</div>
			</div>
		</div>

		<script>
			$("#basicModal").modal({
				show : true
			});
		</script>
	</c:if>

</body>








</html>
