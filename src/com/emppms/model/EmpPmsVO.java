package com.emppms.model;

import java.io.Serializable;

public class EmpPmsVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4460763891449308745L;
	private String empno;
	  private String pmsid;
  public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getPmsid() {
		return pmsid;
	}
	public void setPmsid(String pmsid) {
		this.pmsid = pmsid;
	}

}
