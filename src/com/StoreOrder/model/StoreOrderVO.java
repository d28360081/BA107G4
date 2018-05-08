package com.StoreOrder.model;

import java.sql.Date;

public class StoreOrderVO implements java.io.Serializable{
	private String rec_id;
	private String mem_id;
	private String st_id;
	private String buyer_name;
	private String mem_email;
	private String rec_delivery;
	private Double payment;
	private String rec_py;
	private Date rec_date;
	private String rec_dlv_sts;
	private String mem_add;
	private String mem_ph;
	private Integer usd_bns;
	private String dlv_id;
	
	public StoreOrderVO(){
		super();
	}

	public String getBuyer_name() {
		return buyer_name;
	}
	
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getRec_id() {
		return rec_id;
	}

	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getRec_delivery() {
		return rec_delivery;
	}

	public void setRec_delivery(String rec_delivery) {
		this.rec_delivery = rec_delivery;
	}

	public String getSt_id() {
		return st_id;
	}

	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public String getRec_py() {
		return rec_py;
	}

	public void setRec_py(String rec_py) {
		this.rec_py = rec_py;
	}

	public Date getRec_date() {
		return rec_date;
	}

	public void setRec_date(Date rec_date) {
		this.rec_date = rec_date;
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

	public Integer getUsd_bns() {
		return usd_bns;
	}

	public void setUsd_bns(Integer usd_bns) {
		this.usd_bns = usd_bns;
	}

	public String getDlv_id() {
		return dlv_id;
	}

	public void setDlv_id(String dlv_id) {
		this.dlv_id = dlv_id;
	}

	
}
