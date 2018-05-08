package com.news.model;

import java.io.Serializable;

public class NewsVO implements Serializable {
	private String ns_id;
	private String emp_no;
	private String ns_tit;
	private String ns_cnt;
	private String ns_date;
	private Integer ns_like;
	private String ns_cdate;
	private String ns_sts;
	private String mem_id;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getNs_id() {
		return ns_id;
	}
	public void setNs_id(String ns_id) {
		this.ns_id = ns_id;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getNs_tit() {
		return ns_tit;
	}
	public void setNs_tit(String ns_tit) {
		this.ns_tit = ns_tit;
	}
	public String getNs_cnt() {
		return ns_cnt;
	}
	public void setNs_cnt(String ns_cnt) {
		this.ns_cnt = ns_cnt;
	}
	public String getNs_date() {
		return ns_date;
	}
	public void setNs_date(String ns_date) {
		this.ns_date = ns_date;
	}
	public Integer getNs_like() {
		return ns_like;
	}
	public void setNs_like(Integer ns_like) {
		this.ns_like = ns_like;
	}
	public String getNs_cdate() {
		return ns_cdate;
	}
	public void setNs_cdate(String ns_cdate) {
		this.ns_cdate = ns_cdate;
	}
	public String getNs_sts() {
		return ns_sts;
	}
	public void setNs_sts(String ns_sts) {
		this.ns_sts = ns_sts;
	}
	
}
	
