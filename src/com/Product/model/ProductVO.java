package com.Product.model;

import javax.servlet.http.Part;

public class ProductVO  implements java.io.Serializable{
	private String it_id;
	private String st_id;
	private Integer it_num;
	private String it_name;
	private String it_intro;
	private byte[] it_pic;
	private Double it_prc;
	private String it_cate;
	
	public String getIt_cate() {
		return it_cate;
	}

	public void setIt_cate(String it_cate) {
		this.it_cate = it_cate;
	}

	private String it_sts;

	
	
	public String getIt_id() {
		return it_id;
	}

	public void setIt_id(String it_id) {
		this.it_id = it_id;
	}

	public String getSt_id() {
		return st_id;
	}

	public void setSt_id(String st_id) {
		this.st_id = st_id;
	}

	public Integer getIt_num() {
		return it_num;
	}

	public void setIt_num(Integer it_num) {
		this.it_num = it_num;
	}

	public String getIt_name() {
		return it_name;
	}

	public void setIt_name(String it_name) {
		this.it_name = it_name;
	}

	public String getIt_intro() {
		return it_intro;
	}

	public void setIt_intro(String it_intro) {
		this.it_intro = it_intro;
	}

	public byte[] getIt_pic() {
		return it_pic;
	}

	public void setIt_pic(byte[] it_pic) {
		this.it_pic = it_pic;
	}

	public Double getIt_prc() {
		return it_prc;
	}

	public void setIt_prc(Double it_prc) {
		this.it_prc = it_prc;
	}

	public String getIt_sts() {
		return it_sts;
	}

	public void setIt_sts(String it_sts) {
		this.it_sts = it_sts;
	}

	public ProductVO(){
		
	}
	
	
}
