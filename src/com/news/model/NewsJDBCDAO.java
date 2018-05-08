package com.news.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsJDBCDAO implements NewsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";

	private static final String INSERT_STMT = "INSERT INTO NEWS (NS_ID,EMP_NO,NS_TIT,NS_CNT) VALUES('NS'||LPAD(TO_CHAR(NS_ID.NEXTVAL), 3, '0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM NEWS order by NS_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM NEWS where NS_ID =?";
	private static final String DELETE = "DELETE FROM NEWS where NS_ID = ?";
	private static final String UPDATE = "UPDATE NEWS set EMP_NO=?, NS_TIT=?, NS_CNT =?, NS_CDATE=CURRENT_TIMESTAMP where NS_ID = ?";
	private static final String UPLIKE = "UPDATE NEWS set NS_LIKE=? where NS_ID = ?";
	

	@Override
	public void update(NewsVO nsVO) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(4, nsVO.getNs_id());
			psmt.setString(1, nsVO.getEmp_no());
			psmt.setString(2, nsVO.getNs_tit());
			psmt.setString(3, nsVO.getNs_cnt());
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
	public void updatelike(NewsVO ns) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(UPLIKE);
			psmt.setString(2, ns.getNs_id());
			psmt.setInt(1,ns.getNs_like() );
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
	public void delete(String ns_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, ns_id);
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
	public NewsVO findByPK(String ns_id) {
		
		NewsVO nsv = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(GET_ONE_STMT);

			psmt.setString(1, ns_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				nsv = new NewsVO();
				nsv.setNs_id(rs.getString("ns_id"));
				nsv.setEmp_no(rs.getString("emp_no"));
				nsv.setNs_tit(rs.getString("ns_tit"));
				nsv.setNs_cnt(rs.getString("ns_cnt"));
				nsv.setNs_date(rs.getString("ns_date"));
				nsv.setNs_like(rs.getInt("ns_like"));
				nsv.setNs_cdate(rs.getString("ns_cdate"));
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
		return nsv;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO nsvo = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(GET_ALL_STMT);
			rs = psmt.executeQuery();

			while (rs.next()) {
				nsvo = new NewsVO();
				nsvo.setNs_id(rs.getString("ns_id"));;
				nsvo.setEmp_no(rs.getString("emp_no"));
				nsvo.setNs_tit(rs.getString("ns_tit"));
				nsvo.setNs_cnt(rs.getString("ns_cnt"));
				nsvo.setNs_date(rs.getString("ns_date"));
				nsvo.setNs_like(rs.getInt("ns_like"));
				nsvo.setNs_cdate(rs.getString("ns_cdate"));
				list.add(nsvo);
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

		NewsJDBCDAO dao = new NewsJDBCDAO();

//		 �憓�
//		 NewsVO nsV = new NewsVO();
//		 nsV.setEmp_no("EMP001");
//		 nsV.setNs_tit("JDBC皜祈岫");
//		 nsV.setNs_cnt("皜祈岫皜祈岫皜�");
//		 dao.add(nsV);
		

		// 靽格
//		 NewsVO nsV = new NewsVO();
//		 nsV.setEmp_no("EMP001");
//		 nsV.setNs_tit("皜祈岫2");
//		 nsV.setNs_cnt("皜祈岫����333");
//		 nsV.setNs_id("NS007");
//		 dao.update(nsV);
		
		
//		憓���
		NewsVO nsV = new NewsVO();
		nsV.setNs_id("NS006");
		NewsVO nsVO = dao.findByPK("NS006");
		int count = nsVO.getNs_like();
		nsV.setNs_like(++count);
		System.out.println(count);
		dao.updatelike(nsV);
		
		
		
		
		
		// ��
//		 dao.delete("NS007");

		// �閰�
//		NewsVO nsVO = dao.findByPK("NS006");
//		System.out.print(nsVO.getNs_id()+ ",");
//		System.out.print(nsVO.getEmp_no() + ",");
//		System.out.print(nsVO.getNs_tit() + ",");
//		System.out.print(nsVO.getNs_cnt() + ",");
//		System.out.print(nsVO.getNs_date() + ",");
//		System.out.print(nsVO.getNs_like() + ",");
//		System.out.print(nsVO.getNs_cdate());
		
		
		

		//
		// // �閰�
//		 List<NewsVO> list = dao.getAll();
//		 for (NewsVO nsvo : list) {
//			 System.out.print(nsvo.getNs_id()+ ",");
//				System.out.print(nsvo.getEmp_no() + ",");
//				System.out.print(nsvo.getNs_tit() + ",");
//				System.out.print(nsvo.getNs_cnt() + ",");
//				System.out.print(nsvo.getNs_date());
//				System.out.print(nsvo.getNs_like() + ",");
//				System.out.print(nsvo.getNs_cdate());
//				System.out.println();
//		 }

	}

	@Override
	public void updatests(NewsVO ns) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<NewsVO> selectsts(String ns_sts) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String add(NewsVO ns) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
