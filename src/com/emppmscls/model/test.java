package com.emppmscls.model;

public class test {
	public static void main(String args[]){
		EmpPmsClsVO epcv=new EmpPmsClsVO();
		EmpPmsClsDAOJDBC epcdj=new EmpPmsClsDAOJDBC();
		
		epcv.setPmsintro("����4");
		epcv=epcdj.insert(epcv);
		System.out.println(epcv.getPmsid());
		
		epcv=epcdj.select(epcv.getPmsid());
		System.out.println(epcv.getPmsintro());
		
		epcv.setPmsintro("���է���");
		epcdj.update(epcv);
		
		epcdj.delete(epcv.getPmsid());
		
		
		
	}

}
