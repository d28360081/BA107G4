package com.bonusorderdetail.model;

import java.util.List;

public class BonusProductOrderdetaiTest {

	public static void main(String[] args) {
		BonusProductOrderdetailDAO dao=new BonusProductOrderdetailDAO();
		BonusProductOrderdetailVO bonusProductOrderdetailVO1=new BonusProductOrderdetailVO();
		//�s�W	
//		bonusProductOrderdetailVO1.setBns_rec_id("BPO001");
//		bonusProductOrderdetailVO1.setBns_it_id("BP003");
//		bonusProductOrderdetailVO1.setIt_num(5);
//		dao.insert(bonusProductOrderdetailVO1);
		
		//�ק�
//		BonusProductOrderdetailVO bonusProductOrderdetailVO2=new BonusProductOrderdetailVO();
//		dao.delete("BPO001", "BP003");
		
//		BonusProductOrderdetailVO bonusProductOrderdetailVO3=dao.findByPrimaryKey("BPO001");
//		System.out.print(bonusProductOrderdetailVO3.getBns_rec_id()+" , ");
//		System.out.print(bonusProductOrderdetailVO3.getBns_it_id()+" , ");
//		System.out.print(bonusProductOrderdetailVO3.getIt_num());
		List<BonusProductOrderdetailVO>list=dao.getAll();
		for(BonusProductOrderdetailVO bonusProductOrderdetailVO4 :list){
			System.out.print(bonusProductOrderdetailVO4.getBns_rec_id()+" , ");
			System.out.print(bonusProductOrderdetailVO4.getBns_it_id()+" , ");
			System.out.println(bonusProductOrderdetailVO4.getIt_num());
			System.out.println("========================================");
		}
	}

}
