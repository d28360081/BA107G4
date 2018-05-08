<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- p189~ -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 去除秒 -->

<%@ page import="com.pmt.model.*"%>
<%@ page import="com.pmt_det.model.*" %>

<jsp:useBean id="pmtDetSvc" class="com.pmt_det.model.PmtDetService"/>
<jsp:useBean id="productSvc" class="com.Product.model.ProductService"/>
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
		margin-top: 83px;
		margin-left: 180px;
		padding: 50px;
		
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

.myTable {
	width: 1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 1px solid #CCCCFF;
	font-size: 16px;		
}

.myTable th,
.myTable td{
	font-weight: 100;
    padding: 5px;
    text-align: center;
    border: 1px solid #CCCCFF;
    text-align:center;
}
</style>

</head>
<body>
<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>


	<div class="maincontent">
	<!--     程式碼放這邊 -->
	<h4><input type="button" value="回首頁" onclick="location.href='<%=request.getContextPath()%>/com.pmt.view/pmt_index.jsp'"></h4>
	<table style="width:100%"  class="myTable">
		<tr>
			<td width="70px">促銷編號</td>
			<td width="70px">員工編號</td>
			<td width="150px">促銷名稱</td>
			<td width="250px">方案說明</td>
			<td width="100px">開始</td>
			<td width="100px">結束</td>
			<td width="70px">狀態</td>
			<td width="75px">促銷折扣</td>
			<th width="200px" >方案圖片</th>		
			<th width="150px">商品</th>	
		</tr>
		<c:forEach var="pmtVO" items="${pmtList}">
		<tr>
			<td>${pmtVO.pmt_id}</td>
			<td>${pmtVO.emp_no}</td>
			<td>${pmtVO.pmt_name}</td>
			<td>${pmtVO.pmt_intro}</td>
			<td><fmt:formatDate value="${pmtVO.pmt_s_date}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td><fmt:formatDate value="${pmtVO.pmt_e_date}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td>${pmtVO.pmt_sts}</td>
			<td>${pmtVO.pmt_discount}</td>		
			<!--取圖片顯示 ${pmtVO.pmt_pic}-->
			<th><img src="<%=request.getContextPath()%>/DBGifReader?table=promotion&pmt_id=${pmtVO.pmt_id}"  width="90%" height="100px"></th>
			<th>
				<c:forEach var="pmtDetVO" items="${pmtDetSvc.getByPMT_ID(pmtVO.pmt_id)}">
					
					<img src="<%=request.getContextPath()%>/DBGifReader?table=product&it_id=${pmtDetVO.it_id}"	width="50px" height="50px">
				</c:forEach>
			</th>	
		</tr>
		</c:forEach>
	</table>
	
	

</div>

		<!--     //程式碼放這邊 -->

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
</body>
<style>
 .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
 .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script>
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