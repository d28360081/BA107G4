<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.articlecommand.model.*"%>
<%@ page import="com.articlereply.model.*"%>
<%@ page import="com.articlecollection.model.*"%>

<!DOCTYPE html>
<% 
	MemVO memVO = (MemVO) session.getAttribute("MemVO");
	MemService memSrc = new MemService();
	memVO = memSrc.getByAcc(memVO.getAcc());
	List<MemVO> listmem = memSrc.getAll();
	
	ArticleService artSrc = new ArticleService();
	
	ArtcomVO artcomVO =(ArtcomVO)request.getAttribute("artcomVO");
	
	ArticleVO artVO = (ArticleVO)request.getAttribute("artVO");

	ArticleVO artVO2 = artSrc.findByArt_id(artVO.getArt_id());
	
	ArtreVO artreVO = (ArtreVO) request.getAttribute("artreVO");
	
	List<ArtcolVO> artcollist = artSrc.findColByMem_id(memVO.getMem_id());
	List<ArtcomVO> artcomlist = artSrc.findAllByArt_id(artVO.getArt_id());
	List<ArtreVO>  artrelist  = artSrc.getAllByArt(artVO.getArt_id());
	pageContext.setAttribute("listmem", listmem);
	pageContext.setAttribute("artcomlist", artcomlist);
	pageContext.setAttribute("artcollist", artcollist);
	pageContext.setAttribute("artrelist", artrelist);
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <link rel="icon" href="img/gogo.png">
    <script src="https://cdn.ckeditor.com/4.9.1/standard/ckeditor.js"></script>
    <style type="text/css">
        .media-object{
            margin-top: 95px;
            margin-left: 50px;
        }
        .media-heading{
            margin-top: 130px;
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
        	width: 100px;
        	height: 100px;
        	border-radius: 50%;
			overflow: hidden;
        	
        }
        .pic	img{
        	width:100%;
            height:100%;
        }
        #body{
           margin-top: 110px;
        }
        #appeal{
           margin-right: 45px;
        }
        img{
           max-width:	700px;
        }
        #page{
           margin-left:	900px;
        }
    </style>
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
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
       
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-3">
                <a href="index.jsp">首頁/  </a><a href="<%=request.getContextPath()%>/Article/articleIndex_h.jsp">文章專區/  </a><%=artVO2.getArt_tlt()%>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-3">
                        
                          
                      
                        <div class="media" id="left">
                          <div class="media-left">
                            <a href="#">
                            	<c:set var="mem_id"><%=artVO2.getMem_id()%></c:set>
								<c:forEach var="memVOgetacc" items="${listmem}" >
                    				<c:if test="${mem_id==memVOgetacc.mem_id}">		
	                    		<img class="media-object pic" src="<%=request.getContextPath()%>/member/MemPhoto?acc=${memVOgetacc.acc}">
                              		</c:if>
               					</c:forEach>
                            </a>
                          </div>
                          <div class="media-body">
                            <h5 class="media-heading">
                                文章作者:			<c:set var="mem_id"><%=artVO2.getMem_id()%></c:set>
								<c:forEach var="memVOgetacc" items="${listmem}" >
                    				<c:if test="${mem_id==memVOgetacc.mem_id}">
                    					${memVOgetacc.acc}		
                                	</c:if>
               					</c:forEach>
               				<br>
               					<%-- <a href="#chatarea" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="chatarea" onclick="connect();">
               						<img src="https://cdn2.iconfinder.com/data/icons/flat-icons-web/40/Chat_Alt-32.png">
               						<input type="hidden" id="mem_id_get" value="<%=artVO2.getMem_id()%>">
               						<input type="hidden" id="mem_id_send" value="<%=memVO.getMem_id()%>">
               					</a>	 --%>
                            </h5> 
                            <br>  
                          </div>
                        </div>
                    </div>
  
                        
                    
                    <div class="col-xs-12 col-sm-9">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h1 class="panel-title"><%=artVO2.getArt_tlt()%></h1>
                                <h6 align="right" >
             					<a id="star" href="<c:url value="/colArt"><c:param name="action" value="collection"/><c:param name="mem_id" value="<%=memVO.getMem_id() %>"/><c:param name="art_id" value="<%=artVO.getArt_id()%>"/></c:url>" 
                                title="收藏此文章~~" data-toggle="popover" data-trigger="hover" data-placement="bottom" >
                                <img id="colstar" src="https://cdn3.iconfinder.com/data/icons/silky-line-general-part1/48/collection-20.png"></a>
             					<c:set var="art_id"><%=artVO2.getArt_id() %></c:set>
                                <c:forEach var="getArtcol" items="${artcollist}">
                                <c:if test="${art_id==getArtcol.art_id}">
                                <script>
                                  document.getElementById("colstar").src="https://cdn4.iconfinder.com/data/icons/small-n-flat/24/star-20.png";
                                  document.getElementById("star").title="取消收藏此篇文章";
                                  document.getElementById("star").href="<c:url value="/deleteCol"><c:param name="action" value="deleteCol"/><c:param name="mem_id" value="<%=memVO.getMem_id()%>"/><c:param name="art_id" value="<%=artVO.getArt_id()%>"/></c:url>";
                                </script>
                                </c:if>
             					</c:forEach>                          
                                        發表日期:<%=artVO2.getArt_date()%>
                                </h6>       
                            </div>
                        </div>
                            <div class="panel panel-default">
                                  <div class="panel-body" >
                                    <%=artVO2.getArt_cnt()%>
                                  </div>
                            </div>
		        			<a href="#aaa" data-parent="#accordion2" data-toggle="collapse"  onclick="return changename()" id="seecom" role="button" aria-expanded="true" aria-controls="aaa">查看留言</a>
		        			<div id="aaa" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel1">
		     		 			<div class="panel-body">
		       						<c:forEach var="artcom" items="${artcomlist}">
		       						<table class="table table-hover">
		          					<tr>
		          						<th>
		          							<c:forEach var="memVOgetacc" items="${listmem}" >
                    							<c:if test="${artcom.mem_id==memVOgetacc.mem_id}">
                    								${memVOgetacc.acc}		
                                				</c:if>
               								</c:forEach>
		          						</th>
		          						<td>
		          							${artcom.art_msg_cnt}
		          						</td>
		          						<td>${artcom.art_msg_date}</td>
		          						<td>
		          						<a href="<c:url value="/ArtCompressLike"><c:param name="action" value="pressmsglike"/><c:param name="art_msg_id" value="${artcom.art_msg_id}"/><c:param name="art_msg_like_num" value="${artcom.art_msg_like_num}"/><c:param name="art_id" value="<%=artVO.getArt_id() %>"/><c:param name="mem_id" value="<%=memVO.getMem_id()%>"/></c:url>" 
		          						title="${artcom.art_msg_like_num}人已按讚" data-toggle="popover" data-trigger="hover" id="compresslike" data-content="按讚扣5點紅利" data-placement="bottom">
		          						<img src="https://cdn4.iconfinder.com/data/icons/evil-icons-user-interface/64/like-16.png">
		          						</a>
		          						</td>
		          					</tr>
		          			</table>
		       						</c:forEach>
		      					</div>
		    				</div>
		    				<br><br>
                        <div class="container">
                            <div class="row">
                                <div class="col-xs-12 col-sm-2">
                                </div>
                                <div class="col-xs-12 col-sm-10">
                                    <div class="btn-group">
                                        <a href="#modal-reply" data-toggle="modal" class="btn btn-go">回文</a>
                                        <a href='#modal-id' data-toggle="modal" class="btn btn-go">留言</a>
                                        <a href="<c:url value="/PressLikeArt"><c:param name="action" value="presslike"/><c:param name="art_id" value="<%=artVO2.getArt_id()%>"/><c:param name="like_num" value="<%=artVO2.getLike_num().toString() %>"/><c:param name="bonus" value="<%=memVO.getBonus().toString()%>"/><c:param name="mem_id" value="<%=memVO.getMem_id()%>"/><c:param name="mem_id_author" value="<%=artVO2.getMem_id()%>"/></c:url>" title="<%=artVO2.getLike_num() %>人已按讚" data-toggle="popover" data-trigger="hover" data-placement="bottom" 
                                        class="btn btn-go" role="button" id="presslike" data-content="按讚扣5點紅利">按讚</a><input type="hidden" id="like" value=0/>
                                        <a href="#modal-appeal" data-toggle="modal" class="btn btn-go">檢舉</a>
                                    </div>
                                    <c:if test="<%=memVO.getBonus()<=4 %>">
                                    	<script>
                                    		var pop = document.getElementById("presslike");
                                    		pop.href="#";
                                    		var data = pop.getAttribute("data-content");
                                    		var content = "您的紅利點數不足><";
                                    		pop.setAttribute("data-content",content);
                                    		var compop = document.getElementById("compresslike");
                                    		compop.href="#";
                                    		var comdata = compop.getAttribute("data-content");
                                    		var comcontent = "您的紅利點數不足><";
                                    		compop.setAttribute("data-content",comcontent);
                                    	</script>
                                    </c:if>
                                    <script type="text/javascript">
                                   		 $(document).ready(function(){
                                        	$('[data-toggle="popover"]').popover();   
                                   		 });
                                   		function changename(){
                                   			var font = document.getElementById("seecom");
                                   			if(font.innerHTML==='查看留言'){
                                   				font.innerHTML="收起留言";
                                   			}else{
                                   				font.innerHTML="查看留言";
                                   			}
                                   		}
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    
    <c:forEach var="artre" items="${artrelist}">
    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-sm-3">
                <div class="media">
                  <div class="media-left">
                    <a href="#">
                    	<c:forEach var="memVOgetacc" items="${listmem}" >
                    			<c:if test="${artre.mem_id==memVOgetacc.mem_id}">
                    				<img class="media-object pic" src="<%=request.getContextPath()%>/member/MemPhoto?acc=${memVOgetacc.acc}" alt="會員頭貼">		
                                </c:if>
               			</c:forEach>
                      
                    </a>
                  </div>
                  <div class="media-body">
                    <h5 class="media-heading">
                        回覆會員:		
						<c:forEach var="memVOgetacc" items="${listmem}" >
                    			<c:if test="${artre.mem_id==memVOgetacc.mem_id}">
                    				${memVOgetacc.acc}		
                                </c:if>
               			</c:forEach>
               		<br><%-- <a href="#chatarea" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="chatarea" onclick="connect();" >
               			<img src="https://cdn2.iconfinder.com/data/icons/flat-icons-web/40/Chat_Alt-32.png">
               			<input type="hidden" id="mem_id_get" value="${artre.mem_id}">
               			<input type="hidden" id="mem_id_send" value="<%=memVO.getMem_id()%>">
               			</a> --%>
                    </h5>   
                  </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-9">
                <div class="panel panel-default" id="body">
                    <div class="panel-body" >
                      ${artre.re_cnt}
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 col-sm-5"></div>
                            <div class="col-xs-12 col-sm-7">
                                <div class="btn-group" id="appeal">
                                    <a href="#${artre.re_id }" data-toggle="modal" class="btn btn-go">檢舉</a>
                                </div>
                                    <div class="modal fade" id="${artre.re_id}">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
													<h4 class="modal-title">檢舉此回文章</h4>
												</div>
												<div class="modal-body">
														<div class="form-group">
														  <label for="comment">請簡述檢舉原因</label>
														  <textarea class="form-control" rows="5" id="comment2" name="apl_cnt"></textarea>
														</div>
													
							                  			<br>
							                  			<button type="button" id='btnclose2' class="btn btn-default" data-dismiss="modal">取消</button>
							                  			<input  type='submit' class="btn btn-info" id="btnsubmit2" value="確認送出" data-dismiss="modal" data='${artre.re_id}'>
												</div>
											
											</div>
										</div>
									</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
    <br>
		
    <div class="modal fade" id="modal-id">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">發表留言</h4>
					</div>
					<div class="modal-body">
						<form action="CommandArt" method="post">
							<input type="text" name="art_msg_cnt" id="art_msg_cnt" value="">
						
						<script>
                    		CKEDITOR.replace("art_msg_cnt");
                    		
                    	
                    		function getValue(){
                    			var data = CKEDITOR.instances.art_msg_cnt.getData();
                    			document.getElementById("art_msg_cnt").value=data;
                    		}	
                    		
                  		</script>
                  			<br>
                  			<input type="hidden" name="action" value="insertCom">
                  			<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
                  			<input type="hidden" name="art_id" value="<%=artVO.getArt_id()%>">
                  			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                  			<input type="submit" class="btn btn-info" onclick="getValue()" value="確認送出">
                  		</form>	
					</div>
					<button onclick="comment()"></button>
					<script type="text/javascript">
						function comment(){
							CKEDITOR.instances.art_msg_cnt.setData('<p>好可愛喔喔喔喔d(`･∀･)b</p>');
						}
					</script>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modal-reply">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">回復文章</h4>
					</div>
					<div class="modal-body">
						<form action="ReplyArt" method="post">
							<input type="text" name="re_cnt" id="re_cnt" value="">
						
						<script>
                    		CKEDITOR.replace("re_cnt");
                    		
                    	
                    		
                    		function getreValue(){
                    			var data = CKEDITOR.instances.re_cnt.getData();
                    			document.getElementById("re_cnt").value=data;
                    		}	
                    		
                  		</script>
                  			<br>
                  			<input type="hidden" name="action" value="replyart">
                  			<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
                  			<input type="hidden" name="art_id" value="<%=artVO.getArt_id()%>">
                  			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                  			<input type="submit" class="btn btn-info" onclick="getreValue()" value="確認送出">
                  		</form>	
					</div>
					<button onclick="reply()"></button>
					<script type="text/javascript">
						function reply(){
							CKEDITOR.instances.re_cnt.setData('<p>好票釀喔 我也有一件ㄟ</p><p><img src="http://image.hellokiki.com.tw/MBC/ProductQuickImg/69/0/18JAN149/636519118855819230.jpg" /></p>');
						}
					</script>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modal-appeal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">檢舉此文章【<%=artVO.getArt_tlt()%>】</h4>
					</div>
					<div class="modal-body">
							<div class="form-group">
							  <label for="comment">請簡述檢舉原因</label>
							  <textarea class="form-control" rows="5" id="comment" name="apl_cnt"></textarea>
							</div>
						
                  			<br>
                  			<button type="button" id='btnclose' class="btn btn-default" data-dismiss="modal">取消</button>
                  			<input id='btnsubmit' type='submit' class="btn btn-info" value="確認送出" data-dismiss="modal">
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
    <script>
	$(document.body).delegate("#btnsubmit",'click',function()
		{	
		    
			var mem_id='<%=memVO.getMem_id()%>';
			var art_id='<%=artVO.getArt_id()%>';
			var comment = $("#comment").val();
			if(comment.trim().length == 0 || comment ==""){
				alert("回復檢舉內容不得為空白");
				return false;
			}
			
			var btnsubmitVO ={
				mem_id:mem_id,
				art_id:art_id,
				art_apl_cnt:comment,
				action: 'appeal_This_Article'
			}

		$.ajax({
			url : "${pageContext.request.contextPath}/articleappeal.do",
			cache: false,
			type : "POST",
			data : btnsubmitVO,
			success : function(data) {
			$("#btnclose").click(),
			$("#btnsubmit").prop("disabled",true);
			alert("發送檢舉成功")
			
			},
			error: function(xhr, ajaxOptions, thrownError) {
				console.log(xhr.status + "\n" + thrownError);
				console.log("error");
				$("#loading").hide();
				$("#btnsubmit").prop("disabled",false);
			}
		});
	});
	</script>
	<script>
	$(document.body).delegate("#btnsubmit2",'click',function()
		{	
		    
			var re_id =$(this).attr('data');
			var mem_id ='<%=memVO.getMem_id()%>';
			var comment2 = $("#comment2").val();
			if(comment2.trim().length == 0 || comment ==""){
				alert("回復檢舉內容不得為空白");
				return false;
			}
			
			var btnsubmit2VO ={
				mem_id:mem_id,
				re_id:re_id,
				art_re_apl_cnt:comment2,
				action: 'appeal_ReplyArticle'
			}

		$.ajax({
			url : "${pageContext.request.contextPath}/articlereplyappeal.do",
			cache: false,
			type : "POST",
			data : btnsubmit2VO,
			success : function(data) {
			$("#btnsubmit2").prop("disabled",true);
			alert("發送檢舉成功")
			
			},
			error: function(xhr, ajaxOptions, thrownError) {
				console.log(xhr.status + "\n" + thrownError);
				console.log("error");
				$("#btnsubmit2").prop("disabled",false);
			}
		});
	});
	</script>
</body>

</html>