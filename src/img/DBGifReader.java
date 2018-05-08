package img;



import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBGifReader extends HttpServlet {
	private static DataSource ds = null; 
	static{
		try{
			Context ctx = new InitialContext();
			ds =(DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	final static String PMT_SQL = "SELECT PMT_PIC FROM PROMOTION WHERE PMT_ID=?";
	final static String PRODUCT_SQL = "SELECT IT_PIC FROM PRODUCT WHERE IT_ID=?";
//	final static String MIS_SQL = "";
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();		

		try {
			String pmt_id = req.getParameter("pmt_id");
			String it_id = req.getParameter("it_id");

			System.out.println("pmt_id = " + pmt_id);//測試
			System.out.println("it_id = " + it_id);//測試
			con = ds.getConnection();

			
			String table = req.getParameter("table");
			
			switch(table){
			case "promotion" :
				pstmt = con.prepareStatement(PMT_SQL);
				pstmt.setString(1, pmt_id);
				break;
			case "product":
				pstmt = con.prepareStatement(PRODUCT_SQL);
				pstmt.setString(1, it_id);
				break;
		}
			
//			if("promotion".equals(table)){//表格名稱！
//				pstmt = con.prepareStatement(PMT_SQL);
//				pstmt.setString(1, pmt_id);
//			}else if("product".equals(table)){
//				pstmt = con.prepareStatement(PRODUCT_SQL);
//				pstmt.setString(1, it_id);
//			}
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				BufferedInputStream in = null;
				if("promotion".equals(table)){//表格名稱！
					in = new BufferedInputStream(rs.getBinaryStream("PMT_PIC"));
				}else if("product".equals(table)){
					in = new BufferedInputStream(rs.getBinaryStream("IT_PIC"));					
				}
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream in =getServletContext().getResourceAsStream("/NoData/no.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
		} catch (Exception e) {
			InputStream in =getServletContext().getResourceAsStream("/NoData/null.png");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		} finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

}