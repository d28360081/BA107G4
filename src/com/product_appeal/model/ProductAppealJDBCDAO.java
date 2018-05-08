package com.product_appeal.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductAppealJDBCDAO implements ProductAppealDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";

	private static final String INSERT_STMT = 
			"INSERT INTO PRODUCT_APPEAL(IT_APL_ID,MEM_ID,IT_ID,IT_APL_CNT,APL_STS) VALUES ('IT_APL'||LPAD(TO_CHAR(PRODUCT_APPEAL_SEQ.NEXTVAL), 3, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM PRODUCT_APPEAL order by IT_APL_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM PRODUCT_APPEAL where IT_APL_ID = ?";
	private static final String DELETE = "DELETE FROM PRODUCT_APPEAL where IT_APL_ID = ?";
	private static final String UPDATE = "UPDATE PRODUCT_APPEAL set  EMP_NO=?, APL_STS=?, IT_RAPL=?, IT_RDATE=CURRENT_TIMESTAMP where IT_APL_ID = ?";

	@Override
	public void add(ProductAppealVO pa) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, pa.getMem_id());
			psmt.setString(2, pa.getIt_id());
			psmt.setString(3, pa.getIt_apl_cnt());
			psmt.setString(4, pa.getApl_sts());
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
	public void update(ProductAppealVO pa) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, pa.getEmp_no());
			psmt.setString(2, pa.getApl_sts());
			psmt.setString(3, pa.getIt_rapl());
			psmt.setString(4, pa.getIt_apl_id());
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
	public void delete(String it_apl_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, it_apl_id);
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
	public ProductAppealVO findByPK(String it_apl_id) {
		ProductAppealVO paVO = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(GET_ONE_STMT);

			psmt.setString(1, it_apl_id);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				paVO = new ProductAppealVO();
				paVO.setIt_apl_id(rs.getString("it_apl_id"));
				paVO.setMem_id(rs.getString("mem_id"));
				paVO.setEmp_no(rs.getString("emp_no"));
				paVO.setIt_id(rs.getString("it_id"));
				paVO.setIt_apl_cnt(rs.getString("it_apl_cnt"));
				paVO.setApl_sts(rs.getString("apl_sts"));
				paVO.setPa_date(rs.getString("pa_date"));
				paVO.setIt_rapl(rs.getString("it_rapl"));
				paVO.setIt_rdate(rs.getString("it_rdate"));
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
		return paVO;
	}


	@Override
	public List<ProductAppealVO> getAll() {
		List<ProductAppealVO> list = new ArrayList<ProductAppealVO>();
		ProductAppealVO paVO = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			psmt = con.prepareStatement(GET_ALL_STMT);
			rs = psmt.executeQuery();

			while (rs.next()) {
				paVO = new ProductAppealVO();
				paVO.setIt_apl_id(rs.getString("it_apl_id"));
				paVO.setMem_id(rs.getString("mem_id"));
				paVO.setEmp_no(rs.getString("emp_no"));
				paVO.setIt_id(rs.getString("it_id"));
				paVO.setIt_apl_cnt(rs.getString("it_apl_cnt"));
				paVO.setApl_sts(rs.getString("apl_sts"));
				paVO.setPa_date(rs.getString("pa_date"));
				paVO.setIt_rapl(rs.getString("it_rapl"));
				paVO.setIt_rdate(rs.getString("it_rdate"));
				list.add(paVO);
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

		ProductAppealJDBCDAO dao = new ProductAppealJDBCDAO();

		// 新增
//		ProductAppealVO pavo = new ProductAppealVO();
//		pavo.setMem_id("M000005");
//		pavo.setIt_id("IT001");
//		pavo.setIt_apl_cnt("垃圾快下架");
//		pavo.setApl_sts("待處理");
//		dao.add(pavo);


		// 修改
//		ProductAppealVO pavo = new ProductAppealVO();
//		pavo.setEmp_no("EMP001");
//		pavo.setApl_sts("檢舉有效");
//		pavo.setIt_rapl("感謝您的參與");
//		pavo.setIt_apl_id("IT_APL002");
//		dao.update(pavo);

		// 刪除
//		dao.delete("IT_APL002");

		// 查詢
//		ProductAppealVO pavo = dao.findByPK("IT_APL002");
//		System.out.print(pavo.getIt_apl_id() + ",");
//	    System.out.print(pavo.getMem_id() + ",");
//		System.out.print(pavo.getEmp_no() +",");
//		System.out.print(pavo.getIt_id() + ",");
//		System.out.print(pavo.getIt_apl_cnt() +",");
//		System.out.print(pavo.getApl_sts() +",");
//		System.out.print(pavo.getPa_date() +",");
//		System.out.print(pavo.getIt_rapl() +",");
//		System.out.print(pavo.getIt_rdate());
		
////
//		// 查詢
		List<ProductAppealVO> list = dao.getAll();
		for (ProductAppealVO pavo : list) {
		System.out.print(pavo.getIt_apl_id() + ",");
	    System.out.print(pavo.getMem_id() + ",");
		System.out.print(pavo.getEmp_no() +",");
		System.out.print(pavo.getIt_id() + ",");
		System.out.print(pavo.getIt_apl_cnt() +",");
		System.out.print(pavo.getApl_sts() +",");
		System.out.print(pavo.getPa_date());
		System.out.print(pavo.getIt_rapl() +",");
		System.out.print(pavo.getIt_rdate());
		System.out.println();
		}
		
	}

	@Override
	public List<ProductAppealVO> getSelected(String apl_sts) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

