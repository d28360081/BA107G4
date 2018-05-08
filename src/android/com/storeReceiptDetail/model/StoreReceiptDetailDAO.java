package android.com.storeReceiptDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.storeorder.model.StoreOrderVO;





public class StoreReceiptDetailDAO implements StoreReceiptDetail_interface {
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
			"insert into Store_Receipt_Detail (rec_id,it_id,it_num)values(?,?,?)";	//�ק�
	private static final String UPDATE = 
			"update Store_Receipt_Detail set rec_id=? where it_id = ?";	
	private static final String DELETE = 
			"delete * from Store_Receipt_Detail where rec_id=?";
	private static final String GET_ONE_STMT = 
			"select * from Store_Receipt_Detail where rec_id = ? ";	//�d�ߥ���
	private static final String GET_ALL_BY_REC_ID = 
			"select * from Store_Receipt_Detail where rec_id = ? ";	//�d�ߥ���
	private static final String GET_ALL_STMT = 
			"select * from Store_Receipt_Detail order by rec_id";
	private static final String GET_STORE_ORDER_BY_REC_ID = 
			"select * from store_order where REC_ID=?  order by rec_id";
	@Override
	public void insertStoreReceiptDetail(StoreReceiptDetailVO storeReceiptDetailVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			try {
				
				con = ds.getConnection();		
			}  catch (SQLException e1) {
				e1.printStackTrace();
			}
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, storeReceiptDetailVO.getRec_id());
			pstmt.setString(2, storeReceiptDetailVO.getIt_id());
			pstmt.setInt(3, storeReceiptDetailVO.getIt_num());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			 if(con != null) {
			        try {
			        	con.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
			 throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void insertStoreReceiptDetail2(StoreReceiptDetailVO storeReceiptDetailVO, Connection con) {

		PreparedStatement pstmt=null;
		try {
			
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, storeReceiptDetailVO.getRec_id());
			pstmt.setString(2, storeReceiptDetailVO.getIt_id());
			pstmt.setInt(3, storeReceiptDetailVO.getIt_num());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			 if(con != null) {
			        try {			        	
			        	System.err.print("Transaction is being ");
						System.err.println("rolled back-由-StoreReceiptDetail");
						con.rollback(); 
			        }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			            throw new RuntimeException("rollback error occured. "
								+ ex.getMessage());
			        }
			    }
			
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		
	}
	
}
