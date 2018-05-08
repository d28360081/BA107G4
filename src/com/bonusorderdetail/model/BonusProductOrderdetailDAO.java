package com.bonusorderdetail.model;

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



public class BonusProductOrderdetailDAO implements BonusProductOrderdetail_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
		"INSERT INTO Bonus_Product_Order_Detail (bns_rec_id,bns_it_id,it_num) VALUES (?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM Bonus_Product_Order_Detail order by bns_rec_id";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM Bonus_Product_Order_Detail where bns_rec_id = ?";
	private static final String DELETE = 
		"DELETE FROM Bonus_Product_Order_Detail where bns_rec_id = ? AND bns_it_id=?";
//	private static final String UPDATE = 
//		"UPDATE Bonus_Product_Orderdetail set it_num=? where bns_rec_id = ?";

	@Override
	public void insert(BonusProductOrderdetailVO bonusProductOrderdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, bonusProductOrderdetailVO.getBns_rec_id());
			pstmt.setString(2, bonusProductOrderdetailVO.getBns_it_id());
			pstmt.setInt(3, bonusProductOrderdetailVO.getIt_num());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	

	@Override
	public void delete(String bns_rec_id , String bns_it_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bns_rec_id);
			pstmt.setString(2, bns_it_id);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	@Override
	public BonusProductOrderdetailVO findByPrimaryKey(String bns_rec_id) {
		BonusProductOrderdetailVO bonusProductOrderdetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bns_rec_id);

			rs = pstmt.executeQuery();			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				bonusProductOrderdetailVO = new BonusProductOrderdetailVO();		
				bonusProductOrderdetailVO.setBns_rec_id(rs.getString("bns_rec_id"));
				bonusProductOrderdetailVO.setBns_it_id(rs.getString("bns_it_id"));
				bonusProductOrderdetailVO.setIt_num(rs.getInt("it_num"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return bonusProductOrderdetailVO;
	}

	@Override
	public List<BonusProductOrderdetailVO> getAll() {
		List<BonusProductOrderdetailVO> list = new ArrayList<BonusProductOrderdetailVO>();
		BonusProductOrderdetailVO bonusProductOrderdetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				bonusProductOrderdetailVO = new BonusProductOrderdetailVO();
				bonusProductOrderdetailVO.setBns_rec_id(rs.getString("bns_rec_id"));
				bonusProductOrderdetailVO.setBns_it_id(rs.getString("bns_it_id"));
				bonusProductOrderdetailVO.setIt_num(rs.getInt("it_num"));
				
				list.add(bonusProductOrderdetailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}  finally {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}



	@Override
	public void insert2(BonusProductOrderdetailVO bonusProductOrderdetailVO, Connection con) {
		PreparedStatement pstmt = null;
		try {

     		pstmt = con.prepareStatement(INSERT_STMT);
     		pstmt.setString(1, bonusProductOrderdetailVO.getBns_rec_id());
			pstmt.setString(2, bonusProductOrderdetailVO.getBns_it_id());
			pstmt.setInt(3, bonusProductOrderdetailVO.getIt_num());
     		pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}



	

}
