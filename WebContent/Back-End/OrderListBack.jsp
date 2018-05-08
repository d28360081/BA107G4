<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.Product.model.*"%>
<%@page import="java.util.*, java.util.Map.Entry"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.StoreOrder.model.*"%>
<!DOCTYPE html>
<html lang="">

<%
	StoreOrderService storeOrderService = new StoreOrderService();
	List<StoreOrderVO> storeOrderList = storeOrderService.StoreOrdergetAllNotST001();
	pageContext.setAttribute("storeOrderList", storeOrderList);
	
	request.getAttribute("storeReceiptDetailList");

%>



<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>後端 會員商城管理</title>
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
	
	<div class="container">
		<div class="col-xs-12 col-sm-2"> 
		</div>
	</div>
	    <!-- =-------------------------------------------------------------------------------- -->
	<div class="col-xs-12 col-sm-10 mainBody">     
		<div class="main">
			<ol class="breadcrumb">
				<li><a href="#">首頁</a></li>
				<li><a href="#">官方商城管理</a>
				<li><a
href="${pageContext.request.contextPath}/Back-End/OrderListBack.jsp">會員商城訂單管理</a>
			</ol>	
	
			<table
				class="table table-hover table-striped table-bordered table-condensed ">

				<thead class="table-title">
					<tr>
						<th class="center  mytable">訂單編號</th>
						<th class="center  mytable">購買人姓名</th>
						<th class="center  mytable">聯絡用E-Mail</th>
						<th class="center  mytable">貨運方式</th>
						<th class="center  mytable">聯絡電話</th>
						<th class="center  mytable">訂單總金額</th>
						<th class="center  mytable">收貨地址</th>
						<th class="center  mytable">訂單時間</th>
						<th class="center  mytable">狀態</th>
						<th class="center  mytable">操作</th>
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
						rowNumber = storeOrderList.size();
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
					<c:forEach var="storeOrderList2" items="${storeOrderList}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr>						
							<td class="center  mytable">${storeOrderList2.rec_id}</td>
							<td class="center  mytable">${storeOrderList2.buyer_name}</td>
							<td class="center  mytable">${storeOrderList2.mem_email}</td>
							<td class="center  mytable">${storeOrderList2.rec_delivery}</td>
							<td class="center  mytable">${storeOrderList2.mem_ph}</td>

							<td class="center  mytable">$<fmt:formatNumber
									value="${storeOrderList2.payment}" pattern="#" type="number" /></td>
							<td class="center  mytable">${storeOrderList2.mem_add}</td>
							<td class="center  mytable">${storeOrderList2.rec_date}</td>
							<td class="center  mytable"><font color="red">${storeOrderList2.rec_dlv_sts}</font></td>
							<td class="center  mytable">
								<form
									action="${pageContext.request.contextPath}/StoreReceiptDetailController"
									method="post">
									<input class="btn btn-info" type="submit" value="查看訂單詳細內容">
									<input type="hidden" name="action"
										value="lookUpStoreOrderDetailFromBack"> <input type="hidden"
										name="rec_id" value="${storeOrderList2.rec_id}"> <input
										type="hidden" name="st_id" value="${storeOrderList2.st_id}">
										<input
										type="hidden" name="whichPage" value="<%=whichPage%>">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<b>符合條件訂單:共<font color=#3ab2d9><%=rowNumber%></font>筆
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
						<jsp:include page="OrderListBackDetail.jsp" />

						
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
</div>
</body>








</html>
