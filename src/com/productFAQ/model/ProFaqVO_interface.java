package com.productFAQ.model;

import java.util.List;

public interface ProFaqVO_interface {
	public void insert(ProFaqVO proFaqVO);
	public void update(ProFaqVO proFaqVO);
	public void delete(String	faq_id);
	public ProFaqVO findByPrimaryKey(String faq_id);
	public List<ProFaqVO> getAll();
}
