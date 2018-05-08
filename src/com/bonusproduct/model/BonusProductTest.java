package com.bonusproduct.model;

import java.util.List;

public class BonusProductTest {

	public static void main(String[] args) {
		BonusProductDAO dao=new BonusProductDAO();
		//�s�W
//		BonusProductVO bonusProductVO1=new BonusProductVO();
//		bonusProductVO1.setEmp_no("EMP001");
//		bonusProductVO1.setBns_it_name("����������");
//		bonusProductVO1.setBns_it_intro("�L�h�_�����u�T�S�̡v�O�ï]�B���B�B�P��A��ӥ����אּ�u�������v�A�u���ï]�򥬤B�A���椣�ܦ��]���|���A�]���Ưu�������h���ܹ�b�A�Ӥ]�����ͳz�S���n������٦������T�S�̡A�ثe���G�u���_���S���C");
//		bonusProductVO1.setBns_in_pic(null);
//		bonusProductVO1.setBns_it_prc((double) 50);
//		bonusProductVO1.setBns_it_strg(99);
//		bonusProductVO1.setBns_it_sts("�W�[");
//		bonusProductVO1.setBns_it_cash(80);
//		dao.insert(bonusProductVO1);		
//		//�ק�
//		BonusProductVO bonusProductVO2=new BonusProductVO();		
//		bonusProductVO2.setBns_it_name("����������");
//		bonusProductVO2.setBns_it_intro("�L�h�_�����u�T�S�̡v�O�ï]�B���B�B�P��A��ӥ����אּ�u�������v�A�u���ï]�򥬤B�A���椣�ܦ��]���|���A�]���Ưu�������h���ܹ�b�A�Ӥ]�����ͳz�S���n������٦������T�S�̡A�ثe���G�u���_���S���C");
//		bonusProductVO2.setBns_in_pic(null);
//		bonusProductVO2.setBns_it_prc((double) 20);
//		bonusProductVO2.setBns_it_strg(99);
//		bonusProductVO2.setBns_it_sts("�U�[");
//		bonusProductVO2.setBns_it_cash(20);
//		bonusProductVO2.setBns_it_id("BP001");
//		
//		dao.update(bonusProductVO2);
//		//�R��
//		//dao.delete("BP002");
//		
//		BonusProductVO bonusProductVO3=dao.findByPrimaryKey("BP001");
//		System.out.println(bonusProductVO3.getBns_it_id());
//		System.out.println(bonusProductVO3.getEmp_no());
//		System.out.println(bonusProductVO3.getBns_it_name());
//		System.out.println(bonusProductVO3.getBns_it_intro());
//		System.out.println(bonusProductVO3.getBns_in_pic());
//		System.out.println(bonusProductVO3.getBns_it_prc());
//		System.out.println(bonusProductVO3.getBns_it_strg());
//		System.out.println(bonusProductVO3.getBns_it_sts());
		
		
		
		List<BonusProductVO> list=dao.getAll();
		for(BonusProductVO bonusProductVO4:list){
			System.out.println(bonusProductVO4.getBns_it_id());
			System.out.println(bonusProductVO4.getEmp_no());
			System.out.println(bonusProductVO4.getBns_it_name());
			System.out.println(bonusProductVO4.getBns_it_intro());
			System.out.println(bonusProductVO4.getBns_in_pic());
			System.out.println(bonusProductVO4.getBns_it_prc());
			System.out.println(bonusProductVO4.getBns_it_strg());
			System.out.println(bonusProductVO4.getBns_it_sts());
		}
		
	}

}
