package com.emppms.model;

import java.util.ArrayList;
import java.util.List;

public class EmpPmsService implements EmpPmsInterface{
	private EmpPmsDAOJNDI epdj=new EmpPmsDAOJNDI();
     

	@Override
	public EmpPmsVO insert(EmpPmsVO emppmsvo) {
		return epdj.insert(emppmsvo);
	}

	@Override
	public void update(EmpPmsVO emppmsvo, String pms_Id) {
              epdj.update(emppmsvo,pms_Id);
	}

	@Override
	public List<EmpPmsVO> selectByPrimaryKey(String empno) {
		List<EmpPmsVO> list=new ArrayList<EmpPmsVO>();
		list=epdj.selectByPrimaryKey(empno);
		return list;
	}

	@Override
	public List<EmpPmsVO> selectAll() {
		List<EmpPmsVO> list=epdj.selectAll();
		return list;
	}

	@Override
	public void delete(String empno, String pms_Id) {
		 epdj.delete(empno, pms_Id);
		
	}
	
	public void deleteAll(String empno){
		epdj.deleteAll(empno);
	}

}
