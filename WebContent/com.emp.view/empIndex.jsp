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
    
    .form-group input[type="checkbox"] {
    display: none;
}

.form-group input[type="checkbox"] + .btn-group > label span {
    width: 20px;

}

.form-group input[type="checkbox"] + .btn-group > label span:first-child {
    display: none;
}
.form-group input[type="checkbox"] + .btn-group > label span:last-child {
    display: inline-block;   
}

.form-group input[type="checkbox"]:checked + .btn-group > label span:first-child {
    display: inline-block;
}
.form-group input[type="checkbox"]:checked + .btn-group > label span:last-child {
    display: none;   
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


    
    </style>
</head>

<body>
 <div class="container">


	<div class="col-xs-12 col-sm-2"> 
	<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>
	</div>
	    <!-- =-------------------------------------------------------------------------------- -->
	<div class="col-xs-12 col-sm-10 mainBody">     
    <!-- 員工列表 -->
      <c:if test="${selectlist ne null}">
      
      <jsp:include page="/com.emp.view/EmpViewList.jsp" flush="true"/>
      </c:if>
     <!-- 登入提醒 -->
      <c:if test="${selectlist eq null}">
      		<c:if test="${Message ne null}">
       			<script>
      			alert("${Message}");
      			</script>
      		</c:if>
      </c:if>
        <!-- 權限列表列表 -->
      <c:if test="${type eq 'AuthEdit'}">
      <jsp:include page="/com.emp.view/EmpEditpage.jsp" flush="true"/>
      </c:if>
      <!-- 修改完成跳轉 -->
       <c:if test="${type eq 'Editfinish'}">
      <jsp:include page="/com.emp.view/EditFinish.jsp" flush="true"/>
      </c:if>
      
      <!-- 創建會員 -->
      <c:if test="${type eq 'EstablishNewMember'}">
      <jsp:include page="/com.emp.view/EstablishEmp.jsp" flush="true"/>
      </c:if>
      </div>
   </div>
      <!---->
      
    <!---------------------------------------------------------------------------------- -->
     

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
</body>

</html>