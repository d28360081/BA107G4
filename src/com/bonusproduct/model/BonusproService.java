package com.bonusproduct.model;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.bonusorderdetail.model.BonusProductOrderdetailDAO;
import com.bonusorderdetail.model.BonusProductOrderdetailVO;
import com.bonusorderdetail.model.BonusProductOrderdetail_interface;
import com.bonusproductorder.model.BonusProductOrderDAO;
import com.bonusproductorder.model.BonusProductOrderVO;
import com.bonusproductorder.model.BonusProductOrder_interface;

public class BonusproService {
	private BonusProduct_interface dao;
	private	BonusProductOrder_interface dao2;
	private BonusProductOrderdetail_interface dao3;
	public BonusproService(){
		dao = new BonusProductDAO();
		dao2 = new	BonusProductOrderDAO();
		dao3 = new BonusProductOrderdetailDAO();
	}
	
	public BonusProductVO addbns(String emp_no,String bns_it_name,String bns_it_intro,byte[] bns_in_pic,Integer bns_it_prc,Integer bns_it_strg,
			String bns_it_sts,Integer bns_it_cash){
		
		BonusProductVO bnsVO = new BonusProductVO();
		bnsVO.setEmp_no(emp_no);
		bnsVO.setBns_it_name(bns_it_name);
		bnsVO.setBns_it_intro(bns_it_intro);
		bnsVO.setBns_in_pic(bns_in_pic);
		bnsVO.setBns_it_prc(bns_it_prc);
		bnsVO.setBns_it_strg(bns_it_strg);
		bnsVO.setBns_it_sts(bns_it_sts);
		bnsVO.setBns_it_cash(bns_it_cash);
		dao.insert(bnsVO);
		return bnsVO;
	}
	
	public BonusProductVO updatebns(String emp_no,String bns_it_name,String bns_it_intro,byte[] bns_in_pic,Integer bns_it_prc,Integer bns_it_strg,
			String bns_it_sts,Integer bns_it_cash){
		BonusProductVO bnsVO = new BonusProductVO();
		bnsVO.setEmp_no(emp_no);
		bnsVO.setBns_it_name(bns_it_name);
		bnsVO.setBns_it_intro(bns_it_intro);
		bnsVO.setBns_it_prc(bns_it_prc);
		bnsVO.setBns_it_strg(bns_it_strg);
		bnsVO.setBns_it_sts(bns_it_sts);
		bnsVO.setBns_it_cash(bns_it_cash);
		dao.update(bnsVO);
		return bnsVO;
	}
	public BonusProductVO updatebnsSts(String bns_it_sts,String bns_it_id){
		BonusProductVO bnsVO = new BonusProductVO();
		bnsVO.setBns_it_sts(bns_it_sts);
		bnsVO.setBns_it_id(bns_it_id);
		dao.update(bnsVO);
		return bnsVO;
	}
	public BonusProductOrderVO updateOrderSts(String rec_dlv_sts,String bns_rec_id){
		BonusProductOrderVO bnsor = new BonusProductOrderVO();
		bnsor.setRec_dlv_sts(rec_dlv_sts);
		bnsor.setBns_rec_id(bns_rec_id);
		dao2.updateSts(bnsor);
		return bnsor;
	}
	public void deletebns(String bns_it_id){
		dao.delete(bns_it_id);
	}
	
	public BonusProductVO getOnebns(String bns_it_id){
		return dao.findByPrimaryKey(bns_it_id);
	}
	public List<BonusProductVO> getAll(){
		return dao.getAll();
	}
	public List<BonusProductOrderVO> getMemAllOrder(String mem_id){
		return dao2.findByMem_id(mem_id);
	}
	public List<BonusProductOrderVO> getAllbnsOrder(){
		return dao2.getAll();
	}
	public BonusProductOrderdetailVO findByRec_id(String bns_rec_id){
		return dao3.findByPrimaryKey(bns_rec_id);
	}
	public List<BonusProductOrderdetailVO> getAllDetail(){
		return dao3.getAll();
	}
	public BonusProductOrderVO addBnsOrder(String mem_id,Integer sum_prc,String rec_dlv_sts,String mem_add,String mem_ph,String mem_name){
		BonusProductOrderVO bnsorVO = new BonusProductOrderVO();
		List<BonusProductOrderdetailVO> list=null;
		list = dao3.getAll();
		bnsorVO.setMem_id(mem_id);
		bnsorVO.setSum_prc(sum_prc);
		bnsorVO.setRec_dlv_sts(rec_dlv_sts);
		bnsorVO.setMem_add(mem_add);
		bnsorVO.setMem_ph(mem_ph);
		bnsorVO.setMem_name(mem_name);
		
		dao2.insert(bnsorVO);
		
		return bnsorVO;
	}
	public BonusProductOrderVO addOrder(String mem_id,Integer sum_prc,String rec_dlv_sts,String mem_add,String mem_ph,String mem_name){
		BonusProductOrderVO bnsorVO = new BonusProductOrderVO();
		List<BonusProductOrderdetailVO> list=null;
		list = dao3.getAll();
		bnsorVO.setMem_id(mem_id);
		bnsorVO.setSum_prc(sum_prc);
		bnsorVO.setRec_dlv_sts(rec_dlv_sts);
		bnsorVO.setMem_add(mem_add);
		bnsorVO.setMem_ph(mem_ph);
		bnsorVO.setMem_name(mem_name);
		
		dao2.insert2(bnsorVO,list);
		
		return bnsorVO;
	}
	public BonusProductOrderVO addOrdeatail(String mem_id,Integer sum_prc,String rec_dlv_sts,String mem_add,String mem_ph,String mem_name,String BNS_IT_ID){
		BonusProductOrderVO bnsorVO = new BonusProductOrderVO();
		
		bnsorVO.setMem_id(mem_id);
		bnsorVO.setSum_prc(sum_prc);
		bnsorVO.setRec_dlv_sts(rec_dlv_sts);
		bnsorVO.setMem_add(mem_add);
		bnsorVO.setMem_ph(mem_ph);
		bnsorVO.setMem_name(mem_name);
		
		dao2.insert5(bnsorVO,BNS_IT_ID);
		
		return bnsorVO;
	}
	public BonusProductOrderdetailVO  addBnsOrderDetail(String bns_rec_id,String bns_it_id,Integer it_num){
		BonusProductOrderdetailVO bnsor = new BonusProductOrderdetailVO();
		bnsor.setBns_it_id(bns_rec_id);
		bnsor.setBns_it_id(bns_it_id);
		bnsor.setIt_num(1);
		dao3.insert(bnsor);
		return bnsor;
	}
	public void insertOrderwithDetail(BonusProductOrderVO bonusProductOrderVO, HashMap<String, BonusProductVO> BNSDetailMap){
		dao2.insert3(bonusProductOrderVO, BNSDetailMap);
	}
	public void addDetail(BonusProductOrderdetailVO bonusProductOrderdetailVO, Connection con){
		dao3.insert2(bonusProductOrderdetailVO, con);
	}
}
