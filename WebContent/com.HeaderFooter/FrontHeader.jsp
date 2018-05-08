<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<%@page import="com.member.model.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%	

MemVO memVO10 = (MemVO) session.getAttribute("MemVO");

request.setAttribute("memVO10", memVO10);
%>
<style>
      /* width */
::-webkit-scrollbar {
    width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
    background: #FFFFFF; 
    border-radius:30px;
}
 
/* Handle */
::-webkit-scrollbar-thumb {
    background: #3ab2d9;
        /* Old browsers */
        background: -moz-linear-gradient(45deg, #3ab2d9 0%, #16e8b1 100%);
        /* FF3.6-15 */
        background: -webkit-linear-gradient(45deg, #3ab2d9 0%, #16e8b1 100%);
        /* Chrome10-25,Safari5.1-6 */
        background: linear-gradient(45deg, #3ab2d9 0%, #16e8b1 100%);
        /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3ab2d9', endColorstr='#16e8b1', GradientType=1);
         border-radius:30px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
    background: #555; 
}
</style>

<script>

function LoginCheck(href){
	<c:if test="${memVO10 eq null}">			
	    $('#modal-id').modal('show');
	    return false
	</c:if>	
	    
	<c:if test="${memVO10 ne null}">	
	    return true
	</c:if>	
	    
}
</script>
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
                <a class="navbar-brand" href="<%=request.getContextPath()%>/Indexnologin.jsp"><img src="<%=request.getContextPath()%>/res/logo.svg" alt=""></a>
            </div>
            <!-- 手機隱藏選單區 -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <!-- 左選單 -->
                <ul class="nav navbar-nav nav-custom">
                    <li><a href="<%=request.getContextPath()%>/ShoppingMall/ShoppingMallForOfficial.jsp" class="hvr-underline-from-left">官方商店</a></li>
                    <li><a href="<%=request.getContextPath()%>/Com_Controller?type=checklist&page=1" class="hvr-underline-from-left">代購專區</a></li>
                    <li><a href="<%=request.getContextPath()%>/com.pmt.view/pmtFont_Index.jsp" class="hvr-underline-from-left">活動專區</a></li>
                    <li><a href="<%=request.getContextPath()%>/Article/articleIndex_h.jsp" class="hvr-underline-from-left">文章專區</a></li>                   
                    <li><a href="<%=request.getContextPath()%>/ShoppingMall/FavoriteProductList.jsp" class="hvr-underline-from-left" onclick="return LoginCheck(this);">商品收藏專區</a></li>
                </ul>
               
              <!--  <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="輸入關鍵字">
                    </div>
                    <button type="submit" class="btn btn-default btn-go ">搜尋</button>
                </form>  搜尋表單 -->
                <!-- 會員下拉選單 -->
                <ul class="nav navbar-nav navbar-right">
                
                
                <!-- 會員登入 -->
                
                <!-- 已登入 -->
              
               <c:if test="${MemVO.mem_id ne null}">   
                  <!-- 會員下拉選單 -->
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    ${MemVO.mem_name}  您好
                    <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%=request.getContextPath()%>/member/memberIndex2.jsp" class="hvr-underline-from-left">會員專區</a></li>
                            <li><a href="<%=request.getContextPath()%>/OrderListFront/OrderList.jsp" class="hvr-underline-from-left">我的訂單</a></li>
                            
                            <li><a href="<%=request.getContextPath()%>/member/login?action=logout" class="hvr-underline-from-left">登出</a></li>
                        </ul>
                    </li>
                    <li><a href="<%=request.getContextPath()%>/member/login?action=logout">登出</a></li>
                </ul>
                 <!-- 會員下拉選單 -->
                </c:if>
                <!-- 已登入 -->
 
                <!-- 未登入 -->
                 <c:if test="${MemVO.mem_id eq null}">   
                    <li><a href='#modal-id' data-toggle="modal">登入</a></li>
                    
                    <div class="modal fade" id="modal-id">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <form action="<%=request.getContextPath()%>/member/login" method="post">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title">會員登入</h4>
                                    <div align="right">沒有帳號嗎?
                                    <a href="<%=request.getContextPath() %>/member/register2.jsp">註冊會員</a>
                                    </div>
                                </div>
                                <div class="modal-body">

                                    <div class="input-group">
                                      <label for="acc" class="col-xs-12 col-sm-3 control-label data">
                                          帳號
                                      </label>
                                      <div class="col-xs-12 col-sm-9 data">
                                          <input type="text" name="acc" id="acc" placeholder="" class="form-control" value="">
                                      </div>
                                      <label for="psw" class="col-xs-12 col-sm-3 control-label data">
                                          密碼
                                      </label>
                                      <div class="col-xs-12 col-sm-9 data">
                                          <input type="password" name="psw" id="psw"  class="form-control" value="">
                                      </div>
                                    </div>
                                    <div>
                                    <c:if test="${not empty errMsg}">
                                    		${errMsg}
                                    </c:if>
									</div>
								</div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-gogo" data-dismiss="modal">取消</button>
                                    <input type="hidden" name="action" value="login">
                                    <input type="submit" class="btn btn-info" value="登入">
                                </div>
                                </form>
                            </div>
                           <button onclick="no1()">1</button>
                           <button onclick="no2()">2</button>
                           <script>
                           		var acc= document.getElementById("acc");
                           		var psw= document.getElementById("psw");
                           		function no1(){
                           			acc.value="SAM5487";
                           			psw.value="SAM5487";
                           		}
                           		function no2(){
                           			acc.value="FGO089";
                           			psw.value="FGO089";
                           		}
                           </script>
                        </div>
                    </div>
                   </c:if>
                  <!-- 會員登入視窗 --> 
                    
                </ul>
            </div>
            <!-- 手機隱藏選單區結束 -->
        </div>
    </nav>
    <div class="gradient"></div>
   
</body>
</html>