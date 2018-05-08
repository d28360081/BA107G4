<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="java.util.*"%>
     <%@ page import="com.com.model.*" %>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>GogoShop</title>
<style>
   .noneNotifi{
   margin-top:80px;
   }
   .warning{
   background-color:#BBFFEE;
   }
   .info{
   background-color:#FFC8B4;
   }
   a:hover{
   background-color:#b3d9d9;
   } 


</style>

</head>

<body>
  <jsp:useBean class="com.member.model.MemService" id="memservice"/>
    <%if(request.getAttribute("joinList")!=null&&((List)request.getAttribute("joinList")).size()>0){ %>
      
            
                <table class="table table-hover listtable card">
                    <thead>
                        <tr>
                            <th scope="col" style="text-align:center;">編號</th>
                            <th scope="col" style="text-align:center;">案件名稱</th>
                            <th scope="col" style="text-align:center;">主辦人帳號</th>
                            <th scope="col" style="text-align:center;">開始時間</th>
                            <th scope="col" style="text-align:center;">結束時間</th>
                            <th scope="col" style="text-align:center;">案件狀態</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                   <%int joinListLenght=((List)request.getAttribute("joinList")).size();%>
                    <c:forEach var="ComVO" items="${joinList}" varStatus="counts" begin="0" end="<%=joinListLenght%>">
                                                 
                        <tr>
                            <th>${counts.count}</th>
                                 
                            <td>
                                 <c:if test="${type eq 'joinParty'}">
                                 <a href="<%=request.getContextPath()%>/Com_Controller?com_id=${ComVO.com_id}&type=checkdetail"> ${ComVO.com_tit}</a>
                                 </c:if>
                                 <c:if test="${type eq 'joinBuy'}">
                                  <c:if test="${!ComVO.com_sts eq '購買確認中' || !ComVO.com_sts eq '投票結束' ||!ComVO.com_sts eq '正在確認'||!ComVO.com_sts eq '開始購買'||ComVO.com_sts eq '準備進行投票'||ComVO.com_sts eq '招募代購人'}">
                                 <a href="<%=request.getContextPath() %>/Com_Controller?com_id=${ComVO.com_id}&type=checkDaiGodetail" class="leadd">${ComVO.com_tit}</a>
                                  </c:if>
                                  <c:if test="${ComVO.com_sts eq '購買確認中' || ComVO.com_sts eq '投票結束' ||ComVO.com_sts eq '正在確認'||ComVO.com_sts eq '開始購買'}">
                                    <a href="<%=request.getContextPath()%>/ComGroupEditController?type=GroupEdit&com_id=${ComVO.com_id}">${ComVO.com_tit}</a>
                                  </c:if>
                                 </c:if> 
                                 
                                
                            <br>
                            <c:if test="${requestScope.type == 'deleteItem'}">
                             <a href="<%=request.getContextPath()%>/Com_Controller?com_id=${ComVO.com_id}&type=checkDaiGodetail"> ${ComVO.com_tit}</a><br>
                             <c:if test="${ComVO.com_sts eq '招募參與'}"> 
                             <a href="<%=request.getContextPath()%>/Com_ComClc_Controller?type=deleteItem&com_id=${ComVO.com_id}" class="btn btn-go info" role="button">團購下架</a>
                            <%request.setAttribute("Nowtime",new Date().getTime()); %>
                            
                           <c:if test="${ComVO.com_e_dt.getTime()<Nowtime}">   
                            <a href="<%=request.getContextPath()%>/CombidController?com_id=${ComVO.com_id}&type=BiddingEdit" class="btn btn-go" role="button">編輯代購時間</a>
                           </c:if>
                           
                             <c:if test="${ComVO.com_e_dt.getTime()>Nowtime}">   
                             <a href="<%=request.getContextPath()%>/Com_ComClc_Controller?type=changeState&com_id=${ComVO.com_id}" class="btn btn-go warning" role="button">團購編輯</a>
                              </c:if>
                             
                             </c:if>
                              <c:if test="${ComVO.com_sts ne '招募參與'}">
                               <a href="<%=request.getContextPath()%>/CombidController?com_id=${ComVO.com_id}&type=BiddingEdit" class="btn btn-go warning" role="button">代購編輯</a>
                            </c:if>
                             </c:if>
                            
                            <c:if test="${type == 'removeCollection' }">
                             <a href="<%=request.getContextPath()%>/Com_Controller?com_id=${ComVO.com_id}&type=checkDaiGodetail"> ${ComVO.com_tit}</a><br>
                             <a href="<%=request.getContextPath()%>/Com_ComClc_Controller?type=removeCollection&com_id=${ComVO.com_id}" class="btn btn-go warning" role="button">移除收藏</a>
                            </c:if>
                            
                            <br>
                            </td>
                            <td>${memservice.getOneMem(ComVO.mem_id).acc}</td>
                            <td>${fn:substring(ComVO.com_s_dt, 0, 16)}</td>
                            <td>${fn:substring(ComVO.com_e_dt, 0, 16)}</td>
                            <td>${ComVO.com_sts}</td>
                        </tr>
                       
                        </c:forEach>
                    </tbody>
                </table>
           
       



<% }else{%>
<%//如果沒有回傳直顯示最新消息 %>
   <c:if test="${Message eq null}">
     <span style="font-size:60px;font-color:#B0E0E6;" class="noneNotifi">${MemVO.acc} 歡迎</span>
     </c:if>
     <c:if test="${Message ne null}">
   
     <img src="<%=request.getContextPath()%>/img/ComAh.png"><br>
       <span style="font-size:35px;font-color:#B0E0E6;" class="noneNotifi">${Message}</span>
     </c:if>
<%}; %>
</body>
</html>