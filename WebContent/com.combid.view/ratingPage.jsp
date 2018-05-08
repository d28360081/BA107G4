<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/gogo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--  animate -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.4.0/animate.min.css">
     <script src="https://cdn.ckeditor.com/4.9.1/standard/ckeditor.js"></script>
    <!--  hover -->
    <!--   <link href="css/hover.css" rel="stylesheet" media="all"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<style>
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
    height: 20%;
    background-color: #39D0CB;
    text-align: left;
    padding-left: 10px

}
.bottmTit{

    color: black;
    font-size: 25px;
    margin-bottom: 0px;
}
.bottmTime{
        margin-bottom: 0px;
}
.bottomImg{
    margin-bottom: 0px;
}
.checkBtn{
float: right;
    border: 1px solid black;
    width: 50px;
    height: 50px;
    border-radius: 50%;
    margin: 3%;

}
* {
    box-sizing: border-box;
}



.heading {
    font-size: 25px;
    margin-right: 25px;
}

.fa {
    font-size: 25px;
}

.checked {
    color: orange;
}

/* Three column layout */
.side {
    float: left;
    width: 15%;
    margin-top:10px;
}

.middle {
    margin-top:10px;
    float: left;
    width: 70%;
}

/* Place text to the right */
.right {
    text-align: right;
}

/* Clear floats after the columns */
.row:after {
    content: "";
    display: table;
    clear: both;
}

/* The bar container */
.bar-container {
    width: 100%;
    background-color: #f1f1f1;
    text-align: center;
    color: white;
}

/* Individual bars */
.bar-5 {width: 60%; height: 18px; background-color: #4CAF50;}
.bar-4 {width: 30%; height: 18px; background-color: #2196F3;}
.bar-3 {width: 10%; height: 18px; background-color: #00bcd4;}
.bar-2 {width: 4%; height: 18px; background-color: #ff9800;}
.bar-1 {width: 15%; height: 18px; background-color: #f44336;}

/* Responsive layout - make the columns stack on top of each other instead of next to each other */
@media (max-width: 400px) {
    .side, .middle {
        width: 100%;
    }
    .right {
        display: none;
    }
}
#cke_editor1{
    display: inline-block;
    margin-top:3%;
    margin-bottom:5%;
}
</style>

</head>


<body>
<jsp:useBean class="com.combid.model.ComBidService" id="combidservice"/>
<jsp:useBean class="com.member.model.MemService" id="memservice"/>
    <nav class="navbar navbar-default navbar-default-custom" role="navigation">
        <div class="container container-top">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">選單切換</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"><img src="<%=request.getContextPath()%>/res/logo.svg" alt=""></a>
            </div>
            <!-- 手機隱藏選單區 -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <!-- 左選單 -->
                  <ul class="nav navbar-nav nav-custom">
                    <ul class="nav navbar-nav nav-custom">
                    <li><a href="ShoppingMall.html " class="hvr-underline-from-left active">官方商店</a></li>
                    <li><a href="Commision.html" class="hvr-underline-from-left">代購專區</a></li>
                    <li><a href="Commision-Joint.html" class="hvr-underline-from-left">
                    揪團專區</a></li>
                    <li><a href="articleIndex.html" class="hvr-underline-from-left">文章專區</a></li>                
                    <li><a href="demo.html" class="hvr-underline-from-left">促銷專區</a></li>
                    <li><a href="memberIndex_commision.html" class="hvr-underline-from-left">會員中心</a></li>
                </ul>
                </ul>
                <!-- 搜尋表單 -->
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="輸入關鍵字">
                    </div>
                    <button type="submit" class="btn btn-default btn-go ">搜尋</button>
                </form>
                <!-- 會員下拉選單 -->
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    張藝您好
                    <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="memberIndex_commision.html" class="hvr-underline-from-left">會員專區</a></li>
                            <li><a href="#" class="hvr-underline-from-left">我的收藏</a></li>
                            <li><a href="#" class="hvr-underline-from-left">我的訂單</a></li>
                            <li><a href="memberIndex_updatedata.html" class="hvr-underline-from-left">修改會員資料</a></li>
                            <li><a href="#" class="hvr-underline-from-left">登出</a></li>
                        </ul>
                    </li>
                    <li><a href="#" onclick="printDiv()">登出</a></li>
                </ul>
            </div>
            <!-- 手機隱藏選單區結束 -->
        </div>
    </nav>
    <div class="gradient"></div>
   <section >
        <div class="container">
         <p style="margin: 6%;font-size:24px;">
                                  恭喜您,現在已經成功完成案件了,<br>發送一篇曬貨文章吧,跟大家炫耀一下你的心得與感想<br>
             
             
             
             
             </p>
             <div style="    display: inline-block;
    width: 360px;
    height: 180px;
    margin: 2%;">
                 <img src="<%=request.getContextPath()%>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC1" style="    width: 100%;
    height: 100%;">
             </div>
            
               <form  action="<%=request.getContextPath()%>/ComCorController" method="post">
                    <span style="font-size:20px;">案件編號:${ComVO.com_id }</span><br>
                    <span style="font-size:20px;">案件名稱${ComVO.com_tit }</span><br>
                    <div style="margin:2%;">
                    <div style="border: 1px solid #b0b0b061;margin-bottom: 2%;"></div>
                     <span style="font-size:20px;">珍惜代購人的心血,給予幾顆星星作為打賞吧</span><br>
                     
		                     <div style="margin-bottom: 2%;margin-top: 2%;">
		                            <div>
		                            <div style="background-image:url(<%=request.getContextPath()%>/member/MemberPhotoReader?mem_id=${combidservice.selectBiddingWinner(ComVO.com_id).mem_id});width: 70px;height: 70px;display: inline-block;border-radius:  50%;"></div>
				                    	<span class="heading" style="position: absolute;padding-top: 1%;padding-left: 2%;display: inline;">${memservice.getOneMem(combidservice.selectBiddingWinner(ComVO.com_id).mem_id).acc}</span>
				                    </div>
				                    <div style="margin-top: 2%;">
					                    <span class="fa fa-star"></span>
					                    <span class="fa fa-star"></span>
					                    <span class="fa fa-star"></span>
					                    <span class="fa fa-star"></span>
					                    <span class="fa fa-star"></span>
					                </div>
		                    </div>
                    </div>
                    <div style="border: 1px solid #e1e1e1;margin-bottom: 2%;"></div>
                   
                    <input id="rating" name="rating" value="" type="hidden"></div>
                    <input type="hidden" value="${MemVO.mem_id}" name="mem_id">
                    <input type="hidden" value="${ComVO.com_id}" name="com_id">
                    <input type="hidden" value="SanHo" name="type">
                     <div class='btn-go' id="btn1" style="border:1px solid;width:5%;">""</div> 
                    <textarea id="editor1" name="editor1" style="max-width: 300px;"></textarea>  
                    
                    <br><button type="submit" class="btn btn-go">送出</button>
              </form>
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
    <!-- banner之下開始 -->
 
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <script>

          CKEDITOR.replace( 'editor1', {width: 1022,
  			height: 680});
          
        
          $('#btn1').click(function(){
        	  
             CKEDITOR.instances.editor1.setData("<p>本來想買BoseSoundlinkmini,實在覺得有點貴&nbsp;<br/>無意間在小黃屋看到LGNP7550,試聽之下覺得也還可以,比bose便宜一半&nbsp;<br/>回家就上價格.com看看<br/>瞎咪!!!日本售價5449丹!!!&nbsp;<br/>剛好又看到7-11也開始與myday合作日本代購,12/8前還有各種優惠&nbsp;<br/>於是就手刀下單了!&nbsp;<br/><br/>(下完單才發現amazon.co.jp才賣4600,但已來不及撤單了,多花849丹,殘念!好在到貨時amazon已漲到5100~5800,也差不多)<br/><br/>11/26在myday完成下單,並匯入第一階段費用(商品價5449+國外當地轉帳手續費350,合台幣1635,坑人匯率,扣除新入會折價券100,實匯1535),接下來的時程如圖<br/><imgsrc='https://attach.mobile01.com/attach/201512/mobile01-0adbd2751d9bb78ef0a6fae38d7e3bd5.jpg'/><br/><br/>總之一週就到貨了,第二階段費用(國際運費台幣317)在小七付現就好;<br/><br/>下面就是簡單不專業開箱<br/><br/>日本郵寄標籤還在外盒上,原箱轉寄<br/><imgsrc='https://attach.mobile01.com/attach/201512/mobile01-c54a8527fac7e95de4b0806730646fe2.jpg'/><br/><imgsrc='https://attach.mobile01.com/attach/201512/mobile01-79bfabe51b55b64b74540eff368bea49.jpg'/><br/><br/>保護弄得不錯<br/><imgsrc='https://attach.mobile01.com/attach/201512/mobile01-7566230f63946bba509acab615507edc.jpg'/><br/><imgsrc='https://attach.mobile01.com/attach/201512/mobile01-e090fafd6635f75c404d7d8cabed585b.jpg'/><br/><br/>2015五月的產品<br/><imgsrc='https://attach.mobile01.com/attach/201512/mobile01-4aaa2fb701b21449db8071c91a352ef1.jpg'/><br/><imgsrc='https://attach.mobile01.com/attach/201512/mobile01-2e1f4cee6b0d849c9494baf2518ce802.jpg'/><br/><br/>打開就是本體+附件盒+無用保證書</p>");
           
          });
          
          
          
          

          $('.fa-star').click(
              
              function(){
                console.log($(this).hasClass('checked'));
                if($(this).hasClass('checked')){
                    $(this).removeClass('checked');
                }
                else{
                     $(this).addClass('checked');
                }
              console.log($('.fa-star').length);
                $('#rating').text($('.checked').length);
                 $('#rating').val($('.checked').length);
                 var data = CKEDITOR.instances.editor1.getData();
             
     			document.getElementById("editor1").value=data;
              }
              
              
            );
          
        


      </script>
</body>

</html>