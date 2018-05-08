package com.FavoriteProduct.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class FavoriteProductDAO implements FavoriteProductDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";
	private static final String INSERT_STMT = 
			"insert into Favorite_Product (mem_id,it_id)values(?,?)";	
	private static final String UPDATE = 
			"update Favorite_Product set it_id=? where mem_id = ?";	
	private static final String DELETE = 
			"delete from Favorite_Product where mem_id=? ";
	private static final String DELETE_BY_IT_ID = 
			"delete from Favorite_Product where it_id=? ";
	private static final String GET_ONE_STMT = 
			"select * from Favorite_Product where mem_id = ? ";	
	private static final String GET_ONE_STMT_BY_IT_ID = 
			"select * from Favorite_Product where it_id = ? ";	
	private static final String GET_ALL_STMT = 
			"select * from Favorite_Product order by mem_id DESC";
	private static final String GET_ALL_STMT_BY_IT_ID = 
			"select * from Favorite_Product WHERE it_id = ? order by mem_id DESC";
	private static final String GET_ALL_STMT_BY_MEM_ID = 
			"select * from Favorite_Product where mem_id=? order by mem_id DESC";
	
	
	@Override
	public void insert(FavoriteProductVO favoriteProductVO) {
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
			
			pstmt.setString(1, favoriteProductVO.getMem_id());
			pstmt.setString(2, favoriteProductVO.getIt_id());			
			
							
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
	public void update(FavoriteProductVO favoriteProductVO) {
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

			
			
			
			pstmt.setString(1, favoriteProductVO.getMem_id());
			pstmt.setString(2, favoriteProductVO.getIt_id());
			

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
	public void delete(String mem_id ) {

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

			pstmt.setString(1, mem_id);
	
		
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
	public FavoriteProductVO findByPrimaryKey(String mem_id) {
		FavoriteProductVO favoriteProductVO = null;
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

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				favoriteProductVO = new FavoriteProductVO();				
				
				favoriteProductVO.setMem_id(rs.getString("mem_id"));
				favoriteProductVO.setIt_id(rs.getString("it_id"));
				
				
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
		return favoriteProductVO;
	}

	
	@Override
	public List<FavoriteProductVO> getAll() {
		List<FavoriteProductVO> list = new ArrayList<FavoriteProductVO>();
		FavoriteProductVO favoriteProductVO = null;

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
				favoriteProductVO = new FavoriteProductVO();				
				
				favoriteProductVO.setMem_id(rs.getString("mem_id"));
				favoriteProductVO.setIt_id(rs.getString("it_id"));
				list.add(favoriteProductVO); // Store the row in the list
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
	public List<FavoriteProductVO> getAll_by_it_id(String it_id) {
		List<FavoriteProductVO> list = new ArrayList<FavoriteProductVO>();
		FavoriteProductVO favoriteProductVO = null;

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
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_IT_ID);
			pstmt.setString(1, it_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				favoriteProductVO = new FavoriteProductVO();				
				
				favoriteProductVO.setMem_id(rs.getString("mem_id"));
				favoriteProductVO.setIt_id(rs.getString("it_id"));
				list.add(favoriteProductVO); // Store the row in the list
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
	public List<FavoriteProductVO> getAll_by_mem_id(String mem_id) {
		List<FavoriteProductVO> list = new ArrayList<FavoriteProductVO>();
		FavoriteProductVO favoriteProductVO = null;

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
			pstmt = con.prepareStatement(GET_ALL_STMT_BY_MEM_ID);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				favoriteProductVO = new FavoriteProductVO();				
				
				favoriteProductVO.setMem_id(rs.getString("mem_id"));
				favoriteProductVO.setIt_id(rs.getString("it_id"));
				list.add(favoriteProductVO); // Store the row in the list
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
	public void deleteBy_it_id(String it_id) {
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
			pstmt = con.prepareStatement(DELETE_BY_IT_ID);

			pstmt.setString(1, it_id);
	
		
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
	public FavoriteProductVO findByPrimaryKey_by_it_id(String it_id) {
		FavoriteProductVO favoriteProductVO = null;
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
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_IT_ID);

			pstmt.setString(1, it_id);

			rs = pstmt.executeQuery();			
			while (rs.next()) {
		
				favoriteProductVO = new FavoriteProductVO();				
				
				favoriteProductVO.setMem_id(rs.getString("mem_id"));
				favoriteProductVO.setIt_id(rs.getString("it_id"));
				
				
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
		return favoriteProductVO;
	}

}
