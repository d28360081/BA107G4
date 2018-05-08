package com.emp.model;

import java.util.List;

public class EmpService {
	private EmpDAOJNDI empdao;
	
 public EmpService(){
	 empdao=new EmpDAOJNDI();
 }
 
 public EmpVO insert(EmpVO empvo){
 return empdao.insert(empvo);
 }
	public void update(EmpVO empvo){
		empdao.update(empvo);
	}
	public void delete(String empno){
		empdao.delete(empno);
	}
	public boolean selectByPrimaryKey(String empno,String emp_psw){
		return empdao.selectByPrimaryKey(empno,emp_psw);
	}
	public List<EmpVO> selectAll(){
		return empdao.selectAll();
	}
	public EmpVO selectEmp(String emp_no,String Emp_psw){
		return empdao.selectEmp(emp_no, Emp_psw);
	}
	public EmpVO selectEmp(String emp_no){
		return empdao.selectEmp(emp_no);
	}
}
