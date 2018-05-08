<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/card.css">
     <script src="https://cdn.ckeditor.com/4.9.2/standard/ckeditor.js"></script>
     <script src="https://cdn.ckeditor.com/4.9.1/standard/ckeditor.js"></script>
      <script src="https://code.jquery.com/jquery-1.10.2.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script>
 /* tinymce.init({
	  selector:'textarea',
	  selector: 'textarea',
	  height: 500,
	  plugins: [
	        "advlist autolink lists link image charmap print preview anchor",
	        "searchreplace visualblocks code fullscreen",
	        "insertdatetime media table contextmenu paste imagetools wordcount"
	    ],
	    toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
	  // imagetools_cors_hosts: ['www.tinymce.com', 'codepen.io'],
	  content_css: [
	    '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
	    '//www.tinymce.com/css/codepen.min.css'
	  ]
	  });*/
  </script>
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
 <jsp:useBean class="com.com.model.ComService" id="comserviceforGroupEdit2"/>
 
 .chatPic{
    background-color: black;
    width: 50px;
    height: 50px;
    position: absolute;
    border-radius: 50%;
    margin-left: 5%;
    border: 1px solid black;
 }
 
        .comtitle{
        margin-bottom:50px;
        }
        .editbody{
        margin-bottom:50px;}
        .btn-go{
        margin-top:30px;
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
   .groupeditpage{
   height:600px;
   width:60%;
   margin-left:15%;
   border: 2px solid #3ab2d9;
   }
   .groupeditpageinput{
   border: 1px solid #3ab2d9;
   border-radius:5px;
   width:250px;
   height:170px;
   
   }
 
.textBox{
     padding-top:40px;
	 width: 300px;
	 height:100px;
     background-color:#CCDDFF;
     border-radius: 15px;
     border: 1px solid #EEEEEE;
     margin:20px;
     box-shadow: 0 2px 5px rgba(0,0,0,0.3);

    }
.textBoxhost{
     padding-top:40px;
	   width: 300px;
	 height:100px;
     background-color:#ffcccc;
     border-radius: 15px;
     border: 1px solid #EEEEEE;
     margin:20px;
     box-shadow: 0 2px 5px rgba(0,0,0,0.3);
}    
.infoBox{
     padding-top:40px;
      width: 300px;
	 height:100px;
     background-color:#FFC8B4;
     border-radius: 15px;
     border: 1px solid #EEEEEE;
     margin:20px;
     box-shadow: 0 2px 5px rgba(0,0,0,0.3);

}    
    
    
    .WinnerComImg{
    width:100px;
    height:100px;
    border-radius:50%;
    margin-top:30px;
    margin-left:20px;
    }
   
    .editcard{
     
     border: 1px solid #EEEEEE;
     margin:20px;
     box-shadow: 0 2px 5px rgba(0,0,0,0.3);
     height:380px;
     width:270px;
     border-radius:15px;
    
    }
    .textboxleft{
    margin-left:520px;
    }
    .textboxright{
     margin-right:520px;
    }
    .textcenter{
  
    margin-left:250px;
    padding-top: 2px;
    margin-left: 150px;
    width: 598px;
    height: 30px;
    background-color: #999;
    color: white;
    border-radius: 15px;
    border: 1px solid #EEEEEE;

    }
    .winnerBidCard{
    background-color:#FFA488;
    }
    .JoinBidCard{
    background-color:#77FFCC;
    }
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

.textImgBox{
    height: 310px;
    padding-top: 10px;
    background-image: linear-gradient(to top, #cfd9df 0%, #e2ebf0 100%);
    width: 300px;
    background-color: #CCDDFF;
    border-radius: 15px;
    border: 1px solid #EEEEEE;
    margin: 20px;
    box-shadow: 0 2px 5px rgba(0,0,0,0.3);
}
  

#Checkimg{
    height: 150px;
    width: 150px;
    margin-top: 30px;
}
#cke_editor1{
    border-radius: 15px;
    border: 1px solid aqua;
    width: 800px;
    margin-left:40px;
}
#circleicon img{
    position: fixed;
    height: 100px;
    width: 100px;
    right: 49px;
    bottom: 50px;
}
#iconWindow{
    background-color: #E0FFFF;
    display: none;
    position: fixed;
    left: 125px;
    top: 110px;
    width: 570px;
    background-color: white;
    border-radius: 20px;
    border: 2px solid #CC6600;
    padding-right:20px;
    padding-left:20px;
    
}
#editor2{
   width: 500px;
    border: 1px solid #48c6ef;
    height: 100px;
    border-radius: 20px;
    margin-left: 80px;
    margin-top: 30px;
}
#GroupMemberButton{
    width: 60px;
    height: 180px;
    position: fixed;
    left: 0px;
    top: 100px;
    background: white;
    border: 2px solid #1edcba;
    border-radius: 0px 18px 18px 0px;
    color: #9d8f8f;
    writing-mode: vertical-rl;
    text-orientation: upright;
    padding-right: 20px;
}
#buyerButton
{
       width: 60px;
    height: 180px;
    position: fixed;
    left: 0px;
    top: 300px;
    background: white;
    border: 2px solid #3abbd5;
    border-radius: 0px 18px 18px 0px;
    color: #9d9595;
    writing-mode: vertical-rl;
    text-orientation: upright;
    padding-right: 20px;
}
#inforButton{
    width: 60px;
    height: 180px;
    position: fixed;
    left: 0px;
    top: 500px;
    background: white;
    border: 2px solid #86969882;
    border-radius: 0px 18px 18px 0px;
    color: #888888;
    writing-mode: vertical-rl;
    text-orientation: upright;
    font-weight: 400;
    padding-right: 20px;
}
#CaseIndex{
    width: 60px;
    height: 180px;
    position: fixed;
    left: 0px;
    top: 580px;
    background: white;
    border: 1px solid #00DD00;
    border-radius: 0px 18px 18px 0px;
    color: #227700;
    writing-mode: vertical-rl;
    text-orientation: upright;
    padding-left: px;
    padding-right: 20px;
}
.sideIndexButton:hover{
box-shadow: 0 2px 5px rgba(0,0,0,0.3);

}
.sideIndexButton{
z-index:5;
}
#test{
z-index:5;
}
#payModal input{
      border: none; /* <-- This thing here */
      border:solid 1px #ccc;
      border-radius: 10px;

}
.card .front .lower .number{
font-size:18px;
}
#test{   
    background-color: 	#F8F8FF;
    border: 1px solid #ff000087;
    width: 130px;
    height: 270px;
    position: fixed;
    top: 390px;
    left: 90px;
    border-radius: 20px;
    display:none;
    padding-top:10px;
}
#test div:hover{
background-color:	#F5F5F5;
border-bottom:1px solid black;
border-radius:0px;

}
#buyInforCnt{
border: none;
    height: 300px;
        width: 500px;
    border: 1px solid black;
    border-radius: 15px;
    margin-top: 30px;
}

.CorMem{
margin:25px;
}

   <c:if test="${comserviceforGroupEdit2.select(ComVO.com_id).com_sts eq '開始購買'}">  
#CaseDetail{
display:block;
}
#corBtn{
display:block;
}
  </c:if>
  <c:if test="${comserviceforGroupEdit2.select(ComVO.com_id).com_sts ne '開始購買'}">
  #CaseDetail{
display:none;
}

.CorPhoto{
    width: 65px;
    height: 65px;
    border-radius: 50%;
    display: inline-block;
}

  </c:if>
#CorBtn{
display:none;
}   
#buyerInfor{
display:none;
}
        </style>	
</head>

<body>

<jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
	
    
     <jsp:useBean class="com.compant.model.ComPantLsService" id="compantlsservice"/>     
     <jsp:useBean class="com.combid.model.ComBidService" id="combidservice"/>
    
    <div class="container">
     
       
       <div class="col-xs-12 col-sm-4" id="sideBoxes">
          
       
       
        
           
           
       </div>
       
       
       <div class="col-xs-12 col-sm-8 editbody"> 
       
       <c:if test="${type ne 'voteOutcome' && type ne 'GroupEdit'}">
       <jsp:include page="/Com/ComBidEdit.jsp" flush="true"/>
       </c:if>
      
       <c:if test="${type eq 'voteOutcome'}">
           
              <c:forEach var="ComBidVO" items="${combidservice.selectAll(com_id)}">      
                 
                 
                 
                 <span class="min-chart" id="chart-sales" data-percent="${ComBidVO.n_o_v/totalBallot*100}"><span class="percent"></span></span>
				<h5><span class="label green">${ComBidVO.mem_id}<i class="fa fa-arrow-circle-up"></i></span></h5>
                 
              </c:forEach>
       </c:if>
       
       
       <c:if test="${type eq 'GroupEdit'}">
          <jsp:include page="/com.combid.view/GroupEditPage.jsp" flush="true"/>
          

       </c:if>
       
       
       
       </div>
      
       
 
       
    </div>      
    
    	
    <c:if test="${type eq 'GroupEdit'}">


<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
     <div class="modal-content">
       <div class="modal-header">
         <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
           <span aria-hidden="true">&times;</span>
            </button>
             </div>
               <div class="modal-body">
                
               * 商品數量<input type="text"><br>
                                          付款方式 <input type="text"><br>
                                          地址 <input type="text">                       
               </div>
             <div class="modal-footer">
           <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<!-- Modal -->
         </div>
        </div>
    
    </c:if>
    
    
    
    
    
    
    
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
     <script src="<%=request.getContextPath()%>/js/card.js"></script>
  
   
    
    <c:if test="${type ne 'voteOutcome' && type ne 'GroupEdit'}">
    <script>
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
     if(dd<10){
            dd='0'+dd
        } 
        if(mm<10){
            mm='0'+mm
        } 

    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("LT_M_DT").setAttribute("min", today);
    </script>
    	</c:if>
    	
     	 <c:if test="${type eq 'GroupEdit'}">
  <script>
  $(function(){
      
	  <c:if test="${compantlsservice.select(MemVO.mem_id,ComVO.com_id) ne null}">
      
	   $("#GroupMemberButton").click(function(){
	     	
	       $("#myModal").modal();
	  
       });
	  </c:if>
	
  
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var endPointURL = "ws://"+window.location.host+webCtx+"/GroupEditWS/${MemVO.mem_id}/${ComVO.com_id}";
        var stopNumber;
        var count=0;
        var hostid='${combidservice.selectBiddingWinner(ComVO.com_id).mem_id}';
		var BuyerCheckReplycount=0;
		var ws = new WebSocket(endPointURL);
		var countA=0;
		<!--  競標人專屬-->     
	    <c:if test="${combidservice.selectBiddingWinner(ComVO.com_id).mem_id eq MemVO.mem_id}">
	     
	     	 
			
	     	$("#droparea").on('dragover',function(e){
	     	     		
	     	     		e.preventDefault();
	     	     		e.stopPropagation();
	     	     		$(this).css('border', '#39b311 2px dashed');
	     				$(this).css('background', '#f1ffef');
	     	     	});
	     	     	
	     	    	$("#droparea").on('dragleave',function(e){
	     	     		e.preventDefault();
	     	     		e.stopPropagation();
	     	     		
	     	     		$('#droparea').css('border', '#3ab2d9 2px solid');
	     	     	}
	     	     	);
	     	    	
	     	     	$(document).on('dragover',function(e){
	     	     		$(document.body).css('background-color','#999');
	     	     		
	     	     		if(count==0){
	     	     		$(document.body).append($(document.createElement('div')).attr('id','NofText').css('position','fixed').css('writing-mode','vertical-lr').css('top','300px').css('left','180px').css('font-size','30px').css('color','white').text('請將圖片拖曳至畫框'));
	     	     		count++;
	     	     		}   
	     	     	});
	     	     	
	     	     	$(document).on('dragleave',function(e){
	     	     		count=0;
	     	     		$("#NofText").remove();
	     	     		$(document.body).css('background-color','white');
	     	     		
	     	     	});
	     	 
	     	 
	     	$("#droparea").on('dragenter', function(e) {
				e.preventDefault();
				
			});
	     	 
	     	
	     
	     	
	     	
	     	$(".droparea").on('drop',function(e){
	     	
	     		e.preventDefault();
	     		e.stopPropagation();
	     		$('#droparea').css('border', '#3ab2d9 2px solid');
	     		   var dt = event.dataTransfer;
	     		   if(dt!=null){
	     		   var files = dt.files;
	     		   var n = files.length;
	     		   for (var i = 0; i < n; i++) {
	     			  if (files[i].type.match('image')){
	     		   var file = files[i];
	     		   var fileName=file.name;
	     		   var reader = new FileReader();
	     		     reader.readAsDataURL(file);
	     		     reader.onloadend =function(event){
	     		    var filedata = event.target.result;

		    	       $("#droparea").off('drop');
		    	       
		    	       var pic ={"img":filedata};
		    	       console.log(JSON.stringify(pic));
 					    ws.send(JSON.stringify(pic));
		    	        
// 	     		       /*$.ajax({
// 	     		        	 type:'post',
<%-- 	     		        	 url:'<%=request.getContextPath()%>/ComGroupEditController', --%>
// 	     		        	 data:{'img':filedata,'com_id':'${ComVO.com_id}','type':'picupload'},
	     		        
// 	     		         });*/
	                            		    
	     		        }
	     			  }
	     		   }
	     	  }
	     	});
	     	
	     	
	     	
	     	</c:if>
	     	<!--  競標人專屬-->  
		
		

		
		
		
	
		                         
	     ws.onopen = function()
	     {
	    	
	     };
	     ws.onmessage = function (evt) 
	     { 
	    	
	    	 var msg=JSON.parse(evt.data);
	    	 
	  
	    for(var j=0;j<msg.length;j++){	
	    	
	    	console.log(msg[j].type);
	    	
	    	
	    	 if(msg[j].type=='payUpdate')
	    	 {
	    	
	    			 $('.groupeditpage').append($(document.createElement('div')).addClass("infoBox").addClass("textboxright").text(msg[j].mem_id+"已繳交資料"));

	    		 
	    	 }
	    	 
	    	 else if(msg[j].type=='numberConfirm'){
	    		 
	    		 $('.groupeditpage').append($(document.createElement('div')).addClass("infoBox").addClass("textboxright").text(msg[j].mem_id+'已更新資料'));
	    		 
	    		 
	    	 }else if(msg[j].type=="Comment"){
	    		 
	    		 if(msg[j].mem_id==hostid){
	    		 $('.groupeditpage').append($(document.createElement('div')).addClass("textBoxhost").addClass("textboxleft").html("<span>(代購人)"+msg[j].mem_acc+":</span><br>"+msg[j].nof_cnt));
	    		 }
	    		 else{
	    			 $('.groupeditpage').append($(document.createElement('div')).addClass("textBox").addClass("textboxleft").html("<span>(參與會員)"+msg[j].mem_acc+":</span><br>"+msg[j].nof_cnt));
	    		 }
	    		 
	    	 }
	    	 else if(msg[j].type=='stopNumberUpdate'){
	    		 stopNumber++;
	    		 $('#GroupMemberButton').off('click');
	    		 $('.groupeditpage').append($(document.createElement('div')).addClass("textcenter").text('所有會員資料更新完畢'));
	    		 $('#GroupMemberButton').click(
	    			          function(){
	    			        	 alert('所有會員已經提交資料');
	    			          }		 
	    			         );
	    		 
	    		
	    		   
	    		

	    	 }
	    	 else if(msg[j].type=='buyerCheck'){
	    	   
	    	       $("#droparea").off('drop');
	    		 if(msg[j].answer=='voted'){
	    			 $('.groupeditpage').append($(document.createElement('div')).addClass("textImgBox").addClass("textboxright").html("<span>代購人上傳圖片</span><br><img id='Checkimg' src='"+msg[j].pic+"'><br>已確認目標物"));
	    		     
	    			 console.log(msg[j].answer+'997');	 
	    	 
	    	    
	    		 }else{
	    			
	    			 countA++;
	    			
	    			<c:if test="${compantlsservice.select(MemVO.mem_id,ComVO.com_id) ne null}">
		    		 $('.groupeditpage').append($(document.createElement('div')).addClass("textImgBox").addClass("textboxright").html("<span>代購人上傳圖片</span><br><img id='Checkimg' src='"+msg[j].pic+"'><br>例圖是否與購買目標物雷同<br><a href='#' class='btn btn-go'  id='buyerCheckBtn'>確認物件</a>"));
		    		</c:if>
		    		 console.log(msg[j].answer+'998');	 
		    		<c:if test="${compantlsservice.select(MemVO.mem_id,ComVO.com_id) eq null}">
		    		 $('.groupeditpage').append($(document.createElement('div')).addClass("textImgBox").addClass("textboxright").html("<span>代購人上傳圖片</span><br><img id='Checkimg' src='"+msg[j].pic+"'><br>圖片已上傳<br>"));
		    		</c:if>
	    			 
	    		 }
	    		
	    		 $(".btn-go").click(
	    			    	function(){
	    			    		var checkConfirm=confirm('確認要購買嗎');

	    			    			$(this).remove();
	    			    			console.log(checkConfirm);
	    			    		if(checkConfirm){
	    			    			$.ajax({
	    			    				 type:'post',
	    		     		        	 url:'<%=request.getContextPath()%>/ComGroupEditController',
	    		     		        	 dataType:"json",
	    		     		        	 data:{'com_id':'${ComVO.com_id}','mem_id':'${MemVO.mem_id}','type':'BuyerCheck','answer':checkConfirm}
	    			    				
	    			    			});
	    			    	  
	    			    		} else if(!checkConfirm){
		    			    		   $.ajax({
		    			    				 type:'post',
		    		     		        	 url:'<%=request.getContextPath()%>/ComGroupEditController',
		    		     		        	 dataType:"json",
		    		     		        	 data:{'com_id':'${ComVO.com_id}','mem_id':'${MemVO.mem_id}','type':'BuyerCheck','answer':checkConfirm}
		    			    				
		    			    			});
		    			    		   
		    			    		   
		    			    			}
	    			    		
	    			    	}); 
	    			       
	    		
	    		
	    	 }else if(msg[j].type=='BuyerCheckReply'){
	    		 console.log(msg[j].reponse=='true');
	    		 if(msg[j].reponse=='true'){
	    		     $('#buyerInfor').css('display','block');
	    			 $('#CaseDetail').css('display','block');
	    			
	    			 $("#droparea").off('drop');
	    			 $('.groupeditpage').append($(document.createElement('div')).addClass("textcenter").text('物件已經確認完成'));
	    			 $(document).off('dragover');
	    			
		    	     		if(BuyerCheckReplycount==0){
		    	     			 <c:if test="${combidservice.selectBiddingWinner(ComVO.com_id).mem_id eq MemVO.mem_id}">
		    	     			$(document).on('dragover',function(e){

		    	     				
		    	     				    
		    	     	     			$(document.body).append($(document.createElement('div')).attr('id','NofText').css('position','fixed').css('writing-mode','vertical-lr').css('top','300px').css('left','130px').css('font-size','30px').css('color','white').text('所有代購人已經確認圖片,無法再次上傳'));
		                                $(document.body).css('background-color','gray');
		                                BuyerCheckReplycount++;
		                                
		                                $(document).on('dragleave',
		    			    	     			function(){
		    		    	     			
		    			    	     				BuyerCheckReplycount=0;
		    			    	     	     		$("#NofText").remove();
		    			    	     	     		$(document.body).css('background-color','white');
		    			    	     			}		
		    		    	     			);
		    	     	     	
		    	     			
		    	     			
		    	     			});
		    	     			
		    	     			
		    	     			
		    	     			
		    	     			
		    	     			
		    	     			  </c:if>
		    	     		}   
		    	  
		    	     
	    		 }
	    		 else{
	    			 <c:if test="${combidservice.selectBiddingWinner(ComVO.com_id).mem_id eq MemVO.mem_id}">
	    			 $('.groupeditpage').append($(document.createElement('div')).addClass("textcenter").text('物件確認失敗'));
	    			 $("#droparea").on('drop',function(e){
	    				 $('#droparea').css('border', '#3ab2d9 2px solid');
	    		     		e.preventDefault();
	    		     		e.stopPropagation();
	    		     		$("#droparea").off('drop');
	    		     		   var dt = event.dataTransfer;
	    		     		   var files = dt.files;
	    		     		   var n = files.length;
	    		     		   for (var i = 0; i < n; i++) {
	    		     		   var file = files[i];
	    		     		   var fileName=file.name;
	    		     		   var reader = new FileReader();
	    		     		     reader.readAsDataURL(file);
	    		     		     reader.onloadend =function(event){
	    		     		    var filedata = event.target.result;
	    		     		    var pic2 ={"img":filedata};
	    			    	     console.log('9997');
	    					    ws.send(JSON.stringify(pic2));
// 	    		     		         $.ajax({
// 	    		     		        	 type:'post',
<%-- 	    		     		        	 url:'<%=request.getContextPath()%>/ComGroupEditController', --%>
// 	    		     		        	 data:{'img':filedata,'com_id':'${ComVO.com_id}','type':'picupload'},
	    		     		   
	    		     		        	 
// 	    		     		         });
	    		                            		    
	    		     		        }
	    		     		   }
	    		     	  
	    		     	});
	    			 </c:if>
	    			
	    		 }
	    	 }
	    	 else if(msg[j].type=='delivery'){
	    		 $('.groupeditpage').append($(document.createElement('div')).addClass("textcenter").text('賣家已出貨'));
	    		 $('#CorBtn').css('display','block');
	    	 }
	    }
	  };
	     
	     
	     
	  window.onbeforeunload = function(event) {
          ws.onclose =function(){};
          ws.close();
      }
	     ws.onerror = function()
	     { 
	     };		


	
	     <c:if test="${compantlsservice.select(MemVO.mem_id,ComVO.com_id) ne null}">
	      
	    	   $("#GroupMemberButton").click(function(){
	   	     	
	    	       $("#myModal").modal();
	    	  
	            });
	     
	         

         $("#payModalSub").click(function(){
        	 $('#myModal').modal('toggle'); 	
	    var jobpri={"com_it_num":$("#com_it_num").val(),"com_py":$("#com_py").val(), "dlt_adds":$("#dlt_adds").val(),"mem_id":"${MemVO.mem_id}","com_id":"${ComVO.com_id}","type":"numberSend"};
	    var josob= JSON.stringify(jobpri);
	         $('#GroupMemberButton').off("click");
	         $('#GroupMemberButton').click(
	          function(){
	        	 alert('已經提交資料');
	          }		 
	         );
	    ws.send(josob);
	
          });

          
     	</c:if>
     	
 $("#editor2").keypress(
     	     	function(e){
     	     		if(e.which=='13'){
     	     		     var jobpri={"mem_id":"${MemVO.mem_id}","com_id":"${ComVO.com_id}","type":"rmdsend","rmd":$("#editor2").val()};
     					   $("#editor2").val("");
     					  
     					 
     					 ws.send(JSON.stringify(jobpri));
     	     		}
     	     	}		
     	     	
     	     	);
     	     	 
     	
  });
  </script>
 	 </c:if>
    
    
   
   
</body>

</html>
</body>
</html>