<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.articlereply.model.*"%>
<%@ page import="com.articlecollection.model.*"%>
<!DOCTYPE html>
<html lang="">
<% 
	MemVO memVO = (MemVO) session.getAttribute("MemVO");
	MemService memSrc = new MemService();
	

	
	List<MemVO> listmem = memSrc.getAll();
	
	ArticleService artSrc = new ArticleService();
	
	List<ArticleVO> list = artSrc.findByMem(memVO.getMem_id());
	List<ArtreVO>listre = artSrc.findreBymem(memVO.getMem_id());
	List<ArticleVO> listall = artSrc.getAll();
	List<ArtcolVO> listcol = artSrc.findColByMem_id(memVO.getMem_id());
	
	pageContext.setAttribute("listmem", listmem);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("listre",listre);
	pageContext.setAttribute("listall", listall);
	pageContext.setAttribute("listcol", listcol);
	
	
%>
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
            margin-left: 85px;
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
        #photo{
        	width: 100px;
        	height: 100px;
        	
        }
        .main{
        	height:100%;
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
                <a href="index.html">首頁/  </a><a href="<%=request.getContextPath()%>/member/memberIndex2.jsp">會員專區/  </a>文章管理
            </div>
            <div class="col-xs-12 col-sm-6"></div>
            <div class="col-xs-12 col-sm-3"></div>
        </div>
    </div>
    <div class="container main">
        <div class="row">
            <%-- <div class="col-xs-12 col-sm-3">
                
                <div class="media">
                  <div class="media-left media-bottom">
                    <a href="memberIndex2.jsp">
                      <img class="media-object photo"  src="<%=request.getContextPath()%>/member/MemPhoto?acc=<%=memVO.getAcc()%>" alt="圖片替代文字">
                    </a>
                  </div>
                </div>

                <div class="list-group">
                <a href="memberIndex_commision.html" class="list-group-item ">代購管理</a>
                <a href="memberIndex_updatedata.jsp" class="list-group-item">修改基本資料</a>
                <a href="member_order.html" class="list-group-item">訂單管理</a>
                <a href="member_bonus.html" class="list-group-item">紅利管理</a>
                <a href="memberIndex_article.jsp" class="list-group-item active">文章管理</a>
                <a href="member_mission.html" class="list-group-item">任務管理</a>
                </div>
            </div> --%>
            <!-- <div class="col-xs-12 col-sm-9"> -->
                <div role="tabpanel">
                    <!-- 標籤面板：標籤區 -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">已發表文章</a>
                        </li>
                        <li role="presentation">
                            <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">已回復文章</a>
                        </li>
                        <li role="presentation">
                            <a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">我的收藏</a>
                        </li>
                    </ul>
                
                    <!-- 標籤面板：內容區 -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab1">
                            <table class="table table-hover table-striped table-bordered table-condensed">
                                <thead>
                                    <th>文章標題</th>
                                    <th>發表日期</th>
                                    <th>按讚數量</th>
                                </thead>
                                <tbody>
                                    <c:forEach var="artVO" items="${list}" >
                                    <tr>
                                    	<td>
                                    	<a href="<c:url value="/seeArticle"><c:param name="action" value="seearticle"/><c:param name="art_id" value="${artVO.art_id}"/></c:url>">
                                    		${artVO.art_tlt}
                                    		
                                    	</a>
                                    	
                                    	<a href="#${artVO.art_id}" data-toggle="modal" class="delete">
                                    	<img class="icon" src="https://cdn4.iconfinder.com/data/icons/evil-icons-user-interface/64/basket-16.png">
                                    	</a>
                                    	<div class="modal fade" id="${artVO.art_id}">
											<div class="modal-dialog">
												<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
													<h4 class="modal-title">確定刪除本篇文章</h4>
												</div>
												<div class="modal-body">
												
												<form action="<%=request.getContextPath() %>/deleteArt" method="post">
													<button type="button" class="btn btn-default" data-dismiss="modal">不要</button>
													<input type="hidden" name="action" value="deleteart"/>
													<input type="hidden" name="art_id" value="${artVO.art_id}"/>
													<input type="submit" class="btn btn-info" value="確定"/>
												</form>
												</div>
												</div>
											</div>
										 </div>
                                    	</td>
                                    	<td>${artVO.art_date}</td>
                                    	<td>${artVO.like_num}</td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tab2">
                             <table class="table table-hover table-striped table-bordered table-condensed">
                                <thead>
                                    <th>回復文章標題</th>
                                    <th>發表日期</th>
                                    <th>回復內容</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <c:forEach var="artreVO" items="${listre}" >
                                    <tr>
                                    	<td>
                                    		<c:forEach var="artVO2" items="${listall}" >
                    						<c:if test="${artreVO.art_id==artVO2.art_id}">
                    						<a href="<c:url value="/seeArticle"><c:param name="action" value="seearticle"/><c:param name="art_id" value="${artVO2.art_id}"/></c:url>">
	                    					${artVO2.art_tlt}
                    						</a>
                    						</c:if>
               							 	</c:forEach>
               							 	<a href="#${artreVO.re_id}" data-toggle="modal">
               							 		<img src="https://cdn4.iconfinder.com/data/icons/evil-icons-user-interface/64/basket-16.png">
               							 	</a>
               							 	<div class="modal fade" id="${artreVO.re_id}">
											<div class="modal-dialog">
												<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
													<h4 class="modal-title">確定刪除此篇回復文章</h4>
												</div>
												<div class="modal-body">
												
												<form action="<%=request.getContextPath() %>/deleteRe" method="post">
													<button type="button" class="btn btn-default" data-dismiss="modal">不要</button>
													<input type="hidden" name="action" value="deletere"/>
													<input type="hidden" name="re_id" value="${artreVO.re_id}"/>
													<input type="submit" class="btn btn-info" value="確定"/>
												</form>
												</div>
												</div>
											</div>
										 	</div>
                                    	</td>
                                    	<td>${artreVO.re_date}</td>
                                    	<td>${artreVO.re_cnt}</td>
                                    </tr>
                                    	</c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                            
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tab3">
                            <table class="table table-hover table-striped table-bordered table-condensed">
                                <thead>
                                    <th>文章標題</th>
                                    <th>文章作者</th>
                                    <th>按讚數量</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <c:forEach var="artcolVO" items="${listcol}" >
                                    <tr>
                                    	<c:forEach var="artVO2" items="${listall}" >
                    					<c:if test="${artcolVO.art_id==artVO2.art_id}">
                                    	<td>
                    						<a href="<c:url value="/seeArticle"><c:param name="action" value="seearticle"/><c:param name="art_id" value="${artcolVO.art_id}"/></c:url>">
	                    					${artVO2.art_tlt}</a>
										</td>
                                    	</c:if>
               							</c:forEach>
               							<c:forEach var="artVO2" items="${listall}" >
                    					<c:if test="${artcolVO.art_id==artVO2.art_id}">
                                    	<td>
	                    						<c:forEach var="memVOgetacc" items="${listmem}" >
                    							<c:if test="${artVO2.mem_id==memVOgetacc.mem_id}">
	                    						${memVOgetacc.acc}
                    							</c:if>
               							 		</c:forEach>
                                    	</td>
                                    	</c:if>
               							</c:forEach>
               							<c:forEach var="artVO2" items="${listall}" >
                    					<c:if test="${artcolVO.art_id==artVO2.art_id}">
                                    	<td>
	                    					${artVO2.like_num}
                                    	</td>
                                    	</c:if>
               							</c:forEach>
                                    </tr>
                                    	</c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                           
                        </div>
                    </div>
                </div>
            <!-- </div> -->
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