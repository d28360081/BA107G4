package com.product_appeal.model;

import java.io.Serializable;

public class ProductAppealVO implements Serializable{

	private String it_apl_id;
	private String mem_id;
	private String emp_no;
	private String it_id;
	private String it_apl_cnt;
	private String apl_sts;
	private String pa_date;
	private String it_rapl;
	private String it_rdate;
	public String getIt_apl_id() {
		return it_apl_id;
	}
	public void setIt_apl_id(String it_apl_id) {
		this.it_apl_id = it_apl_id;
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
	public String getIt_id() {
		return it_id;
	}
	public void setIt_id(String it_id) {
		this.it_id = it_id;
	}
	public String getIt_apl_cnt() {
		return it_apl_cnt;
	}
	public void setIt_apl_cnt(String it_apl_cnt) {
		this.it_apl_cnt = it_apl_cnt;
	}
	public String getApl_sts() {
		return apl_sts;
	}
	public void setApl_sts(String apl_sts) {
		this.apl_sts = apl_sts;
	}
	public String getPa_date() {
		return pa_date;
	}
	public void setPa_date(String pa_date) {
		this.pa_date = pa_date;
	}
	public String getIt_rapl() {
		return it_rapl;
	}
	public void setIt_rapl(String it_rapl) {
		this.it_rapl = it_rapl;
	}
	public String getIt_rdate() {
		return it_rdate;
	}
	public void setIt_rdate(String it_rdate) {
		this.it_rdate = it_rdate;
	}
	
}
