package com.StoreOrder.model;

import java.util.HashMap;
import java.util.List;

import com.Product.model.ProductVO;
import com.StoreOrder.model.StoreOrderVO;
import com.StoreReceiptDetail.model.StoreReceiptDetailVO;

public interface StoreOrder_interface {
	public void insertStoreOrderWithStoreReceiptDetail(StoreOrderVO storeordervp,HashMap<String,ProductVO> StoreReceiptDetailMap);
	public void updateStoreOrder(StoreOrderVO storeOrderVO);
    public StoreOrderVO StoreOrderfindByST_id(String st_id);
    public void deleteStoreOrder(String rec_id);
    public StoreOrderVO StoreOrderfindByPrimaryKey(String rec_id);
    public List<StoreOrderVO> StoreOrdergetAll();
    public List<StoreOrderVO> StoreOrdergetAll_By_St_id(String st_id);
    public void updateStoreOrder_sts(StoreOrderVO storeOrderVO);
    
    
	
}
