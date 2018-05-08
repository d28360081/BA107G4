<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/gogo.png">

<style>
.pmtCard{
    width:100%;
    height:380px;
    padding-top: 0px;
    padding-bottom: 10px;
     border-radius: 20px;
     box-shadow: 6px 6px 22px -11px rgba(0,0,0,0.75);
     margin-bottom:40px;
}
.pmtCard:hover{

box-shadow: 6px 10px 22px -8px rgba(0,0,0,0.75);
}
.pmtCard img{
    border-radius: 20px 20px 0 0;
    width:100%;
    height:100%;
}
.pmtCardCnt{
 height:30%;
 overflow:hidden; 
 max-width:100%; 
 text-overflow: ellipsis;
}

.card-title{
    font-weight: 200;
    font-size: 25px;
    margin-bottom: 15px;
}
.card-title p{
font-size: 17px;
}
</style>
</head>

 <body onload="connect();" onunload="disconnect();">
<jsp:include page="/com.HeaderFooter/FrontHeader.jsp"/>
<!-- 中間body部分 -->
  

<div class="container">
<div class="col-xs-12 col-sm-1"></div> 

<div class="col-xs-12 col-sm-10">
<jsp:useBean class="com.pmt.model.PmtService" id="pmtservice"/>
<c:forEach var="pmtVO" items="${pmtservice.onlineAll}" begin="0" end="2">
	 <div class="" style="overflow:hidden; max-width:100%; text-overflow: ellipsis; width: 100%; height: 100%;padding: 20px;" >
		<a href="<%=request.getContextPath()%>/pmt/pmt.do?action=checkPmtDetail&pmt_id=${pmtVO.pmt_id}">
		<div class="card bg-dark text-white pmtCard">
		  <div style="height:70%;width:100%;">
			  <img class="card-img" src="<%=request.getContextPath()%>/DBGifReader?table=promotion&pmt_id=${pmtVO.pmt_id}" alt="Card image" >
			</div>  
			  <div class="card-img-overlay pmtCardCnt">
			    <h5 class="card-title" >${pmtVO.pmt_name}</h5>
			    <p class="card-text">${pmtVO.pmt_intro}</p>
			    <p class="card-text">${fn:substring(pmtVO.pmt_e_date,0,10)}</p>
	        </div>
      </div>
      </a>
	</div>
</c:forEach>
	
	<div id="accordion">
  <div class="">
    <div class="card-header" id="headingThree">
      <h5 class="mb-0">
        <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
           更多案件
        </button>
      </h5>
    </div>
    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
      <div class="card-body">
		    <c:forEach var="pmtVO" items="${pmtservice.onlineAll}" begin="3" >
		     <!-- 案件卡片 -->
				 <div class="" style="overflow:hidden; max-width:100%; text-overflow: ellipsis; width: 100%; height: 100%;" >
					<a href="<%=request.getContextPath()%>/pmt/pmt.do?action=checkPmtDetail&pmt_id=${pmtVO.pmt_id}">
					<div class="card bg-dark text-white pmtCard">
					  <div style="height:70%;width:100%;">
						  <img class="card-img" src="<%=request.getContextPath()%>/DBGifReader?table=promotion&pmt_id=${pmtVO.pmt_id}" alt="Card image" >
						
						</div>  
						  <div class="card-img-overlay pmtCardCnt">
						    <h5 class="card-title">${pmtVO.pmt_name}</h5>
						    <p class="card-text">${pmtVO.pmt_intro}</p>
						    <p class="card-text">${pmtVO.pmt_e_date}</p>
						    
				        </div>
			      	</div>
			      	</a>
				</div>
				<!-- 案件卡片 -->
		</c:forEach>
      </div>
    </div>
  </div>
</div>
	
	
	


</div>
<div class="col-xs-12 col-sm-1"></div> 
</div>

  <!-- 中間body部分 -->
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
	<!-- The Modal -->
	<div class="modal fade" id="myModal">
	  <div class="modal-dialog">
	    <div class="modal-content">      
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">活動更新中...</h4>         
	      </div>       
	      <!-- Modal footer -->
	      <div class="modal-footer">
	         <a href="${pageContext.request.requestURL}">
		         <button type="button" class="btn btn-danger">
		        	Close
		        </button>
	       	</a>
	      </div>
	      
	    </div>
	  </div>
	</div>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
<script>
// webSocket用
    var MyPoint = "/PmtEchoServer/person";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件 基本四個方法 建立 連線 回傳 結束
		webSocket = new WebSocket(endPointURL);//建立
		
		webSocket.onopen = function(event) {// 	連線呼叫server的open的方法 event=事件	
			console.log("WebSocket 成功連線");
		};

		webSocket.onmessage = function(event) { //回傳訊息
			$("#myModal").modal("show");
		};

		webSocket.onclose = function(event) {//結束
			console.log("WebSocket 已離線");
		};
	}
	
	function disconnect() {
		webSocket.close();
	}
</script>
</html>