package FavoriteProductController;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FavoriteProduct.model.FavoriteProductService;
import com.FavoriteProduct.model.FavoriteProductVO;

@WebServlet("/FavoriteProductController")
public class FavoriteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");	
		String whichPage=request.getParameter("whichPage");		
		
		//會員商店 分類部分商品加入收藏
		if ("addFavoriteProductFromCategory".equals(action)) {
			String it_id = request.getParameter("it_id");
			String type= request.getParameter("type");
			String it_cate=request.getParameter("it_cate");
			String mem_id= request.getParameter("mem_id");
			FavoriteProductService favoriteProductService = new FavoriteProductService();
			FavoriteProductVO favoriteProduct = new FavoriteProductVO();
			
			List<FavoriteProductVO> favoriteProductList = favoriteProductService.getAll();
			// 如果加入最愛商品重複就不給加
			Boolean isNotIn=false;
			for (FavoriteProductVO list : favoriteProductList) {				
				if (list.getIt_id().equals(it_id)&&list.getMem_id().equals(mem_id)) {
					isNotIn=true;					
					break;					
				}	
			}
			if(!isNotIn){		
				favoriteProduct.setMem_id(mem_id);
				favoriteProduct.setIt_id(it_id);
				favoriteProductService.insert(favoriteProduct);					
				response.sendRedirect(request.getContextPath() + "/ProductCategoryController?type="+type+"&it_cate="+it_cate+"&whichPage="+whichPage);
			}else{			
			response.sendRedirect(request.getContextPath() + "/ProductCategoryController?type="+type+"&it_cate="+it_cate+"&whichPage="+whichPage);
			}	
		
		}
		
		//官方商店 分類商品加入收藏
		if ("addFavoriteProductFromCategoryOfficial".equals(action)) {
			String it_id = request.getParameter("it_id");
			String type= request.getParameter("type");
			String it_cate=request.getParameter("it_cate");	
			String mem_id= request.getParameter("mem_id");
			FavoriteProductService favoriteProductService = new FavoriteProductService();
			FavoriteProductVO favoriteProduct = new FavoriteProductVO();
			
			List<FavoriteProductVO> favoriteProductList = favoriteProductService.getAll();
			// 如果加入最愛商品重複就不給加
			Boolean isNotIn=false;
			for (FavoriteProductVO list : favoriteProductList) {				
				if (list.getIt_id().equals(it_id)&&list.getMem_id().equals(mem_id)) {
					isNotIn=true;					
					break;					
				}	
			}
			if(!isNotIn){		
				favoriteProduct.setMem_id(mem_id);
				favoriteProduct.setIt_id(it_id);
				favoriteProductService.insert(favoriteProduct);					
				response.sendRedirect(request.getContextPath() + "/ProductOfficalCategoryController?type="+type+"&it_cate="+it_cate+"&from=from"+"&whichPage="+whichPage);
			}else{			
			response.sendRedirect(request.getContextPath() + "/ProductOfficalCategoryController?type="+type+"&it_cate="+it_cate+"&from=from"+"&whichPage="+whichPage);
			}	
		
		}		
		
		//會員商店 所有商品加入收藏
		if ("addFavoriteProduct".equals(action)) {
			String mem_id= request.getParameter("mem_id");
			
			String it_id = request.getParameter("it_id");
			FavoriteProductService favoriteProductService = new FavoriteProductService();
			FavoriteProductVO favoriteProduct = new FavoriteProductVO();
			List<FavoriteProductVO> favoriteProductList = favoriteProductService.getAll();
			// 如果加入最愛商品重複就不給加
			Boolean isNotIn=false;
			for (FavoriteProductVO list : favoriteProductList) {				
				if (list.getIt_id().equals(it_id)&&list.getMem_id().equals(mem_id)) {
					isNotIn=true;					
					break;					
				}	
			}
			if(!isNotIn){		
				favoriteProduct.setMem_id(mem_id);
				favoriteProduct.setIt_id(it_id);
				favoriteProductService.insert(favoriteProduct);					
				response.sendRedirect(request.getContextPath() + "/ShoppingMall/ShoppingMall.jsp?whichPage="+whichPage);
			}else{		
			response.sendRedirect(request.getContextPath() + "/ShoppingMall/ShoppingMall.jsp?whichPage="+whichPage);
			}	
		
		}
		//官方商店 所有商品加入收藏
		if ("addFavoriteProductOfficial".equals(action)) {
			String it_id = request.getParameter("it_id");
			String mem_id= request.getParameter("mem_id");
			FavoriteProductService favoriteProductService = new FavoriteProductService();
			FavoriteProductVO favoriteProduct = new FavoriteProductVO();
			List<FavoriteProductVO> favoriteProductList = favoriteProductService.getAll();
			// 如果加入最愛商品重複就不給加
			Boolean isNotIn=false;
			for (FavoriteProductVO list : favoriteProductList) {				
				if (list.getIt_id().equals(it_id)&&list.getMem_id().equals(mem_id)) {
					isNotIn=true;					
					break;					
				}	
			}
			if(!isNotIn){		
				favoriteProduct.setMem_id(mem_id);
				favoriteProduct.setIt_id(it_id);
				favoriteProductService.insert(favoriteProduct);					
				response.sendRedirect(request.getContextPath() + "/ShoppingMall/ShoppingMallForOfficial.jsp?whichPage="+whichPage);
			}else{		
			response.sendRedirect(request.getContextPath() + "/ShoppingMall/ShoppingMallForOfficial.jsp?whichPage="+whichPage);
			}	
		
		}
		
		
		//詳細商品頁面加入收藏
		if ("addFavoriteProductFromDetailPage".equals(action)) {
			String mem_id= request.getParameter("mem_id");
			String it_id = request.getParameter("it_id");
			FavoriteProductService favoriteProductService = new FavoriteProductService();
			FavoriteProductVO favoriteProduct = new FavoriteProductVO();
			List<FavoriteProductVO> favoriteProductList = favoriteProductService.getAll();
			// 如果加入最愛商品重複就不給加
			Boolean isNotIn=false;
			for (FavoriteProductVO list : favoriteProductList) {				
				if (list.getIt_id().equals(it_id)&&list.getMem_id().equals(mem_id)) {
					isNotIn=true;					
					break;					
				}	
			}
			if(!isNotIn){
				favoriteProduct.setMem_id(mem_id);
				favoriteProduct.setIt_id(it_id);
				favoriteProductService.insert(favoriteProduct);			
				
			}else{
			
			}	
			response.sendRedirect(request.getContextPath() + "/ShoppingMall/ProductDetail.jsp?it_id="+it_id);
		}
		
		
		
		
		if("deleteFavoriteProduct".equals(action)){
			String it_id=request.getParameter("it_id");		
			FavoriteProductService favoriteProductService = new FavoriteProductService();
			favoriteProductService.deleteBy_it_id(it_id);		
			response.sendRedirect(request.getContextPath() + "/ShoppingMall/FavoriteProductList.jsp?it_id="+it_id+"&whichPage="+whichPage);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
