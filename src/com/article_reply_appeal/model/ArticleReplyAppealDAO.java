package com.article_reply_appeal.model;

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

public class ArticleReplyAppealDAO implements ArticleReplyAppealDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO ARTICLE_REPLY_APPEAL (ART_CAPL_ID,RE_ID,MEM_ID,ART_RE_APL_CNT,ART_RE_APL_STS) VALUES ('ACI'||LPAD(to_char(ART_CAPL_ID.NEXTVAL), 3, '0'), ?, ?, ?,'待處理')";
	private static final String GET_ALL_STMT = "SELECT ART_CAPL_ID,RE_ID,MEM_ID,EMP_NO,ART_RE_APL_CNT,ART_RE_APL_STS,TO_CHAR(ART_RE_APL_DATE,'yyyy-mm-dd hh:mm:ss')ART_RE_APL_DATE,ART_RE_RAPL,TO_CHAR(ART_REDATE,'yyyy-mm-dd hh:mm:ss')ART_REDATE FROM ARTICLE_REPLY_APPEAL order by ART_RE_APL_DATE DESC";
	private static final String GET_ONE_STMT = "SELECT ART_CAPL_ID,RE_ID,MEM_ID,EMP_NO,ART_RE_APL_CNT,ART_RE_APL_STS,TO_CHAR(ART_RE_APL_DATE,'yyyy-mm-dd hh:mm:ss')ART_RE_APL_DATE,ART_RE_RAPL,TO_CHAR(ART_REDATE,'yyyy-mm-dd hh:mm:ss')ART_REDATE FROM ARTICLE_REPLY_APPEAL where ART_CAPL_ID = ?";
	private static final String DELETE = "DELETE FROM ARTICLE_REPLY_APPEAL where ART_CAPL_ID = ?";
	private static final String UPDATE = "UPDATE ARTICLE_REPLY_APPEAL set  EMP_NO = ?, ART_RE_APL_STS = ?, ART_RE_RAPL= ?, ART_REDATE=CURRENT_TIMESTAMP where ART_CAPL_ID = ?";
	private static final String GET_STS_STMT = "SELECT ART_CAPL_ID,RE_ID,MEM_ID,EMP_NO,ART_RE_APL_CNT,ART_RE_APL_STS,TO_CHAR(ART_RE_APL_DATE,'yyyy-mm-dd hh:mm:ss')ART_RE_APL_DATE,ART_RE_RAPL,TO_CHAR(ART_REDATE,'yyyy-mm-dd hh:mm:ss')ART_REDATE FROM ARTICLE_REPLY_APPEAL where ART_RE_APL_STS = ? ORDER BY ART_RE_APL_DATE DESC";
	@Override
	public void add(ArticleReplyAppealVO ara) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, ara.getRe_id());
			psmt.setString(2, ara.getMem_id());
			psmt.setString(3, ara.getArt_re_apl_cnt());
			psmt.executeUpdate();
		} catch (SQLException se) {

			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public void update(ArticleReplyAppealVO ara) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, ara.getEmp_no());
			psmt.setString(2, ara.getArt_re_apl_sts());
			psmt.setString(3, ara.getArt_re_rapl());
			psmt.setString(4, ara.getArt_capl_id());
			psmt.executeUpdate();
		} catch (SQLException se) {

			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public void delete(String art_capl_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, art_capl_id);
			psmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public ArticleReplyAppealVO findByPK(String art_capl_id) {
		ArticleReplyAppealVO ara = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ONE_STMT);
			psmt.setString(1, art_capl_id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ara = new ArticleReplyAppealVO();
				ara.setArt_capl_id(rs.getString("art_capl_id"));
				ara.setEmp_no(rs.getString("emp_no"));
				ara.setMem_id(rs.getString("mem_id"));
				ara.setArt_re_apl_cnt(rs.getString("art_re_apl_cnt"));
				ara.setArt_re_apl_sts(rs.getString("art_re_apl_sts"));
				ara.setRe_id(rs.getString("re_id"));
				ara.setArt_re_apl_date(rs.getString("art_re_apl_date"));
				ara.setArt_re_rapl(rs.getString("art_re_rapl"));
				ara.setArt_redate(rs.getString("art_redate"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return ara;
	}

	@Override
	public List<ArticleReplyAppealVO> getAll() {
		List<ArticleReplyAppealVO> list = new ArrayList<ArticleReplyAppealVO>();
		ArticleReplyAppealVO ara = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL_STMT);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ara = new ArticleReplyAppealVO();
				ara.setArt_capl_id(rs.getString("art_capl_id"));
				ara.setEmp_no(rs.getString("emp_no"));
				ara.setMem_id(rs.getString("mem_id"));
				ara.setArt_re_apl_cnt(rs.getString("art_re_apl_cnt"));
				ara.setArt_re_apl_sts(rs.getString("art_re_apl_sts"));
				ara.setRe_id(rs.getString("re_id"));
				ara.setArt_re_apl_date(rs.getString("art_re_apl_date"));
				ara.setArt_re_rapl(rs.getString("art_re_rapl"));
				ara.setArt_redate(rs.getString("art_redate"));
				list.add(ara);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public List<ArticleReplyAppealVO> getSelected(String art_re_apl_sts) {
		
		List<ArticleReplyAppealVO> list = new ArrayList<ArticleReplyAppealVO>();
		ArticleReplyAppealVO ara = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_STS_STMT);
			psmt.setString(1, art_re_apl_sts);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ara = new ArticleReplyAppealVO();
				ara.setArt_capl_id(rs.getString("art_capl_id"));
				ara.setEmp_no(rs.getString("emp_no"));
				ara.setMem_id(rs.getString("mem_id"));
				ara.setArt_re_apl_cnt(rs.getString("art_re_apl_cnt"));
				ara.setArt_re_apl_sts(rs.getString("art_re_apl_sts"));
				ara.setRe_id(rs.getString("re_id"));
				ara.setArt_re_apl_date(rs.getString("art_re_apl_date"));
				ara.setArt_re_rapl(rs.getString("art_re_rapl"));
				ara.setArt_redate(rs.getString("art_redate"));
				list.add(ara);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
