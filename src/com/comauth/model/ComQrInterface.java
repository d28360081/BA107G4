package com.comauth.model;

public interface ComQrInterface {

	public ComQrVO select(String com_id,String mem_id);
	public void insert(ComQrVO comqrvo);
	public void delete(ComQrVO comqrvo);
	public void update(ComQrVO comqrvo);

}
