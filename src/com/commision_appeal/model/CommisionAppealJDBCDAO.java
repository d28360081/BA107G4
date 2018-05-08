package com.commision_appeal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommisionAppealJDBCDAO implements CommisionAppealDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";

	private static final String INSERT_STMT = 
			"INSERT INTO COMMISION_APPEAL (COM_APL_ID,MEM_ID,COM_ID,APL_CNT,APL_STS) VALUES ('COM_APL'||LPAD(TO_CHAR(COM_APL_SEQ.NEXTVAL),3,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM COMMISION_APPEAL order by COM_APL_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM COMMISION_APPEAL where COM_APL_ID = ?";
	private static final String DELETE = "DELETE FROM COMMISION_APPEAL where COM_APL_ID = ?";
	private static final String UPDATE = "UPDATE COMMISION_APPEAL set EMP_NO=?, APL_STS=?, COM_RAPL=?, COM_RAPLDATE=CURRENT_TIMESTAMP  where COM_APL_ID = ?";

	@Override
	public void add(CommisionAppealVO ca) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, ca.getMem_id());
			psmt.setString(2, ca.getCom_id());
			psmt.setString(3, ca.getApl_cnt());
			psmt.setString(4, ca.getApl_sts());
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
	public void update(CommisionAppealVO ca) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, ca.getEmp_no());
			psmt.setString(2, ca.getApl_sts());
			psmt.setString(3, ca.getCom_rapl());
			psmt.setString(4, ca.getCom_apl_id());
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
	public void delete(String com_apl_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, com_apl_id);
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
	public CommisionAppealVO findByPK(String com_apl_id) {
		CommisionAppealVO caVO = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(GET_ONE_STMT);

			psmt.setString(1, com_apl_id);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				caVO = new CommisionAppealVO();
				caVO.setCom_apl_id(rs.getString("com_apl_id"));
				caVO.setMem_id(rs.getString("mem_id"));
				caVO.setEmp_no(rs.getString("emp_no"));
				caVO.setCom_id(rs.getString("com_id"));
				caVO.setApl_cnt(rs.getString("apl_cnt"));
				caVO.setApl_sts(rs.getString("apl_sts"));
				caVO.setCom_capl_date(rs.getString("com_capl_date"));
				caVO.setCom_rapl(rs.getString("com_rapl"));
				caVO.setCom_rapldate(rs.getString("com_rapldate"));
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
		return caVO;
	}


	@Override
	public List<CommisionAppealVO> getAll() {
		List<CommisionAppealVO> list = new ArrayList<CommisionAppealVO>();
		CommisionAppealVO caVO = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(GET_ALL_STMT);
			rs = psmt.executeQuery();

			while (rs.next()) {
				caVO = new CommisionAppealVO();
				caVO.setCom_apl_id(rs.getString("com_apl_id"));
				caVO.setMem_id(rs.getString("mem_id"));
				caVO.setEmp_no(rs.getString("emp_no"));
				caVO.setCom_id(rs.getString("com_id"));
				caVO.setApl_cnt(rs.getString("apl_cnt"));
				caVO.setApl_sts(rs.getString("apl_sts"));
				caVO.setCom_capl_date(rs.getString("com_capl_date"));
				caVO.setCom_rapl(rs.getString("com_rapl"));
				caVO.setCom_rapldate(rs.getString("com_rapldate"));
				list.add(caVO);
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

		CommisionAppealJDBCDAO dao = new CommisionAppealJDBCDAO();

		// 新增
//		CommisionAppealVO cavo = new CommisionAppealVO();
//		cavo.setMem_id("M000004");
//		cavo.setCom_id("COM002");
//		cavo.setApl_cnt("測試測試");
//		cavo.setApl_sts("待處理");
//		dao.add(cavo);


		// 修改
//		CommisionAppealVO cavo = new CommisionAppealVO();
//		cavo.setEmp_no("EMP001");
//		cavo.setApl_sts("檢舉成功");
//		cavo.setCom_rapl("感謝您的參與");
//		cavo.setCom_apl_id("COM_APL002");
//		dao.update(cavo);

		// 刪除
//		dao.delete("COM_APL021");

		// 查詢
		CommisionAppealVO cavo = dao.findByPK("COM_APL002");
		System.out.print(cavo.getCom_apl_id() + ",");
	    System.out.print(cavo.getMem_id() + ",");
		System.out.print(cavo.getEmp_no() +",");
		System.out.print(cavo.getCom_id() + ",");
		System.out.print(cavo.getApl_cnt() +",");
		System.out.print(cavo.getApl_sts() +",");
		System.out.print(cavo.getCom_capl_date());
		System.out.print(cavo.getCom_rapl());
		System.out.print(cavo.getCom_rapldate());
////
//		// 查詢
//		List<CommisionAppealVO> list = dao.getAll();
//		for (CommisionAppealVO cavo : list) {
//		System.out.print(cavo.getCom_apl_id() + ",");
//	    System.out.print(cavo.getMem_id() + ",");
//		System.out.print(cavo.getEmp_no() +",");
//		System.out.print(cavo.getCom_id() + ",");
//		System.out.print(cavo.getApl_cnt() +",");
//		System.out.print(cavo.getApl_sts() +",");
//		System.out.print(cavo.getCom_capl_date());
//		System.out.print(cavo.getCom_rapl());
//		System.out.print(cavo.getCom_rapldate());
//		System.out.println();
//		}
		
	}

	@Override
	public List<CommisionAppealVO> getSelected(String apl_sts) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

