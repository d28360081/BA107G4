package com.article_appeal.model;

import java.util.List;

public class ArticleAppealListVO {

	private String art_apl_sts;

	private List<ArticleAppealVO> aaVOList;

	public String getArt_apl_sts() {
		return art_apl_sts;
	}

	public void setArt_apl_sts(String art_apl_sts) {
		this.art_apl_sts = art_apl_sts;
	}

	public List<ArticleAppealVO> getAaVOList() {
		return aaVOList;
	}

	public void setAaVOList(List<ArticleAppealVO> aaVOList) {
		this.aaVOList = aaVOList;
	}

}
