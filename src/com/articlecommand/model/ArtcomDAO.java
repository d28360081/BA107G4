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
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ArtcomDAO implements ArtcomVO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT=
			"INSERT INTO ARTICLE_COMMENT(art_msg_id,art_id,mem_id,art_msg_cnt,art_msg_pic) VALUES ('ARTCOM'||LPAD(to_char(ARTMSG_SEQ.NEXTVAL),3,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT art_msg_id,art_id,mem_id,art_msg_cnt,art_msg_pic,to_char(art_msg_date,'yyyy-mm-dd hh24:mm:ss')art_msg_date,art_msg_like_num FROM ARTICLE_COMMENT ORDER BY art_msg_id";
	private static final String GET_ONE_STMT=
			"SELECT art_msg_id,art_id,mem_id,art_msg_cnt,art_msg_pic,to_char(art_msg_date,'yyyy-mm-dd hh24:mm:ss')art_msg_date,art_msg_like_num FROM ARTICLE_COMMENT WHERE art_msg_id=?";
	private static final String DELETE=
			"DELETE FROM ARTICLE_COMMENT WHERE art_msg_id=?";
	private static final String UPDATE=
			"UPDATE ARTICLE_COMMENT set art_msg_cnt=?,art_msg_pic=?,art_msg_date=?,art_msg_like_num=? WHERE art_msg_id=?";
	private static final String GETBYART_ID=
			"SELECT art_msg_id,art_id,mem_id,art_msg_cnt,art_msg_pic,to_char(art_msg_date,'yyyy-mm-dd hh24:mm:ss')art_msg_date,art_msg_like_num FROM ARTICLE_COMMENT WHERE art_id=?";
	private static final String UPDATELIKE=
			"UPDATE ARTICLE_COMMENT set art_msg_like_num=? WHERE art_msg_id=?";
	@Override
	public void insert(ArtcomVO art_comVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, art_comVO.getArt_id());
			pstmt.setString(2, art_comVO.getMem_id());
			pstmt.setString(3, art_comVO.getArt_msg_cnt());
			pstmt.setBytes(4, art_comVO.getArt_msg_pic());
			
			
			pstmt.executeUpdate();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, art_msg_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, art_comVO.getArt_msg_cnt());
			pstmt.setBytes(2, art_comVO.getArt_msg_pic());
			pstmt.setTimestamp(3, art_comVO.getArt_msg_date());
			pstmt.setInt(4, art_comVO.getArt_msg_like_num());
			pstmt.setString(5, art_comVO.getArt_msg_id());
			
			
			pstmt.executeUpdate();
		}  catch (SQLException se) {
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

			con = ds.getConnection();
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
		}  catch (SQLException se) {
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

			con = ds.getConnection();
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
		}  catch (SQLException se) {
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

	@Override
	public List<ArtcomVO> getByartid(String art_id) {
		List<ArtcomVO> list = new ArrayList<ArtcomVO>();
		ArtcomVO artcomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATELIKE);
			
			pstmt.setInt(1, art_comVO.getArt_msg_like_num());
			pstmt.setString(2, art_comVO.getArt_msg_id());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
