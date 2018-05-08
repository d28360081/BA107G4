package com.member.model;

import java.io.Serializable;
import java.sql.Date;


public class MemVO implements Serializable{
	private String  mem_id;
	private String 	acc;
	private String 	psw;
	private Date	bir_dt;
	private String	address;
	private Integer	bonus;
	private String	mem_pay;
	private Integer	mem_eva;
	private byte[]	mem_photo;
	private Integer	po_auth;
	private Integer	st_auth;
	private Integer com_auth;
	private Integer	par_auth;
	private String  mem_email;
	private String  mem_name;
	

	public MemVO() {
		super();
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public Date getBir_dt() {
		return bir_dt;
	}
	public void setBir_dt(Date bir_dt) {
		this.bir_dt = bir_dt;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getBonus() {
		return bonus;
	}
	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}
	public String getMem_pay() {
		return mem_pay;
	}
	public void setMem_pay(String mem_pay) {
		this.mem_pay = mem_pay;
	}
	public Integer getMem_eva() {
		return mem_eva;
	}
	public void setMem_eva(Integer mem_eva) {
		this.mem_eva = mem_eva;
	}
	public byte[] getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}
	public Integer getPo_auth() {
		return po_auth;
	}
	public void setPo_auth(Integer po_auth) {
		this.po_auth = po_auth;
	}
	public Integer getSt_auth() {
		return st_auth;
	}
	public void setSt_auth(Integer st_auth) {
		this.st_auth = st_auth;
	}
	public Integer getCom_auth() {
		return com_auth;
	}
	public void setCom_auth(Integer com_auth) {
		this.com_auth = com_auth;
	}
	public Integer getPar_auth() {
		return par_auth;
	}
	public void setPar_auth(Integer par_auth) {
		this.par_auth = par_auth;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	
}

