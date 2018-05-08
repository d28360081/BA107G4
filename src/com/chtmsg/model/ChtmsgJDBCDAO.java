package com.chtmsg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChtmsgJDBCDAO implements Cht_msgVO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";
	
	private static final String INSERT_STMT=
			"INSERT INTO CHATROOM_MESSAGE (msg_id,mem_id_send,mem_id_get,msg_sts,msg_cnt) VALUES ('CHAT'||LPAD(to_char(chat_seq.NEXTVAL),3,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT * FROM CHATROOM_MESSAGE";
	private static final String GET_ONE_STMT=
			"SELECT * FROM CHATROOM_MESSAGE WHERE msg_id=?";
	private static final String DELETE=
			"DELETE FROM CHATROOM_MESSAGE WHERE msg_id=?";
	private static final String UPDATE=
			"UPDATE CHATROOM_MESSAGE SET msg_time=?,msg_sts=?,msg_cnt=? WHERE msg_id=?";
	private static final String UPDATESTS=
			"UPDATE CHATROOM_MESSAGE set msg_sts=? where msg_id=?";
	@Override
	public void insert(ChtmsgVO cht_msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, cht_msgVO.getMem_id_send());
			pstmt.setString(2, cht_msgVO.getMem_id_get());
			pstmt.setInt(3, cht_msgVO.getMsg_sts());
			pstmt.setString(4, cht_msgVO.getMsg_cnt());
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
	public void update(ChtmsgVO cht_msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setTimestamp(1,cht_msgVO.getMsg_time());
			pstmt.setInt(2,cht_msgVO.getMsg_sts());
			pstmt.setString(3, cht_msgVO.getMsg_cnt());
			pstmt.setString(4, cht_msgVO.getMsg_id());
			
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
	public void delete(String msg_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, msg_id);
			
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
	public ChtmsgVO findByPrimaryKey(String msg_id) {
		ChtmsgVO chtmsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, msg_id);
			
			rs =pstmt.executeQuery();
			
			while(rs.next()){
				chtmsgVO = new ChtmsgVO();
				chtmsgVO.setMsg_id(rs.getString("msg_id"));
				chtmsgVO.setMem_id_send(rs.getString("mem_id_send"));
				chtmsgVO.setMem_id_get(rs.getString("mem_id_get"));
				chtmsgVO.setMsg_time(rs.getTimestamp("msg_time"));
				chtmsgVO.setMsg_sts(rs.getInt("msg_sts"));
				chtmsgVO.setMsg_cnt(rs.getString("msg_cnt"));
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
		return chtmsgVO;
	}

	@Override
	public List<ChtmsgVO> getAll() {

		List<ChtmsgVO> list = new ArrayList<ChtmsgVO>();
		ChtmsgVO chtmsgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				chtmsgVO = new ChtmsgVO();
				chtmsgVO.setMsg_id(rs.getString("msg_id"));
				chtmsgVO.setMem_id_send(rs.getString("mem_id_send"));
				chtmsgVO.setMem_id_get(rs.getString("mem_id_get"));
				chtmsgVO.setMsg_time(rs.getTimestamp("msg_time"));
				chtmsgVO.setMsg_sts(rs.getInt("msg_sts"));
				chtmsgVO.setMsg_cnt(rs.getString("msg_cnt"));
				list.add(chtmsgVO);
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
	
	public static void main(String[]args){
		ChtmsgJDBCDAO dao = new ChtmsgJDBCDAO();
		
		ChtmsgVO chtmsg = new ChtmsgVO();
		
//		chtmsg.setMem_id_send("M000007");
//		chtmsg.setMem_id_get("M000010");
//		chtmsg.setMsg_time(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
//		chtmsg.setMsg_sts(0);
//		chtmsg.setMsg_cnt("hi hi hi hi ");
//		dao.INSERT(chtmsg);
		
//		chtmsg.setMsg_time(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
//		chtmsg.setMsg_sts(1);
//		chtmsg.setMsg_cnt("22222");
//		chtmsg.setMsg_id("CHAT105");
//		dao.UPDATE(chtmsg);
		chtmsg.setMsg_sts(1);
		chtmsg.setMsg_id("CHAT101");
		dao.changests(chtmsg);
//		dao.DELETE("CHAT105");
		
//		ChtmsgVO cht = dao.findByPrimaryKey("CHAT103");
//		System.out.print(cht.getMsg_id()+" , ");
//		System.out.print(cht.getMem_id_send()+" , ");
//		System.out.print(cht.getMem_id_get()+" , ");
//		System.out.print(cht.getMsg_time()+" , ");
//		System.out.print(cht.getMsg_sts()+" , ");
//		System.out.print(cht.getMsg_cnt());
		
//		List<ChtmsgVO>list = dao.getAll();
//		for(ChtmsgVO cht2 : list){
//			System.out.print(cht2.getMsg_id()+" , ");
//			System.out.print(cht2.getMem_id_send()+" , ");
//			System.out.print(cht2.getMem_id_get()+" , ");
//			System.out.print(cht2.getMsg_time()+" , ");
//			System.out.print(cht2.getMsg_sts()+" , ");
//			System.out.println(cht2.getMsg_cnt());
//			System.out.println("==================================");
//		}
		
	}

	@Override
	public void changests(ChtmsgVO cht_msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTS);
			
			pstmt.setInt(1,cht_msgVO.getMsg_sts());
			pstmt.setString(2, cht_msgVO.getMsg_id());
			
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
