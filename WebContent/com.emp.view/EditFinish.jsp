<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    
  <jsp:useBean id="EmpPmsClsSrv" class="com.emppmscls.model.EmpPmsClsService"/>  
    <div class="editFinishDiv">      
       <font style="font-size:40px;margin-left:170px;">"</font><font style="font-size:30px;">權限已更新</font> <span style="font-size:40px;">"</span>                                                      
      <c:forEach var="EmpPmsClsVO" varStatus="Count" items="${EmpPmsClsSrv.selectall()}">
      <c:forEach var="EmpPmsVO" items="${EmpPmslist}">
      <c:if test="${EmpPmsVO.pmsid eq EmpPmsClsVO.pmsid}">

       <div class="card w-75 editcard">
	            <div class="card-body">
	                 <h4 class="card-title" style="text-shadow: 0px 2px 1px #DDDDDD;">權限更新</h4>
	                 <p class="card-text">使用者: <span style="font-size:21px;">${EmpVO2.emp_nm}</span> 已新增權限:  <span style="font-size:21px;">${EmpPmsClsVO.pmsintro}</span></p>
	           </div>
	     </div>
      

      </c:if>
      </c:forEach>
      </c:forEach>
       <a href="<%=request.getContextPath()%>/EmpController?type=AuthManage" style="border:1px solid #778899;margin-top:50px;margin-left:210px;margin-bottom:50px;border-radius:15px;width:105px;height:70px;" >回員工列表</a>
   </div>
     
</body>
</html>