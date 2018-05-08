package com.articlereply.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ArtreVO implements Serializable{
	private String		re_id;
	private String		art_id;
	private String		mem_id;
	private String		re_cnt;
	private Timestamp		re_date;
	private	byte[]		re_pic;
	private String		re_sts;
	
	public ArtreVO() {
		super();
	}

	public String getRe_id() {
		return re_id;
	}

	public void setRe_id(String re_id) {
		this.re_id = re_id;
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

	public String getRe_cnt() {
		return re_cnt;
	}

	public void setRe_cnt(String re_cnt) {
		this.re_cnt = re_cnt;
	}

	public Timestamp getRe_date() {
		return re_date;
	}

	public void setRe_date(Timestamp re_date) {
		this.re_date = re_date;
	}

	public byte[] getRe_pic() {
		return re_pic;
	}

	public void setRe_pic(byte[] re_pic) {
		this.re_pic = re_pic;
	}

	public String getRe_sts() {
		return re_sts;
	}

	public void setRe_sts(String re_sts) {
		this.re_sts = re_sts;
	}
	
	
}
