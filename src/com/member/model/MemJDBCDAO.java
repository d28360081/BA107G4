package com.member.model;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemJDBCDAO implements MemDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";
	
	private static final String INSERT_STMT=
			"INSERT INTO MEMBER (mem_id,acc,psw,bir_dt,address,mem_pay,mem_email,mem_name,mem_photo) VALUES('M'||LPAD(to_char(MEM_SEQ.NEXTVAL),6,'0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT mem_id,acc,psw,to_char(bir_dt,'yyyy-mm-dd')bir_dt,address,bonus,mem_pay,mem_eva,mem_photo,po_auth,st_auth,com_auth,par_auth,mem_email,mem_name FROM member order by mem_id";
	private static final String GET_ONE_STMT=
			"SELECT mem_id,acc,psw,to_char(bir_dt,'yyyy-mm-dd')bir_dt,address,bonus,mem_pay,mem_eva,mem_photo,po_auth,st_auth,com_auth,par_auth,mem_email,mem_name FROM member WHERE mem_id=?";
	private static final String DELETE=
			"DELETE FROM member WHERE mem_id=?";
	private static final String UPDATE=
			"UPDATE member SET acc=?,psw=?,bir_dt=?,address=?,mem_pay=?,mem_photo=?,mem_email=?,mem_name=? WHERE mem_id=?";
	private static final String CHECK_ACC_EXIST=
			"SELECT ACC FROM MEMBER WHERE ACC=?";
	private static final String FIND_BY_ACCPSW=
			"SELECT * FROM MEMBER WHERE ACC=? AND PSW=?";
	private static final String Get_Pic=
			"SELECT mem_photo FROM MEMBER WHERE acc=? ";
	private static final String FINDUSER=
			"SELECT ACC,PSW FROM MEMBER";
	private static final String GET_ONEBYACC=
			"SELECT mem_id,acc,psw,to_char(bir_dt,'yyyy-mm-dd')bir_dt,address,bonus,mem_pay,mem_eva,mem_photo,po_auth,st_auth,com_auth,par_auth,mem_email,mem_name FROM member WHERE acc=?";
	private static final String UPDATEBS=
			"UPDATE member SET bonus=? WHERE mem_id=?";
	private static final String UPDATEAUTH=
			"UPDATE member SET po_auth=?,st_auth=?,com_auth=?,par_auth=? WHERE mem_id=?";
	@Override
	public void insert(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, memVO.getAcc());
			pstmt.setString(2, memVO.getPsw());
			pstmt.setDate(3, memVO.getBir_dt());
			pstmt.setString(4,memVO.getAddress());
			pstmt.setString(5, memVO.getMem_pay());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_name());
			pstmt.setBytes(8,memVO.getMem_photo());
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
	public void update(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, memVO.getAcc());
			pstmt.setString(2, memVO.getPsw());
			pstmt.setDate(3, memVO.getBir_dt());
			pstmt.setString(4,memVO.getAddress());
			pstmt.setString(5, memVO.getMem_pay());
			pstmt.setBytes(6,memVO.getMem_photo());
			pstmt.setString(7, memVO.getMem_email());
			pstmt.setString(8, memVO.getMem_name());
			pstmt.setString(9, memVO.getMem_id());
			
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
	public void delete(String mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_id);

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
	public MemVO findByPrimaryKey(String mem_id) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_id);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setAcc(rs.getString("acc"));
				memVO.setPsw(rs.getString("psw"));
				memVO.setBir_dt(rs.getDate("bir_dt"));
				memVO.setAddress(rs.getString("address"));
				memVO.setBonus(rs.getInt("bonus"));
				memVO.setMem_pay(rs.getString("mem_pay"));
				memVO.setMem_eva(rs.getInt("mem_eva"));
				memVO.setMem_photo(rs.getBytes("mem_photo"));
				memVO.setPo_auth(rs.getInt("po_auth"));
				memVO.setSt_auth(rs.getInt("st_auth"));
				memVO.setCom_auth(rs.getInt("com_auth"));
				memVO.setPar_auth(rs.getInt("par_auth"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_name(rs.getString("mem_name"));
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

		return memVO;
	}
	@Override
	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setAcc(rs.getString("acc"));
				memVO.setPsw(rs.getString("psw"));
				memVO.setBir_dt(rs.getDate("bir_dt"));
				memVO.setAddress(rs.getString("address"));
				memVO.setBonus(rs.getInt("bonus"));
				memVO.setMem_pay(rs.getString("mem_pay"));
				memVO.setMem_eva(rs.getInt("mem_eva"));
				memVO.setMem_photo(rs.getBytes("mem_photo"));
				memVO.setPo_auth(rs.getInt("po_auth"));
				memVO.setSt_auth(rs.getInt("st_auth"));
				memVO.setCom_auth(rs.getInt("com_auth"));
				memVO.setPar_auth(rs.getInt("par_auth"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_name(rs.getString("mem_name"));
				list.add(memVO);
				
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
	
	
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];//��嚙踝蕭
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	}
	public static void main(String args[]) throws IOException {
		MemJDBCDAO dao = new MemJDBCDAO();
//		byte[] pic = getPictureByteArray("image/12.jpg");
		MemVO mem = new MemVO();
//		mem.setAcc("qweeeee");
//		mem.setPsw("qweeeee");
//		mem.setBir_dt(java.sql.Date.valueOf("1987-08-07"));
//		mem.setAddress("aaaaaaaaaaaaaaaaaaaaaaaa");
//		mem.setBonus(8000);
//		mem.setMem_pay("���甈�");
//		mem.setMem_email("qqqqqqqqqqqqqqqqqqqqqqq@gmail.com");
//		mem.setMem_eva(3);
//		mem.setMem_photo(pic);
//		mem.setPo_auth(1);
//		mem.setSt_auth(1);
//		mem.setCom_auth(1);
//		mem.setPar_auth(1);
//		dao.insert(mem);
//		mem.setMem_id("M000012");
//		mem.setMem_pay("bbb");
//		mem.setBir_dt(java.sql.Date.valueOf("1987-08-07"));
//		mem.setPsw("bbb");
//		mem.setAddress("aaa");
//		mem.setAcc("aaa");
//		mem.setMem_email("wwwwww@gmail.com");
//		dao.update(mem);
//		dao.delete("M000012");
//		mem.setBonus(8000);
//		mem.setMem_id("M000001");
//		dao.updateBonus(mem);
//		mem.setPo_auth(0);
//		mem.setSt_auth(0);
//		mem.setCom_auth(0);
//		mem.setPar_auth(0);
//		mem.setMem_id("M000012");
//		dao.updateAuth(mem);
//		MemVO mem2 = dao.findByAcc("LALA2");
//		System.out.print(mem2.getMem_id()+",");
//		System.out.print(mem2.getAcc()+",");
//		System.out.print(mem2.getPsw()+",");
//		System.out.println(mem2.getBir_dt()+",");
//		System.out.print(mem2.getAddress()+",");
//		System.out.print(mem2.getBonus()+",");
//		System.out.print(mem2.getMem_pay()+",");
//		System.out.print(mem2.getMem_eva()+",");
//		System.out.println(mem2.getMem_photo());
//		System.out.println(mem2.getPo_auth()+",");
//		System.out.print(mem2.getSt_auth()+",");
//		System.out.print(mem2.getCom_auth()+",");
//		System.out.print(mem2.getPar_auth());
//		System.out.println(mem2.getMem_email()+",");
//		System.out.println(mem2.getMem_name());
//		System.out.println("==============================================");

//		System.out.println(dao.isMember("LALA", "LALA2"));
//		System.out.println(dao.isUserIdExist("LLA2"));
//		dao.readPic("ZZ887");
//		mem.setAcc("LALA2");
//		mem.setPsw("LALA2");
//		System.out.print(dao.authuser(mem));
	}


	public boolean isMember(String acc, String psw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isMember = false;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_ACCPSW);

			pstmt.setString(1, acc);
			pstmt.setString(2, psw);
			ResultSet rs = pstmt.executeQuery();
			isMember = rs.next();
			return isMember;

			// Handle any driver errors
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isMember;
	}

	@Override
	public boolean isUserIdExist(String acc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isUserIdExist = false;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHECK_ACC_EXIST);

			pstmt.setString(1, acc);
			ResultSet rs = pstmt.executeQuery();
			isUserIdExist = rs.next();
			return isUserIdExist;

			// Handle any driver errors
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isUserIdExist;
	}
	@Override
	public void readPic(String acc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		InputStream is = null;
        OutputStream os = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_Pic);
			
			pstmt.setString(1, acc);
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				is= rs.getBinaryStream("mem_photo");
				File file = new File("C:\\Users\\Java\\Documents\\picread",acc+".jpg");
				os = new FileOutputStream(file);
				byte[] buffer = new byte[4*1024];
				int len = 0;
				while((len = is.read(buffer))!=-1) {
					os.write(buffer, 0 , len);
				}
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch(FileNotFoundException fe) {
			fe.printStackTrace();
		} catch(IOException ie) {
			ie.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(is != null) {
				try {
					is.close();
				}catch( IOException ie) {
					ie.printStackTrace();
				}
			}
			if(os != null) {
				try {
					os.close();
				}catch( IOException ie) {
					ie.printStackTrace();
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
	public String authuser(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FINDUSER);
			
			String acc = memVO.getAcc();
			String psw = memVO.getPsw();
			
			String uacc= "";
			String upsw= "";
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				uacc = rs.getString("acc");
				upsw = rs.getString("psw");
				
				if(acc.equals(uacc) && psw.equals(upsw)) {
					return "action";
				}
			}
			// Handle any driver errors
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Valid Account or Password";
	}
	@Override
	public MemVO findByAcc(String acc) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONEBYACC);

			pstmt.setString(1, acc);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MemVO();
				memVO.setMem_id(rs.getString("mem_id"));
				memVO.setAcc(rs.getString("acc"));
				memVO.setPsw(rs.getString("psw"));
				memVO.setBir_dt(rs.getDate("bir_dt"));
				memVO.setAddress(rs.getString("address"));
				memVO.setBonus(rs.getInt("bonus"));
				memVO.setMem_pay(rs.getString("mem_pay"));
				memVO.setMem_eva(rs.getInt("mem_eva"));
				memVO.setMem_photo(rs.getBytes("mem_photo"));
				memVO.setPo_auth(rs.getInt("po_auth"));
				memVO.setSt_auth(rs.getInt("st_auth"));
				memVO.setCom_auth(rs.getInt("com_auth"));
				memVO.setPar_auth(rs.getInt("par_auth"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_name(rs.getString("mem_name"));
				
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

		return memVO;
	}
	@Override
	public void updateBonus(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEBS);
			
			pstmt.setInt(1, memVO.getBonus());
			pstmt.setString(2, memVO.getMem_id());
			
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
	public void updateAuth(MemVO memVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEAUTH);
			
			pstmt.setInt(1, memVO.getPo_auth());
			pstmt.setInt(2, memVO.getSt_auth());
			pstmt.setInt(3, memVO.getCom_auth());
			pstmt.setInt(4, memVO.getPar_auth());
			pstmt.setString(5, memVO.getMem_id());
			
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
}
