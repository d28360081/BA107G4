<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>AboutUs</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/gogo.png">
    <!--  animate -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.4.0/animate.min.css">
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
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
</head>

<body>
    <%@ include file="/com.HeaderFooter/FrontHeader.jsp"%>
    
    <div class="gradient"></div>
    <section>
    <div class="container bbbc">
<div class="col-xs-12 col-sm-3"><a href="<%=request.getContextPath()%>/indexnologin.jsp">首頁  </a>/<a href="<%=request.getContextPath()%>/frontform/AboutUs.jsp">關於我們</a></div>
<div class="col-xs-12 col-sm-6"></div>
<div class="col-xs-12 col-sm-3"></div>
</div>
   <div class="col-xs-7 col-xs-offset-2"> 
        
    </div>
 
     
       
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-3">
               <div class="list-group listnews">
                      
                         <a href="<%=request.getContextPath()%>/frontform/News.jsp" class="list-group-item">
                      <p class="list-group-item-text">最新消息</p>
                    </a>
                    
                    <a href="<%=request.getContextPath()%>/frontform/AboutUs.jsp" class="list-group-item  active">
                      <p class="list-group-item-text">關於我們</p>
                    </a>



                </div>
            </div>
                 <div class="col-xs-12 col-sm-9 text-left">
                    <!-- 標籤面板：內容區 -->
					<h1 class="green sbar" style="text-align: center" > 關於我們</h1>
                    <hr style="border:1px #3ab2d9 solid; margin-bottom: 10px "><br>
                            <h1 class="blue">海外狗GO簡介</h1>
                                <p>現今社會，因為科技進步，資訊流通快速，讓人們更快一手掌握國內外的各種資訊，也因為資訊發達，網路購物也成了現今消費方式的重要來源之一。然而國內並未引進的部分國際商品價格過高，或是數量極有限的情況下，我們成立了海外狗GO平台，以更合理的方式，讓國人能透過網絡進行海外商品購買、代售。</p>
                                    
                                 <p>同時，以往的代購平台，雖提共五花八門的服務，但是服務內容卻是包山包海，讓許多想要海外代購的民眾不得其門而入。</p>
                                    
                                 <p>因此，海外狗GO平台，希望將讓所有對海外商品有興趣的台灣民眾們能用最簡便的方式以及最便宜的價格，購買到心儀的商品。</p>
                                 
                                 <p>整個平台以人為出發點，註冊會員可以擔任購買以及提供者的角色，購買商品前可以透過代購人文章，以及曬貨心得，搶先了解產品以及當地特色。 </p>
                                 
                                 <p>代購的過程可以是真實並且建立在人與人之間的互動，我們即將帶你前往另外一個世界，海外狗 GO ！ </p>
                                 
                          <table>
                          	  <tr><td><h1 class="blue">客服時間</h1> </td></tr>
                              <tr><td>週一至週五 9:00~12:00 ｜13:30~16:30</td></tr>
                          	  <tr><td> <h1 class="blue">客服電話</h1></td></tr> 
                              <tr><td>客服專線(代表號)：03-4257387</td></tr>
                              <tr><td><h1 class="blue">客服信箱</h1></td></tr>
                              <tr><td>support@gogoshop.com.tw</td></tr>                              
                          </table>
                          
                                   
                                                  
                          <h1 class="blue">電子地圖</h1>
                          <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1808.4825132509477!2d121.19064309837205!3d24.967304511532454!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x346823ea50c732a5%3A0x1b5e6ee66e9fec49!2z6LOH562W5pyD5Lit5aOiIFRpYmFNZSDlnIvpmpvkurrmiY3nmbzlsZXkuK3lv4M!5e0!3m2!1szh-TW!2stw!4v1521539396256" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>

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