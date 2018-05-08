package com.emppmscls.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpPmsClsDAOJDBC implements EmpPmsClsDAOInterface{
	static final String INSERT="INSERT INTO EMP_PERMISSION_CLASS (PMS_ID,PMS_INTRO) VALUES('PMS'||LPAD(TO_CHAR(EMP_PMS_SEQ.NEXTVAL),3,'0'),?)";
	static final String DELETE="DELETE FROM EMP_PERMISSION_CLASS WHERE PMS_ID=?";
	static final String SELECT="SELECT * FROM EMP_PERMISSION_CLASS WHERE PMS_ID=?";
	static final String SELECTALL="SELECT * FROM EMP_PERMISSION_CLASS ";
	static final String UPDATE="UPDATE EMP_PERMISSION_CLASS SET PMS_INTRO=? WHERE PMS_ID=?";
	
	@Override
	public EmpPmsClsVO select(String pmsid) {
		Connection c=null;	 
		EmpPmsClsVO emppmsclsvo=new EmpPmsClsVO();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				 c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	
				 PreparedStatement prst=c.prepareStatement(this.SELECT);
				 prst.setString(1, pmsid);
				 ResultSet rs=prst.executeQuery();
				 while(rs.next()){
					 emppmsclsvo.setPmsid(rs.getString(1));
					 emppmsclsvo.setPmsintro(rs.getString(2));
				 }
				 
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
			
		return emppmsclsvo;
	}

	@Override
	public EmpPmsClsVO insert(EmpPmsClsVO emppmsclsvo) {
		Connection c=null;	 
			try {
				 Class.forName("oracle.jdbc.driver.OracleDriver");
				 c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");		
				 c.setAutoCommit(false);
				 String[] generateKey =new String[]{"PMS_ID"};
				 PreparedStatement prst=c.prepareStatement(this.INSERT,generateKey);
				 
				 prst.setString(1, emppmsclsvo.getPmsintro());
				 prst.executeUpdate();
				 ResultSet rs=prst.getGeneratedKeys();
				 while(rs.next()){
					 emppmsclsvo.setPmsid(rs.getString(1));
				 }
				 c.setAutoCommit(true);
				 c.commit();
			} catch (SQLException | ClassNotFoundException e) {
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
			return emppmsclsvo;
	}

	@Override
	public void delete(String pmsid) {
		Connection c=null;	 
		  
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	
			 c.setAutoCommit(false);
			 PreparedStatement prst=c.prepareStatement(this.DELETE);
			 prst.setString(1, pmsid);
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
	public void update(EmpPmsClsVO emppmsclsvo) {
		Connection c=null;	 
		  
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	
			 c.setAutoCommit(false);
			 PreparedStatement prst=c.prepareStatement(this.UPDATE);			 
			 prst.setString(1, emppmsclsvo.getPmsintro());
			 prst.setString(2, emppmsclsvo.getPmsid());
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
	public List<EmpPmsClsVO> selectall() {
		Connection c=null;	 
		List<EmpPmsClsVO> list=new ArrayList<EmpPmsClsVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	
			 PreparedStatement prst=c.prepareStatement(this.SELECTALL);
			 ResultSet rs=prst.executeQuery();
			 while(rs.next()){
				 EmpPmsClsVO emppmsclsvo=new EmpPmsClsVO();
				 emppmsclsvo.setPmsid(rs.getString(1));
				 emppmsclsvo.setPmsintro(rs.getString(2));
				 list.add(emppmsclsvo);
			 }
			
		} catch (ClassNotFoundException | SQLException e) {		
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
