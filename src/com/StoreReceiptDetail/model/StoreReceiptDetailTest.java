package com.StoreReceiptDetail.model;

import java.util.List;
import java.sql.Connection;

public class StoreReceiptDetailTest {

	public static void main(String[] args) {
		Connection con=null;
		StoreReceiptDetailDAO dao=new StoreReceiptDetailDAO();
		StoreReceiptDetailVO storeReceiptDetailVO1=new StoreReceiptDetailVO();
		
		storeReceiptDetailVO1.setRec_id("REC001");
		storeReceiptDetailVO1.setIt_id("IT001");
		storeReceiptDetailVO1.setIt_num(20);		
		
		dao.insertStoreReceiptDetail2(storeReceiptDetailVO1,con);
		
		StoreReceiptDetailVO storeReceiptDetailVO2=new StoreReceiptDetailVO();
		storeReceiptDetailVO2.setRec_id("REC001");
		storeReceiptDetailVO2.setIt_id("IT001");
		
		dao.updateStoreReceiptDetail(storeReceiptDetailVO2);		
		
		//dao.delete("REC001");
		StoreReceiptDetailVO storeReceiptDetailVO3=dao.StoreReceiptDetailfindByPrimaryKey("REC001");
		System.out.println(storeReceiptDetailVO3.getRec_id());
		System.out.println(storeReceiptDetailVO3.getIt_id());
		System.out.println(storeReceiptDetailVO3.getIt_num());
		
		
		List<StoreReceiptDetailVO>list=dao.StoreReceiptDetailgetAll();
		for(StoreReceiptDetailVO storeReceiptDetailVO4:list){
			System.out.println(storeReceiptDetailVO4.getRec_id());
			System.out.println(storeReceiptDetailVO4.getIt_id());
			System.out.println(storeReceiptDetailVO4.getIt_num());		
		}
		
	
		
	}


}


