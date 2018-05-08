package com.comrf.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Timestamp;

public class ComRfVO implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -1964984515617184889L;
private String com_rf_id;
private String mem_id;
private String emp_no;
private String com_id;
private Integer it_num;
private Integer it_sum;
private String rf_cnt;
private String rf_mt;
private String rf_sts;
private Timestamp rf_date;
private Timestamp rf_e_date;
private Timestamp rf_e_end;
public String getCom_rf_id() {
	return com_rf_id;
}
public void setCom_rf_id(String com_rf_id) {
	this.com_rf_id = com_rf_id;
}
public String getMem_id() {
	return mem_id;
}
public void setMem_id(String mem_id) {
	this.mem_id = mem_id;
}
public String getEmp_no() {
	return emp_no;
}
public void setEmp_no(String emp_no) {
	this.emp_no = emp_no;
}
public String getCom_id() {
	return com_id;
}
public void setCom_id(String com_id) {
	this.com_id = com_id;
}
public Integer getIt_num() {
	return it_num;
}
public void setIt_num(Integer it_num) {
	this.it_num = it_num;
}
public Integer getIt_sum() {
	return it_sum;
}
public void setIt_sum(Integer it_sum) {
	this.it_sum = it_sum;
}
public String getRf_cnt() {
	return rf_cnt;
}
public void setRf_cnt(String rf_cnt) {
	this.rf_cnt = rf_cnt;
}
public String getRf_mt() {
	return rf_mt;
}
public void setRf_mt(String rf_mt) {
	this.rf_mt = rf_mt;
}
public String getRf_sts() {
	return rf_sts;
}
public void setRf_sts(String rf_sts) {
	this.rf_sts = rf_sts;
}
public Timestamp getRf_date() {
	return rf_date;
}
public void setRf_date(Timestamp rf_date) {
	this.rf_date = rf_date;
}
public Timestamp getRf_e_date() {
	return rf_e_date;
}
public void setRf_e_date(Timestamp rf_e_date) {
	this.rf_e_date = rf_e_date;
}
public Timestamp getRf_e_end() {
	return rf_e_end;
}
public void setRf_e_end(Timestamp rf_e_end) {
	this.rf_e_end = rf_e_end;
}

}
