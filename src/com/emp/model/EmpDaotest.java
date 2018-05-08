package com.emp.model;

import java.util.List;

public class EmpDaotest {
	public static void main(String args[]) {
		EmpVO empvo=new EmpVO();
		EmpDAOJDBC a=new EmpDAOJDBC();


//	新增測試
	empvo.setEmp_nm("王大陸");
	empvo.setEmp_psw("111");
	empvo.setEmp_sts("在職");
    empvo=a.insert(empvo);
		
		
//全選測試
		List<EmpVO> list=a.selectAll();
		for(EmpVO emp: list) {
			System.out.println(emp.getEmp_no());
		}
		
	

	
//選擇測試
		empvo=a.selectEmp(empvo.getEmp_no());
		System.out.println("員工姓名:_"+empvo.getEmp_nm());
		
		
//	刪除測試
		a.delete(empvo.getEmp_no());
	}
		
}
	
