package com.comauth.model;

public class ComQrService implements ComQrInterface{
ComQrDAO comqrdao=new ComQrDAO();
	@Override
	public ComQrVO select(String com_id,String mem_id) {
		return comqrdao.select(com_id,mem_id);
	}

	@Override
	public void insert(ComQrVO comqrvo) {
		comqrdao.insert(comqrvo);
		
	}

	@Override
	public void delete(ComQrVO comqrvo) {
		comqrdao.delete(comqrvo);
		
	}

	@Override
	public void update(ComQrVO comqrvo) {
		// TODO Auto-generated method stub
		comqrdao.update(comqrvo);		
	}

}
