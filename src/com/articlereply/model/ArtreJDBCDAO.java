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

public class ArtreJDBCDAO implements ArtreVO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";
	
	private static final String INSERT_STMT=
			"INSERT INTO ARTICLE_REPLY (re_id,art_id,mem_id,re_cnt,re_pic,re_sts) VALUES ('ARTRE'||LPAD(TO_CHAR(ARTMSG_SEQ.NEXTVAL),3,'0'),?,?,?,?,?)";
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setString(1, art_reVO.getArt_id());
				pstmt.setString(2, art_reVO.getMem_id());
				pstmt.setString(3, art_reVO.getRe_cnt());
				pstmt.setBytes(4, art_reVO.getRe_pic());
				pstmt.setString(5, art_reVO.getRe_sts());
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
	public void update(ArtreVO art_reVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, art_reVO.getRe_cnt());
			pstmt.setTimestamp(2, art_reVO.getRe_date());
			pstmt.setBytes(3, art_reVO.getRe_pic());
			pstmt.setString(4, art_reVO.getRe_sts());
			pstmt.setString(5, art_reVO.getRe_id());
			
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
	public void delete(String re_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, re_id);
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
	public ArtreVO findByPrimaryKey(String re_id) {

		ArtreVO artreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		ArtreJDBCDAO dao = new ArtreJDBCDAO();
//		byte[] pic = getPictureByteArray("image/12.jpg");
//		byte[] pic2 = getPictureByteArray("image/100.jpg");
		
		ArtreVO artre = new ArtreVO();
//		artre.setArt_id("ART004");
//		artre.setMem_id("M000007");
//		artre.setRe_cnt("擐葛憭��恥銝�撟�4000�隞乩��羲憯怠側��鞈��銝�撟�1000���恥嚗�死敺�����羲憯怠側����������撟港�憟賢嗾�����恥嚗��遣���蔣������餈������");
//		artre.setRe_pic(pic);
//		artre.setRe_sts("甇�撣�");
//		dao.INSERT(artre);
		
//		ArtreVO artre2 = new ArtreVO();
//		artre2.setRe_cnt("憒��予銝���������甇◤鋆賭�靘停蝞�����雿���敺閫�������犖撠勗����單��撠梁瘜摰��������閫豢��璅�迨撠������之憭找�憭拇�憭�甇�隢�隞�暻潭��◤撖怠靘閬�����停蝞停蝞��僑朣⊥��之銋���蜇銋末憟��之璁�憭拇�憭����������鈭����絞閮�葆銝������憭拙像��之璁6~10擐����府���銝��擐�����之摰�");
//		artre2.setRe_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
//		artre2.setRe_pic(pic2);
//		artre2.setRe_sts("甇�撣�");
//		artre2.setRe_id("ARTRE209");
//		dao.UPDATE(artre2);
		
//		dao.DELETE("ARTRE209");
//		artre.setRe_id("ARTRE200");
//		artre.setRe_sts("下架");
//		dao.updatests(artre);
//		ArtreVO artre3 = dao.findByPrimaryKey("ARTRE203");
//		System.out.print(artre3.getRe_id()+" , ");
//		System.out.print(artre3.getArt_id()+" , ");
//		System.out.print(artre3.getMem_id()+" , ");
//		System.out.println(artre3.getRe_cnt()+" , ");
//		System.out.print(artre3.getRe_date()+" , ");
//		System.out.print(artre3.getRe_pic()+" , ");
//		System.out.print(artre3.getRe_sts());
//		System.out.println("=======================");
		
//		List<ArtreVO> list = dao.getByMem("M000001");
//		for(ArtreVO artre4 : list) {
//			System.out.print(artre4.getRe_id()+" , ");
//			System.out.print(artre4.getArt_id()+" , ");
//			System.out.print(artre4.getMem_id()+" , ");
//			System.out.println(artre4.getRe_cnt()+" , ");
//			System.out.print(artre4.getRe_date()+" , ");
//			System.out.print(artre4.getRe_pic()+" , ");
//			System.out.print(artre4.getRe_sts());
//			System.out.println("=======================");
//		}
		List<ArtreVO> list = dao.getByArt_id("ART001");
		for(ArtreVO artre4 : list) {
			System.out.print(artre4.getRe_id()+" , ");
			System.out.print(artre4.getArt_id()+" , ");
			System.out.print(artre4.getMem_id()+" , ");
			System.out.println(artre4.getRe_cnt()+" , ");
			System.out.print(artre4.getRe_date()+" , ");
			System.out.print(artre4.getRe_pic()+" , ");
			System.out.print(artre4.getRe_sts());
			System.out.println("=======================");
		}
	
	}

	@Override
	public List<ArtreVO> getByMem(String Mem_id) {
		List<ArtreVO> list = new ArrayList<ArtreVO>();
		ArtreVO artreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<ArtreVO> getByArt_id(String art_id) {
		List<ArtreVO> list = new ArrayList<ArtreVO>();
		ArtreVO artreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void updatests(ArtreVO art_reVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTS);
			
			pstmt.setString(1 , art_reVO.getRe_sts());
			pstmt.setString(2, art_reVO.getRe_id());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
