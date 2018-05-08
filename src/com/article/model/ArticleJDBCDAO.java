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

import com.member.model.MemVO;



public class ArticleJDBCDAO implements ArticleVO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";
	
	private static final String INSERT_STMT=
			"INSERT INTO ARTICLE(art_id,mem_id,art_tlt,art_cnt,art_pic1,art_pic2,art_pic3) VALUES ('ART'||LPAD(to_char(art_seq.nextval),3,'0'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT art_id,mem_id,art_tlt,to_char(art_date,'yyyy-mm-dd hh:mm:ss')art_date,art_cnt,art_pic1,art_pic2,art_pic3,like_num,art_sts FROM ARTICLE where art_sts='正常' ORDER BY art_id DESC";
	private static final String GET_ONE_STMT=
			"SELECT art_id,mem_id,art_tlt,to_char(art_date,'yyyy-mm-dd hh:mm:ss')art_date,art_cnt,art_pic1,art_pic2,art_pic3,like_num,art_sts FROM ARTICLE WHERE art_id=? and art_sts='正常'";
	private static final String DELETE=
			"DELETE FROM ARTICLE WHERE art_id=?";
	private static final String UPDATE=
			"UPDATE ARTICLE set art_tlt=?,art_date=?,art_cnt=?,art_pic1=?,art_pic2=?,art_pic3=?,like_num=?,art_sts=? WHERE art_id=?";
	private static final String GETBYMEM_ID=
			"SELECT art_id,mem_id,art_tlt,to_char(art_date,'yyyy-mm-dd hh:mm:ss')art_date,art_cnt,art_pic1,art_pic2,art_pic3,like_num,art_sts FROM ARTICLE WHERE mem_id=? and art_sts='正常'";
	private static final String UPDATELIKE=
			"UPDATE ARTICLE set like_num=? WHERE art_id=?";
	private static final String UPDATESTATUS=
			"UPDATE ARTICLE set art_sts=? Where art_id=?";
	public void insert(ArticleVO articleVO){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, articleVO.getMem_id());
			pstmt.setString(2, articleVO.getArt_tlt());
			pstmt.setString(3,articleVO.getArt_cnt());
			pstmt.setBytes(4, articleVO.getArt_pic1());
			pstmt.setBytes(5, articleVO.getArt_pic2());
			pstmt.setBytes(6, articleVO.getArt_pic3());
			
			
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
	public void update(ArticleVO articleVO){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String art_id){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, art_id);
			
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
	public ArticleVO findByPrimaryKey(String	art_id){
		ArticleVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return artVO;
	}
	public List<ArticleVO> getAll(){
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO artVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	
	public static void main (String[]args) throws IOException {
		ArticleJDBCDAO dao = new ArticleJDBCDAO();
		 ArticleVO art = new ArticleVO();
//		 byte[] pic = getPictureByteArray("image/100.jpg");
//		 byte[] pic2 = getPictureByteArray("image/101.jpg");
//		 byte[] pic3 = getPictureByteArray("image/102.jpg");

		
//		 art.setMem_id("M000002");
//		 art.setArt_tlt("有沒有未聞花名的八卦");
//		 art.setArt_cnt("Aniplex、富士電視台及A-1 Pictures於2010年12月啟動原創動畫企劃「ANOHANA PROJECT」，其中發布的監督、劇本和人物設定均出自2008年人氣動畫《虎與龍》的製作班底。這是監督長井龍雪第一次擔任原創動畫的監督工作。小說由劇本作者岡田麿里在月刊《DA VINCI》2011年3月號起連載。 舞台設定位於埼玉縣西邊的秩父市，出現不少真實場景以及建築，當中秩父橋與實正山定林寺更成為故事中重要的場景，由樂團Galileo Galilei主唱的開場曲「藍色書籤（青い栞）」真人PV亦為配合主題於秩父市內拍攝。2010年12月在西武鐵道西武線列車車廂貼上「超和平Busters」海報，在放送開始後，車站內掲示節目宣傳海報。秩父市地方組織和商會亦活躍於藉助動畫名聲為當地的旅遊事業作大量宣傳，更請來動畫的聲優擔任當地大型活動的嘉賓，吸引大量動漫迷到訪。");
//		 art.setArt_pic1(pic);
//		 art.setArt_pic2(pic2);
//		 art.setArt_pic3(pic3);

//		 dao.insert(art);
//		 ArticleVO art2 = new ArticleVO();
//		 
//		 art2.setArt_tlt("U咪U八卦3");
//		 art2.setArt_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
//		 art2.setArt_cnt("《我们仍未知道那天所看见的花的名字》是由日本动画公司A-1 Pictures制作的原创电视动画，于2011年4月14日起在日本富士电视台的“noitaminA”节目播出，全11集。官方的简称为“那朵花（あの花）”，在中国通常又被称为“未闻花名”。同名剧场版于2013年8月31日公开播映。");
//		 art2.setArt_pic1(pic2);
//		 art2.setArt_pic2(pic3);
//		 art2.setArt_pic3(pic);
//		 art2.setLike_num(20);
//		 art2.setArt_sts("正常");
//		 art2.setArt_id("ART005");
//		 dao.UPDATE(art2);
		 
//		 art.setArt_id("ART012");
//		 art.setArt_sts("下架");;
//		 dao.updatestatus(art);
//		 dao.DELETE("ART005");
		 
//		 ArticleVO art4 = dao.findBymem("M000001");
//		 System.out.print(art4.getArt_id()+" , ");
//		 System.out.print(art4.getMem_id()+" , ");
//		 System.out.print(art4.getArt_tlt()+" , ");
//		 System.out.print(art4.getArt_date()+" , ");
//		 System.out.println(art4.getArt_cnt());
//		 System.out.println(art4.getArt_pic1());
//		 System.out.println(art4.getArt_pic2());
//		 System.out.println(art4.getArt_pic3());
//		 System.out.println(art4.getLike_num()+" , ");
//		 System.out.println(art4.getArt_sts());
		 
		 
		 List<ArticleVO>list = dao.findBymem("M000001");
		 for(ArticleVO art3 : list) {
			 System.out.println(art3.getArt_id()+" , ");
			 System.out.print(art3.getMem_id()+" , ");
			 System.out.print(art3.getArt_tlt()+" , ");
			 System.out.print(art3.getArt_date()+" , ");
			 System.out.println(art3.getArt_cnt());
			 System.out.println(art3.getArt_pic1());
			 System.out.println(art3.getArt_pic2());
			 System.out.println(art3.getArt_pic3());
			 System.out.println(art3.getLike_num()+" , ");
			 System.out.println(art3.getArt_sts());
		 }
		 
	}

	@Override
	public List<ArticleVO> findBymem(String mem_id) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATELIKE);
			
			pstmt.setInt(1 , articleVO.getLike_num());
			pstmt.setString(2, articleVO.getArt_id());
			
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
	public void updatestatus(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTATUS);
			
			pstmt.setString(1 , articleVO.getArt_sts());
			pstmt.setString(2, articleVO.getArt_id());
			
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
