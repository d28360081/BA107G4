package com.comclc.model;

import java.util.List;

import com.com.model.ComVO;

public interface ComClcDAOInterface {
   public ComClcVO select(String memid,String comid);
   public void insert(ComClcVO comclcvo);
   public void delete(String memid,String comid);
   public void update(String memid,String comid_old,String comid_new);
   public List<ComClcVO> selectAll(String mem_id);
   
   static final String SELECT="SELECT * FROM COMMISION_COLLECTION WHERE MEM_ID=? and COM_ID=?";
   static final String INSERT="INSERT INTO COMMISION_COLLECTION (MEM_ID,COM_ID) VALUES (?,?)";
   static final String DELETE="DELETE FROM COMMISION_COLLECTION WHERE MEM_ID=? and COM_ID=?";
   static final String SELECTALL="SELECT * FROM COMMISION_COLLECTION where MEM_ID=?";
   static final String UPDATE="UPDATE COMMISION_COLLECTION SET COM_ID=? where com_id=? and mem_id=?";
 

}
