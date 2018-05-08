package com.com.model;

import java.util.List;

import com.compant.model.ComPantLsVO;
import com.member.model.MemVO;


public class ComService implements ComDaoInterface{
private final ComDaoJndi comdaojndi=new ComDaoJndi();
	@Override
	public void update(ComVO comvo) throws RuntimeException{
		try{
		comdaojndi.update(comvo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String comid) throws RuntimeException{
	     comdaojndi.delete(comid);
		
	}

	@Override
	public ComVO insert(ComVO comvo) throws RuntimeException{
		return comdaojndi.insert(comvo);
	}

	@Override
	public List<ComVO> selectAll() throws RuntimeException{
		return comdaojndi.selectAll();
	}

	@Override
	public ComVO select(String comid) throws RuntimeException{
		
		return comdaojndi.select(comid);
	}

	@Override
	public ComVO selectpic(String comid) throws RuntimeException{
		return comdaojndi.selectpic(comid);
	}

	@Override
	public String joinselect(String comid, String memid) throws RuntimeException{
		return comdaojndi.joinselect(comid, memid);
	}

	@Override
	public ComVO select(String comid, String memid) throws RuntimeException{
		return comdaojndi.select(comid, memid);
	}

	@Override
	public List<ComVO> selectJoinGroupFromComPantsLs(List<ComPantLsVO> list) throws RuntimeException{
	  return comdaojndi.selectJoinGroupFromComPantsLs(list);
	}

	@Override
	public List<ComVO> selectJoinBuyFromComPantsLs(List<ComPantLsVO> list) throws RuntimeException{
		return comdaojndi.selectJoinBuyFromComPantsLs(list);
	}

	@Override
	public List<ComVO> selectHistory(String mem_id) throws RuntimeException{
		return comdaojndi.selectHistory(mem_id);
	}

	@Override
	public List<ComVO> selectSelf(String mem_id) throws RuntimeException{
		return comdaojndi.selectSelf(mem_id);
	}
	public List<ComVO> selectDaiGo()throws RuntimeException{
		return comdaojndi.selectDaiGo();
	}
	public List<ComVO> search(String com_id){
		return comdaojndi.search(com_id);
	}            
	public ComVO selectOnlineDaiGo(String com_id){
		System.out.println(123);
		return comdaojndi.selectOnlineDaiGo(com_id);
	}
	public ComPantLsVO insertCom(ComVO comvo,MemVO memvo){
		return comdaojndi.selectinsertcom(comvo,memvo);
	}
	public void updatePIC(ComVO ComVO){
		comdaojndi.updatePIC(ComVO);
	}
	public void updateCkPic(byte[] bytePic,String com_id){
		comdaojndi.updateCkPic(bytePic,com_id);
	}
	public List<ComVO> selectDaiGoToCount(){
		return comdaojndi.selectDaiGotoCount();
	}
	public List<ComVO> selectTuanGoToCount(){
		return comdaojndi.selectTuanGoToCount();
	}

}
