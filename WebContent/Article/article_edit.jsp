<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.article.model.*"%>
<% 
	MemVO memVO = (MemVO) session.getAttribute("MemVO");

	MemService memSrc = new MemService();
	MemVO memVO2 = memSrc.getByAcc(memVO.getAcc());
%>
<!DOCTYPE html>
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
    <!-- Include stylesheet -->
    <script src="https://cdn.ckeditor.com/4.9.1/standard/ckeditor.js"></script>
    <style type="text/css">
        .edit{
            height: 1020px;
            width: 1022px;
        }
        #art_cnt{
            height: 1020px;
            
        }
        .send{
          margin-top: 20px;
        }
        .edit{
          margin-top: 30px;
        }
        #title{
          margin-bottom: 50px;
        }
       	#preview{
       	  margin-top: 40px;
       	}
    </style>
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
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
          <a href="index.html">首頁/  </a><a href="<%=request.getContextPath()%>/Article/articleIndex_h.jsp">文章專區/  </a>編輯文章
        </div>
        <div class="col-xs-12 col-sm-6"></div>
        <div class="col-xs-12 col-sm-3"></div>
      </div>
    </div>
     <div class="panel panel-default">
      <form action="<%=request.getContextPath()%>/ArticleEdit"  method="post" enctype="multipart/form-data">
          <div class="panel-heading">
          	<div class="container">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
              <h1>編輯文章</h1>
              <br>
              <div class="form-group" id="title">
                <label class="col-sm-4 control-label" for="art_tlt" >文章標題</label>
                <div class="col-sm-5">
                  <input type="text" class="form-control" id="art_tlt" name="art_tlt" placeholder="請輸入文章標題" />
                </div>
              </div>
              <br>
              <div class="form-group" >
				<label class="col-sm-4 control-label" for="art_pic1" id="pic">上傳文章封面圖片</label>
				<div class="col-sm-5">
					<input type="file" class="form-control" placeholder="請選擇文章封面圖片" name="art_pic1" id="art_pic1" onchange="document.getElementById('preview').src=window.URL.createObjectURL(this.files[0])"  >
                  	<img width="250" height="200" id="preview" >
				</div>
			  </div>
              <br>
              <div class="container">
					<div class="row">
						<div class="col-xs-12 col-sm-12">
			  				<c:if test="${not empty errMsgs}">
								<font style="color:#ff8080">請修正以下錯誤:</font>
								<ul>
								<c:forEach var="message" items="${errMsgs}">
								<li style="color:#ff8080">${message}</li>
								</c:forEach>
								</ul>
		  	  					</c:if>
		  	  			</div>
		  	  		</div>
			  </div>
          </div>
          </div>
          </div>
          </div>
          
      <div class="panel-body">
        <div class="container edit">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                  	<div class="form-group">
						<input type="text" name="art_cnt" width="1022px" height="1020px" id="art_cnt" value="" >
						<script>
                    		CKEDITOR.replace( "art_cnt",{
                    			width: 1022,
                    			height: 1020
                    		} );
                    		
                    		function setValue(){
                    			var data = CKEDITOR.instances.art_cnt.getData();
                    			document.getElementById("art_cnt").value=data;
                    		}
                    		
                  		</script>
					</div>

                    <div class="form-group">
						<input type="hidden" name="mem_id" value="<%=memVO2.getMem_id()%>">
                        <input type="hidden" name="action" value="artEdit">
                        <input type="hidden" name="bonus" value="<%=memVO2.getBonus()%>">
                        <input type="submit" class="btn btn-info" onclick="setValue()" value="確認送出">		
						</a>
					</div>
                 </div>
             </div>
         </div>
        </div>
        </form>
        	<button onclick="art()"></button>
					<script>
						function art(){
							var art_tlt = document.getElementById("art_tlt");
							art_tlt.value="Disney坐姿立體娃娃";
							CKEDITOR.instances.art_cnt.setData
							('<p>;【商品尺寸】<br />;約6．5;12．5;7．5cm<br /><br />【商品介紹】<br />新出了高飛和布魯托好可愛呀！</p><p>日本大人氣熱賣商品，<br />;多款經典迪士尼娃娃，非常精緻可愛喔!<br />;因應大家喜歡帶娃娃出門拍照的風潮，<br />;設計了這款輕巧方便攜帶，坐姿設計輕鬆融入各種景色喔，<br />;雙手端正的坐姿超可愛的!還可以放筆或花束在手中～<br />;也可以往前靠著做出不同的姿勢～非常有趣！<br /><br />;圖片為日本網友提供分享，僅供參考喔，<br />;只有選項中的款式，可以多留候補款式：）<br />*注意如需不同款式，要分開下標喔!</p><p><br />;　</p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636504176165942612.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636504174435923714.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729423087962.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729257520862.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456728870271846.jpg" /><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456728870584350.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456728869803129.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729114408556.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729896695587.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729896226558.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729895758102.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729894351615.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729890444705.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729890132204.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729884794025.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456729884481199.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456730691265115.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456730690796648.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456730690327553.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456730689702464.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456730689233951.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456730688921092.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456730687358428.jpg" /></p><p><img src="http://image.ninijp.com.tw/MBC/ProductQuickImg/320/0/Y175/636456730682847010.jpg" /></p>');
						}
					</script>
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