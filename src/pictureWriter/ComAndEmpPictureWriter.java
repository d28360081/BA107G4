package pictureWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComAndEmpPictureWriter
 */
@WebServlet("/ComAndEmpPictureWriter")
public class ComAndEmpPictureWriter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComAndEmpPictureWriter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BA107G4","123");
		PreparedStatement prst=c.prepareStatement("UPDATE COMMISION SET COM_PIC1=? , COM_PIC2=? WHERE COM_ID=?");
		
		String path="http://localhost:8081/BA107G4/WebContent/img/Com_pic";
		
		System.out.println(path);
		File f=new File(path);
	    String[] files=f.list();
	    System.out.println(files);
	    int count =0;
	    for(String d:files){
	    	
	    	StringBuffer sb=new StringBuffer();
	    	sb.append(path);
	    	sb.append("/"+d);
	    	
	    	File f2=new File(sb.toString());
	    	String[] pics=f2.list();
	    	for(int i=0;i<pics.length;i++){
	    		
	    		String picName=pics[i];
	    		File pic=new File(sb.toString()+"/"+picName);
	    		
	    		FileInputStream fop=new FileInputStream(pic);
	    		prst.setBinaryStream(i+1, fop, fop.available());
	    		
	    		
	    	}
	    	
	    	prst.setString(3, files[count]);
	    
	    	prst.executeUpdate();
	    	count++;
	    	}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
