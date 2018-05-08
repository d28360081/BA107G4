package com.comrf.model;

import java.util.List;

public class ComRfService implements ComRfDAOInterface{
private ComRfDAOPJNDI comrfdaojndi=new  ComRfDAOPJNDI();
	@Override
	public ComRfVO selectByPrimaryKey(String comrfid) {
		// TODO Auto-generated method stub
		return comrfdaojndi.selectByPrimaryKey(comrfid);
	}

	@Override
	public ComRfVO insert(ComRfVO comrfvo) {
		// TODO Auto-generated method stub
		return comrfdaojndi.insert(comrfvo);
	}

	@Override
	public void delete(String comrfid) {
		// TODO Auto-generated method stub
		comrfdaojndi.delete(comrfid);
	}

	@Override
	public void update(ComRfVO comrfvo) {
		// TODO Auto-generated method stub
		comrfdaojndi.update(comrfvo);
	}

	@Override
	public List<ComRfVO> selectAll() {
		// TODO Auto-generated method stub
		return comrfdaojndi.selectAll();
	}
	

}
