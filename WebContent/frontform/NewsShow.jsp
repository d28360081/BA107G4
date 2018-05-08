<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.news.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<%
request.getAttribute("nsVO");
%>




<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>News</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/gogo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.4.0/animate.min.css">
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<style type="text/css">
  
    .navbar-center {
      display: inline-block;
      float: none;
      vertical-align: top;
    }

    .navbar-collapse-center {
      text-align: center;
    }
    .pagination .active a {
      background-color: #3ab2d9;
      border-color: #C9D9DF;
      color:#ffffff;
      cursor: default;
    }
    .pagination .active a:hover {
      background-color: #FFFFFF;
      color:#000000;
      border-color: #C9D9DF;
      cursor: default;
    }
	.listnews{
	margin-top: 100px;
	}
	.listnews2{
	margin-top: 10px;
	}
	.sbar{
	background-color:#16c2d0;
	line-height:30px;
	font-size:150%;
	margin:5px;
	display:inline-block;
	width:100%;
	color:white;
	font-family:"微軟正黑體";
	}
	
</style>
         <c:if test="${not empty errMsg}">
	    <script>
	    $(document).ready(function (){
	   	 <c:forEach var="msg" items="${errMsg}">
	    window.alert('<c:out value="${msg}"/>');
	   	 </c:forEach>
	    });
	    </script>
	    </c:if>

</head>

<body>
     <%@ include file="/com.HeaderFooter/FrontHeader.jsp"%>
    <div class="gradient"></div>
    <section>
    <div class="container bbbc">
<div class="col-xs-12 col-sm-3"><a href="href=<%=request.getContextPath()%>/Indexnolongin.jsp">首頁  </a><a href="<%=request.getContextPath()%>/frontform/News.jsp">  最新消息</a></div>
<div class="col-xs-12 col-sm-6"></div>
<div class="col-xs-12 col-sm-3"></div>
</div>
   <div class="col-xs-7 col-xs-offset-2"> 
        
    </div>
 
     
       
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-3">
                <div class="list-group listnews">
                
                    <a href="<%=request.getContextPath()%>/frontform/News.jsp" class="list-group-item active">
                      <p class="list-group-item-text">最新消息</p>
                    </a>
                    
                    <a href="<%=request.getContextPath()%>/frontform/AboutUs.jsp" class="list-group-item ">
                      <p class="list-group-item-text">關於我們</p>
                    </a>
                    


                </div>
            </div>
                 <div class="col-xs-12 col-sm-9 text-left">
                    <!-- 標籤面板：內容區 -->
                  <h1 class="green sbar" style="text-align: center" >最新消息</h1>
                    <hr style="border:1px #3ab2d9 solid; margin-bottom: 10px "><br>
                      <div class="container listnews2" align="left">
                        <div class="row">
                          <div class="col-xs-12 col-sm-2">
                          		 ${nsVO.ns_date}
                          </div>
                          
                          </div>
                          </div>
                        
                        	<div style='margin-bottom:20px;font-weight:bold;text-align:center'>
                           		<h1 class="lgreen">${nsVO.ns_tit}</h1>
                           	</div>
                        	<div>
                         		 ${nsVO.ns_cnt}
                        	</div>
                     </div>  
				</div>
              </div>





        <!-- 從這裡開始寫吧 -->
        <!--         <input type="text" class="form-control" style="width: 200px;">
        <button type="submit" class="btn btn-default btn-go ">搜尋</button> -->
<!-- <div class="container">

<table class="table table-striped" >
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
</div> -->

    </section>
    <footer class="footer">
        <div class="row">
			<div class="container add-mobile-gutter">
				<div class="row">
					<div class="col-xs-12 col-sm-12 fix-col-xs">
						<div class="row">
							<ul class="link-list">
								<li><a href="<%=request.getContextPath()%>/frontform/News.jsp">最新消息</a></li>
								<li><a href="FAQ_bonus.asp">紅利點數</a></li>
								<li><a href="FAQ_order.asp">購物須知</a></li>
								<li><a href="FAQ_returns-exchanges.asp">售後服務</a></li>
								<li><a href="FAQ_order-overseas.asp">INTERNATIONAL
										ORDERS</a></li>
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
								<li><a href="TWCA.asp" target="_blank"><span
										class="lock"></span>安心購物</a></li>
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
</body>


</html>