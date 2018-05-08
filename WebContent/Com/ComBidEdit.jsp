<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>GogoShop</title>
</head>
<body>

 <img src="<%=request.getContextPath()%>/img/gogo.png">
       
        <h2>恭喜您, 團購編號:${ComVO.com_id} 已經成團了</h2><br>
        <h3>現在在下面打一點, 你希望對代購人的提醒吧</h3><br>
        <h3>已經選擇代購競標的時間</h3>
       
       <form action="<%=request.getContextPath()%>/CombidController?type=EditBiddingFinish" method="post">
       <input type="hidden" name="com_id" value="${ComVO.com_id}">
       <div class="input-group comtitle">
                    <span class="input-group-addon" >競標結束日期</span>
                    <input type="date" class="form-control" placeholder="Username" name="LT_M_DT" id="LT_M_DT">        
                     <input class="form-control" type="time" name="LT_M_DT_time" id="LT_M_DT_time">     
      </div>
       
        <div id="btn1" style="border: 1px solid;height: 10px;width: 5%;margin-bottom: 10px;"></div>
       <textarea id="com_rmd" name="com_rmd">
       </textarea>
      
         <button type="submit" class="btn btn-go">送出</button>
        </form>
	  <script>
			CKEDITOR.replace('com_rmd');
			 $('#btn1').click(function(){
	                CKEDITOR.instances.com_rmd.setData(' <p>代買物品：零食/雜貨 <br>運費/面交時地： ◎郵局掛號 &lt; 1-5 件 65元 &gt; &lt; 6 件以上 80元 &gt; ◎店到店(7-11 . 全家) 一律 60 元 (運費皆多不退少不補，除大型家電或是高單價商品請來信討論) <br>◎可另開蝦皮賣場 代買物品： 行程屬個人旅遊，除了重物及大型電器用品外，其他行程內可買的到的商品都能代買 <br>購入來源：西松屋/ASHIBINA OUTLET/AEON mall/大國藥妝/國際通商店/美國村/機場/海洋館<br> 購入價格：當地含稅價*0.29匯率 <br>代買費用：購入價格(臺幣)*5%<br> 截止日期：6/2 <br>提供LINE ID:來訊提供 <br>參與方式：繳交訂金NT200元(無繳交訂金，若有下訂單時須在24小時內付款商品全額)。 <br>訂金將多退少補，退款將在連線時間結束後一週內退款完畢。 <br>運費/面交時地： 中壢可面交 蝦皮需加運費</p>');
	                var data = CKEDITOR.instances.com_rmd.getData();
	            	document.getElementById("art_cnt").value=data;
			 });
			 
			 
 		
			
		</script>


</body>

</html>