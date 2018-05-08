package com.article_appeal.model;
import java.io.Serializable;

public class ArticleAppealVO implements Serializable{
	private String art_apl_id;
	private String art_id;
	private String mem_id;
	private String emp_no;
	private String art_apl_cnt;
	private String art_apl_sts;
	private String art_apl_date;
	private String art_rapl;
	private String art_rapldate;
	public String getArt_apl_id() {
		return art_apl_id;
	}
	public void setArt_apl_id(String art_apl_id) {
		this.art_apl_id = art_apl_id;
	}
	public String getArt_id() {
		return art_id;
	}
	public void setArt_id(String art_id) {
		this.art_id = art_id;
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
	public String getArt_apl_cnt() {
		return art_apl_cnt;
	}
	public void setArt_apl_cnt(String art_apl_cnt) {
		this.art_apl_cnt = art_apl_cnt;
	}
	public String getArt_apl_sts() {
		return art_apl_sts;
	}
	public void setArt_apl_sts(String art_apl_sts) {
		this.art_apl_sts = art_apl_sts;
	}
	public String getArt_apl_date() {
		return art_apl_date;
	}
	public void setArt_apl_date(String art_apl_date) {
		this.art_apl_date = art_apl_date;
	}
	public String getArt_rapl() {
		return art_rapl;
	}
	public void setArt_rapl(String art_rapl) {
		this.art_rapl = art_rapl;
	}
	public String getArt_rapldate() {
		return art_rapldate;
	}
	public void setArt_rapldate(String art_rapldate) {
		this.art_rapldate = art_rapldate;
	}
	
}