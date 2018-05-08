package com.bonusproductorder.model;

import java.sql.Date;

public class BonusProductOrderVO {
	private String bns_rec_id;
	private String mem_id;
	private Date   pur_date;
	private Integer sum_prc;
	private String rec_dlv_sts;
	private String mem_add;
	private String mem_ph;
	private String mem_name;
	
	public BonusProductOrderVO() {
		super();
	}

	public String getBns_rec_id() {
		return bns_rec_id;
	}

	public void setBns_rec_id(String bns_rec_id) {
		this.bns_rec_id = bns_rec_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public Date getPur_date() {
		return pur_date;
	}

	public void setPur_date(Date pur_date) {
		this.pur_date = pur_date;
	}

	public Integer getSum_prc() {
		return sum_prc;
	}

	public void setSum_prc(Integer sum_prc) {
		this.sum_prc = sum_prc;
	}

	public String getRec_dlv_sts() {
		return rec_dlv_sts;
	}

	public void setRec_dlv_sts(String rec_dlv_sts) {
		this.rec_dlv_sts = rec_dlv_sts;
	}

	public String getMem_add() {
		return mem_add;
	}

	public void setMem_add(String mem_add) {
		this.mem_add = mem_add;
	}

	public String getMem_ph() {
		return mem_ph;
	}

	public void setMem_ph(String mem_ph) {
		this.mem_ph = mem_ph;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	
	
}