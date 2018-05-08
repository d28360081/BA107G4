package ProductController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Product.model.ProductVO;
import com.Product.model.ProductService;

@WebServlet("/SerachProduct")
public class SerachProduct extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String It_name2 = request.getParameter("It_name");
		String It_name = new String(It_name2.getBytes("ISO-8859-1"), "UTF-8");
		String whichPage = request.getParameter("whichPage");
		String action = request.getParameter("action");		
		//搜尋商品 會員商店
		if (action.equals("SerachFromAll")) {		
				ProductService productService = new ProductService();
				Map<String, ProductVO> ProductSearchMap = new HashMap<String, ProductVO>();
				ProductSearchMap = productService.getAllProductMapBy_it_name_Serach(It_name);
				request.setAttribute("ProductSearchMap", ProductSearchMap);
				request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp?whichPage=" + whichPage).forward(request, response);
			
		}
		//搜尋商品 官方商店
		if (action.equals("SerachFromAllOfficial")) {		
			ProductService productService = new ProductService();
			Map<String, ProductVO> ProductSearchMap = new HashMap<String, ProductVO>();
			ProductSearchMap = productService.getAllProductMapBy_it_name_Serach_ST001(It_name);
			request.setAttribute("ProductSearchMap", ProductSearchMap);
			request.getRequestDispatcher("/ShoppingMall/ShoppingMallForOfficial.jsp?whichPage=" + whichPage).forward(request, response);
		
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
