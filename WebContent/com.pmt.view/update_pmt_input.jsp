<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- p189~ -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.pmt.model.*"%>
<%@ page import="com.pmt_det.model.*" %>
<c:set var="statusList" value="上架,下架"/>  <!--自訂標籤庫，抓之前的狀態資料 P193 var變數名稱 value存入變數的值-->
<%PmtVO pmtVO=(PmtVO) request.getAttribute("pmtVO");%><!-- request.getAttribute 將指定物件存入servletcontext -->
<%PmtDetVO pmtdetVO=(PmtDetVO) request.getAttribute("pmtdetVO"); %>
<jsp:useBean id="pmtDetSvc" class="com.pmt_det.model.PmtDetService"/><!-- p168 -->
<jsp:useBean id="ProductSrc" class="com.Product.model.ProductService" />
<jsp:useBean id="EmpSrv" class="com.emp.model.EmpService"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/BackBase.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.4.0/animate.min.css">
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
	margin-top: 100px;
	margin-left: 200px;
	padding: 20px;
}
#mainpic {
	width: 300px;
	height: 200px;	
}
.custom-title {
	padding-left: 210px;
}
.custom-footer {
	padding-right: 250px;
}
.pmt_stssize{
	width: 80%;
	height: 200px;
/* 	word-break: break-all;  */
	word-wrap:break-word;
	
}
</style>
</head>
<body>
<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>

	<div class="maincontent">
	<!--     程式碼放這邊 -->
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<!--FORM是要回傳的  -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pmt/pmt.do" 
	name="form1" enctype="multipart/form-data" style="border:3px solid black;">
<table>
	<tr>
		<td style="width:15%"><input type="button" style="font-size:20px" value="回上層" 
		onclick="location.href='<%=request.getContextPath()%>/com.pmt.view/pmt_index.jsp'">
			<input type="submit" style="font-size:20px" value="送出修改">	
		</td>	
		<td style="width:85%" ></td>	
	</tr>
	<tr>
		<td><h3>員工編號:</h3></td>
      	<td><h3>
      		<select size="1" name="emp_no">    
        		<c:forEach var="EmpVo" items="${EmpSrv.selectAll()}"> 
         			<option value="${EmpVo.emp_no}">${EmpVo.emp_no}
        		</c:forEach> 
        	</select></h3>
        </td>
	</tr>	
	<tr>
		<td><h3>促銷名稱:</h3></td>
		<td>
			<h3><input type="TEXT" name="pmt_name" size="45" value="<%=pmtVO.getPmt_name()%>">
		</h3></td>
	</tr>
	<tr>
		<td><h3>原促銷圖片：</h3></td><!--取促銷圖片顯示 -->
		<td>
			<img src="<%=request.getContextPath()%>/DBGifReader?table=promotion&pmt_id=${pmtVO.pmt_id}"	width="80%" height="200px">
		</td>
	</tr>
	<tr>
		<td><h3>新促銷圖片：</h3></td><!--取圖片顯示 -->
		<td>
			<h3><input type="file" class="pmt_addPic" name="upfile" id="upfile" value="<%=(pmtVO == null) ? "" : pmtVO.getPmt_pic()%>"/></h3>	
			<img src="" alt="" width="80%" height="200px" style="min-width:80%; min-height:200px; border: 1px solid #BBB;"><br>
		</td>
	</tr>
	<tr >
		<td><h3>促銷說明:</h3></td>
		<td>
			<h3><TEXTAREA  name="pmt_intro"  rows="5" cols="70%" ><%=pmtVO.getPmt_intro() %></TEXTAREA></h3>
			
		</td>
	</tr>
	<tr>
		<td><h3>開始日期:</h3></td>
		<td>
			<h3><input type="text" name="pmt_s_date" id="pmt_sdate" 
				value="<fmt:formatDate value="<%=pmtVO.getPmt_s_date()%>" pattern="yyyy-MM-dd HH:mm"/>">
		</h3></td>
	</tr>	
	<tr>
		<td><h3>結束日期:</h3></td>
		<td>
			<h3><input type="text" name="pmt_e_date" id="pmt_edate" 
				value="<fmt:formatDate value="<%=pmtVO.getPmt_e_date()%>" pattern="yyyy-MM-dd HH:mm"/>">
		</h3></td>
	</tr>
	<tr>
		<td><h3>狀態:</h3></td>
		<td><h3><%-- jstl語法 <c:set var="statusList" value="上架,下架"/> --%>
			<c:forTokens var="status" items="${statusList}" delims=",">
				<c:choose>
					<c:when test="${status == pmtVO.pmt_sts}">
						<input  type="radio" name="pmt_sts" value="${status}" checked>${status}
					</c:when>
					<c:otherwise>
						<input  type="radio" name="pmt_sts" value="${status}">${status}
					</c:otherwise>
				</c:choose>
			</c:forTokens>		
		</h3></td>		
	</tr>
	<tr>
		<td><h3>折扣：如0.8</h3></td>
		<td><h3>
			<input type="Number" name="pmt_discount" id="pmt_discount" size="30" min="0" step="0.01" value="<%=pmtVO.getPmt_discount()%>">
		</h3></td>
		
	</tr>
</table>
<jsp:include page="/com.pmt.view/update_pmtdet.jsp"/><!-- 位置要放在你要放的位置 -->

<input type="hidden" name="action" value="update">
<input type="hidden" name="pmt_id" value="<%=pmtVO.getPmt_id()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
</FORM>	
<!-- include的東西 要放在你要include的位置上 -->
<jsp:include page="/com.pmt.view/update_pmtdet_IT_ID.jsp"/>

		<!--     //程式碼放這邊 -->
</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title custom-title" id="exampleModalLabel">GoGoShop</h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!--燈箱中間表單-->
					<form class="form-horizontal"
						action="<%=request.getContextPath()%>" method="post">
						<div class="form-group">
							<label class="control-label col-sm-2" for="email">帳號:</label>
							<div class="col-sm-7">
								<input type="email" class="form-control" id="email"
									placeholder="Enter email" name="emp_id">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">密碼:</label>
							<div class="col-sm-7">
								<input type="password" class="form-control" id="pwd"
									placeholder="Enter password" name="emp_psw">
							</div>
						</div>

					</form>
					<!--燈箱中間表單-->
				</div>
				<div class="modal-footer custom-footer">
					<button type="button"
						class="btn btn-lg  btn-primary btn-gogo hvr-sweep-to-right btn-help green">Login</button>
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">clear</button>
				</div>
			</div>
		</div>
	</div>
	<!--燈箱結尾-->
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/com.pmt.view/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/com.pmt.view/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/com.pmt.view/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script>
$.datetimepicker.setLocale('zh');
$(function(){
	 $('#pmt_sdate').datetimepicker({
	  format:'Y-m-d H:i',
	  onShow:function(){
	   this.setOptions({
		minDate:new Date(),  
	    maxDate:$('#pmt_edate').val()?$('#pmt_edate').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
	 
	 $('#pmt_edate').datetimepicker({
	  format:'Y-m-d H:i',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#pmt_sdate').val()?$('#pmt_sdate').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
});	

$(".pmt_addPic").change(function() {
	var inputPos = this;
	if (this.files && this.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$(inputPos).next().attr('src', e.target.result);
		}
		reader.readAsDataURL(this.files[0]);
	}
});
	
	$(".pmt_addPic").change(function() {
		var inputPos = this;
		if (this.files && this.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$(inputPos).next().attr('src', e.target.result);
			}

			reader.readAsDataURL(this.files[0]);
		}
	});
	
	$(".source_it_id").draggable({
	    helper: "this",
	    connectToSortable: ".sortgroup",
	    opacity:1
	});

	$(".sortgroup").sortable({
	    receive: function (e, ui) { 
	    	isOut = false;
	    },
	    over: function (e, ui) { 
	    	isOut = false;
	    },
	    out: function (e, ui) { isOut = true;},
	    beforeStop: function (e, ui) {
	        if (isOut) {
	        	$(ui.item).remove();
	        }
	    }
	}).disableSelection();
</script>

</html>