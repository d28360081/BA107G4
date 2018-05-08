package com.product_appeal.model;

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

public class ProductAppealDAO implements ProductAppealDAO_interface {
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
			"INSERT INTO PRODUCT_APPEAL(IT_APL_ID,MEM_ID,IT_ID,IT_APL_CNT,APL_STS) VALUES ('IT_APL'||LPAD(TO_CHAR(PRODUCT_APPEAL_SEQ.NEXTVAL), 3, '0'),?,?,?,'待處理')";
	private static final String GET_ALL_STMT = 
			"SELECT IT_APL_ID,MEM_ID,EMP_NO,IT_ID,IT_APL_CNT,APL_STS,TO_CHAR(PA_DATE,'YYYY-MM-DD HH:MM:SS')PA_DATE,IT_RAPL,TO_CHAR(IT_RDATE,'YYYY-MM-DD HH:MM:SS')IT_RDATE FROM PRODUCT_APPEAL order by PA_DATE";
	private static final String GET_ONE_STMT = 
			"SELECT IT_APL_ID,MEM_ID,EMP_NO,IT_ID,IT_APL_CNT,APL_STS,TO_CHAR(PA_DATE,'YYYY-MM-DD HH:MM:SS')PA_DATE,IT_RAPL,TO_CHAR(IT_RDATE,'YYYY-MM-DD HH:MM:SS')IT_RDATE FROM PRODUCT_APPEAL where IT_APL_ID = ?";
	private static final String DELETE = 
			"DELETE FROM PRODUCT_APPEAL where IT_APL_ID = ?";
	private static final String UPDATE = 
			"UPDATE PRODUCT_APPEAL set  EMP_NO=?, APL_STS=?, IT_RAPL=?, IT_RDATE=CURRENT_TIMESTAMP where IT_APL_ID = ?";
	private static final String GET_STS_STMT = 
			"SELECT IT_APL_ID,MEM_ID,EMP_NO,IT_ID,IT_APL_CNT,APL_STS,TO_CHAR(PA_DATE,'YYYY-MM-DD HH:MM:SS')PA_DATE,IT_RAPL,TO_CHAR(IT_RDATE,'YYYY-MM-DD HH:MM:SS')IT_RDATE FROM PRODUCT_APPEAL where APL_STS = ?";
	@Override
	public void add(ProductAppealVO pa) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, pa.getMem_id());
			psmt.setString(2, pa.getIt_id());
			psmt.setString(3, pa.getIt_apl_cnt());
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
	public void update(ProductAppealVO pa) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, pa.getEmp_no());
			psmt.setString(2, pa.getApl_sts());
			psmt.setString(3, pa.getIt_rapl());
			psmt.setString(4, pa.getIt_apl_id());
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
	public void delete(String it_apl_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, it_apl_id);
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
	public ProductAppealVO findByPK(String it_apl_id) {

		ProductAppealVO paVO = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

	@Override
	public List<ProductAppealVO> getSelected(String apl_sts) {
		List<ProductAppealVO> list = new ArrayList<ProductAppealVO>();
		ProductAppealVO paVO = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_STS_STMT);
			psmt.setString(1, apl_sts);
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

