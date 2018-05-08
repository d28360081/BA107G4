package com.StoreOrder.model;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.Product.model.ProductVO;
import com.StoreOrder.model.StoreOrderDAO;
import com.StoreReceiptDetail.model.StoreReceiptDetailVO;

public class StoreOrderService implements StoreOrder_interface{
	private StoreOrderDAO dao = new StoreOrderDAO();
	
	public void updateStoreOrder2(StoreOrderVO storeOrderVO,Connection con){
		dao.updateStoreOrder2(storeOrderVO, con);
	}
	public void updateStoreOrder_sts(StoreOrderVO storeOrderVO){
		dao.updateStoreOrder_sts(storeOrderVO);
	}

	
	public void updateStoreOrder(StoreOrderVO storeOrderVO, Connection con) {
		dao.updateStoreOrder(storeOrderVO);
	}
	public void deleteStoreOrder(String rec_id) {
		dao.deleteStoreOrder(rec_id);
	}
	public StoreOrderVO StoreOrderfindByPrimaryKey(String rec_id) {
		return dao.StoreOrderfindByPrimaryKey(rec_id);
	}
	public List<StoreOrderVO> StoreOrdergetAll() {
		return  (List<StoreOrderVO>)dao.StoreOrdergetAll();
	}
	public List<StoreOrderVO> StoreOrdergetAllBy_mem_id(String mem_id) {
		return  (List<StoreOrderVO>)dao.StoreOrdergetAllBy_mem_id(mem_id);
	}
	
	
	
	public List<StoreOrderVO> StoreOrdergetAll_ST001() {
		return  (List<StoreOrderVO>)dao.StoreOrdergetAll_ST001();
	}
	public List<StoreOrderVO> StoreOrdergetAllNotST001() {
		return  (List<StoreOrderVO>)dao.StoreOrdergetAllNotST001();
	}
	public StoreOrderVO StoreOrderfindByST_id(String st_id) {
		return dao.StoreOrderfindByST_id(st_id);
	}
	@Override
	public void insertStoreOrderWithStoreReceiptDetail(StoreOrderVO storeordervp,HashMap<String,ProductVO> StoreReceiptDetaillist){
		dao.insertStoreOrderWithStoreReceiptDetail(storeordervp, StoreReceiptDetaillist);
		
	}
	@Override
	public void updateStoreOrder(StoreOrderVO storeOrderVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StoreOrderVO> StoreOrdergetAll_By_St_id(String st_id) {
		return  (List<StoreOrderVO>)dao.StoreOrdergetAll_By_St_id(st_id);
	}
	
	
	
	
}
