package com.comapl.model;

public class ComAplDaoTest {
public static void main(String args[]){
	ComAplVO cav=new ComAplVO();
	ComAplDaoJdbc cadj=new ComAplDaoJdbc();
	
	cav.setMem_id("M000001");
	cav.setEmp_no("EMP001");
	cav.setCom_id("COM001");
	
	//代刚穝糤よ猭
	cav=cadj.insert(cav);
	//代刚琩高よ猭
	cav=cadj.select(cav.getCom_apl_id());
	System.out.println(cav.getCom_apl_id());
	//代刚эよ猭
	cav.setApl_cnt("穝糤代刚");
	cadj.update(cav);
	//代刚埃
	cadj.delete(cav.getCom_apl_id());
	

	
}
}
