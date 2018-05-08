package com.productFAQ.model;

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

public class ProFaqDAO implements ProFaqVO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT=
			"INSERT INTO PRODUCTFAQ (faq_id,it_id,mem_id,faq_cnt) VALUES ('FAQ'||LPAD(TO_CHAR(FAQ_ID_SEQ.NEXTVAL),3,'0'),?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT * FROM PRODUCTFAQ ORDER BY faq_id DESC";
	private static final String GET_ONE_STMT=
			"SELECT faq_id,it_id,mem_id,faq_cnt,to_char(faq_date,'yyyy-mm-dd hh24:mm:ss')faq_date FROM PRODUCTFAQ WHERE faq_id=?";
	private static final String DELETE=
			"DELETE FROM PRODUCTFAQ WHERE faq_id=?";
	private static final String UPDATE=
			"UPDATE PRODUCTFAQ set faq_cnt=?,faq_date=? WHERE faq_id=?";
	
	@Override
	public void insert(ProFaqVO proFaqVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, proFaqVO.getIt_id());
			pstmt.setString(2, proFaqVO.getMem_id());
			pstmt.setString(3, proFaqVO.getFaq_cnt());
			
			pstmt.executeUpdate();
		}  catch (SQLException se) {
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
	public void update(ProFaqVO proFaqVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, proFaqVO.getFaq_cnt());
			pstmt.setTimestamp(2, proFaqVO.getFaq_date());
			pstmt.setString(3, proFaqVO.getFaq_id());
			pstmt.executeUpdate();
		}  catch (SQLException se) {
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
	public void delete(String faq_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, faq_id);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public ProFaqVO findByPrimaryKey(String faq_id) {
		ProFaqVO proFaqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, faq_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				proFaqVO = new ProFaqVO();
				proFaqVO.setFaq_id(rs.getString("faq_id"));
				proFaqVO.setIt_id(rs.getString("it_id"));
				proFaqVO.setMem_id(rs.getString("mem_id"));
				proFaqVO.setFaq_cnt(rs.getString("faq_cnt"));
				proFaqVO.setFaq_date(rs.getTimestamp("faq_date"));
			}
			// Handle any driver errors
		} 
			// Handle any SQL errors
		catch (SQLException se) {
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
		return proFaqVO;
	}

	@Override
	public List<ProFaqVO> getAll() {
		List<ProFaqVO> list = new ArrayList<ProFaqVO>();
		ProFaqVO proFaqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				proFaqVO = new ProFaqVO();
				proFaqVO.setFaq_id(rs.getString("faq_id"));
				proFaqVO.setIt_id(rs.getString("it_id"));
				proFaqVO.setMem_id(rs.getString("mem_id"));
				proFaqVO.setFaq_cnt(rs.getString("faq_cnt"));
				proFaqVO.setFaq_date(rs.getTimestamp("faq_date"));
				list.add(proFaqVO);
			}
			// Handle any driver errors
		} 
			// Handle any SQL errors
		  catch (SQLException se) {
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
		return list;
	}
}
