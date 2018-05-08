package com.chtmsg.model;

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

public class ChtmsgDAO implements Cht_msgVO_interface{
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

			con = ds.getConnection();

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, cht_msgVO.getMem_id_send());
			pstmt.setString(2, cht_msgVO.getMem_id_get());
			pstmt.setInt(3, cht_msgVO.getMsg_sts());
			pstmt.setString(4, cht_msgVO.getMsg_cnt());
			pstmt.executeUpdate();

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
	}

	@Override
	public void update(ChtmsgVO cht_msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setTimestamp(1,cht_msgVO.getMsg_time());
			pstmt.setInt(2,cht_msgVO.getMsg_sts());
			pstmt.setString(3, cht_msgVO.getMsg_cnt());
			pstmt.setString(4, cht_msgVO.getMsg_id());
			
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
	public void delete(String msg_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, msg_id);
			
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
	public ChtmsgVO findByPrimaryKey(String msg_id) {
		ChtmsgVO chtmsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

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

			con = ds.getConnection();

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
	public void changests(ChtmsgVO cht_msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTS);
			
			pstmt.setInt(1,cht_msgVO.getMsg_sts());
			pstmt.setString(2, cht_msgVO.getMsg_id());
			
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
