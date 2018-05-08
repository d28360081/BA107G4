package com.comclc.model;

import java.util.List;

public class ComClcService implements ComClcDAOInterface{
private ComClcDAOJNDI comclcdaojndi=new ComClcDAOJNDI();
	@Override
	public ComClcVO select(String memid, String comid) {
		// TODO Auto-generated method stub
		
		return comclcdaojndi.select(memid, comid);
	}

	@Override
	public void insert(ComClcVO comclcvo) {
		comclcdaojndi.insert(comclcvo);
		
	}

	@Override
	public void delete(String memid, String comid) {
		comclcdaojndi.delete(memid, comid);
		
	}

	@Override
	public void update(String memid, String comid_old, String comid_new) {
		comclcdaojndi.update(memid, comid_old, comid_new);
		
	}

	@Override
	public List<ComClcVO> selectAll(String mem_id) {
		return comclcdaojndi.selectAll(mem_id);
	}

}
