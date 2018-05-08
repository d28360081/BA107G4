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
	
	ProductVO EditAllProductToNothingFromBack=(ProductVO) request.getAttribute("EditAllProductToNothingFromBack");
	
%>



<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>後端 所有商品</title>
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
	<div class="col-xs-12 col-sm-2"> 
		<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>
	</div>
	    <!-- =-------------------------------------------------------------------------------- -->
	<div class="col-xs-12 col-sm-10 mainBody">     
		<div class="main">
			<ol class="breadcrumb">
				<li><a href="#">首頁</a></li>
				<li><a href="#">官方商城管理</a>
				<li><a
					href="${pageContext.request.contextPath}/Back-End/ProductALLEditBackOfficial.jsp">所有商品</a>
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
						<th class="center  mytable">商品上下架</th>
						<th class="center  mytable">商品移除</th>
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
											編輯 <input type="hidden" name="action"
												value="editProductFromBack-End"> <input
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
							<c:if test="${ProductMapAll2.value.it_sts eq '上架'}">
								<form method="post"
									action="<%=request.getContextPath()%>/EditProductServlet">
									<td><div>
											<button type="submit" class="btn btn-primary btn-lg"
												data-toggle="modal" data-target="#myModal">
												下架 <input type="hidden" name="action"
													value="EditAllProductFromBackOntoOff"> <input
													type="hidden" name="it_id"
													value="${ProductMapAll2.value.it_id}"> <input
													type="hidden" name="whichPage" value="<%=whichPage%>">
											</button>
										</div></td>
								</form>
							</c:if>

							<c:if test="${ProductMapAll2.value.it_sts eq '下架'}">
								<form method="post"
									action="<%=request.getContextPath()%>/EditProductServlet">
									<td><div>
											<button type="submit" class="btn btn-primary btn-lg"
												data-toggle="modal" data-target="#myModal">
												上架 <input type="hidden" name="action"
													value="EditAllProductFromBackOfftoOn"> <input
													type="hidden" name="it_id"
													value="${ProductMapAll2.value.it_id}"> <input
													type="hidden" name="whichPage" value="<%=whichPage%>">
											</button>
										</div></td>
								</form>
							</c:if>
							<form method="post"
								action="<%=request.getContextPath()%>/EditProductServlet">
								<td><div>
										<button type="submit" class="btn btn-primary btn-lg"
											data-toggle="modal" data-target="#myModal">
											移除 <input type="hidden" name="action"
												value="EditAllProductToNothingFromBack"> <input
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
						<jsp:include page="EditProductBackOfficial.jsp" />

						<div class="modal-footer">
							<div class="btn-group btn-group-lg">
								<input type="button" data-dismiss="modal" value="離開"
									class="btn btn-info">
							</div>
							<div class="btn-group btn-group-lg">
								<input type="submit" value="確定修改商品" class="btn btn-info">
							</div>
						</div>
						<input type="file" name="it_pic" onchange="readURL(this)"
							targetID="preview">
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
