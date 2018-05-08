package com.articlereply.model;

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

public class ArtreDAO implements ArtreVO_interface{

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
			"INSERT INTO ARTICLE_REPLY (re_id,art_id,mem_id,re_cnt,re_pic) VALUES ('ARTRE'||LPAD(TO_CHAR(ARTMSG_SEQ.NEXTVAL),3,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT * FROM ARTICLE_REPLY where re_sts='正常' ORDER BY re_id";
	private static final String GET_ONE_STMT=
			"SELECT re_id,art_id,mem_id,re_cnt,to_char(re_date,'yyyy-mm-dd hh24:mm:ss')re_date,re_pic,re_sts FROM ARTICLE_REPLY WHERE re_id=? and re_sts='正常'";
	private static final String DELETE=
			"DELETE FROM ARTICLE_REPLY WHERE re_id=?";
	private static final String UPDATE=
			"UPDATE ARTICLE_REPLY set re_cnt=?,re_date=?,re_pic=?,re_sts=? WHERE re_id=?";
	private static final String GETBYMEM=
			"SELECT re_id,art_id,mem_id,re_cnt,to_char(re_date,'yyyy-mm-dd hh24:mm:ss')re_date,re_pic,re_sts FROM ARTICLE_REPLY WHERE MEM_ID=? and re_sts='正常' ORDER BY RE_DATE";
	private static final String GETBYART=
			"SELECT re_id,art_id,mem_id,re_cnt,to_char(re_date,'yyyy-mm-dd hh24:mm:ss')re_date,re_pic,re_sts FROM ARTICLE_REPLY WHERE ART_ID=? and re_sts='正常' ORDER BY RE_DATE";
	private static final String UPDATESTS=
			"UPDATE ARTICLE_REPLY set re_sts=? WHERE re_id=?";
	@Override
	public void insert(ArtreVO art_reVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, art_reVO.getArt_id());
				pstmt.setString(2, art_reVO.getMem_id());
				pstmt.setString(3, art_reVO.getRe_cnt());
				pstmt.setBytes(4, art_reVO.getRe_pic());
				
			pstmt.executeUpdate();
		} 
			// Handle any SQL errors
		 catch (SQLException se) {
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
	public void update(ArtreVO art_reVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, art_reVO.getRe_cnt());
			pstmt.setTimestamp(2, art_reVO.getRe_date());
			pstmt.setBytes(3, art_reVO.getRe_pic());
			pstmt.setString(4, art_reVO.getRe_sts());
			pstmt.setString(5, art_reVO.getRe_id());
			
			pstmt.executeUpdate();
		} 
			// Handle any SQL errors
		 catch (SQLException se) {
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
	public void delete(String re_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, re_id);
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
	public ArtreVO findByPrimaryKey(String re_id) {

		ArtreVO artreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, re_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artreVO = new ArtreVO();
				artreVO.setRe_id(rs.getString("re_id"));
				artreVO.setArt_id(rs.getString("art_id"));
				artreVO.setMem_id(rs.getString("mem_id"));
				artreVO.setRe_cnt(rs.getString("re_cnt"));
				artreVO.setRe_date(rs.getTimestamp("re_date"));
				artreVO.setRe_pic(rs.getBytes("re_pic"));
				artreVO.setRe_sts(rs.getString("re_sts"));
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

		return artreVO;
	}

	@Override
	public List<ArtreVO> getAll() {
		List<ArtreVO> list = new ArrayList<ArtreVO>();
		ArtreVO artreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artreVO = new ArtreVO();
				artreVO.setRe_id(rs.getString("re_id"));
				artreVO.setArt_id(rs.getString("art_id"));
				artreVO.setMem_id(rs.getString("mem_id"));
				artreVO.setRe_cnt(rs.getString("re_cnt"));
				artreVO.setRe_date(rs.getTimestamp("re_date"));
				artreVO.setRe_pic(rs.getBytes("re_pic"));
				artreVO.setRe_sts(rs.getString("re_sts"));
				list.add(artreVO);
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
	public List<ArtreVO> getByMem(String Mem_id) {
		List<ArtreVO> list = new ArrayList<ArtreVO>();
		ArtreVO artreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GETBYMEM);
			
			pstmt.setString(1, Mem_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artreVO = new ArtreVO();
				artreVO.setRe_id(rs.getString("re_id"));
				artreVO.setArt_id(rs.getString("art_id"));
				artreVO.setMem_id(rs.getString("mem_id"));
				artreVO.setRe_cnt(rs.getString("re_cnt"));
				artreVO.setRe_date(rs.getTimestamp("re_date"));
				artreVO.setRe_pic(rs.getBytes("re_pic"));
				artreVO.setRe_sts(rs.getString("re_sts"));
				list.add(artreVO);
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

	@Override
	public List<ArtreVO> getByArt_id(String art_id) {
		List<ArtreVO> list = new ArrayList<ArtreVO>();
		ArtreVO artreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETBYART);

			pstmt.setString(1, art_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				artreVO = new ArtreVO();
				artreVO.setRe_id(rs.getString("re_id"));
				artreVO.setArt_id(rs.getString("art_id"));
				artreVO.setMem_id(rs.getString("mem_id"));
				artreVO.setRe_cnt(rs.getString("re_cnt"));
				artreVO.setRe_date(rs.getTimestamp("re_date"));
				artreVO.setRe_pic(rs.getBytes("re_pic"));
				artreVO.setRe_sts(rs.getString("re_sts"));
				list.add(artreVO);
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

	@Override
	public void updatests(ArtreVO art_reVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTS);
			
			pstmt.setString(1 , art_reVO.getRe_sts());
			pstmt.setString(2, art_reVO.getRe_id());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
