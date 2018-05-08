package com.pmt.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.pmt_det.model.PmtDetDAO;
import com.pmt_det.model.PmtDetVO;

public class PmtDAO implements PmtDAO_interface{
	
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
			"INSERT INTO promotion (pmt_id,emp_no,pmt_name,pmt_intro,pmt_pic,pmt_s_date,pmt_e_date,pmt_sts,pmt_discount) VALUES ('PMT'||LPAD(TO_CHAR(PROMOTION_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT * FROM promotion order by pmt_id desc";
	private static final String GET_ONE_STMT =
			"SELECT * FROM promotion where pmt_id = ?";
	private static final String DELETE =
			"DELETE FROM promotion where pmt_id =?";	
	private static final String UPDATE =
			"UPDATE promotion set emp_no =?,pmt_name=?,pmt_intro=?,pmt_pic=?,pmt_s_date=?,pmt_e_date=?,pmt_sts=?,pmt_discount=? where pmt_id=? ";
	private static final String UPDATE_STS =
			"UPDATE promotion set pmt_sts=? where pmt_id=? ";
	private static final String GET_KEYWORD_STMT = 
			"select * from promotion where pmt_name like ?";
	private static final String GET_ONLINE_ALL_STMT = 
			"SELECT * FROM promotion where pmt_sts='上架' order by pmt_id desc";
	@Override
	public String insert(PmtVO pmtVO, List<PmtDetVO> pmtdetList) {
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);// 設定於 pstm.executeUpdate()之前
			String cols[] = {"pmt_id"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);			
			pstmt.setString(1, pmtVO.getEmp_no());
			pstmt.setString(2, pmtVO.getPmt_name());
			pstmt.setString(3, pmtVO.getPmt_intro());
			pstmt.setBytes(4, pmtVO.getPmt_pic());
			pstmt.setTimestamp(5, pmtVO.getPmt_s_date());
			pstmt.setTimestamp(6, pmtVO.getPmt_e_date());
			pstmt.setString(7,pmtVO.getPmt_sts());	
			pstmt.setDouble(8,pmtVO.getPmt_discount());
			pstmt.executeUpdate(); 						
			String next_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_id +"(剛新增成功編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			
			PmtDetDAO pmtDetDAO = new PmtDetDAO();			
			for(PmtDetVO pmtdetVO: pmtdetList){
				pmtdetVO.setPmt_id(next_id);
				pmtDetDAO.insert2(pmtdetVO,con);
			}
			
			con.commit();
			con.setAutoCommit(true);
			return next_id;
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
				} catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}			
	}
	
	@Override
	public void update(PmtVO pmtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, pmtVO.getEmp_no());
			pstmt.setString(2, pmtVO.getPmt_name());
			pstmt.setString(3, pmtVO.getPmt_intro());
			pstmt.setBytes(4, pmtVO.getPmt_pic());
			pstmt.setTimestamp(5, pmtVO.getPmt_s_date());
			pstmt.setTimestamp(6, pmtVO.getPmt_e_date());
			pstmt.setString(7,pmtVO.getPmt_sts());
			pstmt.setDouble(8,pmtVO.getPmt_discount());
			pstmt.setString(9,pmtVO.getPmt_id());
			
			pstmt.executeUpdate(); 
		}catch (SQLException se) {
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
	public void update(PmtVO pmtVO, List<PmtDetVO> pmtdetList) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, pmtVO.getEmp_no());
			pstmt.setString(2, pmtVO.getPmt_name());
			pstmt.setString(3, pmtVO.getPmt_intro());
			pstmt.setBytes(4, pmtVO.getPmt_pic());
			pstmt.setTimestamp(5, pmtVO.getPmt_s_date());
			pstmt.setTimestamp(6, pmtVO.getPmt_e_date());
			pstmt.setString(7,pmtVO.getPmt_sts());
			pstmt.setDouble(8,pmtVO.getPmt_discount());
			pstmt.setString(9,pmtVO.getPmt_id());
			
			
			pstmt.executeUpdate(); 
			
			PmtDetDAO pmtDetDAO = new PmtDetDAO();
			pmtDetDAO.delete2(pmtVO.getPmt_id(), con);
			
			for(PmtDetVO pmtDetVO :pmtdetList){
				pmtDetDAO.insert2(pmtDetVO, con);
			}
			
			con.commit();
			con.setAutoCommit(true);
		}catch (SQLException se) {
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
	public void delete(String pmt_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, pmt_id); 
			
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
	public PmtVO findByPrimaryKey(String pmt_id) {		
		PmtVO pmtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1,pmt_id);			
			rs = pstmt.executeQuery();			
			while(rs.next()){
				pmtVO = new PmtVO();
				pmtVO.setPmt_id(rs.getString("PMT_ID"));
				pmtVO.setEmp_no(rs.getString("EMP_NO"));
				pmtVO.setPmt_name(rs.getString("PMT_NAME"));
				pmtVO.setPmt_intro(rs.getString("PMT_INTRO"));
				pmtVO.setPmt_pic(rs.getBytes("PMT_PIC"));
				pmtVO.setPmt_s_date(rs.getTimestamp("PMT_S_DATE"));
				pmtVO.setPmt_e_date(rs.getTimestamp("PMT_E_DATE"));
				pmtVO.setPmt_sts(rs.getNString("PMT_STS"));	
				pmtVO.setPmt_discount(rs.getDouble("pmt_discount"));
			}
		}catch (SQLException se) {
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
		return pmtVO;
	}

	@Override
	public List<PmtVO> getAll() {
		List<PmtVO> list = new ArrayList<PmtVO>();
		PmtVO pmtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pmtVO = new PmtVO();
				pmtVO.setPmt_id(rs.getString("PMT_ID"));
				pmtVO.setEmp_no(rs.getString("EMP_NO"));
				pmtVO.setPmt_name(rs.getString("PMT_NAME"));
				pmtVO.setPmt_intro(rs.getString("PMT_INTRO"));
				pmtVO.setPmt_pic(rs.getBytes("PMT_PIC"));
				pmtVO.setPmt_s_date(rs.getTimestamp("PMT_S_DATE"));
				pmtVO.setPmt_e_date(rs.getTimestamp("PMT_E_DATE"));
				pmtVO.setPmt_sts(rs.getNString("PMT_STS"));	
				pmtVO.setPmt_discount(rs.getDouble("pmt_discount"));
				list.add(pmtVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
	public void insert(PmtVO pmtVO) {
		Connection con= null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);			
			pstmt.setString(1, pmtVO.getEmp_no());
			pstmt.setString(2, pmtVO.getPmt_name());
			pstmt.setString(3, pmtVO.getPmt_intro());
			pstmt.setBytes(4, pmtVO.getPmt_pic());
			pstmt.setTimestamp(5, pmtVO.getPmt_s_date());
			pstmt.setTimestamp(6, pmtVO.getPmt_e_date());
			pstmt.setString(7,pmtVO.getPmt_sts());	
			pstmt.setDouble(8,pmtVO.getPmt_discount());
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
				} catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}			
	}

	@Override
	public List<PmtVO> getOnlineAll() {
		List<PmtVO> list = new ArrayList<PmtVO>();
		PmtVO pmtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONLINE_ALL_STMT);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pmtVO = new PmtVO();
				pmtVO.setPmt_id(rs.getString("PMT_ID"));
				pmtVO.setEmp_no(rs.getString("EMP_NO"));
				pmtVO.setPmt_name(rs.getString("PMT_NAME"));
				pmtVO.setPmt_intro(rs.getString("PMT_INTRO"));
				pmtVO.setPmt_pic(rs.getBytes("PMT_PIC"));
				pmtVO.setPmt_s_date(rs.getTimestamp("PMT_S_DATE"));
				pmtVO.setPmt_e_date(rs.getTimestamp("PMT_E_DATE"));
				pmtVO.setPmt_sts(rs.getNString("PMT_STS"));	
				pmtVO.setPmt_discount(rs.getDouble("pmt_discount"));
				list.add(pmtVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
	public List<PmtVO> getByKeyword(String keyword) {
		List<PmtVO> list = new ArrayList<PmtVO>();
		PmtVO pmtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			if(keyword.matches("PMT.*")){
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setString(1, keyword);
			}else{
				pstmt = con.prepareStatement(GET_KEYWORD_STMT);
				pstmt.setString(1,"%"+keyword+"%");
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pmtVO = new PmtVO();
				pmtVO.setPmt_id(rs.getString("PMT_ID"));
				pmtVO.setEmp_no(rs.getString("EMP_NO"));
				pmtVO.setPmt_name(rs.getString("PMT_NAME"));
				pmtVO.setPmt_intro(rs.getString("PMT_INTRO"));
				pmtVO.setPmt_pic(rs.getBytes("PMT_PIC"));
				pmtVO.setPmt_s_date(rs.getTimestamp("PMT_S_DATE"));
				pmtVO.setPmt_e_date(rs.getTimestamp("PMT_E_DATE"));
				pmtVO.setPmt_sts(rs.getNString("PMT_STS"));	
				pmtVO.setPmt_discount(rs.getDouble("pmt_discount"));
				list.add(pmtVO);
			}
		} catch (SQLException se) {
			se.printStackTrace();
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
	public void update(String pmt_id, String pmt_sts) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STS);
			
			pstmt.setString(1,pmt_sts);
			pstmt.setString(2,pmt_id);
			
			pstmt.executeUpdate(); 
		}catch (SQLException se) {
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

}
