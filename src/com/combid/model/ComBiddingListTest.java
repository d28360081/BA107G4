package com.combid.model;

public class ComBiddingListTest {
   public static void main(String args[]){
	   ComBidVO cb=new ComBidVO();
	   ComBidDAOJdbc jb=new ComBidDAOJdbc();
	   cb.setMem_id("M000005");
	   cb.setCom_id("COM005");
	   cb.setAt_sts("");
       cb.setAuc_del_prc(10.0);
       cb.setAuc_prc(100.00);
       cb.setN_o_v(0);
       
       //���շs�W��k
        //jb.insert(cb);
       //���լd��
       cb=jb.select(cb.getMem_id(),cb.getCom_id());
       System.out.println("mem_id:_"+cb.getMem_id()+"_com_id:_"+cb.getCom_id());
       //���խק�
       cb.setN_o_v(99);
       jb.update(cb);
       //���էR��
       
	   jb.delete(cb.getMem_id(), cb.getCom_id());


   }
}
