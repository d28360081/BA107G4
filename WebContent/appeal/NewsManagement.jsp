<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.news.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="">

<%
NewsService nsSvc = new NewsService(); 
List<NewsVO> list = nsSvc.getAll();
pageContext.setAttribute("list",list);
%>



<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>NewsManagement</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Base2.css">
    <style>
      .table-title{
      font-size: 20px; 
      font-weight:bold; 
        }
        .table-content{
         font-size: 20px;
        }
        #clients{
          background-color: #bfbfbf;
        }
        .one_line{
        width: 300px;
        height: 30px;
        overflow: hidden;
        text-overflow: ellipsis;
         white-space: nowrap;
        }  
        .button {
	    background-color: #1DB3D3; 
	    color: white;
	    padding: 2px 40px;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 24px;
	    margin: 4px 2px;
	    cursor: pointer;
	    -webkit-transition-duration: 0.4s; /* Safari */
	    transition-duration: 0.4s;
	    border:3px orange double;
	    font-family: Microsoft JhengHei;
		}
		.button:hover {
		box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
		}
    </style>
   
   <script type="text/javascript">

   </script>
   <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
   <div class="col-xs-12 col-sm-2"> 
	<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>
	</div>
        <div class="col-xs-12 col-sm-10">
            <div class="main">
                 <ol class="breadcrumb">
                   <li>
                       <a href="#">首頁</a>
                   </li>
                   <li>
                       <a href="<%=request.getContextPath()%>/appeal/NewsManagement.jsp">最新消息管理</a>
               </ol>
                <table class="table table-hover table-striped table-bordered table-condensed ">
             
                <thead class="table-title">
                    <tr align="center" style='height:30pt'>
                        <th width="80"><div >編輯</div></th>
                        <th width="100"><div>編輯時間</div></th>
                        <th width="100"><div>消息編號</div></th>
                        <th width="100"><div>編輯員工</div></th>
                        <th width="100"><div>消息名稱</div></th>
                        <th width="140"><div>發布狀態</div></th>
                        <th ><div>編輯內容</div></th>
                    </tr>
                </thead>
                <tbody class="table-content">
                    <%@ include file="pages/page3.file" %>
                    
                   <c:forEach var="nsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                     <tr align="center" style='height:70pt'>
                     	
                        	<td>
                        		<form method="post" action="<%=request.getContextPath()%>/news.do">
	                        		<div>
		                        		<button type="submit" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">修改
			                                <input type="hidden" name="action" value="tonsupdate" >
			                                <input type="hidden" name="ns_id" value="${nsVO.ns_id}">
			                                <input type="hidden" name="requsetURL" value="/appeal/NewsManagement.jsp">
		                              	</button><br>
									</div>
								</form>
								<form method="post" action="<%=request.getContextPath()%>/news.do">
	                        		<div>
		                        		<button type="submit" class="btn btn-warning btn-lg btndelete" data-toggle="modal" data-target="#myModal"  data="${nsVO.ns_id}">刪除
			                                <input type="hidden" name="action" value="delete">
			                                <input type="hidden" name="ns_id" value="${nsVO.ns_id}">
			                                <input type="hidden" name="requsetURL" value="/appeal/NewsManagement.jsp">
		                              	</button><br>
									</div>
								</form>		
							</td>
                       
                            <td><div>"${nsVO.ns_date}"</div></td>
                            <td><div>"${nsVO.ns_id}"</div></td>
                            <td><div>"${nsVO.emp_no}"</div></td>
                            <td><div class="one_line">"${nsVO.ns_tit}"</div></td>
                            <td><div class="radio">
                                <label>
                                    <select class="form-control toption_id" data="${nsVO.ns_id}">
                                        <option value="等候發布" ${nsVO.ns_sts=='等候發布'?'selected':''}>等候發布</option>
                                        <option value="發布" ${nsVO.ns_sts=='發布'?'selected':''}>發布</option>
                                    </select>
                                </label>
                            </div></td>
                        <td><div class="one_line">"${nsVO.ns_cnt}"</div></td>
                    </tr>
                  </c:forEach>
                </tbody>
            </table>
        <%@ include file="pages/page4.file" %>   
        <div  align="right">
	        <a href="${pageContext.request.contextPath}/appeal/NewsEditor.jsp">
		        <button type="button" class="btn btn-danger btn-lg">
				  	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增消息
				</button>
	        </a> 
        </div>
         
        </div>
            </div>
            
        
    </body>
    
    
    
    <script>
    
    	$('.btndelete').click(function(){
    		 if(confirm("確認要刪除?")==true)   

    			    return true;

    			  else  

    			    return false;

    			});
	
    	$(document.body).delegate('.toption_id','change',function(){
    		var releaseOp = $(this).find("option:selected").val();
			var ns_id =$(this).attr("data");
			
    		var releaseVO ={
    			ns_id : ns_id,
    			seleased : releaseOp,
    			action : "released"
    		};
    		
    	$.ajax({
    		url:"${pageContext.request.contextPath}/news.do",
    		cache:false,
    		type: "POST",
    		data:releaseVO,
    		success:function(data){
    			console.log(data);
    			alert("修改成功");
    	
    		},
    		error : function(xhr, ajaxOptions, thrownError) {
				console.log(xhr.status + "\n" + thrownError);
				console.log("error");
			}
    	});	
    		
    		
    		
    	});
    </script>
    
    
    
    
 
</html>
