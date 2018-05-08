package com.productFAQ.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ProFaqVO implements Serializable{
		private String faq_id;
		private String it_id;
		private String mem_id;
		private String faq_cnt;
		private Timestamp	faq_date;
		
		public ProFaqVO (){
			super();
		}

		public String getFaq_id() {
			return faq_id;
		}

		public void setFaq_id(String faq_id) {
			this.faq_id = faq_id;
		}

		public String getIt_id() {
			return it_id;
		}

		public void setIt_id(String it_id) {
			this.it_id = it_id;
		}

		public String getMem_id() {
			return mem_id;
		}

		public void setMem_id(String mem_id) {
			this.mem_id = mem_id;
		}

		public String getFaq_cnt() {
			return faq_cnt;
		}

		public void setFaq_cnt(String faq_cnt) {
			this.faq_cnt = faq_cnt;
		}

		public Timestamp getFaq_date() {
			return faq_date;
		}

		public void setFaq_date(Timestamp faq_date) {
			this.faq_date = faq_date;
		}
		
		
	
}
