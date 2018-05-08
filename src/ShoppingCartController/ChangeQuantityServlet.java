package ShoppingCartController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Product.model.Cart;
import com.Product.model.ProductService;


/**
 * Servlet implementation class ChangeQuantityServlet
 */
@WebServlet("/ChangeQuantityServlet")

public class ChangeQuantityServlet extends HttpServlet {

 protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

     String it_id = request.getParameter("it_id");
     String it_num =request.getParameter("it_num");
   
     Cart cart = (Cart) request.getSession().getAttribute("cart");

     ProductService service = new ProductService();
     service.changeItemQuantity(it_id, it_num, cart);

     request.getRequestDispatcher("/ShoppingMall/ShoppingCart.jsp").forward(request, response);
 }

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     doGet(request, response);
 }

}