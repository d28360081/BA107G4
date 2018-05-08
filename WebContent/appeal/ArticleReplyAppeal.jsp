<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.article_appeal.model.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>ArticleReplyAppeal.jsp</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/Base2.css">


<style>
		.table-title {
			font-size: 16px;
			font-weight: bold;
		}
		
		.table-content {
			font-size: 14px;
		}
		.one_line{
		        width: 300px;
		        overflow: hidden;
		        text-overflow: ellipsis;
		         white-space: nowrap;
			}
		
</style>

</head>

	
<body class="dt-example" >
		<div class="col-xs-12 col-sm-2"> 
	<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>
	</div>
	
<!-- **********************************************************DataTable********************************************************** -->
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/appeal/media/js/jquery.dataTables.js">
</script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/appeal/resources/syntax/shCore.js">
</script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/appeal/resources/demo.js">
</script>
<script type="text/javascript" language="javascript" class="init">	
</script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/appeal/media/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/appeal/resources/syntax/shCore.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/appeal/resources/demo.css">
<style type="text/css" class="init"></style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- **********************************************************DataTable************************************************************ -->    
	
	
        <div class="col-xs-12 col-sm-10">
            <div class="main">
                 <ol class="breadcrumb">
				<li><a href="#">首頁</a></li>
				<li><a href="#">檢舉管理</a>
				<li><a href="<%=request.getContextPath()%>/appeal/ArticleReplyAppeal.jsp">回復文章檢舉</a>
			</ol>
			<!-- **************************************選單******************************** -->
				<div class=col-xs-9 align='right'></div>
				<select class="form-group col-xs-3" id="cselected">
					<option>請選擇顯示分類</option>
					<option value="顯示全部">顯示所有回復文章檢舉</option>
					<option value="待處理">顯示待處理</option>
					<option value="有效檢舉">顯示有效檢舉</option>
					<option value="無效檢舉">顯示無效檢舉</option>
				</select>
			
			
			<!-- **********************************選單********************************** -->
			
			<section>	
				<table class="display table-title table table-hover table-striped table-bordered table-condensed" id="example" cellspacing="0" width="100%">
					<thead class="table-title">
						<tr align="center" style='height:30px'>
							<td width="100"><div>編輯</div></td>
							<td width="140"><div>檢舉時間</div></td>
							<td width="100"><div>檢舉編號</div></td>
							<td width="100"><div>案件狀態</div></td>
							<td width="100"><div>處理員工</div></td>
							<td><div>檢舉內容</div></td>
							<td><div>處理回覆</div></td>
							<td><div>回覆時間</div></td>
						</tr>
					</thead>							
					<tbody id="tbody" class="table-content">	
					</tbody>		
				</table>
			</section>
				
		</div>
			<div id ="editor"> 
			</div>
	</div>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
		
		
		
			$("#cselected").change(function() {

				var selectedOp = "";
				$("option:selected", this).each(function() {
					selectedOp = this.value;
				})
				var articlereplyappealVO = {
					selected : selectedOp,
					action : "selected"
				};
				$('.b').remove();
			
				$.ajax({
					url : "${pageContext.request.contextPath}/articlereplyappeal.do",
					cache: false,
					type : "POST",
					data : articlereplyappealVO,
					async: false,
					success : function(data) {
						console.log(data);		
		 				var re = JSON.parse(data);
		 				var getData = re.araLVO;
		 				
					    var pageLength=-1;
						
						if($("[name='example_length']").length!=0)
						{
						   pageLength = $("[name='example_length']").val();	
						}
		 				
		 				$('#example').DataTable().destroy();
		 				
		 				$('.b').remove();
		 				
						$.each(getData, function(index,getData){	
							
							if(!getData.emp_no){
								getData.emp_no ="";
								getData.art_re_rapl="";
								getData.art_redate="";
							}
							
							
							var tt=$("#tbody");
							tt.append(
							"<tr class='b'><td><button class='btn btn-primary btn-lg' id='edbutton' value='"+getData.art_capl_id+"'  click>編輯</button></td><td>" 
							+getData.art_re_apl_date+ "</td><td>"		
							+getData.art_capl_id+ "</td><td>" 
							+getData.art_re_apl_sts+ "</td><td>"
							+getData.emp_no+ "</td><td><div class=one_line>" 
							+getData.art_re_apl_cnt+ "</td></div><td><div class=one_line>"
							+getData.art_re_rapl+ "</td></div><td <td>" 
							+getData.art_redate+ "</td></tr>"
							);	
						
						});
										
     				    $('#example').DataTable({
  			 				"pagingType": "full_numbers",
  			 				"retrieve": true,
  			 				"lengthMenu": [[-1,3,5,10], ["All",3,5,10,]]
  			 			});
     				    
     				 $("[name='example_length'").val(pageLength).change();
     					 
					},
					error : function(xhr, ajaxOptions, thrownError) {
						console.log(xhr.status + "\n" + thrownError);
						console.log("error");
					}
				});
			});
			$(document).ready(function(){ $('#cselected').val('顯示全部').change()});
		</script>
		
		<script>
			$(document.body).delegate('#edbutton','click',function(){

				var cells = $(this).val();

				var edVO = {
					art_capl_id : cells,
					action : "getOne_For_Update",
									};
			$.ajax({
				url : "${pageContext.request.contextPath}/articlereplyappeal.do",
				cache: false,
				type : "POST",
				data : edVO,
				success : function(data) {
					
					console.log(data);		
	 				var re = JSON.parse(data);
	 				var getData = re;
	 				var element = $("<div class='well'>");
						var table =$("<table class='table table-bordered table-hover' width='900'>");
			            var a1r = $("<tr class='table-title'  align='center'>");
			            var a2 = $("<td width='180'>文章檢舉編號</td><td width='180'>文章編號</td><td width='180'>檢舉會員編號</td><td width='180'>案件狀態</td><td width='180'>檢舉時間</td>");    
			            a1r.append(a2);

			            var a3r = $("<tr class='table-content'  align='center'>");
			            var a4 = $("<td id='art_capl_id' >"+getData.art_capl_id+"</td><td id='re_id'>"+getData.re_id+"</td><td>"+getData.mem_id+"</td><td>"+getData.art_re_apl_sts+"</td><td>"+getData.art_re_apl_date+"</td>");
			            a3r.append(a4);
			            var a5r = $("<tr class='table-title'  align='center'>");
			            var a6 = $("<td class='table-title' colspan='4'>檢舉內容</td>");
			            var a7 = $("<td class='table-title' colspan='1'>檢舉處理</td>");
			            a5r.append(a6).append(a7);
			            var a8r = $("<tr class='table-content'  align='center'>");
			       		var a9 = $("<td colspan='4'>"+getData.art_re_apl_cnt+"</td>");
			       		a8r.append(a9);
			            var $td = $("<td>");
			            var $div = $("<div class='btn-group' data-toggle='buttons'>");
						
			            var a12 = $("<label class='btn btn-default form-check-label'>");
			            var a13 = $("<input class='form-check-input' type='radio' name='art_re_apl_sts' value='有效檢舉'>有效檢舉</label>");
			            a12.append(a13);

			            var a14 = $("<label class='btn btn-default form-check-label'>");
			            var a15 = $("<input class='form-check-input' type='radio' name='art_re_apl_sts' value='無效檢舉'>無效檢舉</label>");
			            a14.append(a15);
			            
			            if(getData.art_re_apl_sts === '無效檢舉')
						{
							a14.addClass("active");
							a15.attr('checked',"checked");
						}
			            else
			            {
			            	a12.addClass("active");
			            	a13.prop('checked',true);        
			            }

                        $div.append(a12).append(a14);
			            $td.append($div);
			            
                        a8r.append($td);
			            
			            var a16r = $("<tr align='center'><td colspan='4' class='table-title'>回覆內容</td><td><a class='geto' href='${pageContext.request.contextPath}/articlereplyappeal.do?art_capl_id="+getData.art_capl_id+"&action=getOrigin' >查看原始文件</a></td></tr>");
			            
			            
			            var a17r = $("<tr align='center'>");
			            var a18 = $("<td colspan='4' class='table-content'><textarea id='art_re_rapl' class='form-control required' rows='2' id='comment' name='art_rapl' required></textarea></td>");
			            var a19 = $("<td><input id='btnsubmit' class='btn btn-primary' type='submit' value='送出編輯' style='width:140px;height:50px;'><img id='loading' style='display:none' src='${pageContext.request.contextPath}/frontform/gif/ajax-loader.gif'/></td>");
			        
			            a17r.append(a18).append(a19);

			           table.append(a1r).append(a3r).append(a5r).append(a8r).append(a16r).append(a17r);

					    a13.click(function(){
							a14.removeClass('active');
							a12.addClass('active');
						});

					    a15.click(function(){
					    	a12.removeClass('active');
					    	a14.addClass('active');
					    });
			            
			            element.html(table);
				  $("#editor").html(element);      
	             
				  $('.geto').click(function()
		              		{
		              	        window.open(this.href, 'morinfo','left=20,top=20,width=500,height=500,toolbar=1,resizable=0');
		              			return false;
		              		});
				  
				
	
				},
				error: function(xhr, ajaxOptions, thrownError) {
					console.log(xhr.status + "\n" + thrownError);
					console.log("error");
				}
			});
		});
			

		
		
	
	
			
	</script>
	<script type="text/javascript">
		$(document).on("click","#btnsubmit",function()
			{
			    $("#loading").show();
			    $("#btnsubmit").prop("disabled",true);
			  
				var art_re_apl_sts;
				var art_capl_id;
				var art_re_rapl = $("#art_re_rapl").val();
				if(art_re_rapl.trim().length == 0){
					alert("回復檢舉內容不得為空白");
					return false
				}
				
				var re_id;
				
				
				var btnsubmitVO ={
					art_re_apl_sts: $('input[name="art_re_apl_sts"]:checked').val(),
					art_capl_id:$("#art_capl_id").text(),
					art_re_rapl:$("#art_re_rapl").val(),
					re_id:$("#re_id").text(),
					action: 'update'
				}

			$.ajax({
				url : "${pageContext.request.contextPath}/articlereplyappeal.do",
				cache: false,
				type : "POST",
				data : btnsubmitVO,
				success : function(data) {
				alert("處理完成");
				$("#cselected").change();
				$(".well").remove();





				},
				error: function(xhr, ajaxOptions, thrownError) {
					console.log(xhr.status + "\n" + thrownError);
					console.log("error");
					$("#loading").hide();
					$("#btnsubmit").prop("disabled",false);
				}
			});
		});

	
	</script>



</body>

</html>

