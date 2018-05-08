package com.PersonalStore.model;

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

import com.Product.model.ProductVO;



public class PersonalStoreDAO implements PersonalStore_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";

	private static final String INSERT_STMT = 
		"INSERT INTO Personal_Store (st_id,mem_id,st_name,st_intro,st_sts) VALUES ('ST'||LPAD(to_char(PERSONAL_STORE_SEQ.NEXTVAL), 3, '0'),?,?,?,?)";

	private static final String GET_ALL_STMT = 
		"SELECT * FROM Personal_Store order by st_id DESC";
	
	private static final String GET_ALL_PRODUCT_BY_ST_ID = 
			"SELECT * FROM PRODUCT WHERE  st_id=? order by it_id DESC";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM Personal_Store where st_id = ?";
	private static final String GET_ONE_STMT_BY_MEM_ID = 
			"SELECT * FROM Personal_Store where mem_id = ?";
	private static final String DELETE = 
		"DELETE FROM Personal_Store where st_id = ?";
	private static final String UPDATE = 
		"UPDATE Personal_Store set st_name=?, st_intro=?, st_sts=? where st_id = ?";

	@Override
	public void insert(PersonalStoreVO personalStoreVO) {

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
			pstmt = con.prepareStatement(INSERT_STMT);
			
					
			pstmt.setString(1, personalStoreVO.getMem_id());
			pstmt.setString(2, personalStoreVO.getSt_name());
			pstmt.setString(3, personalStoreVO.getSt_intro());
			pstmt.setString(4, personalStoreVO.getSt_sts());			

			pstmt.executeUpdate();

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
	public void update(PersonalStoreVO personalStoreVO) {

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

			
			
			
					
			pstmt.setString(1, personalStoreVO.getSt_name());
			pstmt.setString(2, personalStoreVO.getSt_intro());
			pstmt.setString(3, personalStoreVO.getSt_sts());
			pstmt.setString(4, personalStoreVO.getSt_id());

			

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String st_id) {

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

			pstmt.setString(1, st_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public PersonalStoreVO findByPrimaryKey(String st_id) {

		PersonalStoreVO personalStoreVO = null;
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

			pstmt.setString(1, st_id);

			rs = pstmt.executeQuery();			
			while (rs.next()) {
				
				personalStoreVO = new PersonalStoreVO();		
				personalStoreVO.setSt_id(rs.getString("st_id"));
				personalStoreVO.setMem_id(rs.getString("mem_id"));			
				personalStoreVO.setSt_name(rs.getString("st_name"));
				personalStoreVO.setSt_intro(rs.getString("st_intro"));
				personalStoreVO.setSt_sts(rs.getString("st_sts"));
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
		return personalStoreVO;
	}

	public PersonalStoreVO findByMemId(String mem_id) {

		PersonalStoreVO personalStoreVO = null;
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
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_MEM_ID);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();			
			if(rs.next()) {
				personalStoreVO = new PersonalStoreVO();		
				personalStoreVO.setSt_id(rs.getString("st_id"));
				personalStoreVO.setMem_id(rs.getString("mem_id"));			
				personalStoreVO.setSt_name(rs.getString("st_name"));
				personalStoreVO.setSt_intro(rs.getString("st_intro"));
				personalStoreVO.setSt_sts(rs.getString("st_sts"));
				return personalStoreVO;
			}else{
				return null;
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
	public List<PersonalStoreVO> getAll() {
		List<PersonalStoreVO> list = new ArrayList<PersonalStoreVO>();
		PersonalStoreVO personalStoreVO = null;

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
				// empVO �]�٬� Domain objects
				personalStoreVO = new PersonalStoreVO();
				personalStoreVO.setSt_id(rs.getString("st_id"));
				personalStoreVO.setMem_id(rs.getString("mem_id"));			
				personalStoreVO.setSt_name(rs.getString("st_name"));
				personalStoreVO.setSt_intro(rs.getString("st_intro"));
				personalStoreVO.setSt_sts(rs.getString("st_sts"));
				list.add(personalStoreVO); // Store the row in the list
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
	public Set<ProductVO> getProductsBySt_id(String st_id) {
		Set<ProductVO> set = new LinkedHashSet<ProductVO>();
		ProductVO product =null;
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
			pstmt = con.prepareStatement(GET_ALL_PRODUCT_BY_ST_ID);
			pstmt.setString(1, st_id);
			rs = pstmt.executeQuery();
			
	
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
				product.setIt_cate(rs.getString("it_cate"));
				set.add(product);
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
