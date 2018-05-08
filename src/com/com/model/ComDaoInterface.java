package com.com.model;

import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

import com.comclc.model.ComClcVO;
import com.compant.model.ComPantLsVO;

public interface ComDaoInterface {
	
	public void update(ComVO comvo);
	public void delete(String comid);
	public ComVO insert(ComVO comvo);
	public List<ComVO> selectAll();
	public ComVO select(String comid);
	public ComVO selectpic(String comid);
	public String joinselect(String comid,String memid);
	public ComVO select(String comid,String memid);
	public List<ComVO> selectJoinGroupFromComPantsLs(List<ComPantLsVO> list);
	public List<ComVO> selectJoinBuyFromComPantsLs(List<ComPantLsVO> list);
	public List<ComVO> selectHistory(String mem_id);
	public List<ComVO> selectSelf(String mem_id);
	  
	
	
	
	

}
