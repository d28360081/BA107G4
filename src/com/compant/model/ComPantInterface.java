package com.compant.model;

import java.util.List;

import com.combid.model.ComBidVO;

public interface ComPantInterface {
	static final String SELECT="SELECT * FROM COMMISION_PARTICIPANTS_LIST WHERE MEM_ID=? and COM_ID=?";
	static final String UPDATE="UPDATE COMMISION_PARTICIPANTS_LIST SET MEM_STS=?, COM_IT_NUM=? ,DLT_ADDS=? ,COM_PY=? , COM_STS=?, DLV_ID=?, COR_ID=? WHERE MEM_ID=? and COM_ID=?";
	static final String DELETE="delete from COMMISION_PARTICIPANTS_LIST WHERE MEM_ID=? and COM_ID=?";
	static final String INSERT="INSERT INTO COMMISION_PARTICIPANTS_LIST (MEM_ID, COM_ID, MEM_STS, COM_IT_NUM, DLT_ADDS, COM_PY, COM_STS, DLV_ID, COR_ID) VALUES(?,?,?,?,?,?,?,?,?)";
    static final String SELECTALL="SELECT * FROM COMMISION_PARTICIPANTS_LIST where mem_id=? and mem_sts!='未投票' and mem_sts!='退出'"; 
    static final String SELECTSTS="SELECT * FROM COMMISION_PARTICIPANTS_LIST where com_id=? and mem_STS=?";
    static final String SELECTINCASE="SELECT * FROM COMMISION_PARTICIPANTS_LIST WHERE COM_ID=?";
    static final String SELECTUNVOTED="SELECT * FROM COMMISION_PARTICIPANTS_LIST WHERE COM_ID=? AND mem_sts='參與中' or com_id=? and mem_sts='已讀代購通知'";
    
 public ComPantLsVO select(String memid,String comid);
 public List<ComPantLsVO> selectSTS(String comid,String sts);
 public void insert(ComPantLsVO compantlsvo);
 public void update(ComPantLsVO compantlsvo);
 public void delete(String memid,String comid);
 public List<ComPantLsVO> selectAll(String mem_id);
 public List<ComPantLsVO> selectAllinCase(String com_id);
 
}
