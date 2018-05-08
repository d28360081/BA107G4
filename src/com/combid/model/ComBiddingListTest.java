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
       
       //代刚穝糤よ猭
        //jb.insert(cb);
       //代刚琩高
       cb=jb.select(cb.getMem_id(),cb.getCom_id());
       System.out.println("mem_id:_"+cb.getMem_id()+"_com_id:_"+cb.getCom_id());
       //代刚э
       cb.setN_o_v(99);
       jb.update(cb);
       //代刚埃
       
	   jb.delete(cb.getMem_id(), cb.getCom_id());


   }
}
