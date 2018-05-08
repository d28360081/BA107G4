package com.PersonalStore.model;

import java.util.List;

public class PersonalStoreTest {

	public static void main(String[] args) {
		PersonalStoreDAO dao=new PersonalStoreDAO();
		//�s�W
		PersonalStoreVO personalStoreVO1=new PersonalStoreVO();		
		personalStoreVO1.setMem_id("M000001");
		personalStoreVO1.setSt_name("測試用");
		personalStoreVO1.setSt_intro("測試用");
		personalStoreVO1.setSt_sts("上架");
		dao.insert(personalStoreVO1);
		
//		PersonalStoreVO personalStoreVO2=new PersonalStoreVO();	
//		personalStoreVO2.setSt_name("測試用");
//		personalStoreVO2.setSt_intro("測試用2");
//		personalStoreVO2.setSt_sts("上架");
//		personalStoreVO2.setSt_id("ST001");
//		dao.update(personalStoreVO2);
		
		//dao.delete("ST002");
		
		PersonalStoreVO personalStoreVO3=dao.findByPrimaryKey("ST001");
		System.out.println(personalStoreVO3.getSt_id());
		System.out.println(personalStoreVO3.getMem_id());		
		System.out.println(personalStoreVO3.getSt_intro());
		System.out.println(personalStoreVO3.getSt_name());
		System.out.println(personalStoreVO3.getSt_sts());
		System.out.println("---------------------------------------");
	
		List<PersonalStoreVO>list =dao.getAll();
		for(PersonalStoreVO personalStoreVO4:list){
			System.out.println(personalStoreVO4.getSt_id());
			System.out.println(personalStoreVO4.getMem_id());			
			System.out.println(personalStoreVO4.getSt_intro());
			System.out.println(personalStoreVO4.getSt_name());
			System.out.println(personalStoreVO4.getSt_sts());
		}

	}

}
