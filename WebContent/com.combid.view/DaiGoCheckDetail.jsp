<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
 <%@page import="com.combid.model.*"%>
 <%@page import="com.Product.model.ProductService"%>
<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/gogo.png">
    <script src="https://cdn.ckeditor.com/4.9.1/standard/ckeditor.js"></script>
      <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.css" rel="stylesheet"  />
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
    
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        
        <style>
        .mainbody{
        margin-bottom:50px;
        
        }
        #panel1 img{
        width:680px;
        height:450px;
        padding-bottom:30px;
        }
        .modalCus{
        padding-top:25px;
        padding-bottom:25px;
        padding-left:90px;
        padding-right:90px;
        }
        .editor1{
        margin-top:30px;
        padding-bottom:30px;
        }
        .gogocard{
            border-radius: 5px;
            background-color: #FFF;
            border: 1px solid #39D0CB;
            
        }
        .commentcard{
            border-radius: 5px;
            background-color: #FFF;
            border: 1px solid #2894ff;
            margin-bottom:15px;
        }
        .panel3body{
           padding-top:50px;
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
        
        
        </style>
</head>

<body>
<jsp:useBean class="com.cocmt.model.ComComtService" id="ComComtSrv"/>
         <jsp:useBean class="com.combid.model.ComBidService" id="combidservice"/>
         <jsp:useBean class="com.compant.model.ComPantLsService" id="compantlsservice"/>
         <jsp:useBean class="com.member.model.MemDAO" id="memdao"/>
<jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
<jsp:useBean class="com.comclc.model.ComClcService" id="comclcservice"/>
   <div class="container">   
        <div class="col-xs-12 col-sm-2"></div>
        <div class="col-xs-12 col-sm-8" style="margin-top:80px;">
            
<!-- Nav tabs -->
<ul class="nav nav-tabs nav-justified">
    <li class="nav-item ">
        <a class="nav-link active" data-toggle="tab" href="#panel1" >案件資訊</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#panel2" >案件留言</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#panel3" role="tab">案件詳情</a>
    </li>
</ul>
<!-- Tab panels -->
<div class="tab-content gogocard mainbody">
    <!--Panel 1-->
    <div class="tab-pane fade in active mainbody " id="panel1" role="tabpanel" style="padding: 2%;">
        <br>
        <p>  
            <h1>${ComVO.com_tit}  
	                <c:if test="${empty comclcservice.select(MemVO.mem_id,ComVO.com_id)}"> 
	                  <a href="<%=request.getContextPath()%>/Com_ComClc_Controller?type=addCollection&com_id=${ComVO.com_id}" type="button" class="glyphicon glyphicon-heart cusheart"> </a>
	               </c:if>
	               
	            
	                <c:if test="${not empty comclcservice.select(MemVO.mem_id,ComVO.com_id)}"> 
	                   <span class="glyphicon glyphicon-heart" data-toggle="已經加入收藏" data-placement="right" title="已經加入收藏"></span>
	                 </c:if>
            </h1><br>
            
            
        	<img src="<%=request.getContextPath()%>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC1" style="margin-bottom:60px;width:100%;"><br>
            <img src="<%=request.getContextPath()%>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC2" style="margin-bottom:60px;width:100%;"><br>
             ${ComVO.com_rmd}<br>
            <h5 style="color:red;padding: 4%;">代購結束時間為: ${ComVO.lmt_m_dt}</h5>
            <h5 style="color:red;">代購人權限要求為: ${ComVO.lmt_atd_clv }</h5>
             <!-- Button trigger modal -->
             
          <c:if test="${type eq 'checkDaiGodetail'}">
             <c:if test="${combidservice.select(MemVO.mem_id,ComVO.com_id) eq null}">
				<button type="button" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green" data-toggle="modal" data-target="#exampleModalCenter">
  						參與代購競標
				</button>
           </c:if>
          </c:if>
        </p>
    </div>
    <!--/.Panel 1-->
    <!--Panel 2-->
    
    <div class="tab-pane fade mainbody" id="panel2" role="tabpanel" style="overflow:auto;">
    
        <br>
       
      
        <c:forEach var="ComComt" items="${ComComtSrv.selectAll(ComVO.com_id)}" begin="0" end="5">
        
       <div class="commentcard" style="margin-right:15px;margin-left:15px;">
			
			 
  				 <div class="card-body">
  	   			 <h5 class="card-title">${memdao.findByPrimaryKey(ComComt.mem_id).acc}說:</h5>
       			 <p class="card-text">${ComComt.comt_cnt}</p>
        		 
        		 </div>
      </div>
       </c:forEach>
       
          <div class="accordion" id="accordion2">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" id="ComtCollaspse">
						<img src="<%=request.getContextPath()%>/img/Comt_Down_Arrow.png" id="Comt_Down_Arrow"> 更多留言
					</a>
				</div>
				<div id="collapseOne" class="accordion-body collapse" style="height: 0px; ">
					<div class="accordion-inner">
					 <c:forEach var="ComComt" items="${ComComtSrv.selectAll(ComVO.com_id)}" begin="6" end="${ComComtSrv.selectAll(ComVO.com_id).size()}">
					   <div class="commentcard" style="margin-right:15px;margin-left:15px;">
					    <div class="card-body">
					                                
  	   			        	 <h5 class="card-title">${memdao.findByPrimaryKey(ComComt.mem_id).acc}說:</h5>
       			 			<p class="card-text">${ComComt.comt_cnt}</p>
        					
        				 </div>
        				</div> 
        			 </c:forEach>
					</div>
				</div>
			</div>
	   </div>       

       
       
       
        <button type="button" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green" data-toggle="modal" data-target=".bd-example-modal-lg" style="box-shadow: 0px 3px 6px 0px rgba(0, 0, 0, 0.3);
    margin-bottom: 20px;margin-top:30px;">留言</button>
    </div>
    
    <!--/.Panel 2-->
  <!--Panel 3-->
  <div class="tab-pane fade mainbody" id="panel3" role="tabpanel" >
  <div class="panel3body" style="margin-top:40px;">
  
<!-- 沒有參與代購競標或是在團購參與名單內 -->

<c:forEach var="combidvo" items="${combidservice.selectVoteCandidate(ComVO.com_id) }">


</c:forEach>


 <c:if test="${compantlsservice.toMap(compantlsservice.selectAllinCase(ComVO.com_id)).containsKey(MemVO.mem_id)||combidservice.toMap2(combidservice.selectVoteCandidate(ComVO.com_id)).containsKey(MemVO.mem_id)}">
 <!-- 準備進行投票狀態 -->   
    <c:if test="${ComVO.com_sts eq '準備進行投票'||ComVO.com_sts eq '投票結束'}">
        <br>
   
    <c:if test="${compantlsservice.selectUnVoted(ComVO.com_id).containsKey(MemVO.mem_id) eq true}">
        <span style="font-size:25px;">目前狀態: <p class="lead">${ComVO.com_sts}</p></span><br>
         <span style="font-size:36px;">您的案件已經開始投票了,<br>趕快前往投票吧</span><br>
          <div style="border:1px solid #3ab2d9; border-radius:15px;padding:10px;margin-top:20px;">
           <a href="<%=request.getContextPath()%>/com.combid.view/ComBidVote.jsp?com_id=${ComVO.com_id}">前往投票</a>      
          </div>  
    </c:if>
        
        <c:if test="${compantlsservice.selectUnVoted(ComVO.com_id).containsKey(MemVO.mem_id) eq false}">  
       <p style="font-size:30px;"> 投票完成</p>
         <c:forEach var="combidvo" items="${combidservice.selectVoteCandidate(ComVO.com_id)}">
          
		         <div class="card" style="margin-bottom:20px; margin: 20px;">
		  					<div class="card-header candidateCard"style="font-size:20px;">
		  						  ${memdao.findByPrimaryKey(combidvo.mem_id).acc}
		  						  <div >
		  						    <img src="" style="
    height: 100px;
    width:  100px;
    border-radius: 50%;
    display: inline-block;
    float:left;
    margin:15px;
    
    padding-right: 0px;
		  						    ">
		  						  </div>
		  					</div>
		 			 <div class="card-body">
		 			   	<blockquote class="blockquote mb-0" style="text-align:left;">
		    			 		 <p>競標價格: ${combidvo.auc_prc}</p>
		    			 		 <p>競標運費: ${combidvo.auc_del_prc}</p>
		    					  <footer class="blockquote-footer">
		    					    已得票:${combidvo.n_o_v}
		    					  </footer>
		  			 	 </blockquote>
		 			 </div>
				</div>
           
               
           </c:forEach>
          </c:if>
     </c:if>
        
    <!-- 招募代購人狀態 -->    
        <c:if test="${ComVO.com_sts eq '招募代購人'}">
         <span style="font-size:25px;">目前狀態: <p class="lead" style="font-size:36px;">${ComVO.com_sts }</p></span><br>
         <span style="font-size:25px;font-color:red;">投票時間:${fn:substring(ComVO.lmt_m_dt, 0, 16)}</span><br>
          <span style="font-size:25px;font-color:red;"></span><br>
         <p class="text-lg-left" style="font-size:25px;font-color:#787878;">目前參與代購者</p><br>
       
        
         <c:forEach var="candidateVO" items="${combidservice.selectVoteCandidate(ComVO.com_id)}">
              <p class="text-lg-left" style="font-size:25px;font-color:#919191;">${memservice.getOneMem(candidateVO.mem_id)}</p><br>
         </c:forEach>
        </c:if>
        
        
        <!-- 已經參與競標或在團購內 -->
        <c:if test="${ComVO.com_sts eq '等待主辦人編輯代購訊息'}">
        <span style="font-size:25px;">目前狀態: <p class="lead" style="font-size:36px;">${ComVO.com_sts}</p></span><br>
         
         <p class="text-lg-left" style="font-size:25px;font-color:#787878;">系統已經通知主辦人,<br>編輯完將通知會員進入團體聊天室</p><br>
         </c:if>
       </c:if>
        <!-- <a href="<%=request.getContextPath()%>/Com_Controller?type=goDaiGo&page=1" type="button" class="btn btn-go" style="margin-top:70px;">
        		      回首頁
         </a>  -->
      
        <!-- 不再團購或競標內 -->
        <c:if test="${compantlsservice.toMap(compantlsservice.selectAllinCase(ComVO.com_id)).containsKey(MemVO.mem_id) eq false&&combidservice.toMap2(combidservice.selectVoteCandidate(ComVO.com_id)).containsKey(MemVO.mem_id) eq false}">

            <c:if test="${type eq 'checkDaiGodetail'}">
       
             <span style="font-size:25px;font-color:red;">此處為參與成員或競標成員專屬,<br>參與後才能看到詳細資訊</span><br>
				<button type="button" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green" data-toggle="modal" data-target="#exampleModalCenter" style="margin-top:60px;">
  						參與代購競標
				</button>
         
          	</c:if>
          </c:if>
     
        <!-- 不再團購或競標內 -->
        </div>
        
    </div>
    <!--/.Panel 3-->
</div>
			 </div>
			 <div class="col-xs-12 col-sm-2"></div>
			
       

    
    </div>
    
    
    
    
 
  
    
    
  
  <!-- 跳窗 -->
				<div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
				
  					<div class="modal-dialog modal-lg">
    						<div class="modal-content card blue modalCus">
    						  <form method="post" action="<%=request.getContextPath()%>/ComComtController">
    						         留言內容
    						         <div class="editor1">
                                <textarea name="editor1" ></textarea>
									<script>
		                             	CKEDITOR.replace( 'editor1' );
									</script>
									<input type="hidden" name="com_id" value="${ComVO.com_id}">
									<input type="hidden" name="type" value="comment">
									</div>
									<button type="submit" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green">送出</button>
									</form>
   						 	</div>
 				 	</div>
 				  
				</div>
     
  <!-- 跳窗 -->
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
  


<!-- 代購競標 跳窗 -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle" style="font-size: 27px;margin-bottom: 30px;">${ComVO.com_tit}<br> 的參與競標</h5>
         <h5 style="color:red;font-size:17px;">代購結束時間: ${fn:substring(ComVO.lmt_m_dt, 0, 16)}</h5>
         <h5 style="color:red;font-size:17px;">代購人權限要求: ${ComVO.lmt_atd_clv }</h5>
         <h5 style="color:red;font-size:17px;">最低價格: ${ComVO.lmt_m_prc}</h5>
         <h5 style="color:red;font-size:17px;">最低運費: ${ComVO.lmt_del_prc}</h5>
          <h6 style="color:red;font-size:17px;margin-top:30px;" id="ErrorModalMeg"></h6>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="<%=request.getContextPath()%>/CombidController" method="post">
        
                <div class="input-group comtitle" style="margin-bottom: 30px;">
                    <span class="input-group-addon" id="basic-addon1">競標價格</span>
                    <input type="text" class="form-control" aria-describedby="basic-addon1" name="auc_prc" id="auc_prc">
                </div>
                
                <div class="input-group comtitle" style="margin-bottom: 30px;">
                    <span class="input-group-addon" id="basic-addon1">競標運費</span>
                    <input type="text" class="form-control" aria-describedby="basic-addon1" name="auc_del_prc" id="auc_del_prc">
                </div>
        
                 <input type="hidden" name="com_id" value="${ComVO.com_id}">
                 <input type="hidden" name="type" value="BiddingAudit">
        
      
      <div class="modal-footer" >
        <button type="button" class="btn btn-secondary btn-go" data-dismiss="modal" style="height:40px;">Close</button>
        <button type="submit" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green" id="bidsubmitbtn" style="margin-right: 170px;">送出</button>
      </div>
      </form>
      </div>
    </div>
  </div>
</div>
  <!-- 代購競標 跳窗-->
  
  <div class="row">
  <div class="col-xs-12 col-sm-12">
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
            </div>
    </footer>
    </div>
  </div>
  
</body>
    
    <script src="https://code.jquery.com/jquery.js">
    
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
  $(function(){

  
       $("#bidsubmitbtn").click(function(){
    	 var lmt_m_prc=${ComVO.lmt_m_prc};
         var lmt_del_prc=${ComVO.lmt_del_prc};
       
    	   var reg=new RegExp("^[0-9]*$");
    	 
    
    	   
    	   if($.trim($("#auc_prc").val()).length>0){
    		   if($.trim($("#auc_del_prc").val()).length>0){
    			   
         	if(!reg.test($("#auc_prc").val())){
              	alert("競標價格請輸入數字");
              	 console.log('00');
              	return false;
              	
              	if(!reg.test($("#auc_del_prc").val())){
             		alert("競標運費請輸入數字");
                     console.log('1');
                  	return false;
                  	
                  	if($("#auc_prc").val()<lmt_m_prc){
                 		$( "#exampleModalCenter" ).effect( "shake" );
                 		$("#ErrorModalMeg").text("出價不得小於最小價格");
                 		 console.log('2');
                 		return false;
                 		
                 		

                     	if($("#auc_del_prc").val()<lmt_del_prc){
                     		$( "#exampleModalCenter" ).effect( "shake" );
                     		$("#ErrorModalMeg").text("出價不得小於最小運費");
                     		return false;
                     	}
                 	}
             	}
         	
         	
         	}
    		   }else{
    			   console.log('22222');
        		   return false;
        	   }
    	   }else{
    		   alert('競標價格請勿空直');
    		   return false;
    	   }
         	
         	
         	
         
       });
       
       
       $("#ComtCollaspse").click(function(){
    	   
    	  if($("#Comt_Down_Arrow").attr("src")=="<%=request.getContextPath()%>/img/Comt_End1.png"){
    		  $("#Comt_Down_Arrow").attr("src","<%=request.getContextPath()%>/img/Comt_Down_Arrow.png");
    	  } 
    	  else{
    		  $("#Comt_Down_Arrow").attr("src","<%=request.getContextPath()%>/img/Comt_End1.png");
    		
    	  } 
    	 
       
       });
       
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
   			        console.log('9901');
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
   		  
   		 
       
       
       
       
       
  });
     
    
    </script>
</html>



