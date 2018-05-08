package ShoppingCartController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Product.model.ProductService;
import com.Product.model.Cart;
import com.Product.model.CartItem;
import com.Product.model.ProductVO;
import com.StoreOrder.model.StoreOrderDAO;
import com.StoreOrder.model.StoreOrderVO;

@WebServlet("/TestController")
public class ShoppingCartController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");		
		request.setCharacterEncoding("utf-8");


		if ("clearCart".equals(action)) {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			ProductService service = new ProductService();
			service.clearCart(cart);
			request.getRequestDispatcher("/ShoppingMall/ShoppingCart.jsp").forward(request, response);
		}

		if ("checkOut".equals(action)) {
			RequestDispatcher checkOut = request.getRequestDispatcher("/ShoppingMall/CheckOut.jsp");
			checkOut.forward(request, response);
		}		

	}

}
