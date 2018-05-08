<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>申請會員</title>
</head>
<body>
<div id="editInputGroup">
    <form action="<%=request.getContextPath()%>/EmpController" method="post" enctype="multipart/form-data">
    <input type="hidden" name="type" value="establishFinish">
       <div class="form-group2">
    		 <input type="file" name="Emp_Pic" style="display: none;background:url(<%=request.getContextPath()%>/img/gogo.png) no-repeat center;width:180px;height:180px;border-radius:50%;
          border:1px solid #D3D3D3;
          display:block;  
          font-size:0;  
          line-height:0;  
          text-indent:-9999px; " id="Emp_Pic" onchange="openFile(event)">
  		</div>
  <div class="form-group2 col-xs-12 col-sm-12">
    <label for="exampleInputEmail1">員工姓名</label>
    <input type="text" class="form-control" id="emp_nm" aria-describedby="emailHelp" name="emp_nm">
  </div>
  <div class="form-group2">
    <label for="exampleInputPassword1">員工信箱</label>
    <input type="email" class="form-control" id="emp_mail" placeholder="mail" name="emp_mail">
  
  
    <div class="container" style="margin-top:30px;margin-bottom:50px;">
    <div class="[ row ]">
    <div class="[ col-xs-12 col-sm-6 ]">
         <!-- ------------------------------------------------------------------------------------ --> 
        <div class="[ form-group ]">
            <input type="checkbox" name="authOption" id="fancy-checkbox-default-custom-icons" autocomplete="off" value="PMS006"/>
            <div class="[ btn-group ]">
                <label for="fancy-checkbox-default-custom-icons" class="[ btn btn-default ]">
                    <span class="[ glyphicon glyphicon-plus ]"></span>
                    <span class="[ glyphicon glyphicon-minus ]"></span>
                </label>
                <label for="fancy-checkbox-default-custom-icons" class="[ btn btn-default active ]">
                    權限管理
                </label>
            </div>
        </div>
    <!-- ------------------------------------------------------------------------------------ --> 
        <div class="[ form-group ]">
            <input type="checkbox" name="authOption" id="fancy-checkbox-primary-custom-icons" autocomplete="off" value="PMS005"/>
            <div class="[ btn-group ]">
                <label for="fancy-checkbox-primary-custom-icons" class="[ btn btn-primary ]">
                    <span class="[ glyphicon glyphicon-plus ]"></span>
                    <span class="[ glyphicon glyphicon-minus ]"></span>
                </label>
                <label for="fancy-checkbox-primary-custom-icons" class="[ btn btn-default active ]">
                   檢舉管理
                </label>
            </div>
        </div>
     <!-- ------------------------------------------------------------------------------------ -->      
        <div class="[ form-group ]">
            <input type="checkbox" name="authOption" id="fancy-checkbox-success-custom-icons" autocomplete="off" value="PMS004"/>
            <div class="[ btn-group ]">
                <label for="fancy-checkbox-success-custom-icons" class="[ btn btn-success ]">
                    <span class="[ glyphicon glyphicon-plus ]"></span>
                    <span class="[ glyphicon glyphicon-minus ]"></span>
                </label>
                <label for="fancy-checkbox-success-custom-icons" class="[ btn btn-default active ]">
                最新消息管理
                </label>
            </div>
        </div>
          <!-- ------------------------------------------------------------------------------------ --> 
    </div>
    
    
     <div class="[ col-xs-12 col-sm-6 ]">
     	<div class="[ form-group ]">
            <input type="checkbox" name="authOption" id="fancy-checkbox-danger-custom-icons" autocomplete="off" value="PMS003"/>
            <div class="[ btn-group ]">
                <label for="fancy-checkbox-danger-custom-icons" class="[ btn btn-danger ]">
                    <span class="[ glyphicon glyphicon-plus ]"></span>
                    <span class="[ glyphicon glyphicon-minus ]"></span>
                </label>
                <label for="fancy-checkbox-danger-custom-icons" class="[ btn btn-default active ]">
                   官方商城管理
                </label>
            </div>
        </div>
        
        <div class="[ form-group ]">
            <input type="checkbox" name="authOption" id="fancy-checkbox-warning-custom-icons" autocomplete="off" value="PMS002"/>
            <div class="[ btn-group ]">
                <label for="fancy-checkbox-warning-custom-icons" class="[ btn btn-warning ]">
                    <span class="[ glyphicon glyphicon-plus ]"></span>
                    <span class="[ glyphicon glyphicon-minus ]"></span>
                </label>
                <label for="fancy-checkbox-warning-custom-icons" class="[ btn btn-default active ]">
                    任務管理
                </label>
            </div>
        </div>
        
        <div class="[ form-group ]">
            <input type="checkbox" name="authOption" id="fancy-checkbox-info-custom-icons" autocomplete="off" value="PMS001"/>
            <div class="[ btn-group ]">
                <label for="fancy-checkbox-info-custom-icons" class="[ btn btn-info ]">
                    <span class="[ glyphicon glyphicon-plus ]"></span>
                    <span class="[ glyphicon glyphicon-minus ]"></span>
                </label>
                <label for="fancy-checkbox-info-custom-icons" class="[ btn btn-default active ]">
                    促銷活動管理
                </label>
            </div>
        </div>
        
     </div>
     
</div>
  </div>
  <!-- 權限按鈕 -->


    <button type="submit" style="border:1px solid 	#778899;margin-left:310px;border-radius:15px;width:95px;height:40px;" id="membereditSubmitBtn"> 編輯完成</button>

 </div>
</form>
<script>

    document.getElementById('membereditSubmitBtn').onclick=function(){
    	var emp_nm=document.getElementById('emp_nm');
    	var emp_mail=document.getElementById('emp_mail');
    	var authOption=document.getElementsByName('authOption');
    	var count =0;
 
    	if(emp_nm.value==null||emp_nm.value==""){
    		alert("員工姓名不能空白");
    		emp_nm.focus();
    		return false;
    	}
    	if(emp_mail.value==null||emp_mail.value==""){
    		alert("員工密碼不能空白");
    		emp_mail.focus();
    		return false;
    	}
    	
    	for(var i=0;i<authOption.length;i++){
    	
    		if(authOption[i].checked==true){
    			count++;
    		}
    		
    	}
    	
    	

    	if(count==0){
			alert('請勾選權限');
			return false;
		} 
    	
    	
    };
    
    function openFile(event){
        var input = event.target; //取得上傳檔案
        var reader = new FileReader(); //建立FileReader物件

         reader.readAsDataURL(input.files[0]); //以.readAsDataURL將上傳檔案轉換為base64字串

         reader.onload = function(){ //FileReader取得上傳檔案後執行以下內容
            var dataURL = reader.result; //設定變數dataURL為上傳圖檔的base64字串
            document.getElementById('Emp_Pic').style.backgroundImage="url("+dataURL+")"; //將img的src設定為dataURL並顯示
            console.log(dataURL);
                            };
            }
    
    
</script>

   
</body>
</html>