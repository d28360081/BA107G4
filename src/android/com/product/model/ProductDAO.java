package android.com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import android.com.com.model.ComVO;
import android.com.main.ImageUtil;

public class ProductDAO implements Product_interface{
	private static DataSource ds=null;
	static {
			  try {
			   Context ctx = new InitialContext();
			   ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			  } catch (NamingException e) {
			   e.printStackTrace();
			  }
	 }
	 static final String FIND_GOGOSHOP_PRODUCT= "SELECT * FROM PRODUCT WHERE ST_ID='ST001' AND IT_STS='上架'";
	 static final String FIND_GOGOSHOP_PRODUCT_P_DESC= "SELECT * FROM PRODUCT WHERE ST_ID='ST001' AND IT_STS='上架' ORDER BY IT_PRC DESC";
	 static final String FIND_GOGOSHOP_PRODUCT_P= "SELECT * FROM PRODUCT WHERE ST_ID='ST001' AND IT_STS='上架' ORDER BY IT_PRC";
	 private static final String GET_ONE_STMT = "SELECT * FROM Product where it_id = ?";
	@Override
	public List<ProductVO> selectAllgogoshop() {
		List<ProductVO> proList = new ArrayList<>();
		ProductVO productvo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_GOGOSHOP_PRODUCT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productvo = new ProductVO();
				productvo.setIt_id(rs.getString(1));
				productvo.setSt_id(rs.getString(2));
				productvo.setIt_num(rs.getInt(3));
				productvo.setIt_name(rs.getString(4));
				productvo.setIt_intro(rs.getString(5));
				productvo.setIt_pic(ImageUtil.shrink(rs.getBytes(6), 300));
				productvo.setIt_prc(rs.getDouble(7));
				productvo.setIt_cate(rs.getString(9));
			
				proList.add(productvo);

			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return proList;
	}
	@Override
	public ProductVO findByPrimaryKey(String it_id) {

		ProductVO productVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {			

			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, it_id);
				
			rs = pstmt.executeQuery();			
			while (rs.next()) {
				
				productVO = new ProductVO();	
				
				productVO.setIt_id(rs.getString("it_id"));
				productVO.setSt_id(rs.getString("st_id"));
				productVO.setIt_num(rs.getInt("it_num"));
				productVO.setIt_name(rs.getString("it_name"));
				productVO.setIt_intro(rs.getString("it_intro"));
				
				
				productVO.setIt_pic(rs.getBytes("it_pic"));
				productVO.setIt_prc(rs.getDouble("it_prc"));
				productVO.setIt_sts(rs.getString("it_sts"));
				productVO.setIt_cate(rs.getString("it_cate"));
				
				
				
				
				
			}
			conn.commit();
			conn.setAutoCommit(true);
			// Handle any driver errors
		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
			 throw new RuntimeException("A database error occured. " + se.getMessage());
		
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return productVO;
	}
	@Override
	public List<ProductVO> selectAllgogoshopOrderByPrice() {
		List<ProductVO> proList = new ArrayList<>();
		ProductVO productvo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_GOGOSHOP_PRODUCT_P);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productvo = new ProductVO();
				productvo.setIt_id(rs.getString(1));
				productvo.setSt_id(rs.getString(2));
				productvo.setIt_num(rs.getInt(3));
				productvo.setIt_name(rs.getString(4));
				productvo.setIt_intro(rs.getString(5));
				productvo.setIt_pic(ImageUtil.shrink(rs.getBytes(6), 300));
				productvo.setIt_prc(rs.getDouble(7));
				productvo.setIt_cate(rs.getString(9));
			
				proList.add(productvo);

			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return proList;
	}
	@Override
	public List<ProductVO> selectAllgogoshopOrderByPriceDESC() {
		List<ProductVO> proList = new ArrayList<>();
		ProductVO productvo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_GOGOSHOP_PRODUCT_P_DESC);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productvo = new ProductVO();
				productvo.setIt_id(rs.getString(1));
				productvo.setSt_id(rs.getString(2));
				productvo.setIt_num(rs.getInt(3));
				productvo.setIt_name(rs.getString(4));
				productvo.setIt_intro(rs.getString(5));
				productvo.setIt_pic(ImageUtil.shrink(rs.getBytes(6), 300));
				productvo.setIt_prc(rs.getDouble(7));
				productvo.setIt_cate(rs.getString(9));
			
				proList.add(productvo);

			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return proList;
	}


}
