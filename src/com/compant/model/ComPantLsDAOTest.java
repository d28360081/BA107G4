package com.compant.model;

import java.util.ArrayList;
import java.util.List;

public class ComPantLsDAOTest {
   public static void main(String args[]) {
	   ComPantLsVO cplv=new ComPantLsVO();
	   ComPantLsDaoJDBC cpld=new ComPantLsDaoJDBC();
	   cplv.setMem_id("M000001");
	   cplv.setCom_id("COM001");
	   cplv.setCom_it_num(0);
	   List<ComPantLsVO> list=cpld.selectToVote("COM018");
	   System.out.println(list==null);
	   System.out.println(list.size());
	   //���խק��k
//	   AA.insert(a);
	   //���խק��k
//	   cplv.setCom_it_num(99);
//	   cpld.update(cplv);
	   //���լd��
//	   cplv=cpld.select(cplv.getMem_id(), cplv.getCom_id());
//	   System.out.println(cplv.getCom_it_num());
//	   //���էR����k
//	   cpld.delete(cplv.getMem_id(), cplv.getCom_id());
	   

	   
   }
}
