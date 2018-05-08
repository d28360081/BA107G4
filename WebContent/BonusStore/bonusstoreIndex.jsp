<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.bonusproduct.model.*"%>
<!DOCTYPE html>
<%
	MemVO memVO = (MemVO) session.getAttribute("MemVO");
	MemService memSrc = new MemService();
	memVO = memSrc.getByAcc(memVO.getAcc());
	BonusproService bnsSrc = new BonusproService();
	List<BonusProductVO> listbns =  bnsSrc.getAll();
	pageContext.setAttribute("listbns", listbns);
%>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="icon" href="img/gogo.png">
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            .col-sm-4{
                padding-bottom: 50px;
            }
            .thumbnail{
                margin-right: 3px;
            }
            .bread{
                margin-bottom: 20px;
            }
            .edit{
                margin-top: 20px;
            }
            .list-filter{
                margin-left: 10px;
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
            .bnsdetail{
                margin-top: 10%;
                height: auto;
                margin: auto;
            }
            .detail{
                height: 550px;
                overflow-x: hidden;
                overflow-y: scroll;
            }
            .detail ::-webkit-scrollbar{
                width: 10px;
            }
            .detail ::-webkit-scrollbar-thumb{
                border-radius: 5px;
                background: rgba(0,0,0,.1);
            } 
            .bonus{
                font-size: 20px;
                color: #3AB2D9;
            }
            #font{
                font-size: 16px;
                font-family: verdana;
                font-style: italic;
                color: #ff9999;
            }
            #page{
                margin-left:    900px;
            }
			.pic{
            	width:200px;
            	height:275px;
				overflow: hidden;
            }
            .pic img{
            	width:100%;
            	height:100%;
            }
            .modal-title{
            	font-size:28px;
            	color: #3AB2D9;
            }
            .check{
            	margin-right:	350px;
            }
        </style>
        <c:if test="${not empty errMsgs}">
    	<script>
    		$(document).ready(function (){
    		<c:forEach var="msg" items="${errMsgs}">
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
       <div class="container">
           <div class="row">
               <div class="col-xs-12 col-sm-3 bread">
                   <a href="">首頁/  </a>紅利商店
               </div>
               <div class="col-xs-12 col-sm-6"></div>
               <div class="col-xs-12 col-sm-3"></div>
           </div>
       </div>
        <div id="page">
		<%@ include file="pages/page1.file" %>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <c:forEach var="bnsVO" items="${listbns}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                    <div class="col-xs-12 col-sm-4"><a href='#${bnsVO.bns_it_id}' data-toggle="modal">
                    	<img class="pic" src="<%=request.getContextPath()%>/BNSPhoto?bns_it_id=${bnsVO.bns_it_id}">
                    	</a>
                        <br>
                        <div class="lead">
                            <a href='#${bnsVO.bns_it_id}' data-toggle="modal">${bnsVO.bns_it_name}</a>
                            
                        </div>
                    </div>
                    
                    <div class="modal fade bnsdetail" id="${bnsVO.bns_it_id}">
                		<div class="modal-dialog modal-lg">
                    		<div class="modal-content">
                        	<div class="modal-header">
                            	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            		<h1 class="modal-title">${bnsVO.bns_it_name}</h1>

                        	</div>
                        	<div class="modal-body detail">
                           		${bnsVO.bns_it_intro}
                        	</div>
                        	<div class="modal-footer">
                            	<div class="bonus">您的紅利點數:	<%=memVO.getBonus()%></div>
                            	<div class="bonus">消費紅利點數: ${bnsVO.bns_it_prc}</div>
                            	<br>
                            	<form action="<%=request.getContextPath()%>/buyBns" method="post">
                            	<input type="hidden" name="action" value="buybns"/>
                            	<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>"/>
                            	<input type="hidden" name="bns_it_id" value="${bnsVO.bns_it_id}">
                            	<input type="submit" value="確認購買" class="btn btn-info check"/>
                            	</form>
                        	</div>
                    		</div>
                		</div>
            		</div>
                    </c:forEach>
                </div>
                <br>
					<%@ include file="pages/page2.file" %>
            </div>
           

        </div>
        </div>
    </section>
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
    </footer>
    <script></script>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>