package com.StoreReceiptDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.StoreOrder.model.StoreOrderVO;



public class StoreReceiptDetailDAO implements StoreReceiptDetail_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";
	
	private static final String INSERT_STMT = 
			"insert into Store_Receipt_Detail (rec_id,it_id,it_num)values(?,?,?)";	//�ק�
	private static final String UPDATE = 
			"update Store_Receipt_Detail set rec_id=? where it_id = ?";	
	private static final String DELETE = 
			"delete * from Store_Receipt_Detail where rec_id=?";
	private static final String GET_ONE_STMT = 
			"select * from Store_Receipt_Detail where rec_id = ? ";	//�d�ߥ���
	private static final String GET_ALL_BY_REC_ID = 
			"select * from Store_Receipt_Detail where rec_id = ? ";	//�d�ߥ���
	private static final String GET_ALL_STMT = 
			"select * from Store_Receipt_Detail order by rec_id";
	private static final String GET_STORE_ORDER_BY_REC_ID = 
			"select * from store_order where REC_ID=?  order by rec_id";

	@Override
	public void insertStoreReceiptDetail(StoreReceiptDetailVO storeReceiptDetailVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				con = ds.getConnection();		
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, storeReceiptDetailVO.getRec_id());
			pstmt.setString(2, storeReceiptDetailVO.getIt_id());
			pstmt.setInt(3, storeReceiptDetailVO.getIt_num());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			 if(con != null) {
			        try {
			        	con.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
			 throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
public void insertStoreReceiptDetail2(StoreReceiptDetailVO storeReceiptDetailVO,Connection con) {
		
		PreparedStatement pstmt=null;
		try {
			
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, storeReceiptDetailVO.getRec_id());
			pstmt.setString(2, storeReceiptDetailVO.getIt_id());
			pstmt.setInt(3, storeReceiptDetailVO.getIt_num());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
			 if(con != null) {
			        try {			        	
			        	System.err.print("Transaction is being ");
						System.err.println("rolled back-由-StoreReceiptDetail");
						con.rollback(); 
			        }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			            throw new RuntimeException("rollback error occured. "
								+ ex.getMessage());
			        }
			    }
			
		}finally{
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		
	}

	@Override
	public void updateStoreReceiptDetail(StoreReceiptDetailVO storeReceiptDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				con = ds.getConnection();		
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			pstmt = con.prepareStatement(UPDATE);

			
			
			
			pstmt.setString(1, storeReceiptDetailVO.getRec_id());
			pstmt.setString(2, storeReceiptDetailVO.getIt_id());
			

			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			 if(con != null) {
			        try {
			        	con.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
	public void deleteStoreReceiptDetail(String rec_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				con = ds.getConnection();		
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rec_id);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			 if(con != null) {
			        try {
			        	con.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
	public StoreReceiptDetailVO StoreReceiptDetailfindByPrimaryKey(String rec_id) {
		StoreReceiptDetailVO storeReceiptDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				con = ds.getConnection();		
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rec_id);

			rs = pstmt.executeQuery();			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				storeReceiptDetailVO = new StoreReceiptDetailVO();				
				
				storeReceiptDetailVO.setRec_id(rs.getString("rec_id"));
				storeReceiptDetailVO.setIt_id(rs.getString("it_id"));
				storeReceiptDetailVO.setIt_num(rs.getInt("it_num"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			 if(con != null) {
			        try {
			        	con.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
		return storeReceiptDetailVO;
	}

	@Override
	public List<StoreReceiptDetailVO> StoreReceiptDetailgetAll() {
		List<StoreReceiptDetailVO> list = new ArrayList<StoreReceiptDetailVO>();
		StoreReceiptDetailVO storeReceiptDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				con = ds.getConnection();		
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				storeReceiptDetailVO = new StoreReceiptDetailVO();				
				
				storeReceiptDetailVO.setRec_id(rs.getString("rec_id"));
				storeReceiptDetailVO.setIt_id(rs.getString("it_id"));
				storeReceiptDetailVO.setIt_num(rs.getInt("it_num"));
				list.add(storeReceiptDetailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			 if(con != null) {
			        try {
			        	con.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
	public List<StoreReceiptDetailVO> StoreReceiptDetailgetAllBy_rec_id(String rec_id) {
		List<StoreReceiptDetailVO> list = new ArrayList<StoreReceiptDetailVO>();
		StoreReceiptDetailVO storeReceiptDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				con = ds.getConnection();		
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
			pstmt = con.prepareStatement(GET_ALL_BY_REC_ID);			
			pstmt.setString(1, rec_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				storeReceiptDetailVO = new StoreReceiptDetailVO();				
				
				storeReceiptDetailVO.setRec_id(rs.getString("rec_id"));
				storeReceiptDetailVO.setIt_id(rs.getString("it_id"));
				storeReceiptDetailVO.setIt_num(rs.getInt("it_num"));
				list.add(storeReceiptDetailVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			 if(con != null) {
			        try {
			        	con.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
	public Set<StoreOrderVO> getStoreOrdersBy_rec_id(String rec_id) {
		Set<StoreOrderVO> set = new LinkedHashSet<StoreOrderVO>();	
		StoreOrderVO storeOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				con = ds.getConnection();		
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
			pstmt = con.prepareStatement(GET_STORE_ORDER_BY_REC_ID);			
			pstmt.setString(1, rec_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {				
				storeOrderVO = new StoreOrderVO();	
				storeOrderVO.setRec_id(rs.getString("rec_id"));
				storeOrderVO.setMem_id(rs.getString("mem_id"));
				storeOrderVO.setSt_id(rs.getString("st_id"));
				storeOrderVO.setBuyer_name(rs.getString("buyer_name"));
				storeOrderVO.setMem_email(rs.getString("mem_email"));
				storeOrderVO.setRec_delivery(rs.getString("rec_delivery"));
				storeOrderVO.setPayment(rs.getDouble("payment"));
				storeOrderVO.setRec_py(rs.getString("rec_py"));
				storeOrderVO.setRec_date(rs.getDate("rec_date"));
				storeOrderVO.setRec_dlv_sts(rs.getString("rec_dlv_sts"));
				storeOrderVO.setMem_add(rs.getString("mem_add"));
				storeOrderVO.setMem_ph(rs.getString("mem_ph"));
				storeOrderVO.setUsd_bns(rs.getInt("usd_bns"));
				storeOrderVO.setDlv_id(rs.getString("dlv_id"));				
				set.add(storeOrderVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			 if(con != null) {
			        try {
			        	con.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
		return set;
	}

}
