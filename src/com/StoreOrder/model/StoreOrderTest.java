package com.StoreOrder.model;

import java.util.List;

public class StoreOrderTest {

	public static void main(String[] args) {		
	
		StoreOrderDAO dao=new StoreOrderDAO();
		StoreOrderVO storeOrderVO1=new StoreOrderVO();
		//�s�W			
		
//		storeOrderVO1.setMem_id("M000001");
//		storeOrderVO1.setSt_id("ST001");		
//		storeOrderVO1.setBuyer_name("張西西");
//		storeOrderVO1.setMem_email("23423rds@gmail.com");
//		storeOrderVO1.setRec_delivery("空運");
//		storeOrderVO1.setPayment((double) 200);
//		storeOrderVO1.setRec_py("信用卡付款");	
//		storeOrderVO1.setRec_dlv_sts("運送中");
//		storeOrderVO1.setMem_add("苗栗頭份");
//		storeOrderVO1.setMem_ph("0987654321");
//		storeOrderVO1.setUsd_bns(1);
//		storeOrderVO1.setDlv_id("2");
//		dao.insertStoreOrder(storeOrderVO1);			
		//�ק�
//		StoreOrderVO storeOrderVO2=new StoreOrderVO();		
//		storeOrderVO2.setPayment((double) 5000);
//		storeOrderVO2.setRec_py("�״���b");		
//		storeOrderVO2.setRec_dlv_sts("�w�e�F");
//		storeOrderVO2.setMem_add("��鿤�����871��");
//		storeOrderVO2.setMem_ph("0987654321");
//		storeOrderVO2.setUsd_bns(2000);
//		storeOrderVO2.setDlv_id("2");
//		storeOrderVO2.setRec_id("REC001");
//		dao.update(storeOrderVO2);	
		//�R��
		//dao.delete("REC002");
		
		StoreOrderVO storeOrderVO3=dao.StoreOrderfindByPrimaryKey("REC001");
		System.out.println(storeOrderVO3.getRec_id());		
		System.out.println(storeOrderVO3.getMem_id());
		System.out.println(storeOrderVO3.getSt_id());
		System.out.println(storeOrderVO3.getBuyer_name());
		System.out.println(storeOrderVO3.getMem_email());
		System.out.println(storeOrderVO3.getRec_delivery());
		System.out.println(storeOrderVO3.getPayment());
		System.out.println(storeOrderVO3.getRec_py());
		System.out.println(storeOrderVO3.getRec_date());
		System.out.println(storeOrderVO3.getRec_dlv_sts());
		System.out.println(storeOrderVO3.getMem_add());
		System.out.println(storeOrderVO3.getMem_ph());
		System.out.println(storeOrderVO3.getUsd_bns());
		System.out.println(storeOrderVO3.getDlv_id());
		
//		List<StoreOrderVO> list =dao.StoreOrdergetAll();
//		for(StoreOrderVO storeOrderVO4:list){
//			System.out.println(storeOrderVO4.getRec_id());		
//			System.out.println(storeOrderVO4.getMem_id());
//			System.out.println(storeOrderVO4.getSt_id());
//			System.out.println(storeOrderVO3.getBuyer_name());
//			System.out.println(storeOrderVO3.getMem_email());
//			System.out.println(storeOrderVO3.getRec_delivery());
//			System.out.println(storeOrderVO4.getPayment());
//			System.out.println(storeOrderVO4.getRec_py());
//			System.out.println(storeOrderVO4.getRec_date());
//			System.out.println(storeOrderVO4.getRec_dlv_sts());
//			System.out.println(storeOrderVO4.getMem_add());
//			System.out.println(storeOrderVO4.getMem_ph());
//			System.out.println(storeOrderVO4.getUsd_bns());
//			System.out.println(storeOrderVO4.getDlv_id());
//		}
		
	
	  
		
	}

}
