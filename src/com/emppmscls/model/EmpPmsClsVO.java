package com.emppmscls.model;

import java.io.Serializable;

public class EmpPmsClsVO implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 4938331812228865495L;
private String pmsid;
 private String pmsintro;
 public String getPmsid() {
	return pmsid;
}
public void setPmsid(String pmsid) {
	this.pmsid = pmsid;
}
public String getPmsintro() {
	return pmsintro;
}
public void setPmsintro(String pmsintro) {
	this.pmsintro = pmsintro;
}

}
