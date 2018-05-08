package com.article_reply_appeal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleReplyAppealJDBCDAO implements ArticleReplyAppealDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";
	
	private static final String INSERT_STMT = "INSERT INTO ARTICLE_REPLY_APPEAL (ART_CAPL_ID,RE_ID,MEM_ID,ART_RE_APL_CNT,ART_RE_APL_STS) VALUES ('ACI'||LPAD(to_char(ART_CAPL_ID.NEXTVAL), 3, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM ARTICLE_REPLY_APPEAL order by ART_CAPL_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM ARTICLE_REPLY_APPEAL where ART_CAPL_ID = ?";
	private static final String DELETE = "DELETE FROM ARTICLE_REPLY_APPEAL where ART_CAPL_ID = ?";
	private static final String UPDATE = "UPDATE ARTICLE_REPLY_APPEAL set  EMP_NO = ?, ART_RE_APL_STS = ?, ART_RE_RAPL= ?, ART_REDATE=CURRENT_TIMESTAMP where ART_CAPL_ID = ?";

	
	@Override
	public void add(ArticleReplyAppealVO ara) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, ara.getRe_id());
			psmt.setString(2, ara.getMem_id());
			psmt.setString(3, ara.getArt_re_apl_cnt());
			psmt.setString(4, ara.getArt_re_apl_sts());
			psmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, ara.getEmp_no());
			psmt.setString(2, ara.getArt_re_apl_sts());
			psmt.setString(3, ara.getArt_re_rapl());
			psmt.setString(4, ara.getArt_capl_id());
			psmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, art_capl_id);
			psmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		List<ArticleReplyAppealVO> list = new ArrayList<ArticleReplyAppealVO>();
		ArticleReplyAppealVO ara = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	public static void main(String[] args) {

		ArticleReplyAppealJDBCDAO dao = new ArticleReplyAppealJDBCDAO();

		// �憓�
//		ArticleReplyAppealVO ara = new ArticleReplyAppealVO();
//		ara.setRe_id("ARTRE200");
//		ara.setMem_id("M000005");
//		ara.setArt_re_apl_cnt("皜祈岫JDBC4894****");
//		ara.setArt_re_apl_sts("敺���");
//		dao.add(ara);


		// 靽格
		ArticleReplyAppealVO ara = new ArticleReplyAppealVO();
		ara.setEmp_no("EMP001");
		ara.setArt_re_apl_sts("瑼Ｚ����");
		ara.setArt_capl_id("ACI005");
		ara.setArt_re_rapl("銝�閮�");
		dao.update(ara);

		// ��
//		dao.delete("ACI007");

		// �閰�
//		ArticleReplyAppealVO ara = dao.findByPK("ACI002");
//		System.out.println(ara.getArt_capl_id() + ",");
//	    System.out.print(ara.getRe_id() + ",");
//		System.out.print(ara.getMem_id() + ",");		
//		System.out.print(ara.getEmp_no() + ",");		
//		System.out.print(ara.getArt_re_apl_cnt() + ",");	
//		System.out.print(ara.getArt_re_apl_sts() + ",");
//		System.out.print(ara.getArt_re_apl_date() + ",");
//		System.out.print(ara.getArt_re_rapl() + ",");	
//		System.out.print(ara.getArt_redate() + ",");	
		
//
//		// �閰�
//		List<ArticleReplyAppealVO> list = dao.getAll();
//		for (ArticleReplyAppealVO ara : list) {
//			System.out.print(ara.getArt_capl_id() + ",");
//		    System.out.print(ara.getRe_id() + ",");
//			System.out.print(ara.getMem_id() + ",");		
//			System.out.print(ara.getEmp_no() + ",");		
//			System.out.print(ara.getArt_re_apl_cnt() + ",");	
//			System.out.print(ara.getArt_re_apl_sts() + ",");
//			System.out.print(ara.getArt_re_apl_date() + ",");	
//			System.out.print(ara.getArt_re_rapl() + ",");	
//			System.out.print(ara.getArt_redate() + ",");	
//			System.out.println();
//		}
		
	}


	@Override
	public List<ArticleReplyAppealVO> getSelected(String art_re_apl_sts) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
