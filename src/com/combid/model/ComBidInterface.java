package com.combid.model;

import java.util.List;

public interface ComBidInterface {
	static final String SELECT="SELECT * FROM COMMISION_BIDDING_LIST WHERE MEM_ID=? AND COM_ID=?";
	static final String UPDATE="UPDATE COMMISION_BIDDING_LIST SET AUC_PRC=?, AUC_DEL_PRC=?, N_O_V=?, AT_STS=? WHERE MEM_ID=? AND COM_ID=?";
	static final String DELETE="DELETE from COMMISION_BIDDING_LIST WHERE MEM_ID=? AND COM_ID=?";
	static final String INSERT="INSERT INTO COMMISION_BIDDING_LIST (MEM_ID, COM_ID, AUC_PRC, AUC_DEL_PRC, N_O_V, AT_STS) VALUES (?,?,?,?,?,?)";
    static final String SELECTALL="SELECT * FROM COMMISION_BIDDING_LIST where Com_id=? and at_sts='競標中'"; 
 public ComBidVO select(String memid,String comid);
 public void insert(ComBidVO combidvo);
 public void update(ComBidVO combidvo);
 public void delete(String memid,String comid);
 public List<ComBidVO> selectAll(String comid);
}
