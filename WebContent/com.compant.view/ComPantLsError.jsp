<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getServletContext().getContextPath() %>/css/base.css">
    <link rel="icon" href="<%=request.getServletContext().getContextPath() %>/img/gogo.png">
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    <style>
    .success {
        padding-top: 20px;
        margin-bottom: 60px;

    }

    .success a {
        padding-bottom: 15px;
    }

    .backhome {
        margin-bottom: 40px;
    }

    h2 {
        padding-bottom: 15px;
    }
    </style>
</head>

<body>
   <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>

    <div class="container success">
        <img src="<%=request.getServletContext().getContextPath() %>/img/gogo_cross.png" alt="">
    </div>
    <h2 ><font color="#AA0000">${Message}</font></h2>
    <div class="container">
        <table class="table table-info">

            <tr>
                <td colspan="2">代購標題</td>
                <td>主辦人帳號</td>
                 <td>開始時間</td>
                <td>結束時間</td>
                <td>案件狀態</td>
            </tr>
            <% if(request.getAttribute("ComVO")!=null){%>
             <tr>
                <td colspan="2">${ComVO.com_tit}</td>
                <td>${ComVO.mem_id}</td>
                <td>${ComVO.com_e_dt}</td>
                <td>${ComVO.com_s_dt}</td>
                <td>${ComVO.com_sts}</td>
            </tr>
            <%} %>
            <tr>
                <td colspan="2">扣除紅利:</td>
                <td colspan="2"><font color="red">0</font>點</td>
            </tr>
            <tr>
            </tr>
        </table>
    </div>
    <div class="container">
        <div class="col-xs-12 col-sm-4"></div>
        <div class="col-xs-12 col-sm-4">
            <br>
            <a href="<%=request.getContextPath() %>/Com_Controller?type=checklist&page=1" class="btn btn-info backhome">回首頁</a>
        </div>
        <div class="col-xs-12 col-sm-4"></div>
    </div>
    <footer class="footer">
        <div class="row">
            <div class="container add-mobile-gutter">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 fix-col-xs">
                        <div class="row">
                            <ul class="link-list">
                                   <li><a href="<%=request.getContextPath()%>/frontform/News.jsp">最新消息 </a></li>
                                <li><a href="<%=request.getContextPath()%>/BonusStore/bonusstoreIndex.jsp">紅利點數</a></li>
                                <li><a href="<%=request.getContextPath()%>/frontform/AboutUs.jsp">關於我們</a></li>
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