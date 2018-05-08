package com.article.model;

import java.util.List;

public interface ArticleVO_h_interface {
	public void insert(ArticleVO_Hibernate articleVO_h);
	public void update(ArticleVO_Hibernate articleVO_h);
	public void delete(String 	 art_id);
	public void updateLike(ArticleVO_Hibernate articleVO_h);
	public void updatestatus(ArticleVO_Hibernate articleVO_h);
	public ArticleVO_Hibernate findByPrimaryKey(String  art_id);
	public List<ArticleVO_Hibernate> searchArt(String value);
	public List<ArticleVO_Hibernate> getAll();
	public List<ArticleVO_Hibernate> findBymem(String mem_id);
}
