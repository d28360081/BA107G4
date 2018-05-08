package com.Product.model;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;



public class ProductDAO implements ProductDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";
	
	Map<String, String> dict = new HashMap<String, String>();

	
	private static final String INSERT_STMT = 
		"INSERT INTO Product (it_id,st_id,it_num,it_name,it_intro,it_pic,it_prc,it_sts,it_cate) VALUES ('IT'||LPAD(to_char(Product_SEQ.NEXTVAL), 3, '0'),?, ?, ?, ?, ?, ?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM Product where IT_STS='上架' or IT_STS='下架' AND  NOT ST_ID='ST001'  order by it_id DESC";	
	private static final String GET_ALL_STMT_ST001FORBACKEND = 		
	"SELECT * FROM Product where IT_STS='上架' AND ST_ID='ST001' or IT_STS='下架'  AND ST_ID='ST001'  order by it_id DESC";	
	private static final String GET_ALL_STMT_BY_STS_ON = 
			"SELECT * FROM Product where it_sts='上架'  AND  NOT ST_ID='ST001'  order by it_id DESC ";	
	private static final String GET_ALL_STMT_BY_STS_OFF = 
			"SELECT * FROM Product where it_sts='下架'AND  NOT ST_ID='ST001' order by it_id DESC ";	
	private static final String GET_ALL_STMT_BY_STS_ON_ST001 = 
			"SELECT * FROM Product where it_sts='上架' AND  ST_ID='ST001' order by it_id DESC ";	
	private static final String GET_ALL_STMT_BY_STS_OFF_ST001 = 
			"SELECT * FROM Product where it_sts='下架'AND  ST_ID='ST001' order by it_id DESC ";	
	
	private static final String GET_ALL_STMT_BY_STS_ON_AND_STORE = 
			"SELECT * FROM Product where it_sts='上架' AND ST_ID=?  order by it_id DESC ";	
	private static final String GET_ALL_STMT_BY_STS_OFF_AND_STORE = 
			"SELECT * FROM Product where it_sts='下架' AND ST_ID=? order by it_id DESC ";	
	
	private static final String GET_ONE_STMT = 
		"SELECT * FROM Product where it_id = ?";
	private static final String GET_ALL_STMT_BY_IT_ID = 
			"SELECT * FROM Product where it_id = ?";
	private static final String GET_ALL_STMT_BY_IT_NAME_SERACH =
			"select * from PRODUCT where IT_NAME like ?  And IT_STS='上架' AND  NOT ST_ID='ST001' order by it_id DESC";
	private static final String GET_ALL_STMT_BY_CATE_AND_STS_ON = 
			"SELECT * FROM Product where it_CATE = ? AND  NOT ST_ID='ST001'  and it_sts='上架' ";	
	
	private static final String GET_ALL_STMT_BY_IT_NAME_SERACH_ST001 =
			"select * from PRODUCT where IT_NAME like ?  And IT_STS='上架' AND  ST_ID='ST001' order by it_id DESC";
	private static final String GET_ALL_STMT_BY_CATE_AND_STS_ON_ST001 = 
			"SELECT * FROM Product where it_CATE = ? AND  ST_ID='ST001'  and it_sts='上架' ";	
	private static final String UPDATE = 
		"UPDATE Product set  it_num=?, it_name=?, it_intro=?, it_pic=?, it_prc=? ,it_sts=?,it_cate=? where it_id = ?";
	private static final String UPDATE_NUM = 
			"UPDATE Product set  it_num=? where it_id = ?";
	private static final String UPDATE_STS = 
			"UPDATE Product set  it_sts=? where it_id = ?";
	
	public ProductDAO(){
		super();
		dict.put("snack", "零食點心區");
		dict.put("sport", "休閒運動區");
		dict.put("tripleC", "3C周邊區");
		dict.put("lifeLiving", "居家生活區");
		dict.put("workOut", "運動健身區");
		dict.put("Game", "電玩遊戲區");
		dict.put("furniture", "傢俱寢飾區");
		dict.put("Women_Clothing", "女性服飾區");
		dict.put("toyForJoy", "玩具童裝區");
		
	}
	public Map<String, ProductVO> getAllProductMap() {
		
	Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
	Connection conn = null;		
	
	ProductVO product = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;	
	try {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
			conn = ds.getConnection();		
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();		
		int i = 1;
		
		while (rs.next()) {
			product = new ProductVO();
			product.setIt_id(rs.getString("it_id"));
			product.setSt_id(rs.getString("st_id"));				
			product.setIt_num(rs.getInt("it_num"));
			product.setIt_name(rs.getString("it_name"));
			product.setIt_intro(rs.getString("it_intro"));
			product.setIt_pic(rs.getBytes("it_pic"));
			product.setIt_prc(rs.getDouble("it_prc"));
			product.setIt_sts(rs.getString("it_sts"));
			String it_cate= rs.getString("it_cate");
			String is_cate_encode = dict.get(it_cate);
			product.setIt_cate(is_cate_encode);
			map.put(String.valueOf(i++), product);
		}
		conn.commit();
		if(conn != null) {
	        try {
	            conn.rollback(); 
	            }
	        catch(SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
		conn.setAutoCommit(true);
	} catch (SQLException se) {
		 if(conn != null) {
		        try {
		        	conn.rollback(); }
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
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	return map;
}
	//此為官方商品
	public Map<String, ProductVO> getAllProductMap_ST001() {
		
		Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
		Connection conn = null;		
		
		ProductVO product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();		
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(GET_ALL_STMT_ST001FORBACKEND);
			rs = pstmt.executeQuery();		
			int i = 1;
			
			while (rs.next()) {
				product = new ProductVO();
				product.setIt_id(rs.getString("it_id"));
				product.setSt_id(rs.getString("st_id"));				
				product.setIt_num(rs.getInt("it_num"));
				product.setIt_name(rs.getString("it_name"));
				product.setIt_intro(rs.getString("it_intro"));
				product.setIt_pic(rs.getBytes("it_pic"));
				product.setIt_prc(rs.getDouble("it_prc"));
				product.setIt_sts(rs.getString("it_sts"));
				String it_cate= rs.getString("it_cate");
				String is_cate_encode = dict.get(it_cate);
				product.setIt_cate(is_cate_encode);
				map.put(String.valueOf(i++), product);
			}
			conn.commit();
			if(conn != null) {
		        try {
		            conn.rollback(); 
		            }
		        catch(SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
			conn.setAutoCommit(true);
		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return map;
	}	
	

	public Map<String, ProductVO> getAllProductMapBy_Sts_On() {
		
		Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
		Connection conn = null;		
		
		ProductVO product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();				
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(GET_ALL_STMT_BY_STS_ON);
			rs = pstmt.executeQuery();

			int i = 1;
			
			while (rs.next()) {
				product = new ProductVO();
				product.setIt_id(rs.getString("it_id"));
				product.setSt_id(rs.getString("st_id"));				
				product.setIt_num(rs.getInt("it_num"));
				product.setIt_name(rs.getString("it_name"));
				product.setIt_intro(rs.getString("it_intro"));
				product.setIt_pic(rs.getBytes("it_pic"));
				product.setIt_prc(rs.getDouble("it_prc"));
				product.setIt_sts(rs.getString("it_sts"));	
				String it_cate= rs.getString("it_cate");
				String is_cate_encode = dict.get(it_cate);
				product.setIt_cate(is_cate_encode);
				//
				map.put(String.valueOf(i++), product);
			}
				conn.commit();
				conn.setAutoCommit(true);

		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
		return map;
	}
public Map<String, ProductVO> getAllProductMapBy_Sts_On_ST001() {
		
		Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
		Connection conn = null;		
		
		ProductVO product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();				
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(GET_ALL_STMT_BY_STS_ON_ST001);
			rs = pstmt.executeQuery();

			int i = 1;
			
			while (rs.next()) {
				product = new ProductVO();
				product.setIt_id(rs.getString("it_id"));
				product.setSt_id(rs.getString("st_id"));				
				product.setIt_num(rs.getInt("it_num"));
				product.setIt_name(rs.getString("it_name"));
				product.setIt_intro(rs.getString("it_intro"));
				product.setIt_pic(rs.getBytes("it_pic"));
				product.setIt_prc(rs.getDouble("it_prc"));
				product.setIt_sts(rs.getString("it_sts"));	
				String it_cate= rs.getString("it_cate");
				String is_cate_encode = dict.get(it_cate);
				product.setIt_cate(is_cate_encode);
				//
				map.put(String.valueOf(i++), product);
			}
				conn.commit();
				conn.setAutoCommit(true);

		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
		return map;
	}
	
	
public Map<String, ProductVO> getAllProductMapBy_Sts_Off() {
		
		Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
		Connection conn = null;		
		
		ProductVO product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();
				conn.setAutoCommit(false);
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(GET_ALL_STMT_BY_STS_OFF);
			rs = pstmt.executeQuery();

			int i = 1;
			
			while (rs.next()) {
				product = new ProductVO();
				product.setIt_id(rs.getString("it_id"));
				product.setSt_id(rs.getString("st_id"));				
				product.setIt_num(rs.getInt("it_num"));
				product.setIt_name(rs.getString("it_name"));
				product.setIt_intro(rs.getString("it_intro"));
				product.setIt_pic(rs.getBytes("it_pic"));
				product.setIt_prc(rs.getDouble("it_prc"));
				product.setIt_sts(rs.getString("it_sts"));
				String it_cate= rs.getString("it_cate");
				String is_cate_encode = dict.get(it_cate);
				product.setIt_cate(is_cate_encode);
				map.put(String.valueOf(i++), product);
			}
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
		return map;
	}	
public Map<String, ProductVO> getAllProductMapBy_Sts_Off_ST001() {
	
	Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
	Connection conn = null;		
	
	ProductVO product = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(GET_ALL_STMT_BY_STS_OFF_ST001);
		rs = pstmt.executeQuery();

		int i = 1;
		
		while (rs.next()) {
			product = new ProductVO();
			product.setIt_id(rs.getString("it_id"));
			product.setSt_id(rs.getString("st_id"));				
			product.setIt_num(rs.getInt("it_num"));
			product.setIt_name(rs.getString("it_name"));
			product.setIt_intro(rs.getString("it_intro"));
			product.setIt_pic(rs.getBytes("it_pic"));
			product.setIt_prc(rs.getDouble("it_prc"));
			product.setIt_sts(rs.getString("it_sts"));
			String it_cate= rs.getString("it_cate");
			String is_cate_encode = dict.get(it_cate);
			product.setIt_cate(is_cate_encode);
			map.put(String.valueOf(i++), product);
		}
		conn.commit();
		conn.setAutoCommit(true);

	} catch (SQLException se) {
		 if(conn != null) {
		        try {
		        	conn.rollback(); }
		        catch(SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
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
	return map;
}	
public Map<String, ProductVO> getAllProductMapBy_Sts_Off_AndStore(String st_id) {
	
	Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
	Connection conn = null;		
	
	ProductVO product = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(GET_ALL_STMT_BY_STS_OFF_AND_STORE);
		pstmt.setString(1, st_id);
		rs = pstmt.executeQuery();

		int i = 1;
		
		while (rs.next()) {
			product = new ProductVO();
			product.setIt_id(rs.getString("it_id"));
			product.setSt_id(rs.getString("st_id"));				
			product.setIt_num(rs.getInt("it_num"));
			product.setIt_name(rs.getString("it_name"));
			product.setIt_intro(rs.getString("it_intro"));
			product.setIt_pic(rs.getBytes("it_pic"));
			product.setIt_prc(rs.getDouble("it_prc"));
			product.setIt_sts(rs.getString("it_sts"));
			String it_cate= rs.getString("it_cate");
			String is_cate_encode = dict.get(it_cate);
			product.setIt_cate(is_cate_encode);
			map.put(String.valueOf(i++), product);
		}
		conn.commit();
		conn.setAutoCommit(true);

	} catch (SQLException se) {
		 if(conn != null) {
		        try {
		        	conn.rollback(); }
		        catch(SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
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
	return map;
}	
public Map<String, ProductVO> getAllProductMapBy_Sts_On_AndStore(String st_id) {
	
	Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
	Connection conn = null;		
	
	ProductVO product = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	try {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(GET_ALL_STMT_BY_STS_ON_AND_STORE);
		pstmt.setString(1, st_id);
		rs = pstmt.executeQuery();

		int i = 1;
		
		while (rs.next()) {
			product = new ProductVO();
			product.setIt_id(rs.getString("it_id"));
			product.setSt_id(rs.getString("st_id"));				
			product.setIt_num(rs.getInt("it_num"));
			product.setIt_name(rs.getString("it_name"));
			product.setIt_intro(rs.getString("it_intro"));
			product.setIt_pic(rs.getBytes("it_pic"));
			product.setIt_prc(rs.getDouble("it_prc"));
			product.setIt_sts(rs.getString("it_sts"));
			String it_cate= rs.getString("it_cate");
			String is_cate_encode = dict.get(it_cate);
			product.setIt_cate(is_cate_encode);
			map.put(String.valueOf(i++), product);
		}
		conn.commit();
		conn.setAutoCommit(true);

	} catch (SQLException se) {
		 if(conn != null) {
		        try {
		        	conn.rollback(); }
		        catch(SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
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
	return map;
}		

	@Override
	public void insert(ProductVO productVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
		try {

			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(INSERT_STMT);
				
			
			pstmt.setString(1, productVO.getSt_id());
			pstmt.setInt(2, productVO.getIt_num());
			pstmt.setString(3, productVO.getIt_name());
			pstmt.setString(4, productVO.getIt_intro());			
			pstmt.setBytes(5, productVO.getIt_pic());
			pstmt.setDouble(6, productVO.getIt_prc());
			pstmt.setString(7, productVO.getIt_sts());			
			pstmt.setString(8,productVO.getIt_cate());				
				
			pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			
			

			// Handle any SQL errors
		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
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
					se.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(ProductVO productVO) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
//		Part parts = productVO.getIt_picTest();
//		InputStream in;
//		ByteArrayOutputStream bao = null ;
//		byte [] b = null;
//		try {
//			in = parts.getInputStream();
//			bao = new ByteArrayOutputStream();
//			int i;
//			b = new byte[8182]; 
//			while((i=in.read(b))!=-1) {
//				bao.write(b,0,i);
//				bao.close();	
//			}	
//		} catch (IOException e1) {				
//			e1.printStackTrace();
//		} 

		try {

			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(UPDATE);
				
			
			
			pstmt.setInt(1, productVO.getIt_num());
			pstmt.setString(2, productVO.getIt_name());
			pstmt.setString(3, productVO.getIt_intro());
			
			
			
			pstmt.setBytes(4, productVO.getIt_pic());		
			pstmt.setDouble(5, productVO.getIt_prc());
			pstmt.setString(6, productVO.getIt_sts());
			pstmt.setString(7,productVO.getIt_cate());
			pstmt.setString(8, productVO.getIt_id());
						
			

			pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}	
	public void update_STS(ProductVO productVO) {

		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {

			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(UPDATE_STS);
				
			pstmt.setString(1, productVO.getIt_sts());
			pstmt.setString(2, productVO.getIt_id());

			

			pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		

	}	
	

	
	@Override
	public ProductVO findByPrimaryKey(String it_id) {

		ProductVO productVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {			

			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
			
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, it_id);
				
			rs = pstmt.executeQuery();			
			while (rs.next()) {
				
				productVO = new ProductVO();	
				
				productVO.setIt_id(rs.getString("it_id"));
				productVO.setSt_id(rs.getString("st_id"));
				productVO.setIt_num(rs.getInt("it_num"));
				productVO.setIt_name(rs.getString("it_name"));
				productVO.setIt_intro(rs.getString("it_intro"));
				
				
				productVO.setIt_pic(rs.getBytes("it_pic"));
				productVO.setIt_prc(rs.getDouble("it_prc"));
				productVO.setIt_sts(rs.getString("it_sts"));
				productVO.setIt_cate(rs.getString("it_cate"));
				
				
				
				
				
			}
			conn.commit();
			conn.setAutoCommit(true);
			// Handle any driver errors
		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return productVO;
	}

	@Override
	public Map<String, ProductVO> findBy_Cate_Sts_OnProduct(String it_cate) {
		Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
		Connection conn = null;		
	
	
		
		ProductVO product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();
				conn.setAutoCommit(false);
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(GET_ALL_STMT_BY_CATE_AND_STS_ON);
		
			pstmt.setString(1, it_cate);
			rs = pstmt.executeQuery();

			int i = 1;
			
			while (rs.next()) {
				product = new ProductVO();
				product.setIt_id(rs.getString("it_id"));
				product.setSt_id(rs.getString("st_id"));				
				product.setIt_num(rs.getInt("it_num"));
				product.setIt_name(rs.getString("it_name"));
				product.setIt_intro(rs.getString("it_intro"));
				product.setIt_pic(rs.getBytes("it_pic"));
				product.setIt_prc(rs.getDouble("it_prc"));
				product.setIt_sts(rs.getString("it_sts"));
				String it_cate2= rs.getString("it_cate");
				String is_cate_encode = dict.get(it_cate2);
				product.setIt_cate(is_cate_encode);
				map.put(String.valueOf(i++), product);
			}
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return map;
	}
	@Override
	public Map<String, ProductVO> getAllProductMapBy_it_id(String it_id) {
		Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
		Connection conn = null;		
		
		ProductVO product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();		
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(GET_ALL_STMT_BY_IT_ID);
			pstmt.setString(1, it_id);
			rs = pstmt.executeQuery();		
			int i = 1;
			
			while (rs.next()) {
				product = new ProductVO();
				product.setIt_id(rs.getString("it_id"));
				product.setSt_id(rs.getString("st_id"));				
				product.setIt_num(rs.getInt("it_num"));
				product.setIt_name(rs.getString("it_name"));
				product.setIt_intro(rs.getString("it_intro"));
				product.setIt_pic(rs.getBytes("it_pic"));
				product.setIt_prc(rs.getDouble("it_prc"));
				product.setIt_sts(rs.getString("it_sts"));
				String it_cate= rs.getString("it_cate");
				String is_cate_encode = dict.get(it_cate);
				product.setIt_cate(is_cate_encode);
				map.put(String.valueOf(i++), product);
			}
			conn.commit();
			if(conn != null) {
		        try {
		            conn.rollback(); 
		            }
		        catch(SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
			conn.setAutoCommit(true);
		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return map;
	}
	@Override
	public void updateNum(ProductVO productVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(UPDATE_NUM);
				
			
			
			pstmt.setInt(1, productVO.getIt_num());		
			pstmt.setString(2, productVO.getIt_id());
						
			

			pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);

			// Handle any driver errors
		} catch (SQLException se) {		
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public Map<String, ProductVO> getAllProductMapBy_it_name_Serach(String it_name) {

		Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
		Connection conn = null;		
		
		ProductVO product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();				
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(GET_ALL_STMT_BY_IT_NAME_SERACH);
			
			pstmt.setString(1, "%"+it_name+"%");	
			rs = pstmt.executeQuery();

			int i = 1;
			
			while (rs.next()) {
				product = new ProductVO();
				product.setIt_id(rs.getString("it_id"));
				product.setSt_id(rs.getString("st_id"));				
				product.setIt_num(rs.getInt("it_num"));
				product.setIt_name(rs.getString("it_name"));
				product.setIt_intro(rs.getString("it_intro"));
				product.setIt_pic(rs.getBytes("it_pic"));
				product.setIt_prc(rs.getDouble("it_prc"));
				product.setIt_sts(rs.getString("it_sts"));	
				String it_cate= rs.getString("it_cate");
				String is_cate_encode = dict.get(it_cate);
				product.setIt_cate(is_cate_encode);
				//
				map.put(String.valueOf(i++), product);
			}
				conn.commit();
				conn.setAutoCommit(true);

		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
		return map;
	}
	public Map<String, ProductVO> getAllProductMapBy_it_name_Serach_ST001(String it_name) {

		Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
		Connection conn = null;		
		
		ProductVO product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();				
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}	
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(GET_ALL_STMT_BY_IT_NAME_SERACH_ST001);
			
			pstmt.setString(1, "%"+it_name+"%");	
			rs = pstmt.executeQuery();

			int i = 1;
			
			while (rs.next()) {
				product = new ProductVO();
				product.setIt_id(rs.getString("it_id"));
				product.setSt_id(rs.getString("st_id"));				
				product.setIt_num(rs.getInt("it_num"));
				product.setIt_name(rs.getString("it_name"));
				product.setIt_intro(rs.getString("it_intro"));
				product.setIt_pic(rs.getBytes("it_pic"));
				product.setIt_prc(rs.getDouble("it_prc"));
				product.setIt_sts(rs.getString("it_sts"));	
				String it_cate= rs.getString("it_cate");
				String is_cate_encode = dict.get(it_cate);
				product.setIt_cate(is_cate_encode);
				//
				map.put(String.valueOf(i++), product);
			}
				conn.commit();
				conn.setAutoCommit(true);

		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
			        catch(SQLException ex) {
			            ex.printStackTrace();
			        }
			    }
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
		return map;
	}
	public Map<String, ProductVO> findBy_Cate_Sts_OnProduct_ST001(String it_cate) {
		Map<String, ProductVO> map = new LinkedHashMap<String, ProductVO>();
		Connection conn = null;		
	
	
		
		ProductVO product = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				Context ctx = new javax.naming.InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA107G4");
				conn = ds.getConnection();
				conn.setAutoCommit(false);
			} catch (NamingException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(GET_ALL_STMT_BY_CATE_AND_STS_ON_ST001);
		
			pstmt.setString(1, it_cate);
			rs = pstmt.executeQuery();

			int i = 1;
			
			while (rs.next()) {
				product = new ProductVO();
				product.setIt_id(rs.getString("it_id"));
				product.setSt_id(rs.getString("st_id"));				
				product.setIt_num(rs.getInt("it_num"));
				product.setIt_name(rs.getString("it_name"));
				product.setIt_intro(rs.getString("it_intro"));
				product.setIt_pic(rs.getBytes("it_pic"));
				product.setIt_prc(rs.getDouble("it_prc"));
				product.setIt_sts(rs.getString("it_sts"));
				String it_cate2= rs.getString("it_cate");
				String is_cate_encode = dict.get(it_cate2);
				product.setIt_cate(is_cate_encode);
				map.put(String.valueOf(i++), product);
			}
			conn.commit();
			conn.setAutoCommit(true);

		} catch (SQLException se) {
			 if(conn != null) {
			        try {
			        	conn.rollback(); }
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return map;
	}
	
}
