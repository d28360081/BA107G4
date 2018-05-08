<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>GogoShop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/gogo.png">

  <style>
  .topic::after{
     content: "";
    display: block;
    height: 15px;
    width: 100%;
    z-index: -1;
    bottom: -29%;
    left: -2%;
    position: absolute;
    background-color: #C9D9DF;
    border-radius: 20px;
    opacity: .6;
    display: inline;
  }
  .topic{
  margin-top:3%;
  
  }
  
  
  </style>

</head>
<body>

<jsp:useBean class="com.combid.model.ComBidService" id="ComBidSrc"/>
<jsp:useBean class="com.member.model.MemDAO" id="memdao"/>
<jsp:useBean class="com.com.model.ComService" id="comservice"/>
<div class="col-xs-12 col-sm-12" style="
    height: 100px;
    background-color: #39d0cb;
">
    <div>
        <p style="
    color: white;
    float: left;
    position:  absolute;
    top: 30%;
    left: 2%;
    font-size: 28px;
    font-weight: 900;
">${comservice.select(param.com_id).com_tit}&nbsp;投下你神聖的一票吧&nbsp;&nbsp;<span class="glyphicon glyphicon-play"></span><span class="glyphicon glyphicon-play"></span></p>
    </div>
</div>

<div class="container" style="
      height: 50px;
    padding: 2%;
    width: 150px;
    float: left;">
    <p class="topic">代購人列表</p>
</div>

 <div class="container">

 <c:if test="${ComBidSrc.selectAll(param.com_id).size()>0}">
<c:forEach var="ComBidVO" items="${ComBidSrc.selectAll(param.com_id)}" begin="0" end="2">
        
                <div class="col-xs-12 col-sm-4" style="margin-top:8%;margin-bottom:4%;">
                    <!--    案件1 -->
                    <a href="#">
                        <div class="card">
                            <div class="purchase-head ">
                                <div class="user-avatar"><img src="<%=request.getContextPath()%>/member/MemberPhotoReader?mem_id=${ComBidVO.mem_id}" alt=""></div>
                                <div class="subject">
                                    <h5 class="green">${memdao.findByPrimaryKey(ComBidVO.mem_id).acc}</h5>
                                </div>
                            </div>
                            <hr>
                            <div class="purchase-body">
                                <ul class="purchase-list"> 
                                    <li>投標金額：${ComBidVO.auc_prc} 元</li>
                                    <li>投標運費：${ComBidVO.auc_del_prc} 元</li>
                                </ul>
                            </div>
                            <div class="purchase-foot ">
                                <a class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green" href="<%=request.getContextPath()%>/CombidController?type=vote&mem_id=${ComBidVO.mem_id}&com_id=${ComBidVO.com_id}" role="button">投票</a>
                            </div>
                        </div>
                    </a>
                    </div>
                    </c:forEach>
                    

 </div>


 <div class="container">
<c:forEach var="ComBidVO" items="${ComBidSrc.selectAll(param.com_id)}" begin="3" end="5">
        
                <div class="col-xs-12 col-sm-4" style="margin-top:5%;">
                    <!--    案件1 -->
                    <a href="#">
                        <div class="card">
                            <div class="purchase-head ">
                                <div class="user-avatar"><img src="img/avatar.jpg" alt=""></div>
                                <div class="subject">
                                    <h5 class="green">${ComBidVO.mem_id}</h5>
                                </div>
                            </div>
                            <hr>
                            <div class="purchase-body">
                                <ul class="purchase-list">
                                    <li>會員權限:</li>
                                    <li>期望金額：${ComBidVO.auc_prc} 元</li>
                                    <li>期望運費：${ComBidVO.auc_del_prc} 元</li>
                                    <li>商品數量：1</li>
                                </ul>
                            </div>
                            <div class="purchase-foot ">
                                <a class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green" href="<%=request.getContextPath()%>/CombidController?type=vote&mem_id=${ComBidVO.mem_id}&com_id=${ComBidVO.com_id}" role="button">投票</a>
                            </div>
                        </div>
                    </a>
                    </div>
</c:forEach>
</c:if>
 </div>
 <div class="col-xs-12 col-sm-12" style="
    position: absolute;
    background-color: #b1c0c0;
    bottom: 0;
    height: 80px;
">
   

 </div>
<c:if test="${ComBidSrc.selectAll(param.com_id).size()==0}">
  沒有人競標喔
</c:if>





<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>

