package ProductController;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Product.model.ProductService;
import com.Product.model.ProductVO;

@WebServlet("/ProductCategoryController")
public class ProductCategoryController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		Map<String, ProductVO> ProductMap=null;
		String it_cate2 = request.getParameter("it_cate");
		String it_cate =new String(it_cate2.getBytes("ISO-8859-1"),"UTF-8");				
		String from=request.getParameter("from");
		ProductService service =new ProductService();
		//會員商店的分類
		if("snack".equals(type)){
			Map<String, ProductVO> snack=service.findBy_Cate_Sts_OnProduct(it_cate);		
			ProductMap=snack;			
			request.setAttribute("ProductMap", ProductMap);				
			request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp").forward(request, response);
		}
		if("sport".equals(type)){						
			Map<String, ProductVO> sport=service.findBy_Cate_Sts_OnProduct(it_cate);
			ProductMap=sport;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp").forward(request, response);
		}
		if("tripleC".equals(type)){		
			Map<String, ProductVO> tripleC=service.findBy_Cate_Sts_OnProduct(it_cate);
			ProductMap=tripleC;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp").forward(request, response);
		}
		if("lifeLiving".equals(type)){			
			Map<String, ProductVO> lifeLiving=service.findBy_Cate_Sts_OnProduct(it_cate);
			ProductMap=lifeLiving;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp").forward(request, response);
		}
		if("workOut".equals(type)){			
			Map<String, ProductVO> workOut=service.findBy_Cate_Sts_OnProduct(it_cate);
			ProductMap=workOut;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp").forward(request, response);
		}
		if("Game".equals(type)){			
			Map<String, ProductVO> Game=service.findBy_Cate_Sts_OnProduct(it_cate);
			ProductMap=Game;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp").forward(request, response);
		}
		if("furniture".equals(type)){			
			Map<String, ProductVO> furniture=service.findBy_Cate_Sts_OnProduct(it_cate);
			ProductMap=furniture;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp").forward(request, response);
		}
		if("Women_Clothing".equals(type)){
			
			Map<String, ProductVO> Women_Clothing=service.findBy_Cate_Sts_OnProduct(it_cate);
			ProductMap=Women_Clothing;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp").forward(request, response);
		}
		if("toyForJoy".equals(type)){
			
			Map<String, ProductVO> toyForJoy=service.findBy_Cate_Sts_OnProduct(it_cate);
			ProductMap=toyForJoy;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp").forward(request, response);
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	doGet(request, response);
	}

}
