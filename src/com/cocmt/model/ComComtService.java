package com.cocmt.model;

import java.util.List;

public class ComComtService implements ComComtDAOInterface{
	private ComComtDAOJNDI comcomtdaojndi=new ComComtDAOJNDI();

	@Override
	public ComComtVO selectByPrimaryKey(String comid, String comtid) {
		return comcomtdaojndi.selectByPrimaryKey(comid, comtid);
	}

	@Override
	public ComComtVO insert(ComComtVO comcomtvo) {
		return comcomtdaojndi.insert(comcomtvo);
	}

	@Override
	public void delete(String comid, String comtid) {
		comcomtdaojndi.delete(comid, comtid);
		
	}

	@Override
	public void update(ComComtVO comcomtvo) {
		comcomtdaojndi.update(comcomtvo);
	}

	@Override
	public List<ComComtVO> selectAll(String comid) {
		return comcomtdaojndi.selectAll(comid);
	}

}
