package com.articlecommand.model;


import java.sql.Timestamp;


public class ArtcomVO {
	private	String 	art_msg_id;
	private	String	art_id;
	private String	mem_id;
	private	String	art_msg_cnt;
	private	byte[]	art_msg_pic;
	private Timestamp	art_msg_date;
	private	Integer	art_msg_like_num;
	
	public ArtcomVO() {
		super();
	}

	public String getArt_msg_id() {
		return art_msg_id;
	}

	public void setArt_msg_id(String art_msg_id) {
		this.art_msg_id = art_msg_id;
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

	public String getArt_msg_cnt() {
		return art_msg_cnt;
	}

	public void setArt_msg_cnt(String art_msg_cnt) {
		this.art_msg_cnt = art_msg_cnt;
	}

	public byte[] getArt_msg_pic() {
		return art_msg_pic;
	}

	public void setArt_msg_pic(byte[] art_msg_pic) {
		this.art_msg_pic = art_msg_pic;
	}

	public Timestamp getArt_msg_date() {
		return art_msg_date;
	}

	public void setArt_msg_date(Timestamp art_msg_date) {
		this.art_msg_date = art_msg_date;
	}

	public Integer getArt_msg_like_num() {
		return art_msg_like_num;
	}

	public void setArt_msg_like_num(Integer art_msg_like_num) {
		this.art_msg_like_num = art_msg_like_num;
	}
	
	
}
