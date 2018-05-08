<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.ProductVO"%>
<%@page import="com.StoreOrder.model.StoreOrderVO"%>
<%@page import="com.StoreOrder.model.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.PersonalStore.model.*"%>

<%
MemVO memVO2 = (MemVO) session.getAttribute("MemVO");
request.setAttribute("memVO2", memVO2);
String mem_id2=memVO2.getMem_id();

PersonalStoreVO MempersonalStore=new PersonalStoreVO();
PersonalStoreService  personalStoreService=new PersonalStoreService();
MempersonalStore=personalStoreService.findByMemId(mem_id2);
request.setAttribute("MempersonalStore", MempersonalStore);


	StoreOrderService storeOrderService = new StoreOrderService();
	String st_id = MempersonalStore.getSt_id();
	List<StoreOrderVO> storeOrderList = storeOrderService.StoreOrdergetAll_By_St_id(st_id);
	request.setAttribute("storeOrderList", storeOrderList);

	String activeOrderList = "activeOrderList";
	request.setAttribute("activeOrderList", activeOrderList);
%>

<!DOCTYPE html>
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

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Base2.css">
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
<!--   自己寫的css   -->
<!-- <link rel="stylesheet" href="css/OOXX.css"> -->
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<script>

function CancelOrder(form) {
	
	var b = window.confirm("您確定取消訂單嗎？");
	if (b) {			
		return true;		
	} else {	
		return false;			
	}
}

function Send(form) {
	
	var b = window.confirm("您確定發貨嗎？");
	if (b) {			
		return true;		
	} else {		
		return false;			
	}
}
</script>



</head>

<body>
	 <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
	<section>

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





		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-5 ">
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath }/index.html">首頁</a></li>
						
						<li>訂單管理</li>
					</ol>
				</div>
				<div class="col-xs-12 col-sm-5"></div>
			</div>
		</div>

		<div class="container">
			<%@include file="/PersonalStore/PersonalStoreLeftBar.jsp"%>


			<div class="col-xs-12 col-sm-9">
				<div role="tabpanel">
					<!-- 標籤面板：標籤區 -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#tab1"
							aria-controls="tab1" role="tab" data-toggle="tab">訂單管理</a></li>
					</ul>



					<!-- 標籤面板：內容區 -->

					<div class="container">
						<div class="row">
							<div class="col-xs-12 col-sm-9 textLeft" style="padding-top: 30px;">
							



								<c:forEach var="storeOrderList2" items="${storeOrderList}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<table
										class="table table-hover table-striped table-condensed table-bordered">
										<thead>
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
										<tbody>
											<tr>				
												<td class="center  mytable">${storeOrderList2.rec_id}</td>
												<td class="center  mytable">${storeOrderList2.buyer_name}</td>
												<td class="center  mytable">${storeOrderList2.mem_email}</td>
												<td class="center  mytable">${storeOrderList2.rec_delivery}</td>
												<td class="center  mytable">${storeOrderList2.mem_ph}</td>

												<td class="center  mytable">$<fmt:formatNumber
														value="${storeOrderList2.payment}" pattern="#"
														type="number" /></td>
												<td class="center  mytable">${storeOrderList2.mem_add}</td>
												<td class="center  mytable">${storeOrderList2.rec_date}</td>
												<td class="center  mytable"><font color="red">${storeOrderList2.rec_dlv_sts}</font></td>
												<td class="center  mytable">
													<form
														action="${pageContext.request.contextPath}/StoreReceiptDetailController"
														method="post">
														<input class="btn btn-info" type="submit" value="查看訂單詳細內容">
														<input type="hidden" name="action"
															value="lookUpStoreOrderDetailFromPersonalStore"> <input
															type="hidden" name="rec_id"
															value="${storeOrderList2.rec_id}"> <input
															type="hidden" name="st_id"
															value="${storeOrderList2.st_id}"> <input
															type="hidden" name="whichPage" value="<%=whichPage%>">
													</form>
												</td>
											</tr>
										</tbody>
									</table>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
	</section>
	
	
	<c:if test="${openModal!=null}">

		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
			aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-body">
						<jsp:include page="OrderListPersonalStoreDetail.jsp" />

						
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

	<%@ include file="/Footer.jsp"%>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>