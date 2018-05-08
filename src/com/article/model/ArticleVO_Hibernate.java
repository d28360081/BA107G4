package com.article.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.member.model.MemVO;

public class ArticleVO_Hibernate implements Serializable{
	
	private String	art_id;
	private MemVO	memVO;
	private	String	art_tlt;
	private Timestamp	art_date;
	private String	art_cnt;
	private byte[]	art_pic1;
	private byte[]	art_pic2;
	private byte[]	art_pic3;
	private Integer	like_num;
	private String	art_sts;
	
	public ArticleVO_Hibernate() {
		super();
	}

	public String getArt_id() {
		return art_id;
	}

	public void setArt_id(String art_id) {
		this.art_id = art_id;
	}

	public MemVO getMemVO() {
		return memVO;
	}

	public void setMemVO(MemVO memVO) {
		this.memVO = memVO;
	}

	public String getArt_tlt() {
		return art_tlt;
	}

	public void setArt_tlt(String art_tlt) {
		this.art_tlt = art_tlt;
	}

	public Timestamp getArt_date() {
		return art_date;
	}

	public void setArt_date(Timestamp art_date) {
		this.art_date = art_date;
	}

	public String getArt_cnt() {
		return art_cnt;
	}

	public void setArt_cnt(String art_cnt) {
		this.art_cnt = art_cnt;
	}

	public byte[] getArt_pic1() {
		return art_pic1;
	}

	public void setArt_pic1(byte[] art_pic1) {
		this.art_pic1 = art_pic1;
	}

	public byte[] getArt_pic2() {
		return art_pic2;
	}

	public void setArt_pic2(byte[] art_pic2) {
		this.art_pic2 = art_pic2;
	}

	public byte[] getArt_pic3() {
		return art_pic3;
	}

	public void setArt_pic3(byte[] art_pic3) {
		this.art_pic3 = art_pic3;
	}

	public Integer getLike_num() {
		return like_num;
	}

	public void setLike_num(Integer like_num) {
		this.like_num = like_num;
	}

	public String getArt_sts() {
		return art_sts;
	}

	public void setArt_sts(String art_sts) {
		this.art_sts = art_sts;
	}
	
}
