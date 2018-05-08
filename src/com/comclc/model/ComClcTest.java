package com.comclc.model;

public class ComClcTest {
 public static void main(String args[]){
	 ComClcVO cc=new ComClcVO();
	 ComClcDAOJDBC cjb=new ComClcDAOJDBC();
	 
	 cc.setMem_id("M000004");
	 cc.setCom_id("COM007");
	 //代刚穝糤
	 cjb.insert(cc);
	 //代刚穝
	 cjb.update(cc.getMem_id(),cc.getCom_id(), "COM008");
	 //代刚琩高
	 cc=cjb.select(cc.getMem_id(), cc.getCom_id());
	 System.out.println("com_id:_"+cc.getCom_id()+"_mem_id:_"+cc.getMem_id());
	 //代刚埃
	 cjb.delete(cc.getMem_id(), cc.getCom_id());
	 
	
	
 }
}
