package android.com.compant.model;

import java.sql.Connection;
import java.util.List;



public interface ComPantInterface {
	static final String SELECT="SELECT * FROM COMMISION_PARTICIPANTS_LIST WHERE MEM_ID=? and COM_ID=?";
	static final String UPDATE="UPDATE COMMISION_PARTICIPANTS_LIST SET MEM_STS=?, COM_IT_NUM=? ,DLT_ADDS=? ,COM_PY=? , COM_STS=?, DLV_ID=?, COR_ID=? WHERE MEM_ID=? and COM_ID=?";
	static final String DELETE="delete from COMMISION_PARTICIPANTS_LIST WHERE MEM_ID=? and COM_ID=?";
	static final String INSERT="INSERT INTO COMMISION_PARTICIPANTS_LIST (MEM_ID, COM_ID, MEM_STS, COM_IT_NUM, DLT_ADDS, COM_PY, COM_STS, DLV_ID, COR_ID) VALUES(?,?,?,?,?,?,?,?,?)";
    static final String SELECTALL="SELECT * FROM COMMISION_PARTICIPANTS_LIST where mem_id=?"; 
    static final String SELECTSTS="SELECT * FROM COMMISION_PARTICIPANTS_LIST where com_id=? and mem_STS=?";
    static final String SELECTINCASE="SELECT * FROM COMMISION_PARTICIPANTS_LIST WHERE COM_ID=?";
    static final String SELECTUNVOTED="SELECT * FROM COMMISION_PARTICIPANTS_LIST WHERE COM_ID=? AND mem_sts='參與中'";
	static final String IS_EXIST_IN_PLIST= "SELECT * FROM COMMISION_PARTICIPANTS_LIST WHERE MEM_ID=? AND COM_ID=?";

    
 public ComPantLsVO select(String memid,String comid);
 public List<ComPantLsVO> selectSTS(String comid,String sts);

 public void update(ComPantLsVO compantlsvo);
 public void delete(String memid,String comid);
 public List<ComPantLsVO> selectAll(String mem_id);
 public List<ComPantLsVO> selectAllPartList(String com_id);
 
 public List<ComPantLsVO> selectAllinCase(String com_id);
 public	void insert(ComPantLsVO compantlsvo, Connection c);
 public	Boolean insert(ComPantLsVO compantlsvo);

 public boolean isAlreadyInPartList(String memid,String comid);
 public void updateSts(String memid,String comid);
 
}
