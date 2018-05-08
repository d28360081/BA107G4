package com.comauth.model;

public class ComQrVO {
 String com_id;
 String mem_id;
 byte[] qr_code;
 String auth_number;
 
public String getMem_id() {
	return mem_id;
}
public void setMem_id(String mem_id) {
	this.mem_id = mem_id;
}
public String getCom_id() {
	return com_id;
}
public void setCom_id(String com_id) {
	this.com_id = com_id;
}
public byte[] getQr_code() {
	return qr_code;
}
public void setQr_code(byte[] qr_code) {
	this.qr_code = qr_code;
}
public String getAuth_number() {
	return auth_number;
}
public void setAuth_number(String auth_number) {
	this.auth_number = auth_number;
}
}
