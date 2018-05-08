package com.commision_appeal.model;

import java.util.List;

public class CommisionAppealService {

	private CommisionAppealDAO_interface dao;
	
	public CommisionAppealService(){
		dao =  new CommisionAppealDAO();
	}
	
	public CommisionAppealVO add(String mem_id,String com_id,String apl_cnt){
		
		CommisionAppealVO caVO = new CommisionAppealVO();
		caVO.setMem_id(mem_id);
		caVO.setCom_id(com_id);
		caVO.setApl_cnt(apl_cnt);
		dao.add(caVO);
		return caVO;
		
	}
public CommisionAppealVO findByPk(String com_apl_id){
		
		CommisionAppealVO caVO = new CommisionAppealVO();
		caVO.setCom_apl_id(com_apl_id);
		caVO = dao.findByPK(com_apl_id);
		return caVO;
	}
public CommisionAppealVO update(String emp_no,String apl_sts,String com_rapl,String com_apl_id){
	
	CommisionAppealVO caVO = new CommisionAppealVO();
	caVO.setEmp_no(emp_no);
	caVO.setApl_sts(apl_sts);
	caVO.setCom_rapl(com_rapl);
	caVO.setCom_apl_id(com_apl_id);
	dao.update(caVO);
	return caVO;	
}
public List<CommisionAppealVO>getAll(){
	return dao.getAll();
}

public List<CommisionAppealVO>getSelected(String apl_sts){
	return dao.getSelected(apl_sts);
}
	
	
	
}
