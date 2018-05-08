package com.article_reply_appeal.model;

import java.util.List;

public interface ArticleReplyAppealDAO_interface {
	public void add(ArticleReplyAppealVO ara);
	public void update(ArticleReplyAppealVO ara);
	public void delete(String art_capl_id);
	ArticleReplyAppealVO findByPK(String art_capl_id);
	List<ArticleReplyAppealVO> getAll();
	List<ArticleReplyAppealVO> getSelected(String art_re_apl_sts);
}
