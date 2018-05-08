package android.com.combid.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ComBidDAO implements ComBid_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	static final String INSERT = "INSERT INTO COMMISION_BIDDING_LIST (MEM_ID, COM_ID, AUC_PRC, AUC_DEL_PRC, N_O_V, AT_STS) VALUES (?,?,?,?,?,?)";
	static final String IS_EXIST = "SELECT * FROM COMMISION_BIDDING_LIST WHERE MEM_ID=? AND COM_ID=?";

	@Override
	public Boolean isAlreadyIn(String memid, String comid) {
		Connection conn=null;
		PreparedStatement ps=null;
		boolean isAlreadyIn = false;
		try {
			conn=ds.getConnection();
			ps=conn.prepareStatement(IS_EXIST);
			ps.setString(1,memid);
			ps.setString(2,comid);
			ResultSet rs=ps.executeQuery();/*查詢資料庫 傳回資料列*/
			isAlreadyIn=rs.next();  /*.next()  有資料true | 沒有false*/
			return isAlreadyIn;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		return isAlreadyIn;
	}

	@Override
	public Boolean insert(ComBidVO combidvo) {
		Connection c = null;
		try {
			c = ds.getConnection();
			boolean isUpdate = false;
			c.setAutoCommit(false);
			PreparedStatement prst = c.prepareStatement(INSERT);
			prst.setString(1, combidvo.getMem_id());
			prst.setString(2, combidvo.getCom_id());
			prst.setDouble(3, combidvo.getAuc_prc());
			prst.setDouble(4, combidvo.getAuc_del_prc());
			prst.setInt(5, combidvo.getN_o_v());
			prst.setString(6, combidvo.getAt_sts());
			prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
		} catch (SQLException e) {
			if (c != null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		boolean isUpdate = true;
		return Boolean.valueOf(isUpdate);
	}

}
