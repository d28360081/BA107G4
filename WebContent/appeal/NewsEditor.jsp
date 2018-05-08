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
    <title>NewsEditor</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/Base2.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Bootstrap JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="https://cdn.ckeditor.com/4.9.0/standard/ckeditor.js"></script>
    
    
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
  .divtime{
  width:15%;
  }
  
</style>


</head>

<body>
	<div class="col-xs-12 col-sm-2"> 
	<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>
	</div>
	
	<link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/appeal/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/appeal/datetimepicker/jquery.datetimepicker.full.js"></script>
	
	
	
        <div class="col-xs-12 col-sm-10">
            <div class="main">
                 <ol class="breadcrumb">
				<li><a href="#">首頁</a></li>
				<li><a href="<%=request.getContextPath()%>/appeal/NewsManagement.jsp">最新消息管理</a>
				<li><a href="<%=request.getContextPath()%>/appeal/NewsEditor.jsp">新增最新消息</a></li>
			</ol>
			<form METHOD="post" ACTION="<%=request.getContextPath()%>/news.do" id="newseditor">
				<div class="row" align="left">
					<b>編輯最新消息標題</b>
				<!------------------------title value  -------------------------->	
					<div class="form-group">
					  <input type="text" class="form-control" name="ns_tit" id='title'>
					</div>
		        <!--************************end value**************************-->          
				</div>
				<div class="row">
					<b>編輯最新消息內文</b>
				<!------------------------content value  -------------------------->	
				<div class="form-group">
					<textarea name="ns_cnt" id="editor2" rows="10" cols="80" form="newseditor">
		                  </textarea>
				</div>     
		                  
		        <!--************************end value****************************-->          
					<br>

				</div>

				<div class="form-group row">
					
						<label>選擇發布時間</label>
						
				<!---------------------------time value  -------------------------->
							<input name="ns_cdate" id="f_date1" type="text" class="form-control divtime" size="100"> 
				<!--************************end value****************************-->	
						
				</div>
					<div  align='right'>
						<input type="submit" id="btn1" class="btn-danger" value="編輯完成" >
						<input type="hidden" name="action" value="newseditor">
						<input type="hidden" name="requestURL" value="/appeal/NewsEditor.jsp">
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
					
					<button><a href="https://money.udn.com/money/story/10871/3098561" target="_blank">hidden1</a></button>
					<button><a href="https://news.cnyes.com/news/id/4100723" target="_blank">hidden2</button>
					<button><a href="https://money.udn.com/money/story/5616/3098857" target="_blank">hidden3</button>
	</div>
					
	
	
			


</body>



<script>


CKEDITOR.replace('ns_cnt');
	

	
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

