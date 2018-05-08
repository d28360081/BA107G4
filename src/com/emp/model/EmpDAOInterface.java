package com.emp.model;

import java.util.List;

public interface EmpDAOInterface {
	public EmpVO insert(EmpVO empvo);
	public void update(EmpVO empvo);
	public void delete(String empno);
	public boolean selectByPrimaryKey(String empno,String emp_psw);
	public List<EmpVO> selectAll();
    public EmpVO selectEmp(String emp_no);
}
