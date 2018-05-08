package com.pmt_det.model;

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

public class PmtDetDAO implements PmtDetDAO_interface{
	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds =(DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
		}catch(NamingException e){
			e.printStackTrace(System.err);
			}
	}
	//因是複合主鍵 所以關鍵都要兩欄位
	private static final String INSERT_STMT =
			"INSERT INTO promotion_detail (pmt_id,it_id) VALUES (?,?)";
	private static final String GET_ALL_STMT =
			"SELECT pmt_id,it_id FROM promotion_detail order by pmt_id , it_id";//where前面是用 (,)  之後是用 (and)
	private static final String GET_ONE_STMT = 
			"SELECT pmt_id,it_id FROM promotion_detail where pmt_id = ? and it_id=?";	
	private static final String GET_PMT_ID =
			"SELECT pmt_id,it_id FROM promotion_detail where pmt_id = ? ";
	private static final String GET_IT_ID =
			"SELECT pmt_id,it_id FROM promotion_detail where it_id=? ";	
	private static final String DELETE =
			"DELETE FROM promotion_detail where pmt_id=? and it_id=?";	// where後放主建(複合主建)
//促銷明細 沒有修改 只有新增與刪除
	private static final String DELETE_PMT_ID = 
			"DELETE FROM promotion_detail where pmt_id=?";
	private static final String SELECT_ALL_AVAILABLE=
			"SELECT pmt_id,it_id FROM promotion_detail order by pmt_id , it_id WHERE PMT_E_DATE>SYSDATE";
	@Override
	public void insert(PmtDetVO pmtdetVO ) {
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);	
			pstmt.setString(1, pmtdetVO.getPmt_id());
			pstmt.setString(2, pmtdetVO.getIt_id());
		
			pstmt.executeUpdate(); 					
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
			
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				} catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				} catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}			
	}
	
	@Override
	public void delete(String pmt_id,String it_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1,pmt_id);
			pstmt.setString(2,it_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());			
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public PmtDetVO findByPrimaryKey(String pmt_id,String it_id) {
		PmtDetVO pmtdetVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, pmt_id);
			pstmt.setString(2, it_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {	
				pmtdetVO = new PmtDetVO();
				pmtdetVO.setPmt_id(rs.getString("PMT_ID"));
				pmtdetVO.setIt_id(rs.getString("IT_ID"));				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
		return pmtdetVO;
	}
	
	@Override	
	public List<PmtDetVO> getByPMT_ID(String pmt_id){
		List<PmtDetVO> list = new ArrayList<PmtDetVO>();
		PmtDetVO pmtdetVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PMT_ID);
			pstmt.setString(1, pmt_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {			
				pmtdetVO = new PmtDetVO();
				pmtdetVO.setPmt_id(rs.getString("PMT_ID"));
				pmtdetVO.setIt_id(rs.getString("IT_ID"));
				list.add(pmtdetVO); 
			}			
			System.out.println(list.size());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());		
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
	
	public List<PmtDetVO> getByIT_ID(String it_id){
		List<PmtDetVO> list = new ArrayList<PmtDetVO>();
		PmtDetVO pmtdetVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_IT_ID);
			pstmt.setString(1, it_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {			
				pmtdetVO = new PmtDetVO();
				pmtdetVO.setPmt_id(rs.getString("PMT_ID"));
				pmtdetVO.setIt_id(rs.getString("IT_ID"));
				list.add(pmtdetVO); 
			}			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());		
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public List<PmtDetVO> getAll() {
		List<PmtDetVO> list = new ArrayList<PmtDetVO>();
		PmtDetVO pmtdetVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {			
				pmtdetVO = new PmtDetVO();
				pmtdetVO.setPmt_id(rs.getString("PMT_ID"));
				pmtdetVO.setIt_id(rs.getString("IT_ID"));
				list.add(pmtdetVO); 
			}			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());		
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
	public void insert2(PmtDetVO pmtdetVO, Connection con) {
		PreparedStatement pstmt = null;
		try {
     		pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, pmtdetVO.getPmt_id());
			pstmt.setString(2,pmtdetVO.getIt_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-PmtDetDAO insert2");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

		
	}

	@Override
	public void delete2(String pmt_id, Connection con) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(DELETE_PMT_ID);
			pstmt.setString(1, pmt_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-PmtDetDAO delete3");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	
	public List<PmtDetVO> getAllAvaliable() {
		List<PmtDetVO> list = new ArrayList<PmtDetVO>();
		PmtDetVO pmtdetVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT_ALL_AVAILABLE);
			rs = pstmt.executeQuery();
			while (rs.next()) {			
				pmtdetVO = new PmtDetVO();
				pmtdetVO.setPmt_id(rs.getString("PMT_ID"));
				pmtdetVO.setIt_id(rs.getString("IT_ID"));
				list.add(pmtdetVO); 
			}			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());		
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
