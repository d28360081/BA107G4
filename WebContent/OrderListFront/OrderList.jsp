<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.* , com.Product.model.ProductVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.StoreOrder.model.*"%>
<%@ page import="com.StoreReceiptDetail.model.*"%>
<%@page import="com.member.model.*"%>
<%
StoreOrderService storeOrderService=new StoreOrderService();
MemVO memVO = (MemVO) session.getAttribute("MemVO");
request.setAttribute("memVO", memVO);	
String mem_id=memVO.getMem_id();

List<StoreOrderVO> storeOrderList=storeOrderService.StoreOrdergetAllBy_mem_id(mem_id);
pageContext.setAttribute("storeOrderList", storeOrderList);



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
	href="${pageContext.request.contextPath }/css/Sean.css">
<link rel="icon" href="${pageContext.request.contextPath }/img/gogo.png">
<style type="text/css">
</style>


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

function ChangeStoreOrder_sts_finished(form) {
	
	var b = window.confirm("您確定要完成訂單?");
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

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 textLeft">
					<%  int rowsPerPage = 5;  //每頁的筆數    
    int rowNumber=0;      //總筆數
    int pageNumber=0;     //總頁數      
    int whichPage=1;      //第幾頁
    int pageIndexArray[]=null;
    int pageIndex=0; 
%>

					<%  
    rowNumber=storeOrderList.size();
    if (rowNumber%rowsPerPage !=0)
         pageNumber=rowNumber/rowsPerPage + 1;
    else pageNumber=rowNumber/rowsPerPage;    

    pageIndexArray=new int[pageNumber]; 
    for (int i=1 ; i<=pageIndexArray.length ; i++)
         pageIndexArray[i-1]=i*rowsPerPage-rowsPerPage;
%>

					<%  try {
       whichPage = Integer.parseInt(request.getParameter("whichPage"));
       pageIndex=pageIndexArray[whichPage-1];
    } catch (NumberFormatException e) { //第一次執行的時候
       whichPage=1;
       pageIndex=0;
    } catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數
         if (pageNumber>0){
              whichPage=pageNumber;
              pageIndex=pageIndexArray[pageNumber-1];
         }
    } 
%>


					<c:forEach var="storeOrderList2" items="${storeOrderList}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<table
							class="table table-hover table-striped table-condensed table-bordered">
							<thead>
								<tr>
									<th class="center  mytable">商家編號</th>
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
									<c:if test="${storeOrderList2.st_id=='ST001'}">
										<td class="center  mytable">官方商家</td>
									</c:if>
									<c:if test="${storeOrderList2.st_id!='ST001'}">
										<td class="center  mytable">${storeOrderList2.st_id}</td>
									</c:if>


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
												value="lookUpStoreOrderDetail"> <input type="hidden"
												name="rec_id" value="${storeOrderList2.rec_id}"> <input
												type="hidden" name="st_id" value="${storeOrderList2.st_id}">
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
										</form>
									</td>
								</tr>
							</tbody>
						</table>
					</c:forEach>
				</div>
			</div>
		</div>

		<c:if test="${openModal!=null}">

			<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-body">
							<jsp:include page="/OrderListFront/OrderListDetail.jsp" />

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









		<b>商品訂單:共<font color=#3ab2d9><%=rowNumber%></font>筆
		</b> <br> <br>
		<%if (rowsPerPage<rowNumber) {%>
		<%if(pageIndex>=rowsPerPage){%>
		<A href="<%=request.getRequestURI()%>?whichPage=1"><font
			color=#3ab2d9>第一頁</font></A>&nbsp; <A
			href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>"><font
			color=#3ab2d9>上一頁 </font></A>&nbsp;
		<%}%>

		<%if(pageIndex<pageIndexArray[pageNumber-1]){%>
		<A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>"><font
			color=#3ab2d9>下一頁 </font></A>&nbsp; <A
			href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>"><font
			color=#3ab2d9>最後一頁</font></A>&nbsp;
		<%if (pageNumber>0){%>
		<%}%>
		<%}%>
		<%}%>

		<br> <b><font color=#3ab2d9>第<%=whichPage%>/<%=pageNumber%>頁
		</font></b> <br>

		<%if (pageNumber>1) {%>
		<FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">
			<select size="1" name="whichPage">
				<%for (int i=1; i<=pageNumber; i++){%>
				<option value="<%=i%>">跳至第<%=i%>頁
					<%}%>
				
			</select> <input type="submit" value="確定" class="btn btn-info">
		</FORM>
		<%}%>


	</section>
	<%@ include file="/Footer.jsp"%>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>