package android.com.promotion.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PromotionVO implements Serializable{
	private String pmt_id;
	private String emp_no;
	private String pmt_name;
	private String pmt_intro;//CLOB
	private String pmt_sts;
	private byte[] pmtpic;//BLOB
	private Timestamp pmt_s_date;
	private Timestamp pmt_e_date;
	public PromotionVO() {
		super();
	}
	public PromotionVO(String pmt_id, String emp_no, String pmt_name, String pmt_intro, String pmt_sts, byte[] pmtpic,
			Timestamp pmt_s_date, Timestamp pmt_e_date) {
		super();
		this.pmt_id = pmt_id;
		this.emp_no = emp_no;
		this.pmt_name = pmt_name;
		this.pmt_intro = pmt_intro;
		this.pmt_sts = pmt_sts;
		this.pmtpic = pmtpic;
		this.pmt_s_date = pmt_s_date;
		this.pmt_e_date = pmt_e_date;
	}
	public String getPmt_id() {
		return pmt_id;
	}
	public void setPmt_id(String pmt_id) {
		this.pmt_id = pmt_id;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getPmt_name() {
		return pmt_name;
	}
	public void setPmt_name(String pmt_name) {
		this.pmt_name = pmt_name;
	}
	public String getPmt_intro() {
		return pmt_intro;
	}
	public void setPmt_intro(String pmt_intro) {
		this.pmt_intro = pmt_intro;
	}
	public String getPmt_sts() {
		return pmt_sts;
	}
	public void setPmt_sts(String pmt_sts) {
		this.pmt_sts = pmt_sts;
	}
	public byte[] getPmtpic() {
		return pmtpic;
	}
	public void setPmtpic(byte[] pmtpic) {
		this.pmtpic = pmtpic;
	}
	public Timestamp getPmt_s_date() {
		return pmt_s_date;
	}
	public void setPmt_s_date(Timestamp pmt_s_date) {
		this.pmt_s_date = pmt_s_date;
	}
	public Timestamp getPmt_e_date() {
		return pmt_e_date;
	}
	public void setPmt_e_date(Timestamp pmt_e_date) {
		this.pmt_e_date = pmt_e_date;
	}
	
	
	
	
	
	
}
