<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.ProductVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String it_id = request.getParameter("it_id");

	ProductService service = new ProductService();
	ProductVO product = service.findProduct(it_id);
	String it_name = product.getIt_name();

	String it_intro = product.getIt_intro();

	Integer it_num = product.getIt_num();

	Double it_prc = product.getIt_prc();



	String it_sts = product.getIt_sts();

	String it_cate = product.getIt_cate();

	request.setAttribute("it_cate", it_cate);
	request.getAttribute("it_cate");

	request.setAttribute("it_sts", it_sts);
	request.getAttribute("it_sts");

	String whichPage = request.getParameter("whichPage");
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

	function ValidateNumber(e, pnumber) {
		if (!/^\d+$/.test(pnumber)) {
			$(e).val(/^\d+/.exec($(e).val()));
		}
		return false;
	}

	function reg(form) {
		number = /^[0-9]+$/;
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
		
		if (form.it_cate.value == "") {
			alertify.alert("商品分類必須選擇！");
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
						<li><a
							href="${pageContext.request.contextPath }/memberIndex_commision.html">會員專區</a></li>
						<li><a href="">商品管理</a></li>
						<li>修改商品</li>
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
						<li role="presentation" class="active"><a href="#tab2"
							aria-controls="tab2" role="tab" data-toggle="tab">修改商品</a></li>
					</ul>




					<div class="col-xs-12 col-sm-4">
						<div class="row">
							<div role="tabpanel" class="tab-pane" id="tab2">
								<Form
									action="${pageContext.request.contextPath }/EditProductServlet"
									method="post" enctype="multipart/form-data"
									onsubmit="return reg(this);">
									<input type="hidden" name="action" value="EditProduct">
									<input type="hidden" name="it_id" value="<%=it_id%>"> <input
										type="hidden" name="whichPage" value="<%=whichPage%>">



									<div class="textLeft PB PT30">
										商品名稱:<input class="form-control" type="text" name="it_name"
											value=<%=it_name%>>
									</div>

									<div class="textLeft PB">
										商品描述:
										<textarea class="form-control" rows="5" name="it_intro"><%=it_intro%></textarea>
									</div>

									<div class="textLeft PB">
										商品價格:<input class="form-control" type="text" name="it_prc"
											value=<fmt:formatNumber value="<%=it_prc%>" 
           pattern="#" type="number"/>
											style="ime-mode: disabled"
											onkeyup="return ValidateNumber($(this),value)">
									</div>

									<div class="textLeft PB">
										商品數量:<input class="form-control" type="text" name="it_num"
											value=<%=it_num%> style="ime-mode: disabled"
											onkeyup="return ValidateNumber($(this),value)">
									</div>

								


									商品分類:<select class="form-control" name="it_cate">
										<option value="">請選擇</option>
										<option value="snack" ${(it_cate=="snack")?'selected':''}>零食點心區</option>
										<option value="sport" ${(it_cate=="sport")?'selected':''}>休閒運動區</option>
										<option value="tripleC" ${(it_cate=="tripleC")?'selected':''}>3C周邊區</option>
										<option value="lifeLiving"
											${(it_cate=="lifeLiving")?'selected':''}>居家生活區</option>
										<option value="workOut" ${(it_cate=="workOut")?'selected':''}>運動健身區</option>
										<option value="Game" ${(it_cate=="Game")?'selected':''}>電玩遊戲區</option>
										<option value="furniture"
											${(it_cate=="furniture")?'selected':''}>傢俱寢飾區</option>
										<option value="Women_Clothing"
											${(it_cate=="Women_Clothing")?'selected':''}>女性服飾區</option>
										<option value="toyForJoy"
											${(it_cate=="toyForJoy")?'selected':''}>玩具童裝區</option>
									</select>
							</div>


							<div class="textLeft PB">
								商品照片:<input type="file" name="it_pic" onchange="readURL(this)"
									targetID="preview"> <img id="preview"
									src="${pageContext.request.contextPath}/ProductImageReader?it_id=<%=it_id%>" />

							</div>

							<div class="btn-group btn-group-sm">
								<input type="submit" value="確定修改商品" class="btn btn-info">
							</div>
							</form>



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