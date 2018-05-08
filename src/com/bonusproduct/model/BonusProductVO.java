package com.bonusproduct.model;

public class BonusProductVO {
	private String bns_it_id;
	private String emp_no;
	private String bns_it_name;
	private String bns_it_intro;
	private byte[] bns_in_pic;
	private Integer bns_it_prc;
	private Integer bns_it_strg;
	private String bns_it_sts;
	private Integer bns_it_cash;
	
	public String getBns_it_id() {
		return bns_it_id;
	}

	public void setBns_it_id(String bns_it_id) {
		this.bns_it_id = bns_it_id;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getBns_it_name() {
		return bns_it_name;
	}

	public void setBns_it_name(String bns_it_name) {
		this.bns_it_name = bns_it_name;
	}

	public String getBns_it_intro() {
		return bns_it_intro;
	}

	public void setBns_it_intro(String bns_it_intro) {
		this.bns_it_intro = bns_it_intro;
	}

	public byte[] getBns_in_pic() {
		return bns_in_pic;
	}

	public void setBns_in_pic(byte[] bns_in_pic) {
		this.bns_in_pic = bns_in_pic;
	}

	public Integer getBns_it_prc() {
		return bns_it_prc;
	}

	public void setBns_it_prc(Integer bns_it_prc) {
		this.bns_it_prc = bns_it_prc;
	}

	public Integer getBns_it_cash() {
		return bns_it_cash;
	}

	public void setBns_it_cash(Integer bns_it_cash) {
		this.bns_it_cash = bns_it_cash;
	}

	public Integer getBns_it_strg() {
		return bns_it_strg;
	}

	public void setBns_it_strg(Integer bns_it_strg) {
		this.bns_it_strg = bns_it_strg;
	}

	public String getBns_it_sts() {
		return bns_it_sts;
	}

	public void setBns_it_sts(String bns_it_sts) {
		this.bns_it_sts = bns_it_sts;
	}

	
	
	public BonusProductVO() {
		super();
	}

	
}