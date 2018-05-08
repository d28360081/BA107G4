package com.pmt_det.model;

import java.io.Serializable;

public class PmtDetVO implements Serializable{
	private String pmt_id;
	private String it_id;

	
	public String getPmt_id() {
		return pmt_id;
	}
	public void setPmt_id(String pmt_id) {
		this.pmt_id = pmt_id;
	}
	public String getIt_id() {
		return it_id;
	}
	public void setIt_id(String it_id) {
		this.it_id = it_id;
	}
	@Override
	public String toString() {
		return "PmtDetVO [pmt_id=" + pmt_id + ", it_id=" + it_id + "]";
	}

	
	
}
