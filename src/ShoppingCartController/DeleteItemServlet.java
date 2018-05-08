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
 * Servlet implementation class DeleteItemServlet
 */
@WebServlet("/DeleteItemServlet")
public class DeleteItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String it_id = (String)request.getParameter("it_id");
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        ProductService service = new ProductService();
        service.deleteCartItem(it_id, cart);

  
        request.getRequestDispatcher("/ShoppingMall/ShoppingCart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
