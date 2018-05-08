package com.StoreOrder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.Product.model.ProductVO;
import com.StoreReceiptDetail.model.StoreReceiptDetailDAO;
import com.StoreReceiptDetail.model.StoreReceiptDetailService;
import com.StoreReceiptDetail.model.StoreReceiptDetailVO;




public class StoreOrderDAO implements StoreOrder_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";

	private static final String INSERT_STMT = 
		"INSERT INTO Store_Order (rec_id,mem_id,st_id,buyer_name,mem_email,rec_delivery,payment,rec_py,rec_dlv_sts,mem_add,mem_ph,usd_bns,dlv_id) VALUES ('REC'||LPAD(to_char(STORE_ORDER_SEQ.NEXTVAL), 3, '0'),?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT_NOTST001 = 
		"SELECT * FROM Store_Order where not ST_ID='ST001' order by rec_id DESC";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM Store_Order  order by rec_id DESC";
	private static final String GET_ALL_STMT_MEM_ID = 
			"SELECT * FROM Store_Order where MEM_ID=?  order by rec_id DESC";
	
	private static final String GET_ALL_STMT_ST001 = 
			"SELECT * FROM Store_Order where ST_ID='ST001' order by rec_id DESC";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM Store_Order where rec_id = ?";
	private static final String GET_ONE_STMT_BY_ST_ID = 
			"SELECT * FROM Store_Order where st_id = ?";
	
	private static final String GET_ALL_STMT_BY_ST_ID = 
			"SELECT * FROM Store_Order where st_id = ?";
	
	private static final String DELETE = 
		"DELETE FROM Store_Order where rec_id = ?";
	private static final String UPDATE = 
		"UPDATE Store_Order set buyer_name=? ,mem_email=? ,rec_delivery=?  ,payment=?, rec_py=?, rec_dlv_sts=?, mem_add=?, mem_ph=? ,usd_bns=? ,dlv_id=? where rec_id = ?";
	
	private static final String UPDATE_STS = 
			"UPDATE Store_Order set  rec_dlv_sts=? where rec_id = ?";

	@Override
	public void insertStoreOrderWithStoreReceiptDetail(StoreOrderVO storeordervp,HashMap<String,ProductVO> StoreReceiptDetailMap) {
		Connection con = null;
		PreparedStatement pstmt = null;
		HashMap<String, StoreOrderVO> ordMap=new HashMap<String, StoreOrderVO>();
	
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
			con.setAutoCommit(false);
			String cols[] = {"rec_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);	
			ResultSet rs=null;
			for(String s:StoreReceiptDetailMap.keySet()){
				ProductVO psv=StoreReceiptDetailMap.get(s);
				if(ordMap.size()==0){
					StoreOrderVO storeordervp2=new StoreOrderVO();
					storeordervp2.setMem_id(storeordervp.getMem_id());
					storeordervp2.setBuyer_name(storeordervp.getBuyer_name());
					storeordervp2.setMem_email(storeordervp.getMem_email());
					storeordervp2.setRec_delivery(storeordervp.getRec_delivery());
					//訂單金額扣紅利
					storeordervp2.setUsd_bns(storeordervp.getUsd_bns());			
					storeordervp2.setPayment((double)0);	
					storeordervp2.setRec_py(storeordervp.getRec_py());
					// storeOrde.setRec_date(rec_date);					
					//storeordervp2.setRec_dlv_sts(storeordervp.getRec_dlv_sts());
					storeordervp2.setMem_add(storeordervp.getMem_add());
					storeordervp2.setMem_ph(storeordervp.getMem_ph());
					storeordervp2.setSt_id(psv.getSt_id());
					
					if(psv.getSt_id().equals("ST001")){				
						storeordervp2.setRec_dlv_sts("已出貨");
					}else{
						storeordervp2.setRec_dlv_sts("訂單受理中");
					}
					ordMap.put(psv.getSt_id(), storeordervp2);
					
				}
				else{
					String checkit_id=StoreReceiptDetailMap.get(s).getSt_id();
					if(ordMap.containsKey(checkit_id)){
						
					}else{
					
					 StoreOrderVO storeordervp2=new StoreOrderVO();
					 storeordervp2.setMem_id(storeordervp.getMem_id());
						storeordervp2.setBuyer_name(storeordervp.getBuyer_name());
						storeordervp2.setMem_email(storeordervp.getMem_email());
						storeordervp2.setRec_delivery(storeordervp.getRec_delivery());
						//訂單金額扣紅利
						storeordervp2.setUsd_bns(storeordervp.getUsd_bns());			
						storeordervp2.setPayment((double)0);	
						storeordervp2.setRec_py(storeordervp.getRec_py());
						// storeOrde.setRec_date(rec_date);					
					//	storeordervp2.setRec_dlv_sts(storeordervp.getRec_dlv_sts());
						storeordervp2.setMem_add(storeordervp.getMem_add());
						storeordervp2.setMem_ph(storeordervp.getMem_ph());
						storeordervp2.setSt_id(psv.getSt_id());
						ordMap.put(psv.getSt_id(), storeordervp2);
					 storeordervp2.setSt_id(psv.getSt_id());
					 
						if(psv.getSt_id().equals("ST001")){				
							storeordervp2.setRec_dlv_sts("已出貨");
						}else{
							storeordervp2.setRec_dlv_sts("訂單受理中");
						}
					 ordMap.put(psv.getSt_id(), storeordervp2);
					}
					
				}
			}
			
			
			
			
			for(String s:ordMap.keySet()){
			StoreOrderVO storeOrderVO=ordMap.get(s);
			
			
			pstmt.setString(1,storeOrderVO.getMem_id());
			pstmt.setString(2,storeOrderVO.getSt_id());
			pstmt.setString(3,storeOrderVO.getBuyer_name());
			pstmt.setString(4,storeOrderVO.getMem_email());			
			pstmt.setString(5,storeOrderVO.getRec_delivery());
			pstmt.setDouble(6, storeOrderVO.getPayment());
			pstmt.setString(7, storeOrderVO.getRec_py());			
			pstmt.setString(8, storeOrderVO.getRec_dlv_sts());
			pstmt.setString(9, storeOrderVO.getMem_add());
			pstmt.setString(	10, storeOrderVO.getMem_ph());		
			pstmt.setInt(	11,storeOrderVO.getUsd_bns());
			pstmt.setString(12,storeOrderVO.getDlv_id());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			String next_rec_id= null;
			
			if (rs.next()) {
				next_rec_id = rs.getString(1);
				
				storeOrderVO.setRec_id(next_rec_id);
			
	
//				System.out.println("自增主鍵值= " + next_rec_id +"(剛新增成功的編號)");
//				System.out.println("新增編號" + next_rec_id + "時,共有" + StoreReceiptDetailMap.size()
//				+ "同時被新增");
				
			}
			
			else {
				System.out.println("未取得自增主鍵值");
			}
			
	
			}			
			
			rs.close();
		int count=0;
		StoreReceiptDetailService storeReceiptDetailService=new StoreReceiptDetailService();
			for (String s: StoreReceiptDetailMap.keySet()) {
				
				System.out.println("size"+StoreReceiptDetailMap.keySet().size());
				System.out.println(count);
				ProductVO pdv=StoreReceiptDetailMap.get(s);
				for(String s2:ordMap.keySet()){	
				
					
					StoreOrderVO sov=ordMap.get(s2);
					
					if(sov.getSt_id().equals(pdv.getSt_id())){
						System.out.println("商品id"+pdv.getIt_id()+"商品價錢"+":"+pdv.getIt_prc());
						sov.setPayment(sov.getPayment()+pdv.getIt_prc()*pdv.getIt_num());
						StoreReceiptDetailVO storeReceiptDetail=new StoreReceiptDetailVO();
						storeReceiptDetail.setIt_id(pdv.getIt_id());
						storeReceiptDetail.setRec_id(sov.getRec_id());
						storeReceiptDetail.setIt_num(pdv.getIt_num());
						
						
						
						storeReceiptDetailService.insertStoreReceiptDetail2(storeReceiptDetail, con);
					    System.out.println(4);
					}
				}
				count++;
			}
			
			for(String s:ordMap.keySet()){
				StoreOrderVO storeOrderVO=ordMap.get(s);
				System.out.println(storeOrderVO.getPayment());
				StoreOrderService sos=new StoreOrderService();
				sos.updateStoreOrder2(storeOrderVO,con);
			}
			 
			con.commit();
			con.setAutoCommit(true);
			
			System.out.println("list.size()-B="+StoreReceiptDetailMap.size());
			
			
			// Handle any SQL errors
		} catch (Exception se) {
			 se.printStackTrace();
			 if(con != null) {
			        try {
			        	con.rollback();
			        	System.err.print("Transaction is being ");
						System.err.println("rolled back-由-storeOrder");
			        	}
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

	
	public void updateStoreOrder2(StoreOrderVO storeOrderVO,Connection con) throws RuntimeException{
	
		PreparedStatement pstmt = null;

		try {

		
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, storeOrderVO.getBuyer_name());		
			pstmt.setString(2, storeOrderVO.getMem_email());		
			pstmt.setString(3, storeOrderVO.getRec_delivery());		
			pstmt.setDouble(4, storeOrderVO.getPayment());
			pstmt.setString(5, storeOrderVO.getRec_py());		
		
			pstmt.setString(6, storeOrderVO.getRec_dlv_sts());
			pstmt.setString(7, storeOrderVO.getMem_add());
			pstmt.setString(	8, storeOrderVO.getMem_ph());		
			pstmt.setInt(	9,storeOrderVO.getUsd_bns());
			pstmt.setString(10,storeOrderVO.getDlv_id());	
			pstmt.setString(11, storeOrderVO.getRec_id());
			pstmt.executeUpdate();
			System.out.println("1執行"+storeOrderVO.getRec_id());

			System.out.println(pstmt.executeUpdate());

			System.out.println("2執行"+storeOrderVO.getPayment());
			con.commit();
			System.out.println("ˇ3執行"+storeOrderVO.getPayment());
			// Handle any driver errors
		} catch (Exception se) {
			 se.printStackTrace();
			 if(con != null) {
			        try {
			        	con.rollback(); 
			        	}
			        catch(Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			
			 throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
			
		}

		
	}
	
	public void updateStoreOrder_sts(StoreOrderVO storeOrderVO) throws RuntimeException{
		
		PreparedStatement pstmt = null;
		Connection con=null;
		
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
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE_STS);
				
			pstmt.setString(1, storeOrderVO.getRec_dlv_sts());
		
			pstmt.setString(2, storeOrderVO.getRec_id());
  			pstmt.executeUpdate();
			
			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (Exception se) {
			 se.printStackTrace();
			 if(con != null) {
			        try {
			        	con.rollback(); 
			        	}
			        catch(Exception ex) {
			            ex.printStackTrace();
			        }
			    }
			
			 throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception se) {
					se.printStackTrace(System.err);
				}
			}
			
		}
	
		
	}

	@Override
	public void deleteStoreOrder(String rec_id) {
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
	public StoreOrderVO StoreOrderfindByPrimaryKey(String rec_id) {
		// TODO Auto-generated method stub
		StoreOrderVO storeOrderVO=null;
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
		return storeOrderVO;
	}
	
	public StoreOrderVO StoreOrderfindByST_id(String st_id) {
		// TODO Auto-generated method stub
		StoreOrderVO storeOrderVO=null;
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
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_ST_ID);

			pstmt.setString(1, st_id);

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
		return storeOrderVO;
	}

	@Override
	public List<StoreOrderVO> StoreOrdergetAll() {
		List<StoreOrderVO> list = new ArrayList<StoreOrderVO>();
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
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
				list.add(storeOrderVO); // Store the row in the list
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
	public List<StoreOrderVO> StoreOrdergetAllBy_mem_id(String mem_id) {
		List<StoreOrderVO> list = new ArrayList<StoreOrderVO>();
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
			pstmt = con.prepareStatement(GET_ALL_STMT_MEM_ID);
			pstmt.setString(1, mem_id);
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
				list.add(storeOrderVO); // Store the row in the list
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
	
	public List<StoreOrderVO> StoreOrdergetAllNotST001() {
		List<StoreOrderVO> list = new ArrayList<StoreOrderVO>();
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
			pstmt = con.prepareStatement(GET_ALL_STMT_NOTST001);
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
				list.add(storeOrderVO); // Store the row in the list
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
	public List<StoreOrderVO> StoreOrdergetAll_ST001() {
		List<StoreOrderVO> list = new ArrayList<StoreOrderVO>();
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
			pstmt = con.prepareStatement(GET_ALL_STMT_ST001);
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
				list.add(storeOrderVO); // Store the row in the list
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
	public void updateStoreOrder(StoreOrderVO storeOrderVO) {
		// TODO Auto-generated method stub
		
	}
	
	public List<StoreOrderVO> StoreOrdergetAll_By_St_id(String st_id) {
		List<StoreOrderVO> list = new ArrayList<StoreOrderVO>();
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
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_ST_ID);
			pstmt.setString(1, st_id);
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
				list.add(storeOrderVO); // Store the row in the list
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
	
}


