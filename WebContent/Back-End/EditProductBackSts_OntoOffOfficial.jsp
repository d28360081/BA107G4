<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.Product.model.ProductService"%>
<%@page import="com.Product.model.ProductVO"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 取出 對應的DeptVO物件--%>
<%
	String whichPage=(String)request.getAttribute("whichPage");
	ProductVO productVO = (ProductVO) request.getAttribute("product");
	ProductService service = new ProductService();
	ProductVO product = service.findProduct(productVO.getIt_id());
	
%>


<html>
<head>
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

		if (form.it_cate.value == "") {
			alertify.alert("商品分類必須選擇！");
			return false;
		}

	}
</script>
<title>修改商品</title>

<style>
table#table-1 {
	background-color: #3ab2d9;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h3 {
	color: black;
	display: block;
	margin: 5px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 1px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>


	<table class="myTable">
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
			<Form action="${pageContext.request.contextPath }/EditProductServlet"
				method="post" enctype="multipart/form-data"
				onsubmit="return reg(this);">
				<input type="hidden" name="action" value="RealEditProductFromBack-EndSts_OntoOff"> <input
					type="hidden" name="it_id" value="${product.it_id}"> 
				<input
					type="hidden" name="whichPage" value="<%=whichPage%>">
				<td><div>
						<img id="preview"
							src="${pageContext.request.contextPath}/ProductImageReader?it_id=${product.it_id}"
							class="img100" />
					</div></td>
			<td><div>
					<input class="form-control" type="text" name="it_name"
						value="${product.it_name}">
				</div></td>
			<td><div>
					<textarea class="form-control" rows="5" name="it_intro">${product.it_intro}</textarea>
				</div></td>
			<td><div>
					<input class="form-control" type="text" name="it_prc"
						style="ime-mode: disabled"
						onkeyup="return ValidateNumber($(this),value)"
						value=<fmt:formatNumber value="${product.it_prc}"
										pattern="#" type="number" />>
				</div></td>
			<td><div>
					<input class="form-control" type="text" name="it_num"
						value="${product.it_num}">
				</div></td>
			<td><div>
					<select name="it_sts" class="form-control toption_id "
						data="${product.it_id}">
						<option value="上架" ${product.it_sts=='上架'?'selected':''}>上架</option>
						<option value="下架" ${product.it_sts=='下架'?'selected':''}>下架</option>
					</select>
				</div></td>

			<td><div>
					<select class="form-control" name="it_cate">
						<option value="">請選擇</option>
						<option value="snack" ${(product.it_cate=="snack")?'selected':''}>零食點心區</option>
						<option value="sport" ${(product.it_cate=="sport")?'selected':''}>休閒運動區</option>
						<option value="tripleC"
							${(product.it_cate=="tripleC")?'selected':''}>3C周邊區</option>
						<option value="lifeLiving"
							${(product.it_cate=="lifeLiving")?'selected':''}>居家生活區</option>
						<option value="workOut"
							${(product.it_cate=="workOut")?'selected':''}>運動健身區</option>
						<option value="Game" ${(product.it_cate=="Game")?'selected':''}>電玩遊戲區</option>
						<option value="furniture"
							${(product.it_cate=="furniture")?'selected':''}>傢俱寢飾區</option>
						<option value="Women_Clothing"
							${(product.it_cate=="Women_Clothing")?'selected':''}>女性服飾區</option>
						<option value="toyForJoy"
							${(product.it_cate=="toyForJoy")?'selected':''}>玩具童裝區</option>
					</select>

				</div></td>

		</tr>
	</table>

</body>
</html>