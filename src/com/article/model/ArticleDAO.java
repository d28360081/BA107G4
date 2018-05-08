package com.article.model;

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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ArticleDAO implements ArticleVO_interface{
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
			"INSERT INTO ARTICLE(art_id,mem_id,art_tlt,art_cnt,art_pic1,art_pic2,art_pic3) VALUES ('ART'||LPAD(to_char(art_seq.nextval),3,'0'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT art_id,mem_id,art_tlt,to_char(art_date,'yyyy-mm-dd hh24:mm:ss')art_date,art_cnt,art_pic1,art_pic2,art_pic3,like_num,art_sts FROM ARTICLE where art_sts='正常' ORDER BY art_id DESC";
	private static final String GET_ONE_STMT=
			"SELECT art_id,mem_id,art_tlt,to_char(art_date,'yyyy-mm-dd hh24:mm:ss')art_date,art_cnt,art_pic1,art_pic2,art_pic3,like_num,art_sts FROM ARTICLE WHERE art_id=? and art_sts='正常'";
	private static final String DELETE=
			"DELETE FROM ARTICLE WHERE art_id=?";
	private static final String UPDATE=
			"UPDATE ARTICLE set art_tlt=?,art_date=?,art_cnt=?,art_pic1=?,art_pic2=?,art_pic3=?,like_num=?,art_sts=? WHERE art_id=?";
	private static final String GETBYMEM_ID=
			"SELECT art_id,mem_id,art_tlt,to_char(art_date,'yyyy-mm-dd hh24:mm:ss')art_date,art_cnt,art_pic1,art_pic2,art_pic3,like_num,art_sts FROM ARTICLE WHERE mem_id=? and art_sts='正常'";
	private static final String UPDATELIKE=
			"UPDATE ARTICLE set like_num=? WHERE art_id=?";
	private static final String UPDATESTATUS=
			"UPDATE ARTICLE set art_sts=? Where art_id=?";
	
	public void insert(ArticleVO articleVO){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, articleVO.getMem_id());
			pstmt.setString(2, articleVO.getArt_tlt());
			pstmt.setString(3,articleVO.getArt_cnt());
			pstmt.setBytes(4, articleVO.getArt_pic1());
			pstmt.setBytes(5, articleVO.getArt_pic2());
			pstmt.setBytes(6, articleVO.getArt_pic3());
			
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
	public void update(ArticleVO articleVO){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,articleVO.getArt_tlt());
			pstmt.setTimestamp(2, articleVO.getArt_date());
			pstmt.setString(3,articleVO.getArt_cnt());
			pstmt.setBytes(4, articleVO.getArt_pic1());
			pstmt.setBytes(5, articleVO.getArt_pic2());
			pstmt.setBytes(6, articleVO.getArt_pic3());
			pstmt.setInt(7,articleVO.getLike_num());
			pstmt.setString(8, articleVO.getArt_sts());
			pstmt.setString(9,articleVO.getArt_id());
			
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
	public void delete(String art_id){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, art_id);
			
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
	public ArticleVO findByPrimaryKey(String	art_id){
		ArticleVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, art_id);

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				artVO = new ArticleVO();
				artVO.setArt_id(rs.getString("art_id"));
				artVO.setMem_id(rs.getString("mem_id"));
				artVO.setArt_tlt(rs.getString("art_tlt"));
				artVO.setArt_date(rs.getTimestamp("art_date"));
				artVO.setArt_cnt(rs.getString("art_cnt"));
				artVO.setArt_pic1(rs.getBytes("art_pic1"));
				artVO.setArt_pic2(rs.getBytes("art_pic2"));
				artVO.setArt_pic3(rs.getBytes("art_pic3"));
				artVO.setLike_num(rs.getInt("like_num"));
				artVO.setArt_sts(rs.getString("art_sts"));
			}
			
			
			// Handle any driver errors
		}  catch (SQLException se) {
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
		return artVO;
	}
	public List<ArticleVO> getAll(){
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO artVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				artVO = new ArticleVO();
				artVO.setArt_id(rs.getString("art_id"));
				artVO.setMem_id(rs.getString("mem_id"));
				artVO.setArt_tlt(rs.getString("art_tlt"));
				artVO.setArt_date(rs.getTimestamp("art_date"));
				artVO.setArt_cnt(rs.getString("art_cnt"));
				artVO.setArt_pic1(rs.getBytes("art_pic1"));
				artVO.setArt_pic2(rs.getBytes("art_pic2"));
				artVO.setArt_pic3(rs.getBytes("art_pic3"));
				artVO.setLike_num(rs.getInt("like_num"));
				artVO.setArt_sts(rs.getString("art_sts"));
				list.add(artVO);
			}
			// Handle any driver errors
		}  catch (SQLException se) {
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
		byte[] buffer = new byte[8192];//蝺抵��
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	
	@Override
	public List<ArticleVO> findBymem(String mem_id) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GETBYMEM_ID);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				artVO = new ArticleVO();
				artVO.setArt_id(rs.getString("art_id"));
				artVO.setMem_id(rs.getString("mem_id"));
				artVO.setArt_tlt(rs.getString("art_tlt"));
				artVO.setArt_date(rs.getTimestamp("art_date"));
				artVO.setArt_cnt(rs.getString("art_cnt"));
				artVO.setArt_pic1(rs.getBytes("art_pic1"));
				artVO.setArt_pic2(rs.getBytes("art_pic2"));
				artVO.setArt_pic3(rs.getBytes("art_pic3"));
				artVO.setLike_num(rs.getInt("like_num"));
				artVO.setArt_sts(rs.getString("art_sts"));
				list.add(artVO);
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
	public void updateLike(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATELIKE);
			
			pstmt.setInt(1 , articleVO.getLike_num());
			pstmt.setString(2, articleVO.getArt_id());
			
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
	public void updatestatus(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS);
			
			pstmt.setString(1 , articleVO.getArt_sts());
			pstmt.setString(2, articleVO.getArt_id());
			
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
	
}
