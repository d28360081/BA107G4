package ProductController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Product.model.ProductService;
import com.Product.model.ProductVO;

@MultipartConfig
@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		if ("EditProduct".equals(action)) {
			String it_name2 = request.getParameter("it_name");

			String It_name = new String(it_name2.getBytes("ISO-8859-1"), "UTF-8");
			String it_intro2 = request.getParameter("it_intro");
			String it_intro = new String(it_intro2.getBytes("ISO-8859-1"), "UTF-8");


			String it_cate2 = request.getParameter("it_cate");
			String it_cate = new String(it_cate2.getBytes("ISO-8859-1"), "UTF-8");

			Double it_prc = Double.valueOf(request.getParameter("it_prc"));
			Integer it_num = Integer.valueOf(request.getParameter("it_num"));
		
			Part it_pic = request.getPart("it_pic");
			  InputStream pic=it_pic.getInputStream();
		        byte[] upload =new byte[pic.available()];
		        pic.read(upload, 0, upload.length);

			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();

			ProductVO product = service.findProduct(it_id);
			product.setIt_name(It_name);
			product.setIt_intro(it_intro);
			product.setIt_num(it_num);
			product.setIt_prc(it_prc);

			if(upload.length==0){
				product.setIt_pic(product.getIt_pic());
			}else{
				product.setIt_pic(upload);				
			}
			product.setIt_sts("上架");
			product.setIt_cate(it_cate);
			service.updateProduct(product);

		
				String whichPage = (String) request.getParameter("whichPage");
				response.sendRedirect(
						request.getContextPath() + "/PersonalStore/PersonalStoreProductSTS_ON.jsp?whichPage=" + whichPage);
			
		}
		if ("RealEditProductFromBack-End".equals(action)) {
			String it_name2 = request.getParameter("it_name");

			String It_name = new String(it_name2.getBytes("ISO-8859-1"), "UTF-8");
			String it_intro2 = request.getParameter("it_intro");
			String it_intro = new String(it_intro2.getBytes("ISO-8859-1"), "UTF-8");

			String it_sts2 = request.getParameter("it_sts");
			String it_sts = new String(it_sts2.getBytes("ISO-8859-1"), "UTF-8");

			String it_cate2 = request.getParameter("it_cate");
			String it_cate = new String(it_cate2.getBytes("ISO-8859-1"), "UTF-8");

			Double it_prc = Double.valueOf(request.getParameter("it_prc"));
			Integer it_num = Integer.valueOf(request.getParameter("it_num"));
			Part it_pic = request.getPart("it_pic");
			  InputStream pic=it_pic.getInputStream();
		        byte[] upload =new byte[pic.available()];
		        pic.read(upload, 0, upload.length);

			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();

			ProductVO product = service.findProduct(it_id);
			product.setIt_name(It_name);
			product.setIt_intro(it_intro);
			product.setIt_num(it_num);
			product.setIt_prc(it_prc);

			if(upload.length==0){
				product.setIt_pic(product.getIt_pic());
			}else{
				product.setIt_pic(upload);
				
			}

			product.setIt_sts(it_sts);
			product.setIt_cate(it_cate);
			service.updateProduct(product);
			request.setAttribute("productEditAll", product);

			String whichPage = (String) request.getParameter("whichPage");
			request.getRequestDispatcher("/Back-End/ProductALLEditBackOfficial.jsp?whichPage=" + whichPage).forward(request,
					response);
			// response.sendRedirect(
			// request.getContextPath() +
			// "/Back-End/ProductALLEditBack.jsp?whichPage=" + whichPage);
		}
		if ("RealEditProductFromBack-EndSts_OfftoOn".equals(action)) {
			String it_name2 = request.getParameter("it_name");

			String It_name = new String(it_name2.getBytes("ISO-8859-1"), "UTF-8");
			String it_intro2 = request.getParameter("it_intro");
			String it_intro = new String(it_intro2.getBytes("ISO-8859-1"), "UTF-8");

			String it_sts2 = request.getParameter("it_sts");
			String it_sts = new String(it_sts2.getBytes("ISO-8859-1"), "UTF-8");

			String it_cate2 = request.getParameter("it_cate");
			String it_cate = new String(it_cate2.getBytes("ISO-8859-1"), "UTF-8");

			Double it_prc = Double.valueOf(request.getParameter("it_prc"));
			Integer it_num = Integer.valueOf(request.getParameter("it_num"));
			// byte[] it_pic =request.getParameter("it_pic");
			Part it_pic = request.getPart("it_pic");
			  InputStream pic=it_pic.getInputStream();
		        byte[] upload =new byte[pic.available()];
		        pic.read(upload, 0, upload.length);

			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();

			ProductVO product = service.findProduct(it_id);
			product.setIt_name(It_name);
			product.setIt_intro(it_intro);
			product.setIt_num(it_num);
			product.setIt_prc(it_prc);

			if(upload.length==0){
				product.setIt_pic(product.getIt_pic());
			}else{
				product.setIt_pic(upload);				
			}

			product.setIt_sts(it_sts);
			product.setIt_cate(it_cate);
			service.updateProduct(product);

			String whichPage = (String) request.getParameter("whichPage");

			request.setAttribute("whichPage", whichPage);
			request.setAttribute("productOff", product);
			request.getRequestDispatcher("/Back-End/ProductSts_Off_EditBackOfficial.jsp?whichPage=" + whichPage)
					.forward(request, response);
		}
		if ("RealEditProductFromBack-EndSts_OntoOff".equals(action)) {

			String it_name2 = request.getParameter("it_name");

			String It_name = new String(it_name2.getBytes("ISO-8859-1"), "UTF-8");
			String it_intro2 = request.getParameter("it_intro");
			String it_intro = new String(it_intro2.getBytes("ISO-8859-1"), "UTF-8");

			String it_sts2 = request.getParameter("it_sts");
			String it_sts = new String(it_sts2.getBytes("ISO-8859-1"), "UTF-8");

			String it_cate2 = request.getParameter("it_cate");
			String it_cate = new String(it_cate2.getBytes("ISO-8859-1"), "UTF-8");

			Double it_prc = Double.valueOf(request.getParameter("it_prc"));
			Integer it_num = Integer.valueOf(request.getParameter("it_num"));
			Part it_pic = request.getPart("it_pic");
			  InputStream pic=it_pic.getInputStream();
		        byte[] upload =new byte[pic.available()];
		        pic.read(upload, 0, upload.length);

			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();

			ProductVO product = service.findProduct(it_id);
			product.setIt_name(It_name);
			product.setIt_intro(it_intro);
			product.setIt_num(it_num);
			product.setIt_prc(it_prc);

			if(upload.length==0){
				product.setIt_pic(product.getIt_pic());
			}else{
				product.setIt_pic(upload);				
			}

			product.setIt_sts(it_sts);
			product.setIt_cate(it_cate);
			service.updateProduct(product);

			String whichPage = (String) request.getParameter("whichPage");

			request.setAttribute("whichPage", whichPage);
			request.setAttribute("productOn", product);
			request.getRequestDispatcher("/Back-End/ProductSts_On_EditBackOfficial.jsp?whichPage=" + whichPage).forward(request,
					response);

			// response.sendRedirect(
			// request.getContextPath() +
			// "/Back-End/EditProductBackSts_OntoOff.jsp?whichPage="+whichPage
			// );
		}
		// 跳出燈箱用 還沒修改資料，修改資料部分在RealeditProductFromBack-End部分
		if ("editProductFromBack-End".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			String whichPage = (String) request.getParameter("whichPage");
			request.setAttribute("whichPage", whichPage);
			ProductService service = new ProductService();

			ProductVO product = service.findProduct(it_id);
			request.setAttribute("product", product);

			boolean openModal = true;
			request.setAttribute("openModal", openModal);
			request.getRequestDispatcher("/Back-End/ProductALLEditBackOfficial.jsp").forward(request, response);
		}

		// 跳出燈箱用 還沒修改資料，修改資料部分在RealeditProductFromBackEndOfftoOn部分
		if ("editProductFromBack-EndOfftoOn".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();
			String whichPage = (String) request.getParameter("whichPage");
			request.setAttribute("whichPage", whichPage);
			ProductVO product = service.findProduct(it_id);
			request.setAttribute("product", product);

			boolean openModal = true;
			request.setAttribute("openModal", openModal);
			request.getRequestDispatcher("/Back-End/ProductSts_Off_EditBackOfficial.jsp").forward(request, response);
		}
		// 跳出燈箱用 還沒修改資料，修改資料部分在RealeditProductFromBackEndOntoOff部分
		if ("editProductFromBack-EndOntoOff".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();
			String whichPage = (String) request.getParameter("whichPage");
			request.setAttribute("whichPage", whichPage);
			ProductVO product = service.findProduct(it_id);
			request.setAttribute("product", product);

			boolean openModal = true;
			request.setAttribute("openModal", openModal);
			request.getRequestDispatcher("/Back-End/ProductSts_On_EditBackOfficial.jsp").forward(request, response);
		}
		if ("LookUpProductFromAppeal".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();
			String whichPage = (String) request.getParameter("whichPage");
			request.setAttribute("whichPage", whichPage);
			ProductVO product = service.findProduct(it_id);
			request.setAttribute("product", product);

			boolean openModal = true;
			request.setAttribute("openModal", openModal);
			request.getRequestDispatcher("/Back-End/ProductAppeal.jsp").forward(request, response);
		}
		
		// 前台商家商品上架轉下架
		if ("EditProductOntoOff".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("下架");
			service.update_STS(product);
			String whichPage = (String) request.getParameter("whichPage");
			// request.getRequestDispatcher("PersonalStoreProductSTS_ON.jsp").forward(request,
			// response);
			response.sendRedirect(request.getContextPath() + "/PersonalStore/PersonalStoreProductSTS_ON.jsp?whichPage=" + whichPage);

		}
		// 後台上架商品區轉下架
		if ("EditProductOntoOffFromBack".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("下架");
			service.update_STS(product);
			String whichPage = (String) request.getParameter("whichPage");
			request.setAttribute("EditProductOntoOffFromBack", product);
			request.getRequestDispatcher("/Back-End/ProductSts_On_EditBackOfficial.jsp?whichPage=" + whichPage).forward(request,
					response);

		}
		// 所有後台商品上架變下架
		if ("EditAllProductFromBackOntoOff".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("下架");
			service.update_STS(product);
			request.setAttribute("productAllOntoOff", product);

			String whichPage = (String) request.getParameter("whichPage");
			request.getRequestDispatcher("/Back-End/ProductALLEditBackOfficial.jsp?whichPage=" + whichPage).forward(request,
					response);

		}
		// 所有後台商品下架變上架
		if ("EditAllProductFromBackOfftoOn".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("上架");
			service.update_STS(product);
			String whichPage = (String) request.getParameter("whichPage");
			request.setAttribute("productAllOfftoOn", product);
			request.getRequestDispatcher("/Back-End/ProductALLEditBackOfficial.jsp?whichPage=" + whichPage).forward(request,
					response);

		}
		// 後台下架商品區轉上架
		if ("EditProductOfftoOnFromBack".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("上架");
			service.update_STS(product);
			request.setAttribute("EditProductOfftoOnFromBack", product);
			String whichPage = (String) request.getParameter("whichPage");
			request.getRequestDispatcher("/Back-End/ProductSts_Off_EditBackOfficial.jsp?whichPage=" + whichPage)
					.forward(request, response);

		}
		// 前台商家商品下架轉上架
		if ("EditProductOfftoOn".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			ProductService service = new ProductService();
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("上架");
			service.update_STS(product);
			String whichPage = (String) request.getParameter("whichPage");
			response.sendRedirect(request.getContextPath() + "/PersonalStore/PersonalStoreProductSTS_OFF.jsp?whichPage=" + whichPage);
		}
		// 前台商家商品下架轉移除
		if ("EditProductOfftoNothing".equals(action)) {
			ProductService service = new ProductService();
			String it_id = (String) request.getParameter("it_id");
			String whichPage = (String) request.getParameter("whichPage");
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("移除");
			service.update_STS(product);
			response.sendRedirect(request.getContextPath() + "/PersonalStore/PersonalStoreProductSTS_OFF.jsp?whichPage=" + whichPage);
		}
		// 後臺所有商品下架轉移除
		if ("EditAllProductToNothingFromBack".equals(action)) {
			ProductService service = new ProductService();
			String it_id = (String) request.getParameter("it_id");
			String whichPage = (String) request.getParameter("whichPage");
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("移除");
			service.update_STS(product);
			request.setAttribute("EditAllProductToNothingFromBack", product);
			request.getRequestDispatcher("/Back-End/ProductALLEditBackOfficial.jsp?whichPage=" + whichPage).forward(request,
					response);
		}		
		//後臺上架商品轉移除
		if ("EditProductOntoNothingFromBack".equals(action)) {
			ProductService service = new ProductService();
			String it_id = (String) request.getParameter("it_id");
			String whichPage = (String) request.getParameter("whichPage");
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("移除");
			service.update_STS(product);
			request.setAttribute("EditProductOntoNothingFromBack", product);
			request.getRequestDispatcher("/Back-End/ProductSts_On_EditBackOfficial.jsp?whichPage=" + whichPage).forward(request,
					response);
		}
		//後臺下架商品轉移除
		if ("EditProductOfftoNothingFromBack".equals(action)) {
			ProductService service = new ProductService();
			String it_id = (String) request.getParameter("it_id");
			String whichPage = (String) request.getParameter("whichPage");
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("移除");
			service.update_STS(product);
			request.setAttribute("EditProductOfftoNothingFromBack", product);
			request.getRequestDispatcher("/Back-End/ProductSts_Off_EditBackOfficial.jsp?whichPage=" + whichPage).forward(request,
					response);
		}
		
		
		// 前台商家商品上架轉移除
		if ("EditProductOntoNothing".equals(action)) {
			String it_id = (String) request.getParameter("it_id");
			String whichPage = (String) request.getParameter("whichPage");
			ProductService service = new ProductService();
			ProductVO product = service.findProduct(it_id);
			product.setIt_sts("移除");
			service.update_STS(product);
			response.sendRedirect(request.getContextPath() + "/PersonalStore/PersonalStoreProductSTS_ON.jsp?whichPage=" + whichPage);

		}

		doGet(request, response);
	}

}
