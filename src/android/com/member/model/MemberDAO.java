package android.com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import android.com.main.ImageUtil;

public class MemberDAO implements MemberDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	static final String FIND_BY_ACCOUNT_PASWD = "SELECT * FROM MEMBER WHERE ACC = ? AND PSW = ?";
	private static final String UPDATE = "UPDATE member SET acc=?,psw=?,bir_dt=?,address=?,bonus=?,mem_pay=?,mem_eva=?,mem_photo=?,po_auth=?,st_auth=?,com_auth=?,par_auth=?,mem_email=? WHERE mem_id=?";

	private static final String FIND_BY_ID = "SELECT MEM_ID,ACC,PSW,BIR_DT,ADDRESS,BONUS,MEM_PAY,MEM_EVA,MEM_PHOTO,PO_AUTH,ST_AUTH,COM_AUTH,PAR_AUTH,MEM_EMAIL FROM MEMBER WHERE MEM_ID=?";
	private static final String SELECT_PIC = "SELECT MEM_PHOTO FROM MEMBER WHERE MEM_ID=?";

	@Override
	public boolean isMember(String userId, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isMember = false;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(FIND_BY_ACCOUNT_PASWD);
			ps.setString(1, userId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();/* 查詢資料庫 傳回資料列 */
			isMember = rs.next(); /* .next() 有資料true | 沒有false */
			return isMember;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isMember;

	}

	@Override
	public MemberVO findByAccandPsw(String account, String password) {
		MemberVO memVO = new MemberVO();

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(FIND_BY_ACCOUNT_PASWD);
			ps.setString(1, account);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();/* 查詢資料庫 傳回資料列 */
			while (rs.next()) {
				memVO.setMem_id(rs.getString(1));
				memVO.setAcc(rs.getString(2));
				memVO.setPsw(rs.getString(3));
				// 為了日期轉不出來
			
				memVO.setBir_dt(rs.getDate(4));
				// 為了日期轉不出來
				memVO.setAddress(rs.getString(5));
				memVO.setBonus(rs.getInt(6));
				memVO.setMem_pay(rs.getString(7));
				memVO.setMem_eva(rs.getInt(8));

				memVO.setMem_photo(ImageUtil.shrink(rs.getBytes(9), 300));
				memVO.setPo_auth(rs.getInt(10));
				memVO.setSt_auth(rs.getInt(11));
				memVO.setCom_auth(rs.getInt(12));
				memVO.setPar_auth(rs.getInt(13));
				memVO.setMem_email(rs.getString(14));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memVO;
	}

	@Override
	public boolean update(MemberVO membervo) {
		Connection conn;
		PreparedStatement pstmt;
		boolean isUpdate;
		conn = null;
		pstmt = null;
		isUpdate = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE member SET acc=?,psw=?,bir_dt=?,address=?,bonus=?,mem_pay=?,mem_eva=?,mem_photo=?,po_auth=?,st_auth=?,com_auth=?,par_auth=?,mem_email=? WHERE mem_id=?");
			pstmt.setString(1, membervo.getAcc());
			pstmt.setString(2, membervo.getPsw());
			pstmt.setDate(3, membervo.getBir_dt());
			pstmt.setString(4, membervo.getAddress());
			pstmt.setInt(5, membervo.getBonus().intValue());
			pstmt.setString(6, membervo.getMem_pay());
			pstmt.setInt(7, membervo.getMem_eva().intValue());
			pstmt.setBytes(8, membervo.getMem_photo());
			pstmt.setInt(9, membervo.getPo_auth().intValue());
			pstmt.setInt(10, membervo.getSt_auth().intValue());
			pstmt.setInt(11, membervo.getCom_auth().intValue());
			pstmt.setInt(12, membervo.getPar_auth().intValue());
			pstmt.setString(13, membervo.getMem_email());
			pstmt.setString(14, membervo.getMem_id());
			int count = pstmt.executeUpdate();
			isUpdate = count > 0 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return isUpdate;

	}

	@Override
	public MemberVO findById(String userid) {
		MemberVO memVO;
		memVO = new MemberVO();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(FIND_BY_ID);
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();/* 查詢資料庫 傳回資料列 */
			while (rs.next()) {
				memVO.setMem_id(rs.getString(1));
				memVO.setAcc(rs.getString(2));
				memVO.setPsw(rs.getString(3));
				//
				System.out.println(rs.getDate(4));
				memVO.setBir_dt(rs.getDate(4));
			
				//
				memVO.setAddress(rs.getString(5));
				memVO.setBonus(rs.getInt(6));
				memVO.setMem_pay(rs.getString(7));
				memVO.setMem_eva(rs.getInt(8));
				memVO.setMem_photo(ImageUtil.shrink(rs.getBytes(9), 300));
				memVO.setPo_auth(rs.getInt(10));
				memVO.setSt_auth(rs.getInt(11));
				memVO.setCom_auth(rs.getInt(12));
				memVO.setPar_auth(rs.getInt(13));
				memVO.setMem_email(rs.getString(14));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memVO;
	}

	@Override
	public MemberVO selectPic(String userId) {
		MemberVO memVO;
		memVO = new MemberVO();
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(SELECT_PIC);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				memVO.setMem_photo(ImageUtil.shrink(rs.getBytes(1), 100));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memVO;
	}

}
