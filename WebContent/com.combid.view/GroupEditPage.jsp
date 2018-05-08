<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<!-- 旁側按鈕 -->
   <jsp:useBean class="com.combid.model.ComBidService" id="combidservice2"/>
    <jsp:useBean class="com.compant.model.ComPantLsService" id="compantlsservice2"/>   
    <jsp:useBean class="com.com.model.ComService" id="comserviceforGroupEdit"/>
    <jsp:useBean class="com.member.model.MemService" id="membersrcCor"/>

 <c:if test="${combidservice2.selectBiddingWinner(ComVO.com_id).mem_id eq MemVO.mem_id}">
      <div id="buyerButton" class="sideIndexButton">
                             賣家選單
      </div> 
</c:if>


   
<c:if test="${compantlsservice2.select(MemVO.mem_id,ComVO.com_id) ne null}">   
<div 
    style="position: fixed;
    top: 80px;
    left: 20px;
    z-index:  3;"
>
	<div id="GroupMemberButton"  class="sideIndexButton">
	買家選單
	</div>
	
	<div style="
    background-image:url(<%=request.getContextPath()%>/img/gogo.png);
        background-size: 100%;
    width: 30px;
    height: 30px;
    max-height:30px;
    position: absolute;
    top: 20px;
    left: 80px;
	" id="CorBtn">
	</div>
</div>	
	
</c:if>

<div id="inforButton" class="sideIndexButton">

案件資訊

</div>

<div id="test">
 <div class="btn" id="buyerInfor">案件通知</div>

 <div class="btn" id="CaseDetail">案件明細</div>
 


</div>

<!-- 旁側按鈕 -->

        = <!--貨單上傳-->

<div class="modal fade" id="CorModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <form  action="<%=request.getContextPath()%>/ComCorController" method="post">
      <div class="modal-body">
		         <p>輸入您的領貨編號</p><br>
		    
		         
		         <input type="text">
		         <input type="hidden" name="type" value="CorCheck">
		         <input type="hidden" name="com_id" value="${ComVO.com_id}">
		         <input type="hidden" name="mem_id" value="${MemVO.mem_id}">
      </div> 
      <div class="modal-footer">
      
        <button type="submit" class="btn btn-primary">送出</button>
      </div>
      </form>
    </div>
  </div>
</div>

            <!--貨單上傳-->




 <!-- 賣家明細跳窗 -->

 
 
<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
     
      <div class="modal-body" style="background-color: #d2c9c9;border-radius: 15px;overflow:auto;" id="qrContent">
               恭喜您,
      <br>您的物件已經被所有會員確認,<br>
              接下來,請將下列的QR code在<br>
              寄出配合會員編號附上,
              以便查核用
      <br>
           <c:forEach var="compantlsvo2" varStatus="Count" items="${compantlsservice2.selectAllinCase(ComVO.com_id)}" >
      
           <div class="col-xs-12 col-sm-12" style="margin-top:15px;  padding-top: 15px;border-top: 1px dashed black;">
                               會員編號:${compantlsvo2.mem_id}<br>                   
            QrCode:
            	<br>
            
            		<img src="<%=request.getContextPath()%>/QRcodeImgHadler?com_id=${ComVO.com_id}&mem_id=${compantlsvo2.mem_id}&type=Qrcode">
            
            	<br>
            </div>
     
           </c:forEach>
      </div>
      </div>
      <div class="modal-footer col-xs-12 col-sm-12">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
         <button type="button" class="btn btn-secondary printBtn" data-dismiss="modal">Print</button>
      </div>
    </div>
  </div>
</div>

 <!-- 賣家明細跳窗 -->


<!-- 賣家通知跳窗 -->

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel" style="font-size:30px;">貨單編號</h5>
         <small class="form-text text-muted">送出貨物之後,打上貨單號碼吧,以便會員查詢</small>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form action="<%=request.getContextPath()%>/ComCorController" method="post">      
       <input type="hidden" value="${ComVO.com_id}" name="com_id">     
        <c:forEach var="ComPantVOinCor" items="${compantlsservice2.selectAllinCase(ComVO.com_id)}">
         <div class="CorMem">   
         <div class="CorPhoto" style="width: 90px;display: inline-block;height: 90px;"><img src="<%=request.getContextPath()%>/member/MemberPhotoReader?mem_id=${ComPantVOinCor.mem_id}" style="width: 100%;height: 100%;display: inline-block;"></div>
         <div class="CorWord">
		         <p>         
		                      會員:${membersrcCor.getOneMem(ComPantVOinCor.mem_id).mem_id}<br>
		                    購買數量:${ComPantVOinCor.com_it_num}<br>
		                     付款方式:${ComPantVOinCor.com_py}<br>
		           <input type="text" name="${ComPantVOinCor.mem_id}">          
		          <p>
		          <input name="cor_id" value="type" style="border-radius:5px;" type="hidden">  
         </div>            
        </div>
        </c:forEach>
          
		          <div style="display: block;">
		 
					    
					    <button type="submit" class="btn " style="background-color: white;
					    color: darkslategray;
					    border: 1px solid darkslategray;" id="buyerInforSubBtn">送出</button>
			    	</div>	    
		    </form>
		          </div>
          </div>   
          
          
         
      </div>

    </div>
  </div>
</div>
<!-- 賣家通知挑窗 -->


<!-- 中央視窗 -->
     <div class="card groupeditpage droparea" id="droparea" style="overflow:auto;">
        
             
      
     
     </div>
     <!-- 中央視窗 -->
       <!-- 打字區 -->
       <textarea name="editor1" id="editor2">
       </textarea>
	    <!-- 打字區 -->
      
      <!-- 案件說明跳窗 -->
      <div id="iconWindow">
      
          <div>
             <img src="<%=request.getContextPath()%>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC1" style="width: 320px;
    height: 220px;
    margin: 30px;
    border-radius: 15px;">
          </div>
          
          <div style="text-align:left; line-height: 45px;">
             <span style="font-size:20px;">案件名稱:</span><br><span style="font-weight:bold;font-size:22px;"> ${ComVO.com_tit}</span><br>
             <span style="font-size:20px;">案件主辦人:</span><br><span style="font-weight:bold;font-size:22px;"> ${ComVO.mem_id}</span><br>
             <span style="font-size:20px;">案件說明:</span><br><span style="font-weight:bold;font-size:22px;"> ${ComVO.com_cnt}</span><br>
            
             <span style="font-size:20px;">案件狀態:</span><br><span style="font-weight:bold;font-size:22px;margin-top:30px;"> ${ComVO.com_sts}</span>
          </div>
          <div style="margin: 50px;">
            <a href="" type="button" class="btn-go" style="border: 1px solid #3ab2d9;">回案件列表</a>
          </div>
          
          
      </div>
       <!-- 案件說明跳窗 -->
      
      <!-- 買家按鈕跳窗 -->
   <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">買家付款資訊</h4>
        </div>
        <div class="modal-body" id="payModal">
              <form class="card-wrapper" id="creditC" style="font-size:18px;">
                   
                 </form>

                    <div class="col-xs-12">
                       <label for="exampleInputEmail1">卡號:</label>
                       <input type="text" class="form-control col-xs-2 " id="com_py" aria-describedby="emailHelp">
                    </div>

                    <input type="hidden" id="name" style="display:none;" value="${MemVO.mem_id }">
                    <input type="hidden" id="expiry" style="display:none;">
                    <input type="hidden" id="cvc" style="display:none;">
                      
                <div class="col-xs-12">
                       <label for="exampleInputEmail1">商品數量</label>
                       <input type="text" class="form-control col-xs-2" id="com_it_num" aria-describedby="emailHelp">
                </div>
                  <div class="col-xs-12">
                       <label for="exampleInputEmail1">地址</label>
                       <input type="text" class="form-control" id="dlt_adds" aria-describedby="emailHelp">
                  </div>     
        
        <div class="modal-footer "  style="margin-right: 240px;margin-top: 10px;margin-left: 220px;">
          <button type="button" class="btn btn-go" id="payModalSub" style="background-color:white;">提交</button>
        </div>
      </div>
            <!-- Modal content-->
    </div>
  </div>
   <!-- 買家按鈕跳窗 -->


      
       <!--  <div id="numUpdate" class="draggable editcard">
                

                  
             </div> --> 
      
      
      

      
      
      
      
      
      
      
  
     <script src="js/card.js"></script>
    <script>
    $('#CorBtn').click(function(){
    	console.log('asss');
    	$('#CorModal').modal();
    });
    
    
    
      $("#inforButton").click(function(){
    	  
    	  if($("#test").css('display')=='block'){
    		  $('#test').css('display','none');
    	  }
    	  $("#iconWindow").toggle("slow");

      });
      
      $("#buyerButton").click(function(){
    	  
    	  if($("#iconWindow").css('display')=='block'){
    		  $('#iconWindow').css('display','none');
    	  }

    	  $("#test").toggle("fast");
      });
      
      $("#buyerInfor").click(
    	function(){
    		$("#exampleModal").modal();
    	}	  
    	
      );
      <c:if test="${comserviceforGroupEdit.select(ComVO.com_id).com_sts eq '開始購買'}">   
      $("#CaseDetail").click(
    	function(){
    		$("#exampleModalLong").modal();
    	}	  
      );
      
   

      
      </c:if>
      <c:if test="${comserviceforGroupEdit.select(ComVO.com_id).com_sts eq '運送中'}">   
      $('#corBtn').css('display','block');
      </c:if>
      $('#creditC').card({
    	    // a selector or jQuery object for the container
    	    // where you want the card to appear
    	    container: '.card-wrapper', // *required*
    	    numberInput: 'input#com_py', // optional — default input[name="number"]
    	    expiryInput: 'input#expiry', // optional — default input[name="expiry"]
    	    cvcInput: 'input#cvc', // optional — default input[name="cvc"]
    	    nameInput: 'input#name', // optional - defaults input[name="name"]
    	    
    	    width: 300, // optional — default 350px
    	    formatting: true ,// optional - default true
    	 
    	    // Strings for translation - optional
    	    messages: {
    	        validDate: 'valid\ndate', // optional - default 'valid\nthru'
    	        monthYear: 'mm/yyyy', // optional - default 'month/year'
    	        fullName: 'Say my name' // optional - default 'Full Name'
    	    }
    	});
      
      
      $('.printBtn').click(function(){
    	 
                 var divToPrint=document.getElementById('DivIdToPrint');

                 var newWin=window.open('','Print-Window');

                 newWin.document.open();

                 newWin.document.write('<html><head></head><body onload="window.print()" style="text-align:center;background-color: #f6f6f6;">'+$("#qrContent").html()+'</body></html>');

                 newWin.document.close();

                 setTimeout(function(){newWin.close();},10);

      });
      
      
      
    
    
    </script>
</body>
</html>