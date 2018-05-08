package StoreOrderController;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Product.model.Cart;
import com.Product.model.CartItem;
import com.Product.model.ProductService;
import com.Product.model.ProductVO;
import com.StoreOrder.model.StoreOrderService;
import com.StoreOrder.model.StoreOrderVO;
import com.StoreReceiptDetail.model.StoreReceiptDetailVO;

@WebServlet("/StoreOrderController")
public class StoreOrderController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		request.setCharacterEncoding("utf-8");

		if ("CheckOutForSure".equals(action)) {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			String rec_py2 = request.getParameter("rec_py");
			String rec_py = new String(rec_py2.getBytes("ISO-8859-1"), "UTF-8");
			
			String mem_id2 = request.getParameter("mem_id");
			String mem_id = new String(mem_id2.getBytes("ISO-8859-1"), "UTF-8");
			
			String rec_delivery2 = request.getParameter("rec_delivery");
			String rec_delivery = new String(rec_delivery2.getBytes("ISO-8859-1"), "UTF-8");

			String buyer_name2 = request.getParameter("buyer_name");
			String buyer_name = new String(buyer_name2.getBytes("ISO-8859-1"), "UTF-8");

			String mem_ph2 = request.getParameter("mem_ph");
			String mem_ph = new String(mem_ph2.getBytes("ISO-8859-1"), "UTF-8");

			String mem_add2 = request.getParameter("mem_add");
			String mem_add = new String(mem_add2.getBytes("ISO-8859-1"), "UTF-8");

			String mem_email2 = request.getParameter("mem_email");
			String mem_email = new String(mem_email2.getBytes("ISO-8859-1"), "UTF-8");
		
		
			ProductService service = new ProductService();
			
			String acc = request.getParameter("acc");

			Set<Entry<String, CartItem>> cartItem = cart.getMap().entrySet();
			Iterator<Entry<String, CartItem>> it = cartItem.iterator();
			StoreOrderService storeOrderService = new StoreOrderService();
			
		
			StoreOrderVO storeOrde = new StoreOrderVO();
			StoreReceiptDetailVO storeReceiptDetail;

			ProductVO product;
			ProductService ProductService = new ProductService();
			
			
			
			//購物車不為空 才新增訂單等以下動作		
			if(cart!=null){
			storeOrde.setMem_id(mem_id);		
			storeOrde.setBuyer_name(buyer_name);
			storeOrde.setMem_email(mem_email);
			storeOrde.setRec_delivery(rec_delivery);
			//訂單金額扣紅利
		
			storeOrde.setUsd_bns(0);			
			
			
			
			storeOrde.setPayment((double) cart.getPrice());
			
			
			storeOrde.setRec_py(rec_py);
			// storeOrde.setRec_date(rec_date);
			
			//會員購買商品如果是官方商品直接出貨			
			
			
			storeOrde.setMem_add(mem_add);
			storeOrde.setMem_ph(mem_ph);
			
		
			int[] array = new int[10];
			String dlv_id = "DLV";
			for (int a = 0; a < array.length; ++a) {
			    array[a] = (int) (Math.random() * 9);
			    dlv_id = dlv_id+array[a];
			}
			storeOrde.setDlv_id(dlv_id);
			
			

			System.out.println("購物車總金額" + cart.getPrice());
			System.out.println("共買多少商品" + cartItem.size());

			HashMap<String,ProductVO> storeReceiptDetailMap = new HashMap<String,ProductVO>();
			//寄給email中的商品
			List<String> ProductNameInEmail= new ArrayList<String>();
			//分店家
		
			
			while (it.hasNext()) {
				storeReceiptDetail = new StoreReceiptDetailVO();
				
				Map.Entry<String, CartItem> ProductInCart = (Map.Entry<String, CartItem>) it.next();
				System.out.println("商品編號:" + ProductInCart.getValue().getProduct().getIt_id());
				System.out.println("商品名稱:" + ProductInCart.getValue().getProduct().getIt_name());
				System.out.println("商品單一價格:" + ProductInCart.getValue().getProduct().getIt_prc());
				System.out.println("每項商品購買數量:" + ProductInCart.getValue().getIt_num());
				System.out.println("每項商品金額總計:" + ProductInCart.getValue().getIt_prc());
				
			
				
				
				//設定商家編號必須等同該商家的商品				
			//	storeOrde.setSt_id(ProductInCart.getValue().getProduct().getSt_id());
				
				
				// 更改商品的數量
				product = ProductService.findProduct(ProductInCart.getValue().getProduct().getIt_id());
				
				product.setIt_num(product.getIt_num() - ProductInCart.getValue().getIt_num());
				
				if (product.getIt_num() - ProductInCart.getValue().getIt_num() >= 0) {
					ProductService.updateProduct_num(product);
				}else{
					//被扣成0 導入購買失敗頁面
					product.setIt_num(0);
					ProductService.updateProduct_num(product);					
				}
				// 新增訂單詳細內容
				//String it_id = ProductInCart.getValue().getProduct().getIt_id();
				Integer it_num = ProductInCart.getValue().getIt_num();
				//storeReceiptDetail.setRec_id(rec_id);
				//storeReceiptDetail.setIt_id(it_id);
				//storeReceiptDetail.setIt_num(it_num);
				ProductInCart.getValue().getProduct().setIt_num(it_num);
				
				
				// 執行新增訂單詳細內容
				storeReceiptDetailMap.put(ProductInCart.getValue().getProduct().getIt_id(),ProductInCart.getValue().getProduct());
				//商品email
				ProductNameInEmail.add(ProductInCart.getValue().getProduct().getIt_name().toString());
				
			}
			
	
			
			
			storeOrderService.insertStoreOrderWithStoreReceiptDetail(storeOrde, storeReceiptDetailMap);
			
			
		
			
			//EMAIL內容
			  String to = mem_email;
		      
		      String subject = "訂購商品通知函";
		      
		      String mem_name = acc;
		     
		      
		      
String messageText = "親愛的 會員 "+mem_name+" 您好:" + "\n"+"您購買了以下商品:" + "\n"+ProductNameInEmail+"\n"+
"\n"+"收貨地址:"+mem_add+"\n"+"一共為"+cart.getPrice()+"元"+"\n"+"請確認訂單是否正確，如有任何問題，請聯絡客服中心。"+"\n"+"請保留此單據，方便日後查詢，也謝謝您的支持與愛護!※此信件為系統發出信件，請勿直接回覆";		       

		      MailService mailService = new MailService();
		      //寄出EMAIL
		      mailService.sendMail(to, subject, messageText);
			
		  	// 結完帳，寄出email後 清空購物車內容
				service.clearCart(cart);
			response.sendRedirect(request.getContextPath() + "/OrderListFront/OrderList.jsp");
			}
			}
		}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

