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


public class CommisionCommentAppealDAO implements CommisionCommentAppealDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ProductTest");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
			"INSERT INTO COMMISION_COMMENT_APPEAL (COM_CAPL_ID,COM_ID,MEM_ID_RP,MEM_ID_RPD,APL_CNT,APL_STS,COMT_ID) VALUES ('COMT_APL'||LPAD(TO_CHAR(COM_CAPL_ID.NEXTVAL),3,'0'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM COMMISION_COMMENT_APPEAL order by COM_CAPL_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM COMMISION_COMMENT_APPEAL where COM_CAPL_ID = ?";
	private static final String DELETE = "DELETE FROM COMMISION_COMMENT_APPEAL where COM_CAPL_ID = ?";
	private static final String UPDATE = "UPDATE COMMISION_COMMENT_APPEAL set EMP_NO=?, APL_STS=?, COM_RCAPL=?, COM_CDATE=CURRENT_TIMESTAMP   where COM_CAPL_ID = ?";

	@Override
	public void add(CommisionCommentAppealVO cca) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(INSERT_STMT);
			psmt.setString(1, cca.getCom_id());
			psmt.setString(2, cca.getMem_id_rp());
			psmt.setString(3, cca.getMem_id_rpd());
			psmt.setString(4, cca.getApl_cnt());
			psmt.setString(5, cca.getApl_sts());
			psmt.setString(6, cca.getComt_id());
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
	public void update(CommisionCommentAppealVO cca) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, cca.getEmp_no());
			psmt.setString(2, cca.getApl_sts());
			psmt.setString(3, cca.getCom_rcapl());
			psmt.setString(4, cca.getCom_capl_id());
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
	public void delete(String com_capl_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, com_capl_id);
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
	public CommisionCommentAppealVO findByPK(String com_capl_id) {
		CommisionCommentAppealVO ccaVO = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ONE_STMT);
			psmt.setString(1, com_capl_id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ccaVO = new CommisionCommentAppealVO();
				ccaVO.setCom_capl_id(rs.getString("com_capl_id"));
				ccaVO.setCom_id(rs.getString("com_id"));
				ccaVO.setMem_id_rp(rs.getString("mem_id_rp"));
				ccaVO.setMem_id_rpd(rs.getString("mem_id_rpd"));
				ccaVO.setEmp_no(rs.getString("emp_no"));
				ccaVO.setApl_cnt(rs.getString("apl_cnt"));
				ccaVO.setApl_sts(rs.getString("apl_sts"));
				ccaVO.setComt_id(rs.getString("comt_id"));
				ccaVO.setCom_capl_date(rs.getString("com_capl_date"));
				ccaVO.setCom_rcapl(rs.getString("com_rcapl"));
				ccaVO.setCom_cdate(rs.getString("com_cdate"));
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
		return ccaVO;
	}

	@Override
	public List<CommisionCommentAppealVO> getAll() {
		List<CommisionCommentAppealVO> list = new ArrayList<CommisionCommentAppealVO>();
		CommisionCommentAppealVO ccaVO = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL_STMT);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ccaVO = new CommisionCommentAppealVO();
				ccaVO.setCom_capl_id(rs.getString("com_capl_id"));
				ccaVO.setCom_id(rs.getString("com_id"));
				ccaVO.setMem_id_rp(rs.getString("mem_id_rp"));
				ccaVO.setMem_id_rpd(rs.getString("mem_id_rpd"));
				ccaVO.setEmp_no(rs.getString("emp_no"));
				ccaVO.setApl_cnt(rs.getString("apl_cnt"));
				ccaVO.setApl_sts(rs.getString("apl_sts"));
				ccaVO.setComt_id(rs.getString("comt_id"));
				ccaVO.setCom_capl_date(rs.getString("com_capl_date"));
				ccaVO.setCom_rcapl(rs.getString("com_rcapl"));
				ccaVO.setCom_cdate(rs.getString("com_cdate"));
				list.add(ccaVO);
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