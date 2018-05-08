package ShoppingCartController;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Product.model.Cart;
import com.Product.model.CartItem;
import com.Product.model.ProductService;
import com.Product.model.ProductVO;




@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {

 protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

     String it_id = request.getParameter("it_id");
     ProductService service = new ProductService();
     ProductVO product = service.findProduct(it_id);   
     request.getSession().setAttribute("it_id", it_id);
   
  
     Cart cart = (Cart) request.getSession().getAttribute("cart");
     
     if(cart==null) {
         cart = new Cart();
         request.getSession().setAttribute("cart", cart);
     }
   
    if(product.getIt_num()>0){ 
     cart.addCart(product);
    	 request.getRequestDispatcher("/ShoppingMall/ShoppingCart.jsp").forward(request, response);
    }else{
    	request.getRequestDispatcher("/ShoppingMall/ShoppingMall.jsp").forward(request, response);
    }
 }

 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     doGet(request, response);
 }

}
