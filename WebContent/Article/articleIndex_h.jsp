<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.article.model.*"%>
<!DOCTYPE html>
<% 
	MemVO memVO = (MemVO) session.getAttribute("MemVO");
	
	
	ArticleHibernateService artSrc = new ArticleHibernateService();
	
	List<ArticleVO_Hibernate> list = (List<ArticleVO_Hibernate>)request.getAttribute("list");
	if(list==null || list.size()==0){
		list = artSrc.getAll();
	}
	pageContext.setAttribute("list", list);
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
            .pic{
            	width:200px;
            	height:275px;
				overflow: hidden;
            }
            .pic img{
            	width:100%;
            	height:100%;
            }
            #page{
            	margin-left:	900px;
            }
			#search{
				margin-left:	435px;
			}
        </style>
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
    <form class="navbar-form navbar-left" action="<%=request.getContextPath()%>/searchByTlt" method="post" role="search">
                    	<div class="form-group">
                        	<input type="text" class="form-control" name="value" placeholder="輸入關鍵字">
                    	</div>
               			<button type="submit" class="btn btn-default btn-go ">搜尋</button>
            		</form>
  </tbody>
</table>
</div> -->
       <div class="container">
           <div class="row">
               <div class="col-xs-12 col-sm-3 bread">
                   <a href="">首頁/  </a>文章專區
                    
               </div>
               <div class="col-xs-12 col-sm-6">
               </div>
               <div class="col-xs-12 col-sm-3"></div>
           </div>
       </div>
       <div class="container">
           <div class="row">
           		<div class="col-xs-12 col-sm-12">
				<form class="navbar-form navbar-left" action="<%=request.getContextPath()%>/searchByTlt" method="post" role="search" id="search">
            		<div class="form-group">
                 		<input type="text" class="form-control" name="value" placeholder="輸入關鍵字">
            		</div>
            		<button type="submit" class="btn btn-default btn-go ">搜尋</button>
        		</form>
        		</div>
        	</div>
        </div>


       	<div id="page">
		<%@ include file="pages/page1.file" %>
		</div>
		
		<br>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <div class="container">
                        <div class="row">

                            <div class="col-xs-12 col-sm-3">
                               <div class="btn-group">
                               	   	
                                   	<a href="<%=request.getContextPath() %>/Article/article_edit.jsp" class="btn btn-info edit" role="button" id="editart">
                                   	
                                   	發表文章
                                   	</a>
                               </div> 
                            </div>
                            <c:if test="<%=memVO==null%>">
                            <script>
                            	var editart = document.getElementById("editart");
                            	editart.onclick=function(){
                            		window.alert('請先登入會員~');
                            	}
                            	editart.href="#";
                            </script>
                            </c:if>
                            
                            
                            <c:if test="<%=memVO!=null %>">
                            <c:if test="<%=memVO.getPo_auth()==0%>">
                            <script>
                            	var editart = document.getElementById("editart");
                            	editart.href="#"
                            	window.alert("您的權限不足，不能發文喔><");
                            </script>
                            </c:if>
                            </c:if>
                            <div class="col-xs-12 col-sm-9" id="pages">
    
                            </div>
                        </div>
                    </div>
                    <br>
					
                    <c:forEach var="artVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                    <div class="col-xs-12 col-sm-4"><a href="<c:url value="/seeArticle"><c:param name="action" value="seearticle"/><c:param name="art_id" value="${artVO.art_id}"/></c:url>"><img src="<%=request.getContextPath()%>/Article/artpic?art_id=${artVO.art_id}" class="pic"></a>
                        <br><br>
                        <div class="lead"><a href="<c:url value="/seeArticle"><c:param name="action" value="seearticle"/><c:param name="art_id" value="${artVO.art_id}"/></c:url>">${artVO.art_tlt}</a></div>
                        <div class="well">
                        	發文帳號:${artVO.memVO.acc }
                        	
                            <br>日期:${artVO.art_date}</div>
                    </div>
                
				 	</c:forEach>
                </div>
                
                <%@ include file="pages/page2.file" %>
                <br>
                
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