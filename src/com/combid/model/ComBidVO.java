package com.combid.model;

import java.io.Serializable;

public class ComBidVO implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -2191168175645025273L;
private String mem_id;
private String com_id;
private Double auc_prc;
private Double auc_del_prc;
private Integer n_o_v;
private String at_sts;
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
public Double getAuc_prc() {
	return auc_prc;
}
public void setAuc_prc(Double aut_prc) {
	this.auc_prc = aut_prc;
}
public Double getAuc_del_prc() {
	return auc_del_prc;
}
public void setAuc_del_prc(Double auc_del_prc) {
	this.auc_del_prc = auc_del_prc;
}
public Integer getN_o_v() {
	return n_o_v;
}
public void setN_o_v(Integer n_o_v) {
	this.n_o_v = n_o_v;
}
public String getAt_sts() {
	return at_sts;
}
public void setAt_sts(String at_sts) {
	this.at_sts = at_sts;
}


}
