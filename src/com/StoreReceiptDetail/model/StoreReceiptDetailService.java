package com.StoreReceiptDetail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.StoreOrder.model.StoreOrderVO;

public class StoreReceiptDetailService {
	StoreReceiptDetailDAO storeReceiptDetailDAO=new StoreReceiptDetailDAO();
	public void insertStoreReceiptDetail(StoreReceiptDetailVO storeReceiptDetailVO) {
		storeReceiptDetailDAO.insertStoreReceiptDetail(storeReceiptDetailVO);
	}
	public void insertStoreReceiptDetail2(StoreReceiptDetailVO storeReceiptDetailVO,java.sql.Connection con) {
		storeReceiptDetailDAO.insertStoreReceiptDetail2(storeReceiptDetailVO,con);
	}
	public void updateStoreReceiptDetail(StoreReceiptDetailVO storeReceiptDetailVO) {
		storeReceiptDetailDAO.updateStoreReceiptDetail(storeReceiptDetailVO);
		
	}
	
	public void deleteStoreReceiptDetail(String rec_id) {
		storeReceiptDetailDAO.deleteStoreReceiptDetail(rec_id);
	}
	public StoreReceiptDetailVO StoreReceiptDetailfindByPrimaryKey(String rec_id) {
		return storeReceiptDetailDAO.StoreReceiptDetailfindByPrimaryKey(rec_id);
	}
	  public List<StoreReceiptDetailVO> StoreReceiptDetailgetAll(){
		  return (List<StoreReceiptDetailVO>)storeReceiptDetailDAO.StoreReceiptDetailgetAll();
	  }
	  public List<StoreReceiptDetailVO>  StoreReceiptDetailgetAllBy_rec_id(String rec_id){
		  return (List<StoreReceiptDetailVO>)storeReceiptDetailDAO.StoreReceiptDetailgetAllBy_rec_id(rec_id);
	  }
	  public Set<StoreOrderVO> getStoreOrdersBy_rec_id(String rec_id){
		  return (Set<StoreOrderVO>) storeReceiptDetailDAO.getStoreOrdersBy_rec_id(rec_id);
	  }
	 
	
	}
