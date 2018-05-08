package com.bonusproductorder.model;

import java.util.List;

import com.bonusorderdetail.model.BonusProductOrderdetailDAO;
import com.bonusorderdetail.model.BonusProductOrderdetailVO;
import com.bonusorderdetail.model.BonusProductOrderdetail_interface;



public class BonusProductOrderTest {

	public static void main(String[] args) {
		BonusProductOrderDAO dao=new BonusProductOrderDAO();
//		BonusProductOrderdetail_interface dao3;
		//�s�W
//		dao3 = new BonusProductOrderdetailDAO();
		BonusProductOrderVO bonusProductOrderVO1=new BonusProductOrderVO();	
		bonusProductOrderVO1.setRec_dlv_sts("已收貨");
		bonusProductOrderVO1.setBns_rec_id("BPO006");
		dao.updateSts(bonusProductOrderVO1);
//		List<BonusProductOrderdetailVO> list=null;
//		list = dao3.getAll();
//		//bonusProductOrderVO1.setPur_date();
//		bonusProductOrderVO1.setMem_id("M000001");
//		bonusProductOrderVO1.setSum_prc( 5000);
//		bonusProductOrderVO1.setRec_dlv_sts("貨物處理中");
//		bonusProductOrderVO1.setMem_add("桃園市中壢區民族路六段198號");
//		bonusProductOrderVO1.setMem_ph("0223610270");
//		bonusProductOrderVO1.setMem_name("兒");
//		
//		dao.insert2(bonusProductOrderVO1, list);
		//�ק�
//		BonusProductOrderVO bonusProductOrderVO2=new BonusProductOrderVO();			
//		bonusProductOrderVO2.setSum_prc( 5000);
//		bonusProductOrderVO2.setRec_dlv_sts("貨物處理中");
//		bonusProductOrderVO2.setMem_add("桃園市中壢區民族路六段199號");
//		bonusProductOrderVO2.setMem_ph("0223610270");
//		bonusProductOrderVO2.setBns_rec_id("20180316-001");
//		
//		dao.update(bonusProductOrderVO2);
//		//�R��
//		//dao.delete("20180316-001");
//		//�d��
//		BonusProductOrderVO bonusProductOrderVO3=dao.findByPrimaryKey("BPO001");
//		System.out.println(bonusProductOrderVO3.getBns_rec_id());
//		System.out.println(bonusProductOrderVO3.getMem_id());
//		System.out.println(bonusProductOrderVO3.getPur_date());
//		System.out.println(bonusProductOrderVO3.getSum_prc());
//		System.out.println(bonusProductOrderVO3.getRec_dlv_sts());
//		System.out.println(bonusProductOrderVO3.getMem_add());
//		System.out.println(bonusProductOrderVO3.getMem_ph());
//		
//
//		System.out.println("---------------------------------------");
//		//�d��getAll
//		List<BonusProductOrderVO>list =dao.findByMem_id("M000002");
//		for(BonusProductOrderVO bonusProductOrderVO4:list){
//			System.out.println(bonusProductOrderVO4.getBns_rec_id());
//			System.out.println(bonusProductOrderVO4.getMem_id());
//			System.out.println(bonusProductOrderVO4.getPur_date());
//			System.out.println(bonusProductOrderVO4.getSum_prc());
//			System.out.println(bonusProductOrderVO4.getRec_dlv_sts());
//			System.out.println(bonusProductOrderVO4.getMem_add());
//			System.out.println(bonusProductOrderVO4.getMem_ph());
//		
//
//		}

	}

}
