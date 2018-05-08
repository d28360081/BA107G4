package StoreOrderController;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Product.model.Cart;
import com.Product.model.ProductService;
import com.Product.model.ProductVO;
import com.StoreOrder.model.StoreOrderService;
import com.StoreOrder.model.StoreOrderVO;

/**
 * Servlet implementation class Contol
 */
@WebServlet("/Contol")
public class Contol extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		
		StoreOrderService storeOrderService=new StoreOrderService();
		
		
		StoreOrderVO storeOrde=new StoreOrderVO();
		ProductService service=new ProductService();
		ProductVO productVO=service.findProduct("IT001");
		System.out.println(productVO.getIt_id());
		ProductVO productVO2=service.findProduct("IT004");
		System.out.println(productVO2.getIt_id());
		ProductVO productVO3=service.findProduct("IT005");
		System.out.println(productVO3.getIt_id());
		
		
		storeOrde.setMem_id("M000001");

		storeOrde.setBuyer_name("1");
		storeOrde.setMem_email("1");
		storeOrde.setRec_delivery("1");
		//訂單金額扣紅利
		
		storeOrde.setUsd_bns(0);			
		
		
		
		storeOrde.setPayment((double) 0);
		
		
		storeOrde.setRec_py("1");
		// storeOrde.setRec_date(rec_date);
		storeOrde.setRec_dlv_sts("訂單受理中");
		storeOrde.setMem_add("1");
		storeOrde.setMem_ph("1");
		
	
		int[] array = new int[10];
		String dlv_id = "DLV";
		for (int a = 0; a < array.length; ++a) {
		    array[a] = (int) (Math.random() * 9);
		    dlv_id = dlv_id+array[a];
		}
		storeOrde.setDlv_id(dlv_id);
		
		
		HashMap<String,ProductVO> StoreReceiptDetailMap=new HashMap<String,ProductVO>();
		StoreReceiptDetailMap.put(productVO.getIt_id(), productVO);
		StoreReceiptDetailMap.put(productVO2.getIt_id(), productVO2);
		StoreReceiptDetailMap.put(productVO3.getIt_id(), productVO3);
	
		storeOrderService.insertStoreOrderWithStoreReceiptDetail(storeOrde, StoreReceiptDetailMap);
		System.out.println("TEST");
		}catch(Exception e){
			e.printStackTrace();
		}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
