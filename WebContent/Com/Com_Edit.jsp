<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
 
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/gogo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.css">
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
     <style>
    .mainbody {
        padding-top: 30px;
        padding-bottom: 50px;
    }

    .comtitle {
        margin-bottom: 15px;
    }

    .localedropdown {
        padding-right: 250px;
    }
    .submitbtn{
    margin-bottom:50px;
    }

    #previewimg2{
        width: 400px;
        height: 300px;
        background-color: #CCEEFF;
        padding-top: 15px;
        
    }
     #previewimg1{
        width: 400px;
        height: 300px;
        background-color: #CCEEFF;
        padding-top: 15px;
    } 

    .imgcontainer{
        border-radius: 12px;
        background-color: #CCEEFF;
        width: 425px;
        height: 325px;
        margin-right: 70px;
        opacity: 0.7;
    }
   @import url(https://fonts.googleapis.com/earlyaccess/notosanstc.css);
    * {
        font-family: 'Noto Sans TC', sans-serif;
    }
  
  
    
    </style>
    
      <script type="text/javascript">
                 //  <!--
            	 //  for(var j=0;j<szlist.length;j++){
            	//	   if(szlist[j].value()=='${ComVO.it_sz}'){
            	//	   szlist[j].selected=true;
            	//   }   
            	 //  }

            	   
               function openFile(event){
                    var input = event.target; //取得上傳檔案
                    var reader = new FileReader(); //建立FileReader物件

                     reader.readAsDataURL(input.files[0]); //以.readAsDataURL將上傳檔案轉換為base64字串

                     reader.onload = function(){ //FileReader取得上傳檔案後執行以下內容
                        var dataURL = reader.result; //設定變數dataURL為上傳圖檔的base64字串
                        document.getElementById('previewimg1').src=dataURL; //將img的src設定為dataURL並顯示
                        console.log(dataURL);
                                        };
                        }
                          function openFile2(event){
                    var input = event.target; //取得上傳檔案
                    var reader = new FileReader(); //建立FileReader物件

                     reader.readAsDataURL(input.files[0]); //以.readAsDataURL將上傳檔案轉換為base64字串

                     reader.onload = function(){ //FileReader取得上傳檔案後執行以下內容
                        var dataURL = reader.result; //設定變數dataURL為上傳圖檔的base64字串
                        document.getElementById('previewimg2').src=dataURL; //將img的src設定為dataURL並顯示
                        console.log(dataURL);
                                        };
                        }
                          
                          
                          function datechange(){          
                              var Today=new Date();
                              var Toyear=Today.getFullYear();
                              var month=Today.getMonth()+1;
                              var date=Today.getDate();
                              var comenddate=document.getElementById('comenddate').value;
                              var combegindate=document.getElementById('combegindate').value;
                              var comendday=comenddate.substring(8, 10);
                              var combeginday=combegindate.substring(8,10);
                              var combeginmoth=combegindate.substring(5,7);
                              var comendmonth=comenddate.substring(5,7);
                              var comendyear=comenddate.substring(0,4);
                              var combeginyear=combegindate.substring(0,4);
                             
                              
                              // 比對年份大小
                             if(combeginyear<Toyear||comendyear<Toyear){
                                 alert('不得小於現今年分 :'+Toyear);
                                 document.getElementById('comenddate').focus();

                              }else{
                                  if(combeginmoth<month||comendmonth<month){
                                     alert('不得少於現今月份:'+month);

                              }else{
                                 
                                 if(combeginday<date||comendday<date){
                                     alert('不得少於現今日期:'+date);
                                  
                                 }
                                 }
                              }                                                          
                             }      
                          
                          
       </script>

</head>

<body>
  <jsp:include page="/com.HeaderFooter/FrontHeader.jsp" flush="true"/>

    <div class="container mainbody">
        <form action="<%=request.getContextPath()%>/Com_Establish_Controller?type=edit&com_id=${ComVO.com_id}" method="post" enctype="multipart/form-data">
        <!--糾團標題-->
        <div class="container">
            <div class="col-xs-12 col-sm-3 comtitle">
                <div class="input-group comtitle">
                    <span class="input-group-addon" id="basic-addon1">代購標題</span>
                    <input type="text" class="form-control" aria-describedby="basic-addon1" name="com_tit" id="com_tit" value="${ComVO.com_tit }">

                    
                </div>
            </div>
            <div class="col-xs-12 col-sm-9"></div>
        </div>
        <!--糾團標題-->
        <!--糾團開始日期-->
        <div class="container">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group comtitle">
                    <span class="input-group-addon" >糾團開始日期</span>
                    <input type="date" class="form-control" aria-describedby="basic-addon1" name="com_s_dt" id="combegindate">
                    <input class="form-control" type="time" name="combegintime" id="combegintimeinput">
                </div>
            </div>
            <div class="col-xs-12 col-sm-8"></div>
        </div>
        <!--糾團開始日期-->
        <!--糾團結束日期-->
        <div class="container comtitle">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group comtitle">
                    <span class="input-group-addon" >糾團結束日期</span>
                    <input type="date" class="form-control" placeholder="Username" name="com_e_dt" id="comenddate" onchange="datechange()">
                     <input class="form-control" type="time" name="comendtime" id="comendtimeinput">
                </div>
            </div>
            <div class="col-xs-12 col-sm-8"></div>
        </div>
        <!--糾團結束日期-->
        <!--糾團地區限制-->
        <div class="container comtitle">
            <div class="col-xs-12 col-sm-4">
                
             <select class="form-control form-control-lg locale" name="lmt_lcl" id="lmt_lcl">
                             <option value=""  selected="true"></option>
                             <option value="America">America</option>
                             <option value="Europe">Europe</option>
                             <option value="Asia">Asia</option>
                             <option value="Africa">Africa</option>

            </select>
            

            </div>
            <div class="col-xs-12 col-sm-8"></div>
        </div>
        <!--糾團地區限制-->
        <!--糾團參與者等級限制-->
        <div class="container comtitle">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group comtitle">
                    <span class="input-group-addon" id="basic-addon1">最低參與者評價</span>
                    <input type="text" class="form-control" aria-describedby="basic-addon1" name="lmt_atd_clv" id="lmt_atd_clv" value="${ComVO.lmt_atd_clv}">
                </div>
            </div>
            <div class="col-xs-12 col-sm-8"></div>
        </div>
        <!--糾團參與者等級限制-->
        <!--糾團競標者者等級限制-->
        <div class="container comtitle">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group comtitle">
                    <span class="input-group-addon" id="basic-addon1">最低競標者者評價</span>
                    <input type="text" class="form-control" aria-describedby="basic-addon1" name="lmt_auc_lv" id="lmt_auc_lv" value="${ComVO.lmt_auc_lv}">
                </div>
            </div>
            <div class="col-xs-12 col-sm-8"></div>
        </div>
        <!--糾團競標者等級限制-->
        <!--糾團最低人數限制-->
        <div class="container comtitle">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group comtitle">
                    <span class="input-group-addon" id="basic-addon1">最低參加人數</span>
                    <input type="text" class="form-control" aria-describedby="basic-addon1" name="com_min_num" id="com_min_num" value="${ComVO.com_min_num}">
                </div>
            </div>
            <div class="col-xs-12 col-sm-8"></div>
        </div>
        <!--糾團最低人數限制-->
        <!--糾團最大人數限制-->
        <div class="container comtitle">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group comtitle">
                    <span class="input-group-addon" id="basic-addon1">最大參加人數</span>
                    <input type="text" class="form-control" aria-describedby="basic-addon1" name="com_mx_num" id="com_mx_num" value="${ComVO.com_mx_num}">
                </div>
            </div>
            <div class="col-xs-12 col-sm-8"></div>
        </div>
        <!--糾團最大人數限制-->
        <!--糾團最低運費限制-->
        <div class="container comtitle">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group comtitle">
                    <span class="input-group-addon" id="basic-addon1">最低運費限制</span>
                    <input type="text" class="form-control" aria-describedby="basic-addon1" name="lmt_del_prc" id="lmt_del_prc" value="${ComVO.lmt_del_prc}">
                </div>
            </div>
            <div class="col-xs-12 col-sm-8"></div>
        </div>
        <!--糾團最低運費限制-->
        <div class="container">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group comtitle">
                    <span class="input-group-addon" id="basic-addon1">商品顏色</span>
                    <input type="text" class="form-control" aria-describedby="basic-addon1" name="it_col" id="it_col" value="${ComVO.it_col}">
                </div>
            </div>
            <div class="col-xs-12 col-sm-8"></div>
        </div>

        <div class="container">
            <div class="col-xs-12 col-sm-4">
                <select class="form-control form-control-lg locale" name="it_sz" id="it_sz">
                   <option value="nope" selected="true"></option>
                    <option value="s">s</option>
                    <option value="m">m</option>
                    <option value="xl">xl</option>
                    <option value="無尺寸">無尺寸</option>
                </select>
            </div>
            <div class="col-xs-12 col-sm-8"></div>
        </div>

        <div class="container">
            <div class="col-xs-12 col-sm-12">
          		  <div class="form-group">
                   		 <label for="comment">代購內容:</label>
                    	<textarea class="form-control" rows="5" id="comment" name="com_cnt">${ComVO.com_cnt}</textarea>
                 </div>
           
            </div>
        </div>
        
        
        
        
        
        
        <!--糾團圖片一-->
        <div class="container comtitle">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group comtitle">
                    <span class="input-group-addon" id="basic-addon1">糾團圖片一</span>
                    <input type="file" class="form-control" aria-describedby="basic-addon1" name="com_pic1" id="com_pic1" onchange="openFile(event)">
                </div>
            </div>
            <div class="col-xs-12 col-sm-8">
             
        </div>
        <!--糾團圖片一-->
        <!--糾團圖片2-->
        <div class="container comtitle">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group comtitle">
                    <span class="input-group-addon" id="basic-addon1">糾團圖片二</span>
                    <input type="file" class="form-control" name="com_pic2" id="com_pic2" onchange="openFile2(event)">
                    
               
                </div>
            </div>
            <div class="col-xs-12 col-sm-8">
                <div class="container" >
                    <div class="col-xs-12 col-sm-6 imgcontainer">
                    <img id="previewimg1" src="<%=request.getContextPath()%>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC1">
                    </div>
                    <div class="col-xs-12 col-sm-6 imgcontainer">
                    <img id="previewimg2" src="<%=request.getContextPath()%>/ImgHandler?name=${ComVO.com_id}&type=COM_PIC2">
                    </div>
                </div>
            </div>
        </div>
        <!--糾團最圖片2-->
    </div>
    <div class="container">
   				   <div class="col-xs-12 col-sm-4"></div>
                   <div class="col-xs-12 col-sm-4"><button class="btn btn-danger submitbtn" type="submit" id="submitbtn">成立代購<input type="hidden" name="type" value="establish"></div>
                   <div class="col-xs-12 col-sm-4"></div> 
                   <script type="text/javascript">
                       window.onload=function(){
                    	   var itsz=document.getElementById("it_sz");   
                     	   var lcloptions=document.getElementById("lmt_lcl");
                     	   var combegintimeinput=document.getElementById("combegintimeinput");
                     	   var comendtimeinput=document.getElementById("comendtimeinput");
                     	   var combegindate=document.getElementById("combegindate");
                     	   var comenddate=document.getElementById("comenddate");
                     	   var e_dt='${ComVO.com_s_dt}';
                     	   var s_dt='${ComVO.com_e_dt}';
                     	  combegindate.value=e_dt.substring(0,10);
                     	  comenddate.value=s_dt.substring(0,10);
                     	  console.log(combegintimeinput.value);
                     	  combegintimeinput.value=e_dt.substring(11,19);
                     	  comendtimeinput.value=s_dt.substring(11,19);
                     
                     	   for(var i=0;i<lcloptions.options.length;i++){
                     		   if(lcloptions[i].value=='${ComVO.lmt_lcl}'){
                     			   lcloptions[i].selected=true;
                     		   }
                     	   }
                     	   
                     	   for(var j=0;j<it_sz.options.length;j++){
                     		  if(itsz[j].value=='${ComVO.it_sz}'){
                     			 itsz[j].selected=true;
                    		   }
                     	   }
                     	   
                     	   
                    	   
                    	   
                    	   
                    	   
                        document.getElementById('submitbtn').onclick=function(){
     
                        var com_tit=document.getElementById('com_tit');
                        var com_mx_num=document.getElementById('com_mx_num');
                        var com_min_num=document.getElementById('com_min_num');
                        var combegindate=document.getElementById('combegindate');
                        var comendndate=document.getElementById('comendndate');
                        var lmt_atd_clv=document.getElementById('lmt_atd_clv');
                        var lmt_auc_lv=document.getElementById('lmt_auc_lv');
                        var lmt_del_prc=document.getElementById('lmt_del_prc');
                        if (com_tit.value == null || com_tit.value == "") {
                            alert('代購標題要輸入喔');
                            com_tit.focus();
                            return false;
                        } else {
                            if (com_mx_num.value == null || com_mx_num.value == "") {
                                alert('最大人數不得為空白喔');
                                com_mx_num.focus();
                                return false;
                            } else {
                                if (com_min_num.value == null || com_min_num.value == "") {
                                    alert('最小人數不得為空白喔');
                                    com_min_num.focus();
                                    return false;
                                } else {
                                    if (combegindate.value == null || combegindate.value == "") {
                                        alert('開始日期不得為空白喔');
                                        combegindate.focus();
                                        return false;
                                    } else {
                                        if (comenddate.value == null || comenddate.value == "") {
                                            alert('結束日期不得為空白喔');
                                            comenddate.focus();
                                            return false;
                                        } else {
                                            if (lmt_auc_lv.value == null || lmt_auc_lv.value == "") {
                                                alert('競標者等級不得為空白喔');
                                                lmt_auc_lv.focus();
                                                return false;
                                            } else {
                                                
                                                    if (lmt_atd_clv.value == null || lmt_atd_clv.value == "") {
                                                        alert('參加者等級不得為空白喔');
                                                        lmt_atd_clv.focus();
                                                        return false;
                                                    } else {
                                                        if (lmt_del_prc.value == null || lmt_del_prc.value == "") {
                                                            alert('最低運費不得為空白喔');
                                                            lmt_del_prc.focus();
                                                            return false;
                                                        }
                                                    }                                         
                                            }
                                        }




                                    }
                                }
                            }

                        }
                        	
                        
                        
                        	}
                        }
                       

                       
                   </script>
                   
               
    </div>
     
    
    
    
    
    </form>
    <footer class="footer">
        <div class="row">
            <div class="container add-mobile-gutter">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 fix-col-xs">
                        <div class="row">
                            <ul class="link-list">
                                   <li><a href="<%=request.getContextPath()%>/frontform/News.jsp">最新消息 </a></li>
                                <li><a href="<%=request.getContextPath()%>/BonusStore/bonusstoreIndex.jsp">紅利點數</a></li>
                                <li><a href="<%=request.getContextPath()%>/frontform/AboutUs.jsp">關於我們</a></li>
                                <li><a href="FAQ_returns-exchanges.asp">售後服務</a></li>
                                <li><a href="FAQ_order-overseas.asp">INTERNATIONAL ORDERS</a></li>
                                <li><a href="FAQ_anti-fraud.asp">防詐騙</a></li>
                            </ul>
                            <ul class="link-list">
                                <li><a href="about.asp">關於我們</a></li>
                                <li><a href="news.asp?poid=452">媒體露出</a></li>
                                <li><a href="news.asp?poid=450">工作職缺</a></li>
                                <li><a href="FAQ_contact.asp">合作聯絡</a></li>
                                <li><a href="journal.asp?conditiontype=1&amp;poids=573">關懷社會</a></li>
                                <li><a href="journal.asp?conditiontype=1&amp;poids=569">活動分享</a></li>
                            </ul>
                            <ul class="link-list">
                                <li><a href="TWCA.asp" target="_blank"><span class="lock"></span>安心購物</a></li>
                                <li><a href="LEGAL_privacy-policy.asp">隱私權</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
    </footer>
    
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>