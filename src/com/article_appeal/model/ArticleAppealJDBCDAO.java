package com.article_appeal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleAppealJDBCDAO implements ArticleAppealDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";

	private static final String INSERT_STMT = "INSERT INTO ARTICLE_APPEAL (ART_APL_ID,ART_ID,MEM_ID,ART_APL_CNT,ART_APL_STS) VALUES ('AAI'||LPAD(to_char(ART_APL_ID.NEXTVAL), 3, '0'), ?, ?, ?, '待處理')";
	private static final String GET_ALL_STMT = "SELECT * FROM ARTICLE_APPEAL order by ART_APL_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM ARTICLE_APPEAL where ART_APL_ID = ?";
	private static final String DELETE = "DELETE FROM ARTICLE_APPEAL where ART_APL_ID = ?";
	private static final String UPDATE = "UPDATE ARTICLE_APPEAL set EMP_NO=?, ART_APL_STS=?, ART_RAPL=?, ART_RAPLDATE=CURRENT_TIMESTAMP where ART_APL_ID = ?";
	@Override
	public void add(ArticleAppealVO aav) {

		Connection con = null;
		PreparedStatement psmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, aav.getArt_id());
			psmt.setString(2, aav.getMem_id());
			psmt.setString(3, aav.getArt_apl_cnt());

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
	public void update(ArticleAppealVO aav) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, aav.getEmp_no());
			psmt.setString(2, aav.getArt_apl_sts());
			psmt.setString(3, aav.getArt_rapl());
			psmt.setString(4, aav.getArt_apl_id());

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
	public void delete(String art_apl_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, art_apl_id);
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
	public ArticleAppealVO findByPK(String art_apl_id) {
		
		ArticleAppealVO aav = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return aav;
	}

	@Override
	public List<ArticleAppealVO> getAll() {
		List<ArticleAppealVO> list = new ArrayList<ArticleAppealVO>();
		ArticleAppealVO aav = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		ArticleAppealJDBCDAO dao = new ArticleAppealJDBCDAO();

		// 新增
//		ArticleAppealVO aavo = new ArticleAppealVO();
//		aavo.setArt_id("ART002");
//		aavo.setMem_id("M000005");
//		aavo.setArt_apl_cnt("我的家庭里有五個成員，爸爸，媽媽，姐姐，弟弟和我。我的爸爸又高又帥，是我家的頂樑柱，喜歡看電影。我的媽媽美麗大方，喜歡淘寶，平時沒事也喜歡和朋友一起逛街。我的姐姐漂亮可愛，喜歡看課外書和聽歌。我的弟弟帥氣.機靈喜歡玩電腦遊戲。我呢也是一個漂亮可愛的小美人，我喜歡看電視也喜歡畫畫。");
//		dao.add(aavo);


		// 修改
		ArticleAppealVO aavo = new ArticleAppealVO();
		
		aavo.setEmp_no("EMP001");
		aavo.setArt_apl_sts("檢舉成功");
		aavo.setArt_apl_id("AAI013");
		aavo.setArt_rapl("由於您發表違反道德風氣，將針對您的文章進行封鎖刪除");
		dao.update(aavo);

		// 刪除
//		dao.delete("AAI001");

		// 查詢
//		ArticleAppealVO aavo = dao.findByPK("AAI001");
//	    System.out.print(aavo.getArt_apl_id() + ",");
//		System.out.print(aavo.getArt_id() + ",");		
//		System.out.print(aavo.getMem_id() + ",");		
//		System.out.print(aavo.getEmp_no() + ",");	
//		System.out.print(aavo.getArt_apl_cnt() + ",");
//		System.out.print(aavo.getArt_apl_sts() + ",");		
//		System.out.println(aavo.getArt_apl_date());		
//
//		// 查詢
//		List<ArticleAppealVO> list = dao.getAll();
//		for (ArticleAppealVO aAvo : list) {
//		  	System.out.print(aAvo.getArt_apl_id() + ",");
//			System.out.print(aAvo.getArt_id() + ",");
//			System.out.print(aAvo.getMem_id() + ",");
//			System.out.print(aAvo.getEmp_no() + ",");
//			System.out.print(aAvo.getArt_apl_cnt() + ",");
//			System.out.print(aAvo.getArt_apl_sts() + ",");
//			System.out.print(aAvo.getArt_apl_date() + ",");
//			System.out.print(aAvo.getArt_rapl() + ",");
//			System.out.print(aAvo.getArt_apl_date() + ",");
//			System.out.println();
//		}
		
	}

	@Override
	public List<ArticleAppealVO> getSelected(String art_apl_sts) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
