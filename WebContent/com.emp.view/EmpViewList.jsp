<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<div>
  <ol class="breadcrumb">
    <li><a href="#">後臺首頁</a></li>
    <li><a href="#">權限管理</a></li>
    <li class="active">員工列表</li>
 </ol>
</div >  

  <div style="margin-top:30px;">
  <span style="Font-size:45px;color:#888888;">員工列表</span>
  <a href="<%=request.getContextPath()%>/EmpController?type=EstablishNewMember" style="margin-left:50px;"><img src="<%=request.getContextPath()%>/img/gogo.png" style="width:45px;height:45px;">建立新使用者</a>
  </div>
    <table class="table table-condensed" style="margin-top:40px;margin-right:100px;width:800px;border: 1px solid #C0C0C0;border-radius:15px;" id="emptable">
    <thead>
     <tr>
        <th>編號</th>
        <th>員工姓名</th>
        <th>在職狀態:</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
   <c:forEach var="EmpVO" varStatus="Count" items="${selectlist}" begin="0" end="${selectlist.size()}">
      <tr>
        <td align="center" valign="center">${EmpVO.emp_no}</td>
        <td align="center" valign="center">${EmpVO.emp_nm}</td>
        <td align="center" valign="center">${EmpVO.emp_sts}</td>
        <td align="center" valign="center">

        <c:if test="${type eq 'AuthManage'}">
        <a href="<%=request.getContextPath()%>/EmpController?type=EditAuth&emp_no=${EmpVO.emp_no}" class="btn btn-edit">編輯權限</a>
        </c:if>
        </td>
       
      </tr>
   </c:forEach>
     
        </tbody>
  </table>
    
   
</body>
</html>