package com.article_appeal.model;

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



public class ArticleAppealDAO implements ArticleAppealDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO ARTICLE_APPEAL (ART_APL_ID,ART_ID,MEM_ID,ART_APL_CNT,ART_APL_STS) VALUES ('AAI'||LPAD(to_char(ART_APL_ID.NEXTVAL), 3, '0'), ?, ?, ?, '待處理')";
	private static final String GET_ALL_STMT = "SELECT ART_APL_ID,ART_ID,MEM_ID,EMP_NO,ART_APL_CNT,ART_APL_STS,TO_CHAR(ART_APL_DATE,'yyyy-mm-dd hh:mm:ss')ART_APL_DATE,ART_RAPL,TO_CHAR(ART_RAPLDATE,'yyyy-mm-dd hh:mm')ART_RAPLDATE FROM ARTICLE_APPEAL ORDER BY ART_APL_DATE DESC";
	private static final String GET_ONE_STMT = "SELECT ART_APL_ID,ART_ID,MEM_ID,EMP_NO,ART_APL_CNT,ART_APL_STS,TO_CHAR(ART_APL_DATE,'yyyy-mm-dd hh:mm:ss')ART_APL_DATE,ART_RAPL,TO_CHAR(ART_RAPLDATE,'yyyy-mm-dd hh:mm')ART_RAPLDATE FROM ARTICLE_APPEAL where ART_APL_ID = ?";
	private static final String DELETE = "DELETE FROM ARTICLE_APPEAL where ART_APL_ID = ?";
	private static final String UPDATE = "UPDATE ARTICLE_APPEAL set EMP_NO=?, ART_APL_STS=?, ART_RAPL=?, ART_RAPLDATE=CURRENT_TIMESTAMP where ART_APL_ID = ?";
	private static final String GET_SELECTED_STMT = "SELECT ART_APL_ID,ART_ID,MEM_ID,EMP_NO,ART_APL_CNT,ART_APL_STS,TO_CHAR(ART_APL_DATE,'yyyy-mm-dd hh:mm:ss')ART_APL_DATE,ART_RAPL,TO_CHAR(ART_RAPLDATE,'yyyy-mm-dd hh:mm')ART_RAPLDATE FROM ARTICLE_APPEAL where ART_APL_STS = ? ORDER BY ART_APL_DATE DESC";
		
	public void add(ArticleAppealVO aav){
		
		Connection con = null;
		PreparedStatement psmt = null;
		
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, aav.getArt_id());
			psmt.setString(2, aav.getMem_id());
			psmt.setString(3, aav.getArt_apl_cnt());

			psmt.executeUpdate();
			
		} catch (SQLException se) {
			
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
	public void update(ArticleAppealVO aav)	{
		Connection con = null;
		PreparedStatement psmt = null;
		
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE);
			
			psmt.setString(1, aav.getEmp_no());
			psmt.setString(2, aav.getArt_apl_sts());
			psmt.setString(3, aav.getArt_rapl());
			psmt.setString(4, aav.getArt_apl_id());
			
			psmt.executeUpdate();
			
		} catch (SQLException se) {
			
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
	public void delete(String art_apl_id){
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, art_apl_id);
			psmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
	public ArticleAppealVO findByPK(String art_apl_id){
		
		ArticleAppealVO aav =null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ONE_STMT);

			psmt.setString(1, art_apl_id);

			rs = psmt.executeQuery();

			while (rs.next()) {
				
				aav = new ArticleAppealVO();
				aav.setArt_apl_id(rs.getString("art_apl_id"));
				aav.setArt_id(rs.getString("art_id"));
				aav.setMem_id(rs.getString("mem_id"));
				aav.setEmp_no(rs.getString("emp_no"));
				aav.setArt_apl_cnt(rs.getString("art_apl_cnt"));
				aav.setArt_apl_sts(rs.getString("art_apl_sts"));
				aav.setArt_apl_date(rs.getString("art_apl_date"));
				aav.setArt_rapl(rs.getString("art_rapl"));
				aav.setArt_rapldate(rs.getString("art_rapldate"));
	
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
			if (psmt != null) {
				try {
					psmt.close();
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
		return aav;
	}
	public List<ArticleAppealVO> getAll(){
		List<ArticleAppealVO> list = new ArrayList<ArticleAppealVO>();
		ArticleAppealVO aav = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL_STMT);
			rs = psmt.executeQuery();

			while (rs.next()) {
				aav = new ArticleAppealVO();
				aav.setArt_apl_id(rs.getString("art_apl_id"));
				aav.setArt_id(rs.getString("art_id"));
				aav.setMem_id(rs.getString("mem_id"));
				aav.setEmp_no(rs.getString("emp_no"));
				aav.setArt_apl_cnt(rs.getString("art_apl_cnt"));
				aav.setArt_apl_sts(rs.getString("art_apl_sts"));
				aav.setArt_apl_date(rs.getString("art_apl_date"));
				aav.setArt_rapl(rs.getString("art_rapl"));
				aav.setArt_rapldate(rs.getString("art_rapldate"));
				list.add(aav);
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
			if (psmt != null) {
				try {
					psmt.close();
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
	public List<ArticleAppealVO> getSelected(String art_apl_sts){
		List<ArticleAppealVO> list = new ArrayList<ArticleAppealVO>();
		ArticleAppealVO aav = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(GET_SELECTED_STMT);
			psmt.setString(1, art_apl_sts);
			rs = psmt.executeQuery();

			while (rs.next()) {
				aav = new ArticleAppealVO();
				aav.setArt_apl_id(rs.getString("art_apl_id"));
				aav.setArt_id(rs.getString("art_id"));
				aav.setMem_id(rs.getString("mem_id"));
				aav.setEmp_no(rs.getString("emp_no"));
				aav.setArt_apl_cnt(rs.getString("art_apl_cnt"));
				aav.setArt_apl_sts(rs.getString("art_apl_sts"));
				aav.setArt_apl_date(rs.getString("art_apl_date"));
				aav.setArt_rapl(rs.getString("art_rapl"));
				aav.setArt_rapldate(rs.getString("art_rapldate"));
				list.add(aav);
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
			if (psmt != null) {
				try {
					psmt.close();
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
		
