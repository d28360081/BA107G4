package com.commision_appeal.model;

import java.io.Serializable;

public class CommisionAppealVO implements Serializable{
	private String com_apl_id;
	private String mem_id;
	private String emp_no;
	private String com_id;
	private String apl_cnt;
	private String apl_sts;
	private String com_capl_date;
	private String com_rapl;
	private String com_rapldate;
	public String getCom_apl_id() {
		return com_apl_id;
	}
	public void setCom_apl_id(String com_apl_id) {
		this.com_apl_id = com_apl_id;
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
	public String getCom_capl_date() {
		return com_capl_date;
	}
	public void setCom_capl_date(String com_capl_date) {
		this.com_capl_date = com_capl_date;
	}
	public String getCom_rapl() {
		return com_rapl;
	}
	public void setCom_rapl(String com_rapl) {
		this.com_rapl = com_rapl;
	}
	public String getCom_rapldate() {
		return com_rapldate;
	}
	public void setCom_rapldate(String com_rapldate) {
		this.com_rapldate = com_rapldate;
	}
	
	
}
