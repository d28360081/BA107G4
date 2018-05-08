<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    
    <title>Gogo shop</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/base.css">
    <link rel="icon" href="img/gogo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
   	<script src="js/validate/dist/jquery.validate.js"></script>
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <style type="text/css">
        .mem_photo{
            margin-top: 20px;
            margin-left: 85px;
            margin-bottom: 20px;
        }
        .pagination .active a {
            background-color: #3ab2d9;
            border-color: #C9D9DF;
            color:#ffffff;
            cursor: default;
        }
        .pagination .active a:hover {
            background-color: #FFFFFF;
            color:#000000;
            border-color: #C9D9DF;
            cursor: default;
        }
        #paySelect{
        	margin-top: 7px;
        }
       
    </style>
    <!--   自己寫的css   -->
    <!-- <link rel="stylesheet" href="css/OOXX.css"> -->
    <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>

<body>
    <%@ include file="/com.HeaderFooter/FrontHeader.jsp"%>
    <div class="gradient"></div>
    <section>
        <!-- 從這裡開始寫吧 -->
        <!--         <input type="text" class="form-control" style="width: 200px;">
        <button type="submit" class="btn btn-default btn-go ">搜尋</button> -->
<!-- <div class="container">

<table class="table table-striped" >
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
</div> -->
    <div class="modal fade" id="modal-id">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">會員條款</h4>
          </div>
          <div class="modal-body pre-scrollable">
            <p class="lead">1.使用規則 </p>
            <p>若您想成為 gogoshop 的會員或使用 gogoshop 的服務,你必須同意及遵守使用規則,這些使用規則便成為您及,表示您同意接受這些使用規則,為你提供經由畫板,日記,留言板,相薄,論壇,網上資料等（以下簡稱「本服務」）提供服務。
            </p>

            <p class="lead">2.服務條款的修改 </p>
            gogoshop 會根據情況不時修改服務條款而無需特意通知您,服務條款一旦發生變動,如果你在服務條款修改以後希望繼續使用 gogoshop 的服務,您必須同意修改了的服務條款。


            <p class="lead">3.登記資料 
            <p>用戶授權 gogoshop 可向第三方透露包括其的註冊資料在內的資料庫,但 伊莉 不會公開用戶的真實姓名和帳號等個人資料。除非用戶明確要求 gogoshop 或授權某人通過本服務透露這些信息, 以及依據相應的法律及程序要求 伊莉 提供用戶的個人資料。如果用戶提供的資料不準確﹐伊莉保留終止向用戶提供服務的權利。
            </p>

            <p class="lead">4.會員帳戶、密碼及安全</p>
            <p>
            當你完成「本服務」的登記程序成為會員之後，您將收到一個密碼及帳戶。會員是有責任維護密碼及帳戶的機密安全。意即用戶要為利用該密碼及帳戶所進行的一切行動負完全的責任。用戶需同意當密碼或帳戶遭到未獲授權的使用,或者其他任何安全問題發生時,將立即通知 伊莉 。您未能遵守本項規定所衍生之任何損失或損害,伊莉 無法處理及不予負責。 
            </p>

            <p class="lead">5.個別服務之使用條款 </p>
            <p>
            使用論壇的會員,除非另有合約註明,否則必須在遵守論壇內特定的個別規則(否則取消會籍而不作任何倍償),規則亦會以特定方式張貼 ,論壇的管理員有絕對權利察看您的論壇的資料(不包捨真實姓名和帳號)。
            </p>


            <p class="lead">6.服務的修訂或中斷</p>
            <p>
            gogoshop 保留隨時修改或中斷服務而無需特意通知用戶的權利。 如果 gogoshop 行使修改或中斷服務的權利,則無需對用戶或任何第三方負責。</p>


            <p class="lead">7.服務使用行為</p>
            <p>
            你了解一切的資訊、資料、文字、軟體、音樂、音訊、照片、圖形、視訊、信息或其他資料，無論其為公開張貼或私下傳送，均為提供者之責任。意即你，而非gogoshop 

            你同意不將「本服務」作以下用途(否則取消會籍而不作任何倍償)： 
            a.干擾或破壞「本服務」或與「本服務」相連線之伺服器和網路,或不遵守於本服務連線網路之規定.程序.政策或規範； 
            b.以任何方式騷擾他人及傷害未成年人；
            c.冒充任何人或機構,包含但不限於 伊莉 任何工作人員,或以虛偽不實的方式陳述或謊稱與任何人或機構之關係；
            d.將設計目的在於干擾.破壞或限制任何電腦軟體.硬體或通訊設備弁鄐妊n體病毒或其他電腦代碼.檔案和程式之任何資料,加以上載.張貼或以其他方式傳送； 
            e.違反任何相關當地.州政府.國家或國際法規,包含但不限於香港特別行政區的法律,以及任何具有法律效力之規範； 
            f.宣傳或提供任何有關提倡非法活動、危害他人、群眾和動物的生命安全的任何內容。</p>


            <p class="lead">8.拒絕提供擔保及責任的聲明</p>
            <p>
            本服務以使用時所見到及所存在的狀態為基礎,用戶明確地同意由用戶個人承擔使用「本服務」的風險。
            gogoshop 明確表示不向用戶作任何保證﹐不論是明示或暗示的﹐包括商業上使用的擔保､不受到侵犯等。
            gogoshop 不保證服務一定能滿足用戶的要求﹐也不擔保服務的及時性､安全性､不出錯､不中斷。 伊莉拒絕提供任何擔保。
            gogoshop 對任何直接､間接､偶然､特殊及隨後產生的損害不負任何責任。這些損害來自「本服務」
            </p>


            <p class="lead">9.宣傳信息</p>
            <p>
            gogoshop 及第三者有權向用戶寄發廣告, 宣傳資料。 此類資料可能將被寄發到用戶在 gogoshop 登記的電郵地址等。 此類廣告宣傳資料可能來源於 gogoshop 之外的第三者,所以 伊莉 並不對此類廣告宣傳資料的內容負責,用戶同意 gogoshop 無需為此負責。 
            </p>

            <p class="lead">10.彌償</p>
            <p>
            由於你經由「本服務」提供、張貼或傳送之「本服務」、你使用「本服務」、你與「本服務」連線、你違反本服務條款、或你侵害其他人任何權利因而衍生或導致任何第三者提出索賠或請求,包括合理的律師費，你同意賠償 伊莉 及其分公司.分支機構.主管.代理人.聯名廠商或其他夥伴及員工,並使其免受損害。</p>


            <p class="lead">11.法律</p>
            <p>
            您了解網際網路的無國界性，同意遵守當地所有相關規定之網上行為及可接受「內容」之法規。
            您特別同意遵守關於從香港或您居住的國家或地區輸出技術性或個人資料所有相關法律。</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-info" data-dismiss="modal">同意</button>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-xs-12 col-sm-12">
           <div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">新增會員資料</h3>
						<c:if test="${not empty errMsgs}">
							<font style="color:#ff8080">請修正以下錯誤:</font>
						<ul>
						<c:forEach var="message" items="${errMsgs}">
							<li style="color:#ff8080">${message}</li>
						</c:forEach>
						</ul>
						</c:if>
					</div>
					<div class="panel-body">
						<form id="register" method="post" class="form-horizontal" action="login" enctype="multipart/form-data">
							<div class="form-group">
								<label class="col-sm-4 control-label" for="acc">帳號</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="acc2" name="acc" placeholder="請輸入帳號(6個英文數字混合)" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="psw">密碼</label>
								<div class="col-sm-5">
									<input type="password" class="form-control" id="psw2" name="psw" placeholder="請輸入密碼(5個英文數字混合)" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="cpsw">確認密碼</label>
								<div class="col-sm-5">
									<input type="password" class="form-control" id="cpsw" name="cpsw"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_name">暱稱</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="mem_name" name="mem_name"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="bir_dt">生日</label>
								<div class="col-sm-5">
									<input type="date" class="form-control" id="bir_dt" name="bir_dt"/>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="address">地址</label>
								<div class="col-sm-5">
									<input type="text" class="form-control twaddress" id="address" name="address" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_email">電子信箱</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="mem_email" name="mem_email" placeholder="請輸入E-MAIL" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_photo">上傳大頭照</label>
								<div class="col-sm-5">
									<input type="file" class="form-control" name="mem_photo" onchange="document.getElementById('preview').src=window.URL.createObjectURL(this.files[0])" >
                  					<img width="250" height="200" id="preview">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="mem_pay">選擇付款方式</label>
								<div class="col-sm-5">
									<select name="mem_pay" id="paySelect">
									    <option>請選擇</option>
									    <option id="cash" value="現金匯款">現金匯款</option>
									    <option value="信用卡匯款">信用卡匯款</option>
									    <option value="ATM匯款">ATM匯款</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-5 col-sm-offset-4">
									<div class="checkbox">
										<label>
											<input type="checkbox" id="agree" name="agree" value="agree"/><a href="#modal-id" data-toggle="modal" id="apply">會員條款</a>
										</label>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-9 col-sm-offset-4">
									<input type="hidden" name="action" value="register">
									<input type="submit" class="btn btn-info" id="btn" value="確認送出">
								</div>
							</div>
						</form>
						<button onclick="data()"></button>
						<script type="text/javascript">
							var acc = document.getElementById("acc2");
							var psw = document.getElementById("psw2");
							var cpsw = document.getElementById("cpsw");
							var mem_name = document.getElementById("mem_name");
							var bir_dt = document.getElementById("bir_dt");
							var address = document.getElementById("address");
							var mem_email = document.getElementById("mem_email");
							var agree = document.getElementById("agree");
							var cash = document.getElementById("cash");
							function data(){
								acc.value="qoo201";
								psw.value="qoo2018";
								cpsw.value="qoo2018";
								mem_name.value="淡水吳彥祖";
								bir_dt.value="1990-08-31";
								address.value="新北市淡水區中山路93號";
								mem_email.value="ba107team4@gmail.com";
								agree.checked=true;
								cash.selected=true;
							}
						</script>
					</div>
				</div>
        </div>
      </div>
    </div>
    </section>
    <footer class="footer">
        <div class="row">
			<div class="container add-mobile-gutter">
				<div class="row">
					<div class="col-xs-12 col-sm-12 fix-col-xs">
						<div class="row">
							<ul class="link-list">
								<li><a href="<%=request.getContextPath()%>/frontform/News.jsp">最新消息</a></li>
								<li><a href="FAQ_bonus.asp">紅利點數</a></li>
								<li><a href="FAQ_order.asp">購物須知</a></li>
								<li><a href="FAQ_returns-exchanges.asp">售後服務</a></li>
								<li><a href="FAQ_order-overseas.asp">INTERNATIONAL
										ORDERS</a></li>
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
								<li><a href="TWCA.asp" target="_blank"><span
										class="lock"></span>安心購物</a></li>
								<li><a href="LEGAL_privacy-policy.asp">隱私權</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>	
    </footer>
    <script type="text/javascript">
    $.validator.setDefaults( {
		submitHandler: function () {
			alert( "submitted!" );
		}
	} );
	$( document ).ready( function () {
		$( "#register" ).validate( {
			rules: {
				acc: {
					required: true,
					minlength: 6
				},
				psw: {
					required: true,
					minlength: 5
				},
				cpsw: {
					required: true,
					minlength: 5,
					equalTo: "#psw"
				},
				agree: "required"
			},
			messages: {
				acc: {
					required: "請輸入您的帳號",
					minlength: "帳號至少為6位元的，英文數字混合"
				},
				psw: {
					required: "請輸入密碼",
					minlength: "密碼至少為5位元的，英文數字混合"
				},
				cpsw: {
					required: "請輸入確認密碼",
					minlength: "您的密碼必須為5位元以上的英文數字混合",
					equalTo: "確認密碼與密碼不符，請重新輸入"
				},
				agree: "請勾選同意會員條款"
			},
			errorElement: "em",
			errorPlacement: function ( error, element ) {
				// Add the `help-block` class to the error element
				error.addClass( "help-block" );

				if ( element.prop( "type" ) === "checkbox" ) {
					error.insertAfter( element.parent( "label" ) );
				} else {
					error.insertAfter( element );
				}
			},
			highlight: function ( element, errorClass, validClass ) {
				$( element ).parents( ".col-sm-9" ).addClass( "has-error" ).removeClass( "has-success" );
			},
			unhighlight: function (element, errorClass, validClass) {
				$( element ).parents( ".col-sm-9" ).addClass( "has-success" ).removeClass( "has-error" );
			}
		})} );
    </script>
    <script src="https://code.jquery.com/jquery.js"></script>
    <script>
    /*
    * jQuery taiwan address plugin
    * 台灣地址 下拉式連動選單
    * Version 1.1.0 (07/11/2012)
    * @requires jQuery v1.4.2 or later
    *
    * Copyright (c) 2012 Qi-Liang Wen 啟良
    */
    (function ($) {
        var city_list = [
            { pinyin: 'taipeicity', name: '台北市' }, { pinyin: 'xinbeicity', name: '新北市' }, { pinyin: 'keelungcity', name: '基隆市' },
            { pinyin: 'taoyuancounty', name: '桃園市' }, { pinyin: 'hsinchucounty', name: '新竹縣' }, { pinyin: 'hsinchucity', name: '新竹市' },
            { pinyin: 'miaolicounty', name: '苗栗縣' }, { pinyin: 'yilancounty', name: '宜蘭縣' }, { pinyin: 'taichungcity', name: '台中市' },
            { pinyin: 'changhuacounty', name: '彰化縣' }, { pinyin: 'nantoucounty', name: '南投縣' }, { pinyin: 'yunlincounty', name: '雲林縣' },
            { pinyin: 'chiayicity', name: '嘉義市' }, { pinyin: 'chiayicounty', name: '嘉義縣' }, { pinyin: 'tainancity', name: '台南市' },
            { pinyin: 'kaohsiungcity', name: '高雄市' }, { pinyin: 'pingtungcounty', name: '屏東縣' }, { pinyin: 'hualiencounty', name: '花蓮縣' },
            { pinyin: 'taitungcounty', name: '台東縣' }, { pinyin: 'penghucounty', name: '澎湖縣' }, { pinyin: 'kinmencounty', name: '金門縣' },
            { pinyin: 'lienchiangcounty', name: '連江縣' }
            ]
        var dist_list = [
            { city: 'keelungcity', pinyin: 'qidu', name: '七堵區' },
            { city: 'penghucounty', pinyin: 'qimei', name: '七美鄉' },
            { city: 'tainancity', pinyin: 'qigu', name: '七股區' },
            { city: 'pingtungcounty', pinyin: 'sandimen', name: '三地門鄉' },
            { city: 'xinbeicity', pinyin: 'sanxia', name: '三峽區' },
            { city: 'yilancounty', pinyin: 'sanxing', name: '三星鄉' },
            { city: 'kaohsiungcity', pinyin: 'sanmin', name: '三民區' },
            { city: 'miaolicounty', pinyin: 'sanwan', name: '三灣鄉' },
            { city: 'miaolicounty', pinyin: 'sanyi', name: '三義鄉' },
            { city: 'xinbeicity', pinyin: 'sanzhi', name: '三芝區' },
            { city: 'xinbeicity', pinyin: 'sanchong', name: '三重區' },
            { city: 'tainancity', pinyin: 'xiaying', name: '下營區' },
            { city: 'taichungcity', pinyin: 'central', name: '中區' },
            { city: 'xinbeicity', pinyin: 'zhonghe', name: '中和區' },
            { city: 'chiayicounty', pinyin: 'zhongpu', name: '中埔鄉' },
            { city: 'taoyuancounty', pinyin: 'zhongli', name: '中壢區' },
            { city: 'nantoucounty', pinyin: 'zhongliao', name: '中寮鄉' },
            { city: 'keelungcity', pinyin: 'zhongshan', name: '中山區' },
            { city: 'taipeicity', pinyin: 'zhongshan', name: '中山區' },
            { city: 'keelungcity', pinyin: 'zhongzheng', name: '中正區' },
            { city: 'taipeicity', pinyin: 'zhongzheng', name: '中正區' },
            { city: 'tainancity', pinyin: 'westcentral', name: '中西區' },
            { city: 'pingtungcounty', pinyin: 'jiuru', name: '九如鄉' },
            { city: 'yunlincounty', pinyin: 'erlun', name: '二崙鄉' },
            { city: 'changhuacounty', pinyin: 'erlin', name: '二林鎮' },
            { city: 'changhuacounty', pinyin: 'ershui', name: '二水鄉' },
            { city: 'hsinchucounty', pinyin: 'wufeng', name: '五峰鄉' },
            { city: 'yilancounty', pinyin: 'wujie', name: '五結鄉' },
            { city: 'xinbeicity', pinyin: 'wugu', name: '五股區' },
            { city: 'tainancity', pinyin: 'rende', name: '仁德區' },
            { city: 'keelungcity', pinyin: 'renai', name: '仁愛區' },
            { city: 'nantoucounty', pinyin: 'renai', name: '仁愛鄉' },
            { city: 'kaohsiungcity', pinyin: 'renwu', name: '仁武區' },
            { city: 'changhuacounty', pinyin: 'shengang', name: '伸港鄉' },
            { city: 'pingtungcounty', pinyin: 'jiadong', name: '佳冬鄉' },
            { city: 'tainancity', pinyin: 'jiali', name: '佳里區' },
            { city: 'pingtungcounty', pinyin: 'laiyi', name: '來義鄉' },
            { city: 'keelungcity', pinyin: 'xinyi', name: '信義區' },
            { city: 'taipeicity', pinyin: 'xinyi', name: '信義區' },
            { city: 'nantoucounty', pinyin: 'xinyi', name: '信義鄉' },
            { city: 'yunlincounty', pinyin: 'yuanchang', name: '元長鄉' },
            { city: 'hualiencounty', pinyin: 'guangfu', name: '光復鄉' },
            { city: 'pingtungcounty', pinyin: 'neipu', name: '內埔鄉' },
            { city: 'taipeicity', pinyin: 'neihu', name: '內湖區' },
            { city: 'kaohsiungcity', pinyin: 'neimen', name: '內門區' },
            { city: 'taoyuancounty', pinyin: 'bade', name: '八德區' },
            { city: 'xinbeicity', pinyin: 'bali', name: '八里區' },
            { city: 'miaolicounty', pinyin: 'gongguan', name: '公館鄉' },
            { city: 'tainancity', pinyin: 'liujia', name: '六甲區' },
            { city: 'chiayicounty', pinyin: 'liujiao', name: '六腳鄉' },
            { city: 'kaohsiungcity', pinyin: 'liugui', name: '六龜區' },
            { city: 'yilancounty', pinyin: 'dongshan', name: '冬山鄉' },
            { city: 'kaohsiungcity', pinyin: 'qianjin', name: '前金區' },
            { city: 'kaohsiungcity', pinyin: 'qianzhen', name: '前鎮區' },
            { city: 'hsinchucity', pinyin: 'north', name: '北區' },
            { city: 'taichungcity', pinyin: 'north', name: '北區' },
            { city: 'tainancity', pinyin: 'north', name: '北區' },
            { city: 'hsinchucounty', pinyin: 'beipu', name: '北埔鄉' },
            { city: 'taichungcity', pinyin: 'beitun', name: '北屯區' },
            { city: 'taipeicity', pinyin: 'beitou', name: '北投區' },
            { city: 'changhuacounty', pinyin: 'beidou', name: '北斗鎮' },
            { city: 'yunlincounty', pinyin: 'beigang', name: '北港鎮' },
            { city: 'lienchiangcounty', pinyin: 'beigan', name: '北竿鄉' },
            { city: 'tainancity', pinyin: 'beimen', name: '北門區' },
            { city: 'taitungcounty', pinyin: 'beinan', name: '卑南鄉' },
            { city: 'hualiencounty', pinyin: 'zhuoxi', name: '卓溪鄉' },
            { city: 'miaolicounty', pinyin: 'zhuolan', name: '卓蘭鎮' },
            { city: 'tainancity', pinyin: 'nanhua', name: '南化區' },
            { city: 'taichungcity', pinyin: 'south', name: '南區' },
            { city: 'tainancity', pinyin: 'south', name: '南區' },
            { city: 'taichungcity', pinyin: 'nantun', name: '南屯區' },
            { city: 'pingtungcounty', pinyin: 'nanzhou', name: '南州鄉' },
            { city: 'miaolicounty', pinyin: 'nanzhuang', name: '南庄鄉' },
            { city: 'nantoucounty', pinyin: 'nantou', name: '南投市' },
            { city: 'nanhaiislands', pinyin: 'nansha', name: '南沙群島' },
            { city: 'taipeicity', pinyin: 'nangang', name: '南港區' },
            { city: 'yilancounty', pinyin: 'nanao', name: '南澳鄉' },
            { city: 'lienchiangcounty', pinyin: 'nangan', name: '南竿鄉' },
            { city: 'yunlincounty', pinyin: 'kouhu', name: '口湖鄉' },
            { city: 'yunlincounty', pinyin: 'gukeng', name: '古坑鄉' },
            { city: 'taitungcounty', pinyin: 'taitung', name: '台東市' },
            { city: 'yunlincounty', pinyin: 'taixi', name: '台西鄉' },
            { city: 'hualiencounty', pinyin: 'jian', name: '吉安鄉' },
            { city: 'nantoucounty', pinyin: 'mingjian', name: '名間鄉' },
            { city: 'taichungcity', pinyin: 'houli', name: '后里區' },
            { city: 'taichungcity', pinyin: 'heping', name: '和平區' },
            { city: 'changhuacounty', pinyin: 'hemei', name: '和美鎮' },
            { city: 'yilancounty', pinyin: 'yuanshan', name: '員山鄉' },
            { city: 'changhuacounty', pinyin: 'yuanlin', name: '員林鎮' },
            { city: 'tainancity', pinyin: 'shanhua', name: '善化區' },
            { city: 'yunlincounty', pinyin: 'sihu', name: '四湖鄉' },
            { city: 'nantoucounty', pinyin: 'guoxing', name: '國姓鄉' },
            { city: 'xinbeicity', pinyin: 'tucheng', name: '土城區' },
            { city: 'yunlincounty', pinyin: 'tuku', name: '土庫鎮' },
            { city: 'xinbeicity', pinyin: 'pinglin', name: '坪林區' },
            { city: 'changhuacounty', pinyin: 'puxin', name: '埔心鄉' },
            { city: 'nantoucounty', pinyin: 'puli', name: '埔里鎮' },
            { city: 'changhuacounty', pinyin: 'puyan', name: '埔鹽鄉' },
            { city: 'changhuacounty', pinyin: 'pitou', name: '埤頭鄉' },
            { city: 'taipeicity', pinyin: 'shilin', name: '士林區' },
            { city: 'yilancounty', pinyin: 'zhuangwei', name: '壯圍鄉' },
            { city: 'hualiencounty', pinyin: 'shoufeng', name: '壽豐鄉' },
            { city: 'taichungcity', pinyin: 'waipu', name: '外埔區' },
            { city: 'tainancity', pinyin: 'danei', name: '大內區' },
            { city: 'taipeicity', pinyin: 'datong', name: '大同區' },
            { city: 'yilancounty', pinyin: 'datong', name: '大同鄉' },
            { city: 'taoyuancounty', pinyin: 'dayuan', name: '大園區' },
            { city: 'changhuacounty', pinyin: 'dacheng', name: '大城鄉' },
            { city: 'chiayicounty', pinyin: 'dapu', name: '大埔鄉' },
            { city: 'yunlincounty', pinyin: 'dapi', name: '大埤鄉' },
            { city: 'taichungcity', pinyin: 'daan', name: '大安區' },
            { city: 'taipeicity', pinyin: 'daan', name: '大安區' },
            { city: 'kaohsiungcity', pinyin: 'daliao', name: '大寮區' },
            { city: 'changhuacounty', pinyin: 'dacun', name: '大村鄉' },
            { city: 'chiayicounty', pinyin: 'dalin', name: '大林鎮' },
            { city: 'kaohsiungcity', pinyin: 'dashu', name: '大樹區' },
            { city: 'taitungcounty', pinyin: 'dawu', name: '大武鄉' },
            { city: 'miaolicounty', pinyin: 'dahu', name: '大湖鄉' },
            { city: 'taoyuancounty', pinyin: 'daxi', name: '大溪區' },
            { city: 'taichungcity', pinyin: 'dajia', name: '大甲區' },
            { city: 'kaohsiungcity', pinyin: 'dashe', name: '大社區' },
            { city: 'taichungcity', pinyin: 'dadu', name: '大肚區' },
            { city: 'taichungcity', pinyin: 'dali', name: '大里區' },
            { city: 'taichungcity', pinyin: 'daya', name: '大雅區' },
            { city: 'chiayicounty', pinyin: 'taibao', name: '太保市' },
            { city: 'taichungcity', pinyin: 'taiping', name: '太平區' },
            { city: 'taitungcounty', pinyin: 'taimali', name: '太麻里鄉' },
            { city: 'tainancity', pinyin: 'xuejia', name: '學甲區' },
            { city: 'tainancity', pinyin: 'annan', name: '安南區' },
            { city: 'tainancity', pinyin: 'anding', name: '安定區' },
            { city: 'tainancity', pinyin: 'anping', name: '安平區' },
            { city: 'keelungcity', pinyin: 'anle', name: '安樂區' },
            { city: 'tainancity', pinyin: 'guantian', name: '官田區' },
            { city: 'yilancounty', pinyin: 'yilan', name: '宜蘭市' },
            { city: 'hualiencounty', pinyin: 'fuli', name: '富里鄉' },
            { city: 'hsinchucounty', pinyin: 'baoshan', name: '寶山鄉' },
            { city: 'tainancity', pinyin: 'jiangjun', name: '將軍區' },
            { city: 'kaohsiungcity', pinyin: 'xiaogang', name: '小港區' },
            { city: 'hsinchucounty', pinyin: 'jianshi', name: '尖石鄉' },
            { city: 'pingtungcounty', pinyin: 'pingtung', name: '屏東市' },
            { city: 'tainancity', pinyin: 'shanshang', name: '山上區' },
            { city: 'kaohsiungcity', pinyin: 'gangshan', name: '岡山區' },
            { city: 'hsinchucounty', pinyin: 'emei', name: '峨眉鄉' },
            { city: 'pingtungcounty', pinyin: 'kanding', name: '崁頂鄉' },
            { city: 'yunlincounty', pinyin: 'lunbei', name: '崙背鄉' },
            { city: 'kaohsiungcity', pinyin: 'zuoying', name: '左營區' },
            { city: 'tainancity', pinyin: 'zuozhen', name: '左鎮區' },
            { city: 'chiayicounty', pinyin: 'budai', name: '布袋鎮' },
            { city: 'xinbeicity', pinyin: 'pingxi', name: '平溪區' },
            { city: 'taoyuancounty', pinyin: 'pingzhen', name: '平鎮區' },
            { city: 'taitungcounty', pinyin: 'yanping', name: '延平鄉' },
            { city: 'kaohsiungcity', pinyin: 'mituo', name: '彌陀區' },
            { city: 'changhuacounty', pinyin: 'changhua', name: '彰化市' },
            { city: 'tainancity', pinyin: 'houbi', name: '後壁區' },
            { city: 'miaolicounty', pinyin: 'houlong', name: '後龍鎮' },
            { city: 'taoyuancounty', pinyin: 'fuxing', name: '復興區' },
            { city: 'pingtungcounty', pinyin: 'hengchun', name: '恆春鎮' },
            { city: 'taitungcounty', pinyin: 'chenggong', name: '成功鎮' },
            { city: 'taipeicity', pinyin: 'wenshan', name: '文山區' },
            { city: 'yunlincounty', pinyin: 'douliu', name: '斗六市' },
            { city: 'yunlincounty', pinyin: 'dounan', name: '斗南鎮' },
            { city: 'tainancity', pinyin: 'xinhua', name: '新化區' },
            { city: 'pingtungcounty', pinyin: 'xinyuan', name: '新園鄉' },
            { city: 'hualiencounty', pinyin: 'xincheng', name: '新城鄉' },
            { city: 'hsinchucounty', pinyin: 'xinpu', name: '新埔鎮' },
            { city: 'pingtungcounty', pinyin: 'xinpi', name: '新埤鄉' },
            { city: 'taoyuancounty', pinyin: 'xinwu', name: '新屋區' },
            { city: 'tainancity', pinyin: 'xinshi', name: '新市區' },
            { city: 'xinbeicity', pinyin: 'xindian', name: '新店區' },
            { city: 'chiayicounty', pinyin: 'xingang', name: '新港鄉' },
            { city: 'tainancity', pinyin: 'xinying', name: '新營區' },
            { city: 'taichungcity', pinyin: 'xinshe', name: '新社區' },
            { city: 'kaohsiungcity', pinyin: 'xinxing', name: '新興區' },
            { city: 'xinbeicity', pinyin: 'xinzhuang', name: '新莊區' },
            { city: 'hsinchucounty', pinyin: 'xinfeng', name: '新豐鄉' },
            { city: 'kaohsiungcity', pinyin: 'qishan', name: '旗山區' },
            { city: 'kaohsiungcity', pinyin: 'qijin', name: '旗津區' },
            { city: 'pingtungcounty', pinyin: 'chunri', name: '春日鄉' },
            { city: 'keelungcity', pinyin: 'nuannuan', name: '暖暖區' },
            { city: 'penghucounty', pinyin: 'wangan', name: '望安鄉' },
            { city: 'chiayicounty', pinyin: 'puzi', name: '朴子市' },
            { city: 'kaohsiungcity', pinyin: 'shanlin', name: '杉林區' },
            { city: 'taichungcity', pinyin: 'dongshi', name: '東勢區' },
            { city: 'yunlincounty', pinyin: 'dongshi', name: '東勢鄉' },
            { city: 'chiayicity', pinyin: 'east', name: '東區' },
            { city: 'hsinchucity', pinyin: 'east', name: '東區' },
            { city: 'taichungcity', pinyin: 'east', name: '東區' },
            { city: 'tainancity', pinyin: 'east', name: '東區' },
            { city: 'tainancity', pinyin: 'dongshan', name: '東山區' },
            { city: 'lienchiangcounty', pinyin: 'dongyin', name: '東引鄉' },
            { city: 'nanhaiislands', pinyin: 'dongsha', name: '東沙群島' },
            { city: 'taitungcounty', pinyin: 'donghe', name: '東河鄉' },
            { city: 'pingtungcounty', pinyin: 'donggang', name: '東港鎮' },
            { city: 'chiayicounty', pinyin: 'dongshi', name: '東石鄉' },
            { city: 'taipeicity', pinyin: 'songshan', name: '松山區' },
            { city: 'xinbeicity', pinyin: 'banqiao', name: '板橋區' },
            { city: 'pingtungcounty', pinyin: 'fangliao', name: '枋寮鄉' },
            { city: 'pingtungcounty', pinyin: 'fangshan', name: '枋山鄉' },
            { city: 'yunlincounty', pinyin: 'linnei', name: '林內鄉' },
            { city: 'xinbeicity', pinyin: 'linkou', name: '林口區' },
            { city: 'kaohsiungcity', pinyin: 'linyuan', name: '林園區' },
            { city: 'pingtungcounty', pinyin: 'linbian', name: '林邊鄉' },
            { city: 'tainancity', pinyin: 'liuying', name: '柳營區' },
            { city: 'taoyuancounty', pinyin: 'taoyuan', name: '桃園區' },
            { city: 'kaohsiungcity', pinyin: 'taoyuan', name: '桃源區' },
            { city: 'chiayicounty', pinyin: 'meishan', name: '梅山鄉' },
            { city: 'kaohsiungcity', pinyin: 'ziguan', name: '梓官區' },
            { city: 'taichungcity', pinyin: 'wuqi', name: '梧棲區' },
            { city: 'taoyuancounty', pinyin: 'yangmei', name: '楊梅區' },
            { city: 'kaohsiungcity', pinyin: 'nanzi', name: '楠梓區' },
            { city: 'tainancity', pinyin: 'nanxi', name: '楠西區' },
            { city: 'xinbeicity', pinyin: 'shulin', name: '樹林區' },
            { city: 'kaohsiungcity', pinyin: 'qiaotou', name: '橋頭區' },
            { city: 'hsinchucounty', pinyin: 'hengshan', name: '橫山鄉' },
            { city: 'tainancity', pinyin: 'guiren', name: '歸仁區' },
            { city: 'chiayicounty', pinyin: 'minxiong', name: '民雄鄉' },
            { city: 'chiayicounty', pinyin: 'shuishang', name: '水上鄉' },
            { city: 'yunlincounty', pinyin: 'shuilin', name: '水林鄉' },
            { city: 'nantoucounty', pinyin: 'shuili', name: '水里鄉' },
            { city: 'xinbeicity', pinyin: 'yonghe', name: '永和區' },
            { city: 'kaohsiungcity', pinyin: 'yongan', name: '永安區' },
            { city: 'tainancity', pinyin: 'yongkang', name: '永康區' },
            { city: 'changhuacounty', pinyin: 'yongjing', name: '永靖鄉' },
            { city: 'xinbeicity', pinyin: 'xizhi', name: '汐止區' },
            { city: 'taitungcounty', pinyin: 'chishang', name: '池上鄉' },
            { city: 'taichungcity', pinyin: 'shalu', name: '沙鹿區' },
            { city: 'miaolicounty', pinyin: 'taian', name: '泰安鄉' },
            { city: 'xinbeicity', pinyin: 'taishan', name: '泰山區' },
            { city: 'pingtungcounty', pinyin: 'taiwu', name: '泰武鄉' },
            { city: 'taitungcounty', pinyin: 'haiduan', name: '海端鄉' },
            { city: 'xinbeicity', pinyin: 'danshui', name: '淡水區' },
            { city: 'xinbeicity', pinyin: 'shenkeng', name: '深坑區' },
            { city: 'taichungcity', pinyin: 'qingshui', name: '清水區' },
            { city: 'kaohsiungcity', pinyin: 'hunei', name: '湖內區' },
            { city: 'hsinchucounty', pinyin: 'hukou', name: '湖口鄉' },
            { city: 'penghucounty', pinyin: 'huxi', name: '湖西鄉' },
            { city: 'chiayicounty', pinyin: 'xikou', name: '溪口鄉' },
            { city: 'changhuacounty', pinyin: 'xizhou', name: '溪州鄉' },
            { city: 'changhuacounty', pinyin: 'xihu', name: '溪湖鎮' },
            { city: 'pingtungcounty', pinyin: 'manzhou', name: '滿州鄉' },
            { city: 'taichungcity', pinyin: 'tanzi', name: '潭子區' },
            { city: 'pingtungcounty', pinyin: 'chaozhou', name: '潮州鎮' },
            { city: 'kinmencounty', pinyin: 'lieyu', name: '烈嶼鄉' },
            { city: 'xinbeicity', pinyin: 'wulai', name: '烏來區' },
            { city: 'kinmencounty', pinyin: 'wuqiu', name: '烏坵鄉' },
            { city: 'taichungcity', pinyin: 'wuri', name: '烏日區' },
            { city: 'kaohsiungcity', pinyin: 'yanchao', name: '燕巢區' },
            { city: 'pingtungcounty', pinyin: 'mudan', name: '牡丹鄉' },
            { city: 'pingtungcounty', pinyin: 'shizi', name: '獅子鄉' },
            { city: 'miaolicounty', pinyin: 'shitan', name: '獅潭鄉' },
            { city: 'tainancity', pinyin: 'yujing', name: '玉井區' },
            { city: 'hualiencounty', pinyin: 'yuli', name: '玉里鎮' },
            { city: 'pingtungcounty', pinyin: 'liuqiu', name: '琉球鄉' },
            { city: 'hualiencounty', pinyin: 'ruisui', name: '瑞穗鄉' },
            { city: 'xinbeicity', pinyin: 'ruifang', name: '瑞芳區' },
            { city: 'pingtungcounty', pinyin: 'majia', name: '瑪家鄉' },
            { city: 'changhuacounty', pinyin: 'tianzhong', name: '田中鎮' },
            { city: 'kaohsiungcity', pinyin: 'tianliao', name: '田寮區' },
            { city: 'changhuacounty', pinyin: 'tianwei', name: '田尾鄉' },
            { city: 'kaohsiungcity', pinyin: 'jiaxian', name: '甲仙區' },
            { city: 'chiayicounty', pinyin: 'fanlu', name: '番路鄉' },
            { city: 'penghucounty', pinyin: 'baisha', name: '白沙鄉' },
            { city: 'tainancity', pinyin: 'baihe', name: '白河區' },
            { city: 'taichungcity', pinyin: 'shigang', name: '石岡區' },
            { city: 'xinbeicity', pinyin: 'shiding', name: '石碇區' },
            { city: 'xinbeicity', pinyin: 'shimen', name: '石門區' },
            { city: 'yilancounty', pinyin: 'jiaoxi', name: '礁溪鄉' },
            { city: 'changhuacounty', pinyin: 'shetou', name: '社頭鄉' },
            { city: 'taichungcity', pinyin: 'shengang', name: '神岡區' },
            { city: 'changhuacounty', pinyin: 'fuxing', name: '福興鄉' },
            { city: 'hualiencounty', pinyin: 'xiulin', name: '秀林鄉' },
            { city: 'changhuacounty', pinyin: 'xiushui', name: '秀水鄉' },
            { city: 'hsinchucounty', pinyin: 'zhubei', name: '竹北市' },
            { city: 'miaolicounty', pinyin: 'zhunan', name: '竹南鎮' },
            { city: 'changhuacounty', pinyin: 'zhutang', name: '竹塘鄉' },
            { city: 'nantoucounty', pinyin: 'zhushan', name: '竹山鎮' },
            { city: 'chiayicounty', pinyin: 'zhuqi', name: '竹崎鄉' },
            { city: 'hsinchucounty', pinyin: 'zhudong', name: '竹東鎮' },
            { city: 'pingtungcounty', pinyin: 'zhutian', name: '竹田鄉' },
            { city: 'taitungcounty', pinyin: 'ludao', name: '綠島鄉' },
            { city: 'changhuacounty', pinyin: 'xianxi', name: '線西鄉' },
            { city: 'yilancounty', pinyin: 'luodong', name: '羅東鎮' },
            { city: 'kaohsiungcity', pinyin: 'meinong', name: '美濃區' },
            { city: 'chiayicounty', pinyin: 'yizhu', name: '義竹鄉' },
            { city: 'hsinchucounty', pinyin: 'qionglin', name: '芎林鄉' },
            { city: 'changhuacounty', pinyin: 'fenyuan', name: '芬園鄉' },
            { city: 'changhuacounty', pinyin: 'huatan', name: '花壇鄉' },
            { city: 'hualiencounty', pinyin: 'hualien', name: '花蓮市' },
            { city: 'changhuacounty', pinyin: 'fangyuan', name: '芳苑鄉' },
            { city: 'miaolicounty', pinyin: 'yuanli', name: '苑裡鎮' },
            { city: 'kaohsiungcity', pinyin: 'lingya', name: '苓雅區' },
            { city: 'miaolicounty', pinyin: 'miaoli', name: '苗栗市' },
            { city: 'kaohsiungcity', pinyin: 'maolin', name: '茂林區' },
            { city: 'kaohsiungcity', pinyin: 'qieding', name: '茄萣區' },
            { city: 'nantoucounty', pinyin: 'caotun', name: '草屯鎮' },
            { city: 'lienchiangcounty', pinyin: 'juguang', name: '莒光鄉' },
            { city: 'yunlincounty', pinyin: 'citong', name: '莿桐鄉' },
            { city: 'pingtungcounty', pinyin: 'wandan', name: '萬丹鄉' },
            { city: 'pingtungcounty', pinyin: 'wanluan', name: '萬巒鄉' },
            { city: 'hualiencounty', pinyin: 'wanrong', name: '萬榮鄉' },
            { city: 'taipeicity', pinyin: 'wanhua', name: '萬華區' },
            { city: 'xinbeicity', pinyin: 'wanli', name: '萬里區' },
            { city: 'xinbeicity', pinyin: 'luzhou', name: '蘆洲區' },
            { city: 'taoyuancounty', pinyin: 'luzhu', name: '蘆竹區' },
            { city: 'yilancounty', pinyin: 'suao', name: '蘇澳鎮' },
            { city: 'taitungcounty', pinyin: 'lanyu', name: '蘭嶼鄉' },
            { city: 'yunlincounty', pinyin: 'huwei', name: '虎尾鎮' },
            { city: 'yunlincounty', pinyin: 'baozhong', name: '褒忠鄉' },
            { city: 'chiayicity', pinyin: 'west', name: '西區' },
            { city: 'taichungcity', pinyin: 'west', name: '西區' },
            { city: 'taichungcity', pinyin: 'xitun', name: '西屯區' },
            { city: 'penghucounty', pinyin: 'xiyu', name: '西嶼鄉' },
            { city: 'tainancity', pinyin: 'xigang', name: '西港區' },
            { city: 'miaolicounty', pinyin: 'xihu', name: '西湖鄉' },
            { city: 'yunlincounty', pinyin: 'xiluo', name: '西螺鎮' },
            { city: 'taoyuancounty', pinyin: 'guanyin', name: '觀音區' },
            { city: 'taichungcity', pinyin: 'fengyuan', name: '豐原區' },
            { city: 'hualiencounty', pinyin: 'fengbin', name: '豐濱鄉' },
            { city: 'xinbeicity', pinyin: 'gongliao', name: '貢寮區' },
            { city: 'kaohsiungcity', pinyin: 'luzhu', name: '路竹區' },
            { city: 'pingtungcounty', pinyin: 'checheng', name: '車城鄉' },
            { city: 'miaolicounty', pinyin: 'tongxiao', name: '通霄鎮' },
            { city: 'miaolicounty', pinyin: 'zaoqiao', name: '造橋鄉' },
            { city: 'taitungcounty', pinyin: 'daren', name: '達仁鄉' },
            { city: 'kaohsiungcity', pinyin: 'namaxia', name: '那瑪夏區' },
            { city: 'pingtungcounty', pinyin: 'ligang', name: '里港鄉' },
            { city: 'kinmencounty', pinyin: 'jincheng', name: '金城鎮' },
            { city: 'kinmencounty', pinyin: 'jinning', name: '金寧鄉' },
            { city: 'xinbeicity', pinyin: 'jinshan', name: '金山區' },
            { city: 'taitungcounty', pinyin: 'jinfeng', name: '金峰鄉' },
            { city: 'kinmencounty', pinyin: 'jinsha', name: '金沙鎮' },
            { city: 'kinmencounty', pinyin: 'jinhu', name: '金湖鎮' },
            { city: 'miaolicounty', pinyin: 'tongluo', name: '銅鑼鄉' },
            { city: 'pingtungcounty', pinyin: 'changzhi', name: '長治鄉' },
            { city: 'taitungcounty', pinyin: 'changbin', name: '長濱鄉' },
            { city: 'taitungcounty', pinyin: 'guanshan', name: '關山鎮' },
            { city: 'tainancity', pinyin: 'guanmiao', name: '關廟區' },
            { city: 'hsinchucounty', pinyin: 'guanxi', name: '關西鎮' },
            { city: 'kaohsiungcity', pinyin: 'alian', name: '阿蓮區' },
            { city: 'chiayicounty', pinyin: 'alishan', name: '阿里山鄉' },
            { city: 'nantoucounty', pinyin: 'jiji', name: '集集鎮' },
            { city: 'xinbeicity', pinyin: 'shuangxi', name: '雙溪區' },
            { city: 'pingtungcounty', pinyin: 'wutai', name: '霧台鄉' },
            { city: 'taichungcity', pinyin: 'wufeng', name: '霧峰區' },
            { city: 'miaolicounty', pinyin: 'toufen', name: '頭份鎮' },
            { city: 'yilancounty', pinyin: 'toucheng', name: '頭城鎮' },
            { city: 'miaolicounty', pinyin: 'touwu', name: '頭屋鄉' },
            { city: 'hsinchucity', pinyin: 'xiangshan', name: '香山區' },
            { city: 'penghucounty', pinyin: 'magong', name: '馬公市' },
            { city: 'pingtungcounty', pinyin: 'gaoshu', name: '高樹鄉' },
            { city: 'nantoucounty', pinyin: 'yuchi', name: '魚池鄉' },
            { city: 'kaohsiungcity', pinyin: 'niaosong', name: '鳥松區' },
            { city: 'kaohsiungcity', pinyin: 'fengshan', name: '鳳山區' },
            { city: 'hualiencounty', pinyin: 'fenglin', name: '鳳林鎮' },
            { city: 'xinbeicity', pinyin: 'yingge', name: '鶯歌區' },
            { city: 'pingtungcounty', pinyin: 'yanpu', name: '鹽埔鄉' },
            { city: 'kaohsiungcity', pinyin: 'yancheng', name: '鹽埕區' },
            { city: 'tainancity', pinyin: 'yanshui', name: '鹽水區' },
            { city: 'changhuacounty', pinyin: 'lugang', name: '鹿港鎮' },
            { city: 'chiayicounty', pinyin: 'lucao', name: '鹿草鄉' },
            { city: 'nantoucounty', pinyin: 'lugu', name: '鹿谷鄉' },
            { city: 'taitungcounty', pinyin: 'luye', name: '鹿野鄉' },
            { city: 'pingtungcounty', pinyin: 'linluo', name: '麟洛鄉' },
            { city: 'yunlincounty', pinyin: 'mailiao', name: '麥寮鄉' },
            { city: 'tainancity', pinyin: 'madou', name: '麻豆區' },
            { city: 'kaohsiungcity', pinyin: 'gushan', name: '鼓山區' },
            { city: 'taichungcity', pinyin: 'longjing', name: '龍井區' },
            { city: 'tainancity', pinyin: 'longqi', name: '龍崎區' },
            { city: 'taoyuancounty', pinyin: 'longtan', name: '龍潭區' },
            { city: 'taoyuancounty', pinyin: 'guishan', name: '龜山區' }
            ];

        $.fn.twaddress = function () {
            this.each(function () {
                function info() {
                    this.city = '';
                    this.city_pinyin = '';
                    this.dist = '';
                    this.dist_pinyin = '';
                    this.detail = '';
                }
                var o = new info();
                var old_dist = null;
                var opts = $.extend({}, $.fn.twaddress.defaults);
                var options = { city: "", dist: "", detail: "" };
                var fisttimeflag = true;
                var $inputbox = $(this);
                var $twaddrs = $('<div style="display: inline;"></div>');
                var $twcity = $('<select></select>');
                var $twmore = $('<input type="text" class="input"></input>');
                for (var i = 0; i < city_list.length; i++) {
                    if ($inputbox.val().indexOf(city_list[i].name) >= 0) {
                        options.city = city_list[i].name;
                        $inputbox.val($inputbox.val().replace(city_list[i].name, ""));
                    }
                }
                var mx_len = 0;
                for (var i = 0; i < dist_list.length; i++) {
                    if ($inputbox.val().indexOf(dist_list[i].name) >= 0) {
                        if (mx_len < dist_list[i].name.length) {
                            mx_len = dist_list[i].name.length;
                            options.dist = dist_list[i].name;
                        }
                    }
                }
                $inputbox.val($inputbox.val().replace(options.dist, ""));
                options.detail = $inputbox.val();
                $twcity.keyup(function () {
                    $twcity.change();
                });
                $twcity.change(function () {
                    var $new_twdist = $('<select></select>');
                    if (options != undefined && options.detail != undefined && fisttimeflag) {
                        if (options.detail != "hidden") {
                            $twmore.val(options.detail);
                        } else {
                            $twmore.hide();
                        }
                    }
                    for (var i = 0; i < dist_list.length; i++) {
                        if (dist_list[i].city == $twcity.val()) {
                            if (options != undefined && options.dist != undefined && fisttimeflag && dist_list[i].name == options.dist) {
                                $new_twdist.append('<option value="' + dist_list[i].pinyin + '" selected="selected">' + dist_list[i].name + '</option>');
                                fisttimeflag = false;
                            } else {
                                $new_twdist.append('<option value="' + dist_list[i].pinyin + '">' + dist_list[i].name + '</option>');
                            }
                        }
                    }
                    $twcity.after($new_twdist);
                    $inputbox.val(getValue($twcity, $new_twdist, $twmore, o));
                    $inputbox.change();

                    $new_twdist.keyup(function () {
                        $new_twdist.change();
                    });
                    $new_twdist.change(function () {
                        $inputbox.val(getValue($twcity, $new_twdist, $twmore, o));
                        $inputbox.change();
                    });
                    $twmore.change(function () {
                        $inputbox.val(getValue($twcity, $new_twdist, $twmore, o));
                        $inputbox.change();
                    });
                    if (old_dist != null) {
                        old_dist.hide().remove();
                    }
                    old_dist = $new_twdist;
                });

                for (var i = 0; i < city_list.length; i++) {
                    if (options != undefined && options.city != undefined && city_list[i].name == options.city) {
                        $twcity.append('<option value="' + city_list[i].pinyin + '" selected="selected">' + city_list[i].name + '</option>');
                    } else {
                        $twcity.append('<option value="' + city_list[i].pinyin + '">' + city_list[i].name + '</option>');
                    }
                }
                $twaddrs.append($twcity);
                $twaddrs.append($twmore);
                $(this).after($twaddrs);
                $(this).hide();
                $twcity.change();
                return o;
            });
            function getValue(city, dist, more, o) {
                var full_address = '';
                for (var i = 0; i < city_list.length; i++) {
                    if (city_list[i].pinyin == city.val()) {
                        full_address = city_list[i].name;
                        o.city = city_list[i].name;
                    }
                }
                for (var i = 0; i < dist_list.length; i++) {
                    if (dist_list[i].city == city.val() && dist_list[i].pinyin == dist.val()) {
                        full_address += dist_list[i].name;
                        o.dist = dist_list[i].name;
                    }
                }
                o.city_pinyin = city.val();
                o.dist_pinyin = dist.val();
                o.detail = more.val();
                return full_address + more.val();
            }
        };

    })(jQuery);

    $(function () {
        $(".twaddress").twaddress();
    });
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>