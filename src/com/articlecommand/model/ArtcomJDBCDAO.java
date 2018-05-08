package com.articlecommand.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ArtcomJDBCDAO implements ArtcomVO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";
	
	private static final String INSERT_STMT=
			"INSERT INTO ARTICLE_COMMENT(art_msg_id,art_id,mem_id,art_msg_cnt,art_msg_pic,art_msg_like_num) VALUES ('ARTCOM'||LPAD(to_char(ARTMSG_SEQ.NEXTVAL),3,'0'),?,?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT art_msg_id,art_id,mem_id,art_msg_cnt,art_msg_pic,to_char(art_msg_date,'yyyy-mm-dd hh:mm:ss')art_msg_date,art_msg_like_num FROM ARTICLE_COMMENT ORDER BY art_msg_id";
	private static final String GET_ONE_STMT=
			"SELECT art_msg_id,art_id,mem_id,art_msg_cnt,art_msg_pic,to_char(art_msg_date,'yyyy-mm-dd hh:mm:ss')art_msg_date,art_msg_like_num FROM ARTICLE_COMMENT WHERE art_msg_id=?";
	private static final String DELETE=
			"DELETE FROM ARTICLE_COMMENT WHERE art_msg_id=?";
	private static final String UPDATE=
			"UPDATE ARTICLE_COMMENT set art_msg_cnt=?,art_msg_pic=?,art_msg_date=?,art_msg_like_num=? WHERE art_msg_id=?";
	private static final String GETBYART_ID=
			"SELECT art_msg_id,art_id,mem_id,art_msg_cnt,art_msg_pic,to_char(art_msg_date,'yyyy-mm-dd hh:mm:ss')art_msg_date,art_msg_like_num FROM ARTICLE_COMMENT WHERE art_id=?";
	private static final String UPDATELIKE=
			"UPDATE ARTICLE_COMMENT set art_msg_like_num=? WHERE art_msg_id=?";
	@Override
	public void insert(ArtcomVO art_comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, art_comVO.getArt_id());
			pstmt.setString(2, art_comVO.getMem_id());
			pstmt.setString(3, art_comVO.getArt_msg_cnt());
			pstmt.setBytes(4, art_comVO.getArt_msg_pic());
			pstmt.setInt(5, art_comVO.getArt_msg_like_num());
			
			pstmt.executeUpdate();
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
	public void delete(String art_msg_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, art_msg_id);

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
	public void update(ArtcomVO art_comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, art_comVO.getArt_msg_cnt());
			pstmt.setBytes(2, art_comVO.getArt_msg_pic());
			pstmt.setTimestamp(3, art_comVO.getArt_msg_date());
			pstmt.setInt(4, art_comVO.getArt_msg_like_num());
			pstmt.setString(5, art_comVO.getArt_msg_id());
			
			
			pstmt.executeUpdate();
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
	public ArtcomVO findByPrimaryKey(String art_msg_id) {
		ArtcomVO artcomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, art_msg_id);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				artcomVO = new ArtcomVO();
				artcomVO.setArt_msg_id(rs.getString("art_msg_id"));
				artcomVO.setArt_id(rs.getString("art_id"));
				artcomVO.setMem_id(rs.getString("mem_id"));
				artcomVO.setArt_msg_cnt(rs.getString("art_msg_cnt"));
				artcomVO.setArt_msg_pic(rs.getBytes("art_msg_pic"));
				artcomVO.setArt_msg_date(rs.getTimestamp("art_msg_date"));
				artcomVO.setArt_msg_like_num(rs.getInt("art_msg_like_num"));
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

		return artcomVO;
	}

	@Override
	public List<ArtcomVO> getAll() {
		List<ArtcomVO> list = new ArrayList<ArtcomVO>();
		ArtcomVO artcomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				artcomVO = new ArtcomVO();
				artcomVO.setArt_msg_id(rs.getString("art_msg_id"));
				artcomVO.setArt_id(rs.getString("art_id"));
				artcomVO.setMem_id(rs.getString("mem_id"));
				artcomVO.setArt_msg_cnt(rs.getString("art_msg_cnt"));
				artcomVO.setArt_msg_pic(rs.getBytes("art_msg_pic"));
				artcomVO.setArt_msg_date(rs.getTimestamp("art_msg_date"));
				artcomVO.setArt_msg_like_num(rs.getInt("art_msg_like_num"));
				list.add(artcomVO);
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
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];//��嚙踝蕭
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	
	public static void main(String[]args) throws IOException {
		ArtcomJDBCDAO  dao = new ArtcomJDBCDAO();
//		byte[] pic = getPictureByteArray("image/12.jpg");
//		byte[] pic2 = getPictureByteArray("image/100.jpg");
		ArtcomVO artcomVO = new ArtcomVO();
//		artcomVO.setArt_id("ART003");
//		artcomVO.setMem_id("M000006");
//		artcomVO.setArt_msg_cnt("lorem lorem lorem");
//		artcomVO.setArt_msg_pic(pic);
//		artcomVO.setArt_msg_like_num(5);
//		dao.INSERT(artcomVO);
		artcomVO.setArt_msg_like_num(20);
		artcomVO.setArt_msg_id("ARTCOM226");
		dao.updatelike(artcomVO);
//		ArtcomVO artcom = new ArtcomVO();
//		artcom.setArt_msg_cnt("���正��");
//		artcom.setArt_msg_pic(pic2);
//		artcom.setArt_msg_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
//		artcom.setArt_msg_like_num(8);
//		artcom.setArt_msg_id("ARTCOM208");
//		dao.UPDATE(artcom);
		
//		dao.DELETE("ARTCOM209");
		
//		ArtcomVO artcom2 = dao.findByPrimaryKey("ARTCOM207");
//		System.out.print(artcom2.getArt_msg_id()+" , ");
//		System.out.print(artcom2.getArt_id()+" , ");
//		System.out.print(artcom2.getMem_id()+" , ");
//		System.out.print(artcom2.getArt_msg_cnt()+" , ");
//		System.out.println("==============="+artcom2.getArt_msg_pic()+"=================");
//		System.out.println(artcom2.getArt_msg_date()+" , ");
//		System.out.print(artcom2.getArt_msg_like_num());
//		System.out.println("=================================================");
		
		List<ArtcomVO> list = dao.getByartid("ART002");
		for(ArtcomVO artcom4 : list) {
			System.out.print(artcom4.getArt_msg_id()+" , ");
			System.out.print(artcom4.getArt_id()+" , ");
			System.out.print(artcom4.getMem_id()+" , ");
			System.out.print(artcom4.getArt_msg_cnt()+" , ");
			System.out.println("==============="+artcom4.getArt_msg_pic()+"=================");
			System.out.println(artcom4.getArt_msg_date()+" , ");
			System.out.print(artcom4.getArt_msg_like_num());
			System.out.println("=================================================");
		}
		
	}

	@Override
	public List<ArtcomVO> getByartid(String art_id) {
		List<ArtcomVO> list = new ArrayList<ArtcomVO>();
		ArtcomVO artcomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GETBYART_ID);
			
			pstmt.setString(1, art_id);
			
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				artcomVO = new ArtcomVO();
				artcomVO.setArt_msg_id(rs.getString("art_msg_id"));
				artcomVO.setArt_id(rs.getString("art_id"));
				artcomVO.setMem_id(rs.getString("mem_id"));
				artcomVO.setArt_msg_cnt(rs.getString("art_msg_cnt"));
				artcomVO.setArt_msg_pic(rs.getBytes("art_msg_pic"));
				artcomVO.setArt_msg_date(rs.getTimestamp("art_msg_date"));
				artcomVO.setArt_msg_like_num(rs.getInt("art_msg_like_num"));
				list.add(artcomVO);
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

	@Override
	public void updatelike(ArtcomVO art_comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATELIKE);
			
			pstmt.setInt(1, art_comVO.getArt_msg_like_num());
			pstmt.setString(2, art_comVO.getArt_msg_id());
			
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
}
