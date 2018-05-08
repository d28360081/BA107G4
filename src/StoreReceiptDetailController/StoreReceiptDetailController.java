package StoreReceiptDetailController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Product.model.ProductService;
import com.Product.model.ProductVO;
import com.StoreOrder.model.StoreOrderService;
import com.StoreOrder.model.StoreOrderVO;
import com.StoreReceiptDetail.model.StoreReceiptDetailService;
import com.StoreReceiptDetail.model.StoreReceiptDetailVO;


@WebServlet("/StoreReceiptDetailController")
public class StoreReceiptDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		
		if("CancelOrderFromOrderList".equals(action)){
			
			String rec_id=request.getParameter("rec_id");
			String whichPage=request.getParameter("whichPage");	
		
			StoreOrderService storeOrderService=new StoreOrderService();
			StoreOrderVO storeOrderVO=storeOrderService.StoreOrderfindByPrimaryKey(rec_id);
			storeOrderVO.setRec_dlv_sts("訂單取消");
						
			storeOrderService.updateStoreOrder_sts(storeOrderVO);
			
			
			request.setAttribute("rec_id", rec_id);		
			request.getRequestDispatcher("/OrderListFront/OrderList.jsp?whichPage"+whichPage).forward(request, response);
		}
		
		
	if("CancelOrderFromOrderListBack-EndOfficial".equals(action)){
			
			String rec_id=request.getParameter("rec_id");
			String whichPage=request.getParameter("whichPage");	
		
			StoreOrderService storeOrderService=new StoreOrderService();
			StoreOrderVO storeOrderVO=storeOrderService.StoreOrderfindByPrimaryKey(rec_id);
			storeOrderVO.setRec_dlv_sts("訂單取消");
						
			storeOrderService.updateStoreOrder_sts(storeOrderVO);
			
			
			request.setAttribute("rec_id", rec_id);		
			request.getRequestDispatcher("Back-End/OrderListBackForOfficial.jsp?whichPage"+whichPage).forward(request, response);
		}
if("CancelOrderFromOrderListBack".equals(action)){
			
			String rec_id=request.getParameter("rec_id");
			String whichPage=request.getParameter("whichPage");	
		
			StoreOrderService storeOrderService=new StoreOrderService();
			StoreOrderVO storeOrderVO=storeOrderService.StoreOrderfindByPrimaryKey(rec_id);
			storeOrderVO.setRec_dlv_sts("訂單取消");
						
			storeOrderService.updateStoreOrder_sts(storeOrderVO);
			
			
			request.setAttribute("rec_id", rec_id);		
			request.getRequestDispatcher("Back-End/OrderListBack.jsp?whichPage"+whichPage).forward(request, response);
		}
		
		
		
		
		
		
		
		
		if("ChangeStoreOrder_sts_Send".equals(action)){
			
			String rec_id=request.getParameter("rec_id");
			String whichPage=request.getParameter("whichPage");	
		
			StoreOrderService storeOrderService=new StoreOrderService();
			StoreOrderVO storeOrderVO=storeOrderService.StoreOrderfindByPrimaryKey(rec_id);
			storeOrderVO.setRec_dlv_sts("已出貨");
			
			
			storeOrderService.updateStoreOrder_sts(storeOrderVO);
			
			
			
			
			request.setAttribute("rec_id", rec_id);		
			request.getRequestDispatcher("/OrderListFront/OrderListPersonalStore.jsp?whichPage"+whichPage).forward(request, response);
		}
		try{
		
	if("ChangeStoreOrder_sts_finished".equals(action)){			
			String rec_id=request.getParameter("rec_id");
			String whichPage=request.getParameter("whichPage");	
			StoreOrderService storeOrderService=new StoreOrderService();
			
			StoreOrderVO storeOrderVO=storeOrderService.StoreOrderfindByPrimaryKey(rec_id);
			
			storeOrderVO.setRec_dlv_sts("已完成訂單");
			storeOrderService.updateStoreOrder_sts(storeOrderVO);
			
			request.getRequestDispatcher("/OrderListFront/OrderList.jsp?whichPage"+whichPage).forward(request, response);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		if("lookUpStoreOrderDetail".equals(action)){
			
			String rec_id=request.getParameter("rec_id");			
			String whichPage = (String) request.getParameter("whichPage");
			request.setAttribute("whichPage", whichPage);
			StoreReceiptDetailService storeReceiptDetailService = new StoreReceiptDetailService();

			List<StoreReceiptDetailVO> storeReceiptDetailList = storeReceiptDetailService
					.StoreReceiptDetailgetAllBy_rec_id(rec_id);

			request.setAttribute("storeReceiptDetailList", storeReceiptDetailList);

			StoreOrderService storeOrderService = new StoreOrderService();
			List<StoreOrderVO> storeOrderList = storeOrderService.StoreOrdergetAll();

			request.setAttribute("storeOrderList", storeOrderList);

			ProductService productService = new ProductService();
			Map<String, ProductVO> productMap = productService.getAllProductMap();

			request.setAttribute("productMap", productMap);

			boolean openModal = true;
			request.setAttribute("openModal", openModal);
			request.getRequestDispatcher("/OrderListFront/OrderList.jsp?whichPage"+whichPage).forward(request, response);
			
			
		}		
		
		
		
		
		if ("lookUpStoreOrderDetailFromBack".equals(action)) {
			String rec_id=request.getParameter("rec_id");			
			String whichPage = (String) request.getParameter("whichPage");
			request.setAttribute("whichPage", whichPage);
			StoreReceiptDetailService storeReceiptDetailService = new StoreReceiptDetailService();

			List<StoreReceiptDetailVO> storeReceiptDetailList = storeReceiptDetailService
					.StoreReceiptDetailgetAllBy_rec_id(rec_id);

			request.setAttribute("storeReceiptDetailList", storeReceiptDetailList);

			StoreOrderService storeOrderService = new StoreOrderService();
			List<StoreOrderVO> storeOrderList = storeOrderService.StoreOrdergetAll();

			request.setAttribute("storeOrderList", storeOrderList);

			ProductService productService = new ProductService();
			Map<String, ProductVO> productMap = productService.getAllProductMap();

			request.setAttribute("productMap", productMap);

			boolean openModal = true;
			request.setAttribute("openModal", openModal);
			request.getRequestDispatcher("Back-End/OrderListBack.jsp?whichPage"+whichPage).forward(request, response);
		}
		if ("lookUpStoreOrderDetailFromBackOfficial".equals(action)) {
			String rec_id=request.getParameter("rec_id");			
			String whichPage = (String) request.getParameter("whichPage");
			request.setAttribute("whichPage", whichPage);
			StoreReceiptDetailService storeReceiptDetailService = new StoreReceiptDetailService();

			List<StoreReceiptDetailVO> storeReceiptDetailList = storeReceiptDetailService
					.StoreReceiptDetailgetAllBy_rec_id(rec_id);

			request.setAttribute("storeReceiptDetailList", storeReceiptDetailList);

			StoreOrderService storeOrderService = new StoreOrderService();
			List<StoreOrderVO> storeOrderList = storeOrderService.StoreOrdergetAll();

			request.setAttribute("storeOrderList", storeOrderList);

			ProductService productService = new ProductService();
			Map<String, ProductVO> productMap = productService.getAllProductMap();

			request.setAttribute("productMap", productMap);

			boolean openModal = true;
			request.setAttribute("openModal", openModal);
			request.getRequestDispatcher("Back-End/OrderListBackForOfficial.jsp?whichPage"+whichPage).forward(request, response);
		}
		if ("lookUpStoreOrderDetailFromPersonalStore".equals(action)) {
			String rec_id=request.getParameter("rec_id");			
			String whichPage = (String) request.getParameter("whichPage");
			request.setAttribute("whichPage", whichPage);
			StoreReceiptDetailService storeReceiptDetailService = new StoreReceiptDetailService();

			List<StoreReceiptDetailVO> storeReceiptDetailList = storeReceiptDetailService
					.StoreReceiptDetailgetAllBy_rec_id(rec_id);

			request.setAttribute("storeReceiptDetailList", storeReceiptDetailList);

			StoreOrderService storeOrderService = new StoreOrderService();
			List<StoreOrderVO> storeOrderList = storeOrderService.StoreOrdergetAll();

			request.setAttribute("storeOrderList", storeOrderList);

			ProductService productService = new ProductService();
			Map<String, ProductVO> productMap = productService.getAllProductMap();

			request.setAttribute("productMap", productMap);

			boolean openModal = true;
			request.setAttribute("openModal", openModal);
			request.getRequestDispatcher("/OrderListFront/OrderListPersonalStore.jsp?whichPage"+whichPage).forward(request, response);
		}
		
		
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
