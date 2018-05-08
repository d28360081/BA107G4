<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/base.css">
    <link rel="icon" href="<%=request.getContextPath() %>/img/gogo.png">
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<style>
.picLayer{
	margin-bottom: 30px;
}
 
.picLayer img{
	width: 878px;
	height: 258px;
	border-radius: 20px 20px 0px 0px;
}
#PmtDetailtitle{
	text-align: center;
	font-size: 40px;
	margin-bottom: 30px;
	color: #000;
	font-weight: 600;
}
#PmtDetailCnt{
	width: 100%;
	max-width: 800px;
	margin: 50px;
	word-break: break-all;
	
}
.PmtSubTitle{
	text-align: center;
	font-size: 30px;
	margin-bottom: 50px;
	color:#424344;
	font-weight: 600;
}
.pmtDelPic{
	width: 150px;
	height: 150px;
	display: inline-block;
}
.pmtDelPic img{
	width:150px;
	height:150px;
}
.picBody{
	max-width: 700px;
	max-height: 900px;
}
.picBody img{
	width:100%;
	height:100%;
}
</style>
</head>
  
<body>
    <%@ include file="/com.HeaderFooter/FrontHeader.jsp"%>
    
	<jsp:useBean class="com.pmt_det.model.PmtDetService" id="pmtservice"/>    
	<div class="container" style="margin-top:20px;">
		<div class="col-xs-12 col-sm-1"></div> 
		<div class="col-xs-12 col-sm-10">
			<!-- 促銷圖片 -->
			<div class="col-xs-12 col-sm-12 picLayer">
			   <img class="card-img" src="<%=request.getContextPath()%>/DBGifReader?table=promotion&pmt_id=${pmtVO.pmt_id}" alt="Card image" >
			</div>	
			<!-- //促銷圖片 -->
			
			<!-- 促銷文字 -->
			<div id="PmtDetailCnt">
				<h5 id="PmtDetailtitle">${pmtVO.pmt_name}</h5>
				<div style="color:red;text-align:center;font-size:20px;" data-countdown="${pmtVO.pmt_e_date}"></div>
				<h4><b>${pmtVO.pmt_intro}</b></h4>
				<!-- 商品藍 -->
				<h6 class="PmtSubTitle">Product</h6>
			</div>
			<!-- //促銷文字 -->
			
			<!-- 商品圖片 -->
			<c:forEach var="pmtDelVO" items="${pmtservice.getByPMT_ID(pmtVO.pmt_id)}">
				<div class="pmtDelPic" data-toggle="modal" data-target="#${pmtDelVO.it_id}"  class="display:inline-block;">
			     	<img src="<%=request.getContextPath()%>/DBGifReader?table=product&it_id=${pmtDelVO.it_id}">
				</div>
				<div class="modal fade" id="${pmtDelVO.it_id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">  
							<div class="modal-body picBody">
								<a href="">
							    	<img src="<%=request.getContextPath()%>/DBGifReader?table=product&it_id=${pmtDelVO.it_id}">
								</a>
							</div>	    
						</div>
					</div>
				</div>				
			</c:forEach>
			<!-- //商品圖片 -->
			
			
		</div> 
		<div class="col-xs-12 col-sm-1"></div> 
	</div>
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/com.pmt.view/js/jquery.countdown.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/com.pmt.view/js/jquery.plugin.js"></script>
</body>
<script type="text/javascript">
$('[data-countdown]').each(function() {
	var $this = $(this), finalDate = $(this).data('countdown');
	$this.countdown(finalDate, function(event) {
		$this.html(event.strftime('%D天%H時%M分%S秒'));
	}).on('finish.countdown', function() {
		$(this)[0].innerText = "促銷活動已結束";
	});
});
</script>
</html>