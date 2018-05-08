<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.Product.model.*"%>
<%@page import="com.Product.model.ProductVO"%>
<%@page import="com.PersonalStore.model.*"%>
<%@page import="java.util.Map"%>

<% 


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
<link href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css" rel="stylesheet">  
<link href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css" rel="stylesheet">  
<script src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>  
<!--   自己寫的css   -->
<!-- <link rel="stylesheet" href="css/OOXX.css"> -->
<!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var imageTagID = input.getAttribute("targetID");
			var ready = new FileReader();
			ready.onload = function(e) {
				var img = document.getElementById(imageTagID);
				img.setAttribute("src", e.target.result)//.result 讀入的資料內容。只有在讀取完成之後此屬性才有效，而資料的格式則取決於是由哪一個方法進行讀取。
			}
			ready.readAsDataURL(input.files[0]);//開始讀取指定的 Blob，讀取完成後屬性 result 將以 data: URL 格式（base64 編碼）的字串來表示讀入的資料內容。
		}
	}
	function ValidateNumber(e, pnumber)
	{
	    if (!/^\d+$/.test(pnumber))
	    {
	        $(e).val(/^\d+/.exec($(e).val()));
	    }
	    return false;
	}
	
	
	
	function reg(form) {				
		number=/^[0-9]+$/;
		if (form.it_name.value == "") {
			alertify.alert("商品名稱不能為空！");
			return false;
		}
		if (form.it_intro.value == "") {
			alertify.alert("商品描述不能為空！！");
			return false;
		}	
		if (!number.test(form.it_prc.value)) {
			alertify.alert("商品價格只能為數字！");
			return false;
		}
		if (form.it_prc.value == "") {
			alertify.alert("商品價格不能為空！");
			return false;
		}
		if (form.it_num.value == "") {
			alertify.alert("商品數量不能為空！");
			return false;
		}
		if (!number.test(form.it_num.value)) {
			alertify.alert("商品數量只能為數字！");
			return false;
		}
		
		if (form.it_pic.value == "") {
			alertify.alert("商品圖片不能為空！");
			return false;
		}
		if (form.it_cate.value == "") {
			alertify.alert("商品分類必須選擇！");
			return false;
		}
		

	}
</script>
<%
String activeAddProduct="activeAddProduct";
request.setAttribute("activeAddProduct", activeAddProduct);


MemVO memVO2 = (MemVO) session.getAttribute("MemVO");
request.setAttribute("memVO2", memVO2);
String mem_id2=memVO2.getMem_id();


PersonalStoreVO MempersonalStore=new PersonalStoreVO();
PersonalStoreService  personalStoreService=new PersonalStoreService();
MempersonalStore=personalStoreService.findByMemId(mem_id2);

request.setAttribute("MempersonalStore", MempersonalStore);
%>

</head>

<body>
	 <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>
	<section>

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-5 ">
					<ol class="breadcrumb">
						<li><a href="${pageContext.request.contextPath }/index.html">首頁</a></li>
						
							
						<li>新增商品</li>
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
							aria-controls="tab1" role="tab" data-toggle="tab">新增商品</a></li>
					</ul>



					<!-- 標籤面板：內容區 -->
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="tab1">
							<table>
								<tbody>
									<div role="tabpanel" class="tab-pane" id="tab1">
										<Form
											action="${pageContext.request.contextPath }/AddProductServlet"
											method="post" enctype="multipart/form-data"
											onsubmit="return reg(this);">

											<input type="hidden" name="action" value="AddProduct">
											<input type="hidden" name="st_id" value="${MempersonalStore.st_id}">
														

											<div class="textLeft PB PT30">
												商品名稱:<input  class="form-control" type="text" name="it_name">
												
											</div>

											<div class="textLeft PB">
												商品描述:
												<textarea class="form-control" rows="5" name="it_intro"></textarea>
											</div>

											<div class="textLeft PB">
												商品價格:<input class="form-control" type="text" name="it_prc" style="ime-mode:disabled" onkeyup="return ValidateNumber($(this),value)">												
											</div>

											<div class="textLeft PB">
												商品數量:<input class="form-control" type="text" name="it_num" style="ime-mode:disabled" onkeyup="return ValidateNumber($(this),value)">													
											</div>

											

											<div class="textLeft PB">
												商品分類:<select class="form-control" name="it_cate">
													<option value="">請選擇</option>
													<option value="snack">零食點心區</option>
													<option value="sport">休閒運動區</option>
													<option value="tripleC">3C周邊區</option>
													<option value="lifeLiving">居家生活區</option>
													<option value="workOut">運動健身區</option>
													<option value="Game">電玩遊戲區</option>
													<option value="furniture">傢俱寢飾區</option>
													<option value="Women_Clothing">女性服飾區</option>
													<option value="toyForJoy">玩具童裝區</option>
												</select>
											</div>


											<div class="textLeft PB">
												商品照片:<input type="file" name="it_pic"
													onchange="readURL(this)" targetID="preview"> <img
													id="preview"
											/>

											</div>

											<div class="btn-group btn-group-sm">
												<input type="submit" value="確定新增商品" class="btn btn-info">
											</div>
										</form>

									</div>
								</tbody>
							</table>
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