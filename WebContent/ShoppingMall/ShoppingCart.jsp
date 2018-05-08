<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.* , com.Product.model.ProductVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.Map"%>
<%@page import="com.member.model.*"%>
<!DOCTYPE html>
<html lang="">

<head>
<meta charset=utf-8>
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

<%	
ProductService service = new ProductService();	
String it_id = (String)session.getAttribute("it_id");
ProductVO product = service.findProduct(it_id);

MemVO memVO = (MemVO) session.getAttribute("MemVO");

request.setAttribute("memVO", memVO);
%>

<%Integer it_num = product.getIt_num();%>

<script type="text/javascript">

	var product_AmountNum=<%=it_num%> 
	

</script>


<script type="text/javascript">


function LoginCheck(form){
	<c:if test="${memVO eq null}">			
	    $('#modal-id').modal('show');
	    return false
	</c:if>	
	    
	<c:if test="${memVO ne null}">	
	    return true
	</c:if>	
	    
}



function deleteitem(it_id) {
    var b = window.confirm("您確定刪除嗎？");
    if(b) {      	

        window.location.href = "${pageContext.request.contextPath }/DeleteItemServlet?it_id="+it_id;
    }else{
    	alertify.error("取消刪除");
    	return false
    }
}

function clearCart(form) {
	var b = window.confirm("您確定清除嗎？");
    if(b) {  
        return true;
    }else{
    	alertify.error("取消清除");
    	return false;
    }
    
}




function changeQuantity(input, it_id, oldValue) {
    var it_num = input.value; // 得到要修改的数量   
  
    if(it_num<=0 || it_num!=parseInt(it_num)) {    // 1.1 != 1     parseInt("abc")返回NaN
        alert("請輸入一個正整數！！！");
        input.value = oldValue;
        return;
    }
   
    
    if(it_num>product_AmountNum){
    	alert("購買數量超過商品數量！！！");
        input.value = oldValue;
        return;
    }
    
 
    var b = window.confirm("您確定把商品的數量修改為"+it_num+"嗎？")
    if(b) {
        window.location.href = "${pageContext.request.contextPath }/ChangeQuantityServlet?it_id="+it_id+"&it_num="+it_num;
    }else{    	
    	alertify.error("取消修改");
    	input.value = oldValue;
    }
    
}
</script>







<!--   自己寫的css   -->
<!-- <link rel="stylesheet" href="css/OOXX.css"> -->
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>

<body>
	<c:if test="${empty(cart.map)}">
			 <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
		<section>
			<div>
				<h1>您沒有購買任何商品！！！</h1>
			</div>
		</section>
		<%@ include file="/Footer.jsp"%>
	</c:if>


	<c:if test="${!empty(cart.map) }">
	
 <!-- 未登入 -->
                                  
                    <div class="modal fade" id="modal-id">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="<%=request.getContextPath()%>/member/login" method="post">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title">會員登入</h4>
                                    <div align="right">沒有帳號嗎?
                                    <a href="register2.jsp">註冊會員</a>
                                    </div>
                                </div>
                                <div class="modal-body">

                                    <div class="input-group">
                                      <label for="acc" class="col-xs-12 col-sm-3 control-label data">
                                          帳號
                                      </label>
                                      <div class="col-xs-12 col-sm-9 data">
                                          <input type="text" name="acc" id="acc" placeholder="" class="form-control" value="">
                                      </div>
                                      <label for="psw" class="col-xs-12 col-sm-3 control-label data">
                                          密碼
                                      </label>
                                      <div class="col-xs-12 col-sm-9 data">
                                          <input type="password" name="psw" id="psw"  class="form-control" value="">
                                      </div>
                                    </div>
                                    <div>
                                    <c:if test="${not empty errMsg}">
                                    		${errMsg}
                                    </c:if>
									</div>
								</div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-gogo" data-dismiss="modal">取消</button>
                                    <input type="hidden" name="action" value="login">
                                    <input type="submit" class="btn btn-info" value="登入">
                                </div>
                                </form>
                            </div>
                           
                        </div>
                    </div>                  
                
                   
                  <!-- 會員登入視窗 --> 
	
	
	
		<%@ include file="/com.HeaderFooter/FrontHeader.jsp"%>
		<div class="gradient"></div>
		<section>
			<div class="container">
				<c:forEach var="cart" items="${cart.map}">
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
								<th class="center  mytable"></th>
							</tr>
						</thead>
						<tbody>
							<tr>

								<td class="center  mytable"><img
									src="${pageContext.request.contextPath }/ProductImageReader?it_id=${cart.value.product.it_id}"
									class="minjpg"></td>
								<td class="center  mytable"
									style="word-break: break-all; word-wrap: break-all;">${cart.value.product.it_name}</td>
								<td class="center  mytable"
									style="word-break: break-all; word-wrap: break-all;">${cart.value.product.it_intro}</td>
								<td class="center  mytable">$<fmt:formatNumber
										value="${cart.value.product.it_prc}" pattern="#"
										type="number" /></td>
								<td>
									 <input type="text" id="itemnum"
									name="it_num" value="${cart.value.it_num}" style="width: 35px"
									onchange="changeQuantity(this, '${cart.key}', '${cart.value.it_num}')" />
								</td>

								<td class="center  mytable">$<fmt:formatNumber
										value="${cart.value.it_prc}" pattern="#" type="number" /></td>
								<td class="center  mytable"><a href="javascript:void(0)">
										<input type="submit" value="刪除" class="btn btn-info"
										onclick="deleteitem('${cart.key}')">
								</a></td>
							</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="7">
									<div class="container">
										<div class="row">
											<div class="col-xs-12 col-sm-2">
												<div class="btn-group btn-group-lg">
													<form
														action="${pageContext.request.contextPath }/TestController"
														method="post" onsubmit="return clearCart(this)">
														<input type="submit" value="清空購物車" class="btn btn-info">
														<input type="hidden" name="action" value="clearCart">
													</form>
												</div>

											</div>





											<div class="col-xs-12 col-sm-2 rightPL0">
												<form
													action="${pageContext.request.contextPath }/TestController"
													method="post" onsubmit="return LoginCheck(this)">
													<div class="btn-group btn-group-lg">
														<input type="submit" value="結帳" class="btn btn-info">
														<input type="hidden" name="action" value="checkOut"
															class="btn btn-info">
													</div>
												</form>
											</div>
											<div class="col-xs-12 col-sm-2 right">
												<form
													action="${pageContext.request.contextPath }/ShoppingMall/ShoppingMall.jsp"
													method="post">
													<div class="btn-group btn-group-lg">
														<input type="submit" value="繼續購物" class="btn btn-info">
													</div>
												</form>
											</div>

											<div></div>


										</div>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
					<div>
						<b class="amountRed">總金額:$<fmt:formatNumber
								value="${cart.price}" pattern="#" type="number" /></b>
					</div>
			</div>



		</section>
		<%@ include file="/Footer.jsp"%>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</c:if>
</body>

</html>