<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>GoGoShop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/BackBase.css">
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.4.0/animate.min.css">
    <style>
    .loginbutton {
        position: fixed;
        right: 40px;
        top: 25px;
        background-color: #39d0cb;
        color: #000;
    }

    .loginbutton:hover {
        background-color: #33FFFF;
        color: #000;
    }

    .maincontent {
        margin-top: 83px;
        margin-left: 200px;
        
    }

    #mainpic {
        width: 300px;
        height: 200px;
    }
    .custom-title{
    	padding-left: 210px;
    }
    .custom-footer{
    	padding-right: 250px;
    }
    
.btn-edit{
   border:1px solid #D3D3D3;
   
   border-radius:15px;
}
td .btn-edit:hover{
background-color:#DCDCDC;
}

#editInputGroup{
    width:700px;
}
#editInputGroup img{
width:180px;
height:180px;
border-radius:50%;

}
#editInputGroup input{


}
.form-group2{

margin-bottom:15px;
}
#btn-groupedit{

    margin-bottom: 35px;
}
.editFinishDiv{

margin-bottom:100px;
}
.editcard{
border:1px solid #A9A9A9;
width:500px;
height:100px;
margin-top:20px;
border-radius:15px;
text-align:center;
text-size:20px;
padding-top:10px;
}
.editcard:hover{
box-shadow: 0 2px 5px rgba(0,0,0,0.3);
}

::-webkit-scrollbar {
    width: 17px;
}

/* Track */
::-webkit-scrollbar-track {
    background: #FFFFFF; 
    border-radius:30px;
}
 
/* Handle */
::-webkit-scrollbar-thumb {
     border-radius:30px;
background: linear-gradient(-180deg, #BCC5CE 0%, #929EAD 98%), radial-gradient(at top left, rgba(255,255,255,0.30) 0%, rgba(0,0,0,0.30) 100%);
 background-blend-mode: screen;
        /* Old browsers */
background: linear-gradient(-180deg, #BCC5CE 0%, #929EAD 98%), radial-gradient(at top left, rgba(255,255,255,0.30) 0%, rgba(0,0,0,0.30) 100%);
 background-blend-mode: screen;
        /* FF3.6-15 */
background: linear-gradient(-180deg, #BCC5CE 0%, #929EAD 98%), radial-gradient(at top left, rgba(255,255,255,0.30) 0%, rgba(0,0,0,0.30) 100%);
 background-blend-mode: screen;
        /* Chrome10-25,Safari5.1-6 */
background: linear-gradient(-180deg, #BCC5CE 0%, #929EAD 98%), radial-gradient(at top left, rgba(255,255,255,0.30) 0%, rgba(0,0,0,0.30) 100%);
 background-blend-mode: screen;
        /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
background: linear-gradient(-180deg, #BCC5CE 0%, #929EAD 98%), radial-gradient(at top left, rgba(255,255,255,0.30) 0%, rgba(0,0,0,0.30) 100%);
 background-blend-mode: screen;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
   background-image: linear-gradient(to left, #BDBBBE 0%, #9D9EA3 100%), radial-gradient(88% 271%, rgba(255, 255, 255, 0.25) 0%, rgba(254, 254, 254, 0.25) 1%, rgba(0, 0, 0, 0.25) 100%), radial-gradient(50% 100%, rgba(255, 255, 255, 0.30) 0%, rgba(0, 0, 0, 0.30) 100%);
   background-blend-mode: normal, lighten, soft-light; 
}
    
    </style>
</head>

<body>
    <div class="container">
        <div class="col-xs-12 col-sm-12">
            <div class="row">
                <nav class="navbar navbar-inverse navbar-fixed-top navtop">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#"><span class="container navlogo"><img src="<%=request.getContextPath() %>/img/gogo.png" alt="">GogoShop </span>
                        </a>
                        <!-- Button trigger modal -->
                        <c:if test="${EmpVO eq null}">
                        <button type="button" class="btn btn-primary loginbutton" data-toggle="modal" data-target="#exampleModal">
                            會員登錄               
                        </button>
                        </c:if>
                        
                    </div>
                </nav>
            </div>
        </div>
    </div>

        <div class="sidenav hoverable">
          
           <c:if test="${sessionScope.EmpVO ne null}">
                         <jsp:useBean id="EmpPmsSrv" class="com.emppms.model.EmpPmsService"/>
            <img src="<%=request.getContextPath()%>/EmpPic?emp_no=${EmpVO.emp_no}" 
            style="width: 140px; height:140px;border-radius: 50%;margin-left: 21px;margin-bottom: 10px;margin-top:15px;"><br>  
                      
            <span style="font-size: 16px; color: white;margin-left: 44px;margin-bottom: 25px;">${sessionScope.EmpVO.emp_nm}  </span><span style="color:white;">你好</span>
            <a href="<%=request.getContextPath() %>/EmpController?type=logout" style="margin-top:25px;"> 登出</a>
                       
            <c:forEach var="EmpPmsVOAll" items="${EmpPmsSrv.selectAll()}">
            <c:if test="${EmpPmsVOAll.empno eq sessionScope.EmpVO.emp_no}">
            
            <c:if test="${EmpPmsVOAll.pmsid eq 'PMS001'}">
             <a href="<%=request.getContextPath() %>/com.pmt.view/pmt_index.jsp">促銷活動管理</a>
            </c:if>            
           
            
            <c:if test="${EmpPmsVOAll.pmsid eq 'PMS003'}">
             <a data-toggle="collapse" href="#ProductOfficial" aria-expanded="false" aria-controls="#ProductOfficial"> 官方商城管理
				<div class="collapse" id="ProductOfficial">
					<a href="${pageContext.request.contextPath}/Back-End/AddProductBack.jsp">新增商品</a> 
					<a href="${pageContext.request.contextPath}/Back-End/ProductALLEditBackOfficial.jsp">所有商品</a>
					<a href="${pageContext.request.contextPath}/Back-End/ProductSts_On_EditBackOfficial.jsp">所有上架商品</a> 
					<a href="${pageContext.request.contextPath}/Back-End/ProductSts_Off_EditBackOfficial.jsp">所有下架商品</a>
					<a href="${pageContext.request.contextPath}/Back-End/OrderListBackForOfficial.jsp">官方商城訂單管理</a>			
					<a href="${pageContext.request.contextPath}/Back-End/OrderListBack.jsp">會員商城訂單管理</a>	
				</div>
			</a>	
            </c:if>
            
            <c:if test="${EmpPmsVOAll.pmsid eq 'PMS004'}">
            <a href="<%=request.getContextPath()%>/appeal/NewsManagement.jsp">最新消息管理</a>
            </c:if>
            
            <c:if test="${EmpPmsVOAll.pmsid eq 'PMS005'}">
                  <a data-toggle="collapse" href="#services"  aria-expanded="false" aria-controls="#services">檢舉管理
                  <div class="collapse" id="services">
                        <a href="<%=request.getContextPath()%>/appeal/CommisionAppeal.jsp">代購檢舉</a>            
                        <a href="<%=request.getContextPath()%>/appeal/ProductAppeal.jsp">商品檢舉</a>
                        <a href="<%=request.getContextPath()%>/appeal/ArticleAppeal.jsp">文章檢舉</a>
                        <a href="<%=request.getContextPath()%>/appeal/ArticleReplyAppeal.jsp">回復文章檢舉</a>
                  </div>
                 </a>  
            </c:if>
            
             <c:if test="${EmpPmsVOAll.pmsid eq 'PMS006'}">
              <a href="<%=request.getContextPath()%>/EmpController?type=AuthManage" id="authManage">權限管理</a>
            </c:if>
            
      
            </c:if>
            </c:forEach>
             </c:if>
            
        </div>

    <!-- =-------------------------------------------------------------------------------- -->
    

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h2 class="modal-title custom-title" id="exampleModalLabel">GoGoShop</h2>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                	<!--燈箱中間表單-->
                    <form class="form-horizontal" action="<%=request.getContextPath()%>/EmpController" method="post">
                        <div class="form-group">
                            <label class="control-label col-sm-2">帳號:</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control"  placeholder="Enter email" name="emp_id" id="emp_id">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="pwd">密碼:</label>
                            <div class="col-sm-7">
                                <input type="password" class="form-control" placeholder="Enter password" name="emp_psw"  id="emp_psw">
                                <input type="hidden" name="type" value="Login">
                            </div>
                        </div>
                      
                    
                    	<!--燈箱中間表單-->
                </div>
                <div class="modal-footer custom-footer">
                    <button type="submit" class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green" id="loginsubbnt">Login</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">clear</button>
                </div>
                </form>
            </div>
        </div>
    </div>
    <!--燈箱結尾-->
    		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
    var websocket=new WebSocket("ws://192.168.196.243:8081/GroupEditWS/M000001/COM001");
    

    document.getElementById("loginsubbnt").onclick=function(){
    	var emp_id=document.getElementById("emp_id");
    	var emp_psw=document.getElementById("emp_psw");
    	if(emp_id.value=="")
    	{
    		alert("帳號不得為空");
    		return false;
    	}
    	if(emp_psw.value=="")
    	{
    		alert("請輸入密碼");
    		return false;
    	}
    	
    	
    }
    </script>
       
  
</body>

</html>