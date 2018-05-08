package com.news.model;

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

public class NewsDAO implements NewsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO NEWS (NS_ID,EMP_NO,NS_TIT,NS_CNT,NS_STS,NS_CDATE) VALUES('NS'||LPAD(TO_CHAR(NS_ID.NEXTVAL), 3, '0'),?,?,?,'等候發布',?)";
	private static final String GET_ALL_STMT = "SELECT NS_ID,EMP_NO,NS_TIT,NS_CNT,TO_CHAR(NS_DATE,'YYYY,MM,DD')NS_DATE,NS_LIKE,NS_CDATE,NS_STS,MEM_ID FROM NEWS ORDER BY NS_DATE DESC";
	private static final String GET_ONE_STMT = "SELECT NS_ID,EMP_NO,NS_TIT,NS_CNT,TO_CHAR(NS_DATE,'YYYY,MM,DD')NS_DATE,NS_LIKE,NS_CDATE,NS_STS,MEM_ID FROM NEWS where NS_ID =?";
	private static final String DELETE = "DELETE FROM NEWS where NS_ID = ?";
	private static final String UPDATE = "UPDATE NEWS set NS_TIT=?, NS_CNT =?, NS_STS =? where NS_ID = ?";
	private static final String UPLIKE = "UPDATE NEWS set NS_LIKE=? MEM_ID=? where NS_ID = ?";
	private static final String UPDATESTS = "UPDATE NEWS set NS_STS=? where NS_ID = ?";
	private static final String SELECTSTS = "SELECT NS_ID,EMP_NO,NS_TIT,NS_CNT,TO_CHAR(NS_DATE,'YYYY,MM,DD HH:MM')NS_DATE,NS_LIKE,NS_CDATE,NS_STS,MEM_ID FROM NEWS where NS_STS =? ORDER BY NS_DATE";
	
	@Override
	public String add(NewsVO nsVO) {
		Connection con = null;
		PreparedStatement psmt = null;
		String pk =null;
		try {
			con = ds.getConnection();
			String[] generateKey=new String[]{"NS_ID"};
			psmt = con.prepareStatement(INSERT_STMT,generateKey);
			psmt.setString(1, nsVO.getEmp_no());
			psmt.setString(2, nsVO.getNs_tit());
			psmt.setString(3, nsVO.getNs_cnt());
			psmt.setString(4, nsVO.getNs_cdate());
			psmt.executeUpdate();
            ResultSet rs=psmt.getGeneratedKeys();
            if(rs.next()){
            	 pk =rs.getString(1);
            }
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
			return pk;
	}

	@Override
	public void update(NewsVO nsVO) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATE);
			psmt.setString(1, nsVO.getNs_tit());
			psmt.setString(2, nsVO.getNs_cnt());
			psmt.setString(3, nsVO.getNs_sts());
			psmt.setString(4, nsVO.getNs_id());
			psmt.executeUpdate();

		} catch (SQLException se) {

			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
	public void updatelike(NewsVO ns) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPLIKE);
			psmt.setString(3, ns.getNs_id());
			psmt.setInt(1,ns.getNs_like() );
			psmt.setString(2, ns.getMem_id());
			psmt.executeUpdate();

		} catch (SQLException se) {

			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
	public void  delete(String ns_id) {
		Connection con = null;
		PreparedStatement psmt = null;
		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(DELETE);
			psmt.setString(1, ns_id);
			psmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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
	public NewsVO findByPK(String ns_id) {

		NewsVO nsv = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ONE_STMT);

			psmt.setString(1, ns_id);
			rs = psmt.executeQuery();
			while (rs.next()) {
				nsv = new NewsVO();
				nsv.setNs_id(rs.getString("ns_id"));
				nsv.setEmp_no(rs.getString("emp_no"));
				nsv.setNs_tit(rs.getString("ns_tit"));
				nsv.setNs_cnt(rs.getString("ns_cnt"));
				nsv.setNs_date(rs.getString("ns_date"));
				nsv.setNs_like(rs.getInt("ns_like"));
				nsv.setNs_cdate(rs.getString("ns_cdate"));
			}

			// Handle any driver errors
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
			if (psmt != null) {
				try {
					psmt.close();
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
		return nsv;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO nsvo = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(GET_ALL_STMT);
			rs = psmt.executeQuery();

			while (rs.next()) {
				nsvo = new NewsVO();
				nsvo.setNs_id(rs.getString("ns_id"));
				nsvo.setEmp_no(rs.getString("emp_no"));
				nsvo.setNs_tit(rs.getString("ns_tit"));
				nsvo.setNs_cnt(rs.getString("ns_cnt"));
				nsvo.setNs_date(rs.getString("ns_date"));
				nsvo.setNs_like(rs.getInt("ns_like"));
				nsvo.setNs_cdate(rs.getString("ns_cdate"));
				nsvo.setNs_sts(rs.getNString("ns_sts"));
				list.add(nsvo);
			}

			// Handle any driver errors
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
			if (psmt != null) {
				try {
					psmt.close();
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
	public void updatests(NewsVO ns) {
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			con = ds.getConnection();
			psmt = con.prepareStatement(UPDATESTS);
			psmt.setString(2, ns.getNs_id());
			psmt.setString(1,ns.getNs_sts() );
			psmt.executeUpdate();

		} catch (SQLException se) {

			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
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

	
	public List<NewsVO>selectsts(String ns_sts) {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO nsvo = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			psmt = con.prepareStatement(SELECTSTS);
			rs = psmt.executeQuery();
			psmt.setString(1, ns_sts);

			while (rs.next()) {
				nsvo = new NewsVO();
				nsvo.setNs_id(rs.getString("ns_id"));
				nsvo.setEmp_no(rs.getString("emp_no"));
				nsvo.setNs_tit(rs.getString("ns_tit"));
				nsvo.setNs_cnt(rs.getString("ns_cnt"));
				nsvo.setNs_date(rs.getString("ns_date"));
				nsvo.setNs_like(rs.getInt("ns_like"));
				nsvo.setNs_cdate(rs.getString("ns_cdate"));
				nsvo.setNs_sts(rs.getNString("ns_sts"));
				list.add(nsvo);
			}

			// Handle any driver errors
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
			if (psmt != null) {
				try {
					psmt.close();
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
}
