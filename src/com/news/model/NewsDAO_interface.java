package com.news.model;

import java.util.List;

public interface NewsDAO_interface {
	public String add(NewsVO ns);
	public void update(NewsVO ns);
	public void delete(String ns_id);
	public void updatests(NewsVO ns);
	public void updatelike(NewsVO ns);
	NewsVO findByPK(String ns_id);
	List<NewsVO> getAll();
	List<NewsVO>selectsts(String ns_sts);

}
