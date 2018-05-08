package com.comclc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComClcDAOJDBC implements ComClcDAOInterface{

	@Override
	public ComClcVO select(String memid,String comid) {
		ComClcVO comclcvo=new ComClcVO();
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			PreparedStatement prst =c.prepareStatement(this.SELECT);
			prst.setString(1, memid);
			prst.setString(2, comid);
			ResultSet rs=prst.executeQuery();
			rs.next();
			comclcvo.setMem_id(rs.getString(1));
			comclcvo.setCom_id(rs.getString(2));
	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(c!=null){
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return comclcvo;
	}

	@Override
	public void insert(ComClcVO comclcvo) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.INSERT);
			prst.setString(1, comclcvo.getMem_id());
			prst.setString(2, comclcvo.getCom_id());
			prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
		} catch (ClassNotFoundException | SQLException e) {
			if(c!=null){
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(c!=null){
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
	public void delete(String memid, String comid) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.DELETE);
			prst.setString(1, memid);
			prst.setString(2, comid);
			prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
		} catch (ClassNotFoundException | SQLException e) {
			if(c!=null){
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(c!=null){
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
	public void update(String memid,String comid_old,String comid_new) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.UPDATE);
			prst.setString(1, comid_old);
			prst.setString(2, comid_new);
			prst.setString(3, memid);
			
			prst.executeUpdate();
			c.setAutoCommit(true);
			c.commit();
		} catch (ClassNotFoundException | SQLException e) {
			if(c!=null){
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(c!=null){
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
	public List<ComClcVO> selectAll(String mem_id) {
		List<ComClcVO> list=new ArrayList<ComClcVO>();
		Connection c =null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");		
			PreparedStatement prst =c.prepareStatement(this.SELECTALL);	
			prst.setString(1,mem_id);
			ResultSet rs=prst.executeQuery();
			while(rs.next()){
				ComClcVO comclcvo =new ComClcVO();
				comclcvo.setMem_id(rs.getString(1));
				comclcvo.setCom_id(rs.getString(2));
				list.add(comclcvo);
			}
			
					
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			if(c!=null){
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
