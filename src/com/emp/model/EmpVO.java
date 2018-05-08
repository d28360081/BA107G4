package com.emp.model;

import java.io.Serializable;
import java.sql.Blob;

public class EmpVO implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1225221318915391733L;
private String emp_no;
 private String emp_nm;
 private byte[] emp_pic;
 private String emp_psw;
 private String emp_sts;
public String getEmp_no() {
	return emp_no;
}
public void setEmp_no(String emp_no) {
	this.emp_no = emp_no;
}
public String getEmp_nm() {
	return emp_nm;
}
public void setEmp_nm(String emp_nm) {
	this.emp_nm = emp_nm;
}
public byte[] getEmp_pic() {
	return emp_pic;
}
public void setEmp_pic(byte[] emp_pic) {
	this.emp_pic = emp_pic;
}
public String getEmp_psw() {
	return emp_psw;
}
public void setEmp_psw(String emp_psw) {
	this.emp_psw = emp_psw;
}
public String getEmp_sts() {
	return emp_sts;
}
public void setEmp_sts(String emp_sts) {
	this.emp_sts = emp_sts;
}


}
