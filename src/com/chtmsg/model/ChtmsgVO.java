package com.chtmsg.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ChtmsgVO implements Serializable{
	private String 	msg_id;
	private String 	mem_id_send;
	private String 	mem_id_get;
	private Timestamp		msg_time;
	private Integer		msg_sts;
	private String		msg_cnt;
	public ChtmsgVO() {
		super();
	}
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}
	public String getMem_id_send() {
		return mem_id_send;
	}
	public void setMem_id_send(String mem_id_send) {
		this.mem_id_send = mem_id_send;
	}
	public String getMem_id_get() {
		return mem_id_get;
	}
	public void setMem_id_get(String mem_id_get) {
		this.mem_id_get = mem_id_get;
	}
	public Timestamp getMsg_time() {
		return msg_time;
	}
	public void setMsg_time(Timestamp msg_time) {
		this.msg_time = msg_time;
	}
	public Integer getMsg_sts() {
		return msg_sts;
	}
	public void setMsg_sts(Integer msg_sts) {
		this.msg_sts = msg_sts;
	}
	public String getMsg_cnt() {
		return msg_cnt;
	}
	public void setMsg_cnt(String msg_cnt) {
		this.msg_cnt = msg_cnt;
	}
	
	
}
