package android.com.storeReceiptDetail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import android.com.storeorder.model.StoreOrderVO;



public interface StoreReceiptDetail_interface {
	public void insertStoreReceiptDetail(StoreReceiptDetailVO storeReceiptDetailVO);
	public void insertStoreReceiptDetail2(StoreReceiptDetailVO storeReceiptDetailVO,Connection con);
  

   
    
}
