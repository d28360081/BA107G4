package android.com.storeReceiptDetail.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import android.com.storeorder.model.StoreOrderVO;





public class StoreReceiptDetailService {
	StoreReceiptDetailDAO storeReceiptDetailDAO=new StoreReceiptDetailDAO();
	public void insertStoreReceiptDetail(StoreReceiptDetailVO storeReceiptDetailVO) {
		storeReceiptDetailDAO.insertStoreReceiptDetail(storeReceiptDetailVO);
	}
	public void insertStoreReceiptDetail2(StoreReceiptDetailVO storeReceiptDetailVO,java.sql.Connection con) {
		storeReceiptDetailDAO.insertStoreReceiptDetail2(storeReceiptDetailVO,con);
	}
	
	
	}
