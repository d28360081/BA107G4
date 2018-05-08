package com.compant.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComPantLsService implements ComPantInterface{
private ComPantLsDAOJNDI compantlsdaojndi=new ComPantLsDAOJNDI();
	@Override
	public ComPantLsVO select(String memid, String comid) {
		// TODO Auto-generated method stub
		System.out.println(compantlsdaojndi.select(memid, comid)==null);
		return compantlsdaojndi.select(memid, comid);
	}

	@Override
	public List<ComPantLsVO> selectSTS(String comid, String sts) {
		// TODO Auto-generated method stub
		return compantlsdaojndi.selectSTS(comid, sts);
	}

	@Override
	public void insert(ComPantLsVO compantlsvo) {
		compantlsdaojndi.insert(compantlsvo);
		
	}

	@Override
	public void update(ComPantLsVO compantlsvo) {
		compantlsdaojndi.update(compantlsvo);
		
	}

	@Override
	public void delete(String memid, String comid) {
		compantlsdaojndi.delete(memid, comid);
		
	}

	@Override
	public List<ComPantLsVO> selectAll(String mem_id) {
		// TODO Auto-generated method stub
		return compantlsdaojndi.selectAll(mem_id);
	}

	@Override
	public List<ComPantLsVO> selectAllinCase(String com_id) {
		
		return compantlsdaojndi.selectAllinCase(com_id);
	}
	
	public HashMap<String,ComPantLsVO> selectUnVoted(String com_id){ 
		return compantlsdaojndi.selectUnVoted(com_id);
	}
	
	public List<ComPantLsVO> selectToVote(String com_id){
		return compantlsdaojndi.selectToVote(com_id);
		
	}
	
	public Map<String,ComPantLsVO> toMap(List<ComPantLsVO> list){
		Map<String,ComPantLsVO> map=new HashMap<String,ComPantLsVO>();
		for(ComPantLsVO cplv:list){
			map.put(cplv.getMem_id(), cplv);
		}
		return map;
	}

}
