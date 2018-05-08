package PersonalStoreController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.PersonalStore.model.PersonalStoreService;
import com.PersonalStore.model.PersonalStoreVO;


@WebServlet("/PersonalStoreController")
public class PersonalStoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		request.setCharacterEncoding("utf-8");
		if("AddPersonalStore".equals(action)){			
			String mem_id=request.getParameter("mem_id");
			
			String st_name2=request.getParameter("st_name");			
			String st_intro2=request.getParameter("st_intro");
			
			
			String st_name =new String(st_name2.getBytes("ISO-8859-1"),"UTF-8");	
			String st_intro =new String(st_intro2.getBytes("ISO-8859-1"),"UTF-8");	
		
			PersonalStoreVO personalStore=new PersonalStoreVO();
			personalStore.setMem_id(mem_id);	
			personalStore.setSt_name(st_name);
			personalStore.setSt_intro(st_intro);
			personalStore.setSt_sts("開業");
			PersonalStoreService personalStoreService=new PersonalStoreService();
			personalStoreService.insert(personalStore);
		//request.getServletContext().setAttribute("personalStore", personalStore);
			request.setAttribute("personalStore", personalStore);
			request.getRequestDispatcher("/PersonalStore/PersonalStore.jsp").forward(request, response);
		}
		if("EditPersonalStore".equals(action)){
			String st_name2=request.getParameter("st_name");			
			String st_intro2=request.getParameter("st_intro");
			String st_id2=request.getParameter("st_id");
			
			String st_name =new String(st_name2.getBytes("ISO-8859-1"),"UTF-8");	
			String st_intro =new String(st_intro2.getBytes("ISO-8859-1"),"UTF-8");	
			String st_id =new String(st_id2.getBytes("ISO-8859-1"),"UTF-8");	
			PersonalStoreVO personalStore=new PersonalStoreVO();
			personalStore.setSt_id(st_id);
			personalStore.setSt_name(st_name);
			personalStore.setSt_intro(st_intro);
			personalStore.setSt_sts("開業");
			PersonalStoreService personalStoreService=new PersonalStoreService();
			personalStoreService.update(personalStore);
			request.setAttribute("EditStore", "EditStore");
			request.getRequestDispatcher("/PersonalStore/PersonalStore.jsp").forward(request, response);
		}
		if("LookUpRevenue".equals(action)){
			
			
		
			request.getRequestDispatcher("/PersonalStore/PersonalStore.jsp").forward(request, response);
		}
		
		
		
		
	}

}
