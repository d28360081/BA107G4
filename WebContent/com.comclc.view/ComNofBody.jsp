<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>GogoShop</title>



</head>
<body>
<jsp:useBean class="com.com.model.ComService" id="ComServiceForBidWinner"/>

     <c:if test="${joinList ne null}">
  
        <c:forEach var="ComNofVO" varStatus ="Count" items="${joinList}">

         <c:if test="${ComNofVO.nof_sts eq '已讀'}">
         <div class="card w-75 draggable">
              <a href="#" class="btn ">
             <div class="card-body">
               <span><h1 class="card-title">${ComNofVO.nof_tit}</h1></span>
             	<h6 class="card-text">${ComNofVO.nof_cnt}</h6>
           	   
           </div>
            </a>
        </div>
        </c:if>
                
         <c:if test="${ComNofVO.nof_sts eq '未讀'}">
         <div class="card w-75 unread nofbtn draggable" id="${ComNofVO.nof_id}">
          
             <div class="card-body">      
               	<h5 class="card-title" style="font-size: 20px;font-weight: 800;">${ComNofVO.nof_tit}</h5>
             	<p class="card-text">${ComNofVO.nof_cnt}</p>
           	   
           </div>
           
        </div>
        </c:if>
        </c:forEach>
       
     </c:if>
     
     <c:if test="${WinnerList ne null}">
     
          <c:forEach var="ComBidVO" varStatus="Count" items="${WinnerList}">
          
         <div class="card w-75 winnerBidCard" style="text-align:left; padding:20px;">
              <a href="<%=request.getContextPath()%>/ComGroupEditController?type=GroupEdit&com_id=${ComBidVO.com_id}">
              <img class="WinnerComImg" src="<%=request.getContextPath()%>/ImgHandler?name=${ComBidVO.com_id}&type=COM_PIC1" style="vertical-align:middle; display:inline-block; margin-right:20px;">

             <div class="card-body bidcard" style="vertical-align:middle;display:inline-block; ">
             
               <span><h1 class="card-title">案件標題:${ComServiceForBidWinner.selectOnlineDaiGo(ComBidVO.com_id).com_tit}</h1></span>
              
                <h6 class="card-text">案件內文:${ComServiceForBidWinner.selectOnlineDaiGo(ComBidVO.com_id).com_cnt}</h6>
             	<h6 class="card-text">案主提示訊息:${ComServiceForBidWinner.selectOnlineDaiGo(ComBidVO.com_id).com_rmd}</h6>
           	    <h6 class="card-text">物件顏色:${ComServiceForBidWinner.selectOnlineDaiGo(ComBidVO.com_id).it_col}</h6> 
           	    <h6 class="card-text">物件大小:${ComServiceForBidWinner.selectOnlineDaiGo(ComBidVO.com_id).it_sz}</h6> 
           	   
           	    <h6 class="card-text">當初承諾金額:${ComBidVO.auc_prc}</h6>
           	    
           </div>
            </a>
        </div>
    
     </c:forEach>
     </c:if>
       
     <c:if test="${JointButList ne null}">
     
          <c:forEach var="ComPantVO" varStatus="Count" items="${JointButList}">
          <c:if test="${ComServiceForBidWinner.selectOnlineDaiGo(ComPantVO.com_id).com_sts eq '投票結束' || ComServiceForBidWinner.selectOnlineDaiGo(ComPantVO.com_id).com_sts eq '購買確認中'||ComServiceForBidWinner.selectOnlineDaiGo(ComPantVO.com_id).com_sts eq '開始購買'}">
           <a href="<%=request.getContextPath()%>/ComGroupEditController?type=GroupEdit&com_id=${ComBidVO.com_id}">
         <div class="card w-75 JoinBidCard" style="text-align:left; padding:20px;">

           

           
              <img class="WinnerComImg" src="<%=request.getContextPath()%>/ImgHandler?name=${ComBidVO.com_id}&type=COM_PIC1" style="vertical-align:middle; display:inline-block; margin-right:20px;">
               
             <div class="card-body btn" style="vertical-align:middle;display:inline-block; width:550px;">
              
          <span><h1 class="card-title">案件標題:${ComServiceForBidWinner.selectOnlineDaiGo(ComPantVO.com_id).com_tit}</h1></span>
                ${ComPantVO.com_id}
             	<h6 class="card-text">案件內文:${ComServiceForBidWinner.selectOnlineDaiGo(ComPantVO.com_id).com_cnt}</h6>
             	<h6 class="card-text">案主提示訊息:${ComServiceForBidWinner.selectOnlineDaiGo(ComPantVO.com_id).com_rmd}</h6>
           	    <h6 class="card-text">物件顏色:${ComServiceForBidWinner.selectOnlineDaiGo(ComPantVO.com_id).it_col}</h6>
           	    <h6 class="card-text">物件大小:${ComServiceForBidWinner.selectOnlineDaiGo(ComPantVO.com_id).it_sz}</h6>
                
           </div>
           
            
        </div>
        </a>
       </c:if>
     </c:forEach>

     </c:if>
     
     
     
</body>
</html>