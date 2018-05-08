<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.FavoriteProduct.model.*"%>
<%@page import="com.Product.model.*"%>
<%@page import="java.util.*"%>
	<%@page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

<script type="text/javascript">
	function deleteFavorite(form) {
		var b = window.confirm("您確定取消收藏嗎？");
		if (b) {
			return true;
		} else {
			alertify.error("取消動作");
			return false
		}
	}
</script>





</head>
<body>
	 <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
	<%		
	
	MemVO memVO = (MemVO) session.getAttribute("MemVO");
	request.setAttribute("memVO", memVO);
	String mem_id=memVO.getMem_id();
	
	
		FavoriteProductService favoriteProductService = new FavoriteProductService();
		List<FavoriteProductVO> favoriteProductList = favoriteProductService.getAll_by_mem_id(mem_id);
		pageContext.setAttribute("favoriteProductList", favoriteProductList);

		ProductService productService = new ProductService();

		Map<String, ProductVO> productMap = productService.getAllProductMap();
		request.setAttribute("productMap", productMap);

	
		String it_id = request.getParameter("it_id");
		request.setAttribute("it_id", it_id);
		
// 		double test = 1.0;
// 		int j = 1;
// 		for(FavoriteProductVO vo:favoriteProductList){
// 			if(vo.getIt_id() == it_id){
// 				test = Math.ceil(j/9);
// 			}
// 			j++;
// 		}
		
		
		
	
		ProductService service = new ProductService();
		ProductVO product = service.findProduct(it_id);
		request.setAttribute("product", product);
	%>
	
<%-- 	=====<%=test %>==== --%>
	<%
		int rowsPerPage = 9; //每頁的筆數    
		int rowNumber = 0; //總筆數
		int pageNumber = 0; //總頁數      
		int whichPage = 1; //第幾頁
		int pageIndexArray[] = null;
		int pageIndex = 0;
	%>

	<%
		rowNumber = favoriteProductList.size();
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
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">			
				<c:forEach var="favoriteProduct" items="${favoriteProductList}"
					begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<c:forEach var="product" items="${productMap}">
						<c:if test="${product.value.it_id==favoriteProduct.it_id}">
							<table
								class="table table-hover table-striped table-condensed table-bordered" >
								<thead>
									<tr>
										<th class="center  mytable"></th>
										<th class="center  mytable">商品編號</th>
										<th class="center  mytable">商品名稱</th>
										<th class="center  mytable">商品數量</th>
										<th class="center  mytable">商品單價</th>
										<th class="center  mytable">狀態</th>
										<th class="center  mytable">操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:if test="${product.value.it_id==it_id}">
										<td bgcolor="#3ab2d9" class="center  mytable">
										</c:if>
										
										<c:if test="${product.value.it_id!=it_id}">
										<td class="center  mytable">
										</c:if>
										
										<a
											href="${pageContext.request.contextPath}/ShoppingMall/ProductDetail.jsp?it_id=${product.value.it_id}">
												<img
												src="${pageContext.request.contextPath}/ProductImageReader?it_id=${product.value.it_id}"
												alt="" class="img100">
										</a></td>
										
										<td class="center  mytable">${favoriteProduct.it_id}</td>


										<td class="center  mytable">${product.value.it_name}</td>
										<td class="center  mytable">${product.value.it_num}</td>
										<td class="center  mytable"><b class="amountRed">$<fmt:formatNumber
													value="${product.value.it_prc}" pattern="#" type="number" />
										</b></td>
										<td class="center  mytable">
											<c:if
												test="${product.value.it_num==0}">
												<font color=red>缺貨中</font>
											</c:if> 
											
											<c:if test="${product.value.it_num>0}">
												<font color=#3ab2d9>現正熱賣中</font>
											</c:if></td>

										<form
											action="${pageContext.request.contextPath}/FavoriteProductController"
											method="post" onsubmit="return deleteFavorite(this)">
											<td class="center  mytable"><input class="btn btn-info"
												type="submit" value="取消收藏商品"></td> <input type="hidden"
												name="action" value="deleteFavoriteProduct"> <input
												type="hidden" name="whichPage" value="<%=whichPage%>">
											<input type="hidden" name="it_id"
												value="${favoriteProduct.it_id}">
										</form>
									</tr>
								</tbody>
							</table>
						</c:if>
					</c:forEach>
				</c:forEach>
			
			</div>
		</div>
	</div>

	<b>收藏商品:共<font color=#3ab2d9><%=rowNumber%></font>筆
	</b>
	<br>
	<br>
	<%
		if (rowsPerPage < rowNumber) {
	%>
	<%
		if (pageIndex >= rowsPerPage) {
	%>
	<A href="<%=request.getRequestURI()%>?whichPage=1"><font
		color=#3ab2d9>第一頁</font></A>&nbsp;
	<A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>"><font
		color=#3ab2d9>上一頁 </font></A>&nbsp;
	<%
		}
	%>

	<%
		if (pageIndex < pageIndexArray[pageNumber - 1]) {
	%>
	<A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>"><font
		color=#3ab2d9>下一頁 </font></A>&nbsp;
	<A href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>"><font
		color=#3ab2d9>最後一頁</font></A>&nbsp;
	<%
		if (pageNumber > 0) {
	%>
	<%
		}
	%>
	<%
		}
	%>
	<%
		}
	%>

	<br>
	<b><font color=#3ab2d9>第<%=whichPage%>/<%=pageNumber%>頁
	</font></b>
	<br>

	<%
		if (pageNumber > 1) {
	%>
	<FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">
		<select size="1" name="whichPage">
			<%
				for (int i = 1; i <= pageNumber; i++) {
			%>
			<option value="<%=i%>">跳至第<%=i%>頁
				<%
				}
			%>
			
		</select> <input type="submit" value="確定" class="btn btn-info">
	</FORM>
	<%
		}
	%>




	<%@ include file="/Footer.jsp"%>
</body>
</html>