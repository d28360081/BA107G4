<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="java.util.*"%>
    <%@ page import="com.cocmt.model.*" %>
    <%@ page import="com.combid.model.*" %>
    <%@ page import="com.compant.model.*" %>
    <!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop-${ComVO.com_tit }</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/CheckDetail.css">
      <script src="https://cdn.ckeditor.com/4.9.1/standard/ckeditor.js"></script>
    <link rel="icon" href="<%=request.getContextPath()%>/img/gogo.png">
   
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>  
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
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
        
        
       .viewbarimg{
       margin-right:50px;
       }
      
       .btn-danger{
       background-color:#FFA488;
       }
       .btn-info{
        background-color:#77FFCC;
       
       }
       .card a {
        color:#111;
       }
         #panel1 img{
        width:680px;
        height:450px;
        padding-bottom:30px;
        border-radius:5px;
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
        .card{
        margin-bottom:3px;
        margin-left:4px;
        margin-right:4px;
        }
       
         .nav-link:hover {
           
	       box-shadow: 2px -2px 2px #DDDDDD;
         }
         .cusbtn{
         margin-top:30px;
         }
         .cusheart:before {
          color:red;
         }  
         .com_tit{
         margin-bottom:35px;
         }
        .footer{
        margin-bottom:5px;
        }
        .progress{
        margin-left:20px;
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
        
        
#panel3 {
padding-top:150px;
    padding-left: 60px;
    padding-right: 60px;
margin-bottom:400px;
 }
.textblow{
margin-top:300px;
margin-right:60px;
}
 .commentcard{
            border-radius: 5px;
            background-color: #FFF;
            border: 1px solid #2894ff;
            margin-bottom:15px;
        }
</style>

</head>




<body>
    <jsp:useBean class="com.compant.model.ComPantLsService" id="compantsrv"/>
    <jsp:useBean class="com.comclc.model.ComClcService" id="comclcservice"/>
<jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
	


    <div class="container">
        <div class="col-xs-12 col-sm-2"></div>
        <div class="col-xs-12 col-sm-8" style="margin-top:80px;">
            
<!-- Nav tabs -->
<ul class="nav nav-tabs nav-justified">
    <li class="nav-item">
        <a class="nav-link active " data-toggle="tab" href="#panel1" role="tab">案件資訊</a>
    </li>
    <li class="nav-item ">
        <a class="nav-link " data-toggle="tab" href="#panel2" role="tab">留言</a>
    </li>
    <li class="nav-item ">
        <a class="nav-link " data-toggle="tab" href="#panel3" role="tab" id="tabforpanel3">案件詳情</a>
    </li>
</ul>
<!-- Tab panels -->
<div class="tab-content card" style="padding: 2%;">
    <!--Panel 1-->
    <div class="tab-pane fade in active" id="panel1" role="tabpanel">
        <br>
        <p>  
                <div class="com_tit">
                <span  style="font-size:30px;" >${ComVO.com_tit}</span>
           
                
               <c:if test="${empty comclcservice.select(MemVO.mem_id,ComVO.com_id)}"> 
                  <a href="<%=request.getContextPath()%>/Com_ComClc_Controller?type=addCollection&com_id=${ComVO.com_id}" type="button" class="glyphicon glyphicon-heart cusheart"> </a>
               </c:if>
              
                <c:if test="${not empty comclcservice.select(MemVO.mem_id,ComVO.com_id)}"> 
                   <span class="glyphicon glyphicon-heart" data-toggle="已經加入收藏" data-placement="right" title="已經加入收藏"></span>
                 </c:if>
                 
               
                </div>
             																	
             <br>
             
               
                       
        	<img src="<%=request.getContextPath()%>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC1" style="margin-bottom:60px;    width: 100%;"><br>
            <img src="<%=request.getContextPath()%>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC2" style="margin-bottom:60px;    width: 100%;"><br>
             ${ComVO.com_cnt}<br>
            <c:if test="${type eq 'checkDaiGodetail'}">  
            <h5 style="color:red;padding: 4%;">代購結束時間為: ${ComVO.lmt_m_dt}</h5>
            </c:if>
            
            <c:if test="${type ne 'checkDaiGodetail'}">  
             <h5 style="color:red;padding: 4%;">團購結束時間為: ${ComVO.com_e_dt}</h5>
            </c:if>
            
            <h5 style="color:red;padding: 4%;">代購人權限要求為: ${ComVO.lmt_atd_clv }</h5>
             <!-- Button trigger modal -->
             <jsp:useBean id="ComBidSrc" class="com.combid.model.ComBidService"/>
         
                   
                <div class="mb30">              
                    <div class="row">
                  <%String join=(String)request.getAttribute("join"); %>
                  <%String unjoin=(String)request.getAttribute("unjoin");%>
                 
                   <div class="col-xs-12 col-sm-4 joinbuttondiv">
                       <c:if test="${type ne 'checkDaiGodetail'}">
                          <c:if test="${join ne 'none'}">
                            <a href="<%=request.getContextPath()%>/Com_PantLs_Controller?type=joinCommision&com_id=${ComVO.com_id}" type="button" class="btn btn-info" style="display:<%=join%>;border:none;border-radius:50px;"><span class="glyphicon glyphicon-plus">
                            </span> 加入代購</a>
                           </c:if>
                        </c:if> 
                        
                        <c:if test="${type ne 'checkDaiGodetail' }">
                           <c:if test="${join eq 'none'}">
                             <a href="#" type="button" class="btn btn-info" disabled style="border:none;border-radius:50px;"><span class="glyphicon glyphicon-plus">
                             </span> 已經加入</a>
                            </c:if>
                        </c:if>
                        </div>
                 
                  		 <div class="col-xs-12 col-sm-4">
                     		<c:if test="${type ne 'checkDaiGodetail'}" >

                            <a href="<%=request.getContextPath()%>/Com_PantLs_Controller?type=leaveCommision&com_id=${ComVO.com_id}" type="button" class="btn btn-danger" style="display:<%=unjoin %>; border:none;border-radius:50px;"><span class="glyphicon glyphicon-minus">
                            </span> 退出代購</a>
                     	    </c:if>     
                  		 </div>
                   
                  		    <c:if test="${type eq 'checkDaiGodetail'}">
                     		 <a href="<%=request.getContextPath()%>/Com_PantLs_Controller?type=joinBidding&com_id=${ComVO.com_id}" type="button" class="btn btn-info" id=""><span class="glyphicon glyphicon-plus">
                            </span> 代購競標</a>
                            </c:if>
                  
                  
                        
                            <div class="col-xs-12 col-sm-4">
                             <a href="#modal-appeal" type="button" data-toggle="modal" class="btn btn-warning" style="border:none;border-radius:50px;" id="btn-appeal"><span class="glyphicon glyphicon-warning-sign"  ></span>檢舉代購</a>
                            </div>
                    </div>
                </div>
     
        </p>
    </div>
    <!--/.Panel 1-->
    <!--Panel 2-->
    
     <div class="tab-pane fade mainbody" id="panel2" role="tabpanel" style="overflow:auto;">
            <jsp:useBean class="com.member.model.MemDAO" id="memdao"/>
        <br>
       
        <jsp:useBean class="com.cocmt.model.ComComtService" id="ComComtSrv"/>
        
        <c:forEach var="ComComt1" items="${ComComtSrv.selectAll(ComVO.com_id)}" begin="0" end="5">
        
       <div class="commentcard" style="margin-right:15px;margin-left:15px;">
			
			 
  				 <div class="card-body">
  	   			 <h5 class="card-title">${memdao.findByPrimaryKey(ComComt1.mem_id).acc}說:</h5>
       			 <p class="card-text">${ComComt1.comt_cnt}</p>

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

   			 <div class="tab-pane fade" id="panel3" role="tabpanel">
   			 
   			 <img src="<%=request.getContextPath()%>/img/gogo.png" style="height:170px;width:170px;margin-bottom:120px;"><br>
        		<!-- 尚未加入團購 -->
        		<c:if test="${!((compantsrv.toMap(compantsrv.selectAllinCase(ComVO.com_id))).containsKey(MemVO.mem_id))}">
        		<br>
        		<span style="font-size:30px;font-color:#7D7D7D;">尚未加入團購,此畫面為加入會員特有資訊</span><br>
        		 <a href="<%=request.getContextPath()%>/Com_PantLs_Controller?type=joinCommision&com_id=${ComVO.com_id}" type="button" class="btn btn-go" style="display:<%=join%>;margin-top:120px;"> 加入代購</a>
        		</c:if>
        		
        			<!-- 尚未加入團購 -->
        			
        	<!--  -->		
        		<c:if test="${((compantsrv.toMap(compantsrv.selectAllinCase(ComVO.com_id))).containsKey(MemVO.mem_id))}">
        		   <c:if test="${compantsrv.selectAllinCase(ComVO.com_id).size()<ComVO.com_min_num}">
        		      <span style="font-size:40px;font-color:#FFA488;margin-bottom:120px;">人數尚未到達最小參與人數,<br>邀請更多人參加吧</span><br>
        		     <span style="font-size:20px;font-color:#FFA488;margin-bottom:120px;">已完成:</span><span style="font-size:60px;font-color:#FFA488;">${compantsrv.selectAllinCase(ComVO.com_id).size()/ComVO.com_min_num*100} %</span><br>
        		   </c:if>
        		   
        		
        		    <c:if test="${compantsrv.selectAllinCase(ComVO.com_id).size()>ComVO.com_min_num}">
        		       <span style="font-size:40px;font-color:#FFA488;margin-bottom:120px;">招募已達最小參予人數,<br>請耐心等待進入下個階段</span><br>
        		    </c:if> 
        		    
        		    
        		    <span style="font-size:20px;font-color:#FFA488;">最小參與人數:${ComVO.com_min_num}人</span><br>
        		    <span style="font-size:20px;font-color:#7D7D7D;">現在人數        :${compantsrv.selectAllinCase(ComVO.com_id).size()} 人</span><br> 
        		     <a href="<%=request.getContextPath()%>/Com_Controller?type=checklist&page=1" type="button" class="btn btn-go" style="margin-top:70px;">
        		      回首頁
        		   </a>
        		  </div>
        		</c:if>
        		
        	      
        		
  			  </div>
    <!--/.Panel 3-->
			</div>	
        </div>
         <div class="col-xs-12 col-sm-2"></div>
    </div> 
    
     <!-- 跳窗 -->
     <div class="row">
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
     </div>
  <!-- 跳窗 -->
  
  <!-- 檢舉跳窗 -->
  		<div class="modal fade" id="modal-appeal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">檢舉此代購</h4>
					</div>
					<div class="modal-body">
							<div class="form-group">
							  <label for="comment">請簡述檢舉原因</label>
							  <textarea class="form-control" rows="5" id="comment" name="apl_cnt"></textarea>
							</div>
						
                  			<br>
                  			<button type="button" id='btnclose' class="btn btn-default" data-dismiss="modal">取消</button>
                  			<input id='btnsubmit' type='submit' class="btn btn-info" value="確認送出" data-dismiss="modal">
					</div>
				</div>
			</div>
		</div>
<!--  -->
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
   
   
    
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>  
<script type="text/javascript" src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    
      
      $("#ComtCollaspse").click(function(){
   	   
    	  if($("#Comt_Down_Arrow").attr("src")=="<%=request.getContextPath()%>/img/Comt_End1.png"){
    		  $("#Comt_Down_Arrow").attr("src","<%=request.getContextPath()%>/img/Comt_Down_Arrow.png");
    	  } 
    	  else{
    		  $("#Comt_Down_Arrow").attr("src","<%=request.getContextPath()%>/img/Comt_End1.png");
    		
    	  } 
    	 
       
       });
      
      
      
      $(document.body).delegate("#btnsubmit",'click',function()
  			{	
  			    
  				var mem_id ='${MemVO.mem_id}';
  				var com_id ='${ComVO.com_id}';
  				var comment =$("#comment").val();
  				if(comment.trim().length == 0 || comment ==""){
  					alert("回復檢舉內容不得為空白");
  					return false;
  				}
  				
  				var btnsubmitVO ={
  					mem_id:mem_id,
  					com_id:com_id,
  					apl_cnt:comment,
  					action: 'appeal_This_Commision'
  				}

  			$.ajax({
  				url : "${pageContext.request.contextPath}/commisionappeal.do",
  				cache: false,
  				type : "POST",
  				data : btnsubmitVO,
  				success : function(data) {
  				$("#btnsubmit").prop("disabled",true);
  				alert("發送檢舉成功")
  				
  				},
  				error: function(xhr, ajaxOptions, thrownError) {
  					console.log(xhr.status + "\n" + thrownError);
  					console.log("error");
  					$("#btnsubmit").prop("disabled",false);
  				}
  			});
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