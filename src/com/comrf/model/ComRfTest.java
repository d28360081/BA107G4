package com.comrf.model;

public class ComRfTest {
 public static void main(String args[]) {
		ComRfVO crv=new ComRfVO();
		ComRfDAOJDBC crdj=new ComRfDAOJDBC();
		crv.setCom_rf_id("COMT_APL001");
		crv.setCom_id("COM001");
		crv.setMem_id("M000002");
		crv.setEmp_no("EMP001");
		crv.setRf_cnt("代刚");
		crv.setIt_num(10);
		crv.setIt_sum(10);
		
		//代刚穝糤よ猭
		crv=crdj.insert(crv);
		//代刚穝よ猭
		crv.setRf_cnt("代刚2");
		crv.setEmp_no("EMP002");
		crdj.update(crv);
	
		//琩高よ猭
		crv=crdj.selectByPrimaryKey(crv.getCom_rf_id());
		System.out.println(crv.getCom_rf_id());
		//代刚埃よ猭
		crdj.delete(crv.getCom_rf_id());
	
		
		
 }
}
