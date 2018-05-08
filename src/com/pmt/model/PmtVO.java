package com.pmt.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class PmtVO implements Serializable{
	private String pmt_id;
	private String emp_no;
	private String pmt_name;
	private String pmt_intro;	
	private byte[] pmt_pic;
	private Timestamp pmt_s_date;
	private Timestamp pmt_e_date;
	private String pmt_sts;	
	private Double pmt_discount;
	
	public String getPmt_id() {
		return pmt_id;
	}
	public void setPmt_id(String pmt_id) {
		this.pmt_id = pmt_id;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getPmt_name() {
		return pmt_name;
	}
	public void setPmt_name(String pmt_name) {
		this.pmt_name = pmt_name;
	}
	public String getPmt_intro() {
		return pmt_intro;
	}
	public void setPmt_intro(String pmt_intro) {
		this.pmt_intro = pmt_intro;
	}	
	public Timestamp getPmt_s_date() {
		return pmt_s_date;
	}
	public void setPmt_s_date(Timestamp pmt_s_date) {
		this.pmt_s_date = pmt_s_date;
	}
	public Timestamp getPmt_e_date() {
		return pmt_e_date;
	}
	public void setPmt_e_date(Timestamp pmt_e_date) {
		this.pmt_e_date = pmt_e_date;
	}
	public String getPmt_sts() {
		return pmt_sts;
	}
	public void setPmt_sts(String pmt_sts) {
		this.pmt_sts = pmt_sts;
	}
	public byte[] getPmt_pic() {
		return pmt_pic;
	}
	public void setPmt_pic(byte[] pmt_pic) {
		this.pmt_pic = pmt_pic;
	}
	public Double getPmt_discount() {
		return pmt_discount;
	}
	public void setPmt_discount(Double pmt_discount) {
		this.pmt_discount = pmt_discount;
	}
	
}
