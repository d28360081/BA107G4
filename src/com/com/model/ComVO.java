package com.com.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

import timeCount.TimeObject;

public class ComVO implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 3315379270647509947L;
private String  com_id;
private String  mem_id;
private String com_tit;
private String com_cnt;
private Timestamp com_s_dt;
private Timestamp com_e_dt;
private String com_sts;
private Timestamp lmt_m_dt;
private String lmt_lcl;
private Integer lmt_atd_clv;
private Integer lmt_auc_lv;
private Integer com_min_num;
private Integer com_mx_num;
private Integer lmt_m_prc;
private Integer lmt_del_prc;
private Timestamp pur_s_date;
private Timestamp pur_e_date;
private byte[] com_pic1;
private byte[] com_pic2;
private byte[] it_chk_pic1;
private String com_it_sts;
private Integer bns_number;
private Integer com_prc;
private String it_sz;
private String it_col;
private String com_rmd;
private TimeObject timeObject;



public long getEdt(){
	return this.getCom_e_dt().getTime();
}



public String getCom_id() {
	return com_id;
}
public void setCom_id(String com_id) {
	this.com_id = com_id;
}
public String getMem_id() {
	return mem_id;
}
public void setMem_id(String mem_id) {
	this.mem_id = mem_id;
}
public String getCom_tit() {
	return com_tit;
}
public void setCom_tit(String com_tit) {
	this.com_tit = com_tit;
}
public String getCom_cnt() {
	return com_cnt;
}
public void setCom_cnt(String com_cnt) {
	this.com_cnt = com_cnt;
}
public Timestamp getCom_s_dt() {
	return com_s_dt;
}
public void setCom_s_dt(Timestamp com_s_dt) {
	this.com_s_dt = com_s_dt;
}
public Timestamp getCom_e_dt() {
	return com_e_dt;
}
public void setCom_e_dt(Timestamp com_e_dt) {
	this.com_e_dt = com_e_dt;
}
public String getCom_sts() {
	return com_sts;
}
public void setCom_sts(String com_sts) {
	this.com_sts = com_sts;
}
public Timestamp getLmt_m_dt() {
	return lmt_m_dt;
}
public void setLmt_m_dt(Timestamp lmt_m_dt) {
	this.lmt_m_dt = lmt_m_dt;
}
public String getLmt_lcl() {
	return lmt_lcl;
}
public void setLmt_lcl(String lmt_lcl) {
	this.lmt_lcl = lmt_lcl;
}
public Integer getLmt_atd_clv() {
	return lmt_atd_clv;
}
public void setLmt_atd_clv(Integer lmt_atd_clv) {
	this.lmt_atd_clv = lmt_atd_clv;
}
public Integer getLmt_auc_lv() {
	return lmt_auc_lv;
}
public void setLmt_auc_lv(Integer lmt_auc_lv) {
	this.lmt_auc_lv = lmt_auc_lv;
}
public Integer getCom_min_num() {
	return com_min_num;
}
public void setCom_min_num(Integer com_min_num) {
	this.com_min_num = com_min_num;
}
public Integer getCom_mx_num() {
	return com_mx_num;
}
public void setCom_mx_num(Integer com_mx_num) {
	this.com_mx_num = com_mx_num;
}
public Integer getLmt_m_prc() {
	return lmt_m_prc;
}
public void setLmt_m_prc(Integer lmt_m_prc) {
	this.lmt_m_prc = lmt_m_prc;
}
public Integer getLmt_del_prc() {
	return lmt_del_prc;
}
public void setLmt_del_prc(Integer lmt_del_prc) {
	this.lmt_del_prc = lmt_del_prc;
}
public Timestamp getPur_s_date() {
	return pur_s_date;
}
public void setPur_s_date(Timestamp pur_s_date) {
	this.pur_s_date = pur_s_date;
}
public Timestamp getPur_e_date() {
	return pur_e_date;
}
public void setPur_e_date(Timestamp pur_e_date) {
	this.pur_e_date = pur_e_date;
}
public byte[] getCom_pic1() {
	return com_pic1;
}
public void setCom_pic1(byte[] com_pic1) {
	this.com_pic1 = com_pic1;
}
public byte[] getCom_pic2() {
	return com_pic2;
}
public void setCom_pic2(byte[] com_pic2) {
	this.com_pic2 = com_pic2;
}
public byte[] getIt_chk_pic1() {
	return it_chk_pic1;
}
public void setIt_chk_pic1(byte[] it_chk_pic1) {
	this.it_chk_pic1 = it_chk_pic1;
}
public String getCom_it_sts() {
	return com_it_sts;
}
public void setCom_it_sts(String com_it_sts) {
	this.com_it_sts = com_it_sts;
}
public Integer getBns_number() {
	return bns_number;
}
public void setBns_number(Integer bns_number) {
	this.bns_number = bns_number;
}
public Integer getCom_prc() {
	return com_prc;
}
public void setCom_prc(Integer com_prc) {
	this.com_prc = com_prc;
}
public String getIt_sz() {
	return it_sz;
}
public void setIt_sz(String it_sz) {
	this.it_sz = it_sz;
}
public String getIt_col() {
	return it_col;
}
public void setIt_col(String it_col) {
	this.it_col = it_col;
}
public String getCom_rmd() {
	return com_rmd;
}
public void setCom_rmd(String com_rmd) {
	this.com_rmd = com_rmd;
}
public TimeObject getTimeObject() {
	return timeObject;
}
public void setTimeObject(TimeObject timeObject) {
	this.timeObject = timeObject;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}




}
