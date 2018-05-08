<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.news.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="">

<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>NewsUpdate</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Base2.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Bootstrap JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="https://cdn.ckeditor.com/4.9.0/standard/ckeditor.js"></script>
    <link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/appeal/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/appeal/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/appeal/datetimepicker/jquery.datetimepicker.full.js"></script>
    
<style>
.table-title {
	font-size: 16px;
	font-weight: bold;
}

.table-content {
	font-size: 14px;
}

#clients {
	background-color: #bfbfbf;
}

.one_line {
	width: 300px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}
 .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
  .row{
  		margin-top: 3%;
  }
</style>


</head>





<body>
	<div class="col-xs-12 col-sm-2"> 
	<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>
	</div>
        <div class="col-xs-12 col-sm-10">
            <div class="main">
                 <ol class="breadcrumb">
				<li><a href="#">首頁</a></li>
				<li><a href="<%=request.getContextPath()%>/appeal/NewsManagement.jsp">最新消息管理</a>
				<li><a href="<%=request.getContextPath()%>/appeal/NewsUpdate.jsp">修改消息</a></li>
			</ol>
			
<%
request.getAttribute("nsVO");
%>
			
			
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/news.do" id="newseditor">
				<div class="row" align="center">
					<b>修改消息標題</b>
					<p>消息編號 : ${nsVO.ns_id}"</p>
					
					<br>
				<!------------------------title value  -------------------------->	
					<div class="form-group">
					  <input type="text" class="form-control"  name="ns_tit" id="nstit" value="${nsVO.ns_tit}">
					</div>
		        <!--************************end value**************************-->          
				</div>
				<div class="row" align="center" >
					<b>修改消息內文</b>
				<!------------------------content value  -------------------------->	
				<div class="form-group">	
					<textarea name="ns_cnt" id="editor2"  class="form-control" rows="10" id="comment" form="newseditor">
		             ${nsVO.ns_cnt}</textarea>
		        </div>     
		        <!--************************end value****************************-->          
					<br>

				</div>
				
				<div align='left'>
					<label>
                        <select class="form-control toption_id" name="ns_sts">
                            <option value="等候發布" ${nsVO.ns_sts=='等候發布'?'selected':''}>等候發布</option>
                            <option value="發布" ${nsVO.ns_sts=='發布'?'selected':''}>發布</option>
                        </select>
                    </label>
				
				</div>
				<div class='col-sm-9' align='right'>
					<input type="submit" id="btn1" class="btn-primary" value="編輯完成">
					<input type="hidden" name="action" value="newseupdate">
					<input type="hidden" name="ns_id" value="${nsVO.ns_id}">
					<input type="hidden" name="requestURL" value="/appeal/NewsUpdate.jsp">
				</div>
			</form>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
		</div>
	</div>
	
			


</body>



<script>

CKEDITOR.replace('ns_cnt');

$(".collapsetabs").click(function(){
	console.log();

	var obj=$(this).find('.in');
	console.log(obj.hasClass('in'));
	if(obj.hasClass('in')){
		for(var i=0;i<obj.length;i++){
			console.log(obj[i].id);
			obj.toggle('hide');
			
		}
	}
});




$('.btn1').click(function(){
	 if(confirm("確認要修改?")==true)   

		    return true;

		  else  

		    return false;

		});


	
    $.datetimepicker.setLocale('zh'); // kr ko ja en
    $('#f_date1').datetimepicker({
       theme: '',          //theme: 'dark',
       timepicker: true,   //timepicker: false,
       step: 1,            //step: 60 (這是timepicker的預設間隔60分鐘)
       format: 'Y-m-d H:i:s',
       value: new Date(),
       //disabledDates:    ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
       //startDate:	        '2017/07/10',  // 起始日
       minDate:           '-1970-01-01', // 去除今日(不含)之前
       //maxDate:           '+1970-01-01'  // 去除今日(不含)之後
    });
</script>



</html>

