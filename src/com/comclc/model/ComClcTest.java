package com.comclc.model;

public class ComClcTest {
 public static void main(String args[]){
	 ComClcVO cc=new ComClcVO();
	 ComClcDAOJDBC cjb=new ComClcDAOJDBC();
	 
	 cc.setMem_id("M000004");
	 cc.setCom_id("COM007");
	 //���շs�W
	 cjb.insert(cc);
	 //���է�s
	 cjb.update(cc.getMem_id(),cc.getCom_id(), "COM008");
	 //���լd��
	 cc=cjb.select(cc.getMem_id(), cc.getCom_id());
	 System.out.println("com_id:_"+cc.getCom_id()+"_mem_id:_"+cc.getMem_id());
	 //���էR��
	 cjb.delete(cc.getMem_id(), cc.getCom_id());
	 
	
	
 }
}
