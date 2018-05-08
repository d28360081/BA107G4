package com.articlecollection.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtcolJDBCDAO implements ArtcolVO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";
	private static final String INSERT_STMT=
			"INSERT INTO ARTICLE_COLLECTION VALUES(?,?)";
	private static final String GET_ALL_STMT=
			"SELECT * FROM ARTICLE_COLLECTION";
	private static final String GET_ONE_STMT=
			"SELECT * FROM ARTICLE_COLLECTION WHERE mem_id=?";
	private static final String DELETE=
			"DELETE FROM ARTICLE_COLLECTION WHERE mem_id=? AND art_id=?";
	
	@Override
	public void insert(ArtcolVO art_colVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, art_colVO.getMem_id());
			pstmt.setString(2, art_colVO.getArt_id());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String mem_id , String art_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, mem_id);
			pstmt.setString(2, art_id);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public ArtcolVO findByPrimaryKey(String mem_id) {
		ArtcolVO artcolVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artcolVO = new ArtcolVO();
				artcolVO.setMem_id(rs.getString("mem_id"));
				artcolVO.setArt_id(rs.getString("art_id"));
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

		return artcolVO;
	}

	@Override
	public List<ArtcolVO> getAll() {
		List<ArtcolVO> list = new ArrayList<ArtcolVO>();
		ArtcolVO artcolVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				artcolVO = new ArtcolVO();
				artcolVO.setMem_id(rs.getString("mem_id"));
				artcolVO.setArt_id(rs.getString("art_id"));
				list.add(artcolVO);
			}
			
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

		return list;
	}
	public static void main(String args[]) {
		ArtcolJDBCDAO dao = new ArtcolJDBCDAO();
//		ArtcolVO artcol = new ArtcolVO();
//		artcol.setMem_id("M000009");
//		artcol.setArt_id("ART003");
//		dao.INSERT(artcol);
		
//		dao.DELETE("M000009", "ART003");
//		ArtcolVO artcol2 = dao.findByPrimaryKey("M000005");
//		System.out.print(artcol2.getMem_id()+" , ");
//		System.out.print(artcol2.getArt_id());
		
		List<ArtcolVO> list = dao.findallByMem("M000001");
		for(ArtcolVO artcol3 : list) {
			System.out.print(artcol3.getMem_id()+" , ");
			System.out.println(artcol3.getArt_id());
			System.out.println("=================");
		}
	}



	@Override
	public List<ArtcolVO> findallByMem(String mem_id) {
		List<ArtcolVO> list = new ArrayList<ArtcolVO>();
		ArtcolVO artcolVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, mem_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				artcolVO = new ArtcolVO();
				artcolVO.setMem_id(rs.getString("mem_id"));
				artcolVO.setArt_id(rs.getString("art_id"));
				list.add(artcolVO);
			}
			
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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

		return list;
	}
}
