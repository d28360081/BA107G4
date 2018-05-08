package android.com.comnof.model;

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

public class ComNofJndi implements ComNofInterface {

	static DataSource ds;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ComNofVO select(String nof_id) {
		   Connection c=null;
		   ComNofVO comnofvo=new ComNofVO();
					try {
					c=ds.getConnection();
					c.setAutoCommit(false);
					PreparedStatement prst =c.prepareStatement("select * from Notification where nof_id =?");
					prst.setString(1, nof_id);
					ResultSet rs=prst.executeQuery();
				    while(rs.next()){
				    	
				    	comnofvo.setNof_id(rs.getString(1));
				    	comnofvo.setMem_id(rs.getString(2));
				    	comnofvo.setCom_id(rs.getString(3));
				    	comnofvo.setNof_tit(rs.getString(4));
				    	comnofvo.setNof_cnt(rs.getString(5));
				    	comnofvo.setNof_sts(rs.getString(6));
				    	comnofvo.setNof_time(rs.getTimestamp(7));
					}
					c.setAutoCommit(true);
					return comnofvo;
					
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
				return null;
			}
	

	@Override
	public void delete(String nof_id) {
		   Connection c=null;
		   ComNofVO comnofvo=new ComNofVO();
					try {
					c=ds.getConnection();
					c.setAutoCommit(false);
					PreparedStatement prst =c.prepareStatement("delete from Notification where nof_id =?");
					prst.setString(1, nof_id);
					prst.executeUpdate();
				    
					c.setAutoCommit(true);

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
	public ComNofVO insert(ComNofVO comnofvo) {
		            Connection c=null;
					try {
					c=ds.getConnection();
					c.setAutoCommit(false);
					String[] generateKey=new String[]{"nof_id"};
					PreparedStatement prst =c.prepareStatement("insert into NOTIFICATION (nof_id,mem_id,com_id,nof_tit,nof_cnt,nof_sts,nof_time) values ('NOF'||LPAD(TO_CHAR(NOF_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,sysdate)",generateKey);
					
					prst.setString(1, comnofvo.getMem_id());
					prst.setString(2, comnofvo.getCom_id());
					prst.setString(3, comnofvo.getNof_tit());

					prst.setString(4, comnofvo.getNof_cnt());
					prst.setString(5,comnofvo.getNof_sts());
				
					prst.executeUpdate();
					
					ResultSet rs=prst.getGeneratedKeys();
					
				    if(rs.next()){
				    	comnofvo.setNof_id(rs.getString(1));  
				    	
					}
					c.setAutoCommit(true);
					c.commit();
					return comnofvo;
					
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
				return null;
	}

	@Override
	public List<ComNofVO> selectAll(String mem_id) {
		   Connection c=null;
		   
					try {
					c=ds.getConnection();
					c.setAutoCommit(false);
					PreparedStatement prst =c.prepareStatement("select * from Notification where mem_id =? and nof_sts='已讀' OR mem_id =? and nof_sts='未讀'");
					prst.setString(1, mem_id);
					prst.setString(2, mem_id);
					ResultSet rs=prst.executeQuery();
					List<ComNofVO> list=new ArrayList<ComNofVO>();
				    while(rs.next()){
				    	ComNofVO comnofvo=new ComNofVO();
				    	comnofvo.setNof_id(rs.getString(1));
				    	comnofvo.setMem_id(rs.getString(2));
				    	comnofvo.setCom_id(rs.getString(3));
				    	comnofvo.setNof_tit(rs.getString(4));
				    	comnofvo.setNof_cnt(rs.getString(5));
				    	comnofvo.setNof_sts(rs.getString(6));
				    	comnofvo.setNof_time(rs.getTimestamp(7));
				    	list.add(comnofvo);
					}
					c.setAutoCommit(true);
					return list;
					
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
				return null;
	}

	@Override
	public void update(ComNofVO comnofvo) throws RuntimeException{
		Connection c=null;
		try {
		c=ds.getConnection();
		c.setAutoCommit(false);
		PreparedStatement prst =c.prepareStatement("update notification set NOF_TIT=?,nof_cnt=?,nof_sts=? where nof_id=?");
		//prst.setString(1, comnofvo.getMem_id());
		prst.setString(1, comnofvo.getNof_tit());
		prst.setString(2, comnofvo.getNof_cnt());
		prst.setString(3,comnofvo.getNof_sts());
		prst.setString(4, comnofvo.getNof_id());
		prst.executeUpdate();
        
		c.commit();
		c.setAutoCommit(true);
	 
		
	} catch (SQLException e) {
		if(c!=null){
			try {
				c.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
	
	}finally{
		if(c!=null){
			try {
				c.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
	
		
	}
	
	public List<ComNofVO> selectAllUnread(String mem_id) {
		   Connection c=null;
		   
					try {
					c=ds.getConnection();
					c.setAutoCommit(false);
					PreparedStatement prst =c.prepareStatement("select * from NOTIFICATION where mem_id =? and nof_sts='未讀'");
					prst.setString(1, mem_id);
					ResultSet rs=prst.executeQuery();
					List<ComNofVO> list=new ArrayList<ComNofVO>();
				    while(rs.next()){
				    	ComNofVO comnofvo=new ComNofVO();
				    
				    	
				    	comnofvo.setNof_id(rs.getString(1));
				    	comnofvo.setMem_id(rs.getString(2));
				    	comnofvo.setCom_id(rs.getString(3));
				    	comnofvo.setNof_tit(rs.getString(4));
				    	comnofvo.setNof_cnt(rs.getString(5));
				    	comnofvo.setNof_sts(rs.getString(6));
				    	list.add(comnofvo);
					}
					c.setAutoCommit(true);
					
					return list;
					
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
				return null;
	}

	
	@Override
	public List<ComNofVO> selectCom_Nof(String com_id) {
		   Connection c=null;
		   
					try {
					c=ds.getConnection();
					c.setAutoCommit(false);
					PreparedStatement prst =c.prepareStatement("select * from Notification where COM_ID =? and nof_sts='代購留言' ORDER BY NOF_TIME ASC");
					prst.setString(1, com_id);
					ResultSet rs=prst.executeQuery();
					List<ComNofVO> list=new ArrayList<ComNofVO>();
				    while(rs.next()){
				    	ComNofVO comnofvo=new ComNofVO();
				    	comnofvo.setNof_id(rs.getString(1));
				    	comnofvo.setMem_id(rs.getString(2));
				    	comnofvo.setCom_id(rs.getString(3));
				    	comnofvo.setNof_tit(rs.getString(4));
				    	comnofvo.setNof_cnt(rs.getString(5));
				    	comnofvo.setNof_sts(rs.getString(6));
				    	comnofvo.setNof_time(rs.getTimestamp(7));
				    	list.add(comnofvo);
					}
					c.setAutoCommit(true);
					return list;
					
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
				return null;
			}
	
	public List<ComNofVO> selectCom_NofSys(String com_id) {
		   Connection c=null;
		   
					try {
					c=ds.getConnection();
					c.setAutoCommit(false);
					PreparedStatement prst =c.prepareStatement("select * from Notification where COM_ID =? and nof_sts='代購通知' ORDER BY COM_TIME ASC");
					prst.setString(1, com_id);
					ResultSet rs=prst.executeQuery();
					List<ComNofVO> list=new ArrayList<ComNofVO>();
				    while(rs.next()){
				    	ComNofVO comnofvo=new ComNofVO();
				    	comnofvo.setNof_id(rs.getString(1));
				    	comnofvo.setMem_id(rs.getString(2));
				    	comnofvo.setCom_id(rs.getString(3));
				    	comnofvo.setNof_tit(rs.getString(4));
				    	comnofvo.setNof_cnt(rs.getString(5));
				    	comnofvo.setNof_sts(rs.getString(6));
				    	comnofvo.setNof_time(rs.getTimestamp(7));
				    	list.add(comnofvo);
					}
					c.setAutoCommit(true);
					return list;
					
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
				return null;
			}
	

}
