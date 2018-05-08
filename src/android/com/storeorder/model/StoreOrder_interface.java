package android.com.storeorder.model;

import java.util.List;

import android.com.storeReceiptDetail.model.StoreReceiptDetailVO;



public interface StoreOrder_interface {
	public void insertStoreOrderWithStoreReceiptDetail(StoreOrderVO storeOrderVO,List<StoreReceiptDetailVO>list);

}
