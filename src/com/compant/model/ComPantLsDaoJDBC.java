package com.compant.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.combid.model.ComBidVO;



public class ComPantLsDaoJDBC implements ComPantInterface{

	
	@Override
	public ComPantLsVO select(String memid, String comid) {
		ComPantLsVO compantlsvo=new ComPantLsVO();
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	
			PreparedStatement prst =c.prepareStatement(this.SELECT);
			prst.setString(1, memid);
			prst.setString(2, comid);
			ResultSet rs=prst.executeQuery();
			while(rs.next()) {
				compantlsvo.setMem_id(rs.getString(1));
				compantlsvo.setCom_id(rs.getString(2));
				compantlsvo.setMem_sts(rs.getString(3));
				compantlsvo.setCom_it_num(rs.getInt(4));
				compantlsvo.setDlt_adds(rs.getString(5));
				compantlsvo.setCom_py(rs.getString(6));
				compantlsvo.setCom_sts(rs.getString(7));
				compantlsvo.setDlv_id(rs.getString(8));
				compantlsvo.setCor_id(rs.getString(9));
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
		return compantlsvo;
		
	}

	@Override
	public void insert(ComPantLsVO compantlsvo) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.INSERT);
		    prst.setString(1, compantlsvo.getMem_id());
		    prst.setString(2, compantlsvo.getCom_id());
		    prst.setString(3, compantlsvo.getMem_sts());
		    prst.setInt(4, compantlsvo.getCom_it_num());
		    prst.setString(5, compantlsvo.getDlt_adds());
		    prst.setString(6, compantlsvo.getCom_py());
		    prst.setString(7, compantlsvo.getCom_sts());
		    prst.setString(8, compantlsvo.getDlv_id());
		    prst.setString(9, compantlsvo.getCor_id());
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
	public void update(ComPantLsVO compantlsvo) {
		Connection c=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.UPDATE);
			prst.setString(1, compantlsvo.getMem_sts());
			prst.setInt(2, compantlsvo.getCom_it_num());
			prst.setString(3, compantlsvo.getDlt_adds());
			prst.setString(4, compantlsvo.getCom_py());
			prst.setString(5, compantlsvo.getCom_sts());
			prst.setString(6,compantlsvo.getDlv_id());
			prst.setString(7,compantlsvo.getCor_id());
			prst.setString(8, compantlsvo.getMem_id());
			prst.setString(9, compantlsvo.getCom_id());
		
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
	public void delete(String memid,String comid) {
		Connection c=null;
		try {
			c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			c.setAutoCommit(false);
			PreparedStatement prst =c.prepareStatement(this.DELETE);
		    prst.setString(1, memid);
		    prst.setString(2, comid);
		    prst.executeUpdate();	
		    c.setAutoCommit(true);
			c.commit();
		} catch (SQLException e) {
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
	public List<ComPantLsVO> selectAll(String comid) {
		 List<ComPantLsVO> list=new ArrayList<ComPantLsVO> ();
		Connection c=null;
		try {
			c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");	
			PreparedStatement prst =c.prepareStatement(this.SELECTALL);
			prst.setString(1, comid);
		    ResultSet rs=prst.executeQuery();
		    while(rs.next()){
		    	ComPantLsVO compantlsvo=new ComPantLsVO();
		    	compantlsvo.setMem_id(rs.getString(1));
		    	compantlsvo.setCom_id(rs.getString(2));
		    	compantlsvo.setMem_sts(rs.getString(3));
		    	compantlsvo.setCom_it_num(rs.getInt(4));
		    	compantlsvo.setDlt_adds(rs.getString(5));
		    	compantlsvo.setCom_py(rs.getString(6));
                compantlsvo.setCom_sts(rs.getString(7));
                compantlsvo.setDlv_id(rs.getString(8));
                compantlsvo.setCor_id(rs.getString(9));
                list.add(compantlsvo);
		    }
		
		} catch (SQLException e) {
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
		return list;
		
	}

	@Override
	public List<ComPantLsVO> selectSTS(String comid, String memsts) {
		 List<ComPantLsVO> list=new ArrayList<ComPantLsVO> ();
			Connection c=null;
				try {
					c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
					PreparedStatement prst =c.prepareStatement(this.SELECTALL);
					prst.setString(1, comid);
					prst.setString(2, memsts);
				    ResultSet rs=prst.executeQuery();
				    while(rs.next()){
				    	ComPantLsVO compantlsvo=new ComPantLsVO();
				    	compantlsvo.setMem_id(rs.getString(1));
				    	compantlsvo.setCom_id(rs.getString(2));
				    	compantlsvo.setMem_sts(rs.getString(3));
				    	compantlsvo.setCom_it_num(rs.getInt(4));
				    	compantlsvo.setDlt_adds(rs.getString(5));
				    	compantlsvo.setCom_py(rs.getString(6));
		                compantlsvo.setCom_sts(rs.getString(7));
		                compantlsvo.setDlv_id(rs.getString(8));
		                compantlsvo.setCor_id(rs.getString(9));
		                list.add(compantlsvo);
				    }
				} catch (SQLException e) {
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
				return list;
	}

	
	@Override
	public List<ComPantLsVO> selectAllinCase(String com_id) {
		 List<ComPantLsVO> list=new ArrayList<ComPantLsVO> ();
		 Connection c=null;
			try {
				c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","Bredon","123");
			
			PreparedStatement prst =c.prepareStatement(this.SELECTINCASE);
			prst.setString(1, com_id);
		    ResultSet rs=prst.executeQuery();
		    while(rs.next()){
		    	ComPantLsVO compantlsvo=new ComPantLsVO();
		    	compantlsvo.setMem_id(rs.getString(1));
		    	compantlsvo.setCom_id(rs.getString(2));
		    	compantlsvo.setMem_sts(rs.getString(3));
		    	compantlsvo.setCom_it_num(rs.getInt(4));
		    	compantlsvo.setDlt_adds(rs.getString(5));
		    	compantlsvo.setCom_py(rs.getString(6));
                compantlsvo.setCom_sts(rs.getString(7));
                compantlsvo.setDlv_id(rs.getString(8));
                compantlsvo.setCor_id(rs.getString(9));
                list.add(compantlsvo);
		    }
		
		} catch (SQLException e) {
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
		return list;
	}	
	
	
	public List<ComPantLsVO> selectToVote(String com_id){
		List<ComPantLsVO> list=new ArrayList<ComPantLsVO>();
		 Connection c=null;
			try {
				c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BA107G4","123");
			PreparedStatement prst =c.prepareStatement("select * from commision_participants_list where com_id=? and MEM_STS='已讀代購通知'");
			prst.setString(1, com_id);
		    ResultSet rs=prst.executeQuery();
		    while(rs.next()){
		    	ComPantLsVO compantlsvo=new ComPantLsVO();
		    	compantlsvo.setMem_id(rs.getString(1));
		    	compantlsvo.setCom_id(rs.getString(2));
		    	compantlsvo.setMem_sts(rs.getString(3));
		    	compantlsvo.setCom_it_num(rs.getInt(4));
		    	compantlsvo.setDlt_adds(rs.getString(5));
		    	compantlsvo.setCom_py(rs.getString(6));
               compantlsvo.setCom_sts(rs.getString(7));
               compantlsvo.setDlv_id(rs.getString(8));
               compantlsvo.setCor_id(rs.getString(9));
               list.add(compantlsvo);
		    }
		
		} catch (SQLException e) {
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
		return list;
	}
	
	
	
}
