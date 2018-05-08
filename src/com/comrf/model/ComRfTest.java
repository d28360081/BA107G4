package com.comrf.model;

public class ComRfTest {
 public static void main(String args[]) {
		ComRfVO crv=new ComRfVO();
		ComRfDAOJDBC crdj=new ComRfDAOJDBC();
		crv.setCom_rf_id("COMT_APL001");
		crv.setCom_id("COM001");
		crv.setMem_id("M000002");
		crv.setEmp_no("EMP001");
		crv.setRf_cnt("����");
		crv.setIt_num(10);
		crv.setIt_sum(10);
		
		//���շs�W��k
		crv=crdj.insert(crv);
		//���է�s��k
		crv.setRf_cnt("����2");
		crv.setEmp_no("EMP002");
		crdj.update(crv);
	
		//�d�ߤ�k
		crv=crdj.selectByPrimaryKey(crv.getCom_rf_id());
		System.out.println(crv.getCom_rf_id());
		//���էR����k
		crdj.delete(crv.getCom_rf_id());
	
		
		
 }
}
