package com.combid.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComBidDAOJdbc implements ComBidInterface{

	@Override
	public ComBidVO select(String memid,String comid) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			ComBidVO combidvo=new ComBidVO();
			PreparedStatement prst =c.prepareStatement(ComBidDAOJdbc.SELECT);
			prst.setString(1, memid);
			prst.setString(2, comid);
			ResultSet rs=prst.executeQuery();
			while(rs.next()) {
				combidvo.setMem_id(rs.getString(1));
				combidvo.setCom_id(rs.getString(2));
				combidvo.setAuc_prc(rs.getDouble(3));
				combidvo.setAuc_del_prc(rs.getDouble(4));
				combidvo.setN_o_v(rs.getInt(5));
				combidvo.setAt_sts(rs.getString(6));
			}
			return combidvo;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public void insert(ComBidVO combidvo) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.INSERT);
			prst.setString(1, combidvo.getMem_id());
			prst.setString(2, combidvo.getCom_id());
			prst.setDouble(3, combidvo.getAuc_prc());
			prst.setDouble(4,combidvo.getAuc_del_prc());
			prst.setInt(5, combidvo.getN_o_v());
			prst.setString(6, combidvo.getAt_sts());
		    prst.executeUpdate();	
		    c.setAutoCommit(true);
		    c.commit();
		} catch (ClassNotFoundException | SQLException e) {
			if(c!=null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void update(ComBidVO combidvo) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.UPDATE);
			prst.setDouble(1, combidvo.getAuc_prc());
			prst.setDouble(2,combidvo.getAuc_del_prc());
			prst.setInt(3, combidvo.getN_o_v());
			prst.setString(4, combidvo.getAt_sts());
			prst.setString(5, combidvo.getMem_id());
			prst.setString(6, combidvo.getCom_id());
			prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
		} catch (ClassNotFoundException | SQLException e) {
			if(c!=null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void delete(String memid,String comid) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			PreparedStatement prst =c.prepareStatement(this.DELETE);
			prst.setString(1, memid);
			prst.setString(2, comid);
			prst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			if(c!=null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<ComBidVO> selectAll(String comid) {
		Connection c=null;
		List<ComBidVO> list=new ArrayList<ComBidVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			PreparedStatement prst =c.prepareStatement(this.SELECTALL);
			prst.setString(1, comid);
			ResultSet rs=prst.executeQuery();			
			while(rs.next()) {
				ComBidVO combidvo=new ComBidVO();
				combidvo.setMem_id(rs.getString(1));
				combidvo.setCom_id(rs.getString(2));
				combidvo.setAuc_prc(rs.getDouble(3));
				combidvo.setAuc_del_prc(rs.getDouble(4));
				combidvo.setN_o_v(rs.getInt(5));
				combidvo.setAt_sts(rs.getString(6));
				list.add(combidvo);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			if(c!=null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally {
			if(c!=null) {
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}
