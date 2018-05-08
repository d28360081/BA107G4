package android.com.comt.model;

import java.io.Serializable;

public class ComComtVO implements Serializable{
	 private String  com_id;
	 private String  comt_id;
	 private String  mem_id;
	 private String comt_cnt;
	 private byte[] comt_art_pic1;
	 private byte[] comt_art_pic2;
	 
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	public String getComt_id() {
		return comt_id;
	}
	public void setComt_id(String comt_id) {
		this.comt_id = comt_id;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getComt_cnt() {
		return comt_cnt;
	}
	public void setComt_cnt(String comt_cnt) {
		this.comt_cnt = comt_cnt;
	}
	public byte[] getComt_art_pic1() {
		return comt_art_pic1;
	}
	public void setComt_art_pic1(byte[] comt_art_pic1) {
		this.comt_art_pic1 = comt_art_pic1;
	}
	public byte[] getComt_art_pic2() {
		return comt_art_pic2;
	}
	public void setComt_art_pic2(byte[] comt_art_pic2) {
		this.comt_art_pic2 = comt_art_pic2;
	}
	 

}
