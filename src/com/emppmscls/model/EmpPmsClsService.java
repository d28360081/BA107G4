package com.emppmscls.model;

import java.util.List;

public class EmpPmsClsService implements EmpPmsClsDAOInterface{
private EmpPmsClsDAOJNDI epcdj=new EmpPmsClsDAOJNDI();
	
	
	@Override
	public EmpPmsClsVO select(String pmsid) {
		return epcdj.select(pmsid);
		
	}

	@Override
	public EmpPmsClsVO insert(EmpPmsClsVO emppmsclsvo) {
		return epcdj.insert(emppmsclsvo);
	}

	@Override
	public void delete(String pmsid) {
		epcdj.delete(pmsid);
		
	}

	@Override
	public void update(EmpPmsClsVO emppmsclsvo) {
		epcdj.update(emppmsclsvo);
		
	}

	@Override
	public List<EmpPmsClsVO> selectall() {
		return epcdj.selectall();
	}

}
