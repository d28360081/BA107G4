package android.com.storeorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.storeReceiptDetail.model.StoreReceiptDetailDAO;
import android.com.storeReceiptDetail.model.StoreReceiptDetailVO;






public class StoreOrderDAO implements StoreOrder_interface {
	private static DataSource ds;
	static{
		try {
			 Context ctx = new InitialContext();
			 ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static final String INSERT_STMT = 
		"INSERT INTO Store_Order (rec_id,mem_id,st_id,buyer_name,mem_email,rec_delivery,payment,rec_py,rec_dlv_sts,mem_add,mem_ph,usd_bns,dlv_id) VALUES ('REC'||LPAD(to_char(STORE_ORDER_SEQ.NEXTVAL), 3, '0'),?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM Store_Order order by rec_id DESC";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM Store_Order where rec_id = ?";
	private static final String DELETE = 
		"DELETE FROM Store_Order where rec_id = ?";
	private static final String UPDATE = 
		"UPDATE Store_Order set buyer_name=? ,mem_email=? ,rec_delivery=?  ,payment=?, rec_py=?, rec_dlv_sts=?, mem_add=?, mem_ph=? ,usd_bns=? ,dlv_id=? where rec_id = ?";
	@Override
	public void insertStoreOrderWithStoreReceiptDetail(StoreOrderVO storeOrderVO, List<StoreReceiptDetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			try {
				
			
				con = ds.getConnection();		
			}  catch (SQLException e1) {
				e1.printStackTrace();
			}
			con.setAutoCommit(false);
			String cols[] = {"rec_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);		
			
			pstmt.setString(1,storeOrderVO.getMem_id());
			pstmt.setString(2,storeOrderVO.getSt_id());
			
			pstmt.setString(3,storeOrderVO.getBuyer_name());
			pstmt.setString(4,storeOrderVO.getMem_email());			
			pstmt.setString(5,storeOrderVO.getRec_delivery());
			pstmt.setDouble(6, storeOrderVO.getPayment());
			pstmt.setString(7, storeOrderVO.getRec_py());			
			pstmt.setString(8, storeOrderVO.getRec_dlv_sts());
			pstmt.setString(9, storeOrderVO.getMem_add());
			pstmt.setString(	10, storeOrderVO.getMem_ph());		
			pstmt.setInt(	11,storeOrderVO.getUsd_bns());
			pstmt.setString(12,storeOrderVO.getDlv_id());
			
			pstmt.executeUpdate();
			
			String next_rec_id= null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_rec_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_rec_id +"(剛新增成功的編號)");
			}
			else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			
			StoreReceiptDetailDAO dao=new StoreReceiptDetailDAO();
			for (StoreReceiptDetailVO aStoreReceiptDetail : list) {
				aStoreReceiptDetail.setRec_id(new String(next_rec_id));
				dao.insertStoreReceiptDetail2(aStoreReceiptDetail,con);			
			}
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增編號" + next_rec_id + "時,共有" + list.size()
					+ "同時被新增");
			
			// Handle any SQL errors
		} catch (SQLException se) {
			 if(con != null) {
			        try {
			        	con.rollback();
			        	System.err.print("Transaction is being ");
						System.err.println("rolled back-由-storeOrder");
			        	}
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
			 throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}
	
}


