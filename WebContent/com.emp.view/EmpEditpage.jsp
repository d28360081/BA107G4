<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div>
  <ol class="breadcrumb">
    <li><a href="#">後臺首頁</a></li>
    <li><a href="#">權限管理</a></li>
    <li class="active">員工列表</li>
 </ol>
</div >  

  <form action="<%=request.getContextPath()%>/EmpController" method="post" enctype="multipart/form-data">
  
		<div id="editInputGroup col-sm-12" style="text-align:center;">
		  <input type="hidden" name="type" value="editFinish">
	
		    <input type="file" name="Emp_Pic" style="display: none;background:url(<%=request.getContextPath()%>/EmpPic?emp_no=${EmpVO2.emp_no}) no-repeat center;width:180px;height:180px;border-radius:50%;
		          border:1px solid #D3D3D3;
		          display:block;  
		          font-size:0;  
		          line-height:0;  
		          background-size: contain;
		          text-indent:-9999px; " onchange="openFile(event)" id="Emp_Pic">
		          
		</div>

   
  <div class="form-group2">
  
    <label for="emp_no">員工編號</label>
    <input type="text" class="form-control" id="emp_no" name="emp_no" value="${EmpVO2.emp_no}" >
  </div>
  
  <div class="form-group2">
    <label for="pwd">員工姓名</label>
    <input type="text" class="form-control" id="emp_nm" name="emp_nm" value="${EmpVO2.emp_nm}">
  </div>
   
   
  <div class="container">
    <div class="[ row ]" id="btn-groupedit">
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
              
   <button type="submit" style="border:1px solid 	#778899;margin-left:310px;border-radius:15px;width:95px;height:40px;" > 編輯完成</button>
</form>
</div>


<script>
window.onload=function(){
var authoptions=document.getElementsByName("authOption");
for(var i=0;i<authoptions.length;i++){
	<c:forEach var="EmpPmsVO" items="${EmpPmsVOlist}">
	if(authoptions[i].value=='${EmpPmsVO.pmsid}'){
		authoptions[i].checked=true;
	}
	</c:forEach>
}
}

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