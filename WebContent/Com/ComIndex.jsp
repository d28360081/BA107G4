<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
   <%@ page import="com.com.model.*" %>
   <%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/gogo.png">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ComIndex.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.css" rel="stylesheet"  />
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>
    <style>
    .col-sm-4 img{
    width:275px;
    height:275px;
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
   
    
   #notificationCard{
    background-color:#CCDDFF;
    opacity: 0;
    position:fixed;
    border-radius:10px 10px 0px 0px;
    left:50px;
    bottom:0px;
    width:300px;
    height:140px;
    text-align:center;
    line-height:100px;

    } 
    .btn-close{
     background-color:#DDDDDD;
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

.cardbody{
border-left: 2px solid #dddddd;
border-bottom:2px solid #dddddd;
padding-right:35px;
padding-top:25px;	
width:380px;
height:700px;
border-radius:15px;
background-color:	#F0FFFF;
}
.cardbody:hover{
box-shadow: 0 2px 5px rgba(0,0,0,0.3);
}
.comCard{
    background-color: black;
    width: 100%;
    height: 380px;
}
.Cardtop{

        width: 100%;
    height: 80%;
    background-image: url(https://lintvwpri.files.wordpress.com/2017/04/pic-of-the-day-april-26-2017.jpg?w=650);
    color: black;
    background-position: center;
}
.cardbottom{
     width: 100%;
    height: 24%;
    background-color: #39D0CB;
    text-align: left;
    padding-left: 10px;
    padding-top: 5px;

}
.bottmTit{
    color: #eee;
    font-size: 20px;
    font-weight: 700;
    margin-bottom: 7px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    max-width: 210px;
}
.bottmTime{
    margin-bottom: 0px;
    color: white;
}
.bottomImg{
    margin-bottom: 0px;
}
.checkBtn{

    float: right;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    margin-top: 17px;
   padding-bottom: 0px;
    color: #39b3d8;
    padding-top: 15px;
    padding-right: 18px;
    padding-left: 11px;
    background-color: white;
    font-weight: 700;
    
}

.bottomleft{
display: inline;
    float: left;
}
.bottomright{

}
.time{
    color: red;
    font-weight: bolder;
}

    
    </style>
</head>

<body>
    <a href="#" id="VoteLik"><div id="notificationCard"><div id="divWord"></div></div></a> 
    <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
	
	
    <jsp:useBean class="com.member.model.MemService" id="memservice"/>


<!--麵包屑-->
    <div class="container bbbc">
        <div class="col-xs-12 col-sm-3">
           <div class="btn btn-go" style="margin-top:50px;">
           
               		 <c:if test="${type ne 'TuanGo'}">
       				  <a href="<%=request.getContextPath()%>/Com_Controller?type=checklist&page=1">團購專區  </a> 
       			 	  </c:if>
       			 
       			 	  <c:if test="${type eq 'TuanGo'}">
        		 	  <a href="<%=request.getContextPath() %>/Com_Controller?type=goDaiGo&page=1">代購專區</a>
        		 	  </c:if>
        		 </div>
        </div>
        <div class="col-xs-12 col-sm-6"></div>
        <div class="col-xs-12 col-sm-3"></div>
    </div>
    <div class="container">
        <div class="col-xs-12 col-sm-4"></div>
        <div class="col-xs-12 col-sm-4"> </div>
        <div class="col-xs-12 col-sm-4">
            <form action="<%=request.getContextPath()%>/Com_ComClc_Controller" method="post" class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="hidden" name="type" value="search">
                        <input type="text" class="form-control" placeholder="輸入關鍵字" name="keyword">
                    </div>
                    <button type="submit" class="btn btn-default btn-go ">搜尋</button>
                </form>
        </div>
    </div>
    <!--麵包屑-->
     <div class="container">
        <div class="col-xs-12 col-sm-4"></div>
        <div class="col-xs-12 col-sm-4">排序:<a href="">熱門度   </a>/<a href="<%=request.getContextPath()%>/Com_Controller?type=checklist&page=1">   列表 </a></div>
        <div class="col-xs-12 col-sm-4"></div>
    </div>
    <!--上方篩選條件-->
     <div class="container">
     <div class="col-xs-12 col-sm-9"></div>
     <div class="col-xs-12 col-sm-3">
     <c:if test="${type eq 'TuanGo'}">
        <c:if test="${MemVO.com_auth eq 1}">
     <a href='<%=request.getContextPath()%>/Com/Com_Establish.jsp' class="btn btn-default btn-go">
     <span class="glyphicon glyphicon-plane">舉辦代購Go</span></a>
        </c:if>
    </c:if>
    </div>
     </div>
    <div class="container">
        <div class="col-xs-12 col-sm-4"></div>
        <div class="col-xs-12 col-sm-4"><div class="clear"></div></div>
        <div class="col-xs-12 col-sm-4"></div>
    </div>
    <!--上方篩選條件end-->
    
  
    
    <!--案件卡片內容-->
    <c:set var="myvar">${MemVO.mem_id}</c:set>
     <jsp:useBean id="timeCountTool" class="timeCount.TimeCountTool"/>

  <div class="container abc">
    <c:forEach var="ComVO" varStatus="Count" items="${ComVOlist}"  begin="${(param.page-1)*9}" end="${(param.page-1)*9+2}" >
   
     <div class="col-xs-12 col-sm-4">
                    <!--    案件1 -->
                    
                       <div class="comCard">
                        	    
                          <div class="Cardtop" style="background-image:url(<%=request.getContextPath() %>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC1);">
                         
						    
                          </div>
                              <div class="cardbottom">
                              <div class="bottomleft">
                                 <p class="bottmTit" style="">${ComVO.com_tit}</p>
                                 <p class="bottmTime" style="">${fn:substring(ComVO.lmt_m_dt, 0, 10)}</p>
                                 <div class="time"></div>
                                 <input class="timeval" value="${ComVO.getTimeObject().getLeftTime()}" type="hidden">
                              </div>
                              <div class="bottomright">  
                                <c:if test="${type ne 'TuanGo'}">  
						       
						        <a href="<%=request.getContextPath() %>/Com_Controller?com_id=${ComVO.com_id}&type=checkDaiGodetail" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green checkBtn" style="">GO</a>
						     </c:if>
						      <c:if test="${type eq 'TuanGo'}">
                        	 
                        	 <a href="<%=request.getContextPath() %>/Com_Controller?com_id=${ComVO.com_id}&type=checkdetail" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green checkBtn" style="">GO</a>
						    </c:if>  
                                  
                             
                               </div>
                               </div>
                            
                        </div>
                </div>
            
            
     </c:forEach>
       </div>
       
        <div class="container abc">
    <c:forEach var="ComVO" varStatus="Count" items="${ComVOlist}"  begin="${(param.page-1)*9+3}" end="${(param.page-1)*9+5}" >
   
            <div class="col-xs-12 col-sm-4">
                    <!--    案件1 -->
                    
                      <div class="comCard">
                        	    
                          <div class="Cardtop" style="background-image:url(<%=request.getContextPath() %>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC1);">
                         
						    
                          </div>
                              <div class="cardbottom">
                              <div class="bottomleft">
                                 <p class="bottmTit" style="">${ComVO.com_tit}</p>
                                 <p class="bottmTime" style="">${fn:substring(ComVO.lmt_m_dt, 0, 10)}</p>
                                 <div class="time"></div>
                                 <input class="timeval" value="${ComVO.getTimeObject().getLeftTime()}" type="hidden">
                              </div>
                              <div class="bottomright">  
                                <c:if test="${type ne 'TuanGo'}">  
						       
						        <a href="<%=request.getContextPath() %>/Com_Controller?com_id=${ComVO.com_id}&type=checkDaiGodetail" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green checkBtn" style="">GO</a>
						     </c:if>
						      <c:if test="${type eq 'TuanGo'}">
                        	 
                        	 <a href="<%=request.getContextPath() %>/Com_Controller?com_id=${ComVO.com_id}&type=checkdetail" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green checkBtn" style="">GO</a>
						    </c:if>  
                                  
                             
                               </div>
                               </div>
                            
                        </div>
                       
                </div>
            
            
             
            
     </c:forEach>
       </div>
        
    
    <div class="container abc">
    <c:forEach var="ComVO" varStatus="Count" items="${ComVOlist}"  begin="${(param.page-1)*9+6}" end="${(param.page-1)*9+8}" >
   
           <div class="col-xs-12 col-sm-4">
                    <!--    案件1 -->
                    
                        <div class="comCard">
                        	    
                          <div class="Cardtop" style="background-image:url(<%=request.getContextPath() %>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC1);">
                         
						    
                          </div>
                              <div class="cardbottom">
                              <div class="bottomleft">
                                 <p class="bottmTit" style="">${ComVO.com_tit}</p>
                                 <p class="bottmTime" style="">${fn:substring(ComVO.lmt_m_dt, 0, 10)}</p>
                                 <div class="time"></div>
                                 <input class="timeval" value="${ComVO.getTimeObject().getLeftTime()}" type="hidden">
                              </div>
                              <div class="bottomright">  
                                <c:if test="${type ne 'TuanGo'}">  
						       
						        <a href="<%=request.getContextPath() %>/Com_Controller?com_id=${ComVO.com_id}&type=checkDaiGodetail" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green checkBtn" style="">GO</a>
						     </c:if>
						      <c:if test="${type eq 'TuanGo'}">
                        	 
                        	 <a href="<%=request.getContextPath() %>/Com_Controller?com_id=${ComVO.com_id}&type=checkdetail" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green checkBtn" style="">GO</a>
						    </c:if>  
                                  
                             
                               </div>
                               </div>
                            
                        </div>
                </div>
            
            
     </c:forEach>
       </div>
       
       
<%int totalNumber=((List<ComVO>)request.getAttribute("ComVOlist")).size(); %>
<%int pageNumbers=0; %>
<%if((totalNumber%9)==0){ %>
<%pageNumbers=(totalNumber/9); }else{%>
<%pageNumbers=(totalNumber/9)+1; } %>

<!--下方頁碼-->

     <ul class="pagination pagination-sm">
     <c:if test="${type eq 'TuanGo'}">
     
       <c:forEach var="page" varStatus="counts" begin="1" end="<%=pageNumbers%>">
       <li><a href="<%=request.getContextPath() %>/Com_Controller?type=checklist&page=${counts.count}">${counts.count}</a></li>
       </c:forEach>
       
     </c:if>
     
     <c:if test="${type ne 'TuanGo'}">
        
       	  <c:forEach var="page" varStatus="counts" begin="1" end="<%=pageNumbers%>">
          <li><a href="<%=request.getContextPath() %>/Com_Controller?type=goDaiGo&page=${counts.count}">${counts.count}</a></li>
          </c:forEach>
         
     </c:if>
     
     </ul>
     

    </div>
    

    

    <!--footer-->
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
    
    <jsp:useBean class="com.comnof.model.ComNofService" id="ComNofService"/>
    
  <c:if test="${MemVO ne null}">  
    <div  id="floatingbutton">
  
   	 <a href="<%=request.getContextPath()%>/com.comclc.view/Com_CheckJointFrame.jsp" >
   	
   	<c:if test="${ComNofService.selectAllUnread(MemVO.mem_id).size() > 0}">
       <span class="label label-danger">${ComNofService.selectAllUnread(MemVO.mem_id).size()}</span>
   	</c:if>
   	 
  	 <img src="<%=request.getContextPath()%>/img/gogo.png">
  	 </a>
    <!-- Display the countdown timer in an element -->
    </div>
    </c:if>
    <script></script>
    <script src="https://code.jquery.com/jquery.js">
    
    </script>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    


<script>

window.onload=function(){
	var timers=document.body.querySelectorAll('.time');
    var timeval=document.body.querySelectorAll('.timeval');
    var j=0;


		votews();
    
    
    
    

<c:if test="${type eq 'TuanGo'}">	 
for(var i =0;i<timers.length;i++){
	

var countDownDate = timeval[i].value;
   myfun(i,countDownDate);

}


	function myfun(i,time){
// Update the count down every 1 second
var x=setInterval(function() {

  // Get todays date and time
  var now = new Date().getTime();
  // Find the distance between now an the count down date
  var distance = time - now;

  // Time calculations for days, hours, minutes and seconds
  var days = Math.floor(distance / (1000 * 60 * 60 * 24));
  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
  var seconds = Math.floor((distance % (1000 * 60)) / 1000);

  // Display the result in the element with id="demo"
  timers[i].innerHTML= days + "d " + hours + "h "
  + minutes + "m " + seconds + "s ";
  timers[i].style.color='red';
  // If the count down is finished, write some text 
  if(distance<0)
  {   
	  console.log(distance);
	  clearInterval(x);
	  timers[i].innerHTML='準備下架';
  }
  
 
}, 1000);}
</c:if>	

	
	}
	
	
function votews(){
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
		  
		 }
		 
		 
		 
function fadeout(str){
    document.getElementById("divWord").innerHTML=str;
    
  var opcatitynum=0;
  var opcatitynum2=1;
  var timer= setInterval(function(){
       opcatitynum+=0.1;
        document.getElementById("notificationCard").style.opacity=opcatitynum;
			if(document.getElementById("notificationCard").style.opacity==1){ clearInterval(timer); }
                                      },100); 
          
  setTimeout(function(){
	   setInterval(function(){
		   document.getElementById("notificationCard").style.opacity=opcatitynum2;
		   		opcatitynum2-=0.1;
		   			if(document.getElementById("notificationCard").style.opacity==0){
		             document.getElementById("notificationCard").style.display="none";}
		   			 document.getElementById("VoteLik").href="#";
	                             },100);
                                    },10000);
                                           }
		     
    
</script>

</body>

</html>