<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.ProductVO"%>
<%@page import="com.bonusproduct.model.*"%>
<%@page import="java.util.*, java.util.Map.Entry"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>官方商城管理</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Base2.css">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.core.css"
	rel="stylesheet">
<link
	href="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.default.css"
	rel="stylesheet">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/alertify.js/0.3.10/alertify.min.js"></script>

<%
	ProductVO product = (ProductVO) request.getAttribute("product");
	BonusProductVO bnsVo = (BonusProductVO) request.getAttribute("bnsVo");
%>






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
		if (form.it_sts.value == "") {
			alertify.alert("商品商品必須上架或下架！");
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

<script src="https://code.jquery.com/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<c:if test="${product!=null}">
		<script>
			alertify.success('新增商品成功');
		</script>
	</c:if>
	
	<c:if test="${bnsVo!=null}">
		<script>
			alertify.success('新增商品成功');
		</script>
	</c:if>
 <div class="container">
	<div class="col-xs-12 col-sm-2"> 
		<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>
	</div>
	    <!-- =-------------------------------------------------------------------------------- -->
	<div class="col-xs-12 col-sm-10 mainBody">     
		<div class="main">
			<ol class="breadcrumb">
				<li><a href="#">首頁</a></li>
				<li><a href="#">官方商城管理</a>
				<li><a
					href="${pageContext.request.contextPath}/Back-End/AddProductBack.jsp">新增商品</a>
			</ol>
			<table
				class="table table-hover table-striped table-bordered table-condensed ">
				<tr>
					<th width="140"><div>商品圖片</div></th>
					<th width="140"><div>商品名稱</div></th>
					<th width="140"><div>商品描述</div></th>
					<th width="140"><div>商品價格</div></th>
					<th width="140"><div>商品數量</div></th>
					<th width="140"><div>商品狀態</div></th>
					<th width="140"><div>商品分類</div></th>
				</tr>
				<tr>
					<Form
						action="${pageContext.request.contextPath }/AddProductServlet"
						method="post" enctype="multipart/form-data"
						onsubmit="return reg(this);">
						<input type="hidden" name="action" value="AddProductFromBack">
						<input type="hidden" name="it_id" value="${product.it_id}">

						<td><div>
								<img id="preview" />
							</div></td>

						<td><div>
								<input class="form-control" type="text" name="it_name">
							</div></td>
						<td><div>
								<textarea class="form-control" rows="5" name="it_intro"></textarea>
							</div></td>
						<td><div>
								<input class="form-control" type="text" name="it_prc"
									style="ime-mode: disabled"
									onkeyup="return ValidateNumber($(this),value)" />
							</div></td>
						<td><div>
								<input class="form-control" type="text" name="it_num">
							</div></td>
						<td><div>
								<select name="it_sts" class="form-control toption_id ">
									<option value="上架">上架</option>
									<option value="下架">下架</option>
								</select>
							</div></td>

						<td><div>
								<select class="form-control" name="it_cate">
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
									<option value="BonusProduct">紅利商品區</option>
								</select>

							</div></td>
				</tr>
			</table>
			<input type="file" name="it_pic" onchange="readURL(this)"
				targetID="preview">

			<div class="btn-group btn-group-lg" style="margin-top: 30px;">
				<input type="submit" value="確定" class="btn btn-primary btn-lg">
			</div>


			</form>
		</div>
	</div>


</body>







</html>
