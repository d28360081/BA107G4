package com.comapl.model;

import java.util.List;

public class ComAplService implements ComAplInterface{
private ComAplDAOJndi comapldaojndi=new ComAplDAOJndi();
	@Override
	public ComAplVO insert(ComAplVO comaplvo) {
		// TODO Auto-generated method stub
		return comapldaojndi.insert(comaplvo);
	}

	@Override
	public void update(ComAplVO comaplvo) {
		comapldaojndi.update(comaplvo);
		
	}

	@Override
	public void delete(String conaplid) {
		 comapldaojndi.delete(conaplid);
		
	}

	@Override
	public ComAplVO select(String conaplid) {
		return comapldaojndi.select(conaplid);
	}

	@Override
	public List<ComAplVO> selectAll() {
		return comapldaojndi.selectAll();
	}

}
