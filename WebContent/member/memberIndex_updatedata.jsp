<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<% 
	MemVO memVO = (MemVO) session.getAttribute("MemVO");
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
    <style type="text/css">
         .photo{
         	margin-top: 20px;
            margin-left: 75px;
            margin-bottom: 20px;
        	width: 100px;
        	height: 100px;
        	border-radius: 50%;
			overflow: hidden;
         }
         .pic img{
            width:100%;
            height:100%;
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
        .data{
            margin-top: 25px;
        }
        #cancel{
        	margin-top: 10px;
        	
            margin-bottom: 50px;
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
                        <a href="index.jsp">首頁/  </a><a href="<%=request.getContextPath()%>/member/memberIndex2.jsp">會員專區/  </a>修改基本資料
                    </div>
                    <div class="col-xs-12 col-sm-6"></div>
                    <div class="col-xs-12 col-sm-3"></div>
                </div>
        </div>
    <div class="container">
        <div class="row">
            <%-- <div class="col-xs-12 col-sm-3">
                
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

                <div class="list-group">
                <a href="memberIndex_commision.html" class="list-group-item ">代購管理</a>
                <a href="memberIndex_updatedata.jsp" class="list-group-item active">修改基本資料</a>
                <a href="member_order.html" class="list-group-item">訂單管理</a>
                <a href="member_bonus.html" class="list-group-item">紅利管理</a>
                <a href="memberIndex_article.jsp" class="list-group-item">文章管理</a>
                <a href="member_mission.html" class="list-group-item">任務管理</a>
                </div>
            </div> --%>
                <!-- <div class="col-xs-12 col-sm-9"> -->
 					<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">修改會員資料</h3>
						<c:if test="${not empty errMsgs}">
							<font style="color:#ff8080">請修正以下錯誤:</font>
						<ul>
						<c:forEach var="message" items="${errMsgs}">
							<li style="color:#ff8080">${message}</li>
						</c:forEach>
						</ul>
						</c:if>
					</div>
					<div class="panel-body">
						<form  method="post" class="form-horizontal" action="login" enctype="multipart/form-data">
							<div class="form-group">
								<label class="col-sm-4 control-label" for="acc">帳號</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="acc" name="acc" value="<%=memVO.getAcc()%>" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="psw">密碼</label>
								<div class="col-sm-5">
									<input type="password" class="form-control" id="psw" name="psw" placeholder="請輸入密碼(5個英文數字混合)" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="cpsw">確認密碼</label>
								<div class="col-sm-5">
									<input type="password" class="form-control" id="cpsw" name="cpsw"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_name">暱稱</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="mem_name" name="mem_name" value="<%=memVO.getMem_name()%>"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="bir_dt">生日</label>
								<div class="col-sm-5">
									<input type="date" class="form-control" id="bir_dt" name="bir_dt" value="<%=memVO.getBir_dt()%>"/>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="address">地址</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="address" name="address"   value="<%=memVO.getAddress()%>"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_email">電子信箱</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="mem_email" name="mem_email" value="<%=memVO.getMem_email()%>" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_photo">上傳大頭照</label>
								<div class="col-sm-5">
									<input type="file" class="form-control" name="mem_photo" onchange="document.getElementById('preview').src=window.URL.createObjectURL(this.files[0])"  >
                  					<img width="250" height="200" id="preview" src="<%=request.getContextPath()%>/member/MemPhoto?acc=<%=memVO.getAcc()%>">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_pay">選擇付款方式</label>
								<div class="col-sm-5">
									<select name="mem_pay" id="paySelect">
									    <option value="現金匯款" ${(memVO.mem_pay=='現金匯款')?'selected':''}>現金匯款</option>
									    <option value="信用卡匯款" ${(memVO.mem_pay=='信用卡匯款')?'selected':''}>信用卡匯款</option>
									    <option value="ATM匯款" ${(memVO.mem_pay=='ATM匯款')?'selected':''}>ATM轉帳</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-9 col-sm-offset-4">
									<input type="hidden" name="mem_photo2" value="<%=memVO.getMem_photo()%>">
									<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
                                	<input type="hidden" name="action" value="update">
                                    <input type="submit" class="btn btn-info" value="確認修改">
                                    <a href="<c:url value="/member/login"><c:param name="action" value="back"/></c:url>"class="btn btn-go" role="button">取消</a>
								</div>
							</div>
						</form>
					</div>
				</div>
                                <!-- </div> -->
                            </div>
                        </div>
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