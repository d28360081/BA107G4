package com.bonusproduct.model;

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

public class BonusProductDAO implements BonusProduct_interface {
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
		"INSERT INTO BONUS_PRODUCT  (bns_it_id,emp_no,bns_it_name,bns_it_intro,bns_in_pic,bns_it_prc,bns_it_strg,bns_it_sts,bns_it_cash) VALUES ('BP'||LPAD(to_char(BNS_PRD_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM BONUS_PRODUCT where bns_it_sts='上架' order by bns_it_id DESC";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM BONUS_PRODUCT  where bns_it_sts='上架' and bns_it_id = ?";
	private static final String DELETE = 
		"DELETE FROM BONUS_PRODUCT  where bns_it_sts='上架' and bns_it_id = ?";
	private static final String UPDATE = 
		"UPDATE BONUS_PRODUCT  set bns_it_name=?, bns_it_intro=?, bns_in_pic=?, bns_it_prc=?,bns_it_strg=?,bns_it_sts=?,bns_it_cash=? where bns_it_id = ?";
	private static final String UPDATE_STS = 
			"UPDATE BONUS_PRODUCT  set bns_it_sts=? where bns_it_id = ?";

	@Override
	public void insert(BonusProductVO bonusProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
							
			pstmt.setString(1, bonusProductVO.getEmp_no());
			pstmt.setString(2, bonusProductVO.getBns_it_name());			
			pstmt.setString(3, bonusProductVO.getBns_it_intro());
			pstmt.setBytes(4, bonusProductVO.getBns_in_pic());
			pstmt.setInt(5, bonusProductVO.getBns_it_prc());
			pstmt.setInt(6, bonusProductVO.getBns_it_strg());
			pstmt.setString(7, bonusProductVO.getBns_it_sts());
			pstmt.setInt(8, bonusProductVO.getBns_it_cash());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}  finally {
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
	public void update(BonusProductVO bonusProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			
			
					
			pstmt.setString(1,bonusProductVO.getBns_it_name());
			pstmt.setString(2, bonusProductVO.getBns_it_intro());
			pstmt.setBytes(3, bonusProductVO.getBns_in_pic());
			pstmt.setInt(4, bonusProductVO.getBns_it_prc());
			pstmt.setInt(5, bonusProductVO.getBns_it_strg());
			pstmt.setString(6, bonusProductVO.getBns_it_sts());
			pstmt.setInt(7, bonusProductVO.getBns_it_cash());
			pstmt.setString(8, bonusProductVO.getBns_it_id());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}  finally {
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
	public void delete(String bns_it_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bns_it_id);

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
	public BonusProductVO findByPrimaryKey(String bns_it_id) {
		BonusProductVO bonusProductVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bns_it_id);

			rs = pstmt.executeQuery();			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				bonusProductVO = new BonusProductVO();		
				bonusProductVO.setBns_it_id(rs.getString("bns_it_id"));
				bonusProductVO.setEmp_no(rs.getString("emp_no"));
				bonusProductVO.setBns_it_name(rs.getString("bns_it_name"));
				bonusProductVO.setBns_it_intro(rs.getString("bns_it_intro"));
				bonusProductVO.setBns_in_pic(rs.getBytes("bns_in_pic"));
				bonusProductVO.setBns_it_prc(rs.getInt("bns_it_prc"));
				bonusProductVO.setBns_it_strg(rs.getInt("bns_it_strg"));
				bonusProductVO.setBns_it_sts(rs.getString("bns_it_sts"));
				bonusProductVO.setBns_it_cash(rs.getInt("bns_it_cash"));
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
		return bonusProductVO;
	}

	@Override
	public List<BonusProductVO> getAll() {
		List<BonusProductVO> list = new ArrayList<BonusProductVO>();
		BonusProductVO bonusProductVO=null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();	
		
			while (rs.next()) {
				// empVO �]�٬� Domain objects				
				bonusProductVO = new BonusProductVO();		
				bonusProductVO.setBns_it_id(rs.getString("bns_it_id"));
				bonusProductVO.setEmp_no(rs.getString("emp_no"));
				bonusProductVO.setBns_it_name(rs.getString("bns_it_name"));
				bonusProductVO.setBns_it_intro(rs.getString("bns_it_intro"));			
				bonusProductVO.setBns_in_pic(rs.getBytes("bns_in_pic"));			
				bonusProductVO.setBns_it_prc(rs.getInt("bns_it_prc"));				
				bonusProductVO.setBns_it_strg(rs.getInt("bns_it_strg"));		
				bonusProductVO.setBns_it_sts(rs.getString("bns_it_sts"));
				bonusProductVO.setBns_it_cash(rs.getInt("bns_it_cash"));
				
				list.add(bonusProductVO); // Store the row in the list
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
	public void updateSts(BonusProductVO bonusProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STS);
			pstmt.setString(1, bonusProductVO.getBns_it_sts());
			pstmt.setString(2, bonusProductVO.getBns_it_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}  finally {
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
