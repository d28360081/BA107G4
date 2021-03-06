<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="icon" href="img/gogo.png">
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    <style type="text/css">
        #input{
            margin-left: 250px;
        }
        #psw{
            margin-top: 20px;
        }
        #acc{
            margin-top: 20px;
        }
        #btn{
            margin-top: 20px;
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
      <nav class="navbar navbar-default navbar-default-custom" role="navigation">
        <div class="container container-top">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">選單切換</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><img src="res/logo.svg" alt=""></a>
            </div>
            <!-- 手機隱藏選單區 -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <!-- 左選單 -->
                <ul class="nav navbar-nav nav-custom">
                    <li><a href="#" class="hvr-underline-from-left">官方商店</a></li>
                    <li><a href="#" class="hvr-underline-from-left">代購專區</a></li>
                    <li><a href="#" class="hvr-underline-from-left">
                    揪團專區</a></li>
                    <li><a href="#" class="hvr-underline-from-left">文章專區</a></li>
                    <li><a href="#" class="hvr-underline-from-left">聯絡客服</a></li>
                </ul>
                <!-- 搜尋表單 -->
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="輸入關鍵字">
                    </div>
                    <button type="submit" class="btn btn-default btn-go ">搜尋</button>
                </form>
                <!-- 會員下拉選單 -->
                
            </div>
            <!-- 手機隱藏選單區結束 -->
        </div>
    </nav>
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
            <div class="col-xs-12 col-sm-12">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">
            <form action="login" method="post" onsubmit="return validate()">
            <h1 class="modal-title">會員登入</h1>
            <div align="right">沒有帳號嗎?
            <a href="register.html">註冊會員</a>
            </div>
        </h3>
      </div>
      <div class="panel-body">
            <div class="input-group" id="input">
              <label for="acc" class="col-xs-12 col-sm-3 control-label data" id="acc">
                  帳號
              </label>
              <div class="col-xs-12 col-sm-9 data">
                  <input type="text" name="acc" id="acc" placeholder="" class="form-control" value="">
              </div>
              <label for="psw" class="col-xs-12 col-sm-3 control-label data" id="psw">
                  密碼
              </label>
              <div class="col-xs-12 col-sm-9 data">
                  <input type="password" name="psw" id="psw"  class="form-control" value="">
              </div>
            </div>
            <c:if test="${not empty errMsg}">
                 ${errMsg}
            </c:if>
            <input type="hidden" name="action" value="login">
            <input type="submit" class="btn btn-info" id="btn" value="登入">
      </div>
            </form>
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