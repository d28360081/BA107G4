package com.StoreReceiptDetail.model;

public class StoreReceiptDetailVO implements java.io.Serializable {
	private String rec_id;
	private String it_id;
	private Integer it_num;
	
	public String getRec_id() {
		return rec_id;
	}

	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}

	public String getIt_id() {
		return it_id;
	}

	public void setIt_id(String it_id) {
		this.it_id = it_id;
	}

	public Integer getIt_num() {
		return it_num;
	}

	public void setIt_num(Integer it_num) {
		this.it_num = it_num;
	}

	public StoreReceiptDetailVO(){
		
	}
	
}
