package com.commision_appeal.model;

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

public class CommisionAppealDAO implements CommisionAppealDAO_interface{
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
			"INSERT INTO COMMISION_APPEAL (COM_APL_ID,MEM_ID,COM_ID,APL_CNT,APL_STS) VALUES ('COM_APL'||LPAD(TO_CHAR(COM_APL_SEQ.NEXTVAL),3,'0'),?,?,?,'待處理')";
	private static final String GET_ALL_STMT =
			"SELECT COM_APL_ID,MEM_ID,EMP_NO,COM_ID,APL_CNT,APL_STS,TO_CHAR(COM_CAPL_DATE, 'YYYY-MM-DD HH:MM:SS')COM_CAPL_DATE,COM_RAPL,TO_CHAR(COM_RAPLDATE, 'YYYY-MM-DD HH:MM:SS')COM_RAPLDATE FROM COMMISION_APPEAL order by COM_CAPL_DATE DESC";
	private static final String GET_ONE_STMT =
			"SELECT COM_APL_ID,MEM_ID,EMP_NO,COM_ID,APL_CNT,APL_STS,TO_CHAR(COM_CAPL_DATE, 'YYYY-MM-DD HH:MM:SS')COM_CAPL_DATE,COM_RAPL,TO_CHAR(COM_RAPLDATE, 'YYYY-MM-DD HH:MM:SS')COM_RAPLDATE FROM COMMISION_APPEAL where COM_APL_ID = ?";
	private static final String DELETE =
			"DELETE FROM COMMISION_APPEAL where COM_APL_ID = ?";
	private static final String UPDATE = 
			"UPDATE COMMISION_APPEAL set EMP_NO=?, APL_STS=?, COM_RAPL=?, COM_RAPLDATE=CURRENT_TIMESTAMP  where COM_APL_ID = ?";
	private static final String GET_STS_STMT =
			"SELECT COM_APL_ID,MEM_ID,EMP_NO,COM_ID,APL_CNT,APL_STS,TO_CHAR(COM_CAPL_DATE, 'YYYY-MM-DD HH:MM:SS')COM_CAPL_DATE,COM_RAPL,TO_CHAR(COM_RAPLDATE, 'YYYY-MM-DD HH:MM:SS')COM_RAPLDATE FROM COMMISION_APPEAL where APL_STS = ?";
	
	
	
	@Override
	public void add(CommisionAppealVO ca) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, ca.getMem_id());
			psmt.setString(2, ca.getCom_id());
			psmt.setString(3, ca.getApl_cnt());
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
	public void update(CommisionAppealVO ca) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, ca.getEmp_no());
			psmt.setString(2, ca.getApl_sts());
			psmt.setString(3, ca.getCom_rapl());
			psmt.setString(4, ca.getCom_apl_id());
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
	public void delete(String com_apl_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, com_apl_id);
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
	public CommisionAppealVO findByPK(String com_apl_id) {
		CommisionAppealVO caVO = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	public List<CommisionAppealVO> getSelected(String apl_sts) {
		List<CommisionAppealVO> list = new ArrayList<CommisionAppealVO>();
		CommisionAppealVO caVO = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_STS_STMT);
			psmt.setString(1, apl_sts);
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
