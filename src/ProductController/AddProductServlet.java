package ProductController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Product.model.ProductService;
import com.Product.model.ProductVO;
import com.bonusproduct.model.BonusProductVO;
import com.bonusproduct.model.BonusproService;


@WebServlet("/AddProductServlet")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");		
		request.setCharacterEncoding("utf-8");	
		
			if("AddProduct".equals(action)){
			String st_id = request.getParameter("st_id"); 			
			String it_name2 = request.getParameter("it_name");
			String It_name =new String(it_name2.getBytes("ISO-8859-1"),"UTF-8");			
	        String it_intro2 = request.getParameter("it_intro");        
	    	String it_intro =new String(it_intro2.getBytes("ISO-8859-1"),"UTF-8");
	    
	    	
	    	
	    	String it_cate2= request.getParameter("it_cate");
	    
	    	
	    	String	it_cate =new String(it_cate2.getBytes("ISO-8859-1"),"UTF-8");
	    	
	    
	    	
	    	    Double it_prc = Double.valueOf(request.getParameter("it_prc"));       
		        Integer it_num = Integer.valueOf(request.getParameter("it_num"));	
		     //   byte[] it_pic =request.getParameter("it_pic");	
		        
		        Part it_pic = request.getPart("it_pic");
		        InputStream pic=it_pic.getInputStream();
		        byte[] upload =new byte[pic.available()];
		        pic.read(upload, 0, upload.length);
		        
//		        ServletOutputStream out=response.getOutputStream();
//		        out.write(upload);
				ProductVO product=new ProductVO();
				
				
				product.setSt_id(st_id);
				product.setIt_name(It_name);
				product.setIt_intro(it_intro);
				product.setIt_prc(it_prc);
				product.setIt_num(it_num);
			
				product.setIt_pic(upload);
	    		product.setIt_sts("上架");			
	    		product.setIt_cate(it_cate);
				ProductService service = new ProductService();
				service.insertProduct(product);	
				
				
				
				response.sendRedirect(request.getContextPath()+"/PersonalStore/PersonalStoreProductSTS_ON.jsp");
				}
	    	
			if("AddProductFromBack".equals(action)){
			//	String st_id = request.getParameter("st_id"); 			
				String it_name2 = request.getParameter("it_name");
				String It_name =new String(it_name2.getBytes("ISO-8859-1"),"UTF-8");			
		        String it_intro2 = request.getParameter("it_intro");        
		    	String it_intro =new String(it_intro2.getBytes("ISO-8859-1"),"UTF-8");
		    
		    	
		    	String	it_sts2 = request.getParameter("it_sts");
		    	
		    	String	it_sts =new String(it_sts2.getBytes("ISO-8859-1"),"UTF-8");
		    	String it_cate2= request.getParameter("it_cate");
		    
		    	
		    	String	it_cate =new String(it_cate2.getBytes("ISO-8859-1"),"UTF-8");
		    	
		    
		    	
		    	    Double it_prc = Double.valueOf(request.getParameter("it_prc"));       
			        Integer it_num = Integer.valueOf(request.getParameter("it_num"));	
			       // byte[] it_pic =request.getParameter("it_pic");	        
			      
			        Part it_pic = request.getPart("it_pic");
			        InputStream pic=it_pic.getInputStream();
			        byte[] upload =new byte[pic.available()];
			        pic.read(upload, 0, upload.length);
			        
			        if("BonusProduct".equals(it_cate)){
			    		Part bns_pic = request.getPart("it_pic");
			    		System.out.println(bns_pic);
			    		byte[] bns_it_pic = null;
			    		InputStream in = bns_pic.getInputStream();
			    		bns_it_pic = new byte[in.available()];
						in.read(bns_it_pic);
						in.close();
			    		Integer bns_it_prc = Integer.valueOf(request.getParameter("it_prc"));
			    		//////////session get empno////////////////
			    		String emp_no ="EMP001";
			    		///////////////////////////////////////////
			    		BonusproService bnsSrc = new BonusproService();
			    		BonusProductVO bnsVo=bnsSrc.addbns(emp_no, It_name, it_intro, bns_it_pic, bns_it_prc, it_num, it_sts, 0);
			    		request.setAttribute("bnsVo", bnsVo);
			    		request.getRequestDispatcher("/Back-End/AddProductBack.jsp").forward(request, response);
			    		return;
			    	}else{
					ProductVO product=new ProductVO();
					
					//book.setPicture(picture);
					//官方商品 商家id一律為ST001
					product.setSt_id("ST001");
					product.setIt_name(It_name);
					product.setIt_intro(it_intro);
					product.setIt_prc(it_prc);
					product.setIt_num(it_num);				
					product.setIt_pic(upload);
		    		product.setIt_sts(it_sts);	
		    		
		    		product.setIt_cate(it_cate);
					ProductService service = new ProductService();
					service.insertProduct(product);	
					request.setAttribute("product", product);
					request.getRequestDispatcher("/Back-End/AddProductBack.jsp").forward(request, response);
			//		response.sendRedirect(request.getContextPath()+"/Back-End/AddProductBack.jsp");
			    	}
			}
			        
		}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
