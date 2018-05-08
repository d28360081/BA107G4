<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.bonusproduct.model.*"%>
<!DOCTYPE html >
<% 
	MemVO memVO = (MemVO) session.getAttribute("MemVO");
	
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="icon" href="img/gogo.png">
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    
    <c:if test="${not empty errMsgs}">
    	<script>
    		$(document).ready(function (){
    		<c:forEach var="msg" items="${errMsgs}">
    			window.alert('<c:out value="${msg}"/>');
    		</c:forEach>
    		});
    	</script>
    </c:if>
</head>
<body>
	 <%@ include file="/com.HeaderFooter/FrontHeader.jsp"%>
    <div class="gradient"></div>
    <section>
    
    <div class="container">
           <div class="row">
               <div class="col-xs-12 col-sm-3 bread">
                   <a href="">首頁/  </a>紅利訂單資料填寫
               </div>
               <div class="col-xs-12 col-sm-6"></div>
               <div class="col-xs-12 col-sm-3"></div>
           </div>
    </div>
    <div class="container">
        <div class="row">
        <div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">紅利商品訂單資料</h3>
						<c:if test="${not empty errMsgs}">
							<font style="color:#ff8080">請修正以下錯誤:</font>
						<ul>
						<c:forEach var="message" items="${errMsgs}">
							<li style="color:#ff8080">${message}</li>
						</c:forEach>
						</ul>
						</c:if>
					</div>
					<div class="panel-body">
						<form  method="post" class="form-horizontal" action="createBonusOrder">
							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_name">收件人姓名</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="mem_name" name="mem_name" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_ph">收件人電話</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="mem_ph" name="mem_ph"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_add">貨物送達地址</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="mem_add" name="mem_add" value="<%=memVO.getAddress()%>" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-9 col-sm-offset-4">
									<input type="hidden" name="bns_it_id" value="${bns_it_id}">
									<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
                                	<input type="hidden" name="action" value="createOrder">
                                    <input type="submit" class="btn btn-info" value="確認訂購">
                                    <a href="<c:url value="/buyBns"><c:param name="action" value="back"/></c:url>"class="btn btn-go" role="button">取消</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
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
          </div>
    </footer>
    <script></script>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>   
</body>
</html>