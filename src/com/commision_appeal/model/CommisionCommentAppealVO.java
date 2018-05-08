package com.commision_appeal.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

public class CommisionCommentAppealVO implements Serializable{
	private String com_capl_id;
	private String com_id;
	private String mem_id_rp;
	private String mem_id_rpd;
	private String emp_no;
	private String apl_cnt;
	private String apl_sts;
	private String comt_id;
	private String com_capl_date;
	private String com_rcapl;
	private String com_cdate;
	public String getCom_capl_id() {
		return com_capl_id;
	}
	public void setCom_capl_id(String com_capl_id) {
		this.com_capl_id = com_capl_id;
	}
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getMem_id_rp() {
		return mem_id_rp;
	}
	public void setMem_id_rp(String mem_id_rp) {
		this.mem_id_rp = mem_id_rp;
	}
	public String getMem_id_rpd() {
		return mem_id_rpd;
	}
	public void setMem_id_rpd(String mem_id_rpd) {
		this.mem_id_rpd = mem_id_rpd;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getApl_cnt() {
		return apl_cnt;
	}
	public void setApl_cnt(String apl_cnt) {
		this.apl_cnt = apl_cnt;
	}
	public String getApl_sts() {
		return apl_sts;
	}
	public void setApl_sts(String apl_sts) {
		this.apl_sts = apl_sts;
	}
	public String getComt_id() {
		return comt_id;
	}
	public void setComt_id(String comt_id) {
		this.comt_id = comt_id;
	}
	public String getCom_capl_date() {
		return com_capl_date;
	}
	public void setCom_capl_date(String com_capl_date) {
		this.com_capl_date = com_capl_date;
	}
	public String getCom_rcapl() {
		return com_rcapl;
	}
	public void setCom_rcapl(String com_rcapl) {
		this.com_rcapl = com_rcapl;
	}
	public String getCom_cdate() {
		return com_cdate;
	}
	public void setCom_cdate(String com_cdate) {
		this.com_cdate = com_cdate;
	}
	
	
}
