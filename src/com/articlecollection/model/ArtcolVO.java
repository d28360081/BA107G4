package com.articlecollection.model;

import java.io.Serializable;

public class ArtcolVO implements Serializable{
	private	String 	mem_id;
	private String	art_id;
	public ArtcolVO() {
		super();
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getArt_id() {
		return art_id;
	}
	public void setArt_id(String art_id) {
		this.art_id = art_id;
	}
	
	
}
