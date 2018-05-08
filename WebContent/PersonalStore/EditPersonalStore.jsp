<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.ProductVO"%>

<%@page import="com.PersonalStore.model.*"%>
<%@page import="java.util.Map"%>
<%@page import="com.PersonalStore.model.*"%>
<%
MemVO memVO2 = (MemVO) session.getAttribute("MemVO");
request.setAttribute("memVO2", memVO2);
String mem_id2=memVO2.getMem_id();

PersonalStoreVO MempersonalStore=new PersonalStoreVO();
PersonalStoreService  personalStoreService=new PersonalStoreService();
MempersonalStore=personalStoreService.findByMemId(mem_id2);
request.setAttribute("MempersonalStore", MempersonalStore);


	String st_id=MempersonalStore.getSt_id();
	PersonalStoreVO  personalStoreEdit=new PersonalStoreVO();
	
	personalStoreEdit=personalStoreService.findByPrimaryKey(st_id);

	request.setAttribute("personalStoreEdit", personalStoreEdit);
	
	String activeEditStore = "activeEditStore";
	request.setAttribute("activeEditStore", activeEditStore);
%>

<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Gogo shop</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/Sean.css">
<link rel="icon" href="${pageContext.request.contextPath }/img/gogo.png">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css"
	rel="stylesheet">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css"
	rel="stylesheet">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>
<!--   自己寫的css   -->
<!-- <link rel="stylesheet" href="css/OOXX.css"> -->
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<script type="text/javascript">
	function reg(form) {
		if (form.st_name.value == "") {
			alertify.alert("商店名稱不能為空！");
			return false;
		}
		if (form.st_intro.value == "") {
			alertify.alert("描述不能為空！！");
			return false;
		}
	}
</script>

</head>

<body>
	 <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
	<section>

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-5 ">
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath }/index.html">首頁</a></li>
						
						<li>新增商家</li>
					</ol>
				</div>
				<div class="col-xs-12 col-sm-5"></div>
			</div>
		</div>

		<div class="container">
			<%@include file="PersonalStoreLeftBar.jsp"%>


			<div class="col-xs-12 col-sm-9">
				<div role="tabpanel">
					<!-- 標籤面板：標籤區 -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#tab1"
							aria-controls="tab1" role="tab" data-toggle="tab">新增商家</a></li>
					</ul>



					<!-- 標籤面板：內容區 -->
					<div class="col-xs-12 col-sm-4">
						<div class="row">
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="tab1">
									<table>
										<thead>
											<div role="tabpanel" class="tab-pane" id="tab1">
												<Form
													action="${pageContext.request.contextPath }/PersonalStoreController"
													method="post" onsubmit="return reg(this);">
													<input type="hidden" name="action" value="EditPersonalStore">

													<div class="textLeft PB PT30">
														商店名稱:<input class="form-control" type="text"
															name="st_name" value="${personalStoreEdit.st_name}">
													</div>

													<div class="textLeft PB">
														商店描述:
														<textarea class="form-control" rows="5" name="st_intro">${personalStoreEdit.st_intro}</textarea>
													</div>

													<div class="btn-group btn-group-sm">
														<input type="submit" value="確定" class="btn btn-info">
													</div>
												</form>
											</div>
										</thead>
									</table>
								</div>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>
	<%@ include file="/Footer.jsp"%>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>