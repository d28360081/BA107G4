package com.article_appeal.model;

import java.util.List;

public interface ArticleAppealDAO_interface {

	public void add(ArticleAppealVO aav);
	public void update(ArticleAppealVO aav);
	public void delete(String art_apl_id);
	ArticleAppealVO findByPK(String art_apl_id);
	List<ArticleAppealVO>getAll();
	List<ArticleAppealVO>getSelected(String art_apl_sts);
}
