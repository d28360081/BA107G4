<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- p189~ -->
<%@ page import="com.pmt.model.*"%>
<%@ page import="com.pmt_det.model.*" %>

<jsp:useBean id="pmtDetSvc" class="com.pmt_det.model.PmtDetService"/>
<jsp:useBean id="productSvc" class="com.Product.model.ProductService"/>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
<!-- 全部商品區域 -->
<!-- 	<table style="width:100%"> -->
<!-- 	<tr style="height:50px;"></tr> -->
<!-- 	<tr> -->
<!-- 		<th colspan="3" style="text-align:center;border:2px solid black;"><h3><b>商品圖</b></h3></th> -->
<!-- 	</tr> -->
<!-- 	</table> -->
	<form class="form-inline" id="searchITs">
		<div class="form-group">
			<input type="text" name ="it_name" class="form-control" placeholder="查詢商品名稱"
				 id="searchContext" style="font-size:20px;margin-top:10px;">
		</div>
	</form>
	<div class="sortgroup" id="ITPic"></div>
</body>
<script type="text/javascript">
$(document).ready(function() {
// 	searchITs();
	
	$('body').on('change', '#searchContext',function() {
		searchITs();
	});
	$('body').on('keypress', '#searchContext',function(e) {
		code = (e.keyCode ? e.keyCode : e.which);
		if(code == 13){
			e.preventDefault();
			searchITs();
		}
	});	
	function searchITs(){
		var obj = $('#searchITs').serializeArray();
		obj[obj.length] = {name: "action",  value: "search_IT_Name"};   
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/pmt/pmt.do',
			data: obj,
			dataType: 'json',
			success: (function(json) {
				$('#ITPic')[0].innerHTML = "";
				var objKey = Object.keys(json);
				var objValue = Object.values(json);
				for(var i=0;i<objKey.length-1;i++){
					var isExists = false;
					var it_id = objValue[i].it_id;
					
					$('#oriITs').find('div.source_it_id').each(function (index) {
						var ori_it_id = $(this).find('input')[0].value;
						if(ori_it_id == it_id){
							isExists = true;
						}
			       	});
					if(isExists == false){
						var img =  $('<img>').attr('src','${pageContext.request.contextPath}/DBGifReader?table=product&it_id='+it_id);
						img.css('height','200px').css('width','200px');
						var input = $('<input>').attr('type','hidden').attr('name','it_id').attr('value',it_id);
						
						var div = $('<div>').addClass('source_it_id');
						div.draggable({
						    helper: "this",
						    connectToSortable: ".sortgroup",
						    opacity:1
						});
						div.css('text-align','center').css('height','200px').css('width','200px').css('display','inline-block');
						$(div).append(img);
						$(div).append(input);
						
						$('#ITPic').append(div);
					}
				}
			}),
			error:(function() { console.log("second error"); })
		}); 
	}	
});
</script>
</html>