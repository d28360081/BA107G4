package com.StoreReceiptDetail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.StoreOrder.model.StoreOrderVO;
import com.StoreReceiptDetail.model.StoreReceiptDetailVO;


public interface StoreReceiptDetail_interface {
	public void insertStoreReceiptDetail(StoreReceiptDetailVO storeReceiptDetailVO);
	public void insertStoreReceiptDetail2(StoreReceiptDetailVO storeReceiptDetailVO,Connection conn);
    public void updateStoreReceiptDetail(StoreReceiptDetailVO storeReceiptDetailVO);
    public void deleteStoreReceiptDetail(String rec_id);
    public StoreReceiptDetailVO StoreReceiptDetailfindByPrimaryKey(String rec_id);
    public List<StoreReceiptDetailVO> StoreReceiptDetailgetAll();
    public List<StoreReceiptDetailVO> StoreReceiptDetailgetAllBy_rec_id(String rec_id);
    public Set<StoreOrderVO> getStoreOrdersBy_rec_id(String rec_id);
    
}
