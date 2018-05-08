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

@WebServlet("/ProductOfficalCategoryController")
public class ProductOfficialCategoryController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		Map<String, ProductVO> ProductMap=null;
		String it_cate2 = request.getParameter("it_cate");
		String it_cate =new String(it_cate2.getBytes("ISO-8859-1"),"UTF-8");				
		String from=request.getParameter("from");
		String whichPage=request.getParameter("whichPage");
		ProductService service =new ProductService();
	
		//官方商城的分類
		if("snack".equals(type)&&"from".equals(from)){
			Map<String, ProductVO> snack=service.findBy_Cate_Sts_OnProduct_ST001(it_cate);
			ProductMap=snack;			
			request.setAttribute("ProductMap", ProductMap);				
			request.getRequestDispatcher("/ShoppingMall/ShoppingMallForOfficial.jsp").forward(request, response);
		}
		if("sport".equals(type)&&"from".equals(from)){						
			Map<String, ProductVO> sport=service.findBy_Cate_Sts_OnProduct_ST001(it_cate);
			ProductMap=sport;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMallForOfficial.jsp").forward(request, response);
		}
		if("tripleC".equals(type)&&"from".equals(from)){		
			Map<String, ProductVO> tripleC=service.findBy_Cate_Sts_OnProduct_ST001(it_cate);
			ProductMap=tripleC;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMallForOfficial.jsp").forward(request, response);
		}
		if("lifeLiving".equals(type)&&"from".equals(from)){			
			Map<String, ProductVO> lifeLiving=service.findBy_Cate_Sts_OnProduct_ST001(it_cate);
			ProductMap=lifeLiving;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMallForOfficial.jsp").forward(request, response);
		}
		if("workOut".equals(type)&&"from".equals(from)){			
			Map<String, ProductVO> workOut=service.findBy_Cate_Sts_OnProduct_ST001(it_cate);
			ProductMap=workOut;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMallForOfficial.jsp").forward(request, response);
		}
		if("Game".equals(type)&&"from".equals(from)){			
			Map<String, ProductVO> Game=service.findBy_Cate_Sts_OnProduct_ST001(it_cate);
			ProductMap=Game;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMallForOfficial.jsp").forward(request, response);
		}
		if("furniture".equals(type)&&"from".equals(from)){			
			Map<String, ProductVO> furniture=service.findBy_Cate_Sts_OnProduct_ST001(it_cate);
			ProductMap=furniture;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMallForOfficial.jsp").forward(request, response);
		}
		if("Women_Clothing".equals(type)&&"from".equals(from)){
			
			Map<String, ProductVO> Women_Clothing=service.findBy_Cate_Sts_OnProduct_ST001(it_cate);
			ProductMap=Women_Clothing;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMallForOfficial.jsp").forward(request, response);
		}
		if("toyForJoy".equals(type)&&"from".equals(from)){
			
			Map<String, ProductVO> toyForJoy=service.findBy_Cate_Sts_OnProduct_ST001(it_cate);
			ProductMap=toyForJoy;
			request.setAttribute("ProductMap", ProductMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMallForOfficial.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	doGet(request, response);
	}

}
