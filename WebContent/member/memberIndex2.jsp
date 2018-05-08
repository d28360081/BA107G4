<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@page import="com.PersonalStore.model.*"%>
<!DOCTYPE html>

<% 
MemVO memVO = (MemVO) session.getAttribute("MemVO");
request.setAttribute("memVO", memVO);
String mem_id=memVO.getMem_id();
request.setAttribute("mem_id", mem_id);

PersonalStoreVO MempersonalStore2=new PersonalStoreVO();
PersonalStoreService  personalStoreService2=new PersonalStoreService();
MempersonalStore2=personalStoreService2.findByMemId(mem_id);
request.setAttribute("MempersonalStore2", MempersonalStore2);
%>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" href="img/gogo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    
    <style type="text/css">
        
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
        .checked {
            color: orange;
        }
        #panel{
          width: 650px;
        }
        #m{
          margin-right:  300px;
        }
        .photo{
        	width: 210px;
        	height: 200px;
        	border-radius: 50%;
			overflow: hidden;
        	
        }
        .photo	img{
        	width:100%;
            height:100%;
        }
    </style>
	<script type="text/javascript">
           
	</script>
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
            <div class="col-xs-12 col-sm-3">
                <a href="index.html">首頁/  </a><a href="memberIndex2.jsp">會員專區  </a>
            </div>
            <div class="col-xs-12 col-sm-6"></div>
            <div class="col-xs-12 col-sm-3"></div>
        </div>
    </div>

    <div class="container" id="m">
      <div class="row">
        <div class="col-xs-12 col-sm-3">
          <br><br>
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">頭像</h3>
            </div>
            <div class="panel-body">
                  <div class="container">
                	<div class="row">
                  		<div class="col-xs-12 col-sm-2"><img src="<%=request.getContextPath()%>/member/MemPhoto?acc=<%=memVO.getAcc()%>" class="photo"></div>
                  		<div class="col-xs-12 col-sm-3"></div>
                  		<div class="col-xs-12 col-sm-2"></div>
                  		<div class="col-xs-12 col-sm-2"></div>
                  		<div class="col-xs-12 col-sm-2"></div>
                  		<div class="col-xs-12 col-sm-2"></div>
                	</div>
              	   </div>
                  <br><br><br>
                  <font><%=memVO.getAcc()%></font>
                  <br>
                  <br>
                  <ul class="list-inline">
                    <li><a href="<%=request.getContextPath()%>/BonusStore/bonusOrderDetails.jsp">查看紅利商品訂單</a></li>
                    <li><a href="memberIndex_article.jsp">查看個人文章</a></li>
                    <li><a href="memberIndex_updatedata.jsp">修改個人資料
                    
                    </a></li>
                    <!-- <li><a href="#">查看任務</a></li> -->
                  </ul>
            </div>
          </div>
        </div>
        <div class="col-xs-12 col-sm-9">
          <br><br>
          <div class="panel panel-default" id="panel">
            <div class="panel-heading">
              <h3 class="panel-title">個人資料</h3>
            </div>
            <div class="panel-body">
                <ul class="list-unstyled">
                  <table class="table table-hover">
                    <tbody>
                      <tr>
                        <td>帳號</td>
                        <td><%=memVO.getAcc() %></td>
                      </tr>
                      <tr>
                        <td>我的電子信箱</td>
                        <td><%=memVO.getMem_email() %></td>
                      </tr>
                      <tr>
                        <td>紅利點數</td>
                        <td><%=memVO.getBonus() %> <br><a href="<%=request.getContextPath()%>/BonusStore/bonusstoreIndex.jsp">前往紅利商店</a></td>
                      </tr>
                      <tr>
                        <td>付款方式</td>
                        <td><%=memVO.getMem_pay() %></td>
                      </tr>
                      <tr>
                        <td>配送地址</td>
                        <td><%=memVO.getAddress() %></td>
                      </tr>
                      <tr>
                        <td>我的商店</td>
                          <c:if test="${MempersonalStore2 eq null}">
                        <td><a id="shop" href="<%=request.getContextPath()%>/PersonalStore/AddPersonalStore.jsp">前往商店管理</a></td>
                        </c:if>
                        <c:if test="${MempersonalStore2 ne null}">
                        <td><a id="shop" href="<%=request.getContextPath()%>/OrderListFront/OrderListPersonalStore.jsp">前往商店管理</a></td>
                        </c:if>
                      </tr>
                      <tr>
                        <td>我的評價</td>
                        <td>
                        
                          <span class="fa fa-star" id="1"></span>
                          <span class="fa fa-star" id="2"></span>
                          <span class="fa fa-star" id="3"></span>
                          <span class="fa fa-star" id="4"></span>
                          <span class="fa fa-star" id="5"></span>
                          <script>
                          	var i=<%=memVO.getMem_eva()%>;
                          	for(j=1 ; j<=i ; j++){
                          		document.getElementById(j).classList.add("checked");
                          	}
                          </script>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </ul>
            </div>
          </div>
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