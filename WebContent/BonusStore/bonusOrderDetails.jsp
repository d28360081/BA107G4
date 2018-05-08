<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.bonusproduct.model.*"%>
<%@ page import="com.bonusproductorder.model.*"%>
<%@ page import="com.bonusorderdetail.model.*"%>
<!DOCTYPE html >
<%
	MemVO memVO = (MemVO)session.getAttribute("MemVO");
	BonusproService bnsSrc = new BonusproService();
	List<BonusProductOrderVO> listor = bnsSrc.getMemAllOrder(memVO.getMem_id());
	List<BonusProductOrderdetailVO> listod = bnsSrc.getAllDetail();
	List<BonusProductVO> listpro = bnsSrc.getAll();
	pageContext.setAttribute("listod", listod);
	pageContext.setAttribute("listor", listor);
	pageContext.setAttribute("listpro", listpro);
%>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" href="img/gogo.png">
    <style type="text/css">

         #page{
                margin-left:    1000px;
                margin-bottom:20px;
            }
    </style>
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
</head>
</head>
<body>
	<%@ include file="/com.HeaderFooter/FrontHeader.jsp"%>
    <div class="gradient"></div>
    <section>
        <!-- 從這裡開始寫吧 -->
        <!--         <input type="text" class="form-control" style="width: 200px;">
        <button type="submit" class="btn btn-default btn-go ">搜尋</button> -->
<!-- <div class="container">

<table class="table table-striped" >
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
</div> -->
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-3">
                <a href="index.html">首頁/  </a><a href="memberIndex.html">會員專區/  </a>紅利商品訂單管理
            </div>
            <div class="col-xs-12 col-sm-6"></div>
            <div class="col-xs-12 col-sm-3"></div>
        </div>
    </div>
    <div class="container main">
        <div class="row">
            <!-- <div class="col-xs-12 col-sm-9"> -->
                
                
                    <!-- 標籤面板：內容區 -->
                  
                       <div id="page">
							<%@ include file="pages/page_order2.file" %>
       				   </div>
       				   <br>
                            <table class="table table-hover table-striped table-bordered table-condensed">
                                <thead>
                                    <th>訂單編號</th>
                                    <th>收件人姓名</th>
                                    <th>消費紅利</th>
                                    <th>訂購日期</th>
                                    <th>訂購商品</th>
                                    <th>貨物狀態</th>
                                </thead>
                                <tbody>
                                    <c:forEach var="pucorVO" items="${listor}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
                                    <tr>
                                    	<td>
                                    		${pucorVO.bns_rec_id}
                                    	</td>
                                    	<td>${pucorVO.mem_name}</td>
                                    	<td>${pucorVO.sum_prc}</td>
                                    	<td>${pucorVO.pur_date}</td>
                                    	<td>
                                    		<c:forEach var="ordel" items="${listod}">
                                    		<c:if test="${pucorVO.bns_rec_id == ordel.bns_rec_id}">
                                    			<c:forEach var="product" items="${listpro}">
                                    				<c:if test="${ordel.bns_it_id == product.bns_it_id}">
                                    					${product.bns_it_name}
                                    				</c:if>
                                    			</c:forEach>
                                    			
                                    		</c:if>
                                    		</c:forEach>
                                    	</td>
                                    	<td>
                                    	${pucorVO.rec_dlv_sts}
                                    	<a href="#${pucorVO.bns_rec_id}" data-toggle="modal" id="checkSts"><img src="https://cdn2.iconfinder.com/data/icons/ios-tab-bar/25/Check_Circle-16.png"/></a>
                                    	</td>
                                    </tr>
                                    <c:if	test="${pucorVO.rec_dlv_sts=='已收貨'}">
                                    	<script>
                                    		var check = document.getElementById("checkSts");
                                    		check.href="#already";
                                    	</script>
                                    </c:if>
                                    <div class="modal fade" id="${pucorVO.bns_rec_id}">
										<div class="modal-dialog">
											<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
												<h4 class="modal-title">您收到貨物了嗎?</h4>
											</div>
											<div class="modal-body">
												<form action="<%=request.getContextPath()%>/buyBns" method="post">
                  								<input type="hidden" name="action" value="getProduct">
                  								<input type="hidden" name="bns_rec_id" value="${pucorVO.bns_rec_id}">
                  								<button type="button" class="btn btn-default" data-dismiss="modal">我還沒收到QQ</button>
                  								<input type="submit" class="btn btn-info"  value="我收到了">
                  								</form>	
											</div>
											
											</div>
										</div>
									</div>
									<div class="modal fade" id="already">
										<div class="modal-dialog">
											<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
												<h4 class="modal-title">您已經收貨了</h4>
											</div>
											<div class="modal-body">
                  								<button type="button" class="btn btn-default" data-dismiss="modal">關閉此視窗(´・ω・`)</button>
											</div>
											
											</div>
										</div>
									</div>
                                    </c:forEach>
                                </tbody>
                            </table>
                            
                            <%@ include file="pages/page_order1.file" %>
                
            <!-- </div> -->
        
    </div>
    
    </section>
    <footer class="footer">
        <div class="row">
            <div class="container add-mobile-gutter">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 fix-col-xs">
                        <div class="row">
                            <ul class="link-list">
                                <li><a href="FAQ_member.asp">會員制度</a></li>
                                <li><a href="FAQ_bonus.asp">紅利點數</a></li>
                                <li><a href="FAQ_order.asp">購物須知</a></li>
                                <li><a href="FAQ_returns-exchanges.asp">售後服務</a></li>
                                <li><a href="FAQ_order-overseas.asp">INTERNATIONAL ORDERS</a></li>
                                <li><a href="FAQ_anti-fraud.asp">防詐騙</a></li>
                            </ul>
                            <ul class="link-list">
                                <li><a href="about.asp">關於我們</a></li>
                                <li><a href="news.asp?poid=452">媒體露出</a></li>
                                <li><a href="news.asp?poid=450">工作職缺</a></li>
                                <li><a href="FAQ_contact.asp">合作聯絡</a></li>
                                <li><a href="journal.asp?conditiontype=1&amp;poids=573">關懷社會</a></li>
                                <li><a href="journal.asp?conditiontype=1&amp;poids=569">活動分享</a></li>
                            </ul>
                            <ul class="link-list">
                                <li><a href="TWCA.asp" target="_blank"><span class="lock"></span>安心購物</a></li>
                                <li><a href="LEGAL_privacy-policy.asp">隱私權</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
    </footer>
    <script></script>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>