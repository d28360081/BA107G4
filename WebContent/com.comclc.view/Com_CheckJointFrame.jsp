   <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>GogoShop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath() %>/css/base.css">
    <link rel="icon" href="<%=request.getServletContext().getContextPath() %>/img/gogo.png">
       <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.css" rel="stylesheet"  />
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
<style>
.listtable {
    margin-left: 15px;
    margin-top: 50px;
    border-style: solid;
}

  .ShowDiv{
  padding-bottom:100px;
  height:600px;
  
  }
  
  /* width */
::-webkit-scrollbar {
    width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
    background: #FFFFFF; 
    border-radius:30px;
}
 
/* Handle */
::-webkit-scrollbar-thumb {
    background: #3ab2d9;
        /* Old browsers */
        background: -moz-linear-gradient(45deg, #3ab2d9 0%, #16e8b1 100%);
        /* FF3.6-15 */
        background: -webkit-linear-gradient(45deg, #3ab2d9 0%, #16e8b1 100%);
        /* Chrome10-25,Safari5.1-6 */
        background: linear-gradient(45deg, #3ab2d9 0%, #16e8b1 100%);
        /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3ab2d9', endColorstr='#16e8b1', GradientType=1);
         border-radius:30px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
    background: #555; 
}
  
  
@import url(https://fonts.googleapis.com/earlyaccess/notosanstc.css);
    * {
        font-family: 'Noto Sans TC', sans-serif;
    }
    
    #active{
    text-decoration: none; 
    }
    
    .panel-title{  text-decoration: none;  }
    
  .comcheckjoinFrambody{
     height:900px;
  
  }
    #floatingbutton{
    position:fixed;
    right:100px;
    bottom:50px;
    }
    #floatingbutton:hover{
    position:fixed;
    bottom:53px;    
    }
    .card{
    padding-top:0px;
    margin-top:0px;
    
    }
    table, td, th{       
    background-color: #FFF;
    border: 1px solid #3ab2d9;
    margin-bottom:15px;
    }
   table{
    width:450px;
   }
   .card:hover {
   box-shadow: 0 5px 15px rgba(0,0,0,0.3);
    
   }
   .card{
   margin-bottom:25px;
   margin-top:15px;
   padding-top:20px;
   padding-bottom:20px;
   }
   .unread{
    box-shadow: 0 0 15px #FF3B3B;
   
   }
   .WinnerComImg{
    width: 160px;
    height: 160px;
    border-radius:50%;
   
   }
    .winnerBidCard{
    background-color:#FFE4C4;
    background-image: url("<%=request.getContextPath()%>/img/shopping-bag.png");
    background-repeat:no-repeat;
    background-position:600px 60px;
    }
  
    .JoinBidCard{
    background-color:#77FFCC;
    }
    
    

</style>
</head>

<body>
  <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
    <div class="comcheckjoinFrambody">
    <div class="container sidebar">
        <div class="col-xs-12 col-sm-3">
            <div class="panel-group card" id="accordion2" role="tablist" aria-multiselectable="true">
                <!-- 區塊1 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="panel1">
                        <h4 class="panel-title">
                    <a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa" id="panelborading">
                      查看參予案件
                    </a>
                  </h4>
                    </div>
                    <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
                        <div class="panel-body">
                            <a href="<%=request.getContextPath() %>/Com_ComClc_Controller?type=joinParty">查看參與團購</a>
                            <br>
                            <a href="<%=request.getContextPath() %>/Com_ComClc_Controller?type=joinBuy">查看參與代購</a>
                        </div>
                    </div>
                </div>
                <!-- 區塊2 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="panel2">
                        <h4 class="panel-title">
                    <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
                      查看收藏
                      
                    </a>
                  </h4>
                    </div>
                    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
                        <div class="panel-body">
                            <a href="<%=request.getContextPath()%>/Com_ComClc_Controller?type=checkCollection">查看收藏糾團</a>
                           
                        </div>
                    </div>
                </div>
                <!-- 區塊3 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="panel3">
                        <h4 class="panel-title">
                    <a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
                                                                     歷史案件
                    </a>
                  </h4>
                    </div>
                    <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
                        <div class="panel-body">
                              <a href="<%=request.getContextPath() %>/Com_ComClc_Controller?type=lookupHistory">查看歷史案件</a>                                
                        </div>
                    </div>
                </div>
                <!--區塊四-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="panel4">
                        <h4 class="panel-title">
                    <a href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ddd">
                                                               編輯發起案件
                    </a>
                  </h4>
                    </div>
                    <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel4">
                        <div class="panel-body">
                          <a href="<%=request.getContextPath()%>/Com_ComClc_Controller?type=editSelfItem">編輯發起案件</a> 
                        </div>
                    </div>
                </div>
                <!-- 區塊4結束 -->
                <!-- 區塊5 -->
                       <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="panel5">
                        <h4 class="panel-title">
                     <a href="#eee" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
                                                        查看最新消息
                      
                    </a>
                  </h4>
                    </div>
                    <div id="eee" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel5">
                        <div class="panel-body">
                            <a href="<%=request.getContextPath()%>/ComNofController?type=checkNotification"> 查看最新消息</a>
                           
                        </div>
                    </div>
                </div>
                <!--  -->
                 <!-- 區塊6 -->
                       <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="panel6">
                        <h4 class="panel-title">
                     <a href="#fff" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
                                                       得標案件管理
                      
                    </a>
                  </h4>
                    </div>
                    <div id="fff" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel6">
                        <div class="panel-body">
                            <a href="<%=request.getContextPath()%>/ComNofController?type=WinningBidCheck">得標案件管理</a>
                            
                        </div>
                    </div>
                </div>
                <!--  -->
                
            </div>
        </div>
       		 <div class="col-xs-12 col-sm-9 ShowDiv " style="overflow:auto;background-color:#F0FFFF;">
       		
       		 <!-- 如果是查看最新消息要另外include -->
       		 	<c:if test="${type ne 'checkNotification'}">
    			 <jsp:include page="/com.comclc.view/Com_CheckJointBody.jsp" flush="true"/>
    			 </c:if>
    		
    			 <c:if test="${type eq 'checkNotification'}">
    			   <jsp:include page="/com.comclc.view/ComNofBody.jsp" flush="true"/>
    			 </c:if>
    			 
    			 
     			
     		</div>
     		</div>
     		</div>
    <footer class="footer">
        <div class="row">
            <div class="container add-mobile-gutter">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 fix-col-xs">
                        <div class="row">
                            <ul class="link-list">
                                   <li><a href="<%=request.getContextPath()%>/frontform/News.jsp">最新消息 </a></li>
                                <li><a href="<%=request.getContextPath()%>/BonusStore/bonusstoreIndex.jsp">紅利點數</a></li>
                                <li><a href="<%=request.getContextPath()%>/frontform/AboutUs.jsp">關於我們</a></li>
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
        <!-- 投票燈箱 -->
    <div class="modal" tabindex="-1" role="dialog" id="Votemodal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modal-title"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       
      </div>
      <div class="modal-footer">
        <a href="#" id="voteuri" class="btn btn-go"></a>
        <button type="button" class="btn btn-go btn-close" data-dismiss="modal">關閉</button>
      </div>
    </div>
  </div>
</div>
 <!-- 投票燈箱 -->
     <div  id="floatingbutton">
        <a href="<%=request.getContextPath()%>/Com_Controller?type=checklist&page=1" >
        <img src="<%=request.getContextPath()%>/img/gogo.png"></a>
    </div>
    
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <script>
    $(".nofbtn").click(function(e){
    	$(e.target).removeClass("unread");
    	$.ajax({
    	url:"http://localhost:8081/BA107G4/ComNofController?type=ajax&comnof_id="+e.target.id,
    	dataType:"text",
    	success:function(){}

    })		
    }
    );
    
   
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var endPointURL = "ws://"+window.location.host+webCtx+"/TimeWs/${MemVO.mem_id}";
	                                                      
		    
			 
			var websocket=new WebSocket(endPointURL);
			 websocket.onopen=function(e){
				 
			 }
			 websocket.onerror=function(e){
				 
			 }
			 websocket.onmessage=function(e){	 
				 var msg=JSON.parse(e.data);
				console.log(msg.type);
			    console.log(msg);
			    console.log(msg.type=='info');
			    toastr.info('您有新訊息');
			    
			  if(msg.type=="successFinding"){
				 
				  $("#modal-title").text('恭喜您,案件'+msg.com_id+"準備出團囉");
			      $("#voteuri").text("招募成功");
			      
				  //解析Json物件
				  //用type做判斷
			   
			  }
			  else if(msg.type=="voteConfirm"){
				  
				  $("#modal-title").text('恭喜您,您的案件:'+msg.comtit+"準備出團了,前往頁面編輯給代購人的訊息吧");
				  $("#voteuri").attr("href", msg.editUri);
			      $("#voteuri").text("前往編輯");
			      $("#Votemodal").modal();
				  
				 }else if(msg.type=='info'){
				    	toastr.info(msg.cnt);
				    
				    }
				   
				  
			  else if(msg.type=="cometoVote"){
				       console.log(msg);
				      $("#modal-title").text(msg.com_id);
				      $("#voteuri").attr("href", msg.voteuri);
				      $("#voteuri").text("前往投票");
				      $("#Votemodal").modal();

				  }
			  else if(msg.type=='RecruitFail'){
				  $("#modal-title").text(msg.com_id);
				  $("#voteuri").hide();
				  $("#Votemodal").modal();
			  }
			  else if(msg.type="voteDone"){
				  $("#modal-title").text(msg.cnt);
			      $("#voteuri").attr("href", msg.voteuri);
			      $("#voteuri").remove();
			      $("#Votemodal").modal();
			   }
			  
			    
			  }
			 websocket.onclose=function(e){

		     	}
			  
			 
		  
		 
    
    
    
    </script>
</body>

</html>