package com.article.model;

import java.util.List;

public interface ArticleVO_interface {
	public void insert(ArticleVO articleVO);
	public void update(ArticleVO articleVO);
	public void delete(String 	 art_id);
	public void updateLike(ArticleVO articleVO);
	public void updatestatus(ArticleVO articleVO);
	public ArticleVO findByPrimaryKey(String  art_id);
	public List<ArticleVO> getAll();
	public List<ArticleVO> findBymem(String mem_id);
}
