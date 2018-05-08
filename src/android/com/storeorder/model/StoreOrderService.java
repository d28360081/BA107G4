package android.com.storeorder.model;

import java.util.List;

import android.com.storeReceiptDetail.model.StoreReceiptDetailVO;



public class StoreOrderService {
	private StoreOrderDAO dao = new StoreOrderDAO();
	
	public void insertStoreOrderWithStoreReceiptDetail(StoreOrderVO storeOrderVO, List<StoreReceiptDetailVO> list) {
		dao.insertStoreOrderWithStoreReceiptDetail(storeOrderVO,list);
	}
	
	
}
