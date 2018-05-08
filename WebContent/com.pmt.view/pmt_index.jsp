<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- p189~ -->
<jsp:useBean id="pmtSvc" scope="page" class="com.pmt.model.PmtService" />	

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
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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

</style>
</head>
<body onload="connect();" onunload="disconnect();">

<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>

	<div class="maincontent">
		<!--     程式碼放這邊 -->
		<%-- 錯誤表列 --%>
		<%-- 	<c:if test="${errorMsgs != null}"> --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	
  
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content" >      
			<!-- Modal Header -->
         	<div class="modal-header">
         		 <h4 class="modal-title">活動更新中...</h4>         
        	</div>       
       		 <!-- Modal footer -->
        	<div class="modal-footer">
          		<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        	</div>
        
      </div>
    </div>
  </div>
		
		<br><h4 >促銷頁面</h4>
			<!-- 動態請求↓ request.getContextPath()%>/pmt/listAllEmp.jsp  -->
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pmt/pmt.do">				
					<h4>輸入關鍵字:</h4>
					<h4><input type="text" name="keyword">
					<input type="submit" value="送出"></h4>
					<input type="hidden" name="action" value="getOne_For_Display">
		</FORM>
				
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pmt/pmt.do">
			<h4>選擇促銷名稱:</h4> 
			<h4><select size="1" name="keyword">
				<c:forEach var="pmtVO" items="${pmtSvc.all}">
					<option value="${pmtVO.pmt_id}">${pmtVO.pmt_name}
				</c:forEach>
			</select>
			
			<input type="submit" value="送出">  </h4>
			<input type="hidden" name="action" value="getOne_For_Display">
		</FORM>		
		
		
		
		<h4><input type="button" value="新增" onclick="location.href='<%=request.getContextPath()%>/com.pmt.view/addPmt.jsp'"></h4>
  		<jsp:include page="/com.pmt.view/listAllPmt.jsp"/> 
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


 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
// webSocket用
    var MyPoint = "/PmtEchoServer/back";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件 基本四個方法 建立 連線 回傳 結束
		webSocket = new WebSocket(endPointURL);//建立
		
		webSocket.onopen = function(event) {// 	連線呼叫server的open的方法 event=事件	
			console.log("WebSocket 成功連線");
		};

		webSocket.onmessage = function(event) { //回傳訊息
			window.location.href = "${pageContext.request.requestURL}";
		};

		webSocket.onclose = function(event) {//結束
			console.log("WebSocket 已離線");
		};
	}
	
	function disconnect() {
		webSocket.close();
	}
</script>
</body>
</html>