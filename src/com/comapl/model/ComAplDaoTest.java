package com.comapl.model;

public class ComAplDaoTest {
public static void main(String args[]){
	ComAplVO cav=new ComAplVO();
	ComAplDaoJdbc cadj=new ComAplDaoJdbc();
	
	cav.setMem_id("M000001");
	cav.setEmp_no("EMP001");
	cav.setCom_id("COM001");
	
	//���շs�W��k
	cav=cadj.insert(cav);
	//���լd�ߤ�k
	cav=cadj.select(cav.getCom_apl_id());
	System.out.println(cav.getCom_apl_id());
	//���խק��k
	cav.setApl_cnt("�s�W����");
	cadj.update(cav);
	//���էR��
	cadj.delete(cav.getCom_apl_id());
	

	
}
}
