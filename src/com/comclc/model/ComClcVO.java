package com.comclc.model;

import java.io.Serializable;

public class ComClcVO implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -3056607510784463347L;
private String mem_id;
private String  com_id;
public String getMem_id() {
	return mem_id;
}
public void setMem_id(String mem_id) {
	this.mem_id = mem_id;
}
public String getCom_id() {
	return com_id;
}
public void setCom_id(String com_id) {
	this.com_id = com_id;
}


}
