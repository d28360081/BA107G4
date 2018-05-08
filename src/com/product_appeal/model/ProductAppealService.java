package com.product_appeal.model;

import java.util.List;

public class ProductAppealService {
 private ProductAppealDAO_interface dao;
 public ProductAppealService(){
	 dao = new ProductAppealDAO();
 }
 public ProductAppealVO add(String mem_id,String it_id,String it_apl_cnt){
	 ProductAppealVO paVO = new ProductAppealVO();
	 paVO.setMem_id(mem_id);
	 paVO.setIt_id(it_id);
	 paVO.setIt_apl_cnt(it_apl_cnt);
	 dao.add(paVO);
	 return paVO;
 }
 
 public ProductAppealVO update(String emp_no,String apl_sts,String it_rapl,String it_apl_id){
	 ProductAppealVO paVO = new ProductAppealVO();
	 paVO.setEmp_no(emp_no);
	 paVO.setApl_sts(apl_sts);
	 paVO.setIt_rapl(it_rapl);
	 paVO.setIt_apl_id(it_apl_id);
	 dao.update(paVO);
	 return paVO;
 }
 public ProductAppealVO findByPk(String it_apl_id){
	 return dao.findByPK(it_apl_id);
 }
 public List<ProductAppealVO>getAll(){
	 return dao.getAll();
 }
 public List<ProductAppealVO>getSelected(String apl_sts){
	 return dao.getSelected(apl_sts);
 }
}
