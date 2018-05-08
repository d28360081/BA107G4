package com.emppms.model;

public class Test {
 public static void main(String args[]){
	 EmpPmsVO eps=new EmpPmsVO();
	 EmpPmsDAOJDBC epdj=new EmpPmsDAOJDBC();
	 eps.setEmpno("EMP003");
	 eps.setPmsid("PMS005");
	 epdj.insert(eps);
	 
	 eps.setPmsid("PMS004");
	 epdj.update(eps, "PMS006");
	 

	 
	 epdj.delete(eps.getEmpno(),eps.getPmsid());
 }
}
