package android.com.promotion.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PromotionDAO implements PromotionDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	static final String GET_ALL = "SELECT * FROM PROMOTION";
	static final String GET_ALL_ORDERBY_TIME = "SELECT * FROM PROMOTION ORDER BY PMT_S_DATE DESC;";
	static final String GET_ALL_ORDERBY_TIME_FOUR = "SELECT * FROM PROMOTION WHERE ROWNUM <4 ORDER BY PMT_S_DATE DESC";

	@Override
	public List<PromotionVO> getAll() {
		List<PromotionVO> promotionList = new ArrayList<>();
		PromotionVO promotion = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(GET_ALL_ORDERBY_TIME_FOUR);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				promotion = new PromotionVO();
				promotion.setPmt_id(rs.getString(1));
				promotion.setEmp_no(rs.getString(2));
				promotion.setPmt_name(rs.getString(3));
				promotion.setPmt_intro(rs.getString(4));
				promotion.setPmtpic(rs.getBytes(5));
				promotion.setPmt_s_date(rs.getTimestamp(6));
				promotion.setPmt_e_date(rs.getTimestamp(7));
				promotion.setPmt_sts(rs.getString(8));

				promotionList.add(promotion);

			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return promotionList;
	}
}
