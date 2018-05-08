<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.* , com.Product.model.ProductVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.member.model.*"%>

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
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css"
	rel="stylesheet">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css"
	rel="stylesheet">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>


<script
  src="http://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>

<script type="text/javascript">


<% 
MemVO memVO = (MemVO) session.getAttribute("MemVO");
request.setAttribute("memVO", memVO);		


%>



</script>


<!--   自己寫的css   -->
<!-- <link rel="stylesheet" href="css/OOXX.css"> -->
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
       
        
<script>
	


	function reg(form) {
		
	
		
		if (form.rec_py.value == "") {
			alertify.alert("請勾選付款方式！");
			return false;
		}
		if (form.rec_delivery.value == "") {
			alertify.alert("請勾選貨運方式！！");
			return false;
		}
		if (form.buyer_name.value == "") {
			alertify.alert("請填入購買人姓名！");
			return false;
		}
		if (form.mem_ph.value == "") {
			alertify.alert("請填入聯絡電話！");
			return false;
		}
		if (form.mem_add.value == "") {
			alertify.alert("請填入收貨地址！");
			return false;
		}
		if (form.mem_email.value == "") {
			alertify.alert("請填入聯絡用E-Mail！");
			return false;
		}
		
				
		document.getElementById("checkOutSubmit").disabled=true;
	}
	
	function ValidateNumber(e, pnumber)
	{
	    if (!/^\d+$/.test(pnumber))
	    {
	        $(e).val(/^\d+/.exec($(e).val()));
	    }
	    return false;
	}
	
	 
</script>


   


</head>

<c:if test="${empty(cart.map) }">
	<html>
<body>
	沒東西啦
	<br> 你是怎麼連到這來的
</body>
	</html>
</c:if>



<c:if test="${!empty(cart.map)}">
	<body>
		 <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>


		<section>

			<div class="container">
				<c:forEach var="entry" items="${cart.map}">
					<table
						class="table table-hover table-striped table-condensed table-bordered">
						<thead>
							<tr>
								<th></th>

								<th class="center  mytable">商品</th>
								<th class="center  mytable">描述</th>
								<th class="center  mytable">價格</th>
								<th class="center  mytable">數量</th>
								<th class="center  mytable">總計</th>
							</tr>
						</thead>
						<tbody>
							<tr>

								<td class="center  mytable"><img
									src="${pageContext.request.contextPath }/ProductImageReader?it_id=${entry.key}"
									class="minjpg"></td>
								<td class="center  mytable">${entry.value.product.it_name}</td>
								<td class="center  mytable">${entry.value.product.it_intro}</td>
								<td class="center  mytable">$<fmt:formatNumber
										value="${entry.value.product.it_prc}" pattern="#"
										type="number" /></td>
								<td class="center  mytable">${entry.value.it_num}</td>


								<td class="center  mytable ">$<fmt:formatNumber
										value="${entry.value.it_prc}" pattern="#" type="number" /></td>
							</tr>
						</tbody>
						<tfoot>

						</tfoot>
					</table>
				</c:forEach>

				<div>
					<b class="amountRed">總金額:$<fmt:formatNumber
							value="${cart.price}" pattern="#" type="number" /></b>
				</div>

			</div>


			<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-12 textLeft">
						<form id="post-form"
							action="${pageContext.request.contextPath }/StoreOrderController"
							method="post" onsubmit="return reg(this);">
							<div class="container">
								<div class="row">
									<div class="col-xs-12 col-sm-6 textLeft">
										


										<h1>
											<b>付款方式</b>
										</h1>
										<div>									
											
											<input type="radio" name="rec_py" value="現金匯款" ${memVO.mem_pay eq '現金匯款'?"checked":""}>現金匯款<br>
											<input type="radio" name="rec_py" value="信用卡匯款" ${memVO.mem_pay eq '信用卡匯款'?"checked":""}>信用卡匯款<br>
											<input type="radio" name="rec_py" value="ATM匯款" ${memVO.mem_pay eq 'ATM匯款'?"checked":""}>ATM匯款<br>
											
										</div>

										<h1>
											<b>貨運方式</b>
										</h1>
										<input type="radio" name="rec_delivery" value="船運">船運<br>
										<input type="radio" name="rec_delivery" value="空運">空運<br>



										<h1 class="PB">
											<b>收貨人資料</b>
										</h1>
										<div class="PB">
											購買人姓名:<input class="form-control" type="text"
												name="buyer_name" placeholder="請輸入姓名">
										</div>

										<div class="PB">
											聯絡電話:<input class="form-control" type="text" name="mem_ph"
												placeholder="請輸入聯絡號碼">
										</div>

										<div class="PB">
											收貨地址:<input class="form-control" type="text" name="mem_add"
												value="${memVO.address}" placeholder="請輸入收貨地址">
										</div>

										<div class="PB">
											聯絡用E-Mail:<input class="form-control" type="text"
												name="mem_email" value="${memVO.mem_email}" placeholder="請輸入聯絡用E-Mail">
										</div>
									</div>
								</div>
							</div>
							<span id="Msg"></span>

							<div class="btn-group btn-group-lg">
								<input id="checkOutSubmit" type="submit" value="確定結帳"
									class="btn btn-info"> <input type="hidden"
									name="action" value="CheckOutForSure">
									<input type="hidden"
									name="mem_id" value="${memVO.mem_id}">
									<input type="hidden"
									name="acc" value="${memVO.acc}">
									
							</div>
						</form>
					</div>
				</div>
			</div>


		</section>
		<%@ include file="/Footer.jsp"%>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</c:if>
</html>