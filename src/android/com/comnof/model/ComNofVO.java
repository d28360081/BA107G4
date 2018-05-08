package android.com.comnof.model;

import java.sql.Timestamp;

public class ComNofVO {
	private String nof_id;
	  private String mem_id;
	  private String nof_tit;
	  private String nof_cnt;
	  private String nof_sts;
	  private Timestamp nof_time;
	  private String com_id;
	  
		public ComNofVO(){
		  
	  }

		public String getNof_id() {
			return nof_id;
		}

		public void setNof_id(String nof_id) {
			this.nof_id = nof_id;
		}

		public String getMem_id() {
			return mem_id;
		}

		public void setMem_id(String mem_id) {
			this.mem_id = mem_id;
		}

		public String getNof_tit() {
			return nof_tit;
		}

		public void setNof_tit(String nof_tit) {
			this.nof_tit = nof_tit;
		}

		public String getNof_cnt() {
			return nof_cnt;
		}

		public void setNof_cnt(String nof_cnt) {
			this.nof_cnt = nof_cnt;
		}

		public String getNof_sts() {
			return nof_sts;
		}

		public void setNof_sts(String nof_sts) {
			this.nof_sts = nof_sts;
		}

		public Timestamp getNof_time() {
			return nof_time;
		}

		public void setNof_time(Timestamp nof_time) {
			this.nof_time = nof_time;
		}

		public String getCom_id() {
			return com_id;
		}

		public void setCom_id(String com_id) {
			this.com_id = com_id;
		}
	


  
  
}
