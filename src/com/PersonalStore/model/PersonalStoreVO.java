package com.PersonalStore.model;

public class PersonalStoreVO implements java.io.Serializable {
	private String st_id;
	private String mem_id;
	private String st_name;
	private String st_intro;
	private String st_sts;
	
	public String getSt_id() {
		return st_id;
	}

	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public String getSt_intro() {
		return st_intro;
	}

	public void setSt_intro(String st_intro) {
		this.st_intro = st_intro;
	}

	public String getSt_sts() {
		return st_sts;
	}

	public void setSt_sts(String st_sts) {
		this.st_sts = st_sts;
	}

	
	public PersonalStoreVO(){
		
	}
	
}
